package com.ppmg.ei.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.vo.FundValuationVO;
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
import com.ppmg.ei.model.FundValuationModel;
import com.ppmg.ei.service.FundValuationService;
@Controller
@Scope("prototype")
@Api(tags={"基金估值接口"})
public class FundValuationController extends BaseAction{

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private FundValuationService fundValuationService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@ApiOperation(value="基金估值列表", notes="基金估值列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
		@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
		@ApiImplicitParam(name = "fundId", value = "基金ID", required = true, dataType = "String")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/fundValuationList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String fundValuationList(@RequestParam("length") int length, @RequestParam("currPage") int currPage, @RequestParam("fundId") String fundId){
		try {

			searchCondition.setPageSize(length);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("FUND_ID", fundId);
			searchCondition.addConditionNotEqualTo("STATUS","9");
			PageInfo<FundValuationModel> rows = fundValuationService.selectListPage(searchCondition);
			List<FundValuationVO> list = new ArrayList<FundValuationVO>();
			for(FundValuationModel model : rows.getList()){
				FundValuationVO vo = new FundValuationVO();
				/*vo.setAssessByName("金祖庆");
				vo.setAssessDt(new Date());
				vo.setInveValuation(1.88);
				vo.setIvCurrencyName("人民币");*/
				BeanUtils.copyProperties(vo, model);
				vo.setIvCurrencyName(codeUtils.getCodeNameByValue("103", model.getIvCurrency()));

				list.add(vo);
				/*FundValuationVO vo1 = new FundValuationVO();
				vo1.setAssessByName("蔡晨");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				vo1.setAssessDt(sdf.parse("2017-11-11"));
				vo1.setInveValuation(1.22);
				vo1.setIvCurrencyName("人民币");
				list.add(vo1);*/
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

	/*@ApiOperation(value="获取基金估值详细信息", notes="根据url的id来获取基金估值详细信息")
	@ApiImplicitParam(name = "id", value = "XXXID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
   })
	@GetMapping(value = "/XXXInfo/{id}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detail(@PathVariable(value = "id") String id){
		try {
			FundValuationModel model = fundValuationService.selectById(id);
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
		return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteMapNullValue);
	}*/

}

