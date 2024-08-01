package com.stone.crm.domain;

import com.stone.common.annotation.Excel;
import com.stone.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 负责人变更记录对象 st01_crm_owner_record
 * 
 * @author stone
 * @date 2024-04-25
 */
public class St01_crm_owner_record extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String recordId;

    /** 对象id */
    @Excel(name = "对象id")
    private String typeId;

    /** 对象类型 */
    @Excel(name = "对象类型")
    private String type;

    /** 上一负责人 */
    @Excel(name = "上一负责人")
    private String preOwnerUserId;

    /** 接手负责人 */
    @Excel(name = "接手负责人")
    private String postOwnerUserId;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private String createdTime;

    public void setRecordId(String recordId) 
    {
        this.recordId = recordId;
    }

    public String getRecordId() 
    {
        return recordId;
    }
    public void setTypeId(String typeId) 
    {
        this.typeId = typeId;
    }

    public String getTypeId() 
    {
        return typeId;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setPreOwnerUserId(String preOwnerUserId) 
    {
        this.preOwnerUserId = preOwnerUserId;
    }

    public String getPreOwnerUserId() 
    {
        return preOwnerUserId;
    }
    public void setPostOwnerUserId(String postOwnerUserId) 
    {
        this.postOwnerUserId = postOwnerUserId;
    }

    public String getPostOwnerUserId() 
    {
        return postOwnerUserId;
    }
    public void setCreatedTime(String createdTime) 
    {
        this.createdTime = createdTime;
    }

    public String getCreatedTime() 
    {
        return createdTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recordId", getRecordId())
            .append("typeId", getTypeId())
            .append("type", getType())
            .append("preOwnerUserId", getPreOwnerUserId())
            .append("postOwnerUserId", getPostOwnerUserId())
            .append("createdTime", getCreatedTime())
            .toString();
    }
}
