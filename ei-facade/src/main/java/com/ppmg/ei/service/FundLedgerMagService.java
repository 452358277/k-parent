package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.FundLedgerMagModel;
import com.ppmg.ei.model.InveInfoModel;

import java.util.List;

/** 
 * 描述 [Service] 
 * @author root
 * @date 2019-09-03 14:21 
 */ 
public interface FundLedgerMagService extends BaseService<FundLedgerMagModel>  {

    /**
     * 查询投资人信息
     * @param investorId
     * @return
     */
    public InveInfoModel selectOneInveInfo(String investorId);
    List<FundLedgerMagModel> selectFundLedgerMagList(String pkId);

}