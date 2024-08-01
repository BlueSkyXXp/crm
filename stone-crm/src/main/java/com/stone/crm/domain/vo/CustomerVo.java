package com.stone.crm.domain.vo;

import com.stone.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 客户请求参数对象 st02_crm_customer_vo
 *
 * @author xiongxiaopeng
 * @date 2024-06-29
 */
@Getter
@Setter
@ApiModel(value = "客户请求参数对象")
public class CustomerVo {


    private static final long serialVersionUID = 1L;

    /** 客户ID */
    private Long id;

    /** 客户姓名 */
    @ApiModelProperty(value = "客户姓名")
    @Excel(name = "客户姓名")
    private String name;

    /** 电话 */
    @ApiModelProperty(value = "电话")
    @Excel(name = "电话")
    private String phone;

    /** 客户状态 */
    @ApiModelProperty(value = "客户状态")
    @Excel(name = "客户状态")
    private Long status;

    /** 客户星级 */
    @ApiModelProperty(value = "客户星级")
    @Excel(name = "客户星级")
    private Long level;

    /** 客户来源 */
    @ApiModelProperty(value = "客户来源")
    @Excel(name = "客户来源")
    private String source;

    /** 备注 */
    @ApiModelProperty(value = "备注")
    @Excel(name = "备注")
    private String remark;

    /** 未新增备注超过多少天 */
    @ApiModelProperty(value = "未新增备注超过多少天")
    @Excel(name = "未新增备注超过多少天")
    private Long remarkDay;

    /** 贷款类型 */
    @ApiModelProperty(value = "贷款类型")
    @Excel(name = "贷款类型")
    private Long loanType;

    /** 车产信息: 0: 无, 1: 有 */
    @ApiModelProperty(value = "车产信息: 0: 无, 1: 有")
    @Excel(name = "车产信息: 0: 无, 1: 有")
    private Long carInfo;

    /** 房产信息:0:无, 1: 有 */
    @ApiModelProperty(value = "房产信息:0:无, 1: 有")
    @Excel(name = "房产信息:0:无, 1: 有")
    private Long propertyInfo;

    /** 保单信息: 0: 无, 1:有 */
    @ApiModelProperty(value = "保单信息: 0: 无, 1:有")
    @Excel(name = "保单信息: 0: 无, 1:有")
    private Long policyInfo;

    /** 部门ID */
    @ApiModelProperty(value = "部门ID")
    @Excel(name = "部门ID")
    private Long dept;

    /** 用户ID */
    @ApiModelProperty(value = "用户ID")
    @Excel(name = "用户ID")
    private String userId;

    /** 最小资金需求 */
    @ApiModelProperty(value = "最小资金需求")
    @Excel(name = "最小资金需求")
    private Long minDemandMoney;

    /** 最大资金需求 */
    @ApiModelProperty(value = "最大资金需求")
    @Excel(name = "最大资金需求")
    private Long maxDemandMoney;

    /** 跟进次数 */
    @ApiModelProperty(value = "跟进次数")
    @Excel(name = "跟进次数")
    private Long followTimes;

    /** 申请时间 */
    @ApiModelProperty(value = "申请时间")
    @Excel(name = "申请时间")
    private List<Long> createTime;

    /** 原始分配时间 */
    @ApiModelProperty(value = "原始分配时间")
    @Excel(name = "原始分配时间")
    private List<Long> firstAssignTime;

    /** 最近分配时间 */
    @ApiModelProperty(value = "最近分配时间")
    @Excel(name = "最近分配时间")
    private List<Long> lastAssignTime;

    public CustomerVo(Long id, String name, String phone, Long status, Long level, String source, String remark, Long remarkDay, Long loanType, Long carInfo, Long propertyInfo, Long policyInfo, Long dept, Long userId, Long minDemandMoney, Long maxDemandMoney, Long followTimes, List<Long> createTime, List<Long> firstAssignTime, List<Long> lastAssignTime) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.level = level;
        this.source = source;
        this.remark = remark;
        this.remarkDay = remarkDay;
        this.loanType = loanType;
        this.carInfo = carInfo;
        this.propertyInfo = propertyInfo;
        this.policyInfo = policyInfo;
        this.dept = dept;
        this.userId = userId.toString();
        this.minDemandMoney = minDemandMoney;
        this.maxDemandMoney = maxDemandMoney;
        this.followTimes = followTimes;
        this.createTime = createTime;
        this.firstAssignTime = firstAssignTime;
        this.lastAssignTime = lastAssignTime;
    }

    public CustomerVo(CustomerVo customerVo) {
        this.id = customerVo.getId();
        this.name = customerVo.getName();
        this.phone = customerVo.getPhone();
        this.status = customerVo.getStatus();
        this.level = customerVo.getLevel();
        this.source = customerVo.getSource();
        this.remark = customerVo.getRemark();
        this.remarkDay = customerVo.getRemarkDay();
        this.loanType = customerVo.getLoanType();
        this.carInfo = customerVo.getCarInfo();
        this.propertyInfo = customerVo.getPropertyInfo();
        this.policyInfo = customerVo.getPolicyInfo();
        this.dept = customerVo.getDept();
        this.userId = customerVo.getUserId();
        this.minDemandMoney = customerVo.getMinDemandMoney();
        this.maxDemandMoney = customerVo.getMaxDemandMoney();
        this.followTimes = customerVo.getFollowTimes();
        this.createTime = customerVo.getCreateTime();
        this.firstAssignTime = customerVo.getFirstAssignTime();
        this.lastAssignTime = customerVo.getLastAssignTime();
    }

    public CustomerVo() {
    }
}
