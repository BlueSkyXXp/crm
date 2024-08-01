package com.stone.crm.domain.vo;

import com.stone.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@ApiModel(value = "扫码登记")
@Data
@Builder
public class VisitDetailReqVo {
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    @Excel(name = "用户ID")
    public Long userId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Excel(name = "创建时间")
    public List<Long> queryTime;

    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门ID")
    @Excel(name = "部门ID")
    public Long deptId;


    public VisitDetailReqVo() {
    }

    public VisitDetailReqVo(Long deptId, List<Long> queryTime, Long userId) {
        this.deptId = deptId;
        this.queryTime = queryTime;
        this.userId = userId;
    }

    public VisitDetailReqVo(VisitDetailReqVo visitVo) {
        this.userId = visitVo.getUserId();
        this.queryTime = visitVo.getQueryTime();
        this.deptId = visitVo.getDeptId();
    }

}
