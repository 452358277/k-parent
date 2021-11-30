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
import com.ppmg.ei.bean.AdminSearchBean;
import com.ppmg.ei.dto.AdminAssetDTO;
import com.ppmg.ei.dto.AdminDTO;
import com.ppmg.ei.dto.AdminPartnerDTO;
import com.ppmg.ei.model.AdminAssetModel;
import com.ppmg.ei.model.AdminListModel;
import com.ppmg.ei.model.AdminModel;
import com.ppmg.ei.model.AdminPartnerModel;
import com.ppmg.ei.service.AdminService;
import com.ppmg.ei.vo.AdminAssetVO;
import com.ppmg.ei.vo.AdminListVO;
import com.ppmg.ei.vo.AdminPartnerVO;
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
 * 描述 [Controller] 
 * @author null
 * @date 2019-08-13 10:53 
 */
@RestController
@Scope("prototype")
@Api(tags={"基金管理人库接口"})
@RequestMapping("admin")
public class AdminController extends BaseController{

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private AdminService adminService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;


	@ApiOperation(value="基金管理人库列表", notes="基金管理人库列表")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String list(@ModelAttribute AdminSearchBean searchBean){
		try {
			PageInfo<AdminListModel> rows = adminService.selectPage(searchCondition,searchBean);
			List<AdminListVO> list = new ArrayList<AdminListVO>();
			for(AdminListModel model : rows.getList()){
				//基金管理人库信息
				AdminListVO vo = new AdminListVO();
				BeanUtils.copyProperties(model,vo );
				if(StringUtils.isNotBlank(model.getIsCoo())) {
					vo.setIsCooName(codeUtils.getCodeNameByValue("102", model.getIsCoo()) == null ? "" : codeUtils.getCodeNameByValue("102", model.getIsCoo()));
				}
				if(StringUtils.isNotBlank(model.getOrg())) {
					vo.setOrgName(codeUtils.getCodeNameByValue("203", model.getOrg()) == null ? "" : codeUtils.getCodeNameByValue("203", model.getOrg()));
				}
				if(model.getAdminAssetModel()!=null){
					AdminAssetModel adminAssetModel = model.getAdminAssetModel();
					//资产信息
					AdminAssetVO adminAssetVO = new AdminAssetVO();
					BeanUtils.copyProperties(adminAssetModel,adminAssetVO );
					vo.setAdminAssetVO(adminAssetVO);
				}
                if(model.getAdminPartnerModels()!=null){
					List<AdminPartnerModel> adminPartnerModels = model.getAdminPartnerModels();
					//股东信息
					List<AdminPartnerVO> adminPartnerVOs = new ArrayList<>();
					if(adminPartnerModels!=null&&adminPartnerModels.size()>0){
						for (AdminPartnerModel partnerModel:adminPartnerModels) {
							AdminPartnerVO partnerVO = new AdminPartnerVO();
							BeanUtils.copyProperties(partnerModel,partnerVO );
							if(partnerModel.getPartnerType() != null && StringUtils.isNotBlank(partnerModel.getPartnerType())){
								partnerVO.setPartnerType(Long.valueOf(partnerModel.getPartnerType()));
								partnerVO.setPartnerTypeName(codeUtils.getCodeNameByValue("552", partnerModel.getPartnerType()) == null ? "" : codeUtils.getCodeNameByValue("552", partnerModel.getPartnerType()));
							}
							if(partnerModel.getPartnerPro() != null && StringUtils.isNotBlank(partnerModel.getPartnerPro())){
								partnerVO.setPartnerPro(Long.valueOf(partnerModel.getPartnerPro()));
								partnerVO.setPartnerProName(codeUtils.getCodeNameByValue("9015", partnerModel.getPartnerPro()) == null ? "" : codeUtils.getCodeNameByValue("9015", partnerModel.getPartnerPro()));
							}
							adminPartnerVOs.add(partnerVO);
						}
					}

					vo.setAdminPartnerVOS(adminPartnerVOs);
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

	@ApiOperation(value = "新增基金管理人库", notes = "根据对象")
	@ApiImplicitParam(name = "dto", value = "基金管理人库", required = true, dataType = "AdminDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(@RequestBody AdminDTO dto){

		try {
			//查询基金管理人是否存在
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

			AdminAssetDTO adminAssetDTO = dto.getAdminAssetDTO();
			List<AdminPartnerDTO> adminPartnerDTOS = dto.getAdminPartnerDTOS();
			long userId = this.getLoginUser().getUserId();
			AdminModel model = new AdminModel();
			BeanUtils.copyProperties(dto, model);
			model.setUpdateBy(String.valueOf(userId));
			model.setCreateBy(String.valueOf(userId));
			AdminAssetModel adminAssetModel = new AdminAssetModel();
			if(adminAssetDTO!=null){
				BeanUtils.copyProperties(adminAssetDTO,adminAssetModel);
				adminAssetModel.setCreateBy(String.valueOf(userId));
				adminAssetModel.setUpdateBy(String.valueOf(userId));
			}
			List<AdminPartnerModel> adminPartnerModels = new ArrayList<>();
			if(adminPartnerDTOS!=null&&adminPartnerDTOS.size()>0){
				for (AdminPartnerDTO partnerDTO:adminPartnerDTOS) {
					AdminPartnerModel adminPartnerModel = new AdminPartnerModel();
					BeanUtils.copyProperties(partnerDTO,adminPartnerModel);
					adminPartnerModel.setCreateBy(String.valueOf(userId));
					adminPartnerModel.setUpdateBy(String.valueOf(userId));
					adminPartnerModels.add(adminPartnerModel);
				}
			}
			String password = Encrypt.DataEncrypt("888888");
			model.setPassword(password);
			adminService.insertModel(model,adminAssetModel,adminPartnerModels);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			baseResponse.error(e.getMessage());
		}
		return JSONObject.toJSONString(baseResponse, SerializerFeature.WriteMapNullValue);
	}

	@ApiOperation(value="获取基金管理人库详细信息", notes="根据url的id来获取基金管理人库详细信息")
	@ApiImplicitParam(name = "adminId", value = "基金管理人库主键", required = true, dataType = "String")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/detail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detail(String adminId){
		try {
			AdminModel sqlModel = new AdminModel();
			sqlModel.setAdminId(adminId);
			List<AdminListModel> modelList = adminService.selectListModel(sqlModel);
			if(modelList!=null&&modelList.size()>0){
				AdminListModel listModel = modelList.get(0);
				AdminAssetModel adminAssetModel = listModel.getAdminAssetModel();
				List<AdminPartnerModel> adminPartnerModels = listModel.getAdminPartnerModels();
				//基金管理人库信息
				AdminListVO vo = new AdminListVO();
				BeanUtils.copyProperties(listModel,vo );
				if(StringUtils.isNotBlank(listModel.getIsCoo())) {
					vo.setIsCooName(codeUtils.getCodeNameByValue("102", listModel.getIsCoo()) == null ? "" : codeUtils.getCodeNameByValue("102", listModel.getIsCoo()));
				}
				if(StringUtils.isNotBlank(listModel.getOrg())) {
					vo.setOrgName(codeUtils.getCodeNameByValue("203", listModel.getOrg()) == null ? "" : codeUtils.getCodeNameByValue("203", listModel.getOrg()));
				}
				//资产信息
				AdminAssetVO adminAssetVO = new AdminAssetVO();
				BeanUtils.copyProperties(adminAssetModel,adminAssetVO );
				//股东信息
				List<AdminPartnerVO> adminPartnerVOs = new ArrayList<>();
				if(adminPartnerModels!=null&&adminPartnerModels.size()>0){
					for (AdminPartnerModel partnerModel:adminPartnerModels) {
						AdminPartnerVO partnerVO = new AdminPartnerVO();
						BeanUtils.copyProperties(partnerModel,partnerVO );
						adminPartnerVOs.add(partnerVO);
					}
				}

				vo.setAdminAssetVO(adminAssetVO);
				vo.setAdminPartnerVOS(adminPartnerVOs);
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

	@ApiOperation(value = "更新基金管理人库", notes = "根据对象")
	@ApiImplicitParam(name = "dto", value = "基金管理人库", required = true, dataType = "AdminDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update(@RequestBody AdminDTO dto){
		try {

			if(StringUtils.isNotEmpty(dto.getAdminId())){
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
			AdminAssetDTO adminAssetDTO = dto.getAdminAssetDTO();
			List<AdminPartnerDTO> adminPartnerDTOS = dto.getAdminPartnerDTOS();
			long userId = this.getLoginUser().getUserId();
			AdminModel model = new AdminModel();
			BeanUtils.copyProperties(dto, model);
			model.setUpdateBy(String.valueOf(userId));
			AdminAssetModel adminAssetModel = new AdminAssetModel();
			if(adminAssetDTO != null){
				BeanUtils.copyProperties(adminAssetDTO,adminAssetModel);
				adminAssetModel.setUpdateBy(String.valueOf(userId));
			}
			List<AdminPartnerModel> adminPartnerModels = new ArrayList<>();
			if(adminPartnerDTOS!=null&&adminPartnerDTOS.size()>0){
				for (AdminPartnerDTO partnerDTO:adminPartnerDTOS) {
					AdminPartnerModel adminPartnerModel = new AdminPartnerModel();
					BeanUtils.copyProperties(partnerDTO,adminPartnerModel);
					adminPartnerModel.setUpdateBy(String.valueOf(userId));
					adminPartnerModel.setCreateBy(String.valueOf(userId));
					adminPartnerModels.add(adminPartnerModel);
				}
			}
			String password = Encrypt.DataEncrypt("888888");
			model.setPassword(password);
			adminService.updateModel(model,adminAssetModel,adminPartnerModels);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			baseResponse.error(e.getMessage());
		}
		return JSONObject.toJSONString(baseResponse, SerializerFeature.WriteMapNullValue);
	}

	@ApiOperation(value="删除基金管理人库", notes="删除基金管理人库")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "adminIds", value = "主键拼接", required = true, dataType = "String"),
	})
	@GetMapping(value = "/delete", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(String adminIds){

		try {
			String[] idsArray = adminIds.split(",");
			adminService.deleteBatchModel(idsArray);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse, SerializerFeature.WriteMapNullValue);

	}

	@ApiOperation(value="删除基金管理人库", notes="删除基金管理人库")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "adminIds", value = "主键拼接", required = true, dataType = "String"),
	})
	@PostMapping(value = "/deleteByList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteByList(@RequestBody List<AdminModel> list){
		try {
			adminService.deleteByList(list);
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

