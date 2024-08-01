package com.stone.crm.domain;

import com.stone.common.annotation.Excel;
import com.stone.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 客户跟踪对象 st02_crm_customer_follow_info
 * 
 * @author xiongxiaopeng
 * @date 2024-06-27
 */
@Getter
@Setter
@ApiModel(value = "客户跟踪对象")
public class St02CrmCustomerFollowInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 跟踪记录ID */
    private Long id;

    /** 客户姓名 */
    @ApiModelProperty(value = "客户姓名")
    @Excel(name = "客户姓名")
    private Long customerId;

    /** 客户等级 */
    @ApiModelProperty(value = "客户等级")
    @Excel(name = "客户等级")
    private Long customerLevel;

    /** 客户状态:待跟进,已上门,已签约.... */
    @ApiModelProperty(value = "客户状态:待跟进,已上门,已签约....")
    @Excel(name = "客户状态:待跟进,已上门,已签约....")
    private Long customerStatus;

    /** 预审批额度 */
    @ApiModelProperty(value = "预审批额度")
    @Excel(name = "预审批额度")
    private Long approvalMoney;

    /** 预审批渠道 */
    @ApiModelProperty(value = "预审批渠道")
    @Excel(name = "预审批渠道")
    private String approvalChannel;

    /** 备注 */
    @ApiModelProperty(value = "备注")
    @Excel(name = "备注")
    private String remark;

    /** 点评 */
    @ApiModelProperty(value = "点评")
    @Excel(name = "点评")
    private String comments;

    /** 意向感知度备注 */
    @ApiModelProperty(value = "意向感知度备注")
    @Excel(name = "意向感知度备注")
    private String intentPerceptionRemark;

    /** 更新者 */
    @ApiModelProperty(value = "更新者")
    @Excel(name = "更新者")
    private String operator;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("customerId", getCustomerId())
            .append("customerLevel", getCustomerLevel())
            .append("customerStatus", getCustomerStatus())
            .append("approvalMoney", getApprovalMoney())
            .append("approvalChannel", getApprovalChannel())
            .append("remark", getRemark())
            .append("comments", getComments())
            .append("intentPerceptionRemark", getIntentPerceptionRemark())
            .append("operator", getOperator())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
