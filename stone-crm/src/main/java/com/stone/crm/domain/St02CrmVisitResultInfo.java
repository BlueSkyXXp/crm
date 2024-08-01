package com.stone.crm.domain;


import com.stone.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 客户来访对象 st02_crm_visit_result_info
 *
 * @author xiongxiaopeng
 * @date 2024-06-25
 */
@Getter
@Setter
@ApiModel(value = "客户来访统计")
public class St02CrmVisitResultInfo {
    /** 跟进人(用户ID) */
    @ApiModelProperty(value = "跟进人(用户ID)")
    @Excel(name = "跟进人(用户ID)")
    private Long userId;

    /** 跟进人 */
    @ApiModelProperty(value = "跟进人")
    @Excel(name = "跟进人")
    private String userName;

    /** 部门ID */
    @ApiModelProperty(value = "部门ID")
    @Excel(name = "部门ID")
    private Long deptId;

    /** 部门 */
    @ApiModelProperty(value = "部门")
    @Excel(name = "部门")
    private String deptName;

    /** 次数 */
    @ApiModelProperty(value = "次数")
    @Excel(name = "次数")
    private Integer times;
}
