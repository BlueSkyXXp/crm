package com.stone.crm.domain.vo;

import com.stone.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel(value = "审件实体类请求参数")
@Getter
@Setter
public class ApprovalInfoVo {
    /** 顾客名称 */
    @ApiModelProperty(value = "顾客名称")
    @Excel(name = "顾客名称")
    private String customerName;
    /** 银行经理 */
    @ApiModelProperty(value = "银行经理")
    @Excel(name = "银行经理")
    private String bankManager;
    /** 银行 */
    @ApiModelProperty(value = "银行")
    @Excel(name = "银行")
    private String bank;
    /** 审件状态 */
    @ApiModelProperty(value = "审批人id")
    @Excel(name = "审批人id")
    private Long userId;
    /** 审批人IDs */
    @ApiModelProperty(value = "审批人IDs")
    @Excel(name = "审批人IDs")
    private String userIds;
    /** 审件状态 */
    @ApiModelProperty(value = "审件状态")
    @Excel(name = "审件状态")
    private Integer status;
    /** 审件类型 */
    @ApiModelProperty(value = "审件类型")
    @Excel(name = "审件类型")
    private Integer type;
    /** 部门ID */
    @ApiModelProperty(value = "部门ID")
    @Excel(name = "部门ID")
    private Long deptId;
    /** 部门名称 */
    @ApiModelProperty(value = "部门名称")
    @Excel(name = "部门名称")
    private String deptName;
    /** 查询时间范围 */
    @ApiModelProperty(value = "查询时间范围")
    @Excel(name = "查询时间范围")
    private List<Long> queryTime;

    public ApprovalInfoVo(String customerName, Long userId, String bankManager, Integer status, Integer type, String deptName, Long deptId, List<Long> queryTime) {
        this.customerName = customerName;
        this.userId = userId;
        this.bankManager = bankManager;
        this.status = status;
        this.type = type;
        this.deptName = deptName;
        this.deptId = deptId;
        this.queryTime = queryTime;
    }

    public ApprovalInfoVo() {
    }

    public ApprovalInfoVo(ApprovalInfoVo approvalInfoVo) {
        this.customerName = approvalInfoVo.getCustomerName();
        this.userId = approvalInfoVo.getUserId();
        this.bankManager = approvalInfoVo.getBankManager();
        this.status = approvalInfoVo.getStatus();
        this.type = approvalInfoVo.getType();
        this.deptName = approvalInfoVo.getDeptName();
        this.deptId = approvalInfoVo.getDeptId();
        this.queryTime = approvalInfoVo.getQueryTime();
    }
}
