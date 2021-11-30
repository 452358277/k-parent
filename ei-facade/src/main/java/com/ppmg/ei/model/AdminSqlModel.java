package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;
import lombok.Data;

import java.util.List;

/** 
 * 资产信息
 * @author null
 * @date 2019-08-13 10:53 
 */ 
@Data 
public class AdminSqlModel extends BaseModel {


	/** 资产信息ID */
	private String assetId;

	/** 股东ID */
	private String partnerId;

	/** 基金管理人ID */
	private List<String> adminIds;

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public List<String> getAdminIds() {
		return adminIds;
	}

	public void setAdminIds(List<String> adminIds) {
		this.adminIds = adminIds;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
}