package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.founder.ssm.foundation.service.SerialnoService;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.bean.AdminInvestSearchBean;
import com.ppmg.ei.bean.AdminSearchBean;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/** 
 * 基金管理人库
 * @author null
 * @date 2019-08-13 10:53 
 */ 
@Component("adminService")
@Service(interfaceClass = AdminService.class)
public class AdminServiceImpl extends BaseServiceImpl<AdminModel> implements AdminService {

	public AdminServiceImpl(){
		this.setNamespace("Admin");
	}

	@Reference(check = false)
	private SerialnoService serialnoService;


	@Resource
	private AdminService adminService;
	@Resource
	private AdminAssetService assetService;
	@Resource
	private AdminPartnerService partnerService;

	@Resource
	private FundAccountService fundAccountService;

	@Resource
	private FundAccountMapingService fundAccountMapingService;

	@Resource
	private UserService userService;

	@Resource
	private AppUserroleService appUserroleService;

	@Value("${uim.appId}")
	private Long appId;
	/**
	 * 新增基金管理人库
	 * @param model 管理人信息
	 * @param assetModel 资产信息
	 * @param partnerModels 股东信息
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertModel(AdminModel model, AdminAssetModel assetModel, List<AdminPartnerModel> partnerModels) {
		//先查询appuser 是否存在该名称
		UserModel user1=new UserModel();
		user1.setLoginname(model.getAdminName());
		user1.setName(model.getAdminName());
		user1.setIsDelete("0");
		List<UserModel> listUser=userService.selectList(user1);
		String uid ="";
		if(listUser==null||listUser.isEmpty()){
			uid = serialnoService.getSequence("BASE.APP_USER");
			user1.setId(Long.parseLong(uid));
			user1.setUsertype(5L);
			user1.setPassword(model.getPassword());
			userService.insert(user1);
		}else{
			uid =String.valueOf(listUser.get(0).getId());

		}		//先查询改用户是否存下了改角色
		AppUserroleModel appUserroleModel = new AppUserroleModel();
		appUserroleModel.setAppid(appId);
		appUserroleModel.setUserid(Long.parseLong(uid));
		List<AppUserroleModel> listRole=appUserroleService.selectList(appUserroleModel);
		if(listRole==null||listRole.isEmpty()){
			appUserroleModel.setRoleid(10031);
			appUserroleService.insert(appUserroleModel);
		}
		//基金管理人信息
		String adminId=serialnoService.getSequence("BD_T_FUND_ADMIN");
		model.setAdminId(adminId);
		model.setCreateDt(new Date());
		model.setUpdateDt(new Date());
		model.setIsRep("");
		adminService.insert(model);
		//基金管理人账户
		FundAccountModel fundAccount=new FundAccountModel();
		fundAccount.setUserId(model.getAdminName());
		List<FundAccountModel> list=fundAccountService.selectList(fundAccount);
		if(list==null||list.isEmpty()){
			String pkId=serialnoService.getSequence("EI.BD_T_FUND_ACCOUNT");
			FundAccountModel moStr=new FundAccountModel();
			moStr.setUserId(model.getAdminName());
			moStr.setPkId(pkId);
			moStr.setPassword(model.getPassword());
			moStr.setCreateBy(model.getUpdateBy());
			moStr.setUpdateBy(model.getUpdateBy());
			moStr.setCreateDt(new Date());
			moStr.setUpdateDt(new Date());
			fundAccountService.insert(moStr);
			FundAccountMapingModel maping=new FundAccountMapingModel();
			maping.setPkId(pkId);
			maping.setUserId(model.getAdminName());
			maping.setCreateBy(model.getUpdateBy());
			maping.setUpdateBy(model.getUpdateBy());
			maping.setCreateDt(new Date());
			maping.setUpdateDt(new Date());
			maping.setAdminId(adminId);
			fundAccountMapingService.insert(maping);
		}
		//资产信息
		if(assetModel!=null) {
			assetModel.setAssetId(serialnoService.getSequence("BD_T_FUND_ADMIN_ASSET"));
			assetModel.setCreateDt(new Date());
			assetModel.setUpdateDt(new Date());
			assetModel.setAdminId(model.getAdminId());
			assetService.insert(assetModel);
		}
		//股东信息
		if(partnerModels!=null&&partnerModels.size()>0){
			List<AdminPartnerModel> insertModels = new ArrayList<>(partnerModels.size());
			for (AdminPartnerModel partner:partnerModels) {
				AdminPartnerModel partnerModel = new AdminPartnerModel();
				BeanUtils.copyProperties(partner,partnerModel);
				partnerModel.setCreateDt(new Date());
				partnerModel.setUpdateDt(new Date());
				partnerModel.setAdminId(model.getAdminId());
				partnerModel.setPartnerId(serialnoService.getSequence("BD_T_FUND_ADMIN_PARTNER"));
				insertModels.add(partnerModel);
			}
			partnerService.insertBatch(insertModels);
		}
	}

	/**
	 * 分页查询
	 * @param var1
	 * @param searchBean
	 * @param <E>
	 * @return
	 */
	@Override
	public <E> PageInfo<AdminListModel> selectPage(SearchCondition var1, AdminSearchBean searchBean) {
		if(searchBean.getCurrPage()<0){
			var1.setCurrPage(0);
		}else{
			var1.setCurrPage(searchBean.getCurrPage());
		}
		if(searchBean.getPageSize()<0){
			var1.setPageSize(10);
		}else{
			var1.setCurrPage(searchBean.getPageSize());
		}

		if(searchBean.getOrg() != null && StringUtils.isNotBlank(searchBean.getOrg())){
			List<String> orgs = new ArrayList<>();
			String[] array = searchBean.getOrg().split(",");
			orgs = Arrays.asList(array);
			searchBean.setOrgs(orgs);
		}
		var1.setSearchBean(searchBean);
		PageInfo<AdminListModel> listPage = adminService.selectListPage(var1);
		if(listPage.getList() != null && listPage.getList().size() > 0){
			List<String> adminIds = new ArrayList<>(listPage.getList().size());
			for (AdminListModel admin:listPage.getList()) {
				adminIds.add(admin.getAdminId());
			}
			AdminSqlModel sqlModel = new AdminSqlModel();
			sqlModel.setAdminIds(adminIds);
			//资产
			List<AdminAssetModel> assetModels = assetService.selectList("selectListByAdminId",sqlModel);
			if(assetModels != null && assetModels.size() > 0){
				for (AdminListModel admin:listPage.getList()) {
					for (AdminAssetModel asset:assetModels) {
						if(admin.getAdminId().equals(asset.getAdminId())){
							admin.setAdminAssetModel(asset);
						}
					}
				}
			}
			//股东
			List<AdminPartnerModel> partnerModels = partnerService.selectList("selectListByAdminId",sqlModel);
			if(partnerModels != null && partnerModels.size() > 0){
				for (AdminListModel admin:listPage.getList()) {
					List<AdminPartnerModel> adminPartnerModels = new ArrayList<>();
					for (AdminPartnerModel partner:partnerModels) {
						if(admin.getAdminId().equals(partner.getAdminId())){
							adminPartnerModels.add(partner);
						}
					}
					admin.setAdminPartnerModels(adminPartnerModels);
				}
			}
		}

		//int count = adminService.selectCount("selectAdminCount",var1);
		//listPage.setTotal(count);
		return listPage;
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateModel(AdminModel model, AdminAssetModel assetModel, List<AdminPartnerModel> partnerModels) {
		if(StringUtils.isNotEmpty(model.getUserId())){
			UserModel user1=new UserModel();
			user1.setLoginname(model.getAdminName());
			user1.setName(model.getAdminName());
			user1.setIsDelete("0");
			user1.setId(Long.valueOf(model.getUserId()));
			userService.update(user1);
		}

		AdminModel oldModel = adminService.selectById(model.getAdminId());
		BeanUtils.copyProperties(model,oldModel,"createBy","createDt");
		oldModel.setUpdateDt(new Date());
		oldModel.setIsRep("");
		adminService.update(oldModel);
		//
		AdminAssetModel sqlAModel = new AdminAssetModel();
		sqlAModel.setAdminId(model.getAdminId());
		List<AdminAssetModel> oldAssetModels = assetService.selectList(sqlAModel);
		if(oldAssetModels != null && oldAssetModels.size() > 0){
			AdminAssetModel oldAssetModel = oldAssetModels.get(0);
			BeanUtils.copyProperties(assetModel,oldAssetModel,"createBy","createDt","adminId","assetId");
			oldAssetModel.setUpdateDt(new Date());
			assetService.update(oldAssetModel);
		}
		//股东信息  先删除 在添加
		List<String> adminIds = new ArrayList<>();
		adminIds.add(model.getAdminId());
		partnerService.delete("deleteBatchByAdminId",adminIds);
		//重新插入股东信息
		if(partnerModels!=null&&partnerModels.size()>0){
			List<AdminPartnerModel> insertModels = new ArrayList<>(partnerModels.size());
			for (AdminPartnerModel partner:partnerModels) {
				AdminPartnerModel partnerModel = new AdminPartnerModel();
				BeanUtils.copyProperties(partner,partnerModel);
				partnerModel.setUpdateDt(new Date());
				partnerModel.setCreateDt(new Date());
				partnerModel.setAdminId(model.getAdminId());
				partnerModel.setPartnerId(serialnoService.getSequence("BD_T_FUND_ADMIN_PARTNER"));
				insertModels.add(partnerModel);
			}
			partnerService.insertBatch(insertModels);
		}
        //基金管理人账户
		FundAccountModel fundAccount=new FundAccountModel();
		fundAccount.setUserId(model.getAdminName());
		List<FundAccountModel> list=fundAccountService.selectList(fundAccount);
		if(list==null||list.isEmpty()){
			String pkId=serialnoService.getSequence("EI.BD_T_FUND_ACCOUNT");
			FundAccountModel moStr=new FundAccountModel();
			moStr.setUserId(model.getAdminName());
			moStr.setPkId(pkId);
			moStr.setPassword("111111");
			moStr.setCreateBy(model.getUpdateBy());
			moStr.setUpdateBy(model.getUpdateBy());
			moStr.setCreateDt(new Date());
			moStr.setUpdateDt(new Date());
			fundAccountService.insert(moStr);
			FundAccountMapingModel maping=new FundAccountMapingModel();
			maping.setPkId(pkId);
			maping.setUserId(model.getAdminName());
			maping.setCreateBy(model.getUpdateBy());
			maping.setUpdateBy(model.getUpdateBy());
			maping.setCreateDt(new Date());
			maping.setUpdateDt(new Date());
			maping.setAdminId(model.getAdminId());
			fundAccountMapingService.insert(maping);
		}

	}

	/**
	 * 批量删除
	 * @param adminIds
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@Override
	public void deleteBatchModel(String[] adminIds) {

		if(adminIds!=null&&adminIds.length>0){
			adminService.deleteBatch(adminIds);
			List<String> list = Arrays.asList(adminIds);
			//资产信息
			assetService.delete("deleteBatchByAdminId",list);
			//股东信息
			partnerService.delete("deleteBatchByAdminId",list);
		}

	}

	/**
	 * 查询集合
	 * @param sqlModel
	 * @return
	 */
	@Override
	public List<AdminListModel> selectListModel(AdminModel sqlModel) {
		List<AdminListModel> list = adminService.selectList(sqlModel);
		return list;
	}

	@Override
	public <E> PageInfo<AdminInvestModel> selectAdminInvestPage(SearchCondition var1, AdminInvestSearchBean searchBean) {
		if(searchBean.getCurrPage()<0){
			var1.setCurrPage(0);
		}else{
			var1.setCurrPage(searchBean.getCurrPage());
		}
		if(searchBean.getPageSize()<0){
			var1.setPageSize(1);
		}else{
			var1.setCurrPage(searchBean.getPageSize());
		}
		if(searchBean.getOrg() != null && StringUtils.isNotBlank(searchBean.getOrg())){
			List<String> orgs = new ArrayList<>();
			String[] array = searchBean.getOrg().split(",");
			orgs = Arrays.asList(array);
			searchBean.setOrgs(orgs);
		}
		var1.setSearchBean(searchBean);
		long count = adminService.selectCount("selectAdminInvestCount",var1);
		PageInfo<AdminInvestModel> listPage = adminService.selectListPage("selectAdminInvestPage",var1);
		listPage.setTotal(count);
		return listPage;
	}

	@Override
	public void insertAdminInvestModel(AdminModel model,String password) {
		//用户表
		//先查询appuser 是否存在该名称
		UserModel user1=new UserModel();
		user1.setLoginname(model.getAdminName());
		user1.setName(model.getAdminName());
		user1.setIsDelete("0");
		List<UserModel> listUser=userService.selectList(user1);
		String uid ="";
		if(listUser==null||listUser.isEmpty()){
			uid = serialnoService.getSequence("BASE.APP_USER");
			user1.setId(Long.parseLong(uid));
			user1.setUsertype(5L);
			user1.setPassword(password);
			userService.insert(user1);
		}else{
			uid =String.valueOf(listUser.get(0).getId());

		}		//先查询改用户是否存下了改角色
		AppUserroleModel appUserroleModel = new AppUserroleModel();
		appUserroleModel.setAppid(appId);
		appUserroleModel.setUserid(Long.parseLong(uid));
		List<AppUserroleModel> listRole=appUserroleService.selectList(appUserroleModel);
		if(listRole==null||listRole.isEmpty()){
			appUserroleModel.setRoleid(10032);
			appUserroleService.insert(appUserroleModel);
		}


		String adminId=serialnoService.getSequence("BD_T_FUND_ADMIN");
		model.setAdminId(adminId);
		model.setCreateDt(new Date());
		model.setUpdateDt(new Date());
		adminService.insert(model);
		//基金管理人账户
		FundAccountModel fundAccount=new FundAccountModel();
		fundAccount.setUserId(model.getAdminName());
		List<FundAccountModel> list=fundAccountService.selectList(fundAccount);
		if(list==null||list.isEmpty()){
			String pkId=serialnoService.getSequence("EI.BD_T_FUND_ACCOUNT");
			FundAccountModel moStr=new FundAccountModel();
			moStr.setUserId(model.getAdminName());
			moStr.setPkId(pkId);
			moStr.setPassword(password);
			moStr.setCreateBy(model.getUpdateBy());
			moStr.setUpdateBy(model.getUpdateBy());
			moStr.setCreateDt(new Date());
			moStr.setUpdateDt(new Date());
			fundAccountService.insert(moStr);
			FundAccountMapingModel maping=new FundAccountMapingModel();
			maping.setPkId(pkId);
			maping.setUserId(model.getAdminName());
			maping.setCreateBy(model.getUpdateBy());
			maping.setUpdateBy(model.getUpdateBy());
			maping.setCreateDt(new Date());
			maping.setUpdateDt(new Date());
			maping.setAdminId(adminId);
			maping.setIsRep("rep");
			fundAccountMapingService.insert(maping);
		}

	}

	@Override
	public void updateAdminInvestModel(AdminModel model,String password) {
		if(StringUtils.isNotEmpty(model.getUserId())){
			UserModel user1=new UserModel();
			user1.setId(Long.parseLong(model.getUserId()));
			user1.setLoginname(model.getAdminName());
			user1.setName(model.getAdminName());
			user1.setUsertype(5L);
			user1.setPassword(password);
			userService.update(user1);
		}
		AdminModel oldModel = adminService.selectById(model.getAdminId());
		BeanUtils.copyProperties(model,oldModel,"createBy","createDt");
		oldModel.setUpdateDt(new Date());
		oldModel.setIsRep("rep");
		adminService.update(oldModel);
		//基金管理人账户
		FundAccountModel fundAccount=new FundAccountModel();
		fundAccount.setUserId(model.getAdminName());
		List<FundAccountModel> list=fundAccountService.selectList(fundAccount);
		if(list==null||list.isEmpty()){
			String pkId=serialnoService.getSequence("EI.BD_T_FUND_ACCOUNT");
			FundAccountModel moStr=new FundAccountModel();
			moStr.setUserId(model.getAdminName());
			moStr.setPkId(pkId);
			moStr.setPassword("888888");
			moStr.setCreateBy(model.getUpdateBy());
			moStr.setUpdateBy(model.getUpdateBy());
			moStr.setCreateDt(new Date());
			moStr.setUpdateDt(new Date());
			fundAccountService.insert(moStr);
			FundAccountMapingModel maping=new FundAccountMapingModel();
			maping.setPkId(pkId);
			maping.setUserId(model.getAdminName());
			maping.setCreateBy(model.getUpdateBy());
			maping.setUpdateBy(model.getUpdateBy());
			maping.setCreateDt(new Date());
			maping.setUpdateDt(new Date());
			maping.setAdminId(model.getAdminId());
			maping.setIsRep("rep");
			fundAccountMapingService.insert(maping);
		}
	}

	@Override
	public void deleteBatchAdminInvestModel(String[] adminIds) {
		adminService.deleteBatch(adminIds);
	}

	@Override
	public void deleteByList(List<AdminModel> list) {
		if(list!=null&& !list.isEmpty()){
			for(AdminModel adminModel:list){
				if(StringUtils.isNotEmpty(adminModel.getAdminId())){
					adminService.deleteById(adminModel.getAdminId());
				}
				if(StringUtils.isNotEmpty(adminModel.getUserId())){
					userService.deleteById(adminModel.getUserId());
				}
			}
		}

	}


	@Override
	public List<AdminModel> getListInfo(AdminModel adminModel) {
		return this.selectList("getListInfo",adminModel);
	}
}