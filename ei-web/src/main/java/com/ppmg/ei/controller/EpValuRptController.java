package com.ppmg.ei.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.founder.ssm.foundation.service.SerialnoService;
import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.dto.EpValuRptDTO;
import com.ppmg.ei.dto.FitRegulationYelpDTO;
import com.ppmg.ei.model.FitRegulationYelpModel;
import com.ppmg.ei.vo.EpValuRptVO;
import com.ppmg.ei.vo.FitRegulationYelpVO;
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
import com.ppmg.ei.model.EpValuRptModel;
import com.ppmg.ei.service.EpValuRptService;
@Controller
@Scope("prototype")
@Api(tags={"定期报告更新接口"})
public class EpValuRptController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private EpValuRptService epValuRptService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@ApiOperation(value="定期报告查询接口", notes="定期报告查询接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
		@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/epValuRptList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String list(@RequestParam("length") int length, @RequestParam("currPage") int currPage, @RequestParam("afterFunderId") String afterFunderId,@RequestParam("objectId") String objectId){
		try {
			searchCondition.setPageSize(length);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionNotEqualTo("STATUS", "9");
			searchCondition.addConditionEqualTo("AFTER_FUNDER_ID",afterFunderId);
			searchCondition.addConditionEqualTo("OBJECT_ID",objectId);

			PageInfo<EpValuRptModel> rows = epValuRptService.selectListPage(searchCondition);
			List<EpValuRptVO> list = new ArrayList<EpValuRptVO>();
			for(EpValuRptModel model : rows.getList()){
				EpValuRptVO vo = new EpValuRptVO();
				BeanUtils.copyProperties(vo, model);
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

	@ApiOperation(value="项目定期报告详细信息", notes="定期报告详细信息")
	@ApiImplicitParam(name = "pkId", value = "pkId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
   })
	@GetMapping(value = "/epValuRpt/detaiInfo/{pkId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detail(@PathVariable(value = "pkId") String pkId){
		try {
			EpValuRptModel model = epValuRptService.selectById(pkId);
			EpValuRptVO vo = new EpValuRptVO();
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

	@ApiOperation(value="项目定期报告保存", notes="项目定期报告保存")
	@ApiImplicitParam(name = "FitRegulationYelpDTO", value = "项目定期报告保存", required = true, dataType = "FitRegulationYelpDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/epValuRpt/saveEpValuRptInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveEpValuRptInfo(@RequestBody EpValuRptDTO dto){

		try {
			EpValuRptModel model = new EpValuRptModel();
			BeanUtils.copyProperties(model, dto);
			//String userId="1005000";
			String userId=this.getLoginUserId();
			if(StringUtils.isNotEmpty(dto.getPkId())){
				model.setUpdateDt(new Date());
				model.setUpdateBy(userId);
				epValuRptService.update(model);
			}else{
				String pkid = serialnoService.getSequence("EI.TZ_T_EP_VALU_RPT");
				model.setPkId(pkid);
				model.setCreateBy(userId);
				model.setCreateDt(new Date());
				model.setUpdateBy(userId);
				model.setUpdateDt(new Date());
				model.setStatus("0");
				epValuRptService.insert(model);
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

