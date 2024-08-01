package com.stone.web.controller.tool;

/**
 * 节点转换器
 */
public interface NodeConvert<T> {

    /**
     * 实体转换为节点
     * @param t
     * @return
     */
    Node convert(T t);

}

