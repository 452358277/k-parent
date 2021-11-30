package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.BankProductModel;
import com.ppmg.ei.model.LedgerMagModel;

import java.util.List;

public interface LedgerMagService extends BaseService<LedgerMagModel>  {

    <E> PageInfo<E> selectcashFlowPage(SearchCondition var1);

    <E> PageInfo<E> selectLedgerPage(SearchCondition var1);

    String insertLedgerMag(LedgerMagModel ledgerMagModel,String userId, BankProductModel bankProduct);

      void updateByName(LedgerMagModel ledgerMagModel,String userId, BankProductModel bankProduct,String productId) throws Exception;

    <E> PageInfo<E> selectListFundPage(SearchCondition var1);


    void insertInfo(LedgerMagModel ledgerMagModel,Double intendedAmount,String intendedCurrency) throws Exception;

    void updateInfo(LedgerMagModel ledgerMagModel,Double intendedAmount,String intendedCurrency) throws Exception;


   void  updateByObject (LedgerMagModel model);

   List<LedgerMagModel> selectListByOccDt(LedgerMagModel ledgerMagModel);
}