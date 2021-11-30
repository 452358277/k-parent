package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class CommonModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 表名 */
	private String tableName;

	/** 主键名 */
	private String keyName;

	/** 主键值 */
	private String keyValue;

	/** 字段名 */
	private String fieldName;

	/** 字段值 */
	private String fieldValue;

	/** 字段ID */
	private String fieldId;

	private String updateId;

	//关键人锁定 1
	private String type;

	private String contentOld;

	public String getContentOld() {
		return contentOld;
	}

	public void setContentOld(String contentOld) {
		this.contentOld = contentOld;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
}