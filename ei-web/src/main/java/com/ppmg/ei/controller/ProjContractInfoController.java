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
import com.ppmg.ei.dto.ProjContractInfoFileDTO;
import com.ppmg.ei.model.AppUserModel;
import com.ppmg.ei.model.ProjContractFileModel;
import com.ppmg.ei.model.ProjContractInfoModel;
import com.ppmg.ei.service.AppUserService;
import com.ppmg.ei.service.FixflowRunTaskinstanceService;
import com.ppmg.ei.service.ProjContractFileService;
import com.ppmg.ei.service.ProjContractInfoService;
import com.ppmg.ei.utils.OaConstants;
import com.ppmg.ei.vo.ProjContractFileListLcVO;
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
@Api(tags={"项目投管接口"})
@RequestMapping("/projContractInfo")
public class ProjContractInfoController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private ProjContractInfoService projContractInfoService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Reference
	private ProjContractFileService projContractFileService;

	@Reference
	private FixflowRunTaskinstanceService fixflowRunTaskinstanceService;

	@Reference
	private AppUserService appUserService;

	@ApiIgnore
	@ApiOperation(value="项目投管-新增合同", notes="项目投管-新增合同")
	@ApiImplicitParam(name = "ProjContractFileDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/projContractFile/projSave", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String projSave(@RequestBody ProjContractInfoFileDTO dto){
		try {
			ProjContractInfoModel vo=new ProjContractInfoModel();
			BeanUtils.copyProperties(vo, dto);
			String userId=this.getLoginUserId();
			//String userId="112";
			if(dto.getList()!=null&&dto.getList().size()>0){
				HashSet<String> setOnly = new HashSet<String>();
				for(ProjContractFileModel projContractFileModel:dto.getList()){
					String value=projContractFileModel.getFileTitle();
					if (setOnly.contains(value)) {
						// 重复元素
						baseResponse.setMsg("合同名称为"+projContractFileModel.getFileTitle()+"重复");
						return JSONObject.toJSONString(baseResponse);
					} else {
						setOnly.add(value);
					}
				}
			}
			if(StringUtils.isNotEmpty(dto.getId())){
				//修改
				if(dto.getList()!=null&&dto.getList().size()>0) {
					for (ProjContractFileModel projContractFileModel : dto.getList()) {
						//判断是否之前存在
						if (StringUtils.isNotEmpty(projContractFileModel.getFileId())) {

							if (StringUtils.isNotEmpty(projContractFileModel.getFileId())) {
								ProjContractFileModel fileInfo = projContractFileService.selectById(projContractFileModel.getFileId());
								if (fileInfo != null) {
									//判断已存在的合同修改了名字
									if (!fileInfo.getFileTitle().equals(projContractFileModel.getFileTitle())) {
										ProjContractFileModel projFile = new ProjContractFileModel();
										projFile.setFileTitle(projContractFileModel.getFileTitle());
										projFile.setProjId(dto.getProjId());
										List<ProjContractFileModel> list = projContractFileService.selectList(projFile);
										if (list != null && list.size() > 0) {
											baseResponse.setMsg("合同名称为" + projContractFileModel.getFileTitle() + "已存在");
											return JSONObject.toJSONString(baseResponse);
										}
									}
								}
							} else {
								//先判断 修改的时候先删除原来的名字，又新增原来的名字 这样导致没有 fileid
								//做法，先判断新加是否原来就有
								ProjContractFileModel projD = new ProjContractFileModel();
								projD.setFileInfoId(dto.getId());
								List<ProjContractFileModel> listprojD = projContractFileService.selectList(projD);
								if (listprojD != null && listprojD.size() > 0) {
									HashSet<String> setFileTile = new HashSet<String>();
									for (ProjContractFileModel prF : listprojD) {
										setFileTile.add(prF.getFileTitle());
									}
									//判断编辑的时候，把原来的删除，新增一个一样的
									if (setFileTile.contains(projContractFileModel.getFileTitle()) == false) {
										ProjContractFileModel projFile = new ProjContractFileModel();
										projFile.setFileTitle(projContractFileModel.getFileTitle());
										projFile.setProjId(dto.getProjId());
										List<ProjContractFileModel> list = projContractFileService.selectList(projFile);
										if (list != null && list.size() > 0) {
											baseResponse.setMsg("合同名称为" + projContractFileModel.getFileTitle() + "已存在");
											return JSONObject.toJSONString(baseResponse);
										}
									}
								}


							}

						}

					}
				}
				projContractInfoService.updateByproj(vo,dto.getList(),this.getLoginUserId(),dto.getMajorTerm(),dto.getContractFile(),dto.getGroupId(),dto.getSignDt(),dto.getFinalFile());
			 }else{
				if(dto.getList()!=null&&dto.getList().size()>0){
					for(ProjContractFileModel projContractFileModel:dto.getList()){
						ProjContractFileModel projFile=new ProjContractFileModel();
						projFile.setFileTitle(projContractFileModel.getFileTitle());
						projFile.setProjId(dto.getProjId());
						System.out.println("项目的id为"+dto.getProjId());
						List<ProjContractFileModel> list= projContractFileService.selectList(projFile);
						if(list!=null&&list.size()>0){
							baseResponse.setMsg("合同名称为"+projContractFileModel.getFileTitle()+"已存在");
							return JSONObject.toJSONString(baseResponse);
						}
					}


				}
				String  id=serialnoService.getSequence("EI.TZ_T_PROJ_CONTRACT_INFO");
				vo.setId(id);
				projContractInfoService.insertByproj(vo,dto.getList(),this.getLoginUserId(),dto.getMajorTerm(),dto.getContractFile());
			}

			if(dto.getIsStartFlow()!=null){
				if(dto.getIsStartFlow()) {
					//如果点击是提交则添加流程
					startWorkFlow(vo.getId(),vo.getProjId());
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiOperation(value="项目投管-合同签署详情", notes="详情")
	@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/projContractFile/projDetainfo/{id}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String projDetainfo(@PathVariable(value = "id") String id){
		try {
			ProjContractInfoModel conFile=projContractInfoService.selectById(id);
			if(conFile!=null){
				if(StringUtils.isNotEmpty(conFile.getProcessInstId())){
					List<Map<String,Object>> listMap=  fixflowRunTaskinstanceService.getFixFlowByTaskId(conFile.getProcessInstId());
					if(listMap!=null&&listMap.size()>0){
						String taskId=listMap.get(0).get("TASKINSTANCE_ID").toString();
						mapResponse.put("taskId",taskId);
					}else{
						mapResponse.put("taskId","");
					}
				}else{
					mapResponse.put("taskId","");
				}
			}
			ProjContractFileModel projContractFile=new ProjContractFileModel();
			projContractFile.setFileInfoId(id);
			List<ProjContractFileModel> list = projContractFileService.selectList(projContractFile);
			if(list!=null&&list.size()>0){
				for(ProjContractFileModel model:list){
					if(StringUtils.isNotEmpty(model.getFileType())){
						model.setFileTypeName(codeUtils.getCodeNameByValue("238", model.getFileType()));
					}
					if(StringUtils.isNotEmpty(model.getStatus())){
						String  statusName=codeUtils.getCodeNameByValue("264",model.getStatus());
						model.setStatusName(statusName==null?"":statusName);
					}
					if(StringUtils.isNotEmpty(model.getSignAmountCurr())){
						String  signAmountCurrName=codeUtils.getCodeNameByValue("103",model.getSignAmountCurr());
						model.setSignAmountCurrName(signAmountCurrName==null?"":signAmountCurrName);
					}
					/*if(StringUtils.isNotEmpty(model.getSignAmountCurr())){
						String  signAmountCurrName=codeUtils.getCodeNameByValue("103",model.getSignAmountCurr());
						model.setSignAmountCurrName(signAmountCurrName==null?"":signAmountCurrName);
					}*/

				}
				ProjContractFileModel projContractFileModel1=list.get(0);
				mapResponse.put("ProjContractFile",projContractFileModel1);
			}

			mapResponse.put("ProjContractInfoModel",conFile);
			mapResponse.put("list",list);
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
	@ApiOperation(value="项目投管合同/删除", notes="项目投管合同、删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "fileId", value = "fileId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "ProjContractFileListLcVO", value = "ProjContractFileListLcVO", required = true, dataType = "ProjContractFileListLcVO")
	})
	@GetMapping(value = "/projContractFileInfo/delete/{id}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@PathVariable("id") String id){
		try {
			projContractInfoService.updateByInfo(id,this.getLoginUserId());
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
    @GetMapping(value = "/projContractFileInfoList", produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String projContractFileInfoList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,String groupId,String projId){

        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            if(StringUtils.isNotEmpty(groupId)){
                searchCondition.addConditionEqualTo("GROUP_ID", groupId);
            }
            if(StringUtils.isNotEmpty(projId)){
				searchCondition.addConditionEqualTo("PROJ_ID", projId);
			}
            searchCondition.addConditionNotEqualTo("STATUS", "9");
            //searchCondition.addConditionEqualTo("create_by",this.getLoginUserId());
			//projContractInfoService.selectListByInfoPage(searchCondition);
            PageInfo<ProjContractFileModel> rows = projContractFileService.selectByListPage(searchCondition);
            List<ProjContractFileListLcVO> list = new ArrayList<ProjContractFileListLcVO>();
            for(ProjContractFileModel model : rows.getList()){
                ProjContractFileListLcVO vo = new ProjContractFileListLcVO();
                BeanUtils.copyProperties(vo, model);
                //查询创建人
				if(StringUtils.isNotEmpty(model.getCreateBy())){
					AppUserModel  appUserModel=appUserService.selectById(model.getCreateBy());
					if(appUserModel!=null){
						vo.setLoginName(appUserModel.getName());
					}

				}

                if(StringUtils.isNotEmpty(model.getFileType())){
                    vo.setFileTypeName(codeUtils.getCodeNameByValue("238", model.getFileType()));
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

	private void startWorkFlow(String bussKeyId,String projId) {
		String name= projContractFileService.selectGetName(bussKeyId);
		IWorkflowManager wm = new WorkflowManager();
		String fileName = "ei_contract_proj";
		String currUserId = this.getLoginUserId();
		String id = bussKeyId;
		String taskTitle =  "合同签署："+name;
		if (wm.isEnd(id, fileName, currUserId)) {
			Map<String, Object> formMap = new HashMap<>();
			formMap.put("taskTitle", taskTitle);
			formMap.put("businessKey", id);
			formMap.put("projId", projId);
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
				ProjContractInfoModel projContractInfo1=new ProjContractInfoModel();
				projContractInfo1.setId(bussKeyId);
				projContractInfo1.setProcessInstId(inst.getId());
				projContractInfo1.setStatus("1");
				projContractInfoService.update(projContractInfo1);
			} else {
				throw new SystemException("流程启动失败，请确认下一节点审批人是否存在");
			}
		}
	}

}

