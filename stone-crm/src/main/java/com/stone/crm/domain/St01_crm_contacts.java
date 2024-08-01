package com.stone.crm.domain;

import com.stone.common.annotation.Excel;
import com.stone.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 联系人对象 st01_crm_contacts
 * 
 * @author stone
 * @date 2024-04-25
 */
public class St01_crm_contacts extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 联系人编号 */
    private String contactsId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String contactsName;

    /** 手机 */
    @Excel(name = "手机")
    private String mobile;

    /** 电话 */
    @Excel(name = "电话")
    private String telephone;

    /** 部门 */
    @Excel(name = "部门")
    private String department;

    /** 职务 */
    @Excel(name = "职务")
    private String duties;

    /** 性别;0 未知 1 男 2 女 */
    @Excel(name = "性别;0 未知 1 男 2 女")
    private String sex;

    /** 生日 */
    @Excel(name = "生日")
    private String birthday;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String mailbox;

    /** 微信号 */
    @Excel(name = "微信号")
    private String weChat;

    /** QQ号 */
    @Excel(name = "QQ号")
    private String qq;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

    /** 亲密度 */
    @Excel(name = "亲密度")
    private String intimacy;

    /** 重要程度 */
    @Excel(name = "重要程度")
    private String important;

    /** 下次联系时间 */
    @Excel(name = "下次联系时间")
    private String nextContactTime;

    /** 是否为默认联系人;0是1否 */
    @Excel(name = "是否为默认联系人;0是1否")
    private String dc;

    /** 是否为关键决策人;0是1否 */
    @Excel(name = "是否为关键决策人;0是1否")
    private String dm;

    /** 客户ID */
    @Excel(name = "客户ID")
    private String customerId;

    /** 删除标记;0未删除1已删除 */
    @Excel(name = "删除标记;0未删除1已删除")
    private String deltag;

    /** 负责人 */
    @Excel(name = "负责人")
    private String ownerUserId;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private String createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @Excel(name = "更新时间")
    private String updatedTime;

    public void setContactsId(String contactsId) 
    {
        this.contactsId = contactsId;
    }

    public String getContactsId() 
    {
        return contactsId;
    }
    public void setContactsName(String contactsName) 
    {
        this.contactsName = contactsName;
    }

    public String getContactsName() 
    {
        return contactsName;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setTelephone(String telephone) 
    {
        this.telephone = telephone;
    }

    public String getTelephone() 
    {
        return telephone;
    }
    public void setDepartment(String department) 
    {
        this.department = department;
    }

    public String getDepartment() 
    {
        return department;
    }
    public void setDuties(String duties) 
    {
        this.duties = duties;
    }

    public String getDuties() 
    {
        return duties;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setBirthday(String birthday) 
    {
        this.birthday = birthday;
    }

    public String getBirthday() 
    {
        return birthday;
    }
    public void setMailbox(String mailbox) 
    {
        this.mailbox = mailbox;
    }

    public String getMailbox() 
    {
        return mailbox;
    }
    public void setWeChat(String weChat) 
    {
        this.weChat = weChat;
    }

    public String getWeChat() 
    {
        return weChat;
    }
    public void setQq(String qq) 
    {
        this.qq = qq;
    }

    public String getQq() 
    {
        return qq;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setIntimacy(String intimacy) 
    {
        this.intimacy = intimacy;
    }

    public String getIntimacy() 
    {
        return intimacy;
    }
    public void setImportant(String important) 
    {
        this.important = important;
    }

    public String getImportant() 
    {
        return important;
    }
    public void setNextContactTime(String nextContactTime) 
    {
        this.nextContactTime = nextContactTime;
    }

    public String getNextContactTime() 
    {
        return nextContactTime;
    }
    public void setDc(String dc) 
    {
        this.dc = dc;
    }

    public String getDc() 
    {
        return dc;
    }
    public void setDm(String dm) 
    {
        this.dm = dm;
    }

    public String getDm() 
    {
        return dm;
    }
    public void setCustomerId(String customerId) 
    {
        this.customerId = customerId;
    }

    public String getCustomerId() 
    {
        return customerId;
    }
    public void setDeltag(String deltag) 
    {
        this.deltag = deltag;
    }

    public String getDeltag() 
    {
        return deltag;
    }
    public void setOwnerUserId(String ownerUserId) 
    {
        this.ownerUserId = ownerUserId;
    }

    public String getOwnerUserId() 
    {
        return ownerUserId;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
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
            .append("contactsId", getContactsId())
            .append("contactsName", getContactsName())
            .append("mobile", getMobile())
            .append("telephone", getTelephone())
            .append("department", getDepartment())
            .append("duties", getDuties())
            .append("sex", getSex())
            .append("birthday", getBirthday())
            .append("mailbox", getMailbox())
            .append("weChat", getWeChat())
            .append("qq", getQq())
            .append("address", getAddress())
            .append("intimacy", getIntimacy())
            .append("important", getImportant())
            .append("nextContactTime", getNextContactTime())
            .append("dc", getDc())
            .append("dm", getDm())
            .append("customerId", getCustomerId())
            .append("deltag", getDeltag())
            .append("remark", getRemark())
            .append("ownerUserId", getOwnerUserId())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("updatedBy", getUpdatedBy())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
