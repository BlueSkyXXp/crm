package com.stone.crm.util;

import com.stone.common.utils.DateUtil;
import com.stone.crm.domain.vo.CustomerReqVo;
import com.stone.crm.domain.vo.CustomerVo;

public class BeanUtil {

    public static CustomerReqVo toSt02CrmCustomer(CustomerVo customerVo) {
        CustomerReqVo customer = new CustomerReqVo(customerVo);
        if (customerVo.getRemarkDay() != null) {
            customer.setLastRemarkTime(
                    DateUtil.timestampToDateString(System.currentTimeMillis() - customerVo.getRemarkDay() * 86400 * 1000L, "yyyy-MM-dd HH:mm:ss"));
        }
        if (customerVo.getFirstAssignTime() != null && customerVo.getFirstAssignTime().size() > 1) {
            customer.setMaxFirstAssignTime(DateUtil.timestampToDateString(customerVo.getFirstAssignTime().get(1), "yyyy-MM-dd HH:mm:ss"));
            customer.setMinFirstAssignTime(DateUtil.timestampToDateString(customerVo.getFirstAssignTime().get(0), "yyyy-MM-dd HH:mm:ss"));
        }

        if (customerVo.getLastAssignTime() != null && customerVo.getLastAssignTime().size() > 1) {
            customer.setMaxLastAssignTime(DateUtil.timestampToDateString(customerVo.getLastAssignTime().get(1), "yyyy-MM-dd HH:mm:ss"));
            customer.setMinLastAssignTime(DateUtil.timestampToDateString(customerVo.getLastAssignTime().get(0), "yyyy-MM-dd HH:mm:ss"));
        }

        if (customerVo.getCreateTime() != null && customerVo.getCreateTime().size() > 1) {
            customer.setMaxCreateTime(DateUtil.timestampToDateString(customerVo.getCreateTime().get(1), "yyyy-MM-dd HH:mm:ss"));
            customer.setMinCreateTime(DateUtil.timestampToDateString(customerVo.getCreateTime().get(0), "yyyy-MM-dd HH:mm:ss"));
        }
        return customer;
    }
}
