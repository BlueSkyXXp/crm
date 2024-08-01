package com.stone.crm.domain;

import java.math.BigDecimal;

import com.stone.common.annotation.DictValue;
import com.stone.common.annotation.Excel;
import com.stone.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 产品对象 st01_crm_product
 * 
 * @author stone
 * @date 2024-04-25
 */
@Data
public class St01_crm_product extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String productId;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String num;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal price;

    /** 状态;1 上架 0 下架 3 删除 */
    @Excel(name = "状态;1 上架 0 下架 3 删除")
    @DictValue(disp = "statusDisp" ,dict = "product_status")
    private String status;
    private String statusDisp;
    /** 产品分类ID */
    @Excel(name = "产品分类ID")
    private String categoryId;
    private String categoryName;
    /** 产品描述 */
    @Excel(name = "产品描述")
    private String description;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

}
