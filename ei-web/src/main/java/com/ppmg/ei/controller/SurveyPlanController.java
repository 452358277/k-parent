package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.core.manage.interfaces.IWorkflowManager;
import com.founder.core.manage.local.WorkflowManager;
import com.founder.core.util.UimUtils;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.foundation.service.SerialnoService;
import com.founder.ssm.web.common.CommonStatus;
import com.founder.uim.xdk.AppGroup;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.common.utils.ProcessUserUtils;
import com.ppmg.ei.dto.SurveyPlanDTO;
import com.ppmg.ei.model.ProjInfoModel;
import com.ppmg.ei.model.SurverCooorgModel;
import com.ppmg.ei.model.SurveyPlanModel;
import com.ppmg.ei.model.SurveyPlanUsersModel;
import com.ppmg.ei.service.*;
import com.ppmg.ei.utils.OaConstants;
import com.ppmg.ei.vo.SurveyPlanVO;
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
import java.util.*;

@Controller
@Scope("prototype")
@Api(tags={"尽职调查计划接口"})
@RequestMapping("/surveyPlan")
public class SurveyPlanController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private SurveyPlanService surveyPlanService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Reference
	private SurveyPlanUsersService surveyPlanUsersService;

	@Reference
	private SurverCooorgService surverCooorgService;

	@Reference
	private FixflowRunTaskinstanceService fixflowRunTaskinstanceService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@Reference
	private ProjInfoService projInfoService;


	@ApiOperation(value="尽职调查计划列表", notes="尽职调查计划列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/urveyPlanList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String urveyPlanList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,String planId,String keyWord,Boolean type,String projId){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			if(StringUtils.isNotEmpty(planId)){
				searchCondition.addConditionEqualTo("PLAN_ID",planId);
			}
			if(StringUtils.isNotEmpty(projId)){
				searchCondition.addConditionEqualTo("PROJ_ID",projId);
			}
			if(keyWord!=null){
				if(StringUtils.isNotEmpty(keyWord.trim())){
					keyWord="%"+keyWord.trim()+"%";
					searchCondition.addParam("planName",keyWord);
				}
			}
			if(type!=null){
				if(type){
					//尽职调查下拉款
					searchCondition.addConditionEqualTo("STATUS","4");
				}else{
					searchCondition.addConditionNotEqualTo("STATUS","9");
				}
			}
			//searchCondition.addConditionEqualTo("create_by",this.getLoginUserId());
			PageInfo<SurveyPlanModel> rows = surveyPlanService.selectListPage(searchCondition);
			List<SurveyPlanVO> list = new ArrayList<SurveyPlanVO>();
			for(SurveyPlanModel model : rows.getList()){
				SurveyPlanVO vo = new SurveyPlanVO();
				BeanUtils.copyProperties(vo, model);
				String surveyContentName="";
				String  planUserNames=surveyPlanUsersService.selectByPlanUserName(model.getPlanId());
				vo.setPlanUserNames(planUserNames);
				if(StringUtils.isNotEmpty(model.getInterior())){
				String  interiorName=codeUtils.getCodeNameByValue("3434",model.getInterior());
					vo.setInteriorName(interiorName==null?"":interiorName);
				}
				if(StringUtils.isNotEmpty(model.getStatus())){
					String  statusName=codeUtils.getCodeNameByValue("264",model.getStatus());
					vo.setStatusName(statusName==null?"":statusName);
				}
				if(StringUtils.isNotEmpty(vo.getSurveyContent())){
					List<String> listS = Arrays.asList(vo.getSurveyContent().split(","));
					for(String surveyContent:listS){
						if(StringUtils.isNotEmpty(surveyContentName)){
							if("1".equals(surveyContent)){
								surveyContentName=surveyContentName+","+"财务";
							}
							if("2".equals(surveyContent)){
								surveyContentName=surveyContentName+","+"业务";
							}
							if("3".equals(surveyContent)){
								surveyContentName=surveyContentName+","+"法务";
							}
						}else{
							if("1".equals(surveyContent)){
								surveyContentName="财务";
							}
							if("2".equals(surveyContent)){
								surveyContentName="业务";
							}
							if("3".equals(surveyContent)){
								surveyContentName="法务";
							}
						}
					}
				}
				SurverCooorgModel surverCooorgModel=new SurverCooorgModel();
				surverCooorgModel.setPlanId(model.getPlanId());
			   List<SurverCooorgModel> listSurverCooorg=surverCooorgService.selectListById(surverCooorgModel);
				if(listSurverCooorg!=null&&listSurverCooorg.size()>0){
					for(SurverCooorgModel  surverCooorgVo:listSurverCooorg){
						if(surverCooorgVo.getCooOrgModel()!=null){
							if(StringUtils.isNotEmpty(surverCooorgVo.getCooOrgModel().getOrgType())){
								surverCooorgVo.getCooOrgModel().setOrgTypeName(codeUtils.getCodeNameByValue("237",surverCooorgVo.getCooOrgModel().getOrgType()));
							}

						}
					}
					vo.setListSurverCooorg(listSurverCooorg);
				}
				vo.setSurveyContentName(surveyContentName);
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

	@ApiOperation(value="校验计划名称已存在", notes="校验计划名称已存在")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "planName", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "planId", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/check/checkName", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String checkName(@RequestParam("planName")String  planName, String  planId,String projId){
		try {
          if(StringUtils.isNotEmpty(planName)){
			  if(StringUtils.isNotEmpty(planId)){
				//修改
				  SurveyPlanModel sur=surveyPlanService.selectById(planId);
				  if(sur!=null){
				  	if(!sur.getPlanName().equals(planName)){
					  SurveyPlanModel surveyPlanModel=new SurveyPlanModel();
					  surveyPlanModel.setPlanName(planName);
					  surveyPlanModel.setProjId(projId);
					  List<SurveyPlanModel> list= surveyPlanService.selectList(surveyPlanModel);
					  if(list!=null&&list.size()>0){
						  baseResponse.setMsg("计划名称已存在！");
						  return	JSONObject.toJSONString(baseResponse);
					  }

					}
				  }
			  }else {
              //新增
				  SurveyPlanModel surveyPlanModel=new SurveyPlanModel();
				  surveyPlanModel.setPlanName(planName);
				  surveyPlanModel.setProjId(projId);
				 List<SurveyPlanModel> list= surveyPlanService.selectList(surveyPlanModel);
				 if(list!=null&&list.size()>0){
					 baseResponse.setMsg("计划名称已存在！");
					 return	JSONObject.toJSONString(baseResponse);
				 }
			  }
		  }

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiOperation(value = "新增尽职调查计划", notes = "新增尽职调查计划")
	@ApiImplicitParam(name = "SurveyPlanDTO", value = "SurveyPlanDTO", required = true, dataType = "RequirementsDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/saveInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveInfo(@RequestBody SurveyPlanDTO dto) {

		try {
			SurveyPlanModel model = new SurveyPlanModel();
			BeanUtils.copyProperties(model, dto);
			model.setUpdateBy(this.getLoginUserId());
			model.setUpdateDt(new Date());
			if(StringUtils.isNotEmpty(dto.getPlanId())){
				//内部
				if("1".equals(dto.getInterior())){
					surveyPlanService.updateByUser(model,dto.getIds());
				}else{
					//外部,内+外
					//surveyPlanService.updateInfo(model,dto.getUserIds(),dto.getList());
					surveyPlanService.updateInfo(model,dto.getIds(),dto.getList());
				}
			}else{
				String id = serialnoService.getSequence("EI.TZ_T_SURVEY_PLAN");
				model.setCreateBy(this.getLoginUserId());
				model.setCreateDt(new Date());
				model.setUpdateDt(new Date());
				model.setUpdateBy(this.getLoginUserId());
				model.setPlanId(id);
				//前台传status="0";
				if("1".equals(dto.getInterior())){
                //内部
					surveyPlanService.insertUser(model,dto.getIds());
				}else{
					//外部或者内+外
					surveyPlanService.insertInfo(model,dto.getIds(),dto.getList());
				}


			}
			if(dto.getIsStartFlow()!=null){
				if(dto.getIsStartFlow()) {//如果点击是提交则添加流程
					startWorkFlow(model);
				}
			}


		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			baseResponse.error();
			logger.error(e.getMessage(), e);
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiOperation(value = "删除尽职调查计划", notes = "删除尽职调查计划")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids", value = "计划审核ID", required = true, dataType = "String"),
	})
	@GetMapping(value = "/plan/delete", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@RequestParam("ids") String ids) {

		try {
			String[] idsArray = ids.split(",");
			surveyPlanService.deleteByIds(Arrays.asList(idsArray), this.getLoginUserId());
		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			baseResponse.error();
			logger.error(e.getMessage(), e);
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiOperation(value="详情", notes="根据url的详情")
	@ApiImplicitParam(name = "id", value = "XXXID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/detainfo/{planId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detail(@PathVariable(value = "planId") String planId){
		try {
			SurveyPlanModel model=surveyPlanService.selectById(planId);
			SurveyPlanVO vo = new SurveyPlanVO();
			BeanUtils.copyProperties(vo, model);
			if(model!=null){
				if(StringUtils.isNotEmpty(model.getInterior())){
					String  interiorName=codeUtils.getCodeNameByValue("3434",model.getInterior());
					vo.setInteriorName(interiorName==null?"":interiorName);
				}
				if(StringUtils.isNotEmpty(model.getProcessInstId())){
					List<Map<String,Object>> listMap=  fixflowRunTaskinstanceService.getFixFlowByTaskId(model.getProcessInstId());
					if(listMap!=null&&listMap.size()>0){
						String taskId=listMap.get(0).get("TASKINSTANCE_ID").toString();
						vo.setTaskId(taskId);
					}else{
						vo.setTaskId("");
					}
				}else{
					    vo.setTaskId("");
				}

			}
			if(StringUtils.isNotEmpty(model.getSurveyContent())){
				String surveyContentName="";
				List<String> listS = Arrays.asList(model.getSurveyContent().split(","));
				for(String surveyContent:listS){
					if(StringUtils.isNotEmpty(surveyContentName)){
						if("1".equals(surveyContent)){
							surveyContentName=surveyContentName+","+"财务";
						}
						if("2".equals(surveyContent)){
							surveyContentName=surveyContentName+","+"业务";
						}
						if("3".equals(surveyContent)){
							surveyContentName=surveyContentName+","+"法务";
						}
					}else{
						if("1".equals(surveyContent)){
							surveyContentName="财务";
						}
						if("2".equals(surveyContent)){
							surveyContentName="业务";
						}
						if("3".equals(surveyContent)){
							surveyContentName="法务";
						}
					}

				}
				vo.setSurveyContentName(surveyContentName);
			}

			SurveyPlanUsersModel surveyPlanUsersModel=new SurveyPlanUsersModel();
			surveyPlanUsersModel.setPlanId(planId);
			List<SurveyPlanUsersModel> list= surveyPlanUsersService.selectList(surveyPlanUsersModel);
			vo.setList(list);
			//查询人员
			String  planUserNames=surveyPlanUsersService.selectByPlanUserName(planId);
			vo.setPlanUserNames(planUserNames);
			SurverCooorgModel surverCooorgModel=new SurverCooorgModel();
			surverCooorgModel.setPlanId(model.getPlanId());
			List<SurverCooorgModel> listSurverCooorg=surverCooorgService.selectListById(surverCooorgModel);
			for(SurverCooorgModel  surverCooorgVo:listSurverCooorg){
				if(surverCooorgVo.getCooOrgModel()!=null){
					surverCooorgVo.getCooOrgModel().setOrgTypeName(codeUtils.getCodeNameByValue("237",surverCooorgVo.getCooOrgModel().getOrgType()));
				}

			}
			if(listSurverCooorg!=null&&listSurverCooorg.size()>0){
				vo.setListSurverCooorg(listSurverCooorg);
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

	@ApiIgnore
	@ApiOperation(value="尽职调查计划修改/删除", notes="尽职调查计划修改、删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "planId", value = "planId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "SurveyPlanVO", value = "SurveyPlanVO", required = true, dataType = "SurveyPlanVO")
	})
	@PostMapping(value = "/update/{planId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update(@PathVariable("planId") String planId, @RequestBody SurveyPlanVO dto){
		try {
			SurveyPlanModel model = new SurveyPlanModel();
			BeanUtils.copyProperties(model, dto);
			model.setPlanId(planId);
			model.setUpdateBy(this.getLoginUserId());
			model.setUpdateDt(new Date());
			surveyPlanService.update(model);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	private void startWorkFlow(SurveyPlanModel model) {
		IWorkflowManager wm = new WorkflowManager();
		String fileName = "ei_urveyPlan";
		if(StringUtils.isNotEmpty(model.getProjId())){
			ProjInfoModel	projInfoModel=projInfoService.selectById(model.getProjId());
			if(projInfoModel!=null&&"1".equals(projInfoModel.getProjType())){
				fileName = "ei_urveyPlan_pro";
			}
		}
		String currUserId = this.getLoginUserId();
		String id = model.getPlanId();
		String taskTitle ="尽职调查计划:"+ model.getPlanName() ;
		if (wm.isEnd(id, fileName, currUserId)) {
			Map<String, Object> formMap = new HashMap<>();
			formMap.put("taskTitle", taskTitle);
			formMap.put("businessKey", id);
			AppGroup dept = UimUtils.getDept(Long.parseLong(currUserId));
			//部门负责人
			String departmentManager = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(dept.getId(), OaConstants.DEPARTMENT_MANAGER_POST_ID);
			formMap.put("departmentManager", departmentManager);
			//分管副总
			String divisionVicePresident = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(dept.getId(), OaConstants.DIVISION_VICE_PRESIDENT_POST_ID);
			formMap.put("divisionVicePresident", divisionVicePresident);
			//发起人
			formMap.put("startUser", currUserId);

			ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", fileName, id, currUserId);
			if (inst != null) {
				//"提交成功！"
				model.setProcessInstId(inst.getId());
				model.setStatus("1");
				surveyPlanService.update(model);
			} else {
				throw new SystemException("流程启动失败，请确认下一节点审批人是否存在");
			}
		}
	}



}

