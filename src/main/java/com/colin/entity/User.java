package com.colin.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;

@ExcelTarget("User")
public class User implements Serializable {
    private  int  userid;
    @Excel(name = "供应商码", orderNum = "1", needMerge = true,width = 12D)
    private  String  username;
    @Excel(name = "供应商名称", orderNum = "2", needMerge = true,width = 30D)
    private  String  nickname;
    private  String  password;
    private  Integer state;      //是否被冻结
    private String role;
    @Excel(name = "邮箱地址", orderNum = "3", needMerge = true,width = 20D)
    private String email;
    @Excel(name = "业务联系人名称", orderNum = "4", needMerge = true,width = 15D)
    private String businessName;
    @Excel(name = "业务联系人电话", orderNum = "5", needMerge = true,width = 15D)
    private String businessPhone;
    @Excel(name = "财务联系人名称", orderNum = "6", needMerge = true,width = 15D)
    private String financeName;
    @Excel(name = "财务联系人邮箱", orderNum = "7", needMerge = true,width = 20D)
    private String financeEmail;
    @Excel(name = "财务联系人电话", orderNum = "8", needMerge = true,width = 15D)
    private String financePhone;
    @Excel(name = "银行账户", orderNum = "12", needMerge = true,width = 30D)
    private String bankAccount;
    @Excel(name = "银行名称", orderNum = "11", needMerge = true,width = 20D)
    private String bankName;
    @Excel(name = "退货地址", orderNum = "9", needMerge = true,width = 20D)
    private String backAddress;
    @Excel(name = "退货联系电话", orderNum = "10", needMerge = true,width = 20D)
    private String backPhone;
    private String businessLicense;
    private String taxCertificate;
    private String organizationCertificate;
    private String lastOperator;

    public String getFinanceEmail() {
        return financeEmail;
    }

    public void setFinanceEmail(String financeEmail) {
        this.financeEmail = financeEmail;
    }

    public String getBackAddress() {
        return backAddress;
    }

    public void setBackAddress(String backAddress) {
        this.backAddress = backAddress;
    }

    public String getBackPhone() {
        return backPhone;
    }

    public void setBackPhone(String backPhone) {
        this.backPhone = backPhone;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getFinanceName() {
        return financeName;
    }

    public void setFinanceName(String financeName) {
        this.financeName = financeName;
    }

    public String getFinancePhone() {
        return financePhone;
    }

    public void setFinancePhone(String financePhone) {
        this.financePhone = financePhone;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getTaxCertificate() {
        return taxCertificate;
    }

    public void setTaxCertificate(String taxCertificate) {
        this.taxCertificate = taxCertificate;
    }

    public String getOrganizationCertificate() {
        return organizationCertificate;
    }

    public void setOrganizationCertificate(String organizationCertificate) {
        this.organizationCertificate = organizationCertificate;
    }

    public String getLastOperator() {
        return lastOperator;
    }

    public void setLastOperator(String lastOperator) {
        this.lastOperator = lastOperator;
    }
}
