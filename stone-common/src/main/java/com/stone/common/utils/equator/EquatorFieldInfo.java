package com.stone.common.utils.equator;

import lombok.Data;
import lombok.Getter;


/**
 * <p>Description: 解下比较对象返回的结果</p>
 */
@Data
public class EquatorFieldInfo {
    /**
     * 属性名
     */
    private String fieldName;
    /**
     * 属性中文名
     */
    private String fieldNote;
    /**
     * 第一属性类型
     */
    private Class<?> fieldType;
    /**
     * 第一属性值，变更前数据
     */
    private Object firstVal;
    /**
     * 第二属性值，变更后数据
     */
    private Object secondVal;

    @Override
    public String toString() {
        String val = "{" + fieldNote + ":" + this.firstVal + ":" + this.secondVal + "}";

        return val;
    }
}
