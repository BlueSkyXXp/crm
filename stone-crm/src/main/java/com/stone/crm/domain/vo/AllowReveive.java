package com.stone.crm.domain.vo;

import com.stone.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "批量开启/禁止接收客户--将废弃")
@Data
public class AllowReveive {

    @ApiModelProperty(value = "是否开启接收")
    @Excel(name = "是否开启接收")
    public Integer allowReveice;


    @ApiModelProperty(value = "用户id")
    @Excel(name = "用户ids")
    public List<Long> ids;

    public AllowReveive() {
    }

    public AllowReveive(Integer allowReveice, List<Long> ids) {
        this.allowReveice = allowReveice;
        this.ids = ids;
    }
}
