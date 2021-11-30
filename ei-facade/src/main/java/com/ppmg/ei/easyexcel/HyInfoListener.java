package com.ppmg.ei.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.ppmg.ei.model.HyNewFunctionExcel;
import easyexcel.utils.ExcelListener;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于excel导入业务管理员的监听器
 *
 * @author yuyongjun
 * @date 2019/6/22 11:18
 */
public class HyInfoListener extends ExcelListener {

    private List<HyNewFunctionExcel> dataList = new ArrayList<>();

    /**
     * 通过 AnalysisContext 对象还可以获取当前 sheet，当前行等数据
     */
    @Override
    public void invoke(Object object, AnalysisContext context) {
        if (StringUtils.isNotBlank(getError())) {
            return;
        }
        HyNewFunctionExcel model = (HyNewFunctionExcel) object;

        dataList.add(model);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //do something
    }

    @Override
    public List<HyNewFunctionExcel> getDataList() {
        return dataList;
    }
}