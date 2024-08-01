package com.stone.common.utils.equator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Description: 在需要比较的属性上添加注解@EqualsAnnotation</p>
 * <p>Copyright: Copyright (c) 2024</p>
 *
 * @author zjt
 * &#064;date  2024/05/26/14:58
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EqualsAnnotation {

    /**
     * 属性名称
     */
    String value() default "";

    /**
     * 属性描述
     */
    String describe() default "";

}
