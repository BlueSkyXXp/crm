package com.stone.system.service.impl;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson2.JSON;
import com.stone.common.annotation.DictValue;
import com.stone.common.core.service.BaseService;
import com.stone.common.utils.DictUtils;
import com.stone.common.utils.equator.EquatorFieldInfo;
import com.stone.common.utils.equator.EquatorUtil;
import io.jsonwebtoken.lang.Collections;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.system.mapper.SysFormLogMapper;
import com.stone.system.domain.SysFormLog;
import com.stone.system.service.ISysFormLogService;

/**
 * 单变更日志记录Service业务层处理
 *
 * @author ruoyi
 * @date 2024-06-07
 */
@Service
public class SysFormLogServiceImpl extends BaseService implements ISysFormLogService
{

    @Autowired
    private SysFormLogMapper sysFormLogMapper;

    /**
     * 查询单变更日志记录
     *
     * @param customerId 单变更日志记录主键
     * @return 单变更日志记录
     */
    @Override
    public SysFormLog selectSysFormLogByCustomerId(String customerId)
    {
        return sysFormLogMapper.selectSysFormLogByCustomerId(customerId);
    }

    /**
     * 查询单变更日志记录列表
     *
     * @param sysFormLog 单变更日志记录
     * @return 单变更日志记录
     */
    @Override
    public List<SysFormLog> selectSysFormLogList(SysFormLog sysFormLog) throws ClassNotFoundException {
        //根据ID获取当前表单的所有日志记录
        List<SysFormLog> resultList=sysFormLogMapper.selectSysFormLogList(sysFormLog);
        transBeanInList(resultList);
        List<SysFormLog> list= new ArrayList<>();
        for(int i=2;i < resultList.size();i++){
            SysFormLog formLog = resultList.get(i);
            //比较两个对象中数据的差异
            Class beanClass= Class.forName(formLog.getBeanClass());
            Object firstObject = JSON.parseObject(formLog.getOperateParams(), beanClass);
            Object secondObject = JSON.parseObject(resultList.get(i - 1).getOperateParams(), beanClass);
            //实现对象中码值的转换
            transBeanByData(firstObject,beanClass);
            transBeanByData(secondObject,beanClass);
            List<EquatorFieldInfo> equalResult=EquatorUtil.getDifferentFields(firstObject, secondObject);
            if(!Collections.isEmpty(equalResult)){
                formLog.setEquatorFieldList(equalResult);
                list.add(resultList.get(i));
            }
        }
        return list;
    }

    public void transBeanByData(Object bean, Class beanClass) {
        try {
            Field[] field= beanClass.getDeclaredFields();
            for (Field f : field) {
                Annotation[] anno=f.getAnnotations();
                for (Annotation a : anno) {
                    if(a instanceof DictValue){
                        String disp=((DictValue) a).disp();
                        String dict=((DictValue) a).dict();
                        PropertyDescriptor pdValue = new PropertyDescriptor(f.getName(),beanClass);
                        PropertyDescriptor pdDisp=new PropertyDescriptor(disp,beanClass);
                        String valueDisp= DictUtils.getDictLabel(dict,(String)pdValue.getReadMethod().invoke(bean));
                        pdDisp.getWriteMethod().invoke(bean,valueDisp);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 新增单变更日志记录
     *
     * @param sysFormLog 单变更日志记录
     * @return 结果
     */
    @Override
    public int insertSysFormLog(SysFormLog sysFormLog)
    {
        return sysFormLogMapper.insertSysFormLog(sysFormLog);
    }

    /**
     * 修改单变更日志记录
     *
     * @param sysFormLog 单变更日志记录
     * @return 结果
     */
    @Override
    public int updateSysFormLog(SysFormLog sysFormLog)
    {
        return sysFormLogMapper.updateSysFormLog(sysFormLog);
    }

    /**
     * 批量删除单变更日志记录
     *
     * @param customerIds 需要删除的单变更日志记录主键
     * @return 结果
     */
    @Override
    public int deleteSysFormLogByCustomerIds(String[] customerIds)
    {
        return sysFormLogMapper.deleteSysFormLogByCustomerIds(customerIds);
    }

    /**
     * 删除单变更日志记录信息
     *
     * @param customerId 单变更日志记录主键
     * @return 结果
     */
    @Override
    public int deleteSysFormLogByCustomerId(String customerId)
    {
        return sysFormLogMapper.deleteSysFormLogByCustomerId(customerId);
    }
}
