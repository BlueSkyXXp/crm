package com.stone.quartz.util;

import com.stone.crm.domain.St02Rong360Order;

import java.util.ArrayList;

public class BdApiCommonOrderGetorderlistResponse {
    private int pages;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getDataLength() {
        return dataLength;
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    private int dataLength; // JSON字段名应为camelCase风格
    private ArrayList<String> data; // 注意：这里假设data字段是Object类型，实际应根据具体情况定义
    private int count;
    private int page;
    private int pageSize; // JSON字段名应为camelCase风格
}
