package com.stone.crm.domain.vo;

import com.stone.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "分配客户接口参数")
@Data
public class CustomerAssignVo {

    /** 跟进人(用户ID) */
    @ApiModelProperty(value = "跟进人(用户ID)")
    @Excel(name = "跟进人(用户ID)")
    public Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(List<Long> customerIds) {
        this.customerIds = customerIds;
    }

    /** 客户IDS */
    @ApiModelProperty(value = "客户IDS")
    @Excel(name = "客户IDS")
    public List<Long> customerIds;

    public CustomerAssignVo() {
    }

    public CustomerAssignVo(Long userId, List<Long> customerIds) {
        this.userId = userId;
        this.customerIds = customerIds;
    }
}
