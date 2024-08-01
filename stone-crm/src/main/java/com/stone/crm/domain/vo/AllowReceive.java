package com.stone.crm.domain.vo;

import com.stone.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;


@ApiModel(value = "批量开启/禁止接收客户")
@Data
public class AllowReceive {

    @ApiModelProperty(value = "是否开启接收")
    @Excel(name = "是否开启接收")
    @NotNull(message = "是否开启接收不能为空")
    public Integer allowReceive;


    public Integer getAllowReceive() {
        return allowReceive;
    }

    public void setAllowReceive(Integer allowReceive) {
        this.allowReceive = allowReceive;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    @ApiModelProperty(value = "团队管理id")
    @Excel(name = "团队管理ids")
    public List<Long> ids;

    public AllowReceive() {
    }

    public AllowReceive(Integer allowReceive, List<Long> ids) {
        this.allowReceive = allowReceive;
        this.ids = ids;
    }
}





