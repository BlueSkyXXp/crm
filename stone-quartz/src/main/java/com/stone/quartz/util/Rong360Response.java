package com.stone.quartz.util;
import com.stone.crm.domain.St02Rong360Order;

import java.util.ArrayList;

public class Rong360Response {
    private int error;
    private String msg;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public String getBdApiCommonOrderGetorderlistResponse() {
        return bdApiCommonOrderGetorderlistResponse;
    }

    public void setBdApiCommonOrderGetorderlistResponse(String bdApiCommonOrderGetorderlistResponse) {
        this.bdApiCommonOrderGetorderlistResponse = bdApiCommonOrderGetorderlistResponse;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String bdApiCommonOrderGetorderlistResponse;
}
