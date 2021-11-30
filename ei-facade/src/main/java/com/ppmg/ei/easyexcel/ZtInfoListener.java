package com.ppmg.ei.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.ppmg.ei.Util.RegexUtils;
import com.ppmg.ei.model.DatNewFunctionExcel;
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
public class ZtInfoListener extends ExcelListener {

    private List<DatNewFunctionExcel> dataList = new ArrayList<>();

    /**
     * 通过 AnalysisContext 对象还可以获取当前 sheet，当前行等数据
     */
    @Override
    public void invoke(Object object, AnalysisContext context) {
        if (StringUtils.isNotBlank(getError())) {
            return;
        }
        DatNewFunctionExcel model = (DatNewFunctionExcel) object;

        Integer currentRowNum = context.getCurrentRowNum() + 1;
        if (StringUtils.isEmpty(model.getInveId())) {
            setError("第" + currentRowNum + "行，缺少出资主体id");
            return;
        }
        if (StringUtils.isNotEmpty(model.getSetupDt())) {
            if(!RegexUtils.isDate(model.getSetupDt().trim())){
                setError("第" + currentRowNum + "行，成立时间 不符合规则，请重新输入");
                return;
            }
        }

        if (StringUtils.isNotEmpty(model.getOccurDt())) {
            if(!RegexUtils.isDate(model.getOccurDt().trim())){
                setError("第" + currentRowNum + "行，出资时间 不符合规则，请重新输入"+model.getOccurDt());
                return;
            }
        }
       if (StringUtils.isNotEmpty(model.getOccurDt1())) {
            if(!RegexUtils.isDate(model.getOccurDt1().trim())){
                setError("第" + currentRowNum + "行，退出时间 不符合规则，请重新输入");
                return;
            }
        }

        dataList.add(model);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //do something
    }

    @Override
    public List<DatNewFunctionExcel> getDataList() {
        return dataList;
    }
}