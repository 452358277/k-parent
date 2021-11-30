package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.FundProGovernmentModel;
import com.ppmg.ei.service.FundProGovernmentService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("fundProGovernmentService")
@Service(interfaceClass = FundProGovernmentService.class)
public class FundProGovernmentServiceImpl extends BaseServiceImpl<FundProGovernmentModel> implements FundProGovernmentService {
    public FundProGovernmentServiceImpl(){
        this.setNamespace("FundProGovernment");
    }

    @Override//基金类型
    public <E> PageInfo<E> selectallListPageFundType(SearchCondition var1) {
        PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
        List<E> list = this.selectList("selectListPageFundType", var1);
        PageInfo<E> page = new PageInfo(list);
        return page;
    }

    @Override //基金形式
    public <E> PageInfo<E> selectallListPageFundFrom(SearchCondition var1) {
        PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
        List<E> list = this.selectList("selectListPagefundFrom", var1);
        PageInfo<E> page = new PageInfo(list);
        return page;
    }

    @Override //企业标签
    public <E> PageInfo<E> selectallListPageEnterpriseLabel(SearchCondition var1) {
        PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
        List<E> list = this.selectList("selectListPageEnterpriseLabel", var1);
        PageInfo<E> page = new PageInfo(list);
        return page;
    }

    @Override
    public <E> PageInfo<E> selectallListPageFundGenre(SearchCondition var1) {
        PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
        List<E> list = this.selectList("selectListPageFundGenre", var1);
        PageInfo<E> page = new PageInfo(list);
        return page;
    }

    //----------------------------运营监控-报表-----------------------------
    @Override
    public <E> PageInfo<E> selectListPageReportSon(SearchCondition var1) {
        PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
        List<E> list = this.selectList("selectListPageReport", var1);
        PageInfo<E> page = new PageInfo(list);
        return page;
    }
    @Override
    public <E> PageInfo<E> selectListPageReportSon2(SearchCondition var1) {
        PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
        List<E> list = this.selectList("selectListPageReport2", var1);
        PageInfo<E> page = new PageInfo(list);
        return page;
    }

    @Override
    public <E> PageInfo<E> selectListPageReportSonSecond(SearchCondition var1) {
        PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
        List<E> list = this.selectList("selectListPageReportSonSecond", var1);
        PageInfo<E> page = new PageInfo(list);
        return page;
    }
    @Override
    public <E> PageInfo<E> selectListPageReportSonSecond2(SearchCondition var1) {
        PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
        List<E> list = this.selectList("selectListPageReportSonSecond2", var1);
        PageInfo<E> page = new PageInfo(list);
        return page;
    }
}
