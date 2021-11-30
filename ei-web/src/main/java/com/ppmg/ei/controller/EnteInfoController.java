package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.foundation.service.SerialnoService;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.dto.EnteInfoDTO;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.vo.EnteInfoListVO;
import com.ppmg.ei.vo.EnteInfoVO;
import com.ppmg.ei.vo.ProjInfoVO;
import io.swagger.annotations.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ApiIgnore
@Controller
@Scope("prototype")
@Api(tags={"企业信息相关接口"})
public class EnteInfoController extends BaseController {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private EnteInfoService enteInfoService;

	@Reference(retries = -1)
	private EntBaseInfoService entBaseInfoService;

	@Reference
	private CustMemberService custMemberService;

	@Reference
	private ProjInfoService projInfoService;

	@Reference
	private FundInfoService fundInfoService;

	@Reference(check = false)
	private SerialnoService serialnoService;
	
	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@Reference(check = false)
	private EntLabelService entLabelService;

	@Reference
	private LabelService labelService;

	@Reference
	private AppUserService appUserService;

	@Reference
	private AppUserroleService appUserroleService;
	
	@ApiOperation(value="获取企业、或企业项目列表列表", notes="获取企业信息列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
		@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/enteInfos/list", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String list(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,String keyword,String enterpriseForm){
		
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			if(StringUtils.isNotEmpty(keyword)){
				searchCondition.addConditionLike("CH_NAME", "%" + keyword.trim() + "%");
			}
			if (StringUtils.isNotEmpty(enterpriseForm)) {
				List<String> enterpriseFormS = Arrays.asList(enterpriseForm.split(","));
				searchCondition.addConditionIn("ENTERPRISE_FORM", enterpriseFormS);
			}
			//查询角色       //判断是基金管理人还是
			AppUserModel appUser=  appUserService.selectById(this.getLoginUserId());
			AppUserroleModel appUserroleModel=new AppUserroleModel();
			appUserroleModel.setUserid(Long.parseLong(this.getLoginUserId()));
			List<AppUserroleModel> listRole=appUserroleService.selectList(appUserroleModel);
			//直投平台
			String typeStr="";
			if (appUser!=null&&5==appUser.getUsertype()) {
				for(AppUserroleModel role:listRole){
					//基金管理人
					if(10031L==role.getRoleid()){
						typeStr="1";
						break;
					}
					//出资人代表
					if(10032L==role.getRoleid()){
						typeStr="2";
						break;
					}
				}

			}else if(appUser!=null&&1==appUser.getUsertype()) {
				typeStr="3";

			}
			if("1".equals(typeStr)){
				//
				searchCondition.addParam("create_by",this.getLoginUserId());
				//searchCondition.addConditionEqualTo("d.create_by",this.getLoginUserId());
			}
			searchCondition.addParam("typeStr",typeStr);
			searchCondition.addParam("userId",this.getLoginUserId());
			searchCondition.addParam("loginName",appUser.getLoginname());
			searchCondition.addConditionNotEqualTo("d.status","2");
			PageInfo<ProjInfoModel> rows = enteInfoService.selectListPage(searchCondition);
			List<ProjInfoVO> list = new ArrayList<ProjInfoVO>();
			for(ProjInfoModel model : rows.getList()){
				ProjInfoVO vo = new ProjInfoVO();
				BeanUtils.copyProperties(vo, model);
				if(model!=null&&model.getEnteInfoModel()!=null){
					if(StringUtils.isNotEmpty(model.getEnteInfoModel().getEnterpriseForm())){
						String enterpriseFormName = codeUtils.getCodeNameByValue("203",model.getEnteInfoModel().getEnterpriseForm());
						vo.setEnterpriseFormName(enterpriseFormName);
					}
					if(model.getEnteInfoModel().getUpDt()!=null){
						model.getEnteInfoModel().setUpdateDt(model.getEnteInfoModel().getUpDt());
					}

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

	@ApiOperation(value="投企业信息", notes="获取企业信息列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/enteInfos/getList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,String chName){

		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("create_by",this.getLoginUserId());
			if(StringUtils.isNotEmpty(chName)){
				searchCondition.addConditionLike("CH_NAME", "%" + chName + "%");
			}

			PageInfo<EnteInfoModel> rows = enteInfoService.selectGetListPage(searchCondition);
			List<EnteInfoListVO> list = new ArrayList<EnteInfoListVO>();
			for(EnteInfoModel model : rows.getList()){
				EnteInfoListVO vo = new EnteInfoListVO();
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
	
	@ApiOperation(value="获取企业详细信息", notes="根据url的id来获取企业详细信息")
	@ApiImplicitParam(name = "id", value = "企业ID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/enteInfo/detail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detail( String enterpriseId,@RequestParam("projId")String projId){
		try {
			EnteInfoVO vo = new EnteInfoVO();
			ProjInfoModel projModel=projInfoService.selectById(projId);
			if(projModel!=null){
				enterpriseId=projModel.getOldEnterpriseId();
				vo.setProjModel(projModel);
				//查询企业表的统一社会信用代码
				if(StringUtils.isNotEmpty(projModel.getProjStatus())){
					String projStatusS = codeUtils.getCodeNameByValue("218",projModel.getProjStatus());
					vo.setProjStatusName(projStatusS);
				}
               vo.setProjStatus(projModel.getProjStatus());
			}
			EnteInfoModel model = enteInfoService.selectById(enterpriseId);
			BeanUtils.copyProperties(vo, model);
			if(projModel!=null&&StringUtils.isNotEmpty(projModel.getProjObject())){
				EntBaseInfoModel enteBase=entBaseInfoService.selectById(projModel.getProjObject());
			    if(StringUtils.isNotEmpty(enteBase.getCreditCode())){
			    	vo.setSocialCode(enteBase.getCreditCode());
			    	if(StringUtils.isNotEmpty(enteBase.getAddressDetails())){
						vo.setRegAdd(enteBase.getAddressDetails());
					}

				}
			}

			List<String> labels=new ArrayList<>();
			if(model!=null&&StringUtils.isNotEmpty(model.getEnteId())){
				//查询标签
				List<LabelModel> listLabel = labelService.selectListByLabel(model.getEnteId());
				String labelName = "";
				if(listLabel!=null && !listLabel.isEmpty()){
					for(LabelModel ent:listLabel){
						  if(StringUtils.isNotEmpty(ent.getLabelId())){
							  labels.add(ent.getLabelId());
							  if ("".equals(labelName)) {
								  labelName = ent.getLabelName();
							  } else {
								  labelName = labelName + "," + ent.getLabelName();
							  }

						  }
					}
				}
              vo.setLabels(labels);
				vo.setLabelsName(labelName);
			}
			//企业类型
			if(StringUtils.isNotEmpty(model.getEnterpriseForm())){
				String enterpriseFormName = codeUtils.getCodeNameByValue("203",model.getEnterpriseForm());
				vo.setEnterpriseFormName(enterpriseFormName);
			}
			if(StringUtils.isNotEmpty(model.getPhase())){
				String phaseName = codeUtils.getCodeNameByValue("9014",model.getPhase());
				vo.setPhaseName(phaseName);
			}
			if(StringUtils.isNotEmpty(model.getRegAmountCurrency())){
				String regAmountCurrencyName = codeUtils.getCodeNameByValue("103",model.getRegAmountCurrency());
				vo.setRegAmountCurrencyName(regAmountCurrencyName);
			}
			dataResponse.setData(vo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			baseResponse.error();
		}

		return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteMapNullValue);
		
	}
	

	@ApiIgnore
	@ApiOperation(value="删除企业信息", notes="根据url的id来删除企业信息")
	@ApiImplicitParam(name = "id", value = "企业ID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/enteInfo/delete", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@RequestParam("projId") String projId){
		
		try {
			ProjInfoModel pro=new ProjInfoModel();
			pro.setStatus("2");
			pro.setProjId(projId);
			projInfoService.update(pro);
		/*	EnteInfoModel model= enteInfoService.selectById(enterpriseId);
			if(model!=null){
				ProjInfoModel proj=new ProjInfoModel();
				proj.setProjObject(enterpriseId);
				proj.setProjType("1");
				List<ProjInfoModel> list=projInfoService.selectList(proj);
				if(list!=null&&!list.isEmpty()){
					baseResponse.setStatus("-1");
					baseResponse.setMsg("已存在业务数据");
					return JSONObject.toJSONString(baseResponse);
				}
			}

			enteInfoService.deleteById(enterpriseId);*/
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
		
	}
	




	@ApiIgnore
	@ApiOperation(value="新增企业信息", notes="根据EnteInfoModel对象创建企业")
	@ApiImplicitParam(name = "model", value = "企业信息实体 EnteInfoModel", required = true, dataType = "EnteInfoModel")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/ent/enteInfo/save", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String save(@RequestBody EnteInfoDTO dto){
		try {
			//因为涉及到一些置空的数据，需要重新写修改，企业详情修改需要参数为 UpdateNull='1'
			//判断是否
			EnteInfoModel model=new EnteInfoModel();
			BeanUtils.copyProperties(model,dto);
			CustMemberModel cum=new CustMemberModel();
			BeanUtils.copyProperties(cum,dto);
			ProjInfoModel projInfoModel=new ProjInfoModel();
			projInfoModel.setProjStatus(dto.getProjStatus());
			projInfoModel.setInveId(dto.getInveId());
			projInfoModel.setInveName(dto.getInveName());
			projInfoModel.setProjId(dto.getProjId());
			projInfoModel.setProjObject(dto.getEnteId());
			projInfoModel.setProjName(dto.getChName());
			projInfoModel.setProjType("1");
			projInfoModel.setGroupId(3L);
            //查询企业在标红是否存在
			if(StringUtils.isNotEmpty(dto.getEnterpriseId())){
				String typeStr="";
				String enterpriseId=dto.getEnterpriseId();
				EnteInfoModel enteM=enteInfoService.selectById(dto.getEnterpriseId());
			   if(enteM!=null&&StringUtils.isNotEmpty(enteM.getEnterpriseId())&& !dto.getChName().equals(enteM.getChName())){
					EnteInfoModel modelS=new EnteInfoModel();
					modelS.setChName(dto.getChName());
					List<EnteInfoModel> listE=  enteInfoService.selectList(modelS);
					if(listE!=null&&!listE.isEmpty()){
						enterpriseId=listE.get(0).getEnterpriseId();
						model.setEnterpriseId(enterpriseId);
						typeStr="update";
						projInfoModel.setOldEnterpriseId(enterpriseId);
					}else{
						enterpriseId=serialnoService.getSequence("EI.BD_T_ENTE_INFO");
						typeStr="add";
						model.setEnterpriseId(enterpriseId);
						projInfoModel.setOldEnterpriseId(enterpriseId);
					}
				}else{
				   typeStr="update";
			   }
				//判断出资主体是否修改
				if(StringUtils.isNotEmpty(dto.getProjId())){
					ProjInfoModel projMo= projInfoService.selectById(dto.getProjId());
					if(projMo!=null&&!dto.getInveId().equals(projMo.getInveId())){
						projInfoModel.setProjNo(getProjNo(dto.getInveId()));
					}
				}

				model.setUpdateBy(this.getLoginUserId());
				model.setUpdateDt(new Date());
				model.setUpdateNull(dto.getUpdateNull());
				projInfoModel.setProjObjectName(dto.getChName());
				enteInfoService.updateInfo(model,cum,dto.getLabels(),projInfoModel,typeStr);
			}else{
				EnteInfoModel modelSname=new EnteInfoModel();
				modelSname.setChName(dto.getChName());
				List<EnteInfoModel> listE=  enteInfoService.selectList(modelSname);
				String enterpriseId="";
				String typeStr="add";
				if(listE!=null&&!listE.isEmpty()){
					enterpriseId=listE.get(0).getEnterpriseId();
					//修改 typeStr
					typeStr="update";
				}else{
					enterpriseId=serialnoService.getSequence("EI.BD_T_ENTE_INFO");
					typeStr="add";
				}
				projInfoModel.setProjNo(getProjNo(dto.getInveId()));
				model.setEnterpriseId(enterpriseId);
				model.setCreateBy(this.getLoginUserId());
				model.setCreateDt(new Date());
				model.setUpdateBy(this.getLoginUserId());
				model.setUpdateDt(new Date());
				projInfoModel.setStatus("0");
				//projInfoModel.setProjStatus("1");
				projInfoModel.setOldEnterpriseId(enterpriseId);
				projInfoModel.setProjObjectName(dto.getChName());
				enteInfoService.insertInfo(model,cum, dto.getLabels(),projInfoModel,typeStr);

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}
	public String getProjNo(String inveId){
		String projNo="";
		if(StringUtils.isNotEmpty(inveId)){
			FundInfoModel fundModel=fundInfoService.selectById(inveId);
			if(fundModel!=null){
				//查询基金投了多少项目
				Integer num= fundInfoService.getSumProj(inveId);
				String orderNo = "";
				if(num+1<10){
					orderNo = "000" + String.valueOf(num+1);
				}else if(num+1<100){
					orderNo = "00" + String.valueOf(num+1);
				}else if(num+1<1000){
					orderNo = "0" + String.valueOf(num+1);
				}else{
					orderNo = String.valueOf(num+1);
				}
				//年份
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
				String year = sdf.format(new Date());
				//组合项目编号
				projNo=fundModel.getFundCode()==null?"":fundModel.getFundCode()+year+orderNo;

			}

		}
		return projNo;
	}



}
