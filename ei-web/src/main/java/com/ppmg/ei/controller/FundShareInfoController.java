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
import com.ppmg.ei.bean.CashFlowSearchBean;
import com.ppmg.ei.bean.ShareInfoSearchBean;
import com.ppmg.ei.dto.FundShareDTO;
import com.ppmg.ei.dto.ShareInfoDTO;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.utils.OaConstants;
import com.ppmg.ei.utils.UimUtilsUserId;
import com.ppmg.ei.vo.*;
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
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Scope("prototype")
@Api(tags={"基金层面接口"})
@RequestMapping("/fundShareInfo")
public class FundShareInfoController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private FundShareInfoService fundShareInfoService;

	@Reference
	private FundShareItemService fundShareItemService;

	@Reference
	private FundLedgerMagService fundLedgerMagService;

	@Reference
	private InveInfoService inveInfoService;

	@Reference//投资关系
	private InveRelService inveRelService;

	@Reference//合作关系信息
	private InveGroupService inveGroupService;


	@Reference
	private LedgerMagService ledgerMagService;


	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@Reference
	private ProjInfoService projInfoService;

	@Reference
	private ProjAppInfoService projAppInfoService;

	@Reference
	private FundInfoService fundInfoService;

	@Reference
	private AppUserService appUserService;



	@Reference(check = false)
	private SerialnoService serialnoService;

	@ApiOperation(value="查询基金层面列表", notes="查询基金层面记录列表")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/fundShareList", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public String fundShareList(@RequestBody CashFlowSearchBean searchBean){

		try {
			searchCondition.setSearchBean(searchBean);
			PageInfo<Map<String,Object>> rows = fundShareInfoService.selectFundSharePage(searchCondition);
			List<FundShareBeddingVO> list = new ArrayList<FundShareBeddingVO>();
			for(Map m : rows.getList()){
				FundShareBeddingVO vo = new FundShareBeddingVO();
				vo.setRegName((String)m.get("regName"));
				if(m.get("INVEAMOUNT")!=null){
					vo.setInveAmount(Double.parseDouble(m.get("INVEAMOUNT").toString()));
				}
				if(m.get("PAYMENTAMOUNT")!=null){
					vo.setPaymentAmount(Double.parseDouble(m.get("PAYMENTAMOUNT").toString()));
				}
                if(m.get("BJJANOUNT")!=null){
					vo.setBjjAnount(Double.parseDouble(m.get("BJJANOUNT").toString()));
				}
                if(m.get("SHANOUNT")!=null){
						vo.setShAnount(Double.parseDouble(m.get("SHANOUNT").toString()));
                }

				if(m.get("INVEAMOUNT")!=null && Double.parseDouble(m.get("INVEAMOUNT").toString())!=0){
					Double pr=Double.parseDouble(m.get("BJJAONEY").toString());
					Double pr1=Double.parseDouble(m.get("INVEAMOUNT").toString());
					if(!"0".equals(pr)&& pr1!=0){
						Double persent=pr/pr1*100;
						DecimalFormat df4 = new DecimalFormat("#.##%");//数字格式化
						vo.setPercent(df4.format(persent));
					}

				}
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


	@ApiOperation(value="出资情况表", notes="出资情况表")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/fundShareInfoList", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public String fundShareInfoList(@RequestBody ShareInfoSearchBean searchBean){
		try {

			if(StringUtils.isNotEmpty(searchBean.getFundId())){
				searchBean.setFundId(searchBean.getFundId());
			}
			if(StringUtils.isNotEmpty(searchBean.getInvestorName())){
				searchBean.setInvestorName("%"+searchBean.getInvestorName()+"%");
			}
			searchCondition.setSearchBean(searchBean);
			PageInfo<FundShareInfoModel> rows = fundShareInfoService.selectFundShareInfoPage(searchCondition);
			List<FundShareInfoResultVO> list = new ArrayList<FundShareInfoResultVO>();
			for(FundShareInfoModel model : rows.getList()){
				FundShareInfoResultVO vo = new FundShareInfoResultVO();
				//根据fundId查询出资人
				BeanUtils.copyProperties(vo, model);
				if(model.getFundInfoModel()!=null){
					vo.setFundName(model.getFundInfoModel().getFundName());
				}
				Double sumInveAmount=0.0;
				if(StringUtils.isNotEmpty(model.getPkId())){
                  //查询寻所有的出资人
					List<FundShareItemModel> fundShareItemList = fundShareItemService.selectItemList(model.getPkId());
					Double  oldInveAmount=0.0;
					if(fundShareItemList!=null&&fundShareItemList.size()>0){
						for(FundShareItemModel shareItemModel:fundShareItemList){
							//累加认缴金额
							if(shareItemModel.getInveAmount()!=null){
								sumInveAmount=sumInveAmount+shareItemModel.getInveAmount();
							}

						}
						//前台选择第几期
						Double newPaymentAmount=0.0;
						FundShareItemModel base=new FundShareItemModel();
						base.setPkId(model.getPkId());
						//查询所有的实缴金额
						Double d=fundShareItemService.selectBySum(model.getPkId());
						if(searchBean.getCloseRound()!=null){
							base.setCloseRound(searchBean.getCloseRound());
							//查询本期金额
							FundShareItemModel fundShareItemModel1=fundShareItemService.selectByC(base);
							if(fundShareItemModel1!=null){
								vo.setNewPaymentAmount(fundShareItemModel1.getPaymentAmount());
							}
							//查询本期前
						   Double oldInveAmount1=fundShareItemService.selectByCR(base);
							vo.setOldInveAmount(oldInveAmount1);
							//本期后
							Double lastInveAmount=fundShareItemService.selectByLastCR(base);
							vo.setLastInveAmount(lastInveAmount);
							if(d!=null){
								if(sumInveAmount!=0){
									int per=(int)((new BigDecimal(d/sumInveAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())*100);
									String invePer = per+"%";
									vo.setInvePer(invePer);
								}
							}
							//未交出资额
							/*if(model.getInveAmount()!=null){
								if(d!=null){
									vo.setNoInveAmount(model.getInveAmount()-d);

								}

							}*/

						}else{
							//查询最新的一条
							List<FundShareItemModel> shar = fundShareItemService.selectList(base);
							//本次实缴出资额
							if(shar!=null&&shar.size()>0){
								if(shar.get(0).getPaymentAmount()!=null){
									newPaymentAmount=shar.get(0).getPaymentAmount();
									vo.setNewPaymentAmount(newPaymentAmount);
								}
							}
							//未交出资额
							/*if(model.getInveAmount()!=null){
								if(d!=null){
									vo.setNoInveAmount(model.getInveAmount()-d);
								}else{
									vo.setNoInveAmount(model.getInveAmount()-0);
								}

							}*/
							if(d!=null){
								//认缴出资比例
								if(model.getInveAmount()!=0){
									if(sumInveAmount!=null&&sumInveAmount!=0){
										Double per=(Double)((new BigDecimal(d*100/sumInveAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
										String invePer = per+"%";
										vo.setInvePer(invePer);
									}

								}
								//本次前实缴金额
								if(newPaymentAmount!=null){
									 oldInveAmount=d-newPaymentAmount;
									 vo.setOldInveAmount(oldInveAmount);
								}
							}else{
								oldInveAmount=0.0;
							}
							//本次后实缴金额
							vo.setLastInveAmount(0.0);
							//本次实缴比例
							if(sumInveAmount!=0){
								Double per1=(Double)((new BigDecimal(newPaymentAmount*100 /sumInveAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
								String invePer1 = per1+"%";
								vo.setThisInvePer(invePer1);
							}else{
								vo.setThisInvePer("0%");
							}
						}

						vo.setSumInveAmount1(String.valueOf(sumInveAmount));
						//
						if(d!=null){
							vo.setNoInveAmount(sumInveAmount-d);
						}else{
							vo.setNoInveAmount(sumInveAmount);
						}

					}


				}



				if(model.getInveInfoModel()!=null){
					vo.setInvestorName(model.getInveInfoModel().getInvestorName());
				}
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
	/************************出资信息********华丽分隔线*************************************************/

	@ApiOperation(value = "分页查询出资信息列表", notes = "分页查询出资信息列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
			@ApiImplicitParam(name = "fundId", value = "基金编号", required = false, dataType = "String")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/selectFundShareInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectFundShareInfo(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage
			,@RequestParam("fundId") String fundId) {
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			if (StringUtils.isNotEmpty(fundId)) {
				searchCondition.addConditionEqualTo("FUND_ID",fundId);
			}
			PageInfo<FundShareInfoModel> rows = fundShareInfoService.selectFundShareInfoPageList(searchCondition);
			List<FundSharInfoTwoVO> list = new ArrayList<>();
			//SELECT sum(INVE_AMOUNT) FROM rz_t_fund_share_info where fund_id = #{fundId,jdbcType=VARCHAR}
			Double All_d1 = fundShareInfoService.getInveAmountSum(fundId);//当前基金，所有投资人认缴金额
			for (FundShareInfoModel model : rows.getList()) {
				//计算股比：股比=认缴金额/所有投资人认缴金额，
				Double d2 = model.getInveAmount();//认缴金额
				DecimalFormat df = new DecimalFormat("0%");
				if (All_d1 != null && d2 != null) {
					Double d = d2 / All_d1;
					logger.info("原数据1---》"+d);
					logger.info("转换后数据1---》" + df.format(d));
					model.setGB(df.format(d));//股比
				}
				// 出资进度=累计出资金额/认缴金额
				Double d3 = 0.0;//累计出资金额
				String pkId = model.getPkId();
				//查询台账里面出资金额
				List<FundLedgerMagModel> tz = fundLedgerMagService.selectFundLedgerMagList(pkId);
				for (FundLedgerMagModel fm : tz) {
					d3 = d3 + fm.getRmbInveAmount();//出资金额：occurAmount
				}

				if(d2 != null && d3 != null){
					Double d4 = d3 / d2;
					logger.info("原数据2---》"+d3);
					logger.info("转换后数据2---》" + df.format(d4));
					model.setSchedule(df.format(d4));//出资进度
				}
				FundSharInfoTwoVO vo = new FundSharInfoTwoVO();
				BeanUtils.copyProperties(vo, model);
				String type = model.getInveType();
				if(StringUtils.isNotEmpty(type)){//投资人类型
					String name = codeUtils.getCodeNameByValue("11017",type);
					vo.setInveTypeName(name);
				}
				vo.setTotalFinancial(d3);//累计出资金额（万元）
				vo.setTotalFinCurrency("1");//累计出资金额（万元）币种
				String nameb = codeUtils.getCodeNameByValue("103","1");
				vo.setTotalFinCurrencyName(nameb);//累计出资金额（万元）币种

				String type6 = model.getInveNature();
				if(StringUtils.isNotEmpty(type6)){//投资人属性
					String name = codeUtils.getCodeNameByValue("888",type6);
					vo.setInveNatureName(name);
				}
				String type1 = model.getInveRole();
				if(StringUtils.isNotEmpty(type1)){//投资角色
					String name = codeUtils.getCodeNameByValue("244",type1);
					vo.setInveRoleName(name);
				}
				String type2 = model.getInveCurrency();
				if(StringUtils.isNotEmpty(type2)){//认缴金额币种
					String name = codeUtils.getCodeNameByValue("103",type2);
					vo.setInveCurrencyName(name);
				}
				/*String type3 = model.getTotalFinCurrency();
				if(StringUtils.isNotEmpty(type3)){//累计出资币种
					String name = codeUtils.getCodeNameByValue("103",type3);
					vo.setTotalFinCurrencyName(name);
				}*/
				if (model != null && StringUtils.isNotEmpty(model.getInvestorId())) {
					InveInfoModel im = inveInfoService.selectById(model.getInvestorId());//查询投资人名称
					if(im != null){
						vo.setInvestorIdName(im.getInvestorName());
					}
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

	@ApiOperation(value = "查询出资信息列表详情 ", notes = "查询出资信息列表详情")
	@ApiImplicitParam(name = "pkId", value = "主键id", required = true, dataType = "String")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/selectFundShareInfoDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectFundShareInfoDetail(@RequestParam(value = "pkId") String pkId) {
		try {
			FundShareInfoModel model = fundShareInfoService.selectOneFundShareInfo(pkId);
			FundSharInfoTwoVO vo = new FundSharInfoTwoVO();
			BeanUtils.copyProperties(vo, model);
			String type = model.getInveType();
			if(StringUtils.isNotEmpty(type)){//投资人类型 1:个人，2机构
				String name = codeUtils.getCodeNameByValue("11017",type);
				vo.setInveTypeName(name);
			}
			String type1 = model.getInveRole();
			if(StringUtils.isNotEmpty(type1)){//投资角色,LP	1，GP	2，管理公司	3，其他	4
				String name = codeUtils.getCodeNameByValue("244",type1);
				vo.setInveRoleName(name);
			}
			String type6 = model.getInveNature();
			//投资人属性（中央财政	1，国家基金	2，省级财政	3，省级基金	4，市县财政	5，国有企业	6，国有金融机构	7，民营企业	8，自然人	9）
			if(StringUtils.isNotEmpty(type6)){
				String name = codeUtils.getCodeNameByValue("888",type6);
				vo.setInveNatureName(name);
			}
			String type2 = model.getInveCurrency();
			if(StringUtils.isNotEmpty(type2)){//认缴金额币种，人民币	1，美元	2，欧元	3
				String name = codeUtils.getCodeNameByValue("103",type2);
				vo.setInveCurrencyName(name);
			}
			String type3 = model.getTotalFinCurrency();
			if(StringUtils.isNotEmpty(type3)){//累计出资币种，人民币	1，美元	2，欧元	3
				String name = codeUtils.getCodeNameByValue("103",type3);
				vo.setTotalFinCurrencyName(name);
			}
			if (model != null && StringUtils.isNotEmpty(model.getInvestorId())) {
				InveInfoModel im = inveInfoService.selectById(model.getInvestorId());//查询投资人名称
				if(im != null){
					vo.setInvestorIdName(im.getInvestorName());
				}
			}
			/*if (model != null && StringUtils.isNotEmpty(model.getInvestorId())) {
				InveInfoModel im = inveInfoService.selectById(model.getInvestorId());//查询投资人属性
				vo.setInvePropertyF(im.getSubType());
			}*/
			dataResponse.setData(vo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			baseResponse.error();
		}
		return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}
	@ApiOperation(value = "增加出资信息", notes = "增加出资信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "fundShareInfoModel", value = "实体类", required = true, dataType = "FundShareInfoModel")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@RequestMapping(value = "/addFundShareInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addFundShareInfo(@RequestBody FundShareInfoModel fundShareInfoModel){
		try {
			if(fundShareInfoModel != null){
				String id = UimUtilsUserId.generateKey();
				String userId = String.valueOf(this.getLoginUser().getUserId());
				//String userId = UimUtilsUserId.getUserId("1");
				System.out.println("当前登陆人测试----------->"+userId);
				fundShareInfoModel.setPkId(id);//主键
				if(StringUtils.isEmpty(fundShareInfoModel.getStatus())){
					fundShareInfoModel.setStatus("0");//状态默认0
				}
				if(StringUtils.isEmpty(fundShareInfoModel.getCreateBy())){
					fundShareInfoModel.setCreateBy(userId);
				}
				if(StringUtils.isEmpty(fundShareInfoModel.getUpdateBy())){
					fundShareInfoModel.setUpdateBy(userId);
				}
				fundShareInfoService.addFundShareInfoListCZ(fundShareInfoModel);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	@ApiOperation(value = "更新出资信息", notes = "更新出资信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "fundShareInfoModel", value = "实体类", required = true, dataType = "FundShareInfoModel")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@RequestMapping(value = "/updateFundShareInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateFundShareInfo(@RequestBody FundShareInfoModel fundShareInfoModel){
		try {
			if(fundShareInfoModel != null){
				String userId = String.valueOf(this.getLoginUser().getUserId());
				//String userId = UimUtilsUserId.getUserId("1");
				System.out.println("当前登陆人测试----------->"+userId);
				fundShareInfoModel.setUpdateBy(userId);
				fundShareInfoService.updateFundShareInfo(fundShareInfoModel);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}
	@ApiOperation(value = "删除出资信息", notes = "删除出资信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pkId", value = "主键id", required = true, dataType = "String")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@RequestMapping(value = "/deleteFundShareInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteFundShareInfo(@RequestParam("pkId") String pkId) {
		try {
			String[] arr = null;
			if (pkId != "" && pkId != null) {
				arr = pkId.split(",");
				fundShareInfoService.deleteBatch(arr);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	/*** 出自信息---查询投资人**/
	@GetMapping(value = "/selectInveInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectInveInfo(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage
			,@RequestParam("name") String name,@RequestParam("type") String type) {
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			if (StringUtils.isNotEmpty(name)) {
				searchCondition.addConditionLike("INVESTOR_NAME", "%" + name + "%");
			}
			if (StringUtils.isNotEmpty(type)) {
				searchCondition.addConditionEqualTo("INVESTOR_TYPE",type);
			}
			PageInfo<InveInfoModel> rows = inveInfoService.selectListPage(searchCondition);
			List<InveInfoVO> list = new ArrayList<>();
			for (InveInfoModel model : rows.getList()) {
				String type1 = model.getInvestorType();
				if(StringUtils.isNotEmpty(type1)){//投资人类型(1:个人，2:机构)
					String name1 = codeUtils.getCodeNameByValue("209",type1);
					model.setInvestorTypeName(name1);
				}
				InveInfoVO vo = new InveInfoVO();
				BeanUtils.copyProperties(vo, model);
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

	/*********台账管理******************华丽分割线*************/
	@ApiOperation(value = "分页查询台账管理列表", notes = "分页查询台账管理列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
			@ApiImplicitParam(name = "fundId", value = "基金编号", required = false, dataType = "String")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/selectFundLedgerMag", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectFundLedgerMag(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage
			,@RequestParam("pkId") String pkId) {
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			if (StringUtils.isNotEmpty(pkId)) {
				searchCondition.addConditionEqualTo("PK_ID",pkId);
			}
			PageInfo<FundLedgerMagModel> rows = fundLedgerMagService.selectListPage(searchCondition);
			List<FundLedgerMagVO> list = new ArrayList<>();
			for (FundLedgerMagModel model : rows.getList()) {
				String type1 = model.getLedgerObject();
				if(StringUtils.isNotEmpty(type1)){//资金对象,LP	1,GP	2,管理公司	3,其他	4
					String name = codeUtils.getCodeNameByValue("244",type1);
					model.setLedgerObjectName(name);
				}
				String type2 = model.getLedgerType();
				if(StringUtils.isNotEmpty(type2)){//资金类型:出资	1 ,减资	2 ,管理费	3 ,往来款	4
					//,分红	5 ,退出本金	6 ,退出收益	7 ,理财收益	8 ,补贴收益	9 ,其他	10 ,补偿款	11
					String name = codeUtils.getCodeNameByValue("242",type2);
					model.setLedgerTypeName(name);
				}
				String type3 = model.getOccurCurr();
				if(StringUtils.isNotEmpty(type3)){//发生金额币种，出资金额币种:人民币	1 ,美元	2 ,欧元	3
					String name = codeUtils.getCodeNameByValue("103",type3);
					model.setOccurCurrName(name);
				}
				String type4 = model.getPayWay();
				if(StringUtils.isNotEmpty(type4)){//支付方式:现金	1 ,银行转账	2
					String name = codeUtils.getCodeNameByValue("247",type4);
					model.setPayWayName(name);
				}
				FundLedgerMagVO vo = new FundLedgerMagVO();
				BeanUtils.copyProperties(vo, model);

				FundShareInfoModel pk = fundShareInfoService.selectOneFundShareInfo(pkId);
				/** 发生金额*/
				vo.setInveAmount(pk.getInveAmount());
				/** 发生金额币种 */
				if(StringUtils.isNotEmpty(pk.getInveCurrency())){//发生金额币种，:人民币	1 ,美元	2 ,欧元	3
					String name = codeUtils.getCodeNameByValue("103",pk.getInveCurrency());
					vo.setInveCurrency(pk.getInveCurrency());
					vo.setInveCurrencyName(name);
				}
				if (pk != null && StringUtils.isNotEmpty(pk.getInvestorId())) {
					InveInfoModel im = inveInfoService.selectById(pk.getInvestorId());//查询投资人名称
					if(im != null){
						vo.setInvestorIdName(im.getInvestorName());
					}
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

	@ApiOperation(value = "查询台账管理详情 ", notes = "查询台账管理详情")
	@ApiImplicitParam(name = "ledgerId", value = "主键id", required = true, dataType = "String")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/selectFundLedgerMagDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectFundLedgerMagDetail(@RequestParam(value = "ledgerId") String ledgerId) {
		try {
			FundLedgerMagModel model = fundLedgerMagService.selectById(ledgerId);
			String type1 = model.getLedgerObject();
			if(StringUtils.isNotEmpty(type1)){//资金对象
				String name = codeUtils.getCodeNameByValue("244",type1);
				model.setLedgerObjectName(name);
			}
			String type2 = model.getLedgerType();
			if(StringUtils.isNotEmpty(type2)){//资金类型
				String name = codeUtils.getCodeNameByValue("242",type2);
				model.setLedgerTypeName(name);
			}
			String type3 = model.getOccurCurr();
			if(StringUtils.isNotEmpty(type3)){//发生金额币种，出资金额币种
				String name = codeUtils.getCodeNameByValue("103",type3);
				model.setOccurCurrName(name);
			}
			String type4 = model.getPayWay();
			if(StringUtils.isNotEmpty(type4)){//支付方式
				String name = codeUtils.getCodeNameByValue("247",type4);
				model.setPayWayName(name);
			}
			FundLedgerMagVO vo = new FundLedgerMagVO();
			BeanUtils.copyProperties(vo, model);
			dataResponse.setData(vo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			baseResponse.error();
		}
		return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}
	@ApiOperation(value = "增加台账管理", notes = "增加台账管理")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "fundLedgerMagModel", value = "实体类", required = true, dataType = "FundLedgerMagModel")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@RequestMapping(value = "/addfundLedgerMag", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addfundLedgerMag(@RequestBody FundLedgerMagModel fundLedgerMagModel){
		try {
			if(fundLedgerMagModel != null){
				String id = UimUtilsUserId.generateKey();
				String userId = String.valueOf(this.getLoginUser().getUserId());
				//String userId = UimUtilsUserId.getUserId("1");
				System.out.println("当前登陆人测试----------->"+userId);
				fundLedgerMagModel.setLedgerId(id);//主键
				if(StringUtils.isEmpty(fundLedgerMagModel.getStatus())){
					fundLedgerMagModel.setStatus("0");
				}
				fundLedgerMagModel.setUpdateBy(userId);
				fundLedgerMagModel.setCreateBy(userId);
				fundLedgerMagModel.setCreateDt(new Date());
				fundLedgerMagModel.setUpdateDt(new Date());
				fundLedgerMagService.insert(fundLedgerMagModel);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	@ApiOperation(value = "更新台账管理", notes = "更新台账管理")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "fundLedgerMagModel", value = "实体类", required = true, dataType = "FundLedgerMagModel")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@RequestMapping(value = "/updateFundLedgerMagModel", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateFundLedgerMagModel(@RequestBody FundLedgerMagModel fundLedgerMagModel){
		try {
			if(fundLedgerMagModel != null){
				fundLedgerMagService.update(fundLedgerMagModel);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}
	@ApiOperation(value = "删除台账管理", notes = "删除台账管理")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ledgerId", value = "主键id", required = true, dataType = "String")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@RequestMapping(value = "/deleteFundLedgerMagModel", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteFundLedgerMagModel(@RequestParam("ledgerId") String ledgerId) {
		try {
			String[] arr = null;
			if (ledgerId != "" && ledgerId != null) {
				arr = ledgerId.split(",");
				fundLedgerMagService.deleteBatch(arr);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	/*++++++==查询投资人信息————*/
	@GetMapping(value = "/selectInveRelDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectInveRelDetail(@RequestParam(value = "investorId") String investorId) {
		try {
			InveInfoModel model = inveInfoService.selectOneInveInfo(investorId);
			String type1 = model.getInvestorType();
			if(StringUtils.isNotEmpty(type1)){//投资人类型(1:个人，2:机构)
				String name1 = codeUtils.getCodeNameByValue("209",type1);
				model.setInvestorTypeName(name1);
			}
			InveInfoVO vo = new InveInfoVO();
			BeanUtils.copyProperties(vo, model);
			dataResponse.setData(vo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			baseResponse.error();
		}
		return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}
	/**
	 * 查询投资关系信息列表
	 */
	@GetMapping(value = "/selectInveRel", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectInveRel(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage
			,@RequestParam("investorId") String investorId) {
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			if (StringUtils.isNotEmpty(investorId)) {
				searchCondition.addConditionEqualTo("INVESTOR1_ID",investorId);
			}
			PageInfo<InveRelModel> rows = inveRelService.selectListPage(searchCondition);
			List<InveRelVO> list = new ArrayList<>();
			for (InveRelModel model : rows.getList()) {
				String type1 = model.getCurrencyType();
				if(StringUtils.isNotEmpty(type1)){//货币类型
					String name1 = codeUtils.getCodeNameByValue("103",type1);
					model.setCurrencyTypeName(name1);
				}
				InveRelVO vo = new InveRelVO();
				BeanUtils.copyProperties(vo, model);
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

	/**
	 * 查询合作关系信息
	 */
	@GetMapping(value = "/selectInveGroupMember", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectInveGroupMember(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage
			,@RequestParam("investorId") String investorId) {
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			if (StringUtils.isNotEmpty(investorId)) {
				searchCondition.addConditionEqualTo("INVESTOR_ID",investorId);
			}
			PageInfo<InveGroupModel> rows = inveGroupService.selectListPage(searchCondition);
			List<InveGroupVO> list = new ArrayList<>();
			for (InveGroupModel model : rows.getList()) {
				String BeginDate = model.getBeginDate();
				String EndDate = model.getEndDate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				if(StringUtils.isNotEmpty(BeginDate)){
					Date d = sdf.parse(BeginDate);
					model.setBeginDate(sdf2.format(d));
				}
				if(StringUtils.isNotEmpty(EndDate)){
					Date d = sdf.parse(EndDate);
					model.setEndDate(sdf2.format(d));
				}
				InveGroupVO vo = new InveGroupVO();
				BeanUtils.copyProperties(vo, model);
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
	/**
	 * 与本公司基金关系
	 */
	@GetMapping(value = "/selectBdtFundInfoName", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectBdtFundInfoName(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage
			,@RequestParam("investorId") String investorId) {
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			if (StringUtils.isNotEmpty(investorId)) {
				searchCondition.addConditionEqualTo("INVESTOR_ID",investorId);
			}
			PageInfo<FundShareInfoModel> rows = fundShareInfoService.selectFundShareInfoPageListTwo(searchCondition);
			List<FundSharInfoTwoVO> list = new ArrayList<>();
			for (FundShareInfoModel model : rows.getList()) {
				java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
				nf.setGroupingUsed(false);
				System.out.println("d:="+nf.format(model.getInveAmount()));
				model.setInveAmountString(nf.format(model.getInveAmount()));
				FundSharInfoTwoVO vo = new FundSharInfoTwoVO();
				BeanUtils.copyProperties(vo, model);
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

	@ApiOperation(value = "保存基金与用户关联关系信息", notes = "保存基金与用户关联关系信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "relateId", value = "relateId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "dtoList", value = "基金与用户关联关系FundUserRelateDTO", required = true, dataType = "FundUserRelateDTO")
	})
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/delate/fundShare/{pkId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveFundUserRelate(@PathVariable("relateId")String pkId) {

		try {
			fundShareInfoService.updateByName(pkId);

		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			baseResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(baseResponse);
	}


	//查询出资情况表
	@ApiOperation(value = "新增/修改出资情况", notes = "新增/修改出资情况")
	@ApiImplicitParam(name = "SurveyPlanDTO", value = "SurveyPlanDTO", required = true, dataType = "RequirementsDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/saveInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveInfo(@RequestBody FundShareDTO dto) {

		try {
			FundShareInfoModel model = new FundShareInfoModel();
			BeanUtils.copyProperties(model, dto);
			FundShareItemModel fundShareItem = new FundShareItemModel();
			BeanUtils.copyProperties(fundShareItem, dto);
			if(StringUtils.isNotEmpty(dto.getProjId())){
				ProjInfoModel projInfoModel=projInfoService.selectById(dto.getProjId());
				if(projInfoModel!=null){
					model.setInvestorId(projInfoModel.getInveId());
				}
			}
			if(StringUtils.isNotEmpty(dto.getPkId())&&StringUtils.isNotEmpty(dto.getItemId())){
				//修改
				fundShareItemService.update(fundShareItem);
			}else{
				//新增
				String itemId= serialnoService.getSequence("EI.RZ_T_FUND_SHARE_ITEM");
				fundShareItem.setItemId(itemId);
				fundShareInfoService.insertFundShare(model,fundShareItem,this.getLoginUserId());
			}
			if(dto.getIsStartFlow()!=null){
				if(dto.getIsStartFlow()){//如果点击是提交则添加流程
					startWorkFlow(model,fundShareItem,dto.getProjId());
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

	@ApiOperation(value="查询出资列表", notes="查询出资列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/fundShareItemList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String fundShareItemList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,@RequestParam("projId") String projId,@RequestParam("fundId") String fundId){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("A.FUND_ID",fundId);
			ProjInfoModel projInfoModel=projInfoService.selectById(projId);
			FundShareInfoModel fundShareInfoModel=new FundShareInfoModel();
			if(projInfoModel!=null){
				searchCondition.addConditionEqualTo("A.INVESTOR_ID",projInfoModel.getInveId());
				fundShareInfoModel.setFundId(fundId);
				fundShareInfoModel.setInvestorId(projInfoModel.getInveId());
			}
			searchCondition.addConditionEqualTo("B.create_by",this.getLoginUserId());
			PageInfo<FundShareInfoModel> rows = fundShareInfoService.selectByItemListPage(searchCondition);
			List<FundShareInfoVO> list = new ArrayList<FundShareInfoVO>();
			for(FundShareInfoModel model : rows.getList()){
				FundShareInfoVO vo = new FundShareInfoVO();
				BeanUtils.copyProperties(vo, model);
				ProjAppInfoModel  projAppInfoModel=projAppInfoService.selectById(projId);
				vo.setInveCurrentAmount(projAppInfoModel.getInveCurrentAmount());
				if(model.getFundShareItem()!=null){
					vo.setFundShareItem(model.getFundShareItem());
					String  statusName=codeUtils.getCodeNameByValue("264",model.getFundShareItem().getStatus());
					vo.setStatusName(statusName==null?"":statusName);
					//查询期数
					Double totalMoney=0.0;
					/*if(model.getFundShareItem().getCreateDt()!=null){
						fundShareInfoModel.setCreateDt(model.getFundShareItem().getCreateDt());
						 totalMoney=fundShareInfoService.getSumPayAmount(fundShareInfoModel);
					}*/

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

	@ApiOperation(value="详情", notes="详情")
	@ApiImplicitParam(name = "itemId", value = "itemId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/getDetailInfo1", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getDetailInfo1(@RequestParam(value = "itemId") String itemId,@RequestParam("projId")String projId,@RequestParam("fundId")String fundId){
		try {
			FundShareItemModel	fundShareItemModel= fundShareItemService.selectById(itemId);
			ProjAppInfoModel  projAppInfoModel=projAppInfoService.selectById(projId);
			if(projAppInfoModel!=null){
				mapResponse.put("inveCurrentAmount",projAppInfoModel.getInveCurrentAmount());

			}

			FundShareInfoModel fundShareInfoModel=new FundShareInfoModel();
			fundShareInfoModel.setFundId(fundId);
			ProjInfoModel projInfoModel=projInfoService.selectById(projId);
			Double totalMoney=0.0;
			if(projInfoModel!=null&&StringUtils.isNotEmpty(projInfoModel.getInveId())){
				fundShareInfoModel.setInvestorId(projInfoModel.getInveId());
				mapResponse.put("investorId",projInfoModel.getInveId());
				if(fundShareItemModel!=null){
					fundShareInfoModel.setItemId(fundShareItemModel.getItemId());
				}
				totalMoney=fundShareInfoService.getSumPayAmount(fundShareInfoModel);
				if(totalMoney==null){
					totalMoney=0.0;
				}
			}
			mapResponse.put("totalMoney",totalMoney);

			if(fundShareItemModel!=null&&StringUtils.isNotEmpty(fundShareItemModel.getInveCurrency())){
				String  inveCurrencyName=codeUtils.getCodeNameByValue("103",fundShareItemModel.getInveCurrency());
				mapResponse.put("inveCurrencyName",inveCurrencyName);
			}
			mapResponse.put("fundShareItemModel",fundShareItemModel);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataResponse.error(e.getMessage());
		} catch (Exception e) {
			dataResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);
	}

	private void startWorkFlow(FundShareInfoModel model,FundShareItemModel fundShareItem,String projId) {
		IWorkflowManager wm = new WorkflowManager();
		String fileName = "ei_inve_desc";
		String currUserId = this.getLoginUserId();
		String id = fundShareItem.getItemId();
		FundInfoModel  fundInfoModel=fundInfoService.selectById(model.getInvestorId());
		String name ="";
		if(StringUtils.isNotEmpty(fundInfoModel.getFundName())){
          name=fundInfoModel.getFundName();
		}
		//todo项目名称
		String taskTitle = "基金出资:"+name+"第"+fundShareItem.getCloseRound()+"期";
		if (wm.isEnd(id, fileName, currUserId)) {
			Map<String, Object> formMap = new HashMap<>();
			formMap.put("taskTitle", taskTitle);
			formMap.put("businessKey", id);
			formMap.put("projId", projId);
			formMap.put("fundId", model.getFundId());
			AppUserModel mo= appUserService.selectById(currUserId);
			Long deptId=mo.getDeptid();
			//AppGroup dept = UimUtils.getDept(Long.parseLong(currUserId));
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
				fundShareItem.setProcessInstId(inst.getId());
				fundShareItemService.update(fundShareItem);
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
	@GetMapping(value = "/fundShare/startWork1", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String startWork1(@RequestParam("projId")String projId,@RequestParam("fundId")String fundId,@RequestParam("itemId")String itemId,String investorId,String currId){
		try {
			IWorkflowManager wm = new WorkflowManager();
			String fileName = "ei_inve_desc_money";
			String currUserId = currId;
			String id = itemId;
			FundInfoModel  fundInfoModel=fundInfoService.selectById(investorId);
			String name ="";
			if(StringUtils.isNotEmpty(fundInfoModel.getFundName())){
				name=fundInfoModel.getFundName();
			}
			FundShareItemModel	fundShareItem= fundShareItemService.selectById(itemId);
			//todo项目名称
			String taskTitle = "基金出资:"+name+"第"+fundShareItem.getCloseRound()+"期";
			if (wm.isEnd(id, fileName, currUserId)) {
				Map<String, Object> formMap = new HashMap<>();
				formMap.put("taskTitle", taskTitle);
				formMap.put("businessKey", id);
				formMap.put("projId", projId);
				formMap.put("fundId", fundId);
				//AppGroup dept = UimUtils.getDept(Long.parseLong(currUserId));
				AppUserModel mo= appUserService.selectById(currUserId);
				Long deptId=mo.getDeptid();
				//财务负责人
				String financeManage = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(deptId, 110);
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
					fundShareItem.setProcessInstId(inst.getId());
					fundShareItemService.update(fundShareItem);
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

	@ApiOperation(value="新增投资款拨付带出初始值", notes="新增投资款拨付带出初始值")
	@ApiImplicitParam(name = "projId", value = "pqId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/fundShare/getInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getInfo(@RequestParam("projId")String projId,@RequestParam("fundId")String fundId){
		try {
			ProjAppInfoModel  projAppInfoModel=projAppInfoService.selectById(projId);
			mapResponse.put("inveCurrentAmount",projAppInfoModel.getInveCurrentAmount());
			FundShareInfoModel fundShareInfoModel=new FundShareInfoModel();
			fundShareInfoModel.setFundId(fundId);
			ProjInfoModel projInfoModel=projInfoService.selectById(projId);
			Double totalMoney=0.0;
			if(projInfoModel!=null&&StringUtils.isNotEmpty(projInfoModel.getInveId())){
				fundShareInfoModel.setInvestorId(projInfoModel.getInveId());
				totalMoney=fundShareInfoService.getSumPayAmount(fundShareInfoModel);
				if(totalMoney==null){
					totalMoney=0.0;
				}
			}
			mapResponse.put("totalMoney",totalMoney);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			mapResponse.error(e.getMessage());
		} catch (Exception e) {
			mapResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);
	}

	/**
	 * 生成key主键
	 * @param sequenceName
	 * @return
	 */
	public String randomKey(String sequenceName){
		String id = null;
		id = String.valueOf(fundShareInfoService.selectOne("getSeqFundShareInfoKey",sequenceName));
		return id;
	}
	public String randomKey2(String sequenceName){
		String id = null;
		id = String.valueOf(fundLedgerMagService.selectOne("getSeqFundLedgerMagKey",sequenceName));
		return id;
	}




	//#***********************************老系统股权结构**********************************************************
	@ApiOperation(value = "新增-母基金-股权机构", notes = "新增-母基金-股权机构")
	@ApiImplicitParam(name = "SurveyPlanDTO", value = "SurveyPlanDTO", required = true, dataType = "RequirementsDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/saveInfoShar", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveInfo(@RequestBody ShareInfoDTO dto) {

		try {
			FundShareInfoModel model = new FundShareInfoModel();
			BeanUtils.copyProperties(model, dto);
			model.setUpdateBy(this.getLoginUserId());
			model.setUpdateDt(new Date());
			if(StringUtils.isNotEmpty(dto.getPkId())){
				FundShareInfoModel fundShar=fundShareInfoService.selectById(dto.getPkId());
				if(fundShar!=null){
					if(StringUtils.isNotEmpty(fundShar.getInvestorId())&&!fundShar.getInvestorId().equals(dto.getInvestorId())){
						//新增 判断是否已存在
						FundShareInfoModel fundS=new FundShareInfoModel();
						fundS.setFundId(dto.getFundId());
						fundS.setInvestorId(dto.getInvestorId());
						fundS.setStatus("0");
						List<FundShareInfoModel> list=fundShareInfoService.selectListByList(fundS);
						if(list!=null&&!list.isEmpty()){
							baseResponse.error("-1","投资人已存在");
							return JSONObject.toJSONString(baseResponse);
						}
					}

				}
				//修改
				fundShareInfoService.updateShareInfo(model,dto.getList(),dto.getProjId(), dto.getOldInvestorId());
			}else{
				//新增 判断是否已存在
				FundShareInfoModel fundS=new FundShareInfoModel();
				fundS.setFundId(dto.getFundId());
				fundS.setInvestorId(dto.getInvestorId());
				fundS.setStatus("0");
				List<FundShareInfoModel> list=fundShareInfoService.selectListByList(fundS);
				if(list!=null&&!list.isEmpty()){
					baseResponse.error("-1","投资人已存在");
					return JSONObject.toJSONString(baseResponse);
				}
				String pkId= serialnoService.getSequence("EI.RZ_T_FUND_SHARE_INFO");
				model.setPkId(pkId);
				fundShareInfoService.insert(model);
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
	@ApiOperation(value="股权结构详情", notes="详情")
	@ApiImplicitParam(name = "appId", value = "appId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/fundShare/ledgeM/detainfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detainfo(@RequestParam("pkId") String pkId){
		try {
			FundShareInfoModel  fundShareInfoModel=fundShareInfoService.selectById(pkId);
			System.out.print("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&1");
			if(fundShareInfoModel!=null){
                System.out.print("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&2");
				if(StringUtils.isNotEmpty(	fundShareInfoModel.getInvestorType())){
					String investorType = codeUtils.getCodeNameByValue("209",	fundShareInfoModel.getInvestorType());
					fundShareInfoModel.setInvestorTypeName(investorType);
				}
                System.out.print("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&3");
				if(StringUtils.isNotEmpty(fundShareInfoModel.getInveRole())){
                    System.out.print("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&4");
					String inveRoleName = codeUtils.getCodeNameByValue("244",fundShareInfoModel.getInveRole());
					fundShareInfoModel.setInveRoleName(inveRoleName);
				}
                System.out.print("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&5");
				if(StringUtils.isNotEmpty(	fundShareInfoModel.getInveProperty())){
					String invePropertyName = codeUtils.getCodeNameByValue("9015",fundShareInfoModel.getInveProperty());
					fundShareInfoModel.setInvePropertyName(invePropertyName);
				}
                System.out.print("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&15");
				if(StringUtils.isNotEmpty(	fundShareInfoModel.getInveCurrency())){
					String inveCurrencyName = codeUtils.getCodeNameByValue("103",fundShareInfoModel.getInveCurrency());
					fundShareInfoModel.setInveCurrencyName(inveCurrencyName);
				}
			}
            System.out.print("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&15");
			mapResponse.put("fundShareInfoModel",fundShareInfoModel);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			mapResponse.error(e.getMessage());
		} catch (Exception e) {
			mapResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);
	}


	@ApiOperation(value="股权结构详情", notes="详情")
	@ApiImplicitParam(name = "appId", value = "appId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/fundShare/getDetainfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detainfo(@RequestParam("projId") String projId,@RequestParam("investorId") String investorId){
		try {
            System.out.print("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&15");
			List<FofFundShareVO> listVo=new ArrayList<>();
			if(StringUtils.isNotEmpty(projId)){
				LedgerMagModel ledgerMagModel=new LedgerMagModel();
				ledgerMagModel.setProjId(projId);
				ledgerMagModel.setObjectId(investorId);
				ledgerMagModel.setStatus("0");
                System.out.print("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&17775");
				List<LedgerMagModel> list=ledgerMagService.selectList(ledgerMagModel);
                System.out.print("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&8888");
				if(list!=null&&!list.isEmpty()){
					for(LedgerMagModel mo:list){
                        System.out.print("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&0000000");
						FofFundShareVO vo=new FofFundShareVO();
						BeanUtils.copyProperties(vo, mo);
						//基金类型
						if(StringUtils.isNotEmpty(mo.getLedgerType())){
							String ledgerTypeName = codeUtils.getCodeNameByValue("242",mo.getLedgerType());
							vo.setLedgerTypeName(ledgerTypeName);
						}
						//资金对象
						if(StringUtils.isNotEmpty(mo.getLedgerObject())){
							String ledgerObjectName = codeUtils.getCodeNameByValue("244",mo.getLedgerObject());
							vo.setLedgerObjectName(ledgerObjectName);
						}
						if(StringUtils.isNotEmpty(mo.getOccurCurr())){
							String occurCurrName = codeUtils.getCodeNameByValue("103",mo.getOccurCurr());
							vo.setOccurCurrName(occurCurrName);
						}
						listVo.add(vo);
                        System.out.print("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&118");
					}
				}

			}
			mapResponse.put("listVo",listVo);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			mapResponse.error(e.getMessage());
		} catch (Exception e) {
			mapResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);
	}


	@ApiOperation(value="老系统-母基金-股权结构-列表", notes="现金流列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/fof/getFundShareList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getFundShareList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,  @RequestParam("fundId") String fundId,@RequestParam("projId")String projId){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("a.FUND_ID",fundId);
			searchCondition.addParam("FUND_ID",fundId);
			searchCondition.addParam("projId",projId);
			PageInfo<FundShareInfoModel> rows = fundShareInfoService.getFundShareList(searchCondition);
			//PageInfo<LedgerMagModel> rows = ledgerMagService.selectListFundPage(searchCondition);
			List<FundShareInfoVO> list = new ArrayList<FundShareInfoVO>();
			for(FundShareInfoModel model : rows.getList()){
				FundShareInfoVO vo = new FundShareInfoVO();
				BeanUtils.copyProperties(vo, model);
				if(StringUtils.isNotEmpty(model.getInvestorType())){
					String investorTypeName = codeUtils.getCodeNameByValue("209",model.getInvestorType());
					vo.setInvestorTypeName(investorTypeName);
				}
				if(StringUtils.isNotEmpty(model.getInveRole())){
					String inveRoleName = codeUtils.getCodeNameByValue("244",model.getInveRole());
					vo.setInveRoleName(inveRoleName);
				}
				if(StringUtils.isNotEmpty(model.getInveCurrency())){
					String inveCurrencyName = codeUtils.getCodeNameByValue("103",model.getInveCurrency());
					vo.setInveCurrencyName(inveCurrencyName);
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



	@ApiIgnore
	@ApiOperation(value = "母基金删除-股权结构", notes = "基金阶段")
	@ApiImplicitParam(name = "financeId", value = "financeId", required = true, dataType = "financeId")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/fofFundShare/del", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String del(@RequestParam("pkId") String pkId,@RequestParam("projId")  String projId) {
		try {
			FundShareInfoModel  model= fundShareInfoService.selectById(pkId);
			if(model!=null){
				String fundId=model.getFundId();
				String objectId=model.getInvestorId();
				LedgerMagModel mag=new LedgerMagModel();
				mag.setProjId(projId);
				mag.setObjectId(objectId);
				if(StringUtils.isNotEmpty(projId)&&StringUtils.isNotEmpty(objectId)){
					mag.setStatus("1");
					ledgerMagService.updateByObject(mag);
				}
				fundShareInfoService.deleteById(pkId);

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}



}

