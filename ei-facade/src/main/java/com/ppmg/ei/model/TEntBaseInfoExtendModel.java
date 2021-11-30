package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TEntBaseInfoExtendModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 企业ID */
	private String enterpriseId;

	/** 董事席数 */
	private Long directorCnt;

	/** 监事席数 */
	private Long supervisorCnt;

	/** 项目关联 */
	private String projGuid;

	/** 企业编号 */
	private String custNo;

	/** 信息途径 */
	private String infoWay;

	/** 税务登记号 */
	private String taxNo;

	/** 主体类型 */
	private String mainType;

	/** 贷款卡号 */
	private String provideCarno;

	/** 贷款卡密码 */
	private String providePsw;

	/** 特种许可证名称 */
	private String special;

	/** 许可证有效期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date specialTime;

	/** 实收资本 */
	private Double paidCapital;

	/** 年营业收入 */
	private Double yearIncome;

	/** 企业规模 */
	private String scale;

	/** 企业类型 */
	private String enterpStype;

	/** 企业属性 */
	private String enterpPro;

	/** 占地面积 */
	private Double enterpArea;

	/** 厂房面积 */
	private Double plantArea;

	/** 是否房地产类（0：否，1是） */
	private String isRealEstate;

	/** 涉科类型 */
	private String sheKeType;

	/** 招商载体 */
	private String bizCarrier;

	/** 税收征管方式 */
	private String taxCollection;

	/** 是否高新技术企业（0：否，1：是） */
	private String isHnTech;

	/** 批准日期（高新技术企业证书获得时间） */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date apprDtate;

	/** 证书（附件） */
	private String certificate;

	/** 其他企业类型 */
	private String otheEnteType;

	/** 国民经济行业分类大类 */
	private String gicsIndustryBig;

	/** 国民经济行业分类小类 */
	private String gicsIndustrySmall;

	/** 网址 */
	private String website;

	/** 电子邮件 */
	private String mail;

	/** 实际控制人 */
	private String controller;

	/** 实际控制人电话 */
	private String controllerTel;

	/** 删除标志(0正常，1删除) */
	private String deleteFlag;

	/** 是否中小企业（0.否，1.是） */
	private String isSmEnterprise;

	/** 中小企业上传材料 */
	private String smFile;

	/** 高新企业有效日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date effectiveDate;

	/** （高新企业）证书编号 */
	private String gxNumber;

	/** （高新企业）证书有效期 */
	private String gxTerm;

	/** 上市时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date marketDt;

	/** 上市板块 */
	private String listedSector;

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getDirectorCnt() {
		return directorCnt;
	}

	public void setDirectorCnt(Long directorCnt) {
		this.directorCnt = directorCnt;
	}

	public Long getSupervisorCnt() {
		return supervisorCnt;
	}

	public void setSupervisorCnt(Long supervisorCnt) {
		this.supervisorCnt = supervisorCnt;
	}

	public String getProjGuid() {
		return projGuid;
	}

	public void setProjGuid(String projGuid) {
		this.projGuid = projGuid;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getInfoWay() {
		return infoWay;
	}

	public void setInfoWay(String infoWay) {
		this.infoWay = infoWay;
	}

	public String getTaxNo() {
		return taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	public String getMainType() {
		return mainType;
	}

	public void setMainType(String mainType) {
		this.mainType = mainType;
	}

	public String getProvideCarno() {
		return provideCarno;
	}

	public void setProvideCarno(String provideCarno) {
		this.provideCarno = provideCarno;
	}

	public String getProvidePsw() {
		return providePsw;
	}

	public void setProvidePsw(String providePsw) {
		this.providePsw = providePsw;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public Date getSpecialTime() {
		return specialTime;
	}

	public void setSpecialTime(Date specialTime) {
		this.specialTime = specialTime;
	}

	public Double getPaidCapital() {
		return paidCapital;
	}

	public void setPaidCapital(Double paidCapital) {
		this.paidCapital = paidCapital;
	}

	public Double getYearIncome() {
		return yearIncome;
	}

	public void setYearIncome(Double yearIncome) {
		this.yearIncome = yearIncome;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getEnterpStype() {
		return enterpStype;
	}

	public void setEnterpStype(String enterpStype) {
		this.enterpStype = enterpStype;
	}

	public String getEnterpPro() {
		return enterpPro;
	}

	public void setEnterpPro(String enterpPro) {
		this.enterpPro = enterpPro;
	}

	public Double getEnterpArea() {
		return enterpArea;
	}

	public void setEnterpArea(Double enterpArea) {
		this.enterpArea = enterpArea;
	}

	public Double getPlantArea() {
		return plantArea;
	}

	public void setPlantArea(Double plantArea) {
		this.plantArea = plantArea;
	}

	public String getIsRealEstate() {
		return isRealEstate;
	}

	public void setIsRealEstate(String isRealEstate) {
		this.isRealEstate = isRealEstate;
	}

	public String getSheKeType() {
		return sheKeType;
	}

	public void setSheKeType(String sheKeType) {
		this.sheKeType = sheKeType;
	}

	public String getBizCarrier() {
		return bizCarrier;
	}

	public void setBizCarrier(String bizCarrier) {
		this.bizCarrier = bizCarrier;
	}

	public String getTaxCollection() {
		return taxCollection;
	}

	public void setTaxCollection(String taxCollection) {
		this.taxCollection = taxCollection;
	}

	public String getIsHnTech() {
		return isHnTech;
	}

	public void setIsHnTech(String isHnTech) {
		this.isHnTech = isHnTech;
	}

	public Date getApprDtate() {
		return apprDtate;
	}

	public void setApprDtate(Date apprDtate) {
		this.apprDtate = apprDtate;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getOtheEnteType() {
		return otheEnteType;
	}

	public void setOtheEnteType(String otheEnteType) {
		this.otheEnteType = otheEnteType;
	}

	public String getGicsIndustryBig() {
		return gicsIndustryBig;
	}

	public void setGicsIndustryBig(String gicsIndustryBig) {
		this.gicsIndustryBig = gicsIndustryBig;
	}

	public String getGicsIndustrySmall() {
		return gicsIndustrySmall;
	}

	public void setGicsIndustrySmall(String gicsIndustrySmall) {
		this.gicsIndustrySmall = gicsIndustrySmall;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public String getControllerTel() {
		return controllerTel;
	}

	public void setControllerTel(String controllerTel) {
		this.controllerTel = controllerTel;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getIsSmEnterprise() {
		return isSmEnterprise;
	}

	public void setIsSmEnterprise(String isSmEnterprise) {
		this.isSmEnterprise = isSmEnterprise;
	}

	public String getSmFile() {
		return smFile;
	}

	public void setSmFile(String smFile) {
		this.smFile = smFile;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getGxNumber() {
		return gxNumber;
	}

	public void setGxNumber(String gxNumber) {
		this.gxNumber = gxNumber;
	}

	public String getGxTerm() {
		return gxTerm;
	}

	public void setGxTerm(String gxTerm) {
		this.gxTerm = gxTerm;
	}

	public Date getMarketDt() {
		return marketDt;
	}

	public void setMarketDt(Date marketDt) {
		this.marketDt = marketDt;
	}

	public String getListedSector() {
		return listedSector;
	}

	public void setListedSector(String listedSector) {
		this.listedSector = listedSector;
	}

}