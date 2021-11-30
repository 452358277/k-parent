package com.ppmg.ei.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.ppmg.ei.Util.RegexUtils;
import easyexcel.utils.ExcelListener;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FundListener extends ExcelListener {

    private List<FundManagerModel> dataList = new ArrayList<>();

    /**
     * 通过 AnalysisContext 对象还可以获取当前 sheet，当前行等数据
     */
    @Override
    public void invoke(Object object, AnalysisContext context) {
        if (StringUtils.isNotBlank(getError())) {
            return;
        }
        FundManagerModel model = (FundManagerModel) object;
        Integer currentRowNum = context.getCurrentRowNum() + 1;
        String errMsg="";
        if (StringUtils.isEmpty(model.getFundName())) {
            errMsg=errMsg+"第" + currentRowNum + "行，请输入四级基金名称--";
        }
        if (StringUtils.isEmpty(model.getIsSpv())) {
            errMsg=errMsg+"第" + currentRowNum + "行，请输入是否属于SPV基金--";
        }
        if(StringUtils.isNotEmpty(model.getIsSpv())){
            if("是".equals(model.getIsSpv())){
             if(StringUtils.isEmpty(model.getSpvType())){
                 errMsg=errMsg+"第" + currentRowNum + "行，请输入SPV基金种类--";
             }
                model.setIsSpv("0");
            }else{
                model.setIsSpv("1");
            }

        }
        if (StringUtils.isEmpty(model.getFundStruct())) {
            errMsg=errMsg+"第" + currentRowNum + "行，请输入四级基金形式--";
        }
        if (StringUtils.isEmpty(model.getProjStatus())) {
            errMsg=errMsg+"第" + currentRowNum + "行，请输入项目状态--";
        }
        if (StringUtils.isEmpty(model.getFundSts())) {
            errMsg=errMsg+"第" + currentRowNum + "行，请输入四级基金注册状态--";
        }
        if (StringUtils.isEmpty(model.getIsRecord())) {
            errMsg=errMsg+"第" + currentRowNum + "行，请输四级基金备案情况--";
        }
        if(StringUtils.isNotEmpty(model.getIsRecord())){
            if("是".equals(model.getIsRecord())){
                if (StringUtils.isEmpty(model.getRecordCode())) {
                    errMsg=errMsg+"第" + currentRowNum + "行，请输四级基金备案号--";
                }
            }

        }
        if (StringUtils.isEmpty(model.getIsCorr())) {
            errMsg=errMsg+"第" + currentRowNum + "行，请输是否关联交易--";
        }
        if (StringUtils.isEmpty(model.getInveName())) {
            errMsg=errMsg+"第" + currentRowNum + "行，请输三级基金名称--";
        }

        if (StringUtils.isEmpty(model.getIsJs())) {
            errMsg=errMsg+"第" + currentRowNum + "行，请输项目是否属于江苏省内企业--";
        }
        if (StringUtils.isEmpty(model.getPlanAmount())) {
            errMsg=errMsg+"第" + currentRowNum + "行，请输四级基金目标规模（万元）--";
        }

        if(StringUtils.isNotEmpty(model.getPlanAmount())){
            if(!RegexUtils.checkDecimals(model.getPlanAmount())){
                errMsg=errMsg+"第" + currentRowNum + "行，四级基金目标规模（万元）不符合规则，请重新输入--";
            }
        }

        if (StringUtils.isEmpty(model.getCurrentAmount())) {
            errMsg=errMsg+"第" + currentRowNum + "行，请输四级基金认缴总规模（万元)--";
        }
        if(StringUtils.isNotEmpty(model.getCurrentAmount())){
            if(!RegexUtils.checkDecimals(model.getCurrentAmount())){
                errMsg=errMsg+"第" + currentRowNum + "行，请输四级基金目标规模（万元），不符合规则，请重新输入--";
            }
        }

        if (StringUtils.isEmpty(model.getInveCurrentAmount())) {
            errMsg=errMsg+"第" + currentRowNum + "行，请输其中：三级基金认缴规模（万元)--";
        }
        if(StringUtils.isNotEmpty(model.getInveCurrentAmount())){
            if(!RegexUtils.checkDecimals(model.getInveCurrentAmount())){
                errMsg=errMsg+"第" + currentRowNum + "行，三级基金认缴规模（万元)，不符合规则，请重新输入--";
            }
        }


        if (StringUtils.isEmpty(model.getInveRaiseAmount())) {
            errMsg=errMsg+"第" + currentRowNum + "行，请输其中：三级基金实缴金额（万元）--";
        }

        if(StringUtils.isNotEmpty(model.getInveRaiseAmount())){
            if(!RegexUtils.checkDecimals(model.getInveRaiseAmount().trim())){
                errMsg=errMsg+"第" + currentRowNum + "行，三级基金实缴金额（万元），不符合规则，请重新输入--";
            }
        }


        if (StringUtils.isEmpty(model.getActualPayDate())) {
            errMsg=errMsg+"第" + currentRowNum + "行，请输投资时间";
        }
        if (StringUtils.isNotEmpty(model.getActualPayDate())) {
            if(!RegexUtils.isDate(model.getActualPayDate().trim())){
                errMsg=errMsg+"第" + currentRowNum + "行，投资时间 不符合规则，请重新输入";
                /*setError("第" + currentRowNum + "行，投资时间 不符合规则，请重新输入");
                return;*/
            }
        }
        if(StringUtils.isNotEmpty(errMsg)){
            setError(errMsg);
            return;
        }

        if(StringUtils.isNotEmpty(model.getIsDecisionPass())){
            if("是".equals(model.getIsDecisionPass())){
                model.setIsDecisionPass("1");
            }
            if("否".equals(model.getIsDecisionPass())){
                model.setIsDecisionPass("0");
            }
        }
        if(StringUtils.isNotEmpty(model.getIsCorr())){
            if("是".equals(model.getIsCorr())){
                model.setIsCorr("1");
            }
            if("否".equals(model.getIsCorr())){
                model.setIsCorr("0");
            }
        }


        if(StringUtils.isNotEmpty(model.getQuitType())){
            if("全部退出".equals(model.getQuitType())){
                model.setQuitType("1");
            }
            if("部分退出".equals(model.getQuitType())){
                model.setQuitType("2");
            }
        }

        if (StringUtils.isNotEmpty(model.getIsRecord())) {
            if("已备案".equals(model.getIsRecord())){
                model.setIsRecord("2");
            }
            if("未备案".equals(model.getIsRecord())){
                model.setIsRecord("0");
            }
        }
        String projStatus = "";
        if ("储备项目".equals(model.getProjStatus())) {
            projStatus = "1";

        }else if ("已报合规审查".equals(model.getProjStatus())) {
            projStatus = "2";
        }
        else if ("合规审查通过".equals(model.getProjStatus())) {
            projStatus = "3";
        }else if ("合规审查未通过".equals(model.getProjStatus())) {
            projStatus = "4";
        }else if ("投决通过".equals(model.getProjStatus())) {
            projStatus = "5";
        }else if ("投决未通过".equals(model.getProjStatus())) {
            projStatus = "6";
        }else if ("已出资".equals(model.getProjStatus())) {
            projStatus = "7";
        }else if ("部分退出".equals(model.getProjStatus())) {
            projStatus = "8";
        }else if ("已退出".equals(model.getProjStatus())) {
            projStatus = "9";
        }else if ("已终止".equals(model.getProjStatus())) {
            projStatus = "10";
        }else if ("已注销".equals(model.getProjStatus())) {
            projStatus = "11";
        }
        model.setProjStatus(projStatus);

        if("第一种".equals(model.getSpvType())){
            model.setSpvType("1");
        }
        if("第二种".equals(model.getSpvType())){
            model.setSpvType("2");
        }
        if("合伙制".equals(model.getFundStruct())){
            model.setFundStruct("1");
        }
        if("公司制".equals(model.getFundStruct())){
            model.setFundStruct("2");
        }
        if("契约制".equals(model.getFundStruct())){
            model.setFundStruct("3");
        }
        if("未注册".equals(model.getFundSts())){
            model.setFundSts("0");
        }
        if("已注册".equals(model.getFundSts())){
            model.setFundSts("2");
        }
        if("是".equals(model.getIsJs())){
            model.setIsJs("1");
        }
        if("否".equals(model.getIsJs())){
            model.setIsJs("0");
        }

        dataList.add(model);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //do something
    }

    @Override
    public List<FundManagerModel> getDataList() {
        return dataList;
    }


}
