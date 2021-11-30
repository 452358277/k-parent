package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import com.founder.ssm.core.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SysMessageVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String msgId;

	/** 消息正文 */
	private String msgContext;

	/** 显示页面URL */
	private String msgUrl;

	/** 接收人ID */
	private String receiveUserId;

	/** 接收人名字 */
	private String receiveUserName;

	/** 消息类型(0:OA;1:EI) */
	private String msgType;

	/** 状态(0:未阅；1：已阅；2：已处理；9：删除) */
	private String status;

	/** 标题 */
	private String msgTitle;

	/** 附件 */
	private String attaFile;

	/** 消息类型(1:消息模块发送;空:系统推送) */
	private String msgFrom;

	/** 父ID(消息发送记录) */
	private String parentId;

	/** 1:草稿;空:已发送 */
	private String sendStatus;

	protected String createBy;

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	protected Date createDt;

	protected String updateBy;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	protected Date updateDt;

	/** 是否删除（1:删除;0:未删除;） */
	private String isDelete;

	/** 头像路径 */
	private String filePath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgContext() {
		return msgContext;
	}

	public void setMsgContext(String msgContext) {
		this.msgContext = msgContext;
	}

	public String getMsgUrl() {
		return msgUrl;
	}

	public void setMsgUrl(String msgUrl) {
		this.msgUrl = msgUrl;
	}

	public String getReceiveUserId() {
		return receiveUserId;
	}

	public void setReceiveUserId(String receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	public String getReceiveUserName() {
		return receiveUserName;
	}

	public void setReceiveUserName(String receiveUserName) {
		this.receiveUserName = receiveUserName;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getAttaFile() {
		return attaFile;
	}

	public void setAttaFile(String attaFile) {
		this.attaFile = attaFile;
	}

	public String getMsgFrom() {
		return msgFrom;
	}

	public void setMsgFrom(String msgFrom) {
		this.msgFrom = msgFrom;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

}