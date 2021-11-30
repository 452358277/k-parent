package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.*;

import java.util.List;
import java.util.Map;

public interface FundInfoService extends BaseService<FundInfoModel>  {

    /**
     * 根据项目ID获取项目基本信息
     * @param fundId
     * @return
     */
    public FundInfoModel getFundDetailInfoByFundId(String fundId);

    /**
     * 根据基金ID获取基金投资信息
     * @param fundId
     * @return
     */
    public FundInfoModel getFundInvestInfoByFundId(String fundId);

    /**
     * 更新基金投资信息
     * @param model
     * @param userId
     * @return
     */
    public void updateFundInfo(FundInfoModel model, String userId, String password)throws  Exception;

    /**
     * 江苏省政府投资基金基本情况表
     * @return
     */
    <E> PageInfo<E> selectgovernmentFundListPage(SearchCondition var1);

    /**
     * 查询基金出资人情况统计表
     * @return
     */
    <E> PageInfo<E> fundinveInfoList(SearchCondition var1);

    <E> PageInfo<E> selectListProjPage(SearchCondition var1);


    <E> PageInfo<E>  selectFundOrSpvListByPage(SearchCondition var1);

      Map<String,Object> selectFundIdList(String fundId);

     Map<String,Object>  selectSumByFundId(String fundId);

    /**
     * 查询三基金和spv基金
     * @return
     */
    public FundInfoModel selectByName(String fundName);


    List<FundInfoModel> selectListByFundCode(String fundCode);


    /**
     * 综合统计表
     * @return
     */
    <E> PageInfo<E> fundComprehensiveListPage(SearchCondition var1);

    List<FundInfoModel> selectByNameAndFundSrc(String fundName);

   void  insertFundInfoProj(FundInfoModel fundInfo, ProjInfoModel projInfo, String userId, FundInveDescModel fundInveDescModel, ProjAppInfoModel projAppInfoModel, FundShareInfoModel fundShareInfoModel)throws  Exception;

    void updateFundInfoProj(FundInfoModel fundInfo, ProjInfoModel projInfo, String userId, FundInveDescModel fundInveDescModel, ProjAppInfoModel projAppInfoModel)throws  Exception;

    void insertFundInfo(FundInfoModel fundInfoModel);

    void updateFundInfo(FundInfoModel fundInfoModel);


    List<FundInfoModel> selectNewCodeName(String code, Integer con);

    <E> PageInfo<E> selectFofListPage(SearchCondition var1);

    <E> PageInfo<E> selectFofListPageAll(SearchCondition var1);


    List<FundInfoModel> selectByList(FundInfoModel model);

    /**
     * 查询基金编码
     * @return
     */
    List<FundInfoModel> selectNewCode(String code, Integer cou);


    void insertByInfo(FundInfoModel model, ProjInfoModel projInfoModel, FundInveDescModel fundInveDescModel, ProjAppInfoModel projAppInfoModel, EntBaseInfoModel ent, String typeStr);

    void insertByFundOrInfo(FundInfoModel model, ProjInfoModel projInfoModel, FundInveDescModel fundInveDescModel, ProjAppInfoModel projAppInfoModel, EntBaseInfoModel ent, String typeStr, List<AdminModel> listAdmin, String typeUpdate);



    void  updateByInfo(FundInfoModel model, ProjInfoModel projInfoModel, FundInveDescModel fundInveDescModel, ProjAppInfoModel projAppInfoModel, EntBaseInfoModel ent, String typeStr);

    void  updateByFundOrInfo(FundInfoModel model, ProjInfoModel projInfoModel, FundInveDescModel fundInveDescModel, ProjAppInfoModel projAppInfoModel, EntBaseInfoModel ent, String typeStr, List<AdminModel> listAdmin);



    //查询母基金投的项目个数
    Integer selectByProjNum(String fundId);

    <E> PageInfo<E>  getInfoListPage(SearchCondition var1);

    <E> PageInfo<E>  selectInveListPage(SearchCondition var1);

    <E> PageInfo<E> getJcFundList(SearchCondition var1);


    <E> PageInfo<E> selectJcInveListPage(SearchCondition var1);

      Double  getSumFund();

    <E> PageInfo<E> fundInfoInveList(SearchCondition var1);

    Integer getSumProj(String fundId);


    void  savaFundth(FundInfoModel fundInfoModel, FundInveDescModel inveDes, FundRecordInfoModel record, FundMemberModel fundMum);

    <E> PageInfo<E> selectCgListPage(SearchCondition var1);

    <E> PageInfo<E> getFofListPage(SearchCondition var1);

    void  savaFundF(FundInfoModel fundInfoModel, FundInveDescModel inveDes, FundMemberModel fundMum)throws Exception;


    <E> PageInfo<E> getEnteInveList(SearchCondition var1);

    <E> PageInfo<E> selectFundExportList(SearchCondition var1);

    List<FundInfoExportModel> getExportInfoList(SearchCondition var1);

    <E> PageInfo<E> projInfoExportListPage(SearchCondition var1);

     List<ProjInfoExportModel> projInfoExportList(SearchCondition var1);

    List<ProjInfoAllExportModel> projExportList(SearchCondition var1);


    List<LedgeMagExportModel> projLedgeExportList(SearchCondition var1);

    List<LedgeMagExportModel> ledgeExportList(SearchCondition var1);


    int  getCountLedgeMage(SearchCondition var1);

    int  projInfoExportCount(SearchCondition var1);


    /**
     * 季度填报查询需要填报的基金数据
     * @return
     */
   List<FundInfoModel> selectAllFundList(SearchCondition var1);


   List<DataQuarterModel> selectFundInfoList(String keyWord);

    List<DataQuarterModel> getSendFundList(String keyWord);


    List<DataQuarterModel> selectSendAdminList(String keyWord);


}