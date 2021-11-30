package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.FundProGovernmentGQRJModel;
import com.ppmg.ei.model.FundProGovernmentGQSJModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 
 * 子基金信息List
 * @return 
 * @author zhaokuiyu
 * @date 2019/12/3 13:32
 * @creed: The best time to plant a tree is ten years ago, followed by now
 */
public class FundProGovernmentReportAllVO extends BaseVO {

    List<FundProGovernmentReportVO> FundProGovernmentReportVO;

    public List<com.ppmg.ei.vo.FundProGovernmentReportVO> getFundProGovernmentReportVO() {
        return FundProGovernmentReportVO;
    }

    public void setFundProGovernmentReportVO(List<FundProGovernmentReportVO> fundProGovernmentReportVO) {
        FundProGovernmentReportVO = fundProGovernmentReportVO;
    }
}
