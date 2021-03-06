package com.ppmg.ei.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.founder.core.manage.interfaces.IWorkflowManager;
import com.founder.core.manage.local.WorkflowManager;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.model.FpPaymentRequestStopModel;
import com.ppmg.ei.model.ProjAppInfoQuitModel;
import com.ppmg.ei.model.ProjInfoModel;
import com.ppmg.ei.service.FixflowRunTaskinstanceService;
import com.ppmg.ei.service.ProjInfoService;
import com.ppmg.ei.utils.UimUtilsUserId;
import com.ppmg.ei.vo.ProjStopVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
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
import com.ppmg.ei.model.ProjStopModel;
import com.ppmg.ei.service.ProjStopService;

/**
 * @author zhaokuiyu
 * @return
 * @date 2019-10-22 11:46
 * @creed: The best time to plant a tree is ten years ago, followed by now
 */
@RestController
@Scope("prototype")
@Api(tags = {"??????????????????????????????????????????"})
public class ProjStopController extends BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference
    private ProjStopService projStopService;
    @Reference
    private ProjInfoService projInfoService;
    @Resource(name = "codeUtils")
    private CodeUtils codeUtils;
    @Reference
    private FixflowRunTaskinstanceService fixflowRunTaskinstanceService;

    @ApiOperation(value = "????????????", notes = "????????????", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "length", value = "????????????", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "????????????", required = true, dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "????????????"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "?????????????????????"),
    })
    @GetMapping(value = "/projectStopList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String projectStoplist(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage) {
        try {

            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            PageInfo<ProjStopModel> rows = projStopService.selectListPage(searchCondition);
            List<ProjStopVO> list = new ArrayList<ProjStopVO>();
            for (ProjStopModel model : rows.getList()) {
                ProjStopVO vo = new ProjStopVO();
                BeanUtils.copyProperties(model, vo);
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

    @ApiOperation(value = "??????????????????????????????", notes = "??????????????????????????????", httpMethod = "GET")
    @ApiImplicitParam(name = "stopId", value = "STOP_ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "????????????"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "?????????????????????"),
    })
    @GetMapping(value = "/projectStopDetail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String projectStopDetail(@RequestParam(value = "stopId") String stopId) {
        try {
            ProjStopModel model = projStopService.selectById(stopId);
            ProjStopVO vo = new ProjStopVO();
            BeanUtils.copyProperties(model, vo);
            ProjInfoModel pf_Serch = projInfoService.selectById(model.getProjId());
            //????????????
            String projName = pf_Serch.getProjName();
            //????????????
            String inveName = pf_Serch.getInveName();
            vo.setInveName(inveName);
            vo.setProjName(projName);
            //???????????????????????????
            if (model.getStopGreat() != null && !"".equals(model.getStopGreat())) {
                String name = codeUtils.getCodeNameByValue("11016", model.getStopGreat());
                vo.setStopGreatName(name);
            }
            //????????????????????????
            if (model.getStopLawWorks() != null && !"".equals(model.getStopLawWorks())) {
                vo.setStopLawWorksName(codeUtils.getCodeNameByValue("9013", model.getStopLawWorks()));
            }
            if (model.getStopType() != null && model.getStopType() != "") {
                if ("0".equals(model.getStopType())) {
                    vo.setStopTypeName("??????");
                }
                if ("1".equals(model.getStopType())) {
                    vo.setStopTypeName("??????");
                }
            }
            //????????????
            String name1 = codeUtils.getCodeNameByValue("599", model.getStatus());
            vo.setStatusName(name1);
            //??????taskId
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
        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataResponse.error(e.getMessage());
        } catch (Exception e) {
            dataResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value = "??????projId??????????????????????????????", notes = "??????????????????????????????", httpMethod = "GET")
    @ApiImplicitParam(name = "projId", value = "PROJ_ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "????????????"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "?????????????????????"),
    })
    @GetMapping(value = "/projectStopDetailProjId", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String projectStopDetailP(@RequestParam(value = "projId") String projId) {
        try {
            ProjStopVO vo = new ProjStopVO();
            ProjStopModel model = projStopService.selectProjId(projId);
            if (model == null) {
                dataResponse.setStatus("400");
                dataResponse.setMsg("???????????????????????????");
                return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteMapNullValue);
            }
            BeanUtils.copyProperties(model, vo);
            //??????
            ProjInfoModel pf_Serch = projInfoService.selectById(projId);
            //????????????
            String projName = pf_Serch.getProjName();
            //????????????
            String inveName = pf_Serch.getInveName();
            //????????????
            vo.setInveName(inveName);
            //????????????
            vo.setProjName(projName);
            //???????????????????????????
            if (model.getStopGreat() != null && !"".equals(model.getStopGreat())) {
                String name = codeUtils.getCodeNameByValue("11016", model.getStopGreat());
                vo.setStopGreatName(name);
            }
            //????????????????????????
            if (model.getStopLawWorks() != null && !"".equals(model.getStopLawWorks())) {
                vo.setStopLawWorksName(codeUtils.getCodeNameByValue("9013", model.getStopLawWorks()));
            }
            if (model.getStopType() != null && model.getStopType() != "") {
                if ("0".equals(model.getStopType())) {
                    vo.setStopTypeName("??????");
                }
                if ("1".equals(model.getStopType())) {
                    vo.setStopTypeName("??????");
                }
            }
            //????????????
            String name1 = codeUtils.getCodeNameByValue("599", model.getStatus());
            vo.setStatusName(name1);
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

    //****************?????????????????????************************
    @ApiOperation(value = "????????????????????????", notes = "?????????-??????????????????", httpMethod = "POST")
    @RequestMapping(value = "/addProjStopProject", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String projStopProject(@RequestBody ProjStopModel projStopModel) {
        try {
            if (projStopModel != null) {
                String stopId = UUID.randomUUID().toString();
                String projId = projStopModel.getProjId();
                //???????????????????????????????????????
                ProjStopModel pm = projStopService.selectProjId(projId);
                if (pm != null) {
                    baseResponse.setStatus("500");
                    baseResponse.setMsg("???????????????????????????????????????");
                    return JSONObject.toJSONString(baseResponse);
                }
                //????????????????????????????????????????????????
                List<FpPaymentRequestStopModel> list = projStopService.selectList(projId);
                if (list.size() > 0) {
                    baseResponse.setStatus("400");
                    baseResponse.setMsg("???????????????????????????");
                    return JSONObject.toJSONString(baseResponse);
                } else {
                    //??????
                    String id = stopId;
                    //??????
                    ProjInfoModel pf_Serch = projInfoService.selectById(projId);
                    //???????????????1??????????????????2???????????????3:???????????????,4spv,5.??????????????????????????????6.????????????????????????
                    String projType = pf_Serch.getProjType();
                    String userId = String.valueOf(this.getLoginUser().getUserId());
                    //String userId = UimUtilsUserId.getUserId(projType);
                    System.out.println("?????????????????????----------->" + userId);
                    Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();

                    IWorkflowManager wm = new WorkflowManager();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date = sdf.format(new Date());
                    String authid = userId;
                    //??????id
                    String businessKey = id;
                    //????????????
                    String processID = null;
                    //????????????
                    String subject = null;
                    //???????????????
                    String StopType = null;
                    String name = pf_Serch.getProjName() != null ? pf_Serch.getProjName() : "";
                    if (projType != null && "2".equals(projType)) {
                        //????????????
                        processID = "Bid_project_stop";
                        StopType = projStopModel.getStopType();
                        if ("0".equals(StopType)) {
                            //???????????????0????????????1????????????
                            subject = "??????" + ":" + name;
                        } else {
                            subject = "??????" + ":" + name;
                        }
                    } else if (projType != null && "1".equals(projType)) {
                        //????????????
                        processID = "Bid_project_stop_proj";
                        subject = "??????" + "???" + name;
                    }
                    System.out.println("???????????????-??????------>" + subject + "-" + date);
                    //????????????
                    Map<String, Object> formMap = new HashMap<String, Object>();
                    formMap.put("taskTitle", subject);
                    formMap.put("businessKey", businessKey);
                    //????????????deptId;
                    formMap.put("deptId", deptId);
                    //???????????????0????????????1????????????
                    formMap.put("StopType", StopType);
                    formMap.put("projId", projId);
                    ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", processID, businessKey, authid);
                    //"???????????????"
                    if (inst != null) {
                        logger.info("##------->??????????????????<--------##");
                        //????????????
                        projStopModel.setStopId(stopId);
                        //?????????
                        projStopModel.setStatus("1");
                        projStopModel.setCreateBy(userId);
                        projStopModel.setCreateDt(new Date());
                        projStopModel.setUpdateBy(userId);
                        projStopModel.setUpdateDt(new Date());
                        projStopModel.setProcessInstId(inst.getId());
                        projStopService.insert(projStopModel);
                    } else {
                        logger.error("##------->??????????????????<--------##");
                        baseResponse.error("??????????????????");
                    }
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);
    }

    @ApiOperation(value = "??????????????????-????????????", notes = "?????????-??????????????????-????????????", httpMethod = "POST")
    @RequestMapping(value = "/updateProjStopProject", produces = "application/json;charset=UTF-8")
    public String updateProjStopProject(@RequestBody ProjStopModel projStopModel) {
        try {
            if (projStopModel != null) {
                projStopService.update(projStopModel);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);
    }
}

