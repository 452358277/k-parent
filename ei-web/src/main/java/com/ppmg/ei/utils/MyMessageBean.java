package com.ppmg.ei.utils;

import java.io.Serializable;

public class MyMessageBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3905456509006628705L;
	private String MSG_ID;
	private String MSG_CONTEXT;
	private String MSG_URL;
	private String RECEIVE_USER_ID;
	private String RECEIVE_USER_NAME;
	private String MSG_TYPE;
	private String CREATE_DT;
	private String CREATE_BY;
	private String UPDATE_DT;
	private String UPDATE_BY;
	private String STATUS;
	private String MSG_TITLE;
	private String MSG_FROM;
	private String PARENT_ID;
	private String ATTA_FILE;
	
	
	public String getMSG_ID() {
		return MSG_ID;
	}
	public void setMSG_ID(String mSG_ID) {
		MSG_ID = mSG_ID;
	}
	public String getMSG_CONTEXT() {
		return MSG_CONTEXT;
	}
	public void setMSG_CONTEXT(String mSG_CONTEXT) {
		MSG_CONTEXT = mSG_CONTEXT;
	}
	public String getMSG_URL() {
		return MSG_URL;
	}
	public void setMSG_URL(String mSG_URL) {
		MSG_URL = mSG_URL;
	}
	public String getRECEIVE_USER_ID() {
		return RECEIVE_USER_ID;
	}
	public void setRECEIVE_USER_ID(String rECEIVE_USER_ID) {
		RECEIVE_USER_ID = rECEIVE_USER_ID;
	}
	public String getRECEIVE_USER_NAME() {
		return RECEIVE_USER_NAME;
	}
	public void setRECEIVE_USER_NAME(String rECEIVE_USER_NAME) {
		RECEIVE_USER_NAME = rECEIVE_USER_NAME;
	}
	public String getMSG_TYPE() {
		return MSG_TYPE;
	}
	public void setMSG_TYPE(String mSG_TYPE) {
		MSG_TYPE = mSG_TYPE;
	}
	public String getCREATE_DT() {
		return CREATE_DT;
	}
	public void setCREATE_DT(String cREATE_DT) {
		CREATE_DT = cREATE_DT;
	}
	public String getCREATE_BY() {
		return CREATE_BY;
	}
	public void setCREATE_BY(String cREATE_BY) {
		CREATE_BY = cREATE_BY;
	}
	public String getUPDATE_DT() {
		return UPDATE_DT;
	}
	public void setUPDATE_DT(String uPDATE_DT) {
		UPDATE_DT = uPDATE_DT;
	}
	public String getUPDATE_BY() {
		return UPDATE_BY;
	}
	public void setUPDATE_BY(String uPDATE_BY) {
		UPDATE_BY = uPDATE_BY;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public String getMSG_TITLE() {
		return MSG_TITLE;
	}
	public void setMSG_TITLE(String mSG_TITLE) {
		MSG_TITLE = mSG_TITLE;
	}
	public String getMSG_FROM() {
		return MSG_FROM;
	}
	public void setMSG_FROM(String mSG_FROM) {
		MSG_FROM = mSG_FROM;
	}
	public String getPARENT_ID() {
		return PARENT_ID;
	}
	public void setPARENT_ID(String pARENT_ID) {
		PARENT_ID = pARENT_ID;
	}
	public String getATTA_FILE() {
		return ATTA_FILE;
	}
	public void setATTA_FILE(String aTTA_FILE) {
		ATTA_FILE = aTTA_FILE;
	}


}
