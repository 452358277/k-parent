package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.action.BaseAction;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.TzTProjOwnershipInfoModel;
import com.ppmg.ei.service.TzTProjOwnershipInfoService;
import com.ppmg.ei.vo.TzTProjOwnershipInfoVO;
import io.swagger.annotations.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
@Controller
@Scope("prototype")
@Api(tags={"一期股权结构-接口"})
public class TzTProjOwnershipInfoController extends BaseAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private TzTProjOwnershipInfoService tzTProjOwnershipInfoService;

	@ApiOperation(value = "股权结构列表", notes = "股权结构")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/projOwnershipList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String projOwnershipList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,  String fundId,String enterpriseId,String projId) {
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			if(StringUtils.isNotEmpty(fundId)){
				searchCondition.addConditionEqualTo("FUND_ID", fundId);
			}
			if(StringUtils.isNotEmpty(projId)){
				searchCondition.addConditionEqualTo("PROJ_ID", projId);
			}
			if(StringUtils.isNotEmpty(enterpriseId)){
				searchCondition.addConditionEqualTo("ENTE_ID", enterpriseId);
			}
			PageInfo<TzTProjOwnershipInfoModel> rows = tzTProjOwnershipInfoService.selectListPage(searchCondition);
			List<TzTProjOwnershipInfoVO> list = new ArrayList<TzTProjOwnershipInfoVO>();
			for (TzTProjOwnershipInfoModel model : rows.getList()) {
				TzTProjOwnershipInfoVO vo = new TzTProjOwnershipInfoVO();
				BeanUtils.copyProperties(vo, model);
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
	@ApiOperation(value = "删除", notes = "基金阶段")
	@ApiImplicitParam(name = "financeId", value = "financeId", required = true, dataType = "financeId")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/projOwnership/del", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String del(@RequestParam("osId") String osId) {
		try {
			tzTProjOwnershipInfoService.deleteById(osId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);


	}

}