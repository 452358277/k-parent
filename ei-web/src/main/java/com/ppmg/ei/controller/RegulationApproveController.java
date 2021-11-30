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
import com.ppmg.ei.dto.RegulationApproveDTO;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.FixflowRunTaskinstanceService;
import com.ppmg.ei.service.FundInfoService;
import com.ppmg.ei.service.RegulationApproveCommService;
import com.ppmg.ei.service.RegulationApproveService;
import com.ppmg.ei.vo.RegulationApproveVO;
import io.swagger.annotations.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.*;

@Controller
@Scope("prototype")
@Api(tags={"XXX接口"})
@RequestMapping("/regulationApprove")
public class RegulationApproveController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private RegulationApproveService regulationApproveService;

	@Reference(check = false)
	private SerialnoService serialnoService;


	@Resource(name="codeUtils")
	private CodeUtils codeUtils;


	@Reference(check = false)
	private RegulationApproveCommService regulationApproveCommService;


	@Reference
	private FixflowRunTaskinstanceService fixflowRunTaskinstanceService;


	@Reference
	private FundInfoService  fundInfoService;


	@ApiOperation(value = "合规审查列表", notes = "合规审查列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/regulationList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String regulationList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("fundId") String fundId) {
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			//searchCondition.addConditionEqualTo("CREATE_BY", this.getLoginUserId());
			searchCondition.addConditionNotEqualTo("IS_DELETE", "9");
			searchCondition.addConditionEqualTo("INVE_ID", fundId);


			PageInfo<RegulationApproveModel> rows = regulationApproveService.selectListPage(searchCondition);
			List<RegulationApproveVO> list = new ArrayList<RegulationApproveVO>();
			for (RegulationApproveModel model : rows.getList()) {
				RegulationApproveVO vo = new RegulationApproveVO();
				BeanUtils.copyProperties(vo, model);
				if (StringUtils.isNotEmpty(model.getEntStatus())) {
					vo.setEntStatusName(codeUtils.getCodeNameByValue("249", model.getEntStatus()));
				}
				if (StringUtils.isNotEmpty(model.getFinanceDate())) {
					vo.setFinanceDateName(codeUtils.getCodeNameByValue("203", model.getFinanceDate()));
				}
				if (StringUtils.isNotEmpty(model.getProcessStatus())) {
					vo.setProcessStatusName(codeUtils.getCodeNameByValue("2681", model.getProcessStatus()));
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


	@ApiOperation(value = "合规审查", notes = "合规审查")
	@ApiImplicitParam(name = "RegulationApproveDTO", value = "RegulationApproveDTO", required = true, dataType = "RegulationApproveDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/saveInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveInfo(@RequestBody RegulationApproveDTO dto) {

		try {
			RegulationApproveModel model = new RegulationApproveModel();
			BeanUtils.copyProperties(model, dto);
			model.setUpdateBy(this.getLoginUserId());
			model.setUpdateDt(new Date());
			if(StringUtils.isEmpty(dto.getIraId())){
				String id = serialnoService.getSequence("EI.TZ_T_REGULATION_APPROVE");
				model.setIraId(id);
				regulationApproveService.saveInfo(model,dto.getList());
			}else{
				model.setCreateBy(this.getLoginUserId());
				model.setCreateDt(new Date());
				regulationApproveService.updateInfo(model,dto.getList());
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
	@GetMapping(value = "/delete", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@RequestParam("ids") String ids) {

		try {
			String[] idsArray = ids.split(",");
			regulationApproveService.deleteByIds(Arrays.asList(idsArray), this.getLoginUserId());
		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			baseResponse.error();
			logger.error(e.getMessage(), e);
		}

		return JSONObject.toJSONString(baseResponse);

	}



	@ApiOperation(value="合规审查", notes="合规审查")
	@ApiImplicitParam(name = "id", value = "XXXID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/detainfo/{iraId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detail(@PathVariable(value = "iraId") String iraId){
		try {

			RegulationApproveModel model = regulationApproveService.selectById(iraId);
			RegulationApproveVO vo = new RegulationApproveVO();
			BeanUtils.copyProperties(vo, model);

			if (StringUtils.isNotEmpty(model.getEntStatus())) {
				vo.setEntStatusName(codeUtils.getCodeNameByValue("249", model.getEntStatus()));
			}
			if (StringUtils.isNotEmpty(model.getFinanceDate())) {
				vo.setFinanceDateName(codeUtils.getCodeNameByValue("203", model.getFinanceDate()));
			}

			if(StringUtils.isNotEmpty(model.getProcessInstId())){
				List<Map<String,Object>> listMap=  fixflowRunTaskinstanceService.getFixFlowByTaskId(model.getProcessInstId());
				if(listMap!=null&&listMap.size()>0){
					String taskId=listMap.get(0).get("TASKINSTANCE_ID").toString();
					vo.setTaskId(taskId);
					String deftId=listMap.get(0).get("PROCESSDEFINITION_ID").toString();
					vo.setDeftId(deftId);
				}else{
					vo.setTaskId("");
				}
			}else{
				vo.setTaskId("");
			}
			if(StringUtils.isNotEmpty(model.getInveId())){
			FundInfoModel fundInfoModel=fundInfoService.selectById(model.getInveId());
				if(fundInfoModel!=null){
					vo.setRegName(fundInfoModel.getFundName());
					vo.setMcName(fundInfoModel.getMcName());
				}
			}


			RegulationApproveCommModel reg=new RegulationApproveCommModel();
			reg.setIraId(iraId);
			List<RegulationApproveCommModel> list=regulationApproveCommService.selectList(reg);
			vo.setList(list);
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



	public void startWorkFlow(RegulationApproveModel model) {
		IWorkflowManager wm = new WorkflowManager();
		String fileName ="EI_COMPLIANCE";
		String currUserId = this.getLoginUserId();
		String iraId = model.getIraId();
		String taskTitle ="合规审查："+ model.getEntName() ;
		if (wm.isEnd(iraId, fileName, currUserId)) {
			Map<String, Object> formMap = new HashMap<> ();
			formMap.put("taskTitle", taskTitle);
			formMap.put("businessKey", iraId);
			formMap.put("startUser", currUserId);
			//查询岗位
			AppGroup dept = UimUtils.getDept(Long.parseLong(currUserId));
			formMap.put("dept", dept);
			//查询发起人是几级公司

			ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", fileName, iraId, currUserId);
			if (inst != null) {
				RegulationApproveModel modelStr = new RegulationApproveModel();
				modelStr.setIraId (iraId);
				modelStr.setProcessStatus ("1");
				modelStr.setProcessInstId (inst.getId());
				regulationApproveService.update (modelStr);
			} else {
				throw new SystemException ("流程启动失败，请确认下一节点审批人是否存在");
			}
		}
	}



}

