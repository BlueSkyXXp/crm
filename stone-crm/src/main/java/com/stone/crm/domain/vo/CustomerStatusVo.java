package com.stone.crm.domain.vo;

import com.stone.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "不同类型客户查询接口参数")
@Data
public class CustomerStatusVo {


    /** 跟进人(用户ID) */
    @ApiModelProperty(value = "跟进人(用户ID)")
    @Excel(name = "跟进人(用户ID)")
    public Long userId;

    /** 客户状态 */
    @ApiModelProperty(value = "客户状态")
    @Excel(name = "客户状态")
    public Long status;

    public CustomerStatusVo(Long userId, Long status) {
        this.userId = userId;
        this.status = status;
    }

    public CustomerStatusVo() {
    }
}
