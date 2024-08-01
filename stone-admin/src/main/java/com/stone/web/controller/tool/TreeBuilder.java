package com.stone.web.controller.tool;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 树构建器
 *
 * @param <T>
 */
public class TreeBuilder<T> {

    /**
     * 节点转换器
     */
    private NodeConvert<T> nodeConvert;

    /**
     * 根节点选择器
     */
    private RootSelector rootSelector;

    public TreeBuilder(NodeConvert nodeConvert, RootSelector rootSelector) {
        this.nodeConvert = nodeConvert;
        this.rootSelector = rootSelector;
    }

    /**
     * 初始化节点列表
     *
     * @return
     */
    protected CopyOnWriteArrayList<Node> initNodeList(List<T> list) {
        CopyOnWriteArrayList<Node> nodeList = new CopyOnWriteArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (T model : list) {
                nodeList.add(nodeConvert.convert(model));
            }
        }
        return nodeList;
    }

    /**
     * 构建节点结构
     *
     * @param nodeList
     * @return
     */
    protected CopyOnWriteArrayList<Node> buildNodeStructure(CopyOnWriteArrayList<Node> nodeList) {
        CopyOnWriteArrayList<Node> rootList = new CopyOnWriteArrayList<>();
        if (CollectionUtils.isNotEmpty(nodeList)) {
            // 1、构建root节点
            for (Node node : nodeList) {
                if (rootSelector.select(node)) {
                    rootList.add(node);
                    nodeList.remove(node);
                }
            }
            // 2、循环查找子节点
            for (Node rootNode : rootList) {
                this.buildChildNode(rootNode, nodeList);
            }
        }
        return rootList;
    }

    /**
     * 构建子节点（反复调用）
     *
     * @param parentNode     父节点
     * @param originNodeList 自己节点列表
     */
    protected void buildChildNode(Node parentNode, CopyOnWriteArrayList<Node> originNodeList) {
        if (CollectionUtils.isNotEmpty(originNodeList)) {
            for (Node node : originNodeList) {
                if (parentNode.getCode().equals(node.getParentCode())) {
                    parentNode.addChildNode(node);
                    originNodeList.remove(node);
                    this.buildChildNode(node, originNodeList);
                }
            }

        }
    }

    /**
     * 节点集合排序
     *
     * @param nodeList
     * @return
     */
    protected void sortNodeList(List<Node> nodeList) {
        if (CollectionUtils.isNotEmpty(nodeList)) {
            Collections.sort(nodeList);
            for (Node node : nodeList) {
                if (CollectionUtils.isNotEmpty(node.getChildList())) {
                    this.sortNodeList(node.getChildList());
                }
            }
        }
    }

    /**
     * 构建树
     *
     * @param list
     * @return
     */
    public Tree build(CopyOnWriteArrayList<T> list) {
        CopyOnWriteArrayList<Node> nodeList = this.initNodeList(list);
        CopyOnWriteArrayList<Node> buildNodeList = this.buildNodeStructure(nodeList);
        this.sortNodeList(buildNodeList);

        Tree tree = new Tree();
        tree.setNodeList(buildNodeList);
        return tree;
    }

}

