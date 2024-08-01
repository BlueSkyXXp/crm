package com.stone.crm.domain;

import java.math.BigDecimal;
import com.stone.common.annotation.Excel;
import com.stone.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 日内部数据统计对象 st02_crm_team_static
 *
 * @author xiongxiaopeng
 * @date 2024-06-30
 */
@Getter
@Setter
@ApiModel(value = "日内部数据统计对象")
public class St02CrmTeamStatic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门名称")
    @Excel(name = "部门名称")
    private Long deptId;

    /** 部门名称 */
    @ApiModelProperty(value = "部门名称")
    @Excel(name = "部门名称")
    private String deptName;

    /** 用户名 */
    @ApiModelProperty(value = "用户名")
    @Excel(name = "用户名")
    private String userName;

    /** 0星数据 */
    @ApiModelProperty(value = "0星数据")
    @Excel(name = "0星数据")
    private Long level0Cnt;

    /** 0星比例 */
    @ApiModelProperty(value = "0星比例")
    @Excel(name = "0星比例")
    private BigDecimal level0Percentage;

    /** 1星数据 */
    @ApiModelProperty(value = "1星数据")
    @Excel(name = "1星数据")
    private Long level1Cnt;

    /** 1星比例 */
    @ApiModelProperty(value = "1星比例")
    @Excel(name = "1星比例")
    private BigDecimal level1Percentage;

    /** 2星数据 */
    @ApiModelProperty(value = "2星数据")
    @Excel(name = "2星数据")
    private Long level2Cnt;

    /** 2星比例 */
    @ApiModelProperty(value = "2星比例")
    @Excel(name = "2星比例")
    private BigDecimal level2Percentage;

    /** 3星数据 */
    @ApiModelProperty(value = "3星数据")
    @Excel(name = "3星数据")
    private Long level3Cnt;

    /** 3星比例 */
    @ApiModelProperty(value = "3星比例")
    @Excel(name = "3星比例")
    private BigDecimal level3Percentage;

    /** 4星数据 */
    @ApiModelProperty(value = "4星数据")
    @Excel(name = "4星数据")
    private Long level4Cnt;

    /** 4星比例 */
    @ApiModelProperty(value = "4星比例")
    @Excel(name = "4星比例")
    private BigDecimal level4Percentage;

    /** 重要客户 */
    @ApiModelProperty(value = "重要客户")
    @Excel(name = "重要客户")
    private Long level5Cnt;

    /** 重要客户比例 */
    @ApiModelProperty(value = "重要客户比例")
    @Excel(name = "重要客户比例")
    private BigDecimal level5Percentage;

    /** 客户总数 */
    @ApiModelProperty(value = "客户总数")
    @Excel(name = "客户总数")
    private Long cnt;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("deptName", getDeptName())
                .append("userName", getUserName())
                .append("level0Cnt", getLevel0Cnt())
                .append("level0Percentage", getLevel0Percentage())
                .append("level1Cnt", getLevel1Cnt())
                .append("level1Percentage", getLevel1Percentage())
                .append("level2Cnt", getLevel2Cnt())
                .append("level2Percentage", getLevel2Percentage())
                .append("level3Cnt", getLevel3Cnt())
                .append("level3Percentage", getLevel3Percentage())
                .append("level4Cnt", getLevel4Cnt())
                .append("level4Percentage", getLevel4Percentage())
                .append("level5Cnt", getLevel5Cnt())
                .append("level5Percentage", getLevel5Percentage())
                .append("cnt", getCnt())
                .toString();
    }
}
