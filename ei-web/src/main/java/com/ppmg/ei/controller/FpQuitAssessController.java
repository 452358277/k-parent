package com.ppmg.ei.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.action.BaseAction;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.FpQuitAssessModel;
import com.ppmg.ei.service.AppUserService;
import com.ppmg.ei.service.FpQuitAssessService;
import com.ppmg.ei.vo.FpQuitAssessListVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Controller
@Scope("prototype")
@Api(tags={"基金退出后评估相关接口"})
public class FpQuitAssessController extends BaseAction{

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private FpQuitAssessService fpQuitAssessService;

	@Reference
	private AppUserService appUserService;

	@ApiOperation(value="获取基金退出后评估列表", notes="获取基金退出后评估列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
		@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/fpQuitAssessList", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public String fpQuitAssessList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("projId") String projId){
		
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("PROJ_ID", projId);
			searchCondition.addConditionNotEqualTo("STATUS", "9");
			PageInfo<FpQuitAssessModel> rows = fpQuitAssessService.selectListPage(searchCondition);
			List<FpQuitAssessListVO> list = new ArrayList<FpQuitAssessListVO>();
			for(FpQuitAssessModel model : rows.getList()){
				FpQuitAssessListVO vo = new FpQuitAssessListVO();
				BeanUtils.copyProperties(vo, model);
				vo.setAssessBy(appUserService.selectById(model.getAssessBy()).getName());
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

}

