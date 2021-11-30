package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.ExportZipListModel;
import com.ppmg.ei.model.PerfManageFundModel;
import com.ppmg.ei.model.PerformanceModel;
import com.ppmg.ei.service.PerfManageFundService;
import com.ppmg.ei.service.PerfService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 描述 [Service.impl]
 *
 * @author null
 * @date 2019-12-12 16:52
 */
@Component("perfManageFundService")
@Service(interfaceClass = PerfManageFundService.class)
public class PerfManageFundServiceImpl extends BaseServiceImpl<PerfManageFundModel> implements PerfManageFundService {
    @Resource
    private PerfService perfService;
    @Reference(check = false)
    private PerfManageFundService perfManageFundService;

    public PerfManageFundServiceImpl() {
        this.setNamespace("PerfManageFund");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insertPerfManageFundList(List<PerfManageFundModel> fundList, String userId) {
        System.out.println("------------新增绩效与基金关联关系----------------");
        int count = 0;
        //查询过滤的基金
        PerfManageFundModel perfManageFundModel = null;
        Date thisStartTime = null;
        Date thisEndTime = null;
        String pPerId = null;
        String year = null;
        if (fundList != null && fundList.size() > 0) {
            pPerId = fundList.get(0).getpPerId();
            year = fundList.get(0).getYearCount();
            thisStartTime = fundList.get(0).getThisStartTime();
            thisEndTime = fundList.get(0).getThisEndTime();
            count = fundList.size();
            PerfManageFundModel m = new PerfManageFundModel();
            for (PerfManageFundModel fund : fundList) {
                m = new PerfManageFundModel();
                m.setYearCount(year);
                m.setFundId(fund.getFundId());
                List<PerfManageFundModel> ls2 = perfManageFundService.selectList(m);
                if (ls2.size() == 0) {
                    fund.setId(UUID.randomUUID().toString());
                    fund.setpPerId(pPerId);
                    fund.setYearCount(year);
                    fund.setThisStartTime(thisStartTime);
                    fund.setThisEndTime(thisEndTime);
                    fund.setUpdateBy(userId);
                    fund.setUpdateDt(new Date());
                    fund.setCreateBy(userId);
                    fund.setCreateDt(new Date());
                    perfManageFundService.insert(fund);
                }
            }
        }
        //更新主表状态为已发布
		PerformanceModel performanceModel = perfService.selectById(pPerId);
        if(performanceModel != null ){
        	if(performanceModel.getFundCount() != null){
				count = count + Integer.valueOf(performanceModel.getFundCount());
			}
			//已发布
			performanceModel.setpStatus("1");
			//考核基金数
			performanceModel.setFundCount(count + "");
			perfService.update(performanceModel);
		}
        return count;
    }

    @Override
    public List<ExportZipListModel> exportZip(String pPerId, String fundId) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("pPerId", pPerId);
        params.put("fundId", fundId);
        return  this.selectList("exportZip", params);
    }
}
