package com.ppmg.ei.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.dubbo.config.support.Parameter;
import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.utils.UimUtilsUserId;
import com.ppmg.ei.vo.FundDescInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.beanutils.BeanUtils;
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
import com.ppmg.ei.model.FundDescInfoModel;
import com.ppmg.ei.service.FundDescInfoService;
/** 
 * 描述 [Controller] 
 * @author null
 * @date 2019-09-02 18:15 
 */ 
@RestController
@Scope("prototype")
@Api(tags={"历史沿革及增值服务接口"})
public class FundDescInfoController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference(check = false)
	private FundDescInfoService fundDescInfoService;

	@ApiOperation(value="获取历史沿革及增值服务详细信息", notes="获取历史沿革及增值服务详细信息")
	@ApiImplicitParam(name = "fundId", value = "fundId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
   })
	@GetMapping(value = "/selectFundDescInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectFundDescInfo(@RequestParam (value = "fundId") String fundId){
		try {
			FundDescInfoModel model = fundDescInfoService.selectById(fundId);
			FundDescInfoVO vo = new FundDescInfoVO();
			if(model != null){
				BeanUtils.copyProperties(vo, model);
			}
			dataResponse.setData(vo);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataResponse.error(e.getMessage());
		} catch (Exception e) {
			dataResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}

	@ApiOperation(value="更新历史沿革及增值服务详细信息", notes="更新历史沿革及增值服务详细信息")
	@ApiImplicitParam(name = "fundId", value = "fundId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@RequestMapping(value = "/updateFundDescInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateFundDescInfo(@RequestBody FundDescInfoModel fundDescInfoModel){
		try {
			if(fundDescInfoModel != null){
				String userId = String.valueOf(this.getLoginUser().getUserId());
				//String userId = UimUtilsUserId.getUserId("1");
				System.out.println("当前登陆人测试----------->"+userId);
				String fundId = fundDescInfoModel.getFundId();
				FundDescInfoModel model = fundDescInfoService.selectById(fundId);
				if(model != null){
					//更新
					fundDescInfoService.update(fundDescInfoModel);
				}else{
					//增加
					fundDescInfoModel.setUpdateBy(userId);
					fundDescInfoModel.setCreateBy(userId);
					fundDescInfoService.insert(fundDescInfoModel);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	@ApiOperation(value="增加历史沿革及增值服务详细信息", notes="增加历史沿革及增值服务详细信息")
	@ApiImplicitParam(name = "fundId", value = "fundId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/addFundDescInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addFundDescInfo(@RequestBody FundDescInfoModel fundDescInfoModel){
		try {
			if(fundDescInfoModel != null){
				fundDescInfoService.insert(fundDescInfoModel);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

}

