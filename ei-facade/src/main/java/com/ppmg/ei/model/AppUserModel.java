package com.ppmg.ei.model;

import java.util.Date;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

/** 
 * 用户 [Model]
 * @author yuyongjun
 * @date 2019-06-24 17:51
 */ 
@Data 
public class AppUserModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private Long id;

	/** null */
	private String name;

	/** null */
	private String description;

	/** null */
	private String guid;

	/** null */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date createddate;

	/** null */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date modifieddate;

	/** null */
	private String readonly;

	/** null */
	private String nodelete;

	/** null */
	private String loginname;

	/** null */
	private String password;

	/** null */
	private String email;

	/** null */
	private Long levelid;

	/** null */
	private Long dutylevelid;

	/** null */
	private String disabled;

	/** null */
	private String nodisable;

	/** null */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date lastlogin;

	/** null */
	private Long lastVisitedPage;

	/** null */
	private Long sortorder;

	/** null */
	private Long priority;

	/** null */
	private String ntaccout;

	/** null */
	private Long recordtype;

	/** null */
	private String phone;

	/** null */
	private String mobilephone;

	/** null */
	private Long lendcopies;

	/** null */
	private Long recordnumperpage;

	/** null */
	private Long frameperpage;

	/** null */
	private Long verified;

	/** null */
	private Long isabsent;

	/** null */
	private Long substitute;

	/** null */
	private Long sexy;

	/** 01:内部用户 02：外部用户 */
	private Long usertype;

	/** null */
	private String groupcode;

	/** null */
	private Long ptype;

	/** null */
	private Long salary;

	/** null */
	private String orgcode;

	/** null */
	private Long grouplevel;

	/** null */
	private String idcardno;

	/** null */
	private String label;

	/** null */
	private String duty;

	/** null */
	private String pl;

	/** null */
	private String displayname;

	/** 1代表已登录，其他情况代表未登录过 */
	private String ifFirstLogin;

	/** 证件类型 */
	private String idType;

	/** QQ号码 */
	private String qq;

	/** null */
	private String ifmodifypassword;

	/** null */
	private String fax;

	/** null */
	private String jobId;

	/** null */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date birthday;

	/** null */
	private String idNo;

	/** null */
	private Long orgid;

	/** null */
	private Long deptid;

	/** 家庭号码 */
	private String housephone;

	/** 短号 */
	private String cornetno;

	/** Exchange密码。暂时未明文 */
	private String exchangePassword;

	/** 是否同步（0，1） */
	private String isSynchronize;

	/** 岗位ID */
	private String postId;

}