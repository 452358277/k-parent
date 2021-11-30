package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.foundation.service.SerialnoService;
import com.founder.ssm.web.common.CommonStatus;
import com.founder.uim.util.Encrypt;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.common.SerialNoConstants;
import com.ppmg.ei.dto.BankInfoDTO;
import com.ppmg.ei.model.BankInfoModel;
import com.ppmg.ei.model.FundMcInfoModel;
import com.ppmg.ei.model.UserModel;
import com.ppmg.ei.service.AppUserService;
import com.ppmg.ei.service.BankInfoService;
import com.ppmg.ei.service.FundMcInfoService;
import com.ppmg.ei.service.UserService;
import com.ppmg.ei.vo.BankInfoVO;
import com.ppmg.ei.vo.FundMcInfoVO;
import io.swagger.annotations.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
@Scope("prototype")
@Api(tags={"基金管理人接口"})
public class FundMcInfoController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private FundMcInfoService fundMcInfoService;

	@Reference
	private UserService userService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@Reference
	private BankInfoService bankInfoService;



	@ApiOperation(value="基金管理人列表", notes="基金管理人列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/fundMcInfoList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String fundMcInfoList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam(value="type", required=false) String type,@RequestParam(value="mcName", required=false) String mcName){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
              if(StringUtils.isNotEmpty(type)){
              	String a="0";
                  if(a.equals(type)){
                      //管理人信息维护
                      searchCondition.addConditionEqualTo("USER_ID",this.getLoginUserId());
                  }
              }
			if(StringUtils.isNotEmpty(mcName)){
				searchCondition.addConditionLike("MC_NAME","%"+mcName+"%");
			}

			PageInfo<FundMcInfoModel> rows = fundMcInfoService.selectListPage1(searchCondition);
			List<FundMcInfoVO> list = new ArrayList<FundMcInfoVO>();
			for(FundMcInfoModel model : rows.getList()){
				FundMcInfoVO vo = new FundMcInfoVO();
				BeanUtils.copyProperties(vo, model);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				if(model.getEntBaseInfo()!=null){
					vo.setIsRegister(model.getIsRegister());
					vo.setStatus(model.getEntBaseInfo().getStatus());
					vo.setCreditCode(model.getEntBaseInfo().getCreditCode());
					vo.setRegistCapi(model.getEntBaseInfo().getRegistCapi());
					vo.setAddressDetails(model.getEntBaseInfo().getAddressDetails());
					if(model.getEntBaseInfo().getStartDate()!=null){
					String time=formatter.format(model.getEntBaseInfo().getStartDate());
						vo.setStartDate(time);
					}
				}
				/*if(StringUtils.isNotEmpty(model.getIsRegister())){
					vo.setIsRegister(codeUtils.getCodeNameByValue("225", model.getIsRegister()));
				}*/
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

	@ApiOperation(value="基金管理人新增", notes="基金管理人新增")
	@ApiImplicitParam(name = "FundMcInfoVO", value = "基金管理人实体FundMcInfoVO", required = true, dataType = "FundMcInfoVO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/fundMcInfo/add", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(@RequestBody FundMcInfoVO dto){

		try {
			if(StringUtils.isNotEmpty(dto.getMcName())){
				FundMcInfoModel mo = new FundMcInfoModel();
				mo.setMcName(dto.getMcName());
				FundMcInfoModel fundMc=fundMcInfoService.selectBy(mo);
				if(fundMc==null){
					FundMcInfoModel model = new FundMcInfoModel();
					long userId = this.getLoginUser().getUserId();
					//long userId =1005000;
					model.setCreateBy(String.valueOf(userId));
					model.setCreateDt(new Date());
					model.setUpdateBy(String.valueOf(userId));
					model.setUpdateDt(new Date());
					BeanUtils.copyProperties(model, dto);
					String mcId = serialnoService.getSequence("EI.BD_T_FUND_MC_INFO");
					String uid = serialnoService.getSequence("BASE.APP_USER");
					model.setMcId(mcId);
					model.setUserId(uid);
					if(StringUtils.isNotEmpty(dto.getRegisterDate())){
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						Date regDate=formatter.parse(dto.getRegisterDate());
						model.setRegisterDt(regDate);
					}
						UserModel user=new UserModel();
						//String uid = serialnoService.getSequence(SerialNoConstants.APP_USER);
						user.setId(Long.parseLong(uid));
						user.setLoginname(dto.getMcName());
						user.setName(dto.getMcName());
						user.setDescription(dto.getMcName());
						user.setUsertype(2L);
					    user.setIsDelete("0");
						String password = Encrypt.DataEncrypt("888888");
						user.setPassword(password);
						UserModel user1=new UserModel();
						user1.setLoginname(model.getMcName());
						UserModel user2=userService.selectBy(user1);
						if(user2==null){
							fundMcInfoService.insertById(user,model);
							/*userService.insert(user);
							fundMcInfoService.insert(model);*/
						}else{
							baseResponse.setMsg("管理人用户名已存在");
						}


				}else{
					baseResponse.setMsg("管理人名称已存在");
				}
			}
	/*		FundMcInfoModel model = new FundMcInfoModel();
			long userId = this.getLoginUser().getUserId();
			long userId=13240;
			model.setCreateBy(String.valueOf(userId));
			model.setCreateDt(new Date());
			model.setUpdateBy(String.valueOf(userId));
			model.setUpdateDt(new Date());
			BeanUtils.copyProperties(model, dto);
			String mcId = serialnoService.getSequence("EI.BD_T_FUND_MC_INFO");
			model.setMcId(mcId);
			fundMcInfoService.insert(model);*/

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse,SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);

	}

	@ApiIgnore
	@ApiOperation(value="修改基金管理人", notes="修改基金管理人")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mcId", value = "mcId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "FundMcInfoVO", value = "FundMcInfoVO", required = true, dataType = "FundMcInfoVO")
	})
	@PostMapping(value = "/fundMcInfo/update/{mcId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update(@PathVariable("mcId") String mcId, @RequestBody FundMcInfoVO dto){
		try {
			FundMcInfoModel model = new FundMcInfoModel();
			BeanUtils.copyProperties(model, dto);
			if(StringUtils.isNotEmpty(dto.getRegisterDate())){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date regDate=formatter.parse(dto.getRegisterDate());
				model.setRegisterDt(regDate);
			}
			model.setMcId(mcId);
			model.setUpdateBy(this.getLoginUserId());
			model.setUpdateDt(new Date());
			fundMcInfoService.update(model);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}


	@ApiOperation(value="基金管理人列表", notes="基金管理人列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/fundMc/mcList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String mcList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam(value="type", required=false) String type,@RequestParam(value="mcName", required=false) String mcName){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			if(StringUtils.isNotEmpty(mcName)){
				searchCondition.addConditionLike("MC_NAME","%"+mcName+"%");
			}

			PageInfo<FundMcInfoModel> rows = fundMcInfoService.selectListPage1(searchCondition);
			List<FundMcInfoVO> list = new ArrayList<FundMcInfoVO>();
			for(FundMcInfoModel model : rows.getList()){
				FundMcInfoVO vo = new FundMcInfoVO();
				BeanUtils.copyProperties(vo, model);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				if(model.getEntBaseInfo()!=null){
					vo.setIsRegister(model.getIsRegister());
					vo.setStatus(model.getEntBaseInfo().getStatus());
					vo.setCreditCode(model.getEntBaseInfo().getCreditCode());
					vo.setRegistCapi(model.getEntBaseInfo().getRegistCapi());
					vo.setAddressDetails(model.getEntBaseInfo().getAddressDetails());
					if(model.getEntBaseInfo().getStartDate()!=null){
						String time=formatter.format(model.getEntBaseInfo().getStartDate());
						vo.setStartDate(time);
					}
				}
				/*if(StringUtils.isNotEmpty(model.getIsRegister())){
					vo.setIsRegister(codeUtils.getCodeNameByValue("225", model.getIsRegister()));
				}*/
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
			@ApiImplicitParam(name = "mcId", value = "mcId", required = true, dataType = "String",paramType = "path"),
	})
	@GetMapping(value = "/fundMcInfo/delete/{mcId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@PathVariable("mcId") String mcId){

		try {
			fundMcInfoService.deleteById(mcId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiIgnore
	@ApiOperation(value="基金管理人重置密码", notes="基金管理人重置密码")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mcId", value = "mcId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "FundMcInfoVO", value = "FundMcInfoVO", required = true, dataType = "FundMcInfoVO")
	})
	@GetMapping(value = "/fundMcInfo/resetPassword/{mcId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String resetPassword(@PathVariable("mcId") String mcId){
		try {

				String password = Encrypt.DataEncrypt("888888");
				UserModel userModel=new UserModel();
				userModel.setId( Long.valueOf(mcId));
				userModel.setPassword(password);
				userModel.setUpdateDt(new Date());
				userModel.setUpdateBy(this.getLoginUserId());
				Integer c=userService.update(userModel);
				if(c>0){
					baseResponse.setMsg("密码重置为：888888。");
					return JSONObject.toJSONString(baseResponse);
				}


		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}
}

