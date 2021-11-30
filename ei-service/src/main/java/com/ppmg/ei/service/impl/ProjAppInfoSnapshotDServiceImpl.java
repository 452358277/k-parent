package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Component("projAppInfoSnapshotDService")
@Service(interfaceClass = ProjAppInfoSnapshotDService.class,timeout = 90000,retries = 0)
public class ProjAppInfoSnapshotDServiceImpl extends BaseServiceImpl<ProjAppInfoSnapshotDModel> implements ProjAppInfoSnapshotDService {

	public ProjAppInfoSnapshotDServiceImpl(){
		this.setNamespace("ProjAppInfoSnapshotD");
	}

	@Resource
	private ProjAppInfoService projAppInfoService;

	@Resource
	private FundInfoService fundInfoService;

	@Resource
	private ProjAppInfoSnapshotDService projAppInfoSnapshotDService;

	@Resource
	private ProjInfoService projInfoService;


	@Resource
	private BdTFitRegulationSelfService bdTFitRegulationSelfService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateByAappInfo(ProjAppInfoModel projAppInfo, FundInfoModel fundInfoModel, ProjAppInfoSnapshotDModel model, ProjInfoModel projInfoModel, BdTFitRegulationSelfModel bdTFitRegulationSelf) throws Exception {

		projAppInfo.setUpdateDt(new Date());
		projAppInfoService.update(projAppInfo);
		projInfoModel.setUpdateBy(projAppInfo.getUpdateBy());
		projInfoModel.setUpdateDt(new Date());
		projInfoService.update(projInfoModel);
		bdTFitRegulationSelf.setUpdateDt(new Date());
		bdTFitRegulationSelf.setUpdateBy(projAppInfo.getUpdateBy());
		bdTFitRegulationSelfService.update(bdTFitRegulationSelf);

		fundInfoModel.setUpdateDt(new Date());
		fundInfoModel.setUpdateBy(projAppInfo.getUpdateBy());
		fundInfoService.update(fundInfoModel);
		model.setUpdateDt(new Date());
		model.setUpdateBy(projAppInfo.getUpdateBy());
		ProjAppInfoSnapshotDModel  appModel= projAppInfoSnapshotDService.selectById(projAppInfo.getProjId());
		if(appModel==null){
			model.setCreateDt(new Date());
			model.setCreateBy(projAppInfo.getUpdateBy());
			projAppInfoSnapshotDService.insert(model);
		}else{
			projAppInfoSnapshotDService.update(model);
		}
	}
}