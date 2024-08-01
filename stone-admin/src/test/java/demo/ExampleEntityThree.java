package demo;

import com.stone.common.utils.equator.EqualsAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleEntityThree {

    @EqualsAnnotation(value = "String中文名称3", describe = "String3属性描述")
    private String stringExample;

}
