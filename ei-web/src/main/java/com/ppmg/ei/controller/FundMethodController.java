package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.foundation.service.SerialnoService;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.dto.FundMethodDTO;
import com.ppmg.ei.model.FundMethodModel;
import com.ppmg.ei.service.FundMethodService;
import com.ppmg.ei.vo.FundMethodVO;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
@Scope("prototype")
@Api(tags={"基金管理办法接口"})
@RequestMapping("/fundMethod")
public class FundMethodController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private FundMethodService fundMethodService;

	@Reference(check = false)
	private SerialnoService serialnoService;


	@ApiOperation(value="基金管理办法列表", notes="基金定期报告列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
			@ApiImplicitParam(name = "fundId", value = "基金ID", required = true, dataType = "String")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/getList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getList(@RequestParam("length") int length, @RequestParam("currPage") int currPage, @RequestParam("fundId") String fundId){
		try {

			searchCondition.setPageSize(length);
			searchCondition.setCurrPage(currPage);
			if(StringUtils.isNotEmpty(fundId)){
				searchCondition.addConditionEqualTo("FUND_ID",fundId );
			}
			searchCondition.addConditionNotEqualTo("status","1");
			PageInfo<FundMethodModel> rows = fundMethodService.selectListPage(searchCondition);
			List<FundMethodVO> list = new ArrayList<FundMethodVO>();
			for(FundMethodModel model : rows.getList()){
				FundMethodVO vo = new FundMethodVO();
				BeanUtils.copyProperties(model,vo);
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

	@ApiOperation(value="相关资料新增", notes="相关资料新增")
	@ApiImplicitParam(name = "FundMethodDTO", value = "相关资料新增", required = true, dataType = "FundMcInfoVO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/save", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String save(@RequestBody FundMethodDTO dto){
		try {
			long userId = this.getLoginUser().getUserId();
			FundMethodModel model = new FundMethodModel();
			BeanUtils.copyProperties(dto, model);
			model.setUpdateBy(String.valueOf(userId));
			model.setUpdateDt(new Date());
			if(StringUtils.isNotEmpty(dto.getMethodId())){
				fundMethodService.update(model);
			}else {
				model.setCreateBy(String.valueOf(userId));
				model.setCreateDt(new Date());
				model.setStatus("0");
				model.setMethodId(serialnoService.getSequence("EI.BD_T_FUND_METHOD"));
				fundMethodService.insert(model);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiOperation(value="管理办法详细信息", notes="根据url的id来获取XXX详细信息")
	@ApiImplicitParam(name = "id", value = "XXXID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/getdetail/{methodId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getFundReport(@PathVariable(value = "methodId") String methodId){
		try {
			FundMethodModel model = fundMethodService.selectById(methodId);
			FundMethodVO vo = new FundMethodVO();
			BeanUtils.copyProperties(model, vo);
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
	@ApiOperation(value="删除", notes="删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "consId", value = "consId", required = true, dataType = "String",paramType = "path"),
	})
	@GetMapping(value = "/delete/{methodId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@PathVariable("methodId") String methodId){
		try {
			String[] ids = methodId.split(",");
			for(int i = 0; i < ids.length; i++){
				if(StringUtils.isNotEmpty(ids[i])){
					FundMethodModel model=new FundMethodModel();
					model.setStatus("1");
					model.setMethodId(ids[i]);
					fundMethodService.update(model);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);

	}

}

