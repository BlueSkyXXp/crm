package com.stone.web.controller.tool;

public interface RootSelector {

    /**
     * 选择节点为根节点
     * @param node 被选择节点
     * @return true：选择为根节点；false：不选择为根节点
     */
    boolean select(Node node);
}
