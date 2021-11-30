package com.ppmg.ei.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.founder.ssm.foundation.service.SerialnoService;
import com.founder.ssm.web.annotations.Token;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.enumeration.UserTypeEnmu;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.dto.InveInfoDTO;
import com.ppmg.ei.dto.LedgerMagInfoDTO;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.vo.*;
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

@Controller
@Scope("prototype")
@Api(tags={"查询出资人接口接口"})
public class InveInfoController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference(check = false)
	private InveInfoService inveInfoService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@Reference(check = false)
	private InveResumeService inveResumeService;

	@Reference
	private AppRoleService appRoleService;

	@Reference
	private FundShareInfoService fundShareInfoService;



	@Reference
	private AppUserService appUserService;


	@ApiOperation(value="查询出资人接口下拉款" , notes="查询查询出资人接口接口情况表")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/inveInfo/getList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getList(@RequestParam(value="mcName", required=false) String mcName){
		try {
			InveInfoModel in=new InveInfoModel();
			in.setInvestorName(mcName);
			List<InveInfoModel> rows=inveInfoService.selectListByMcName(in);
			List<InveInfoVO> list=new ArrayList<InveInfoVO>();
			for(InveInfoModel model:rows){
				InveInfoVO vo=new InveInfoVO();
				BeanUtils.copyProperties(vo, model);
				list.add(vo);
			}
			dataTablesResponse.setData(list);

		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
		} catch (Exception e) {
			dataTablesResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}

	@ApiIgnore
	@ApiOperation(value="新增出资人信息", notes="新增出资人信息")
	@ApiImplicitParam(name = "InveInfoDTO", value = "InveInfoDTO", required = true, dataType = "InveInfoDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/inveInfo/add", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(@RequestBody InveInfoDTO dto){
		try {
			//判断出资人是否存在
             if(StringUtils.isNotEmpty(dto.getInvestorName())){
				 InveInfoModel InveInfoVO=new InveInfoModel();
				 InveInfoVO.setInvestorName(dto.getInvestorName());
				 InveInfoModel InveInfoS= inveInfoService.selectBy(InveInfoVO);
				 if(InveInfoS!=null){
					 baseResponse.setMsg("对不起投资人名称已存在！");
					 return	JSONObject.toJSONString(baseResponse);
				 }
			 }else{
				 baseResponse.setMsg("请选择出资人！");
				 return	JSONObject.toJSONString(baseResponse);
			 }
			InveInfoModel model = new InveInfoModel();
			BeanUtils.copyProperties(model,dto);

			InveResumeModel inveResumeModel=new InveResumeModel();
			BeanUtils.copyProperties(inveResumeModel,dto);

			String id = serialnoService.getSequence("EI.BD_T_INVE_INFO");
			model.setInvestorId(id);
			model.setStatus("0");
			String userId=this.getLoginUserId();
			//String userId="1005002";
			inveResumeModel.setInvestorId(id);
			//截取
			if(StringUtils.isNotEmpty(dto.getRegCapitalCurrency())){
				if(dto.getRegCapitalCurrency().contains("万")){
					model.setRegCapital(Double.valueOf(dto.getRegCapitalCurrency().substring(0,dto.getRegCapitalCurrency().indexOf("万")))*10000);
				}
				if(dto.getRegCapitalCurrency().contains("人民币")){
					model.setRegCurrency("人民币");
				}else if(dto.getRegCapitalCurrency().contains("美元")){
					model.setRegCurrency("美元");
				}else if(dto.getRegCapitalCurrency().contains("欧元")){
					model.setRegCurrency("欧元");
				}
			}else{
				model.setRegCurrency("");
			}


			inveInfoService.insertByInvestorId(model,userId,inveResumeModel);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiIgnore
	@ApiOperation(value="修改出资人信息", notes="根据url的id来修改出资人信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "investorId", value = "investorId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "InveInfoDTO", value = "InveInfoDTO", required = true, dataType = "InveInfoDTO")
	})
	@PostMapping(value = "/inveInfo/update/{investorId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update(@PathVariable("investorId") String investorId, @RequestBody InveInfoDTO dto){
		try {
			if(StringUtils.isNotEmpty(dto.getInvestorName())){
				InveInfoModel inModel =inveInfoService.selectById(investorId);
				if(inModel!=null){
					if(StringUtils.isNotEmpty(inModel.getInvestorName())){
						if(!inModel.getInvestorName().equals(dto.getInvestorName())){
							InveInfoModel inveInfoVo =new InveInfoModel();
							inveInfoVo.setInvestorName(dto.getInvestorName());
							List<InveInfoModel> inveInfoVo1=inveInfoService.selectList(inveInfoVo);
							if(inveInfoVo1!=null&&!inveInfoVo1.isEmpty()){
								baseResponse.setMsg("对不起投资人名称已存在！");
								return	JSONObject.toJSONString(baseResponse);
							}
						}
					}

				}
			}
			InveInfoModel model = new InveInfoModel();
			BeanUtils.copyProperties(model, dto);
			String userId=this.getLoginUserId();
			//String userId="1005002";
			InveResumeModel inveResumeModel=new InveResumeModel();
			BeanUtils.copyProperties(inveResumeModel,dto);
            model.setRegCapital(dto.getRegCapital()==null?0.0:dto.getRegCapital()*10000);
			inveInfoService.updateByInvestorId(model,userId,inveResumeModel);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);
	}
	@ApiOperation(value="出资人信息列表", notes="出资人信息列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/inveInfo/inveInfoList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String inveInfoList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,String keyWord,String investorType){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			if(keyWord != null && keyWord != ""){
				keyWord = "'%" + keyWord.trim() + "%'";
			}
			searchCondition.addParam("keyWord",keyWord);

			if(StringUtils.isNotEmpty(investorType)){
				List<String> investor_type = Arrays.asList(investorType.split(","));
				searchCondition.addConditionIn("INVESTOR_TYPE", investor_type);
			}

			searchCondition.addConditionEqualTo("STATUS","0");
			String userId=this.getLoginUserId();
			//String userId="1005002";
			//searchCondition.addConditionEqualTo("CREATE_BY",userId);
			//判断如果是管理人才需要创建人
			UserTypeEnmu userType = getLoginUserType();
			List<AppRoleModel> listRow = appRoleService.selectByUserId(this.getLoginUserId());
			if (userType.compareTo(UserTypeEnmu.EXTERNAL) == 0) {
				if (listRow != null && listRow.size() > 0) {
					if (1005002 == listRow.get(0).getId() || 1005003 == listRow.get(0).getId()) {
						searchCondition.addConditionEqualTo("CREATE_BY",userId);
					}
				}

			}else{
				AppUserModel appU=appUserService.selectById(this.getLoginUserId());
				if(appU!=null &&5 ==appU.getUsertype()){
					searchCondition.addConditionEqualTo("CREATE_BY",userId);
				}
			}

			PageInfo<InveInfoModel> rows = inveInfoService.selectListPage(searchCondition);
			List<InveInfoModelVO> list = new ArrayList<InveInfoModelVO>();
			for(InveInfoModel model : rows.getList()){
				InveInfoModelVO vo = new InveInfoModelVO();
				BeanUtils.copyProperties(vo, model);
				if(model.getRegCapital()!=null){
                    vo.setRegCapital(model.getRegCapital()/10000);
                }
				if(StringUtils.isNotEmpty(model.getInvestorName())){
					vo.setInvestorTypeName(codeUtils.getCodeNameByValue("209", model.getInvestorType()));
				}
				InveResumeModel inveResumeModel=inveResumeService.selectById(model.getInvestorId());
				if(inveResumeModel!=null){
					vo.setInveResumeModel(inveResumeModel);
				}
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
	@ApiOperation(value="检查出资人是否存在", notes="检查出资人是否存在")
	@ApiImplicitParam(name = "InveInfoDTO", value = "InveInfoDTO", required = true, dataType = "InveInfoDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/inveInfo/check", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String check(@RequestBody InveInfoDTO dto){
		try {
			//判断出资人是否存在
			if(StringUtils.isEmpty(dto.getInvestorId())){
				if(StringUtils.isNotEmpty(dto.getInvestorName())){
					InveInfoModel InveInfoVO=new InveInfoModel();
					InveInfoVO.setInvestorName(dto.getInvestorName());
					InveInfoModel InveInfoS= inveInfoService.selectBy(InveInfoVO);
					if(InveInfoS!=null){
						baseResponse.setMsg("投资人名称已存在！");
						return	JSONObject.toJSONString(baseResponse);
					}
				}

			}else{
				InveInfoModel inModel =inveInfoService.selectById(dto.getInvestorId());
				if(inModel!=null){
					if(!inModel.getEnteId().equals(dto.getInvestorName())){
						//
						InveInfoModel inveInfoVo =new InveInfoModel();
						inveInfoVo.setEnteId(dto.getInvestorName());
						InveInfoModel inveInfoVo1=inveInfoService.selectBy(inveInfoVo);
						if(inveInfoVo1!=null){
							baseResponse.setMsg("投资人名称已存在！");
							return	JSONObject.toJSONString(baseResponse);
						}
					}
				}

			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);

	}




	@ApiIgnore
	@ApiOperation(value="检查出资人是否存在", notes="检查出资人是否存在")
	@ApiImplicitParam(name = "InveInfoDTO", value = "InveInfoDTO", required = true, dataType = "InveInfoDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/inveInfo/delate/{investorId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delate(@PathVariable("investorId") String investorId){
		try {
			//判断出资人是否存在
			if(StringUtils.isEmpty(investorId)){
				//查询是否有基金选择则了该出资主体
				FundShareInfoModel fundShareInfoModel=new FundShareInfoModel();
				fundShareInfoModel.setInvestorId(investorId);
				List<FundShareInfoModel>  list=fundShareInfoService.selectList(fundShareInfoModel);
				if(list!=null&&list.size()>0){
					baseResponse.setMsg("存在业务数据-不可删除！");
					return	JSONObject.toJSONString(baseResponse);
				}else{
					inveInfoService.deleteById(investorId);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);

	}

}

