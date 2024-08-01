package com.stone.crm.domain.vo;


import com.stone.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@ApiModel(value = "审件审核请求参数")
@Getter
@Setter
public class AuditVo {
    @ApiModelProperty(value = "审件id")
    @Excel(name = "审件id")
    private Long id;

    @ApiModelProperty(value = "审件状态")
    @Excel(name = "审件状态")
    private Long status;

    @ApiModelProperty(value = "成本")
    @Excel(name = "成本")
    private BigDecimal costMoney;

    public AuditVo(Long id, Long status, BigDecimal costMoney) {
        this.id = id;
        this.status = status;
        this.costMoney = costMoney;
    }

    public AuditVo() {
    }
}
