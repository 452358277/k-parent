package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.FundQuitApplModel;
import com.ppmg.ei.service.FundQuitApplService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("fundQuitApplService")
@Service(interfaceClass = FundQuitApplService.class)
public class FundQuitApplServiceImpl extends BaseServiceImpl<FundQuitApplModel> implements FundQuitApplService {

	public FundQuitApplServiceImpl(){
		this.setNamespace("FundQuitAppl");
	}

	@Override
	public <E> PageInfo<E> fundQuitApplListPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("fundQuitApplListPageMapper", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public void insertFundQuitAppl(FundQuitApplModel fundQuitApplModel) {
		List<FundQuitApplModel> list = new ArrayList<FundQuitApplModel>();
		list.add(fundQuitApplModel);
		this.insert("insertFundQuitApplMapper",list);
	}

	@Override
	public void updateFundQuitAppl(FundQuitApplModel fundQuitApplModel) {
		this.update("updateFundQuitApplMapper",fundQuitApplModel);
	}

	@Override
	public <E> PageInfo<E> selectListInfoPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectListInfoPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}
}