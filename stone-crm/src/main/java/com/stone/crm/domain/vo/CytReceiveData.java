package com.stone.crm.domain.vo;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CytReceiveData {
    private String businessCode;
    private String data;

    public CytReceiveData(String businessCode, String data) {
        this.businessCode = businessCode;
        this.data = data;
    }

    public CytReceiveData() {
    }
}
