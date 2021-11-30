package com.ppmg.ei.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;

public class FixflowRunTaskinstanceListVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 任务实例编号 */
	private String taskinstanceId;

	/** 流程实例编号 */
	private String processinstanceId;

	/** 流程定义编号 */
	private String processdefinitionId;

	/** 节点名称 */
	private String nodeName;

	/** 处理人 */
	private String assigneeName;

	/** 任务到达（创建）时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/** 结束时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	/** null */
	private String commandMessage;

	/** null */
	private String taskComment;

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getAssigneeName() {
		return assigneeName;
	}

	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public String getProcessinstanceId() {
		return processinstanceId;
	}

	public void setProcessinstanceId(String processinstanceId) {
		this.processinstanceId = processinstanceId;
	}

	public String getTaskinstanceId() {
		return taskinstanceId;
	}

	public void setTaskinstanceId(String taskinstanceId) {
		this.taskinstanceId = taskinstanceId;
	}

	public String getProcessdefinitionId() {
		return processdefinitionId;
	}

	public void setProcessdefinitionId(String processdefinitionId) {
		this.processdefinitionId = processdefinitionId;
	}

}