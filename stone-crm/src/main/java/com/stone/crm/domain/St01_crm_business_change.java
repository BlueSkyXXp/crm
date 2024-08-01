package com.stone.crm.domain;

import com.stone.common.annotation.Excel;
import com.stone.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 商机阶段变化对象 st01_crm_business_change
 * 
 * @author stone
 * @date 2024-04-25
 */
public class St01_crm_business_change extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 变化ID */
    private String changeId;

    /** 商机ID */
    @Excel(name = "商机ID")
    private String businessId;

    /** 阶段ID */
    @Excel(name = "阶段ID")
    private String statusId;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private String createdTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    public void setChangeId(String changeId) 
    {
        this.changeId = changeId;
    }

    public String getChangeId() 
    {
        return changeId;
    }
    public void setBusinessId(String businessId) 
    {
        this.businessId = businessId;
    }

    public String getBusinessId() 
    {
        return businessId;
    }
    public void setStatusId(String statusId) 
    {
        this.statusId = statusId;
    }

    public String getStatusId() 
    {
        return statusId;
    }
    public void setCreatedTime(String createdTime) 
    {
        this.createdTime = createdTime;
    }

    public String getCreatedTime() 
    {
        return createdTime;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("changeId", getChangeId())
            .append("businessId", getBusinessId())
            .append("statusId", getStatusId())
            .append("createdTime", getCreatedTime())
            .append("createdBy", getCreatedBy())
            .toString();
    }
}
