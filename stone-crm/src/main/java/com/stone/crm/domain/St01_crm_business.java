package com.stone.crm.domain;

import java.math.BigDecimal;
import java.util.List;

import com.stone.common.annotation.DictValue;
import com.stone.common.annotation.Excel;
import com.stone.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商机对象 st01_crm_business
 * 
 * @author stone
 * @date 2024-04-25
 */
@Data
public class St01_crm_business extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商机编号 */
    private String businessId;

    /** 商机名称 */
    @Excel(name = "商机名称")
    private String businessName;

    /** 商机金额 */
    @Excel(name = "商机金额")
    private BigDecimal money;

    /** 商机阶段;1：初步沟通2：需求确定3：方案报价4：谈判协商5： */
    @Excel(name = "商机阶段;1：初步沟通2：需求确定3：方案报价4：谈判协商5：")
    @DictValue(dict = "business_status_id", disp = "statusIdDisp")
    private String statusId;
    private String statusIdDisp;

    /** 下次联系时间 */
    @Excel(name = "下次联系时间")
    private String nextTime;

    /** 客户ID */
    @Excel(name = "客户ID")
    private String customerId;

    /** 预计成交日期 */
    @Excel(name = "预计成交日期")
    private String dealDate;

    /** 整单折扣 */
    @Excel(name = "整单折扣")
    private BigDecimal discountRate;

    /** 产品总金额 */
    @Excel(name = "产品总金额")
    private BigDecimal totalPrice;

    /** 商机来源;1：独立开发2：来电咨询3：广告宣传4：搜索引擎5：客户介绍6：展会7：招标公告8：其他 */
    @Excel(name = "商机来源;1：独立开发2：来电咨询3：广告宣传4：搜索引擎5：客户介绍6：展会7：招标公告8：其他")
    @DictValue(dict = "bussiness_source" ,disp = "sourceDisp")
    private String source;
    private String sourceDisp;
    /** 只读权限 */
    @Excel(name = "只读权限")
    private String roUserId;

    /** 读写权限 */
    @Excel(name = "读写权限")
    private String rwUserId;

    /** 1赢单2输单3无效 */
    @Excel(name = "1赢单2输单3无效")
    private String isEnd;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 负责人ID */
    @Excel(name = "负责人ID")
    private String ownerUserId;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private String createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @Excel(name = "更新时间")
    private String updatedTime;

    private List<St01_crm_business_product> productList;
}
