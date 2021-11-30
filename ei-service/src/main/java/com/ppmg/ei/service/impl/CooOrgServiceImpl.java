package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.foundation.service.SerialnoService;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.bean.CooOrgSearchBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.CooOrgModel;
import com.ppmg.ei.service.CooOrgService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/** 
 * 合作方机构库
 * @author null
 * @date 2019-08-13 10:53 
 */ 
@Component("cooOrgService")
@Service(interfaceClass = CooOrgService.class)
public class CooOrgServiceImpl extends BaseServiceImpl<CooOrgModel> implements CooOrgService {

	public CooOrgServiceImpl(){
		this.setNamespace("CooOrg");
	}

	@Resource
	private CooOrgService cooOrgService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	/**
	 * 新增合作方机构库
	 * @param model
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@Override
	public void insertModel(CooOrgModel model) {
		model.setCreateDt(new Date());
		model.setUpdateDt(new Date());
		model.setOrgId(serialnoService.getSequence("BD_T_FUND_COO_ORG"));
		cooOrgService.insert(model);
	}

	/**
	 * 分页查询
	 * @param var1
	 * @param searchBean
	 * @param <E>
	 * @return
	 */
	@Override
	public <E> PageInfo<CooOrgModel> selectPage(SearchCondition var1, CooOrgSearchBean searchBean) {
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
		if(searchBean.getOrgType() != null && StringUtils.isNotBlank(searchBean.getOrgType())){
			List<String> orgTypes = new ArrayList<>();
			String[] array = searchBean.getOrgType().split(",");
			orgTypes = Arrays.asList(array);
			searchBean.setOrgTypes(orgTypes);
		}
		var1.setSearchBean(searchBean);
		PageInfo<CooOrgModel> pageInfo = cooOrgService.selectListPage(var1);
		return pageInfo;
	}

	/**
	 * 更新合作方机构库
	 * @param model
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@Override
	public void updateModel(CooOrgModel model) {
		CooOrgModel oldCooOrgModel = cooOrgService.selectById(model.getOrgId());
		if(oldCooOrgModel!=null){
			BeanUtils.copyProperties(model,oldCooOrgModel,"createDt","createBy");
			oldCooOrgModel.setUpdateDt(new Date());
			cooOrgService.update(oldCooOrgModel);
		}

	}

	/**
	 * 批量删除
	 * @param orgIds
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@Override
	public void deleteBatchModel(String[] orgIds) {
		cooOrgService.deleteBatch(orgIds);
	}

	/**
	 * 查询集合
	 * @param
	 * @param
	 * @return
	 */
	@Override
	public List<CooOrgModel> selectListModel(CooOrgModel sqlModel) {
		List<CooOrgModel> pageInfo = cooOrgService.selectList(sqlModel);
		return pageInfo;
	}

	@Override
	public List<CooOrgModel> selectListByCooName(CooOrgModel cooOrgModel) {
		return this.selectList("selectListByCooName",cooOrgModel);
	}
}