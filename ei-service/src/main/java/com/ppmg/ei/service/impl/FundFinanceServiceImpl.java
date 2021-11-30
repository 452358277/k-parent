package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.FundFinanceModel;
import com.ppmg.ei.service.FundFinanceService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("fundFinanceService")
@Service(interfaceClass = FundFinanceService.class)
public class FundFinanceServiceImpl extends BaseServiceImpl<FundFinanceModel> implements FundFinanceService {

	public FundFinanceServiceImpl() {
		this.setNamespace("FundFinance");
	}


	@Override
	public <E> PageInfo<E> selectListYearPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectListYearPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public void updateIsNull(FundFinanceModel model) {
		this.update("updateIsNull",model);
	}
}