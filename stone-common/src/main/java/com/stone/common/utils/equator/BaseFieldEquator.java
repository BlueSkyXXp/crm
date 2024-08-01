package com.stone.common.utils.equator;
import com.stone.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.*;

/**
 * <p>Description: 比较器具体实现</p>
 */
@Slf4j
public class BaseFieldEquator{

    /**
     * 返回被修改的属性值
     */
    public <T> List<EquatorFieldInfo> getDifferentFields(T first, T second) {
        if (first == second || Objects.equals(first, second)) return new ArrayList<>();
        List<EquatorFieldInfo> diffFields=new ArrayList<>();
        Class firstClass=first.getClass();
        Class secondClass=second.getClass();
        Field[] fields=firstClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(EqualsAnnotation.class)){
                //判断字段上是否有注解@EqualsAnnotation
                EqualsAnnotation equalsAnnotation = field.getAnnotation(EqualsAnnotation.class);
                //属性名称
                String fieldName = field.getName();
                try {
                    System.out.println(first);
                    System.out.println(new PropertyDescriptor(fieldName,firstClass).getReadMethod().invoke(first));
                    Object firstVal = new PropertyDescriptor(fieldName,firstClass)
                            .getReadMethod()
                            .invoke(first);
                    Object secondVal = new PropertyDescriptor(fieldName,secondClass)
                            .getReadMethod()
                            .invoke(second);
                    if(!Objects.equals(firstVal,secondVal)){
                        EquatorFieldInfo efi = new EquatorFieldInfo();
                        efi.setFirstVal(firstVal);
                        efi.setSecondVal(secondVal);
                        //属性中文别名
                        String value = equalsAnnotation.value();
                        if (StringUtils.isEmpty(value)) {
                            value = field.getName();
                        }
                        efi.setFieldNote(value);
                        efi.setFieldName(fieldName);
                        //属性类型
                        efi.setFieldType(field.getType());
                        diffFields.add(efi);
                    }

                } catch (Exception e) {
                    log.error("获取属性失败", e);
                }
            }
        }
        return diffFields;
    }


}
