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
import com.ppmg.ei.dto.projValuationDTO;
import com.ppmg.ei.model.AppUserModel;
import com.ppmg.ei.model.ProjRegularAssessModel;
import com.ppmg.ei.model.ProjValuationModel;
import com.ppmg.ei.service.AppUserService;
import com.ppmg.ei.service.ProjValuationService;
import com.ppmg.ei.vo.ProjValuationVO;
import io.swagger.annotations.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
@Scope("prototype")
@Api(tags={"项目估值接口"})
public class ProjValuationController extends BaseController{

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private ProjValuationService projValuationService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Reference
	private  AppUserService appUserService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@ApiIgnore
	@ApiOperation(value="新增项目估值接口", notes="新增项目估值接口")
	@ApiImplicitParam(name = "projValuationDTO", value = "projValuationDTO", required = true, dataType = "projValuationDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/projValuation/add", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(@RequestBody projValuationDTO dto){
		try {
			ProjValuationModel model=new ProjValuationModel();
			BeanUtils.copyProperties(model,dto);
			ProjRegularAssessModel projRegularAssessModel=new ProjRegularAssessModel();
			BeanUtils.copyProperties(projRegularAssessModel,dto);
			String userId=this.getLoginUserId();
			//String userId="1005000";
			projValuationService.insertByProjValuation(userId,model,projRegularAssessModel);

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiOperation(value="项目定期估值列表", notes="项目估值列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/projValuation/projValuationList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String projValuationList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("projObject") String projObject,@RequestParam("inveId") String inveId ){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			//projObject被投对象
			searchCondition.addParam("projObject",projObject);
			searchCondition.addParam("inveId",inveId);
			searchCondition.addConditionNotEqualTo("t1.STATUS", "9");
			PageInfo<ProjValuationModel> rows = projValuationService.selectListPage(searchCondition);
			List<ProjValuationVO> list = new ArrayList<ProjValuationVO>();
			for(ProjValuationModel model : rows.getList()){
				ProjValuationVO vo = new ProjValuationVO();
				BeanUtils.copyProperties(vo, model);
				if(StringUtils.isNotEmpty(model.getValuType())){
					vo.setValuTypeName(codeUtils.getCodeNameByValue("17130", model.getValuType()));
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


	@ApiOperation(value="详情", notes="详情")
	@ApiImplicitParam(name = "appId", value = "appId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/projValuation/detainfo/{pkId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detail(@PathVariable(value = "pkId") String pkId){
		try {
			ProjValuationModel model = projValuationService.selectById(pkId);
			ProjValuationVO vo=new ProjValuationVO();
			BeanUtils.copyProperties(vo, model);
			if(StringUtils.isNotEmpty(model.getValuType())){
				vo.setValuTypeName(codeUtils.getCodeNameByValue("17130", model.getValuType()));
			}

			if(model!=null){
				//填写人
				if(StringUtils.isNotEmpty(model.getCreateBy())&& !"init".equals(model.getCreateBy())){
					AppUserModel appUser=appUserService.selectById(model.getCreateBy());
					vo.setUserName(appUser.getName());
				}else{
					vo.setUserName("初始化");
				}
				if(model.getCreateDt()!=null){
					SimpleDateFormat forMat=new SimpleDateFormat("yyyy-MM-dd");
					vo.setApplyDt(forMat.format(model.getCreateDt()));

				}
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


	@ApiIgnore
	@ApiOperation(value="修改", notes="修改")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "appId", value = "appId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "FundQuitApplVO", value = "FundQuitApplVO", required = true, dataType = "FundQuitApplVO")
	})
	@PostMapping(value = "/projValuation/update/{pkId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update(@PathVariable("pkId") String pkId, @RequestBody projValuationDTO dto){

		try {
			ProjValuationModel model = new ProjValuationModel();
			BeanUtils.copyProperties(model, dto);
			model.setPkId(pkId);
			//String userId="1005000";
			String userId=this.getLoginUserId();
			model.setUpdateBy(userId);
			model.setUpdateDt(new Date());
			projValuationService.update(model);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);

	}

	//
	@ApiIgnore
	@ApiOperation(value="删除", notes="删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "praId", value = "praId", required = true, dataType = "String",paramType = "path"),
	})
	@GetMapping(value = "/projValuation/delete/{praId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@PathVariable("praId") String praId){
		try {
			projValuationService.deleteByvalue(praId);

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);

	}

	//***************************************股权投资，企业投资***************************************************
	@ApiIgnore
	@ApiOperation(value="新增项目估值接口", notes="新增项目估值接口")
	@ApiImplicitParam(name = "projValuationDTO", value = "projValuationDTO", required = true, dataType = "projValuationDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/projValuation/saveEnteValue", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveEnteValue(@RequestBody projValuationDTO dto){
		try {
			ProjValuationModel model=new ProjValuationModel();
			BeanUtils.copyProperties(model,dto);
			String userId=this.getLoginUserId();
			model.setUpdateBy(userId);
			model.setUpdateDt(new Date());
			if(StringUtils.isNotEmpty(dto.getPkId())){
				projValuationService.update(model);
			}else{
				String pkId=serialnoService.getSequence("EI.TZ_T_PROJ_VALUATION");
				model.setCreateBy(userId);
				model.setCreateDt(new Date());
				model.setPkId(pkId);
				projValuationService.insert(model);
			}


		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}


	@ApiOperation(value="企业定期估值列表", notes="企业定期估值列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/enteValuation/enteValuaList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String enteValuationList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("iaId") String iaId ){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			//projObject被投对象
			//searchCondition.addParam("OBJECT_ID",objectId);
			searchCondition.addConditionEqualTo("IA_ID",iaId);
			searchCondition.addConditionNotEqualTo("STATUS", "9");
			PageInfo<ProjValuationModel> rows = projValuationService.selectListEntPage(searchCondition);
			List<ProjValuationVO> list = new ArrayList<ProjValuationVO>();
			for(ProjValuationModel model : rows.getList()){
				ProjValuationVO vo = new ProjValuationVO();
				BeanUtils.copyProperties(vo, model);
				if(StringUtils.isNotEmpty(model.getValuType())){
					vo.setValuTypeName(codeUtils.getCodeNameByValue("17130", model.getValuType()));
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
	@ApiOperation(value="删除", notes="删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "praId", value = "praId", required = true, dataType = "String",paramType = "path"),
	})
	@GetMapping(value = "/projValuation/deleteBy/{pkId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteBy(@PathVariable("pkId") String pkId){
		try {
			projValuationService.deleteById(pkId);

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);

	}
}

