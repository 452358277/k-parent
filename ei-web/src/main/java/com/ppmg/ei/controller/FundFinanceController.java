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
import com.ppmg.ei.dto.FundFinanceDTO;
import com.ppmg.ei.model.FundFinanceModel;
import com.ppmg.ei.service.FundFinanceService;
import com.ppmg.ei.vo.FundFinanceVO;
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
@Api(tags={"季度财务信息接口"})
@RequestMapping("/fundFinance")
public class FundFinanceController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private FundFinanceService fundFinanceService;

	@Reference(check = false)
	private SerialnoService serialnoService;


	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@ApiIgnore
	@ApiOperation(value = "基金阶段", notes = "基金阶段")
	@ApiImplicitParam(name = "ProjContractFileDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/fundFinance/save", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String save(@RequestBody FundFinanceDTO dto) {
		try {
			String userId = this.getLoginUserId();
			FundFinanceModel model = new FundFinanceModel();
			BeanUtils.copyProperties(dto,model);
			model.setUpdateBy(userId);
			model.setUpdateDt(new Date());
			if(StringUtils.isNotEmpty(dto.getFinanceId())){
				FundFinanceModel m=fundFinanceService.selectById(dto.getFinanceId());
				if(!m.getYear().equals(dto.getYear())|| !m.getQuarter().equals(dto.getQuarter())){
					FundFinanceModel modelS = new FundFinanceModel();
					modelS.setFundId(dto.getFundId());
					modelS.setYear(dto.getYear());
					modelS.setQuarter(dto.getQuarter());
					List<FundFinanceModel> list=fundFinanceService.selectList(modelS);
					if(list!=null&&!list.isEmpty()){
						String quarterName ="";
						if(StringUtils.isNotEmpty(model.getQuarter())){
							 quarterName = codeUtils.getCodeNameByValue("90131",model.getQuarter());
						}
						baseResponse.error("-1",dto.getYear()+"的"+quarterName+"已存在");
						return	JSONObject.toJSONString(baseResponse);
					}
				}
				if("1".equals(dto.getUpdateIsNull())){
					fundFinanceService.updateIsNull(model);
				}else{
					fundFinanceService.update(model);
				}

			}else{
				//先判断该年的季度数据是否存在
				FundFinanceModel modelS = new FundFinanceModel();
				modelS.setFundId(dto.getFundId());
				modelS.setYear(dto.getYear());
				modelS.setQuarter(dto.getQuarter());
				List<FundFinanceModel> list=fundFinanceService.selectList(modelS);
				if(list!=null&&!list.isEmpty()){
					String quarterName ="";
					if(StringUtils.isNotEmpty(model.getQuarter())){
						quarterName = codeUtils.getCodeNameByValue("90131",model.getQuarter());
					}
					baseResponse.error("-1",dto.getYear()+"的"+quarterName+"已存在");
					return	JSONObject.toJSONString(baseResponse);
				}

				String financeId = serialnoService.getSequence("EI.GF_T_FUND_FINANCE");
				model.setFinanceId(financeId);
				model.setCreateBy(userId);
				model.setCreateDt(new Date());
				//1未确认,2：已确认  码值：422
				model.setStatus("1");
				fundFinanceService.insert(model);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	//列表
	@ApiOperation(value="季度财务列表", notes="基金管理人列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/fundFinanceList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String fundFinanceList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("fundId") String fundId){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("FUND_ID",fundId);
			PageInfo<FundFinanceModel> rows = fundFinanceService.selectListPage(searchCondition);
			List<FundFinanceVO> list = new ArrayList<FundFinanceVO>();
			for(FundFinanceModel model : rows.getList()){
				FundFinanceVO vo=new FundFinanceVO();
				BeanUtils.copyProperties(model,vo);
				//季度
				if(StringUtils.isNotEmpty(model.getQuarter())){
					String quarterName = codeUtils.getCodeNameByValue("90131",model.getQuarter());
				    vo.setQuarterName(quarterName);
				}
				//状态
				if(StringUtils.isNotEmpty(model.getStatus())){
					String statusName = codeUtils.getCodeNameByValue("422",model.getStatus());
					vo.setStatusName(statusName);
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

	@ApiOperation(value="详情", notes="详情")
	@ApiImplicitParam(name = "appId", value = "appId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/detainfo/{financeId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detainfo(@PathVariable(value = "financeId") String financeId){
		try {
			FundFinanceModel fundFinanceModel=fundFinanceService.selectById(financeId);

			FundFinanceVO vo=new FundFinanceVO();
			BeanUtils.copyProperties( fundFinanceModel,vo);
			//季度
			if(StringUtils.isNotEmpty(fundFinanceModel.getQuarter())){
				String quarterName = codeUtils.getCodeNameByValue("90131",fundFinanceModel.getQuarter());
				vo.setQuarterName(quarterName);
			}
			//状态
			if(StringUtils.isNotEmpty(fundFinanceModel.getStatus())){
				String statusName = codeUtils.getCodeNameByValue("422",fundFinanceModel.getStatus());
				vo.setStatusName(statusName);
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
	@ApiOperation(value = "删除", notes = "基金阶段")
	@ApiImplicitParam(name = "financeId", value = "financeId", required = true, dataType = "financeId")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/del", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String del(@RequestParam("financeId") String financeId) {
		try {
			fundFinanceService.deleteById(financeId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	//列表
	@ApiOperation(value="年度财务列表", notes="基金管理人列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/yearFundFinanceList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String yearFundFinanceList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("fundId") String fundId){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("FUND_ID",fundId);
			PageInfo<FundFinanceModel> rows = fundFinanceService.selectListYearPage(searchCondition);
			List<FundFinanceVO> list = new ArrayList<FundFinanceVO>();
			for(FundFinanceModel model : rows.getList()){
				FundFinanceVO vo=new FundFinanceVO();
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

	@ApiOperation(value="详情", notes="详情")
	@ApiImplicitParam(name = "appId", value = "appId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/detainfo/init", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String init(@RequestParam("fundId") String fundId,@RequestParam("quarter")String quarter,@RequestParam("year")String year){
		try {
			FundFinanceModel fundFinance=new FundFinanceModel();
			fundFinance.setFundId(fundId);
			fundFinance.setQuarter(quarter);
			fundFinance.setYear(year);
			List<FundFinanceModel> list=fundFinanceService.selectList(fundFinance);
			FundFinanceModel fundFinanceModel=new FundFinanceModel();
            if(list!=null&&!list.isEmpty()){
				fundFinanceModel=list.get(0);
			}
			FundFinanceVO vo=new FundFinanceVO();
			BeanUtils.copyProperties( fundFinanceModel,vo);
			//季度
			if(StringUtils.isNotEmpty(fundFinanceModel.getQuarter())){
				String quarterName = codeUtils.getCodeNameByValue("90131",fundFinanceModel.getQuarter());
				vo.setQuarterName(quarterName);
			}
			//状态
			if(StringUtils.isNotEmpty(fundFinanceModel.getStatus())){
				String statusName = codeUtils.getCodeNameByValue("422",fundFinanceModel.getStatus());
				vo.setStatusName(statusName);
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

