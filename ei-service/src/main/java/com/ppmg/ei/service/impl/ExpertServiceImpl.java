package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.foundation.service.SerialnoService;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.bean.ExpertSearchBean;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.ExpertLabelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.service.ExpertService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/** 
 * 描述 [Service.impl] 
 * @author null
 * @date 2019-08-12 15:01 
 */ 
@Component("expertService")
@Service(interfaceClass = ExpertService.class)
public class ExpertServiceImpl extends BaseServiceImpl<ExpertModel> implements ExpertService {

	public ExpertServiceImpl(){
		this.setNamespace("Expert");
	}

	@Resource
	ExpertService expertService;

	@Resource
	ExpertLabelService expertLabelService;

	@Reference
	SerialnoService serialnoService;
	/**
	 * 修改数据
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateExpert(ExpertModel expertModel,String labels) {

		ExpertModel exModel = expertService.selectById(expertModel.getExpertId());
		if(exModel!=null){
			BeanUtils.copyProperties(expertModel,exModel);
			exModel.setUpdateDt(new Date());
			exModel.setUpdateBy(expertModel.getUpdateBy());
			expertService.update(exModel);
		}
		//先删除
		ExpertLabelSqlModel sqlModel = new ExpertLabelSqlModel();
		List<String> expertIds = new ArrayList<>();
		expertIds.add(expertModel.getExpertId());
		sqlModel.setExpertIds(expertIds);
		expertLabelService.delete("deleteBatchBySqlModel",sqlModel);
		//在新增
		List<ExpertLabelModel> labelModels = new ArrayList<>();
		if(labels != null && !"".equals(labels)){
			String[] array = labels.split(",");

			for (int i = 0; i <array.length ; i++) {
				ExpertLabelModel labelModel = new ExpertLabelModel();
				labelModel.setLabel(array[i]);
				labelModel.setPkId(serialnoService.getSequence("BD_T_FUND_EXPERT_LABEL"));
				labelModel.setExpertId(expertModel.getExpertId());
				labelModel.setCreateBy(expertModel.getUpdateBy());
				labelModel.setUpdateBy(expertModel.getUpdateBy());
				labelModel.setCreateDt(new Date());
				labelModel.setUpdateDt(new Date());
				labelModels.add(labelModel);
			}
			expertLabelService.insertBatch(labelModels);
		}

	}

	/**
	 *通过IDS进行逻辑删除
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void deleteByIds(String[] array, String userId) {
		expertService.deleteBatch(array);
		ExpertLabelSqlModel sqlModel = new ExpertLabelSqlModel();
		List<String> expertIds = new ArrayList<>();
		if(expertIds != null && expertIds.size() > 0){
			for (int i = 0; i <array.length ; i++) {
				expertIds.add(array[i]);
			}
			expertLabelService.delete("deleteBatchBySqlModel",expertIds);
		}
	}

	/**
	 *插入数据
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertModel(ExpertModel expertModel, String labels) throws Exception{
		/**
		 * 利用事物同时插入两张表
		 */
		String expertId = serialnoService.getSequence("BD_T_FUND_EXPERT");
		expertModel.setExpertId(expertId);
		expertModel.setCreateDt(new Date());
		expertModel.setUpdateDt(new Date());
		expertService.insert(expertModel);
		List<ExpertLabelModel> labelModels = new ArrayList<>();
		if(labels != null && !"".equals(labels)){
			String[] array = labels.split(",");

			for (int i = 0; i <array.length ; i++) {
				ExpertLabelModel labelModel = new ExpertLabelModel();
				labelModel.setLabel(array[i]);
				labelModel.setPkId(serialnoService.getSequence("BD_T_FUND_EXPERT_LABEL"));
				labelModel.setExpertId(expertId);
				labelModel.setCreateBy(expertModel.getCreateBy());
				labelModel.setUpdateBy(expertModel.getCreateBy());
				labelModel.setCreateDt(new Date());
				labelModel.setUpdateDt(new Date());
				labelModels.add(labelModel);
			}
			expertLabelService.insertBatch(labelModels);
		}

	}

	@Override
	public Integer selectCount(Object parameter) {
		return super.selectCount(parameter);
	}

	@Override
	public <E> PageInfo<ExpertListModel> selectExpertByPage(SearchCondition searchCondition,ExpertSearchBean searchBean) {
		if(searchBean.getCurrPage()<0){
			searchCondition.setCurrPage(0);
		}else{
			searchCondition.setCurrPage(searchBean.getCurrPage());
		}
		if(searchBean.getPageSize()<0){
			searchCondition.setPageSize(1);
		}else{
			searchCondition.setCurrPage(searchBean.getPageSize());
		}
		searchCondition.setSearchBean(searchBean);
		PageInfo<ExpertListModel> listByPage = expertService.selectListPage(searchCondition);
		if(listByPage.getList() != null && listByPage.getList().size() > 0){
			List<String> expertIds = new ArrayList<>();
			for (ExpertListModel expert:listByPage.getList()) {
				expertIds.add(expert.getExpertId());
			}
			ExpertLabelSqlModel sqlModel = new ExpertLabelSqlModel();
			sqlModel.setExpertIds(expertIds);
			List<ExpertLabelModel>  labelModels = expertLabelService.selectList("selectListByExpertId",sqlModel);
			if(labelModels != null && labelModels.size() > 0){

				for (ExpertListModel expert:listByPage.getList()) {
					List<ExpertLabelModel> labelModelList = new ArrayList<>();
					for (ExpertLabelModel label:labelModels) {
						if(expert.getExpertId().equals(label.getExpertId())){
							labelModelList.add(label);
						}
					}
					expert.setLabelModels(labelModelList);
				}
			}
		}

		return listByPage;
	}


	@Override
	public List<ExpertListModel> exportList(SearchCondition searchCondition, ExpertSearchBean searchBean) {
		searchCondition.setSearchBean(searchBean);
		List<ExpertListModel> modelList = expertService.selectList("selectListPage",searchCondition);
		if(modelList != null && modelList.size() > 0){
			List<String> expertIds = new ArrayList<>();
			for (ExpertListModel expert: modelList) {
				expertIds.add(expert.getExpertId());
			}
			ExpertLabelSqlModel sqlModel = new ExpertLabelSqlModel();
			sqlModel.setExpertIds(expertIds);
			List<ExpertLabelModel>  labelModels = expertLabelService.selectList("selectListByExpertId",sqlModel);
			if(labelModels != null && labelModels.size() > 0){

				for (ExpertListModel expert: modelList) {
					List<ExpertLabelModel> labelModelList = new ArrayList<>();
					for (ExpertLabelModel label:labelModels) {
						if(expert.getExpertId().equals(label.getExpertId())){
							labelModelList.add(label);
						}
					}
					expert.setLabelModels(labelModelList);
				}
			}
		}
		return modelList;
	}
}