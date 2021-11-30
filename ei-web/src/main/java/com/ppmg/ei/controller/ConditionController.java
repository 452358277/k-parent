package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.core.manage.interfaces.IWorkflowManager;
import com.founder.core.manage.local.WorkflowManager;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.model.CommTCodeModel;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.utils.UimUtilsUserId;
import com.ppmg.ei.vo.*;
import io.swagger.annotations.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Scope("prototype")
@Api(tags = {"公开征集，征集信息接口"})
public class ConditionController extends BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Reference
    private ConditionService conditionService;
    @Resource(name="codeUtils")
    private CodeUtils codeUtils;
    @Reference
    private FundInfoService fundInfoService;
    @Reference
    private AdminService adminService;
    @Reference
    private ProjInfoService projInfoService;

    @Reference
    private TzTCooConditionInfoService tzTCooConditionInfoService;


    @ApiOperation(value = "公开征集信息分页查询列表", notes = "公开征集信息分页查询列表",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "fundName", value = "母基金名称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态", required = false, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/selectCondition", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectConditionController(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage
            ,@RequestParam("fundName") String fundName,@RequestParam("status") String status) {
        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);

            if (StringUtils.isNotEmpty(fundName)) {
                searchCondition.addConditionLike("FUND_NAME", "%" + fundName + "%");
            }
            if (StringUtils.isNotEmpty(status)) {
                //searchCondition.addConditionEqualTo("STATUS",status);
                String[] arr = status.split(",");
                searchCondition.addConditionIn("STATUS", Arrays.asList(arr));
            }
            PageInfo<ConditionModel> rows = conditionService.selectListPage(searchCondition);
            List<ConditionVO> list = new ArrayList<ConditionVO>();
            for (ConditionModel model : rows.getList()) {
                String status_ = model.getStatus();
                if(StringUtils.isNotEmpty(status_)){
                    String name = codeUtils.getCodeNameByValue("599",status_);
                    model.setStatusName(name);
                }
                ConditionVO vo = new ConditionVO();
                BeanUtils.copyProperties(vo, model);
                //查詢母基金
                FundInfoModel fm = fundInfoService.selectById(model.getFundId());
                if(fm!=null){
                    vo.setFundName(fm.getFundName());//母基金名稱
                    vo.setFundSize(fm.getCurrentAmount());//母基金规模(万元
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

    @ApiOperation(value = "公开征集信息列表详情 ", notes = "公开征集信息列表详情",httpMethod = "GET")
    @ApiImplicitParam(name = "conditionId", value = "合作条件ID", required = true, dataType = "String")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/selectConditionDetail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectConditionDetailController(@RequestParam(value = "conditionId") String conditionId) {
        try {
            ConditionModel model = conditionService.selectById(conditionId);
            String status_ = model.getStatus();
            if(StringUtils.isNotEmpty(status_)){
                String name = codeUtils.getCodeNameByValue("599",status_);
                model.setStatusName(name);
            }
            ConditionVO vo = new ConditionVO();
            BeanUtils.copyProperties(vo, model);
            //查詢母基金
            FundInfoModel fm = fundInfoService.selectById(model.getFundId());
            if(fm!=null){
                vo.setFundName(fm.getFundName());//母基金名稱
                vo.setFundSize(fm.getCurrentAmount());//母基金规模(万元
            }
            dataResponse.setData(vo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResponse.error();
        }
        return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value = "公开征集信息增加", notes = "公开征集信息增加",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "conditionModel", value = "实体类", required = true, dataType = "ConditionModel"),
            @ApiImplicitParam(name = "tag", value = "0:提交并且启动流程，1：提交数据", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/addCondition", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addConditionController(@RequestBody ConditionModel conditionModel){
        try {
            if(conditionModel != null){
                String userId = String.valueOf(this.getLoginUser().getUserId());
                //String userId = "110";
                logger.info("##------->增加公开征集信息列表----userId-->"+userId);
                //获取部门
                Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();
                logger.info("##------->增加公开征集信息列表----deptId-->"+deptId);
                String id = UUID.randomUUID().toString();
                conditionModel.setConditionId(id);//主键
                conditionModel.setCreateBy(userId);
                conditionModel.setUpdateBy(userId);
                if(StringUtils.isEmpty(conditionModel.getStatus())){
                    conditionModel.setStatus("0");
                }
                String tag = conditionModel.getTag();
                if (tag != null && "0".equals(tag)) {//提交流程并且保存数据
                    logger.info("##------->增加公开征集信息列表----tag-->"+tag);
                    IWorkflowManager wm = new WorkflowManager();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date = sdf.format(new Date());
                    String authid = userId;
                    //主键id
                    String businessKey = id;
                    //流程编号
                    String processID = "ei_publish_conditions";//基金征集-发布征集公告.bpmn
                    //流程主题
                    String subject = "基金征集-发布征集公告" + "-" + date;
                    //其他參數
                    Map<String, Object> formMap = new HashMap<String, Object>();
                    formMap.put("taskTitle", subject);
                    formMap.put("businessKey", businessKey);
                    formMap.put("Id", businessKey);
                    formMap.put("deptId", deptId);
                    //启动流程
                    //HandleCommand_StartAndSubmit：要在对应的流程中设置按钮的名字
                    //Map<String, Object> formMap, String commandId, String processDefinitionKey, String businessKey, String authId
                    logger.info("##-----------启动流程---start-->" + formMap + "<-->" + processID + "<-->" + businessKey + "<-->" + authid);
                    ProcessInstance inst = wm.startAndSubmit(formMap,"HandleCommand_StartAndSubmit",processID,businessKey,authid);
                    //"提交成功！"
                    if (inst != null) {
                        logger.info("##------->流程启动成功<--------##");
                        //保存数据
                        conditionModel.setStatus("1");//审批中
                        conditionModel.setProcessInstId(inst.getId());
                        conditionService.insert(conditionModel);
                    } else {
                        logger.error("##------->流程启动失败<--------##");
                        baseResponse.error("流程启动失败");
                    }
                }else if(tag != null && "1".equals(tag)){
                    //保存数据
                    conditionModel.setStatus("0");//草稿
                    conditionService.insert(conditionModel);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);
    }

    @ApiOperation(value = "公开征集信息更新", notes = "公开征集信息更新",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "conditionModel", value = "实体类", required = true, dataType = "ConditionModel")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/updateCondition", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateConditionController(@RequestBody ConditionModel conditionModel){
        try {
            if(conditionModel != null){
                String userId = String.valueOf(this.getLoginUser().getUserId());
                //String userId = UimUtilsUserId.getUserId();
                logger.info("##------->更新公开征集信息列表---------userId"+userId);
                //获取部门
                Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();
                logger.info("##------->更新公开征集信息列表---------deptId"+deptId);
                String id = conditionModel.getConditionId();
                conditionModel.setConditionId(id);//主键
                conditionModel.setCreateBy(userId);
                conditionModel.setUpdateBy(userId);
                if(StringUtils.isEmpty(conditionModel.getStatus())){
                    conditionModel.setStatus("0");
                }
                String tag = conditionModel.getTag();
                if (tag != null && "0".equals(tag)) {//提交流程并且保存数据
                    logger.info("##------->更新公开征集信息列表---------tag"+tag);
                    IWorkflowManager wm = new WorkflowManager();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date = sdf.format(new Date());
                    String authid = userId;
                    //主键id
                    String businessKey = id;
                    //流程编号
                    String processID = "ei_publish_conditions";//基金征集-发布征集公告.bpmn
                    //流程主题
                    String subject = "基金征集-发布征集公告" + "-" + date;
                    //其他參數
                    Map<String, Object> formMap = new HashMap<String, Object>();
                    formMap.put("taskTitle", subject);
                    formMap.put("businessKey", businessKey);
                    formMap.put("Id", businessKey);
                    formMap.put("deptId", deptId);
                    //启动流程
                    //HandleCommand_StartAndSubmit：要在对应的流程中设置按钮的名字
                    //Map<String, Object> formMap, String commandId, String processDefinitionKey, String businessKey, String authId
                    logger.info("##------->更新公开征集信息列表---------启动流程--start");
                    ProcessInstance inst = wm.startAndSubmit(formMap,"HandleCommand_StartAndSubmit",processID,businessKey,authid);
                    //"提交成功！"
                    if (inst != null) {
                        logger.info("##------->流程启动成功<--------##");
                        //保存数据
                        conditionModel.setStatus("1");//审批中
                        conditionModel.setUpdateDt(new Date());
                        conditionModel.setProcessInstId(inst.getId());
                        conditionService.update(conditionModel);
                    } else {
                        logger.error("##------->流程启动失败<--------##");
                        baseResponse.error("流程启动失败");
                    }
                }else if(tag != null && "1".equals(tag)){
                    //保存数据
                    conditionModel.setStatus("0");//草稿
                    conditionModel.setUpdateDt(new Date());
                    conditionService.update(conditionModel);
                }else if(tag != null && "3".equals(tag)){
                    //保存数据
                    conditionModel.setUpdateDt(new Date());
                    conditionService.update(conditionModel);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }

    @ApiOperation(value = "公开征集信息删除", notes = "公开征集信息删除",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "conditionId", value = "合作条件ID", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/deleteCondition", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteConditionController(@RequestParam("conditionId") String conditionId) {
        try {
            String[] arr = null;
            if (conditionId != "" && conditionId != null) {
                arr = conditionId.split(",");
                conditionService.deleteBatch(arr);//删除公开征集
                for (String id : arr) {
                    ConditionInfoModel cm = conditionService.selectOneConditionInfo(id);
                    if(cm != null){
                        String infoId = cm.getInfoId();
                        conditionService.deleteConditionInfos(infoId);//删除征集信息
                    }
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }

    @ApiOperation(value = "公开征集查询母基金接口（母基金+代管基金）", notes = "公开征集查询母基金接口（母基金+代管基金）",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "groupId", value = "平台代码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "oldSubPlatProp", value = "2:查询母基金；3：查询代管基金；21：查询母基金+代管基金合集", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fundInfoFofListAll", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fundInfoFofList(@RequestParam("length") int length, @RequestParam("currPage") int currPage,
                                  @RequestParam("groupId") String groupId,String fundName, String fundStruct,
                                  String platProp, String fundSts,String fundSrc, String fundGenre,
                                  String subPlatProp, String oldSubPlatProp) {
        try {
            searchCondition.setPageSize(length);
            searchCondition.setCurrPage(currPage);
            if (fundName != null && !"".equals(fundName)) {
                fundName = "'%" + fundName + "%'";
                searchCondition.addParam("keyWord", fundName);
            }
            if (StringUtils.isNotEmpty(oldSubPlatProp)) {
                //searchCondition.addConditionEqualTo("OLD_SUB_PLAT_PROP", oldSubPlatProp);
                searchCondition.addParam("oldSubPlatProp", oldSubPlatProp);
            }
            PageInfo<FundInfoModel> rows = fundInfoService.selectFofListPageAll(searchCondition);
            List<FundInfoVO> list = new ArrayList<FundInfoVO>();
            for (FundInfoModel model : rows.getList()) {
                FundInfoVO vo = new FundInfoVO();
                BeanUtils.copyProperties(vo, model);
                //查询已投项目数
                Integer projNum = fundInfoService.selectByProjNum(model.getFundId());
                if (projNum == null) {
                    vo.setProjNum("0");
                } else {
                    vo.setProjNum(String.valueOf(projNum));
                }
                //查询出资成功的数据
                Double paySumAmount = projInfoService.selectPassPayAmount(model.getFundId());
                if (paySumAmount != null) {
                    vo.setPaySumAmount(paySumAmount);
                } else {
                    vo.setPaySumAmount(0.0);
                }
                if (StringUtils.isNotEmpty(model.getFundStruct())) {
                    String codeName = codeUtils.getCodeNameByValue("210", model.getFundStruct());
                    vo.setFundStructName(StringUtils.isEmpty(codeName) ? "" : codeName);
                }
                if (StringUtils.isNotEmpty(vo.getFundGenre())) {
                    vo.setFundGenreName(codeUtils.getCodeNameByValue("1011", model.getFundGenre()));
                }
                list.add(vo);
            }
            dataTablesResponse.setData(list);
            dataTablesResponse.setTotalCount(Long.valueOf(rows.getTotal()).intValue(), rows.getPageNum(), rows.getPageSize());
        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    //##################----------------征集信息-------------------########################
    @ApiOperation(value = "征集信息信息分页查询列表", notes = "征集信息信息分页查询列表",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "fundName", value = "母基金名称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态", required = false, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/selectConditionInfoList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectConditionInfoListController(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage
            ,@RequestParam("fundName") String fundName,@RequestParam("status") String status) {
        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            if (StringUtils.isNotEmpty(fundName)) {
                searchCondition.addConditionLike("FUND_NAME", "%" + fundName + "%");
            }
            if (StringUtils.isNotEmpty(status)) {
                //searchCondition.addConditionEqualTo("STATUS",status);
                String[] arr = status.split(",");
                searchCondition.addConditionIn("STATUS", Arrays.asList(arr));
            }
            PageInfo<ConditionInfoModel> rows = conditionService.selectListPageInfo(searchCondition);
            List<ConditionInfoItemVo> list = new ArrayList<ConditionInfoItemVo>();
            for (ConditionInfoModel model : rows.getList()) {
                //状态
                String status_ = model.getStatus();
                if(StringUtils.isNotEmpty(status_)){
                   String name = codeUtils.getCodeNameByValue("599",status_);
                   model.setStatusName(name);
                }
                ConditionInfoItemVo vo = new ConditionInfoItemVo();
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
    @ApiOperation(value = "征集信息详情 ", notes = "征集信息详情",httpMethod = "GET")
    @ApiImplicitParam(name = "conditionId", value = "合作条件ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/selectConditionInfoDetail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectConditionInfoDetailController(@RequestParam(value = "conditionId") String conditionId) {
        try {
            ConditionInfoModel model = new ConditionInfoModel();
            logger.info("1--------ConditionId-------------->>" + conditionId);
            ConditionModel models = conditionService.selectConditionInfoDetails(conditionId);
            if (models != null) {
                BeanUtils.copyProperties(model, models.getConditionInfoModel());
            } else {//根据infoId查询
                ConditionInfoModel modelT = conditionService.selectOneConditionInfoT(conditionId);
                if (modelT != null) {
                    logger.info("2--------ConditionId-------------->>" + modelT.getConditionId());
                    models = conditionService.selectConditionInfoDetails(modelT.getConditionId());
                    BeanUtils.copyProperties(model, models.getConditionInfoModel());
                }
            }
            if (models != null) {
                List<ConditionItemModel> it_list = models.getConditionItemModel();
                if (it_list != null && it_list.size() > 0) {
                    List<ConditionItemModel> new_it = new ArrayList<>();
                    for (ConditionItemModel itemId : it_list) {
                        ConditionItemModel model_it = conditionService.selectConditionItemDetailService(itemId.getItemId());
                        if (model_it != null) {
                            String adminId = model_it.getFundAdminId();
                            AdminModel modelList = adminService.selectById(adminId);
                            if (modelList != null) {
                                model_it.setFundAdminName(modelList.getAdminName()); //基金管理人名称
                            }
                            new_it.add(model_it);
                        }
                    }
                    model.setConditionItemModel(new_it);
                }
            }
            ConditionInfoVO vo = new ConditionInfoVO();
            BeanUtils.copyProperties(vo, model);
            //状态
            String status_ = model.getStatus();
            if(StringUtils.isNotEmpty(status_)){
                String name = codeUtils.getCodeNameByValue("599",status_);
                vo.setStatusName(name);
            }
            dataResponse.setData(vo);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResponse.error();
        }
        return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value = "征集信息增加 ", notes = "征集信息增加",httpMethod = "POST")
    @ApiImplicitParam(name = "conditionModel", value = "公开征集实体类", required = true, dataType = "ConditionModel", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/addConditionInfoItemAll", produces = "application/json;charset=UTF-8")
    @ResponseBody
   public String addConditionInfoController(@RequestBody ConditionInfoModel conditionInfoModel) {
        try {
            if(conditionInfoModel != null){
                String conditionId = conditionInfoModel.getConditionId();
                ConditionInfoModel cf = conditionService.selectOneConditionInfo(conditionId);
                //long userId = this.getLoginUser().getUserId();//获取登陸人id
                //System.out.println("当前登陆人测试----------->"+userId);
                //发起人APP_USER的id,应该是取当前登陆人
                String userId = String.valueOf(this.getLoginUser().getUserId());
                //String userId = UimUtilsUserId.getUserId();
                //获取部门
                Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();
                if(cf == null){
                    String tag = conditionInfoModel.getTag();
                    String infoIds = UUID.randomUUID().toString();
                    if (tag != null && "0".equals(tag)) {//提交流程并且保存数据
                        IWorkflowManager wm = new WorkflowManager();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String date = sdf.format(new Date());
                        String authid = userId;
                        //主键id
                        String businessKey = infoIds;
                        //流程编号
                        String processID = "ei_confirm_condition_result";//基金征集-确认征集结果.bpmn
                        //流程主题
                        String subject = "基金征集-确认征集结果" + "-" + date;
                        //其他參數
                        Map<String, Object> formMap = new HashMap<String, Object>();
                        formMap.put("taskTitle", subject);
                        formMap.put("businessKey", businessKey);
                        formMap.put("Id", businessKey);
                        formMap.put("infoId", businessKey);//infoId
                        formMap.put("deptId", deptId);//deptId
                        //启动流程
                        //HandleCommand_StartAndSubmit：要在对应的流程中设置按钮的名字
                        //Map<String, Object> formMap, String commandId, String processDefinitionKey, String businessKey, String authId
                        ProcessInstance inst = wm.startAndSubmit(formMap,"HandleCommand_StartAndSubmit",processID,businessKey,authid);
                        //"提交成功！"
                        if (inst != null) {
                            logger.info("##------->流程启动成功<--------##");
                            //保存数据
                            conditionInfoModel.setInfoId(infoIds);
                            conditionInfoModel.setUserId(userId);
                            conditionInfoModel.setStatus("1");
                            conditionInfoModel.setProcessInstId(inst.getId());
                            conditionService.addConditionInfos(conditionInfoModel);
                        } else {
                            logger.error("##------->流程启动失败<--------##");
                            baseResponse.error("流程启动失败");
                        }
                    }else if(tag != null && "1".equals(tag)){
                        //保存数据
                        conditionInfoModel.setInfoId(infoIds);
                        conditionInfoModel.setUserId(userId);
                        conditionInfoModel.setStatus("0");
                        conditionService.addConditionInfos(conditionInfoModel);
                    }
                }else {
                    baseResponse.setStatus("500");//重复数据
                    baseResponse.setMsg("此公开征集已填写过征集信息，请重新选择。");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }
    @ApiOperation(value = "征集信息更新 ", notes = "征集信息更新页面",httpMethod = "POST")
    @ApiImplicitParam(name = "conditionModel", value = "公开征集实体类", required = true, dataType = "ConditionModel", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/updateConditionInfoItemAll", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateConditionInfoController(@RequestBody ConditionInfoModel conditionInfoModel) {
        try {
            if(conditionInfoModel != null){
                String conditionId = conditionInfoModel.getConditionId();
                String infoId = conditionInfoModel.getInfoId();
                TzTCooConditionInfoModel info = tzTCooConditionInfoService.selectById(infoId);
                ConditionInfoModel cf = null;
                if(info != null){
                    if(!conditionId.equals(info.getConditionId())){
                        cf = conditionService.selectOneConditionInfo(conditionId);
                    }
                }
                String userId = String.valueOf(this.getLoginUser().getUserId());
                //String userId = UimUtilsUserId.getUserId();
                System.out.println("当前登陆人测试----------->"+userId);
                //获取部门
                Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();
                if(cf == null){
                    String tag = conditionInfoModel.getTag();
                    String infoIds = conditionInfoModel.getInfoId();
                    if (tag != null && "0".equals(tag)) {
                        //提交流程并且保存数据
                        IWorkflowManager wm = new WorkflowManager();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String date = sdf.format(new Date());
                        String authid = userId;
                        //主键id
                        String businessKey = infoIds;
                        //流程编号
                        String processID = "ei_confirm_condition_result";
                        //流程主题
                        String subject = "基金征集-确认征集结果" + "-" + date;
                        //其他參數
                        Map<String, Object> formMap = new HashMap<String, Object>();
                        formMap.put("taskTitle", subject);
                        formMap.put("businessKey", businessKey);
                        formMap.put("Id", businessKey);
                        formMap.put("infoId", businessKey);
                        formMap.put("deptId", deptId);
                        //启动流程
                        //HandleCommand_StartAndSubmit：要在对应的流程中设置按钮的名字
                        //Map<String, Object> formMap, String commandId, String processDefinitionKey, String businessKey, String authId
                        ProcessInstance inst = wm.startAndSubmit(formMap,"HandleCommand_StartAndSubmit",processID,businessKey,authid);
                        //"提交成功！"
                        if (inst != null) {
                            logger.info("##------->流程启动成功<--------##");
                            //保存数据
                            conditionInfoModel.setUserId(userId);
                            conditionInfoModel.setStatus("1");
                            conditionInfoModel.setUpdateDt(new Date());
                            conditionInfoModel.setProcessInstId(inst.getId());
                            conditionService.updateConditionInfos(conditionInfoModel);
                        } else {
                            logger.error("##------->流程启动失败<--------##");
                            baseResponse.error("流程启动失败");
                        }
                    }else if(tag != null && "1".equals(tag)){
                        //保存数据
                        conditionInfoModel.setUserId(userId);
                        conditionInfoModel.setStatus("0");
                        conditionInfoModel.setUpdateDt(new Date());
                        conditionService.updateConditionInfos(conditionInfoModel);
                    }else if(tag != null && "3".equals(tag)){
                        //保存数据
                        conditionInfoModel.setUserId(userId);
                        conditionInfoModel.setUpdateDt(new Date());
                        conditionService.updateConditionInfos(conditionInfoModel);
                    }

                }else {
                    //重复数据
                    baseResponse.setStatus("500");
                    baseResponse.setMsg("此公开征集已填写过征集信息，请重新选择。");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }
    @ApiOperation(value = "征集信息判断是否可编辑 ", notes = "征集信息判断是否可编辑",httpMethod = "POST")
    @RequestMapping(value = "/onlyUpdateConditionInfo", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String onlyUpdateConditionInfoController(@RequestParam(value = "conditionId") String conditionId) {
        try {
            if(conditionId != null){
                ConditionInfoModel cf = conditionService.selectOneConditionInfo(conditionId);
                if (cf != null && "0".equals(cf.getStatus())) {
                    baseResponse.setStatus("200");//可以编辑
                    baseResponse.setMsg("可以编辑！！！");
                }else{
                    baseResponse.setStatus("500");//
                    baseResponse.setMsg("只能编辑草稿状态的数据！！！");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }
    @ApiOperation(value = "征集信息删除 ", notes = "征集信息删除",httpMethod = "POST")
    @ApiImplicitParam(name = "infoId", value = "infoId", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/deleteConditionInfoItemAll", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteConditionInfoController(@RequestParam(value = "infoId") String infoId) {
        try {
            if(infoId != null && infoId != ""){
                String[] arr = infoId.split(",");
                for (String st : arr) {
                    conditionService.deleteConditionInfos(st);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }
    //#######---------------基金管理人---------#########
    @ApiOperation(value = "基金管理人分页查询列表", notes = "基金管理人分页查询列表",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "infoId", value = "征集信息id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "基金管理人名称", required = false, dataType = "String")
    })
    @GetMapping(value = "/selectConditionItem", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectConditionItem(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage
            ,@RequestParam("infoId") String infoId,@RequestParam("name") String name) {
        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            searchCondition.addConditionEqualTo("INFO_ID",infoId);
            if (StringUtils.isNotEmpty(name)) {
                searchCondition.addConditionLike("FUND_ADMIN_NAME", "%" + name + "%");
            }
            PageInfo<ConditionItemModel> rows = conditionService.selectListPageItem(searchCondition);
            List<ConditionItemVO> list = new ArrayList<ConditionItemVO>();
            for (ConditionItemModel model : rows.getList()) {
                String status_ = model.getIsFit();
                if(StringUtils.isNotEmpty(status_)){
                    String name1 = codeUtils.getCodeNameByValue("102",status_);
                    model.setIsFitName(name1);
                }
                ConditionItemVO vo = new ConditionItemVO();
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
    @ApiOperation(value = "基金管理详情 ", notes = "基金管理人详情",httpMethod = "GET")
    @ApiImplicitParam(name = "itemId", value = "itemId", required = true, dataType = "String")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/selectConditionItemDetail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectConditionItemDetailController(@RequestParam(value = "itemId") String itemId) {
        try {
            ConditionItemModel model = conditionService.selectConditionItemDetailService(itemId);
            String status_ = model.getIsFit();
            if(StringUtils.isNotEmpty(status_)){
                String name1 = codeUtils.getCodeNameByValue("102",status_);
                model.setIsFitName(name1);
            }
            ConditionItemVO vo = new ConditionItemVO();
            BeanUtils.copyProperties(vo, model);
            dataResponse.setData(vo);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResponse.error();
        }
        return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }
    @ApiOperation(value = "基金管理人增加 ", notes = "基金管理人增加",httpMethod = "POST")
    @ApiImplicitParam(name = "conditionItemModel", value = "基金管理人实体类", required = true, dataType = "ConditionItemModel", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/addConditionItem", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addConditionItemController(@RequestBody ConditionItemModel conditionItemModel) {
        try {
            if(conditionItemModel != null){
                if(StringUtils.isEmpty(conditionItemModel.getCreateBy())){
                    conditionItemModel.setCreateBy("创建人");
                }
                if(StringUtils.isEmpty(conditionItemModel.getUpdateBy())){
                    conditionItemModel.setUpdateBy("更新人");
                }
                String itemId = conditionService.insertConditionItems(conditionItemModel);
                baseResponse.setMsg(itemId);//基金管理人id
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);
    }

    @ApiOperation(value = "基金管理人修改 ", notes = "基金管理人修改",httpMethod = "POST")
    @ApiImplicitParam(name = "conditionItemModel", value = "基金管理人实体类", required = true, dataType = "ConditionItemModel", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/updateConditionItem", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateConditionItem(@RequestBody ConditionItemModel conditionItemModel) {
        try {
            if(conditionItemModel != null){
                conditionService.updateConditionItems(conditionItemModel);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);
    }

    @ApiOperation(value = "基金管理人删除 ", notes = "基金管理人删除",httpMethod = "POST")
    @ApiImplicitParam(name = "itemId", value = "itemId", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/deleteConditionItem", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteConditionItem(@RequestParam("itemId") String itemId) {
        try {
            String[] arr = null;
            if (itemId != "" && itemId != null) {
                arr = itemId.split(",");
                conditionService.deleteConditionItems(arr);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }

    @ApiOperation(value = "征集材料详情 ", notes = "征集材料详情",httpMethod = "GET")
    @ApiImplicitParam(name = "fileId", value = "fileId", required = true, dataType = "String")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/selectConditionFileDetail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectConditionFileDetailController(@RequestParam(value = "fileId") String fileId) {
        try {
            ConditionFileModel model = conditionService.selectConditionFileDetails(fileId);
            ConditionFileVO vo = new ConditionFileVO();
            BeanUtils.copyProperties(vo, model);
            dataResponse.setData(vo);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResponse.error();
        }
        return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value = "查询基金管理人接口", notes = "查询基金管理人接口",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "name", value = "ADMIN_NAME", required = true, dataType = "String")
    })
    @GetMapping(value = "/selectAdminController", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectAdminController(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage
            ,@RequestParam("name") String name) {
        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);

            if (StringUtils.isNotEmpty(name)) {
                searchCondition.addConditionLike("ADMIN_NAME", "%" + name + "%");
            }
            PageInfo<AdminModel> rows = conditionService.selectAdmins(searchCondition);
            List<AdminVO> list = new ArrayList<AdminVO>();
            for (AdminModel model : rows.getList()) {
                String status_ = model.getIsCoo();
                if(StringUtils.isNotEmpty(status_)){//是否合作过
                    String name1 = codeUtils.getCodeNameByValue("102",status_);
                    model.setIsCoo(name1);
                }
                String status_2 = model.getOrg();
                if(StringUtils.isNotEmpty(status_)){//组织形式
                    String name2 = codeUtils.getCodeNameByValue("203",status_2);
                    model.setOrg(name2);
                }
                AdminVO vo = new AdminVO();
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
    @ApiOperation(value = "查询母基金列表", notes = "查询母基金列表",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/selectFundInfoLists", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectFundInfoListController(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage) {
        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            PageInfo<FundInfoModel> rows = conditionService.selectListPageFundInfo(searchCondition);
            List<FundInfoVO> list = new ArrayList<FundInfoVO>();
            for (FundInfoModel model : rows.getList()) {
                FundInfoVO vo = new FundInfoVO();
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
     * 查询码值表
     * @param
     * @return
     */
    @ApiOperation(value = "查询码值接口", notes = "查询码值接口",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "parentId", required = true, dataType = "String")
    })
    @GetMapping(value = "/selectConditionCode", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectConditionCode(@RequestParam("parentId") String parentId) {
        try {
            //599
            if(StringUtils.isNotEmpty(parentId)){
                List<CommTCodeModel> codelist = codeUtils.getCodeByParentId(parentId);
                List<CodeVO> voList = new ArrayList();
                for (CommTCodeModel cm :codelist) {
                    CodeVO vo = new CodeVO();
                    BeanUtils.copyProperties(vo, cm);
                    voList.add(vo);
                }
                dataTablesResponse.setData(voList);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResponse.error();
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    /**
     *使流程走到下一個結點
     * @param process_key:流程編號：ei_publish_conditions
     * @param task_Number:要走到下一步的當前節點：UserTask_6
     * @param bizKey:主鍵id：10010
     * @return
     */
    /*@RequestMapping(value = "/ProcessSubmitNext", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Boolean fixFlowNextTask(@RequestParam("process_key") String process_key
            ,@RequestParam("task_Number") String task_Number ,@RequestParam("bizKey") String bizKey){
        UimUtilsUserId.findTaskComment("HYSQ201408200825","UserTask_2");
        //conditionService.next(process_key,bizKey,task_Number);
        TaskService taskService = ProcessEngineManagement.getDefaultProcessEngine().getTaskService();
        TaskQuery taskQuery = taskService.createTaskQuery();
        // 查询到接受任务节点的任务实例
        ProcessEngine processEngine =ProcessEngineManagement.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
       // ProcessInstance processInstance = processInstanceQuery.processDefinitionKey(process_key).processInstanceBusinessKey(bizKey).singleResult();
        //FIXFLOW_RUN_PROCESSINSTANCE:PROCESSINSTANCE_ID
        //String processInstanceId = processInstance.getId();
        //TaskQuery t = taskQuery.processDefinitionKey(process_key).processInstanceId(processInstanceId);
        //TaskInstanceEntity taskInstanceEntity = (TaskInstanceEntity) t.nodeId(task_Number).taskNotEnd().singleResult();
        // 驱动流程往下走一步
        //String tokenId = taskInstanceEntity.getTokenId();//FIXFLOW_RUN_TASKINSTANCE:TOKEN_ID
        String tokenId = "ecd92b19-ed39-49eb-a0ca-7143929c62d0";
        //runtimeService.setTokenVariable(tokenId, "investNewshopApply", apply);
        runtimeService.tokenSignal(tokenId, null);
        return true;
    }*/


}
