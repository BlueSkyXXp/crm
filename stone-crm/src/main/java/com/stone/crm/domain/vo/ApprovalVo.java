package com.stone.crm.domain.vo;

import com.stone.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@ApiModel(value = "日内部数据统计请求参数")
@Data
@Builder
public class ApprovalVo {

    /** 聚合类型 */
    @ApiModelProperty(value = "聚合类型")
    @Excel(name = "聚合类型")
    public String type;

    /** 查询时间范围 */
//    @ApiModelProperty(value = "查询时间范围")
    @Excel(name = "查询时间范围")
    public List<Long> queryTime;

    public ApprovalVo(ApprovalVo approvalVo) {
        this.type = approvalVo.getType();
        this.queryTime = approvalVo.getQueryTime();
    }

    public ApprovalVo(String type, List<Long> queryTime) {
        this.type = type;
        this.queryTime = queryTime;
    }

    public ApprovalVo() {
    }
}
