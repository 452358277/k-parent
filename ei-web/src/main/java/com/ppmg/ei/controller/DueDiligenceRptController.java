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
import com.ppmg.ei.dto.DueDiligenceRptDTO;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.utils.OaConstants;
import com.ppmg.ei.vo.DueDiligenceRptVO;
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
@Api(tags={"组织尽调接口"})
@RequestMapping("/dueDiligenceRpt")
public class DueDiligenceRptController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private DueDiligenceRptService dueDiligenceRptService;

	@Reference(check = false)
	private SerialnoService serialnoService;

    @Reference
    private SurveyPlanService surveyPlanService;


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

	@ApiOperation(value="组织尽调列表", notes="组织尽调报告")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/dueDiligenceRptList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String dueDiligenceRptList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,String projId){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionNotEqualTo("STATUS","9");
			if(StringUtils.isNotEmpty(projId)){
				searchCondition.addConditionEqualTo("proj_id",projId);
			}

			PageInfo<DueDiligenceRptModel> rows = dueDiligenceRptService.selectListPage(searchCondition);
			List<DueDiligenceRptVO> list = new ArrayList<DueDiligenceRptVO>();
			for(DueDiligenceRptModel model : rows.getList()){
				DueDiligenceRptVO vo = new DueDiligenceRptVO();
				BeanUtils.copyProperties(vo, model);
				if(StringUtils.isNotEmpty(model.getStatus())){
					String  statusName=codeUtils.getCodeNameByValue("264",model.getStatus());
					vo.setStatusName(statusName==null?"":statusName);

				}
                SurveyPlanModel surveyPlanModel=surveyPlanService.selectById(model.getSurveyPlanId());
                if(surveyPlanModel!=null){
                   String surveyContentName="";
                    if(StringUtils.isNotEmpty(surveyPlanModel.getSurveyContent())){
                        List<String> listS = Arrays.asList(surveyPlanModel.getSurveyContent().split(","));
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
                    vo.setSurveyContentName(surveyContentName);
                }
                vo.setSurveyPlanModel(surveyPlanModel);
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
	@ApiOperation(value = "新增尽职调查报告", notes = "新增尽职调查报告")
	@ApiImplicitParam(name = "SurveyPlanDTO", value = "SurveyPlanDTO", required = true, dataType = "RequirementsDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/saveInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveInfo(@RequestBody DueDiligenceRptDTO dto) {

		try {
			DueDiligenceRptModel model = new DueDiligenceRptModel();
			BeanUtils.copyProperties(model, dto);

			SurverCooorgModel surverCooorgVO=new SurverCooorgModel();
			BeanUtils.copyProperties(surverCooorgVO, dto);

			model.setUpdateBy(this.getLoginUserId());
			model.setUpdateDt(new Date());
			if(StringUtils.isNotEmpty(dto.getRptId())){
			/*	if(StringUtils.isNotEmpty(dto.getSurveyPlanId())){
					SurveyPlanModel surveyPlanModel=surveyPlanService.selectById(dto.getSurveyPlanId());
					if(surveyPlanModel!=null){
                        //排除自身
					}
				}*/

				dueDiligenceRptService.updateByDue(model,dto.getList());
			}else{
			/*	if(StringUtils.isNotEmpty(dto.getSurveyPlanId())){
					SurveyPlanModel surveyPlanModel=surveyPlanService.selectById(dto.getSurveyPlanId());
					if(surveyPlanModel!=null){

					}
				}*/
				String id = serialnoService.getSequence("EI.TZ_T_DUE_DILIGENCE_RPT");
				model.setCreateBy(this.getLoginUserId());
				model.setCreateDt(new Date());
				model.setUpdateDt(new Date());
				model.setUpdateBy(this.getLoginUserId());
				model.setRptId(id);
				//todo 必须是流程结束的才可以重新发起这条
				dueDiligenceRptService.insertByDue(model,dto.getList());
				//前台传status="0";
			}
			if(dto.getIsStartFlow()) {//如果点击是提交则添加流程
				SurveyPlanModel surveyPlanModel=surveyPlanService.selectById(model.getSurveyPlanId());
				startWorkFlow(model,surveyPlanModel);
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

    @ApiOperation(value="详情", notes="根据url的详情")
    @ApiImplicitParam(name = "rptId", value = "rptId", required = true, dataType = "String", paramType = "path")
    @ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/detainfo/{rptId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String detail(@PathVariable(value = "rptId") String rptId){
        try {
            SurveyPlanVO vo = new SurveyPlanVO();
            DueDiligenceRptModel dueDiligenceRpt=dueDiligenceRptService.selectById(rptId);
            if(dueDiligenceRpt!=null){
				if(StringUtils.isNotEmpty(dueDiligenceRpt.getProcessInstId())){
					List<Map<String,Object>> listMap=  fixflowRunTaskinstanceService.getFixFlowByTaskId(dueDiligenceRpt.getProcessInstId());
					if(listMap!=null&&listMap.size()>0){
						String taskId=listMap.get(0).get("TASKINSTANCE_ID").toString();
						vo.setTaskId(taskId);
					}else{
						vo.setTaskId("");
					}
				}else{
					vo.setTaskId("");
				}
               if(StringUtils.isNotEmpty(dueDiligenceRpt.getSurveyPlanId())){
                   SurveyPlanModel model=surveyPlanService.selectById(dueDiligenceRpt.getSurveyPlanId());
                   BeanUtils.copyProperties(vo, model);
                   vo.setSurveyPlanModel(model);
				   vo.setDueDiligenceRpt(dueDiligenceRpt);
                   if(model!=null){
                       if(StringUtils.isNotEmpty(model.getInterior())){
                           String  interiorName=codeUtils.getCodeNameByValue("3434",model.getInterior());
                           vo.setInteriorName(interiorName==null?"":interiorName);
                       }
                       String surveyContentName="";
                       if(StringUtils.isNotEmpty(model.getSurveyContent())){
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

                       }
                       vo.setSurveyContentName(surveyContentName);
                   }

                   SurveyPlanUsersModel surveyPlanUsersModel=new SurveyPlanUsersModel();
                   surveyPlanUsersModel.setPlanId(dueDiligenceRpt.getSurveyPlanId());
                   List<SurveyPlanUsersModel> list= surveyPlanUsersService.selectList(surveyPlanUsersModel);
                   vo.setList(list);
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
	@ApiOperation(value="组织尽调修改/删除", notes="尽职调查计划修改、删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "rptId", value = "rptId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "SurveyPlanVO", value = "SurveyPlanVO", required = true, dataType = "SurveyPlanVO")
	})
	@PostMapping(value = "/urveyPlan/update/{rptId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update(@PathVariable("rptId") String rptId, @RequestBody DueDiligenceRptVO dto){
		try {
			DueDiligenceRptModel model = new DueDiligenceRptModel();
			BeanUtils.copyProperties(model, dto);
			model.setRptId(rptId);
			model.setUpdateBy(this.getLoginUserId());
			model.setUpdateDt(new Date());
			dueDiligenceRptService.update(model);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	@ApiOperation(value = "删除尽职调查计划", notes = "删除尽职调查计划")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids", value = "计划审核ID", required = true, dataType = "String"),
	})
	@GetMapping(value = "/urveyPlan/delete", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@RequestParam("ids") String ids) {
		try {
			String[] idsArray = ids.split(",");
			dueDiligenceRptService.deleteByIds(Arrays.asList(idsArray), this.getLoginUserId());
		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			baseResponse.error();
			logger.error(e.getMessage(), e);
		}

		return JSONObject.toJSONString(baseResponse);

	}


	private void startWorkFlow(DueDiligenceRptModel model,SurveyPlanModel surveyPlanModel) {
		IWorkflowManager wm = new WorkflowManager();
		String fileName = "ei_dueDiligenceRpt";
		if(StringUtils.isNotEmpty(model.getProjId())){
			ProjInfoModel projInfoModel=projInfoService.selectById(model.getProjId());
			if(projInfoModel!=null&&"1".equals(projInfoModel.getProjType())){
				fileName = "ei_dueDiligenceRpt_pro";
			}
		}

		String currUserId = getLoginUserId();
		String id = model.getRptId();
		String taskTitle ="";
		if(surveyPlanModel!=null){
			 taskTitle = "组织尽调:" +surveyPlanModel.getPlanName() ;
		}
		if (wm.isEnd(id, fileName, currUserId)) {
			Map<String, Object> formMap = new HashMap<>();
			formMap.put("taskTitle", taskTitle);
			formMap.put("businessKey", id);
			formMap.put("isMeeting", model.getIsMeeting());
			AppGroup dept = UimUtils.getDept(Long.parseLong(currUserId));
			//部门负责人
			String departmentManager = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(dept.getId(), OaConstants.DEPARTMENT_MANAGER_POST_ID);
			formMap.put("departmentManager", departmentManager);
          //分管领导
			String ducManager = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(dept.getId(),103);
			formMap.put("ducManager", ducManager);

			//风控经理10009
			List<String> list = UimUtils.getUserIdByRoleId(10009L);
			String users = "";
			for (String s : list) {
				users += s + ",";
			}
			if (users.length() > 0) {
				users = users.substring(0, users.length() - 1);
			}
			formMap.put("divisionManager", users);

            //项目经理10010
			//项目经理 10010，和10011
			List<String> projectManagerList = UimUtils.getUserIdByRoleId(10010L);
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

			ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", fileName, id, currUserId);
			if (inst != null) {
				//"提交成功！"
				model.setProcessInstId(inst.getId());
				dueDiligenceRptService.update(model);
			} else {
				throw new SystemException("流程启动失败，请确认下一节点审批人是否存在");
			}
		}
	}


}

