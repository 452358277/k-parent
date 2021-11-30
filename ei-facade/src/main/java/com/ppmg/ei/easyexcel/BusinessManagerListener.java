package com.ppmg.ei.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.ppmg.ei.Util.RegexUtils;
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
public class BusinessManagerListener extends ExcelListener {

    private List<BusinessManagerModel> dataList = new ArrayList<>();

    /**
     * 通过 AnalysisContext 对象还可以获取当前 sheet，当前行等数据
     */
    @Override
    public void invoke(Object object, AnalysisContext context) {
        if (StringUtils.isNotBlank(getError())) {
            return;
        }
        BusinessManagerModel model = (BusinessManagerModel) object;


        Integer currentRowNum = context.getCurrentRowNum() + 1;

  /*      String[] templateTiles = new String[]{"业务管理员登录名(必填)","业务管理员姓名（必填）","电子邮箱","固定电话","移动电话","备注"};
        if(currentRowNum==1){
            if(!templateTiles[0].equals(model.getLoginName()) ||
                    !templateTiles[1].equals(model.getUserName()) ||
                    !templateTiles[2].equals(model.getEmail()) ||
                    !templateTiles[3].equals(model.getPhone()) ||
                    !templateTiles[4].equals(model.getMobilePhone())||
                    !templateTiles[5].equals(model.getDescription())
            ){
                setError("导入模板格式错误");
            }
            return;
        }*/

        if (StringUtils.isEmpty(model.getEntName())) {
            setError("第" + currentRowNum + "行，请输入企业名称");
            return;
        }

        if (StringUtils.isEmpty(model.getCreditCode())) {
            setError("第" + currentRowNum + "行，请输入企业统一社会信用代码");
            return;
        }
        if (StringUtils.isEmpty(model.getProjStatus())) {
            setError("第" + currentRowNum + "行，请输入项目状态");
            return;
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
        if (StringUtils.isEmpty(model.getFundName())) {
            setError("第" + currentRowNum + "行，请输入子基金名称");
            return;
        }
        if (StringUtils.isEmpty(model.getSocialCode())) {
            setError("第" + currentRowNum + "行，请输入子基金统一社会信用代码");
            return;
        }

        if (StringUtils.isEmpty(model.getIsZx())) {
            setError("第" + currentRowNum + "行，请输入是否属于中小企业");
            return;
        }
        List<String> label = new ArrayList<String>();
        if("是".equals(model.getIsZx())){
            label.add("3");
        }

        if (StringUtils.isEmpty(model.getIsGx())) {
            setError("第" + currentRowNum + "行，请输入是否属于高新技术行业");
            return;
        }
        if("是".equals(model.getIsGx())){
            label.add("1");
        }

        if (StringUtils.isEmpty(model.getIsYdyl())) {
            setError("第" + currentRowNum + "行，请输入是否属于国家一带一路战略");
            return;
        }
        if("是".equals(model.getIsYdyl())){
            label.add("2");
        }
        if (StringUtils.isEmpty(model.getIsZlxx())) {
            setError("第" + currentRowNum + "行，请输入是否属于战略新兴产业");
            return;
        }
        if("是".equals(model.getIsZlxx())){
            label.add("4");
        }
        model.setLabels(label);
        if (StringUtils.isEmpty(model.getProjProperty())) {
            setError("第" + currentRowNum + "行，请输入企业性质");
            return;
        }
        //企业性质
        String projPropertys = "";
        if ("国有独资".equals(model.getProjProperty())) {
            model.setProjProperty("2");
        }
        if ("国有控股".equals(model.getProjProperty())) {
            model.setProjProperty("3");
        }
        if ("民营".equals(model.getProjProperty())) {
            model.setProjProperty("1");
        }
        if("国有控制".equals(model.getProjProperty())){
            model.setProjProperty("4");
        }
        if (StringUtils.isEmpty(model.getIndustryName())) {
            setError("第" + currentRowNum + "行，请输入项目所属行业/领域");
            return;
        }
        if (StringUtils.isEmpty(model.getIndustryNameTwo())) {
            setError("第" + currentRowNum + "行，请输入项目所属行业/领域");
            return;
        }

      /*  if (StringUtils.isEmpty(model.getNewIndustryName())) {
            setError("第" + currentRowNum + "行，请输入战略性新兴行业");
            return;
        }

        if (StringUtils.isEmpty(model.getNewIndustryNameTwo())) {
            setError("第" + currentRowNum + "行，请输入战略性新兴行业");
            return;
        }*/
        if (StringUtils.isEmpty(model.getJs())) {
            setError("第" + currentRowNum + "行，请输入项是否属于江苏省内项目");
            return;
        }
        if (StringUtils.isEmpty(model.getFinaRounds())) {
            setError("第" + currentRowNum + "行，请输入被投企业融资轮次");
            return;
        }
        if (StringUtils.isEmpty(model.getIntendedAmount())) {
            setError("第" + currentRowNum + "行，请输入本轮投资金额（万元）");
            return;
        }
        if (StringUtils.isNotEmpty(model.getIntendedAmount())) {
            if(!RegexUtils.checkDecimals(model.getIntendedAmount().trim())){
                setError("第" + currentRowNum + "行，本轮投资金额（万元）不符合规则，请重新输入");
                return;
            }
        }

        if (StringUtils.isEmpty(model.getActualAmount())) {
            setError("第" + currentRowNum + "行，请输入其中：本基金投资金额（万元）");
            return;
        }
        if (StringUtils.isNotEmpty(model.getActualAmount())) {
            if(!RegexUtils.checkDecimals(model.getActualAmount().trim())){
                setError("第" + currentRowNum + "行，本轮其中：本基金投资金额（万元）不符合规则，请重新输入");
                return;
            }
        }

        if (StringUtils.isEmpty(model.getCurTmoneyPer())) {
            setError("第" + currentRowNum + "行，请输入 本基金投资金额占本基金认缴出资总额的比例（%）");
            return;
        }

        if (StringUtils.isNotEmpty(model.getCurTmoneyPer())) {
            if(!RegexUtils.checkDecimals(model.getCurTmoneyPer().trim())){
                setError("第" + currentRowNum + "行，本基金投资金额占本基金认缴出资总额的比例（%）不符合规则，请重新输入");
                return;
            }
        }

        if (StringUtils.isEmpty(model.getAllTmoney())) {
            setError("第" + currentRowNum + "行，请输入本基金对项目累计投资金额（万元）");
            return;
        }
        if (StringUtils.isNotEmpty(model.getAllTmoney())) {
            if(!RegexUtils.checkDecimals(model.getAllTmoney().trim())){
                setError("第" + currentRowNum + "行，本基金对项目累计投资金额（万元）不符合规则，请重新输入");
                return;
            }
        }

        if (StringUtils.isEmpty(model.getFunToPro())) {
            setError("第" + currentRowNum + "行，请输入本基金对项目的累计出资比例（%）");
            return;
        }
        if (StringUtils.isNotEmpty(model.getFunToPro())) {
            if(!RegexUtils.checkDecimals(model.getFunToPro().trim())){
                setError("第" + currentRowNum + "行，本基金对项目的累计出资比例（%）不符合规则，请重新输入");
                return;
            }
        }

        if (StringUtils.isEmpty(model.getAllTmoneyPer())) {
            setError("第" + currentRowNum + "行，请输入 本基金对项目累计投资金额占本基金认缴出资总额的比例（%）");
            return;
        }
        if (StringUtils.isNotEmpty(model.getAllTmoneyPer())) {
            if(!RegexUtils.checkDecimals(model.getAllTmoneyPer().trim())){
                setError("第" + currentRowNum + "行，本基金对项目累计投资金额占本基金认缴出资总额的比例（%）不符合规则，请重新输入");
                return;
            }
        }
        if (StringUtils.isEmpty(model.getPreMoney())) {
            setError("第" + currentRowNum + "行，请输入 本轮估值（万元）");
            return;
        }
        if (StringUtils.isNotEmpty(model.getPreMoney())) {
            if(!RegexUtils.checkDecimals(model.getPreMoney().trim())){
                setError("第" + currentRowNum + "行，本轮估值（万元）不符合规则，请重新输入");
                return;
            }
        }
        if (StringUtils.isEmpty(model.getPostMoney())) {
            setError("第" + currentRowNum + "行，请输入 最新估值（万元）");
            return;
        }
        if (StringUtils.isNotEmpty(model.getPostMoney())) {
            if(!RegexUtils.checkDecimals(model.getPostMoney().trim())){
                setError("第" + currentRowNum + "行，最新估值（万元）不符合规则，请重新输入");
                return;
            }
        }

        if (StringUtils.isEmpty(model.getInveRounds())) {
            setError("第" + currentRowNum + "行，请输入投资次数");
            return;
        }

        if (StringUtils.isNotEmpty(model.getActualPayDate())) {
            if(!RegexUtils.isDate(model.getActualPayDate().trim())){
                setError("第" + currentRowNum + "行，投资时间 不符合规则，请重新输入");
                return;
            }
        }
        if (StringUtils.isNotEmpty(model.getQuitDt())) {
            if(!RegexUtils.isDate(model.getQuitDt().trim())){
                setError("第" + currentRowNum + "行，退出日期 不符合规则，请重新输入");
                return;
            }
        }
        if(StringUtils.isNotEmpty(model.getInveType())){
            String INVE_TYPE="";
            if("直接投资".equals(model.getInveType())){
                INVE_TYPE="1";
            }else if("可转债".equals(model.getInveType())){
                INVE_TYPE="2";
            }else if("债权投资".equals(model.getInveType())){
                INVE_TYPE="3";
            }
            model.setInveType(INVE_TYPE);
        }
        if(StringUtils.isNotEmpty(model.getInveRole())){
            if("领投".equals(model.getInveRole())){
                model.setInveRole("1");
            }
            if("共同投资".equals(model.getInveRole())){
                model.setInveRole("2");
            }
            if("单一投资".equals(model.getInveRole())){
                model.setInveRole("4");
            }
        }

        dataList.add(model);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //do something
    }

    @Override
    public List<BusinessManagerModel> getDataList() {
        return dataList;
    }
}