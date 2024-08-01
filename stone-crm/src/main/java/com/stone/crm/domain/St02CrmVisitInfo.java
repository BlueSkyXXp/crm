package com.stone.crm.domain;

import com.stone.common.annotation.Excel;
import com.stone.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 客户来访对象 st02_crm_visit_info
 * 
 * @author xiongxiaopeng
 * @date 2024-06-25
 */
@Setter
@Getter
@ApiModel(value = "客户来访对象")
public class St02CrmVisitInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 来访记录ID */
    private Long id;

    /** 客户id */
    @ApiModelProperty(value = "客户id")
    @Excel(name = "客户id")
    private Long customerId;

    /** 客户名称 */
    @ApiModelProperty(value = "客户名称")
    @Excel(name = "客户名称")
    private String customerName;

    /** 客户电话 */
    @ApiModelProperty(value = "客户电话")
    @Excel(name = "phone")
    private String phone;

    /** 跟进人(用户ID) */
    @ApiModelProperty(value = "跟进人(用户ID)")
    @Excel(name = "跟进人(用户ID)")
    private Long userId;

    /** 跟进人 */
    @ApiModelProperty(value = "跟进人")
    @Excel(name = "跟进人")
    private String userName;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("customerId", getCustomerId())
            .append("remark", getRemark())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
