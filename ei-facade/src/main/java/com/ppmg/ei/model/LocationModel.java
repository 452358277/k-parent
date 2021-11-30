package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class LocationModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 地区编码 */
	private String locId;

	/** 上级编码 */
	private String parentId;

	/** 地区名称 */
	private String locName;

	/** 地区级别 */
	private String locLevel;

	/** 是否末级(0:否，1：是) */
	private String lastflag;

	/** 顺序编号 */
	private Long orderNo;

	/** 区号 */
	private String zoneId;

	/** 地区类型（个税用，和pt_code关联确定住房租金） */
	private String locType;

	public String getLocId() {
		return locId;
	}

	public void setLocId(String locId) {
		this.locId = locId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public String getLocLevel() {
		return locLevel;
	}

	public void setLocLevel(String locLevel) {
		this.locLevel = locLevel;
	}

	public String getLastflag() {
		return lastflag;
	}

	public void setLastflag(String lastflag) {
		this.lastflag = lastflag;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public String getLocType() {
		return locType;
	}

	public void setLocType(String locType) {
		this.locType = locType;
	}

}