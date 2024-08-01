package com.stone.common.core.service;

import cn.hutool.db.Db;
import com.stone.common.annotation.DictValue;
import com.stone.common.utils.DictUtils;
import com.stone.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

public class BaseService {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public <T> void transBeanInList(List<T> dataList) {
        try {
            for (T data : dataList) {
                Field[] field= data.getClass().getDeclaredFields();
                for (Field f : field) {
                    if(f.isAnnotationPresent(DictValue.class)){
                        DictValue a =(DictValue)f.getAnnotation(DictValue.class);
                        String disp=a.disp();
                        String dictType=a.dict();
                        PropertyDescriptor pdValue = new PropertyDescriptor(f.getName(),data.getClass());
                        PropertyDescriptor pdDisp=new PropertyDescriptor(disp,data.getClass());
                        String dictValue=(String)pdValue.getReadMethod().invoke(data);
                        if(StringUtils.isEmpty(dictValue))continue;
                        String dispValue= DictUtils.getDictLabel(dictType,dictValue);
                        pdDisp.getWriteMethod().invoke(data,dispValue);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> void transBean(T data) {
        try {
            Field[] field= data.getClass().getDeclaredFields();
            for (Field f : field) {
                Annotation[] anno=f.getAnnotations();
                for (Annotation a : anno) {
                    if(a instanceof DictValue){
                        String disp=((DictValue) a).disp();
                        String dict=((DictValue) a).dict();
                        PropertyDescriptor pdValue = new PropertyDescriptor(f.getName(),data.getClass());
                        PropertyDescriptor pdDisp=new PropertyDescriptor(disp,data.getClass());
                        String valueDisp= DictUtils.getDictLabel(dict,(String)pdValue.getReadMethod().invoke(data));
                        pdDisp.getWriteMethod().invoke(data,valueDisp);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
