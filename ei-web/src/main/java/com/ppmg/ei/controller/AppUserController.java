package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.core.util.UimUtils;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.core.vo.BaseResponse;
import com.founder.ssm.web.common.CommonStatus;
import com.founder.uim.sso.filter.ISSOSession;
import com.founder.uim.util.Encrypt;
import com.founder.uim.util.SSOUtil;
import com.founder.uim.xdk.AppRole;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.bean.AppUserSearchBean;
import com.ppmg.ei.dto.AppUserCreateDTO;
import com.ppmg.ei.dto.AppUserUpdateDTO;
import com.ppmg.ei.dto.AppUserUpdatePasswordDTO;
import com.ppmg.ei.model.AppRoleModel;
import com.ppmg.ei.model.AppUserModel;
import com.ppmg.ei.service.AppRoleService;
import com.ppmg.ei.service.AppUserService;
import com.ppmg.ei.service.FundUserRelateService;
import com.ppmg.ei.vo.AppUserSearchVO;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 外部用户 [Controller]
 *
 * @author yuyongjun
 * @date 2019-06-24 16:17
 */
@Controller
@RequestMapping("/appUser")
@Scope("prototype")
@Api(tags = {"外部用户接口"})
public class AppUserController extends BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference(check = false)
    private AppUserService appUserService;

    @Reference(check = false)
    private FundUserRelateService fundUserRelateService;

    @Reference
    private AppRoleService appRoleService;

    @Resource(name = "codeUtils")
    private CodeUtils codeUtils;

    @ApiOperation(value = "外部用户列表", notes = "外部用户列表")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list(@RequestBody AppUserSearchBean searchBean) {
        try {
            searchCondition.setSearchBean(searchBean);
            searchCondition.addParam("CREATE_BY", this.getLoginUserId());
            PageInfo<AppUserModel> rows = appUserService.selectListPage(searchCondition);
            List<AppUserSearchVO> list = new ArrayList<>();
            for (AppUserModel model : rows.getList()) {
                AppUserSearchVO vo = new AppUserSearchVO();
                BeanUtils.copyProperties(model, vo);
                if (model.getSexy() != null) {
                    vo.setSexyName(codeUtils.getCodeNameByValue("101", model.getSexy().toString()));
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

    @ApiOperation(value = "新增外部用户信息")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String add(@RequestBody AppUserCreateDTO dto) {
        try {
            AppUserModel model = new AppUserModel();
            BeanUtils.copyProperties(dto, model);
            String password = Encrypt.DataEncrypt(model.getPassword());
            model.setPassword(password);
            String loginUserId = this.getLoginUserId();
            //String loginUserId ="1005002";
            model.setCreateBy(loginUserId);
            model.setCreateDt(new Date());
            model.setUpdateBy(loginUserId);
            model.setUpdateDt(new Date());
            AppRole[] appRoles = UimUtils.getUserRoles(10001, Long.parseLong(loginUserId));
            if (appRoles == null || appRoles.length <= 0) {
                throw new SystemException("您没有被赋予任何角色，请联系管理员");
            }
            baseResponse = appUserService.inserOutUser(model, appRoles[0].getId());
        } catch (SystemException e) {
            logger.error(e.getMessage());
            baseResponse.error(e.getMessage());
        } catch (Exception e) {
            baseResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(baseResponse, SerializerFeature.WriteMapNullValue);
    }


    @ApiOperation(value = "获取外部用户详细信息")
    @ApiImplicitParam(name = "id", value = "外部用户ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/detail/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String detail(@PathVariable(value = "id") String id) {
        try {
            AppUserModel model = appUserService.selectById(id);
            AppUserSearchVO vo = new AppUserSearchVO();
            BeanUtils.copyProperties(model, vo);
            if (model.getSexy() != null) {
                vo.setSexyName(codeUtils.getCodeNameByValue("101", model.getSexy().toString()));
            }
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

    @ApiOperation(value = "修改外部用户信息")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String update(@RequestBody AppUserUpdateDTO dto) {
        try {
            AppUserModel model = new AppUserModel();
            BeanUtils.copyProperties(dto, model);
            appUserService.update(model);
        } catch (SystemException e) {
            logger.error(e.getMessage());
            baseResponse.error(e.getMessage());
        } catch (Exception e) {
            baseResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(baseResponse, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value = "删除外部用户信息")
    @ApiImplicitParam(name = "id", value = "外部用户ID", required = true, dataType = "Long", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/delete/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delete(@PathVariable(value = "id") Long id) {
        try {
            if (String.valueOf(id).equals(this.getLoginUserId())) {
                baseResponse.setMsg("不能删除自己");
                return JSONObject.toJSONString(baseResponse);
            }
            AppUserModel model = new AppUserModel();
            model.setId(id);
            model.setUpdateBy(this.getLoginUserId());
            model.setUpdateDt(new Date());
            appUserService.deleteLogic(model);
        } catch (SystemException e) {
            logger.error(e.getMessage());
            baseResponse.error(e.getMessage());
        } catch (Exception e) {
            baseResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(baseResponse, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value = "修改外部用户密码")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/updatePassword", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updatePassword(@RequestBody AppUserUpdatePasswordDTO dto, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (StringUtils.isEmpty(dto.getNewPassword())) {
                throw new SystemException("新密码不能为空");
            }
            if (StringUtils.isEmpty(dto.getOldPassword())) {
                throw new SystemException("旧密码不能为空");
            }
            AppUserModel model = new AppUserModel();
            BeanUtils.copyProperties(dto, model);
            String password = Encrypt.DataEncrypt(dto.getNewPassword());
            String oldPassword = Encrypt.DataEncrypt(dto.getOldPassword());
            model.setPassword(password);
            model.setId(Long.valueOf(this.getLoginUserId()));
            model.setIfmodifypassword("1");
            baseResponse = appUserService.updatePassword(model, oldPassword);
            if (baseResponse.hasError()) {
                return JSONObject.toJSONString(baseResponse, SerializerFeature.WriteMapNullValue);
            }

            //强制退出已登录账号
            ISSOSession loginSsoSession = SSOUtil.getSSOSessionByUserId(model.getId().toString());
            if (loginSsoSession != null) {
                loginSsoSession.remove();
            }
            request.getSession().invalidate();
            //String authUrl = SSOFilter.getAuthUrl(request, response);
            //response.sendRedirect(authUrl);

        } catch (SystemException e) {
            logger.error(e.getMessage());
            baseResponse.error(e.getMessage());
        } catch (Exception e) {
            baseResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(baseResponse, SerializerFeature.WriteMapNullValue);
    }


    @ApiOperation(value = "查询所有外部用户-列表", notes = "外部用户列表")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/userList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String userList(@RequestBody AppUserSearchBean searchBean) {
        try {
            if(searchBean.getKeyword()!=null){
                if("".equals(searchBean.getKeyword().trim())||searchBean.getKeyword().trim()==null){
                    searchBean.setKeyword("");
                }else{
                    searchBean.setKeyword(searchBean.getKeyword().trim());
                }
            }

            if(searchBean.getFundName()!=null){
                if("".equals(searchBean.getFundName().trim())||searchBean.getFundName().trim()==null){
                    searchBean.setFundName("");
                }else{
                    searchBean.setFundName(searchBean.getFundName().trim());
                }
            }

            searchCondition.setSearchBean(searchBean);
            //PageInfo<AppUserModel> rows = appUserService.selectListPage(searchCondition);
            PageInfo<AppUserModel> rows = appUserService.selectUserListPage(searchCondition);
            List<AppUserSearchVO> list = new ArrayList<>();
            for (AppUserModel model : rows.getList()) {
                AppUserSearchVO vo = new AppUserSearchVO();
                BeanUtils.copyProperties(model, vo);
                //根据用户查询他的角色
                HashSet<String> setOnly = new HashSet<String>();
                List<AppRoleModel> listRow = appRoleService.selectByUserId(String.valueOf(model.getId()));
                if (listRow != null && listRow.size() > 0) {
                    for (AppRoleModel staffs : listRow) {
                        String value = String.valueOf(staffs.getId());
                        setOnly.add(value);
                    }
                    if(setOnly.contains("1005000")||setOnly.contains("1005001")||setOnly.contains("1005004")||setOnly.contains("1005005")||setOnly.contains("1005009")){
                     vo.setFundName("所有基金");
                    }else{
                        Map<String, Object> map= fundUserRelateService.selectFundlist(String.valueOf(model.getId()));
                        if(map!=null){
                            vo.setFundName(String.valueOf(map.get("FUNDNAME")));
                        }
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

    @ApiOperation(value = "修改外部用户信息")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/getUserInfo", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BaseResponse getUserInfo(@RequestBody AppUserUpdateDTO dto) {
        try {
            AppUserModel model = new AppUserModel();
            model.setLoginname(dto.getLoginname());
            System.out.print("*************dto.getLoginname()******************************"+dto.getLoginname());
            AppUserModel modeS=appUserService.selectBy(model);
            System.out.print("*******************************************");
            mapResponse.put("model", modeS);
        } catch (SystemException e) {
            logger.error(e.getMessage());
            baseResponse.error(e.getMessage());
        } catch (Exception e) {
            baseResponse.error();
            logger.error(e.getMessage(), e);
        }
        return mapResponse;
    }

    @ApiOperation(value = "查询USERTYPE=5的列表", notes = "外部用户列表")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/userAllList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String userAllList(@RequestBody AppUserSearchBean searchBean) {
        try {
            if(searchBean.getKeyword()!=null){
                if("".equals(searchBean.getKeyword().trim())||searchBean.getKeyword().trim()==null){
                    searchBean.setKeyword("");
                }else{
                    searchBean.setKeyword(searchBean.getKeyword().trim());
                }
            }
            searchCondition.setSearchBean(searchBean);
            PageInfo<AppUserModel> rows = appUserService.selectUserAllListPage(searchCondition);
            List<AppUserSearchVO> list = new ArrayList<>();
            for (AppUserModel model : rows.getList()) {
                AppUserSearchVO vo = new AppUserSearchVO();
                BeanUtils.copyProperties(model, vo);
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

