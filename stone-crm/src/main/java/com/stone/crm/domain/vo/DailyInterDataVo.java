package com.stone.crm.domain.vo;

import com.stone.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "意向感知度备注")
@Data
public class DailyInterDataVo {
    /** 渠道来源 */
    @ApiModelProperty(value = "渠道来源")
    @Excel(name = "渠道来源")
    public String source;

    /** 查询时间范围 */
    @ApiModelProperty(value = "查询时间范围")
    @Excel(name = "查询时间范围")
    public List<Long> createTime;

    /** 部门ID */
    @ApiModelProperty(value = "部门ID")
    @Excel(name = "部门ID")
    public Long deptId;

    public DailyInterDataVo(String source, Long deptId, List<Long> createTime) {
        this.source = source;
        this.deptId = deptId;
        this.createTime = createTime;
    }

    public DailyInterDataVo(DailyInterDataVo dailyInterDataVo) {
        this.source = dailyInterDataVo.getSource();
        this.deptId = dailyInterDataVo.getDeptId();
        this.createTime = dailyInterDataVo.getCreateTime();
    }

    public DailyInterDataVo() {
    }
}
