package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.dto.EnteAuditInfoDTO;
import com.ppmg.ei.model.AppUserModel;
import com.ppmg.ei.model.EnteAuditInfoModel;
import com.ppmg.ei.service.AppUserService;
import com.ppmg.ei.service.EnteAuditInfoService;
import com.ppmg.ei.vo.EnteAuditInfoVO;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Controller
@Scope("prototype")
@Api(tags={"审计报告接口"})
public class EnteAuditInfoController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private EnteAuditInfoService enteAuditInfoService;


	@Reference
	private AppUserService appUserService;

	@ApiOperation(value="审计报告列表", notes="审计报告列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/fundEnteAuditInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String fundEnteAuditInfo(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("enterpriseId") String enterpriseId){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("a.ENTERPRISE_ID",enterpriseId);
			PageInfo<EnteAuditInfoModel> rows = enteAuditInfoService.selectListPage(searchCondition);
			List<EnteAuditInfoVO> list = new ArrayList<EnteAuditInfoVO>();
			for(EnteAuditInfoModel model : rows.getList()){
				EnteAuditInfoVO vo=new EnteAuditInfoVO();
				BeanUtils.copyProperties(model,vo);
				if(model!=null&&StringUtils.isNotEmpty(model.getUpdateBy())){
					AppUserModel modelStr = appUserService.selectById(model.getUpdateBy());
					String name=modelStr.getName();
					vo.setUpdateByName(name);
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
	@PostMapping(value = "/fundEnteAudit/save", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String save(@RequestBody EnteAuditInfoDTO dto) {
		try {
			String userId = this.getLoginUserId();
			EnteAuditInfoModel model=new EnteAuditInfoModel();
			BeanUtils.copyProperties(dto, model);
			model.setUpdateBy(userId);
			model.setUpdateDt(new Date());
		    if(StringUtils.isNotEmpty(dto.getPkId())){
				enteAuditInfoService.update(model);
			}else{
		    	model.setPkId(UUID.randomUUID().toString());
				model.setCreateBy(this.getLoginUserId());
				model.setCreateDt(new Date());
				enteAuditInfoService.insert(model);
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
	@GetMapping(value = "/enteAuditInfo/{pkId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String enteAuditInfo(@PathVariable(value = "pkId") String pkId){
		try {
			EnteAuditInfoModel model = enteAuditInfoService.selectById(pkId);
			EnteAuditInfoVO vo = new EnteAuditInfoVO();
			BeanUtils.copyProperties(model, vo);
            if(model!=null&&StringUtils.isNotEmpty(model.getUpdateBy())){
				AppUserModel modelStr = appUserService.selectById(model.getUpdateBy());
				String name=modelStr.getName();
				vo.setUpdateByName(name);
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
	@GetMapping(value = "/enteAudit/delete/{pkId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@PathVariable(value = "pkId") String pkId){
		try {
			enteAuditInfoService.deleteById(pkId);
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

