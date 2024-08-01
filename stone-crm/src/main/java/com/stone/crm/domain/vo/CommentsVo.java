package com.stone.crm.domain.vo;

import com.stone.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel(value = "点评")
@Data
@Builder
public class CommentsVo {
    /** 点评 */
    @ApiModelProperty(value = "点评")
    @Excel(name = "点评")
    public String comment;

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间")
    @Excel(name = "创建时间")
    public String createTime;

    /** 操作者 */
    @ApiModelProperty(value = "操作者")
    @Excel(name = "操作者")
    public String operator;

    public CommentsVo() {
    }

    public CommentsVo(String comment, String createTime, String operator) {
        this.comment = comment;
        this.createTime = createTime;
        this.operator = operator;
    }
}
