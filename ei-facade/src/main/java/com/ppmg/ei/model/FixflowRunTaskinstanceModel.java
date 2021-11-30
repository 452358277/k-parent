package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class FixflowRunTaskinstanceModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 任务实例编号 */
	private String taskinstanceId;

	/** 流程实例编号 */
	private String processinstanceId;

	/** 流程定义编号 */
	private String processdefinitionId;

	/** 流程定义版本号 */
	private Long version;

	/** 令牌编号 */
	private String tokenId;

	/** 节点编号 */
	private String nodeId;

	/** 任务主题 */
	private String description;

	/** 父任务编号，不为空说明是会签（多实例）任务 */
	private String parenttaskId;

	/** 任务代理人 */
	private String assignee;

	/** 领取时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date claimTime;

	/** 任务名称 FLOWNODE的NAME属性 */
	private String name;

	/** 任务到达（创建）时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/** 开始时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date startTime;

	/** 是否阻塞 */
	private String isblocking;

	/** 结束时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	/** 处理期限 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date duedate;

	/** 优先级别 */
	private Long priority;

	/** 任务分类(最终用户决定分类的意义) */
	private String category;

	/** 任务所有者 */
	private String owner;

	/** 任务代理状态 */
	private String delegationstatestring;

	/** null */
	private String bizkey;

	/** null */
	private String commandType;

	/** null */
	private String commandMessage;

	/** null */
	private String taskComment;

	/** null */
	private String nodeName;

	/** null */
	private String processdefinitionKey;

	/** 表单地址 */
	private String formuri;

	/** null */
	private String taskgroup;

	/** null */
	private String tasktype;

	/** null */
	private String processdefinitionName;

	/** null */
	private String iscancelled;

	/** null */
	private String issuspended;

	/** null */
	private String isopen;

	/** null */
	private String isdraft;

	/** null */
	private Long expectedExecutiontime;

	/** null */
	private String agent;

	/** null */
	private String admin;

	/** null */
	private String formuriview;

	/** null */
	private String callactivityInstanceId;

	/** null */
	private String commandId;

	/** null */
	private String pendingtaskid;

	/** null */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date archiveTime;

	private AppUserModel userModel;
	
	public String getTaskinstanceId() {
		return taskinstanceId;
	}

	public void setTaskinstanceId(String taskinstanceId) {
		this.taskinstanceId = taskinstanceId;
	}

	public String getProcessinstanceId() {
		return processinstanceId;
	}

	public void setProcessinstanceId(String processinstanceId) {
		this.processinstanceId = processinstanceId;
	}

	public String getProcessdefinitionId() {
		return processdefinitionId;
	}

	public void setProcessdefinitionId(String processdefinitionId) {
		this.processdefinitionId = processdefinitionId;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParenttaskId() {
		return parenttaskId;
	}

	public void setParenttaskId(String parenttaskId) {
		this.parenttaskId = parenttaskId;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public Date getClaimTime() {
		return claimTime;
	}

	public void setClaimTime(Date claimTime) {
		this.claimTime = claimTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getIsblocking() {
		return isblocking;
	}

	public void setIsblocking(String isblocking) {
		this.isblocking = isblocking;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getDuedate() {
		return duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDelegationstatestring() {
		return delegationstatestring;
	}

	public void setDelegationstatestring(String delegationstatestring) {
		this.delegationstatestring = delegationstatestring;
	}

	public String getBizkey() {
		return bizkey;
	}

	public void setBizkey(String bizkey) {
		this.bizkey = bizkey;
	}

	public String getCommandType() {
		return commandType;
	}

	public void setCommandType(String commandType) {
		this.commandType = commandType;
	}

	public String getCommandMessage() {
		return commandMessage;
	}

	public void setCommandMessage(String commandMessage) {
		this.commandMessage = commandMessage;
	}

	public String getTaskComment() {
		return taskComment;
	}

	public void setTaskComment(String taskComment) {
		this.taskComment = taskComment;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getProcessdefinitionKey() {
		return processdefinitionKey;
	}

	public void setProcessdefinitionKey(String processdefinitionKey) {
		this.processdefinitionKey = processdefinitionKey;
	}

	public String getFormuri() {
		return formuri;
	}

	public void setFormuri(String formuri) {
		this.formuri = formuri;
	}

	public String getTaskgroup() {
		return taskgroup;
	}

	public void setTaskgroup(String taskgroup) {
		this.taskgroup = taskgroup;
	}

	public String getTasktype() {
		return tasktype;
	}

	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}

	public String getProcessdefinitionName() {
		return processdefinitionName;
	}

	public void setProcessdefinitionName(String processdefinitionName) {
		this.processdefinitionName = processdefinitionName;
	}

	public String getIscancelled() {
		return iscancelled;
	}

	public void setIscancelled(String iscancelled) {
		this.iscancelled = iscancelled;
	}

	public String getIssuspended() {
		return issuspended;
	}

	public void setIssuspended(String issuspended) {
		this.issuspended = issuspended;
	}

	public String getIsopen() {
		return isopen;
	}

	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}

	public String getIsdraft() {
		return isdraft;
	}

	public void setIsdraft(String isdraft) {
		this.isdraft = isdraft;
	}

	public Long getExpectedExecutiontime() {
		return expectedExecutiontime;
	}

	public void setExpectedExecutiontime(Long expectedExecutiontime) {
		this.expectedExecutiontime = expectedExecutiontime;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getFormuriview() {
		return formuriview;
	}

	public void setFormuriview(String formuriview) {
		this.formuriview = formuriview;
	}

	public String getCallactivityInstanceId() {
		return callactivityInstanceId;
	}

	public void setCallactivityInstanceId(String callactivityInstanceId) {
		this.callactivityInstanceId = callactivityInstanceId;
	}

	public String getCommandId() {
		return commandId;
	}

	public void setCommandId(String commandId) {
		this.commandId = commandId;
	}

	public String getPendingtaskid() {
		return pendingtaskid;
	}

	public void setPendingtaskid(String pendingtaskid) {
		this.pendingtaskid = pendingtaskid;
	}

	public Date getArchiveTime() {
		return archiveTime;
	}

	public void setArchiveTime(Date archiveTime) {
		this.archiveTime = archiveTime;
	}

	public AppUserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(AppUserModel userModel) {
		this.userModel = userModel;
	}

}