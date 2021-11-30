package com.ppmg.ei.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.founder.ssm.foundation.service.SerialnoService;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.bean.CooOrgSearchBean;
import com.ppmg.ei.dto.CooOrgDTO;
import com.ppmg.ei.model.SurverCooorgModel;
import com.ppmg.ei.service.SurverCooorgService;
import com.ppmg.ei.vo.CooOrgVO;
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
import com.ppmg.ei.model.CooOrgModel;
import com.ppmg.ei.service.CooOrgService;
/**
 * 描述 [Controller] 
 * @author null
 * @date 2019-08-13 10:53 
 */
@RestController
@Scope("prototype")
@Api(tags={"合作方机构库接口"})
@RequestMapping("/cooOrg")
public class CooOrgController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private CooOrgService cooOrgService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;


	@ApiOperation(value="合作方机构库列表", notes="合作方机构库列表")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String list(@ModelAttribute CooOrgSearchBean searchBean){
		try {
			if(StringUtils.isNotEmpty(searchBean.getKeyWord())){
				searchBean.setKeyWord("%"+searchBean.getKeyWord().trim()+"%");
			}
			PageInfo<CooOrgModel> rows = cooOrgService.selectPage(searchCondition,searchBean);
			List<CooOrgVO> list = new ArrayList<CooOrgVO>();
			for(CooOrgModel model : rows.getList()){
				CooOrgVO vo = new CooOrgVO();
				BeanUtils.copyProperties(model, vo);
				if(StringUtils.isNoneBlank(model.getOrgType())){
					vo.setOrgTypeName(codeUtils.getCodeNameByValue("237", model.getOrgType()));
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

	@ApiOperation(value = "新增合作方机构库", notes = "根据CooOrgDTO对象")
	@ApiImplicitParam(name = "dto", value = "合作方机构库CooOrgDTO", required = true, dataType = "CooOrgDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(@RequestBody CooOrgDTO dto){
		try {
			long userId =this.getLoginUser().getUserId();
			CooOrgModel model = new CooOrgModel();
			BeanUtils.copyProperties(dto, model);
			model.setUpdateBy(String.valueOf(userId));
			model.setCreateBy(String.valueOf(userId));

			cooOrgService.insertModel(model);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			baseResponse.error(e.getMessage());
		}
		return JSONObject.toJSONString(baseResponse, SerializerFeature.WriteMapNullValue);
	}

	@ApiOperation(value = "更新合作方机构库", notes = "根据url的id来指定更新合作方机构库信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "dto", value = "合作方机构库dto", required = true, dataType = "CooOrgDTO")
	})
	@PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update(@RequestBody CooOrgDTO dto) {
		try {
			long userId = this.getLoginUser().getUserId();
			CooOrgModel model = new CooOrgModel();
			BeanUtils.copyProperties(dto, model);
			model.setUpdateBy(String.valueOf(userId));
			cooOrgService.updateModel(model);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse, SerializerFeature.WriteMapNullValue);

	}

	@ApiOperation(value="获取合作方机构库详细信息", notes="根据url的id来获取合作方机构库详细信息")
	@ApiImplicitParam(name = "orgId", value = "orgId", required = true, dataType = "String")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/detail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detail(String orgId){
		try {
			CooOrgModel sqlModel = new CooOrgModel();
			sqlModel.setOrgId(orgId);
			List<CooOrgModel> modelList = cooOrgService.selectListModel(sqlModel);
			if(modelList!=null&&modelList.size()>0){
				CooOrgVO vo = new CooOrgVO();
				BeanUtils.copyProperties(modelList.get(0), vo);
				dataResponse.setData(vo);
			}else{
				dataResponse.setData(null);
			}

		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataResponse.error(e.getMessage());
		} catch (Exception e) {
			dataResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteMapNullValue);
	}

	@ApiOperation(value="删除合作方机构库", notes="删除合作方机构库")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orgIds", value = "主键拼接", required = true, dataType = "String"),
	})
	@GetMapping(value = "/delete", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@RequestParam("orgIds") String orgIds){

		try {
			String[] idsArray = orgIds.split(",");
			cooOrgService.deleteBatchModel(idsArray);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse, SerializerFeature.WriteMapNullValue);

	}


	@ApiOperation(value="合作方机构库下拉款列表", notes="合作方机构库列表")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/getlist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getlist(@RequestBody CooOrgSearchBean searchBean){
		try {
			if(searchBean.getKeyWord()!=null){
				if(StringUtils.isNotEmpty(searchBean.getKeyWord().trim())){
					searchBean.setKeyWord("%"+searchBean.getKeyWord().trim()+"%");

				}
			}
			//todo必须是流程审批通过的
			CooOrgModel cooOrgModel=new CooOrgModel();
			cooOrgModel.setOrgName(searchBean.getKeyWord().trim());
			List<CooOrgModel> listModel = cooOrgService.selectListByCooName(cooOrgModel);
			List<CooOrgVO> list = new ArrayList<CooOrgVO>();
			for(CooOrgModel model : listModel){
				CooOrgVO vo = new CooOrgVO();
				BeanUtils.copyProperties(model, vo);
				if(StringUtils.isNoneBlank(model.getOrgType())){
					vo.setOrgTypeName(codeUtils.getCodeNameByValue("237", model.getOrgType()));
				}

				list.add(vo);
			}
			mapResponse.put("list", list);

		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
		} catch (Exception e) {
			dataTablesResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}

}

