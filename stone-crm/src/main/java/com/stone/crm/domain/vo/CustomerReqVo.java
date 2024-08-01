package com.stone.crm.domain.vo;
import com.stone.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;


/**
 * 客户请求参数对象 st02_crm_customer_vo
 *
 * @author xiongxiaopeng
 * @date 2024-06-29
 */
@Getter
@Setter
@ApiModel(value = "客户请求参数对象")
public class CustomerReqVo extends CustomerVo {

    private static final long serialVersionUID = 1L;

    /** 最近备注时间 */
    @ApiModelProperty(value = "最近备注时间")
    @Excel(name = "最近备注时间")
    private String lastRemarkTime;

    /** 申请时间 */
    @ApiModelProperty(value = "申请时间")
    @Excel(name = "申请时间")
    private String minCreateTime;

    /** 原始分配时间 */
    @ApiModelProperty(value = "原始分配时间")
    @Excel(name = "原始分配时间")
    private String minFirstAssignTime;

    /** 最近分配时间 */
    @ApiModelProperty(value = "最近分配时间")
    @Excel(name = "最近分配时间")
    private String minLastAssignTime;

    /** 申请时间 */
    @ApiModelProperty(value = "申请时间")
    @Excel(name = "申请时间")
    private String maxCreateTime;

    /** 原始分配时间 */
    @ApiModelProperty(value = "原始分配时间")
    @Excel(name = "原始分配时间")
    private String maxFirstAssignTime;

    /** 最近分配时间 */
    @ApiModelProperty(value = "最近分配时间")
    @Excel(name = "最近分配时间")
    private String maxLastAssignTime;

    public CustomerReqVo() {
        super();
    }

    public CustomerReqVo(CustomerVo customerVo) {
        super(customerVo);
    }

}
