package com.stone.system.domain;

import com.stone.common.annotation.Excel;
import lombok.Data;

@Data
public class SysArea {
    private static final long serialVersionUID = 1L;

    /** 区域主键 */
    private Long id;

    /** 区域名称 */
    @Excel(name = "区域名称")
    private String name;

    /** 区域上级标识 */
    @Excel(name = "区域上级标识")
    private Long pid;

    /** 地名简称 */
    @Excel(name = "地名简称")
    private String sname;

    /** 区域等级 */
    @Excel(name = "区域等级")
    private Long level;

    /** 区域编码 */
    @Excel(name = "区域编码")
    private String citycode;

    /** 邮政编码 */
    @Excel(name = "邮政编码")
    private String yzcode;

    /** 经度 */
    @Excel(name = "经度")
    private Long Lng;

    /** 维度 */
    @Excel(name = "维度")
    private Long Lat;

    /** 拼音 */
    @Excel(name = "拼音")
    private String pinyin;

    /** 组合名称 */
    @Excel(name = "组合名称")
    private String mername;
}
