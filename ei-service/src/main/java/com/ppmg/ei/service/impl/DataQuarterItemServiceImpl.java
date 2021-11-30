package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.DataQuarterItemModel;
import com.ppmg.ei.service.DataQuarterItemService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("dataQuarterItemService")
@Service(interfaceClass = DataQuarterItemService.class)
public class DataQuarterItemServiceImpl extends BaseServiceImpl<DataQuarterItemModel> implements DataQuarterItemService {

	public DataQuarterItemServiceImpl(){
		this.setNamespace("DataQuarterItem");
	}

	@Override
	public <E> PageInfo<E> selectListMcPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectListMcPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}
}