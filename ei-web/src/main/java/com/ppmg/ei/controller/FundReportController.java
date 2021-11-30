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
import com.ppmg.ei.dto.FundReportDTO;
import com.ppmg.ei.model.FundReportModel;
import com.ppmg.ei.service.FundReportService;
import com.ppmg.ei.vo.FundReportVO;
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
@Api(tags={"管理报告接口"})
@RequestMapping("/fundReport")
public class FundReportController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private FundReportService fundReportService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;



	//列表
	@ApiOperation(value="管理报告列表", notes="管理报告列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/fundReportList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String fundReportList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("fundId") String fundId){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("FUND_ID",fundId);
			//searchCondition.addConditionEqualTo("CREATE_BY",this.getLoginUserId());
			PageInfo<FundReportModel> rows = fundReportService.selectListPage(searchCondition);
			List<FundReportVO> list = new ArrayList<FundReportVO>();
			for(FundReportModel model : rows.getList()){
				FundReportVO vo=new FundReportVO();
				BeanUtils.copyProperties(model,vo);
				//季度
				if(StringUtils.isNotEmpty(model.getQuarter())){
					String quarterName = codeUtils.getCodeNameByValue("90131",model.getQuarter());
					vo.setQuarterName(quarterName);
				}
				if(StringUtils.isNotEmpty(model.getType())){
					String type = codeUtils.getCodeNameByValue("345",model.getType());
					vo.setTypeName(type);
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






	@ApiIgnore
	@ApiOperation(value = "管理报告新增", notes = "基金阶段")
	@ApiImplicitParam(name = "ProjShareInfoDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/report/save", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String save(@RequestBody FundReportDTO dto) {
		try {
			String userId = this.getLoginUserId();
			FundReportModel model=new FundReportModel();
			BeanUtils.copyProperties(dto, model);
			model.setUpdateBy(userId);
			model.setUpdateDt(new Date());
			if(StringUtils.isNotEmpty(dto.getReportId())){
				//判断年否是否修改
			/*	FundReportModel fundReport=fundReportService.selectById(dto.getReportId());
				if(fundReport!=null){
					if(StringUtils.isNotEmpty(fundReport.getYear())&&!fundReport.getYear().equals(dto.getYear())){
						FundReportModel report=new FundReportModel();
						report.setQuarter(dto.getQuarter());
						report.setYear(dto.getYear());
						report.setFundId(dto.getFundId());
						List<FundReportModel> list=fundReportService.selectList(report);
						if(list!=null && !list.isEmpty()){
							baseResponse.error("-1","该年的季度已存在");
							return	JSONObject.toJSONString(baseResponse);
						}
					}else if(fundReport.getYear().equals(dto.getYear())){
						if(!fundReport.getQuarter().equals(dto.getQuarter())){
							FundReportModel report=new FundReportModel();
							report.setQuarter(dto.getQuarter());
							report.setYear(dto.getYear());
							report.setFundId(dto.getFundId());
							List<FundReportModel> list=fundReportService.selectList(report);
							if(list!=null && !list.isEmpty()){
								baseResponse.error("-1","该年的季度已存在");
								return	JSONObject.toJSONString(baseResponse);
							}
						}


					}
				}*/

				fundReportService.update(model);
			}else{
				//查询是否存在了季度
			/*	FundReportModel report=new FundReportModel();
				report.setQuarter(dto.getQuarter());
				report.setYear(dto.getYear());
				report.setFundId(dto.getFundId());
				List<FundReportModel> list=fundReportService.selectList(report);
				if(list!=null && !list.isEmpty()){
					baseResponse.error("-1","该年的季度已存在");
					return	JSONObject.toJSONString(baseResponse);
				}*/
				String reportId=serialnoService.getSequence("EI.GF_T_FUND_REPORT");
				model.setCreateBy(userId);
				model.setCreateDt(new Date());
				model.setReportId(reportId);
				fundReportService.insert(model);
			}


		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	@ApiOperation(value="获取管理报告详细信息", notes="根据url的id来获取XXX详细信息")
	@ApiImplicitParam(name = "id", value = "XXXID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
   })
	@GetMapping(value = "/getReport/{reportId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getFundReport(@PathVariable(value = "reportId") String reportId){
		try {
			FundReportModel model = fundReportService.selectById(reportId);
			FundReportVO vo = new FundReportVO();
			BeanUtils.copyProperties(model, vo);
			if(StringUtils.isNotEmpty(model.getQuarter())){
				String quarterName = codeUtils.getCodeNameByValue("90131",model.getQuarter());
				vo.setQuarterName(quarterName);
			}
			if(StringUtils.isNotEmpty(model.getType())){
				String type = codeUtils.getCodeNameByValue("345",model.getType());
				vo.setTypeName(type);
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

	@ApiOperation(value="管理报告删除", notes="根据url的id来获取XXX详细信息")
	@ApiImplicitParam(name = "id", value = "XXXID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/delete/{reportId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@PathVariable(value = "reportId") String reportId){
		try {
			fundReportService.deleteById(reportId);
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

