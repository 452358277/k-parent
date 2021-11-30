package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.founder.ssm.foundation.service.SerialnoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component("enteInfoService")
@Service(interfaceClass = EnteInfoService.class)
public class EnteInfoServiceImpl extends BaseServiceImpl<EnteInfoModel> implements EnteInfoService {

	public EnteInfoServiceImpl(){
		this.setNamespace("EnteInfo");
	}


	@Resource
	private EnteInfoService enteInfoService;

	@Resource
	private CustMemberService custMemberService;

	@Resource
	private EntLabelService entLabelService;

	@Resource(name="entBaseInfoService")
	private EntBaseInfoService entBaseInfoService;


	@Reference(check = false)
	private SerialnoService serialnoService;

	@Resource
	private ProjAppInfoService projAppInfoService;

	@Resource
	private ProjInfoService projInfoService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertInfo(EnteInfoModel model, CustMemberModel cum, List<String> labels, ProjInfoModel projInfoModel,String typeStr) {
		String enterpriseId="";
		String isQcc="";
		if("0".equals(model.getIsAbroad()) && "2".equals(model.getIsReg())){
			isQcc="1";
		}else{
			isQcc="2";
		}
		if("2".equals(isQcc)){
			//未注册
			EntBaseInfoModel entBaseInfoModel1 = new EntBaseInfoModel();
			entBaseInfoModel1.setName(model.getChName());
			List<EntBaseInfoModel> entList = entBaseInfoService.selectList(entBaseInfoModel1);
			if(entList!=null&&!entList.isEmpty()){
				enterpriseId=entList.get(0).getEnterpriseId();
				model.setEnteId(enterpriseId);
				projInfoModel.setProjObject(enterpriseId);
			}else{
				enterpriseId = serialnoService.getSequence("MASTER.T_ENT_BASE_INFO");
				EntBaseInfoModel entBaseInfoModel2 = new EntBaseInfoModel();
				entBaseInfoModel2.setEnterpriseId(enterpriseId);
				entBaseInfoModel2.setName(model.getChName());
				entBaseInfoModel2.setEnName(model.getChName());
				entBaseInfoModel2.setDeleteFlag("0");
				entBaseInfoModel2.setStatus("未注册");
				entBaseInfoModel2.setCreateBy(model.getUpdateBy());
				entBaseInfoModel2.setCreateDt(new Date());
				entBaseInfoModel2.setUpdateBy(model.getUpdateBy());
				entBaseInfoModel2.setUpdateDt(new Date());
				entBaseInfoService.insert(entBaseInfoModel2);
				model.setEnteId(enterpriseId);
				projInfoModel.setProjObject(enterpriseId);
			}
		}
		EntLabelModel entLabelModel = new EntLabelModel();
		if (StringUtils.isNotEmpty(model.getEnteId())) {
			entLabelModel.setEntId(model.getEnteId());
			entLabelService.delete(entLabelModel);
		}
		if (labels != null && !labels.isEmpty()) {
			//先删除之前的标签
			for (int i = 0; i < labels.size(); i++) {
				EntLabelModel entLabel = new EntLabelModel();
				entLabel.setEntId(model.getEnteId());
				entLabel.setLabelId(labels.get(i));
				entLabel.setCreateDt(new Date());
				entLabel.setCreateBy(String.valueOf(model.getCreateBy()));
				entLabel.setUpdateDt(new Date());
				entLabel.setUpdateBy(String.valueOf(model.getCreateBy()));
				entLabelService.insert(entLabel);
			}
		}
		//查询是否存在
	  /*  if(StringUtils.isEmpty(model.getEnterpriseId())){
			enteInfoService.insert(model);
		}*/
	    if("add".equals(typeStr)){
			enteInfoService.insert(model);
		}else{
			enteInfoService.update(model);
		}

	    String 	projId=serialnoService.getSequence("EI.TZ_T_PROJ_INFO");
		projInfoModel.setProjId(projId);
		projInfoModel.setCreateBy(model.getUpdateBy());
		projInfoModel.setCreateDt(new Date());
		projInfoModel.setUpdateBy(model.getUpdateBy());
		projInfoModel.setUpdateDt(new Date());
		projInfoService.insert(projInfoModel);

		ProjAppInfoModel appInfo=new ProjAppInfoModel();
		appInfo.setProjId(projId);
		appInfo.setCreateBy(model.getUpdateBy());
		appInfo.setCreateDt(new Date());
		appInfo.setUpdateBy(model.getUpdateBy());
		appInfo.setUpdateDt(new Date());
		projAppInfoService.insert(appInfo);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateInfo(EnteInfoModel model, CustMemberModel cum,List<String> labels,ProjInfoModel projInfoModel,String typeStr) {
		String enterpriseId="";
		String isQcc="";
		if("0".equals(model.getIsAbroad()) && "2".equals(model.getIsReg())){
			isQcc="1";
		}else{
			isQcc="2";
		}
		if("2".equals(isQcc)){
			//未注册
			EntBaseInfoModel entBaseInfoModel1 = new EntBaseInfoModel();
			entBaseInfoModel1.setName(model.getChName());
			List<EntBaseInfoModel> entList = entBaseInfoService.selectList(entBaseInfoModel1);
			if(entList!=null&&!entList.isEmpty()){
				enterpriseId=entList.get(0).getEnterpriseId();
				model.setEnteId(enterpriseId);
				projInfoModel.setProjObject(enterpriseId);
				projInfoModel.setProjObjectName(model.getChName());
			}else{
				enterpriseId = serialnoService.getSequence("MASTER.T_ENT_BASE_INFO");
				EntBaseInfoModel entBaseInfoModel2 = new EntBaseInfoModel();
				entBaseInfoModel2.setEnterpriseId(enterpriseId);
				entBaseInfoModel2.setName(model.getChName());
				entBaseInfoModel2.setEnName(model.getChName());
				entBaseInfoModel2.setDeleteFlag("0");
				entBaseInfoModel2.setStatus("未注册");
				entBaseInfoModel2.setCreateBy(model.getUpdateBy());
				entBaseInfoModel2.setCreateDt(new Date());
				entBaseInfoModel2.setUpdateBy(model.getUpdateBy());
				entBaseInfoModel2.setUpdateDt(new Date());
				entBaseInfoService.insert(entBaseInfoModel2);
				model.setEnteId(enterpriseId);
				projInfoModel.setProjObject(enterpriseId);
				projInfoModel.setProjObjectName(model.getChName());
			}
		}
        //先判断企业是否存在
		if("update".equals(typeStr)){
			if("1".equals(model.getUpdateNull())){
				enteInfoService.update("updateByNull",model);
			}else{
				enteInfoService.update(model);
			}
		}else if("add".equals(typeStr)){
			enteInfoService.insert(model);
		}

		projInfoModel.setUpdateBy(model.getUpdateBy());
		projInfoModel.setUpdateDt(new Date());
		projInfoService.update(projInfoModel);
		EntLabelModel entLabelModel = new EntLabelModel();
		if (StringUtils.isNotEmpty(model.getEnteId())) {
			entLabelModel.setEntId(model.getEnteId());
			entLabelService.delete(entLabelModel);
		}
		if (labels != null && !labels.isEmpty()) {
			//先删除之前的标签
			for (int i = 0; i < labels.size(); i++) {
				EntLabelModel entLabel = new EntLabelModel();
				entLabel.setEntId(model.getEnteId());
				entLabel.setLabelId(labels.get(i));
				entLabel.setCreateDt(new Date());
				entLabel.setCreateBy(String.valueOf(model.getCreateBy()));
				entLabel.setUpdateDt(new Date());
				entLabel.setUpdateBy(String.valueOf(model.getCreateBy()));
				entLabelService.insert(entLabel);
			}
		}
	}

	@Override
	public <E> PageInfo<E> selectGetListPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectGetListPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}
}