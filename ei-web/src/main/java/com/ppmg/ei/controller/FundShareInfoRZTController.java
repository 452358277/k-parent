package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.foundation.service.SerialnoService;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.utils.UimUtilsUserId;
import com.ppmg.ei.vo.*;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.*;

@Controller
@Scope("prototype")
@Api(tags={"母基金"})
public class FundShareInfoRZTController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());


	@Reference
	private FundLedgerMagService fundLedgerMagService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@Reference
	private FundShareInfoService fundShareInfoService;

	@Reference
	private FundShareInfoRZTService fundShareInfoRZTService;

	@Reference
	private InveInfoService inveInfoService;


	@Reference
	private HandleRecordService handleRecordService;


	@Reference(check = false)
	private SerialnoService serialnoService;

	//************-股权结构 *************************************
	@ApiOperation(value="股权结构分页列表", notes="股权结构分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/selectFundSharInfoRZT", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectFundSharInfoRZT(@RequestParam("pageSize") int length, @RequestParam("currPage") int currPage
			,@RequestParam("fundId") String fundId){
		try {
			searchCondition.setPageSize(length);
			searchCondition.setCurrPage(currPage);
			if(StringUtils.isNotEmpty(fundId)){
				searchCondition.addConditionEqualTo("FUND_ID", fundId);
			}
			PageInfo<FundShareInfoModel> rows = fundShareInfoRZTService.selectListPage(searchCondition);
			List<FundShareInfoRZTVO> list = new ArrayList<FundShareInfoRZTVO>();
			//当前基金，所有投资人认缴金额
			Double All_d1 = fundShareInfoService.getInveAmountSum(fundId);
			for(FundShareInfoModel model : rows.getList()){
				FundShareInfoRZTVO vo = new FundShareInfoRZTVO();
				BeanUtils.copyProperties(model, vo);
				//1.计算股比：股比=认缴金额/所有投资人认缴金额，
				// 认缴金额
				Double d2 = model.getInveAmount();
				DecimalFormat df = new DecimalFormat("0%");
				if (All_d1 != null && d2 != null) {
					Double d = d2 / All_d1;
					logger.info("原数据1---》"+d);
					logger.info("转换后数据1---》" + df.format(d));
					//股比
					vo.setGB(df.format(d));
				}
				//2. 出资进度=累计出资金额/认缴金额
				// 累计出资金额
				Double d3 = 0.0;
				String pkId = model.getPkId();
				//查询台账里面出资金额
				List<FundLedgerMagModel> tz = fundLedgerMagService.selectFundLedgerMagList(pkId);
				for (FundLedgerMagModel fm : tz) {
					//出资金额：occurAmount
					d3 = d3 + fm.getRmbInveAmount();
				}
				//出资进度
				if(d2 != null && d3 != null){
					Double d4 = d3 / d2;
					logger.info("原数据2---》"+d3);
					logger.info("转换后数据2---》" + df.format(d4));
					//出资进度
					vo.setSchedule(df.format(d4));
				}
				//3.累计出资金额 = 台账里面出资金额相加（rmbInveAmount）
				// 累计出资金额（万元）
				vo.setTotalFinancial(d3);
				//累计出资金额（万元）币种
				vo.setTotalFinCurrency("1");
				String nameb = codeUtils.getCodeNameByValue("103","1");
				vo.setTotalFinCurrencyName(nameb);
				//累计出资金额（万元）币种
				String type = model.getInveType();
				if(StringUtils.isNotEmpty(type)){
					//投资人类型
					String name = codeUtils.getCodeNameByValue("11017",type);
					vo.setInveTypeName(name);
				}
				String type6 = model.getInveNature();
				if(StringUtils.isNotEmpty(type6)){
					//投资人属性
					String name = codeUtils.getCodeNameByValue("888",type6);
					vo.setInveNatureName(name);
				}
				String type1 = model.getInveRole();
				if(StringUtils.isNotEmpty(type1)){
					//投资角色
					String name = codeUtils.getCodeNameByValue("244",type1);
					vo.setInveRoleName(name);
				}
				String type2 = model.getInveCurrency();
				if(StringUtils.isNotEmpty(type2)){
					//认缴金额币种
					String name = codeUtils.getCodeNameByValue("103",type2);
					vo.setInveCurrencyName(name);
				}
				if (model != null && StringUtils.isNotEmpty(model.getInvestorId())) {
					//查询投资人名称
					InveInfoModel im = inveInfoService.selectById(model.getInvestorId());
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

	@ApiOperation(value="获取股权结构详细信息", notes="根据url的id来获取股权结构详细信息")
	@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/selectFundSharInfoRZTdetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectFundSharInfoRZTdetail(@RequestParam(value = "pkId") String id){
		try {
			FundShareInfoModel model = fundShareInfoRZTService.selectById(id);
			FundShareInfoRZTVO vo = new FundShareInfoRZTVO();
			BeanUtils.copyProperties(model,vo);
			String type = model.getInveType();
			if(StringUtils.isNotEmpty(type)){
				//投资人类型 1:个人，2机构
				String name = codeUtils.getCodeNameByValue("11017",type);
				vo.setInveTypeName(name);
			}
			String type1 = model.getInveRole();
			if(StringUtils.isNotEmpty(type1)){
				//投资角色,LP	1，GP	2，管理公司	3，其他	4
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
			if(StringUtils.isNotEmpty(type2)){
				//认缴金额币种，人民币	1，美元	2，欧元	3
				String name = codeUtils.getCodeNameByValue("103",type2);
				vo.setInveCurrencyName(name);
			}
			String type3 = model.getTotalFinCurrency();
			if(StringUtils.isNotEmpty(type3)){
				//累计出资币种，人民币	1，美元	2，欧元	3
				String name = codeUtils.getCodeNameByValue("103",type3);
				vo.setTotalFinCurrencyName(name);
			}
			if (model != null && StringUtils.isNotEmpty(model.getInvestorId())) {
				InveInfoModel im = inveInfoService.selectById(model.getInvestorId());
				//查询投资人名称
				if(im != null){
					vo.setInvestorIdName(im.getInvestorName());
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
	@ApiOperation(value="增加股权结构", notes="增加股权结构")
	@PostMapping(value = "/addFundSharInfoRZT", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addFundSharInfoRZT(@RequestBody FundShareInfoModel Model){
		try {
			if(Model != null){
				//String userId = String.valueOf(this.getLoginUser().getUserId());
				String userId = UimUtilsUserId.getUserId();
				String id = UUID.randomUUID().toString();
				Model.setPkId(id);
				Model.setCreateBy(userId);
				Model.setCreateDt(new Date());
				Model.setUpdateBy(userId);
				Model.setUpdateDt(new Date());
				fundShareInfoRZTService.insert(Model);
				HandleRecordModel handleRecordModel=new HandleRecordModel();
				String pkId1 = serialnoService.getSequence("EI.SEQ_EI_MY_HANDLE_RECORD");
				handleRecordModel.setPkId(pkId1);
				handleRecordModel.setProjectOrFundId(Model.getFundId());
				handleRecordModel.setContent(Model.getInvestorName());
				handleRecordModel.setContentOld("新增");
				handleRecordModel.setColumResource("基金合伙人");
				handleRecordModel.setColumName("del");
				handleRecordModel.setUpdateBy("1");
				handleRecordModel.setUpdateDt(new Date());
				handleRecordService.insert(handleRecordModel);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	@ApiOperation(value="更新股权结构", notes="更新股权结构")
	@PostMapping(value = "/updateFundSharInfoRZT", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateFundSharInfoRZT(@RequestBody FundShareInfoModel Model){
		try {
			if(Model != null){
				String userId = String.valueOf(this.getLoginUser().getUserId());
				//String userId = UimUtilsUserId.getUserId();
				Model.setUpdateBy(userId);
				Model.setUpdateDt(new Date());
				fundShareInfoRZTService.updateByInfo(Model);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	@ApiOperation(value="股权结构-删除", notes="股权结构-删除")
	@RequestMapping(value = "/deleteFundSharInfoRZT", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteProjContractFileQuit(@RequestParam("id") String id) {
		try {
			String[] arr = null;
			if (id != "" && id != null) {
				arr = id.split(",");
				for (String pkId : arr) {
					FundShareInfoModel fundShareInfoModel=	fundShareInfoRZTService.selectById(id);
					if(fundShareInfoModel!=null){
						HandleRecordModel handleRecordModel=new HandleRecordModel();
						String pkId1 = serialnoService.getSequence("EI.SEQ_EI_MY_HANDLE_RECORD");
						handleRecordModel.setPkId(pkId1);
						handleRecordModel.setProjectOrFundId(fundShareInfoModel.getFundId());
						handleRecordModel.setContent("删除操作");
						handleRecordModel.setContentOld("删除操作");
						handleRecordModel.setColumResource("基金合伙人");
						handleRecordModel.setColumName("del");
						handleRecordModel.setUpdateBy("1");
						handleRecordModel.setUpdateDt(new Date());
						handleRecordService.insert(handleRecordModel);
					}

					fundShareInfoRZTService.deleteById(pkId);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}
	/*********台账管理******************华丽分割线*************/

}

