package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;
import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.model.AppFuncPermissionModel;
import com.ppmg.ei.model.ProjInfoModel;
import com.ppmg.ei.service.AppFuncPermissionService;
import com.ppmg.ei.service.ProjInfoService;
import com.ppmg.ei.vo.AppFuncPermissionListVO;
import com.ppmg.ei.vo.ButtonsVO;
import com.ppmg.ei.vo.FuncPermissionMetaVO;
import com.ppmg.ei.vo.FuncPermissionVO;
import io.swagger.annotations.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@Scope("prototype")
@Api(tags={"功能权限菜单接口"})
public class AppFuncPermissionController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference(check = false ,timeout = 200000 )
	private AppFuncPermissionService appFuncPermissionService;

	@Reference(check = false,timeout = 200000 )
	private ProjInfoService projInfoService;

	@ApiOperation(value="获取子基金详情功能权限菜单列表", notes="获取子基金详情功能权限菜单列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "fatherid", value = "父菜单ID", required = true, dataType = "String"),
		@ApiImplicitParam(name = "projId", value = "项目ID", required = true, dataType = "String")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/appFuncPermissionList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String appFuncPermissionList(@RequestParam("fatherid") String fatherid, @RequestParam("projId") String projId){

		try {
			ProjInfoModel proj = projInfoService.getProjBaseInfoById(projId);
			String groupId = "";
			if(proj != null){
				groupId = proj.getGroupId().toString();
			}
			List<AppFuncPermissionModel> rows;
			if("18".equals(groupId)){
				rows = appFuncPermissionService.getAppFuncPermissionByFatherIdXx(fatherid);
			}else{
				rows = appFuncPermissionService.getAppFuncPermissionByFatherId(fatherid);
			}
			List<AppFuncPermissionListVO> list = new ArrayList<AppFuncPermissionListVO>();
			for(AppFuncPermissionModel model : rows){
				AppFuncPermissionListVO vo = new AppFuncPermissionListVO();
				BeanUtils.copyProperties(vo, model);
				List<AppFuncPermissionModel> child;
				if("18".equals(groupId)){
					child = appFuncPermissionService.getAppFuncPermissionByFatherIdXx(String.valueOf(model.getId()));
				}else{
					child = appFuncPermissionService.getAppFuncPermissionByFatherId(String.valueOf(model.getId()));
				}
				List<AppFuncPermissionListVO> childList = new ArrayList<AppFuncPermissionListVO>();
				for(AppFuncPermissionModel childModel : child){
					AppFuncPermissionListVO childVo = new AppFuncPermissionListVO();
					BeanUtils.copyProperties(childVo, childModel);
					childList.add(childVo);
				}
				vo.setChildList(childList);
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

	@ApiOperation(value="获取平台管理详情功能权限菜单列表", notes="获取平台管理详情功能权限菜单列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "fatherid", value = "父菜单ID", required = true, dataType = "String"),
			@ApiImplicitParam(name = "groupId", value = "平台代码", required = true, dataType = "String")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/platFuncPermissionList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String platFuncPermissionList(@RequestParam("fatherid") String fatherid, @RequestParam("groupId") String groupId){

		try {
			List<AppFuncPermissionModel> rows;
			String userId = this.getLoginUserId();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("FATHERID", fatherid);
			params.put("USER_ID", userId);
			params.put("PLAT_CODE", groupId);

			if("6".equals(groupId)){
				rows = appFuncPermissionService.getPlatFuncPermissionByFatherIdYhkg(params);
			}else{
				rows = appFuncPermissionService.getPlatFuncPermissionByFatherId(params);
			}
			List<AppFuncPermissionListVO> list = new ArrayList<AppFuncPermissionListVO>();
			for(AppFuncPermissionModel model : rows){
				AppFuncPermissionListVO vo = new AppFuncPermissionListVO();
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


	@ApiOperation(value="根据登录用户获取权限", notes="获取根据登录用户获取权限菜单列表")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/selectJcPermissionList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectJcPermissionList(String pId){

		try {
			String userId=this.getLoginUserId();
			//String userId="1005000";
			List<FuncPermissionVO> funcPermissions = new ArrayList<>();
          //根据用户查询用户对应的菜单
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("FATHERID", pId);
			params.put("USER_ID", userId);
            //查询菜单

			List<Map<String,Object>> list=appFuncPermissionService.getPlatFuncPermissionByFatherIdJc(params);
            if(list!=null&& list.size()>0){
             for(int i=0;i<list.size();i++){
				 List<FuncPermissionVO> children = new ArrayList<FuncPermissionVO>();
				 String funId = String.valueOf(list.get(i).get("FUNID"));
				 String component = String.valueOf(list.get(i).get("COMPONENT"));
				 Long fatherId =null;
				 if(list.get(i).get("FATHERID")!=null){
					  fatherId = Long.parseLong(String.valueOf(list.get(i).get("FATHERID")));
				 }
				 String funcType = String.valueOf(list.get(i).get("FUNCTYPE"));
				 if(list.get(i).get("ID")!=null){
					 Long id = Long.parseLong(String.valueOf(list.get(i).get("ID")));
				 }
				 String name = String.valueOf(list.get(i).get("NAME"));
				 String path = String.valueOf(list.get(i).get("PATH"));
				 String redirect = String.valueOf(list.get(i).get("REDIRECT"));
				 String icon = String.valueOf(list.get(i).get("ICON"));
				 String title = String.valueOf(list.get(i).get("TITLE"));
				 FuncPermissionVO funcPermissionVO = new FuncPermissionVO();
				 funcPermissionVO.setPath(path);
				 funcPermissionVO.setComponent("Layout");
				 funcPermissionVO.setRedirect(redirect);
				 funcPermissionVO.setAlwaysshow(true);
				 funcPermissionVO.setFatherId(fatherId);
				 funcPermissionVO.setFuncType(funcType);
				 FuncPermissionVO funcPermissionChildrenVO = new FuncPermissionVO();
				 // 子类meta里的数据
				 FuncPermissionMetaVO funcPermissionChildrenMetaVO = new FuncPermissionMetaVO();
				 funcPermissionChildrenMetaVO.setTitle(name);
				 funcPermissionChildrenMetaVO.setIcon(icon);
				 funcPermissionChildrenMetaVO.setNoCache(false);
				 if(StringUtils.isNotEmpty(path)){
					 funcPermissionChildrenVO.setPath(path.replaceFirst("/",""));
					 //funcPermissionChildrenVO.setPath(path.replaceAll("/",""));
				 }
				 funcPermissionChildrenVO.setAlwaysshow(false);
				 funcPermissionChildrenVO.setComponent(component);
				 funcPermissionChildrenVO.setName(component);
				 funcPermissionChildrenVO.setMeta(funcPermissionChildrenMetaVO);
				 children.add(funcPermissionChildrenVO);
				 funcPermissionVO.setChildren(children);
				 Map<String, Object> params1 = new HashMap<String, Object>();
				 params1.put("FATHERID", funId);
				 params1.put("USER_ID",userId);
				 //params1.put("USER_ID", "1005000");
				 //查询第一级菜单下的按钮
				 List<Map<String,Object>> buttonsList=appFuncPermissionService.selectButtenList(params1);
				 List<ButtonsVO> buttons=new ArrayList<ButtonsVO>();
				 if(buttonsList!=null&&buttonsList.size()>0){
				 	for(int cc=0;cc<buttonsList.size();cc++){
						String code = String.valueOf(buttonsList.get(cc).get("ID"));
						String fundName = String.valueOf(buttonsList.get(cc).get("NAME"));
						ButtonsVO button=new ButtonsVO();
						button.setCode(code);
						button.setName(fundName);
						buttons.add(button);
					}
				 }
				 funcPermissionChildrenMetaVO.setButtons(buttons);

				 List<Map<String,Object>> listMap=appFuncPermissionService.getPlatFuncPermissionByFatherIdJc(params1);
				 List<FuncPermissionVO> children2;
				if(listMap.size()>0){
					children2 = new ArrayList<FuncPermissionVO>();
					funcPermissionChildrenVO.setChildren(children2);
				}
				 funcPermissions.add(funcPermissionVO);
				 //把子类的值存到类中
				 if(listMap.size()>0){
					 for(int c=0;c<listMap.size();c++){
						 List<FuncPermissionVO> children1 = new ArrayList<>();
						 String funId1 = String.valueOf(listMap.get(c).get("FUNID"));
						 String component1 = String.valueOf(listMap.get(c).get("COMPONENT"));
					/*	 Long fatherId1 =null;
						 if(listMap.get(c).get("FATHERID")!=null){
							 fatherId1 = Long.parseLong(String.valueOf(listMap.get(c).get("FATHERID")));
						 }*/
						/* String funcType1 = String.valueOf(listMap.get(c).get("FUNCTYPE"));
						 if(listMap.get(c).get("ID")!=null){
							 Long id = Long.parseLong(String.valueOf(listMap.get(c).get("ID")));
						 }*/
						 String name1 = String.valueOf(listMap.get(c).get("NAME"));
						 String path1 = String.valueOf(listMap.get(c).get("PATH"));
						// String redirect1 = String.valueOf(listMap.get(c).get("REDIRECT"));
						 String icon1 = String.valueOf(listMap.get(c).get("ICON"));
						 //String title1 = String.valueOf(listMap.get(c).get("TITLE"));
						 FuncPermissionVO funcPermissionChildrenVO1 = new FuncPermissionVO();
						 if(StringUtils.isNotEmpty(path1)){
						/*	 funcPermissionChildrenVO1.setPath(path1.replaceAll("/",""));
							 funcPermissionChildrenVO1.setComponent(path1.replaceAll("/",""));*/
							 funcPermissionChildrenVO1.setComponent(component1);
							 funcPermissionChildrenVO1.setPath(path1.replaceFirst("/",""));
							 funcPermissionChildrenVO1.setName(path1.replaceFirst("/",""));
						 }
						 // meta里的数据
						 FuncPermissionMetaVO funcPermissionChildrenMetaVO1 = new FuncPermissionMetaVO();
						 funcPermissionChildrenMetaVO1.setTitle(name1);
						 funcPermissionChildrenMetaVO1.setIcon(icon1);
						 funcPermissionChildrenMetaVO1.setNoCache(false);
						 funcPermissionChildrenVO1.setMeta(funcPermissionChildrenMetaVO1);
						  funcPermissionChildrenVO1.setChildren(children1);
						 Map<String, Object> params2 = new HashMap<String, Object>();
						 params2.put("FATHERID", funId1);
						 params2.put("USER_ID",userId);
						 //查询第二级菜单下的按钮
						 List<Map<String,Object>> buttonTwoList=appFuncPermissionService.selectButtenList(params2);
						 List<ButtonsVO> buttonTwoVO=new ArrayList<ButtonsVO>();
						 if(buttonTwoList!=null&&buttonTwoList.size()>0){
							 for(int aa=0;aa<buttonTwoList.size();aa++){
								 String code = String.valueOf(buttonTwoList.get(aa).get("ID"));
								 String fundName = String.valueOf(buttonTwoList.get(aa).get("NAME"));
								 ButtonsVO bu=new ButtonsVO();
								 bu.setCode(code);
								 bu.setName(fundName);
								 buttonTwoVO.add(bu);
							 }
						 }
						 funcPermissionChildrenMetaVO1.setButtons(buttonTwoVO);
						 //children.add(funcPermissionChildrenVO1);
						 //children.add(funcPermissionChildrenVO1);
						 //funcPermissionVO.setChildren(children1);
						 // funcPermissionVO.getChildren().add(funcPermissionChildrenVO1);//ok
						 //children.get(i).setChildren();
						 // children.add(funcPermissionChildrenVO1);
						 funcPermissions.get(i).getChildren().add(funcPermissionChildrenVO1);
					 }
					 //给父类的meta赋值
					 funcPermissions.get(i).setMeta( funcPermissions.get(i).getChildren().get(0).getMeta());
					 //在children中包含父类和子类的，去除第一个只保留子类的。
					 funcPermissions.get(i).getChildren().remove(0);
				 }
			 }
			}
			mapResponse.put("funcPermissions",funcPermissions);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			mapResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			mapResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);
	}


	@ApiOperation(value="获取项目管理详情功能权限菜单列表", notes="获取项目管理详情功能权限菜单列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "fatherid", value = "父菜单ID", required = true, dataType = "String")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/projectFuncPermissionList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String projectFuncPermissionList(@RequestParam("fatherid") String fatherid){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("FATHERID", fatherid);
			params.put("USER_ID",this.getLoginUserId());
			//params.put("USER_ID", "1005000");
			List<AppFuncPermissionModel> rows = appFuncPermissionService.getAppFuncPermissionByFatherId1(params);
			List<AppFuncPermissionListVO> list = new ArrayList<AppFuncPermissionListVO>();
			for(AppFuncPermissionModel model : rows){
				AppFuncPermissionListVO vo = new AppFuncPermissionListVO();
				BeanUtils.copyProperties(vo, model);
				//查询第一级菜单下的按钮
				Map<String, Object> params3 = new HashMap<String, Object>();
				params3.put("FATHERID", String.valueOf(model.getId()));
				params3.put("USER_ID",this.getLoginUserId());
				List<Map<String,Object>> buttonsList=appFuncPermissionService.selectButtenList(params3);
				List<ButtonsVO> buttons=new ArrayList<ButtonsVO>();
				if(buttonsList!=null&&buttonsList.size()>0){
					for(int cc=0;cc<buttonsList.size();cc++){
						String code = String.valueOf(buttonsList.get(cc).get("ID"));
						String fundName = String.valueOf(buttonsList.get(cc).get("NAME"));
						ButtonsVO button=new ButtonsVO();
						button.setCode(code);
						button.setName(fundName);
						buttons.add(button);
					}
				}
				vo.setButtons(buttons);
				Map<String, Object> params2 = new HashMap<String, Object>();
				params2.put("FATHERID", String.valueOf(model.getId()));
				params2.put("USER_ID",this.getLoginUserId());
				List<AppFuncPermissionModel> child = appFuncPermissionService.getAppFuncPermissionByFatherId1(params2);
				List<AppFuncPermissionListVO> childList = new ArrayList<AppFuncPermissionListVO>();
				for(AppFuncPermissionModel childModel : child){
					AppFuncPermissionListVO childVo = new AppFuncPermissionListVO();
					BeanUtils.copyProperties(childVo, childModel);
					//查询第二级级菜单下的按钮
					Map<String, Object> params22 = new HashMap<String, Object>();
					params22.put("FATHERID", String.valueOf(childModel.getId()));
					params22.put("USER_ID",this.getLoginUserId());
					List<Map<String,Object>> buttonsList1=appFuncPermissionService.selectButtenList(params22);
					List<ButtonsVO> buttons1=new ArrayList<ButtonsVO>();
					if(buttonsList1!=null&&buttonsList1.size()>0){
						for(int cc=0;cc<buttonsList1.size();cc++){
							String code = String.valueOf(buttonsList1.get(cc).get("ID"));
							String fundName = String.valueOf(buttonsList1.get(cc).get("NAME"));
							ButtonsVO button1=new ButtonsVO();
							button1.setCode(code);
							button1.setName(fundName);
							buttons1.add(button1);
						}
					}
					childVo.setButtons(buttons1);
					if(!"2".equals(childModel.getFptype())){
						childList.add(childVo);
					}

				}
				vo.setChildList(childList);
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

	@ApiOperation(value="根据登录用户获取权限", notes="获取根据登录用户获取权限菜单列表")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/selectJcPermissionList1", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectJcPermissionList1(String fatherIds,String serviceId){
		try {
			serviceId = "778";
			fatherIds = "601";
			List<FuncPermissionVO> funcPermissions = new ArrayList<>();
			//根据用户查询用户对应的菜单
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("fatherId", fatherIds);
			params.put("serviceId", serviceId);
			params.put("USER_ID", "1200119390");
			//查询菜单

			List<Map<String,Object>> list=appFuncPermissionService.selectMenuTree(params);
			if(list!=null&& list.size()>0){
				for(int i=0;i<list.size();i++){
					List<FuncPermissionVO> children = new ArrayList<FuncPermissionVO>();
					String funId = String.valueOf(list.get(i).get("FUNID"));
					String component = String.valueOf(list.get(i).get("COMPONENT"));
					Long fatherId =null;
					if(list.get(i).get("FATHERID")!=null){
						fatherId = Long.parseLong(String.valueOf(list.get(i).get("FATHERID")));
					}
					String funcType = String.valueOf(list.get(i).get("FUNCTYPE"));
					if(list.get(i).get("ID")!=null){
						Long id = Long.parseLong(String.valueOf(list.get(i).get("ID")));
					}
					String name = String.valueOf(list.get(i).get("NAME"));
					String path = String.valueOf(list.get(i).get("PATH"));
					String redirect = String.valueOf(list.get(i).get("REDIRECT"));
					String icon = String.valueOf(list.get(i).get("ICON"));
					String title = String.valueOf(list.get(i).get("TITLE"));
					FuncPermissionVO funcPermissionVO = new FuncPermissionVO();
					funcPermissionVO.setPath(path);
					funcPermissionVO.setComponent("Layout");
					funcPermissionVO.setRedirect(redirect);
					funcPermissionVO.setAlwaysshow(false);
					funcPermissionVO.setFatherId(fatherId);
					funcPermissionVO.setFuncType(funcType);
					funcPermissionVO.setName(component);
					FuncPermissionVO funcPermissionChildrenVO = new FuncPermissionVO();
					// 子类meta里的数据
					FuncPermissionMetaVO funcPermissionChildrenMetaVO = new FuncPermissionMetaVO();
					funcPermissionChildrenMetaVO.setTitle(name);
					funcPermissionChildrenMetaVO.setIcon(icon);
					funcPermissionChildrenMetaVO.setNoCache(false);
					if(StringUtils.isNotEmpty(path)){
						String paths = path.replaceAll("/","");
						funcPermissionChildrenVO.setPath(paths);
					}
					funcPermissionChildrenVO.setAlwaysshow(false);
					funcPermissionChildrenVO.setComponent(component);
					funcPermissionChildrenVO.setName(component);
					funcPermissionChildrenVO.setMeta(funcPermissionChildrenMetaVO);
					children.add(funcPermissionChildrenVO);
					funcPermissionVO.setChildren(children);
					Map<String, Object> params1 = new HashMap<String, Object>(20);
					params1.put("fatherId", funId);
					params1.put("USER_ID","1200119390");
					//params1.put("USER_ID", "1005000");
					//查询第一级菜单下的按钮


					List<Map<String,Object>> listMap=appFuncPermissionService.selectMenuTree(params1);
					List<FuncPermissionVO> children2;
					if(listMap.size()>0){
						children2 = new ArrayList<FuncPermissionVO>();
						funcPermissionChildrenVO.setChildren(children2);
					}
					funcPermissions.add(funcPermissionVO);
					FuncPermissionMetaVO meta = funcPermissions.get(i).getChildren().get(0).getMeta();
					FuncPermissionMetaVO newMeta = new FuncPermissionMetaVO();
					if(meta!=null){
						newMeta.setTitle(meta.getTitle());
						newMeta.setIcon(meta.getIcon());
						newMeta.setNoCache(meta.getNoCache());
						newMeta.setButtons(meta.getButtons());
						newMeta.setRoles(meta.getRoles());
					}
					//给父类的meta赋值
					//funcPermissions.get(i).setMeta(newMeta);
					//把子类的值存到类中
					if(listMap.size()>0){
						for(int c=0;c<listMap.size();c++){
							List<FuncPermissionVO> children1 = new ArrayList<>();
							String funId1 = String.valueOf(listMap.get(c).get("FUNID"));
							String component1 = String.valueOf(listMap.get(c).get("COMPONENT"));
							Long fatherId1 =null;
							if(listMap.get(c).get("FATHERID")!=null){
								fatherId1 = Long.parseLong(String.valueOf(listMap.get(c).get("FATHERID")));
							}
							String funcType1 = String.valueOf(listMap.get(c).get("FUNCTYPE"));
							if(listMap.get(c).get("ID")!=null){
								Long id = Long.parseLong(String.valueOf(listMap.get(c).get("ID")));
							}
							String name1 = String.valueOf(listMap.get(c).get("NAME"));
							String path1 = String.valueOf(listMap.get(c).get("PATH"));
							String redirect1 = String.valueOf(listMap.get(c).get("REDIRECT"));
							String icon1 = String.valueOf(listMap.get(c).get("ICON"));
							String title1 = String.valueOf(listMap.get(c).get("TITLE"));
							FuncPermissionVO funcPermissionChildrenVO1 = new FuncPermissionVO();
							// funcPermissionChildrenVO1.setPath(path1);
							if(StringUtils.isNotEmpty(path1)){
								funcPermissionChildrenVO1.setPath(path1.replaceAll("/","")+"/"+serviceId);
								funcPermissionChildrenVO1.setComponent(path1.replaceAll("/",""));
								funcPermissionChildrenVO1.setName(path1.replaceAll("/",""));
							}
							// meta里的数据
							FuncPermissionMetaVO funcPermissionChildrenMetaVO1 = new FuncPermissionMetaVO();
							funcPermissionChildrenMetaVO1.setTitle(name1);
							funcPermissionChildrenMetaVO1.setIcon(icon1);
							funcPermissionChildrenMetaVO1.setNoCache(false);
							funcPermissionChildrenVO1.setMeta(funcPermissionChildrenMetaVO1);
							funcPermissionChildrenVO1.setChildren(children1);
							funcPermissionChildrenVO1.setFatherId(fatherId1);
							funcPermissionChildrenVO1.setFuncType(funcType1);

							//查询第二级菜单下的按钮

							funcPermissions.get(i).getChildren().add(funcPermissionChildrenVO1);
						}

						//在children中包含父类和子类的，去除第一个只保留子类的。
						funcPermissions.get(i).getChildren().remove(0);
					}
				}
			}
			mapResponse.put("funcPermissions",funcPermissions);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			mapResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			mapResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);
	}

}

