package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.FundShareInfoModel;
import com.ppmg.ei.model.FundShareItemModel;
import com.ppmg.ei.model.LedgerMagModel;

import java.util.List;

public interface FundShareInfoService extends BaseService<FundShareInfoModel>  {

    <E> PageInfo<E> selectFundSharePage(SearchCondition var1);

    <E> PageInfo<E> selectFundShareInfoPage(SearchCondition var1);

    <E> PageInfo<E> selectByItemListPage(SearchCondition var1);


    List<FundShareInfoModel> selectListByEntId(FundShareInfoModel fundShareInfoModel);


    //增加出资信息
    void addFundShareInfoListCZ(FundShareInfoModel fundShareInfoModel);

    /**
     * 分页查询出资信息列表
     * @param var1
     * @return
     */
    <E> PageInfo<E> selectFundShareInfoPageList(SearchCondition var1);

    /**
     * 查询详情
     * @param pkId
     * @return
     */
    public FundShareInfoModel selectOneFundShareInfo(String pkId);

    public Integer updateFundShareInfo(FundShareInfoModel fundShareInfoModel);

    /**
     * 分页查询与本公司基金关系
     */
    <E> PageInfo<E> selectFundShareInfoPageListTwo(SearchCondition var1);

    /**
     * 获取当前基金的认缴基金之和
     * @param fundId
     * @return
     */
    public Double getInveAmountSum(String fundId);
    void insertFundShare(FundShareInfoModel model , FundShareItemModel fundShareItem,String userId) throws  Exception;

    Double getSumPayAmount(FundShareInfoModel fundShareInfoModel);

    void updateByName(String pkId) throws Exception;

    void updateShareInfo(FundShareInfoModel model,List<LedgerMagModel> list,String projId,String oldInvestorId);


    <E> PageInfo<E> getFundShareList(SearchCondition var1);

    List<FundShareInfoModel>  selectListByList(FundShareInfoModel model);
}