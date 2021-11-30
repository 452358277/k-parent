package com.ppmg.ei.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.model.BankInfoModel;
import com.ppmg.ei.model.FundUserRelateModel;
import com.ppmg.ei.model.LedgerMagModel;
import com.ppmg.ei.service.BankInfoService;
import com.ppmg.ei.service.FundUserRelateService;
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
import com.ppmg.ei.model.BankProductModel;
import com.ppmg.ei.service.BankProductService;
@Controller
@Scope("prototype")
@Api(tags={"理财产品接口"})
public class BankProductController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private BankProductService bankProductService;

	@Reference
	private BankInfoService bankInfoService;


	@Reference
	private FundUserRelateService fundUserRelateService;

	@ApiIgnore
	@ApiOperation(value="理财产品", notes="理财产品")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "fundId", value = "fundId", required = true, dataType = "String",paramType = "path"),
	})
	@GetMapping(value = "/product/{fundId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String product(@PathVariable("fundId") String fundId){
		try {
			//根据登录人查询是哪个银行
			String userId=this.getLoginUserId();
			//String userId="1005003";
			//BankInfoModel bankInfoModel=bankInfoService.selectByUserId(userId);

			FundUserRelateModel fundUserRelateModel=new FundUserRelateModel();
			fundUserRelateModel.setFundId(fundId);
			fundUserRelateModel.setUserId(userId);
			FundUserRelateModel fundUserRelate=fundUserRelateService.selectBy(fundUserRelateModel);
			if(fundUserRelate!=null&&StringUtils.isNotEmpty(fundUserRelate.getAccounts())){
				BankProductModel bankProductModel=new BankProductModel();
				//bankProductModel.setBankId(bankInfoModel.getBankId());
				bankProductModel.setFundId(fundId);
				bankProductModel.setStatus("0");
				bankProductModel.setAccounts(fundUserRelate.getAccounts());
				List<BankProductModel> list= bankProductService.selectList(bankProductModel);
				mapResponse.put("list", list);
			}
		/*	if(bankInfoModel!=null){
              //根据基金id 和 bankId 查询产品编号
				BankProductModel bankProductModel=new BankProductModel();
				bankProductModel.setBankId(bankInfoModel.getBankId());
				bankProductModel.setFundId(fundId);
				bankProductModel.setStatus("0");
				bankProductModel.setAccounts(bankInfoModel);
				List<BankProductModel> list= bankProductService.selectList(bankProductModel);
				mapResponse.put("list", list);
			}*/

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);

	}

}

