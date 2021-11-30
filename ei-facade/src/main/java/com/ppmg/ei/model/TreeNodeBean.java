package com.ppmg.ei.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class TreeNodeBean implements Serializable {

    /**
     * 节点ID
     */
    private String id;

    /**
     * 节点名称
     */
    private String text;

    /**
     * 节点状态（open/close默认为closed）
     */
    private String state;

    /**
     * 节点其他属性
     *
     * 可以添加属性：节点类型以及其他
     */
    private Map<String, Object> attributes = new HashMap<String,Object>();

    /**
     * 子节点信息
     */
    private List<TreeNodeBean> children = new ArrayList<TreeNodeBean>();

    public static final String NODE_STATE_CLOSED = "closed";
    public static final String NODE_STATE_OPEN = "open";
    public static final String NODE_STATE_NULL = null;




}
