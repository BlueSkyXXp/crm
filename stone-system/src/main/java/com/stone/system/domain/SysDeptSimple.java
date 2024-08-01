package com.stone.system.domain;

import com.stone.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel(value = "部门信息")
@Data
@Builder
public class SysDeptSimple {
    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门ID")
    @Excel(name = "部门ID")
    public Long deptId;

    /**
     * 部门名
     */
    @ApiModelProperty(value = "部门名")
    @Excel(name = "deptName")
    public String deptName;
}
