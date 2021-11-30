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
import com.ppmg.ei.dto.FundConsDTO;
import com.ppmg.ei.model.FundConsModel;
import com.ppmg.ei.service.FundConsService;
import com.ppmg.ei.vo.FundConsVO;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
@Scope("prototype")
@Api(tags={"基金合伙协议（章程）及修正案信息接口"})
@RequestMapping("/fundCons")
public class FundConsController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private FundConsService fundConsService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@Reference(check = false)
	private SerialnoService serialnoService;


	@ApiOperation(value="基金合伙协议列表", notes="基金定期报告列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
			@ApiImplicitParam(name = "fundId", value = "基金ID", required = true, dataType = "String")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/fundCons/getList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getList(@RequestParam("length") int length, @RequestParam("currPage") int currPage, @RequestParam("fundId") String fundId){
		try {

			searchCondition.setPageSize(length);
			searchCondition.setCurrPage(currPage);
			if(StringUtils.isNotEmpty(fundId)){
				searchCondition.addConditionEqualTo("FUND_ID",fundId );
			}
			searchCondition.addConditionNotEqualTo("status","1");
			PageInfo<FundConsModel> rows = fundConsService.selectListPage(searchCondition);
			List<FundConsVO> list = new ArrayList<FundConsVO>();
			for(FundConsModel model : rows.getList()){
				FundConsVO vo = new FundConsVO();
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

	@ApiOperation(value="相关资料新增", notes="基金分析报告新增")
	@ApiImplicitParam(name = "FundAnalysisReportVO", value = "基金管理人实体FundAnalysisReportVO", required = true, dataType = "FundMcInfoVO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/save", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String save(@RequestBody FundConsDTO dto){
		try {
			long userId = this.getLoginUser().getUserId();
			FundConsModel model = new FundConsModel();
			BeanUtils.copyProperties(dto, model);
			model.setUpdateBy(String.valueOf(userId));
			model.setUpdateDt(new Date());
			if(StringUtils.isNotEmpty(dto.getConsId())){
				fundConsService.update(model);
			}else {
				model.setCreateBy(String.valueOf(userId));
				model.setCreateDt(new Date());
				model.setStatus("0");
				model.setConsId(serialnoService.getSequence("EI.BD_T_FUND_CONS"));
				fundConsService.insert(model);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiOperation(value="相关资料详细信息", notes="根据url的id来获取XXX详细信息")
	@ApiImplicitParam(name = "id", value = "XXXID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/getdetail/{consId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getFundReport(@PathVariable(value = "consId") String consId){
		try {
			FundConsModel model = fundConsService.selectById(consId);
			FundConsVO vo = new FundConsVO();
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
	@GetMapping(value = "/delete/{consId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@PathVariable("consId") String consId){
		try {
			String[] ids = consId.split(",");
			for(int i = 0; i < ids.length; i++){
				if(StringUtils.isNotEmpty(ids[i])){
					FundConsModel model=new FundConsModel();
					model.setStatus("1");
					model.setConsId(ids[i]);
					fundConsService.update(model);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);

	}

}

