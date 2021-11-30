package com.ppmg.ei.vo;

import com.founder.ssm.core.model.BaseModel;
import com.founder.ssm.core.vo.BaseVO;

public class EntDirectorsVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 董事长 */
	private String DSZ;

	/** 董事长 */
	private String 	ZJL;

	/** 董事长 */
	private String DS;

	/** 董事长 */
	private String JS;

	public String getDSZ() {
		return DSZ;
	}

	public void setDSZ(String DSZ) {
		this.DSZ = DSZ;
	}

	public String getZJL() {
		return ZJL;
	}

	public void setZJL(String ZJL) {
		this.ZJL = ZJL;
	}

	public String getDS() {
		return DS;
	}

	public void setDS(String DS) {
		this.DS = DS;
	}

	public String getJS() {
		return JS;
	}

	public void setJS(String JS) {
		this.JS = JS;
	}
}