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
import com.ppmg.ei.dto.EnteEmployeeOverviewDTO;
import com.ppmg.ei.model.EnteEmployeeOverviewModel;
import com.ppmg.ei.service.AppUserService;
import com.ppmg.ei.service.EnteEmployeeOverviewService;
import com.ppmg.ei.vo.EnteEmployeeOverviewVO;
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
@Api(tags={"人事接口"})
@RequestMapping("/enteEmployee")
public class EnteEmployeeOverviewController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference(check = false)
	private EnteEmployeeOverviewService enteEmployeeOverviewService;

	@Reference(check = false)
	private AppUserService appUserService;


	@Reference(check = false)
	private SerialnoService serialnoService;


	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@ApiOperation(value="APP-人事列表", notes="APP-人事列表")
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
	@GetMapping(value = "/EnteEmployeeOverviewList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String EnteEmployeeOverviewList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,@RequestParam("projectObject") String projectObject,@RequestParam("inveId") String inveId){
		try {

			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("ENTERPRISE_ID", projectObject);
			searchCondition.addConditionEqualTo("INVEST_ID", inveId);
			PageInfo<EnteEmployeeOverviewModel> rows = enteEmployeeOverviewService.selectListPage(searchCondition);
			List<EnteEmployeeOverviewVO> list = new ArrayList<EnteEmployeeOverviewVO>();

			for(EnteEmployeeOverviewModel model : rows.getList()){
				EnteEmployeeOverviewVO vo = new EnteEmployeeOverviewVO();
				BeanUtils.copyProperties( model,vo);
				vo.setCreateName(appUserService.selectById(model.getCreateBy()).getName());
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
	@ApiOperation(value = "基金阶段", notes = "基金阶段")
	@ApiImplicitParam(name = "ProjContractFileDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/save", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String save(@RequestBody EnteEmployeeOverviewDTO dto) {
		try {
			String userId = this.getLoginUserId();
			EnteEmployeeOverviewModel model = new EnteEmployeeOverviewModel();
		     BeanUtils.copyProperties(dto,model);
			model.setUpdateBy(userId);
			model.setUpdateDt(new Date());
			if(StringUtils.isNotEmpty(dto.getPkId())){
				EnteEmployeeOverviewModel m=enteEmployeeOverviewService.selectById(dto.getPkId());
				//获取年，和季度
				String year="";
				String quar="";
				if(m!=null&&StringUtils.isNotEmpty(m.getQuarter())){
					 year=m.getQuarter().substring(0,4);
					 quar=m.getQuarter().substring(4);
				}

				String yearNew=dto.getQuarter().substring(0,4);
				String quarNew=m.getQuarter().substring(4);
				if(!m.getQuarter().equals(dto.getQuarter())){
					EnteEmployeeOverviewModel modelS = new EnteEmployeeOverviewModel();
					modelS.setEnterpriseId(dto.getEnterpriseId());
					modelS.setQuarter(dto.getQuarter());
					List<EnteEmployeeOverviewModel> list=enteEmployeeOverviewService.selectList(modelS);
					if(list!=null&&!list.isEmpty()){
						baseResponse.error("-1",yearNew+"年:该季度已存在");
						return	JSONObject.toJSONString(baseResponse);
					}
				}
				enteEmployeeOverviewService.update(model);
			}else{
				//先判断该年的季度数据是否存在
				String yearStr=dto.getQuarter().substring(0,4);
				EnteEmployeeOverviewModel modelS = new EnteEmployeeOverviewModel();
				modelS.setEnterpriseId(dto.getEnterpriseId());
				modelS.setQuarter(dto.getQuarter());
				List<EnteEmployeeOverviewModel> list=enteEmployeeOverviewService.selectList(modelS);
				if(list!=null&&!list.isEmpty()){
					baseResponse.error("-1",yearStr+"年:该季度已存在");
					return	JSONObject.toJSONString(baseResponse);
				}
				String pkId = serialnoService.getSequence("EI.BD_T_ENTE_EMPLOYEE_OVERVIEW");
				model.setPkId(pkId);
				model.setCreateBy(userId);
				model.setCreateDt(new Date());
				enteEmployeeOverviewService.insert(model);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	//列表
	@ApiOperation(value="人事信息列表", notes="基金管理人列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/getEnteEmployeeList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getEnteEmployeeList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("enterpriseId") String enterpriseId){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("ENTERPRISE_ID",enterpriseId);
			PageInfo<EnteEmployeeOverviewModel> rows = enteEmployeeOverviewService.selectListPage(searchCondition);
			List<EnteEmployeeOverviewVO> list = new ArrayList<EnteEmployeeOverviewVO>();
			for(EnteEmployeeOverviewModel model : rows.getList()){
				EnteEmployeeOverviewVO vo=new EnteEmployeeOverviewVO();
				org.springframework.beans.BeanUtils.copyProperties(model,vo);
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
	@ApiImplicitParam(name = "pkId", value = "pkId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/detainfo/{pkId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detainfo(@PathVariable(value = "pkId") String pkId){
		try {
			EnteEmployeeOverviewModel fundFinanceModel=enteEmployeeOverviewService.selectById(pkId);
			EnteEmployeeOverviewVO vo=new EnteEmployeeOverviewVO();
			org.springframework.beans.BeanUtils.copyProperties( fundFinanceModel,vo);
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
	public String del(@RequestParam("pkId") String pkId) {
		try {
			enteEmployeeOverviewService.deleteById(pkId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}




}

