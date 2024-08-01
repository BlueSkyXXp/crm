package com.stone.crm.domain.vo;

import com.stone.common.annotation.Excel;
import com.stone.common.utils.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "来访信息")
@Getter
@Setter
public class VisitReqVo extends  VisitVo{

    /** 开始查询时间 */
    @ApiModelProperty(value = "开始查询时间")
    @Excel(name = "开始查询时间")
    public String startTime;

    /** 结束查询时间 */
    @ApiModelProperty(value = "结束查询时间")
    @Excel(name = "结束查询时间")
    public String endTime;

    public VisitReqVo(VisitVo visitVo) {
        super(visitVo);
        if (queryTime == null || queryTime.size() < 2) {
            this.endTime = DateUtil.timestampToDateString(System.currentTimeMillis() + 86400000L, "yyyy-MM-dd");
            this.startTime = DateUtil.timestampToDateString(System.currentTimeMillis(), "yyyy-MM-dd");
        } else {
            this.endTime = DateUtil.timestampToDateString(queryTime.get(1));
            this.startTime = DateUtil.timestampToDateString(queryTime.get(0));
        }
    }

    public VisitReqVo() {
    }

    public VisitReqVo(VisitDetailReqVo visitDetailReqVo) {
        if (visitDetailReqVo.getQueryTime() == null || visitDetailReqVo.getQueryTime().size() < 2) {
            this.endTime = DateUtil.timestampToDateString(System.currentTimeMillis() + 86400000L, "yyyy-MM-dd");
            this.startTime = DateUtil.timestampToDateString(System.currentTimeMillis(), "yyyy-MM-dd");
        } else {
            this.endTime = DateUtil.timestampToDateString(visitDetailReqVo.getQueryTime().get(1));
            this.startTime = DateUtil.timestampToDateString(visitDetailReqVo.getQueryTime().get(0));
        }
    }
}
