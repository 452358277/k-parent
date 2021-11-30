package com.ppmg.ei.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.action.BaseAction;
import com.founder.ssm.core.exception.SystemException;
import com.ppmg.common.model.CommTCodeModel;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.model.TreeNodeBean;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
@Api(tags={"获取行业接口"})
public class GetTreeController extends BaseAction {


    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name="codeUtils")
    private CodeUtils codeUtils;

    @GetMapping(value = "/queryCodeList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String queryCodeList(String parentId){
        List<TreeNodeBean> tree = new ArrayList<TreeNodeBean>();
        List<TreeNodeBean> menu2List = new ArrayList<TreeNodeBean>();//存放所有二级菜单
        try {
            List<CommTCodeModel> list = codeUtils.getCodeByParentId(parentId);
            if(list!=null && !list.isEmpty()){
                for(CommTCodeModel commTCodeModel:list){
                    if(StringUtils.isNotEmpty(commTCodeModel.getCodeValue())){
                        if(commTCodeModel.getCodeValue().toString().length()==2){
                            TreeNodeBean bean = new TreeNodeBean();
                            bean.setId(commTCodeModel.getCodeValue());
                            bean.setText(commTCodeModel.getCodeName());
                            bean.setState("closed");//一级菜单默认收起来
                            tree.add(bean);
                        }
                        //二级菜单,0101,0102
                        if(commTCodeModel.getCodeValue().toString().length() == 4){
                            TreeNodeBean bean = new TreeNodeBean();
                            bean.setId(commTCodeModel.getCodeValue());
                            bean.setText(commTCodeModel.getCodeName());
                            menu2List.add(bean);
                        }
                    }

                }


            }
            //将二级菜单关联到一级菜单
            if(menu2List!=null&& !menu2List.isEmpty()){
                for(TreeNodeBean menu2 : menu2List){
                    String menu2Id = menu2.getId();
                    for(TreeNodeBean menu1 : tree){
                        String menu1Id = menu1.getId();
                        if(menu1Id.equals(menu2Id.substring(0, 2))){
                            menu1.getChildren().add(menu2);
                        }
                    }
                }
            }


            mapResponse.put("tree", tree);
        } catch (SystemException e) {
            logger.error(e.getMessage());
            mapResponse.error(e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            logger.error(e.getMessage());
            mapResponse.error(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }



}
