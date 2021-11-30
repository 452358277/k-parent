package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;
import com.founder.ssm.core.vo.BaseResponse;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.model.BaseInfoExtendModel;
import com.ppmg.ei.bean.ProjInfoSearchBean;
import com.ppmg.ei.easyexcel.BusinessManagerImportRequest;
import com.ppmg.ei.easyexcel.FundManagerImportRequest;
import com.ppmg.ei.easyexcel.HyBusinessManagerImportRequest;
import com.ppmg.ei.easyexcel.ZtBusinessManagerImportRequest;
import com.ppmg.ei.model.*;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface ProjInfoService extends BaseService<ProjInfoModel>  {

    /**
     * 根据项目ID获取项目基本信息
     * @param projId
     * @return
     */
    public ProjInfoModel getProjBaseInfoById(String projId);

    /**
     * 根据项目ID获取项目详细信息
     * @param projId
     * @return
     */
    public ProjInfoModel getProjInfoById(String projId);

    /**
     * 根据基金ID获取基金管理的项目详细信息
     * @param fundId
     * @return
     */
    public List<ProjInfoModel> getProjListByFundId(String fundId);

    <ProjInfoModel> PageInfo<ProjInfoModel> selectListPageByFundId(SearchCondition var1);

    /**
     * 获取子基金项目列表
     * @param searchCondition
     * @return
     */
    <ProjInfoModel> PageInfo<ProjInfoModel> selectSubFundListPage(SearchCondition searchCondition);

    /**
     * 新增项目与项目经理
     * @param model
     * @return
     */
    void insertProjInfoAndMember(ProjInfoModel model, String memberId, String memberName, String loginUserId, String orgId) throws Exception ;

    void insertprojInfo(ProjInfoModel projInfoModel);

    void updateprojInfo(ProjInfoModel projInfoModel);


    <E> PageInfo<E> selectProjInfoPage(SearchCondition var1);


    <E> PageInfo<E> selectProjInfoListPage(SearchCondition var1);

    <E> PageInfo<E> selectbussinessListPage(SearchCondition var1);

    List<ProjInfoModel> selectprojectInvestment(ProjInfoSearchBean projInfoSearchBean);

    List<BussniessResultModel> selectCountBussunessName(ProjInfoSearchBean projInfoSearchBean);

    List<SumProjInfo>  seleSumProjInfo(ProjInfoSearchBean projInfoSearchBean);

    <E> PageInfo<E> selectSpvListPage(SearchCondition var1);

    <E> PageInfo<E> selectdirectProjListPage(SearchCondition var1);

    <E> PageInfo<E> projectInvestedList(SearchCondition var1);

    <E> PageInfo<E> projectGovementList(SearchCondition var1);

    void insertProjFund(ProjInfoModel model, ProjAppInfoModel projAppInfoModel, BdTFitRegulationSelfModel bdTFitRegulationSelfModel, FundInfoModel fund, String userId, EntBaseInfoModel entInfo, String[] ids, String entId, BaseInfoExtendModel baseInfoExtend) throws Exception;

    void  updateProjFund(ProjInfoModel model, ProjAppInfoModel projAppInfoModel, BdTFitRegulationSelfModel bdTFitRegulationSelfModel, FundInfoModel fund, EntBaseInfoModel entBaseInfoModel, String[] ids, String userId, BaseInfoExtendModel baseInfoExtend) throws Exception;

    <E> PageInfo<E> selectProjListPage(SearchCondition var1);



    <E> PageInfo<E> selectProjListByPage(SearchCondition var1);



    ProjInfoModel selectByprojId(String projId);

    Map<String,Object> selectPayAmount(ProjInfoModel model);

    /**
     * 修改决策
     * @param model
     * @return
     */
    void updateProjApp(ProjAppInfoModel projAppInfoModel, FundInfoModel fund, String projType, String userId, String projObject, ProjInfoModel model, BdTFitRegulationSelfModel bdTFitRegulationSelfModel)throws Exception;


    void insertByFundName(BdTFitRegulationSelfModel bdTFitRegulationSelfModel, ProjInfoModel projInfo1, ProjAppInfoModel projAppInfoModel, String userId, List<String> labels, EntBaseInfoModel entBase, ProjQuitApplModel projQuitApplModel, XjlTPaymentRequestModel xjlTPaymentRequestModel)throws Exception;

    List<Map<String, Object>> getExcelInfo(String fileName, InputStream in);

    List<ProjInfoModel>  selectListByProjNo(ProjInfoModel projInfoModel);

    Double getsumCurrentAmount(String fundId);

    Double getsumIsExceptAmount(String fundId);

    Double getSpvSumAmount(String fundId);

    Double getSpvIsExceptSumAmount(String fundId);

    Double getsumCurrentC(String fundId);

    Double selectSumAmount(String fundId);

    Double selectExceptSumAmount(String fundId);

    Map<String,Object> getsumIsExpCurrentC(String fundId);

    Double getsumCurrentRaise(String fundId);

    Double getExSumPayAmount(String fundId);

    Map<String,Object> selectSum(String fundId);

    Map<String,Object> selectCountLavel(String fundId);

    Map<String,Object> selectTwoCountLavel(String fundId);

    Map<String,Object>  selectFourCountLavel(String fundId);

    void delateOld(String projId) throws  Exception;

    void updateProjOrOthers(ProjInfoModel proj, ProjAppInfoModel projApp, BdTFitRegulationSelfModel bd, ProjQuitApplModel projQ, XjlTPaymentRequestModel xj, String userId, List<String> labels)throws  Exception;

    void updateProjOrFundInfo(ProjInfoModel proj, ProjAppInfoModel projApp, BdTFitRegulationSelfModel bd, ProjQuitApplModel projQ, XjlTPaymentRequestModel xjl, String userId, FundInfoModel fund, FundInfoModel fundInfo) throws  Exception;

    void insertByName(ProjInfoModel projInfo1, FundInfoModel fundInfo, ProjAppInfoModel projApp, ProjQuitApplModel projQuitApplModel, FundInfoModel threeFundInfo, XjlTPaymentRequestModel xjlTPaymentRequestModel, String userId, String types, BdTFitRegulationSelfModel bd)throws  Exception;

    ProjInfoModel selectInfoProj(String projId);

    /**
     * 投管流程新增-内部用户新增
     *
     * **/
    void insertInternalProj(ProjInfoModel model, ProjAppInfoModel projApp, BdTFitRegulationSelfModel bdFit, String userId, String[] ids, ProjMemberModel projNanage, ProjMemberModel generalManager, List<ProjMemberModel> operators)throws Exception;

    /**
     * 投管流程修改-内部用户修改-
     *
     * **/
    void updateInternalProj(ProjInfoModel model, String userId, String[] ids, ProjMemberModel projNanage, ProjMemberModel generalManager, List<ProjMemberModel> operators, BdTFitRegulationSelfModel bdTFitRegulationSelfModel)throws Exception;
    /**
     * 投管流程-列表查询
     *
     * **/
    <E> PageInfo<E> getInternalProjList(SearchCondition var1);

    <E> PageInfo<E> getJchyProjList(SearchCondition var1);



    List<ProjInfoModel> selectListByInveId(ProjInfoModel projInfo);

    Double selectSumPayAmount(String fundId);

    List<ProjInfoModel> getNewProCode(String projCode);

    /**
     * 获取审核通过的拨付款
     *
     * **/
    Double selectPassPayAmount(String fundId);

    <E> PageInfo<E>  selectByFofInfoList(SearchCondition var1);

    <E> PageInfo<E>  selectFofDirectList(SearchCondition var1);

    void addProjOrOthers(ProjInfoModel proj, ProjAppInfoModel projApp, BdTFitRegulationSelfModel bd, ProjQuitApplModel projQ, XjlTPaymentRequestModel xj, String userId, List<String> labels)throws  Exception;

    void insertProjOrFundInfo(ProjInfoModel proj, ProjAppInfoModel projApp, BdTFitRegulationSelfModel bd, ProjQuitApplModel projQ, XjlTPaymentRequestModel xjl, String userId, FundInfoModel fund, FundInfoModel fundInfo) throws  Exception;

    List<ProjInfoModel> selectByInveList(ProjInfoModel projInfo);


    /**
     * 导入投项目（存在进行修改，不存在进行插入）
     *
     * @param request
     * @return
     */
    BaseResponse importBusinessManager(BusinessManagerImportRequest request);


    BaseResponse ztImportBusinessManager(ZtBusinessManagerImportRequest request)throws Exception;

//行业导入
    BaseResponse  hyImportBusinessManager(HyBusinessManagerImportRequest request)throws Exception;




    /**
     * 导入基金（存在进行修改，不存在进行插入）
     *
     * @param request
     * @return
     */
    BaseResponse importFundManager(FundManagerImportRequest request);


    <E> PageInfo<E> getJcProjList(SearchCondition var1);


    /**
     * 二级基金投项目-标签模块
     *
     * @param
     * @return
     */
    <E> PageInfo<E> getReportList(SearchCondition var1);


    /**
     * 三级级基金投项目-标签模块
     *
     * @param
     * @return
     */
    <E> PageInfo<E> selectFundInvePage(SearchCondition var1);


    /**
     * 统计简报
     *
     * @param
     * @return
     */
    <E> PageInfo<E> selectSumInvePage(SearchCondition var1);

    List<ProjInfoModel> selectImportInveList(Map<String, Object> param);

    void delThreeProj(String projId, String appId);


    void saveInfoE(ProjAppInfoModel appModel, ProjInfoModel model) throws  Exception;

    void updateInfoE(ProjAppInfoModel appModel, ProjInfoModel model) throws  Exception;

    <E> PageInfo<E> selectListInfoPage(SearchCondition var1);


    List<ProjInfoModel> selectNumList(ProjInfoModel model);


     List<ProjInfoModel> selectAllProjInfo(SearchCondition var1);



    <E> PageInfo<E> selectAllProjList(SearchCondition var1);
}