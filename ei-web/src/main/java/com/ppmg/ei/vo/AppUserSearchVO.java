package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 外部用户查询 [VO]
 * @author yuyongjun
 * @date 2019-06-24 16:17
 */
@Data
public class AppUserSearchVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 用户编号 */
	private Long id;

	/** 姓名 */
	private String name;

	/** 登录名 */
	private String loginname;

	/** 电子邮箱 */
	private String email;

	/** 性别（码值） */
	private Long sexy;

	/** 性别 */
	private String sexyName;

	/** 移动电话 */
	private String mobilephone;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	protected Date createDt;

	private String fundName;
}