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
import com.ppmg.ei.dto.FundRelateInfoDTO;
import com.ppmg.ei.model.FundRelateInfoModel;
import com.ppmg.ei.service.FundRelateInfoService;
import com.ppmg.ei.vo.FundRelateInfoVO;
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
@Api(tags={"相关资料接口"})
public class FundRelateInfoController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private FundRelateInfoService fundRelateInfoService;


	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@Reference(check = false)
	private SerialnoService serialnoService;


	@ApiOperation(value="相关资料列表", notes="基金定期报告列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
			@ApiImplicitParam(name = "fundId", value = "基金ID", required = true, dataType = "String")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/fundRelate/getInfoList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getInfoList(@RequestParam("length") int length, @RequestParam("currPage") int currPage, @RequestParam("fundId") String fundId){
		try {

			searchCondition.setPageSize(length);
			searchCondition.setCurrPage(currPage);
			if(StringUtils.isNotEmpty(fundId)){
				searchCondition.addConditionEqualTo("FUND_ID",fundId );
			}
			searchCondition.addConditionNotEqualTo("status","1");
			PageInfo<FundRelateInfoModel> rows = fundRelateInfoService.selectListPage(searchCondition);
			List<FundRelateInfoVO> list = new ArrayList<FundRelateInfoVO>();
			for(FundRelateInfoModel model : rows.getList()){
				FundRelateInfoVO vo = new FundRelateInfoVO();
				BeanUtils.copyProperties(model,vo);
				if(StringUtils.isNotEmpty(model.getInfoType())){
					vo.setInfoTypeName(codeUtils.getCodeNameByValue("222", model.getInfoType()));
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

	@ApiOperation(value="相关资料新增", notes="基金分析报告新增")
	@ApiImplicitParam(name = "FundAnalysisReportVO", value = "基金管理人实体FundAnalysisReportVO", required = true, dataType = "FundMcInfoVO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/fundRelate/save", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String save(@RequestBody FundRelateInfoDTO dto){
		try {
			long userId = this.getLoginUser().getUserId();
			FundRelateInfoModel model = new FundRelateInfoModel();
			BeanUtils.copyProperties(dto, model);
			model.setUpdateBy(String.valueOf(userId));
			model.setUpdateDt(new Date());
			if(StringUtils.isNotEmpty(dto.getRelateId())){
				fundRelateInfoService.update(model);
			}else {
				model.setCreateBy(String.valueOf(userId));
				model.setCreateDt(new Date());
				model.setStatus("0");
				model.setRelateId(serialnoService.getSequence("EI.BD_T_FUND_RELATE_INFO"));
				fundRelateInfoService.insert(model);
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
	@GetMapping(value = "/getFundRelata/{relateId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getFundReport(@PathVariable(value = "relateId") String relateId){
		try {
			FundRelateInfoModel model = fundRelateInfoService.selectById(relateId);
			FundRelateInfoVO vo = new FundRelateInfoVO();
			BeanUtils.copyProperties(model, vo);
			if(StringUtils.isNotEmpty(model.getInfoType())){
				vo.setInfoTypeName(codeUtils.getCodeNameByValue("222", model.getInfoType()));
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


	@ApiIgnore
	@ApiOperation(value="删除", notes="删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "relateId", value = "rptId", required = true, dataType = "String",paramType = "path"),
	})
	@GetMapping(value = "/fundRelata/delete/{relateId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@PathVariable("relateId") String relateId){
		try {
			String[] ids = relateId.split(",");
			for(int i = 0; i < ids.length; i++){
				if(StringUtils.isNotEmpty(ids[i])){
					FundRelateInfoModel model=new FundRelateInfoModel();
					model.setStatus("1");
					model.setRelateId(ids[i]);
					fundRelateInfoService.update(model);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);

	}

}

