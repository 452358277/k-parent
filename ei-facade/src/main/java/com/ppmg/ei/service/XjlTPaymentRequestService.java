package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;

import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.XjlTPaymentRequestModel;

import java.util.List;

public interface XjlTPaymentRequestService extends BaseService<XjlTPaymentRequestModel>  {

    public List<XjlTPaymentRequestModel> selectByProjId(String projId);

    void insertByPqId(XjlTPaymentRequestModel xjlTPaymentRequestModel,String userId);

    Double selectCountSum(String projectOrFundId);

    Double selectSumRmbActualPayAmount(String projectOrFundId);

    Double selectSumPass(String projectOrFundId);

    List<XjlTPaymentRequestModel>  selectListByPoId(XjlTPaymentRequestModel xxl );

    void updateByPo(XjlTPaymentRequestModel xjlTPaymentRequestModel,String userId)throws  Exception;

    Double selectCountBySum(XjlTPaymentRequestModel xjlT);

    Double selectsumAllPay(String inveId);

    <E> PageInfo<E> selectInveMoneyListPage(SearchCondition var1);

    void deleteByIds(List<String> ids, String userId);


    /**
     *
     * 查询某个项目下出资是否有在审核中的数据
     * **/
    List<XjlTPaymentRequestModel> selectNoPassList(String projId);

}