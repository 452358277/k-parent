package com.ppmg.ei.vo;

import com.founder.ssm.core.model.BaseModel;
import com.founder.ssm.core.vo.BaseVO;

public class EiMyHandleVO extends BaseVO {

	private static final long serialVersionUID = 1L;



	/** 推送季度 */
	private String sendMonth;

	/** 管理平台ID */
	private String groupId;

	/** 管理平台名称 */
	private String groupName;

	/** 推送类型(1:企业基本投后信息;2:企业人事财务信息) */
	private String descPart2;

	/** 是否已完成更新(1:完成) */
	private String isFinish;


	/** 未完成 */
	private String isNotFinishNum;

	/** 未完成率 */
	private String isNotFinishRate;

	/** 已完成 */
	private String isFinishNum;

	/** 已完成率 */
	private String isFinishRate;

	/** 总计 */
	private String total;

	public String getIsNotFinishNum() {
		return isNotFinishNum;
	}

	public void setIsNotFinishNum(String isNotFinishNum) {
		this.isNotFinishNum = isNotFinishNum;
	}

	public String getIsNotFinishRate() {
		return isNotFinishRate;
	}

	public void setIsNotFinishRate(String isNotFinishRate) {
		this.isNotFinishRate = isNotFinishRate;
	}

	public String getIsFinishNum() {
		return isFinishNum;
	}

	public void setIsFinishNum(String isFinishNum) {
		this.isFinishNum = isFinishNum;
	}

	public String getIsFinishRate() {
		return isFinishRate;
	}

	public void setIsFinishRate(String isFinishRate) {
		this.isFinishRate = isFinishRate;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getSendMonth() {
		return sendMonth;
	}

	public void setSendMonth(String sendMonth) {
		this.sendMonth = sendMonth;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public String getDescPart2() {
		return descPart2;
	}

	public void setDescPart2(String descPart2) {
		this.descPart2 = descPart2;
	}

	public String getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(String isFinish) {
		this.isFinish = isFinish;
	}


}