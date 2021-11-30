package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.core.manage.interfaces.IWorkflowManager;
import com.founder.core.manage.local.WorkflowManager;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.common.utils.ProcessUserUtils;
import com.ppmg.ei.bean.ProjInfoSearchBean;
import com.ppmg.ei.dto.BdTFitRegulationInfoDTO;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.utils.OaConstants;
import com.ppmg.ei.vo.BdTFitRegulationSelfVO;
import com.ppmg.ei.vo.BdTFitRegulationSumVO;
import com.ppmg.ei.vo.ProjInfoVO;
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
@Api(tags={"基金投资合规自查接口"})
public class BdTFitRegulationSelfController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private BdTFitRegulationSelfService bdTFitRegulationSelfService;

	@Reference
	private ProjInfoService projInfoService;

	@Reference
	private FundInfoService fundInfoService;

	@Reference
	private FundMcInfoService fundMcInfoService;

	@Reference
	private ProjContractFileService projContractFileService;

	@Reference
	private FundInveDescService fundInveDescService;

	@Reference
	private ProjAppInfoService projAppInfoService;

    @Reference
    private XjlTPaymentRequestService xjlTPaymentRequestService;

	@Reference
	private FixflowRunTaskinstanceService fixflowRunTaskinstanceService;

	@Reference
	private AppUserService appUserService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;


	@Reference
	private AppUserroleService appUserroleService;

	@ApiOperation(value="基金投资合规自查详细信息", notes="根据url的id来获取基金投资合规自查详细信息")
	@ApiImplicitParam(name = "projId", value = "projId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/bdTFitRegulationInfo/{projId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String bdTFitRegulationInfo(@PathVariable(value = "projId") String projId){
		try {
			ProjInfoModel projInfoModel=projInfoService.selectById(projId);
			if(projInfoModel!=null && StringUtils.isNotEmpty(projInfoModel.getProjObject())){
				ProjInfoVO projInfoVO= new ProjInfoVO();
				BeanUtils.copyProperties(projInfoVO,projInfoModel);

				AppUserroleModel  appUserRole=new AppUserroleModel();
				appUserRole.setUserid(Long.valueOf(this.getLoginUserId()));
				List<AppUserroleModel> list=appUserroleService.selectList(appUserRole);
				mapResponse.put("listAppUserRole", list);

				 mapResponse.put("projInfoModel", projInfoVO);
				 FundInfoModel fundInfoModel=fundInfoService.selectById(projInfoModel.getInveId());
				mapResponse.put("fundInfoModel", fundInfoModel);
				FundInveDescModel fundInveDescModel=fundInveDescService.selectById(projInfoModel.getInveId());
				mapResponse.put("fundInveDescModel", fundInveDescModel);
				BdTFitRegulationSelfModel bdTFitRegulationSelfModel= bdTFitRegulationSelfService.selectById(projInfoModel.getProjId());
				if(bdTFitRegulationSelfModel!=null){
					BdTFitRegulationSelfVO bdf=new BdTFitRegulationSelfVO();
					BeanUtils.copyProperties(bdf,bdTFitRegulationSelfModel);
					if(StringUtils.isNotEmpty(bdTFitRegulationSelfModel.getProcessInstId())){
						List<Map<String,Object>> listMap=  fixflowRunTaskinstanceService.getFixFlowByTaskId(bdTFitRegulationSelfModel.getProcessInstId());
						if(listMap!=null&&listMap.size()>0){
							String taskId=listMap.get(0).get("TASKINSTANCE_ID").toString();
							mapResponse.put("taskId", taskId);
						}else{
							mapResponse.put("taskId", "");
						}
					}else{
						mapResponse.put("taskId", "");
					}
					String a="1";
					String b="2";
					String c="4";
					if(StringUtils.isNotEmpty(projInfoVO.getProjType())){
						if(a.equals(projInfoVO.getProjType())){
							projInfoVO.setIsFundName("否");
						}else if(b.equals(projInfoVO.getProjType())||c.equals(projInfoVO.getProjType())){
							projInfoVO.setIsFundName("是");
						}
						if(c.equals(projInfoVO.getProjType())){
							bdf.setIsSpvName("是");
						}else{
							bdf.setIsSpvName("否");
						}

					}

				/*	if(StringUtils.isNotBlank(bdTFitRegulationSelfModel.getIndustry())){
						String industName=codeUtils.getCodeNameByValue("2043", bdTFitRegulationSelfModel.getIndustry());
						bdf.setIndustryName(StringUtils.isEmpty(industName) ? "" : industName);
					}*/
					bdf.setIndustryName(projInfoModel.getIndustryName());
					mapResponse.put("bdTFitRegulationSelfModel", bdf);
				}
			}
		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataResponse.error(e.getMessage());
		} catch (Exception e) {
			dataResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}


	@ApiOperation(value="更新投资合规自查信息", notes="根据url的id来更新投资合规自查信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "BdTFitRegulationInfoDTO", value = "BdTFitRegulationInfoDTO", required = true, dataType = "BdTFitRegulationInfoDTO")
	})
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/bdTFitRegulationInfo/updateInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateInfo(@RequestBody BdTFitRegulationInfoDTO dto){
		try {
			//判断内部人员是否提交了，提交了或者商河通过了咋不能修改
			   String userId = this.getLoginUserId();
			 //String userId ="1005000";
				ProjInfoModel model = new ProjInfoModel();
				BeanUtils.copyProperties(model,dto);

				FundInfoModel fund=new FundInfoModel();
				BeanUtils.copyProperties(fund,dto);
                fund.setStatus("");
				FundInveDescModel fundInves=new FundInveDescModel();
				BeanUtils.copyProperties(fundInves,dto);

				BdTFitRegulationSelfModel bdt=new BdTFitRegulationSelfModel();
				BeanUtils.copyProperties(bdt,dto);
				//bdTFitRegulationSelf.setCreateBy(String.valueOf(userId));
			   bdTFitRegulationSelfService.operation(fund,model,bdt,fundInves,userId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			dataTablesResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}
	@ApiOperation(value="查询产业基金已报合规性审查项目情况表" , notes="查询产业基金已报合规性审查项目情况表")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/quarterReport/list", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String quarterReport(@RequestBody ProjInfoSearchBean searchBean){
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(searchBean.getProjName())){
				map.put("projName", "%"+searchBean.getProjName()+"%");
			}
			if(StringUtils.isNotEmpty(searchBean.getIsDecisionPass())){
				List<String> isDecisionPass1 = Arrays.asList(searchBean.getIsDecisionPass().split(","));
				map.put("isDecisionPass", isDecisionPass1);
			}
			if(StringUtils.isNotEmpty(searchBean.getIsFit())){
				List<String> isFitS = Arrays.asList(searchBean.getIsFit().split(","));
				map.put("isFit", isFitS);
			}
			if(StringUtils.isNotEmpty(searchBean.getProjType())){
				List<String> PROJ_TYPE = Arrays.asList(searchBean.getProjType().split(","));
				map.put("projTypeS", PROJ_TYPE);
			}
		/*	if(StringUtils.isNotEmpty(searchBean.getProjType())){
				List<String> PROJ_TYPE = Arrays.asList(searchBean.getProjType().split(","));
				searchCondition.addConditionIn("c.PROJ_TYPE", PROJ_TYPE);
			}*/
            //PageInfo<BdTFitRegulationSelfModel> list=bdTFitRegulationSelfService.selectListBybdTFit(map);
            List<BdTFitRegulationSelfModel> list=bdTFitRegulationSelfService.selectListBybdTFit(map);
			List<BdTFitRegulationSelfVO> listvo = new ArrayList<BdTFitRegulationSelfVO>();
			double sumSignAmount=0;
			double signAmount=0;
			double sumIntendedAmount=0;
			double noJssumIntendedAmount=0;
			double noIsTwelve=0;
			double noIsMfield=0;
			if(list!=null&&list.size()>0){
				for(BdTFitRegulationSelfModel bdTFitmodel:list){
					BdTFitRegulationSelfVO bdTFitRegulation=new BdTFitRegulationSelfVO();
					BeanUtils.copyProperties(bdTFitRegulation, bdTFitmodel);
					bdTFitRegulation.setSumActualPayAmount(bdTFitmodel.getSumXjlActualPayAmount());
					bdTFitRegulation.setSignAmount(bdTFitmodel.getSumFileSignAmount());
					if(StringUtils.isNotEmpty(bdTFitmodel.getIsJs())){
						if("0".equals(bdTFitmodel.getIsJs())){
							bdTFitRegulation.setIsJs("否");
						}else if("1".equals(bdTFitmodel.getIsJs())){
							bdTFitRegulation.setIsJs("是");
						}
					}
					if(StringUtils.isNotEmpty(bdTFitmodel.getIsTwelve())){
						if("1".equals(bdTFitmodel.getIsTwelve())){
							bdTFitRegulation.setIsTwelve("是");
						}else {
							bdTFitRegulation.setIsTwelve("否");
						}
					}
					if(StringUtils.isNotEmpty(bdTFitmodel.getIsMfield())){
						if("0".equals(bdTFitmodel.getIsMfield())){
							bdTFitRegulation.setIsMfield("否");
						}else if("1".equals(bdTFitmodel.getIsMfield())){
							bdTFitRegulation.setIsMfield("是");

						}
					}
					if(bdTFitmodel.getCurTmoney()==null){
						bdTFitRegulation.setCurTmoney(0.0);
					}

					ProjInfoModel pro=bdTFitmodel.getProjInfoModel();
					if(pro!=null){
						bdTFitRegulation.setProjName(pro.getProjName());
						if("10".equals(pro.getProjStatus())){
							//是否终止投资
							bdTFitRegulation.setProjStatusName("是");
						}else{
							bdTFitRegulation.setProjStatusName("否");
						}
					}
					if(bdTFitmodel.getFundInfoModel()!=null){
						bdTFitRegulation.setIsFit(bdTFitmodel.getFundInfoModel().getIsFit());
						if("1".equals(bdTFitmodel.getFundInfoModel().getIsFit())){
							bdTFitRegulation.setIsFitName("是");
						}else if("0".equals(bdTFitmodel.getFundInfoModel().getIsFit())){
							bdTFitRegulation.setIsFitName("否");
						}
					}
               /*     List<XjlTPaymentRequestModel> xjltList=xjlTPaymentRequestService.selectByProjId(bdTFitmodel.getProjId());
                    double sumActualPayAmount=0;
                    if(xjltList!=null&&xjltList.size()>0){
                        for(XjlTPaymentRequestModel xjltpaymentrequestmodel:xjltList){
                            if(xjltpaymentrequestmodel.getActualPayAmount()!=null){
                                sumActualPayAmount=sumActualPayAmount+xjltpaymentrequestmodel.getActualPayAmount();
                            }
                        }
                        bdTFitRegulation.setSumActualPayAmount(sumActualPayAmount);
                    }*/
/*					ProjContractFileModel projContractFileModel=new ProjContractFileModel();
					projContractFileModel.setProjId(bdTFitmodel.getProjId());
					Double  amount1= projContractFileService.getSumSignAmount(bdTFitmodel.getProjId(),"");*/
					/*List<ProjContractFileModel> projcontractfile= projContractFileService.selectList(projContractFileModel);
					if(projcontractfile!=null && projcontractfile.size()>0){
						//协议金额
						if(projcontractfile.get(0).getSignAmount()==null){
							signAmount=0;
						}else{
							signAmount=projcontractfile.get(0).getSignAmount();
						}

					}else{
						signAmount=0;
					}*/
					/*if(amount1!=null){
						bdTFitRegulation.setSignAmount(amount1);//协议金额
					}*/
					//bdTFitRegulation.setSignAmount(signAmount);//协议金额
					sumSignAmount=sumSignAmount+signAmount;
					 ProjAppInfoModel projAppInfoModel=bdTFitmodel.getProjAppInfoModel();
					if(projAppInfoModel!=null){
						//项目的id查询投决会情况
						if("1".equals(projAppInfoModel.getIsDecisionPass())){
							bdTFitRegulation.setIsDecisionPass("通过");
							projAppInfoModel.setIsDecisionPass("通过");
						}else if("0".equals(projAppInfoModel.getIsDecisionPass())){
							bdTFitRegulation.setIsDecisionPass("不通过");
							projAppInfoModel.setIsDecisionPass("不通过");
						}
						bdTFitRegulation.setTjResult(projAppInfoModel.getIsDecisionPass());
						if(projAppInfoModel.getIntendedAmount()==null){
							projAppInfoModel.setIntendedAmount(0.0);
						}
						//拟投资金额
						bdTFitRegulation.setIntendedAmount(projAppInfoModel.getIntendedAmount());
						//拟投资总金额
						sumIntendedAmount=sumIntendedAmount+projAppInfoModel.getIntendedAmount();
						if(StringUtils.isNotEmpty(bdTFitmodel.getIsJs())){
                         if("0".equals(bdTFitmodel.getIsJs())){
                         	//不是江苏省内投资总额
							 noJssumIntendedAmount=noJssumIntendedAmount+projAppInfoModel.getIntendedAmount();
						 }
                         if("0".equals(bdTFitmodel.getIsTwelve())){
                         	//江苏省内项目不属于12条（一）情形的投资总金额
							 noIsTwelve=noIsTwelve+projAppInfoModel.getIntendedAmount();
						 }
							if("0".equals(bdTFitmodel.getIsMfield())){
								//江苏省内项目不属于12条（一）情形的投资总金额
								noIsMfield=noIsMfield+projAppInfoModel.getIntendedAmount();
							}
						}
					}

					listvo.add(bdTFitRegulation);
				}
				BdTFitRegulationSumVO sum=new BdTFitRegulationSumVO();
				sum.setSumSignAmount(sumSignAmount);
				sum.setSignAmount(signAmount);
				sum.setNoIsMfield(noIsMfield);
				sum.setNoIsTwelve(noIsTwelve);
				sum.setSumIntendedAmount(sumIntendedAmount);
				sum.setNoJssumIntendedAmount(noJssumIntendedAmount);
				sum.setSumSignAmount(sumSignAmount);
				sum.setListvo(listvo);
				dataResponse.setData(sum);
			}

		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataResponse.error(e.getMessage());
		} catch (Exception e) {
			dataResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}

	@ApiOperation(value="内部用户合规性审查列表", notes="查询现金流记录列表")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/regulationList", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public String regulationList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,@RequestParam("fundId") String  fundId){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			//只有省政府平台基金才有合规性审查
			if(StringUtils.isNotEmpty(fundId)){
				searchCondition.addConditionEqualTo("cc.INVE_ID", fundId);
			}
			//searchCondition.addConditionEqualTo("a.status", "10060");
			PageInfo<BdTFitRegulationSelfModel> rows =bdTFitRegulationSelfService.selectRegulationPage(searchCondition);
			List<BdTFitRegulationSelfVO> list = new ArrayList<BdTFitRegulationSelfVO>();
			for(BdTFitRegulationSelfModel model : rows.getList()){
				BdTFitRegulationSelfVO vo=new BdTFitRegulationSelfVO();
				BeanUtils.copyProperties(vo, model);
				list.add(vo);
			}
			dataTablesResponse.setData(list);
			dataTablesResponse.setTotalCount(Long.valueOf(rows.getTotal()).intValue(), rows.getPageNum(), rows.getPageSize());
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

	@ApiOperation(value = "合规性审查", notes = "合规性审查")
	@ApiImplicitParam(name = "BdTFitRegulationInfoDTO", value = "BdTFitRegulationInfoDTO", required = true, dataType = "RequirementsDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/regulation/saveInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveInfo(@RequestBody BdTFitRegulationInfoDTO model) {

		try {
			startWorkFlow(model);

		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			baseResponse.error();
			logger.error(e.getMessage(), e);
		}

		return JSONObject.toJSONString(baseResponse);

	}



	private void  startWorkFlow(BdTFitRegulationInfoDTO model) {
		BdTFitRegulationSelfModel bdt=new BdTFitRegulationSelfModel();
		IWorkflowManager wm = new WorkflowManager();
		String fileName = "ei_regulation";
		String currUserId = this.getLoginUserId();
		String id = model.getProjId();
		String taskTitle = "合规性审查:"+model.getProjName() ;
		if (wm.isEnd(id, fileName, currUserId)) {
			Map<String, Object> formMap = new HashMap<>();
			formMap.put("taskTitle", taskTitle);
			formMap.put("businessKey", id);
			AppUserModel mo= appUserService.selectById(currUserId);
			Long deptId=mo.getDeptid();
			//AppGroup dept = UimUtils.getDept(Long.parseLong(currUserId));
			//部门负责人
			String departmentManager = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(deptId, OaConstants.DEPARTMENT_MANAGER_POST_ID);
			formMap.put("departmentManager", departmentManager);
			//分管副总
			String divisionVicePresident = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(deptId, OaConstants.DIVISION_VICE_PRESIDENT_POST_ID);
			formMap.put("divisionVicePresident", divisionVicePresident);
			//发起人
			formMap.put("startUser", currUserId);

			ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", fileName, id, currUserId);
			if (inst != null) {
				//"提交成功！"
				bdt.setProcessInstId(inst.getId());
				bdt.setFlowStatus("1");
				bdt.setProjId(model.getProjId());
				bdTFitRegulationSelfService.update(bdt);
				//已报合规
			} else {
				throw new SystemException("流程启动失败，请确认下一节点审批人是否存在");
			}
		}
	}



}

