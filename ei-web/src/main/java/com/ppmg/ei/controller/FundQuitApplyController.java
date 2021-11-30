package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.core.manage.interfaces.IWorkflowManager;
import com.founder.core.manage.local.WorkflowManager;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.foundation.service.SerialnoService;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.dto.FundQuitApplDTO;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.vo.FundQuitApplTProjectVO;
import com.ppmg.ei.vo.FundQuitApplTVO;
import com.ppmg.ei.vo.FundQuitApplVO;
import com.ppmg.ei.vo.FundQuitApplyListVO;
import io.swagger.annotations.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Scope("prototype")
@Api(tags = {"基金退出记录相关接口"})
public class FundQuitApplyController extends BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference(check = false)
    private FundQuitApplService fundQuitApplyService;

    @Resource(name = "codeUtils")
    private CodeUtils codeUtils;

    @Reference(check = false)
    private SerialnoService serialnoService;

    @Reference(check = false)
    private AppUserService appUserService;

    @Reference(check = false)
    private FixflowRunTaskinstanceService fixflowRunTaskinstanceService;

    @Reference(check = false)
    private FundInfoService fundInfoService;

    @Reference(check = false)
    private ProjInfoService projInfoService;

    @Reference(check = false)
    private ProjAppInfoQuitService projAppInfoQuitService;

    @Reference(check = false)
    private ProjAppInfoService projAppInfoService;

    @ApiOperation(value = "获取基金退出记录列表", notes = "获取基金退出记录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fundQuitApplyList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fundQuitApplyList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("projId") String projId) {

        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            searchCondition.addConditionEqualTo("PROJ_ID", projId);
            searchCondition.addConditionNotEqualTo("STATUS", "9");
            PageInfo<FundQuitApplModel> rows = fundQuitApplyService.selectListPage(searchCondition);
            List<FundQuitApplyListVO> list = new ArrayList<FundQuitApplyListVO>();
            for (FundQuitApplModel model : rows.getList()) {
                FundQuitApplyListVO vo = new FundQuitApplyListVO();
                BeanUtils.copyProperties(vo, model);
                if (StringUtils.isNotEmpty(model.getQuitType())) {
                    vo.setQuitTypeName(codeUtils.getCodeNameByValue("262", model.getQuitType()));
                }
                if (StringUtils.isNotEmpty(model.getQuitWay())) {
                    vo.setQuitWayName(codeUtils.getCodeNameByValue("217", model.getQuitWay()));
                }
                if (StringUtils.isNotEmpty(model.getStatus())) {
                    vo.setStatusName(codeUtils.getCodeNameByValue("336", model.getStatus()));
                }

                if (model.getProcessInstId() != null) {
                    List<FixflowRunTaskinstanceModel> flows = fixflowRunTaskinstanceService.getListByProcessinstanceId(model.getProcessInstId());
                    if (flows.get(0).getFormuriview() == null || flows.get(0).getFormuriview() == "") {
                        vo.setInstanceModel(flows.get(1));
                    } else {
                        vo.setInstanceModel(flows.get(0));
                    }
                }
                list.add(vo);
            }
            dataTablesResponse.setData(list, rows);
        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }


    @ApiIgnore
    @ApiOperation(value = "投基金退出", notes = "投基金退出")
    @ApiImplicitParam(name = "ProjContractFileDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/fundQuitApply/add", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String add(@RequestBody FundQuitApplDTO dto) {
        try {
            String userId = this.getLoginUserId();
            //String userId="1005000";
            String appId = serialnoService.getSequence("EI.TZ_T_FUND_QUIT_APPL");
            FundQuitApplModel fundQuitApplModel = new FundQuitApplModel();
            BeanUtils.copyProperties(fundQuitApplModel, dto);
            fundQuitApplModel.setAppDt(new Date());
            fundQuitApplModel.setAppUser(appUserService.getUserNameById(userId));
            fundQuitApplModel.setCreateDt(new Date());
            fundQuitApplModel.setCreateBy(userId);
            fundQuitApplModel.setUpdateBy(userId);
            fundQuitApplModel.setUpdateDt(new Date());
            fundQuitApplModel.setAppId(appId);
            fundQuitApplModel.setStatus("0");
            fundQuitApplyService.insert(fundQuitApplModel);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }

    @ApiOperation(value = "详情", notes = "详情")
    @ApiImplicitParam(name = "appId", value = "appId", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fundQuitApply/detainfo/{appId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String detail(@PathVariable(value = "appId") String appId) {
        try {
            FundQuitApplModel model = fundQuitApplyService.selectById(appId);
            FundQuitApplVO vo = new FundQuitApplVO();
            BeanUtils.copyProperties(vo, model);
            dataResponse.setData(vo);
        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataResponse.error(e.getMessage());
        } catch (Exception e) {
            dataResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteMapNullValue);
    }


    @ApiIgnore
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "appId", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "FundQuitApplVO", value = "FundQuitApplVO", required = true, dataType = "FundQuitApplVO")
    })
    @PostMapping(value = "/fundQuitApply/update/{appId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String update(@PathVariable("appId") String appId, @RequestBody FundQuitApplVO dto) {

        try {
            FundQuitApplModel model = new FundQuitApplModel();
            BeanUtils.copyProperties(model, dto);
            model.setAppId(appId);
            model.setUpdateBy(this.getLoginUserId());
            model.setUpdateDt(new Date());
            fundQuitApplyService.update("updateInfo",model);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);

    }

    @ApiIgnore
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "appId", required = true, dataType = "String", paramType = "path"),
    })
    @GetMapping(value = "/fundQuitApply/delete/{appId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delete(@PathVariable("appId") String appId) {

        try {
            fundQuitApplyService.deleteById(appId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);

    }

    /*———--------------------------—退出決策————新加----退出決策---------------------------------------*/

    @ApiOperation(value = "分页查询退出決策", notes = "分页查询退出決策", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/selectfundQuitApplyList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectfundQuitApplyList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage
            , @RequestParam("projId") String projId, @RequestParam("status") String status) {
        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            searchCondition.addConditionEqualTo("PROJ_ID", projId);
            if (StringUtils.isNotEmpty(status)) {
                searchCondition.addConditionEqualTo("STATUS", status);
            }
            PageInfo<FundQuitApplModel> rows = fundQuitApplyService.fundQuitApplListPage(searchCondition);
            ProjInfoModel pf_Serch = projInfoService.selectById(projId);
            //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
            String projType = null;
            if (pf_Serch != null) {
                projType = pf_Serch.getProjType();
            }
            if (projType != null && "2".equals(projType)) {
                //2：===============================投基金================================
                List<FundQuitApplTVO> list = new ArrayList<FundQuitApplTVO>();
                for (FundQuitApplModel model : rows.getList()) {
                    FundQuitApplTVO vo = new FundQuitApplTVO();
                    String fundId = null;
                    ProjInfoModel pf = projInfoService.selectById(projId);
                    if (pf != null) {
                        fundId = pf.getProjObject();
                        FundInfoModel fm = fundInfoService.selectById(fundId);
                        if (fm != null) {
                            //基金认缴总规模（万元)CURRENT_AMOUNT	BD_T_FUND_INFO表（认缴出资额（万元））
                            model.setFundSubscribed(fm.getCurrentAmount());
                            //基金实缴规模（万元）RAISE_AMOUNT BD_T_FUND_INFO表 （实缴出资额（万元））
                            model.setFundPaidIn(fm.getRaiseAmount());
                        }
                    }
                    BeanUtils.copyProperties(vo, model);
                    if (StringUtils.isNotEmpty(model.getQuitWay())) {
                        //退出方式
                        String name = model.getQuitWay();
                        String name2 = codeUtils.getCodeNameByValue("262", name);
                        vo.setQuitWayName(name2);
                    }
                    if (StringUtils.isNotEmpty(model.getStatus())) {
                        //流程状态
                        vo.setStatusName(codeUtils.getCodeNameByValue("599", model.getStatus()));
                    }
                    //获取taskId
                    if (StringUtils.isNotEmpty(model.getProcessInstId())) {
                        List<Map<String, Object>> listMap = fixflowRunTaskinstanceService.getFixFlowByTaskId(model.getProcessInstId());
                        if (listMap != null && listMap.size() > 0) {
                            String taskId = listMap.get(0).get("TASKINSTANCE_ID").toString();
                            vo.setTaskId(taskId);
                        } else {
                            vo.setTaskId("");
                        }
                    }
                    list.add(vo);
                }
                dataTablesResponse.setData(list, rows);
            } else if (projType != null && "1".equals(projType)) {
                //=========================投项目============================
                List<FundQuitApplTProjectVO> list = new ArrayList<>();
                for (FundQuitApplModel model : rows.getList()) {
                    FundQuitApplTProjectVO vo = new FundQuitApplTProjectVO();
                    BeanUtils.copyProperties(vo, model);
                    ProjInfoModel pf = projInfoService.selectById(projId);
                    //出资主体
                    vo.setInveName(pf.getInveName());
                    //項目名稱
                    vo.setProjName(pf.getProjName());
                    //退出立项主键
                    String quitProjId = model.getQuitProjId();
                    ProjAppInfoQuitModel model_quit = projAppInfoQuitService.selectById(quitProjId);
                    if (model_quit != null) {
                        //退出立项名称
                        vo.setQuitName(model_quit.getQuitName());
                        //退出立项日期
                        vo.setQuitDate(model_quit.getQuitDate());
                        //退出方式
                        String name = codeUtils.getCodeNameByValue("262", model_quit.getQuitWay());
                        vo.setQuitWay(model_quit.getQuitWay());
                        vo.setQuitWayName(name);
                        //投时估值（万元
                        vo.setWhenCurrency(model_quit.getWhenCurrency());
                        //退出时估值（万元
                        vo.setQuitCurrency(model_quit.getQuitCurrency());
                        //当前股比%
                        vo.setNowShare(model_quit.getNowShare());
                        //收回金額
                        Double backMony = 0.0;
                        if (model_quit.getQuitCurrency() != null && model_quit.getNowShare() != null) {
                            Double nowShare = model_quit.getNowShare() / 100;
                            backMony = model_quit.getQuitCurrency() * nowShare;
                        }
                        //收回金额（万元
                        vo.setBackMony(backMony);
                        //转让出资额（万元）
                        vo.setOverAmount(model_quit.getOverAmount());
                        //转让股比%
                        vo.setOverShare(model_quit.getOverShare());
                        //剩余股比%
                        vo.setResidueShare(model_quit.getResidueShare());
                    }

                    if (StringUtils.isNotEmpty(model.getQuitWay())) {
                        //退出方式
                        vo.setQuitWayName(codeUtils.getCodeNameByValue("262", model.getQuitWay()));
                    }
                    if (StringUtils.isNotEmpty(model.getStatus())) {
                        //流程状态
                        vo.setStatusName(codeUtils.getCodeNameByValue("599", model.getStatus()));
                    }
                    //获取taskId
                    if (StringUtils.isNotEmpty(model.getProcessInstId())) {
                        List<Map<String, Object>> listMap = fixflowRunTaskinstanceService.getFixFlowByTaskId(model.getProcessInstId());
                        if (listMap != null && listMap.size() > 0) {
                            String taskId = listMap.get(0).get("TASKINSTANCE_ID").toString();
                            vo.setTaskId(taskId);
                        } else {
                            vo.setTaskId("");
                        }
                    }
                    list.add(vo);
                }
                dataTablesResponse.setData(list, rows);
            }

        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value = "获取退出決策详情 ", notes = "获取退出決策详情", httpMethod = "GET")
    @ApiImplicitParam(name = "appId", value = "appId", required = true, dataType = "String")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/selectfundQuitApplyDetail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectfundQuitApplyDetailController(@RequestParam(value = "appId") String appId) {
        try {
            FundQuitApplModel model = fundQuitApplyService.selectById(appId);
            String fundId = null;
            String projId = model.getProjId();
            ProjInfoModel pf = projInfoService.selectById(projId);
            //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
            String projType = pf.getProjType();

            if (projType != null && "2".equals(projType)) {
                //2：投基金,立项
                fundId = pf.getProjObject();
                FundQuitApplTVO vo = new FundQuitApplTVO();
                FundInfoModel fm = fundInfoService.selectById(fundId);
                if (fm != null) {
                    //基金认缴总规模（万元)CURRENT_AMOUNT	NUMBER(20,4)	Yes		43	基金认缴总规模
                    model.setFundSubscribed(fm.getCurrentAmount());
                    //基金实缴规模（万元）RAISE_AMOUNT
                    //model.setRaiseAmount(fm.getRaiseAmount());
                    model.setFundPaidIn(fm.getRaiseAmount());
                }
                BeanUtils.copyProperties(vo, model);
                if (StringUtils.isNotEmpty(model.getQuitWay())) {
                    //退出方式
                    vo.setQuitWayName(codeUtils.getCodeNameByValue("262", model.getQuitWay()));
                }
                if (StringUtils.isNotEmpty(model.getStatus())) {
                    //流程状态
                    vo.setStatusName(codeUtils.getCodeNameByValue("599", model.getStatus()));
                }
                //获取taskId
                if (StringUtils.isNotEmpty(model.getProcessInstId())) {
                    List<Map<String, Object>> listMap = fixflowRunTaskinstanceService.getFixFlowByTaskId(model.getProcessInstId());
                    if (listMap != null && listMap.size() > 0) {
                        String taskId = listMap.get(0).get("TASKINSTANCE_ID").toString();
                        vo.setTaskId(taskId);
                    } else {
                        vo.setTaskId("");
                    }
                }
                dataResponse.setData(vo);

            } else if (projType != null && "1".equals(projType)) {
                //2：投项目
                FundQuitApplTProjectVO vo = new FundQuitApplTProjectVO();
                //出资主体
                vo.setInveName(pf.getInveName());
                //項目名稱
                vo.setProjName(pf.getProjName());
                //退出立项主键
                String quitProjId = model.getQuitProjId();
                ProjAppInfoQuitModel model_quit = projAppInfoQuitService.selectById(quitProjId);
                BeanUtils.copyProperties(vo, model);
                if (model_quit != null) {
                    //退出立项名称
                    vo.setQuitName(model_quit.getQuitName());
                    //退出立项日期
                    vo.setQuitDate(model_quit.getQuitDate());
                    //退出方式
                    String name = codeUtils.getCodeNameByValue("262", model_quit.getQuitWay());
                    vo.setQuitWay(model_quit.getQuitWay());
                    vo.setQuitWayName(name);
                    //投时估值（万元
                    vo.setWhenCurrency(model_quit.getWhenCurrency());
                    // 退出时估值（万元
                    vo.setQuitCurrency(model_quit.getQuitCurrency());
                    // 当前股比%
                    vo.setNowShare(model_quit.getNowShare());
                    Double backMony = 0.0;
                    if (model_quit.getQuitCurrency() != null && model_quit.getNowShare() != null) {
                        Double nowShare = model_quit.getNowShare() / 100;
                        backMony = model_quit.getQuitCurrency() * nowShare;
                    }
                    //收回金额（万元
                    vo.setBackMony(backMony);
                    //转让出资额（万元）
                    vo.setOverAmount(model_quit.getOverAmount());
                    //转让股比%
                    vo.setOverShare(model_quit.getOverShare());
                    //剩余股比%
                    vo.setResidueShare(model_quit.getResidueShare());
                }

                if (StringUtils.isNotEmpty(model.getQuitWay())) {
                    //退出方式
                    vo.setQuitWayName(codeUtils.getCodeNameByValue("262", model.getQuitWay()));
                }
                if (StringUtils.isNotEmpty(model.getStatus())) {
                    //流程状态
                    vo.setStatusName(codeUtils.getCodeNameByValue("599", model.getStatus()));
                }
                //获取taskId
                if (StringUtils.isNotEmpty(model.getProcessInstId())) {
                    List<Map<String, Object>> listMap = fixflowRunTaskinstanceService.getFixFlowByTaskId(model.getProcessInstId());
                    if (listMap != null && listMap.size() > 0) {
                        String taskId = listMap.get(0).get("TASKINSTANCE_ID").toString();
                        vo.setTaskId(taskId);
                    } else {
                        vo.setTaskId("");
                    }
                }
                dataResponse.setData(vo);
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResponse.error();
        }
        return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }


    @ApiOperation(value = "查询退出决策审批通过的数据 ", notes = "查询退出决策审批通过的数据", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "projId", value = "projId", required = true, dataType = "String"),
            @ApiImplicitParam(name = "status(2:审批通过)", value = "status", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/selectfundQuitApplyListTwo", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectfundQuitApplyListTwo(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage
            , @RequestParam("projId") String projId, @RequestParam("status") String status) {
        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            searchCondition.addConditionEqualTo("PROJ_ID", projId);
            if (StringUtils.isNotEmpty(status)) {
                searchCondition.addConditionEqualTo("STATUS", status);
            }

            PageInfo<FundQuitApplModel> rows = fundQuitApplyService.fundQuitApplListPage(searchCondition);
            ProjInfoModel pf_Serch = projInfoService.selectById(projId);//主表
            //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
            String projType = pf_Serch.getProjType();
            if (projType != null && "2".equals(projType)) {//2：投基金,立项
                List<FundQuitApplTVO> list = new ArrayList<FundQuitApplTVO>();
                for (FundQuitApplModel model : rows.getList()) {
                    FundQuitApplTVO vo = new FundQuitApplTVO();
                    String fundId = null;
                    ProjInfoModel pf = projInfoService.selectById(projId);//主表
                    if (pf != null) {
						/*if("2".equals(pf.getProjType())||"4".equals(pf.getProjType())){
                        fundId = pf.getProjObject();
						}else{
							fundId = pf.getInveId();
						}*/
                        fundId = pf.getProjObject();
                        FundInfoModel fm = fundInfoService.selectById(fundId);
                        if (fm != null) {
                            //基金认缴总规模（万元)CURRENT_AMOUNT	NUMBER(20,4)	Yes		43	基金认缴总规模
                            model.setFundSubscribed(fm.getCurrentAmount());
                            //基金实缴规模（万元）RAISE_AMOUNT
                            model.setFundPaidIn(fm.getRaiseAmount());
                        }
                    }
                    BeanUtils.copyProperties(vo, model);
                    if (StringUtils.isNotEmpty(model.getQuitWay())) {//退出方式
                        vo.setQuitWayName(codeUtils.getCodeNameByValue("262", model.getQuitWay()));
                    }
                    if (StringUtils.isNotEmpty(model.getStatus())) {//流程状态
                        vo.setStatusName(codeUtils.getCodeNameByValue("599", model.getStatus()));
                    }
                    list.add(vo);
                }
                dataTablesResponse.setData(list, rows);
            } else if (projType != null && "1".equals(projType)) {//投项目
                List<FundQuitApplTProjectVO> list = new ArrayList<>();
                for (FundQuitApplModel model : rows.getList()) {
                    FundQuitApplTProjectVO vo = new FundQuitApplTProjectVO();
                    BeanUtils.copyProperties(vo, model);
                    ProjInfoModel pf = projInfoService.selectById(projId);//主表
                    vo.setInveName(pf.getInveName());//出资主体
                    vo.setProjName(pf.getProjName());//項目名稱
                    String quitProjId = model.getQuitProjId();//退出立项主键
                    ProjAppInfoQuitModel model_quit = projAppInfoQuitService.selectById(quitProjId);

                    if (model_quit != null) {
                        vo.setQuitName(model_quit.getQuitName());//退出立项名称
                        vo.setQuitDate(model_quit.getQuitDate());//退出立项日期
                        String name = codeUtils.getCodeNameByValue("262", model_quit.getQuitWay());//退出方式
                        vo.setQuitWay(model_quit.getQuitWay());//退出方式
                        vo.setQuitWayName(name);//退出方式

                        vo.setWhenCurrency(model_quit.getWhenCurrency());//投时估值（万元
                        vo.setQuitCurrency(model_quit.getQuitCurrency());//退出时估值（万元
                        vo.setNowShare(model_quit.getNowShare());//当前股比%
                        Double backMony = 0.0;
                        if (model_quit.getQuitCurrency() != null && model_quit.getNowShare() != null) {
                            Double nowShare = model_quit.getNowShare() / 100;
                            backMony = model_quit.getQuitCurrency() * nowShare;
                        }
                        vo.setBackMony(backMony);//收回金额（万元
                        vo.setOverAmount(model_quit.getOverAmount());//转让出资额（万元）
                        vo.setOverShare(model_quit.getOverShare());//转让股比%
                        vo.setResidueShare(model_quit.getResidueShare());//剩余股比%
                    }

                    if (StringUtils.isNotEmpty(model.getQuitWay())) {//退出方式
                        vo.setQuitWayName(codeUtils.getCodeNameByValue("262", model.getQuitWay()));
                    }
                    if (StringUtils.isNotEmpty(model.getStatus())) {//流程状态
                        vo.setStatusName(codeUtils.getCodeNameByValue("599", model.getStatus()));
                    }
                    list.add(vo);
                }
                dataTablesResponse.setData(list, rows);
            }

        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    @ApiIgnore
    @ApiOperation(value = "新增退出決策", notes = "新增退出決策", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "FundQuitApplModel", value = "FundQuitApplModel", required = true, dataType = "FundQuitApplModel")
    })
    @RequestMapping(value = "/addfundQuitApplyList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addfundQuitApplyListController(@RequestBody FundQuitApplModel fundQuitApplModel) {
        try {
            if (fundQuitApplModel != null) {
                String projId = fundQuitApplModel.getProjId();
                ProjInfoModel pf_Serch = projInfoService.selectById(projId);//主表
                //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
                String projType = pf_Serch.getProjType();
                if (projType != null && "2".equals(projType)) {
                    //判断投基金退出决策名称是否重复
                    FundQuitApplModel ff = new FundQuitApplModel();
                    ff.setProjId(fundQuitApplModel.getProjId());
                    List<FundQuitApplModel> listName = fundQuitApplyService.selectList(ff);
                    for (FundQuitApplModel tk : listName) {
                        String appName = tk.getAppName();
                        if(appName.equals(fundQuitApplModel.getAppName())){
                            baseResponse.setStatus("400");
                            baseResponse.setMsg("退出决策名称已被占用,请重新输入合适名称");
                            return JSONObject.toJSONString(baseResponse);
                        }
                    }
                }

                //更新主表时间
                ProjAppInfoModel proAppInfo = new ProjAppInfoModel();
                proAppInfo.setProjId(projId);
                proAppInfo.setUpdateDt(new Date());//更新时间
                projAppInfoService.updateAppInfo(proAppInfo);
                ProjInfoModel projInfoModel = new ProjInfoModel();
                projInfoModel.setProjId(projId);
                projInfoModel.setUpdateDt(new Date());//更新时间
                projInfoService.updateprojInfo(projInfoModel);
                String userId = String.valueOf(this.getLoginUser().getUserId());
                //String userId = UimUtilsUserId.getUserId(projType);
                System.out.println("当前登陆人测试----------->" + userId);
                AppUserModel mo= appUserService.selectById(userId);
                Long deptId=mo.getDeptid();
                //Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();//獲取部門
                String appId = randomConition();
                fundQuitApplModel.setAppId(appId);
                fundQuitApplModel.setCreateBy(userId);
                fundQuitApplModel.setUpdateBy(userId);
                String tag = fundQuitApplModel.getTag();
                String fundId = null;
                String fundType = "0";//默认0,结束,1：专项自基金；2：市场化子基金
                String fundTypeProject = "1";//默认1：基金分类-1自有基金，2管理基金;3受托资金
                //ProjInfoModel pf = projInfoService.selectById(projId);//主表
                if (pf_Serch != null) {
                    fundId = pf_Serch.getProjObject();
                    FundInfoModel fm = fundInfoService.selectById(fundId);
                    if (fm != null) {
                        //groupId：1：代表是省政府基金，才会有1：专项自基金；2：市场化子基金，否则直接流程结束
                        Long groupId = fm.getGroupId();
                        String type = fm.getFundGenre();
                        if (groupId != null && groupId == 1) {
                            if (type != "" && type != null) {
                                fundType = type;
                            }
                        }
                    }
                    String fundId2 = pf_Serch.getInveId();
                    FundInfoModel fm2 = fundInfoService.selectById(fundId2);
                    if (fm2 != null) {
                        String t2 = fm2.getFundsType();//基金分类-1自有基金，2管理基金;3受托资金
                        if (t2 != "" && t2 != null) {
                            fundTypeProject = t2;
                        }
                    }
                }
                if (tag != null && "0".equals(tag)) {//提交流程并且保存数据
                    IWorkflowManager wm = new WorkflowManager();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date = sdf.format(new Date());
                    String authid = userId;
                    //主键id
                    String businessKey = appId;
                    String processID = null;
                    String subject = null;
                    Map<String, Object> formMap = null;
                    if (projType != null && "2".equals(projType)) {
                        //投基金
                        //流程编号
                        processID = "TG_ExitDecision";
                        //流程主题
                        //subject = "投管基金-退出決策" + "-" + date;
                        //流程名称：项目或基金名称（股权相关的功能）
                        subject = "退出决策" + "：" + pf_Serch.getProjName();
                        logger.info("##--------------ProjName--->>" + pf_Serch.getProjName() + "<--subject--->" + subject);
                        //其他參數
                        formMap = new HashMap<String, Object>();
                        formMap.put("taskTitle", subject);
                        formMap.put("businessKey", businessKey);
                        formMap.put("fundType", fundType);//基金類型，专项子基金or市场化子基金
                        formMap.put("deptId", deptId);
                        formMap.put("projId", projId);
                        formMap.put("fundId", fundId);
                        formMap.put("quitWay", fundQuitApplModel.getQuitWay());//退出方式--1：全部退出；2：部分退出
                    } else if (projType != null && "1".equals(projType)) {
                        //投项目
                        //流程编号
                        processID = "TG_ExitDecision_project";
                        //流程主题
                        //subject = "投项目-退出決策" + "-" + date;
                        //流程名称：项目或基金名称（股权相关的功能）
                        subject = "退出决策" + "：" + pf_Serch.getProjName();
                        logger.info("##--------------ProjName--->>" + pf_Serch.getProjName() + "<--subject--->" + subject);
                        //其他參數
                        formMap = new HashMap<String, Object>();
                        formMap.put("taskTitle", subject);
                        formMap.put("businessKey", businessKey);
                        formMap.put("fundType", fundTypeProject);//基金分类-1自有基金，2管理基金;3受托资金
                        formMap.put("deptId", deptId);
                        formMap.put("projId", projId);
                        formMap.put("fundId", fundId);
                        formMap.put("quitWay", fundQuitApplModel.getQuitWay());//退出方式--1：全部退出；2：部分退出
                        ProjAppInfoQuitModel model_quit = projAppInfoQuitService.selectById(fundQuitApplModel.getQuitProjId());
                        Double backMony = 0.0;//收回金额
                        String quitWay = null;//退出方式
                        if (model_quit != null) {
                            if (model_quit.getQuitCurrency() != null && model_quit.getNowShare() != null) {
                                Double nowShare = model_quit.getNowShare() / 100;
                                backMony = model_quit.getQuitCurrency() * nowShare;
                            }
                            quitWay = model_quit.getQuitWay();
                        }
                        formMap.put("backMony", backMony);//收回金额
                        formMap.put("quitWay", quitWay);//收回金额:1：全部退出；2：部分退出
                    }
                    //启动流程
                    ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", processID, businessKey, authid);
                    //"提交成功！"
                    if (inst != null) {
                        logger.info("##------->流程启动成功<--------##");
                        //保存数据
                        fundQuitApplModel.setStatus("1");//审批中
                        fundQuitApplModel.setProcessInstId(inst.getId());
                        fundQuitApplyService.insertFundQuitAppl(fundQuitApplModel);
                    } else {
                        logger.error("##------->流程启动失败<--------##");
                        baseResponse.error("流程启动失败");
                    }
                } else if (tag != null && "1".equals(tag)) {
                    //保存数据
                    fundQuitApplModel.setStatus("0");//草稿
                    fundQuitApplyService.insertFundQuitAppl(fundQuitApplModel);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);
    }

    @ApiIgnore
    @ApiOperation(value = "修改退出決策", notes = "修改退出決策", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "FundQuitApplModel", value = "FundQuitApplModel", required = true, dataType = "FundQuitApplModel")
    })
    @RequestMapping(value = "/updatefundQuitApplyList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updatefundQuitApplyListController(@RequestBody FundQuitApplModel fundQuitApplModel) {
        try {
            if (fundQuitApplModel != null) {
                String projId = fundQuitApplModel.getProjId();
                ProjInfoModel pf_Serch = projInfoService.selectById(projId);//主表
                //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
                String projType = pf_Serch.getProjType();
                if (projType != null && "2".equals(projType)) {
                    //判断投基金退出决策名称是否重复
                    FundQuitApplModel ff = new FundQuitApplModel();
                    ff.setProjId(fundQuitApplModel.getProjId());
                    List<FundQuitApplModel> listName = fundQuitApplyService.selectList(ff);
                    for (FundQuitApplModel tk : listName) {
                        String appName = tk.getAppName();
                        String id = tk.getAppId();
                        if(appName.equals(fundQuitApplModel.getAppName())){
                            if(!id.equals(fundQuitApplModel.getAppId())){
                                baseResponse.setStatus("400");
                                baseResponse.setMsg("退出决策名称已被占用,请重新输入合适名称");
                                return JSONObject.toJSONString(baseResponse);
                            }
                        }
                    }
                }
                //更新主表时间
                ProjAppInfoModel proAppInfo = new ProjAppInfoModel();
                proAppInfo.setProjId(projId);
                proAppInfo.setUpdateDt(new Date());//更新时间
                projAppInfoService.updateAppInfo(proAppInfo);
                ProjInfoModel projInfoModel = new ProjInfoModel();
                projInfoModel.setProjId(projId);
                projInfoModel.setUpdateDt(new Date());//更新时间
                projInfoService.updateprojInfo(projInfoModel);

                String userId = String.valueOf(this.getLoginUser().getUserId());
                //String userId = UimUtilsUserId.getUserId(projType);
                System.out.println("当前登陆人测试----------->" + userId);
                AppUserModel mo= appUserService.selectById(userId);
                Long deptId=mo.getDeptid();
                //Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();//獲取部門
                String tag = fundQuitApplModel.getTag();
                String fundId = null;
                String fundType = "0";//默认0,1：专项自基金；2：市场化子基金
                String fundTypeProject = "1";//默认0：基金分类-0自有基金，1管理基金
                //ProjInfoModel pf = projInfoService.selectById(projId);//主表
                FundInfoModel fm = null;
                if (pf_Serch != null) {
                    fundId = pf_Serch.getProjObject();
                    fm = fundInfoService.selectById(fundId);
                    if (fm != null) {
                        //groupId：1：代表是省政府基金，才会有1：专项自基金；2：市场化子基金，否则直接流程结束
                        Long groupId = fm.getGroupId();
                        String type = fm.getFundGenre();
                        if (groupId != null && groupId == 1) {
                            if (type != "" && type != null) {
                                fundType = type;
                            }
                        }
                    }
                    String fundId2 = pf_Serch.getInveId();
                    FundInfoModel fm2 = fundInfoService.selectById(fundId2);
                    if (fm2 != null) {
                        String t2 = fm2.getFundsType();//基金分类-1自有基金，2管理基金;3受托资金
                        if (t2 != "" && t2 != null) {
                            fundTypeProject = t2;
                        }
                    }
                }
                if (tag != null && "0".equals(tag)) {//提交流程并且保存数据
                    IWorkflowManager wm = new WorkflowManager();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date = sdf.format(new Date());
                    String authid = userId;
                    //主键id
                    String businessKey = fundQuitApplModel.getAppId();
                    String processID = null;
                    String subject = null;
                    Map<String, Object> formMap = null;
                    if (projType != null && "2".equals(projType)) {//投基金
                        //流程编号
                        processID = "TG_ExitDecision";
                        //流程主题
                        //subject = "投管基金-退出決策" + "-" + date;
                        //流程名称：项目或基金名称（股权相关的功能）
                        subject = "退出决策" + ":" + pf_Serch.getProjName();
                        logger.info("##--------------ProjName--->>" + pf_Serch.getProjName() + "<--subject--->" + subject);
                        //其他參數
                        formMap = new HashMap<String, Object>();
                        formMap.put("taskTitle", subject);
                        formMap.put("businessKey", businessKey);
                        formMap.put("fundType", fundType);//基金類型，专项子基金or市场化子基金
                        formMap.put("deptId", deptId);
                        formMap.put("projId", projId);
                        formMap.put("fundId", fundId);
                        formMap.put("quitWay", fundQuitApplModel.getQuitWay());//收回金额:1：全部退出；2：部分退出
                    } else if (projType != null && "1".equals(projType)) {//投项目
                        //流程编号
                        processID = "TG_ExitDecision_project";
                        //流程主题
                        //subject = "投项目-退出決策" + "-" + date;
                        //流程名称：项目或基金名称（股权相关的功能）
                        subject = "退出决策" + "：" + pf_Serch.getProjName();
                        logger.info("##--------------ProjName--->>" + pf_Serch.getProjName() + "<--subject--->" + subject);
                        //其他參數
                        formMap = new HashMap<String, Object>();
                        formMap.put("taskTitle", subject);
                        formMap.put("businessKey", businessKey);
                        formMap.put("fundType", fundTypeProject);//基金分类-1自有基金，2管理基金;3受托资金
                        formMap.put("deptId", deptId);
                        formMap.put("projId", projId);
                        formMap.put("fundId", fundId);
                        ProjAppInfoQuitModel model_quit = projAppInfoQuitService.selectById(fundQuitApplModel.getQuitProjId());
                        Double backMony = 0.0;
                        String quitWay = null;//退出方式
                        if (model_quit != null) {
                            if (model_quit.getQuitCurrency() != null && model_quit.getNowShare() != null) {
                                Double nowShare = model_quit.getNowShare() / 100;
                                backMony = model_quit.getQuitCurrency() * nowShare;
                            }
                            quitWay = model_quit.getQuitWay();
                        }
                        formMap.put("backMony", backMony);
                        formMap.put("quitWay", quitWay);//收回金额
                    }
                    //启动流程
                    ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", processID, businessKey, authid);
                    //"提交成功！"
                    if (inst != null) {
                        logger.info("##------->流程启动成功<--------##");
                        //保存数据
                        fundQuitApplModel.setStatus("1");//审批中
                        fundQuitApplModel.setUpdateDt(new Date());
                        fundQuitApplModel.setProcessInstId(inst.getId());
                        fundQuitApplyService.updateFundQuitAppl(fundQuitApplModel);
                    } else {
                        logger.error("##------->流程启动失败<--------##");
                        baseResponse.error("流程启动失败");
                    }
                } else if (tag != null && "1".equals(tag)) {
                    //保存数据
                    fundQuitApplModel.setStatus("0");//草稿
                    fundQuitApplModel.setUpdateDt(new Date());
                    fundQuitApplyService.updateFundQuitAppl(fundQuitApplModel);
                } else if (tag != null && "3".equals(tag)) {
                    //保存数据
                    fundQuitApplyService.updateFundQuitAppl(fundQuitApplModel);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }

    @ApiIgnore
    @ApiOperation(value = "删除退出決策", notes = "删除退出決策", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "appId", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = "/deletefundQuitApplyList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deletefundQuitApplyListController(@RequestParam("appId") String appId) {
        try {
            String[] arr = null;
            if (appId != "" && appId != null) {
                arr = appId.split(",");
                fundQuitApplyService.deleteBatch(arr);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }


    /**
     * 随机生成数字
     *
     * @return
     */
    public static String randomConition() {
        String id = null;
        String id1 = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        int id2 = (int) ((Math.random() * 9 + 1) * 10000);
        id = id1 + id2;
        System.out.println("###---->>" + id);
        return id;
    }

    /**
     ********↓↓↓↓↓↓↓↓↓↓*********平台名称：金财合盈*******↓↓↓↓↓↓*******管理公司退出决策*******↓↓↓↓↓↓↓↓*********
     */

    @ApiIgnore
    @ApiOperation(value = "管理公司新增退出決策", notes = "新增退出決策", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "FundQuitApplModel", value = "FundQuitApplModel", required = true, dataType = "FundQuitApplModel")
    })
    @RequestMapping(value = "/addfundQuitApplyListMc", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addfundQuitApplyListMcController(@RequestBody FundQuitApplModel fundQuitApplModel) {
        try {
            if (fundQuitApplModel != null) {
                String projId = fundQuitApplModel.getProjId();
                ProjInfoModel pf_Serch = projInfoService.selectById(projId);
                //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
                String projType = pf_Serch.getProjType();
                String userId = String.valueOf(this.getLoginUser().getUserId());
                //String userId = UimUtilsUserId.getUserId(projType);
                System.out.println("管理公司,当前登陆人测试----------->" + userId);
                AppUserModel mo= appUserService.selectById(userId);
                Long deptId=mo.getDeptid();
                //Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();
                String appId = randomConition();
                fundQuitApplModel.setAppId(appId);
                fundQuitApplModel.setCreateBy(userId);
                fundQuitApplModel.setCreateDt(new Date());
                fundQuitApplModel.setUpdateBy(userId);
                fundQuitApplModel.setUpdateDt(new Date());
                String tag = fundQuitApplModel.getTag();
                String fundId = null;
                //默认0,结束,1：专项自基金；2：市场化子基金
                String fundType = "0";
                //默认1：基金分类-1自有基金，2管理基金;3受托资金//默认1：基金分类-1自有基金，2管理基金;3受托资金
                String fundTypeProject = "1";
                if (pf_Serch != null) {
                    fundId = pf_Serch.getProjObject();
                    FundInfoModel fm = fundInfoService.selectById(fundId);
                    if (fm != null) {
                        //groupId：1：代表是省政府基金，才会有1：专项自基金；2：市场化子基金，否则直接流程结束
                        Long groupId = fm.getGroupId();
                        String type = fm.getFundGenre();
                        if (groupId != null && groupId == 1) {
                            if (type != null && type != "") {
                                fundType = type;
                            }
                        }
                    }
                    String fundIdTwo = pf_Serch.getInveId();
                    FundInfoModel fundModelTwo = fundInfoService.selectById(fundIdTwo);
                    if (fundModelTwo != null) {
                        //基金分类-1自有基金，2管理基金;3受托资金
                        String fundsTypeTwo = fundModelTwo.getFundsType();
                        if (fundsTypeTwo != null && fundsTypeTwo != "") {
                            fundTypeProject = fundsTypeTwo;
                        }
                    }
                }
                //提交流程并且保存数据
                if (tag != null && "0".equals(tag)) {
                    IWorkflowManager wm = new WorkflowManager();
                    String authid = userId;
                    //主键id
                    String businessKey = appId;
                    Map<String, Object> formMap = new HashMap<>();
                    //流程编号
                    String processID = "MC_WithdrawalDecision";
                    //流程主题
                    String subject = "退出决策" + "：" + pf_Serch.getProjName();
                    logger.info("##--------------ProjName--->>" + pf_Serch.getProjName() + "<--subject--->" + subject);
                    //其他參數
                    formMap.put("taskTitle", subject);
                    formMap.put("businessKey", businessKey);
                    //基金分类-1自有基金，2管理基金;3受托资金
                    formMap.put("fundType", fundTypeProject);
                    formMap.put("deptId", deptId);
                    formMap.put("projId", projId);
                    formMap.put("fundId", fundId);
                    //退出方式--1：全部退出；2：部分退出
                    formMap.put("quitWay", fundQuitApplModel.getQuitWay());
                    ProjAppInfoQuitModel model_quit = projAppInfoQuitService.selectById(fundQuitApplModel.getQuitProjId());
                    //收回金额
                    Double backMony = 0.0;
                    //退出方式
                    String quitWay = null;
                    if (model_quit != null) {
                        if (model_quit.getQuitCurrency() != null && model_quit.getNowShare() != null) {
                            Double nowShare = model_quit.getNowShare() / 100;
                            backMony = model_quit.getQuitCurrency() * nowShare;
                        }
                        quitWay = model_quit.getQuitWay();
                    }
                    //收回金额
                    formMap.put("backMony", backMony);
                    //收回金额:1：全部退出；2：部分退出
                    formMap.put("quitWay", quitWay);
                    //保存数据
                    fundQuitApplModel.setStatus("0");
                    fundQuitApplyService.insertFundQuitAppl(fundQuitApplModel);
                    //启动流程
                    ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", processID, businessKey, authid);
                    //"提交成功！"
                    if (inst != null) {
                        logger.info("##------->流程启动成功<--------##");
                        //更新数据
                        FundQuitApplModel model = new FundQuitApplModel();
                        model.setAppId(fundQuitApplModel.getAppId());
                        // 审批中
                        model.setStatus("1");
                        model.setProcessInstId(inst.getId());
                        fundQuitApplyService.updateFundQuitAppl(model);
                    } else {
                        logger.error("##------->流程启动失败<--------##");
                        baseResponse.error("流程启动失败");
                    }
                } else if (tag != null && "1".equals(tag)) {
                    //保存数据
                    // 草稿
                    fundQuitApplModel.setStatus("0");
                    fundQuitApplyService.insertFundQuitAppl(fundQuitApplModel);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);
    }

    @ApiIgnore
    @ApiOperation(value = "管理公司修改退出決策", notes = "修改退出決策", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "FundQuitApplModel", value = "FundQuitApplModel", required = true, dataType = "FundQuitApplModel")
    })
    @RequestMapping(value = "/updatefundQuitApplyListMc", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updatefundQuitApplyListMcController(@RequestBody FundQuitApplModel fundQuitApplModel) {
        try {
            if (fundQuitApplModel != null) {
                String projId = fundQuitApplModel.getProjId();
                ProjInfoModel pf_Serch = projInfoService.selectById(projId);//主表
                //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
                String projType = pf_Serch.getProjType();
                String userId = String.valueOf(this.getLoginUser().getUserId());
                //String userId = UimUtilsUserId.getUserId(projType);
                System.out.println("当前登陆人测试----------->" + userId);
                AppUserModel mo= appUserService.selectById(userId);
                Long deptId=mo.getDeptid();
               // Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();
                String tag = fundQuitApplModel.getTag();
                String fundId = null;
                //默认0,1：专项自基金；2：市场化子基金
                String fundType = "0";
                //默认1：基金分类-0自有基金，1管理基金
                String fundTypeProject = "1";
                FundInfoModel fm = null;
                if (pf_Serch != null) {
                    fundId = pf_Serch.getProjObject();
                    fm = fundInfoService.selectById(fundId);
                    if (fm != null) {
                        //groupId：1：代表是省政府基金，才会有1：专项自基金；2：市场化子基金，否则直接流程结束
                        Long groupId = fm.getGroupId();
                        String type = fm.getFundGenre();
                        if (groupId != null && groupId == 1) {
                            if (type != "" && type != null) {
                                fundType = type;
                            }
                        }
                    }
                    String fundIdTwo = pf_Serch.getInveId();
                    FundInfoModel fundModelTwo = fundInfoService.selectById(fundIdTwo);
                    if (fundModelTwo != null) {
                        //基金分类-1自有基金，2管理基金;3受托资金
                        String t2 = fundModelTwo.getFundsType();
                        if (t2 != "" && t2 != null) {
                            fundTypeProject = t2;
                        }
                    }
                }
                //提交流程并且保存数据
                if (tag != null && "0".equals(tag)) {
                    IWorkflowManager wm = new WorkflowManager();
                    String authid = userId;
                    //主键id
                    String businessKey = fundQuitApplModel.getAppId();
                    Map<String, Object> formMap = new HashMap<>();
                    //流程编号
                    String processID = "MC_WithdrawalDecision";
                    //流程主题
                    String subject = "退出决策" + "：" + pf_Serch.getProjName();
                    logger.info("##--------------ProjName--->>" + pf_Serch.getProjName() + "<--subject--->" + subject);
                    //其他參數
                    formMap.put("taskTitle", subject);
                    formMap.put("businessKey", businessKey);
                    //基金分类-1自有基金，2管理基金;3受托资金
                    formMap.put("fundType", fundTypeProject);
                    formMap.put("deptId", deptId);
                    formMap.put("projId", projId);
                    formMap.put("fundId", fundId);
                    ProjAppInfoQuitModel model_quit = projAppInfoQuitService.selectById(fundQuitApplModel.getQuitProjId());
                    Double backMony = 0.0;
                    //退出方式
                    String quitWay = null;
                    if (model_quit != null) {
                        if (model_quit.getQuitCurrency() != null && model_quit.getNowShare() != null) {
                            Double nowShare = model_quit.getNowShare() / 100;
                            backMony = model_quit.getQuitCurrency() * nowShare;
                        }
                        quitWay = model_quit.getQuitWay();
                    }
                    //收回金额
                    formMap.put("backMony", backMony);
                    formMap.put("quitWay", quitWay);
                    //保存数据
                    fundQuitApplModel.setUpdateDt(new Date());
                    fundQuitApplModel.setUpdateBy(userId);
                    fundQuitApplyService.updateFundQuitAppl(fundQuitApplModel);
                    //启动流程
                    ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", processID, businessKey, authid);
                    //"提交成功！"
                    if (inst != null) {
                        logger.info("##------->流程启动成功<--------##");
                        //保存数据
                        // 审批中
                        FundQuitApplModel model = new FundQuitApplModel();
                        model.setAppId(fundQuitApplModel.getAppId());
                        model.setStatus("1");
                        model.setProcessInstId(inst.getId());
                        fundQuitApplyService.updateFundQuitAppl(model);
                    } else {
                        logger.error("##------->流程启动失败<--------##");
                        baseResponse.error("流程启动失败");
                    }
                } else if (tag != null && "1".equals(tag)) {
                    //保存数据
                    // 草稿
                    fundQuitApplModel.setStatus("0");
                    fundQuitApplModel.setUpdateDt(new Date());
                    fundQuitApplModel.setUpdateBy(userId);
                    fundQuitApplyService.updateFundQuitAppl(fundQuitApplModel);
                } else if (tag != null && "3".equals(tag)) {
                    //保存数据
                    fundQuitApplModel.setUpdateDt(new Date());
                    fundQuitApplModel.setUpdateBy(userId);
                    fundQuitApplyService.updateFundQuitAppl(fundQuitApplModel);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }
//################################一期系统################################################
    @ApiOperation(value = "一期参股获取退出记录列表", notes = "获取退出记录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/quit/quitApplyList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String quitApplyList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("projId") String projId) {

        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            searchCondition.addConditionEqualTo("PROJ_ID", projId);
            searchCondition.addConditionNotEqualTo("STATUS", "9");
            PageInfo<FundQuitApplModel> rows = fundQuitApplyService.selectListInfoPage(searchCondition);
            List<FundQuitApplyListVO> list = new ArrayList<FundQuitApplyListVO>();
            for (FundQuitApplModel model : rows.getList()) {
                FundQuitApplyListVO vo = new FundQuitApplyListVO();
                BeanUtils.copyProperties(vo, model);
                if (StringUtils.isNotEmpty(model.getQuitType())) {
                    vo.setQuitTypeName(codeUtils.getCodeNameByValue("262", model.getQuitType()));
                }
                if (StringUtils.isNotEmpty(model.getQuitWay())) {
                    vo.setQuitWayName(codeUtils.getCodeNameByValue("21717", model.getQuitWay()));
                }
                if (StringUtils.isNotEmpty(model.getStatus())) {
                    vo.setStatusName(codeUtils.getCodeNameByValue("336", model.getStatus()));
                }
                list.add(vo);
            }
            dataTablesResponse.setData(list, rows);
        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    @ApiIgnore
    @ApiOperation(value = "投基金退出", notes = "投基金退出")
    @ApiImplicitParam(name = "ProjContractFileDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/fundQuitApply/saveInfo", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String saveInfo(@RequestBody FundQuitApplDTO dto) {
        try {
            String userId = this.getLoginUserId();
            if(StringUtils.isEmpty(dto.getAppId())){
                String appId = serialnoService.getSequence("EI.TZ_T_FUND_QUIT_APPL");
                FundQuitApplModel fundQuitApplModel = new FundQuitApplModel();
                BeanUtils.copyProperties(fundQuitApplModel, dto);
                fundQuitApplModel.setAppDt(new Date());
                fundQuitApplModel.setAppUser(appUserService.getUserNameById(userId));
                fundQuitApplModel.setCreateDt(new Date());
                fundQuitApplModel.setCreateBy(userId);
                fundQuitApplModel.setUpdateBy(userId);
                fundQuitApplModel.setUpdateDt(new Date());
                fundQuitApplModel.setAppId(appId);
                fundQuitApplyService.insert(fundQuitApplModel);
            }else{
                FundQuitApplModel fundQuitApplModel = new FundQuitApplModel();
                BeanUtils.copyProperties(fundQuitApplModel, dto);
                fundQuitApplModel.setAppDt(new Date());
                fundQuitApplModel.setAppUser(appUserService.getUserNameById(userId));
                fundQuitApplModel.setUpdateBy(userId);
                fundQuitApplModel.setUpdateDt(new Date());
                fundQuitApplyService.update(fundQuitApplModel);
            }


        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }


    @ApiOperation(value = "详情", notes = "详情")
    @ApiImplicitParam(name = "appId", value = "appId", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fundQuitApply/getDtail/{appId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getDtail(@PathVariable(value = "appId") String appId) {
        try {
            FundQuitApplModel model = fundQuitApplyService.selectById(appId);
            FundQuitApplVO vo = new FundQuitApplVO();
            BeanUtils.copyProperties(vo, model);
            if (StringUtils.isNotEmpty(model.getQuitType())) {
                vo.setQuitTypeName(codeUtils.getCodeNameByValue("262", model.getQuitType()));
            }
            if (StringUtils.isNotEmpty(model.getQuitWay())) {
                vo.setQuitWayName(codeUtils.getCodeNameByValue("21717", model.getQuitWay()));
            }
            dataResponse.setData(vo);
        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataResponse.error(e.getMessage());
        } catch (Exception e) {
            dataResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteMapNullValue);
    }



}

