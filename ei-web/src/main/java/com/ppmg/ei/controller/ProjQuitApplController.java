package com.ppmg.ei.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.founder.ssm.foundation.service.SerialnoService;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.dto.ProjContractFileDTO;
import com.ppmg.ei.dto.ProjQuitApplModelDTO;
import com.ppmg.ei.model.FundMcInfoModel;
import com.ppmg.ei.model.FundQuitApplModel;
import com.ppmg.ei.model.ProjContractFileModel;
import com.ppmg.ei.service.AppUserService;
import com.ppmg.ei.vo.FundMcInfoVO;
import com.ppmg.ei.vo.FundQuitApplVO;
import com.ppmg.ei.vo.ProjQuitApplVO;
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
import com.ppmg.ei.model.ProjQuitApplModel;
import com.ppmg.ei.service.ProjQuitApplService;
@Controller
@Scope("prototype")
@Api(tags={"项目退出接口"})
public class ProjQuitApplController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private ProjQuitApplService projQuitApplService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Reference
	private AppUserService appUserService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@ApiIgnore
	@ApiOperation(value="投项目退出", notes="项目退出")
	@ApiImplicitParam(name = "ProjContractFileDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/projQuitAppl/add", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(@RequestBody ProjQuitApplModelDTO dto){
		try {
			String userId=this.getLoginUserId();
			//String userId="1005000";
			String appId=serialnoService.getSequence("EI.TZ_T_PROJ_QUIT_APPL");
			ProjQuitApplModel projQuitApplModel=new ProjQuitApplModel();
			BeanUtils.copyProperties(projQuitApplModel,dto);
			projQuitApplModel.setCreateDt(new Date());
			projQuitApplModel.setCreateBy(userId);
			projQuitApplModel.setUpdateBy(userId);
			projQuitApplModel.setUpdateDt(new Date());
			projQuitApplModel.setAppId(appId);
			projQuitApplModel.setAppDt(new Date());
			projQuitApplModel.setAppUser(appUserService.getUserNameById(userId));
			projQuitApplModel.setStatus("0");
			projQuitApplService.insert(projQuitApplModel);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	@ApiOperation(value="项目退出列表", notes="项目退出列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/projQuitAppl/projQuitApplList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String fundMcInfoList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,String projId){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("IA_ID", projId);
			searchCondition.addConditionNotEqualTo("STATUS", "9");
			PageInfo<ProjQuitApplModel> rows = projQuitApplService.selectListPage(searchCondition);
			List<ProjQuitApplVO> list = new ArrayList<ProjQuitApplVO>();
			for(ProjQuitApplModel model : rows.getList()){
				ProjQuitApplVO vo = new ProjQuitApplVO();
				BeanUtils.copyProperties(vo, model);
				if(StringUtils.isNotEmpty(model.getQuitType())){
					vo.setQuitTypeName(codeUtils.getCodeNameByValue("262", model.getQuitType()));
				}
				if(StringUtils.isNotEmpty(model.getQuitWay())){
					vo.setQuitWayName(codeUtils.getCodeNameByValue("217", model.getQuitWay()));
				}
				vo.setExitAmount(model.getRevenue());
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
	@ApiOperation(value="修改", notes="修改")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "appId", value = "appId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "ProjQuitApplVO", value = "ProjQuitApplVO", required = true, dataType = "ProjQuitApplVO")
	})
	@PostMapping(value = "/projQuitAppl/update/{appId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update(@PathVariable("appId") String appId, @RequestBody ProjQuitApplVO dto){

		try {
			ProjQuitApplModel model = new ProjQuitApplModel();
			BeanUtils.copyProperties(model, dto);
			model.setAppId(appId);
			projQuitApplService.update("updateInfo",model);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}
	@ApiOperation(value="详情", notes="详情")
	@ApiImplicitParam(name = "appId", value = "appId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/projQuitAppl/detainfo/{appId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detail(@PathVariable(value = "appId") String appId){
		try {
			ProjQuitApplModel model = projQuitApplService.selectById(appId);
			ProjQuitApplVO vo=new ProjQuitApplVO();
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

