package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.FundProGovernmentModel;
/**
 *
 * @return
 * @author zhaokuiyu
 * @date 2019/10/31 15:46
 * @creed: The best time to plant a tree is ten years ago, followed by now
 */
public interface FundProGovernmentService extends BaseService<FundProGovernmentModel> {
    //基金類型
    <E> PageInfo<E> selectallListPageFundType(SearchCondition var1);
    //基金形式
    <E> PageInfo<E> selectallListPageFundFrom(SearchCondition var1);
    //企业标签
    <E> PageInfo<E> selectallListPageEnterpriseLabel(SearchCondition var1);
    //基金属性,1：专项子基金:2：市场化子基金
    <E> PageInfo<E> selectallListPageFundGenre(SearchCondition var1);

    //----------------------------运营监控-报表-----------------------------
    /**江苏金财投资有限公司参股子基金信息表--->>江苏省新型产业创业投资引导基金
     * @author zhaokuiyu
     * @date 2019/12/2 15:45
     * @creed: The best time to plant a tree is ten years ago, followed by now
     */
    <E> PageInfo<E> selectListPageReportSon(SearchCondition var1);
    <E> PageInfo<E> selectListPageReportSon2(SearchCondition var1);
    /**江苏金财投资有限公司参股子基金信息表-->>江苏省省级产业发展资金
     * @author zhaokuiyu
     * @date 2019/12/2 15:45
     * @creed: The best time to plant a tree is ten years ago, followed by now
     */
    <E> PageInfo<E> selectListPageReportSonSecond(SearchCondition var1);
    <E> PageInfo<E> selectListPageReportSonSecond2(SearchCondition var1);
}
