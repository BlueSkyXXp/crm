package com.stone.crm.domain;


import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "客户来访详情")
public class St02CrmVisitDetail {

    /** 客户id */
    private Long customerId;

    /** 客户名称 */
    private String customerName;

    /** 客户电话 */
    private String phone;

    /** 跟进人(用户ID) */
    private Long userId;

    /** 跟进人 */
    private String userName;

    private String remark;

    private String createTime;



}
