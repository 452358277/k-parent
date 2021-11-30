package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.easyexcel.FundProGovernmentReportRowModel;
import com.ppmg.ei.easyexcel.FundProGovernmentReportRowTVO;
import com.ppmg.ei.easyexcel.StyleExcelHandler;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.FundProGovernmentService;
import com.ppmg.ei.service.ProjInfoService;
import com.ppmg.ei.utils.UimUtilsUserId;
import com.ppmg.ei.vo.FundProGovernmentReportTVO;
import com.ppmg.ei.vo.FundProGovernmentReportVO;
import com.ppmg.ei.vo.FundProGovernmentVO;
import easyexcel.exception.ExcelException;
import easyexcel.utils.CustomHandler;
import easyexcel.utils.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 花生糖块儿
 */
@Controller
@Scope("prototype")
@Api(tags = {"运营监控，省政府基金投资"})
public class FundProGovernmentController extends BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference(retries = 3, timeout = 100000)
    private FundProGovernmentService fundProGovernmentService;

    @Resource(name = "codeUtils")
    private CodeUtils codeUtils;
    @Reference
    private ProjInfoService projInfoService;

    @ApiOperation(value = "运营监控列表", notes = "运营监控列表", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "1:区域;2:基金类型;3:基金形式;4:企业标签", required = false, dataType = "String")
    })
    @GetMapping(value = "/selectFundProGovernment", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String FundProGovernmentController(@RequestParam("type") String type) {
        try {
            searchCondition.setPageSize(10);
            searchCondition.setCurrPage(1);
            PageInfo<FundProGovernmentModel> rows = null;
            if ("1".equals(type)) {
                //区域
                rows = fundProGovernmentService.selectListPage(searchCondition);
            } else if ("2".equals(type)) {
                //基金类型
                rows = fundProGovernmentService.selectallListPageFundType(searchCondition);
            } else if ("3".equals(type)) {
                //基金形式
                rows = fundProGovernmentService.selectallListPageFundFrom(searchCondition);
            } else if ("4".equals(type)) {
                //企业标签
                rows = fundProGovernmentService.selectallListPageEnterpriseLabel(searchCondition);
            } else if ("5".equals(type)) {
                //基金属性
                rows = fundProGovernmentService.selectallListPageFundGenre(searchCondition);
            }
            List<FundProGovernmentVO> list = new ArrayList<FundProGovernmentVO>();
            for (FundProGovernmentModel model : rows.getList()) {
                FundProGovernmentVO vo = new FundProGovernmentVO();
                BeanUtils.copyProperties(model, vo);
                if ("1".equals(type)) {
                    //区域；是	1，否	0
                    if (StringUtils.isNotEmpty(model.getIsJs())) {
                        String name1 = null;
                        if ("0".equals(model.getIsJs())) {
                            name1 = "省外";
                        } else if ("1".equals(model.getIsJs())) {
                            name1 = "省内";
                        }
                        vo.setIsJsName(name1);
                    }
                } else if ("2".equals(type)) {
                    //基金类型,1 区域基金	， 2 国家基金	， 3产业基金
                    if (StringUtils.isNotEmpty(model.getPlatProp())) {
                        String name1 = codeUtils.getCodeNameByValue("204", model.getPlatProp());
                        vo.setPlatPropName(name1);
                    }
                } else if ("3".equals(type)) {
                    //基金形式,1合伙制	 ,2公司制	 ,3契约制
                    if (StringUtils.isNotEmpty(model.getFundStruct())) {
                        String name1 = codeUtils.getCodeNameByValue("210", model.getFundStruct());
                        vo.setFundStructName(name1);
                    }
                } else if ("4".equals(type)) {
                    //企业标签,1：高新技术企业，2：带一路，3：中小企业，4：战略新兴
                    String label_id = model.getLabelId();
                    if (StringUtils.isNotEmpty(label_id)) {
                        String getLabel_idsql = "SELECT label_name FROM BASE.COMM_T_LABEL WHERE label_id ='" + label_id + "' ";
                        List<Map<String, Object>> lists = projInfoService.selectSql2Map(getLabel_idsql);
                        if (lists.size() > 0) {
                            String name = lists.get(0).get("LABEL_NAME") + "";
                            vo.setLabelIdName(name);
                        }
                    }
                } else if ("5".equals(type)) {
                    //基金属性,1：专项子基金:2：市场化子基金
                    if (StringUtils.isNotEmpty(model.getFundGenre())) {
                        String name1 = codeUtils.getCodeNameByValue("1011", model.getFundGenre());
                        vo.setFundGenreName(name1);
                    }
                }
                //UimUtilsUserId,投资金额，退出金额：将万元转换成亿元
                // 投资金额
                Double tou_amount = vo.getLeftAmount();
                if (tou_amount != null) {
                    String result = new UimUtilsUserId().amountConversion(tou_amount);
                    logger.info(tou_amount + "<---转换之前------------------投资金额-------------------转换成亿元以后----->" + result);
                    //doubel格式
                    vo.setLeftAmount(Double.valueOf(result));
                    // string格式
                    vo.setLeftAmountStr(result);
                } else {
                    logger.info("<---转换之前------------------投资金额--为0-------------------转换成亿元以后----->");
                    vo.setLeftAmount(0.0);
                    vo.setLeftAmountStr("0.0");
                }
                //退出金额
                Double tui_amount = vo.getQuitAmount();
                if (tui_amount != null) {
                    String result2 = new UimUtilsUserId().amountConversion(tui_amount);
                    logger.info(tui_amount + "<---转换之前------------------退出金额-------------------转换成亿元以后----->" + result2);
                    //doubel格式
                    vo.setQuitAmount(Double.valueOf(result2));
                    //string格式
                    vo.setQuitAmountStr(result2);
                } else {
                    logger.info("<---转换之前------------------退出金额--为0-------------------转换成亿元以后----->");
                    //doubel格式
                    vo.setQuitAmount(0.0);
                    //string格式
                    vo.setQuitAmountStr("0.0");
                }
                list.add(vo);
            }
            //补充
            for (FundProGovernmentVO fpg : list) {
                if (fpg.getLeftCount() == null) {
                    //项目个数
                    fpg.setLeftCount(0.0);
                } else if (fpg.getQuitCount() == null) {
                    //退出个数
                    fpg.setQuitCount(0.0);
                }
            }
            dataTablesResponse.setData(list, rows);
        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value = "子基金信息列表", notes = "子基金信息列表", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "motherFundName", value = "所属母基金", required = false, dataType = "String"),
            @ApiImplicitParam(name = "fundName", value = "参股子基金", required = false, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "1自基金信息-江苏省新兴产业创业投资引导基金；2：子基金信息--江苏省省级产业发展资金",
                    required = false, dataType = "String")
    })
    @GetMapping(value = "/selectFundProGovernmentSonFound", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fundProGovernmentSonFoundController(HttpServletResponse response, @RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,
                                                      @RequestParam("motherFundName") String motherFundName, @RequestParam("fundName") String fundName
            , @RequestParam("type") String type, @RequestParam("dc") String dc) {
        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            PageInfo<FundProGovernmentReportModel> rows = null;
            if (StringUtils.isNotEmpty(fundName)) {
                //searchCondition.addConditionLike("FUND_NAME", "%" + fundName + "%");
                searchCondition.addParam("FUND_NAME", fundName);

            }
            if (StringUtils.isNotEmpty(motherFundName)) {
                //searchCondition.addConditionLike("MOTHER_FUND_NAME", "%" + motherFundName + "%");
                searchCondition.addParam("MOTHER_FUND_NAME", motherFundName);
            }
            if ("1".equals(type)) {
                rows = fundProGovernmentService.selectListPageReportSon(searchCondition);
            } else if ("2".equals(type)) {
                rows = fundProGovernmentService.selectListPageReportSon2(searchCondition);
            }
            List<FundProGovernmentReportVO> list = new ArrayList<>();
            List<FundProGovernmentReportRowModel> daochu = new ArrayList<>();
            FundProGovernmentGQRJModel rj = null;

            for (FundProGovernmentReportModel model : rows.getList()) {
                if (dc == null || dc == "") {
                    FundProGovernmentReportVO vo = new FundProGovernmentReportVO();
                    BeanUtils.copyProperties(model, vo);
                    //母基金认缴比例(母基金认缴出资金额/认缴出资总额(sumIntendedAmount/sumPlanAmount))
                    DecimalFormat df = new DecimalFormat("######0.00");
                    Double sumManageFeeBL = 0.0;
                    if (vo.getSumIntendedAmount() != null && vo.getSumPlanAmount() != null && vo.getSumPlanAmount() != 0) {
                        String k = df.format(vo.getSumIntendedAmount() / vo.getSumPlanAmount());
                        logger.info("母基金认缴比例------------------------" + k);
                        sumManageFeeBL = Double.valueOf(k);
                    }
                    vo.setSumManageFeeBL(sumManageFeeBL);
                    //母基金所占部分对应结余;结余*母基金认缴比例(sumSurplus*sumManageFeeBL)
                    Double motherFundJY = 0.0;
                    if (vo.getSumSurplus() != null) {
                        String k = df.format(vo.getSumSurplus() * sumManageFeeBL);
                        motherFundJY = Double.valueOf(k);
                    }
                    vo.setMotherFundJY(motherFundJY);
                    //退出方式
                    String exitTypeDt11 = null;
                    if (vo.getExitDt() != null && vo.getExitDtDt11() != null) {
                        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
                        //fmt.setTimeZone(new TimeZone()); // 如果需要设置时间区域，可以在这里设置
                        if (fmt.format(vo.getExitDt()).equals(fmt.format(vo.getExitDtDt11()))) {
                            exitTypeDt11 = model.getExitType();
                        }
                    }
                    vo.setExitTypeDT11(exitTypeDt11);
                    //投资企业项目数
                    Double sumManageFeeProjCount = 0.0;
                    String projNum = vo.getProjNum();
                    String projNumDt9 = vo.getProjNumDt9();
                    if (StringUtils.isEmpty(projNum)) {
                        projNum = "0";
                    }
                    if (StringUtils.isEmpty(projNumDt9)) {
                        projNumDt9 = "0";
                    }
                    sumManageFeeProjCount = Double.valueOf(projNum) + Double.valueOf(projNumDt9);
                    vo.setSumManageFeeProjCount(sumManageFeeProjCount);
                    //投资额
                    Double sumManageFeeMoney = 0.0;
                    String projInvest = vo.getProjInvest();
                    String projInvestDt10 = vo.getProjInvestDt10();
                    if (StringUtils.isEmpty(projInvest)) {
                        projInvest = "0";
                    }
                    if (StringUtils.isEmpty(projInvestDt10)) {
                        projInvestDt10 = "0";
                    }
                    sumManageFeeMoney = Double.valueOf(projInvest) + Double.valueOf(projInvestDt10);
                    vo.setSumManageFeeMoney(sumManageFeeMoney);
                    vo.setSumPlanAmountDTName("认缴金额");
                    //伪序列
                    vo.setRownum(model.getRownum());
                    //认缴出资总额
                    vo.setSumPlanAmount(model.getSumPlanAmount() != null ? model.getSumPlanAmount() : 0);
                    //母基金认缴出资金额
                    vo.setSumIntendedAmount(model.getSumIntendedAmount() != null ? model.getSumIntendedAmount() : 0);
                    //实际出资总额
                    vo.setSumPaidAmount(model.getSumPaidAmount() != null ? model.getSumPaidAmount() : 0);
                    //ok母基金实际出资额
                    vo.setSumOccurAmount(model.getSumOccurAmount() != null ? model.getSumOccurAmount() : 0);
                    //子基金投专项基金金额
                    vo.setProjInvestDt10(model.getProjInvestDt10() != null ? model.getProjInvestDt10() : "0");
                    //专项基金投资企业数
                    vo.setProjNumDt9(model.getProjNumDt9() != null ? model.getProjNumDt9() : "0");
                    //母基金实际出资额
                    vo.setSumOccurAmount(model.getSumOccurAmount() != null ? model.getSumOccurAmount() : 0);
                    //结余
                    vo.setSumSurplus(model.getSumSurplus() != null ? model.getSumSurplus() : 0);
                    //其他收入
                    vo.setSumOtherIncome(model.getSumOtherIncome() != null ? model.getSumOtherIncome() : 0);
                    //其他费用
                    vo.setSumOtherFee(model.getSumOtherFee() != null ? model.getSumOtherFee() : 0);
                    //管理费
                    vo.setSumManageFee(model.getSumManageFee()!=null?model.getSumManageFee():0);
                    //投资收益
                    vo.setSumInvestIncome(model.getSumInvestIncome() != null ? model.getSumInvestIncome() : 0);
                    vo.setSumInterestIncome(model.getSumInterestIncome() != null ? model.getSumInterestIncome() : 0);
                    vo.setSumCollocationFee(model.getSumCollocationFee() != null ? model.getSumCollocationFee() : 0);
                    vo.setProjNum(model.getProjNum() != null ? model.getProjNum() : "0");
                    vo.setProjInvest(model.getProjInvest() != null ? model.getProjInvest() : "0");

                    vo.setSumPlanAmountDt4gjjj(model.getSumPlanAmountDt4gjjj() != null ? model.getSumPlanAmountDt4gjjj() : 0);
                    vo.setSumPlanAmountDt4gyjrjg(model.getSumPlanAmountDt4gyjrjg() != null ? model.getSumPlanAmountDt4gyjrjg() : 0);
                    vo.setSumPlanAmountDt4gyqy(model.getSumPlanAmountDt4gyqy() != null ? model.getSumPlanAmountDt4gyqy() : 0);
                    vo.setSumPlanAmountDt4myqy(model.getSumPlanAmountDt4myqy() != null ? model.getSumPlanAmountDt4myqy() : 0);
                    vo.setSumPlanAmountDt4sjcz(model.getSumPlanAmountDt4sjcz() != null ? model.getSumPlanAmountDt4sjcz() : 0);
                    vo.setSumPlanAmountDt4sjjj(model.getSumPlanAmountDt4sjjj() != null ? model.getSumPlanAmountDt4sjjj() : 0);
                    vo.setSumPlanAmountDt4sxcz(model.getSumPlanAmountDt4sxcz() != null ? model.getSumPlanAmountDt4sxcz() : 0);
                    vo.setSumPlanAmountDt4zrr(model.getSumPlanAmountDt4zrr() != null ? model.getSumPlanAmountDt4zrr() : 0);
                    vo.setSumPlanAmountDt4zycz(model.getSumPlanAmountDt4zycz() != null ? model.getSumPlanAmountDt4zycz() : 0);
                    list.add(vo);
                    //---------↓↓↓↓↓↓↓↓--------实际出资金额------------↓↓↓↓↓↓----------------
                    FundProGovernmentReportVO vo2 = new FundProGovernmentReportVO();
                    BeanUtils.copyProperties(vo, vo2);
                    vo2.setSumPlanAmountDTName("实际出资金额");
                    vo2.setSumPlanAmountDt4gjjj(model.getSumPlanAmountDt4gjjj2() != null ? model.getSumPlanAmountDt4gjjj2() : 0);
                    vo2.setSumPlanAmountDt4gyjrjg(model.getSumPlanAmountDt4gyjrjg2() != null ? model.getSumPlanAmountDt4gyjrjg2() : 0);
                    vo2.setSumPlanAmountDt4gyqy(model.getSumPlanAmountDt4gyqy2() != null ? model.getSumPlanAmountDt4gyqy2() : 0);
                    vo2.setSumPlanAmountDt4myqy(model.getSumPlanAmountDt4myqy2() != null ? model.getSumPlanAmountDt4myqy2() : 0);
                    vo2.setSumPlanAmountDt4sjcz(model.getSumPlanAmountDt4sjcz2() != null ? model.getSumPlanAmountDt4sjcz2() : 0);
                    vo2.setSumPlanAmountDt4sjjj(model.getSumPlanAmountDt4sjjj2() != null ? model.getSumPlanAmountDt4sjjj2() : 0);
                    vo2.setSumPlanAmountDt4sxcz(model.getSumPlanAmountDt4sxcz2() != null ? model.getSumPlanAmountDt4sxcz2() : 0);
                    vo2.setSumPlanAmountDt4zrr(model.getSumPlanAmountDt4zrr2() != null ? model.getSumPlanAmountDt4zrr2() : 0);
                    vo2.setSumPlanAmountDt4zycz(model.getSumPlanAmountDt4zycz2() != null ? model.getSumPlanAmountDt4zycz2() : 0);
                    list.add(vo2);
                    dataTablesResponse.setData(list, rows);
                } else {
                    //导出
                    FundProGovernmentReportRowModel vo = new FundProGovernmentReportRowModel();
                    BeanUtils.copyProperties(model, vo);
                    //母基金认缴比例(母基金认缴出资金额/认缴出资总额(sumIntendedAmount/sumPlanAmount))
                    DecimalFormat df = new DecimalFormat("######0.00");
                    Double sumManageFeeBL = 0.0;
                    if (vo.getSumIntendedAmount() != null && vo.getSumPlanAmount() != null && vo.getSumPlanAmount() != 0) {
                        String k = df.format(vo.getSumIntendedAmount() / vo.getSumPlanAmount());
                        logger.info("母基金认缴比例------------------------" + k);
                        sumManageFeeBL = Double.valueOf(k);
                    }
                    //vo.setSumManageFeeBL(sumManageFeeBL);
                    //母基金所占部分对应结余;结余*母基金认缴比例(sumSurplus*sumManageFeeBL)
                    Double motherFundJY = 0.0;
                    if (vo.getSumSurplus() != null) {
                        String k = df.format(vo.getSumSurplus() * sumManageFeeBL);
                        motherFundJY = Double.valueOf(k);
                    }
                    vo.setMotherFundJY(motherFundJY);
                    //退出方式
                    String exitTypeDt11 = null;
                    if (vo.getExitDt() != null && model.getExitDtDt11() != null) {
                        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
                        //fmt.setTimeZone(new TimeZone()); // 如果需要设置时间区域，可以在这里设置
                        if (fmt.format(vo.getExitDt()).equals(fmt.format(model.getExitDtDt11()))) {
                            exitTypeDt11 = model.getExitType();
                        }
                    }
                    vo.setExitTypeDT11(exitTypeDt11);
                    //投资企业项目数
                    Double sumManageFeeProjCount = 0.0;
                    String projNum = model.getProjNum();
                    String projNumDt9 = model.getProjNumDt9();
                    if (StringUtils.isEmpty(projNum)) {
                        projNum = "0";
                    }
                    if (StringUtils.isEmpty(projNumDt9)) {
                        projNumDt9 = "0";
                    }
                    sumManageFeeProjCount = Double.valueOf(projNum) + Double.valueOf(projNumDt9);
                    vo.setSumManageFeeProjCount(sumManageFeeProjCount);
                    //投资额
                    Double sumManageFeeMoney = 0.0;
                    String projInvest = model.getProjInvest();
                    String projInvestDt10 = model.getProjInvestDt10();
                    if (StringUtils.isEmpty(projInvest)) {
                        projInvest = "0";
                    }
                    if (StringUtils.isEmpty(projInvestDt10)) {
                        projInvestDt10 = "0";
                    }
                    sumManageFeeMoney = Double.valueOf(projInvest) + Double.valueOf(projInvestDt10);
                    vo.setSumManageFeeMoney(sumManageFeeMoney);
                    vo.setSumPlanAmountDTName("认缴金额");
                    //伪序列
                    vo.setRownum(model.getRownum());
                    //认缴出资总额
                    vo.setSumPlanAmount(model.getSumPlanAmount() != null ? model.getSumPlanAmount() : 0);
                    //母基金认缴出资金额
                    vo.setSumIntendedAmount(model.getSumIntendedAmount() != null ? model.getSumIntendedAmount() : 0);
                    //实际出资总额
                    vo.setSumPaidAmount(model.getSumPaidAmount() != null ? model.getSumPaidAmount() : 0);
                    //ok母基金实际出资额
                    vo.setSumOccurAmount(model.getSumOccurAmount() != null ? model.getSumOccurAmount() : 0);
                    //子基金投专项基金金额
                   // vo.setProjInvestDt10(model.getProjInvestDt10() != null ? model.getProjInvestDt10() : "0");
                    //专项基金投资企业数
                    //vo.setProjNumDt9(model.getProjNumDt9() != null ? model.getProjNumDt9() : "0");
                    //母基金实际出资额
                    vo.setSumOccurAmount(model.getSumOccurAmount() != null ? model.getSumOccurAmount() : 0);
                    //结余
                    vo.setSumSurplus(model.getSumSurplus() != null ? model.getSumSurplus() : 0);
                    //其他收入
                    vo.setSumOtherIncome(model.getSumOtherIncome() != null ? model.getSumOtherIncome() : 0);
                    //其他费用
                    vo.setSumOtherFee(model.getSumOtherFee() != null ? model.getSumOtherFee() : 0);
                    //管理费
                    vo.setSumManageFee(model.getSumManageFee()!=null?model.getSumManageFee():0);
                    //投资收益
                    vo.setSumInvestIncome(model.getSumInvestIncome() != null ? model.getSumInvestIncome() : 0);
                    vo.setSumInterestIncome(model.getSumInterestIncome() != null ? model.getSumInterestIncome() : 0);
                    vo.setSumCollocationFee(model.getSumCollocationFee() != null ? model.getSumCollocationFee() : 0);
                   // vo.setProjNum(model.getProjNum() != null ? model.getProjNum() : "0");
                   // vo.setProjInvest(model.getProjInvest() != null ? model.getProjInvest() : "0");

                    vo.setSumPlanAmountDt4gjjj(model.getSumPlanAmountDt4gjjj() != null ? model.getSumPlanAmountDt4gjjj() : 0);
                    vo.setSumPlanAmountDt4gyjrjg(model.getSumPlanAmountDt4gyjrjg() != null ? model.getSumPlanAmountDt4gyjrjg() : 0);
                    vo.setSumPlanAmountDt4gyqy(model.getSumPlanAmountDt4gyqy() != null ? model.getSumPlanAmountDt4gyqy() : 0);
                    vo.setSumPlanAmountDt4myqy(model.getSumPlanAmountDt4myqy() != null ? model.getSumPlanAmountDt4myqy() : 0);
                    vo.setSumPlanAmountDt4sjcz(model.getSumPlanAmountDt4sjcz() != null ? model.getSumPlanAmountDt4sjcz() : 0);
                    vo.setSumPlanAmountDt4sjjj(model.getSumPlanAmountDt4sjjj() != null ? model.getSumPlanAmountDt4sjjj() : 0);
                    vo.setSumPlanAmountDt4sxcz(model.getSumPlanAmountDt4sxcz() != null ? model.getSumPlanAmountDt4sxcz() : 0);
                    vo.setSumPlanAmountDt4zrr(model.getSumPlanAmountDt4zrr() != null ? model.getSumPlanAmountDt4zrr() : 0);
                    vo.setSumPlanAmountDt4zycz(model.getSumPlanAmountDt4zycz() != null ? model.getSumPlanAmountDt4zycz() : 0);
                    daochu.add(vo);
                    //---------↓↓↓↓↓↓↓↓--------实际出资金额------------↓↓↓↓↓↓----------------
                    FundProGovernmentReportRowModel vo2 = new FundProGovernmentReportRowModel();
                    BeanUtils.copyProperties(vo, vo2);
                    vo2.setSumPlanAmountDTName("实际出资金额");
                    vo2.setSumPlanAmountDt4gjjj(model.getSumPlanAmountDt4gjjj2() != null ? model.getSumPlanAmountDt4gjjj2() : 0);
                    vo2.setSumPlanAmountDt4gyjrjg(model.getSumPlanAmountDt4gyjrjg2() != null ? model.getSumPlanAmountDt4gyjrjg2() : 0);
                    vo2.setSumPlanAmountDt4gyqy(model.getSumPlanAmountDt4gyqy2() != null ? model.getSumPlanAmountDt4gyqy2() : 0);
                    vo2.setSumPlanAmountDt4myqy(model.getSumPlanAmountDt4myqy2() != null ? model.getSumPlanAmountDt4myqy2() : 0);
                    vo2.setSumPlanAmountDt4sjcz(model.getSumPlanAmountDt4sjcz2() != null ? model.getSumPlanAmountDt4sjcz2() : 0);
                    vo2.setSumPlanAmountDt4sjjj(model.getSumPlanAmountDt4sjjj2() != null ? model.getSumPlanAmountDt4sjjj2() : 0);
                    vo2.setSumPlanAmountDt4sxcz(model.getSumPlanAmountDt4sxcz2() != null ? model.getSumPlanAmountDt4sxcz2() : 0);
                    vo2.setSumPlanAmountDt4zrr(model.getSumPlanAmountDt4zrr2() != null ? model.getSumPlanAmountDt4zrr2() : 0);
                    vo2.setSumPlanAmountDt4zycz(model.getSumPlanAmountDt4zycz2() != null ? model.getSumPlanAmountDt4zycz2() : 0);
                    daochu.add(vo2);
                }
            }
            //导出
            if (dc != null && dc != "") {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(new Date());
                String fileName = "";
                if ("1".equals(type)) {
                    fileName = "子基金信息-江苏省新兴产业创业投资引导基金-" + date;
                } else if ("2".equals(type)) {
                    fileName = "子基金信息-江苏省省级产业发展资金-" + date;
                }
                OutputStream out = getOutputStream(fileName, response, ExcelTypeEnum.XLSX);
                StyleExcelHandler handlers = new StyleExcelHandler();
                //ExcelWriter writer = new ExcelWriter(null, out, ExcelTypeEnum.XLSX, true, handlers);
                ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
                //导出表格内容
                Sheet sheet = new Sheet(1, 0);
                //设置列的宽度：//设置列宽度
                // 第一个参数是列数，第二个是宽度；
                Map<Integer, Integer> columnWidthMap = new HashMap<>();
                columnWidthMap.put(0, 2000);
                columnWidthMap.put(1, 7500);
                columnWidthMap.put(2, 9500);
                columnWidthMap.put(5, 7500);
                sheet.setColumnWidthMap(columnWidthMap);

                sheet.setSheetName("sheet1");
                sheet.setClazz(FundProGovernmentReportRowModel.class);
                //获取开始时间
                long startTime = System.currentTimeMillis();
                try {
                    System.out.println("导出开始-------" + pageSize + "-页----------------子基金信息-》》》》" + startTime);
                    //调用写入方法
                    writer.write(daochu, sheet);
                    //合并单元(合并前8列)
                    for (int j = 0; j < 8; j++) {
                        for (int i = 2; i < daochu.size() + 1; i = i + 2) {
                            writer.merge(i, i + 1, j, j);
                        }
                    }
                    //合并单元(合并后18列-36列)
                    for (int j2 = 18; j2 < 37; j2++) {
                        for (int i2 = 2; i2 < daochu.size() + 1; i2 = i2 + 2) {
                            writer.merge(i2, i2 + 1, j2, j2);
                        }
                    }
                } finally {
                    writer.finish();
                    //获取结束时间
                    long endTime = System.currentTimeMillis();
                    System.out.println("导出结束----------------------子基金信息-》》》》" + endTime);
                    System.out.println("导出运行时间： " + (endTime - startTime) + "ms");
                }
            }
        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    private static OutputStream getOutputStream(String fileName, HttpServletResponse response, ExcelTypeEnum excelTypeEnum) throws ExcelException {
        //创建本地文件
        String filePath = fileName + excelTypeEnum.getValue();
        try {
            fileName = new String(filePath.getBytes(), "ISO-8859-1");
            //response.addHeader("Content-Disposition", "filename=" + fileName);
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            return response.getOutputStream();
        } catch (IOException e) {
            throw new ExcelException("创建文件失败！");
        }
    }

    @ApiOperation(value = "投资企业项目明细", notes = "投资企业项目明细", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chName", value = "公司名称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "inveName", value = "出资主体", required = false, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "投资企业项目明细-江苏省新兴产业创业投资引导基金；2：投资企业项目明细--江苏省省级产业发展资金",
                    required = false, dataType = "String")
    })
    @GetMapping(value = "/selectFundProGovernmentSonSecond", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String FundProGovernmentSonFoundController2(HttpServletResponse response, @RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,
                                                       @RequestParam("chName") String chName, @RequestParam("inveName") String inveName
            , @RequestParam("year") String year, @RequestParam("type") String type, @RequestParam("dc") String dc) {
        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            if (StringUtils.isNotEmpty(chName)) {
                //searchCondition.addConditionLike("CH_NAME", "%" + chName + "%");
                searchCondition.addParam("CH_NAME", chName);
            }
            if (StringUtils.isNotEmpty(inveName)) {
                //searchCondition.addConditionLike("INVE_NAME", "%" + inveName + "%");
                searchCondition.addParam("INVE_NAME", inveName);
            }
            if (StringUtils.isNotEmpty(year) && !"null".equals(year)) {
                searchCondition.addParam("YEAR", year);
                //searchCondition.addConditionLike("NOW_YEAR", "%" + year + "%");
            }
            PageInfo<FundProGovernmentReportTModel> rows = null;
            if ("1".equals(type)) {
                rows = fundProGovernmentService.selectListPageReportSonSecond(searchCondition);
            } else if ("2".equals(type)) {
                rows = fundProGovernmentService.selectListPageReportSonSecond2(searchCondition);
            }
            List<FundProGovernmentReportTVO> list = new ArrayList<>();
            List<FundProGovernmentReportRowTVO> daochu = new ArrayList<>();
            for (FundProGovernmentReportTModel model : rows.getList()) {
                //查询
                if (dc == null || dc == "") {
                    FundProGovernmentReportTVO vo = new FundProGovernmentReportTVO();
                    BeanUtils.copyProperties(model, vo);
                    //占基金总认缴比例(%)==((实际投资额/承诺出资额)*100)
                    Double fundPerShareZB = 0.0;
                    DecimalFormat df = new DecimalFormat("######0.00");
                    if (model.getIntendedAmount() != null && model.getTotalPlanAmountv() != null && model.getTotalPlanAmountv() != 0) {
                        fundPerShareZB = (Double.valueOf(model.getIntendedAmount()) / model.getTotalPlanAmountv()) * 100;
                        fundPerShareZB = Double.valueOf(df.format(fundPerShareZB));
                    }
                    vo.setFundPerShareZB(fundPerShareZB);

                    //退出方式
                    String exitTypeDt5 = null;
                    if (model.getExitDt() != null && vo.getExitDtDt5() != null) {
                        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
                        //fmt.setTimeZone(new TimeZone()); // 如果需要设置时间区域，可以在这里设置
                        if (fmt.format(model.getExitDt()).equals(fmt.format(vo.getExitDtDt5()))) {
                            exitTypeDt5 = model.getExitType();
                        }
                    }
                    vo.setExitTypeDt5(exitTypeDt5);

                    //实际投资额
                    vo.setIntendedAmount(model.getIntendedAmount() != null ? model.getIntendedAmount() : "0");
                    //占股比例(%)
                    vo.setPerShare(model.getPerShare() != null ? model.getPerShare() : 0);
                    //承诺出资额
                    vo.setTotalPlanAmountv(model.getTotalPlanAmountv() != null ? model.getTotalPlanAmountv() : 0);
                    //属于企业第几轮投资
                    vo.setInveRounds(model.getInveRounds() != null ? model.getInveRounds() : "0");
                    //项目投前估值
                    vo.setPreMoney(model.getPreMoney() != null ? model.getPreMoney() : 0);
                    vo.setCorporateFinance("投资前上年度");
                    vo.setStaffNum(model.getStaffNum() != null ? model.getStaffNum() : 0);
                    vo.setRdStaffNum(model.getRdStaffNum() != null ? model.getRdStaffNum() : 0);
                    vo.setTotalAssets(model.getTotalAssets() != null ? model.getTotalAssets() : 0);
                    vo.setTotalLiabilities(model.getTotalLiabilities() != null ? model.getTotalLiabilities() : 0);
                    vo.setOwnerEquity(model.getOwnerEquity() != null ? model.getOwnerEquity() : 0);
                    vo.setTotalIncome(model.getTotalIncome() != null ? model.getTotalIncome() : 0);
                    vo.setTotalProfit(model.getTotalProfit() != null ? model.getTotalProfit() : 0);
                    vo.setNetProfit(model.getNetProfit() != null ? model.getNetProfit() : 0);
                    vo.setRdFee(model.getRdFee() != null ? model.getRdFee() : 0);
                    vo.setPayFee(model.getPayFee() != null ? model.getPayFee() : 0);
                    list.add(vo);
                    //---------↓↓↓↓↓↓↓↓--------本年度------------↓↓↓↓↓↓----------------
                    FundProGovernmentReportTVO vo2 = new FundProGovernmentReportTVO();
                    BeanUtils.copyProperties(vo, vo2);
                    vo2.setCorporateFinance("本年度");
                    vo2.setStaffNum(model.getStaffNum7() != null ? model.getStaffNum7() : 0);
                    vo2.setRdStaffNum(model.getRdStaffNum7() != null ? model.getRdStaffNum7() : 0);
                    vo2.setTotalAssets(model.getTotalAssets7() != null ? model.getTotalAssets7() : 0);
                    vo2.setTotalLiabilities(model.getTotalLiabilities7() != null ? model.getTotalLiabilities7() : 0);
                    vo2.setOwnerEquity(model.getOwnerEquity7() != null ? model.getOwnerEquity7() : 0);
                    vo2.setTotalIncome(model.getTotalIncome7() != null ? model.getTotalIncome7() : 0);
                    vo2.setTotalProfit(model.getTotalProfit7() != null ? model.getTotalProfit7() : 0);
                    vo2.setNetProfit(model.getNetProfit7() != null ? model.getNetProfit7() : 0);
                    vo2.setRdFee(model.getRdFee7() != null ? model.getRdFee7() : 0);
                    vo2.setPayFee(model.getPayFee7() != null ? model.getPayFee7() : 0);

                    list.add(vo2);
                    dataTablesResponse.setData(list, rows);
                } else {
                    //导出
                    FundProGovernmentReportRowTVO vo = new FundProGovernmentReportRowTVO();
                    BeanUtils.copyProperties(model, vo);
                    //占基金总认缴比例(%)==((实际投资额/承诺出资额)*100)
                    Double fundPerShareZB = 0.0;
                    DecimalFormat df = new DecimalFormat("######0.00");
                    if (model.getIntendedAmount() != null && model.getTotalPlanAmountv() != null && model.getTotalPlanAmountv() != 0) {
                        fundPerShareZB = (Double.valueOf(model.getIntendedAmount()) / model.getTotalPlanAmountv()) * 100;
                        fundPerShareZB = Double.valueOf(df.format(fundPerShareZB));
                    }
                    vo.setFundPerShareZB(fundPerShareZB);

                    //退出方式
                    String exitTypeDt5 = null;
                    if (model.getExitDt() != null && model.getExitDtDt5() != null) {
                        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
                        //fmt.setTimeZone(new TimeZone()); // 如果需要设置时间区域，可以在这里设置
                        if (fmt.format(model.getExitDt()).equals(fmt.format(model.getExitDtDt5()))) {
                            exitTypeDt5 = model.getExitType();
                        }
                    }
                    vo.setExitTypeDt5(exitTypeDt5);
                    //实际投资额
                    vo.setIntendedAmount(model.getIntendedAmount() != null ? model.getIntendedAmount() : "0");
                    //占股比例(%)
                    vo.setPerShare(model.getPerShare() != null ? model.getPerShare() : 0);
                    //承诺出资额
                    //vo.setTotalPlanAmountv(model.getTotalPlanAmountv() != null ? model.getTotalPlanAmountv() : 0);
                    //属于企业第几轮投资
                    vo.setInveRounds(model.getInveRounds() != null ? model.getInveRounds() : "0");
                    //项目投前估值
                    vo.setPreMoney(model.getPreMoney() != null ? model.getPreMoney() : 0);

                    vo.setCorporateFinance("投资前上年度");
                    vo.setStaffNum(model.getStaffNum() != null ? model.getStaffNum() : 0);
                    vo.setRdStaffNum(model.getRdStaffNum() != null ? model.getRdStaffNum() : 0);
                    vo.setTotalAssets(model.getTotalAssets() != null ? model.getTotalAssets() : 0);
                    vo.setTotalLiabilities(model.getTotalLiabilities() != null ? model.getTotalLiabilities() : 0);
                    vo.setOwnerEquity(model.getOwnerEquity() != null ? model.getOwnerEquity() : 0);
                    vo.setTotalIncome(model.getTotalIncome() != null ? model.getTotalIncome() : 0);
                    vo.setTotalProfit(model.getTotalProfit() != null ? model.getTotalProfit() : 0);
                    vo.setNetProfit(model.getNetProfit() != null ? model.getNetProfit() : 0);
                    vo.setRdFee(model.getRdFee() != null ? model.getRdFee() : 0);
                    vo.setPayFee(model.getPayFee() != null ? model.getPayFee() : 0);
                    daochu.add(vo);
                    //---------↓↓↓↓↓↓↓↓--------本年度------------↓↓↓↓↓↓----------------
                    FundProGovernmentReportRowTVO vo2 = new FundProGovernmentReportRowTVO();
                    BeanUtils.copyProperties(vo, vo2);
                    vo2.setCorporateFinance("本年度");
                    vo2.setStaffNum(model.getStaffNum7() != null ? model.getStaffNum7() : 0);
                    vo2.setRdStaffNum(model.getRdStaffNum7() != null ? model.getRdStaffNum7() : 0);
                    vo2.setTotalAssets(model.getTotalAssets7() != null ? model.getTotalAssets7() : 0);
                    vo2.setTotalLiabilities(model.getTotalLiabilities7() != null ? model.getTotalLiabilities7() : 0);
                    vo2.setOwnerEquity(model.getOwnerEquity7() != null ? model.getOwnerEquity7() : 0);
                    vo2.setTotalIncome(model.getTotalIncome7() != null ? model.getTotalIncome7() : 0);
                    vo2.setTotalProfit(model.getTotalProfit7() != null ? model.getTotalProfit7() : 0);
                    vo2.setNetProfit(model.getNetProfit7() != null ? model.getNetProfit7() : 0);
                    vo2.setRdFee(model.getRdFee7() != null ? model.getRdFee7() : 0);
                    vo2.setPayFee(model.getPayFee7() != null ? model.getPayFee7() : 0);
                    daochu.add(vo2);
                }
            }
            //导出
            if (dc != null && dc != "") {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(new Date());
                String fileName = "";
                if ("1".equals(type)) {
                    fileName = "投资企业项目明细-江苏省新兴产业创业投资引导基金-" + date;
                } else if ("2".equals(type)) {
                    fileName = "投资企业项目明细-江苏省省级产业发展资金-" + date;
                }
                OutputStream out = getOutputStream(fileName, response, ExcelTypeEnum.XLSX);
                StyleExcelHandler handlers = new StyleExcelHandler();
                //ExcelWriter writer = new ExcelWriter(null, out, ExcelTypeEnum.XLSX, true, handlers);
                ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
                //导出表格内容
                Sheet sheet = new Sheet(1, 0);
                //设置列的宽度：//设置列宽度
                // 第一个参数是列数，第二个是宽度；
                Map<Integer, Integer> columnWidthMap = new HashMap<>();
                columnWidthMap.put(0, 9000);
                //公司名称
                columnWidthMap.put(1, 9000);
                //公司注册地
                columnWidthMap.put(3, 9000);
                columnWidthMap.put(8, 7500);
                columnWidthMap.put(9, 7500);
                columnWidthMap.put(11, 7500);
                columnWidthMap.put(12, 7500);
                sheet.setColumnWidthMap(columnWidthMap);
                sheet.setSheetName("sheet1");
                sheet.setClazz(FundProGovernmentReportRowTVO.class);
                //获取开始时间
                long startTime = System.currentTimeMillis();
                try {
                    System.out.println("导出开始--" + pageSize + "-页-------------------投资企业项目明细-》》》》" + startTime);
                    //调用写入方法
                    writer.write(daochu, sheet);
                    //合并单元(合并前16列)
                    for (int j = 0; j < 16; j++) {
                        for (int i = 2; i < daochu.size() + 1; i = i + 2) {
                            writer.merge(i, i + 1, j, j);
                        }
                    }
                    //合并单元(合并后27列-30列)
                    for (int j2 = 27; j2 < 31; j2++) {
                        for (int i2 = 2; i2 < daochu.size() + 1; i2 = i2 + 2) {
                            writer.merge(i2, i2 + 1, j2, j2);
                        }
                    }
                } finally {
                    writer.finish();
                    //获取结束时间
                    long endTime = System.currentTimeMillis();
                    System.out.println("导出结束----------------------投资企业项目明细-》》》》" + endTime);
                    System.out.println("导出运行时间： " + (endTime - startTime) + "ms");
                }
            }

        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }


}
