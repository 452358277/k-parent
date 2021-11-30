package com.ppmg.ei.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.founder.core.manage.interfaces.IWorkflowManager;
import com.founder.core.manage.local.WorkflowManager;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.utils.UimUtilsUserId;
import com.ppmg.ei.vo.FpThreemeetingInfoVO;
import com.ppmg.ei.vo.ProjBackMoneySettleVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.founder.ssm.core.vo.BaseVO;
import com.founder.ssm.core.action.BaseAction;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.annotation.Resource;

/**
 * 描述 [Controller] 
 * @author root
 * @date 2019-09-26 13:57 
 */ 
@RestController
@Scope("prototype")
@Api(tags={"回收资金结算接口"})
public class ProjBackMoneySettleController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private ProjBackMoneySettleService projBackMoneySettleService;
	@Reference
	private ProjInfoService projInfoService;
	@Reference
	private FpThreemeetingInfoService fpThreemeetingInfoService;
	@Reference
	private FundQuitApplService fundQuitApplyService;
	@Reference
	private ProjContractFileService projContractFileService;
	@Resource(name="codeUtils")
	private CodeUtils codeUtils;
	@Reference(check = false)
	private ProjAppInfoService projAppInfoService;
	@Reference
	private FixflowRunTaskinstanceService fixflowRunTaskinstanceService;

	@ApiOperation(value="分页查询回收资金结算列表", notes="分页查询回收资金结算列表", httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
		@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/selectProjBackMoneySettle", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String list(@RequestParam("pageSize") int length, @RequestParam("currPage") int currPage
			, @RequestParam("projId") String projId
			, @RequestParam("status") String status){
		try {
			searchCondition.setPageSize(length);
			searchCondition.setCurrPage(currPage);
			if(StringUtil.isNotEmpty(projId)){
				searchCondition.addConditionEqualTo("PROJ_ID",projId);
			}
			if(StringUtil.isNotEmpty(status)){
				searchCondition.addConditionEqualTo("STATUS",status);
			}
			PageInfo<ProjBackMoneySettleModel> rows = projBackMoneySettleService.selectListPage(searchCondition);
			List<ProjBackMoneySettleVO> list = new ArrayList<ProjBackMoneySettleVO>();
			for(ProjBackMoneySettleModel model : rows.getList()){
				String appId = model.getAppId();
				FundQuitApplModel fm = fundQuitApplyService.selectById(appId);
				if(fm !=null){
					model.setProjName(fm.getAppName());
				}
				String status_ = model.getSettleType();
				if(StringUtils.isNotEmpty(status_)){
					String name = codeUtils.getCodeNameByValue("1020",status_);
					model.setSettleTypeName(name);
				}

				ProjBackMoneySettleVO vo = new ProjBackMoneySettleVO();
				if(org.apache.commons.lang3.StringUtils.isNotEmpty(model.getStatus())){//流程状态
					vo.setStatusName(codeUtils.getCodeNameByValue("599", model.getStatus()));
				}
				BeanUtils.copyProperties(vo,model);
				//退出决策名称
				vo.setAppIdName(fm.getAppName());
				String name = null;
				//0合同类，1合伙人会议
				if("0".equals(model.getSettleType())){
					ProjContractFileQuitModel models = projContractFileService.selectDetailes(model.getFileId());
					if(models !=null){
						name = models.getFileTitle();
					}
				}else if("1".equals(model.getSettleType())){
					FpThreemeetingInfoModel model3 = fpThreemeetingInfoService.selectById(model.getMeetingId());
					if(model3 !=null){
						name = model3.getMeetingTitle();
					}
				}
				vo.setName(name);
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

	@ApiOperation(value="获取回收资金结算详细信息", notes="根据url的id来获取回收资金结算详细信息", httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "settleId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
   })
	@GetMapping(value = "/selectProjBackMoneyDetailes", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detail(@RequestParam(value = "settleId") String settleId){
		try {
			ProjBackMoneySettleModel model = projBackMoneySettleService.selectById(settleId);
			String appId = model.getAppId();
			FundQuitApplModel fm = fundQuitApplyService.selectById(appId);
			if(fm !=null){
				model.setProjName(fm.getAppName());
			}
			String status_ = model.getSettleType();
			if(StringUtils.isNotEmpty(status_)){
				//回收类型，1020：0，合同类；1，合伙人会议
				String name = codeUtils.getCodeNameByValue("1020",status_);
				model.setSettleTypeName(name);
			}
			ProjBackMoneySettleVO vo = new ProjBackMoneySettleVO();
			BeanUtils.copyProperties(vo, model);
			if(org.apache.commons.lang3.StringUtils.isNotEmpty(model.getStatus())){//流程状态
				vo.setStatusName(codeUtils.getCodeNameByValue("599", model.getStatus()));
			}
			if(model != null){
				if("0".equals(status_)){//0，合同类
					String fileId = model.getFileId();
					ProjContractFileQuitModel contractFileModel = projContractFileService.selectDetailes(fileId);
					//回收类型是合同类的时候，合同名称
					if(contractFileModel!=null){
						vo.setContractTitle(contractFileModel.getFileTitle());
					}

				}
			}
			vo.setAppIdName(fm.getAppName());//退出决策名称
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

	@ApiOperation(value = "回收资金结算增加 ", notes = "回收资金结算增加", httpMethod = "POST")
	@ApiImplicitParam(name = "ProjBackMoneySettleModel", value = "ProjBackMoneySettleModel实体类", required = true, dataType = "ConditionModel", paramType = "path")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@RequestMapping(value = "/addProjBackMoneySettleModel", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addProjBackMoneySettleModelController(@RequestBody ProjBackMoneySettleModel projBackMoneySettleModel) {
		try {
			if(projBackMoneySettleModel != null){
				String appId = projBackMoneySettleModel.getAppId();
				/*ProjBackMoneySettleModel appIdLs = projBackMoneySettleService.selectProjBackMoneySettleAppId(appId);
				if(appIdLs != null){
					baseResponse.setStatus("400");
					baseResponse.setMsg("一個退出決策只回收一次");
					return JSONObject.toJSONString(baseResponse);
				}*/
				//发起人APP_USER的id,应该是取当前登陆人
				String userId = String.valueOf(this.getLoginUser().getUserId());
				System.out.println("当前登陆人测试----------->"+userId);
				//String userId = UimUtilsUserId.getUserId();
				Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();//獲取部門
				System.out.println("当前登陆人测试部门----------->"+deptId);
				String projId = projBackMoneySettleModel.getProjId();

				//更新主表时间
				ProjAppInfoModel proAppInfo = new ProjAppInfoModel();
				proAppInfo.setProjId(projId);
				proAppInfo.setUpdateDt(new Date());//更新时间
				projAppInfoService.updateAppInfo(proAppInfo);
				ProjInfoModel projInfoModel = new ProjInfoModel();
				projInfoModel.setProjId(projId);
				projInfoModel.setUpdateDt(new Date());//更新时间
				projInfoService.updateprojInfo(projInfoModel);

				ProjInfoModel pf_Serch = projInfoService.selectById(projId);//主表
				String tag = projBackMoneySettleModel.getTag();
				String id = UUID.randomUUID().toString();
				projBackMoneySettleModel.setCreateBy(userId);
				projBackMoneySettleModel.setCreateDt(new Date());
				projBackMoneySettleModel.setUpdateBy(userId);
				projBackMoneySettleModel.setUpdateDt(new Date());
				if (tag != null && "0".equals(tag)) {//提交流程并且保存数据
					IWorkflowManager wm = new WorkflowManager();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String date = sdf.format(new Date());
					String authid = userId;
					//主键id
					String businessKey = id;
					//流程编号
					String processID = "TG_RecoveryFundSettlement";
					//流程主题
					//String subject = "投管基金-回收资金结算" + "-" + date;
					//流程名称：项目或基金名称（股权相关的功能）
					String subject = "回收资金结算" + ":" + pf_Serch.getProjName();
					logger.info("##--------------ProjName--->>"+pf_Serch.getProjName()+"<--subject--->"+subject);
					//其他參數
					Map<String, Object> formMap = new HashMap<String, Object>();
					formMap.put("taskTitle", subject);
					formMap.put("businessKey", businessKey);
					formMap.put("Id", businessKey);
					formMap.put("settleId", businessKey);//settleId
					formMap.put("deptId", deptId);//settleId
					formMap.put("projId", projId);//projId
					//启动流程
					//HandleCommand_StartAndSubmit：要在对应的流程中设置按钮的名字
					//Map<String, Object> formMap, String commandId, String processDefinitionKey, String businessKey, String authId
					ProcessInstance inst = wm.startAndSubmit(formMap,"HandleCommand_StartAndSubmit",processID,businessKey,authid);
					//"提交成功！"
					if (inst != null) {
						logger.info("##------->流程启动成功<--------##");
						//保存数据
						projBackMoneySettleModel.setStatus("1");
						projBackMoneySettleModel.setSettleId(id);
						projBackMoneySettleModel.setProcessInstId(inst.getId());
						projBackMoneySettleService.insert(projBackMoneySettleModel);
					} else {
						logger.error("##------->流程启动失败<--------##");
						baseResponse.error("流程启动失败");
					}
				}else if(tag != null && "1".equals(tag)){
					//保存数据
					projBackMoneySettleModel.setStatus("0");
					projBackMoneySettleModel.setSettleId(id);
					projBackMoneySettleService.insert(projBackMoneySettleModel);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	@ApiOperation(value = "回收资金结算更新 ", notes = "回收资金结算更新页面", httpMethod = "POST")
	@ApiImplicitParam(name = "ProjBackMoneySettleModel", value = "ProjBackMoneySettleModel体类", required = true, dataType = "ConditionModel", paramType = "path")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@RequestMapping(value = "/updateProjBackMoneySettleModel", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateProjBackMoneySettleModelController(@RequestBody ProjBackMoneySettleModel projBackMoneySettleModel) {
		try {
			if(projBackMoneySettleModel != null){
				String settleId = projBackMoneySettleModel.getSettleId();
				ProjBackMoneySettleModel model_fileId = projBackMoneySettleService.selectById(settleId);
				if(!projBackMoneySettleModel.getAppId().equals(projBackMoneySettleModel.getAppId())){
					/*String appId = projBackMoneySettleModel.getAppId();
					ProjBackMoneySettleModel appIdLs = projBackMoneySettleService.selectProjBackMoneySettleAppId(appId);
					if(appIdLs != null){
						baseResponse.setStatus("400");
						baseResponse.setMsg("一個退出決策只回收一次");
						return JSONObject.toJSONString(baseResponse);
					}*/
				}
				String userId = String.valueOf(this.getLoginUser().getUserId());
				//String userId = UimUtilsUserId.getUserId();
				System.out.println("当前登陆人更新测试----------->"+userId);
				Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();//獲取部門
				System.out.println("当前登陆人更新测试-deptId---------->"+deptId);
				String projId = projBackMoneySettleModel.getProjId();

				//更新主表时间
				ProjAppInfoModel proAppInfo = new ProjAppInfoModel();
				proAppInfo.setProjId(projId);
				proAppInfo.setUpdateDt(new Date());//更新时间
				projAppInfoService.updateAppInfo(proAppInfo);
				ProjInfoModel projInfoModel = new ProjInfoModel();
				projInfoModel.setProjId(projId);
				projInfoModel.setUpdateDt(new Date());//更新时间
				projInfoService.updateprojInfo(projInfoModel);

				ProjInfoModel pf_Serch = projInfoService.selectById(projId);//主表
				String tag = projBackMoneySettleModel.getTag();
				String id = projBackMoneySettleModel.getSettleId();
				projBackMoneySettleModel.setCreateBy(userId);
				projBackMoneySettleModel.setCreateDt(new Date());
				projBackMoneySettleModel.setUpdateBy(userId);
				projBackMoneySettleModel.setUpdateDt(new Date());
				if (tag != null && "0".equals(tag)) {//提交流程并且保存数据
					IWorkflowManager wm = new WorkflowManager();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String date = sdf.format(new Date());
					String authid = userId;
					//主键id
					String businessKey = id;
					//流程编号
					String processID = "TG_RecoveryFundSettlement";
					//流程主题
					//String subject = "投管基金-回收资金结算" + "-" + date;
					//流程名称：项目或基金名称（股权相关的功能）
					String subject = "回收资金结算" + ":" + pf_Serch.getProjName();
					logger.info("##--------------ProjName--->>"+pf_Serch.getProjName()+"<--subject--->"+subject);
					//其他參數
					Map<String, Object> formMap = new HashMap<String, Object>();
					formMap.put("taskTitle", subject);
					formMap.put("businessKey", businessKey);
					formMap.put("Id", businessKey);
					formMap.put("settleId", businessKey);//settleId
					formMap.put("deptId", deptId);//settleId
					formMap.put("projId", projId);//projId
					//启动流程
					ProcessInstance inst = wm.startAndSubmit(formMap,"HandleCommand_StartAndSubmit",processID,businessKey,authid);
					if (inst != null) {
						logger.info("##------->流程启动成功<--------##");
						//保存数据
						projBackMoneySettleModel.setStatus("1");
						projBackMoneySettleModel.setSettleId(id);
                        projBackMoneySettleModel.setUpdateDt(new Date());
						projBackMoneySettleModel.setProcessInstId(inst.getId());
						projBackMoneySettleService.update(projBackMoneySettleModel);
					} else {
						logger.error("##------->流程启动失败<--------##");
						baseResponse.error("流程启动失败");
					}
				}else if(tag != null && "1".equals(tag)){
					//保存数据
					projBackMoneySettleModel.setStatus("0");
					projBackMoneySettleModel.setSettleId(id);
                    projBackMoneySettleModel.setUpdateDt(new Date());
					projBackMoneySettleService.update(projBackMoneySettleModel);
				}else if(tag != null && "3".equals(tag)){
					//保存数据
					projBackMoneySettleModel.setSettleId(id);
                    projBackMoneySettleModel.setUpdateDt(new Date());
					projBackMoneySettleService.update(projBackMoneySettleModel);
				}

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	@ApiOperation(value = "回收资金结算删除 ", notes = "回收资金结算删除", httpMethod = "POST")
	@ApiImplicitParam(name = "id", value = "settleId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@RequestMapping(value = "/deleteProjBackMoneySettleModel", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteProjBackMoneySettleModelController(@RequestParam(value = "id") String settleId) {
		try {
			if(settleId != null && settleId != ""){
				String[] arr = settleId.split(",");
				for (String st : arr) {
					projBackMoneySettleService.deleteBatch(arr);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}


	@ApiOperation(value = "查询投后事项接口 ", notes = "查询投后事项接口", httpMethod = "POST")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
			@ApiImplicitParam(name = "projId", value = "projId", required = true, dataType = "String"),
			@ApiImplicitParam(name = "status(2:已通过)", value = "status", required = true, dataType = "String")
	})
	@RequestMapping(value = "/selectFpThreemeetingInfoBack", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectThreemeetingListBack(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage
			,@RequestParam("projId") String projId,@RequestParam("status") String status) {
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			//searchCondition.addConditionNotEqualTo("STATUS", "9");
			searchCondition.addConditionEqualTo("CARRIER", projId);
			searchCondition.addConditionEqualTo("MEETING_TYPE", "2");
			if(StringUtils.isNotEmpty(status)){
				searchCondition.addConditionEqualTo("STATUS", status);
			}
			PageInfo<FpThreemeetingInfoModel> rows = fpThreemeetingInfoService.fundFpThreemeetingInfoListPage(searchCondition);
			List<FpThreemeetingInfoVO> list = new ArrayList<>();
			for (FpThreemeetingInfoModel model : rows.getList()) {
				FpThreemeetingInfoVO vo = new FpThreemeetingInfoVO();
				BeanUtils.copyProperties(vo, model);
				//事項類別
				if (model.getMeetingType() != null && !"".equals(model.getMeetingType())) {
					ProjInfoModel pf_Serch = projInfoService.selectById(projId);//主表
					//项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
					String projType = pf_Serch.getProjType();
					if("2".equals(projType)){//基金投后事项，1017
						vo.setMeetingTypeName(codeUtils.getCodeNameByValue("1017", model.getMeetingType()));
					}else if("1".equals(projType)){//项目投后事项1027
						vo.setMeetingTypeName(codeUtils.getCodeNameByValue("1027", model.getMeetingType()));
					}
				}
				//是否需要法务审批
				if (model.getLegalApproval() != null && !"".equals(model.getLegalApproval())) {
					vo.setLegalApprovalName(codeUtils.getCodeNameByValue("9013", model.getLegalApproval()));
				}
				//流程状态
				if(org.apache.commons.lang3.StringUtils.isNotEmpty(model.getStatus())){
					vo.setStatusName(codeUtils.getCodeNameByValue("599", model.getStatus()));
				}
				//一般事项，重大事项
				if (model.getEventType() != null && !"".equals(model.getEventType())) {
					vo.setEventTypeName(codeUtils.getCodeNameByValue("1025", model.getEventType()));
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

}

