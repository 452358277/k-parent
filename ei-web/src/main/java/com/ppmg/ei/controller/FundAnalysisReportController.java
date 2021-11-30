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
import com.ppmg.ei.model.FundAnalysisReportModel;
import com.ppmg.ei.service.AppUserService;
import com.ppmg.ei.service.FundAnalysisReportService;
import com.ppmg.ei.vo.FundAnalysisReportVO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
@Scope("prototype")
@Api(tags={"基金定期报告接口"})
public class FundAnalysisReportController extends BaseController{

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference(check=false)
	private FundAnalysisReportService fundAnalysisReportService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@Reference(check=false)
	private AppUserService appUserService;

	@Reference(check = false)
	private SerialnoService serialnoService;


	@ApiOperation(value="基金定期报告列表", notes="基金定期报告列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
		@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
			@ApiImplicitParam(name = "fundId", value = "基金ID", required = true, dataType = "String")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/fundAnalysisReportList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String fundAnalysisReportList(@RequestParam("length") int length, @RequestParam("currPage") int currPage, @RequestParam("fundId") String fundId){
		try {

			searchCondition.setPageSize(length);
			searchCondition.setCurrPage(currPage);
			if(StringUtils.isNotEmpty(fundId)){
				searchCondition.addConditionEqualTo("FUND_ID",fundId );
			}
            searchCondition.addConditionNotEqualTo("STATUS","1");
			PageInfo<FundAnalysisReportModel> rows = fundAnalysisReportService.selectListPage(searchCondition);
			List<FundAnalysisReportVO> list = new ArrayList<FundAnalysisReportVO>();
			for(FundAnalysisReportModel model : rows.getList()){
				FundAnalysisReportVO vo = new FundAnalysisReportVO();
				BeanUtils.copyProperties(vo, model);
				if(StringUtils.isNotEmpty(model.getRptType())){
					vo.setRptTypeName(codeUtils.getCodeNameByValue("221", model.getRptType()));
				}
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

	@ApiOperation(value="基金分析报告新增", notes="基金分析报告新增")
	@ApiImplicitParam(name = "FundAnalysisReportVO", value = "基金管理人实体FundAnalysisReportVO", required = true, dataType = "FundMcInfoVO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/fundAnalysisReport/add", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(@RequestBody FundAnalysisReportVO dto){
		try {
			FundAnalysisReportModel model=new FundAnalysisReportModel();
			BeanUtils.copyProperties(model, dto);
			long userId = this.getLoginUser().getUserId();
			//long userId =12313;
			model.setCreateBy(String.valueOf(userId));
			model.setCreateDt(new Date());
			model.setUpdateBy(String.valueOf(userId));
			model.setUpdateDt(new Date());
			model.setStatus("0");
			model.setRptId(serialnoService.getSequence("EI.BD_T_FUND_ANALYSIS_REPORT"));
			fundAnalysisReportService.insert(model);

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiIgnore
	@ApiOperation(value="基金分析报告修改", notes="根据url的id来指定更新基金分析报告新增")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "rptId", value = "rptId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "FundMcInfoVO", value = "FundAnalysisReportVO", required = true, dataType = "FundAnalysisReportVO")
	})
	@PostMapping(value = "/fundAnalysisReport/update/{rptId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update(@PathVariable("rptId") String rptId, @RequestBody FundAnalysisReportVO dto){
		try {
			FundAnalysisReportModel model = new FundAnalysisReportModel();
			BeanUtils.copyProperties(model, dto);
			model.setRptId(dto.getRptId());
			model.setUpdateBy(this.getLoginUserId());
			model.setUpdateDt(new Date());
			fundAnalysisReportService.update(model);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}
	@ApiIgnore
	@ApiOperation(value="删除", notes="删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "rptId", value = "rptId", required = true, dataType = "String",paramType = "path"),
	})
	@GetMapping(value = "/fundAnalysisReport/delete/{rptId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@PathVariable("rptId") String rptId){
		try {
			String[] ids = rptId.split(",");
			for(int i = 0; i < ids.length; i++){
				FundAnalysisReportModel model = new FundAnalysisReportModel();
				model.setRptId(ids[i]);
				model.setStatus("1");
				fundAnalysisReportService.update(model);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);

	}

	@ApiOperation(value="基金分析报告详细信息", notes="根据url的id来获取分析报告详细信息")
	@ApiImplicitParam(name = "id", value = "出资人ID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/fundAnalysisReport/{id}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String promoterInfo(@PathVariable(value = "id") String id){
		try {
			FundAnalysisReportModel model = fundAnalysisReportService.selectById(id);
			FundAnalysisReportVO vo = new FundAnalysisReportVO();
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
	}

	//#######################################一期老系统############################################################
	@ApiOperation(value="基金定期报告列表", notes="基金定期报告列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
			@ApiImplicitParam(name = "fundId", value = "基金ID", required = true, dataType = "String")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/report/getReportList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getReportList(@RequestParam("length") int length, @RequestParam("currPage") int currPage, @RequestParam("fundId") String fundId){
		try {

			searchCondition.setPageSize(length);
			searchCondition.setCurrPage(currPage);
			if(StringUtils.isNotEmpty(fundId)){
				searchCondition.addConditionEqualTo("FUND_ID",fundId );
			}
			searchCondition.addConditionEqualTo("CREATE_BY",this.getLoginUserId());
			searchCondition.addConditionNotEqualTo("STATUS","1");
			PageInfo<FundAnalysisReportModel> rows = fundAnalysisReportService.selectListPage(searchCondition);
			List<FundAnalysisReportVO> list = new ArrayList<FundAnalysisReportVO>();
			for(FundAnalysisReportModel model : rows.getList()){
				FundAnalysisReportVO vo = new FundAnalysisReportVO();
				BeanUtils.copyProperties(vo, model);
				if(StringUtils.isNotEmpty(model.getRptType())){
					vo.setRptTypeName(codeUtils.getCodeNameByValue("90071", model.getRptType()));
				}
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


	@ApiOperation(value="基金分析报告新增", notes="基金分析报告新增")
	@ApiImplicitParam(name = "FundAnalysisReportVO", value = "基金管理人实体FundAnalysisReportVO", required = true, dataType = "FundMcInfoVO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/fundAnalysisReport/save", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String save(@RequestBody FundAnalysisReportVO dto){
		try {
			   long userId = this.getLoginUser().getUserId();
				FundAnalysisReportModel model = new FundAnalysisReportModel();
				BeanUtils.copyProperties(model, dto);
			    model.setUpdateBy(String.valueOf(userId));
			    model.setUpdateDt(new Date());
				if(StringUtils.isNotEmpty(dto.getRptId())){
					fundAnalysisReportService.update(model);
				}else {
				   model.setCreateBy(String.valueOf(userId));
				   model.setCreateDt(new Date());
				   model.setStatus("0");
				   model.setRptId(serialnoService.getSequence("EI.BD_T_FUND_ANALYSIS_REPORT"));
				   fundAnalysisReportService.insert(model);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}


	@ApiOperation(value="基金分析报告详细信息", notes="根据url的id来获取分析报告详细信息")
	@ApiImplicitParam(name = "id", value = "出资人ID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/getReportDetail/{rptId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getReportDetail(@PathVariable(value = "rptId") String rptId){
		try {
			FundAnalysisReportModel model = fundAnalysisReportService.selectById(rptId);
			FundAnalysisReportVO vo = new FundAnalysisReportVO();
			BeanUtils.copyProperties(vo, model);
			if(StringUtils.isNotEmpty(model.getRptType())){
				vo.setRptTypeName(codeUtils.getCodeNameByValue("90071", model.getRptType()));
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


}

