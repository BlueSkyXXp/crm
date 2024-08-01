package com.stone.crm.domain.vo;

import com.stone.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "分配记录")
@Data
public class FlowInfoResult {
    /** 目标 */
    @ApiModelProperty(value = "目标")
    @Excel(name = "目标")
    public String targetPlat;

    /** 计数 */
    @ApiModelProperty(value = "计数")
    @Excel(name = "计数")
    public Integer count;

    public FlowInfoResult(String targetPlat, Integer count) {
        this.targetPlat = targetPlat;
        this.count = count;
    }

    public FlowInfoResult() {
    }
}
