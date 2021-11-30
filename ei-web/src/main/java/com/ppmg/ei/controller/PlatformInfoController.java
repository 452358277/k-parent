package com.ppmg.ei.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.founder.ssm.core.model.BaseModel;
import com.founder.ssm.web.annotations.Token;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.bean.PlatformInfoSearchBean;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.vo.*;
import com.ppmg.ei.dto.PlatformInfoDTO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.founder.ssm.core.action.BaseAction;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import com.github.pagehelper.PageInfo;


@Controller
@Scope("prototype")
@RequestMapping("/platformInfo")
@Api(tags={"平台信息接口"})
public class PlatformInfoController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@Reference(retries=-1)
	private PlatformInfoService platformInfoService;

	@Reference(retries=-1)
	private EntBaseInfoService entBaseInfoService;

	@Reference(retries=-1)
	private CommTCodeService commTCodeService;


	@Reference
	private AppUserService appUserService;

	@Reference
	private AppUserroleService appUserroleService;

	@ApiIgnore
	@GetMapping(value = "/toList")
	public String toList(){
		return "platformManagement/list";
	}

	@ApiOperation(value="平台信息列表", notes="平台信息列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
		@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@PostMapping(value = "/list", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String list(@RequestBody PlatformInfoSearchBean searchBean){
		try {
                if(searchBean.getKeyword()!=null){
					searchBean.setKeyword(searchBean.getKeyword().trim());
				}
			/*平台性质start*/
			String platTypes = searchBean.getPlatType();
			if(platTypes!=""&&platTypes!=null){
				List<String> platTypeList = Arrays.asList(searchBean.getPlatType().split(","));
				searchCondition.addConditionIn("PLAT_TYPE", platTypeList);
			}

			//平台列表权限start
			AppUserModel appUserModel = appUserService.selectById(this.getLoginUserId());
			CommTCodeModel commTCodeModel = new CommTCodeModel();
			String groupId = "";
			if(!("1001".equals(String.valueOf(appUserModel.getDeptid()))||"1002".equals(String.valueOf(appUserModel.getDeptid()))||"1003".equals(String.valueOf(appUserModel.getDeptid()))||"21858".equals(String.valueOf(appUserModel.getDeptid())))){
				if("1000".equals(String.valueOf(appUserModel.getOrgid()))&&"1005".equals(String.valueOf(appUserModel.getDeptid()))){
					commTCodeModel.setCodeValue(String.valueOf(appUserModel.getDeptid()));
				}else{
					commTCodeModel.setCodeValue(String.valueOf(appUserModel.getOrgid()));
				}
				commTCodeModel.setParentId("266");
				commTCodeModel = commTCodeService.selectBy(commTCodeModel);
				if(commTCodeModel!=null){
					groupId = commTCodeModel.getCodeName();
				}


			}
			//平台列表权限end
			searchCondition.addParam("groupId",groupId);
			/*平台性质查询end*/
			searchCondition.setSearchBean(searchBean);
			PageInfo<PlatformInfoModel> rows = platformInfoService.selectListPage(searchCondition);
			List<PlatformInfoListVO> list = new ArrayList<PlatformInfoListVO>();
			for(PlatformInfoModel model : rows.getList()){
				String entId = model.getEntId();
				EntBaseInfoModel entBaseInfoModel = entBaseInfoService.selectById(entId);
				String operName = entBaseInfoModel.getOperName();
				String registCapi = entBaseInfoModel.getRegistCapi();

				PlatformInfoListVO vo = new PlatformInfoListVO();
				String platType = "";
				if(model.getPlatType()!=""&&model.getPlatType()!=null){
						platType = codeUtils.getCodeNameByValue("601", model.getPlatType());
						//平台性质
				}

				BeanUtils.copyProperties(vo, model);
				vo.setOperName(operName);
				vo.setRegistCapi(registCapi);
				vo.setPlatType(platType);
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

	@ApiIgnore
	@GetMapping(value = "/toEdit")
	public ModelAndView toEdit(String dealMark, String pkId){
		ModelAndView mav = new ModelAndView("platformManagement/edit");
		try {

			PlatformInfoViewVO vo = new PlatformInfoViewVO();

			String entId = "";
			if("update".equals(dealMark)){
				PlatformInfoModel platformInfoModel = platformInfoService.selectById(pkId);
				entId = platformInfoModel.getEntId();
				EntBaseInfoModel entBaseInfoModel = entBaseInfoService.selectById(entId);
				BeanUtils.copyProperties(vo, platformInfoModel);

                //统一社会信用代码
				String creditCode = entBaseInfoModel.getCreditCode();
				//注册资本
				String registCapi = entBaseInfoModel.getRegistCapi();
				//法定代表人
				String operName = entBaseInfoModel.getOperName();
				//地址
				String addressDetails = entBaseInfoModel.getAddressDetails();

				vo.setCreditCode(creditCode);
				vo.setRegistCapi(registCapi);
				vo.setOperName(operName);
				vo.setAddressDetails(addressDetails);


			}

			mav.addObject("formData", JSONObject.toJSONString(vo, SerializerFeature.WriteMapNullValue));


			mav.addObject("dealMark", dealMark);
			mav.addObject("entId", entId);

		} catch (SystemException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return mav;
	}


	@ApiIgnore
	@ApiOperation(value="新增平台管理", notes="根据PlatformInfoDTO对象创建平台")
	@ApiImplicitParam(name = "ResumeManagementDTO", value = "平台管理实体PlatformInfoDTO", required = true, dataType = "PlatformInfoDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(@RequestBody PlatformInfoDTO dto){

		try {
			PlatformInfoModel model = new PlatformInfoModel();
			BeanUtils.copyProperties(model, dto);
			platformInfoService.insertCodeAndOrgAndRole(model,this.getLoginUserId());
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}


	@ApiIgnore
	@ApiOperation(value="更新平台信息", notes="根据url的id来指定更新平台信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "平台信息主键pkId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "ResumeManagementDTO", value = "平台信息实体PlatformInfoDTO", required = true, dataType = "PlatformInfoDTO")
	})
	@PostMapping(value = "/update/{id}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update(@PathVariable("id") String id, @RequestBody PlatformInfoDTO dto){

		try {

			PlatformInfoModel model = new PlatformInfoModel();
			BeanUtils.copyProperties(model, dto);

			platformInfoService.update(model);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}
//获取详情
@ApiOperation(value = "获取详情", notes = "获取详情")
@ApiImplicitParam(name = "pkId", value = "出资人ID", required = true, dataType = "String", paramType = "path")
@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
		@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
})
@GetMapping(value = "/detail/{pkId}", produces = "application/json;charset=UTF-8")
@ResponseBody
public String detail(@PathVariable(value = "pkId") String pkId) {
	try {

		PlatformInfoViewVO vo = new PlatformInfoViewVO();

		PlatformInfoModel model = platformInfoService.selectById(pkId);
		String entId = model.getEntId();
		EntBaseInfoModel entBaseInfoModel = entBaseInfoService.selectById(entId);
		BeanUtils.copyProperties(vo, model);

        //统一社会信用代码
		String creditCode = entBaseInfoModel.getCreditCode();
		//注册资本
		String registCapi = entBaseInfoModel.getRegistCapi();
		//法定代表人
		String operName = entBaseInfoModel.getOperName();
		//地址
		String addressDetails = entBaseInfoModel.getAddressDetails();

		vo.setCreditCode(creditCode);
		vo.setRegistCapi(registCapi);
		vo.setOperName(operName);
		vo.setAddressDetails(addressDetails);

		//处理主要高管（多人）
		String ZYGGName = "" ;
		//PTPI_EXTEND_ONE
		String ZYGGId = vo.getPtpiExtendOne();
		if(ZYGGId!=null&&ZYGGId!=""){
			ZYGGName = 	String.valueOf(platformInfoService.selectOne("getZYGGName",ZYGGId));
		}

		vo.setPtpiExtendOne(ZYGGName);

		//码值转换
		if (StringUtils.isNotBlank(model.getPlatType())) {
			vo.setPlatTypeName(codeUtils.getCodeNameByValue("601", model.getPlatType()));
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

	@ApiIgnore
	@GetMapping(value = "/toDetail")
	public ModelAndView toDetail(String pkId){
		ModelAndView mav = new ModelAndView("platformManagement/detail");
		try {

			PlatformInfoViewVO vo = new PlatformInfoViewVO();

			PlatformInfoModel model = platformInfoService.selectById(pkId);
			String entId = model.getEntId();
			EntBaseInfoModel entBaseInfoModel = entBaseInfoService.selectById(entId);
			BeanUtils.copyProperties(vo, model);

//统一社会信用代码
			String creditCode = entBaseInfoModel.getCreditCode();
			//注册资本
			String registCapi = entBaseInfoModel.getRegistCapi();
			//法定代表人
			String operName = entBaseInfoModel.getOperName();
			//地址
			String addressDetails = entBaseInfoModel.getAddressDetails();

			vo.setCreditCode(creditCode);
			vo.setRegistCapi(registCapi);
			vo.setOperName(operName);
			vo.setAddressDetails(addressDetails);

			//处理主要高管（多人）
			String ZYGGName = "" ;
			String ZYGGId = vo.getPtpiExtendOne();//PTPI_EXTEND_ONE
			if(ZYGGId!=null&&ZYGGId!=""){
				ZYGGName = 	String.valueOf(platformInfoService.selectOne("getZYGGName",ZYGGId));
			}

			vo.setPtpiExtendOne(ZYGGName);

			//码值转换
			String platType = "";
			if("1".equals(model.getPlatType())){
				platType="股权平台";
			}else if("2".equals(model.getPlatType())){
				platType="债权平台";
			}else if("3".equals(model.getPlatType())){
				platType="投融资平台";
			}else if("4".equals(model.getPlatType())){
				platType="金交平台";
			}
			vo.setPlatType(platType);
			mav.addObject("viewData", vo);
		} catch (SystemException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return mav;
	}


	/*@ApiIgnore
	@GetMapping(value = "/toPlatformInformation")
	public ModelAndView toPlatformInformation(String pkId){
		ModelAndView mav = new ModelAndView("platformInformation");
		return mav;
	}*/

	@ApiOperation(value="获取平台基本信息", notes="根据url的平台Id来获取平台基本信息")
	@ApiImplicitParam(name = "pkId", value = "平台ID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/platInfoDetail/{pkId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String platInfoDetail(@PathVariable(value = "pkId") String pkId){
		try {
			PlatformInfoModel model = platformInfoService.getPlatformInfoByPlatId(pkId);
			PlatformInfoViewVO vo = new PlatformInfoViewVO();
			BeanUtils.copyProperties(vo, model);
			if(model.getPtpiExtendOne() != null && model.getPtpiExtendOne() != ""){
				List<AppUserModel> userModelList = appUserService.getUserListById(model.getPtpiExtendOne());
				List<AppUserVO> userVOList = new ArrayList<AppUserVO>();
				for(AppUserModel userModel : userModelList){
					AppUserVO userVO = new AppUserVO();
					BeanUtils.copyProperties(userVO, userModel);
					userVOList.add(userVO);
				}
				if(userVOList.size() > 0){
					vo.setDjgUserList(userVOList);
				}
			}
			if(model.getPlatType() != null){
				vo.setPlatTypeName(codeUtils.getCodeNameByValue("601", model.getPlatType()));
			}
			if("0".equals(model.getEntId())){
				vo.setPlatTypeName("控股平台");
			}

			//登录用户是否有编辑基本信息权限
			String userId = this.getLoginUserId();
			String users = appUserroleService.getUserIdByRoleId("210495");//平台信息维护人角色
			if(users!= null && users.contains(userId)){
				vo.setIsEdit("Y");
			}else{
				vo.setIsEdit("N");
			}

			if(model.getUpdateBy() != null && model.getUpdateBy() != ""){
				vo.setUpdateBy(appUserService.selectById(model.getUpdateBy()).getName());
			}



			if(model.getEntId()!=""&&model.getEntId()!=null){
				String sql1 = "select (select  wmsys.wm_concat(person_id) from master.t_ent_directors where enterprise_id='"+model.getEntId()+"'  " +
						"      and job in ('董事长','董事长兼经理','董事、董事长','董事长兼总经理','总经理,董事长','董事长（法定代表人）（董事会选举)') " +
						"      ) as PRESIDENT,( " +
						"      select wmsys.wm_concat(person_id) from master.t_ent_directors where enterprise_id='"+model.getEntId()+"' and job in ('董事','职工董事','职工代表董事','执行董事','执行董事,经理','董事、总经理','董事兼总经理','独立董事','董事,副经理','董事兼总经理','董事,董事长,经理','执行（常务）董事') " +
						"      )as DIRECTOR,( " +
						"      select wmsys.wm_concat(person_id) from master.t_ent_directors where enterprise_id='"+model.getEntId()+"' and job in ('监事主席','职工监事','监事','职工代表监事','监事,监事主席','监事会主席') " +
						"      ) as SUPERVISOR from dual ";
				List<Map<String,Object>> directorsList = platformInfoService.selectSql2Map(sql1);
				if(directorsList.size()>0&&directorsList.get(0)!=null){
					if(directorsList.get(0).containsKey("PRESIDENT")){
						if(directorsList.get(0).get("PRESIDENT")!=null&&directorsList.get(0).get("PRESIDENT")!=""){
							vo.setPresident(String.valueOf(directorsList.get(0).get("PRESIDENT")));
						}else{
							vo.setPresident("");
						}
					}else{
						vo.setPresident("");
					}
					if(directorsList.get(0).containsKey("DIRECTOR")){
						if(directorsList.get(0).get("DIRECTOR")!=null&&directorsList.get(0).get("DIRECTOR")!=""){
							vo.setDirector(String.valueOf(directorsList.get(0).get("DIRECTOR")));
						}else{
							vo.setDirector("");
						}
					}else{
						vo.setDirector("");
					}
					if(directorsList.get(0).containsKey("SUPERVISOR")){
						if(directorsList.get(0).get("SUPERVISOR")!=null&&directorsList.get(0).get("SUPERVISOR")!=""){
							vo.setSupervisor(String.valueOf(directorsList.get(0).get("SUPERVISOR")));
						}else{
							vo.setSupervisor("");
						}
					}else{
						vo.setSupervisor("");
					}

				}else{
					vo.setPresident("");//董事长
					vo.setDirector("");//董事
					vo.setSupervisor("");//监事

				}


			}else{
				vo.setPresident("");//董事长
				vo.setDirector("");//董事
				vo.setSupervisor("");//监事

			}



			mapResponse.put("model", vo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}

	@ApiOperation(value="获取投资人列表", notes="获取投资人列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
			@ApiImplicitParam(name = "pkId", value = "平台ID", required = true, dataType = "String")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/getPlatInvestorList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getPlatInvestorList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("pkId") String pkId){
		try {

			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			PageInfo<BaseModel> rows = new PageInfo<BaseModel>();
			rows.setPageSize(10);
			rows.setTotal(1);
			rows.setSize(1);
			List<PlatFormInvestorVO> list = new ArrayList<PlatFormInvestorVO>();
//			for(ProjInfoModel model : rows.getList()){
				PlatFormInvestorVO vo = new PlatFormInvestorVO();
                vo.setId("15481");
                vo.setInvestAmountDisplay("2000 万元 人民币");
                vo.setInvestorId("10755");
                vo.setInvestorName("何金彬");
                vo.setPlatId(pkId);
                vo.setRatio("15%");
//				BeanUtils.copyProperties(vo, model);
				list.add(vo);

			PlatFormInvestorVO vo1 = new PlatFormInvestorVO();
			vo1.setId("15482");
			vo1.setInvestAmountDisplay("1012 万元 人民币");
			vo1.setInvestorId("10756");
			vo1.setInvestorName("马晓杰");
			vo1.setPlatId(pkId);
			vo1.setRatio("10.12%");
                list.add(vo1);
//			}

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

