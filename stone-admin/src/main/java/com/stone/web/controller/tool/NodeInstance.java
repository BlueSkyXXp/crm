package com.stone.web.controller.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Node实例
 */
public abstract class NodeInstance implements Node {

    /**
     * 节点代码
     */
    private String code;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 节点排序
     */
    private Integer order;

    /**
     * 节点样式
     */
    private String css;

    /**
     * 节点状态
     */
    private String state;

    /**
     * 节点复选状态
     */
    private Boolean checked;

    /**
     * 扩展属性
     */
    private Map<String, Object> extMap;
    /**
     * 父节点代码
     */
    private String parentCode;

    /**
     * 子节点集合
     */
    private List<Node> childList;

    @Override
    public void addChildNode(Node node) {
        if (childList == null) {
            childList = new ArrayList<>();
        }
        childList.add(node);
    }

    @Override
    public List<Node> getChildList() {
        return childList;
    }

    @Override
    public void putExtMap(String key, Object value) {
        if (extMap == null) {
            extMap = new HashMap<>();
        }
        extMap.put(key, value);
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }

    @Override
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    @Override
    public Map<String, Object> getExtMap() {
        return extMap;
    }
}
