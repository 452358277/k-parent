package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class MailExchageModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 邮件ID，主键 */
	private String mailId;

	/** 发件人邮箱 */
	private String senderEmail;

	/** 收件人邮箱 */
	private String addresseeEmail;

	/** 标题 */
	private String title;

	/** 邮件内容 */
	private String mailContent;

	/** 附件 */
	private String mailFile;

	/** 邮件时间 */
	private String emailDate;

	/** 抄送邮箱 */
	private String ccEmail;

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getAddresseeEmail() {
		return addresseeEmail;
	}

	public void setAddresseeEmail(String addresseeEmail) {
		this.addresseeEmail = addresseeEmail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public String getMailFile() {
		return mailFile;
	}

	public void setMailFile(String mailFile) {
		this.mailFile = mailFile;
	}

	public String getEmailDate() {
		return emailDate;
	}

	public void setEmailDate(String emailDate) {
		this.emailDate = emailDate;
	}

	public String getCcEmail() {
		return ccEmail;
	}

	public void setCcEmail(String ccEmail) {
		this.ccEmail = ccEmail;
	}

}