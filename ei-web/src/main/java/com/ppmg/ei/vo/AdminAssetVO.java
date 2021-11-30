package com.ppmg.ei.vo;

import com.founder.ssm.core.model.BaseModel;
import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;

/** 
 * 资产信息
 * @author null
 * @date 2019-08-13 10:53 
 */ 
@Data 
public class AdminAssetVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 资产信息ID */
	private String assetId;

	/** 基金管理人ID */
	private String adminId;

	/** 自有资产 */
	private Double asset1;

	/** 代理其它创业投资机构资产 */
	private Double asset2;

	/** 代理其它机构和个人资产 */
	private Double asset3;

	/** 管理资产规模合计 */
	private Double totalAsset;

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public Double getAsset1() {
		return asset1;
	}

	public void setAsset1(Double asset1) {
		this.asset1 = asset1;
	}

	public Double getAsset2() {
		return asset2;
	}

	public void setAsset2(Double asset2) {
		this.asset2 = asset2;
	}

	public Double getAsset3() {
		return asset3;
	}

	public void setAsset3(Double asset3) {
		this.asset3 = asset3;
	}

	public Double getTotalAsset() {
		return totalAsset;
	}

	public void setTotalAsset(Double totalAsset) {
		this.totalAsset = totalAsset;
	}
}