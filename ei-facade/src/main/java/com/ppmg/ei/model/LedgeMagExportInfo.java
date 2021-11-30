package com.ppmg.ei.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LedgeMagExportInfo implements Serializable {

    //出资时间
    @Excel(name = "出资时间",exportFormat = "yyyy-MM-dd", width = 20,needMerge = true ,orderNum = "1")
    private Date occurDt;

    @Excel(name = "出资金额",  width = 20,needMerge = true ,orderNum = "2" ,numFormat ="##.00000" )
    private Double rmbInveAmount;


}
