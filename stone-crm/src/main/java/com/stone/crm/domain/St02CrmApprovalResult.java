package com.stone.crm.domain;


import java.math.BigDecimal;
import com.stone.common.annotation.Excel;
import com.stone.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 审件请求对象 st02_crm_approval_info_req
 *
 * @author xiongxiaopeng
 * @date 2024-06-30
 */
@Getter
@Setter
@ApiModel(value = "审件请求对象")
public class St02CrmApprovalResult extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 部门ID */
    @ApiModelProperty(value = "部门ID")
    @Excel(name = "部门ID")
    private Long deptId;

    /** 部门名称 */
    @ApiModelProperty(value = "部门名称")
    @Excel(name = "部门名称")
    private String deptName;

    /** 用户ID */
    @ApiModelProperty(value = "用户ID")
    @Excel(name = "用户ID")
    private Long userId;

    /** 用户名 */
    @ApiModelProperty(value = "用户名")
    @Excel(name = "用户名")
    private String userName;

    /** 当月创收 */
    @ApiModelProperty(value = "当月创收")
    @Excel(name = "当月创收")
    private BigDecimal monthlyRevenue;

    /** 当月进件 */
    @ApiModelProperty(value = "当月进件")
    @Excel(name = "当月进件")
    private Long monthlyCnt;

    /** 当月收款件数 */
    @ApiModelProperty(value = "当月收款件数")
    @Excel(name = "当月收款件数")
    private Long monthlyPayCnt;

    /** 当日创收 */
    @ApiModelProperty(value = "当日创收")
    @Excel(name = "当日创收")
    private BigDecimal dailyRevenue;

    /** 当日件数 */
    @ApiModelProperty(value = "当日件数")
    @Excel(name = "当日件数")
    private Long dailyCnt;

    /** 当日收款件数 */
    @ApiModelProperty(value = "当日收款件数")
    @Excel(name = "当日收款件数")
    private Long dailyPayCnt;

    /** 月批款额度 */
    @ApiModelProperty(value = "月批款额度")
    @Excel(name = "月批款额度")
    private Long monthlyAppro;

    /** 总业绩 */
    @ApiModelProperty(value = "总业绩")
    @Excel(name = "总业绩")
    private Long yearRevenue;

    /** 当月上门 */
    @ApiModelProperty(value = "当月上门")
    @Excel(name = "当月上门")
    private Long visitMonthlyCnt;

    /** 当日上门 */
    @ApiModelProperty(value = "当日上门")
    @Excel(name = "当日上门")
    private Long visitDailyCnt;

    /** 当月分配 */
    @ApiModelProperty(value = "当月分配")
    @Excel(name = "当月分配")
    private Long flowMonthlyCnt;

    /** 当日分配 */
    @ApiModelProperty(value = "当日分配")
    @Excel(name = "当日分配")
    private Long flowDailyCnt;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("deptId", getDeptId())
                .append("deptName", getDeptName())
                .append("userId", getUserId())
                .append("userName", getUserName())
                .append("monthlyRevenue", getMonthlyRevenue())
                .append("monthlyCnt", getMonthlyCnt())
                .append("monthlyPayCnt", getMonthlyPayCnt())
                .append("dailyRevenue", getDailyRevenue())
                .append("dailyCnt", getDailyCnt())
                .append("dailyPayCnt", getDailyPayCnt())
                .append("monthlyAppro", getMonthlyAppro())
                .append("yearRevenue", getYearRevenue())
                .append("visitMonthlyCnt", getVisitMonthlyCnt())
                .append("visitDailyCnt", getVisitDailyCnt())
                .append("flowMonthlyCnt", getFlowMonthlyCnt())
                .append("flowDailyCnt", getFlowDailyCnt())
                .toString();
    }
}
