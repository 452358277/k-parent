package com.ppmg.ei.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.model.FundInfoModel;
import com.ppmg.ei.vo.CommTNewVO;
import com.ppmg.ei.vo.FundInfoVO;
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
import com.ppmg.ei.model.CommTNewModel;
import com.ppmg.ei.service.CommTNewService;
@Controller
@Scope("prototype")
@Api(tags={"?????????????????????"})
public class CommTNewController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private CommTNewService commTNewService;


	@ApiOperation(value="???????????????????????????", notes="???????????????????????????")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "??????ID", required = true, dataType = "String"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "????????????"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "?????????????????????"),
	})
	@GetMapping(value = "/commTNew/getCommTNewList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getCommTNewList(@RequestParam("id") String id){
		try {
			List<CommTNewModel> rows =commTNewService.selectListById(id);
			List<CommTNewVO> list = new ArrayList<CommTNewVO>();
			for(CommTNewModel model : rows){
				CommTNewVO vo = new CommTNewVO();
				BeanUtils.copyProperties(vo, model);
				list.add(vo);
			}
			dataTablesResponse.setData(list);
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


}

