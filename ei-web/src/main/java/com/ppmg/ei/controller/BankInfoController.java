package com.ppmg.ei.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.founder.ssm.foundation.service.SerialnoService;
import com.founder.uim.util.Encrypt;
import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.dto.BankInfoDTO;
import com.ppmg.ei.model.FundMcInfoModel;
import com.ppmg.ei.vo.BankInfoVO;
import com.ppmg.ei.vo.FundMcInfoVO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
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
import com.ppmg.ei.model.BankInfoModel;
import com.ppmg.ei.service.BankInfoService;
/** 
 * 描述 [Controller] 
 * @author yuyongjun
 * @date 2019-06-25 09:21 
 */

@Controller
@Scope("prototype")
@RequestMapping("/bankInfo")
@Api(tags={"新增银行接口"})
public class BankInfoController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private BankInfoService bankInfoService;


	@ApiOperation(value="银行列表", notes="银行列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/bankInfoList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String bankInfoList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam(value="userId", required=false) String userId,@RequestParam(value="bankName", required=false) String bankName){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
		/*	if(StringUtils.isNotEmpty(userId)){
				searchCondition.addConditionEqualTo("USER_ID",this.getLoginUserId());
			}*/
			if(StringUtils.isNotEmpty(bankName)){
				searchCondition.addConditionLike("BANK_NAME","%"+bankName+"%");
			}
			PageInfo<BankInfoModel> rows = bankInfoService.selectListPage(searchCondition);
			List<BankInfoVO> list = new ArrayList<BankInfoVO>();
			for(BankInfoModel model : rows.getList()){
				BankInfoVO vo = new BankInfoVO();
				BeanUtils.copyProperties(vo, model);
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


	@ApiOperation(value="基金银行", notes="基金银行新增")
	@ApiImplicitParam(name = "BankInfoDTO", value = "BankInfoDTO", required = true, dataType = "BankInfoDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(@RequestBody BankInfoDTO dto){

		try {
			BankInfoModel model = new BankInfoModel();
			BeanUtils.copyProperties(model, dto);
			String password = Encrypt.DataEncrypt("888888");
			String userId=this.getLoginUserId();
            //String userId="150002";
			Integer a=-1;
			Integer b=-2;
			Integer in=bankInfoService.insertBankInfo(model,password,userId);
			if(a.equals(in)){
				baseResponse.setMsg("银行已存在，请直接选择");
			}else if(b.equals(in)){
				baseResponse.setMsg("您的银行登录名已存在");
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}


}

