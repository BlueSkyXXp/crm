package com.stone.common.utils.equator;

import lombok.extern.slf4j.Slf4j;

import java.util.List;


/**
 * <p>Description: 调用工具类</p>
 * <p>Copyright: Copyright (c) 2024</p>
 *
 * @author zjt
 * &#064;date  2024/05/26/14:58
 */
@Slf4j
public class EquatorUtil {

    /**
     * 属性比较对象
     */
    private static final BaseFieldEquator BASE_FIELD_EQUATOR = new BaseFieldEquator();

    public static List<EquatorFieldInfo> getDifferentFields(Object first, Object second) {
        return BASE_FIELD_EQUATOR.getDifferentFields(first, second);
    }

}
