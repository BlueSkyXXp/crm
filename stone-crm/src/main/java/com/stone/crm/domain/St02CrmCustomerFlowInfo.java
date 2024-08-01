package com.stone.crm.domain;

import com.stone.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import com.stone.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 客户流转记录对象 st02_crm_customer_flow_info
 * 
 * @author xiongxiaopeng
 * @date 2024-06-25
 */
@Setter
@Getter
@ApiModel(value = "客户流转记录对象")
public class St02CrmCustomerFlowInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流转记录ID */
    private Long id;

    /** 客户姓名 */
    @ApiModelProperty(value = "客户姓名")
    @Excel(name = "客户姓名")
    private String customerId;

    /** 客户来源 */
    @ApiModelProperty(value = "客户来源")
    @Excel(name = "客户来源")
    private String sourcePlat;

    /** 客户去向 */
    @ApiModelProperty(value = "客户去向")
    @Excel(name = "客户去向")
    private String targetPlat;

    /** 操作者(用户ID) */
    @ApiModelProperty(value = "操作者(用户ID)")
    @Excel(name = "操作者(用户ID)")
    private Long userId;

    /** 操作者(用户姓名) */
    @ApiModelProperty(value = "操作者(用户姓名)")
    @Excel(name = "操作者(用户姓名)")
    private String userName;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("customerId", getCustomerId())
            .append("sourcePlat", getSourcePlat())
            .append("targetPlat", getTargetPlat())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("createTime", getCreateTime())
            .toString();
    }
}
