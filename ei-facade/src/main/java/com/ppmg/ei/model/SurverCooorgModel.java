package com.ppmg.ei.model;

import java.util.Date;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

@Data
public class SurverCooorgModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private String planId;

	/** 合作方机构 */
	private String partnerorGaid;

	/** null */
	private Double money;

	/** null */
	private String createBy;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createDt;

	/** null */
	private String updateBy;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date updateDt;

	/** 参与尽调人员 */
	private String personnel;

	private CooOrgModel  cooOrgModel;



}