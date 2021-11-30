package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.core.utils.DateUtil;
import com.founder.ssm.core.vo.BaseResponse;
import com.founder.ssm.core.vo.BaseVO;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.dto.SmsDetailInfoDTO;
import com.ppmg.ei.dto.SmsInfoDTO;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.vo.SmsDetailInfoVO;
import com.ppmg.ei.vo.SmsInfoVO;
import io.swagger.annotations.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
@RestController
@RequestMapping("/smsInfo")
@Scope("prototype")
@Api(tags={"短信发送接口"})
public class SmsInfoController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private SmsInfoService smsInfoService;

	@Reference(check = false)
	private AdminService adminService;

	@Reference
	private SmsDetailInfoService smsDetailInfoService;


	@Reference
	private BankInfoService bankInfoService;

	@Reference
	private FundMcInfoService fundMcInfoService;


	@Resource(name = "codeUtils")
	private CodeUtils codeUtils;



	@ApiOperation(value="短信发送列表", notes="短信发送列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
		@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/getSmsInfoList", produces = "application/json;charset=UTF-8")
	public BaseResponse getSmsInfoList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage
						, String smsContent, String status){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			if (StringUtils.isNotBlank(smsContent)) {
				searchCondition.addConditionEqualTo("SMS_CONTENT", smsContent);
			}
			searchCondition.addConditionEqualTo("SMS_TYPE","0");
			if (StringUtils.isNotBlank(status)) {
				searchCondition.addConditionIn("SMS_STATUS", Arrays.asList(status.split(",")));
			}
			searchCondition.addConditionEqualTo("SMS_MODULAR", "1");
			searchCondition.addConditionEqualTo("IS_DELETE", "0");
			PageInfo<SmsInfoModel> rows = smsInfoService.selectListPage(searchCondition);
			List<SmsInfoVO> list = new ArrayList<>();
			List<SmsModel> smsModels = smsInfoService.getSmsModel();
			Map<String, String> smsModelMap = setMap(smsModels);
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			for(SmsInfoModel model : rows.getList()){
				SmsInfoVO vo = new SmsInfoVO();
				BeanUtils.copyProperties(model, vo);
				if(model.getSmsTime()!=null){
					String smsTimeStr=format.format(model.getSmsTime());
					vo.setSmsTimeStr(smsTimeStr);
				}
				StringBuilder sb = new StringBuilder();
				if (CollectionUtils.isNotEmpty(model.getDetails())) {
					List<SmsDetailInfoVO> detailVos = new ArrayList<>();
					for (SmsDetailInfoModel detail: model.getDetails()) {
						SmsDetailInfoVO detailVO = new SmsDetailInfoVO();
						BeanUtils.copyProperties(detail, detailVO);
						detailVos.add(detailVO);
						sb.append(detail.getReceiveName()).append(",");
					}
					vo.setDetails(detailVos);
					vo.setReceivers(sb.substring(0, sb.length() - 1));
				}
				if (StringUtils.isNotBlank(vo.getSmsStatus())) {
					vo.setSmsStatusName(codeUtils.getCodeNameByValue("60024", vo.getSmsStatus()));
				}
				if (StringUtils.isNotBlank(vo.getIsFinish())) {
					vo.setIsFinishName("1".equals(vo.getIsFinish()) ? "是" : "否");
				}
				if (StringUtils.isNotBlank(vo.getSmsContent())) {
					vo.setSmsContentName(smsModelMap.get(vo.getSmsContent()));
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
		return dataTablesResponse;
	}

	public Map<String, String> setMap(List<SmsModel> list) {
		Map<String, String> result = new HashMap<>();
		for (SmsModel model: list) {
			result.put(model.getCode(), model.getLabel());
		}
		return result;
	}

	@ApiOperation(value="获取短信发送详细信息", notes="根据url的id来获取短信发送详细信息")
	@ApiImplicitParam(name = "id", value = "短信发送ID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
   })
	@GetMapping(value = "/getSmsInfoById", produces = "application/json;charset=UTF-8")
	public BaseResponse detail(@RequestParam(value = "smsId") String smsId){
		try {
			SmsInfoModel model = smsInfoService.selectById(smsId);
			BaseVO vo = new BaseVO();
			BeanUtils.copyProperties(vo, model);
			dataResponse.setData(vo);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataResponse.error(e.getMessage());
		} catch (Exception e) {
			dataResponse.error();
			logger.error(e.getMessage(), e);
		}
		return dataResponse;
	}

	@ApiOperation(value="获取基金管理人和出资人代表", notes="获取划转企业联系信息")
	@ApiImplicitParam(name = "id", value = "短信发送ID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value="/getAdmins", produces = "application/json;charset=UTF-8")
	public BaseResponse getEntInfos() {
		try {
			AdminModel adminModel=new AdminModel();
			List<AdminModel> entInfos =adminService.getListInfo(adminModel);
			mapResponse.put("list", entInfos);
		}catch (SystemException e) {
			logger.error(e.getMessage());
			mapResponse.error(e.getMessage());
		} catch (Exception e) {
			mapResponse.error();
			logger.error(e.getMessage(), e);
		}
		return mapResponse;
	}

	@ApiOperation(value="新增短信", notes="新增短信")
	@PostMapping(value = "/addSmsInfo", produces = "application/json;charset=UTF-8")
	public BaseResponse addSmsInfo(@RequestBody SmsInfoDTO dto) {
		try {
			String loginUserId = this.getLoginUserId();
			Date date = new Date();

			SmsInfoModel smsInfoModel = new SmsInfoModel();
			BeanUtils.copyProperties(dto, smsInfoModel);
			smsInfoModel.setUpdateBy(loginUserId);
			smsInfoModel.setUpdateDt(date);
			smsInfoModel.setSmsType("0");

			String smsId = smsInfoModel.getSmsId();
			if (StringUtils.isBlank(smsId)) {
				smsId = UUID.randomUUID().toString();
				smsInfoModel.setSmsId(smsId);
				smsInfoModel.setCreateBy(loginUserId);
				smsInfoModel.setCreateDt(date);
				if ("2".equals(smsInfoModel.getSmsStatus())) {
					// 发送短信
					Map<String, String> phonesAndEntName = new HashMap<>();
					for (SmsDetailInfoDTO detail: dto.getDetails()) {
						phonesAndEntName.put(detail.getReceivePhone(), detail.getReceiveName());
					}
					sendMsg(smsInfoModel, phonesAndEntName);
					smsInfoModel.setSmsTime(date);
				}
				if (!"1".equals(smsInfoModel.getIsFinish())) {
					smsInfoModel.setFinishDate(null);
				}
				smsInfoService.insert(smsInfoModel);
			} else {
				if ("2".equals(smsInfoModel.getSmsStatus())) {
					// 发送短信
					Map<String, String> phonesAndEntName = new HashMap<>();
					for (SmsDetailInfoDTO detail: dto.getDetails()) {
						phonesAndEntName.put(detail.getReceivePhone(), detail.getReceiveName());



					}
					sendMsg(smsInfoModel, phonesAndEntName);
					if (!"1".equals(smsInfoModel.getIsFinish())) {
						smsInfoModel.setFinishDate(null);
					}
					smsInfoModel.setSmsTime(date);
				}
				smsInfoService.update(smsInfoModel);
				smsInfoService.deleteDetails(smsId);
			}

			List<SmsDetailInfoModel> detailInfoModels  = new ArrayList<>();
			for (SmsDetailInfoDTO detail: dto.getDetails()) {
				SmsDetailInfoModel detailInfoModel = new SmsDetailInfoModel();
				BeanUtils.copyProperties(detail, detailInfoModel);
				detailInfoModel.setDetailId(UUID.randomUUID().toString());
				detailInfoModel.setCreateDt(date);
				detailInfoModel.setCreateBy(loginUserId);
				detailInfoModel.setUpdateDt(date);
				detailInfoModel.setUpdateBy(loginUserId);
				detailInfoModel.setSmsId(smsId);
				detailInfoModels.add(detailInfoModel);
			}
			smsDetailInfoService.insertBatch(detailInfoModels);


		}catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			baseResponse.error();
			logger.error(e.getMessage(), e);
		}
		return baseResponse;
	}

	// appId
	private static String appId = "1400292074";

	// appId
	private static String appKey = "28167eaf20e1210bc8f49891532c9e95";

	// 短信签名
	private static String smsSign = "金财投资";
	public void sendMsg(SmsInfoModel model, Map<String, String> phonesAndEntName) {

		for (Map.Entry<String, String> entry : phonesAndEntName.entrySet() ) {
			List<String> params = new ArrayList<String>();
			params.add(entry.getValue());
			// 如果有截止日期的，第二个参数为截止日期
			if ("1".equals(model.getIsFinish())) {
				String finishDate = DateUtil.dateToString("yyyy-MM-dd", model.getFinishDate());
				params.add(finishDate);
			}
			SmsMultiSender msender = new SmsMultiSender(Integer.valueOf(appId), appKey);
			SmsMultiSenderResult result =null;
			try {

				List<String> phones = new ArrayList<>();
				phones.add(entry.getKey());
				logger.info("*********发送短信*****");
				result = msender.sendWithParam("86", (ArrayList)phones, Integer.valueOf(model.getSmsContent()), (ArrayList) params, smsSign, "", "");
				logger.info("*******result*****后");
				if(result!=null && result.result == 0){
					model.setSmsStatus2("0");
				}else{
					//发送失败
					model.setSmsStatus2("1");
				}
			}catch (Exception e) {
				logger.error( "发送短信失败", e);
			}
		}
	}

	@ApiOperation(value="删除短信", notes="删除短信")
	@GetMapping(value = "/deleteSmsInfo", produces = "application/json;charset=UTF-8")
	public BaseResponse addSmsInfo(@RequestParam("smsId") String smsId) {
		try {
			smsInfoService.deleteSmsInfo(smsId);
			smsInfoService.deleteDetails(smsId);
		}catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			baseResponse.error();
			logger.error(e.getMessage(), e);
		}
		return baseResponse;
	}

	@ApiOperation(value="直投获取短信模板", notes="获取短信模板")
	@GetMapping(value = "/getSmsModel", produces = "application/json;charset=UTF-8")
	public BaseResponse getSmsModel() {
		try {
			List<SmsModel> smsModels = smsInfoService.getSmsModel();
			mapResponse.put("list", smsModels);
		}catch (SystemException e) {
			logger.error(e.getMessage());
			mapResponse.error(e.getMessage());
		} catch (Exception e) {
			mapResponse.error();
			logger.error(e.getMessage(), e);
		}
		return mapResponse;
	}


	@ApiOperation(value="查询省政府二级基金管理人对应的托管行", notes="获取划转企业联系信息")
	@ApiImplicitParam(name = "id", value = "短信发送ID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value="/getBankList", produces = "application/json;charset=UTF-8")
	public BaseResponse getBankList(String keyword) {
		try {
			if(StringUtils.isNotEmpty(keyword)){
				searchCondition.addParam("keyword","'%"+ keyword.trim()+"%'");
			}
			List<BankInfoModel> list=bankInfoService.getBankList(searchCondition);
			mapResponse.put("list", list);
		}catch (SystemException e) {
			logger.error(e.getMessage());
			mapResponse.error(e.getMessage());
		} catch (Exception e) {
			mapResponse.error();
			logger.error(e.getMessage(), e);
		}
		return mapResponse;
	}

	@ApiOperation(value="查询省政府二级基金管理人对应的管理人", notes="获取划转企业联系信息")
	@ApiImplicitParam(name = "id", value = "短信发送ID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value="/getFundMcPhoneList", produces = "application/json;charset=UTF-8")
	public BaseResponse getFundMcPhoneList(String keyword) {
		try {
			if(StringUtils.isNotEmpty(keyword)){
				searchCondition.addParam("keyword","'%"+ keyword.trim()+"%'");
			}
			List<FundInfoModel> list=bankInfoService.getFundMcPhoneList(searchCondition);
			mapResponse.put("list", list);
		}catch (SystemException e) {
			logger.error(e.getMessage());
			mapResponse.error(e.getMessage());
		} catch (Exception e) {
			mapResponse.error();
			logger.error(e.getMessage(), e);
		}
		return mapResponse;
	}


	@ApiOperation(value="省政府获取短信模板", notes="获取短信模板")
	@GetMapping(value = "/getGovSmsModel", produces = "application/json;charset=UTF-8")
	public BaseResponse getGovSmsModel(String parentId) {
		try {
			if(StringUtils.isNotEmpty(parentId)){
				searchCondition.addParam("parentId",parentId);
			}
			List<SmsModel> smsModels = smsInfoService.getGovSmsModel(searchCondition);
			mapResponse.put("list", smsModels);
		}catch (SystemException e) {
			logger.error(e.getMessage());
			mapResponse.error(e.getMessage());
		} catch (Exception e) {
			mapResponse.error();
			logger.error(e.getMessage(), e);
		}
		return mapResponse;
	}


	@ApiOperation(value="省政府短信发送列表", notes="短信发送列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/getGovSmsInfoList", produces = "application/json;charset=UTF-8")
	public BaseResponse getGovSmsInfoList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage
			, String smsContent, String status){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("SMS_TYPE","0");
			if (StringUtils.isNotBlank(smsContent)) {
				searchCondition.addConditionEqualTo("SMS_CONTENT", smsContent);
			}
			if (StringUtils.isNotBlank(status)) {
				searchCondition.addConditionIn("SMS_STATUS", Arrays.asList(status.split(",")));
			}
			String sms_modular="3,4";
			searchCondition.addConditionIn("SMS_MODULAR", Arrays.asList(sms_modular.split(",")));
			searchCondition.addConditionEqualTo("IS_DELETE", "0");
			PageInfo<SmsInfoModel> rows = smsInfoService.selectListPage(searchCondition);
			List<SmsInfoVO> list = new ArrayList<>();
			List<SmsModel> smsModels = smsInfoService.getGovSmsModel(searchCondition);
			Map<String, String> smsModelMap = setMap(smsModels);
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			for(SmsInfoModel model : rows.getList()){
				SmsInfoVO vo = new SmsInfoVO();
				BeanUtils.copyProperties(model, vo);
				if(model.getSmsTime()!=null){
					String smsTimeStr=format.format(model.getSmsTime());
					vo.setSmsTimeStr(smsTimeStr);
				}
				StringBuilder sb = new StringBuilder();
				if (CollectionUtils.isNotEmpty(model.getDetails())) {
					List<SmsDetailInfoVO> detailVos = new ArrayList<>();
					for (SmsDetailInfoModel detail: model.getDetails()) {
						SmsDetailInfoVO detailVO = new SmsDetailInfoVO();
						BeanUtils.copyProperties(detail, detailVO);
						detailVos.add(detailVO);
						sb.append(detail.getReceiveName()).append(",");
					}
					vo.setDetails(detailVos);
					vo.setReceivers(sb.substring(0, sb.length() - 1));
				}
				if (StringUtils.isNotBlank(vo.getSmsStatus())) {
					vo.setSmsStatusName(codeUtils.getCodeNameByValue("60024", vo.getSmsStatus()));
				}
				if (StringUtils.isNotBlank(vo.getQuarter())) {
					vo.setQuarterName(codeUtils.getCodeNameByValue("2233", vo.getQuarter()));
				}

				if (StringUtils.isNotBlank(vo.getIsFinish())) {
					vo.setIsFinishName("1".equals(vo.getIsFinish()) ? "是" : "否");
				}
				if (StringUtils.isNotBlank(vo.getSmsContent())) {
					vo.setSmsContentName(smsModelMap.get(vo.getSmsContent()));
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
		return dataTablesResponse;
	}


	@ApiOperation(value="省政府新增短信", notes="新增短信")
	@PostMapping(value = "/addGovSmsInfo", produces = "application/json;charset=UTF-8")
	public BaseResponse addGovSmsInfo(@RequestBody SmsInfoDTO dto) {
		try {
			String loginUserId = this.getLoginUserId();
			Date date = new Date();

			SmsInfoModel smsInfoModel = new SmsInfoModel();
			BeanUtils.copyProperties(dto, smsInfoModel);
			smsInfoModel.setUpdateBy(loginUserId);
			smsInfoModel.setUpdateDt(date);
			smsInfoModel.setSmsType("0");

			String smsId = smsInfoModel.getSmsId();
			if (StringUtils.isBlank(smsId)) {
				smsId = UUID.randomUUID().toString();
				smsInfoModel.setSmsId(smsId);
				smsInfoModel.setCreateBy(loginUserId);
				smsInfoModel.setCreateDt(date);
				if ("2".equals(smsInfoModel.getSmsStatus())) {
					// 发送短信
					Map<String, String> phonesAndEntName = new HashMap<>();
					for (SmsDetailInfoDTO detail: dto.getDetails()) {
						phonesAndEntName.put(detail.getReceivePhone(), detail.getReceiveName());
					}
					sendGovMsg(smsInfoModel, phonesAndEntName,dto.getCodeValue());
					smsInfoModel.setSmsTime(date);
				}
				if (!"1".equals(smsInfoModel.getIsFinish())) {
					smsInfoModel.setFinishDate(null);
				}
				smsInfoService.insert(smsInfoModel);
			} else {
				if ("2".equals(smsInfoModel.getSmsStatus())) {
					// 发送短信
					Map<String, String> phonesAndEntName = new HashMap<>();
					for (SmsDetailInfoDTO detail: dto.getDetails()) {
						phonesAndEntName.put(detail.getReceivePhone(), detail.getReceiveName());

					}
					sendGovMsg(smsInfoModel, phonesAndEntName,dto.getCodeValue());
					if (!"1".equals(smsInfoModel.getIsFinish())) {
						smsInfoModel.setFinishDate(null);
					}
					smsInfoModel.setSmsTime(date);
				}
				smsInfoService.update(smsInfoModel);
				smsInfoService.deleteDetails(smsId);
			}

			List<SmsDetailInfoModel> detailInfoModels  = new ArrayList<>();
			for (SmsDetailInfoDTO detail: dto.getDetails()) {
				SmsDetailInfoModel detailInfoModel = new SmsDetailInfoModel();
				BeanUtils.copyProperties(detail, detailInfoModel);
				detailInfoModel.setDetailId(UUID.randomUUID().toString());
				detailInfoModel.setCreateDt(date);
				detailInfoModel.setCreateBy(loginUserId);
				detailInfoModel.setUpdateDt(date);
				detailInfoModel.setUpdateBy(loginUserId);
				detailInfoModel.setSmsId(smsId);
				detailInfoModels.add(detailInfoModel);
			}
			smsDetailInfoService.insertBatch(detailInfoModels);


		}catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			baseResponse.error();
			logger.error(e.getMessage(), e);
		}
		return baseResponse;
	}


	public void sendGovMsg(SmsInfoModel model, Map<String, String> phonesAndEntName,String codeValue) {

		for (Map.Entry<String, String> entry : phonesAndEntName.entrySet() ) {
			List<String> params = new ArrayList<String>();
			params.add(entry.getValue());
			// 如果有截止日期的，第二个参数为截止日期
			if("948920".equals(codeValue)){
				params.add(model.getYear());
			}
			if("948919".equals(codeValue)){
				params.add(model.getYear());
				params.add(model.getQuarter());
			}
			//托管银行
			if("948925".equals(codeValue)){
				params.add(model.getYear());
				params.add(model.getQuarter());
			}
			if("948927".equals(codeValue)){
				params.add(model.getYear());
			}

			if ("1".equals(model.getIsFinish())) {
				String finishDate = DateUtil.dateToString("yyyy-MM-dd", model.getFinishDate());
				params.add(finishDate);
			}
			SmsMultiSender msender = new SmsMultiSender(Integer.valueOf(appId), appKey);
			SmsMultiSenderResult result =null;
			try {

				List<String> phones = new ArrayList<>();
				phones.add(entry.getKey());
				logger.info("*********发送短信*****");
				result = msender.sendWithParam("86", (ArrayList)phones, Integer.valueOf(model.getSmsContent()), (ArrayList) params, smsSign, "", "");
				logger.info("*******result*****后");
				if(result!=null && result.result == 0){
					model.setSmsStatus2("0");
				}else{
					//发送失败
					model.setSmsStatus2("1");
				}
			}catch (Exception e) {
				logger.error( "发送短信失败", e);
			}
		}
	}

}

