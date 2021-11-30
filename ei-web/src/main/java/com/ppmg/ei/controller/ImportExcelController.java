package com.ppmg.ei.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.model.EntBaseInfoModel;
import com.ppmg.ei.service.EntBaseInfoService;
import com.ppmg.ei.service.FundInfoService;
import com.ppmg.ei.service.ImportExcelService;
import com.ppmg.ei.utils.ReadExcel;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
@Api(tags={"导入execle"})
public class ImportExcelController extends BaseController {

    @Reference
    private ImportExcelService importExcelService;

    @Reference(retries=-1)
    private EntBaseInfoService entBaseInfoService;

    @Reference(retries=-1)
    private FundInfoService fundInfoService;

    @RequestMapping(value="/importExc", method = RequestMethod.POST)
    public String importExc(@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            if(file.isEmpty()){
                baseResponse.setMsg("创建Excel工作薄为空！");
                return	JSONObject.toJSONString(baseResponse);
            }
            InputStream in =file.getInputStream();
            // 获取文件名
            String fileName = file.getOriginalFilename();
            //创建处理EXCEL的类
            ReadExcel readExcel = new ReadExcel();
            //解析excel，获取上传的事件单
            List<Map<String, Object>> beExamList = readExcel.getExcelInfo(file);
            if(beExamList!=null&&beExamList.size()>0){
                for(int i=0;i<beExamList.size();i++){
                    String entName = String.valueOf(beExamList.get(i).get("entName"));
                    if(StringUtils.isEmpty(entName)){
                        baseResponse.setMsg("企业名称不能为空！");
                        return	JSONObject.toJSONString(baseResponse);
                    }
                    EntBaseInfoModel entBaseInfoModel=new EntBaseInfoModel();
                    entBaseInfoModel.setName(entName);
                    EntBaseInfoModel entBase=entBaseInfoService.selectBy(entBaseInfoModel);
                    if(entBase==null){
                        baseResponse.setMsg("请填写正确的企业名称！");
                        return	JSONObject.toJSONString(baseResponse);
                    }
                    String creditCode = String.valueOf(beExamList.get(i).get("creditCode"));
                    if(StringUtils.isEmpty(creditCode)){
                        baseResponse.setMsg("企业统一社会信用代码不能为空！");
                        return	JSONObject.toJSONString(baseResponse);
                    }
                    //判断统一社会信用代码是否正确
                    if(!creditCode.equals(entBase.getCreditCode())){
                        baseResponse.setMsg("企业统一社会信用代码不正确！");
                        return	JSONObject.toJSONString(baseResponse);
                    }

                    String projStatus1 = String.valueOf(beExamList.get(i).get("projStatus"));
                    if(StringUtils.isEmpty(projStatus1)){
                        baseResponse.setMsg("项目状态不能为空！");
                        return	JSONObject.toJSONString(baseResponse);
                    }
                    String projStatus="";
                    if("全部退出".equals(projStatus1)){
                        projStatus="1";

                    }else if("部分退出".equals(projStatus1)){
                        projStatus="2";
                    }
                    String fundName = String.valueOf(beExamList.get(i).get("fundName"));
                    if(StringUtils.isEmpty(fundName)){
                        baseResponse.setMsg("子基金名称！");
                        return	JSONObject.toJSONString(baseResponse);
                    }

                }
            }



            return "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";


    }
}