package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.dto.FundAccountDetailDTO;
import com.ppmg.ei.model.FundAccountDetailModel;
import com.ppmg.ei.service.FundAccountDetailService;
import com.ppmg.ei.vo.FundAccountDetailVO;
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
@Api(tags={"基金明细接口"})
public class FundAccountDetailController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private FundAccountDetailService fundAccountDetailService;


	@ApiIgnore
	@ApiOperation(value = "基金阶段", notes = "基金阶段")
	@ApiImplicitParam(name = "ProjContractFileDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/fundAccountDetail/save", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String save(@RequestBody FundAccountDetailDTO dto){
		try {
			String userId = this.getLoginUserId();
			FundAccountDetailModel model = new FundAccountDetailModel();
			BeanUtils.copyProperties(dto,model);
			model.setUpdateBy(userId);
			model.setUpdateDt(new Date());
			if(StringUtils.isNotEmpty(dto.getPkId())){
				fundAccountDetailService.update(model);
			}else{
				String pkId = UUID.randomUUID().toString();
				model.setPkId(pkId);
				model.setCreateBy(userId);
				model.setCreateDt(new Date());
				fundAccountDetailService.insert(model);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	//列表
	@ApiOperation(value="基金账户列表", notes="基金管理人列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/fundAccountList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String fundFinanceList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("fundId") String fundId){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("FUND_ID",fundId);
			searchCondition.addConditionEqualTo("STATUS","0");
			PageInfo<FundAccountDetailModel> rows = fundAccountDetailService.selectListPage(searchCondition);
			List<FundAccountDetailVO> list = new ArrayList<FundAccountDetailVO>();
			for(FundAccountDetailModel model : rows.getList()){
				FundAccountDetailVO vo=new FundAccountDetailVO();
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

	@ApiOperation(value="详情", notes="详情")
	@ApiImplicitParam(name = "appId", value = "appId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/fundAccountDetail/detainfo/{pkId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detainfo(@PathVariable(value = "pkId") String pkId){
		try {
			FundAccountDetailModel model=fundAccountDetailService.selectById(pkId);
			FundAccountDetailVO vo=new FundAccountDetailVO();
			BeanUtils.copyProperties( model,vo);
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

