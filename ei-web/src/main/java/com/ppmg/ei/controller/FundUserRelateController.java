package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.enumeration.UserTypeEnmu;
import com.ppmg.ei.dto.FundUserRelateDTO;
import com.ppmg.ei.model.FundUserRelateModel;
import com.ppmg.ei.service.FundUserRelateService;
import com.ppmg.ei.vo.FundUserRelateVO;
import io.swagger.annotations.*;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
@Scope("prototype")
@Api(tags={"基金与用户关联接口"})
public class FundUserRelateController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference(check = false)
	private FundUserRelateService fundUserRelateService;

	@ApiOperation(value = "保存基金与用户关联关系信息", notes = "保存基金与用户关联关系信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "relateId", value = "relateId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "dtoList", value = "基金与用户关联关系FundUserRelateDTO", required = true, dataType = "FundUserRelateDTO")
	})
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/saveFundUserRelate/{relateId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveFundUserRelate(@PathVariable("relateId")String relateId,@RequestBody List<FundUserRelateModel> dtoList) {

		try {
			UserTypeEnmu userType = getLoginUserType();
			String loginUserId = this.getLoginUserId();
			//String loginUserId ="150002";
			if(loginUserId.equals(relateId)){
				baseResponse.setMsg("对不起，不能分配自己账户下的基金");
				return	JSONObject.toJSONString(baseResponse);
			}
			if(userType.compareTo(UserTypeEnmu.EXTERNAL)==0){
			    fundUserRelateService.insertRelate(relateId,dtoList,loginUserId);
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
	@ApiOperation(value="基金与用户关联关系信息列表", notes="基金与用户关联关系信息列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/fundUserRelateist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String fundUserRelateist(@RequestParam("length") int length, @RequestParam("currPage") int currPage, @RequestParam("userId") String userId){
		try {

			searchCondition.setPageSize(length);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("USER_ID", userId);
			PageInfo<FundUserRelateModel> rows = fundUserRelateService.selectListPage(searchCondition);
			List<FundUserRelateVO> list = new ArrayList<FundUserRelateVO>();
			for(FundUserRelateModel model : rows.getList()){
				FundUserRelateVO vo = new FundUserRelateVO();
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

}

