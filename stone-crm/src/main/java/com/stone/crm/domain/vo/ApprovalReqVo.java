package com.stone.crm.domain.vo;

import com.stone.common.annotation.Excel;
import com.stone.common.utils.StringUtils;
import com.stone.common.utils.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@ApiModel(value = "日内部数据统计请求参数")
@Getter
@Setter
public class ApprovalReqVo extends ApprovalVo{

    /** 开始查询时间 */
    @ApiModelProperty(value = "开始查询时间")
    @Excel(name = "开始查询时间")
    public String startTime;

    /** 结束查询时间 */
    @ApiModelProperty(value = "结束查询时间")
    @Excel(name = "结束查询时间")
    public String endTime;

    public ApprovalReqVo(ApprovalVo approvalVo) {
        super(approvalVo);
        if (StringUtils.isEmpty(type)) {
            setType("all");
        }
        if (queryTime == null || queryTime.size() < 2) {
            this.endTime = DateUtil.timestampToDateString(System.currentTimeMillis() + 86400000L, "yyyy-MM-dd");
            this.startTime = DateUtil.timestampToDateString(System.currentTimeMillis(), "yyyy-MM-dd");
        } else {
            this.endTime = DateUtil.timestampToDateString(queryTime.get(1));
            this.startTime = DateUtil.timestampToDateString(queryTime.get(0));
        }
    }
}
