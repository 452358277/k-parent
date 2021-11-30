package com.ppmg.ei.service.impl;

import com.founder.ssm.core.common.SearchCondition;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.service.BdTAttaItemService;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.BdTFundAttaModel;
import com.ppmg.ei.service.BdTFundAttaService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component("bdTFundAttaService")
@Service(interfaceClass = BdTFundAttaService.class)
public class BdTFundAttaServiceImpl extends BaseServiceImpl<BdTFundAttaModel> implements BdTFundAttaService {

    @Resource(name = "bdTAttaItemService")
    private BdTAttaItemService bdTAttaItemService;

    @Resource(name = "bdTFundAttaService")
    private BdTFundAttaService bdTFundAttaService;

    public BdTFundAttaServiceImpl() {
        this.setNamespace("BdTFundAtta");
    }

    @Override
    public PageInfo<BdTFundAttaModel> selectListPageNT(SearchCondition searchCondition) {
        PageHelper.startPage(searchCondition.getCurrPage(), searchCondition.getPageSize());
        List<BdTFundAttaModel> list = this.baseDao.selectList("BdTFundAtta", "selectListPageNT", searchCondition);
        PageInfo<BdTFundAttaModel> page = new PageInfo(list);
        return page;
    }

    @Override
    public void addBdTFundAttaModel(BdTFundAttaModel bdTFundAttaModel) {
        if(bdTFundAttaModel.getAttaId() != null && bdTFundAttaModel.getAttaId() != ""){
            bdTFundAttaModel.setUpdateDt(new Date());
            //bdTFundAttaModel.setUpdateBy(bdTFundAttaModel.getUpdateBy());
            bdTFundAttaService.update(bdTFundAttaModel);
        }else{
            bdTFundAttaModel.setAttaId(UUID.randomUUID().toString());
            bdTFundAttaModel.setCreateDt(new Date());
            bdTFundAttaModel.setUpdateDt(new Date());
            bdTFundAttaModel.setCreateBy(bdTFundAttaModel.getUpdateBy());
            //bdTFundAttaModel.setUpdateBy(bdTFundAttaModel.getUpdateBy());
            bdTFundAttaService.insert(bdTFundAttaModel);
        }
    }
}