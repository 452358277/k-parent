package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;
import com.founder.uim.util.Encrypt;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.bean.AdminInvestSearchBean;
import com.ppmg.ei.dto.AdminInvestDTO;
import com.ppmg.ei.model.AdminInvestModel;
import com.ppmg.ei.model.AdminListModel;
import com.ppmg.ei.model.AdminModel;
import com.ppmg.ei.model.AppUserModel;
import com.ppmg.ei.service.AdminService;
import com.ppmg.ei.service.AppUserService;
import com.ppmg.ei.vo.AdminInvestVO;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 出资人代表库
 * @author null
 * @date 2019-08-13 10:53 
 */
@RestController
@Scope("prototype")
@Api(tags={"出资人代表库接口"})
@RequestMapping("adminInvest")
public class AdminInvestController extends BaseController{

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference(check = false)
	private AdminService adminService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@Reference(check = false)
	private AppUserService appUserService;

	@ApiOperation(value="出资人代表库列表", notes="出资人代表库列表")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String list(@ModelAttribute AdminInvestSearchBean searchBean){
		try {
			searchBean.setIsRep("rep");
			PageInfo<AdminInvestModel> rows = adminService.selectAdminInvestPage(searchCondition,searchBean);
			List<AdminInvestVO> list = new ArrayList<AdminInvestVO>();
			for(AdminInvestModel model : rows.getList()){
				AdminInvestVO vo = new AdminInvestVO();
				BeanUtils.copyProperties(model, vo);
				if(StringUtils.isNotBlank(model.getIsCoo())) {
					vo.setIsCooName(codeUtils.getCodeNameByValue("102", model.getIsCoo()) == null ? "" : codeUtils.getCodeNameByValue("102", model.getIsCoo()));
				}
				if(StringUtils.isNotBlank(model.getOrg())) {
					vo.setOrgName(codeUtils.getCodeNameByValue("203", model.getOrg()) == null ? "" : codeUtils.getCodeNameByValue("203", model.getOrg()));
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

	@ApiOperation(value = "新增出资人代表库", notes = "根据对象")
	@ApiImplicitParam(name = "dto", value = "出资人代表库", required = true, dataType = "AdminInvestDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(@RequestBody AdminInvestDTO dto){
		try {
			if(StringUtils.isNotEmpty(dto.getAdminName())){
				AdminModel admin=new AdminModel();
				admin.setAdminName(dto.getAdminName());
				List<AdminModel> list= adminService.getListInfo(admin);
				if(list!=null&& !list.isEmpty()){
					if("rep".equals(list.get(0).getIsRep())){
						baseResponse.setMsg(dto.getAdminName()+"已被出资人代表占用");
					}else{
						baseResponse.setMsg(dto.getAdminName()+"已被基金管理人占用");
					}

					baseResponse.setStatus("0");
					return JSONObject.toJSONString(baseResponse, SerializerFeature.WriteMapNullValue);
				}
			}
			long userId = this.getLoginUser().getUserId();
			AdminModel model = new AdminModel();
			BeanUtils.copyProperties(dto, model);
			model.setIsRep("rep");
			model.setUpdateBy(String.valueOf(userId));
			model.setCreateBy(String.valueOf(userId));
			String password = Encrypt.DataEncrypt("888888");
			adminService.insertAdminInvestModel(model,password);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			baseResponse.error(e.getMessage());
		}
		return JSONObject.toJSONString(baseResponse, SerializerFeature.WriteMapNullValue);
	}

	@ApiOperation(value="获取出资人代表库详细信息", notes="根据url的id来获取出资人代表库详细信息")
	@ApiImplicitParam(name = "adminId", value = "出资人代表库主键", required = true, dataType = "String")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/detail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detail(String adminId){
		try {
			AdminModel sqlModel = new AdminModel();
			sqlModel.setAdminId(adminId);
			AdminModel model = adminService.selectById(adminId);
			if(model!=null){
				AdminInvestVO vo = new AdminInvestVO();
				BeanUtils.copyProperties(model,vo );
				dataResponse.setData(vo);
			}else{
				dataResponse.setData(null);
			}

		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataResponse.error(e.getMessage());
		} catch (Exception e) {
			dataResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteMapNullValue);
	}

	@ApiOperation(value = "更新出资人代表库", notes = "根据对象")
	@ApiImplicitParam(name = "dto", value = "出资人代表库", required = true, dataType = "AdminInvestDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update(@RequestBody AdminInvestDTO dto){
		try {
			if(StringUtils.isNotEmpty(dto.getAdminName())){
				AdminModel moStr=adminService.selectById(dto.getAdminId());
				if(moStr!=null&& !dto.getAdminName().equals(moStr.getAdminName())){
					AdminModel admin=new AdminModel();
					admin.setAdminName(dto.getAdminName());
					List<AdminListModel> list= adminService.selectList(admin);
					if(list!=null&& !list.isEmpty()){
						if("reg".equals(list.get(0).getIsRep())){
							baseResponse.setMsg(dto.getAdminName()+"已被出资人代表占用");
						}else{
							baseResponse.setMsg(dto.getAdminName()+"已被基金管理人占用");
						}
						baseResponse.setStatus("0");
						return JSONObject.toJSONString(baseResponse, SerializerFeature.WriteMapNullValue);
					}
				}

			}
			long userId = this.getLoginUser().getUserId();
			AdminModel model = new AdminModel();
			BeanUtils.copyProperties(dto, model);
			model.setUpdateBy(String.valueOf(userId));
			//查询用户原来的密码
			String password ="";
			if(StringUtils.isNotEmpty(dto.getUserId())){
				AppUserModel appModel=appUserService.selectById(dto.getUserId());
				if(appModel!=null){
					password=appModel.getPassword();
				}
			}else{
				password = Encrypt.DataEncrypt("888888");
			}

			adminService.updateAdminInvestModel(model,password);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			baseResponse.error(e.getMessage());
		}
		return JSONObject.toJSONString(baseResponse, SerializerFeature.WriteMapNullValue);
	}

	@ApiOperation(value="删除出资人代表库", notes="删除出资人代表库")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "adminIds", value = "主键拼接", required = true, dataType = "String"),
	})
	@GetMapping(value = "/delete", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(String adminIds){

		try {
			String[] idsArray = adminIds.split(",");
			adminService.deleteBatchAdminInvestModel(idsArray);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse, SerializerFeature.WriteMapNullValue);

	}

}

