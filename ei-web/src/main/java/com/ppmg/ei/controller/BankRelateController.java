package com.ppmg.ei.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.founder.uim.util.Encrypt;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.bean.CashFlowSearchBean;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.BankInfoService;
import com.ppmg.ei.service.LedgerMagService;
import com.ppmg.ei.vo.BankRelateVO;
import com.ppmg.ei.vo.FundMcInfoVO;
import com.ppmg.ei.vo.LedgerMagVO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.ppmg.ei.service.BankRelateService;
@Controller
@Scope("prototype")
@Api(tags={"母基金账号接口"})
public class BankRelateController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private BankRelateService bankRelateService;

	@Reference
	private LedgerMagService ledgerMagService;

	@Reference
	private BankInfoService bankInfoService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@ApiOperation(value="母基金账号", notes="母基金账号")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/bankRelateList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String bankRelateList(@RequestParam("length") int length, @RequestParam("currPage") int currPage){
		try {
			searchCondition.setPageSize(length);
			searchCondition.setCurrPage(currPage);
			//searchCondition.addConditionEqualTo("STATUS","0");
			String userId=this.getLoginUserId();
			//String userId="1005000";
			//searchCondition.addConditionEqualTo("CREATE_BY",userId);
			PageInfo<BankRelateModel> rows = bankRelateService.selectListPage(searchCondition);
			List<BankRelateVO> list = new ArrayList<BankRelateVO>();
			for(BankRelateModel model : rows.getList()){
				BankRelateVO vo = new BankRelateVO();
				BeanUtils.copyProperties(vo, model);
				if(StringUtils.isNotEmpty(vo.getAccountType())){
					vo.setAccountTypeName(codeUtils.getCodeNameByValue("1014", model.getAccountType()));
				}

				//根据bankId查询对应的userId
				BankInfoModel bank=bankInfoService.selectById(model.getBankId());
				//searchCondition.addParam("BANK_ID",model.getBankId());
				//searchCondition.addParam("COMPANY_NUMBER",model.getFundId());
				if(bank!=null){
					vo.setBankName(bank.getBankName());
					LedgerMagModel ledgerMagModel=new LedgerMagModel();
					ledgerMagModel.setCompanyNumber(model.getFundId());
					ledgerMagModel.setStatus("0");
                    //ledgerMagModel.setCreateBy(bank.getUserId());
					ledgerMagModel.setAccounts(model.getAccounts());
					List<LedgerMagModel> rows1 = ledgerMagService.selectList(ledgerMagModel);
					if(rows1!=null&&rows1.size()>0){
						LedgerMagVO vo1=new LedgerMagVO();
						BeanUtils.copyProperties(vo1, rows1.get(0));
						vo.setLedgerMagVO(vo1);

					}else{
						LedgerMagVO ledgerMagModel1=new LedgerMagVO();
						ledgerMagModel1.setAccountBalance(Double.valueOf(0));
						vo.setLedgerMagVO(ledgerMagModel1);
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




	@ApiOperation(value="母基金账号新增", notes="母基金账号新增")
	@ApiImplicitParam(name = "BankRelateVO", value = "BankRelateVO", required = true, dataType = "BankRelateVO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/bankRelate/add", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(@RequestBody BankRelateVO dto){

		try {
             //判断是否存在
			if(StringUtils.isNotBlank(dto.getFundId())&&StringUtils.isNotBlank(dto.getAccounts())&&StringUtils.isNotBlank(dto.getBankId())){
				BankRelateModel bankRelateModel=new BankRelateModel();
				bankRelateModel.setFundId(dto.getFundId());
				bankRelateModel.setBankId(dto.getBankId());
				//bankRelateModel.setAccounts(dto.getAccounts());
				BankRelateModel bank=bankRelateService.selectBy(bankRelateModel);
				if(bank!=null){
					baseResponse.setMsg("母基金在该银行账户已存在");
				}else{
					BankRelateModel bankRelate=new BankRelateModel();
					BeanUtils.copyProperties(bankRelate,dto);
					String userId=this.getLoginUserId();
					//String userId="1005000";
					bankRelateService.insertById(bankRelate,userId);
				}
			}


		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse,SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);

	}

	@ApiIgnore
	@ApiOperation(value="更新母基金托管银行", notes="根据url的id来指定更新工作进展")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "BankRelateVO", value = "BankRelateVO", required = true, dataType = "BankRelateVO")
	})
	@PostMapping(value = "/bankRelatenfo/update", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update( @RequestBody BankRelateVO dto){
		try {
			BankRelateModel model = new BankRelateModel();
			BeanUtils.copyProperties(model, dto);
			model.setUpdateBy(this.getLoginUserId());
			model.setUpdateDt(new Date());
			bankRelateService.updateByUserRelate(model);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}


	@ApiIgnore
	@ApiOperation(value="启用禁用", notes="根据url的id来指定更新工作进展")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "BankRelateVO", value = "BankRelateVO", required = true, dataType = "BankRelateVO")
	})
	@GetMapping(value = "/bankRelatenfo/openOrClose", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String openOrClose(@RequestParam("bankId")String bankId,@RequestParam("fundId")String fundId,@RequestParam("status")String status,@RequestParam("accounts")String accounts){
		try {
			//String userId="1005000";
			String userId=this.getLoginUserId();
			bankRelateService.updateOpenOrClose(bankId,fundId,status,accounts,userId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);

	}

}

