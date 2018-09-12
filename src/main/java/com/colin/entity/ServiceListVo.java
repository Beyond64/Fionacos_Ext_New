package com.colin.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

@ExcelTarget("ServiceListVo")
public class ServiceListVo {
    @Excel(name = "客户商编码", orderNum = "1")
    private Integer gysId;
    @Excel(name = "单据日期", orderNum = "2")
    private String danjuDate;
    @Excel(name = "说明", orderNum = "3")
    private String description;
    @Excel(name = "金额", orderNum = "4")
    private Double amount;
    @Excel(name = "备注", orderNum = "5")
    private String remarks;
    @Excel(name = "付款方式", orderNum = "6")
    private String payType;

    public Integer getGysId() {
        return gysId;
    }

    public void setGysId(Integer gysId) {
        this.gysId = gysId;
    }

    public String getDanjuDate() {
        return danjuDate;
    }

    public void setDanjuDate(String danjuDate) {
        this.danjuDate = danjuDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
