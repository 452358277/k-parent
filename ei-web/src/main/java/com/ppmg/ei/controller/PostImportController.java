package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.core.vo.Response;
import com.founder.ssm.foundation.annotation.AccessLog;
import com.founder.ssm.web.common.CommonStatus;
import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.easyexcel.*;
import com.ppmg.ei.model.DatNewFunctionExcel;
import com.ppmg.ei.model.HyNewFunctionExcel;
import com.ppmg.ei.service.ProjInfoService;
import easyexcel.exception.ExcelException;
import easyexcel.utils.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController
@Scope("prototype")
@Api(tags={"项目信息相关接口"})
@RequestMapping("/import")
public class PostImportController extends BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference
    private ProjInfoService projInfoService;

/*    @Reference(retries=-1)
    private EntBaseInfoService entBaseInfoService;

    @Reference(check = false)
    private BasicPersonalInfoController basicPersonalInfoController;*/

    @ApiOperation(value = "导入业务管理员")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/importBusinessManager")
    @AccessLog(logOperation = "导入")
    public Response importBusinessManager(MultipartHttpServletRequest request) {
        try {

            Iterator<String> itr = request.getFileNames();
            String uploadedFile = itr.next();
            List<MultipartFile> files = request.getFiles(uploadedFile);
            if (CollectionUtils.isEmpty(files)) {
                throw new SystemException("请选择文件！");
            }
            BusinessManagerListener excelListener = new BusinessManagerListener();
            List<BusinessManagerModel> list = ExcelUtil.readExcel(files.get(0), excelListener, BusinessManagerModel.class, 1, 3);
           /* List<String> entName=new ArrayList<>();
            String errors="";
            if(list!=null && !list.isEmpty()){
                int init = 30;
                if(list.size()>30){
                    int total = list.size();
                    int cycelTotal = total / init;
                    if (total % init != 0) {
                        cycelTotal += 1;
                        if (total < init) {
                            init = list.size();
                        }
                    }
                    System.out.println("循环保存的次数："+cycelTotal);//循环多少次

                    for (int i = 0; i < cycelTotal; i++) {
                        List<String> list2 = new ArrayList();
                        for (int j = 0; j < init; j++) {
                            if (list.get(j) == null) {
                                break;
                            }
                            list2.add(list.get(j).getEntName());
                        }
                        //查询企业是否存在本地库中
                        if(list2!=null&&!list2.isEmpty()){
                            searchCondition.addConditionIn("NAME",list2);

                            PageInfo<EntBaseInfoModel> rows = entBaseInfoService.selectListPage(searchCondition);
                            List<EntBaseInfoModel> listEnt=rows.getList();
                            if(listEnt!=null){
                                List<String> list5 = new ArrayList();
                                for(EntBaseInfoModel mo:listEnt){
                                    if(!list5.contains(mo.getName())){
                                        list5.add(mo.getEnName());
                                    }

                                }
                                list2.removeAll(list5);
                            }
                        }
                        for(String nameE:list2){
                            String entInfo = basicPersonalInfoController.QCCDataByName(nameE);
                            if(StringUtils.isNotEmpty(entInfo)){
                                JSONObject sta3 = JSON.parseObject(entInfo);
                                String sta5 = sta3.getString("value");
                                JSONObject resu = JSON.parseObject(sta5);
                                if(resu!=null){
                                    String status = resu.getString("Status");
                                    if(!"200".equals(status)){
                                        errors=errors+"-"+nameE;
                                    }
                                }
                                String sta4 = resu.getString("Result");
                                if(sta4==null){
                                    errors=errors+"-"+nameE;
                                }else{
                                    Gson gson = new Gson();
                                    JSONObject object1 =  JSON.parseObject(sta4);
                                    Result result1 = gson.fromJson(sta4, Result.class);
                                    basicPersonalInfoController.saveEntBaseInfo(result1);
                                }


                            }
                        }

                    }
                }

            }
            if(StringUtils.isNotEmpty(errors)){
                baseResponse.error("企业名称有误:" +errors+"请保持数据与企查查一致。");
                return baseResponse;
            }
*/
            BusinessManagerImportRequest importRequest = new BusinessManagerImportRequest();
            importRequest.setDateList(list);
            String loginUserId = this.getLoginUserId() + "";
            importRequest.setCreateBy(loginUserId);
            importRequest.setCreateDt(new Date());
            importRequest.setUpdateBy(loginUserId);
            importRequest.setUpdateDt(new Date());
            baseResponse = projInfoService.importBusinessManager(importRequest);


        } catch (ExcelException e) {
            logger.error(e.getMessage());
            baseResponse.error(e.getMessage());
        } catch (SystemException e) {
            logger.error(e.getMessage());
            baseResponse.error(e.getMessage());
        } catch (Exception e) {
            baseResponse.error();
            logger.error(e.getMessage(), e);
        }
        return baseResponse;
    }


    @ApiOperation(value = "导入业务管理员")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/importFundManager")
    @AccessLog(logOperation = "导入")
    public Response importFundManager(MultipartHttpServletRequest request) {
        try {

            Iterator<String> itr = request.getFileNames();
            String uploadedFile = itr.next();
            List<MultipartFile> files = request.getFiles(uploadedFile);
            if (CollectionUtils.isEmpty(files)) {
                throw new SystemException("请选择文件！");
            }
            FundListener excelListener = new FundListener();
            List<FundManagerModel> list = ExcelUtil.readExcel(files.get(0), excelListener, FundManagerModel.class, 1, 4);
            FundManagerImportRequest importRequest = new FundManagerImportRequest();
            importRequest.setDateList(list);
            String loginUserId = this.getLoginUserId() + "";
            importRequest.setCreateBy(loginUserId);
            importRequest.setCreateDt(new Date());
            importRequest.setUpdateBy(loginUserId);
            importRequest.setUpdateDt(new Date());
            baseResponse = projInfoService.importFundManager(importRequest);


        } catch (ExcelException e) {
            logger.error(e.getMessage());
            baseResponse.error(e.getMessage());
        } catch (SystemException e) {
            logger.error(e.getMessage());
            baseResponse.error(e.getMessage());
        } catch (Exception e) {
            baseResponse.error();
            logger.error(e.getMessage(), e);
        }
        return baseResponse;
    }

    @ApiOperation(value = "导入业务管理员")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/importZt")
    @AccessLog(logOperation = "导入")
    public Response importZt(MultipartHttpServletRequest request) {
        try {
            Iterator<String> itr = request.getFileNames();
            String uploadedFile = itr.next();
            List<MultipartFile> files = request.getFiles(uploadedFile);
            if (CollectionUtils.isEmpty(files)) {
                throw new SystemException("请选择文件！");
            }
            ZtInfoListener ztExcelListener = new ZtInfoListener();
            List<DatNewFunctionExcel> list = ExcelUtil.readExcel(files.get(0), ztExcelListener, DatNewFunctionExcel.class, 1, 3);



            ZtBusinessManagerImportRequest importRequest = new ZtBusinessManagerImportRequest();
            importRequest.setDateList(list);
           // String loginUserId = this.getLoginUserId() + "";
            String loginUserId = "1120" + "";
            //importRequest.setCreateBy(loginUserId);
            importRequest.setCreateDt(new Date());
            //importRequest.setUpdateBy(loginUserId);
            importRequest.setUpdateDt(new Date());
            //baseResponse = projInfoService.importBusinessManager(importRequest);
            baseResponse = projInfoService.ztImportBusinessManager(importRequest);

        } catch (ExcelException e) {
            logger.error(e.getMessage());
            baseResponse.error(e.getMessage());
        } catch (SystemException e) {
            logger.error(e.getMessage());
            baseResponse.error(e.getMessage());
        } catch (Exception e) {
            baseResponse.error();
            logger.error(e.getMessage(), e);
        }
        return baseResponse;
    }

    @ApiOperation(value = "导入业务管理员")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/importHy")
    @AccessLog(logOperation = "导入行业")
    public Response importHy(MultipartHttpServletRequest request) {
        try {
            Iterator<String> itr = request.getFileNames();
            String uploadedFile = itr.next();
            List<MultipartFile> files = request.getFiles(uploadedFile);
            if (CollectionUtils.isEmpty(files)) {
                throw new SystemException("请选择文件！");
            }
            HyInfoListener hyExcelListener = new HyInfoListener();
            List<HyNewFunctionExcel> list = ExcelUtil.readExcel(files.get(0), hyExcelListener, HyNewFunctionExcel.class, 1, 3);
            HyBusinessManagerImportRequest importRequest = new HyBusinessManagerImportRequest();
            importRequest.setDateList(list);
            String loginUserId = this.getLoginUserId();
            importRequest.setCreateDt(new Date());
            importRequest.setUpdateDt(new Date());
            //baseResponse = projInfoService.ztImportBusinessManager(importRequest);
            baseResponse = projInfoService.hyImportBusinessManager(importRequest);

        } catch (ExcelException e) {
            logger.error(e.getMessage());
            baseResponse.error(e.getMessage());
        } catch (SystemException e) {
            logger.error(e.getMessage());
            baseResponse.error(e.getMessage());
        } catch (Exception e) {
            baseResponse.error();
            logger.error(e.getMessage(), e);
        }
        return baseResponse;
    }





}
