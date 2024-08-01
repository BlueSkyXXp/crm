package com.stone.crm.domain;

import com.stone.common.annotation.Excel;
import com.stone.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 线索对象 st01_crm_leads
 * 
 * @author stone
 * @date 2024-04-25
 */
public class St01_crm_leads extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String leadsId;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String customer;

    /** 客户性质;1：企业客户2：个人客户3：政府单位4：事业单位 */
    @Excel(name = "客户性质;1：企业客户2：个人客户3：政府单位4：事业单位")
    private String nature;

    /** 客户来源;1：电话咨询
2：自主开拓
3：公司资源
4：网络推广
5：渠道代理
6：其他方式 */
    @Excel(name = "客户来源;1：电话咨询 2：自主开拓 3：公司资源 4：网络推广 5：渠道代理 6：其他方式")
    private String source;

    /** 客户行业;1：金融
2：电信
3：教育
4：高科技
5：政府
6：制造业
7：服务业
8：能源
9：零售
10：媒体
11：娱乐
12：咨询
13：非盈利事业
14：公用事业
15：其他 */
    @Excel(name = "客户行业;1：金融 2：电信 3：教育 4：高科技 5：政府 6：制造业 7：服务业 8：能源 9：零售 10：媒体 11：娱乐 12：咨询 13：非盈利事业 14：公用事业 15：其他")
    private String industry;

    /** 客户分级;1：大型
2：中型
3：小型
4：微型 */
    @Excel(name = "客户分级;1：大型 2：中型 3：小型 4：微型")
    private String grade;

    /** 客户官网 */
    private String website;

    /** 客户简介 */
    private String introduction;

    /** 省 */
    private String provinceId;

    /** 市 */
    private String cityId;

    /** 区 */
    private String areaId;

    /** 联系人 */
    private String contacts;

    /** 性别;0 未知 1 男 2 女 */
    private String sex;

    /** 邮箱 */
    private String mailbox;

    /** 是否转为客户;1已转化 0 未转化 */
    private String isTransform;

    /** 跟进状态;0未跟进1已跟进 */
    private String followup;

    /** 下次联系时间 */
    @Excel(name = "下次联系时间")
    private String nextTime;

    /** 电话 */
    private String telephone;

    /** 地址 */
    private String address;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 负责人 */
    @Excel(name = "负责人")
    private String ownerUserId;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private String createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    private String updatedTime;

    public void setLeadsId(String leadsId) 
    {
        this.leadsId = leadsId;
    }

    public String getLeadsId() 
    {
        return leadsId;
    }
    public void setCustomer(String customer) 
    {
        this.customer = customer;
    }

    public String getCustomer() 
    {
        return customer;
    }
    public void setNature(String nature) 
    {
        this.nature = nature;
    }

    public String getNature() 
    {
        return nature;
    }
    public void setSource(String source) 
    {
        this.source = source;
    }

    public String getSource() 
    {
        return source;
    }
    public void setIndustry(String industry) 
    {
        this.industry = industry;
    }

    public String getIndustry() 
    {
        return industry;
    }
    public void setGrade(String grade) 
    {
        this.grade = grade;
    }

    public String getGrade() 
    {
        return grade;
    }
    public void setWebsite(String website) 
    {
        this.website = website;
    }

    public String getWebsite() 
    {
        return website;
    }
    public void setIntroduction(String introduction) 
    {
        this.introduction = introduction;
    }

    public String getIntroduction() 
    {
        return introduction;
    }
    public void setProvinceId(String provinceId) 
    {
        this.provinceId = provinceId;
    }

    public String getProvinceId() 
    {
        return provinceId;
    }
    public void setCityId(String cityId) 
    {
        this.cityId = cityId;
    }

    public String getCityId() 
    {
        return cityId;
    }
    public void setAreaId(String areaId) 
    {
        this.areaId = areaId;
    }

    public String getAreaId() 
    {
        return areaId;
    }
    public void setContacts(String contacts) 
    {
        this.contacts = contacts;
    }

    public String getContacts() 
    {
        return contacts;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setMailbox(String mailbox) 
    {
        this.mailbox = mailbox;
    }

    public String getMailbox() 
    {
        return mailbox;
    }
    public void setIsTransform(String isTransform) 
    {
        this.isTransform = isTransform;
    }

    public String getIsTransform() 
    {
        return isTransform;
    }
    public void setFollowup(String followup) 
    {
        this.followup = followup;
    }

    public String getFollowup() 
    {
        return followup;
    }
    public void setNextTime(String nextTime) 
    {
        this.nextTime = nextTime;
    }

    public String getNextTime() 
    {
        return nextTime;
    }
    public void setTelephone(String telephone) 
    {
        this.telephone = telephone;
    }

    public String getTelephone() 
    {
        return telephone;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }
    public void setOwnerUserId(String ownerUserId) 
    {
        this.ownerUserId = ownerUserId;
    }

    public String getOwnerUserId() 
    {
        return ownerUserId;
    }
    public void setCreatedTime(String createdTime) 
    {
        this.createdTime = createdTime;
    }

    public String getCreatedTime() 
    {
        return createdTime;
    }
    public void setUpdatedBy(String updatedBy) 
    {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy() 
    {
        return updatedBy;
    }
    public void setUpdatedTime(String updatedTime) 
    {
        this.updatedTime = updatedTime;
    }

    public String getUpdatedTime() 
    {
        return updatedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("leadsId", getLeadsId())
            .append("customer", getCustomer())
            .append("nature", getNature())
            .append("source", getSource())
            .append("industry", getIndustry())
            .append("grade", getGrade())
            .append("website", getWebsite())
            .append("introduction", getIntroduction())
            .append("provinceId", getProvinceId())
            .append("cityId", getCityId())
            .append("areaId", getAreaId())
            .append("contacts", getContacts())
            .append("sex", getSex())
            .append("mailbox", getMailbox())
            .append("isTransform", getIsTransform())
            .append("followup", getFollowup())
            .append("nextTime", getNextTime())
            .append("telephone", getTelephone())
            .append("address", getAddress())
            .append("remark", getRemark())
            .append("createdBy", getCreatedBy())
            .append("ownerUserId", getOwnerUserId())
            .append("createdTime", getCreatedTime())
            .append("updatedBy", getUpdatedBy())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
