package com.stone.web.controller.tool;


import java.util.List;
import java.util.Map;

/**
 * 节点
 */
public interface Node extends JsonParser, Comparable<Node> {

    /**
     * 获取节点代码
     *
     * @return
     */
    String getCode();

    /**
     * 获取节点名称
     *
     * @return
     */
    String getName();

    /**
     * 获取节点排序
     *
     * @return
     */
    Integer getOrder();

    /**
     * 获取节点样式
     *
     * @return
     */
    String getCss();

    /**
     * 获取节点状态
     *
     * @return
     */
    String getState();

    /**
     * 获取节点复选状态
     *
     * @return
     */
    Boolean getChecked();

    /**
     * 扩展属性
     *
     * @return
     */
    Map<String, Object> getExtMap();

    /**
     * 获取父节点代码
     *
     * @return
     */
    String getParentCode();

    /**
     * 获取子节点集合
     *
     * @return
     */
    List<Node> getChildList();

    @Override
    default int compareTo(Node o) {
        return this.getOrder().compareTo(o.getOrder());
    }

    /**
     * 添加子节点
     *
     * @param node 子节点
     */
    void addChildNode(Node node);

    /**
     * 设置额外属性
     * @param key
     * @param value
     */
    void putExtMap(String key, Object value);
}
