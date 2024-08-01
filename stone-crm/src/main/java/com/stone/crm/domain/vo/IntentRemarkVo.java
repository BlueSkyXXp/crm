package com.stone.crm.domain.vo;

import com.stone.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel(value = "意向感知度备注")
@Data
@Builder
public class IntentRemarkVo {
    /** 意向感知度备注 */
    @ApiModelProperty(value = "意向感知度备注")
    @Excel(name = "意向感知度备注")
    public String intentRemark;

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间")
    @Excel(name = "创建时间")
    public String createTime;

    /** 操作者 */
    @ApiModelProperty(value = "操作者")
    @Excel(name = "操作者")
    public String operator;

    public IntentRemarkVo() {
    }

    public IntentRemarkVo(String intentRemark, String createTime, String operator) {
        this.intentRemark = intentRemark;
        this.createTime = createTime;
        this.operator = operator;
    }
}
