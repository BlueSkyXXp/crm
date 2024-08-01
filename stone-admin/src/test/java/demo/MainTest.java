package demo;

import com.stone.common.utils.equator.EquatorFieldInfo;
import com.stone.common.utils.equator.EquatorUtil;
import lombok.extern.slf4j.Slf4j;


import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.List;


@Slf4j
public class MainTest {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("item1");
        list.add("item2");
        list.add("item3");

        int lastIndex = list.size() - 1;
        list.remove(lastIndex);

        System.out.println(list);
    }
    /**
     * 单个对象基础比较
     */
   /* @Test
    public void testSingleObject() {

        ExampleEntityOne example1 = new ExampleEntityOne();
        example1.setBooleanExample(true);
        example1.setStringExample("6");
        ExampleEntityOne example2 = new ExampleEntityOne();
        example2.setByteExample("1".getBytes(StandardCharsets.UTF_8)[0]);
        example2.setBooleanExample(false);
        example2.setStringExample("7");
        List<EquatorFieldInfo> differentFields = EquatorUtil.getDifferentFields(example1, example2);
        differentFields.forEach(info -> {
            System.out.println("属性名: " + info.getFieldName());
            System.out.println("属性中文名: " + info.getFieldNote());
            System.out.println("old属性值: " + info.getFirstVal());
            System.out.println("new属性值: " + info.getSecondVal());
            System.out.println("====================================================");
        });
    }

    *//**
     * 级联对象比较
     *//*
    @Test
    public void testCascadingObject() {
        ExampleEntityOne example1 = new ExampleEntityOne();
        example1.setShortExample((short) 123);

        ExampleEntityTwo two1 = new ExampleEntityTwo();
        two1.setLongExample(111L);

        ExampleEntityThree three = new ExampleEntityThree();
        three.setStringExample("ExampleEntityTwo two1 string three");
        two1.setThree(three);
        example1.setTwo(two1);

        ExampleEntityOne example2 = new ExampleEntityOne();
        example2.setShortExample((short) 1234);

        ExampleEntityTwo two2 = new ExampleEntityTwo();
        two2.setLongExample(2222L);

        example2.setTwo(two2);
        ExampleEntityThree three1 = new ExampleEntityThree();
        three1.setStringExample("ExampleEntityTwo two2 string three");
        two2.setThree(three1);
        example2.setTwo(two2);

        List<EquatorFieldInfo> differentFields = EquatorUtil.getDifferentFields(example1, example2);
        differentFields.forEach(info -> {
            System.out.println("属性名: " + info.getFieldName());
            System.out.println("属性中文名: " + info.getFieldNote());
            System.out.println("old属性值: " + info.getFirstVal());
            System.out.println("new属性值: " + info.getSecondVal());
            System.out.println("====================================================");
        });
        //输出
        //属性名: shortExample
        //属性中文名: Short中文名称1
        //属性描述: Short属性描述
        //old属性类型: class java.lang.Short
        //old属性值: 123
        //new属性值: 1234
        //====================================================
        //属性名: stringExample
        //属性中文名: String中文名称3
        //属性描述: String3属性描述
        //old属性类型: class java.lang.String
        //old属性值: ExampleEntityTwo two1 string three
        //new属性值: ExampleEntityTwo two2 string three
        //====================================================
    }

    *//**
     * 对象集合属性基础比较
     *//*
    @Test
    public void testCollectionObject() {
        ExampleEntityOne example1 = new ExampleEntityOne();
        example1.setStringExample("String属性值1");
        ExampleEntityOne example2 = new ExampleEntityOne();
        example2.setStringExample("String属性值2");
        List<ExampleEntityOne> list1= new ArrayList<>();
        list1.add(example1);
        List<ExampleEntityOne> list2= new ArrayList<>();
        list2.add(example2);


        List<EquatorFieldInfo> differentFields = EquatorUtil.getDifferentFields(list1, list2);
        differentFields.forEach(info -> {
            System.out.println("属性名: " + info.getFieldName());
            System.out.println("属性中文名: " + info.getFieldNote());
            System.out.println("old属性值: " + info.getFirstVal());
            System.out.println("new属性值: " + info.getSecondVal());
            System.out.println("====================================================");
        });
        //输出
        //属性名: stringExample
        //属性中文名: String中文名称1
        //属性描述: String属性描述
        //old属性类型: class java.lang.String
        //old属性值: String属性值1
        //new属性值: String属性值2
        //====================================================
    }*/

}
