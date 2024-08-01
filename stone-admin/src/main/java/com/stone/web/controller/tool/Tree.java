package com.stone.web.controller.tool;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * 树
 */
public class Tree {

    /**
     * 根节点
     */
    private List<Node> nodeList;

    public Tree() {
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public String toJson() {
        JSONArray jsonArray = new JSONArray();

        if (CollectionUtils.isNotEmpty(nodeList)) {
            for (Node node : nodeList) {
                jsonArray.add(node.toJson());
            }
        }
        return jsonArray.toJSONString();

    }


}

