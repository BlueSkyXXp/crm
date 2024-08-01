package com.stone.crm.domain;

import com.stone.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import com.stone.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 团队管理对象 st02_crm_team_manage
 * 
 * @author xiongxiaopeng
 * @date 2024-06-25
 */
@ApiModel(value = "团队管理对象")
public class St02CrmTeamManage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增ID */
    private Long id;

    /** 跟进人(用户ID) */
    @ApiModelProperty(value = "跟进人(用户ID)")
    @Excel(name = "跟进人(用户ID)")
    private String userId;

    /** 日分配数据数 */
    @ApiModelProperty(value = "日分配数据数")
    @Excel(name = "日分配数据数")
    private Long planDataNum;

    /** 实际日分配数据数 */
    @ApiModelProperty(value = "实际日分配数据数")
    @Excel(name = "实际日分配数据数")
    private Long receiveDataNum;

    /** 是否接收新数据: 接收,不接收 */
    @ApiModelProperty(value = "是否接收新数据: 接收,不接收")
    @Excel(name = "是否接收新数据: 接收,不接收")
    private Long receiveNewData;

    /** 是否接收公共池数据: 接收,不接收 */
    @ApiModelProperty(value = "是否接收公共池数据: 接收,不接收")
    @Excel(name = "是否接收公共池数据: 接收,不接收")
    private Long receivePublicData;

    /** 是否允许登录: 1, 0 */
    @ApiModelProperty(value = "是否允许登录: 1, 0")
    @Excel(name = "是否允许登录: 1, 0")
    private Long login;

    /** 操作者(用户ID) */
    @ApiModelProperty(value = "操作者(用户ID)")
    @Excel(name = "操作者(用户ID)")
    private Long operatorId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId()
    {
        return userId;
    }
    public void setPlanDataNum(Long planDataNum) 
    {
        this.planDataNum = planDataNum;
    }

    public Long getPlanDataNum() 
    {
        return planDataNum;
    }
    public void setReceiveDataNum(Long receiveDataNum) 
    {
        this.receiveDataNum = receiveDataNum;
    }

    public Long getReceiveDataNum() 
    {
        return receiveDataNum;
    }
    public void setReceiveNewData(Long receiveNewData) 
    {
        this.receiveNewData = receiveNewData;
    }

    public Long getReceiveNewData() 
    {
        return receiveNewData;
    }
    public void setReceivePublicData(Long receivePublicData) 
    {
        this.receivePublicData = receivePublicData;
    }

    public Long getReceivePublicData() 
    {
        return receivePublicData;
    }
    public void setLogin(Long login) 
    {
        this.login = login;
    }

    public Long getLogin() 
    {
        return login;
    }
    public void setOperatorId(Long operatorId) 
    {
        this.operatorId = operatorId;
    }

    public Long getOperatorId() 
    {
        return operatorId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("planDataNum", getPlanDataNum())
            .append("receiveDataNum", getReceiveDataNum())
            .append("receiveNewData", getReceiveNewData())
            .append("receivePublicData", getReceivePublicData())
            .append("login", getLogin())
            .append("remark", getRemark())
            .append("operatorId", getOperatorId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }

    public St02CrmTeamManage(Long operatorId, Long login, Long receivePublicData, Long receiveNewData, Long planDataNum, String userId, Long id, Long receiveDataNum) {
        this.operatorId = operatorId;
        this.login = login;
        this.receivePublicData = receivePublicData;
        this.receiveNewData = receiveNewData;
        this.planDataNum = planDataNum;
        this.userId = userId;
        this.id = id;
        this.receiveDataNum = receiveDataNum;
    }

    public St02CrmTeamManage(St02CrmTeamManage teamManage) {
        this.operatorId = teamManage.getOperatorId();
        this.login = teamManage.getLogin();
        this.receivePublicData = teamManage.getReceivePublicData();
        this.receiveNewData = teamManage.getReceiveNewData();
        this.planDataNum = teamManage.getPlanDataNum();
        this.userId = teamManage.getUserId();
        this.id = teamManage.getId();
        this.receiveDataNum = teamManage.getReceiveDataNum();
    }

    public St02CrmTeamManage() {
    }
}
