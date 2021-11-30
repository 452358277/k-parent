package com.ppmg.ei.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.founder.ssm.core.vo.BaseVO;
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
import com.ppmg.ei.model.AdminPartnerModel;
import com.ppmg.ei.service.AdminPartnerService;
/** 
 * 描述 [Controller] 
 * @author null
 * @date 2019-08-13 10:53 
 */ 
@RestController
@Scope("prototype")
@Api(tags={"XXX接口"})
@RequestMapping("adminPartner")
public class AdminPartnerController extends BaseAction{

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private AdminPartnerService adminPartnerService;

	@ApiOperation(value="XXX列表", notes="XXX列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
		@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@PostMapping(value = "/list", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String list(@RequestParam("length") int length, @RequestParam("currPage") int currPage){
		try {

			searchCondition.setPageSize(length);
			searchCondition.setCurrPage(currPage);
			PageInfo<AdminPartnerModel> rows = adminPartnerService.selectListPage(searchCondition);
			List<BaseVO> list = new ArrayList<BaseVO>();
			for(AdminPartnerModel model : rows.getList()){
				BaseVO vo = new BaseVO();
				BeanUtils.copyProperties(model, vo);
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

	@ApiOperation(value="获取XXX详细信息", notes="根据url的id来获取XXX详细信息")
	@ApiImplicitParam(name = "id", value = "XXXID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
   })
	@GetMapping(value = "/detail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detail(@PathVariable(value = "id") String id){
		try {
			AdminPartnerModel model = adminPartnerService.selectById(id);
			BaseVO vo = new BaseVO();
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

}

