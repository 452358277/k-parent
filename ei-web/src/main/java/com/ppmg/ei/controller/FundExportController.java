package com.ppmg.ei.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.FundInfoService;
import com.ppmg.ei.vo.FundInfoExportVO;
import com.ppmg.ei.vo.LedgeExportVO;
import com.ppmg.ei.vo.LedgeMagExportVO;
import com.ppmg.ei.vo.ProjInfoExportVO;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@Scope("prototype")
@Api(tags={"年度财务报表接口"})
@RequestMapping("/fundExport")
public class FundExportController extends BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference(timeout = 200000)
    protected FundInfoService fundInfoService;

    @Resource(name = "codeUtils")
    private CodeUtils codeUtils;




    @ApiOperation(value="基金明细查询", notes="现金流列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fundExportList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fundExportList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,   @RequestParam("fundId")String fundId, String type, String inveName,String regName){
        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            if(StringUtils.isNotEmpty(fundId)){
                searchCondition.addParam("fundId",fundId);
            }
            if(StringUtils.isNotEmpty(inveName)){
                searchCondition.addConditionLike("a.inve_name","%" +inveName.trim()+"%");
            }
            if(StringUtils.isNotEmpty(regName)){
                searchCondition.addConditionLike("a.reg_name","%" +regName.trim()+"%");
            }
            PageInfo<FundInfoExportModel> rows = fundInfoService.selectFundExportList(searchCondition);
            List<FundInfoExportVO> list = new ArrayList<FundInfoExportVO>();
            for(FundInfoExportModel model : rows.getList()){
                FundInfoExportVO vo = new FundInfoExportVO();
                BeanUtils.copyProperties(model,vo );
                //16是历史数据已退出
                if("16".equals(model.getProjStatus())){
                    vo.setProjStatusName("已退出");
                    //历史数据从EI.BD_T_FUND_QUIT_INVE_INFO 表中取值，不动态统计
                   if(model.getQuitInveInfoModel()!=null){
                       if(model.getQuitInveInfoModel().getInveProjNum()!=null){
                           vo.setProjGs(String.valueOf(model.getQuitInveInfoModel().getInveProjNum()));
                       }
                       if(model.getQuitInveInfoModel().getInveEntNum()!=null){
                           vo.setEnteGs(String.valueOf(model.getQuitInveInfoModel().getInveEntNum()));
                       }
                       if(model.getQuitInveInfoModel().getInveAmt()!=null){
                           vo.setProjAmount(String.valueOf(model.getQuitInveInfoModel().getInveAmt()));
                       }
                       if(model.getQuitInveInfoModel().getInveProvinceNum()!=null){
                           vo.setProvinceGs(String.valueOf(model.getQuitInveInfoModel().getInveProvinceNum()));
                       }
                       if(model.getQuitInveInfoModel().getInveProvinceAmt()!=null){
                           vo.setProvinceAmount(model.getQuitInveInfoModel().getInveProvinceAmt());
                       }
                       if(model.getQuitInveInfoModel().getInveListedNum()!=null){
                           vo.setSsGs(String.valueOf(model.getQuitInveInfoModel().getInveListedNum()));
                       }
                       if(model.getQuitInveInfoModel().getInveIpoNum()!=null){
                           vo.setIpoGs(String.valueOf(model.getQuitInveInfoModel().getInveIpoNum()));
                       }
                       if(model.getQuitInveInfoModel().getInveNewThirdNum()!=null){
                           vo.setNewBoardGs(String.valueOf(model.getQuitInveInfoModel().getInveNewThirdNum()));
                       }
                   }


                }
                if(model!=null&& StringUtils.isNotEmpty(model.getOldSubPlatProp())){
                   if("4".equals(model.getOldSubPlatProp())|| "6".equals(model.getOldSubPlatProp()) || "5".equals(model.getOldSubPlatProp())){
                       vo.setJcCurrentAmount(model.getJcCurrentAmountOne());
                       vo.setJcRaiseAmount(model.getJcRaiseAmountOne());
                   }
                }
                if("0".equals(model.getCooperationYear())){
                    vo.setCooperationYear("");
                }

                if(model.getStartDate()==null){
                    vo.setSetupDt(model.getSetupDt());
                }else{
                    vo.setSetupDt(model.getStartDate());
                }

                list.add(vo);
            }

            dataTablesResponse.setData(list, rows);

        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }


    @ApiOperation(value="基金明细查询导出", notes="数据上报导出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/exportInfo", produces = "application/json;charset=UTF-8")
    public void  exportInfo(HttpServletResponse response, HttpServletRequest request, @RequestParam("fundId")String fundId,String inveName,String regName){
        try {
            if(StringUtils.isNotEmpty(fundId)){
                searchCondition.addParam("fundId",fundId);
            }
            String fileName = URLEncoder.encode("基金明细信息", "UTF-8");
            fileName = fileName + new SimpleDateFormat("yyyyMMdd").format(new Date());
            if(StringUtils.isNotEmpty(inveName)){
                searchCondition.addConditionLike("a.inve_name","%" +inveName.trim()+"%");
            }
            if(StringUtils.isNotEmpty(regName)){
                searchCondition.addConditionLike("a.reg_name","%" +regName.trim()+"%");
            }
            List<FundInfoExportModel> list=fundInfoService.getExportInfoList(searchCondition);

           List<FundInfoExport>  fundInfoExportList=new ArrayList<>();
            int rownums=0;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            for(FundInfoExportModel fundInfoExportModel:list){
                FundInfoExport vo=new FundInfoExport();
                BeanUtils.copyProperties(fundInfoExportModel, vo);
                //16是历史数据已退出
                if("16".equals(fundInfoExportModel.getProjStatus())) {
                    vo.setProjStatusName("已退出");
                    //历史数据从EI.BD_T_FUND_QUIT_INVE_INFO 表中取值，不动态统计
                    if (fundInfoExportModel.getQuitInveInfoModel() != null) {
                        if (fundInfoExportModel.getQuitInveInfoModel().getInveProjNum() != null) {
                            vo.setProjGs(String.valueOf(fundInfoExportModel.getQuitInveInfoModel().getInveProjNum()));
                        }
                        if (fundInfoExportModel.getQuitInveInfoModel().getInveEntNum() != null) {
                            vo.setEnteGs(String.valueOf(fundInfoExportModel.getQuitInveInfoModel().getInveEntNum()));
                        }
                        if (fundInfoExportModel.getQuitInveInfoModel().getInveAmt() != null) {
                            vo.setProjAmount(String.valueOf(fundInfoExportModel.getQuitInveInfoModel().getInveAmt()));
                        }
                        if (fundInfoExportModel.getQuitInveInfoModel().getInveProvinceNum() != null) {
                            vo.setProvinceGs(String.valueOf(fundInfoExportModel.getQuitInveInfoModel().getInveProvinceNum()));
                        }
                        if (fundInfoExportModel.getQuitInveInfoModel().getInveProvinceAmt() != null) {
                            vo.setProvinceAmount(fundInfoExportModel.getQuitInveInfoModel().getInveProvinceAmt());
                        }
                        if (fundInfoExportModel.getQuitInveInfoModel().getInveListedNum() != null) {
                            vo.setSsGs(String.valueOf(fundInfoExportModel.getQuitInveInfoModel().getInveListedNum()));
                        }
                        if (fundInfoExportModel.getQuitInveInfoModel().getInveIpoNum() != null) {
                            vo.setIpoGs(String.valueOf(fundInfoExportModel.getQuitInveInfoModel().getInveIpoNum()));
                        }
                        if (fundInfoExportModel.getQuitInveInfoModel().getInveNewThirdNum() != null) {
                            vo.setNewBoardGs(String.valueOf(fundInfoExportModel.getQuitInveInfoModel().getInveNewThirdNum()));
                        }
                    }
                }
                    if("0".equals(fundInfoExportModel.getCooperationYear())){
                    vo.setCooperationYear("");
                }
                if(fundInfoExportModel!=null&& StringUtils.isNotEmpty(fundInfoExportModel.getOldSubPlatProp())){
                    if("4".equals(fundInfoExportModel.getOldSubPlatProp())|| "6".equals(fundInfoExportModel.getOldSubPlatProp())){
                        vo.setJcCurrentAmount(fundInfoExportModel.getJcCurrentAmountOne());
                        vo.setJcRaiseAmount(fundInfoExportModel.getJcRaiseAmountOne());
                    }
                }
                rownums=rownums+1;
                vo.setRowNums(rownums);
                if(fundInfoExportModel.getSetupDt()!=null){
                    vo.setSetupDtStr(formatter.format(fundInfoExportModel.getSetupDt()));
                }
                if(fundInfoExportModel.getInveStartDate()!=null){
                    vo.setInveStartDateStr(formatter.format(fundInfoExportModel.getInveStartDate()));
                }
                if(fundInfoExportModel.getInveEndDate()!=null){
                    vo.setInveEndDateStr(formatter.format(fundInfoExportModel.getInveEndDate()));
                }
                fundInfoExportList.add(vo);
            }
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
            WriteSheet writeSheet1 = EasyExcel.writerSheet(0, "基金明细信息").head(FundInfoExport.class).build();
            excelWriter.write(fundInfoExportList, writeSheet1);
            excelWriter.finish();

        } catch (IOException e) {
            logger.error(e.getMessage());
            baseResponse.error(e.getMessage());
        } catch (Exception e) {
            baseResponse.error();
            logger.error(e.getMessage(), e);
        }
    }



    @ApiOperation(value="参股投项目", notes="参股投项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projInfoExportList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String projInfoExportList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,  String inveName, String year, String entName,@RequestParam("fundId")String fundId){
        try {
            Calendar cal = Calendar.getInstance();
            int yearStr = cal.get(Calendar.YEAR);
            int begin=(currPage-1)*pageSize;
            int end=pageSize*currPage+1;
            searchCondition.addParam("end",end);
            searchCondition.addParam("begin",begin);
            if(StringUtils.isNotEmpty(inveName)){
                searchCondition.addConditionLike("aaa.inve_name","%" +inveName.trim()+"%");
            }
            if(StringUtils.isNotEmpty(fundId)){
                searchCondition.addParam("fundId",fundId);
            }

            searchCondition.addConditionEqualTo("aaa.group_id","3");
            if(StringUtils.isNotEmpty(year)){
                yearStr=Integer.parseInt(year);
                searchCondition.addParam("year",year);
            }else{
                searchCondition.addParam("year",String.valueOf(yearStr));
            }
            if(StringUtils.isNotEmpty(entName)){
                searchCondition.addConditionLike("bbb.name","%" +entName.trim()+"%");
            }
            //PageInfo<ProjInfoExportModel> rows = fundInfoService.projInfoExportListPage(searchCondition);
           List<ProjInfoExportModel>  rows = fundInfoService.projInfoExportList(searchCondition);
            int total= fundInfoService.projInfoExportCount(searchCondition);
            List<ProjInfoExportVO> list = new ArrayList<ProjInfoExportVO>();
            SimpleDateFormat format=new SimpleDateFormat("yyyy");
            for(ProjInfoExportModel model : rows){
                ProjInfoExportVO vo = new ProjInfoExportVO();
                BeanUtils.copyProperties(model,vo );
                if(model.getOccurDt()!=null){
                  String last=format.format(model.getOccurDt());
                  if(StringUtils.isNotEmpty(last)){
                     String lastYear=String.valueOf(Integer.parseInt(last)-1);
                      vo.setLastYear(lastYear);
                  }
                }
                if(StringUtils.isEmpty(model.getRegAdd())){
                       vo.setRegAdd(model.getAddressDetails());
                   }
                   if(StringUtils.isEmpty(model.getRegistCapi())){
                       if(model.getRegAmount()!=null){
                           String curr="万元人民币";
                           if(StringUtils.isNotEmpty(model.getRegAmountCurrency())){
                                curr= codeUtils.getCodeNameByValue("103", model.getRegAmountCurrency());
                               if("1".equals(model.getRegAmountCurrency())){
                                   curr="万元人民币";
                               }else{
                                   if(StringUtils.isEmpty(curr)){
                                       curr="万元人民币";
                                   }else{
                                       curr="万"+ curr;
                                   }

                               }
                           }
                           vo.setRegistCapi(String.valueOf(model.getRegAmount())+curr);
                       }

                   }else{
                       //判断是否包含单位，不包含默认为人民币
                       if(StringUtils.isNotEmpty(model.getRegistCapi())&&!model.getRegistCapi().contains("万")){
                           vo.setRegistCapi(model.getRegistCapi()+"万元人民币");
                       }
                   }

                if(model.getSetupDt()==null){
                    vo.setSetupDt(model.getStartDate());
                }

                //投资方式
                if(StringUtils.isNotEmpty(model.getInveType())){
                    String inveTypeName= codeUtils.getCodeNameByValue("4444", model.getInveType());
                  vo.setInveTypeName(inveTypeName);
                }
               //是否董事席位
                if(StringUtils.isNotEmpty(model.getIsDirectorCnt())){
                    String isDirectorCnName= codeUtils.getCodeNameByValue("102", model.getIsDirectorCnt());
                    vo.setIsDirectorCnName(isDirectorCnName);
                }

                if(StringUtils.isNotEmpty(model.getIsSupervisorCnt())){
                    String isSupervisorCntName= codeUtils.getCodeNameByValue("102", model.getIsSupervisorCnt());
                    vo.setIsSupervisorCntName(isSupervisorCntName);
                }
                if(StringUtils.isNotEmpty(model.getFinaRounds())){
                    String finaRoundsName= codeUtils.getCodeNameByValue("213", model.getFinaRounds());
                    vo.setFinaRounds(finaRoundsName);
                }

                if(StringUtils.isNotEmpty(model.getIsPublicComp())){
                    String isPublicCompName= codeUtils.getCodeNameByValue("102", model.getIsPublicComp());
                    vo.setIsPublicCompName(isPublicCompName);
                }
                if(StringUtils.isNotEmpty(model.getPhase())){
                    String phaseName= codeUtils.getCodeNameByValue("9014", model.getPhase());
                    vo.setPhaseName(phaseName);
                }

                if(StringUtils.isNotEmpty(model.getExitType())){
                    String exitTypeName= codeUtils.getCodeNameByValue("21717", model.getExitType());
                    vo.setExitTypeName(exitTypeName);
                }
                if(StringUtils.isNotEmpty(model.getIsProvince())){
                    String isProvinceName= codeUtils.getCodeNameByValue("102", model.getIsProvince());
                    vo.setIsProvinceName(isProvinceName);
                }
       /*       if(model.getListM()!=null&& !model.getListM().isEmpty()){
                    if(model.getListM().size()==1){
                        if(StringUtils.isNotEmpty(vo.getListM().get(0).getYear())&&vo.getListM().get(0).getYear().equals(equals(String.valueOf(yearStr)))){
                            vo.getListM().get(0).setInveStr("本年度");
                            FundEnteModel fundEnteModelS=new FundEnteModel();
                            fundEnteModelS.setInveStr("投资前上年度");
                            vo.getListM().add(fundEnteModelS);
                        }else{
                            vo.getListM().get(0).setInveStr("投资前上年度");
                            FundEnteModel fundEnteModelS=new FundEnteModel();
                            fundEnteModelS.setInveStr("本年度");
                            vo.getListM().add(fundEnteModelS);
                        }

                    }else if(model.getListM().size()==2){
                        for(FundEnteModel fundEnteModel:vo.getListM()){
                            if(!String.valueOf(yearStr).equals(fundEnteModel.getYear())){
                                fundEnteModel.setInveStr("投资前上年度");
                            }else{
                                fundEnteModel.setInveStr("本年度");
                            }
                        }

                    }
                }else{
                    for(int i=1;i<=2;i++){
                        FundEnteModel fundEnteModelS=new FundEnteModel();
                        if(i==1){
                            fundEnteModelS.setInveStr("投资前上年度");

                        }else{
                            fundEnteModelS.setInveStr("本年度");
                        }
                        vo.getListM().add(fundEnteModelS);
                    }

                }*/

                list.add(vo);
            }
            //dataTablesResponse.setData(list, rows);
            dataTablesResponse.setData(list);
            dataTablesResponse.setTotalCount(Long.valueOf(total).intValue(), currPage, pageSize);
        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }


    @ApiOperation(value="参股投项目", notes="参股投项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projExportAllList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void projExportAllList( String inveName, String year, String entName,HttpServletResponse response,@RequestParam("fundId")String fundId){
        try {
            Calendar cal = Calendar.getInstance();
            int yearStr = cal.get(Calendar.YEAR);
            if(StringUtils.isNotEmpty(inveName)){
                searchCondition.addConditionLike("aaa.inve_name","%" +inveName.trim()+"%");
            }
            if(StringUtils.isNotEmpty(year)){
                searchCondition.addParam("year",year);
            }else{
                searchCondition.addParam("year",String.valueOf(yearStr));
                year=String.valueOf(yearStr);
            }
            if(StringUtils.isNotEmpty(fundId)){
                searchCondition.addParam("fundId",fundId);
            }
            if(StringUtils.isNotEmpty(entName)){
                searchCondition.addConditionLike("bbb.name","%" +entName.trim()+"%");
            }
            searchCondition.addConditionEqualTo("aaa.group_id","3");
            List<ProjInfoAllExportModel> rows = fundInfoService.projExportList(searchCondition);
            List<ProjInfoExport> list = new ArrayList<ProjInfoExport>();
            int rowNums=0;
            SimpleDateFormat format=new SimpleDateFormat("yyyy");
            for(ProjInfoAllExportModel model : rows){
                ProjInfoExport vo = new ProjInfoExport();
                BeanUtils.copyProperties(model,vo );
                if(StringUtils.isEmpty(model.getRegAdd())){
                    vo.setRegAdd(model.getAddressDetails());
                }
                if(model.getSetupDt()==null){
                    vo.setSetupDt(model.getStartDate());
                }

                //转换数字
                String lastYear="";
                if(model.getOccurDt()!=null){
                    String last=format.format(model.getOccurDt());
                    if(StringUtils.isNotEmpty(last)){
                         lastYear=String.valueOf(Integer.parseInt(last)-1);
                    }
                }
               if(model.getListM()!=null&& !model.getListM().isEmpty()){
                    if(model.getListM().size()==1){
                        if(vo.getListM().get(0).getYear().equals(String.valueOf(lastYear))){
                            //判断投资前上年度和本年度重合
                            if(year.equals(lastYear)){
                                vo.getListM().get(0).setInveStr("投资前上年度");
                                FundEnteExport fundEnteModelS= vo.getListM().get(0);
                                FundEnteExport fundEnteModelN=new FundEnteExport();
                                BeanUtils.copyProperties(fundEnteModelS,fundEnteModelN);
                                fundEnteModelN.setInveStr("本年度");
                                vo.getListM().add(fundEnteModelN);
                            }else{
                                vo.getListM().get(0).setInveStr("投资前上年度");
                                FundEnteExport fundEnteModelS=new FundEnteExport();
                                fundEnteModelS.setInveStr("本年度");
                                vo.getListM().add(fundEnteModelS);
                            }

                        }else if(year.equals(vo.getListM().get(0).getYear()) &&  !vo.getListM().get(0).getYear().equals(String.valueOf(lastYear))) {
                            //说明只有本年度
                            List<FundEnteExport> listN=new ArrayList<>();
                            FundEnteExport fundEnteModelS=new FundEnteExport();
                            fundEnteModelS.setInveStr("投资前上年度");
                            listN.add(fundEnteModelS);
                            FundEnteExport two=vo.getListM().get(0);
                            two.setInveStr("本年度");
                            listN.add(two);
                            //去除原来的集合
                            vo.getListM().clear();
                            //赋值排序后的新集合
                            vo.setListM(listN);

                        }

                    }else if(model.getListM().size()==2){
                        for(FundEnteExport fundEnteExport:vo.getListM()){
                            if(String.valueOf(lastYear).equals(fundEnteExport.getYear())){
                                fundEnteExport.setInveStr("投资前上年度");
                            }else{
                                fundEnteExport.setInveStr("本年度");
                            }
                        }

                    }
                }else{
                        for(int i=1;i<=2;i++){
                            FundEnteExport fundEnteModelS=new FundEnteExport();
                            if(i==1){
                                fundEnteModelS.setInveStr("投资前上年度");

                            }else{
                                fundEnteModelS.setInveStr("本年度");
                            }
                            vo.getListM().add(fundEnteModelS);
                        }

                }

                //投资方式
                if(StringUtils.isNotEmpty(model.getInveType())){
                    String inveTypeName= codeUtils.getCodeNameByValue("4444", model.getInveType());
                    vo.setInveTypeName(inveTypeName);
                }

                if(StringUtils.isNotEmpty(model.getPhase())){
                    String phaseName= codeUtils.getCodeNameByValue("9014", model.getPhase());
                    vo.setPhase(phaseName);
                }

                if(StringUtils.isNotEmpty(model.getExitType())){
                    String exitTypeName= codeUtils.getCodeNameByValue("21717", model.getExitType());
                    vo.setExitTypeName(exitTypeName);
                }
               /* if(StringUtils.isEmpty(model.getRegistCapi())){
                    vo.setRegistCapi(String.valueOf(model.getRegAmount()));
                }*/
                if(StringUtils.isEmpty(model.getRegistCapi())&&model.getRegAmount()!=null){
                    String curr="万元人民币";
                    if(StringUtils.isNotEmpty(model.getRegAmountCurrency())){
                        curr= codeUtils.getCodeNameByValue("103", model.getRegAmountCurrency());
                        if("1".equals(model.getRegAmountCurrency())){
                            curr="万元人民币";
                        }else{
                            if(StringUtils.isEmpty(curr)){
                                curr="万元人民币";
                            }else{
                                curr="万"+ curr;
                            }
                        }
                    }
                    vo.setRegistCapi(String.valueOf(model.getRegAmount())+curr);
                }else{
                    //判断是否包含单位，不包含默认为人民币
                    if(StringUtils.isNotEmpty(model.getRegistCapi())&&!model.getRegistCapi().contains("万")){
                        vo.setRegistCapi(model.getRegistCapi()+"万元人民币");
                    }
                }

                if(model.getSetupDt()==null){
                    vo.setSetupDt(model.getStartDate());
                }

                //投资方式
                if(StringUtils.isNotEmpty(model.getInveType())){
                    String inveTypeName= codeUtils.getCodeNameByValue("4444", model.getInveType());
                    vo.setInveTypeName(inveTypeName);
                }
                //是否董事席位
                if(StringUtils.isNotEmpty(model.getIsDirectorCnt())){
                    String isDirectorCnName= codeUtils.getCodeNameByValue("102", model.getIsDirectorCnt());
                    vo.setIsDirectorCnt(isDirectorCnName);
                }
                if(StringUtils.isNotEmpty(model.getIsSupervisorCnt())){
                    String isSupervisorCnt= codeUtils.getCodeNameByValue("102", model.getIsSupervisorCnt());
                    vo.setIsSupervisorCnt(isSupervisorCnt);
                }

                if(StringUtils.isNotEmpty(model.getIsPublicComp())){
                    String isPublicCompName= codeUtils.getCodeNameByValue("102", model.getIsPublicComp());
                    vo.setIsPublicComp(isPublicCompName);
                }
                if(StringUtils.isNotEmpty(model.getPhase())){
                    String phaseName= codeUtils.getCodeNameByValue("9014", model.getPhase());
                    vo.setPhase(phaseName);
                }

                if(StringUtils.isNotEmpty(model.getExitType())){
                    String exitTypeName= codeUtils.getCodeNameByValue("21717", model.getExitType());
                    vo.setExitTypeName(exitTypeName);
                }
                if(StringUtils.isNotEmpty(model.getIsProvince())){
                    String isProvinceName= codeUtils.getCodeNameByValue("102", model.getIsProvince());
                    vo.setIsProvince(isProvinceName);
                }
                String finaName="";
                String fina="";
                if(StringUtils.isNotEmpty(model.getFinaRounds())){
                    String finaRoundsName= codeUtils.getCodeNameByValue("213", model.getFinaRounds());
                    if(!"8".equals(model.getFinaRounds())&& !"9".equals(model.getFinaRounds())){
                        if(StringUtils.isNotEmpty(model.getFinaTimes())){
                            finaName=finaRoundsName+",次数"+model.getFinaTimes();
                        }else{
                            finaName=finaRoundsName;
                        }

                    }else{
                        finaName=finaRoundsName;
                    }

                    vo.setFinaRounds(finaName);
                }

                rowNums=rowNums+1;
                vo.setRowNums(rowNums);
                list.add(vo);

            }

            ExportParams params = new ExportParams();
            params.setSheetName("投资企业项目明细");//设置sheet名
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("投资企业项目明细", "投资企业项目明细"),
                    ProjInfoExport.class, list);
            //Workbook workbook = ExcelExportUtil.exportExcel(params, ProjInfoExport.class, list);
           setExportExcelFormat(response, workbook, "投资企业项目明细");
        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
        }
    }
    private static void setExportExcelFormat(HttpServletResponse response, Workbook workbook, String fileName) throws Exception {
        response.reset();
        response.setContentType("application/x-msdownload");//下载
        fileName = fileName + new SimpleDateFormat("yyyyMMdd").format(new Date());
        response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO-8859-1") + ".xls");
        ServletOutputStream outStream = null;
        try {
            outStream = response.getOutputStream();
            workbook.write(outStream);
        } finally {
            outStream.close();
        }
    }


    @ApiOperation(value="引导基金分配台账", notes="参股投项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/ledgeExportList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String ledgeExportList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,  String regName, @RequestParam("fundId") String fundId,String inveName,String beginTime,String  endTime){
        try {
            //  List<Articles> list=a.select(page*rows+1，(page-1)*rows);
            int begin=(currPage-1)*pageSize;
            int end=pageSize*currPage+1;
            searchCondition.addParam("end",end);
            searchCondition.addParam("begin",begin);
            if(StringUtils.isNotEmpty(fundId)){
                searchCondition.addParam("fundId",fundId);
            }
            if(StringUtils.isNotEmpty(beginTime)){
                searchCondition.addParam("beginTime",beginTime);
            }

            if(StringUtils.isNotEmpty(endTime)){
                searchCondition.addParam("endTime",endTime);
            }

            if(StringUtils.isNotEmpty(regName)){
                searchCondition.addConditionLike("nnn.reg_name","%"+regName.trim()+"%");
            }
            if(StringUtils.isNotEmpty(inveName)){
                searchCondition.addConditionLike("nnn.inve_Name","%"+inveName.trim()+"%");
            }


            List<LedgeMagExportModel> rows = fundInfoService.projLedgeExportList(searchCondition);
           int total= fundInfoService.getCountLedgeMage(searchCondition);
            List<LedgeMagExportVO> list = new ArrayList<LedgeMagExportVO>();
            for(LedgeMagExportModel model : rows){
                LedgeMagExportVO vo = new LedgeMagExportVO();
                BeanUtils.copyProperties(model,vo );
                if(model!=null&& StringUtils.isNotEmpty(model.getOldSubPlatProp())){
                    if("4".equals(model.getOldSubPlatProp())|| "6".equals(model.getOldSubPlatProp())){
                        vo.setJcRmbInveAmount(model.getJcRmbInveAmountoOne());
                    }
                }
                if(model.getListInveInfo()!=null&& !model.getListInveInfo().isEmpty()){
                    List<InveExportInfo> dtoList = copy(model.getListInveInfo(),InveExportInfo.class);
                    vo.setListInfo(dtoList);
                }
                if(model.getListFundAccountDetail()!=null&& !model.getListFundAccountDetail().isEmpty()){
                    List<FundAccountDetaiExport> dtoList1 = copy(model.getListFundAccountDetail(),FundAccountDetaiExport.class);
                    vo.setListFundAccountDetail(dtoList1);
                }
                list.add(vo);
            }
            //dataTablesResponse.setData(list, rows);
            dataTablesResponse.setData(list);
            dataTablesResponse.setTotalCount(Long.valueOf(total).intValue(), currPage, pageSize);

        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value="引导基金分配台账导出", notes="参股投项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/ledgeExport", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String ledgeExport(HttpServletResponse response, String regName,@RequestParam("fundId") String fundId,String inveName,String beginTime,String  endTime){
        try {
            if(StringUtils.isNotEmpty(regName)){
                searchCondition.addConditionLike("nnn.reg_name","%"+regName.trim()+"%");
            }
            if(StringUtils.isNotEmpty(fundId)){
                searchCondition.addParam("fundId",fundId);
            }
            if(StringUtils.isNotEmpty(inveName)){
                searchCondition.addConditionLike("nnn.inve_Name","%"+inveName.trim()+"%");
            }

            if(StringUtils.isNotEmpty(beginTime)){
                searchCondition.addParam("beginTime",beginTime);
            }
            if(StringUtils.isNotEmpty(endTime)){
                searchCondition.addParam("endTime",endTime);
            }

            List<LedgeMagExportModel> rows = fundInfoService.ledgeExportList(searchCondition);
            List<LedgeExportVO> list = new ArrayList<LedgeExportVO>();
            int rowNums=0;
            for(LedgeMagExportModel model : rows){
                LedgeExportVO vo = new LedgeExportVO();
                BeanUtils.copyProperties(model,vo );
                if(model!=null&& StringUtils.isNotEmpty(model.getOldSubPlatProp())){
                    if("4".equals(model.getOldSubPlatProp())|| "6".equals(model.getOldSubPlatProp())){
                        vo.setJcRmbInveAmount(model.getJcRmbInveAmountoOne());
                    }
                }

                if(model.getListInveInfo()!=null&& !model.getListInveInfo().isEmpty()){
                    List<LedgeMagExportInfo> dtoList = copy(model.getListInveInfo(),LedgeMagExportInfo.class);
                    vo.setListInfo(dtoList);
                }
                if(model.getListFundAccountDetail()!=null&& !model.getListFundAccountDetail().isEmpty()){
                    List<FundAccountDetaiExport> dtoList1 = copy(model.getListFundAccountDetail(),FundAccountDetaiExport.class);
                    vo.setListFundAccountDetail(dtoList1);
                }
                rowNums=rowNums+1;
                vo.setRowNums(rowNums);
                list.add(vo);
            }

            ExportParams params = new ExportParams();
            params.setSheetName("项目资金收益台账");//设置sheet名
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("项目资金收益台账", "项目资金收益台账"),
                    LedgeExportVO.class, list);
            //Workbook workbook = ExcelExportUtil.exportExcel(params, ProjInfoExport.class, list);
            setExportExcelFormat(response, workbook, "项目资金收益台账");

        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    public static <T> List<T> copy(Object sourceList,Class<?> beanClass) throws Exception{
        List<Object> sList = (List<Object>) sourceList;
        List<Object> tList = new ArrayList<Object>();
        for (Object t : sList) {
            Object dto = beanClass.newInstance();
            BeanUtils.copyProperties(t, dto);
            tList.add(dto);
        }
        return (List<T>) tList;

    }

    @ApiIgnore
    @ApiOperation(value = "导入自测", notes = "导入自测")
    @ApiImplicitParam(name = "financeId", value = "financeId", required = true, dataType = "financeId")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/getImportList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getImportList() {
        try {
            //FileInputStream fis = new FileInputStream("D:/aaaaa.xlsx");
            File file = new File("D:\\aaaaa.xlsx");
            ImportParams params = new ImportParams();
            params.setHeadRows(1);
            params.setTitleRows(0);
            List<DatNewFunctionExcel> functionList = ExcelImportUtil.importExcel(new File("D:"+File.separator+"aaaaa.xlsx"),DatNewFunctionExcel.class, params);

        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }


}
