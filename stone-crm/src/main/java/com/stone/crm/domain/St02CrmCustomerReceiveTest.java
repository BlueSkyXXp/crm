package com.stone.crm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.stone.common.annotation.Excel;
import com.stone.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 待分配客户测试对象 st02_crm_customer_receive_test
 * 
 * @author xiongxiaopeng
 * @date 2024-07-10
 */
@ApiModel(value = "待分配客户测试对象")
public class St02CrmCustomerReceiveTest extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户ID */
    private Long id;

    /** 客户来源 */
    @ApiModelProperty(value = "客户来源")
    @Excel(name = "客户来源")
    private String source;

    /** 客户姓名 */
    @ApiModelProperty(value = "客户姓名")
    @Excel(name = "客户姓名")
    private String name;

    /** 电话 */
    @ApiModelProperty(value = "电话")
    @Excel(name = "电话")
    private String phone;

    /** 客户类型 */
    @ApiModelProperty(value = "客户类型")
    @Excel(name = "客户类型")
    private Long type;

    /** 年龄 */
    @ApiModelProperty(value = "年龄")
    @Excel(name = "年龄")
    private String age;

    /** 性别 */
    @ApiModelProperty(value = "性别")
    @Excel(name = "性别")
    private Long sex;

    /** 户籍 */
    @ApiModelProperty(value = "户籍")
    @Excel(name = "户籍")
    private String region;

    /** 城市 */
    @ApiModelProperty(value = "城市")
    @Excel(name = "城市")
    private String city;

    /** 婚姻状态 */
    @ApiModelProperty(value = "婚姻状态")
    @Excel(name = "婚姻状态")
    private Long maritalStatus;

    /** 爱人是否知晓 */
    @ApiModelProperty(value = "爱人是否知晓")
    @Excel(name = "爱人是否知晓")
    private Long otherKnow;

    /** 芝麻信用分 */
    @ApiModelProperty(value = "芝麻信用分")
    @Excel(name = "芝麻信用分")
    private Long sesameCredit;

    /** 微粒贷 */
    @ApiModelProperty(value = "微粒贷")
    @Excel(name = "微粒贷")
    private String microLoans;

    /** 客户状态 */
    @ApiModelProperty(value = "客户状态")
    @Excel(name = "客户状态")
    private Long status;

    /** 客户星级 */
    @ApiModelProperty(value = "客户星级")
    @Excel(name = "客户星级")
    private Long level;

    /** 是否法人 */
    @ApiModelProperty(value = "是否法人")
    @Excel(name = "是否法人")
    private Long legalPerson;

    /** 年份 */
    @ApiModelProperty(value = "年份")
    @Excel(name = "年份")
    private Long appliYear;

    /** 是否有社保 */
    @ApiModelProperty(value = "是否有社保")
    @Excel(name = "是否有社保")
    private Long socialSecurity;

    /** 社保年份 */
    @ApiModelProperty(value = "社保年份")
    @Excel(name = "社保年份")
    private Long socialSecurityYear;

    /** 社保金额 */
    @ApiModelProperty(value = "社保金额")
    @Excel(name = "社保金额")
    private Long socialSecurityMoney;

    /** 是否有公积金 */
    @ApiModelProperty(value = "是否有公积金")
    @Excel(name = "是否有公积金")
    private Long accFund;

    /** 公积金年份 */
    @ApiModelProperty(value = "公积金年份")
    @Excel(name = "公积金年份")
    private Long accFundYear;

    /** 公积金金额 */
    @ApiModelProperty(value = "公积金金额")
    @Excel(name = "公积金金额")
    private Long accFundMoney;

    /** 是否有职业身份 */
    @ApiModelProperty(value = "是否有职业身份")
    @Excel(name = "是否有职业身份")
    private Long professional;

    /** 单位性质: 1:国企,2:公务员,3:上市公司,4:私营企业 */
    @ApiModelProperty(value = "单位性质: 1:国企,2:公务员,3:上市公司,4:私营企业")
    @Excel(name = "单位性质: 1:国企,2:公务员,3:上市公司,4:私营企业")
    private Long professionalType;

    /** 工资 */
    @ApiModelProperty(value = "工资")
    @Excel(name = "工资")
    private Long payMoney;

    /** 发薪方式:1:代发,2:转账,3:现金 */
    @ApiModelProperty(value = "发薪方式:1:代发,2:转账,3:现金")
    @Excel(name = "发薪方式:1:代发,2:转账,3:现金")
    private Long payType;

    /** 房产信息:0:无, 1: 有 */
    @ApiModelProperty(value = "房产信息:0:无, 1: 有")
    @Excel(name = "房产信息:0:无, 1: 有")
    private Long propertyInfo;

    /** 房产状态: 1:全款, 2:按揭, 3:抵押, 4:查封 */
    @ApiModelProperty(value = "房产状态: 1:全款, 2:按揭, 3:抵押, 4:查封")
    @Excel(name = "房产状态: 1:全款, 2:按揭, 3:抵押, 4:查封")
    private Long propertyStatus;

    /** 面积 */
    @ApiModelProperty(value = "面积")
    @Excel(name = "面积")
    private Long area;

    /** 价值 */
    @ApiModelProperty(value = "价值")
    @Excel(name = "价值")
    private Long worth;

    /** 房屋拥有: 1: 共有 2:单独 */
    @ApiModelProperty(value = "房屋拥有: 1: 共有 2:单独")
    @Excel(name = "房屋拥有: 1: 共有 2:单独")
    private Long ownerType;

    /** 城市 */
    @ApiModelProperty(value = "城市")
    @Excel(name = "城市")
    private String houseCity;

    /** 按揭多久(多少个月) */
    @ApiModelProperty(value = "按揭多久(多少个月)")
    @Excel(name = "按揭多久(多少个月)")
    private Long mortgageTime;

    /** 月供 */
    @ApiModelProperty(value = "月供")
    @Excel(name = "月供")
    private Long mortgageMoney;

    /** 按揭银行 */
    @ApiModelProperty(value = "按揭银行")
    @Excel(name = "按揭银行")
    private String mortgageBank;

    /** 车产信息: 0: 无, 1: 有 */
    @ApiModelProperty(value = "车产信息: 0: 无, 1: 有")
    @Excel(name = "车产信息: 0: 无, 1: 有")
    private Long carInfo;

    /** 车辆状态: 1:全款, 2:按揭, 3:抵押 */
    @ApiModelProperty(value = "车辆状态: 1:全款, 2:按揭, 3:抵押")
    @Excel(name = "车辆状态: 1:全款, 2:按揭, 3:抵押")
    private Long carStatus;

    /** 年限 */
    @ApiModelProperty(value = "年限")
    @Excel(name = "年限")
    private Long carTermYear;

    /** 裸车价 */
    @ApiModelProperty(value = "裸车价")
    @Excel(name = "裸车价")
    private String carPrice;

    /** 汽车城市 */
    @ApiModelProperty(value = "汽车城市")
    @Excel(name = "汽车城市")
    private String carCity;

    /** 汽车品牌 */
    @ApiModelProperty(value = "汽车品牌")
    @Excel(name = "汽车品牌")
    private String carBrand;

    /** 按揭多久(多少个月) */
    @ApiModelProperty(value = "按揭多久(多少个月)")
    @Excel(name = "按揭多久(多少个月)")
    private Long carMortgageTime;

    /** 月供 */
    @ApiModelProperty(value = "月供")
    @Excel(name = "月供")
    private Long carMortgageMoney;

    /** 按揭银行 */
    @ApiModelProperty(value = "按揭银行")
    @Excel(name = "按揭银行")
    private String carMortgageBank;

    /** 保单信息: 0: 无, 1:有 */
    @ApiModelProperty(value = "保单信息: 0: 无, 1:有")
    @Excel(name = "保单信息: 0: 无, 1:有")
    private Long policyInfo;

    /** 投保公司 */
    @ApiModelProperty(value = "投保公司")
    @Excel(name = "投保公司")
    private String insuranceCompany;

    /** 支付方式: 1: 年缴, 2:月缴 */
    @ApiModelProperty(value = "支付方式: 1: 年缴, 2:月缴")
    @Excel(name = "支付方式: 1: 年缴, 2:月缴")
    private Long insurancePayType;

    /** 缴了几年几次 */
    @ApiModelProperty(value = "缴了几年几次")
    @Excel(name = "缴了几年几次")
    private String insurancePayTimes;

    /** 是否有信用卡 */
    @ApiModelProperty(value = "是否有信用卡")
    @Excel(name = "是否有信用卡")
    private Long creditCardInfo;

    /** 信用卡金额 */
    @ApiModelProperty(value = "信用卡金额")
    @Excel(name = "信用卡金额")
    private Long creditCardMoney;

    /** 信用卡有无逾期 */
    @ApiModelProperty(value = "信用卡有无逾期")
    @Excel(name = "信用卡有无逾期")
    private Long creditCardLateStatus;

    /** 贷款总额 */
    @ApiModelProperty(value = "贷款总额")
    @Excel(name = "贷款总额")
    private Long loanMoney;

    /** 有无逾期 */
    @ApiModelProperty(value = "有无逾期")
    @Excel(name = "有无逾期")
    private Long lateStatus;

    /** 最小资金需求 */
    @ApiModelProperty(value = "最小资金需求")
    @Excel(name = "最小资金需求")
    private Long minDemandMoney;

    /** 最大资金需求 */
    @ApiModelProperty(value = "最大资金需求")
    @Excel(name = "最大资金需求")
    private Long maxDemandMoney;

    /** 贷款类型 */
    @ApiModelProperty(value = "贷款类型")
    @Excel(name = "贷款类型")
    private Long loanType;

    /** 跟进人(用户ID) */
    @ApiModelProperty(value = "跟进人(用户ID)")
    @Excel(name = "跟进人(用户ID)")
    private Long userId;

    /** 跟进次数 */
    @ApiModelProperty(value = "跟进次数")
    @Excel(name = "跟进次数")
    private Long followTimes;

    /** 原始分配时间 */
    @ApiModelProperty(value = "原始分配时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "原始分配时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date firstAssignTime;

    /** 最近分配时间 */
    @ApiModelProperty(value = "最近分配时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最近分配时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastAssignTime;

    /** 最近备注时间 */
    @ApiModelProperty(value = "最近备注时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最近备注时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastRemarkTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSource(String source) 
    {
        this.source = source;
    }

    public String getSource() 
    {
        return source;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setAge(String age) 
    {
        this.age = age;
    }

    public String getAge() 
    {
        return age;
    }
    public void setSex(Long sex) 
    {
        this.sex = sex;
    }

    public Long getSex() 
    {
        return sex;
    }
    public void setRegion(String region) 
    {
        this.region = region;
    }

    public String getRegion() 
    {
        return region;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setMaritalStatus(Long maritalStatus) 
    {
        this.maritalStatus = maritalStatus;
    }

    public Long getMaritalStatus() 
    {
        return maritalStatus;
    }
    public void setOtherKnow(Long otherKnow) 
    {
        this.otherKnow = otherKnow;
    }

    public Long getOtherKnow() 
    {
        return otherKnow;
    }
    public void setSesameCredit(Long sesameCredit) 
    {
        this.sesameCredit = sesameCredit;
    }

    public Long getSesameCredit() 
    {
        return sesameCredit;
    }
    public void setMicroLoans(String microLoans) 
    {
        this.microLoans = microLoans;
    }

    public String getMicroLoans() 
    {
        return microLoans;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setLevel(Long level) 
    {
        this.level = level;
    }

    public Long getLevel() 
    {
        return level;
    }
    public void setLegalPerson(Long legalPerson) 
    {
        this.legalPerson = legalPerson;
    }

    public Long getLegalPerson() 
    {
        return legalPerson;
    }
    public void setAppliYear(Long appliYear) 
    {
        this.appliYear = appliYear;
    }

    public Long getAppliYear() 
    {
        return appliYear;
    }
    public void setSocialSecurity(Long socialSecurity) 
    {
        this.socialSecurity = socialSecurity;
    }

    public Long getSocialSecurity() 
    {
        return socialSecurity;
    }
    public void setSocialSecurityYear(Long socialSecurityYear) 
    {
        this.socialSecurityYear = socialSecurityYear;
    }

    public Long getSocialSecurityYear() 
    {
        return socialSecurityYear;
    }
    public void setSocialSecurityMoney(Long socialSecurityMoney) 
    {
        this.socialSecurityMoney = socialSecurityMoney;
    }

    public Long getSocialSecurityMoney() 
    {
        return socialSecurityMoney;
    }
    public void setAccFund(Long accFund) 
    {
        this.accFund = accFund;
    }

    public Long getAccFund() 
    {
        return accFund;
    }
    public void setAccFundYear(Long accFundYear) 
    {
        this.accFundYear = accFundYear;
    }

    public Long getAccFundYear() 
    {
        return accFundYear;
    }
    public void setAccFundMoney(Long accFundMoney) 
    {
        this.accFundMoney = accFundMoney;
    }

    public Long getAccFundMoney() 
    {
        return accFundMoney;
    }
    public void setProfessional(Long professional) 
    {
        this.professional = professional;
    }

    public Long getProfessional() 
    {
        return professional;
    }
    public void setProfessionalType(Long professionalType) 
    {
        this.professionalType = professionalType;
    }

    public Long getProfessionalType() 
    {
        return professionalType;
    }
    public void setPayMoney(Long payMoney) 
    {
        this.payMoney = payMoney;
    }

    public Long getPayMoney() 
    {
        return payMoney;
    }
    public void setPayType(Long payType) 
    {
        this.payType = payType;
    }

    public Long getPayType() 
    {
        return payType;
    }
    public void setPropertyInfo(Long propertyInfo) 
    {
        this.propertyInfo = propertyInfo;
    }

    public Long getPropertyInfo() 
    {
        return propertyInfo;
    }
    public void setPropertyStatus(Long propertyStatus) 
    {
        this.propertyStatus = propertyStatus;
    }

    public Long getPropertyStatus() 
    {
        return propertyStatus;
    }
    public void setArea(Long area) 
    {
        this.area = area;
    }

    public Long getArea() 
    {
        return area;
    }
    public void setWorth(Long worth) 
    {
        this.worth = worth;
    }

    public Long getWorth() 
    {
        return worth;
    }
    public void setOwnerType(Long ownerType) 
    {
        this.ownerType = ownerType;
    }

    public Long getOwnerType() 
    {
        return ownerType;
    }
    public void setHouseCity(String houseCity) 
    {
        this.houseCity = houseCity;
    }

    public String getHouseCity() 
    {
        return houseCity;
    }
    public void setMortgageTime(Long mortgageTime) 
    {
        this.mortgageTime = mortgageTime;
    }

    public Long getMortgageTime() 
    {
        return mortgageTime;
    }
    public void setMortgageMoney(Long mortgageMoney) 
    {
        this.mortgageMoney = mortgageMoney;
    }

    public Long getMortgageMoney() 
    {
        return mortgageMoney;
    }
    public void setMortgageBank(String mortgageBank) 
    {
        this.mortgageBank = mortgageBank;
    }

    public String getMortgageBank() 
    {
        return mortgageBank;
    }
    public void setCarInfo(Long carInfo) 
    {
        this.carInfo = carInfo;
    }

    public Long getCarInfo() 
    {
        return carInfo;
    }
    public void setCarStatus(Long carStatus) 
    {
        this.carStatus = carStatus;
    }

    public Long getCarStatus() 
    {
        return carStatus;
    }
    public void setCarTermYear(Long carTermYear) 
    {
        this.carTermYear = carTermYear;
    }

    public Long getCarTermYear() 
    {
        return carTermYear;
    }
    public void setCarPrice(String carPrice) 
    {
        this.carPrice = carPrice;
    }

    public String getCarPrice() 
    {
        return carPrice;
    }
    public void setCarCity(String carCity) 
    {
        this.carCity = carCity;
    }

    public String getCarCity() 
    {
        return carCity;
    }
    public void setCarBrand(String carBrand) 
    {
        this.carBrand = carBrand;
    }

    public String getCarBrand() 
    {
        return carBrand;
    }
    public void setCarMortgageTime(Long carMortgageTime) 
    {
        this.carMortgageTime = carMortgageTime;
    }

    public Long getCarMortgageTime() 
    {
        return carMortgageTime;
    }
    public void setCarMortgageMoney(Long carMortgageMoney) 
    {
        this.carMortgageMoney = carMortgageMoney;
    }

    public Long getCarMortgageMoney() 
    {
        return carMortgageMoney;
    }
    public void setCarMortgageBank(String carMortgageBank) 
    {
        this.carMortgageBank = carMortgageBank;
    }

    public String getCarMortgageBank() 
    {
        return carMortgageBank;
    }
    public void setPolicyInfo(Long policyInfo) 
    {
        this.policyInfo = policyInfo;
    }

    public Long getPolicyInfo() 
    {
        return policyInfo;
    }
    public void setInsuranceCompany(String insuranceCompany) 
    {
        this.insuranceCompany = insuranceCompany;
    }

    public String getInsuranceCompany() 
    {
        return insuranceCompany;
    }
    public void setInsurancePayType(Long insurancePayType) 
    {
        this.insurancePayType = insurancePayType;
    }

    public Long getInsurancePayType() 
    {
        return insurancePayType;
    }
    public void setInsurancePayTimes(String insurancePayTimes) 
    {
        this.insurancePayTimes = insurancePayTimes;
    }

    public String getInsurancePayTimes() 
    {
        return insurancePayTimes;
    }
    public void setCreditCardInfo(Long creditCardInfo) 
    {
        this.creditCardInfo = creditCardInfo;
    }

    public Long getCreditCardInfo() 
    {
        return creditCardInfo;
    }
    public void setCreditCardMoney(Long creditCardMoney) 
    {
        this.creditCardMoney = creditCardMoney;
    }

    public Long getCreditCardMoney() 
    {
        return creditCardMoney;
    }
    public void setCreditCardLateStatus(Long creditCardLateStatus) 
    {
        this.creditCardLateStatus = creditCardLateStatus;
    }

    public Long getCreditCardLateStatus() 
    {
        return creditCardLateStatus;
    }
    public void setLoanMoney(Long loanMoney) 
    {
        this.loanMoney = loanMoney;
    }

    public Long getLoanMoney() 
    {
        return loanMoney;
    }
    public void setLateStatus(Long lateStatus) 
    {
        this.lateStatus = lateStatus;
    }

    public Long getLateStatus() 
    {
        return lateStatus;
    }
    public void setMinDemandMoney(Long minDemandMoney) 
    {
        this.minDemandMoney = minDemandMoney;
    }

    public Long getMinDemandMoney() 
    {
        return minDemandMoney;
    }
    public void setMaxDemandMoney(Long maxDemandMoney) 
    {
        this.maxDemandMoney = maxDemandMoney;
    }

    public Long getMaxDemandMoney() 
    {
        return maxDemandMoney;
    }
    public void setLoanType(Long loanType) 
    {
        this.loanType = loanType;
    }

    public Long getLoanType() 
    {
        return loanType;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setFollowTimes(Long followTimes) 
    {
        this.followTimes = followTimes;
    }

    public Long getFollowTimes() 
    {
        return followTimes;
    }
    public void setFirstAssignTime(Date firstAssignTime) 
    {
        this.firstAssignTime = firstAssignTime;
    }

    public Date getFirstAssignTime() 
    {
        return firstAssignTime;
    }
    public void setLastAssignTime(Date lastAssignTime) 
    {
        this.lastAssignTime = lastAssignTime;
    }

    public Date getLastAssignTime() 
    {
        return lastAssignTime;
    }
    public void setLastRemarkTime(Date lastRemarkTime) 
    {
        this.lastRemarkTime = lastRemarkTime;
    }

    public Date getLastRemarkTime() 
    {
        return lastRemarkTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("source", getSource())
            .append("name", getName())
            .append("phone", getPhone())
            .append("type", getType())
            .append("age", getAge())
            .append("sex", getSex())
            .append("region", getRegion())
            .append("city", getCity())
            .append("maritalStatus", getMaritalStatus())
            .append("otherKnow", getOtherKnow())
            .append("sesameCredit", getSesameCredit())
            .append("microLoans", getMicroLoans())
            .append("status", getStatus())
            .append("level", getLevel())
            .append("legalPerson", getLegalPerson())
            .append("appliYear", getAppliYear())
            .append("socialSecurity", getSocialSecurity())
            .append("socialSecurityYear", getSocialSecurityYear())
            .append("socialSecurityMoney", getSocialSecurityMoney())
            .append("accFund", getAccFund())
            .append("accFundYear", getAccFundYear())
            .append("accFundMoney", getAccFundMoney())
            .append("professional", getProfessional())
            .append("professionalType", getProfessionalType())
            .append("payMoney", getPayMoney())
            .append("payType", getPayType())
            .append("propertyInfo", getPropertyInfo())
            .append("propertyStatus", getPropertyStatus())
            .append("area", getArea())
            .append("worth", getWorth())
            .append("ownerType", getOwnerType())
            .append("houseCity", getHouseCity())
            .append("mortgageTime", getMortgageTime())
            .append("mortgageMoney", getMortgageMoney())
            .append("mortgageBank", getMortgageBank())
            .append("carInfo", getCarInfo())
            .append("carStatus", getCarStatus())
            .append("carTermYear", getCarTermYear())
            .append("carPrice", getCarPrice())
            .append("carCity", getCarCity())
            .append("carBrand", getCarBrand())
            .append("carMortgageTime", getCarMortgageTime())
            .append("carMortgageMoney", getCarMortgageMoney())
            .append("carMortgageBank", getCarMortgageBank())
            .append("policyInfo", getPolicyInfo())
            .append("insuranceCompany", getInsuranceCompany())
            .append("insurancePayType", getInsurancePayType())
            .append("insurancePayTimes", getInsurancePayTimes())
            .append("creditCardInfo", getCreditCardInfo())
            .append("creditCardMoney", getCreditCardMoney())
            .append("creditCardLateStatus", getCreditCardLateStatus())
            .append("loanMoney", getLoanMoney())
            .append("lateStatus", getLateStatus())
            .append("minDemandMoney", getMinDemandMoney())
            .append("maxDemandMoney", getMaxDemandMoney())
            .append("loanType", getLoanType())
            .append("userId", getUserId())
            .append("followTimes", getFollowTimes())
            .append("firstAssignTime", getFirstAssignTime())
            .append("lastAssignTime", getLastAssignTime())
            .append("lastRemarkTime", getLastRemarkTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
