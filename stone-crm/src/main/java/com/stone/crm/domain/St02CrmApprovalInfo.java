package com.stone.crm.domain;

import java.math.BigDecimal;
import com.stone.common.annotation.Excel;
import com.stone.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 审件对象 st02_crm_approval_info
 * 
 * @author xiongxiaopeng
 * @date 2024-06-25
 */
@Getter
@Setter
@ApiModel(value = "审件对象")
public class St02CrmApprovalInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 审件ID */
    private Long id;

    /** 进件日期 */
    @ApiModelProperty(value = "进件日期")
    @Excel(name = "进件日期")
    private String recdate;

    /** 单件类型: 信用贷... */
    @ApiModelProperty(value = "单件类型: 信用贷...")
    @Excel(name = "单件类型: 信用贷...")
    private Long type;

    /** 跟进人(用户ID) */
    @ApiModelProperty(value = "跟进人(用户ID)")
    @Excel(name = "跟进人(用户ID)")
    private Long userId;

    /** 客户名称 */
    @ApiModelProperty(value = "用户名称")
    @Excel(name = "用户名称", readConverterExp = "用户名称")
    private String userName;

    /** 客户id */
    @ApiModelProperty(value = "客户id")
    @Excel(name = "客户id")
    private Long customerId;

    /** 客户姓名 */
    @ApiModelProperty(value = "客户姓名")
    @Excel(name = "客户姓名")
    private String customerName;

    /** 审件状态: 审核,完成 */
    @ApiModelProperty(value = "审件状态: 审核,完成")
    @Excel(name = "审件状态: 审核,完成")
    private Long status;

    /** 付款方式 */
    @ApiModelProperty(value = "付款方式")
    @Excel(name = "付款方式")
    private String payType;

    /** 进件银行 */
    @ApiModelProperty(value = "进件银行")
    @Excel(name = "进件银行")
    private String approvalBank;

    /** 银行经理 */
    @ApiModelProperty(value = "银行经理")
    @Excel(name = "银行经理")
    private String bankManager;

    /** 费率 */
    @ApiModelProperty(value = "费率")
    @Excel(name = "费率")
    private String rates;

    /** 进件额度 */
    @ApiModelProperty(value = "进件额度")
    @Excel(name = "进件额度")
    private String money;

    /** 批款额度 */
    @ApiModelProperty(value = "批款额度")
    @Excel(name = "批款额度")
    private Long approMoney;

    /** 收款额度 */
    @ApiModelProperty(value = "收款额度")
    @Excel(name = "收款额度")
    private BigDecimal collectMoney;

    /** 收款日期 */
    @ApiModelProperty(value = "收款日期")
    @Excel(name = "收款日期")
    private String collectDate;

    /** 成本费用 */
    @ApiModelProperty(value = "成本费用")
    @Excel(name = "成本费用")
    private BigDecimal costMoney;

    /** 诚意金 */
    @ApiModelProperty(value = "诚意金")
    @Excel(name = "诚意金")
    private Long sincerityMoney;

    /** 利润 */
    @ApiModelProperty(value = "利润")
    @Excel(name = "利润")
    private BigDecimal profit;

    /** 收款银行 */
    @ApiModelProperty(value = "收款银行")
    @Excel(name = "收款银行")
    private String collectBank;

    /** 审批人(用户ID) */
    @ApiModelProperty(value = "审批人(用户ID)")
    @Excel(name = "审批人(用户ID)")
    private String operatorName;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("recdate", getRecdate())
                .append("type", getType())
                .append("userId", getUserId())
                .append("customerId", getCustomerId())
                .append("customerName", getCustomerName())
                .append("status", getStatus())
                .append("payType", getPayType())
                .append("approvalBank", getApprovalBank())
                .append("bankManager", getBankManager())
                .append("rates", getRates())
                .append("money", getMoney())
                .append("approMoney", getApproMoney())
                .append("collectMoney", getCollectMoney())
                .append("collectDate", getCollectDate())
                .append("costMoney", getCostMoney())
                .append("sincerityMoney", getSincerityMoney())
                .append("profit", getProfit())
                .append("collectBank", getCollectBank())
                .append("remark", getRemark())
                .append("operatorName", getOperatorName())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
