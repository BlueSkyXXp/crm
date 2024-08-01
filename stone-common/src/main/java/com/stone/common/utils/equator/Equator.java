package com.stone.common.utils.equator;


import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * <p>Description: 比较器接口</p>
 * <p>Copyright: Copyright (c) 2024</p>
 *
 * @author zjt
 * &#064;date  2024/05/26/14:58
 */
public interface Equator {

    /**
     * 获取值不一致的属性
     *
     * @param var1
     * @param var2
     * @return
     */
    List<?> getDifferentFields(Object var1, Object var2);


}