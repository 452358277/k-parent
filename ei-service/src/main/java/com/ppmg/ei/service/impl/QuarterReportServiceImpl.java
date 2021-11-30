package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.founder.ssm.foundation.service.SerialnoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.QuarterReportModel;
import com.ppmg.ei.service.QuarterReportService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component("quarterReportService")
@Service(interfaceClass = QuarterReportService.class)
public class QuarterReportServiceImpl extends BaseServiceImpl<QuarterReportModel> implements QuarterReportService {

	public QuarterReportServiceImpl(){
		this.setNamespace("QuarterReport");
	}

	@Resource
	private QuarterReportService quarterReportService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Override
	public <E> PageInfo<E> selectQuarterReportPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectQuarterReportPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public void insertQuarterReport(QuarterReportModel model, String userId) {
		String reportId = serialnoService.getSequence("EI.XJL_T_QUARTER_REPORT");
		model.setReportId(reportId);
		model.setCreateBy(userId);
		model.setCreateDt(new Date());
		model.setUpdateDt(new Date());
		model.setUpdateBy(userId);
		model.setStatus("0");
		quarterReportService.insert(model);
	}
}