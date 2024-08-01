package com.stone.crm.domain.vo;

import com.stone.common.annotation.Excel;
import com.stone.common.utils.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "审件实体类请求参数")
@Getter
@Setter
public class DailyInterDataReqVo extends DailyInterDataVo {

    public DailyInterDataReqVo(DailyInterDataVo dailyInterDataVo) {
        super(dailyInterDataVo);
        if (createTime == null || createTime.size() < 2) {
            this.endTime = DateUtil.timestampToDateString(System.currentTimeMillis() + 86400000L, "yyyy-MM-dd");
            this.startTime = DateUtil.timestampToDateString(System.currentTimeMillis(), "yyyy-MM-dd");
        } else {
            this.endTime = DateUtil.timestampToDateString(createTime.get(1));
            this.startTime = DateUtil.timestampToDateString(createTime.get(0));
        }
    }

    /** 开始查询时间 */
    @ApiModelProperty(value = "开始查询时间")
    @Excel(name = "开始查询时间")
    public String startTime;

    /** 结束查询时间 */
    @ApiModelProperty(value = "结束查询时间")
    @Excel(name = "结束查询时间")
    public String endTime;
}
