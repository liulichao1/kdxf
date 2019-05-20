package com.iflytek.util;

import java.util.List;

public class Page {
    private int currentPage = 1;
    private int prePageNum = 5;
    private int totalNum;
    private List<?> values;

    public Page() {
        super();
    }

    public Page(int currentPage, int prePageNum) {
        this.currentPage = currentPage < 1 ? 1:currentPage;
        this.prePageNum = prePageNum < 5? 5:prePageNum;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPrePageNum() {
        return prePageNum;
    }

    public void setPrePageNum(int prePageNum) {
        this.prePageNum = prePageNum;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getTotalPage() {
        if (totalNum % prePageNum == 0) {
            return totalNum / prePageNum;
        }
        return (totalNum / prePageNum) + 1;
    }

    public void setTotalPage(int totalPage) {
        int totalPage1 = totalPage;
    }

    public List<?> getValues() {
        return values;
    }

    public void setValues(List<?> values) {
        this.values = values;
    }
}
