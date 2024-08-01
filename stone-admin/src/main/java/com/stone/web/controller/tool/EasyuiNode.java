package com.stone.web.controller.tool;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Easyui节点实例
 */
public class EasyuiNode extends NodeInstance {
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        // ==========【（1）组装必有属性】==========
        jsonObject.put("id", this.getCode());
        jsonObject.put("text", this.getName());

        // ==========【（2）组装可选属性】==========
        String css = this.getCss();
        if (StringUtils.isNotEmpty(css)) {
            jsonObject.put("iconCls", css);
        }
        String state = this.getState();
        if (StringUtils.isNotEmpty(state)) {
            jsonObject.put("state", state);
        }
        Boolean checked = this.getChecked();
        if (checked != null) {
            jsonObject.put("checked", checked);
        }

        Map<String, Object> extMap = this.getExtMap();
        if (MapUtils.isNotEmpty(extMap)) {
            JSONObject attributesObject = new JSONObject();
            for (Map.Entry<String, Object> entry : extMap.entrySet()) {
                attributesObject.put(entry.getKey(), entry.getValue());
            }
            jsonObject.put("attributes", attributesObject);
        }

        // ==========【（3）组装子节点】==========
        List<Node> childList = this.getChildList();
        if (CollectionUtils.isNotEmpty(childList)) {
            JSONArray jsonArray = new JSONArray();
            for (Node child : childList) {
                jsonArray.add(child.toJson());
            }
            jsonObject.put("children", jsonArray);
        }
        return jsonObject;
    }

    @Override
    public int compareTo(Node o) {
        return super.compareTo(o);
    }
}

