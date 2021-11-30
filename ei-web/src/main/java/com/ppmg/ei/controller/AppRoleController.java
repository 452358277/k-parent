package com.ppmg.ei.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.model.AppUserModel;
import com.ppmg.ei.model.AppUserroleModel;
import com.ppmg.ei.service.AppUserService;
import com.ppmg.ei.service.AppUserroleService;
import com.ppmg.ei.vo.AppRoleVO;
import com.ppmg.ei.vo.AppUserVO;
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
import com.ppmg.ei.model.AppRoleModel;
import com.ppmg.ei.service.AppRoleService;
@Controller
@Scope("prototype")
@Api(tags={"角色接口"})
public class AppRoleController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private AppRoleService appRoleService;

	@Reference
	private AppUserService appUserService;

    @Reference
    private AppUserroleService appUserroleService;

    @ApiOperation(value="角色列表", notes="角色列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "groupId", value = "平台ID", required = true, dataType = "String")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/roleList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String roleList(@RequestParam("groupId") String groupId){
		try {

			List<AppRoleModel> rows = appRoleService.getRoleListByGroupId(groupId);
			List<AppRoleVO> list = new ArrayList<AppRoleVO>();
			for(AppRoleModel model : rows){
				AppRoleVO vo = new AppRoleVO();
				BeanUtils.copyProperties(vo, model);
				if(String.valueOf(model.getId()) != null && String.valueOf(model.getId()) != ""){
                    List<AppUserModel> userModelList = appUserService.getUserListByRoleId(String.valueOf(model.getId()));
                    List<AppUserVO> userVOList = new ArrayList<AppUserVO>();
                    for(AppUserModel userModel : userModelList){
                        AppUserVO userVO = new AppUserVO();
                        BeanUtils.copyProperties(userVO, userModel);
                        userVOList.add(userVO);
                    }
                    if(userVOList.size() > 0){
                        vo.setUserList(userVOList);
                    }

				}
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
	@ApiOperation(value="获取角色详细信息", notes="根据url的id来获取角色详细信息")
	@ApiImplicitParam(name = "id", value = "roleID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
   })
	@GetMapping(value = "/roleInfoDetail/{id}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String roleInfoDetail(@PathVariable(value = "id") String id){
		try {
			AppRoleModel model = appRoleService.selectById(id);
			BaseVO vo = new BaseVO();
			BeanUtils.copyProperties(vo, model);
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

    @ApiOperation(value="更新角色用户关联信息", notes="根据url的roleId来指定更新角色用户关联信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userIds", value = "用户ID", required = true, dataType = "String"),
    })
    @PutMapping(value = "/updateUserRole", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateUserRole(@RequestParam("roleId") String roleId, @RequestParam("userIds") String userIds){

        try {
            AppUserroleModel userroleModel = new AppUserroleModel();
            userroleModel.setAppid(Long.parseLong("10001"));
            userroleModel.setRoleid(Long.parseLong(roleId));
            appUserroleService.delete(userroleModel);
            if(userIds != null && !"".equals(userIds)){
                String[] ids = userIds.split(",");
                List<AppUserroleModel> userroleModelList = new ArrayList<AppUserroleModel>();
                for(int i=0; i<ids.length; i++){
                    AppUserroleModel model = new AppUserroleModel();
                    model.setAppid(Long.parseLong("10001"));
                    model.setRoleid(Long.parseLong(roleId));
                    model.setUserid(Long.parseLong(ids[i]));
                    userroleModelList.add(model);
                }
                appUserroleService.insertBatch(userroleModelList);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);

    }

	@ApiOperation(value="查询用户对应的角色", notes="查询用户对应的角色")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "groupId", value = "平台ID", required = true, dataType = "String")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/roleList/getForUserId", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String roleList(){
		try {
			String userId=this.getLoginUserId();
			//String userId="1005000";
			List<AppRoleModel> rows=appRoleService.selectByUserId(userId);
			//List<AppRoleModel> rows = appRoleService.getRoleListByGroupId(groupId);
			List<AppRoleVO> list = new ArrayList<AppRoleVO>();
			for(AppRoleModel model : rows){
				AppRoleVO vo = new AppRoleVO();
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
}

