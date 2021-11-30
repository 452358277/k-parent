package com.ppmg.ei.model;

import java.util.Date;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

/** 
 * 描述 [Model] 
 * @author null
 * @date 2019-08-13 10:53 
 */ 
@Data 
public class TzTCooConditionFileModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 附件ID */
	private String fileId;

	/** 合作征集对象ID */
	private String itemId;

	/** 征集材料类型 */
	private String fileType;

	/** 发生日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date fileDate;

	/** 附件说明 */
	private String fileDetail;

	/** 附件 */
	private String attaFile;

	/** 状态 */
	private String status;

}