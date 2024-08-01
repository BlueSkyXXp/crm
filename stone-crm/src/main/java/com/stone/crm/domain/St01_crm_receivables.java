package com.stone.crm.domain;

import java.math.BigDecimal;

import com.stone.common.annotation.DictValue;
import com.stone.common.annotation.Excel;
import com.stone.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 回款对象 st01_crm_receivables
 * 
 * @author stone
 * @date 2024-04-25
 */
@Data
public class St01_crm_receivables extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 回款ID */
    private String receivablesId;

    /** 回款编号 */
    @Excel(name = "回款编号")
    private String number;

    private String num;

    /** 回款计划ID */
    @Excel(name = "回款计划ID")
    private String planId;

    /** 客户ID */
    @Excel(name = "客户ID")
    private String customerId;

    /** 合同ID */
    @Excel(name = "合同ID")
    private String contractId;
    private String contractName;

    /** 回款日期 */
    @Excel(name = "回款日期")
    private String returnTime;

    /** 回款方式 */
    @Excel(name = "回款方式")
    @DictValue(disp="returnTypeDisp",dict="receivables_plan_returntype")
    private String returnType;
    private String returnTypeDisp;

    /** 回款金额 */
    @Excel(name = "回款金额")
    private BigDecimal money;

    /** 状态;0 未审核 1 审核通过 2 审核拒绝 3 审核中 4 已撤回 */
    @Excel(name = "状态;0 未审核 1 审核通过 2 审核拒绝 3 审核中 4 已撤回")
    @DictValue(disp="checkStatusDisp",dict="receivables_check_status")
    private String checkStatus;
    private String checkStatusDisp;

    /** 审核记录ID */
    @Excel(name = "审核记录ID")
    private String examineRecordId;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 负责人 */
    @Excel(name = "负责人")
    private String ownerUserId;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private String createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @Excel(name = "更新时间")
    private String updatedTime;


}
