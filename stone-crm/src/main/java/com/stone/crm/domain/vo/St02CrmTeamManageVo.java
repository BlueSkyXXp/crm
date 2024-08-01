package com.stone.crm.domain.vo;

import com.stone.common.annotation.Excel;
import com.stone.crm.domain.St02CrmTeamManage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "团队管理拓展对象")
public class St02CrmTeamManageVo extends St02CrmTeamManage {

    /** 跟进人 */
    @ApiModelProperty(value = "跟进人")
    @Excel(name = "跟进人")
    private String userName;


    /** 操作者 */
    @ApiModelProperty(value = "操作者")
    @Excel(name = "操作者")
    private String operatorName;

    /** 跟进人 */
    @ApiModelProperty(value = "跟进人")
    @Excel(name = "跟进人")
    private Long deptId;


    /** 部门 */
    @ApiModelProperty(value = "部门")
    @Excel(name = "部门")
    private String deptName;

}
