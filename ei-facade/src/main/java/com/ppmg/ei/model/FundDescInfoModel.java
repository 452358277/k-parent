package com.ppmg.ei.model;

import lombok.Data;

import com.founder.ssm.core.model.BaseModel;

/** 
 * 描述 [Model] 
 * @author root
 * @date 2019-09-02 18:15 
 */
public class FundDescInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 基金ID */
	private String fundId;

	/** 基金介绍 */
	private String fundDesc;

	/** 基金介绍附件 */
	private String fundDescFile;

	/** 增值服务 */
	private String vaService;

	/** 增值服务附件 */
	private String vaServiceFile;

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getFundDesc() {
		return fundDesc;
	}

	public void setFundDesc(String fundDesc) {
		this.fundDesc = fundDesc;
	}

	public String getFundDescFile() {
		return fundDescFile;
	}

	public void setFundDescFile(String fundDescFile) {
		this.fundDescFile = fundDescFile;
	}

	public String getVaService() {
		return vaService;
	}

	public void setVaService(String vaService) {
		this.vaService = vaService;
	}

	public String getVaServiceFile() {
		return vaServiceFile;
	}

	public void setVaServiceFile(String vaServiceFile) {
		this.vaServiceFile = vaServiceFile;
	}
}