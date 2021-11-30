package com.ppmg.ei.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;

public class AppFuncPermissionListVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private long id;

	/** 名称 */
	private String name;

	/** 父节点 */
	private Long fatherid;

	/** 子节点 */
	private List<AppFuncPermissionListVO> childList;


	private List<ButtonsVO> buttons;


	public List<ButtonsVO> getButtons() {
		return buttons;
	}

	public void setButtons(List<ButtonsVO> buttons) {
		this.buttons = buttons;
	}

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

	public Long getFatherid() {
		return fatherid;
	}

	public void setFatherid(Long fatherid) {
		this.fatherid = fatherid;
	}

	public List<AppFuncPermissionListVO> getChildList() {
		return childList;
	}

	public void setChildList(List<AppFuncPermissionListVO> childList) {
		this.childList = childList;
	}


}