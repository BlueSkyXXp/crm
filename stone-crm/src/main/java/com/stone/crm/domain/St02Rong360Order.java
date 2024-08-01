package com.stone.crm.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.stone.common.annotation.Excel;
import com.stone.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 融360订单数据对象 st02_rong_360_order
 * 
 * @author xiongxiaopeng
 * @date 2024-06-27
 */
@ApiModel(value = "融360订单数据对象")
public class St02Rong360Order extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 系统订单id */
    @ApiModelProperty(value = "系统订单id")
    @Excel(name = "系统订单id")
    private Long orderId;

    /** 城市 */
    @ApiModelProperty(value = "城市")
    @Excel(name = "城市")
    private String city;

    /** 姓名 */
    @ApiModelProperty(value = "姓名")
    @Excel(name = "姓名")
    private String userName;

    /** 电话 */
    @ApiModelProperty(value = "电话")
    @Excel(name = "电话")
    private String userMobile;

    /** 产品名称 */
    @ApiModelProperty(value = "产品名称")
    @Excel(name = "产品名称")
    private String product;

    /** 贷款期数(月) */
    @ApiModelProperty(value = "贷款期数(月)")
    @Excel(name = "贷款期数(月)")
    private Long applicationTerm;

    /** 信贷员id */
    @ApiModelProperty(value = "信贷员id")
    @Excel(name = "信贷员id")
    private Long bankerId;

    /** 系统内部反馈状态 */
    @ApiModelProperty(value = "系统内部反馈状态")
    @Excel(name = "系统内部反馈状态")
    private Long status;

    /** 订单来源: 系统推单, 手推单 */
    @ApiModelProperty(value = "订单来源: 系统推单, 手推单")
    @Excel(name = "订单来源: 系统推单, 手推单")
    private String applyFrom;

    /** 用户资质 */
    @ApiModelProperty(value = "用户资质")
    @Excel(name = "用户资质")
    private String qsearch;

    /** 个人共享清单 */
    @ApiModelProperty(value = "个人共享清单")
    @Excel(name = "个人共享清单")
    private String sharinglistUrl;

    /** 删除时间 */
    @ApiModelProperty(value = "删除时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "删除时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deleteAt;

    /** 贷款金额(万元) */
    @ApiModelProperty(value = "贷款金额(万元)")
    @Excel(name = "贷款金额(万元)")
    private BigDecimal applicationLimit;

    /** 订单花费 */
    @ApiModelProperty(value = "订单花费")
    @Excel(name = "订单花费")
    private BigDecimal cost;

    /** 授权文件(链接有效期5分钟) */
    @ApiModelProperty(value = "授权文件(链接有效期5分钟)")
    @Excel(name = "授权文件(链接有效期5分钟)")
    private String authorizationUrl;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setUserMobile(String userMobile) 
    {
        this.userMobile = userMobile;
    }

    public String getUserMobile() 
    {
        return userMobile;
    }
    public void setProduct(String product) 
    {
        this.product = product;
    }

    public String getProduct() 
    {
        return product;
    }
    public void setApplicationTerm(Long applicationTerm) 
    {
        this.applicationTerm = applicationTerm;
    }

    public Long getApplicationTerm() 
    {
        return applicationTerm;
    }
    public void setBankerId(Long bankerId) 
    {
        this.bankerId = bankerId;
    }

    public Long getBankerId() 
    {
        return bankerId;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setApplyFrom(String applyFrom) 
    {
        this.applyFrom = applyFrom;
    }

    public String getApplyFrom() 
    {
        return applyFrom;
    }
    public void setQsearch(String qsearch) 
    {
        this.qsearch = qsearch;
    }

    public String getQsearch() 
    {
        return qsearch;
    }
    public void setSharinglistUrl(String sharinglistUrl) 
    {
        this.sharinglistUrl = sharinglistUrl;
    }

    public String getSharinglistUrl() 
    {
        return sharinglistUrl;
    }
    public void setDeleteAt(Date deleteAt) 
    {
        this.deleteAt = deleteAt;
    }

    public Date getDeleteAt() 
    {
        return deleteAt;
    }
    public void setApplicationLimit(BigDecimal applicationLimit) 
    {
        this.applicationLimit = applicationLimit;
    }

    public BigDecimal getApplicationLimit() 
    {
        return applicationLimit;
    }
    public void setCost(BigDecimal cost) 
    {
        this.cost = cost;
    }

    public BigDecimal getCost() 
    {
        return cost;
    }
    public void setAuthorizationUrl(String authorizationUrl) 
    {
        this.authorizationUrl = authorizationUrl;
    }

    public String getAuthorizationUrl() 
    {
        return authorizationUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("city", getCity())
            .append("userName", getUserName())
            .append("userMobile", getUserMobile())
            .append("product", getProduct())
            .append("applicationTerm", getApplicationTerm())
            .append("bankerId", getBankerId())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .append("applyFrom", getApplyFrom())
            .append("qsearch", getQsearch())
            .append("sharinglistUrl", getSharinglistUrl())
            .append("deleteAt", getDeleteAt())
            .append("applicationLimit", getApplicationLimit())
            .append("cost", getCost())
            .append("authorizationUrl", getAuthorizationUrl())
            .toString();
    }
}
