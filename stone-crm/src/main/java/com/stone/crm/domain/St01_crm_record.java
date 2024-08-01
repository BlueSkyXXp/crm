package com.stone.crm.domain;

import com.stone.common.annotation.DictValue;
import com.stone.common.annotation.Excel;
import com.stone.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 跟进记录对象 st01_crm_record
 * 
 * @author stone
 * @date 2024-04-25
 */
@Data
public class St01_crm_record extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String recordId;

    /** 关联类型;1:商机2：合同3：回款计划4：汇款单 */
    @Excel(name = "关联类型;1:商机2：合同3：回款计划4：汇款单")
    private String types;

    /** 类型ID */
    @Excel(name = "类型ID")
    private String typesId;

    /** 跟进内容 */
    @Excel(name = "跟进内容")
    private String content;

    /** 跟进类型;1：外出拜访2：客户面谈3：客户来电4：客户来访5：在线交流6：发送资料7：售后回访8：方案整理9：其他方面 */
    @Excel(name = "跟进类型;1：外出拜访2：客户面谈3：客户来电4：客户来访5：在线交流6：发送资料7：售后回访8：方案整理9：其他方面")
    @DictValue(dict = "record_category" ,disp = "categoryDisp")
    private String category;
    private String categoryDisp;
    /** 商机ID */
    @Excel(name = "商机ID")
    private String businessIds;

    /** 联系人ID */
    @Excel(name = "联系人ID")
    private String contactsIds;

    /** 客户ID */
    @Excel(name = "客户ID")
    private String customerId;

    /** 下次联系时间 */
    @Excel(name = "下次联系时间")
    private String nextTime;

    /** 本次跟进时间 */
    @Excel(name = "本次跟进时间")
    private String recordTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @Excel(name = "更新时间")
    private String updatedTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    @Excel(name = "创建时间")
    private String createdTime;

}
