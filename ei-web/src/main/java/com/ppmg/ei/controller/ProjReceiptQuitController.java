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
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.utils.UimUtilsUserId;
import com.ppmg.ei.vo.ProjReceiptQuitVO;
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

/** 
 * 描述 [Controller] 
 * @author null
 * @date 2019-10-15 16:17 
 */ 
@RestController
@Scope("prototype")
@Api(tags={"项目退出收款接口"})
public class ProjReceiptQuitController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private ProjReceiptQuitService projReceiptQuitService;
	@Resource(name="codeUtils")
	private CodeUtils codeUtils;
	@Reference
	private FundQuitApplService fundQuitApplyService;
	@Reference
	private ProjAppInfoQuitService projAppInfoQuitService;
	@Reference
	private ProjContractFileService projContractFileService;
	@Reference
	private ProjInfoService projInfoService;
	@Reference
	private ProjAppInfoService projAppInfoService;
	@Reference
	private FixflowRunTaskinstanceService fixflowRunTaskinstanceService;

	@ApiOperation(value="项目退出收款接口列表", notes="项目退出收款接口列表", httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
		@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/selectProjReceiptQuit", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectProjReceiptQuits(@RequestParam("pageSize") int length, @RequestParam("currPage") int currPage,
			@RequestParam("projId") String projId,@RequestParam("status") String status){
		try {
			searchCondition.setPageSize(length);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("PROJ_ID", projId);
			if(StringUtils.isNotEmpty(status)){
				searchCondition.addConditionEqualTo("STATUS", status);
			}
			PageInfo<ProjReceiptQuitModel> rows = projReceiptQuitService.selectListPage(searchCondition);
			List<ProjReceiptQuitVO> list = new ArrayList<ProjReceiptQuitVO>();
			for(ProjReceiptQuitModel model : rows.getList()){
				ProjReceiptQuitVO vo = new ProjReceiptQuitVO();
				BeanUtils.copyProperties(model, vo);
				//流程狀態
				if(model.getStatus() !=null){
					vo.setStatusName(codeUtils.getCodeNameByValue("599", model.getStatus()));
				}
				//退出合同id
				String fileId = model.getFileId();
				//查询退出合同
				ProjContractFileQuitModel fs = projContractFileService.selectDetailes(fileId);
				if(fs != null){
					String appId = fs.getAppId();
					FundQuitApplModel mo = fundQuitApplyService.selectById(appId);
					if(mo != null){
						//退出立项主键
						String quitProjId = mo.getQuitProjId();
						// 查询退出立项
						ProjAppInfoQuitModel model_quit = projAppInfoQuitService.selectById(quitProjId);
						if(model_quit != null) {
							//退出立项id
							vo.setQuitProjId(model_quit.getQuitProjId());
							//退出立项名称
							vo.setQuitName(model_quit.getQuitName());
						}
					}
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

	@ApiOperation(value = "获取退出收款详细信息", notes = "获取退出收款详细信息", httpMethod = "GET")
	@ApiImplicitParam(name = "receiptId", value = "RECEIPT_ID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/selectProjReceiptQuitDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectProjReceiptQuitDetails(@RequestParam(value = "receiptId") String id){
		try {
			ProjReceiptQuitModel model = projReceiptQuitService.selectById(id);
			ProjReceiptQuitVO vo = new ProjReceiptQuitVO();
			BeanUtils.copyProperties( model,vo);
			ProjInfoModel pf = projInfoService.selectById(model.getProjId());
			if(pf != null){
				//出资主体
				vo.setInveName(pf.getInveName());
				// 項目名稱
				vo.setProjName(pf.getProjName());
			}
			//退出合同id
			String fileId = model.getFileId();
			// 查询退出合同
			ProjContractFileQuitModel fs = projContractFileService.selectDetailes(fileId);
			if(fs != null){
				String appId = fs.getAppId();
				FundQuitApplModel mo = fundQuitApplyService.selectById(appId);
				if(mo != null){
					//退出立项主键
					String quitProjId = mo.getQuitProjId();
					// 查询退出立项
					ProjAppInfoQuitModel model_quit = projAppInfoQuitService.selectById(quitProjId);
					if(model_quit != null) {
						//退出立项id
						vo.setQuitProjId(model_quit.getQuitProjId());
						//退出立项名称
						vo.setQuitName(model_quit.getQuitName());
					}
				}
			}
			//流程狀態
			if(model.getStatus() != null){
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
	@ApiOperation(value="增加退出收款", notes="增加退出收款", httpMethod = "POST")
	@ApiImplicitParam(name = "projReceiptQuitModel", value = "projReceiptQuitModel", required = true, dataType = "ProjReceiptQuitModel")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@RequestMapping(value = "/addProjReceiptQuit", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addProjReceiptQuits(@RequestBody ProjReceiptQuitModel projReceiptQuitModel) {
		try {
			if (projReceiptQuitModel != null) {
				String id = UUID.randomUUID().toString();
				String userId = String.valueOf(this.getLoginUser().getUserId());
				String projId = projReceiptQuitModel.getProjId();

				//更新主表时间
				ProjAppInfoModel proAppInfo = new ProjAppInfoModel();
				proAppInfo.setProjId(projId);
				proAppInfo.setUpdateDt(new Date());
				projAppInfoService.updateAppInfo(proAppInfo);
				ProjInfoModel projInfoModel = new ProjInfoModel();
				projInfoModel.setProjId(projId);
				projInfoModel.setUpdateDt(new Date());
				projInfoService.updateprojInfo(projInfoModel);

				ProjInfoModel pf_Serch = projInfoService.selectById(projId);
				//String userId = UimUtilsUserId.getUserId("1");
				System.out.println("当前登陆人测试----------->"+userId);
				Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();
				projReceiptQuitModel.setCreateBy(userId);
				projReceiptQuitModel.setUpdateBy(userId);
				String tag = projReceiptQuitModel.getTag();
				//提交流程并且保存数据
				if (tag != null && "0".equals(tag)) {
					IWorkflowManager wm = new WorkflowManager();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String date = sdf.format(new Date());
					String authid = userId;
					//主键id
					String businessKey = id;
					//流程编号
					String	processID = "TG_RecoveryFundSettlement_project";
					//流程主题
					//String	subject = "項目投管-项目退出收款" + "-" + date;
					//流程名称：项目或基金名称（股权相关的功能）
					String subject = "退出收款" + "：" + pf_Serch.getProjName();
					logger.info("##--------------ProjName--->>"+pf_Serch.getProjName()+"<--subject--->"+subject);
					//其他參數
					Map<String, Object> formMap = new HashMap<>();
					formMap.put("taskTitle", subject);
					formMap.put("businessKey", businessKey);
					//所在部門deptId
					formMap.put("deptId", deptId);
					formMap.put("projId", projReceiptQuitModel.getProjId());
					ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", processID, businessKey, authid);
					//"提交成功！"
					if (inst != null) {
						logger.info("##------->流程启动成功<--------##");
						//保存数据//審批中
						projReceiptQuitModel.setStatus("1");
						projReceiptQuitModel.setReceiptId(id);
						projReceiptQuitModel.setCreateDt(new Date());
						projReceiptQuitModel.setUpdateDt(new Date());
						projReceiptQuitModel.setProcessInstId(inst.getId());
						projReceiptQuitService.insert(projReceiptQuitModel);
					} else {
						logger.error("##------->流程启动失败<--------##");
						baseResponse.error("流程启动失败");
					}
				} else if (tag != null && "1".equals(tag)) {
					//保存数据//草稿
					projReceiptQuitModel.setStatus("0");
					projReceiptQuitModel.setReceiptId(id);
					projReceiptQuitModel.setCreateDt(new Date());
					projReceiptQuitModel.setUpdateDt(new Date());
					projReceiptQuitService.insert(projReceiptQuitModel);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}
	@ApiOperation(value="修改退出收款", notes="修改退出收款", httpMethod = "POST")
	@ApiImplicitParam(name = "projReceiptQuitModel", value = "projReceiptQuitModel", required = true, dataType = "ProjReceiptQuitModel")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@RequestMapping(value = "/updateProjReceiptQuit", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateProjReceiptQuits(@RequestBody ProjReceiptQuitModel projReceiptQuitModel) {
		try {
			if (projReceiptQuitModel != null) {
				String receiptId = projReceiptQuitModel.getReceiptId();
				String userId = String.valueOf(this.getLoginUser().getUserId());
				String projId = projReceiptQuitModel.getProjId();
				//更新主表时间
				ProjAppInfoModel proAppInfo = new ProjAppInfoModel();
				proAppInfo.setProjId(projId);
				proAppInfo.setUpdateDt(new Date());
				projAppInfoService.updateAppInfo(proAppInfo);
				ProjInfoModel projInfoModel = new ProjInfoModel();
				projInfoModel.setProjId(projId);
				projInfoModel.setUpdateDt(new Date());
				projInfoService.updateprojInfo(projInfoModel);

				ProjInfoModel pf_Serch = projInfoService.selectById(projId);
				//String userId = UimUtilsUserId.getUserId("1");
				System.out.println("当前登陆人测试----------->"+userId);
				Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();
				projReceiptQuitModel.setCreateBy(userId);
				projReceiptQuitModel.setUpdateBy(userId);
				String tag = projReceiptQuitModel.getTag();
				//提交流程并且保存数据
				if (tag != null && "0".equals(tag)) {
					IWorkflowManager wm = new WorkflowManager();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String date = sdf.format(new Date());
					String authid = userId;
					//主键id
					String businessKey = receiptId;
					//流程编号
					String	processID = "TG_RecoveryFundSettlement_project";
					//流程主题
					//String	subject = "項目投管-项目退出收款" + "-" + date;
					//流程名称：项目或基金名称（股权相关的功能）
					String subject = "退出收款" + "：" + pf_Serch.getProjName();
					logger.info("##--------------ProjName--->>"+pf_Serch.getProjName()+"<--subject--->"+subject);
					//其他參數
					Map<String, Object> formMap = new HashMap<>();
					formMap.put("taskTitle", subject);
					formMap.put("businessKey", businessKey);
					//所在部門deptId
					formMap.put("deptId", deptId);
					formMap.put("projId", projReceiptQuitModel.getProjId());
					ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", processID, businessKey, authid);
					//"提交成功！"
					if (inst != null) {
						logger.info("##------->流程启动成功<--------##");
						//保存数据//審批中
						projReceiptQuitModel.setStatus("1");
                        projReceiptQuitModel.setUpdateDt(new Date());
						projReceiptQuitModel.setProcessInstId(inst.getId());
						projReceiptQuitService.update(projReceiptQuitModel);
					} else {
						logger.error("##------->流程启动失败<--------##");
						baseResponse.error("流程启动失败");
					}
				} else if (tag != null && "1".equals(tag)) {
					//保存数据//草稿
					projReceiptQuitModel.setStatus("0");
                    projReceiptQuitModel.setUpdateDt(new Date());
					projReceiptQuitService.update(projReceiptQuitModel);
				}else if (tag != null && "3".equals(tag)) {
					//保存数据//审批中
					projReceiptQuitModel.setStatus("1");
                    projReceiptQuitModel.setUpdateDt(new Date());
					projReceiptQuitService.update(projReceiptQuitModel);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}
	@ApiOperation(value="删除退出收款", notes="删除退出收款", httpMethod = "POST")
	@ApiImplicitParam(name = "projReceiptQuitModel", value = "projReceiptQuitModel", required = true, dataType = "ProjReceiptQuitModel")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@RequestMapping(value = "/deleteProjReceiptQuit", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteProjReceiptQuit(@RequestParam("receiptId") String receiptId) {
		try {
			String[] arr = null;
			if (receiptId != "" && receiptId != null) {
				arr = receiptId.split(",");
				projReceiptQuitService.deleteBatch(arr);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	/**
	 ********↓↓↓↓↓↓↓↓↓↓*********平台名称：金财合盈*******↓↓↓↓↓↓******管理公司退出收款*******↓↓↓↓↓↓↓↓*********
	 */

	@ApiOperation(value="项目退出收款接口列表(管理公司退出收款)", notes="项目退出收款接口列表(管理公司退出收款)", httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/selectProjReceiptQuitMc", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectProjReceiptQuitsMc(@RequestParam("pageSize") int length, @RequestParam("currPage") int currPage,
										 @RequestParam("projId") String projId,@RequestParam("status") String status){
		try {
			searchCondition.setPageSize(length);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("PROJ_ID", projId);
			if(StringUtils.isNotEmpty(status)){
				searchCondition.addConditionEqualTo("STATUS", status);
			}
			PageInfo<ProjReceiptQuitModel> rows = projReceiptQuitService.selectListPage(searchCondition);
			List<ProjReceiptQuitVO> list = new ArrayList<>();
			for(ProjReceiptQuitModel model : rows.getList()){
				ProjReceiptQuitVO vo = new ProjReceiptQuitVO();
				BeanUtils.copyProperties(model, vo);
				//流程狀態
				if(model.getStatus() !=null){
					vo.setStatusName(codeUtils.getCodeNameByValue("599", model.getStatus()));
				}
				//退出合同id
				String fileId = model.getFileId();
				//查询退出合同
				ProjContractFileQuitModel fs = projContractFileService.selectDetailes(fileId);
				if(fs != null){
					//退出立项主键
					String quitProjId = fs.getAppId();
					// 查询退出立项
					ProjAppInfoQuitModel model_quit = projAppInfoQuitService.selectById(quitProjId);
					if(model_quit != null) {
						//退出立项id
						vo.setQuitProjId(model_quit.getQuitProjId());
						//退出立项名称
						vo.setQuitName(model_quit.getQuitName());
					}
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
	@ApiOperation(value = "获取退出收款详细信息", notes = "获取退出收款详细信息", httpMethod = "GET")
	@ApiImplicitParam(name = "receiptId", value = "RECEIPT_ID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/selectProjReceiptQuitDetailMc", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectProjReceiptQuitDetailsMc(@RequestParam(value = "receiptId") String id){
		try {
			ProjReceiptQuitModel model = projReceiptQuitService.selectById(id);
			ProjReceiptQuitVO vo = new ProjReceiptQuitVO();
			BeanUtils.copyProperties( model,vo);
			ProjInfoModel pf = projInfoService.selectById(model.getProjId());
			if(pf != null){
				//出资主体
				vo.setInveName(pf.getInveName());
				// 項目名稱
				vo.setProjName(pf.getProjName());
			}
			//退出合同id
			String fileId = model.getFileId();
			// 查询退出合同
			ProjContractFileQuitModel fs = projContractFileService.selectDetailes(fileId);
			if(fs != null){
				//退出立项主键
				String quitProjId = fs.getAppId();
				// 查询退出立项
				ProjAppInfoQuitModel model_quit = projAppInfoQuitService.selectById(quitProjId);
				if(model_quit != null) {
					//退出立项id
					vo.setQuitProjId(model_quit.getQuitProjId());
					//退出立项名称
					vo.setQuitName(model_quit.getQuitName());
				}
			}
			//流程狀態
			if(model.getStatus() != null){
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
	@ApiOperation(value="管理公司退出收款增加", notes="管理公司退出收款增加", httpMethod = "POST")
	@ApiImplicitParam(name = "projReceiptQuitModel", value = "projReceiptQuitModel", required = true, dataType = "ProjReceiptQuitModel")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@RequestMapping(value = "/addProjReceiptQuitMc", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addProjReceiptQuitsMc(@RequestBody ProjReceiptQuitModel projReceiptQuitModel) {
		try {
			if (projReceiptQuitModel != null) {
				String receiptId = UUID.randomUUID().toString();
				String userId = String.valueOf(this.getLoginUser().getUserId());
				String projId = projReceiptQuitModel.getProjId();
				ProjInfoModel pf_Serch = projInfoService.selectById(projId);
				//String userId = UimUtilsUserId.getUserId("1");
				System.out.println("当前登陆人测试----------->"+userId);
				Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();
				projReceiptQuitModel.setCreateBy(userId);
				projReceiptQuitModel.setCreateDt(new Date());
				projReceiptQuitModel.setUpdateBy(userId);
				projReceiptQuitModel.setUpdateDt(new Date());
				String tag = projReceiptQuitModel.getTag();
				//提交流程并且保存数据
				if (tag != null && "0".equals(tag)) {
					IWorkflowManager wm = new WorkflowManager();
					String authid = userId;
					//主键id
					String businessKey = receiptId;
					//流程编号
					String	processID = "MC_WithdrawalCollection";
					//流程主题
					String subject = "退出收款" + "：" + pf_Serch.getProjName();
					logger.info("##--------------ProjName--->>"+pf_Serch.getProjName()+"<--subject--->"+subject);
					//其他參數
					Map<String, Object> formMap = new HashMap<>();
					formMap.put("taskTitle", subject);
					formMap.put("businessKey", businessKey);
					formMap.put("deptId", deptId);
					formMap.put("projId", projReceiptQuitModel.getProjId());
					//保存数据
					projReceiptQuitModel.setStatus("0");
					projReceiptQuitModel.setReceiptId(receiptId);
					projReceiptQuitService.insert(projReceiptQuitModel);
					ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", processID, businessKey, authid);
					//"提交成功！"
					if (inst != null) {
						logger.info("##------->流程启动成功<--------##");
						//更新数据//審批中
						ProjReceiptQuitModel model = new ProjReceiptQuitModel();
						model.setReceiptId(receiptId);
						model.setStatus("1");
						model.setProcessInstId(inst.getId());
						projReceiptQuitService.update(model);
					} else {
						logger.error("##------->流程启动失败<--------##");
						baseResponse.error("流程启动失败");
					}
				} else if (tag != null && "1".equals(tag)) {
					//保存数据//草稿
					projReceiptQuitModel.setStatus("0");
					projReceiptQuitModel.setReceiptId(receiptId);
					projReceiptQuitService.insert(projReceiptQuitModel);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}
	@ApiOperation(value="管理公司退出收款修改", notes="管理公司退出收款修改", httpMethod = "POST")
	@ApiImplicitParam(name = "projReceiptQuitModel", value = "projReceiptQuitModel", required = true, dataType = "ProjReceiptQuitModel")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@RequestMapping(value = "/updateProjReceiptQuitMc", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateProjReceiptQuitsMc(@RequestBody ProjReceiptQuitModel projReceiptQuitModel) {
		try {
			if (projReceiptQuitModel != null) {
				String receiptId = projReceiptQuitModel.getReceiptId();
				String userId = String.valueOf(this.getLoginUser().getUserId());
				String projId = projReceiptQuitModel.getProjId();
				ProjInfoModel pf_Serch = projInfoService.selectById(projId);
				//String userId = UimUtilsUserId.getUserId("1");
				System.out.println("当前登陆人测试----------->"+userId);
				Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();
				projReceiptQuitModel.setUpdateBy(userId);
				String tag = projReceiptQuitModel.getTag();
				//提交流程并且保存数据
				if (tag != null && "0".equals(tag)) {
					IWorkflowManager wm = new WorkflowManager();
					String authid = userId;
					//主键id
					String businessKey = receiptId;
					//流程编号
					String	processID = "MC_WithdrawalCollection";
					//流程主题
					String subject = "退出收款" + "：" + pf_Serch.getProjName();
					logger.info("##--------------ProjName--->>"+pf_Serch.getProjName()+"<--subject--->"+subject);
					//其他參數
					Map<String, Object> formMap = new HashMap<>();
					formMap.put("taskTitle", subject);
					formMap.put("businessKey", businessKey);
					formMap.put("deptId", deptId);
					formMap.put("projId", projReceiptQuitModel.getProjId());
					//保存数据
					projReceiptQuitModel.setStatus("0");
					projReceiptQuitModel.setUpdateDt(new Date());
					projReceiptQuitService.update(projReceiptQuitModel);
					ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", processID, businessKey, authid);
					//"提交成功！"
					if (inst != null) {
						logger.info("##------->流程启动成功<--------##");
						//保存数据
						ProjReceiptQuitModel model = new ProjReceiptQuitModel();
						model.setReceiptId(projReceiptQuitModel.getReceiptId());
						model.setStatus("1");
						model.setProcessInstId(inst.getId());
						projReceiptQuitService.update(model);
					} else {
						logger.error("##------->流程启动失败<--------##");
						baseResponse.error("流程启动失败");
					}
				} else if (tag != null && "1".equals(tag)) {
					//保存数据//草稿
					projReceiptQuitModel.setStatus("0");
					projReceiptQuitModel.setUpdateDt(new Date());
					projReceiptQuitService.update(projReceiptQuitModel);
				}else if (tag != null && "3".equals(tag)) {
					//保存数据//审批中
					projReceiptQuitModel.setStatus("1");
					projReceiptQuitModel.setUpdateDt(new Date());
					projReceiptQuitService.update(projReceiptQuitModel);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}
}

