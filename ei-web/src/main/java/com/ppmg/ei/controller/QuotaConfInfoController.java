package com.ppmg.ei.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppmg.ei.model.QuotaConfDetailModel;
import com.ppmg.ei.service.AppUserService;
import com.ppmg.ei.service.QuotaConfDetailService;
import com.ppmg.ei.vo.QuotaConfDetailVO;
import com.ppmg.ei.vo.QuotaConfInfoVO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.action.BaseAction;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.QuotaConfInfoModel;
import com.ppmg.ei.service.QuotaConfInfoService;
@Controller
@Scope("prototype")
@Api(tags={"经营计划接口"})
public class QuotaConfInfoController extends BaseAction{

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private QuotaConfInfoService quotaConfInfoService;

	@Reference
	private QuotaConfDetailService quotaConfDetailService;

	@Reference
	private AppUserService appUserService;

	@ApiOperation(value="经营计划列表", notes="经营计划列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
		@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
		@ApiImplicitParam(name = "groupId", value = "平台代码", required = true, dataType = "String")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/QuotaConfList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String QuotaConfList(@RequestParam("length") int length, @RequestParam("currPage") int currPage, @RequestParam("groupId") String groupId){
		try {

			searchCondition.setPageSize(length);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionNotEqualTo("t.status","9");
			searchCondition.addConditionEqualTo("c.code_name",groupId);
			PageInfo<QuotaConfInfoModel> rows = quotaConfInfoService.selectListPage(searchCondition);
			List<QuotaConfInfoVO> list = new ArrayList<QuotaConfInfoVO>();
			for(QuotaConfInfoModel model : rows.getList()){
				QuotaConfInfoVO vo = new QuotaConfInfoVO();
				BeanUtils.copyProperties(vo, model);
				if(model.getPlanBy() != null && model.getPlanBy() != ""){
					vo.setPlanBy(appUserService.selectById(model.getPlanBy()).getName());
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

	@ApiOperation(value="获取经营计划执行情况详细信息", notes="获取经营计划执行情况详细信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "confId", value = "配置表ID", required = true, dataType = "String"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/QuotaConfDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String QuotaConfDetail(@RequestParam("confId") String confId){
		try {
			List<QuotaConfDetailModel> rows = quotaConfDetailService.getQuotaConfDetailByConfId(confId);
			List<QuotaConfDetailVO> list = new ArrayList<QuotaConfDetailVO>();
			for(QuotaConfDetailModel model : rows){
				QuotaConfDetailVO vo = new QuotaConfDetailVO();
				BeanUtils.copyProperties(vo, model);
				if(model.getQuotaInfoModel() != null){
					vo.setQuotaName(model.getQuotaInfoModel().getQuotaName());
					vo.setQuotaUnit(model.getQuotaInfoModel().getQuotaUnit());
				}else{
					vo.setQuotaName("");
					vo.setQuotaUnit("");
				}
				list.add(vo);
			}
			dataTablesResponse.setData(list);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataResponse.error(e.getMessage());
		} catch (Exception e) {
			dataResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}

}

