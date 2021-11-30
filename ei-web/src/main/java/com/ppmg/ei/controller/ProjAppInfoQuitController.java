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
import com.ppmg.ei.vo.ProjAppInfoQuitVO;
import net.sf.jsqlparser.expression.StringValue;
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
 * @author root
 * @date 2019-10-15 16:17 
 */ 
@RestController
@Scope("prototype")
@Api(tags={"項目投管-退出立項接口"})
public class ProjAppInfoQuitController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private ProjAppInfoQuitService projAppInfoQuitService;
	@Reference
	private ProjInfoService projInfoService;
	@Resource(name="codeUtils")
	private CodeUtils codeUtils;
	@Reference
	private FpThreemeetingInfoService fpThreemeetingInfoService;
	@Reference
	private ProjStopService projStopService;
	@Reference
	private ProjAppInfoService projAppInfoService;
	@Reference
	private  FixflowRunTaskinstanceService fixflowRunTaskinstanceService;
	@Reference(check = false)
	private FundInfoService fundInfoService;

	@ApiOperation(value="退出立項分頁列表", notes="退出立項分頁列表", httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
		@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/selectProjAppInfoQuit", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectProjAppInfoQuits(@RequestParam("pageSize") int length, @RequestParam("currPage") int currPage
			, @RequestParam("projId") String projId, @RequestParam("status") String status){
		try {
			//String id = UimUtilsUserId.selectOneProjAppInfoQuit("d7cda44c-7369-4818-a92a-cad7ae70fd64");
			searchCondition.setPageSize(length);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("PROJ_ID", projId);
			if(StringUtils.isNotEmpty(status)){
				searchCondition.addConditionEqualTo("STATUS", status);
			}
			PageInfo<ProjAppInfoQuitModel> rows = projAppInfoQuitService.selectListPage(searchCondition);
			List<ProjAppInfoQuitVO> list = new ArrayList<>();
			for(ProjAppInfoQuitModel model : rows.getList()){
				ProjAppInfoQuitVO vo = new ProjAppInfoQuitVO();
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
				//收回金額
				Double backMony = 0.0;
				if (model.getQuitCurrency() != null && model.getNowShare() != null) {
					Double nowShare = model.getNowShare() / 100;
					backMony = model.getQuitCurrency() * nowShare;
				}
				vo.setBackMony(backMony);
				//退出方式
				if(StringUtils.isNotEmpty(model.getQuitWay())){
					String name = codeUtils.getCodeNameByValue("262", model.getQuitWay());
					vo.setQuitWayName(name);
				}
				if(StringUtils.isNotEmpty(model.getStatus())){
					//流程狀態
					String name1 = codeUtils.getCodeNameByValue("599", model.getStatus());
					vo.setStatusName(name1);
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

	@ApiOperation(value = "获取退出立項详细信息", notes = "获取退出立項详细信息", httpMethod = "GET")
	@ApiImplicitParam(name = "projId", value = "QUIT_PROJ_ID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/selectProjAppInfoQuitDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectProjAppInfoQuitDetails(@RequestParam(value = "quitProjId") String quitProjId){
		try {
			ProjAppInfoQuitVO vo = new ProjAppInfoQuitVO();
			ProjAppInfoQuitModel model = projAppInfoQuitService.selectById(quitProjId);
			if(model != null){
				BeanUtils.copyProperties(model, vo);
			}else{
				return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteMapNullValue);
			}
			//主表
			ProjInfoModel pf_Serch = projInfoService.selectById(model.getProjId());
			//項目名稱
			String projName = pf_Serch.getProjName();
			//出資主體
			String inveName = pf_Serch.getInveName();
			//項目名稱
			vo.setInveName(inveName);
			//出資主體
			vo.setProjName(projName);
			//收回金額
			Double backMony = 0.0;
			if (model.getQuitCurrency() != null && model.getNowShare() != null) {
				Double nowShare = model.getNowShare() / 100;
				backMony = model.getQuitCurrency() * nowShare;
			}
			vo.setBackMony(backMony);
			//退出方式
			if(StringUtils.isNotEmpty(model.getQuitWay())){
				String name = codeUtils.getCodeNameByValue("262", model.getQuitWay());
				vo.setQuitWayName(name);
			}
			if(StringUtils.isNotEmpty(model.getStatus())){
				//流程狀態
				String name1 = codeUtils.getCodeNameByValue("599", model.getStatus());
				vo.setStatusName(name1);
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
	@ApiOperation(value = "退出立項增加", notes = "退出立項增加", httpMethod = "POST")
	@ApiImplicitParam(name = "projAppInfoQuitModel", value = "projAppInfoQuitModel", required = true, dataType = "ProjAppInfoQuitModel")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@RequestMapping(value = "/addProjAppInfoQuit", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addProjAppInfoQuits(@RequestBody ProjAppInfoQuitModel projAppInfoQuitModel) {
		try {
			if (projAppInfoQuitModel != null) {
				//判断退出立项名称是否重复
				ProjAppInfoQuitModel quitModel = new ProjAppInfoQuitModel();
				quitModel.setProjId(projAppInfoQuitModel.getProjId());
				List<ProjAppInfoQuitModel> listName = projAppInfoQuitService.selectList(quitModel);
				for (ProjAppInfoQuitModel pq : listName) {
					String quitName = pq.getQuitName();
					if(quitName.equals(projAppInfoQuitModel.getQuitName())){
						baseResponse.setStatus("400");
						baseResponse.setMsg("退出立项名称已被占用,请重新输入合适名称");
						return JSONObject.toJSONString(baseResponse);
					}
				}
				//主键
				String id = UUID.randomUUID().toString();
				String projId = projAppInfoQuitModel.getProjId();
				//主表
				ProjInfoModel pf_Serch = projInfoService.selectById(projId);
				//项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
				String projType = pf_Serch.getProjType();

				//更新主表时间
				ProjAppInfoModel proAppInfo = new ProjAppInfoModel();
				proAppInfo.setProjId(projId);
				//更新时间
				proAppInfo.setUpdateDt(new Date());
				projAppInfoService.updateAppInfo(proAppInfo);
				ProjInfoModel projInfoModel = new ProjInfoModel();
				projInfoModel.setProjId(projId);
				//更新时间
				projInfoModel.setUpdateDt(new Date());
				projInfoService.updateprojInfo(projInfoModel);

				String userId = String.valueOf(this.getLoginUser().getUserId());
				//String userId = UimUtilsUserId.getUserId(projType);
				System.out.println("当前登陆人测试----------->"+userId);
				//獲取部門
				Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();
				projAppInfoQuitModel.setCreateBy(userId);
				projAppInfoQuitModel.setUpdateBy(userId);
				String tag = projAppInfoQuitModel.getTag();
				if (tag != null && "0".equals(tag)) {
					//提交流程并且保存数据
					IWorkflowManager wm = new WorkflowManager();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String date = sdf.format(new Date());
					String authid = userId;
					//主键id
					String businessKey = id;
					//流程编号
					String	processID = "ei_condition_info_project_quit";
					//流程主题
					//String	subject = "項目投管-退出立項" + "-" + date;
					//流程名称：项目或基金名称（股权相关的功能）
					String subject = "退出立项" + "：" + pf_Serch.getProjName();
					logger.info("##--------------ProjName--->>"+pf_Serch.getProjName()+"<--subject--->"+subject);
					//其他參數
					Map<String, Object> formMap = new HashMap<String, Object>();
					formMap.put("taskTitle", subject);
					formMap.put("businessKey", businessKey);
					//所在部門deptId
					formMap.put("deptId", deptId);
					formMap.put("projId", projId);
					ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", processID, businessKey, authid);
					//"提交成功！"
					if (inst != null) {
						logger.info("##------->流程启动成功<--------##");
						//保存数据
						// 審批中
						projAppInfoQuitModel.setStatus("1");
						projAppInfoQuitModel.setQuitProjId(id);
						projAppInfoQuitModel.setProcessInstId(inst.getId());
						//终止状态默认o
						projAppInfoQuitModel.setStopStatus("0");
						projAppInfoQuitService.insert(projAppInfoQuitModel);
					} else {
						logger.error("##------->流程启动失败<--------##");
						baseResponse.error("流程启动失败");
					}
				} else if (tag != null && "1".equals(tag)) {
					//保存数据
					projAppInfoQuitModel.setQuitProjId(id);
					//草稿
					projAppInfoQuitModel.setStatus("0");
					//终止状态默认o
					projAppInfoQuitModel.setStopStatus("0");
					projAppInfoQuitService.insert(projAppInfoQuitModel);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);
	}
	@ApiOperation(value = "退出立項更新", notes = "退出立項更新", httpMethod = "POST")
	@ApiImplicitParam(name = "projAppInfoQuitModel", value = "projAppInfoQuitModel", required = true, dataType = "ProjAppInfoQuitModel")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@RequestMapping(value = "/updateProjAppInfoQuit", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateProjAppInfoQuits(@RequestBody ProjAppInfoQuitModel projAppInfoQuitModel) {
		try {
			if (projAppInfoQuitModel != null) {
				//判断退出立项名称是否重复
				ProjAppInfoQuitModel quitModel = new ProjAppInfoQuitModel();
				quitModel.setProjId(projAppInfoQuitModel.getProjId());
				List<ProjAppInfoQuitModel> listName = projAppInfoQuitService.selectList(quitModel);
				for (ProjAppInfoQuitModel pq : listName) {
					String quitName = pq.getQuitName();
					String quitId = pq.getQuitProjId();
					String ti = projAppInfoQuitModel.getQuitProjId();
					if (quitName.equals(projAppInfoQuitModel.getQuitName())) {
						if (!quitId.equals(ti)) {
							baseResponse.setStatus("400");
							baseResponse.setMsg("退出立项名称已被占用,请重新输入合适名称");
							return JSONObject.toJSONString(baseResponse);
						}
					}
				}
				String id = projAppInfoQuitModel.getQuitProjId();
				String projId = projAppInfoQuitModel.getProjId();
				//主表
				ProjInfoModel pf_Serch = projInfoService.selectById(projId);
				//项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
				String projType = pf_Serch.getProjType();
				//更新主表时间
				ProjAppInfoModel proAppInfo = new ProjAppInfoModel();
				proAppInfo.setProjId(projId);
				//更新时间
				proAppInfo.setUpdateDt(new Date());
				projAppInfoService.updateAppInfo(proAppInfo);
				ProjInfoModel projInfoModel = new ProjInfoModel();
				projInfoModel.setProjId(projId);
				//更新时间
				projInfoModel.setUpdateDt(new Date());
				projInfoService.updateprojInfo(projInfoModel);

				String userId = String.valueOf(this.getLoginUser().getUserId());
				//String userId = UimUtilsUserId.getUserId(projType);
				System.out.println("当前登陆人测试----------->"+userId);
				//獲取部門
				Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();
				projAppInfoQuitModel.setCreateBy(userId);
				projAppInfoQuitModel.setUpdateBy(userId);
				String tag = projAppInfoQuitModel.getTag();
				if (tag != null && "0".equals(tag)) {
					//提交流程并且保存数据
					IWorkflowManager wm = new WorkflowManager();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String date = sdf.format(new Date());
					String authid = userId;
					//主键id
					String businessKey = id;
					//流程编号
					String	processID = "ei_condition_info_project_quit";
					//流程主题
					//String	subject = "項目投管-退出立項" + "-" + date;
					//流程名称：项目或基金名称（股权相关的功能）
					String subject = "退出立项" + "：" + pf_Serch.getProjName();
					logger.info("##--------------ProjName--->>"+pf_Serch.getProjName()+"<--subject--->"+subject);
					//其他參數
					Map<String, Object> formMap = new HashMap<String, Object>();
					formMap.put("taskTitle", subject);
					formMap.put("businessKey", businessKey);
					//所在部門deptId
					formMap.put("deptId", deptId);
					formMap.put("projId", projId);
					ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", processID, businessKey, authid);
					//"提交成功！"
					if (inst != null) {
						logger.info("##------->流程启动成功<--------##");
						//保存数据
						// 審批中
						projAppInfoQuitModel.setStatus("1");
						//更新时间
						projAppInfoQuitModel.setUpdateDt(new Date());
						//流程实例id
						projAppInfoQuitModel.setProcessInstId(inst.getId());
						projAppInfoQuitService.update(projAppInfoQuitModel);
					} else {
						logger.error("##------->流程启动失败<--------##");
						baseResponse.error("流程启动失败");
					}
				} else if (tag != null && "1".equals(tag)) {
					//保存数据
					// 草稿
					projAppInfoQuitModel.setStatus("0");
					projAppInfoQuitModel.setUpdateDt(new Date());
					projAppInfoQuitService.update(projAppInfoQuitModel);
				}else if (tag != null && "3".equals(tag)) {
					//保存数据
					projAppInfoQuitService.update(projAppInfoQuitModel);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);
	}
	@ApiOperation(value = "退出立項删除", notes = "退出立項删除", httpMethod = "POST")
	@ApiImplicitParam(name = "projId", value = "projId", required = true, dataType = "String")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@RequestMapping(value = "/deleteProjAppInfoQuit", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteProjAppInfoQuit(@RequestParam("projId") String projId) {
		try {
			String[] arr = null;
			if (projId != "" && projId != null) {
				arr = projId.split(",");
				projAppInfoQuitService.deleteBatch(arr);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}


	@ApiOperation(value="退出立项终止流程", notes="退出立项终止流程", httpMethod = "POST")
	@ApiImplicitParam(name = "projAppInfoQuitModel", value = "projAppInfoQuitModel", required = true, dataType = "ProjAppInfoQuitModel")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@RequestMapping(value = "/projAppInfoQuitStop", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String projAppInfoQuitStop(@RequestBody ProjAppInfoQuitModel projAppInfoQuitModel) {
		try {
			if (projAppInfoQuitModel != null) {
				String projId = projAppInfoQuitModel.getProjId();
				//查询项目出资，出资完成后不可终止
				List<FpPaymentRequestStopModel> list = projStopService.selectList(projId);
				if(list.size() > 0){
					baseResponse.setStatus("400");
					baseResponse.setMsg("出资完成后不可终止");
					return JSONObject.toJSONString(baseResponse);
				}

				String id = projAppInfoQuitModel.getQuitProjId();
				ProjInfoModel pf_Serch = projInfoService.selectById(projId);
				//项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
				String projType = pf_Serch.getProjType();
				String userId = String.valueOf(this.getLoginUser().getUserId());
				//String userId = UimUtilsUserId.getUserId(projType);
				System.out.println("当前登陆人测试----------->"+userId);
				Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();
				projAppInfoQuitModel.setCreateBy(userId);
				projAppInfoQuitModel.setUpdateBy(userId);
				String tag = projAppInfoQuitModel.getTag();
				if (tag != null && "0".equals(tag)) {
					//提交流程
					IWorkflowManager wm = new WorkflowManager();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String date = sdf.format(new Date());
					String authid = userId;
					//主键id
					String businessKey = id;
					//流程编号
					String	processID = "ei_condition_info_project_quit_stop";
					//流程主题

					String	subject = "退出立项-终止" + "-" + date;
					if(pf_Serch!=null&&4==pf_Serch.getGroupId()){
							subject = "退出决策-终止" + "-" + date;
					}
					//其他參數
					Map<String, Object> formMap = new HashMap<>();
					formMap.put("taskTitle", subject);
					formMap.put("businessKey", businessKey);
					formMap.put("deptId", deptId);
					formMap.put("groupId", projAppInfoQuitModel.getGroupId());
					ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", processID, businessKey, authid);
					//"提交成功！"
					if (inst != null) {
						logger.info("##------->流程启动成功<--------##");
						//更新终止状态为1审批中
						ProjAppInfoQuitModel pag = new ProjAppInfoQuitModel();
						pag.setQuitProjId(projAppInfoQuitModel.getQuitProjId());
						pag.setStopStatus("1");
						projAppInfoQuitService.update(pag);
					} else {
						logger.error("##------->流程启动失败<--------##");
						baseResponse.error("流程启动失败");
					}
				}else if(tag != null && "3".equals(tag)){
					projAppInfoQuitService.update(projAppInfoQuitModel);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);
	}

	@ApiOperation(value="项目终止更新", notes="项目终止更新", httpMethod = "POST")
	@ApiImplicitParam(name = "projAppInfoQuitModel", value = "projAppInfoQuitModel", required = true, dataType = "ProjAppInfoQuitModel")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@RequestMapping(value = "/updateProjAppInfoQuitStop", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateProjAppInfoQuitStop(@RequestBody ProjAppInfoQuitModel projAppInfoQuitModel) {
		try {
			if (projAppInfoQuitModel != null) {
				String userId = String.valueOf(this.getLoginUser().getUserId());
				//String userId = UimUtilsUserId.getUserId("1");
				System.out.println("当前登陆人测试----------->"+userId);
				//保存数据
				projAppInfoQuitService.update(projAppInfoQuitModel);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);
	}

	/**
	 ********↓↓↓↓↓↓↓↓↓↓*********平台名称：金财合盈*******↓↓↓↓↓↓*******管理公司退出决策*******↓↓↓↓↓↓↓↓*********
	 */
	@ApiOperation(value = "退出立項增加（管理公司退出决策）", notes = "退出立項增加（管理公司退出决策）", httpMethod = "POST")
	@ApiImplicitParam(name = "projAppInfoQuitModelMC", value = "projAppInfoQuitModel", required = true, dataType = "ProjAppInfoQuitModel")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@RequestMapping(value = "/addProjAppInfoQuitMC", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addProjAppInfoQuitMC(@RequestBody ProjAppInfoQuitModel projAppInfoQuitModel) {
		try {
			if (projAppInfoQuitModel != null) {
				//判断退出决策名称是否重复
				ProjAppInfoQuitModel quitModel = new ProjAppInfoQuitModel();
				quitModel.setProjId(projAppInfoQuitModel.getProjId());
				List<ProjAppInfoQuitModel> listName = projAppInfoQuitService.selectList(quitModel);
				for (ProjAppInfoQuitModel pq : listName) {
					String quitName = pq.getQuitName();
					if(quitName.equals(projAppInfoQuitModel.getQuitName())){
						baseResponse.setStatus("400");
						baseResponse.setMsg("退出决策名称已被占用,请重新输入合适名称");
						return JSONObject.toJSONString(baseResponse);
					}
				}
				//主键
				String id = UUID.randomUUID().toString();
				String projId = projAppInfoQuitModel.getProjId();
				//主表
				ProjInfoModel pf_Serch = projInfoService.selectById(projId);
				//项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
				String projType = pf_Serch.getProjType();

				//更新主表时间
				ProjAppInfoModel proAppInfo = new ProjAppInfoModel();
				proAppInfo.setProjId(projId);
				//更新时间
				proAppInfo.setUpdateDt(new Date());
				projAppInfoService.updateAppInfo(proAppInfo);
				ProjInfoModel projInfoModel = new ProjInfoModel();
				projInfoModel.setProjId(projId);
				//更新时间
				projInfoModel.setUpdateDt(new Date());
				projInfoService.updateprojInfo(projInfoModel);

				String userId = String.valueOf(this.getLoginUser().getUserId());
				//String userId = UimUtilsUserId.getUserId(projType);
				System.out.println("当前登陆人测试----------->"+userId);
				//獲取部門
				Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();
				projAppInfoQuitModel.setCreateBy(userId);
				projAppInfoQuitModel.setUpdateBy(userId);
				String tag = projAppInfoQuitModel.getTag();
				if (tag != null && "0".equals(tag)) {
					//默认1：基金分类-1自有基金，2管理基金;3受托资金//默认1：基金分类-1自有基金，2管理基金;3受托资金
					String fundTypeProject = "1";
					String fundId = null;
					if (pf_Serch != null) {
						fundId = pf_Serch.getProjObject();
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
					IWorkflowManager wm = new WorkflowManager();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String date = sdf.format(new Date());
					String authid = userId;
					//主键id
					String businessKey = id;
					//流程编号
					String	processID = "MC_WithdrawalDecision";
					//流程主题
					//流程名称：项目或基金名称（股权相关的功能）
					String subject = "退出决策" + "：" + pf_Serch.getProjName();
					logger.info("##--------------ProjName--->>"+pf_Serch.getProjName()+"<--subject--->"+subject);
					//其他參數
					Map<String, Object> formMap = new HashMap<String, Object>();
					formMap.put("taskTitle", subject);
					formMap.put("businessKey", businessKey);
					//所在部門deptId
					formMap.put("deptId", deptId);
					formMap.put("projId", projId);
					//基金分类-1自有基金，2管理基金;3受托资金
					formMap.put("fundType", fundTypeProject);
					formMap.put("deptId", deptId);
					formMap.put("projId", projId);
					formMap.put("fundId", fundId);
					//退出方式--1：全部退出；2：部分退出
					formMap.put("quitWay", projAppInfoQuitModel.getQuitWay());
					//收回金额
					Double backMony = 0.0;
					if (projAppInfoQuitModel != null) {
						if (projAppInfoQuitModel.getQuitCurrency() != null && projAppInfoQuitModel.getNowShare() != null) {
							Double nowShare = projAppInfoQuitModel.getNowShare() / 100;
							backMony = projAppInfoQuitModel.getQuitCurrency() * nowShare;
						}
					}
					//收回金额
					formMap.put("backMony", backMony);
					ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", processID, businessKey, authid);
					//"提交成功！"
					if (inst != null) {
						logger.info("##------->流程启动成功<--------##");
						//保存数据
						// 審批中
						projAppInfoQuitModel.setStatus("1");
						projAppInfoQuitModel.setQuitProjId(id);
						projAppInfoQuitModel.setProcessInstId(inst.getId());
						//终止状态默认o
						projAppInfoQuitModel.setStopStatus("0");
						projAppInfoQuitService.insert(projAppInfoQuitModel);
					} else {
						logger.error("##------->流程启动失败<--------##");
						baseResponse.error("流程启动失败");
					}
				} else if (tag != null && "1".equals(tag)) {
					//保存数据
					projAppInfoQuitModel.setQuitProjId(id);
					//草稿
					projAppInfoQuitModel.setStatus("0");
					//终止状态默认o
					projAppInfoQuitModel.setStopStatus("0");
					projAppInfoQuitService.insert(projAppInfoQuitModel);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);
	}
	@ApiOperation(value = "退出立項更新（管理公司退出决策", notes = "退出立項更新（管理公司退出决策", httpMethod = "POST")
	@ApiImplicitParam(name = "projAppInfoQuitModel", value = "projAppInfoQuitModel", required = true, dataType = "ProjAppInfoQuitModel")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@RequestMapping(value = "/updateProjAppInfoQuitMC", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateProjAppInfoQuitMC(@RequestBody ProjAppInfoQuitModel projAppInfoQuitModel) {
		try {
			if (projAppInfoQuitModel != null) {
				//判断退出立项名称是否重复
				ProjAppInfoQuitModel quitModel = new ProjAppInfoQuitModel();
				quitModel.setProjId(projAppInfoQuitModel.getProjId());
				List<ProjAppInfoQuitModel> listName = projAppInfoQuitService.selectList(quitModel);
				for (ProjAppInfoQuitModel pq : listName) {
					String quitName = pq.getQuitName();
					String quitId = pq.getQuitProjId();
					String ti = projAppInfoQuitModel.getQuitProjId();
					if (quitName.equals(projAppInfoQuitModel.getQuitName())) {
						if (!quitId.equals(ti)) {
							baseResponse.setStatus("400");
							baseResponse.setMsg("退出决策名称已被占用,请重新输入合适名称");
							return JSONObject.toJSONString(baseResponse);
						}
					}
				}
				String id = projAppInfoQuitModel.getQuitProjId();
				String projId = projAppInfoQuitModel.getProjId();
				//主表
				ProjInfoModel pf_Serch = projInfoService.selectById(projId);
				//项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
				String projType = pf_Serch.getProjType();
				//更新主表时间
				ProjAppInfoModel proAppInfo = new ProjAppInfoModel();
				proAppInfo.setProjId(projId);
				//更新时间
				proAppInfo.setUpdateDt(new Date());
				projAppInfoService.updateAppInfo(proAppInfo);
				ProjInfoModel projInfoModel = new ProjInfoModel();
				projInfoModel.setProjId(projId);
				//更新时间
				projInfoModel.setUpdateDt(new Date());
				projInfoService.updateprojInfo(projInfoModel);

				String userId = String.valueOf(this.getLoginUser().getUserId());
				//String userId = UimUtilsUserId.getUserId(projType);
				System.out.println("当前登陆人测试----------->"+userId);
				//獲取部門
				Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();
				projAppInfoQuitModel.setCreateBy(userId);
				projAppInfoQuitModel.setUpdateBy(userId);
				String tag = projAppInfoQuitModel.getTag();
				if (tag != null && "0".equals(tag)) {
					//默认1：基金分类-1自有基金，2管理基金;3受托资金//默认1：基金分类-1自有基金，2管理基金;3受托资金
					String fundTypeProject = "1";
					String fundId = null;
					if (pf_Serch != null) {
						fundId = pf_Serch.getProjObject();
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
					IWorkflowManager wm = new WorkflowManager();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String date = sdf.format(new Date());
					String authid = userId;
					//主键id
					String businessKey = id;
					//流程编号
					String	processID = "MC_WithdrawalDecision";
					//流程主题
					//流程名称：项目或基金名称（股权相关的功能）
					String subject = "退出决策" + "：" + pf_Serch.getProjName();
					logger.info("##--------------ProjName--->>"+pf_Serch.getProjName()+"<--subject--->"+subject);
					//其他參數
					Map<String, Object> formMap = new HashMap<String, Object>();
					formMap.put("taskTitle", subject);
					formMap.put("businessKey", businessKey);
					//所在部門deptId
					formMap.put("deptId", deptId);
					formMap.put("projId", projId);
					//基金分类-1自有基金，2管理基金;3受托资金
					formMap.put("fundType", fundTypeProject);
					formMap.put("deptId", deptId);
					formMap.put("projId", projId);
					formMap.put("fundId", fundId);
					//退出方式--1：全部退出；2：部分退出
					formMap.put("quitWay", projAppInfoQuitModel.getQuitWay());
					//收回金额
					Double backMony = 0.0;
					if (projAppInfoQuitModel != null) {
						if (projAppInfoQuitModel.getQuitCurrency() != null && projAppInfoQuitModel.getNowShare() != null) {
							Double nowShare = projAppInfoQuitModel.getNowShare() / 100;
							backMony = projAppInfoQuitModel.getQuitCurrency() * nowShare;
						}
					}
					//收回金额
					formMap.put("backMony", backMony);
					ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", processID, businessKey, authid);
					//"提交成功！"
					if (inst != null) {
						logger.info("##------->流程启动成功<--------##");
						//保存数据
						// 審批中
						projAppInfoQuitModel.setStatus("1");
						//更新时间
						projAppInfoQuitModel.setUpdateDt(new Date());
						//流程实例id
						projAppInfoQuitModel.setProcessInstId(inst.getId());
						projAppInfoQuitService.update(projAppInfoQuitModel);
					} else {
						logger.error("##------->流程启动失败<--------##");
						baseResponse.error("流程启动失败");
					}
				} else if (tag != null && "1".equals(tag)) {
					//保存数据
					// 草稿
					projAppInfoQuitModel.setStatus("0");
					projAppInfoQuitModel.setUpdateDt(new Date());
					projAppInfoQuitService.update(projAppInfoQuitModel);
				}else if (tag != null && "3".equals(tag)) {
					//保存数据
					projAppInfoQuitService.update(projAppInfoQuitModel);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);
	}
}

