package com.stone.crm.domain;

import java.math.BigDecimal;

import com.stone.common.annotation.DictValue;
import com.stone.common.annotation.Excel;
import com.stone.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 合同产品关系对象 st01_crm_contract_product
 * 
 * @author stone
 * @date 2024-04-25
 */
@Data
public class St01_crm_contract_product extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String rId;

    /** 合同ID */
    @Excel(name = "合同ID")
    private String contractId;
    private String contractName;

    /** 产品ID */
    @Excel(name = "产品ID")
    @DictValue(dict="product_list",disp="productName")
    private String productId;
    private String productName;

    /** 产品单价 */
    @Excel(name = "产品单价")
    private BigDecimal price;

    /** 销售价格 */
    @Excel(name = "销售价格")
    private BigDecimal salesPrice;

    /** 数量 */
    @Excel(name = "数量")
    private String num;

    /** 折扣 */
    @Excel(name = "折扣")
    private BigDecimal discount;

    /** 小计（折扣后价格） */
    @Excel(name = "小计", readConverterExp = "折=扣后价格")
    private BigDecimal subtotal;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("rId", getRId())
            .append("contractId", getContractId())
            .append("productId", getProductId())
            .append("price", getPrice())
            .append("salesPrice", getSalesPrice())
            .append("num", getNum())
            .append("discount", getDiscount())
            .append("subtotal", getSubtotal())
            .append("unit", getUnit())
            .toString();
    }
}
