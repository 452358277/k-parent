package com.ppmg.ei.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.vo.ButtonsVO;
import com.ppmg.ei.vo.CommTGicsVO;
import com.ppmg.ei.vo.FuncPermissionMetaVO;
import com.ppmg.ei.vo.FuncPermissionVO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.founder.ssm.core.vo.BaseVO;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.action.BaseAction;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.CommTGicsModel;
import com.ppmg.ei.service.CommTGicsService;
@Controller
@Scope("prototype")
@Api(tags={"所属行业口"})
public class CommTGicsController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private CommTGicsService commTGicsService;

	@ApiOperation(value="获取所属行业", notes="获取所属行业")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/selectGicsList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectGicsList(){
		try {
			List<CommTGicsVO> funcPermissions = new ArrayList<CommTGicsVO>();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("PARENT_ID", "0");
			params.put("GICS_LEVEL", "1");
			//查询树的第一层
			List<Map<String,Object>> list=commTGicsService.getGicsList(params);
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					List<CommTGicsVO> children = new ArrayList<CommTGicsVO>();
						String id=String.valueOf(list.get(i).get("ID"));
						String parentId=String.valueOf(list.get(i).get("PARENT_ID"));
						String gicsName=String.valueOf(list.get(i).get("GICS_NAME"));
					    CommTGicsVO commTGicsVO= new CommTGicsVO();
					    commTGicsVO.setGicsName(gicsName);
					    commTGicsVO.setId(id);
					    commTGicsVO.setParentId(parentId);

					   CommTGicsVO CommTGicsVOChildrenVO = new CommTGicsVO();
					    children.add(CommTGicsVOChildrenVO);
					    commTGicsVO.setChildren(children);
					    funcPermissions.add(commTGicsVO);
					Map<String, Object> params1 = new HashMap<String, Object>();
					params1.put("PARENT_ID", id);
					params1.put("GICS_LEVEL", "2");
					List<Map<String,Object>> list2=commTGicsService.getGicsList(params1);
					List<CommTGicsVO> children2;
					if(list2.size()>0){
						children2 = new ArrayList<CommTGicsVO>();
						CommTGicsVOChildrenVO.setChildren(children2);
					}
					//查询第二层
					if(list2!=null&&list2.size()>0){
						for(int b=0;b<list2.size();b++){
							List<CommTGicsVO> children1 = new ArrayList<CommTGicsVO>();
							String id1=String.valueOf(list2.get(b).get("ID"));
							String parentId1=String.valueOf(list2.get(b).get("PARENT_ID"));
							String gicsName1=String.valueOf(list2.get(b).get("GICS_NAME"));
							CommTGicsVO CommTGicsVOChildrenVO1 = new CommTGicsVO();
							CommTGicsVOChildrenVO1.setId(id1);
							CommTGicsVOChildrenVO1.setParentId(parentId1);
							CommTGicsVOChildrenVO1.setGicsName(gicsName1);

							CommTGicsVO CommTGicsVOChildrenVO2 = new CommTGicsVO();
							children1.add(CommTGicsVOChildrenVO2);
							//commTGicsVO.setChildren(children);
							CommTGicsVOChildrenVO1.setChildren(children1);
							funcPermissions.get(i).getChildren().get(0).getChildren().add(CommTGicsVOChildrenVO1);
							//查询第三层数
							Map<String, Object> params2 = new HashMap<String, Object>();
							params2.put("PARENT_ID", id1);
							params2.put("GICS_LEVEL", "3");
							List<Map<String,Object>> list3=commTGicsService.getGicsList(params2);
							List<CommTGicsVO> children3;
							if(list3.size()>0){
								children3 = new ArrayList<CommTGicsVO>();
								CommTGicsVOChildrenVO2.setChildren(children3);
							}
							if(list3!=null&&list3.size()>0){
								for(int bb=0;bb<list3.size();bb++){
									List<CommTGicsVO> children4 = new ArrayList<CommTGicsVO>();
									String id4=String.valueOf(list3.get(bb).get("ID"));
									String parentId4=String.valueOf(list3.get(bb).get("PARENT_ID"));
									String gicsName4=String.valueOf(list3.get(bb).get("GICS_NAME"));
									CommTGicsVO CommTGicsVOChildrenVO4 = new CommTGicsVO();
									CommTGicsVOChildrenVO4.setId(id4);
									CommTGicsVOChildrenVO4.setParentId(parentId4);
									CommTGicsVOChildrenVO4.setGicsName(gicsName4);
									funcPermissions.get(i).getChildren().get(0).getChildren().get(b).getChildren().add(CommTGicsVOChildrenVO4);
								}
								funcPermissions.get(i).getChildren().get(0).getChildren().get(b).getChildren().remove(0);


							}

						}
					}


				}
			}



			mapResponse.put("funcPermissions",funcPermissions);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			mapResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			mapResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);
	}


}

