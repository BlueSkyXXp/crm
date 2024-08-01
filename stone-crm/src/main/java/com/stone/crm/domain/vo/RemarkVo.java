package com.stone.crm.domain.vo;

import com.stone.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel(value = "备注")
@Data
@Builder
public class RemarkVo {
    /** 备注 */
    @ApiModelProperty(value = "备注")
    @Excel(name = "备注")
    public String remark;

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间")
    @Excel(name = "创建时间")
    public String createTime;

    /** 操作者 */
    @ApiModelProperty(value = "操作者")
    @Excel(name = "操作者")
    public String operator;

    public RemarkVo() {
    }

    public RemarkVo(String remark, String createTime, String operator) {
        this.remark = remark;
        this.createTime = createTime;
        this.operator = operator;
    }
}
