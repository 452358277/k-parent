package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class InveExportInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String ledgerId;

    //出资金额
    private Double occurAmount;
    //出资时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date occurDt;

    //出资金额折算人民币
    private Double rmbInveAmount;


}
