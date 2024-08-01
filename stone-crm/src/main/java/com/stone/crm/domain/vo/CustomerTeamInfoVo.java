package com.stone.crm.domain.vo;

import com.stone.common.annotation.Excel;
import com.stone.crm.domain.St02CrmCustomer;
import io.swagger.annotations.ApiModelProperty;

public class CustomerTeamInfoVo extends St02CrmCustomer {

    private static final long serialVersionUID = 1L;

    @Excel(name = "顾问")
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
