package com.ppmg.ei.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.founder.core.manage.interfaces.IWorkflowManager;
import com.founder.core.manage.local.WorkflowManager;
import com.founder.core.util.StringUtils;
import com.founder.core.util.UimUtils;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.uim.xdk.AppGroup;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.common.utils.ProcessUserUtils;
import com.ppmg.ei.dto.ProjAppInfoDTO;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.utils.OaConstants;
import com.ppmg.ei.vo.ProjAppVO;
import com.ppmg.ei.vo.ProjResultDetailVO;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

@Controller
@Scope("prototype")
@Api(tags={"决策备份"})
@RequestMapping("/proj")
public class ProjAppInfoSnapshotDController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private ProjAppInfoSnapshotDService projAppInfoSnapshotDService;

	@Reference
	private ProjAppInfoService projAppInfoService;

	@Reference
	private FundInfoService fundInfoService;

	@Reference
	private ProjInfoService projInfoService;

	@Reference
	private BdTFitRegulationSelfService bdTFitRegulationSelfService;

	@Reference
	private  FixflowRunTaskinstanceService fixflowRunTaskinstanceService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@ApiOperation(value="投管流程-查看项目信息详情", notes="投管流程-查看项目信息详情")
	@ApiImplicitParam(name = "projId", value = "出资人ID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/projInfo/internalDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String internalDetail(@RequestParam String projId,String inveId){
		try {
			ProjAppInfoSnapshotDModel model = projAppInfoSnapshotDService.selectById(projId);
			if(model!=null){
				if(StringUtils.isNotEmpty(model.getProcessInstId())){
					List<Map<String,Object>> listMap=  fixflowRunTaskinstanceService.getFixFlowByTaskId(model.getProcessInstId());
					if(listMap!=null&&listMap.size()>0){
						String taskId=listMap.get(0).get("TASKINSTANCE_ID").toString();
						mapResponse.put("taskId", taskId);
					}else{
						mapResponse.put("taskId", "");
					}
				}else{
					mapResponse.put("taskId", "");
				}
              //备份表已存在
				mapResponse.put("model", model);
				if(StringUtils.isNotEmpty(model.getInveRole())){
					String inveRoleName=codeUtils.getCodeNameByValue("215", model.getInveRole());
					mapResponse.put("inveRoleName", inveRoleName);
				}
				if(StringUtils.isNotEmpty(model.getInveType())){
					String inveTypeName=codeUtils.getCodeNameByValue("234", model.getInveType());
					mapResponse.put("inveTypeName", inveTypeName);
				}
				if(StringUtils.isNotEmpty(model.getPreCurrency())){
					String preCurrency=codeUtils.getCodeNameByValue("103", model.getPreCurrency());
					mapResponse.put("preCurrencyName", preCurrency);
				}
				if(StringUtils.isNotEmpty(model.getFinaCurrency())){
					String finaCurrency=codeUtils.getCodeNameByValue("103", model.getFinaCurrency());
					mapResponse.put("finaCurrencyName", finaCurrency);
				}
				if(StringUtils.isNotEmpty(model.getPostCurrency())){
					String postCurrencyName=codeUtils.getCodeNameByValue("103", model.getPostCurrency());
					mapResponse.put("postCurrencyName", postCurrencyName);
				}
				if(StringUtils.isNotEmpty(model.getFinaRounds())){
					String finaRoundsName=codeUtils.getCodeNameByValue("213", model.getFinaRounds());
					mapResponse.put("finaRoundsName", finaRoundsName);
				}
				if(StringUtils.isNotEmpty(model.getFinaRounds())){
					String finaRoundsName=codeUtils.getCodeNameByValue("213", model.getFinaRounds());
					mapResponse.put("finaRoundsName", finaRoundsName);
				}
				if(StringUtils.isNotEmpty(model.getIntendedCurrency())){
					String intendedCurrencyName=codeUtils.getCodeNameByValue("103", model.getIntendedCurrency());
					mapResponse.put("intendedCurrencyName", intendedCurrencyName);
				}
				if(StringUtils.isNotEmpty(model.getPreCurrency())){
					String preCurrency=codeUtils.getCodeNameByValue("103", model.getPreCurrency());
					mapResponse.put("preCurrencyName", preCurrency);
				}
			}else{
				mapResponse.put("taskId", "");
			    ProjAppInfoModel appInfo=projAppInfoService.selectById(projId);
				mapResponse.put("model", appInfo);
				if(appInfo!=null){

					if(StringUtils.isNotEmpty(appInfo.getInveRole())){
						String inveRoleName=codeUtils.getCodeNameByValue("215", appInfo.getInveRole());
						mapResponse.put("inveRoleName", inveRoleName);
					}
					if(StringUtils.isNotEmpty(appInfo.getInveType())){
						String inveTypeName=codeUtils.getCodeNameByValue("234", appInfo.getInveType());
						mapResponse.put("inveTypeName", inveTypeName);
					}
					if(StringUtils.isNotEmpty(appInfo.getPreCurrency())){
						String preCurrency=codeUtils.getCodeNameByValue("103", appInfo.getPreCurrency());
						mapResponse.put("preCurrencyName", preCurrency);
					}
					if(StringUtils.isNotEmpty(appInfo.getFinaCurrency())){
						String finaCurrency=codeUtils.getCodeNameByValue("103", appInfo.getFinaCurrency());
						mapResponse.put("finaCurrencyName", finaCurrency);
					}
					if(StringUtils.isNotEmpty(appInfo.getPostCurrency())){
						String postCurrencyName=codeUtils.getCodeNameByValue("103", appInfo.getPostCurrency());
						mapResponse.put("postCurrencyName", postCurrencyName);
					}
					if(StringUtils.isNotEmpty(appInfo.getFinaRounds())){
						String finaRoundsName=codeUtils.getCodeNameByValue("213", appInfo.getFinaRounds());
						mapResponse.put("finaRoundsName", finaRoundsName);
					}
					if(StringUtils.isNotEmpty(appInfo.getIntendedCurrency())){
						String intendedCurrencyName=codeUtils.getCodeNameByValue("103", appInfo.getIntendedCurrency());
						mapResponse.put("intendedCurrencyName", intendedCurrencyName);
					}
				}

			}
			ProjInfoModel projInfoModel=projInfoService.selectById(projId);
			mapResponse.put("projInfoModel", projInfoModel);
            if(projInfoModel!=null){
				mapResponse.put("isMeeting", projInfoModel.getIsMeeting());
				mapResponse.put("groupId", projInfoModel.getGroupId());
			}else{
				mapResponse.put("isMeeting", "");
				mapResponse.put("isMeeting", "");
			}

			BdTFitRegulationSelfModel  bdTFitRegulationSelf=bdTFitRegulationSelfService.selectById(projId);
            if(bdTFitRegulationSelf!=null){
				mapResponse.put("isCorr", bdTFitRegulationSelf.getIsCorr());
			}else{
				mapResponse.put("isCorr", "");
			}
			if(StringUtils.isNotEmpty(projInfoModel.getInveId())){
				FundInfoModel fundInfoModel=fundInfoService.selectById(projInfoModel.getInveId());
				mapResponse.put("fundInfoModel", fundInfoModel);
			}

		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataResponse.error(e.getMessage());
		} catch (Exception e) {
			dataResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);
	}

	@ApiIgnore
	@ApiOperation(value="项目投资决策", notes="项目投资决策")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "planId", value = "planId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "SurveyPlanVO", value = "SurveyPlanVO", required = true, dataType = "SurveyPlanVO")
	})
	@PostMapping(value = "/appInfo/saveAppInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveAppInfo(@RequestBody ProjAppInfoDTO dto){
		try {
			ProjAppInfoSnapshotDModel model = new ProjAppInfoSnapshotDModel();
			BeanUtils.copyProperties(model, dto);

			ProjAppInfoModel projAppInfo=new ProjAppInfoModel();
			BeanUtils.copyProperties(projAppInfo, dto);
			projAppInfo.setUpdateBy(this.getLoginUserId());

			BdTFitRegulationSelfModel  bdTFitRegulationSelf=new BdTFitRegulationSelfModel();
			bdTFitRegulationSelf.setProjId(dto.getProjId());
			bdTFitRegulationSelf.setIsCorr(dto.getIsCorr());

			ProjInfoModel projInfoModel=new ProjInfoModel();
			BeanUtils.copyProperties(projInfoModel, dto);

			FundInfoModel fundInfoModel=new FundInfoModel();
			BeanUtils.copyProperties(fundInfoModel, dto);
			fundInfoModel.setFundId(dto.getInveId());
			//判断出资主体是否修改
			if(StringUtils.isNotEmpty(dto.getProjId())){
				ProjInfoModel projInfo1=projInfoService.selectById(dto.getProjId());
				if(projInfo1!=null&&StringUtils.isNotEmpty(projInfo1.getInveId())){
					if(!projInfo1.getInveId().equals(dto.getInveId())){
						String code=getProjACode(dto.getInveId());
						projInfoModel.setProjNo(code);
					}
				}
			}
			projAppInfoSnapshotDService.updateByAappInfo(projAppInfo,fundInfoModel,model,projInfoModel,bdTFitRegulationSelf);
			if(dto.getIsStartFlow()) {//如果点击是提交则添加流程
				startWorkFlow(projAppInfo,model,dto.getProjName(),dto.getInveId());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	private void startWorkFlow( ProjAppInfoModel projAppInfo,ProjAppInfoSnapshotDModel model,String projName,String inveId) {
		IWorkflowManager wm = new WorkflowManager();
		String fileName = "ei_proj_decision";
		String currUserId = this.getLoginUserId();
		String id = projAppInfo.getProjId();
		String taskTitle = "投资决策："+projName;
		if (wm.isEnd(id, fileName, currUserId)) {
			Map<String, Object> formMap = new HashMap<>();
			formMap.put("inveId", inveId);
			formMap.put("taskTitle", taskTitle);
			formMap.put("businessKey", id);
			AppGroup dept = UimUtils.getDept(Long.parseLong(currUserId));
			//部门经理
			String departmentManager = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(dept.getId(), OaConstants.DEPARTMENT_MANAGER_POST_ID);
			formMap.put("departmentManager", departmentManager);

			//分控法务10009
			List<String> riskManager = UimUtils.getUserIdByRoleId(10009L);
			String riskManagers = "";
			for (String s : riskManager) {
				riskManagers += s + ",";
			}
			if (riskManagers.length() > 0) {
				riskManagers = riskManagers.substring(0, riskManagers.length() - 1);
			}
			formMap.put("riskManagers", riskManagers);

			//发起人的分管副总和分控法务副总
			String divisionVicePresidents ="";
			 divisionVicePresidents = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(dept.getId(), OaConstants.DIVISION_VICE_PRESIDENT_POST_ID);
			if(riskManager!=null&&riskManager.size()>0){
				AppGroup dept1 = UimUtils.getDept(Long.parseLong(riskManager.get(0)));
				String divisionVicePresidents1 = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(dept1.getId(), 103);
			     if(StringUtils.isNotEmpty(divisionVicePresidents1)){
					 divisionVicePresidents=divisionVicePresidents+","+divisionVicePresidents1;
				 }
			}
			formMap.put("ResultDivisionVicePresidents", divisionVicePresidents);
			//项目经理10010
			List<String> projectManagerList = UimUtils.getUserIdByRoleId(10011L);
			String projectManager = "";
			for (String s : projectManagerList) {
				projectManager += s + ",";
			}
			if (projectManager.length() > 0) {
				projectManager = projectManager.substring(0, projectManager.length() - 1);
			}
			//项目经理
			formMap.put("projectManager", projectManager);
			//发起人
			formMap.put("startUser", currUserId);
			//总经理办公会
			List<String> list = UimUtils.getUserIdByRoleId(10006L);
			String users = "";
			for (String s : list) {
				users += s + ",";
			}
			if (users.length() > 0) {
				users = users.substring(0, users.length() - 1);
			}
			formMap.put("managerUsers", users);

			//办公室录入员
			//String officeUser = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(dept.getId(), 110);
			List<String> listOfficeUser = UimUtils.getUserIdByRoleId(10020L);
			String officeUser = "";
			for (String s : listOfficeUser) {
				officeUser += s + ",";
			}
			if (officeUser.length() > 0) {
				officeUser = officeUser.substring(0, officeUser.length() - 1);
			}
			formMap.put("officeUser", officeUser);

			ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", fileName, id, currUserId);
			if (inst != null) {
				//"提交成功！"
				projAppInfo.setProcessInstId(inst.getId());
				projAppInfoService.update(projAppInfo);
				model.setProcessInstId(inst.getId());
				projAppInfoSnapshotDService.update(model);
			} else {
				throw new SystemException("流程启动失败，请确认下一节点审批人是否存在");
			}
		}
	}


	/**获取项目编号 **/
	public String getProjACode(String inveId) {
		try {
			FundInfoModel fund= fundInfoService.selectById(inveId);
			String code="";
			if(fund!=null){
				String fundCode=fund.getFundCode();
				SimpleDateFormat sim = new SimpleDateFormat("yyyy");
				String time = sim.format(new Date());
				String proCode=fundCode+time;
				List<ProjInfoModel> projInfoList= projInfoService.getNewProCode("%"+proCode+"%");
				if(projInfoList!=null&&projInfoList.size()>0){
					String proj=projInfoList.get(0).getProjNo();
					Integer len = Integer.parseInt(proj.replaceFirst(proCode,"" ));
					if(len <= 8) {
						code=proCode + "000" + String.valueOf((len + 1));
					}else if(len <= 98) {
						code=proCode + "00" + String.valueOf((len + 1));
					}else if(len <= 998) {
						code=proCode + "0" + String.valueOf((len + 1));
					}else{
						code=proCode+ String.valueOf((len + 1));
					}
				}else{
					code=proCode+"0001";
				}
			}
			return code;

		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataResponse.error(e.getMessage());
		} catch (Exception e) {
			dataResponse.error();
			logger.error(e.getMessage(), e);
		}
		return "";
	}

}

