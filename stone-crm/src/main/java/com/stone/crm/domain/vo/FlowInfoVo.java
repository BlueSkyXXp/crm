package com.stone.crm.domain.vo;

import com.stone.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "分配记录")
@Data
public class FlowInfoVo {
    /** 用户名 */
    @ApiModelProperty(value = "用户名")
    @Excel(name = "用户名")
    public String userName;

    /** 来源 */
    @ApiModelProperty(value = "来源")
    @Excel(name = "来源")
    public String sourcePlat;

    /** 目标 */
    @ApiModelProperty(value = "目标")
    @Excel(name = "目标")
    public String targetPlat;

    /** 查询时间范围 */
    @ApiModelProperty(value = "查询时间范围")
    @Excel(name = "查询时间范围")
    public List<Long> createTime;

    /** 用户ID */
    @ApiModelProperty(value = "用户ID")
    @Excel(name = "用户ID")
    public Long userId;

    public FlowInfoVo(String targetPlat, String sourcePlat, String userName, Long userId, List<Long> createTime) {
        this.targetPlat = targetPlat;
        this.sourcePlat = sourcePlat;
        this.userName = userName;
        this.userId = userId;
        this.createTime = createTime;
    }

    public FlowInfoVo() {
    }

    public FlowInfoVo(FlowInfoVo flowInfoVo) {
        this.targetPlat = flowInfoVo.getTargetPlat();
        this.sourcePlat = flowInfoVo.getSourcePlat();
        this.userName = flowInfoVo.getUserName();
        this.userId = flowInfoVo.getUserId();
        this.createTime = flowInfoVo.getCreateTime();
    }
}
