package com.stone.crm.domain;

import java.math.BigDecimal;
import java.util.List;

import com.stone.common.annotation.DictValue;
import com.stone.common.annotation.Excel;
import com.stone.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 合同对象 st01_crm_contract
 * 
 * @author stone
 * @date 2024-04-25
 */
@Data
public class St01_crm_contract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同编号 */
    @Excel(name = "合同编号")
    private String contractId;

    /** 合同名称 */
    @Excel(name = "合同名称")
    private String contractName;

    /** 合同金额 */
    @Excel(name = "合同金额")
    private BigDecimal money;

    /** 客户 */
    @Excel(name = "客户")
    private String customerId;
    private String customerName;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contactsId;
    private String contactsName;

    /** 商机 */
    @Excel(name = "商机")
    private String businessId;
    private String businessName;

    /** 合同类型 */
    @Excel(name = "合同类型")
    @DictValue(dict="contract_type", disp = "typesDisp")
    private String types;//1：新合同，2：续约合同
    private String typesDisp;
    /** 下单日期 */
    @Excel(name = "下单日期")
    private String orderDate;

    /** 开始时间 */
    @Excel(name = "开始时间")
    private String startTime;

    /** 结束时间 */
    @Excel(name = "结束时间")
    private String endTime;

    /** 状态 */
    private String checkStatus;

    /** 审核记录ID */
    private String examineRecordId;

    /** 整单折扣 */
    private BigDecimal discountRate;

    /** 产品总金额 */
    private BigDecimal totalPrice;

    /** 付款方式 */
    private String paymentType;

    /** 只读权限 */
    private String roUserId;

    /** 读写权限 */
    private String rwUserId;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 负责人 */
    @Excel(name = "负责人")
    private String ownerUserId;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private String createdTime;

    /** 更新时间 */
    private String updatedTime;

    private List<St01_crm_contract_product> productList;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("contractId", this.getContractId())
            .append("contractName", this.getContractName())
            .append("money", getMoney())
            .append("customerId", getCustomerId())
            .append("contactsId", getContactsId())
            .append("businessId", getBusinessId())
            .append("types", getTypes())
            .append("orderDate", getOrderDate())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("checkStatus", getCheckStatus())
            .append("examineRecordId", getExamineRecordId())
            .append("discountRate", getDiscountRate())
            .append("totalPrice", getTotalPrice())
            .append("paymentType", getPaymentType())
            .append("roUserId", getRoUserId())
            .append("rwUserId", getRwUserId())
            .append("remark", getRemark())
            .append("updatedBy", getUpdatedBy())
            .append("createdBy", getCreatedBy())
            .append("ownerUserId", getOwnerUserId())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
