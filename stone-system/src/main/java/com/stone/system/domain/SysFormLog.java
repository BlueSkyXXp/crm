package com.stone.system.domain;

import com.stone.common.annotation.DictValue;
import com.stone.common.annotation.Excel;
import com.stone.common.utils.equator.EquatorFieldInfo;
import lombok.Data;

import java.util.List;

@Data
public class SysFormLog {
    /** 客户编号 */
    @Excel(name = "客户编号")
    private String customerId;

    /** 商机编号 */
    @Excel(name = "商机编号")
    private String businessId;

    /** 联系人编号 */
    @Excel(name = "联系人编号")
    private String contactsId;

    /** 合同编号 */
    @Excel(name = "合同编号")
    private String contractId;

    /** 回款编号 */
    @Excel(name = "回款编号")
    private String receivablesId;

    /** 回款计划编号 */
    @Excel(name = "回款计划编号")
    private String receivablesPlanId;

    /** 跟踪记录编号 */
    @Excel(name = "跟踪记录编号")
    private String recordId;

    /** 线索编号 */
    @Excel(name = "线索编号")
    private String leadsId;

    //业务类型
    @DictValue(disp = "businessTypeDisp",dict = "sys_oper_type")
    private String businessType;
    private String businessTypeDisp;

    //javabean的类型
    private String beanClass;

    /** 表单内容 */
    @Excel(name = "表单内容")
    private String operateParams;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private String createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @Excel(name = "更新时间")
    private String updatedTime;

    private List<EquatorFieldInfo> equatorFieldList;
}
