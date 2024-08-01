package com.stone.crm.domain;

import java.math.BigDecimal;

import com.stone.common.annotation.DictValue;
import com.stone.common.annotation.Excel;
import com.stone.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 回款计划对象 st01_crm_receivables_plan
 * 
 * @author stone
 * @date 2024-04-25
 */
@Data
public class St01_crm_receivables_plan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 回款计划ID */
    private String planId;

    /** 期数 */
    @Excel(name = "期数")
    private String num;

    /** 回款ID */
    @Excel(name = "回款ID")
    private String receivablesId;

    /** 合同ID */
    @Excel(name = "合同ID")
    private String contractId;
    private String contractName;

    /** 客户ID */
    @Excel(name = "客户ID")
    private String customerId;

    /** 状态;1完成 0 未完成 */
    @Excel(name = "状态;1完成 0 未完成")
    @DictValue(dict="receivables_plan_status",disp="statusDisp")
    private String status;
    private String statusDisp;

    /** 计划回款金额 */
    @Excel(name = "计划回款金额")
    private BigDecimal money;

    /** 计划回款日期 */
    @Excel(name = "计划回款日期")
    private String returnDate;

    /** 计划回款方式;1：支付宝2：微信3：银行卡转账4：对公转账5：支票6：现金7：其他 */
    @Excel(name = "计划回款方式;1：支付宝2：微信3：银行卡转账4：对公转账5：支票6：现金7：其他")
    @DictValue(dict="receivables_plan_returntype",disp="returnTypeDisp")
    private String returnType;
    private String returnTypeDisp;

    /** 提前几天提醒;1：提前一天2：提前两天3：提前五天4：提前七天 */
    @Excel(name = "提前几天提醒;1：提前一天2：提前两天3：提前五天4：提前七天")
    @DictValue(dict="receivables_plan_remind",disp="remindDisp")
    private String remind;
    private String remindDisp;

    /** 提醒日期 */
    @Excel(name = "提醒日期")
    private String remindDate;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("planId", getPlanId())
            .append("num", getNum())
            .append("receivablesId", getReceivablesId())
            .append("contractId", getContractId())
            .append("customerId", getCustomerId())
            .append("status", getStatus())
            .append("money", getMoney())
            .append("returnDate", getReturnDate())
            .append("returnType", getReturnType())
            .append("remind", getRemind())
            .append("remindDate", getRemindDate())
            .append("remark", getRemark())
            .append("createdBy", getCreatedBy())
            .append("ownerUserId", getOwnerUserId())
            .append("createdTime", getCreatedTime())
            .append("updatedBy", getUpdatedBy())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
