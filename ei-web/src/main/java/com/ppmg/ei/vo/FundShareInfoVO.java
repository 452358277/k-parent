package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.FundShareItemModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class FundShareInfoVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String pkId;

	/** 基金编号 */
	private String fundId;

	/** 认缴金额 */
	private String inveAmountDisplay;

	/** 累计出资金额 */
	private String totalFinancialDisplay;

	/** 股东 */
	private String investorName;

	/** 持股比例(%) */
	private String GB;

	/** 更新日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	protected Date updateDt;

	/** 出资来源（码值1000：1自有资金、2理财通道、3借贷资金） */
	private String capitalSource;

	/** 出资人性质（码值1001：1政府部门、2国有企业、3混合制企业、4民营企业、5个人） */
	private String inveType;

	private String inveTypeName;

	private String inveRoleName;


	/** 实缴规模 */
	private Double raiseAmount;


	private Double rmbInveAmount;

	/** 本期应缴额 */
	private Double duesAmount;

	/** 本期实缴额 */
	private Double paymentAmount;

	/** 本期到账日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date arriveDate;

	/** 第几期 */
	private String term;

	private Double inveCurrentAmount;

	private String statusName;

	private FundShareItemModel fundShareItem;


	private String inveCurrencyName;

	private String investorTypeName;


	private String contact;

	private String contactNo;

	private String accountBank;

	private String accountNo;

	private String rmbIntendedAmount;

	private String sumTotalFinancial;

	private String financialProcess;


}