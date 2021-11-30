package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.model.EnteBasicInfoModel;
import com.ppmg.ei.service.EnteBasicInfoService;
import com.ppmg.ei.vo.EnteBasicInfoVO;
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
@Api(tags={"企业基础信息接口"})
@RequestMapping("/enteBasic")
public class EnteBasicInfoController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private EnteBasicInfoService enteBasicInfoService;

	@ApiOperation(value="年度财务报", notes="审计报告列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/fundEnteInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String fundEnteInfo(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("enterpriseId") String enterpriseId){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("ENTE_ID",enterpriseId);
			searchCondition.addConditionEqualTo("status","0");
			PageInfo<EnteBasicInfoModel> rows = enteBasicInfoService.selectListPage(searchCondition);
			List<EnteBasicInfoVO> list = new ArrayList<EnteBasicInfoVO>();
			for(EnteBasicInfoModel model : rows.getList()){
				EnteBasicInfoVO vo=new EnteBasicInfoVO();
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



	@ApiOperation(value="企业基础信息详细信息", notes="企业基础信息详细信息")
	@ApiImplicitParam(name = "id", value = "XXXID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/getDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getDetail(@RequestParam(value = "pkId") String pkId){
		try {
			EnteBasicInfoModel model=enteBasicInfoService.selectById(pkId);
			EnteBasicInfoVO vo = new EnteBasicInfoVO();
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



	@ApiIgnore
	@ApiOperation(value = "新增修改", notes = "基金阶段")
	@ApiImplicitParam(name = "ProjShareInfoDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/saveBasic/save", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String save(@RequestBody EnteBasicInfoModel dto) {
		try {
			dto.setUpdateBy(this.getLoginUserId());
			dto.setUpdateDt(new Date());
			//判断是否存在
			EnteBasicInfoModel model=new EnteBasicInfoModel();
			model.setYear(dto.getYear());
			model.setEnteId(dto.getEnteId());
			model.setStatus("0");
			List<EnteBasicInfoModel> list=enteBasicInfoService.selectList(model);
			if(StringUtils.isNotEmpty(dto.getPkId())){
				EnteBasicInfoModel base=enteBasicInfoService.selectById(dto.getPkId());
				if(!base.getYear().equals(dto.getYear())){
					if(list!=null&& !list.isEmpty()){
						baseResponse.error("-1",dto.getYear()+"年已存在");
						return	JSONObject.toJSONString(baseResponse);
					}
				}
				enteBasicInfoService.update(dto);
			}else{
				if(list!=null&& !list.isEmpty()){
					baseResponse.error("-1",dto.getYear()+"年已存在");
					return	JSONObject.toJSONString(baseResponse);
				}
				dto.setCreateBy(this.getLoginUserId());
				dto.setCreateDt(new Date());
				dto.setPkId(UUID.randomUUID().toString());
				enteBasicInfoService.insert(dto);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	@ApiOperation(value="管理报告删除", notes="根据url的id来获取XXX详细信息")
	@ApiImplicitParam(name = "id", value = "XXXID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/delete", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@RequestParam(value = "pkId") String pkId){
		try {
			enteBasicInfoService.deleteById(pkId);
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

