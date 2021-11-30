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
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.common.utils.ProcessUserUtils;
import com.ppmg.ei.dto.XjlTPaymentRequestDTO;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.utils.OaConstants;
import com.ppmg.ei.vo.XjlTPaymentRequestListVO;
import com.ppmg.ei.vo.XjlTPaymentRequestVO;
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
@Api(tags={"投资资金支付相关接口"})
public class XjlTPaymentRequestController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private XjlTPaymentRequestService xjlTPaymentRequestService;

	@Reference
	private AppUserService appUserService;

	@Reference
	private ProjAppInfoService projAppInfoService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@Reference
	private FundInfoService fundInfoService;

	@Reference
	private FixflowRunTaskinstanceService fixflowRunTaskinstanceService;

	@Reference
	private ProjInfoService projInfoService;

    @Reference(check = false)
    private SerialnoService serialnoService;

	@Reference
	private HandleRecordService handleRecordService;

	@Reference(check=false)
	private BdTFitRegulationSelfService bdTFitRegulationSelfService;

	@ApiOperation(value="获取投资资金支付列表(APP通用)", notes="获取投资资金支付列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
		@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/XjlTPaymentRequestList", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public String XjlTPaymentRequestList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("projId") String projId){
		
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("PROJECT_OR_FUND_ID", projId);
			searchCondition.addConditionNotEqualTo("STATUS", "9");
			PageInfo<XjlTPaymentRequestModel> rows = xjlTPaymentRequestService.selectListPage(searchCondition);
			List<XjlTPaymentRequestListVO> list = new ArrayList<XjlTPaymentRequestListVO>();
			for(XjlTPaymentRequestModel model : rows.getList()){
				XjlTPaymentRequestListVO vo = new XjlTPaymentRequestListVO();
				BeanUtils.copyProperties(vo, model);
				if(StringUtils.isNotEmpty(model.getStatus())){
					vo.setStatusName(codeUtils.getCodeNameByValue("264", model.getStatus()));
				}
				if(StringUtils.isNotEmpty(model.getFinanceType())){
					vo.setFinanceTypeName(codeUtils.getCodeNameByValue("1003", model.getFinanceType()));
				}
				if(StringUtils.isNotEmpty(model.getAmountType())){
					vo.setAmountTypeName(codeUtils.getCodeNameByValue("10031", model.getAmountType()));
				}

				if(StringUtils.isNotEmpty(model.getPayCurrency())){
					vo.setPayCurrencyName(codeUtils.getCodeNameByValue("103", model.getPayCurrency()));
				}

			     ProjAppInfoModel	projAppInfoModel=projAppInfoService.selectById(projId);
                 if(projAppInfoModel!=null){
					 vo.setInveCurrentAmount(projAppInfoModel.getInveCurrentAmount());
					 vo.setIntendedAmount(projAppInfoModel.getIntendedAmount());
				}

				//查询合规性审查
				FundInfoModel fundInfoModel=fundInfoService.selectById(model.getInvestorId());
				if(fundInfoModel!=null){
					String isFitName="";
					String isFit=fundInfoModel.getIsFit();
					if("0".equals(isFit)){
						isFitName="无需合规审查";
					}else if("1".equals(isFit)){
						BdTFitRegulationSelfModel bd=bdTFitRegulationSelfService.selectById(fundInfoModel.getFundId());
						if(bd!=null){
							if("0".equals(bd.getFlowStatus())){
								isFitName="草稿";
							}else if("1".equals(bd.getFlowStatus())){
								isFitName="审核中";
							}else if("4".equals(bd.getFlowStatus())){
								isFitName="已通过";
							}
						}else{
							isFitName="无";
						}
						//isFitName="已通过";
					}
					vo.setIsFitName(isFitName);
				}
				//vo.setFinanceType(codeUtils.getCodeNameByValue("62000", model.getFinanceType()));
				//vo.setAppUser(appUserService.selectById(model.getAppUser()).getName());
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
	@ApiOperation(value="新增投资款拨付", notes="新增投资款拨付")
	@ApiImplicitParam(name = "ProjContractFileDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/paymentRequest/add", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(@RequestBody XjlTPaymentRequestDTO dto){
		try {
			String userId=this.getLoginUserId();
			XjlTPaymentRequestModel xjlTPaymentRequestModel=new XjlTPaymentRequestModel();
			BeanUtils.copyProperties(xjlTPaymentRequestModel,dto);
			//累计付款金额
		   if(StringUtils.isNotEmpty(dto.getPqId())){
               xjlTPaymentRequestModel.setUpdateBy(userId);
               xjlTPaymentRequestModel.setUpdateDt(new Date());
               xjlTPaymentRequestService.update(xjlTPaymentRequestModel);
               //传参数 type='1' ,表示是流程里面的修改
			   if("1".equals(dto.getType())){
			   	//是流程中的修改
				   if(!dto.getPayeeName().equals(dto.getOldPayeeName())){
					   HandleRecordModel handleRecordModel=new HandleRecordModel();
					   String pkId = serialnoService.getSequence("EI.SEQ_EI_MY_HANDLE_RECORD");
					   handleRecordModel.setPkId(pkId);
					   handleRecordModel.setProjectOrFundId(dto.getProjectOrFundId());
					   handleRecordModel.setContent(dto.getPayeeName());
					   handleRecordModel.setContentOld(dto.getOldPayeeName());
					   handleRecordModel.setColumResource("关键人士");
					   handleRecordModel.setColumName("KEY_PERSON_LOCK");
					   handleRecordModel.setUpdateBy("1");
					   handleRecordModel.setUpdateDt(new Date());
					   handleRecordService.insert(handleRecordModel);
				   }

			   }
           }else{
			   Double doub=0.0;
				if("2".equals(dto.getGroupId())||("1".equals(dto.getGroupId())&& "10008".equals(dto.getMcId()))||"no58".equals(dto.getGroupType())){
					 doub=xjlTPaymentRequestService.selectSumPass(dto.getProjectOrFundId());
					if(doub!=null){
						if(xjlTPaymentRequestModel.getActualPayAmount()!=null){
							xjlTPaymentRequestModel.setSumInveAmount(doub+xjlTPaymentRequestModel.getRmbActualPayAmount());
						}
					}else{
						xjlTPaymentRequestModel.setSumInveAmount(0.0);
					}
				}else{
					//58上
					 doub=xjlTPaymentRequestService.selectCountSum(dto.getProjectOrFundId());
					if(doub!=null){
						if(xjlTPaymentRequestModel.getActualPayAmount()!=null){
							xjlTPaymentRequestModel.setSumInveAmount(doub+xjlTPaymentRequestModel.getActualPayAmount());
						}
					}else{
						xjlTPaymentRequestModel.setSumInveAmount(xjlTPaymentRequestModel.getActualPayAmount());
					}
				}


               String pqId=serialnoService.getSequence("EI.XJL_T_PAYMENT_REQUEST");
               xjlTPaymentRequestModel.setPqId(pqId);
               xjlTPaymentRequestService.insertByPqId(xjlTPaymentRequestModel,userId);
           }

            if(dto.getIsStartFlow()!=null){
                if(dto.getIsStartFlow()){//如果点击是提交则添加流程
                   startWorkFlow(xjlTPaymentRequestModel,dto.getFundId(),"");
                }
            }
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

    private void startWorkFlow(XjlTPaymentRequestModel xjlTPaymentRequestModel,String fundId,String Type) {
        IWorkflowManager wm = new WorkflowManager();
		String fileName ="";
		String nameStr ="";
		String name ="";
		FundInfoModel fundInfo=fundInfoService.selectById(fundId);
		if("4".equals(xjlTPaymentRequestModel.getGroupId())){
			fileName = "ei_inve_desc_pro";
			nameStr="管理公司出资：";
			if(StringUtils.isNotEmpty(xjlTPaymentRequestModel.getProjectOrFundId())){
				ProjInfoModel pro=	projInfoService.selectById(xjlTPaymentRequestModel.getProjectOrFundId());
				if(pro!=null){
					name=pro.getProjName();
				}
			}
		}else{
			if("1".equals(Type)){
				fileName = "ei_inve_desc_pro";
				nameStr="项目出资：";
				if(StringUtils.isNotEmpty(xjlTPaymentRequestModel.getProjectOrFundId())){
					ProjInfoModel pro=	projInfoService.selectById(xjlTPaymentRequestModel.getProjectOrFundId());
					if(pro!=null){
						name=pro.getProjName();
					}
				}
			}else{
				fileName = "ei_inve_desc";
				nameStr="基金出资：";
				if(fundInfo!=null){
					name=fundInfo.getFundName();
				}
			}
		}

        String currUserId = this.getLoginUserId();
        String id = xjlTPaymentRequestModel.getPqId();


       /* if(StringUtils.isNotEmpty(xjlTPaymentRequestModel.getInvestor())){
            name=xjlTPaymentRequestModel.getInvestor();
        }*/
        //todo项目名称
        String taskTitle =nameStr+ name;
        if (wm.isEnd(id, fileName, currUserId)) {
            Map<String, Object> formMap = new HashMap<>();
            formMap.put("taskTitle", taskTitle);
            formMap.put("businessKey", id);
            formMap.put("projId", xjlTPaymentRequestModel.getProjectOrFundId());
            formMap.put("fundId", fundId);


			AppUserModel mo= appUserService.selectById(currUserId);
			Long deptId=mo.getDeptid();
           // AppGroup dept = UimUtils.getDept(Long.parseLong(currUserId));
            //部门经理
            String departmentManager = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(deptId, OaConstants.DEPARTMENT_MANAGER_POST_ID);
            formMap.put("departmentManager", departmentManager);
            //分管副总
            String divisionVicePresident = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(deptId, OaConstants.DIVISION_VICE_PRESIDENT_POST_ID);
            formMap.put("divisionVicePresident", divisionVicePresident);


            List<String> financeList = UimUtils.getUserIdByRoleId(10008L);
            String finance = "";
            for (String s : financeList) {
                finance += s + ",";
            }
            if (finance.length() > 0) {
                finance = finance.substring(0, finance.length() - 1);
            }
            //财务审批
            formMap.put("finance", finance);

            //总经理10005
            List<String> generalManagerList = UimUtils.getUserIdByRoleId(10005L);
            String generalManager = "";
            for (String s : generalManagerList) {
                generalManager += s + ",";
            }
            if (generalManager.length() > 0) {
                generalManager = generalManager.substring(0, generalManager.length() - 1);
            }
            //总经理10005
            formMap.put("generalManager", generalManager);

            //发起人
            formMap.put("startUser", currUserId);

            ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", fileName, id, currUserId);
            if (inst != null) {
                //"提交成功！"
                xjlTPaymentRequestModel.setProcessInstId(inst.getId());
				xjlTPaymentRequestModel.setStatus("1");
                xjlTPaymentRequestService.update(xjlTPaymentRequestModel);

            } else {
                throw new SystemException("流程启动失败，请确认下一节点审批人是否存在");
            }
        }
    }

	@ApiOperation(value="新增投资款拨付带出初始值", notes="新增投资款拨付带出初始值")
	@ApiImplicitParam(name = "projId", value = "pqId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/paymentRequest/addInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addInfo(@RequestParam("projId")String projId,String inveId){
		try {
			ProjInfoModel proj=projInfoService.selectById(projId);
			if(proj!=null){
				//查询合规性审查
				FundInfoModel fundInfoModel=fundInfoService.selectById(proj.getInveId());
				if(fundInfoModel!=null){
					String isFitName="";
					String isFit=fundInfoModel.getIsFit();
					if("0".equals(isFit)){
						isFitName="无需合规审查";
					}else if("1".equals(isFit)){
						BdTFitRegulationSelfModel bd=bdTFitRegulationSelfService.selectById(fundInfoModel.getFundId());
						if(bd!=null){
							if("0".equals(bd.getFlowStatus())){
								isFitName="草稿";
							}else if("1".equals(bd.getFlowStatus())){
								isFitName="审核中";
							}else if("4".equals(bd.getFlowStatus())){
								isFitName="已通过";
							}
						}else{
							isFitName="无";
						}
						//isFitName="已通过";
					}else{

					}
					mapResponse.put("isFitName",isFitName);
				}
			}
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
			String time=sim.format(new Date());
			mapResponse.put("newTime",time);
			String userName=this.getLoginUser().getName();
			mapResponse.put("userName",userName);
			ProjAppInfoModel projAppInfoModel=projAppInfoService.selectById(projId);
            //ProjInfoModel projInfoModel=projInfoService.selectById(projId);
			if(projAppInfoModel!=null){
				mapResponse.put("actualAmount",projAppInfoModel.getActualAmount());
			}else{
				mapResponse.put("actualAmount","0");
			}
			if(projAppInfoModel!=null&&projAppInfoModel.getInveCurrentAmount()!=null){
				mapResponse.put("inveCurrentAmount",projAppInfoModel.getInveCurrentAmount());
			}
			//拟投资金额
			double inveAmount=0;


			if(proj!=null){

			    if("1".equals(proj.getProjType())){
                    if(projAppInfoModel!=null&&projAppInfoModel.getActualAmount()!=null){
                        inveAmount=projAppInfoModel.getActualAmount();
                    }else{
                        inveAmount=0;
                    }
                }else{
                    if(projAppInfoModel!=null&&projAppInfoModel.getActualAmount()!=null){
                        inveAmount=projAppInfoModel.getActualAmount();
                    }else{
                    	//
						if(StringUtils.isNotEmpty(proj.getProjObject())){
						 FundInfoModel fundI=fundInfoService.selectById(proj.getProjObject());
						 if(fundI!=null){
							 mapResponse.put("inveCurrentAmount",fundI.getInveCurrentAmount());
						 }
						}

                        inveAmount=0;
                    }
                }
            }

			mapResponse.put("inveAmount",inveAmount);

			//List<XjlTPaymentRequestModel> list=xjlTPaymentRequestService.selectByProjId(projId);
			Double doub=xjlTPaymentRequestService.selectCountSum(projId);
            double sumInveAmount=0;
            if(doub!=null){
				sumInveAmount=doub;
				mapResponse.put("sumInveAmount",String.valueOf(sumInveAmount));
			}else{
				mapResponse.put("sumInveAmount","0");
			}
			Double doub1= xjlTPaymentRequestService.selectSumRmbActualPayAmount(projId);
			double SumRmbActualPayAmount=0;
			if(doub1!=null){
				SumRmbActualPayAmount=doub1;
				mapResponse.put("sumRmbActualPayAmount",String.valueOf(SumRmbActualPayAmount));
			}else{
				mapResponse.put("sumRmbActualPayAmount","0");
			}
		} catch (SystemException e) {
			logger.error(e.getMessage());
			mapResponse.error(e.getMessage());
		} catch (Exception e) {
			mapResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);
	}


	@ApiOperation(value="查询投资款拨付记录详情", notes="查询投资款拨付记录详情")
	@ApiImplicitParam(name = "pqId", value = "pqId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/paymentRequest/detaiInfo/{pqId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detail(@PathVariable(value = "pqId") String pqId){
		try {
			XjlTPaymentRequestModel model=xjlTPaymentRequestService.selectById(pqId);
			if(model!=null){
				XjlTPaymentRequestVO vo=new XjlTPaymentRequestVO();
				BeanUtils.copyProperties(vo, model);
				if(StringUtils.isNotEmpty(model.getPayCurrency())){
					vo.setPayCurrencyName(codeUtils.getCodeNameByValue("103", model.getPayCurrency()));
				}
				if(StringUtils.isNotEmpty(model.getProcessInstIdTwo())){
					List<Map<String,Object>> listMap=  fixflowRunTaskinstanceService.getFixFlowByTaskId(model.getProcessInstIdTwo());
					if(listMap!=null&&listMap.size()>0){
						String taskId=listMap.get(0).get("TASKINSTANCE_ID").toString();
						vo.setProcessInstId(model.getProcessInstIdTwo());
						vo.setTaskId(taskId);
					}else{
						vo.setProcessInstId("");
						vo.setTaskId("");
					}
				}else if(StringUtils.isEmpty(model.getProcessInstIdTwo()) && StringUtils.isNotEmpty(model.getProcessInstId())){
					List<Map<String,Object>> listMap=  fixflowRunTaskinstanceService.getFixFlowByTaskId(model.getProcessInstId());
					if(listMap!=null&&listMap.size()>0){
						String taskId=listMap.get(0).get("TASKINSTANCE_ID").toString();
						vo.setTaskId(taskId);
						vo.setProcessInstId(model.getProcessInstId());
					}else{
						vo.setTaskId("");
						vo.setProcessInstId("");
					}
				}
				if(StringUtils.isNotEmpty(model.getProjectOrFundId())){
					Double doub1= xjlTPaymentRequestService.selectSumRmbActualPayAmount(model.getProjectOrFundId());
					if(doub1!=null){
						vo.setSumRmbActualPayAmount(doub1);
					}else{
						vo.setSumRmbActualPayAmount(0.0);
					}

					ProjAppInfoModel projAppInfoModel=projAppInfoService.selectById(model.getProjectOrFundId());
					if(projAppInfoModel!=null&&projAppInfoModel.getInveCurrentAmount()!=null){
					   vo.setInveCurrentAmount(projAppInfoModel.getInveCurrentAmount());
					}
					if(projAppInfoModel!=null&&projAppInfoModel.getIntendedAmount()!=null){
						vo.setIntendedAmount(projAppInfoModel.getIntendedAmount());
					}
					if(projAppInfoModel!=null&&projAppInfoModel.getApplyAmount()!=null){
						vo.setApplyAmount(projAppInfoModel.getApplyAmount());
					}
					ProjInfoModel pro=projInfoService.selectById(model.getProjectOrFundId());
					if(pro!=null){
						vo.setInveName(pro.getInveName());
						vo.setProjName(pro.getProjName());
					}
            }
				dataResponse.setData(vo);
			}

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
			@ApiImplicitParam(name = "pqId", value = "pqId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "XjlTPaymentRequestDTO", value = "XjlTPaymentRequestDTO", required = true, dataType = "FundQuitApplVO")
	})
	@PostMapping(value = "/paymentRequest/update/{pqId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update(@PathVariable("pqId") String pqId, @RequestBody XjlTPaymentRequestDTO dto){
		try {
			XjlTPaymentRequestModel model = new XjlTPaymentRequestModel();
			BeanUtils.copyProperties(model, dto);
			//查询所有的付款除了修改的这条记录
		/*	XjlTPaymentRequestModel xjlTPaymentRequestModel=new XjlTPaymentRequestModel();
			xjlTPaymentRequestModel.setProjectOrFundId(dto.getProjectOrFundId());
			xjlTPaymentRequestModel.setPqId(dto.getPqId());
			Double doub=xjlTPaymentRequestService.selectCountSum(xjlTPaymentRequestModel);
			if(doub!=null){
			  model.setSumInveAmount(doub+dto.getActualPayAmount());
			}else{
				model.setSumInveAmount(dto.getActualPayAmount());
			}*/
			model.setPqId(pqId);
			xjlTPaymentRequestService.updateByPo(model,this.getLoginUserId());
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);

	}

	@ApiIgnore
	@ApiOperation(value="修改", notes="修改")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pqId", value = "pqId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "XjlTPaymentRequestDTO", value = "XjlTPaymentRequestDTO", required = true, dataType = "FundQuitApplVO")
	})
	@PostMapping(value = "/paymentRequest/updateInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateInfo(@RequestBody XjlTPaymentRequestDTO dto){
		try {
			XjlTPaymentRequestModel model = new XjlTPaymentRequestModel();
			BeanUtils.copyProperties(model, dto);
			model.setPqId(dto.getPqId());
			model.setUpdateBy(this.getLoginUserId());
			model.setUpdateDt(new Date());
			if(StringUtils.isNotEmpty(dto.getPqId())){
				xjlTPaymentRequestService.update(model);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);

	}

    @ApiOperation(value="财务发起流程", notes="财务发起流程")
    @ApiImplicitParam(name = "projId", value = "pqId", required = true, dataType = "String", paramType = "path")
    @ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fundShare/startWork", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String startWork(@RequestParam("projId")String projId,String fundId,@RequestParam("pqId")String pqId,String investorId,String currId,String isProj){
        try {
            XjlTPaymentRequestModel xjlTPaymentRequestModel=new XjlTPaymentRequestModel();
            xjlTPaymentRequestModel.setPqId(pqId);
            IWorkflowManager wm = new WorkflowManager();
			String fileName ="";
			String name ="";
			String taskTitle ="";
			String groupId="";
            if("1".equals(isProj)){
				 fileName = "ei_inve_desc_proj_money";
				ProjInfoModel projModel= projInfoService.selectById(projId);
				if(projModel!=null){
					if(StringUtils.isNotEmpty(projModel.getProjName())){
						name=projModel.getProjName();
					}
					if(4==projModel.getGroupId()){
						taskTitle = "管理公司出资："+name;
					}else{
						taskTitle = "项目出资："+name;
					}

				}
			}else{
				 fileName = "ei_inve_desc_money";
				FundInfoModel  fundInfoModel=fundInfoService.selectById(fundId);
				if(StringUtils.isNotEmpty(fundInfoModel.getFundName())){
					name=fundInfoModel.getFundName();
				}
				if(fundInfoModel.getGroupId()!=null){
					groupId=String.valueOf(fundInfoModel.getGroupId());
				}

				taskTitle = "基金出资："+name;
			}
            String currUserId = currId;
            String id = pqId;
            if (wm.isEnd(id, fileName, currUserId)) {
                Map<String, Object> formMap = new HashMap<>();
                formMap.put("taskTitle", taskTitle);
                formMap.put("businessKey", id);
                formMap.put("projId", projId);
                formMap.put("fundId", fundId);
				formMap.put("groupId", groupId);

				AppUserModel mo= appUserService.selectById(currUserId);
				Long deptId=mo.getDeptid();
                //AppGroup dept = UimUtils.getDept(Long.parseLong(currUserId));
                //财务负责人
				String financeManage = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(deptId, 103);
				formMap.put("financeManage", financeManage);
                List<String> financeList = UimUtils.getUserIdByRoleId(10008L);
                String finance = "";
                for (String s : financeList) {
                    finance += s + ",";
                }
                if (finance.length() > 0) {
                    finance = finance.substring(0, finance.length() - 1);
                }
                //财务审批
                formMap.put("finance", finance);
                //发起人
                formMap.put("startUser", currUserId);

                ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", fileName, id, currUserId);
                if (inst != null) {
                    //"提交成功！"

                    xjlTPaymentRequestModel.setProcessInstIdTwo(inst.getId());
                    xjlTPaymentRequestService.update(xjlTPaymentRequestModel);
                } else {
                    throw new SystemException("流程启动失败，请确认下一节点审批人是否存在");
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
//**********************************************投管流程*****************************************************8

	@ApiOperation(value="投管流程出资-带出初始值", notes="投管流程出资")
	@ApiImplicitParam(name = "projId", value = "pqId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/paymentRequest/getProjInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getProjInfo(@RequestParam("projId")String projId){
		try {
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
			String time=sim.format(new Date());
			mapResponse.put("newTime",time);
			String userName=this.getLoginUser().getName();
			mapResponse.put("userName",userName);
			ProjAppInfoModel projAppInfoModel=projAppInfoService.selectById(projId);
			//拟投资金额
			double intendedAmount=0;
			if(projAppInfoModel!=null&&projAppInfoModel.getIntendedAmount()!=null){
				intendedAmount=projAppInfoModel.getIntendedAmount();
			}else{
				intendedAmount=0;
			}
			mapResponse.put("intendedAmount",intendedAmount);

			if(projAppInfoModel!=null&&projAppInfoModel.getInveCurrentAmount()!=null){
				mapResponse.put("inveCurrentAmount",projAppInfoModel.getInveCurrentAmount());
			}
			if(projAppInfoModel!=null&&projAppInfoModel.getApplyAmount()!=null){
				mapResponse.put("applyAmount",projAppInfoModel.getApplyAmount());
			}
			//获取累计付款金额-查询审核通过的记录
			//Double doub=xjlTPaymentRequestService.selectCountSum(projId);
			Double doub=xjlTPaymentRequestService.selectSumPass(projId);
			double sumInveAmount=0;
			if(doub!=null){
				sumInveAmount=doub;
				mapResponse.put("sumInveAmount",String.valueOf(sumInveAmount));
			}else{
				mapResponse.put("sumInveAmount","0");
			}


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
	@ApiOperation(value="项目投管-新增出资", notes="新增投资款拨付")
	@ApiImplicitParam(name = "ProjContractFileDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/paymentRequest/saveInveMoney", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveInveMoney(@RequestBody XjlTPaymentRequestDTO dto){
		try {
			String userId=this.getLoginUserId();
			XjlTPaymentRequestModel xjlTPaymentRequestModel=new XjlTPaymentRequestModel();
			BeanUtils.copyProperties(xjlTPaymentRequestModel,dto);
			//累计付款金额
			if(StringUtils.isNotEmpty(dto.getPqId())){
				xjlTPaymentRequestModel.setUpdateBy(userId);
				xjlTPaymentRequestModel.setUpdateDt(new Date());
				xjlTPaymentRequestService.update(xjlTPaymentRequestModel);
			}else{
				String pqId=serialnoService.getSequence("EI.XJL_T_PAYMENT_REQUEST");
				xjlTPaymentRequestModel.setPqId(pqId);
				xjlTPaymentRequestService.insertByPqId(xjlTPaymentRequestModel,userId);
			}

			if(dto.getIsStartFlow()!=null){
				if(dto.getIsStartFlow()){//如果点击是提交则添加流程
					startWorkFlow(xjlTPaymentRequestModel,dto.getFundId(),"1");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiOperation(value = "基金出资删除", notes = "根据url的id来基金出资删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids", value = "计划审核ID", required = true, dataType = "String"),
	})
	@GetMapping(value = "/paymentRequest/delete", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@RequestParam("ids") String ids) {

		try {
			String[] idsArray = ids.split(",");
			xjlTPaymentRequestService.deleteByIds(Arrays.asList(idsArray), this.getLoginUserId());
		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			baseResponse.error();
			logger.error(e.getMessage(), e);
		}

		return JSONObject.toJSONString(baseResponse);

	}


	@ApiOperation(value="投管流程出资-查看列表是否有审核中的数据", notes="投管流程出资")
	@ApiImplicitParam(name = "projId", value = "pqId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/xjl/check/{projId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String checkInfo(@PathVariable("projId") String projId){
		try {
			//提交判断是否有没审核的出资记录
			List<XjlTPaymentRequestModel> list=xjlTPaymentRequestService.selectNoPassList(projId);
			if(list!=null&&list.size()>0){
				baseResponse.setMsg("请把未审核完成的数据,审核结束后再提交!");
				return JSONObject.toJSONString(baseResponse);
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




}

