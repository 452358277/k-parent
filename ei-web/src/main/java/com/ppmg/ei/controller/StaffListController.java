package com.ppmg.ei.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.founder.core.manage.interfaces.IWorkflowManager;
import com.founder.core.manage.local.WorkflowManager;
import com.founder.core.util.UimUtils;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.ssm.foundation.service.SerialnoService;
import com.founder.uim.xdk.AppGroup;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.common.utils.ProcessUserUtils;
import com.ppmg.ei.dto.ApplyIssuedDocDTO;
import com.ppmg.ei.dto.FundInfoDTO;
import com.ppmg.ei.dto.StaffListDTO;
import com.ppmg.ei.dto.SurveyPlanDTO;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.utils.OaConstants;
import com.ppmg.ei.vo.StaffListVO;
import com.ppmg.ei.vo.SurveyPlanVO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(tags={"??????????????????"})
@RequestMapping("/staffList")
public class StaffListController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private StaffListService staffListService;

	@Reference
	private ProjAssignService projAssignService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Resource(name = "codeUtils")
	private CodeUtils codeUtils;

	@Reference
	private ProjInfoService projInfoService;

	@Reference
	private ApplyIssuedDocService applyIssuedDocService;

	@Reference
	private ApplyInfoService applyInfoService;

	@Reference
	private AppUserService appUserService;

	@Reference
	private FixflowRunTaskinstanceService fixflowRunTaskinstanceService;


	@ApiOperation(value = "??????????????????", notes = "??????????????????")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "????????????", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "????????????", required = true, dataType = "int")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "????????????"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "?????????????????????"),
	})
	@GetMapping(value = "/staffListList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String staffListList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, String projId) {
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			if (StringUtils.isNotEmpty(projId)) {
				searchCondition.addConditionEqualTo("PROJ_ID", projId);
			} else {
				searchCondition.addConditionEqualTo("create_by", this.getLoginUserId());
			}
			searchCondition.addConditionNotEqualTo("STATUS", "9");
			PageInfo<StaffListModel> rows = staffListService.selectListPage(searchCondition);
			List<StaffListVO> list = new ArrayList<StaffListVO>();
			for (StaffListModel model : rows.getList()) {
				StaffListVO vo = new StaffListVO();
				BeanUtils.copyProperties(vo, model);
				list.add(vo);
				ProjAssignModel projAssignModel = projAssignService.selectById(model.getAssignId());
				if (projAssignModel != null) {
					vo.setType(projAssignModel.getStatus());
					if (StringUtils.isNotEmpty(projAssignModel.getStatus())) {
						String statusName = codeUtils.getCodeNameByValue("264", projAssignModel.getStatus());
						vo.setStatusName(statusName == null ? "" : statusName);
					}
				}

				if (StringUtils.isNotEmpty(model.getUserType())) {
					vo.setUserTypeName(codeUtils.getCodeNameByValue("1017", model.getUserType()));
				}
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

	@ApiOperation(value = "??????????????????", notes = "??????????????????")
	@ApiImplicitParam(name = "SurveyPlanDTO", value = "SurveyPlanDTO", required = true, dataType = "RequirementsDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "????????????"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "?????????????????????"),
	})
	@PostMapping(value = "/saveInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveInfo(@RequestBody StaffListDTO dto) {

		try {
			StaffListModel model = new StaffListModel();
			String userId = this.getLoginUserId();
			BeanUtils.copyProperties(model, dto);
			ProjAssignModel projAssignModel = new ProjAssignModel();
			BeanUtils.copyProperties(projAssignModel, dto);
			model.setUpdateBy(userId);
			model.setUpdateDt(new Date());
			//????????????????????????????????????
			String projId = dto.getProjId();
			HashSet<String> setOnly = new HashSet<String>();
			if (dto.getList() != null && dto.getList().size() > 1) {
				for (StaffListModel staffs : dto.getList()) {
					String value = staffs.getUserId();
					//String value = staffs.getUserId() + staffs.getUserType();
					System.out.println("-----??????----" + staffs.getUserId() + "--" + staffs.getUserType());
					if (setOnly.contains(value)) {
						// ????????????
						if("4".equals(dto.getGroupId())){
							baseResponse.setMsg("???????????????????????????");
						}else{
							baseResponse.setMsg("????????????????????????");
						}

						return JSONObject.toJSONString(baseResponse);
					}else {
						setOnly.add(value);
					}
				}
			}
			if (StringUtils.isNotEmpty(dto.getAssignId())) {
				//??????
				if (dto.getList() != null && dto.getList().size() > 0) {
					for (StaffListModel staffListModel : dto.getList()) {
						StaffListModel staff = new StaffListModel();
						staff.setUserId(staffListModel.getUserId());
						//??????????????????????????????
						List<StaffListModel> listS = staffListService.selectListByUid(staff);
						if (listS != null && listS.size() > 0) {
							for (StaffListModel StaffList : listS ) {
								String status = StaffList.getStatus();
								System.out.println("---------ceshigx----" + status + "--projId--???" + projId + "==" + StaffList.getProjId());
								if(projId.equals(StaffList.getProjId())){
									//0:?????????3???????????????
									if (!"0".equals(status) && !"3".equals(status)) {
										// ????????????
										if("4".equals(dto.getGroupId())){
											baseResponse.setMsg("???????????????????????????"+ StaffList.getUserName());
										}else{
											baseResponse.setMsg("??????????????????" + StaffList.getUserName());
										}

										return JSONObject.toJSONString(baseResponse);
									}
									System.out.println("---------ceshi2----" + staffListModel + "--" + StaffList);
									if (staffListModel != null && staffListModel.getAssignId() != null && StaffList != null && StaffList.getAssignId() != null) {
										System.out.println("---------ceshi3----staffListModel-->" + staffListModel.getAssignId() + ";-StaffList->" + StaffList.getAssignId());
										if (("0".equals(status) || "3".equals(status)) && !staffListModel.getAssignId().equals(StaffList.getAssignId())) {
											if("4".equals(dto.getGroupId())){
												baseResponse.setMsg("???????????????????????????"+ StaffList.getUserName());
											}else{
												baseResponse.setMsg("??????????????????" + StaffList.getUserName());
											}
											return JSONObject.toJSONString(baseResponse);
										}
									}
								}
							}
						}
					}
				}
				staffListService.updateAssAndStaffList(dto.getList(), projAssignModel, userId);
			} else {
				if (dto.getList() != null && dto.getList().size() > 0) {
					for (StaffListModel staffListModel : dto.getList()) {
						StaffListModel staff = new StaffListModel();
						staff.setUserId(staffListModel.getUserId());
						//??????????????????????????????
						List<StaffListModel> listS = staffListService.selectListByUid(staff);
						if (listS != null && listS.size() > 0) {
							for (StaffListModel StaffList : listS ) {
								System.out.println("---------ceshi-zj----projId--???" + projId + "==" + StaffList.getProjId());
								if(projId.equals(StaffList.getProjId())){
									baseResponse.setMsg("??????????????????" + StaffList.getUserName());
									return JSONObject.toJSONString(baseResponse);
								}
							}
						}
					}
				}
				//String pkId = serialnoService.getSequence("EI.TZ_T_YH_STAFF_LIST");
				String assignId = serialnoService.getSequence("EI.TZ_T_PROJ_ASSIGN");
				projAssignModel.setAssignId(assignId);
				staffListService.insertAssAndStaffList(dto.getList(), projAssignModel, userId);
			}

			if (dto.getIsStartFlow()) {//????????????????????????????????????
				startWorkFlow(projAssignModel);
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

	@ApiIgnore
	@ApiOperation(value = "??????/??????/??????", notes = "??????/??????")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "assignId", value = "assignId", required = true, dataType = "String", paramType = "path"),
	})
	@GetMapping(value = "/updateStatus/{assignId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update(@PathVariable(value = "assignId") String assignId) {
		try {
			staffListService.delateById(assignId, this.getLoginUserId());
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);

	}


	@ApiOperation(value = "??????", notes = "??????url?????????")
	@ApiImplicitParam(name = "id", value = "XXXID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "????????????"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "?????????????????????"),
	})
	@GetMapping(value = "/detail/{assignId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detail(@PathVariable(value = "assignId") String assignId) {
		try {
			StaffListModel staffListModel = new StaffListModel();
			staffListModel.setAssignId(assignId);
			List<StaffListVO> list = new ArrayList<StaffListVO>();
			List<StaffListModel> listVO = staffListService.selectList(staffListModel);
			if (listVO != null && listVO.size() > 0) {
				for (StaffListModel model : listVO) {
					StaffListVO vo = new StaffListVO();
					BeanUtils.copyProperties(vo, model);
					ProjAssignModel projAssignModel = projAssignService.selectById(model.getAssignId());
					if(projAssignModel!=null){
					if(StringUtils.isEmpty(projAssignModel.getApplyNo())){
						if(StringUtils.isNotEmpty(projAssignModel.getProcessInstId())){
							List<Map<String,Object>> listMap=  fixflowRunTaskinstanceService.getFixFlowByTaskId(projAssignModel.getProcessInstId());
							if(listMap!=null&&listMap.size()>0){
								String taskId=listMap.get(0).get("TASKINSTANCE_ID").toString();
								vo.setTaskId(taskId);
							}else{
								vo.setTaskId("");
							}
							vo.setProcessInstId(projAssignModel.getProcessInstId());

						}else{
							vo.setTaskId("");
						}
					}else{
							ApplyInfoModel applyModel=applyInfoService.selectById(projAssignModel.getApplyNo());
							if(applyModel!=null){
								List<Map<String,Object>> listMap=  fixflowRunTaskinstanceService.getFixFlowByTaskId(applyModel.getProcessInstId());
								if(listMap!=null&&listMap.size()>0){
									String taskId=listMap.get(0).get("TASKINSTANCE_ID").toString();
									vo.setTaskId(taskId);
								}else{
									vo.setTaskId("");
								}
								vo.setProcessInstId(applyModel.getProcessInstId());
							}


					}

					}
					if (StringUtils.isNotEmpty(model.getProjId())) {
						ProjInfoModel projInfoModel = projInfoService.selectInfoProj(model.getProjId());
						if (projInfoModel != null && StringUtils.isNotEmpty(projInfoModel.getProjStatus())) {
							vo.setProjStatusName(codeUtils.getCodeNameByValue("218", projInfoModel.getProjStatus()));
						}
						vo.setProjInfoModel(projInfoModel);
					}
					if (StringUtils.isNotEmpty(model.getUserType())) {
						vo.setUserTypeName(codeUtils.getCodeNameByValue("1017", model.getUserType()));
					}

					list.add(vo);
				}
			}
			   ProjAssignModel projAssignModel=projAssignService.selectById(assignId);
			  if(projAssignModel!=null&&StringUtils.isNotEmpty(projAssignModel.getApplyNo())){
				ApplyIssuedDocModel applyIssuedDocModel=applyIssuedDocService.selectById(projAssignModel.getApplyNo());
				if(applyIssuedDocModel!=null){
					if(StringUtils.isNotEmpty(applyIssuedDocModel.getIssuedLevel())){
						mapResponse.put("issuedLevelName", codeUtils.getCodeNameByValue("237", applyIssuedDocModel.getIssuedLevel()));
					}else{
						mapResponse.put("issuedLevelName","");
					}
					if(StringUtils.isNotEmpty(applyIssuedDocModel.getIssueDocumentClass())){
						mapResponse.put("issueDocumentClassName", codeUtils.getCodeNameByValue("11009", applyIssuedDocModel.getIssueDocumentClass()));
					}else{
						mapResponse.put("issueDocumentClassName","");
					}
					if(StringUtils.isNotEmpty(applyIssuedDocModel.getUrgencyDegree())){
						mapResponse.put("urgencyDegreeName", codeUtils.getCodeNameByValue("11007", applyIssuedDocModel.getUrgencyDegree()));
					}else{
						mapResponse.put("urgencyDegreeName","");
					}
					if(StringUtils.isNotEmpty(applyIssuedDocModel.getIssueDocumentType())){
						mapResponse.put("issueDocumentTypeName", codeUtils.getCodeNameByValue("11008", applyIssuedDocModel.getIssueDocumentType()));
					}else{
						mapResponse.put("issueDocumentTypeName","");
					}

					mapResponse.put("applyIssuedDocModel", applyIssuedDocModel);
				}else{
					ApplyIssuedDocModel applyIssuedDocMo=new ApplyIssuedDocModel();
					mapResponse.put("applyIssuedDocModel", applyIssuedDocModel);
					mapResponse.put("issuedLevelName","");
					mapResponse.put("issueDocumentClassName","");
					mapResponse.put("urgencyDegreeName","");
					mapResponse.put("issueDocumentTypeName","");
				}
			    ApplyInfoModel	applyInfoModel=applyInfoService.selectById(projAssignModel.getApplyNo());
				if(applyInfoModel!=null){
					mapResponse.put("applyInfoModel", applyInfoModel);
					AppGroup dept = UimUtils.getDept(applyInfoModel.getApplicantId());
					AppGroup curOrg = UimUtils.getOrg(applyInfoModel.getApplicantId());//??????????????????
					mapResponse.put("dept", dept);
					mapResponse.put("curOrg", curOrg);
				}else{
					ApplyInfoModel	applyInfoM=new ApplyInfoModel();
					mapResponse.put("applyInfoModel", applyInfoM);
				}

			}
			mapResponse.put("listVO", list);
			mapResponse.put("assignId", assignId);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataResponse.error(e.getMessage());
		} catch (Exception e) {
			dataResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}


	private void startWorkFlow(ProjAssignModel model) {
		IWorkflowManager wm = new WorkflowManager();
		//???????????????????????????????????????
		String fileName = "";
		Long groupId=null;
		if(StringUtils.isNotEmpty(model.getProjId())){
          ProjInfoModel proj=  projInfoService.selectById(model.getProjId());
          if(proj!=null){
              groupId=proj.getGroupId();
          }
        }

		if(4==groupId){
			fileName = "MC_delegate";
		}else{
			 fileName = "ei_professional_Staffing";
			if(StringUtils.isNotEmpty(model.getProjId())){
				ProjInfoModel	projInfoModel=projInfoService.selectById(model.getProjId());
				if(projInfoModel!=null&&"1".equals(projInfoModel.getProjType())){
					fileName = "ei_professional_Staffing_pro";
				}
			}

		}
		String currUserId = this.getLoginUserId();
		String id = model.getAssignId();
		String projName = "";
		if (StringUtils.isNotEmpty(model.getProjId())) {
			ProjInfoModel pro = projInfoService.selectById(model.getProjId());
			if (pro != null) {
				projName = pro.getProjName();
			}

		}
		String taskTitle ="";
		//todo????????????
		if("4".equals(model.getGroupId())){
			 taskTitle = "??????????????????" + projName;
		}else{
			 taskTitle = "???????????????" + projName;
		}

		if (wm.isEnd(id, fileName, currUserId)) {
			Map<String, Object> formMap = new HashMap<>();
			formMap.put("taskTitle", taskTitle);
			formMap.put("businessKey", id);
			formMap.put("groupId",model.getGroupId());
			AppGroup dept = UimUtils.getDept(Long.parseLong(currUserId));
			//????????????
			String departmentManager = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(dept.getId(), OaConstants.DEPARTMENT_MANAGER_POST_ID);
			formMap.put("departmentManager", departmentManager);
			//????????????
			String divisionVicePresident = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(dept.getId(), OaConstants.DIVISION_VICE_PRESIDENT_POST_ID);
			formMap.put("divisionVicePresident", divisionVicePresident);
			//?????????????????? ???????????????
			List<String> managerOfficeList = UimUtils.getUserIdByRoleId(10020L);
			String managerOffice = "";
			for (String s : managerOfficeList) {
				managerOffice += s + ",";
			}
			if (managerOffice.length() > 0) {
				managerOffice = managerOffice.substring(0, managerOffice.length() - 1);
			}

			formMap.put("managerOffice", managerOffice);

			//???????????????10012
			List<String> printingAdminList = UimUtils.getUserIdByRoleId(10012L);
			String printingAdmin = "";
			for (String s : printingAdminList) {
				printingAdmin += s + ",";
			}
			if (printingAdmin.length() > 0) {
				printingAdmin = printingAdmin.substring(0, printingAdmin.length() - 1);
			}
			//???????????????
			formMap.put("printingAdmin", printingAdmin);

			//?????????
			formMap.put("startUser", currUserId);

			ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", fileName, id, currUserId);
			if (inst != null) {
				//"???????????????"
				model.setProcessInstId(inst.getId());
				model.setStatus("1");
				projAssignService.update(model);
			} else {
				throw new SystemException("???????????????????????????????????????????????????????????????");
			}
		}
	}


	@ApiOperation(value = "???????????????????????????????????????", notes = "???????????????????????????????????????")
	@ApiImplicitParam(name = "projId", value = "pqId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "????????????"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "?????????????????????"),
	})
	@PostMapping(value = "/request/getstaffNo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getstaffNo(@RequestBody ProjAssignModel model) {
		try {
			//????????????????????????????????????
			//????????????
			if(StringUtils.isEmpty(model.getProjUser())){
				baseResponse.setMsg("??????????????????");
				return JSONObject.toJSONString(baseResponse);
			}
			AppGroup dept = UimUtils.getDept(Long.parseLong(model.getProjUser()));
			AppGroup curOrg = UimUtils.getOrg(Long.parseLong(model.getProjUser()));//??????????????????
			//??????????????????
			String no = "RSZT";
			projAssignService.updateByNo(no);
			String appNo = "";
			Integer appNum = projAssignService.selectOneNo(no);
			SimpleDateFormat sim = new SimpleDateFormat("yyyy");
			String time = sim.format(new Date());
			if(appNum<10){
				appNo = no + time +"0"+ appNum;
			}else{
				appNo = no + time + appNum;
			}

			mapResponse.put("model", model);
			//??????
			mapResponse.put("dept", dept);
			mapResponse.put("appNo", appNo);
			mapResponse.put("curOrg", curOrg);
			mapResponse.put("PROCESS_STATUS","??????");
		    AppUserModel appu=	appUserService.selectById(model.getProjUser());
			if(appu!=null){
				mapResponse.put("APPLICANT_NAME",appu.getName());
			}
			mapResponse.put("APPLICANT_ID",model.getProjUser());
			SimpleDateFormat simDate = new SimpleDateFormat("yyyy-MM-dd");
			String sim111 = simDate.format(new Date());
			mapResponse.put("APPLY_TIME",sim111);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			mapResponse.error(e.getMessage());
		} catch (Exception e) {
			mapResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);
	}

	@ApiIgnore
	@ApiOperation(value = "???????????????", notes = "???????????????")
	@ApiImplicitParam(name = "ApplyIssuedDocDTO", value = "ApplyIssuedDocDTO", required = true, dataType = "String", paramType = "path")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "????????????"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "?????????????????????"),
	})
	@PostMapping(value = "/staff/startWork", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String startWork(@RequestBody ApplyIssuedDocDTO dto) {
		try {
			//??????????????????????????????
			IWorkflowManager wm = new WorkflowManager();
			String fileName = "sendDocAssProcess";
			String currUserId = dto.getProjUser();
			if(StringUtils.isEmpty(dto.getApplyNo())){
				baseResponse.setMsg("??????????????????");
				return JSONObject.toJSONString(baseResponse);
			}

			ApplyIssuedDocModel applyIssuedDoc=new ApplyIssuedDocModel();
			BeanUtils.copyProperties(applyIssuedDoc, dto);
			ApplyInfoModel applyInfoModel=new ApplyInfoModel();
			BeanUtils.copyProperties(applyInfoModel, dto);
			applyIssuedDoc.setCreateBy(currUserId);
			applyIssuedDocService.insertApply(applyIssuedDoc,applyInfoModel,dto.getAssignId());

			String id = dto.getApplyNo();
			String taskTitle = "????????????-??????";
			if (wm.isEnd(id, fileName, currUserId)) {
				Map<String, Object> formMap = new HashMap<>();
				formMap.put("taskTitle", taskTitle);
				formMap.put("businessKey", id);
				formMap.put("groupId", "2");
				formMap.put("assignId", dto.getAssignId());
				AppGroup dept = UimUtils.getDept(Long.parseLong(currUserId));
				//???????????????
				String fileClerk = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(dept.getId(), OaConstants.DEPARTMENT_MANAGER_POST_ID);
				formMap.put("fileClerk", fileClerk);
				//????????????
				String leaderId = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(dept.getId(), OaConstants.DIVISION_VICE_PRESIDENT_POST_ID);
				formMap.put("leaderId", leaderId);
				//?????????
				formMap.put("startUser", currUserId);

				ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", fileName, id, currUserId);
				if (inst != null) {
					//"???????????????"
					applyInfoModel.setProcessInstId(inst.getId());
					applyInfoModel.setApplyNo(dto.getApplyNo());
					applyInfoService.update(applyInfoModel);
				} else {
					throw new SystemException("???????????????????????????????????????????????????????????????");
				}

			}
		} catch (SystemException e) {
			logger.error(e.getMessage());
			mapResponse.error(e.getMessage());
		} catch (Exception e) {
			mapResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(baseResponse);
	}

	/**
	 ********??????????????????????????????*********???????????????????????????*******??????????????????*******????????????????????????????????????*******????????????????????????*********
	 */

	@ApiOperation(value = "????????????????????????????????????????????????", notes = "????????????????????????????????????????????????")
	@ApiImplicitParam(name = "SurveyPlanDTO", value = "SurveyPlanDTO", required = true, dataType = "RequirementsDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "????????????"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "?????????????????????"),
	})
	@PostMapping(value = "/saveInfoMc", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveInfoMc(@RequestBody StaffListDTO dto) {

		try {
			StaffListModel model = new StaffListModel();
			String userId = this.getLoginUserId();
			BeanUtils.copyProperties(model, dto);
			ProjAssignModel projAssignModel = new ProjAssignModel();
			BeanUtils.copyProperties(projAssignModel, dto);
			model.setUpdateBy(userId);
			model.setUpdateDt(new Date());
			//????????????????????????????????????
			String projId = dto.getProjId();
			HashSet<String> setOnly = new HashSet<String>();
			if (dto.getList() != null && dto.getList().size() > 1) {
				for (StaffListModel staffs : dto.getList()) {
					String value = staffs.getUserId();
					//String value = staffs.getUserId() + staffs.getUserType();
					System.out.println("-----??????----" + staffs.getUserId() + "--" + staffs.getUserType());
					if (setOnly.contains(value)) {
						// ????????????
						baseResponse.setMsg("????????????????????????");
						return JSONObject.toJSONString(baseResponse);
					}else {
						setOnly.add(value);
					}
				}
			}
			if (StringUtils.isNotEmpty(dto.getAssignId())) {
				//??????
				if (dto.getList() != null && dto.getList().size() > 0) {
					for (StaffListModel staffListModel : dto.getList()) {
						StaffListModel staff = new StaffListModel();
						staff.setUserId(staffListModel.getUserId());
						//??????????????????????????????
						List<StaffListModel> listS = staffListService.selectListByUid(staff);
						if (listS != null && listS.size() > 0) {
							for (StaffListModel StaffList : listS ) {
								String status = StaffList.getStatus();
								System.out.println("---------ceshigx----" + status + "--projId--???" + projId + "==" + StaffList.getProjId());
								if(projId.equals(StaffList.getProjId())){
									//0:?????????3???????????????
									if (!"0".equals(status) && !"3".equals(status)) {
										baseResponse.setMsg("??????????????????" + StaffList.getUserName());
										return JSONObject.toJSONString(baseResponse);
									}
									System.out.println("---------ceshi2----" + staffListModel + "--" + StaffList);
									if (staffListModel != null && staffListModel.getAssignId() != null && StaffList != null && StaffList.getAssignId() != null) {
										System.out.println("---------ceshi3----staffListModel-->" + staffListModel.getAssignId() + ";-StaffList->" + StaffList.getAssignId());
										if (("0".equals(status) || "3".equals(status)) && !staffListModel.getAssignId().equals(StaffList.getAssignId())) {
											baseResponse.setMsg("??????????????????" + StaffList.getUserName());
											return JSONObject.toJSONString(baseResponse);
										}
									}
								}
							}
						}
					}
				}
				staffListService.updateAssAndStaffList(dto.getList(), projAssignModel, userId);
			} else {
				if (dto.getList() != null && dto.getList().size() > 0) {
					for (StaffListModel staffListModel : dto.getList()) {
						StaffListModel staff = new StaffListModel();
						staff.setUserId(staffListModel.getUserId());
						//??????????????????????????????
						List<StaffListModel> listS = staffListService.selectListByUid(staff);
						if (listS != null && listS.size() > 0) {
							for (StaffListModel StaffList : listS ) {
								System.out.println("---------ceshi-zj----projId--???" + projId + "==" + StaffList.getProjId());
								if(projId.equals(StaffList.getProjId())){
									baseResponse.setMsg("??????????????????" + StaffList.getUserName());
									return JSONObject.toJSONString(baseResponse);
								}
							}
						}
					}
				}
				String assignId = serialnoService.getSequence("EI.TZ_T_PROJ_ASSIGN");
				projAssignModel.setAssignId(assignId);
				staffListService.insertAssAndStaffList(dto.getList(), projAssignModel, userId);
			}
			//????????????????????????????????????
			if (dto.getIsStartFlow()) {
				startWorkFlowMc(projAssignModel);
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

	/**
	 * ????????????
	 * @param model
	 * @return void
	 * @author zhaokuiyu
	 * @date 2020/5/26 17:35
	 * @creed: The best time to plant a tree is ten years ago, followed by now
	 */
	private void startWorkFlowMc(ProjAssignModel model) {
		IWorkflowManager wm = new WorkflowManager();
		String fileName = "MC_delegate";
		String currUserId = this.getLoginUserId();
		String id = model.getAssignId();
		String projName = "";
		if (StringUtils.isNotEmpty(model.getProjId())) {
			ProjInfoModel pro = projInfoService.selectById(model.getProjId());
			if (pro != null) {
				projName = pro.getProjName();
			}
		}
		//todo????????????
		String taskTitle = "???????????????" + projName;
		if (wm.isEnd(id, fileName, currUserId)) {
			Map<String, Object> formMap = new HashMap<>();
			formMap.put("taskTitle", taskTitle);
			formMap.put("businessKey", id);
			AppGroup dept = UimUtils.getDept(Long.parseLong(currUserId));
			//????????????
			String departmentManager = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(dept.getId(), OaConstants.DEPARTMENT_MANAGER_POST_ID);
			formMap.put("departmentManager", departmentManager);
			//????????????
			String divisionVicePresident = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(dept.getId(), OaConstants.DIVISION_VICE_PRESIDENT_POST_ID);
			formMap.put("divisionVicePresident", divisionVicePresident);
			//?????????????????? ???????????????
			List<String> managerOfficeList = UimUtils.getUserIdByRoleId(10020L);
			String managerOffice = "";
			for (String s : managerOfficeList) {
				managerOffice += s + ",";
			}
			if (managerOffice.length() > 0) {
				managerOffice = managerOffice.substring(0, managerOffice.length() - 1);
			}

			formMap.put("managerOffice", managerOffice);

			//???????????????10012
			List<String> printingAdminList = UimUtils.getUserIdByRoleId(10012L);
			String printingAdmin = "";
			for (String s : printingAdminList) {
				printingAdmin += s + ",";
			}
			if (printingAdmin.length() > 0) {
				printingAdmin = printingAdmin.substring(0, printingAdmin.length() - 1);
			}
			//???????????????
			formMap.put("printingAdmin", printingAdmin);
			//?????????
			formMap.put("startUser", currUserId);
			ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", fileName, id, currUserId);
			if (inst != null) {
				//"???????????????"
				model.setProcessInstId(inst.getId());
				model.setStatus("1");
				projAssignService.update(model);
			} else {
				throw new SystemException("???????????????????????????????????????????????????????????????");
			}
		}
	}
}