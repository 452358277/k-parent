package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.ExportZipListModel;
import com.ppmg.ei.model.PerfManageFundModel;

import java.util.List;

/**
 * 描述 [Service]
 * @author null
 * @date 2019-12-12 16:52
 */
public interface PerfManageFundService extends BaseService<PerfManageFundModel>  {

    /**
     * 点击发布
     * @param model
    	 * @param userId
     * @return int
     * @author zhaokuiyu
     * @date 2020/4/17 14:03
     * @creed: The best time to plant a tree is ten years ago, followed by now
     */
    public int insertPerfManageFundList(List<PerfManageFundModel> model, String userId);

    List<ExportZipListModel> exportZip (String pPerId, String fundId);

}
