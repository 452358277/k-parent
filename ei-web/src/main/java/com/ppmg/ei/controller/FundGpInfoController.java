package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.foundation.service.SerialnoService;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.model.FundGpInfoModel;
import com.ppmg.ei.service.FundGpInfoService;
import com.ppmg.ei.vo.FundGpInfoVO;
import io.swagger.annotations.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
@Scope("prototype")
@Api(tags={"出资人接口"})
public class FundGpInfoController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private FundGpInfoService fundGpInfoService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@ApiOperation(value="获取出资人列表", notes="基金管理人列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/promoterList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String promoterList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam(value="fundId", required=false) String fundId){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			if(StringUtils.isNotEmpty(fundId)){
				searchCondition.addConditionEqualTo("FUND_ID", fundId);
			}
			PageInfo<FundGpInfoModel> rows = fundGpInfoService.selectListPage(searchCondition);
			List<FundGpInfoVO> list = new ArrayList<FundGpInfoVO>();
			for(FundGpInfoModel model : rows.getList()){
				FundGpInfoVO vo = new FundGpInfoVO();
				BeanUtils.copyProperties(vo, model);
				list.add(vo);
			}
			dataTablesResponse.setData(list,rows);
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

	@ApiOperation(value="获取出资人详细信息", notes="根据url的id来获取XXX详细信息")
	@ApiImplicitParam(name = "id", value = "出资人ID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
   })
	@GetMapping(value = "/promoterInfo/{id}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String promoterInfo(@PathVariable(value = "id") String id){
		try {
			FundGpInfoModel model = fundGpInfoService.selectById(id);
			FundGpInfoVO vo = new FundGpInfoVO();
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

	@ApiOperation(value="出资人新增", notes="出资人新增")
	@ApiImplicitParam(name = "FundGpInfoVO", value = "基金管理人实体FundGpInfoVO", required = true, dataType = "FundGpInfoVO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/promoter/add", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(@RequestBody FundGpInfoVO dto){

		try {
			FundGpInfoModel model = new FundGpInfoModel();
			long userId = this.getLoginUser().getUserId();
			//long userId=13240;
			model.setCreateBy(String.valueOf(userId));
			model.setCreateDt(new Date());
			model.setUpdateBy(String.valueOf(userId));
			model.setUpdateDt(new Date());
			BeanUtils.copyProperties(model, dto);
			String gpId = serialnoService.getSequence("EI.BD_T_FUND_GP_INFO");
			model.setGpId(gpId);
			fundGpInfoService.insert(model);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);

	}

	@ApiIgnore
	@ApiOperation(value="更新工作进展", notes="根据url的id来指定更新工作进展")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "gpId", value = "gpId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "FundGpInfoVO", value = "FundGpInfoVO", required = true, dataType = "FundGpInfoVO")
	})
	@PostMapping(value = "/promoter/update/{gpId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update(@PathVariable("gpId") String gpId, @RequestBody FundGpInfoVO dto){

		try {
			FundGpInfoModel model = new FundGpInfoModel();
			BeanUtils.copyProperties(model, dto);
			model.setGpId(gpId);
			model.setUpdateBy(this.getLoginUserId());
			model.setUpdateDt(new Date());
			fundGpInfoService.update(model);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiIgnore
	@ApiOperation(value="删除", notes="删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "gpId", value = "gpId", required = true, dataType = "String",paramType = "path"),
	})
	@GetMapping(value = "/promoter/delete/{gpId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@PathVariable("gpId") String gpId){

		try {
			fundGpInfoService.deleteById(gpId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

}

