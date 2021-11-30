package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.PerfManageFundService;
import com.ppmg.ei.service.PerfService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Component("perfService")
@Service(interfaceClass = PerfService.class)
public class PerfServiceImpl extends BaseServiceImpl<PerformanceModel> implements PerfService {
    public PerfServiceImpl() {
        this.setNamespace("performance");
    }

    @Resource
    private PerfService perfService;
    @Reference(check = false)
    private PerfManageFundService perfManageFundService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertPerManage(PerformanceModel performanceModel) {
        //增加主表
        String perId = performanceModel.getpPerID();
        String userId = performanceModel.getUpdateBy();
        if ("0".equals(performanceModel.getTag())) {
            //草稿
            performanceModel.setpStatus("0");
        } /*else if ("1".equals(performanceModel.getTag())) {
            //选择基金发布
            performanceModel.setpStatus("1");
            //查看考核的所有基金条数
            List<PerfManageFundModel> pff = performanceModel.getPerfManageFundModel();
            if (pff != null && pff.size() > 0) {
                int count = this.perIdFunfId(perId, performanceModel.getpYear(), userId, pff);
                performanceModel.setFundCount(count + "");
            }
        } */else if ("2".equals(performanceModel.getTag())) {
            //待发布
            performanceModel.setpStatus("2");
        }
        performanceModel.setpPerID(perId);
        performanceModel.setCreateDt(new Date());
        performanceModel.setUpdateDt(new Date());
        //增加主表
        perfService.insert(performanceModel);
        //一级指标
        List<PerfOneNormModel> one = performanceModel.getPerfOneNormModel();
        //二级指标
        List<PerfSecondNormModel> two = null;
        //三级指标
        List<PerfThreeNormModel> three = null;
        if (one != null && one.size() > 0) {
            for (int i = 0; i < one.size(); i++) {
                String oNormId = randomKeyOne("PERF_ONE_NORM_ID_SEQ");
                //关联主表
                one.get(i).setpPerId(perId);
                one.get(i).setoNormId(oNormId);
                one.get(i).setCreateBy(userId);
                one.get(i).setCreateDt(new Date());
                one.get(i).setUpdateBy(userId);
                one.get(i).setUpdateDt(new Date());
                //增加一级
                perfService.insertOneNorm(one.get(i));
                two = one.get(i).getPerfSecondNormModel();
                if (two != null && two.size() > 0) {
                    for (int j = 0; j < two.size(); j++) {
                        String sNormId = randomKeyTwo("PERF_SECOND_NORM_ID_SEQ");
                        two.get(j).setsNormId(sNormId);
                        //关联id
                        two.get(j).setoNormId(oNormId);
                        two.get(j).setCreateBy(userId);
                        two.get(j).setCreateDt(new Date());
                        two.get(j).setUpdateBy(userId);
                        two.get(j).setUpdateDt(new Date());
                        //插入二级指标
                        perfService.insertSecondNorm(two.get(j));
                        three = two.get(j).getPerfThreeNormModel();
                        if (three != null && three.size() > 0) {
                            for (int k = 0; k < three.size(); k++) {
                                String tNormId = randomKey("PERF_THREE_NORM_T_NORM_ID_SEQ");
                                three.get(k).settNormId(tNormId);
                                three.get(k).setsNormId(sNormId);
                                three.get(k).setCreateBy(userId);
                                three.get(k).setCreateDt(new Date());
                                three.get(k).setUpdateBy(userId);
                                three.get(k).setUpdateDt(new Date());
                                //插入三级指标
                                perfService.insertThreeNorm(three.get(k));
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updatePerManage(PerformanceModel performanceModel) {
        String id = performanceModel.getpPerID();
        //==============================先删除============================
        //查询一级指标
        List<PerfOneNormModel> perfOneNormModel = this.selectList("PerfOneNormModelId", id);
        if (perfOneNormModel != null && perfOneNormModel.size() > 0) {
            String oNormId = null;
            List<String> arrThree = null;
            for (int i = 0; i < perfOneNormModel.size(); i++) {
                arrThree = new ArrayList<>();
                oNormId = perfOneNormModel.get(i).getoNormId();
                //查询二级指标
                List<PerfSecondNormModel> perfSecondNormModel = this.selectList("PerfSecondNormModelId", oNormId);
                if (perfSecondNormModel != null && perfSecondNormModel.size() > 0) {
                    for (int j = 0; j < perfSecondNormModel.size(); j++) {
                        arrThree.add(perfSecondNormModel.get(j).getsNormId());
                    }
                    //根据二级指标id，删除三级指标
                    delete("deleteThreeNormBatch", arrThree);
                }
            }
            //根据一级指标id，删除二级指标
            List<String> arr = new ArrayList<>();
            for (int i = 0; i < perfOneNormModel.size(); i++) {
                arr.add(perfOneNormModel.get(i).getoNormId());
            }
            delete("deleteSecondNormBatch", arr);
            //根据主表id，删除一级指标
            delete("deleteOneNormBatch", id);
        }
        //根据主表id，删除主表
        delete("deletePerAllBatch", id);

        //========================重新添加=====================
        //增加主表
        String perId = performanceModel.getpPerID();
        String userId = performanceModel.getUpdateBy();
        if ("0".equals(performanceModel.getTag())) {
            //草稿
            performanceModel.setpStatus("0");
        }/* else if ("1".equals(performanceModel.getTag())) {
            //发布
            performanceModel.setpStatus("1");
            //插入主表与基金关联关系
            List<PerfManageFundModel> pff = performanceModel.getPerfManageFundModel();
            if (pff != null && pff.size() > 0) {
                int count = this.perIdFunfId(perId, performanceModel.getpYear(), userId, pff);
                performanceModel.setFundCount(count + "");
            }
        }*/else if ("2".equals(performanceModel.getTag())) {
            //待发布
            performanceModel.setpStatus("2");
        }
        performanceModel.setpPerID(perId);
        performanceModel.setCreateDt(new Date());
        performanceModel.setUpdateDt(new Date());
        performanceModel.setCreateBy(userId);
        performanceModel.setUpdateBy(userId);
        //增加主表
        perfService.insert(performanceModel);
        //一级指标
        List<PerfOneNormModel> one = performanceModel.getPerfOneNormModel();
        //二级指标
        List<PerfSecondNormModel> two = null;
        //三级指标
        List<PerfThreeNormModel> three = null;
        if (one != null && one.size() > 0) {
            for (int i = 0; i < one.size(); i++) {
                String oNormId = randomKeyOne("PERF_ONE_NORM_ID_SEQ");
                //关联主表
                one.get(i).setpPerId(perId);
                one.get(i).setoNormId(oNormId);
                one.get(i).setCreateBy(userId);
                one.get(i).setCreateDt(new Date());
                one.get(i).setUpdateBy(userId);
                one.get(i).setUpdateDt(new Date());
                //增加一级
                perfService.insertOneNorm(one.get(i));
                two = one.get(i).getPerfSecondNormModel();
                if (two != null && two.size() > 0) {
                    for (int j = 0; j < two.size(); j++) {
                        String sNormId = randomKeyTwo("PERF_SECOND_NORM_ID_SEQ");
                        two.get(j).setsNormId(sNormId);
                        //关联id
                        two.get(j).setoNormId(oNormId);
                        two.get(j).setCreateBy(userId);
                        two.get(j).setCreateDt(new Date());
                        two.get(j).setUpdateBy(userId);
                        two.get(j).setUpdateDt(new Date());
                        //插入二级指标
                        perfService.insertSecondNorm(two.get(j));
                        three = two.get(j).getPerfThreeNormModel();
                        if (three != null && three.size() > 0) {
                            for (int k = 0; k < three.size(); k++) {
                                String tNormId = randomKey("PERF_THREE_NORM_T_NORM_ID_SEQ");
                                three.get(k).settNormId(tNormId);
                                three.get(k).setsNormId(sNormId);
                                three.get(k).setCreateBy(userId);
                                three.get(k).setCreateDt(new Date());
                                three.get(k).setUpdateBy(userId);
                                three.get(k).setUpdateDt(new Date());
                                //插入三级指标
                                perfService.insertThreeNorm(three.get(k));
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 点击发布关联绩效主表跟基金关系
     *
     * @return
     * @author zhaokuiyu
     * @date 2019/12/12 15:51
     * @creed: The best time to plant a tree is ten years ago, followed by now
     */
    public int perIdFunfId(String pPerId, String year, String userId, List<PerfManageFundModel> fundList) {
        System.out.println("------------新增绩效与基金关联关系----------------" + pPerId + ";--" + year + ";--" + userId);
        int count = 0;
        //查询过滤的基金
        PerfManageFundModel perfManageFundModel = null;
        Date touStartTime = null;
        Date touEndTime = null;
        if (fundList != null && fundList.size() > 0) {
            touStartTime = fundList.get(0).getTouStartTime();
            touEndTime = fundList.get(0).getTouEndTime();
            count = fundList.size();
            PerfManageFundModel m = new PerfManageFundModel();
            for (PerfManageFundModel fund : fundList) {
                m = new PerfManageFundModel();
                m.setYearCount(year);
                m.setFundId(fund.getFundId());
                List<PerfManageFundModel> ls2 = perfManageFundService.selectList(m);
                if (ls2.size() == 0) {
                    perfManageFundModel = new PerfManageFundModel();
                    perfManageFundModel.setId(UUID.randomUUID().toString());
                    perfManageFundModel.setThisStartTime(touStartTime);
                    perfManageFundModel.setThisEndTime(touEndTime);
                    perfManageFundModel.setpPerId(pPerId);
                    perfManageFundModel.setYearCount(year);
                    perfManageFundModel.setUpdateBy(userId);
                    perfManageFundModel.setUpdateDt(new Date());
                    perfManageFundModel.setCreateBy(userId);
                    perfManageFundModel.setCreateDt(new Date());
                    perfManageFundService.insert(perfManageFundModel);
                }
            }
        }
        return count;
    }

    @Override
    public String getKey(String sequenceName) {
        String id = null;
        id = String.valueOf(this.selectOne("getPerfManageIdSeq", sequenceName));
        return id;
    }

    public static String randomConition() {
        String id = null;
        String id1 = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        int id2 = (int) ((Math.random() * 9 + 1) * 10000);
        id = id1 + id2;
        System.out.println("###---->>" + id);
        return id;
    }

    @Override
    public PerformanceModel selectPerfDetailSer(String id) {
        return (PerformanceModel) this.selectOne("selectPerfDetailSer", id);
    }

    @Override
    public PerformanceModel selectPerfDetailSerYear(String year) {
        return (PerformanceModel) this.selectOne("selectByPerManageIdYear", year);
    }

    @Override
    public PerformanceModel selectPerfDetailSerId(String id) {
        return (PerformanceModel) this.selectOne("selectByPerManageId", id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deletePerAll(String[] idd) {
        try {
            for (String id : idd) {
                //查询一级指标
                List<PerfOneNormModel> perfOneNormModel = this.selectList("PerfOneNormModelId", id);
                if (perfOneNormModel != null && perfOneNormModel.size() > 0) {
                    String oNormId = null;
                    List<String> arrThree = null;
                    for (int i = 0; i < perfOneNormModel.size(); i++) {
                        arrThree = new ArrayList<>();
                        oNormId = perfOneNormModel.get(i).getoNormId();
                        //查询二级指标
                        List<PerfSecondNormModel> perfSecondNormModel = this.selectList("PerfSecondNormModelId", oNormId);
                        if (perfSecondNormModel != null && perfSecondNormModel.size() > 0) {
                            for (int j = 0; j < perfSecondNormModel.size(); j++) {
                                arrThree.add(perfSecondNormModel.get(j).getsNormId());
                            }
                            //根据二级指标id，删除三级指标
                            delete("deleteThreeNormBatch", arrThree);
                        }
                    }
                    //根据一级指标id，删除二级指标
                    List<String> arr = new ArrayList<>();
                    for (int i = 0; i < perfOneNormModel.size(); i++) {
                        arr.add(perfOneNormModel.get(i).getoNormId());
                    }
                    delete("deleteSecondNormBatch", arr);
                    //根据主表id，删除一级指标
                    delete("deleteOneNormBatch", id);
                }
                //根据主表id，删除主表
                delete("deletePerAllBatch", id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override//查询一级
    public List<PerfOneNormModel> selectPerfOneNormModel(String pPerId) {
        return this.selectList("PerfOneNormModelId", pPerId);
    }

    @Override//一级详情
    public PerfOneNormModel selectPerfOneNormDetails(String oNormId) {
        return (PerfOneNormModel) this.selectOne("selectPerfOneNormDetails", oNormId);
    }

    @Override//更新一级
    public void updatePerfOneNorm(PerfOneNormModel perfOneNormModel) {
        this.update("updatePerOneNorm", perfOneNormModel);
    }

    @Override
    public Integer insertOneNorm(PerfOneNormModel perfOneNormModel) {
        List<PerfOneNormModel> list = new ArrayList<>();
        if (perfOneNormModel.getCreateBy() == null) {
            perfOneNormModel.setCreateBy("创建人");
        }
        if (perfOneNormModel.getUpdateBy() == null) {
            perfOneNormModel.setUpdateBy("更新人");
        }
        list.add(perfOneNormModel);
        return this.insert("insertOneNorm", list);
    }

    @Override
    public void delectOneNorm(String oNormId) {
        String[] arr = oNormId.split(",");
        delete("deleteOneNormOnormId", arr);
    }

    @Override//查询二级
    public List<PerfSecondNormModel> selectPerfSecondNormModel(String oNormId) {
        return this.selectList("PerfSecondNormModelId", oNormId);
    }

    @Override//二级详情
    public PerfSecondNormModel selectPerfSecondDetails(String sNormId) {
        return (PerfSecondNormModel) this.selectOne("selectPerfSecondDetails", sNormId);
    }

    @Override//更新二级
    public void updatePerfSecondNorm(PerfSecondNormModel perfSecondNormModel) {
        this.update("updatePerSecondNorm", perfSecondNormModel);
    }

    @Override
    public Integer insertSecondNorm(PerfSecondNormModel perfSecondNormModel) {
        List<PerfSecondNormModel> list = new ArrayList<>();
        if (perfSecondNormModel.getCreateBy() == null) {
            perfSecondNormModel.setCreateBy("创建人");
        }
        if (perfSecondNormModel.getUpdateBy() == null) {
            perfSecondNormModel.setUpdateBy("更新人");
        }
        list.add(perfSecondNormModel);
        return this.insert("insertSecondNorm", list);
    }

    @Override//根据sNormId删除
    public void delectSecondNorm(String sNormId) {
        String[] arr = sNormId.split(",");
        delete("deleteSecondNormSnormId", arr);
    }

    @Override//三级列表
    public List<PerfThreeNormModel> selectPerfThreeNormModel(String sNormId) {
        return this.selectList("PerfThreeNormModelId", sNormId);
    }

    @Override//三级详情
    public PerfThreeNormModel selectPerfThreeNormDetails(String tNormId) {
        return (PerfThreeNormModel) this.selectOne("selectPerfThreeNormDetails", tNormId);
    }

    @Override//更新三级
    public void updatePerfThreeNorm(PerfThreeNormModel perfThreeNormModel) {
        this.update("updatePerThreeNorm", perfThreeNormModel);
    }

    @Override
    public Integer insertThreeNorm(PerfThreeNormModel perfThreeNormModel) {
        String id = randomKey("PERF_THREE_NORM_T_NORM_ID_SEQ");
        perfThreeNormModel.settNormId(id);//主键
        List<PerfThreeNormModel> list = new ArrayList<>();
        if (perfThreeNormModel.getCreateBy() == null) {
            perfThreeNormModel.setCreateBy("创建人");
        }
        if (perfThreeNormModel.getUpdateBy() == null) {
            perfThreeNormModel.setUpdateBy("更新人");
        }
        list.add(perfThreeNormModel);
        return this.insert("insertThreeNorm", list);
    }

    @Override
    public void delectThreeNorm(String id) {
        String[] arr = id.split(",");
        delete("deleteThreeNormTnormId", arr);
    }

    @Override
    public List<PerformanceModel> selectReviewSelfReviewLists(String name, String year) {
        return null;
    }

    @Override//基金绩效考核审核管理，列表分页查询-
    public PageInfo<PerformanceModel> selectListPageAssess1(SearchCondition searchCondition) {
        PageHelper.startPage(searchCondition.getCurrPage(), searchCondition.getPageSize());
        List<PerformanceModel> list = this.baseDao.selectList("performance", "selectListPage1", searchCondition);
        PageInfo<PerformanceModel> page = new PageInfo(list);
        return page;
    }

    @Override//审核，自评详细列表分页查询
    public PageInfo<PerfScoreModel> selectListPageAssess(SearchCondition searchCondition, String type, String userId) {
        PageHelper.startPage(searchCondition.getCurrPage(), searchCondition.getPageSize());
        List<PerfScoreModel> list = null;
        if ("1".equals(type)) {
            //审核
            list = this.baseDao.selectList("performance", "selectListPage_audit", searchCondition);
        } else if ("0".equals(type)) {
            //自评
            searchCondition.addParam("USER_ID", userId);
            list = this.baseDao.selectList("performance", "selectListPage_SelfAssessment", searchCondition);
        }
        PageInfo<PerfScoreModel> page = new PageInfo(list);
        return page;
    }

    @Override
    public PerformanceModel selectPerfFundDetail(SearchCondition searchCondition, String psId) {
        PerformanceModel returnPm = null;
        List<PerfScoreModelDetails> pd_list = null;
        List<PerformanceModel> pm = this.baseDao.selectList("performance", "selectPerfFundDetailOne", searchCondition);
        if (pm.size() > 0) {
            returnPm = pm.get(0);
        }

        /*//查询分数表
        if (psId != "" && psId != null) {
            pd_list = this.selectList("searchPointList", psId);
        }
        if(pm != null && pm.size() > 0){
            returnPm = pm.get(0);
            if (pd_list != null && pd_list.size() > 0) {
                returnPm.setPerfScoreModelDetails(pd_list);
            }
        }*/
        return returnPm;
    }

    @Override
    public void insertPerfScores(PerfScoreModel perfScoreModel) {
        List<PerfScoreModel> pm = new ArrayList<>();
        pm.add(perfScoreModel);
        this.insert("insertPerfScores", pm);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertPointLists(PerfScoreModel perfScoreModel) {
        Double pointAll = 0.0;//审核总得分
        Double ziPointAll = 0.0;//自评总得分
        List<PerfScoreModelDetails> new_list = perfScoreModel.getPerfScoreModelDetails();
        if (new_list != null && new_list.size() > 0) {
            for (PerfScoreModelDetails pd : new_list) {
                if (pd.getPsdAuditScore() != null) {
                    double shen = pd.getPsdAuditScore();
                    pointAll = pointAll + shen;//审核总得分
                }
                if (pd.getPsdOneselfScore() != null) {
                    double zi = pd.getPsdOneselfScore();
                    ziPointAll = ziPointAll + zi;//自评总得分
                }
            }
        }
        String psId = perfScoreModel.getPsId();
        if (StringUtils.isNotEmpty(psId)) {
            //更新分數表
            //PerfScoreModel listp = new PerfScoreModel();
            //listp.setPsId(psId);
            if (pointAll > 0) {//审核
                perfScoreModel.setPsAssessYesno(perfScoreModel.getPsAssessYesno());//是否考核（0草稿，1考核）
                perfScoreModel.setPsAuditScore(pointAll);//考核总得分
            }
            if (ziPointAll > 0) {//自评
                perfScoreModel.setPsOneselfYesno(perfScoreModel.getPsOneselfYesno());//是否自评（0草稿，1自评）
                perfScoreModel.setPsOneselfScore(ziPointAll);//自评总得分
            }
            this.update("updatePerfScores", perfScoreModel);
            //更新分數詳情表
            if (new_list != null && new_list.size() > 0) {
                for (PerfScoreModelDetails lists : new_list) {
                    this.update("updatePointList", lists);
                }
            }
        } else {
            //先插入分数表
            String ps = randomKey2();
            List<PerfScoreModel> pm_list = new ArrayList<>();
            PerfScoreModel pm = new PerfScoreModel();
            pm.setPsId(ps);//主键
            pm.setpPerId(perfScoreModel.getpPerId());
            pm.setFundId(perfScoreModel.getFundId());
            if (pointAll > 0) {//审核
                pm.setPsAssessYesno(perfScoreModel.getPsAssessYesno());//是否考核
                pm.setPsAuditScore(pointAll);//考核总得分
            }
            if (ziPointAll > 0) {//自评
                pm.setPsOneselfYesno(perfScoreModel.getPsOneselfYesno());//是否自评
                pm.setPsOneselfScore(ziPointAll);//自评总得分
            }
            pm.setCreateBy(perfScoreModel.getUpdateBy());
            pm.setUpdateBy(perfScoreModel.getUpdateBy());
            pm_list.add(pm);
            this.insert("insertPerfScores", pm_list);
            //插入分數詳情表
            String id = null;
            if (new_list != null && new_list.size() > 0) {
                for (PerfScoreModelDetails pd : new_list) {
                    id = randomKey2();
                    pd.setPsdId(id);
                    pd.setPsId(ps);
                    pd.setCreateBy(perfScoreModel.getUpdateBy());
                    pd.setUpdateBy(perfScoreModel.getUpdateBy());
                }
                this.insert("insertPointList", new_list);
            }
        }
    }

    @Override
    public List<FundInfoModel> selectFundLists() {
        List<FundInfoModel> list = this.baseDao.selectList("performance", "selectFundListCount", null);
        return list;
    }

    /**
     * 基金绩效考核导出
     * @param performanceModel
     * @return
     */
    @Override public  List<PerformanceExportModel> selectPerformanceExportList(PerformanceModel performanceModel) {
        return this.baseDao.selectList("performance", "selectPerformanceExportList", performanceModel);
    }

    @Override public Map<String, List<String[]>> selectPerformanceExport(SearchCondition searchCondition, String pPerId, String[] title) {
        Map<String, List<String[]>> map = new LinkedHashMap<>();
        //final String[] title = new String[] { "基金名称", "一级指标", "二级指标", "三级指标", "评分细则", "评价内容", "自评得分", "审核得分" };
        //考核基金列表
        List<PerfScoreModel> list = this.baseDao.selectList("performance", "selectListPage_audit", searchCondition);
        if (null != list && !list.isEmpty()) {
            list.forEach(li -> {
                List<String[]> datas = new LinkedList<>();
                //PerformanceModel performanceModel = perfService.selectPerfDetailSer(li.getpPerId());
                searchCondition.addParam("FUND_ID", li.getFundId());
                PerformanceModel performanceModel = perfService.selectPerfFundDetail(searchCondition, null);
                if (performanceModel == null) {
                    //查詢一級，二級，三級，指標
                    performanceModel = perfService.selectPerfDetailSer(pPerId);
                }

                if(performanceModel != null){
                    List<PerfOneNormModel> perfOneNormModel = performanceModel.getPerfOneNormModel();
                    if(perfOneNormModel != null) {
                        perfOneNormModel.forEach(pom -> {
                            String[] data = new String[title.length];
                            data[0] = li.getFundName();
                            data[1] = pom.getoOneName();
                            handleSecond(pom.getPerfSecondNormModel(), datas, data);
                        });
                    }
                }else{
                    String[] data = new String[title.length];
                    data[0] = li.getFundName();
                    datas.add(data);
                }
                map.put(li.getFundName(),datas);
            });
        }
        return map;
    }

    public void handleSecond(List<PerfSecondNormModel> perfSecondNormModel ,List<String[] > datas,String[] data){
        if(perfSecondNormModel == null){
            return;
        }
        perfSecondNormModel.forEach(p->{
            String [] da = data.clone();
            da[2] = p.getsTwoName();
            List<PerfThreeNormModel> perfThreeNormModel = p.getPerfThreeNormModel();
            handleThree(perfThreeNormModel,datas,da);
            // datas.add(da);
        });

    }

    public void handleThree(List<PerfThreeNormModel> perfThreeNormModel ,List<String[] > datas,String[] data){
        if(perfThreeNormModel == null){
            return;
        }
        perfThreeNormModel.forEach(pfm ->{
            String [] da = data.clone();
            da[3] = pfm.gettThreeName();
            da[4] = pfm.gettSuggest();
            da[5] = pfm.gettExplain();
            da[6] = pfm.getPsdOneselfScore();
            da[7] = pfm.getPsdAuditScore();
            datas.add(da);
        });

    }

    /**
     * 主
     *
     * @param sequenceName
     * @return
     */
    public String randomKeyPer(String sequenceName) {
        String id = null;
        id = String.valueOf(this.selectOne("getPerfManageIdSeq", sequenceName));
        return id;
    }

    /**
     * 一級
     *
     * @param sequenceName
     * @return
     */
    public String randomKeyOne(String sequenceName) {
        String id = null;
        id = String.valueOf(this.selectOne("getPerfOneNormIdSeq", sequenceName));
        return id;
    }

    /**
     * 二級
     *
     * @param sequenceName
     * @return
     */
    public String randomKeyTwo(String sequenceName) {
        String id = null;
        id = String.valueOf(this.selectOne("getperfSecondNormIdSeq", sequenceName));
        return id;
    }

    /**
     * 生成三級主键
     *
     * @param sequenceName
     * @return
     */
    public String randomKey(String sequenceName) {
        String id = null;
        id = String.valueOf(this.selectOne("getSeqNextVal_Perf", sequenceName));
        return id;
    }

    public static String randomKey2() {
        String id = null;
        String id1 = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        int id2 = (int) ((Math.random() * 9 + 1) * 10000);
        id = id1 + id2;
        System.out.println("###---->>" + id);
        return id;
    }


}
