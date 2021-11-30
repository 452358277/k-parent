package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.AgroupModel;

import java.util.List;

public class GroupVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** null */
	private long id;

	/** null */
	private String name;

    private String userId;

    private String type;

	private List<AgroupModel> children;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<AgroupModel> getChildren() {
		return children;
	}

	public void setChildren(List<AgroupModel> children) {
		this.children = children;
	}
}