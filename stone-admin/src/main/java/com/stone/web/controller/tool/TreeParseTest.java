package com.stone.web.controller.tool;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TreeParseTest {


    public static void main(String[] args){



        // 节点转换器（将任意对象转换为树节点）
        NodeConvert departmentConverter = new NodeConvert<DeptModel>() {
            @Override
            public Node convert(DeptModel area) {
                // 根据实体的属性，补充到对应的NodeInstance对象上（转换为EasyUi）
                NodeInstance instance = new EasyuiNode();
                instance.setParentCode(area.getParentId());
                instance.setCode(area.getId());
                instance.setName(area.getArdeptName());
                instance.setOrder(area.getOrder());
                // 根据实际业务，配置其他属性......
                /*instance.setCss("icon-value");
                instance.setChecked(true);
                instance.setState("opened");
                instance.putExtMap("aa", "aaVaule");*/

                return instance;
            }
        };

        // 默认根节点选择器（确定根节点）
        RootSelector defaultSelector = new RootSelector() {
            @Override
            public boolean select(Node node) {
                if (node != null && StringUtils.isEmpty(node.getParentCode())) {
                    return true;
                }
                return false;
            }
        };

        CopyOnWriteArrayList<DeptModel> areaList = new CopyOnWriteArrayList<>();
        areaList.add(new DeptModel("100", "若依", 1, ""));
        areaList.add(new DeptModel("101", "一部", 1, "100"));
        areaList.add(new DeptModel("102", "二部", 2, "100"));
        areaList.add(new DeptModel("103", "研发部门", 1, "101"));
        areaList.add(new DeptModel("104", "市场部门", 2, "101"));
        areaList.add(new DeptModel("105", "测试部门", 3, "101"));
        areaList.add(new DeptModel("106", "财务部门", 4, "101"));
        areaList.add(new DeptModel("107", "运维部门", 5, "101"));
        areaList.add(new DeptModel("108", "市场部门", 1, "102"));
        areaList.add(new DeptModel("109", "财务部门", 2, "102"));
        areaList.add(new DeptModel("110", "融翘科技", 2, ""));
        areaList.add(new DeptModel("111", "一部", 1, "110"));

        TreeBuilder<DeptModel> treeBuilder = new TreeBuilder<>(departmentConverter, defaultSelector);
        Tree tree = treeBuilder.build(areaList);
        System.out.println(tree.toJson());
    }
}
