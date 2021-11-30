package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.model.FundAllocationModel;
import com.ppmg.ei.service.FundAllocationService;
import com.ppmg.ei.vo.FundAllocationVO;
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
@Api(tags={"基金明细接口"})
@RequestMapping("/fundAllocation")
public class FundAllocationController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private FundAllocationService fundAllocationService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@ApiOperation(value="基金明细接口", notes="借款信息")
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
	public String getList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,@RequestParam("fundId") String fundId){
		try {

			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("fund_id", fundId);

			PageInfo<FundAllocationModel> rows = fundAllocationService.selectListPage(searchCondition);
			List<FundAllocationVO> list = new ArrayList<FundAllocationVO>();
			for(FundAllocationModel model : rows.getList()){
				FundAllocationVO vo = new FundAllocationVO();
				org.springframework.beans.BeanUtils.copyProperties(vo, model);
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
	public String save(@RequestBody FundAllocationModel model) {
		try {
			String userId = this.getLoginUserId();
			model.setUpdateBy(userId);
			model.setUpdateDt(new Date());
			if(StringUtils.isNotEmpty(model.getAllId())){
				fundAllocationService.update(model);
			}else{
				model.setCreateBy(userId);
				model.setCreateDt(new Date());
				model.setAllId(UUID.randomUUID().toString());
				model.setStatus("0");
				fundAllocationService.insert(model);
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
	@GetMapping(value = "/detail/{allId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detail(@PathVariable(value = "allId") String allId){
		try {
			FundAllocationModel model = fundAllocationService.selectById(allId);
			FundAllocationVO vo = new FundAllocationVO();
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

	@ApiOperation(value="删除", notes="根据url的id来获取XXX详细信息")
	@ApiImplicitParam(name = "id", value = "XXXID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/delete/{allId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@PathVariable(value = "allId") String allId){
		try {
			fundAllocationService.deleteById(allId);
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

