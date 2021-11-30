package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.core.manage.interfaces.IWorkflowManager;
import com.founder.core.manage.local.WorkflowManager;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.core.vo.BaseVO;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.utils.UimUtilsUserId;
import com.ppmg.ei.vo.FpThreemeetingInfoVO;
import com.ppmg.ei.vo.YhStaffListPhVO;
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
@Api(tags = {"重要事项接口，投后事项接口"})
public class FpThreemeetingInfoController extends BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference
    private FpThreemeetingInfoService fpThreemeetingInfoService;
    @Reference
    private ProjMemberService projMemberService;
    @Reference
    private ProjInfoService projInfoService;
    @Reference
    private ProjAppInfoService projAppInfoService;

    @Resource(name = "codeUtils")
    private CodeUtils codeUtils;

    @Reference
    private FixflowRunTaskinstanceService fixflowRunTaskinstanceService;

    @ApiOperation(value = "重要事项列表", notes = "重要事项列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "projId", value = "项目ID", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/threemeetingList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String threemeetingList(@RequestParam("length") int length, @RequestParam("currPage") int currPage, @RequestParam("projId") String projId) {
        try {

            searchCondition.setPageSize(length);

            searchCondition.setCurrPage(currPage);
            searchCondition.addConditionNotEqualTo("STATUS", "9");
            searchCondition.addConditionEqualTo("IS_THREE", "1");
            searchCondition.addConditionEqualTo("CARRIER", projId);
            PageInfo<FpThreemeetingInfoModel> rows = fpThreemeetingInfoService.selectListPage(searchCondition);
            List<FpThreemeetingInfoVO> list = new ArrayList<FpThreemeetingInfoVO>();
            for (FpThreemeetingInfoModel model : rows.getList()) {
                FpThreemeetingInfoVO vo = new FpThreemeetingInfoVO();
                BeanUtils.copyProperties(vo, model);
                if (model.getArchType() != null && !"".equals(model.getArchType())) {
                    vo.setArchType(codeUtils.getCodeNameByValue("227", model.getArchType()));
                } else {
                    vo.setArchType("");
                }
                if (model.getEventType() != null && !"".equals(model.getEventType())) {
                    vo.setEventType(codeUtils.getCodeNameByValue("226", model.getEventType()));
                } else {
                    vo.setEventType("");
                }
                if (model.getImportLevel() != null && !"".equals(model.getImportLevel())) {
                    vo.setImportLevel(codeUtils.getCodeNameByValue("255", model.getImportLevel()));
                } else {
                    vo.setImportLevel("");
                }
                if (model.getStatus() != null && !"".equals(model.getStatus())) {
                    vo.setStatusName(codeUtils.getCodeNameByValue("268", model.getStatus()));
                } else {
                    vo.setStatusName("");
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
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    @ApiIgnore
    @ApiOperation(value = "获取重要事项详细信息", notes = "根据url的id来获取重要事项详细信息")
    @ApiImplicitParam(name = "id", value = "threemeetingID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/threemeetingInfo/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String threemeetingInfo(@PathVariable(value = "id") String id) {
        try {
            FpThreemeetingInfoModel model = fpThreemeetingInfoService.selectById(id);
            BaseVO vo = new BaseVO();
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

    /*-------------------------------------投后事项-------以下---新加---以下-----投后事项-------------------------------------------*/

    @ApiOperation(value = "投基金，投项目--投后事项--分页查询", notes = "投基金，投项目--投后事项--分页查询", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "projId", value = "项目ID", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/selectFpThreemeetingInfo", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectThreemeetingList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("projId") String projId) {
        try {
             ProjInfoModel pf_Serch = projInfoService.selectById(projId);//主表
            //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            searchCondition.addConditionEqualTo("CARRIER", projId);
            PageInfo<FpThreemeetingInfoModel> rows = fpThreemeetingInfoService.fundFpThreemeetingInfoListPage(searchCondition);
            List<FpThreemeetingInfoVO> list = new ArrayList<>();
            for (FpThreemeetingInfoModel model : rows.getList()) {
                FpThreemeetingInfoVO vo = new FpThreemeetingInfoVO();
                BeanUtils.copyProperties(vo, model);
                //項目名稱
                String projName = pf_Serch.getProjName();
                //出資主體
                String inveName = pf_Serch.getInveName();
                //項目名稱
                vo.setInveName(inveName);
                //出資主體
                vo.setProjName(projName);
                //事項類別(1017投基金),
                if (model.getMeetingType() != null && !"".equals(model.getMeetingType())) {
                    //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
                    String projType = pf_Serch.getProjType();
                    if("2".equals(projType)){
                        //基金投后事项，1017
                        vo.setMeetingTypeName(codeUtils.getCodeNameByValue("1017", model.getMeetingType()));
                    }else if("1".equals(projType)){
                        //项目投后事项
                        vo.setMeetingTypeName(codeUtils.getCodeNameByValue("1027", model.getMeetingType()));
                    }
                }
                //事项分类//一般事项，重大事项
                if (model.getEventType() != null && !"".equals(model.getEventType())) {
                    String name = codeUtils.getCodeNameByValue("11016", model.getEventType());
                    vo.setEventTypeName(name);
                }
                //是否需要法务审批
                if (model.getLegalApproval() != null && !"".equals(model.getLegalApproval())) {
                    vo.setLegalApprovalName(codeUtils.getCodeNameByValue("9013", model.getLegalApproval()));
                }
                //流程状态
                if(StringUtils.isNotEmpty(model.getStatus())){
                    vo.setStatusName(codeUtils.getCodeNameByValue("599", model.getStatus()));
                }
                //获取taskId
                if(StringUtils.isNotEmpty(model.getProcessInstId())){
                    List<Map<String,Object>> listMap=  fixflowRunTaskinstanceService.getFixFlowByTaskId(model.getProcessInstId());
                    if (listMap != null && listMap.size() > 0) {
                        String taskId=listMap.get(0).get("TASKINSTANCE_ID").toString();
                        vo.setTaskId(taskId);
                    }else{
                        vo.setTaskId("");
                    }
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


    @ApiOperation(value = "投基金，投项目--投后事项--详情", notes = "投基金，投项目--投后事项--详情", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pkId", value = "项目ID", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/threemeetingInfoDatails", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String threemeetingInfoDetails(@RequestParam("pkId") String id) {
        try {
            FpThreemeetingInfoModel model = fpThreemeetingInfoService.selectById(id);
            String pkId = model.getPkId();
            //查询人员
            List<ProjMemberModel> ls = projMemberService.selectList(pkId);
            if (ls != null && ls.size() > 0) {
                model.setProjMemberModel(ls);
            }
            FpThreemeetingInfoVO vo = new FpThreemeetingInfoVO();
            BeanUtils.copyProperties(vo, model);

            String projId = model.getCarrier();
            ProjInfoModel pf_Serch = projInfoService.selectById(projId);
            //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
            // 項目名稱
            String projName = pf_Serch.getProjName();
            //出資主體
            String inveName = pf_Serch.getInveName();
            //項目名稱
            vo.setInveName(inveName);
            //出資主體
            vo.setProjName(projName);
            vo.setIsFinanceApproval(model.getIsFinanceApproval());
            //事項類別(1017投基金),
            if (model.getMeetingType() != null && !"".equals(model.getMeetingType())) {
                //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
                String projType = pf_Serch.getProjType();
                if("2".equals(projType)){
                    //基金投后事项，1017
                    vo.setMeetingTypeName(codeUtils.getCodeNameByValue("1017", model.getMeetingType()));
                }else if("1".equals(projType)){
                    //项目投后事项1027
                    vo.setMeetingTypeName(codeUtils.getCodeNameByValue("1027", model.getMeetingType()));
                }
            }
            //事项分类,一般事项，重要事项
            if (model.getEventType() != null && !"".equals(model.getEventType())) {
                vo.setEventTypeName(codeUtils.getCodeNameByValue("11016", model.getEventType()));
            }
            //是否需要法务审批(0:否，1：是)
            if (model.getLegalApproval() != null && !"".equals(model.getLegalApproval())) {
                vo.setLegalApprovalName(codeUtils.getCodeNameByValue("9013", model.getLegalApproval()));
            }
            //流程状态
            if(StringUtils.isNotEmpty(model.getStatus())){
                vo.setStatusName(codeUtils.getCodeNameByValue("599", model.getStatus()));
            }
            //获取taskId
            if(StringUtils.isNotEmpty(model.getProcessInstId())){
                List<Map<String,Object>> listMap=  fixflowRunTaskinstanceService.getFixFlowByTaskId(model.getProcessInstId());
                if (listMap != null && listMap.size() > 0) {
                    String taskId=listMap.get(0).get("TASKINSTANCE_ID").toString();
                    vo.setTaskId(taskId);
                }else{
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

    @ApiOperation(value = "投基金，投项目--投后事项--新增", notes = "投基金，投项目--投后事项--新增", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fpThreemeetingInfoModel", value = "项目实体", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/addFpThreemeetingInfo", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addFpThreemeetingInfoController(@RequestBody FpThreemeetingInfoModel fpThreemeetingInfoModel) {
        try {
            if (fpThreemeetingInfoModel != null) {
                //判断投后事项名称是否重复
                FpThreemeetingInfoModel ff = new FpThreemeetingInfoModel();
                ff.setCarrier(fpThreemeetingInfoModel.getCarrier());
                List<FpThreemeetingInfoModel> listName = fpThreemeetingInfoService.selectList(ff);
                for (FpThreemeetingInfoModel tk : listName) {
                    String meetName = tk.getMeetingTitle();
                    if(meetName.equals(fpThreemeetingInfoModel.getMeetingTitle())){
                        baseResponse.setStatus("400");
                        baseResponse.setMsg("事项标题已被占用,请重新输入合适标题");
                        return JSONObject.toJSONString(baseResponse);
                    }
                }
                String id = UUID.randomUUID().toString();
                String projId = fpThreemeetingInfoModel.getCarrier();
                ProjInfoModel pf_Serch = projInfoService.selectById(projId);//主表
                //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
                String projType = pf_Serch.getProjType();

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
                System.out.println("当前登陆人测试----------->"+userId);
                Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();//獲取部門
                System.out.println("当前deptId----------->"+deptId);
                fpThreemeetingInfoModel.setPkId(id);
                fpThreemeetingInfoModel.setCreateBy(userId);
                fpThreemeetingInfoModel.setUpdateBy(userId);
                String tag = fpThreemeetingInfoModel.getTag();
                if (tag != null && "0".equals(tag)) {//提交流程并且保存数据
                    IWorkflowManager wm = new WorkflowManager();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date = sdf.format(new Date());
                    String authid = userId;
                    //主键id
                    String businessKey = id;
                    String processID = null;
                    String subject = null;
                    Map<String, Object> formMap = null;
                    if(projType != null && "2".equals(projType)){//2：投基金,
                        formMap = new HashMap<String, Object>();
                        //流程编号
                        processID = "ManagementOfPostInvestmentMatters";
                        //流程主题
                        //subject = "投管基金-投后事项管理" + "-" + date;
                        //流程名称：项目或基金名称（股权相关的功能）
                        subject = "投后事项" + ":" + pf_Serch.getProjName();
                        logger.info("##--------------ProjName--->>"+pf_Serch.getProjName()+"<--subject--->"+subject);
                        //eventType是否重大事項：1：重大事項，0：一般事項
                        String yesNoGreat = "";
                        if(fpThreemeetingInfoModel.getEventType() != null){
                            yesNoGreat = fpThreemeetingInfoModel.getEventType();
                        }
                        formMap.put("taskTitle", subject);
                        formMap.put("businessKey", businessKey);
                        //是否重大事項
                        formMap.put("yesNoGreat", yesNoGreat);
                        //所在部門deptId
                        formMap.put("deptId", deptId);
                        formMap.put("projId", projId);
                        formMap.put("fundId",pf_Serch.getProjObject());
                        formMap.put("groupId",pf_Serch.getGroupId());
                        //項目投管
                    }else if(projType != null && "1".equals(projType)){
                        //流程编号
                        processID = "ManagementOfPostInvestmentMatters_project";
                        //流程主题
                        //subject = "項目投管-投后事项管理" + "-" + date;
                        //流程名称：项目或基金名称（股权相关的功能）
                        subject = "投后事项" + ":" + pf_Serch.getProjName();
                        logger.info("##--------------ProjName--->>"+pf_Serch.getProjName()+"<--subject--->"+subject);
                        formMap = new HashMap<String, Object>();
                        formMap.put("taskTitle", subject);
                        formMap.put("businessKey", businessKey);
                        //是否重大事項
                        formMap.put("yesNoGreat", "");
                        //所在部門deptId
                        formMap.put("deptId", deptId);
                        //projId
                        formMap.put("projId", projId);
                    }

                    ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", processID, businessKey, authid);
                    //"提交成功！"
                    if (inst != null) {
                        logger.info("##------->流程启动成功<--------##"+inst.getId());
                        //保存数据
                        fpThreemeetingInfoModel.setStatus("1");//審批中
                        fpThreemeetingInfoModel.setProcessInstId(inst.getId());
                        fpThreemeetingInfoService.addFpThreemeetingInfo(fpThreemeetingInfoModel);
                    } else {
                        logger.error("##------->流程启动失败<--------##");
                        baseResponse.error("流程启动失败");
                    }
                } else if (tag != null && "1".equals(tag)) {
                    System.out.println("==============12121-------->");
                    //保存数据
                    fpThreemeetingInfoModel.setStatus("0");//草稿
                    System.out.println("---222222----------11111-------->"+fpThreemeetingInfoModel);
                    fpThreemeetingInfoService.addFpThreemeetingInfo(fpThreemeetingInfoModel);
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);
    }

    @ApiOperation(value = "投基金，投项目--投后事项--修改", notes = "投基金，投项目--投后事项--修改", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fpThreemeetingInfoModel", value = "项目实体", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/updateFpThreemeetingInfo", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateFpThreemeetingInfoController(@RequestBody FpThreemeetingInfoModel fpThreemeetingInfoModel) {
        try {
            if (fpThreemeetingInfoModel != null) {
                //判断投后事项名称是否重复
                FpThreemeetingInfoModel ff = new FpThreemeetingInfoModel();
                ff.setCarrier(fpThreemeetingInfoModel.getCarrier());
                List<FpThreemeetingInfoModel> listName = fpThreemeetingInfoService.selectList(ff);
                for (FpThreemeetingInfoModel tk : listName) {
                    String meetName = tk.getMeetingTitle();
                    String id = tk.getPkId();
                    if(meetName.equals(fpThreemeetingInfoModel.getMeetingTitle())){
                        if(!id.equals(fpThreemeetingInfoModel.getPkId())){
                            baseResponse.setStatus("400");
                            baseResponse.setMsg("事项标题已被占用,请重新输入合适标题");
                            return JSONObject.toJSONString(baseResponse);
                        }
                    }
                }

                String projId = fpThreemeetingInfoModel.getCarrier();
                ProjInfoModel pf_Serch = projInfoService.selectById(projId);//主表
                //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
                String projType = pf_Serch.getProjType();
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
                System.out.println("当前登陆人测试----------->"+userId);
                Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();//獲取部門
                String tag = fpThreemeetingInfoModel.getTag();
                fpThreemeetingInfoModel.setCreateBy(userId);
                fpThreemeetingInfoModel.setUpdateBy(userId);
                if (tag != null && "0".equals(tag)) {
                    IWorkflowManager wm = new WorkflowManager();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date = sdf.format(new Date());
                    String authid = userId;
                    //主键id
                    String businessKey = fpThreemeetingInfoModel.getPkId();
                    String processID = null;
                    String subject = null;
                    //其他參數
                    Map<String, Object> formMap = null;
                    if(projType != null && "2".equals(projType)){//2：投基金,
                        formMap = new HashMap<String, Object>();
                        //流程编号
                        processID = "ManagementOfPostInvestmentMatters";
                        //流程主题
                        //subject = "投管基金-投后事项管理" + "-" + date;
                        //流程名称：项目或基金名称（股权相关的功能）
                        subject = "投后事项" + ":" + pf_Serch.getProjName();
                        logger.info("##--------------ProjName--->>"+pf_Serch.getProjName()+"<--subject--->"+subject);

                        formMap.put("taskTitle", subject);
                        formMap.put("businessKey", businessKey);
                        //eventType是否重大事項：1：重大事項，0：一般事項
                        String yesNoGreat = fpThreemeetingInfoModel.getEventType();
                        formMap.put("yesNoGreat", yesNoGreat);//是否重大事項
                        formMap.put("deptId", deptId);//所在部門deptId
                        formMap.put("projId", projId);//projId
                    }else if(projType != null && "1".equals(projType)){//1：項目投管
                        formMap = new HashMap<String, Object>();
                        //流程编号
                        processID = "ManagementOfPostInvestmentMatters_project";
                        //流程主题
                        //subject = "項目投管-投后事项管理" + "-" + date;
                        //流程名称：项目或基金名称（股权相关的功能）
                        subject = "投后事项" + ":" + pf_Serch.getProjName();
                        logger.info("##--------------ProjName--->>"+pf_Serch.getProjName()+"<--subject--->"+subject);
                        formMap.put("taskTitle", subject);
                        formMap.put("businessKey", businessKey);
                        formMap.put("yesNoGreat", "");//是否重大事項
                        formMap.put("deptId", deptId);//所在部門deptId
                        formMap.put("projId", projId);//projId
                    }

                    //启动流程
                    ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", processID, businessKey, authid);
                    if (inst != null) {
                        logger.info("##------->流程启动成功<--------##"+inst.getId());
                        //更新数据
                        fpThreemeetingInfoModel.setStatus("1");//審批中
                        fpThreemeetingInfoModel.setProcessInstId(inst.getId());
                        fpThreemeetingInfoService.updateFpThreemeetingInfo(fpThreemeetingInfoModel);
                    } else {
                        logger.error("##------->流程启动失败<--------##");
                        baseResponse.error("流程启动失败");
                    }
                } else if (tag != null && "1".equals(tag)) {
                    //更新数据
                    fpThreemeetingInfoModel.setStatus("0");//草稿
                    fpThreemeetingInfoService.updateFpThreemeetingInfo(fpThreemeetingInfoModel);
                }else if (tag != null && "3".equals(tag)) {
                    //更新数据
                    fpThreemeetingInfoService.updateFpThreemeetingInfo(fpThreemeetingInfoModel);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }

    @ApiOperation(value = "投基金，投项目--投后事项--删除", notes = "投基金，投项目--投后事项--删除", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pkId", value = "项目id", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/deleteFpThreemeetingInfo", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteFpThreemeetingInfoController(@RequestParam("pkId") String pkIds) {
        try {
            String[] arr = null;
            if (pkIds != "" && pkIds != null) {
                arr = pkIds.split(",");
                fpThreemeetingInfoService.deleteBatch(arr);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }

    @ApiOperation(value = "查询人事派遣人员", notes = "查询人事派遣人员,投后事项选择事项类别的时候，判断人事派遣类别是否相等，相等则带出相应人员（审批通过）", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projId", value = "项目projId", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userType", value = "userType", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/numberDatails", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list(@RequestParam("projId") String projId,@RequestParam("userType") String userType){
        try {
            searchCondition.setPageSize(100);
            searchCondition.setCurrPage(1);
            searchCondition.addConditionEqualTo("PROJ_ID",projId);
            searchCondition.addConditionEqualTo("USER_TYPE",userType);
            searchCondition.addConditionEqualTo("STATUS","4");
            PageInfo<YhStaffListPhModel> rows = fpThreemeetingInfoService.fundFpThreemeetingInfoListPageNumber(searchCondition);
            List<YhStaffListPhVO> list = new ArrayList<YhStaffListPhVO>();
            for(YhStaffListPhModel model : rows.getList()){
                YhStaffListPhVO vo = new YhStaffListPhVO();
                BeanUtils.copyProperties(vo, model);
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

    /**
     * 获取流程图defId
     * @param processinstanceId
     * @return java.lang.String
     * @author zhaokuiyu
     * @date 2020/2/11 15:22
     * @creed: The best time to plant a tree is ten years ago, followed by now
     */
    @GetMapping(value = "/getDefId", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getDefId(@RequestParam("processinstanceId") String processinstanceId){
        String defId = "";
        try {
            defId = UimUtilsUserId.getDefId(processinstanceId);
        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
        }
        return defId;
    }

    /**
     ********↓↓↓↓↓↓↓↓↓↓*********平台名称：金财合盈*******↓↓↓↓↓↓*******管理公司三会议案审查*******↓↓↓↓↓↓↓↓*********
     */

    @ApiOperation(value = "管理公司三会议案审查新增", notes = "管理公司三会议案审查新增", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fpThreemeetingInfoModel", value = "fpThreemeetingInfoModel", required = true, dataType = "FpThreemeetingInfoModel")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/addFpThreemeetingInfoMc", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addFpThreemeetingInfoMcController(@RequestBody FpThreemeetingInfoModel fpThreemeetingInfoModel) {
        try {
            if (fpThreemeetingInfoModel != null) {
                //判断投后事项名称是否重复
                FpThreemeetingInfoModel ff = new FpThreemeetingInfoModel();
                ff.setCarrier(fpThreemeetingInfoModel.getCarrier());
                List<FpThreemeetingInfoModel> listName = fpThreemeetingInfoService.selectList(ff);
                for (FpThreemeetingInfoModel tk : listName) {
                    String meetName = tk.getMeetingTitle();
                    if(meetName.equals(fpThreemeetingInfoModel.getMeetingTitle())){
                        baseResponse.setStatus("400");
                        baseResponse.setMsg("事项标题已被占用,请重新输入合适标题");
                        return JSONObject.toJSONString(baseResponse);
                    }
                }
                String pkId = UUID.randomUUID().toString();
                String projId = fpThreemeetingInfoModel.getCarrier();
                ProjInfoModel pf_Serch = projInfoService.selectById(projId);
                //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
                String projType = pf_Serch.getProjType();
                String userId = String.valueOf(this.getLoginUser().getUserId());
                //String userId = UimUtilsUserId.getUserId(projType);
                System.out.println("当前登陆人测试----------->"+userId);
                Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();
                System.out.println("当前deptId----------->"+deptId);
                fpThreemeetingInfoModel.setPkId(pkId);
                fpThreemeetingInfoModel.setCreateBy(userId);
                fpThreemeetingInfoModel.setUpdateBy(userId);
                String tag = fpThreemeetingInfoModel.getTag();
                //提交流程并且保存数据
                if (tag != null && "0".equals(tag)) {
                    IWorkflowManager wm = new WorkflowManager();
                    String authid = userId;
                    //主键id
                    String businessKey = pkId;
                    Map<String, Object> formMap = new HashMap<>();
                    //流程编号
                    String processID = "MC_ThreeMeetingsConsideration";
                    //流程主题
                    String subject = "三会议案审查" + ":" + pf_Serch.getProjName();
                    logger.info("##--------------ProjName--->>"+pf_Serch.getProjName()+"<--subject--->"+subject);
                    formMap.put("taskTitle", subject);
                    formMap.put("businessKey", businessKey);
                    //是否重大事項
                    formMap.put("yesNoGreat", "");
                    //所在部門deptId
                    formMap.put("deptId", deptId);
                    //projId
                    formMap.put("projId", projId);
                    //保存数据
                    fpThreemeetingInfoModel.setStatus("0");
                    fpThreemeetingInfoService.addFpThreemeetingInfo(fpThreemeetingInfoModel);
                    //提交流程
                    ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", processID, businessKey, authid);
                    //"提交成功！"
                    if (inst != null) {
                        logger.info("##------->流程启动成功<--------##"+inst.getId());
                        //更新数据
                        FpThreemeetingInfoModel model = new FpThreemeetingInfoModel();
                        model.setPkId(fpThreemeetingInfoModel.getPkId());
                        model.setStatus("1");
                        model.setProcessInstId(inst.getId());
                        fpThreemeetingInfoService.updateFpThreemeetingInfo(model);
                    } else {
                        logger.error("##------->流程启动失败<--------##");
                        baseResponse.error("流程启动失败");
                    }
                } else if (tag != null && "1".equals(tag)) {
                    //保存数据//草稿
                    fpThreemeetingInfoModel.setStatus("0");
                    fpThreemeetingInfoService.addFpThreemeetingInfo(fpThreemeetingInfoModel);
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);
    }

    @ApiOperation(value = "管理公司三会议案审查修改", notes = "管理公司三会议案审查修改", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fpThreemeetingInfoModel", value = "fpThreemeetingInfoModel", required = true, dataType = "FpThreemeetingInfoModel")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/updateFpThreemeetingInfoMc", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateFpThreemeetingInfoMc(@RequestBody FpThreemeetingInfoModel fpThreemeetingInfoModel) {
        try {
            if (fpThreemeetingInfoModel != null) {
                //判断投后事项名称是否重复
                FpThreemeetingInfoModel ff = new FpThreemeetingInfoModel();
                ff.setCarrier(fpThreemeetingInfoModel.getCarrier());
                List<FpThreemeetingInfoModel> listName = fpThreemeetingInfoService.selectList(ff);
                for (FpThreemeetingInfoModel tk : listName) {
                    String meetName = tk.getMeetingTitle();
                    String id = tk.getPkId();
                    if(meetName.equals(fpThreemeetingInfoModel.getMeetingTitle())){
                        if(!id.equals(fpThreemeetingInfoModel.getPkId())){
                            baseResponse.setStatus("400");
                            baseResponse.setMsg("事项标题已被占用,请重新输入合适标题");
                            return JSONObject.toJSONString(baseResponse);
                        }
                    }
                }

                String projId = fpThreemeetingInfoModel.getCarrier();
                ProjInfoModel pf_Serch = projInfoService.selectById(projId);
                //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
                String projType = pf_Serch.getProjType();
                String userId = String.valueOf(this.getLoginUser().getUserId());
                //String userId = UimUtilsUserId.getUserId(projType);
                System.out.println("当前登陆人测试----------->"+userId);
                Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();
                String tag = fpThreemeetingInfoModel.getTag();
                fpThreemeetingInfoModel.setCreateBy(userId);
                fpThreemeetingInfoModel.setUpdateBy(userId);
                //提交流程并且保存数据
                if (tag != null && "0".equals(tag)) {
                    IWorkflowManager wm = new WorkflowManager();
                    String authid = userId;
                    //主键id
                    String businessKey = fpThreemeetingInfoModel.getPkId();
                    //其他參數
                    Map<String, Object> formMap = new HashMap<>();
                    //流程编号
                    String processID = "MC_ThreeMeetingsConsideration";
                    //流程主题
                    String subject = "三会议案审查" + "：" + pf_Serch.getProjName();
                    logger.info("##--------------ProjName--->>"+pf_Serch.getProjName()+"<--subject--->"+subject);
                    formMap.put("taskTitle", subject);
                    formMap.put("businessKey", businessKey);
                    //是否重大事項
                    formMap.put("yesNoGreat", "");
                    formMap.put("deptId", deptId);
                    formMap.put("projId", projId);
                    //更新数据
                    fpThreemeetingInfoModel.setStatus("0");
                    fpThreemeetingInfoService.updateFpThreemeetingInfo(fpThreemeetingInfoModel);
                    //启动流程
                    ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", processID, businessKey, authid);
                    if (inst != null) {
                        logger.info("##------->流程启动成功<--------##"+inst.getId());
                        //更新数据//審批中
                        FpThreemeetingInfoModel model = new FpThreemeetingInfoModel();
                        model.setPkId(fpThreemeetingInfoModel.getPkId());
                        model.setStatus("1");
                        model.setProcessInstId(inst.getId());
                        fpThreemeetingInfoService.updateFpThreemeetingInfo(model);
                    } else {
                        logger.error("##------->流程启动失败<--------##");
                        baseResponse.error("流程启动失败");
                    }
                } else if (tag != null && "1".equals(tag)) {
                    //更新数据//草稿
                    fpThreemeetingInfoModel.setStatus("0");
                    fpThreemeetingInfoService.updateFpThreemeetingInfo(fpThreemeetingInfoModel);
                }else if (tag != null && "3".equals(tag)) {
                    //更新数据
                    fpThreemeetingInfoService.updateFpThreemeetingInfo(fpThreemeetingInfoModel);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }
}

