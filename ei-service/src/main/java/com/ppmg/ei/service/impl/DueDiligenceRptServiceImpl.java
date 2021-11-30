package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ppmg.ei.model.SurverCooorgModel;
import com.ppmg.ei.service.SurverCooorgService;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.DueDiligenceRptModel;
import com.ppmg.ei.service.DueDiligenceRptService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("dueDiligenceRptService")
@Service(interfaceClass = DueDiligenceRptService.class)
public class DueDiligenceRptServiceImpl extends BaseServiceImpl<DueDiligenceRptModel> implements DueDiligenceRptService {

	public DueDiligenceRptServiceImpl(){
		this.setNamespace("DueDiligenceRpt");
	}

	@Resource
	private DueDiligenceRptService dueDiligenceRptService;

	@Resource
	private SurverCooorgService surverCooorgService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertByDue(DueDiligenceRptModel model, List<SurverCooorgModel> list) throws Exception {
		dueDiligenceRptService.insert(model);
		if(list!=null&&list.size()>0){
              for(SurverCooorgModel surverCooorgModel:list){
				  surverCooorgModel.setPlanId(model.getSurveyPlanId());
				  surverCooorgModel.setUpdateDt(new Date());
				  surverCooorgModel.setUpdateBy(model.getUpdateBy());
				  surverCooorgService.update(surverCooorgModel);
			  }
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateByDue(DueDiligenceRptModel model, List<SurverCooorgModel> list) throws Exception {
		dueDiligenceRptService.update(model);
		if(list!=null&&list.size()>0){
			for(SurverCooorgModel surverCooorgModel:list){
				surverCooorgModel.setPlanId(model.getSurveyPlanId());
				surverCooorgModel.setUpdateDt(new Date());
				surverCooorgModel.setUpdateBy(model.getUpdateBy());
				surverCooorgService.update(surverCooorgModel);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void deleteByIds(List<String> ids, String userId) {
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("ids", ids);
		paramsMap.put("userId", userId);
		paramsMap.put("status", "9");
		dueDiligenceRptService.update("deleteByIds", paramsMap);
	}
}