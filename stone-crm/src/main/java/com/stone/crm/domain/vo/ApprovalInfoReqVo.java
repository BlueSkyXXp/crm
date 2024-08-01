package com.stone.crm.domain.vo;

import com.stone.common.annotation.Excel;
import com.stone.common.utils.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "日内部数据统计请求参数")
@Getter
@Setter
public class ApprovalInfoReqVo extends ApprovalInfoVo {

    /** 开始查询时间 */
    @ApiModelProperty(value = "开始查询时间")
    @Excel(name = "开始查询时间")
    public String startTime;

    /** 结束查询时间 */
    @ApiModelProperty(value = "结束查询时间")
    @Excel(name = "结束查询时间")
    public String endTime;

    public ApprovalInfoReqVo(ApprovalInfoVo ApprovalInfoVo) {
        super(ApprovalInfoVo);
        if (super.getQueryTime() != null && super.getQueryTime().size() == 2) {
            this.endTime = DateUtil.timestampToDateString(super.getQueryTime().get(1));
            this.startTime = DateUtil.timestampToDateString(super.getQueryTime().get(0));
        }
    }

}
