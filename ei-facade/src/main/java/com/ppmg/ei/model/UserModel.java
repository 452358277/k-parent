package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class UserModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private long id;

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

	/** null */
	private String isDelete;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public Date getModifieddate() {
		return modifieddate;
	}

	public void setModifieddate(Date modifieddate) {
		this.modifieddate = modifieddate;
	}

	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	public String getNodelete() {
		return nodelete;
	}

	public void setNodelete(String nodelete) {
		this.nodelete = nodelete;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getLevelid() {
		return levelid;
	}

	public void setLevelid(Long levelid) {
		this.levelid = levelid;
	}

	public Long getDutylevelid() {
		return dutylevelid;
	}

	public void setDutylevelid(Long dutylevelid) {
		this.dutylevelid = dutylevelid;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getNodisable() {
		return nodisable;
	}

	public void setNodisable(String nodisable) {
		this.nodisable = nodisable;
	}

	public Date getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

	public Long getLastVisitedPage() {
		return lastVisitedPage;
	}

	public void setLastVisitedPage(Long lastVisitedPage) {
		this.lastVisitedPage = lastVisitedPage;
	}

	public Long getSortorder() {
		return sortorder;
	}

	public void setSortorder(Long sortorder) {
		this.sortorder = sortorder;
	}

	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	public String getNtaccout() {
		return ntaccout;
	}

	public void setNtaccout(String ntaccout) {
		this.ntaccout = ntaccout;
	}

	public Long getRecordtype() {
		return recordtype;
	}

	public void setRecordtype(Long recordtype) {
		this.recordtype = recordtype;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public Long getLendcopies() {
		return lendcopies;
	}

	public void setLendcopies(Long lendcopies) {
		this.lendcopies = lendcopies;
	}

	public Long getRecordnumperpage() {
		return recordnumperpage;
	}

	public void setRecordnumperpage(Long recordnumperpage) {
		this.recordnumperpage = recordnumperpage;
	}

	public Long getFrameperpage() {
		return frameperpage;
	}

	public void setFrameperpage(Long frameperpage) {
		this.frameperpage = frameperpage;
	}

	public Long getVerified() {
		return verified;
	}

	public void setVerified(Long verified) {
		this.verified = verified;
	}

	public Long getIsabsent() {
		return isabsent;
	}

	public void setIsabsent(Long isabsent) {
		this.isabsent = isabsent;
	}

	public Long getSubstitute() {
		return substitute;
	}

	public void setSubstitute(Long substitute) {
		this.substitute = substitute;
	}

	public Long getSexy() {
		return sexy;
	}

	public void setSexy(Long sexy) {
		this.sexy = sexy;
	}

	public Long getUsertype() {
		return usertype;
	}

	public void setUsertype(Long usertype) {
		this.usertype = usertype;
	}

	public String getGroupcode() {
		return groupcode;
	}

	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}

	public Long getPtype() {
		return ptype;
	}

	public void setPtype(Long ptype) {
		this.ptype = ptype;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public Long getGrouplevel() {
		return grouplevel;
	}

	public void setGrouplevel(Long grouplevel) {
		this.grouplevel = grouplevel;
	}

	public String getIdcardno() {
		return idcardno;
	}

	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getPl() {
		return pl;
	}

	public void setPl(String pl) {
		this.pl = pl;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getIfFirstLogin() {
		return ifFirstLogin;
	}

	public void setIfFirstLogin(String ifFirstLogin) {
		this.ifFirstLogin = ifFirstLogin;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getIfmodifypassword() {
		return ifmodifypassword;
	}

	public void setIfmodifypassword(String ifmodifypassword) {
		this.ifmodifypassword = ifmodifypassword;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public Long getOrgid() {
		return orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	public Long getDeptid() {
		return deptid;
	}

	public void setDeptid(Long deptid) {
		this.deptid = deptid;
	}

	public String getHousephone() {
		return housephone;
	}

	public void setHousephone(String housephone) {
		this.housephone = housephone;
	}

	public String getCornetno() {
		return cornetno;
	}

	public void setCornetno(String cornetno) {
		this.cornetno = cornetno;
	}

	public String getExchangePassword() {
		return exchangePassword;
	}

	public void setExchangePassword(String exchangePassword) {
		this.exchangePassword = exchangePassword;
	}

	public String getIsSynchronize() {
		return isSynchronize;
	}

	public void setIsSynchronize(String isSynchronize) {
		this.isSynchronize = isSynchronize;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

}