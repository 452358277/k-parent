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
import com.ppmg.ei.dto.ProjContractFileDTO;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.utils.OaConstants;
import com.ppmg.ei.vo.ProjContractFileListLcVO;
import com.ppmg.ei.vo.ProjContractFileListVO;
import com.ppmg.ei.vo.ProjContractFileQuitVO;
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
@Api(tags={"合同相关接口"})
public class ProjContractFileController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private ProjContractFileService projContractFileService;

	@Reference
	private ProjContractFileDebtService projContractFileDebtService;


	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@Reference
	private FundQuitApplService fundQuitApplyService;

	@Reference
	private FixflowRunTaskinstanceService fixflowRunTaskinstanceService;

	@Reference
	private static EiAttachmentService eiAttachmentService;

	@Reference
	private ProjAppInfoQuitService projAppInfoQuitService;


	@Reference(check = false)
	private SerialnoService serialnoService;
	@Reference
	private ProjInfoService projInfoService;
	@Reference
	private FundInfoService fundInfoService;
	@Reference(check = false)
	private ProjAppInfoService projAppInfoService;

	@Reference(check = false)
	private AppUserService appUserService;

	@ApiOperation(value="获取合同列表", notes="获取合同列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
		@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/projContractFileList", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public String projContractFileList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("projId") String projId,String groupId){
		
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("F.PROJ_ID", projId);
			if(StringUtils.isNotEmpty(groupId)){
				searchCondition.addConditionEqualTo("F.GROUP_ID", groupId);
			}
			searchCondition.addConditionNotEqualTo("F.STATUS", "9");
			PageInfo<ProjContractFileModel> rows = projContractFileService.selectListPage(searchCondition);
			List<ProjContractFileListVO> list = new ArrayList<ProjContractFileListVO>();
			for(ProjContractFileModel model : rows.getList()){
				ProjContractFileListVO vo = new ProjContractFileListVO();
				BeanUtils.copyProperties(vo, model);
				if(!"1".equals(model.getFileType())&&!"8".equals(model.getFileType())&&!"11".equals(model.getFileType())){
					vo.setThisAgreeAmont(null);
				}
				if("11".equals(model.getFileType())){
					ProjContractFileDebtModel projContractFileDebtModel=projContractFileDebtService.selectById(model.getFileId());
					if(projContractFileDebtModel!=null){
						vo.setProjContractFileDebtModel(projContractFileDebtModel);
					}
				}
                if(StringUtils.isNotEmpty(model.getFileType())){
					vo.setFileTypeName(codeUtils.getCodeNameByValue("224", model.getFileType()));
				}

				if(model.getProcessInstId() != null){
					List<FixflowRunTaskinstanceModel> flows = fixflowRunTaskinstanceService.getListByProcessinstanceId(model.getProcessInstId());
					if(flows.get(0).getFormuriview() == null || flows.get(0).getFormuriview() == ""){
						vo.setInstanceModel(flows.get(1));
					}else{
						vo.setInstanceModel(flows.get(0));
					}
				}

				list.add(vo);
			}
			dataTablesResponse.setData(list, rows);
			
		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}

	@ApiOperation(value="APP-获取投后合同-分页", notes="APP-获取投前合同-分页")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/projContractFileAppList", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public String projContractFileAppList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("projectObject") String projectObject,@RequestParam("inveId") String inveId){

		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("OBJECT_ID", projectObject);
			searchCondition.addConditionEqualTo("AFTER_FUNDER_ID", inveId);
			List<String> firstParty = new ArrayList<String>();
			firstParty.add("3");
			firstParty.add("4");
			searchCondition.addConditionIn("FIRST_PARTY", firstParty);
			searchCondition.addConditionNotEqualTo("STATUS", "9");
			PageInfo<ProjContractFileModel> rows = projContractFileService.selectListPage(searchCondition);
			List<ProjContractFileListVO> list = new ArrayList<ProjContractFileListVO>();
			for(ProjContractFileModel model : rows.getList()){
				ProjContractFileListVO vo = new ProjContractFileListVO();
				BeanUtils.copyProperties(vo, model);
				vo.setFileTypeName(codeUtils.getCodeNameByValue("224", model.getFileType()));

				if(vo.getContractFile()!= null && vo.getContractFile()!=  ""){
					EiTAttachmentModel eiTAttachmentModel = new EiTAttachmentModel();
					eiTAttachmentModel.setFieldToken(vo.getContractFile());
					List<EiTAttachmentModel> listEiAtt = eiAttachmentService.selectList(eiTAttachmentModel);
					for(int i=0;i<listEiAtt.size();i++){
						String fileSizeS = "" ;
						Long fileSizeL = Long.parseLong(listEiAtt.get(i).getFileSize());
						if(fileSizeL<1024){
							fileSizeS = String.valueOf(fileSizeL)+"B";
						}else if((fileSizeL/1024)<1024){
							fileSizeS = String.valueOf(fileSizeL/1024)+"KB";
						}else if((fileSizeL/1024/1024)<1024){
							fileSizeS = String.valueOf(fileSizeL/1024/1024)+"M";
						}
						listEiAtt.get(i).setFileSize(fileSizeS);
					}
					vo.setFileLists(listEiAtt);
				}


				/*if(model.getProcessInstId() != null){
					List<FixflowRunTaskinstanceModel> flows = fixflowRunTaskinstanceService.getListByProcessinstanceId(model.getProcessInstId());
					if(flows.get(0).getFormuriview() == null || flows.get(0).getFormuriview() == ""){
						vo.setInstanceModel(flows.get(1));
					}else{
						vo.setInstanceModel(flows.get(0));
					}
				}*/

				list.add(vo);
			}
			dataTablesResponse.setData(list, rows);

		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}





	@ApiOperation(value="APP-获取投前合同-分页", notes="APP-获取投前合同-分页")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/projContractFileAppTQList", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public String projContractFileAppTQList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,@RequestParam("projId") String projId){

		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("PROJ_ID", projId);
			List<String> firstParty = new ArrayList<String>();
			firstParty.add("1");
			firstParty.add("2");
			searchCondition.addConditionIn("FIRST_PARTY", firstParty);
			searchCondition.addConditionNotEqualTo("STATUS", "9");
			PageInfo<ProjContractFileModel> rows = projContractFileService.selectListPage(searchCondition);
			List<ProjContractFileListVO> list = new ArrayList<ProjContractFileListVO>();
			for(ProjContractFileModel model : rows.getList()){
				ProjContractFileListVO vo = new ProjContractFileListVO();
				BeanUtils.copyProperties(vo, model);
				vo.setFileTypeName(codeUtils.getCodeNameByValue("224", model.getFileType()));
				//附件List
				if(vo.getContractFile()!= null && vo.getContractFile()!=  ""){
					EiTAttachmentModel eiTAttachmentModel = new EiTAttachmentModel();
					eiTAttachmentModel.setFieldToken(vo.getContractFile());
					List<EiTAttachmentModel> listEiAtt = eiAttachmentService.selectList(eiTAttachmentModel);
					for(int i=0;i<listEiAtt.size();i++){
						String fileSizeS = "" ;
						Long fileSizeL = Long.parseLong(listEiAtt.get(i).getFileSize());
						if(fileSizeL<1024){
							fileSizeS = String.valueOf(fileSizeL)+"B";
						}else if((fileSizeL/1024)<1024){
							fileSizeS = String.valueOf(fileSizeL/1024)+"KB";
						}else if((fileSizeL/1024/1024)<1024){
							fileSizeS = String.valueOf(fileSizeL/1024/1024)+"M";
						}
						listEiAtt.get(i).setFileSize(fileSizeS);
					}
					vo.setFileLists(listEiAtt);
				}


				list.add(vo);
			}
			dataTablesResponse.setData(list, rows);

		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}

	@ApiIgnore
	@ApiOperation(value="新增合同", notes="新增合同")
	@ApiImplicitParam(name = "ProjContractFileDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/projContractFile/add", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(@RequestBody ProjContractFileDTO dto){
		try {
			if(StringUtils.isNotEmpty(dto.getFileTitle())&&StringUtils.isNotEmpty(dto.getProjId())){
				ProjContractFileModel projFile=new ProjContractFileModel();
				projFile.setFileTitle(dto.getFileTitle());
				projFile.setProjId(dto.getProjId());
				List<ProjContractFileModel> list= projContractFileService.selectList(projFile);
				if(list!=null&&list.size()>0){
					baseResponse.setMsg("协议名称为"+dto.getFileTitle()+"已存在");
					return JSONObject.toJSONString(baseResponse);
				}
			}

			ProjContractFileModel projContractFileModel=new ProjContractFileModel();
             BeanUtils.copyProperties(projContractFileModel,dto);
			String  fileId=serialnoService.getSequence("EI.TZ_T_PROJ_CONTRACT_FILE");
			projContractFileModel.setFileId(fileId);
			ProjContractFileDebtModel projContractFileDebtModel=new ProjContractFileDebtModel();
			BeanUtils.copyProperties(projContractFileDebtModel,dto);
			String userId=this.getLoginUserId();
			projContractFileService.insertById(projContractFileModel,projContractFileDebtModel,userId);
			if(dto.getIsStartFlow()!=null){
				if(dto.getIsStartFlow()) {//如果点击是提交则添加流程
					startWorkFlow(projContractFileModel);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiOperation(value="获取合同列表", notes="获取合同列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/projContractLcFileList", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public String projContractLcFileList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,String groupId,String projId){

		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			if(StringUtils.isNotEmpty(groupId)){
				searchCondition.addConditionEqualTo("GROUP_ID", groupId);
			}
			searchCondition.addConditionNotEqualTo("STATUS", "9");
			if(StringUtils.isNotEmpty(projId)){
				searchCondition.addConditionEqualTo("PROJ_ID",projId);
			}

			//searchCondition.addConditionEqualTo("create_by",this.getLoginUserId());
			PageInfo<ProjContractFileModel> rows = projContractFileService.selectByListPage(searchCondition);
			List<ProjContractFileListLcVO> list = new ArrayList<ProjContractFileListLcVO>();
			for(ProjContractFileModel model : rows.getList()){
				ProjContractFileListLcVO vo = new ProjContractFileListLcVO();
				BeanUtils.copyProperties(vo, model);
				if(StringUtils.isNotEmpty(model.getCreateBy())){
					AppUserModel appUser=appUserService.selectById(model.getCreateBy());
					if(appUser!=null){
						vo.setLoginName(appUser.getName());
					}

				}
                if(StringUtils.isNotEmpty(model.getFileType())){
					vo.setFileTypeName(codeUtils.getCodeNameByValue("9017", model.getFileType()));
				}
				if(StringUtils.isNotEmpty(model.getStatus())){
					String  statusName=codeUtils.getCodeNameByValue("264",model.getStatus());
					vo.setStatusName(statusName==null?"":statusName);
				}

				list.add(vo);
			}
			dataTablesResponse.setData(list, rows);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}

	@ApiOperation(value="详情", notes="详情")
	@ApiImplicitParam(name = "fileId", value = "fileId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/projContractFile/detainfo/{fileId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detail(@PathVariable(value = "fileId") String fileId){
		try {
			ProjContractFileModel model = projContractFileService.selectById(fileId);
			ProjContractFileListLcVO vo = new ProjContractFileListLcVO();
			BeanUtils.copyProperties(vo, model);
			if(model!=null) {
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
				if (StringUtils.isNotEmpty(model.getFileType())) {
					if ("2".equals(model.getGroupId())) {
						vo.setFileTypeName(codeUtils.getCodeNameByValue("9017", model.getFileType()));
					} else {
						vo.setFileTypeName(codeUtils.getCodeNameByValue("224", model.getFileType()));
					}

				}
				if (StringUtils.isNotEmpty(model.getStatus())) {
					String statusName = codeUtils.getCodeNameByValue("264", model.getStatus());
					vo.setStatusName(statusName == null ? "" : statusName);
				}
				if (StringUtils.isNotEmpty(model.getSignAmountCurr())) {
					String signAmountCurrName = codeUtils.getCodeNameByValue("103", model.getSignAmountCurr());
					vo.setSignAmountCurrName(signAmountCurrName == null ? "" : signAmountCurrName);
				}
				if ("11".equals(model.getFileType())) {
					ProjContractFileDebtModel projContractFileDebtModel = projContractFileDebtService.selectById(fileId);
					if (projContractFileDebtModel != null) {
						vo.setProjContractFileDebtModel(projContractFileDebtModel);
					}
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

	@ApiIgnore
	@ApiOperation(value="修改", notes="修改")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "fileId", value = "fileId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "ProjContractFileDTO", value = "ProjContractFileDTO", required = true, dataType = "FundQuitApplVO")
	})
	@PostMapping(value = "/projContractFile/update/{fileId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update(@PathVariable("fileId") String fileId, @RequestBody ProjContractFileDTO dto){

		try {
			if(StringUtils.isNotEmpty(dto.getFileTitle())&&StringUtils.isNotEmpty(dto.getProjId())){
				ProjContractFileModel projContractFileD=projContractFileService.selectById(fileId);
				if(projContractFileD!=null){
					if(!projContractFileD.getFileTitle().equals(dto.getFileTitle())){
						ProjContractFileModel projFile=new ProjContractFileModel();
						projFile.setFileTitle(dto.getFileTitle());
						projFile.setProjId(dto.getProjId());
						List<ProjContractFileModel> list= projContractFileService.selectList(projFile);
						if(list!=null&&list.size()>0){
							baseResponse.setMsg("协议名称为"+dto.getFileTitle()+"已存在");
							return JSONObject.toJSONString(baseResponse);
						}
					}
				}

			}
			String userId=this.getLoginUserId();
			//String userId="1005000";
			ProjContractFileModel model = new ProjContractFileModel();
			BeanUtils.copyProperties(model, dto);
			//是否省政府的项目入口
			model.setIsGov(dto.getIsGov());
			ProjContractFileDebtModel projContractFileDebtModel=new ProjContractFileDebtModel();
			BeanUtils.copyProperties(projContractFileDebtModel, dto);
			model.setFileId(fileId);
			if(model.getSignAmount()==null){
				model.setSignAmount(0.0);
			}
			projContractFileService.updateById(model,projContractFileDebtModel,userId);
			if(dto.getIsStartFlow()!=null){
				if(dto.getIsStartFlow()) {//如果点击是提交则添加流程
					startWorkFlow(model);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);

	}
	@ApiIgnore
	@ApiOperation(value="合同/删除", notes="尽职调查计划修改、删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "fileId", value = "fileId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "ProjContractFileListLcVO", value = "ProjContractFileListLcVO", required = true, dataType = "ProjContractFileListLcVO")
	})
	@PostMapping(value = "/projContractFile/delete/{fileId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update(@PathVariable("fileId") String fileId, @RequestBody ProjContractFileListLcVO dto){
		try {
			ProjContractFileModel model = new ProjContractFileModel();
			BeanUtils.copyProperties(model, dto);
			model.setFileId(fileId);
			model.setUpdateBy(this.getLoginUserId());
			model.setUpdateDt(new Date());
			projContractFileService.update(model);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}


	private void startWorkFlow(ProjContractFileModel projContractFileModel) {
		IWorkflowManager wm = new WorkflowManager();
		String fileName = "ei_contract_sign";
		String currUserId = this.getLoginUserId();
		String id = projContractFileModel.getFileId();
		String taskTitle =  "合同签署及工商注册及变更："+projContractFileModel.getFileTitle();
		if (wm.isEnd(id, fileName, currUserId)) {
			Map<String, Object> formMap = new HashMap<>();
			formMap.put("taskTitle", taskTitle);
			formMap.put("businessKey", id);
			formMap.put("projId", projContractFileModel.getProjId());
			//查询基金的groupId he fundId,为了处理省政府穿透的数据
			ProjInfoModel projm= projInfoService.selectById(projContractFileModel.getProjId());
			if(projm!=null&&"2".equals(projm.getProjType())){
				formMap.put("groupId", projm.getGroupId());
				formMap.put("fundId", projm.getProjObject());
			}


			AppGroup dept = UimUtils.getDept(Long.parseLong(currUserId));
			//部门经理
			String departmentManager = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(dept.getId(), OaConstants.DEPARTMENT_MANAGER_POST_ID);
			formMap.put("departmentManager", departmentManager);
			//分管副总
			String divisionVicePresident = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(dept.getId(), OaConstants.DIVISION_VICE_PRESIDENT_POST_ID);
			formMap.put("divisionVicePresident", divisionVicePresident);

			List<String> subControlLaw = UimUtils.getUserIdByRoleId(10009L);
			String subControlLaws = "";
			for (String s : subControlLaw) {
				subControlLaws += s + ",";
			}
			if (subControlLaws.length() > 0) {
				subControlLaws = subControlLaws.substring(0, subControlLaws.length() - 1);
			}
			//分控法务
			formMap.put("subControlLaws", subControlLaws);

			//总经理10005
			List<String> assembleManager = UimUtils.getUserIdByRoleId(10005L);
			String assembleManagers = "";
			for (String s : assembleManager) {
				assembleManagers += s + ",";
			}
			if (assembleManagers.length() > 0) {
				assembleManagers = assembleManagers.substring(0, assembleManagers.length() - 1);
			}
			//总经理
			formMap.put("assembleManagers", assembleManagers);
			//用印管理员10012
			List<String> printingAdminList = UimUtils.getUserIdByRoleId(10012L);
			String printingAdmin = "";
			for (String s : printingAdminList) {
				printingAdmin += s + ",";
			}
			if (printingAdmin.length() > 0) {
				printingAdmin = printingAdmin.substring(0, printingAdmin.length() - 1);
			}
			//用印管理员
			formMap.put("printingAdmin", printingAdmin);

			//发起人
			formMap.put("startUser", currUserId);

			ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", fileName, id, currUserId);
			if (inst != null) {
				//"提交成功！"
				projContractFileModel.setProcessInstId(inst.getId());
				projContractFileModel.setStatus("1");
				projContractFileService.update(projContractFileModel);
			} else {
				throw new SystemException("流程启动失败，请确认下一节点审批人是否存在");
			}
		}
	}
	/**
	 *----------------------------投基金，投项目----- 退出合同--------------------------------------------*/
	@ApiIgnore
	@ApiOperation(value="退出合同,分页查询，-投基金，投项目", notes="退出合同,分页查询-投基金，投项目", httpMethod = "GET")
	@GetMapping(value = "/selectProjContractFileQuit", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public String selectProjContractFileQuit(@RequestParam("pageSize") int pageSize
			, @RequestParam("currPage") int currPage
			, @RequestParam("projId") String projId, @RequestParam("status") String status){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("PROJ_ID", projId);
			if(StringUtils.isNotEmpty(status)){
				searchCondition.addConditionEqualTo("STATUS", status);
			}
			PageInfo<ProjContractFileQuitModel> rows = projContractFileService.selectPageListQuit(searchCondition);
			List<ProjContractFileQuitVO> list = new ArrayList<ProjContractFileQuitVO>();
			for(ProjContractFileQuitModel model : rows.getList()){
				ProjContractFileQuitVO vo = new ProjContractFileQuitVO();
				String fundId = null;
				ProjInfoModel pf = projInfoService.selectById(projId);//主表
				if(pf != null){
					fundId = pf.getProjObject();
					FundInfoModel fm = fundInfoService.selectById(fundId);
					if(fm != null){
                        //基金认缴总规模（万元)CURRENT_AMOUNT
                        model.setFundSubscribed(fm.getCurrentAmount());
                        //基金实缴规模（万元）RAISE_AMOUNT
                        model.setFundPaidIn(fm.getRaiseAmount());
                    }
				}
				BeanUtils.copyProperties(vo, model);
				String appId = model.getAppId();
				FundQuitApplModel model_ = fundQuitApplyService.selectById(appId);//查询退出决策
				if(model_ != null){
					String quitProjId = model_.getQuitProjId();//退出立项主键
					ProjAppInfoQuitModel model_quit = projAppInfoQuitService.selectById(quitProjId);//查询退出立项
					if(model_quit != null){
						vo.setQuitName(model_quit.getQuitName());//退出立项名称
					}
					if(model_ !=null){
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						if(model_.getQuitDt()!=null){
							String time = sdf.format(model_.getQuitDt()); //可以把日期转换转指定格式的字符串
							System.out.println("当前的系统时间："+ time);
							vo.setQuitDt(time);//退出日期
						}
						vo.setQuitWay(model_.getQuitWay());//退出方式
						vo.setShareRatio(model_.getShareRatio());//股比
						vo.setBackAmount(model_.getBackAmount()+"");//收回金额（万元）
						vo.setExitAmount(model_.getExitAmount()+"");//退出认缴出资额(万元)
						vo.setExitShareRatio(model_.getExitShareRatio()+"");//退出部分所占股比%
						vo.setRestShareRatio(model_.getRestShareRatio()+"");//剩余部分所占股比%
						vo.setDecisionDetail(model_.getDecisionDetail());//決策內容
					}
				}
				if(StringUtils.isNotEmpty(model.getFileType())){
					//合同類別
					vo.setFileTypeName(codeUtils.getCodeNameByValue("1024", model.getFileType()));
				}
				if(StringUtils.isNotEmpty(model.getQuitWay())){
					//退出方式
					vo.setQuitWayName(codeUtils.getCodeNameByValue("262", model.getQuitWay()));
				}
				if(StringUtils.isNotEmpty(model.getStatus())){
					//流程状态
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
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}

	@ApiIgnore
	@ApiOperation(value="退出合同,详情---投基金", notes="退出合同,详情---投基金", httpMethod = "GET")
	@GetMapping(value = "/projContractFileQuitDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String projContractFileQuitDetail(@RequestParam(value = "fileId") String fileId) {
		try {
			  ProjContractFileQuitModel model = projContractFileService.selectDetailes(fileId);
				String projId = model.getProjId();
				ProjContractFileQuitVO vo = new ProjContractFileQuitVO();
				String fundId = null;
				ProjInfoModel pf = projInfoService.selectById(projId);//主表
				if(pf != null){
					fundId = pf.getInveId();
					FundInfoModel fm = fundInfoService.selectById(fundId);
					if(fm != null){
						//基金认缴总规模（万元)CURRENT_AMOUNT
						model.setFundSubscribed(fm.getCurrentAmount());
						//基金实缴规模（万元）RAISE_AMOUNT
						model.setFundPaidIn(fm.getRaiseAmount());
					}
				}
				BeanUtils.copyProperties(vo, model);
				if(StringUtils.isNotEmpty(model.getFileType())){//合同類別
					vo.setFileTypeName(codeUtils.getCodeNameByValue("1024", model.getFileType()));
				}
				if(StringUtils.isNotEmpty(model.getQuitWay())){//退出方式
					vo.setQuitWayName(codeUtils.getCodeNameByValue("262", model.getQuitWay()));
				}
				if(StringUtils.isNotEmpty(model.getStatus())){//流程状态
					vo.setStatusName(codeUtils.getCodeNameByValue("599", model.getStatus()));
				}
                FundQuitApplModel fundModel = fundQuitApplyService.selectById(model.getAppId());
                if (fundModel != null) {
                    vo.setAppName(fundModel.getAppName());
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
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}
	@ApiIgnore
	@ApiOperation(value="退出合同,详情---投项目", notes="退出合同,详情---投项目", httpMethod = "GET")
	@GetMapping(value = "/projContractFileQuitDetailProject", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String projContractFileQuitDetailProject(@RequestParam(value = "fileId") String fileId) {
		try {
			//TODO 根据决策id查询20201128修改
			String appId = "";
            ProjContractFileQuitModel file = projContractFileService.selectDetailes(fileId);
            if(file == null){
				appId = fileId;
			}else{
				appId = file.getAppId();
			}
			List<ProjContractFileQuitModel> models = projContractFileService.selectDetailesAppIdList(appId);
			ProjContractFileQuitModel model = null;
			if (models.size() > 0) {
				model = models.get(0);
				ProjContractFileQuitVO vo = new ProjContractFileQuitVO();
				if(StringUtils.isNotEmpty(model.getStatus())){//流程状态
					vo.setStatusName(codeUtils.getCodeNameByValue("599", model.getStatus()));
				}
				// 协议金额（万元)币种
				if(StringUtils.isNotEmpty(model.getStatus())){
					vo.setThisAgreeAmontCurrName(codeUtils.getCodeNameByValue("103", model.getThisAgreeAmontCurr()));
				}
				//将合同名称类别赋值到list
				List<ProjContractFileQuitUtilModel> ls = new ArrayList<>();
				ProjContractFileQuitUtilModel ls2 = null;
				for (ProjContractFileQuitModel m : models) {
					ls2 = new ProjContractFileQuitUtilModel();
					ls2.setFileTitle(m.getFileTitle());
					ls2.setFileType(m.getFileType());
					// 合同类别
					if(StringUtils.isNotEmpty(m.getFileType())){
						ls2.setFileTypeName(codeUtils.getCodeNameByValue("1024", m.getFileType()));
					}
					ls.add(ls2);
				}
				BeanUtils.copyProperties(vo, model);
				vo.setProjContractFileQuitUtilModel(ls);
				FundQuitApplModel model_ = fundQuitApplyService.selectById(appId);//查询退出决策
				if(model_ != null){
					String quitProjId = model_.getQuitProjId();//退出立项主键
					ProjAppInfoQuitModel model_quit = projAppInfoQuitService.selectById(quitProjId);//查询退出立项
					if(model_quit != null){
						vo.setQuitName(model_quit.getQuitName());//退出立项名称
					}
				}
				ProjInfoModel pf = projInfoService.selectById(model.getProjId());//主表
				if(pf != null){
					vo.setInveName(pf.getInveName());//出资主体
					vo.setProjName(pf.getProjName());//項目名稱
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
			}
		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}
	@ApiIgnore
	@ApiOperation(value="退出合同新增-投基金，投项目", notes="新增退出合同-投基金，投项目", httpMethod = "POST")
	@ApiImplicitParam(name = "addProjContractFileQuit", value = "addProjContractFileQuit", required = true, dataType = "ProjContractFileDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/addProjContractFileQuit", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addProjContractFileQuit(@RequestBody ProjContractFileQuitModel Model){
		try {
			if(Model != null){
				String projId =Model.getProjId();
				ProjInfoModel pf_Serch = projInfoService.selectById(Model.getProjId());//主表
				//项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
				String projType = pf_Serch.getProjType();
				if (projType != null && "2".equals(projType)) {
					//判断投基金合同名称是否重复
					ProjContractFileModel ff = new ProjContractFileModel();
					ff.setProjId(Model.getProjId());
					List<ProjContractFileModel> listName = projContractFileService.selectList(ff);
					for (ProjContractFileModel tk : listName) {
						String fileTitle = tk.getFileTitle();
						if(fileTitle.equals(Model.getFileTitle())){
							baseResponse.setStatus("400");
							baseResponse.setMsg("合同名称已被占用,请重新输入合适名称");
							return JSONObject.toJSONString(baseResponse);
						}
					}
				}
				String userId = String.valueOf(this.getLoginUser().getUserId());
				//String userId = UimUtilsUserId.getUserId(projType);
				Long deptId = UimUtils.getDept(Long.parseLong(userId)).getId();//獲取部門
				String tag = Model.getTag();
				String appId = Model.getAppId();
				//查询退出决策，取里面的退出方式,1：全部退出；2：部分退出
				String quitWay = null;
				FundQuitApplModel model_quit = fundQuitApplyService.selectById(appId);
				if(model_quit != null){
					quitWay = model_quit.getQuitWay();
				}
				System.out.println("##投决会审议同意------1：全部退出；2：部分退出-------quitWay---->>"+quitWay);
				if(projType != null && "2".equals(projType)){
					//2：投基金
					ProjContractFileQuitModel appIdLs = projContractFileService.selectDetailesAppId(appId);
					if(appIdLs != null){
						baseResponse.setStatus("400");
						baseResponse.setMsg("一個退出決策只能选择一次");
						return JSONObject.toJSONString(baseResponse);
					}else{
						String  fileId = UUID.randomUUID().toString();
						if (tag != null && "0".equals(tag)) {
							//提交流程并且保存数据
							IWorkflowManager wm = new WorkflowManager();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String date = sdf.format(new Date());
							String authid = userId;
							//主键id
							String businessKey = fileId;
							//流程编号
							String processID = "TG_ExitContractSigning";
							//流程主题
							//String subject = "投管基金-退出合同" + "-" + date;
							//流程名称：项目或基金名称（股权相关的功能）
							String subject = "退出合同" + ":" + pf_Serch.getProjName();
							logger.info("##--------------ProjName--->>"+pf_Serch.getProjName()+"<--subject--->"+subject);
							//其他參數
							Map<String, Object> formMap = new HashMap<String, Object>();
							formMap.put("taskTitle", subject);
							formMap.put("businessKey", businessKey);
							formMap.put("deptId", deptId);
							formMap.put("projId", projId);
							formMap.put("quitWay", quitWay);
							//启动流程
							ProcessInstance inst = wm.startAndSubmit(formMap,"HandleCommand_StartAndSubmit",processID,businessKey,authid);
							//"提交成功！"
							if (inst != null) {
								logger.info("##------->流程启动成功<--------##"+inst.getId());
								//保存数据
								Model.setStatus("1");
								//是签署合同，还是退出合同（0：退出；1：签署）
								Model.setSignQuit("0");
								Model.setFileId(fileId);
								Model.setProcessInstId(inst.getId());
								projContractFileService.insertIntoQuit(userId,Model);
							} else {
								logger.error("##------->流程启动失败<--------##");
								baseResponse.error("流程启动失败");
							}
						}else if(tag != null && "1".equals(tag)){
							//保存数据
							Model.setStatus("0");
							Model.setSignQuit("0");//是签署合同，还是退出合同（0：退出；1：签署）
							Model.setFileId(fileId);
							projContractFileService.insertIntoQuit(userId,Model);
						}
					}
				}else if(projType != null && "1".equals(projType)){
					//项目投管
					List<ProjContractFileQuitModel> appIdLs = projContractFileService.selectDetailesAppIdList(appId);
					if (appIdLs != null && appIdLs.size() > 0) {
						baseResponse.setStatus("400");
						baseResponse.setMsg("一个退出决策只能选择一次");
						return JSONObject.toJSONString(baseResponse);
					}else{
						if (tag != null && "0".equals(tag)) {
							//提交流程并且保存数据
							IWorkflowManager wm = new WorkflowManager();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String date = sdf.format(new Date());
							String authid = userId;
							//主键id//TODO 根据决策id查询20201128修改
							String businessKey = appId;
                            //String businessKey = Model.getFileId();

							//流程编号
							String processID = "TG_ExitContractSigning_project";
							//流程主题
							//String subject = "项目投管-退出合同" + "-" + date;
							//流程名称：项目或基金名称（股权相关的功能）
							String subject = "退出合同" + ":" + pf_Serch.getProjName();
							logger.info("##--------------ProjName--->>"+pf_Serch.getProjName()+"<--subject--->"+subject);
							//其他參數
							Map<String, Object> formMap = new HashMap<String, Object>();
							formMap.put("taskTitle", subject);
							formMap.put("businessKey", businessKey);
							formMap.put("deptId", deptId);
							formMap.put("projId", projId);
							formMap.put("appId", appId);
							formMap.put("quitWay", quitWay);
							//启动流程
							ProcessInstance inst = wm.startAndSubmit(formMap,"HandleCommand_StartAndSubmit",processID,businessKey,authid);
							if (inst != null) {
								logger.info("##------->流程启动成功<--------##");
								//1：审批中
								Model.setStatus("1");
								Model.setProcessInstId(inst.getId());
								projContractFileService.insertProjContractFileProjectBy(userId,Model);
								//projContractFileService.insertProjContractFileProject(userId,Model);
							} else {
								logger.error("##------->流程启动失败<--------##");
								baseResponse.error("流程启动失败");
							}
						}else if(tag != null && "1".equals(tag)){
							//保存数据
							//0：草稿
							Model.setStatus("0");
							projContractFileService.insertProjContractFileProjectBy(userId,Model);
							//projContractFileService.insertProjContractFileProject(userId,Model);
						}
					}
				}
				//更新主表时间
				ProjAppInfoModel proAppInfo = new ProjAppInfoModel();
				proAppInfo.setProjId(projId);
				proAppInfo.setUpdateDt(new Date());
				projAppInfoService.updateAppInfo(proAppInfo);
				ProjInfoModel projInfoModel = new ProjInfoModel();
				projInfoModel.setProjId(projId);
				projInfoModel.setUpdateDt(new Date());
				projInfoService.updateprojInfo(projInfoModel);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiIgnore
	@ApiOperation(value="退出合同修改-投基金，投项目", notes="退出合同修改-投基金，投项目", httpMethod = "POST")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "fileId", value = "fileId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "ProjContractFileDTO", value = "ProjContractFileDTO", required = true, dataType = "FundQuitApplVO")
	})
	@PostMapping(value = "/updateProjContractFileQuit", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateProjContractFileQuit(@RequestBody ProjContractFileQuitModel Model){
		try {
			if(Model !=null){
				String projId = Model.getProjId();
				ProjInfoModel pf_Serch = projInfoService.selectById(Model.getProjId());
				//项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
				String projType = pf_Serch.getProjType();
				if (projType != null && "2".equals(projType)) {
					//判断投基金合同名称是否重复
					ProjContractFileModel ff = new ProjContractFileModel();
					ff.setProjId(Model.getProjId());
					List<ProjContractFileModel> listName = projContractFileService.selectList(ff);
					for (ProjContractFileModel tk : listName) {
						String fileTitle = tk.getFileTitle();
						String id = tk.getFileId();
						if(fileTitle.equals(Model.getFileTitle())){
							if(!id.equals(Model.getFileId())){
								baseResponse.setStatus("400");
								baseResponse.setMsg("合同名称已被占用,请重新输入合适名称");
								return JSONObject.toJSONString(baseResponse);
							}
						}
					}
				}
				IWorkflowManager wm = new WorkflowManager();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = sdf.format(new Date());
				String userId = String.valueOf(this.getLoginUser().getUserId());
				//String userId = UimUtilsUserId.getUserId(projType);
				Long deptId = UimUtils.getDept(Long.parseLong(userId)).getId();
				System.out.println("退出合同发起------当前登录人---》" + userId + "<---->" + deptId);
				//查询退出决策，取里面的退出方式,1：全部退出；2：部分退出
				String quitWay = null;
				FundQuitApplModel model_quit = fundQuitApplyService.selectById(Model.getAppId());
				if(model_quit != null){
					quitWay = model_quit.getQuitWay();
				}
				if(projType != null && "2".equals(projType)) {
					//2：---------------------------投基金------------------------
					String fileIds = Model.getFileId();
					ProjContractFileQuitModel model_fileId = projContractFileService.selectDetailes(fileIds);
					if(!model_fileId.getAppId().equals(Model.getAppId())){
						String appId = Model.getAppId();
						ProjContractFileQuitModel appIdLs = projContractFileService.selectDetailesAppId(appId);
						if(appIdLs != null){
							baseResponse.error();
							baseResponse.setMsg("一个退出决策只能选择一次");
							return JSONObject.toJSONString(baseResponse);
						}
					}
					String tag = Model.getTag();
					String fileId = Model.getFileId();
					if (tag != null && "0".equals(tag)) {
						//提交流程并且保存数据
						String authid = userId;
						//主键id
						String businessKey = fileId;
						//流程编号
						String processID = "TG_ExitContractSigning";
						//流程主题
						//String subject = "投管基金-退出合同" + "-" + date;
						//流程名称：项目或基金名称（股权相关的功能）
						String subject = "退出合同" + ":" + pf_Serch.getProjName();
						logger.info("##--------------ProjName--->>"+pf_Serch.getProjName()+"<--subject--->"+subject);
						//其他參數
						Map<String, Object> formMap = new HashMap<String, Object>();
						formMap.put("taskTitle", subject);
						formMap.put("businessKey", businessKey);
						formMap.put("deptId", deptId);
						formMap.put("projId", projId);
						formMap.put("quitWay", quitWay);
						//启动流程
						//HandleCommand_StartAndSubmit：要在对应的流程中设置按钮的名字
						//Map<String, Object> formMap, String commandId, String processDefinitionKey, String businessKey, String authId
						ProcessInstance inst = wm.startAndSubmit(formMap,"HandleCommand_StartAndSubmit",processID,businessKey,authid);
						//"提交成功！"
						if (inst != null) {
							logger.info("##------->流程启动成功<--------##"+inst.getId());
							//保存数据
							Model.setStatus("1");
							Model.setProcessInstId(inst.getId());
							projContractFileService.updateIntoQuit(userId,Model);
						} else {
							logger.error("##------->流程启动失败<--------##");
							baseResponse.error("流程启动失败");
						}
					}else if(tag != null && "1".equals(tag)){
						//保存数据
						Model.setStatus("0");
						projContractFileService.updateIntoQuit(userId,Model);
					}else if(tag != null && "3".equals(tag)){
						//保存数据
						projContractFileService.updateIntoQuit(userId,Model);
					}
				}else if(projType != null && "1".equals(projType)) {
					//--------------------项目投管--------------------------------
					String tag = Model.getTag();
					String appId = Model.getAppId();
					ProjContractFileQuitModel one = projContractFileService.selectDetailes(Model.getFileId());
					if (one != null && !one.getAppId().equals(Model.getAppId())) {
						List<ProjContractFileQuitModel> appIdLs = projContractFileService.selectDetailesAppIdList(Model.getAppId());
						if (appIdLs != null && appIdLs.size() > 0) {
							baseResponse.setStatus("400");
							baseResponse.setMsg("一个退出决策只能选择一次");
							return JSONObject.toJSONString(baseResponse);
						}
					}
					if (tag != null && "0".equals(tag)) {
						//提交流程并且保存数据
						String authid = userId;
						//主键id
						String businessKey = Model.getFileId();
						//流程编号
						String processID = "TG_ExitContractSigning_project";
						//流程主题
						//String subject = "项目投管-退出合同" + "-" + date;
						//流程名称：项目或基金名称（股权相关的功能）
						String subject = "退出合同" + ":" + pf_Serch.getProjName();
						logger.info("##--------------ProjName--->>" + pf_Serch.getProjName() + "<--subject--->" + subject);
						//其他參數
						Map<String, Object> formMap = new HashMap<String, Object>();
						formMap.put("taskTitle", subject);
						formMap.put("businessKey", businessKey);
						formMap.put("deptId", deptId);
						formMap.put("projId", projId);
						formMap.put("appId", appId);
						formMap.put("quitWay", quitWay);
						//启动流程
						ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", processID, businessKey, authid);
						if (inst != null) {
							logger.info("##------->流程启动成功<--------##");
							Model.setProcessInstId(inst.getId());
							projContractFileService.updateProjContractFileProject(userId, Model, one);
						} else {
							logger.error("##------->流程启动失败<--------##");
							baseResponse.error("流程启动失败");
						}
					} else if ("1".equals(tag)) {
						projContractFileService.updateProjContractFileProject(userId, Model, one);
					}else if ("3".equals(tag)) {
						projContractFileService.updateProjContractFileProject(userId, Model, one);
					}
				}
				//更新主表时间
				ProjAppInfoModel proAppInfo = new ProjAppInfoModel();
				proAppInfo.setProjId(projId);
				proAppInfo.setUpdateDt(new Date());
				projAppInfoService.updateAppInfo(proAppInfo);
				ProjInfoModel projInfoModel = new ProjInfoModel();
				projInfoModel.setProjId(projId);
				projInfoModel.setUpdateDt(new Date());
				projInfoService.updateprojInfo(projInfoModel);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	@ApiIgnore
	@ApiOperation(value="退出合同-删除-投基金", notes="退出合同-删除-投基金", httpMethod = "POST")
	@RequestMapping(value = "/deleteProjContractFileQuit", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteProjContractFileQuit(@RequestParam("fileId") String fileIds) {
		try {
			String[] arr = null;
			if (fileIds != "" && fileIds != null) {
				arr = fileIds.split(",");
				for (String fileId : arr) {
					projContractFileService.deleteIntoQuit(fileId);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}
	@ApiIgnore
	@ApiOperation(value="退出合同-删除-投项目", notes="退出合同-删除-投项目", httpMethod = "POST")
	@RequestMapping(value = "/deleteProjContractFileQuitProject", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteProjContractFileQuitProject(@RequestParam("fileId") String fileIds) {
		try {
			String[] arr = null;
			if (fileIds != "" && fileIds != null) {
				arr = fileIds.split(",");
				for (String fileId : arr) {
					ProjContractFileQuitModel de = projContractFileService.selectDetailes(fileId);
					if (de != null) {
						String appId = de.getAppId();
						List<ProjContractFileQuitModel> ls = projContractFileService.selectDetailesAppIdList(appId);
						for (ProjContractFileQuitModel ids : ls) {
							projContractFileService.deleteIntoQuit(ids.getFileId());
						}
					}
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}


	@ApiOperation(value="查询退出立项名称", notes="查询退出立项名称", httpMethod = "GET")
	@GetMapping(value = "/selectProjContractFileQuitTwo", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public String selectProjContractFileQuitTwo(@RequestParam("pageSize") int pageSize
			, @RequestParam("currPage") int currPage
			, @RequestParam("projId") String projId,@RequestParam("status") String status){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("PROJ_ID", projId);
			if(StringUtils.isNotEmpty(status)){
				searchCondition.addConditionEqualTo("STATUS", status);
			}
			PageInfo<ProjContractFileQuitModel> rows = projContractFileService.selectPageListQuit(searchCondition);
			List<ProjContractFileQuitVO> list = new ArrayList<ProjContractFileQuitVO>();
			HashSet<String> setOnly = new HashSet<String>();
			for(ProjContractFileQuitModel model : rows.getList()){
				String value = model.getAppId();
				if (setOnly.contains(value)) {
					// 重复元素
				} else {
					//不重复
					ProjContractFileQuitVO vo = new ProjContractFileQuitVO();
					BeanUtils.copyProperties(vo, model);
					String appId = model.getAppId();
					FundQuitApplModel mo = fundQuitApplyService.selectById(appId);
					if(mo != null){
						String quitProjId = mo.getQuitProjId();
						//退出立项主键
						ProjAppInfoQuitModel model_quit = projAppInfoQuitService.selectById(quitProjId);
						if(model_quit != null) {
							vo.setQuitProjId(model_quit.getQuitProjId());
							//退出立项id
							vo.setQuitName(model_quit.getQuitName());
							//退出立项名称
						}
						list.add(vo);
						setOnly.add(value);
					}
				}
			}
			dataTablesResponse.setData(list, rows);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}

	/**
	 ********↓↓↓↓↓↓↓↓↓↓*********平台名称：金财合盈*******↓↓↓↓↓↓*******8、管理公司退出合同签署*******↓↓↓↓↓↓↓↓*********
	 */

	@ApiIgnore
	@ApiOperation(value="退出合同,分页查询，管理公司退出合同签署", notes="退出合同,分页查询-管理公司退出合同签署", httpMethod = "GET")
	@GetMapping(value = "/selectProjContractFileQuitMc", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public String selectProjContractFileQuitMc(@RequestParam("pageSize") int pageSize
			, @RequestParam("currPage") int currPage
			, @RequestParam("projId") String projId, @RequestParam("status") String status){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("PROJ_ID", projId);
			if(StringUtils.isNotEmpty(status)){
				searchCondition.addConditionEqualTo("STATUS", status);
			}
			PageInfo<ProjContractFileQuitModel> rows = projContractFileService.selectPageListQuit(searchCondition);
			List<ProjContractFileQuitVO> list = new ArrayList<>();
			for(ProjContractFileQuitModel model : rows.getList()){
				ProjContractFileQuitVO vo = new ProjContractFileQuitVO();
				String fundId = null;
				ProjInfoModel pf = projInfoService.selectById(projId);
				if(pf != null){
					fundId = pf.getProjObject();
					FundInfoModel fm = fundInfoService.selectById(fundId);
					if(fm != null){
						//基金认缴总规模（万元)CURRENT_AMOUNT
						model.setFundSubscribed(fm.getCurrentAmount());
						//基金实缴规模（万元）RAISE_AMOUNT
						model.setFundPaidIn(fm.getRaiseAmount());
					}
				}
				BeanUtils.copyProperties(vo, model);
				//退出立项主键
				String quitProjId = model.getAppId();
				//查询退出立项
				ProjAppInfoQuitModel model_quit = projAppInfoQuitService.selectById(quitProjId);
				if(model_quit != null){
					//退出立项名称
					vo.setQuitName(model_quit.getQuitName());
				}
				if(model !=null){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					if(model.getQuitDt()!=null){
						//可以把日期转换转指定格式的字符串
						String time = sdf.format(model.getQuitDt());
						System.out.println("当前的系统时间："+ time);
						//退出日期
						vo.setQuitDt(time);
					}
				}
				if(StringUtils.isNotEmpty(model.getFileType())){
					//合同類別
					vo.setFileTypeName(codeUtils.getCodeNameByValue("1024", model.getFileType()));
				}
				if(StringUtils.isNotEmpty(model.getQuitWay())){
					//退出方式
					vo.setQuitWayName(codeUtils.getCodeNameByValue("262", model.getQuitWay()));
				}
				if(StringUtils.isNotEmpty(model.getStatus())){
					//流程状态
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
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}
	@ApiIgnore
	@ApiOperation(value="退出合同,详情---管理公司退出合同签署", notes="退出合同,详情---管理公司退出合同签署", httpMethod = "GET")
	@GetMapping(value = "/projContractFileQuitDetailProjectMc", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String projContractFileQuitDetailProjectMC(@RequestParam(value = "fileId") String fileId) {
		try {
			ProjContractFileQuitModel file = projContractFileService.selectDetailes(fileId);
			String appId = file.getAppId();
			List<ProjContractFileQuitModel> models = projContractFileService.selectDetailesAppIdList(appId);
			ProjContractFileQuitModel model = null;
			if (models.size() > 0) {
				model = models.get(0);
				ProjContractFileQuitVO vo = new ProjContractFileQuitVO();
				if(StringUtils.isNotEmpty(model.getStatus())){//流程状态
					vo.setStatusName(codeUtils.getCodeNameByValue("599", model.getStatus()));
				}
				// 协议金额（万元)币种
				if(StringUtils.isNotEmpty(model.getStatus())){
					vo.setThisAgreeAmontCurrName(codeUtils.getCodeNameByValue("103", model.getThisAgreeAmontCurr()));
				}
				//将合同名称类别赋值到list
				List<ProjContractFileQuitUtilModel> ls = new ArrayList<>();
				ProjContractFileQuitUtilModel ls2 = null;
				for (ProjContractFileQuitModel m : models) {
					ls2 = new ProjContractFileQuitUtilModel();
					ls2.setFileTitle(m.getFileTitle());
					ls2.setFileType(m.getFileType());
					// 合同类别
					if(StringUtils.isNotEmpty(m.getFileType())){
						ls2.setFileTypeName(codeUtils.getCodeNameByValue("1024", m.getFileType()));
					}
					ls.add(ls2);
				}
				BeanUtils.copyProperties(vo, model);
				vo.setProjContractFileQuitUtilModel(ls);

				//退出立项主键
				String quitProjId = appId;
				//查询退出立项
				ProjAppInfoQuitModel model_quit = projAppInfoQuitService.selectById(quitProjId);
				if(model_quit != null){
					//退出立项名称
					vo.setQuitName(model_quit.getQuitName());
				}
				ProjInfoModel pf = projInfoService.selectById(model.getProjId());
				if(pf != null){
					//出资主体
					vo.setInveName(pf.getInveName());
					//項目名稱
					vo.setProjName(pf.getProjName());
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
			}
		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}
	@ApiIgnore
	@ApiOperation(value="管理公司退出合同签署新增", notes="管理公司退出合同签署新增", httpMethod = "POST")
	@ApiImplicitParam(name = "Model", value = "Model", required = true, dataType = "ProjContractFileQuitModel")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/addProjContractFileQuitMc", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addProjContractFileQuitMc(@RequestBody ProjContractFileQuitModel Model){
		try {
			if(Model != null){
				String projId =Model.getProjId();
				ProjInfoModel pf_Serch = projInfoService.selectById(Model.getProjId());
				//项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
				String projType = pf_Serch.getProjType();
				String userId = String.valueOf(this.getLoginUser().getUserId());
				//String userId = UimUtilsUserId.getUserId(projType);
				Long deptId = UimUtils.getDept(Long.parseLong(userId)).getId();
				String tag = Model.getTag();
				String appId = Model.getAppId();
				//查询退出决策，取里面的退出方式,1：全部退出；2：部分退出
				String quitWay = null;
				ProjAppInfoQuitModel model_quit = projAppInfoQuitService.selectById(Model.getAppId());
				if(model_quit != null){
					quitWay = model_quit.getQuitWay();
				}
				System.out.println("##投决会审议同意------1：全部退出；2：部分退出-------quitWay---->>"+quitWay);
				String fileId = UUID.randomUUID().toString();
				Model.setFileId(fileId);
				List<ProjContractFileQuitModel> appIdLs = projContractFileService.selectDetailesAppIdList(appId);
				if (appIdLs != null && appIdLs.size() > 0) {
					baseResponse.setStatus("400");
					baseResponse.setMsg("一个退出决策只能选择一次");
					return JSONObject.toJSONString(baseResponse);
				}else{
					if (tag != null && "0".equals(tag)) {
						//提交流程并且保存数据
						IWorkflowManager wm = new WorkflowManager();
						String authid = userId;
						//主键id
						String businessKey = fileId;
						//流程编号
						String processID = "MC_WithdrawalFromContract";
						//流程主题
						String subject = "退出合同签署" + "：" + pf_Serch.getProjName();
						logger.info("##--------------ProjName--->>"+pf_Serch.getProjName()+"<--subject--->"+subject);
						//其他參數
						Map<String, Object> formMap = new HashMap<>();
						formMap.put("taskTitle", subject);
						formMap.put("businessKey", businessKey);
						formMap.put("deptId", deptId);
						formMap.put("projId", projId);
						formMap.put("appId", appId);
						formMap.put("quitWay", quitWay);
						//启动流程
						ProcessInstance inst = wm.startAndSubmit(formMap,"HandleCommand_StartAndSubmit",processID,businessKey,authid);
						if (inst != null) {
							logger.info("##------->流程启动成功<--------##");
							//1：审批中
							Model.setStatus("1");
							Model.setProcessInstId(inst.getId());
							projContractFileService.insertProjContractFileProject(userId,Model);
						} else {
							logger.error("##------->流程启动失败<--------##");
							baseResponse.error("流程启动失败");
						}
					}else if(tag != null && "1".equals(tag)){
						//保存数据
						//0：草稿
						Model.setStatus("0");
						projContractFileService.insertProjContractFileProject(userId,Model);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiIgnore
	@ApiOperation(value="管理公司退出合同签署修改", notes="管理公司退出合同签署修改", httpMethod = "POST")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Model", value = "Model", required = true, dataType = "ProjContractFileQuitModel")
	})
	@PostMapping(value = "/updateProjContractFileQuitMc", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateProjContractFileQuitMc(@RequestBody ProjContractFileQuitModel Model){
		try {
			if(Model !=null){
				String projId = Model.getProjId();
				ProjInfoModel pf_Serch = projInfoService.selectById(Model.getProjId());
				//项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
				String projType = pf_Serch.getProjType();
				IWorkflowManager wm = new WorkflowManager();
				String userId = String.valueOf(this.getLoginUser().getUserId());
				//String userId = UimUtilsUserId.getUserId(projType);
				Long deptId = UimUtils.getDept(Long.parseLong(userId)).getId();
				System.out.println("退出合同发起------当前登录人---》" + userId + "<---->" + deptId);
				//查询退出决策，取里面的退出方式,1：全部退出；2：部分退出
				String quitWay = null;
				ProjAppInfoQuitModel model_quit = projAppInfoQuitService.selectById(Model.getAppId());
				if(model_quit != null){
					quitWay = model_quit.getQuitWay();
				}
				String tag = Model.getTag();
				String appId = Model.getAppId();
				ProjContractFileQuitModel one = projContractFileService.selectDetailes(Model.getFileId());
				if (one != null && !one.getAppId().equals(Model.getAppId())) {
					List<ProjContractFileQuitModel> appIdLs = projContractFileService.selectDetailesAppIdList(Model.getAppId());
					if (appIdLs != null && appIdLs.size() > 0) {
						baseResponse.setStatus("400");
						baseResponse.setMsg("一个退出决策只能选择一次");
						return JSONObject.toJSONString(baseResponse);
					}
				}
				if (tag != null && "0".equals(tag)) {
					//提交流程并且保存数据
					String authid = userId;
					//主键id
					String businessKey = Model.getFileId();
					//流程编号
					String processID = "MC_WithdrawalFromContract";
					//流程主题
					String subject = "退出合同签署" + "：" + pf_Serch.getProjName();
					logger.info("##--------------ProjName--->>" + pf_Serch.getProjName() + "<--subject--->" + subject);
					//其他參數
					Map<String, Object> formMap = new HashMap<>();
					formMap.put("taskTitle", subject);
					formMap.put("businessKey", businessKey);
					formMap.put("deptId", deptId);
					formMap.put("projId", projId);
					formMap.put("appId", appId);
					formMap.put("quitWay", quitWay);
					//启动流程
					ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", processID, businessKey, authid);
					if (inst != null) {
						logger.info("##------->流程启动成功<--------##");
						one.setProcessInstId(inst.getId());
						projContractFileService.updateProjContractFileProject(userId, Model, one);
					} else {
						logger.error("##------->流程启动失败<--------##");
						baseResponse.error("流程启动失败");
					}
				} else if ("1".equals(tag)) {
					projContractFileService.updateProjContractFileProject(userId, Model, one);
				}else if ("3".equals(tag)) {
					projContractFileService.updateProjContractFileProject(userId, Model, one);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	@ApiOperation(value="查询退出立项名称", notes="查询退出立项名称", httpMethod = "GET")
	@GetMapping(value = "/selectProjContractFileQuitTwoMc", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public String selectProjContractFileQuitTwoMC(@RequestParam("pageSize") int pageSize
			, @RequestParam("currPage") int currPage
			, @RequestParam("projId") String projId,@RequestParam("status") String status){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("PROJ_ID", projId);
			if(StringUtils.isNotEmpty(status)){
				searchCondition.addConditionEqualTo("STATUS", status);
			}
			PageInfo<ProjContractFileQuitModel> rows = projContractFileService.selectPageListQuit(searchCondition);
			List<ProjContractFileQuitVO> list = new ArrayList<ProjContractFileQuitVO>();
			HashSet<String> setOnly = new HashSet<String>();
			for(ProjContractFileQuitModel model : rows.getList()){
				String value = model.getAppId();
				if (setOnly.contains(value)) {
					// 重复元素
				} else {
					//不重复
					ProjContractFileQuitVO vo = new ProjContractFileQuitVO();
					BeanUtils.copyProperties(vo, model);
					String quitProjId = model.getAppId();
					//退出立项主键
					ProjAppInfoQuitModel model_quit = projAppInfoQuitService.selectById(quitProjId);
					if(model_quit != null) {
						vo.setQuitProjId(model_quit.getQuitProjId());
						//退出立项id
						vo.setQuitName(model_quit.getQuitName());
						//退出立项名称
					}
					list.add(vo);
					setOnly.add(value);
				}
			}
			dataTablesResponse.setData(list, rows);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}
}

