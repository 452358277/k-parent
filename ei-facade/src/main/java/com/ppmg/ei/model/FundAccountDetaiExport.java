package com.ppmg.ei.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class FundAccountDetaiExport extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 分配时间 */
	@Excel(name = "基金分配时间",exportFormat = "yyyy-MM-dd",  width = 20)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date disData;
	/** 分配金额 */
	@Excel(name = "基金分配金额（万元）",  width = 20)
	private String fundAmountStr;

	/** 分配金财金额 */
	@Excel(name = "其中，分配金财投资金额（万元）",  width = 20)
	private String disJcAmountStr;

	private String disAmountStr;


}