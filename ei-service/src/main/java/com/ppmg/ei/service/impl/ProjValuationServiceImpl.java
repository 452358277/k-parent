package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.founder.ssm.foundation.service.SerialnoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.ProjRegularAssessModel;
import com.ppmg.ei.model.ProjValuationModel;
import com.ppmg.ei.service.ProjRegularAssessService;
import com.ppmg.ei.service.ProjValuationService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component("projValuationService")
@Service(interfaceClass = ProjValuationService.class)
public class ProjValuationServiceImpl extends BaseServiceImpl<ProjValuationModel> implements ProjValuationService {

	public ProjValuationServiceImpl(){
		this.setNamespace("ProjValuation");
	}

	@Resource
	private ProjValuationService projValuationService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Resource
	private ProjRegularAssessService projRegularAssessService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertByProjValuation(String userId, ProjValuationModel model, ProjRegularAssessModel projRegularAssessModel) throws Exception {
		String pkId1=serialnoService.getSequence("EI.TZ_T_PROJ_REGULAR_ASSESS");
		projRegularAssessModel.setCreateDt(new Date());
		projRegularAssessModel.setCreateBy(userId);
		projRegularAssessModel.setUpdateBy(userId);
		projRegularAssessModel.setUpdateDt(new Date());
		projRegularAssessModel.setPkId(pkId1);
		projRegularAssessModel.setStatus("0");
		//主表
		projRegularAssessService.insert(projRegularAssessModel);

		String pkId=serialnoService.getSequence("EI.TZ_T_PROJ_VALUATION");
		model.setCreateDt(new Date());
		model.setCreateBy(userId);
		model.setUpdateBy(userId);
		model.setUpdateDt(new Date());
		model.setPkId(pkId);
		model.setPraId(pkId1);
		model.setStatus("0");
		projValuationService.insert(model);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void deleteByvalue(String id) throws Exception{
		ProjValuationModel model=new ProjValuationModel();
		model.setPraId(id);
		model.setStatus("9");
		projValuationService.update(model);
		ProjRegularAssessModel projRegularAssessModel=new ProjRegularAssessModel();
		projRegularAssessModel.setPkId(id);
		projRegularAssessModel.setStatus("9");
		projRegularAssessService.update(projRegularAssessModel);
	}


	@Override
	public <E> PageInfo<E> selectListEntPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectListEntPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}
}