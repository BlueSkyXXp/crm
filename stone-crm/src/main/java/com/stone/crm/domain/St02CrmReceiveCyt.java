package com.stone.crm.domain;

import com.stone.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 进线数据对象 st02_crm_customer_receive_data
 *
 * @author xiongxiaopeng
 * @date 2024-07-24
 */
@ApiModel(value = "橙易通进线数据对象")
@Getter
@Setter
public class St02CrmReceiveCyt implements Serializable {
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

    /** 年龄 */
    @ApiModelProperty(value = "年龄")
    @Excel(name = "年龄")
    private String age;

    /** 性别:1-男,2-女,0-未知 */
    @ApiModelProperty(value = "性别:1-男,2-女,0-未知")
    @Excel(name = "性别:1-男,2-女,0-未知")
    private Integer gender;

    /** IP */
    @ApiModelProperty(value = "IP")
    @Excel(name = "IP")
    private String ip;

    /** 城市名 */
    @ApiModelProperty(value = "城市名")
    @Excel(name = "城市名")
    private String city;

    /** 城市编码 */
    @ApiModelProperty(value = "城市编码")
    @Excel(name = "城市编码")
    private String cityCode;

    /** 职业身份: 1-上班族, 2-自由职业,3-企业主,4-公务员/国企 */
    @ApiModelProperty(value = "职业身份: 1-上班族, 2-自由职业,3-企业主,4-公务员/国企")
    @Excel(name = "职业身份: 1-上班族, 2-自由职业,3-企业主,4-公务员/国企")
    private Integer profession;

    /** 贷款额度1:30000-50000 2:50000-100000 3:100000-200000 */
    @ApiModelProperty(value = "贷款额度1:30000-50000 2:50000-100000 3:100000-200000")
    @Excel(name = "贷款额度1:30000-50000 2:50000-100000 3:100000-200000")
    private Integer loanAmount;

    /** 芝麻分1:700以上 2:650-7003:600-6504:无 */
    @ApiModelProperty(value = "芝麻分1:700以上 2:650-7003:600-6504:无")
    @Excel(name = "芝麻分1:700以上 2:650-7003:600-6504:无")
    private Integer sesameSeed;

    /** 社保1:6个月以下2:6-12个月3:12个月以上4:无 */
    @ApiModelProperty(value = "社保1:6个月以下2:6-12个月3:12个月以上4:无")
    @Excel(name = "社保1:6个月以下2:6-12个月3:12个月以上4:无")
    private Integer socialSecurity;

    /** 公积金1:6个月以下2:6-12个月3:12个月以上4:无 */
    @ApiModelProperty(value = "公积金1:6个月以下2:6-12个月3:12个月以上4:无")
    @Excel(name = "公积金1:6个月以下2:6-12个月3:12个月以上4:无")
    private Long providentFund;

    /** 车产信息:2:无, 1: 有 */
    @ApiModelProperty(value = "车产信息:2:无, 1: 有")
    @Excel(name = "车产信息:2:无, 1: 有")
    private Integer vehicle;

    /** 房产信息:2:无, 1: 有 */
    @ApiModelProperty(value = "房产信息:2:无, 1: 有")
    @Excel(name = "房产信息:2:无, 1: 有")
    private Integer house;

    public St02CrmReceiveCyt() {
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("source", getSource())
                .append("name", getName())
                .append("phone", getPhone())
                .append("age", getAge())
                .append("gender", getGender())
                .append("ip", getIp())
                .append("city", getCity())
                .append("cityCode", getCityCode())
                .append("profession", getProfession())
                .append("loadAmount", getLoanAmount())
                .append("sesameSeed", getSesameSeed())
                .append("socialSecurity", getSocialSecurity())
                .append("providentFund", getProvidentFund())
                .append("vehicle", getVehicle())
                .append("house", getHouse())
                .toString();
    }
}
