package com.stone.web.controller.tool;

import com.stone.common.core.domain.entity.SysDept;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ParseUtil {
    public static String list2Json(List<SysDept> depts) {
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
        CopyOnWriteArrayList<DeptModel> deptModels = new CopyOnWriteArrayList<>();
        for (SysDept sysDept : depts) {
            if ( sysDept.getParentId() < 10) {
                deptModels.add(new DeptModel(sysDept.getDeptId().toString(), sysDept.getDeptName(), sysDept.getOrderNum(), ""));
            } else {
                deptModels.add(new DeptModel(sysDept.getDeptId().toString(), sysDept.getDeptName(), sysDept.getOrderNum(), sysDept.getParentId().toString()));
            }
        }
        TreeBuilder<DeptModel> treeBuilder = new TreeBuilder<>(departmentConverter, defaultSelector);
        Tree tree = treeBuilder.build(deptModels);
        return tree.toJson();
    }
}
