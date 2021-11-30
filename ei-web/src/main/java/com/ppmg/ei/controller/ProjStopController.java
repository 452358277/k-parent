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
@Api(tags = {"项目，基金，终止，注销，接口"})
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

    @ApiOperation(value = "分页列表", notes = "分页列表", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
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

    @ApiOperation(value = "获取项目终止详细信息", notes = "获取项目终止详细信息", httpMethod = "GET")
    @ApiImplicitParam(name = "stopId", value = "STOP_ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projectStopDetail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String projectStopDetail(@RequestParam(value = "stopId") String stopId) {
        try {
            ProjStopModel model = projStopService.selectById(stopId);
            ProjStopVO vo = new ProjStopVO();
            BeanUtils.copyProperties(model, vo);
            ProjInfoModel pf_Serch = projInfoService.selectById(model.getProjId());
            //項目名稱
            String projName = pf_Serch.getProjName();
            //出資主體
            String inveName = pf_Serch.getInveName();
            vo.setInveName(inveName);
            vo.setProjName(projName);
            //一般事项，重大事项
            if (model.getStopGreat() != null && !"".equals(model.getStopGreat())) {
                String name = codeUtils.getCodeNameByValue("11016", model.getStopGreat());
                vo.setStopGreatName(name);
            }
            //是否需要法务审批
            if (model.getStopLawWorks() != null && !"".equals(model.getStopLawWorks())) {
                vo.setStopLawWorksName(codeUtils.getCodeNameByValue("9013", model.getStopLawWorks()));
            }
            if (model.getStopType() != null && model.getStopType() != "") {
                if ("0".equals(model.getStopType())) {
                    vo.setStopTypeName("终止");
                }
                if ("1".equals(model.getStopType())) {
                    vo.setStopTypeName("注销");
                }
            }
            //流程狀態
            String name1 = codeUtils.getCodeNameByValue("599", model.getStatus());
            vo.setStatusName(name1);
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
        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataResponse.error(e.getMessage());
        } catch (Exception e) {
            dataResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value = "根据projId获取项目终止详细信息", notes = "获取项目终止详细信息", httpMethod = "GET")
    @ApiImplicitParam(name = "projId", value = "PROJ_ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projectStopDetailProjId", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String projectStopDetailP(@RequestParam(value = "projId") String projId) {
        try {
            ProjStopVO vo = new ProjStopVO();
            ProjStopModel model = projStopService.selectProjId(projId);
            if (model == null) {
                dataResponse.setStatus("400");
                dataResponse.setMsg("没有申请过终止流程");
                return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteMapNullValue);
            }
            BeanUtils.copyProperties(model, vo);
            //主表
            ProjInfoModel pf_Serch = projInfoService.selectById(projId);
            //項目名稱
            String projName = pf_Serch.getProjName();
            //出資主體
            String inveName = pf_Serch.getInveName();
            //項目名稱
            vo.setInveName(inveName);
            //出資主體
            vo.setProjName(projName);
            //一般事项，重大事项
            if (model.getStopGreat() != null && !"".equals(model.getStopGreat())) {
                String name = codeUtils.getCodeNameByValue("11016", model.getStopGreat());
                vo.setStopGreatName(name);
            }
            //是否需要法务审批
            if (model.getStopLawWorks() != null && !"".equals(model.getStopLawWorks())) {
                vo.setStopLawWorksName(codeUtils.getCodeNameByValue("9013", model.getStopLawWorks()));
            }
            if (model.getStopType() != null && model.getStopType() != "") {
                if ("0".equals(model.getStopType())) {
                    vo.setStopTypeName("终止");
                }
                if ("1".equals(model.getStopType())) {
                    vo.setStopTypeName("注销");
                }
            }
            //流程狀態
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

    //****************项目终止，注销************************
    @ApiOperation(value = "增加项目终止流程", notes = "投项目-项目终止流程", httpMethod = "POST")
    @RequestMapping(value = "/addProjStopProject", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String projStopProject(@RequestBody ProjStopModel projStopModel) {
        try {
            if (projStopModel != null) {
                String stopId = UUID.randomUUID().toString();
                String projId = projStopModel.getProjId();
                //项目已经终止，不可再次提交
                ProjStopModel pm = projStopService.selectProjId(projId);
                if (pm != null) {
                    baseResponse.setStatus("500");
                    baseResponse.setMsg("项目已经终止，不可再次提交");
                    return JSONObject.toJSONString(baseResponse);
                }
                //查询项目出资，出资完成后不可终止
                List<FpPaymentRequestStopModel> list = projStopService.selectList(projId);
                if (list.size() > 0) {
                    baseResponse.setStatus("400");
                    baseResponse.setMsg("出资完成后不可终止");
                    return JSONObject.toJSONString(baseResponse);
                } else {
                    //主键
                    String id = stopId;
                    //主表
                    ProjInfoModel pf_Serch = projInfoService.selectById(projId);
                    //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
                    String projType = pf_Serch.getProjType();
                    String userId = String.valueOf(this.getLoginUser().getUserId());
                    //String userId = UimUtilsUserId.getUserId(projType);
                    System.out.println("当前登陆人测试----------->" + userId);
                    Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();

                    IWorkflowManager wm = new WorkflowManager();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date = sdf.format(new Date());
                    String authid = userId;
                    //主键id
                    String businessKey = id;
                    //流程编号
                    String processID = null;
                    //流程主题
                    String subject = null;
                    //终止？注销
                    String StopType = null;
                    String name = pf_Serch.getProjName() != null ? pf_Serch.getProjName() : "";
                    if (projType != null && "2".equals(projType)) {
                        //基金终止
                        processID = "Bid_project_stop";
                        StopType = projStopModel.getStopType();
                        if ("0".equals(StopType)) {
                            //终止类型（0：终止，1：注销）
                            subject = "终止" + ":" + name;
                        } else {
                            subject = "注销" + ":" + name;
                        }
                    } else if (projType != null && "1".equals(projType)) {
                        //项目终止
                        processID = "Bid_project_stop_proj";
                        subject = "终止" + "：" + name;
                    }
                    System.out.println("终止，注销-标题------>" + subject + "-" + date);
                    //其他參數
                    Map<String, Object> formMap = new HashMap<String, Object>();
                    formMap.put("taskTitle", subject);
                    formMap.put("businessKey", businessKey);
                    //所在部門deptId;
                    formMap.put("deptId", deptId);
                    //终止类型（0：终止，1：注销）
                    formMap.put("StopType", StopType);
                    formMap.put("projId", projId);
                    ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", processID, businessKey, authid);
                    //"提交成功！"
                    if (inst != null) {
                        logger.info("##------->流程启动成功<--------##");
                        //保存数据
                        projStopModel.setStopId(stopId);
                        //審批中
                        projStopModel.setStatus("1");
                        projStopModel.setCreateBy(userId);
                        projStopModel.setCreateDt(new Date());
                        projStopModel.setUpdateBy(userId);
                        projStopModel.setUpdateDt(new Date());
                        projStopModel.setProcessInstId(inst.getId());
                        projStopService.insert(projStopModel);
                    } else {
                        logger.error("##------->流程启动失败<--------##");
                        baseResponse.error("流程启动失败");
                    }
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);
    }

    @ApiOperation(value = "项目终止流程-更新数据", notes = "投项目-项目终止流程-更新数据", httpMethod = "POST")
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

