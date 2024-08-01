package com.stone.common.core.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Entity基类
 * 
 * @author ruoyi
 */
@Setter
public class BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 搜索值 */
    @JsonIgnore
    private String searchValue;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间", dataType = "2022-01-01 00:00:00", example = "2022-01-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @ApiModelProperty(value = "更新时间", dataType = "2022-01-01 00:00:00", example = "2022-01-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /** 备注 */
    private String remark;

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> params;

    public String getSearchValue()
    {
        return searchValue;
    }

    public String getCreateBy()
    {
        return createBy;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public String getUpdateBy()
    {
        return updateBy;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public String getRemark()
    {
        return remark;
    }

    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }

}
