package com.stone.crm.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "进线接口数据")
public class St02CrmReceiveData {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "进线分配的渠道id", required = true, position = 0)
    private String assignId;

    @ApiModelProperty(value = "客户姓名", example = "张三", position = 1)
    private String name;

    @ApiModelProperty(value = "手机号", required = true, dataType = "String", example = "15797768333", position = 2)
    private String mobile;

    @ApiModelProperty(value = "年龄", example = "18", dataType = "Integer", position = 3)
    private Integer age;


    @ApiModelProperty(value = "性别", example = "男", allowableValues = "1男, 2女,", position = 4)
    private Integer sex;

    @ApiModelProperty(value = "城市", example = "杭州", position = 5)
    private String city;

    @ApiModelProperty(value = "是否有房", example = "1", allowableValues = "0无, 1有, -1未知", position = 6)
    private Integer hasHouse;

    @ApiModelProperty(value = "是否有车", example = "1", allowableValues = "0, 1, -1", position = 7)
    private Integer hasCar;

    @ApiModelProperty(value = "是否有保单", example = "1", allowableValues = "0, 2、1, -1", position = 8)
    private Integer hasInsurance;

    @ApiModelProperty(value = "是否有信用卡", example = "1", allowableValues = "0, 1, -1", position = 9)
    private Integer hasCredit;

    @ApiModelProperty(value = "是否有公积金", example = "1", position = 10)
    private Integer hasFund;

    @ApiModelProperty(value = "是否有社保", example = "1", position = 11)
    private Integer hasSocialSecurity;

    @ApiModelProperty(value = "是否有职业", example = "1", position = 12)
    private Integer hasWork;

    @ApiModelProperty(value = "申请金额(单位元)", example = "2000", position = 13)
    private Integer moneyDemand;

    @ApiModelProperty(value = "是否有微粒贷", example = "1", allowableValues = "1, 2, -1", position = 14)
    private Integer webank;


    public String getAssignId() {
        return assignId;
    }

    public void setAssignId(String assignId) {
        this.assignId = assignId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getHasSocialSecurity() {
        return hasSocialSecurity;
    }

    public void setHasSocialSecurity(Integer hasSocialSecurity) {
        this.hasSocialSecurity = hasSocialSecurity;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getHasHouse() {
        return hasHouse;
    }

    public void setHasHouse(Integer hasHouse) {
        this.hasHouse = hasHouse;
    }

    public Integer getHasCar() {
        return hasCar;
    }

    public void setHasCar(Integer hasCar) {
        this.hasCar = hasCar;
    }

    public Integer getHasInsurance() {
        return hasInsurance;
    }


    public void setHasInsurance(Integer hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    public Integer getHasCredit() {
        return hasCredit;
    }

    public void setHasCredit(Integer hasCredit) {
        this.hasCredit = hasCredit;
    }

    public Integer getHasFund() {
        return hasFund;
    }

    public void setHasFund(Integer hasFund) {
        this.hasFund = hasFund;
    }

    public Integer getHasWork() {
        return hasWork;
    }

    public void setHasWork(Integer hasWork) {
        this.hasWork = hasWork;
    }

    public Integer getMoneyDemand() {
        return moneyDemand;
    }

    public void setMoneyDemand(Integer moneyDemand) {
        this.moneyDemand = moneyDemand;
    }

    public Integer getWebank() {
        return webank;
    }

    public void setWebank(Integer webank) {
        this.webank = webank;
    }


}
