package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.model.EnteFinanceReportModel;
import com.ppmg.ei.service.EnteFinanceReportService;
import com.ppmg.ei.vo.EnteFinanceReportVO;
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
import java.util.UUID;
@Controller
@Scope("prototype")
@Api(tags={"企业融资上报接口"})
@RequestMapping("/enteFinanceReport")
public class EnteFinanceReportController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private EnteFinanceReportService enteFinanceReportService;


	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@ApiOperation(value="融资上报接口", notes="借款信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
			@ApiImplicitParam(name = "projectObject", value = "被投企业", required = true, dataType = "String"),
			@ApiImplicitParam(name = "inveId", value = "出资主体", required = true, dataType = "String")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/getList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,@RequestParam("enterpriseId") String enterpriseId){
		try {

			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("ENTERPRISE_ID", enterpriseId);

			PageInfo<EnteFinanceReportModel> rows = enteFinanceReportService.selectListPage(searchCondition);
			List<EnteFinanceReportVO> list = new ArrayList<EnteFinanceReportVO>();
			for(EnteFinanceReportModel model : rows.getList()){
				EnteFinanceReportVO vo = new EnteFinanceReportVO();
				BeanUtils.copyProperties( model,vo);
				if(StringUtils.isNotEmpty(model.getFinaRounds())){
					String finaRoundsName = codeUtils.getCodeNameByValue("213",model.getFinaRounds());
					vo.setFinaRoundsName(finaRoundsName);
				}

				if(StringUtils.isNotEmpty(model.getFinaTimes())){
					String finaTimesName = codeUtils.getCodeNameByValue("214",model.getFinaTimes());
					vo.setFinaTimesName(finaTimesName);
				}

				if(StringUtils.isNotEmpty(model.getStopType())){
					String stopTypeName = codeUtils.getCodeNameByValue("1029",model.getStopType());
					vo.setStopTypeName(stopTypeName);
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

	@ApiIgnore
	@ApiOperation(value = "借款信息", notes = "基金阶段")
	@ApiImplicitParam(name = "ProjShareInfoDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/save", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String save(@RequestBody EnteFinanceReportModel dto) {
		try {
			String userId = this.getLoginUserId();
			EnteFinanceReportModel model=new EnteFinanceReportModel();
			org.springframework.beans.BeanUtils.copyProperties(dto, model);
			model.setUpdateBy(userId);
			model.setUpdateDt(new Date());
			if(StringUtils.isNotEmpty(dto.getStopId())){
				enteFinanceReportService.update(model);
			}else{
				model.setCreateBy(userId);
				model.setCreateDt(new Date());
				model.setStopId(UUID.randomUUID().toString());
				enteFinanceReportService.insert(model);
			}


		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	@ApiOperation(value="详细信息", notes="根据url的id来获取XXX详细信息")
	@ApiImplicitParam(name = "id", value = "XXXID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/detail/{stopId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detail(@PathVariable(value = "stopId") String stopId){
		try {
			EnteFinanceReportModel model = enteFinanceReportService.selectById(stopId);
			EnteFinanceReportVO vo = new EnteFinanceReportVO();
			BeanUtils.copyProperties(model, vo);
			if(StringUtils.isNotEmpty(model.getFinaRounds())){
				String finaRoundsName = codeUtils.getCodeNameByValue("213",model.getFinaRounds());
				vo.setFinaRoundsName(finaRoundsName);
			}

			if(StringUtils.isNotEmpty(model.getFinaTimes())){
				String finaTimesName = codeUtils.getCodeNameByValue("214",model.getFinaTimes());
				vo.setFinaTimesName(finaTimesName);
			}
			if(StringUtils.isNotEmpty(model.getStopType())){
				String stopTypeName = codeUtils.getCodeNameByValue("1029",model.getStopType());
				vo.setStopTypeName(stopTypeName);
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

	@ApiOperation(value="删除", notes="根据url的id来获取XXX详细信息")
	@ApiImplicitParam(name = "id", value = "XXXID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/delete/{stopId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@PathVariable(value = "stopId") String stopId){
		try {
			enteFinanceReportService.deleteById(stopId);
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

