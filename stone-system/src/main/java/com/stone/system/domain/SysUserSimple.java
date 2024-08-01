package com.stone.system.domain;

import com.stone.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "用戶信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class SysUserSimple {
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    @Excel(name = "用户ID")
    public Long userId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @Excel(name = "userName")
    public String userName;

    /**
     * 状态
     */
    @ApiModelProperty(value = "用户状态")
    @Excel(name = "用户状态")
    public Long delFlag;
}
