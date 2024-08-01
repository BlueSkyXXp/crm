package demo;

import com.stone.common.utils.equator.EqualsAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleEntityTwo {

    @EqualsAnnotation(value = "three中文名称2", describe = "three属性描述2")
    private ExampleEntityThree three;

    @EqualsAnnotation(value = "Byte中文名称2", describe = "Byte2属性描述")
    private Byte byteExample;

    @EqualsAnnotation(value = "Short中文名称2", describe = "Short2属性描述")
    private Short shortExample;

    @EqualsAnnotation(value = "Integer中文名称2", describe = "Integer2属性描述")
    private Integer integerExample;

    @EqualsAnnotation(value = "Long中文名称2", describe = "Long2属性描述")
    private Long longExample;

    @EqualsAnnotation(value = "Float中文名称2", describe = "Float2属性描述")
    private Float floatExample;

    @EqualsAnnotation(value = "Double中文名称2", describe = "Double2属性描述")
    private Double doubleExample;

    @EqualsAnnotation(value = "Character中文名称2", describe = "Character2属性描述")
    private Character characterExample;

    @EqualsAnnotation(value = "Boolean中文名称2", describe = "Boolean2属性描述")
    private Boolean booleanExample;

    @EqualsAnnotation(value = "String中文名称2", describe = "String2属性描述")
    private String stringExample;



}
