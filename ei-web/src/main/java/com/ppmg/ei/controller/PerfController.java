package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.bean.PerManageFoundSerchBean;
import com.ppmg.ei.model.PerfManageFundModel;
import com.ppmg.ei.model.PerfScoreModel;
import com.ppmg.ei.model.PerformanceModel;
import com.ppmg.ei.service.PerfManageFundService;
import com.ppmg.ei.service.PerfService;
import com.ppmg.ei.utils.ExportDataUtils;
import com.ppmg.ei.vo.PerfManageFundVO;
import com.ppmg.ei.vo.PerfScoreVO;
import com.ppmg.ei.vo.PerformanceVO;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@Scope("prototype")
@Api(tags = {"绩效管理接口"})
public class PerfController extends BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference(timeout = 900000, retries = 0)
    private PerfService perfService;

    @Resource(name = "codeUtils")
    private CodeUtils codeUtils;


    @Reference(check = false)
    private PerfManageFundService perfManageFundService;

    @ApiOperation(value = "分页查询绩效管理列表", notes = "分页查询绩效管理列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pYear", value = "绩效年度", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pStatus", value = "绩效状态（0未发布，1：已发布，9删除）", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/selectPerf", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectPerf(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,
                             @RequestParam("pYear") String year, @RequestParam("pStatus") String status
            , @RequestParam("keyWord") String keyWord) {
        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            if (year != null && year != "") {
                searchCondition.addConditionEqualTo("P_YEAR", year);
            }
            if (status != null && status != "") {
                searchCondition.addConditionEqualTo("P_STATUS", status);
            }
            if (StringUtils.isNotEmpty(keyWord)) {
                searchCondition.addConditionLike("P_NAME", "%" + keyWord + "%");
            }
            PageInfo<PerformanceModel> rows = perfService.selectListPage(searchCondition);

            List<PerformanceVO> list = new ArrayList<PerformanceVO>();
            for (PerformanceModel model : rows.getList()) {
                PerformanceVO vo = new PerformanceVO();
                BeanUtils.copyProperties(model, vo);
                //考核基数
                String status_ = model.getpStatus();
                if (StringUtils.isNotEmpty(model.getpStatus())) {
                    System.out.print("*********************model.getpStatus()*******************"+ model.getpStatus());
                    String codeName = codeUtils.getCodeNameByValue("10181", model.getpStatus());
                    vo.setpStatusName(codeName);
                    System.out.print("*********************codeName*********************"+ codeName);
                }
                vo.setPsFundCount(vo.getFundCount());
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

    @ApiOperation(value = "查询绩效管理详情", notes = "查询绩效管理详情（一，二，三）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pPerId", value = "主表主键id", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/selectPerfDetails", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectPerfDetails(@RequestParam(value = "pPerId") String pPerId) {
        try {
            if (pPerId != null && pPerId != "") {
                PerformanceModel pm = perfService.selectPerfDetailSer(pPerId);
                PerformanceVO performance = new PerformanceVO();
                BeanUtils.copyProperties(pm, performance);
                //考核基数
                performance.setPsFundCount(performance.getFundCount());
                dataResponse.setData(performance);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value = "绩效考核结果导出", notes = "绩效考核结果导出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pPerId", value = "主键id", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/exportPerfDetails", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void exportPerfDetails(@RequestParam(value = "pPerId",required = true) String pPerId, final HttpServletRequest request, final HttpServletResponse response) {
        try {
            searchCondition.setPageSize(Integer.MAX_VALUE);
            searchCondition.addParam("P_PER_ID", pPerId);

            String[] title = new String[]{"基金名称", "一级指标", "二级指标", "三级指标", "评分细则", "评价内容", "自评得分", "审核得分"};
            //获取考核基金绩效基本信息
            PerformanceModel performanceModel = perfService.selectById(pPerId);

            Map<String, List<String[]>> map = perfService.selectPerformanceExport(searchCondition, pPerId, title);

            String zipName = "基金考核结果导出.zip";
            if(!map.isEmpty() && performanceModel != null){
                zipName = performanceModel.getpName()+".zip";
            }
            // 设置response头
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/x-zip-compressed");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(zipName, "utf-8"));
            ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());

            for (Map.Entry<String, List<String[]>> entry : map.entrySet()) {
                //考核基金名
                String key = entry.getKey();
                //String[] split = key.split(Matcher.quoteReplacement(File.separator));
                String filename = key + ".xls";
                Workbook wb = ExportDataUtils.baseExportData(entry.getValue(), title, filename,null,key);
                ZipEntry zipEntry = new ZipEntry(zipName + File.separator + key + ".xls");
                zipOutputStream.putNextEntry(zipEntry);
                wb.write(zipOutputStream);
            }
            zipOutputStream.flush();
            zipOutputStream.close();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @ApiOperation(value = "增加绩效管理列表", notes = "增加绩效管理列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "performanceModel", value = "实体类", required = true, dataType = "PerformanceModel")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/addPerf", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addPerfAfter(@RequestBody PerformanceModel performanceModel) {
        try {
            if (performanceModel != null) {
                //@当前登录人
                String userId = String.valueOf(this.getLoginUser().getUserId());
                //String userId ="109";
                if (performanceModel.getpPerID() != "" && performanceModel.getpPerID() != null) {
                    String pPerIDs = performanceModel.getpPerID();
                    //更新
                    performanceModel.setUpdateBy(userId);
                    perfService.updatePerManage(performanceModel);
                } else {
                    String id = perfService.getKey("PERF_MANAGE_ID_SEQ");
                    //String id = "10000";
                    //主键PERF_ONE_NORM_ID_SEQ
                    performanceModel.setpPerID(id);
                    performanceModel.setCreateBy(userId);
                    performanceModel.setUpdateBy(userId);
                    //增加主表，一级，二级，三级
                    perfService.insertPerManage(performanceModel);
                }

                String status_ = performanceModel.getpStatus();
                if (StringUtils.isNotEmpty(status_)) {
                    String name = codeUtils.getCodeNameByValue("10181", status_);
                    performanceModel.setpStatusName(name);
                }
                //返回給後台
                String t = null;
                PerformanceVO performance = new PerformanceVO();
                BeanUtils.copyProperties(performanceModel, performance);
                if ("0".equals(performanceModel.getTag())) {
                    //未发布
                    t = "0";
                } else if ("1".equals(performanceModel.getTag())) {
                    //发布
                    t = "1";
                }
                dataResponse.setMsg(t);
                dataResponse.setData(performance);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(dataResponse);
    }

    @ApiOperation(value = "更新绩效管理列表（暂时没用）", notes = "更新绩效管理列表（暂时没用）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "performanceModel", value = "实体类", required = true, dataType = "PerformanceModel")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/updatePerf", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updatePerf(@RequestBody PerformanceModel performanceModel) {
        try {
            if (performanceModel != null) {
                String pPerIDs = performanceModel.getpPerID();
                PerformanceModel pPer = perfService.selectPerfDetailSerId(pPerIDs);
                perfService.update(performanceModel);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }

    @ApiOperation(value = "删除绩效管理列表", notes = "删除绩效管理列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pPerId", value = "主表id(多个用,隔开)", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/deletePerfMangeAll", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deletePerfMangeAll(@RequestParam("pPerId") String pPerId) {
        try {
            String[] arr = null;
            if (pPerId != "" && pPerId != null) {
                arr = pPerId.split(",");
                perfService.deletePerAll(arr);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }

    //##########------审核，自评，管理---------------华丽分隔线--------###########
    @ApiOperation(value = "查询审核，自评管理(矩陣)", notes = "查询审核，自评管理列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "name", value = "绩效名称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "year", value = "绩效年度", required = false, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/selectPerfManagementList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectPerfManagementList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage
            , @RequestParam("name") String name, @RequestParam("year") String year
            , @RequestParam("status") String status) {
        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            if (name != null && name != "") {
                searchCondition.addConditionLike("P_NAME", "%" + name + "%");
            }
            if (year != null && year != "") {
                searchCondition.addConditionEqualTo("P_YEAR", year);
            }
            if (status != null && status != "") {
                searchCondition.addConditionEqualTo("P_STATUS", status);
            }
            PageInfo<PerformanceModel> rows = perfService.selectListPageAssess1(searchCondition);
            List<PerformanceVO> list = new ArrayList<PerformanceVO>();
            for (PerformanceModel model : rows.getList()) {
                PerformanceVO vo = new PerformanceVO();
                BeanUtils.copyProperties(model, vo);
                //考核基金数
                PerformanceModel pf = perfService.selectById(model.getpPerID());
                if (pf != null) {
                    vo.setPsFundCount(pf.getFundCount());
                    vo.setFundCount(pf.getFundCount());
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

    @ApiOperation(value = "查询审核，自评基金评测列表", notes = "查询审核，自评基金评测列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pPerId", value = "主表主键id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "psOneselfYesno", value = "是否自评(0:未自评，1：自评)", required = false, dataType = "String"),
            @ApiImplicitParam(name = "psAssessYesno", value = "是否审核(0:未审核，1：审核)", required = false, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "基金名称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "0:自评；1：审核", required = false, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/selectPerfList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectPerfList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,
                                 @RequestParam("pPerId") String pPerId, @RequestParam("psOneselfYesno") String psOneselfYesno,
                                 @RequestParam("psAssessYesno") String psAssessYesno, @RequestParam("name") String name,
                                 @RequestParam("type") String type) {
        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);

            if (StringUtils.isNotEmpty(psOneselfYesno)) {
                List<String> PS_ONESELF_YESNOS = Arrays.asList(psOneselfYesno.split(","));
                searchCondition.addConditionIn("PS_ONESELF_YESNO", PS_ONESELF_YESNOS);
            }

            if (StringUtils.isNotEmpty(psAssessYesno)) {
                List<String> psAssessYesnoS = Arrays.asList(psAssessYesno.split(","));
                searchCondition.addConditionIn("PS_ASSESS_YESNO", psAssessYesnoS);
            }

   /*         if (psOneselfYesno != null && psOneselfYesno != "") {
                if ("1".equals(psOneselfYesno)) {
                    //已自评
                    searchCondition.addConditionEqualTo("PS_ONESELF_YESNO", psOneselfYesno);
                } else if ("0".equals(psOneselfYesno)) {
                    //未自评
                    searchCondition.addConditionIsNull("PS_ONESELF_YESNO");
                }
            }*/
       /*     if (psAssessYesno != null && psAssessYesno != "") {
                if ("1".equals(psAssessYesno)) {
                    //已审核
                    searchCondition.addConditionEqualTo("PS_ASSESS_YESNO", psAssessYesno);
                } else if ("0".equals(psAssessYesno)) {
                    //未审核
                    searchCondition.addConditionIsNull("PS_ASSESS_YESNO");
                }
            }*/
            if (name != null && name != "") {
                searchCondition.addConditionLike("FUND_NAME", "%" + name + "%");
            }
            searchCondition.addParam("P_PER_ID", pPerId);
            String userId = String.valueOf(this.getLoginUser().getUserId());
            //String userId = "1005000";
            System.out.println("当前登陆人测试------查询审核，自评基金评测列表----->" + userId);
            PageInfo<PerfScoreModel> rows = perfService.selectListPageAssess(searchCondition, type, userId);
            List<PerfScoreVO> list = new ArrayList<PerfScoreVO>();
            for (PerfScoreModel model : rows.getList()) {
                if (model.getPsAssessYesno() == null) {
                    //是否考核（0未考核，1考核）
                    model.setPsAssessYesno("0");
                }
                if (model.getPsOneselfYesno() == null) {
                    //是否自评（0未自评，1自评）
                    model.setPsOneselfYesno("0");
                }
                PerfScoreVO vo = new PerfScoreVO();
                BeanUtils.copyProperties(model, vo);
                String name1 = codeUtils.getCodeNameByValue("1023", model.getPsAssessYesno());
                vo.setPsAssessYesnoName(name1);
                String name2 = codeUtils.getCodeNameByValue("1022", model.getPsOneselfYesno());
                vo.setPsOneselfYesnoName(name2);
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

    @ApiOperation(value = "查询审核，自评基金列表详情", notes = "查询审核，自评基金列表详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pPerId", value = "主表主键id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "psId", value = "分数表id（PERF_SCORE）", required = false, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/selectPerfFundDetails", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectPerfFundDetails(@RequestParam("pPerId") String pPerId, @RequestParam("fundId") String fundId) {
        try {
            searchCondition.addParam("P_PER_ID", pPerId);
            searchCondition.addParam("FUND_ID", fundId);
            //查詢一級，二級，三級，指標，以及分數
            PerformanceModel rows = perfService.selectPerfFundDetail(searchCondition, null);
            if (rows == null) {
                //查詢一級，二級，三級，指標
                rows = perfService.selectPerfDetailSer(pPerId);
            }
            PerformanceVO performance = new PerformanceVO();
            BeanUtils.copyProperties(rows, performance);
            dataResponse.setData(performance);
        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value = "自评,审核,打分", notes = "自评，审核,打分")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PerfScoreModel", value = "实体类", required = true, dataType = "PerfScoreModel")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/addPerfScoreModelDetails", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addPerfScoreModelDetails(@RequestBody PerfScoreModel perfScoreModel) {
        try {
            String userId = String.valueOf(this.getLoginUser().getUserId());
            //String userId = "100";
            System.out.println("当前登陆人测试-----自评,审核,打分------>" + userId);
            perfScoreModel.setCreateBy(userId);
            perfScoreModel.setUpdateBy(userId);
            perfService.insertPointLists(perfScoreModel);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);
    }


    @ApiOperation(value = "绩效列表导出", notes = "绩效列表导出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pYear", value = "绩效年度", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pStatus", value = "绩效状态（0未发布，1：已发布，9删除）", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/exportSelectPerf")
    @ResponseBody
    public void selectPerf(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,
                           @RequestParam("pYear") String year, @RequestParam("pStatus") String status
            , @RequestParam("keyWord") String keyWord, HttpServletResponse response) {
        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            if (year != null && year != "") {
                searchCondition.addConditionEqualTo("P_YEAR", year);
            }
            if (status != null && status != "") {
                searchCondition.addConditionEqualTo("P_STATUS", status);
            }
            if (StringUtils.isNotEmpty(keyWord)) {
                searchCondition.addConditionLike("P_NAME", "%" + keyWord + "%");
            }
            PageInfo<PerformanceModel> rows = perfService.selectListPage(searchCondition);
            List<PerformanceVO> list = new ArrayList<PerformanceVO>();
            for (PerformanceModel model : rows.getList()) {
                String status_ = model.getpStatus();
                if (StringUtils.isNotEmpty(status_)) {
                    String name = codeUtils.getCodeNameByValue("10181", status_);
                    model.setpStatusName(name);
                }
                PerformanceVO vo = new PerformanceVO();
                BeanUtils.copyProperties(model, vo);
                //考核基数
                vo.setPsFundCount(vo.getFundCount());
                list.add(vo);
            }
            //开始导出处理
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
            String[] title = new String[]{"绩效名称", "绩效年度", "考核开始时间", "考核结束时间", "考核基金数", "考核状态"};
            //文件名
            String fileName = "绩效列表_" + System.currentTimeMillis() + ".xls";
            List<String[]> dataList = new ArrayList<>();
            if (list != null && list.size() > 0) {
                for (PerformanceVO exModel : list) {
                    String[] data = new String[title.length];
                    //绩效名称
                    data[0] = exModel.getpName();
                    //绩效年度
                    data[1] = exModel.getpYear();
                    data[2] = formatter.format(exModel.getpAssessStartTime());
                    data[3] = formatter.format(exModel.getpAssessEndTime());
                    //考核基金数
                    data[4] = exModel.getPsFundCount();
                    //考核状态
                    data[5] = exModel.getpStatusName();
                    dataList.add(data);
                }
            }
            //调用导出方法
            Workbook wb = ExportDataUtils.exportData(dataList, title, fileName);
            if (wb != null) {
                OutputStream out = null;
                try {
                    out = response.getOutputStream();
                    // 告诉浏览器用什么软件可以打开此文件
                    //response.setHeader("content-Type", "application/vnd.ms-excel");
                    response.setContentType("application/vnd.ms-excel");
                    response.setCharacterEncoding("UTF-8");
                    // 下载文件的默认名称
                    response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
                    wb.write(out);
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (out != null) {
                            out.close();
                        }
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
        } catch (SystemException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @ApiOperation(value = "查询二级省政府考核基金", notes = "查询二级省政府考核基金")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "touStartTime", value = "投资开始时间", required = false, dataType = "date"),
            @ApiImplicitParam(name = "touEndTime", value = "投资结束时间", required = false, dataType = "date"),
            @ApiImplicitParam(name = "year", value = "考核年度", required = true, dataType = "String")

    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/selectFundIdAll", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectFundIdAll(@RequestBody PerManageFoundSerchBean serchBean) {
        try {
            if (StringUtils.isNotEmpty(serchBean.getTouStartTime())) {
                serchBean.setTouStartTime(serchBean.getTouStartTime() + "" + "00:00:00");
            }
            if (StringUtils.isNotEmpty(serchBean.getTouEndTime())) {
                serchBean.setTouEndTime(serchBean.getTouEndTime() + "" + "23:59:59");
            }
            if (StringUtils.isNotEmpty(serchBean.getYear())) {
                searchCondition.addParam("YEAR", serchBean.getYear());
            }
            if (StringUtils.isNotEmpty(serchBean.getpPerId())) {
                searchCondition.addParam("P_PER_ID", serchBean.getpPerId());
            }

            if(StringUtils.isNotEmpty(serchBean.getPlatProp())){
                searchCondition.addConditionEqualTo(" b.PLAT_PROP",serchBean.getPlatProp());
            }
            searchCondition.setSearchBean(serchBean);
            PageInfo<PerfManageFundModel> rows = perfManageFundService.selectListPage(searchCondition);
            List<PerfManageFundVO> list = new ArrayList<>();
            for (PerfManageFundModel model : rows.getList()) {
                String status_ = model.getStatus();
                PerfManageFundVO vo = new PerfManageFundVO();
                BeanUtils.copyProperties(model, vo);
                vo.setStatusName(status_);
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

    @ApiOperation(value = "发布绩效", notes = "发布绩效")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "performanceModel", value = "实体类", required = true, dataType = "PerformanceModel")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/addPerfManageFundList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addPerfManageFundList(@RequestBody List<PerfManageFundModel> fundList) {
        try {
            if (fundList != null && fundList.size() > 0) {
                //@当前登录人
                String userId = String.valueOf(this.getLoginUser().getUserId());
                int count = perfManageFundService.insertPerfManageFundList(fundList, userId);
                System.out.println("考核基金数------" + count);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(dataResponse);
    }


    /**
     * 随机生成数字
     *
     * @return
     */
    public static String random() {
        String id = null;
        String id1 = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        int id2 = (int) ((Math.random() * 9 + 1) * 10000);
        id = id1 + id2;
        System.out.println("###---->>" + id);
        return id;
    }

    @ApiOperation(value = "自评,审核,打分", notes = "自评，审核,打分")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PerfScoreModel", value = "实体类", required = true, dataType = "PerfScoreModel")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/updatePerfScore", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updatePerfScore(@RequestBody PerfScoreModel perfScoreModel) {
        try {
            String userId = String.valueOf(this.getLoginUser().getUserId());
            //String userId = "100";
            System.out.println("当前登陆人测试-----自评,审核,打分------>" + userId);
            perfScoreModel.setCreateBy(userId);
            perfScoreModel.setUpdateBy(userId);
            perfService.update("updatePerfScores", perfScoreModel);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);
    }


}
