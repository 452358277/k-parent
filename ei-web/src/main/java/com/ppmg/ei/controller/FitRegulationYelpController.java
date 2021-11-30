package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.foundation.service.SerialnoService;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.dto.FitRegulationYelpDTO;
import com.ppmg.ei.model.AppUserModel;
import com.ppmg.ei.model.FitRegulationYelpModel;
import com.ppmg.ei.service.AppUserService;
import com.ppmg.ei.service.FitRegulationYelpService;
import com.ppmg.ei.vo.FitRegulationYelpVO;
import io.swagger.annotations.*;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
@Scope("prototype")
@Api(tags={"合规点评接口"})
public class FitRegulationYelpController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private FitRegulationYelpService fitRegulationYelpService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Reference
	private AppUserService appUserService;

	@ApiOperation(value="合规点评列表", notes="合规点评列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
		@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
			@ApiImplicitParam(name = "projOrFundId", value = "项目/基金ID", required = true, dataType = "int")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/FitList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String FitList(@RequestParam("length") int length, @RequestParam("currPage") int currPage, @RequestParam("projOrFundId") String projOrFundId){
		try {
			searchCondition.setPageSize(length);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionNotEqualTo("STATUS", "1");
			searchCondition.addConditionEqualTo("PROJ_OR_FUND_ID",projOrFundId);
			PageInfo<FitRegulationYelpModel> rows = fitRegulationYelpService.selectListPage(searchCondition);
			List<FitRegulationYelpVO> list = new ArrayList<FitRegulationYelpVO>();
			for(FitRegulationYelpModel model : rows.getList()){
				FitRegulationYelpVO vo = new FitRegulationYelpVO();
				BeanUtils.copyProperties(vo, model);
				AppUserModel app=appUserService.selectById(model.getCreateBy());
				if(app!=null){
					vo.setCreateBy(app.getName());
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

	@ApiOperation(value="获取合规点评详细信息", notes="根据url的id来获取合规点评详细信息")
	@ApiImplicitParam(name = "FitID", value = "FitID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
   })
	@GetMapping(value = "/FitInfo/{FitID}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String FitInfo(@PathVariable(value = "FitID") String FitID){
		try {
			FitRegulationYelpModel model = fitRegulationYelpService.selectById(FitID);
			FitRegulationYelpVO vo = new FitRegulationYelpVO();
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

	@ApiOperation(value="保存合规点评", notes="保存合规点评")
	@ApiImplicitParam(name = "FitRegulationYelpDTO", value = "保存合规点评实体FitRegulationYelpDTO", required = true, dataType = "FitRegulationYelpDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/saveFitInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveFitInfo(@RequestBody FitRegulationYelpDTO dto){

		try {
			FitRegulationYelpModel model = new FitRegulationYelpModel();
			BeanUtils.copyProperties(model, dto);
			String userId=this.getLoginUserId();
			//String userId="1005000";
			if(dto.getFitId() != null && dto.getFitId() != ""){
				model.setUpdateDt(new Date());
				model.setUpdateBy(this.getLoginUserId());
			     fitRegulationYelpService.update(model);
			}else{
				model.setIsDelete("0");
				String pkid = serialnoService.getSequence("EI.BD_T_FIT_REGULATION_YELP");
				model.setFitId(pkid);
				model.setCreateBy(userId);
				model.setCreateDt(new Date());
				model.setUpdateBy(userId);
				model.setUpdateDt(new Date());
				model.setStatus("0");
				fitRegulationYelpService.insert(model);
			}
		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			baseResponse.error();
			logger.error(e.getMessage(), e);
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiOperation(value="删除合规点评", notes="删除合规点评")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "fitId", value = "合规点评ID", required = true, dataType = "String"),
	})
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/deleteFitInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteFitInfo(@RequestParam("fitId") String fitId){

		try {
			if(fitId != null && fitId != ""){
				fitRegulationYelpService.deleteById(fitId);
			}
		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			baseResponse.error();
			logger.error(e.getMessage(), e);
		}

		return JSONObject.toJSONString(baseResponse);
	}
}

