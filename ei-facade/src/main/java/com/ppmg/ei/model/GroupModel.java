package com.ppmg.ei.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class GroupModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private long id;

	/** null */
	private String label;

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
	private Long fatherid;

	/** null */
	private Long grouptype;

	/** null */
	private String havechild;

	/** null */
	private Long grouplevel;

	/** null */
	private Long sortorder;

	/** null */
	private Long salary;

	/** null */
	private String deptcode;

	/** null */
	private String orgcode;

	/** null */
	private String orglevelid;

	/** null */
	private Long onstatus;

	/** null */
	private String innerid;

	/** 内外标志(0 内部 1 外部 2 财务公司) */
	private String outFlag;

	/** null */
	private String orgid;

	/** null */
	private String suporgid;

	/** 组织结构类别:0机构，1部门 */
	private String unitType;

    private String userId;

	private String type;

 	private List<GroupModel> children;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	public Long getFatherid() {
		return fatherid;
	}

	public void setFatherid(Long fatherid) {
		this.fatherid = fatherid;
	}

	public Long getGrouptype() {
		return grouptype;
	}

	public void setGrouptype(Long grouptype) {
		this.grouptype = grouptype;
	}

	public String getHavechild() {
		return havechild;
	}

	public void setHavechild(String havechild) {
		this.havechild = havechild;
	}

	public Long getGrouplevel() {
		return grouplevel;
	}

	public void setGrouplevel(Long grouplevel) {
		this.grouplevel = grouplevel;
	}

	public Long getSortorder() {
		return sortorder;
	}

	public void setSortorder(Long sortorder) {
		this.sortorder = sortorder;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public String getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getOrglevelid() {
		return orglevelid;
	}

	public void setOrglevelid(String orglevelid) {
		this.orglevelid = orglevelid;
	}

	public Long getOnstatus() {
		return onstatus;
	}

	public void setOnstatus(Long onstatus) {
		this.onstatus = onstatus;
	}

	public String getInnerid() {
		return innerid;
	}

	public void setInnerid(String innerid) {
		this.innerid = innerid;
	}

	public String getOutFlag() {
		return outFlag;
	}

	public void setOutFlag(String outFlag) {
		this.outFlag = outFlag;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getSuporgid() {
		return suporgid;
	}

	public void setSuporgid(String suporgid) {
		this.suporgid = suporgid;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<GroupModel> getChildren() {
		return children;
	}

	public void setChildren(List<GroupModel> children) {
		this.children = children;
	}
}