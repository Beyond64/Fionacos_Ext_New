package com.colin.entity;

public class OrderDetailVo {
    private String litm;
    private String mcu;
    private Integer IBITM;
    private String IBAITM;
    private String IBSRP3;
    private Integer IBMULT;
    private Integer IBVEND;
    private Integer barCodeCount;       //申请数量
    private Integer ApprovalBarCodeCount;       //审批后数量

    public String getLitm() {
        return litm;
    }

    public void setLitm(String litm) {
        this.litm = litm;
    }

    public String getMcu() {
        return mcu;
    }

    public void setMcu(String mcu) {
        this.mcu = mcu;
    }

    public Integer getIBITM() {
        return IBITM;
    }

    public void setIBITM(Integer IBITM) {
        this.IBITM = IBITM;
    }

    public String getIBAITM() {
        return IBAITM;
    }

    public void setIBAITM(String IBAITM) {
        this.IBAITM = IBAITM;
    }

    public String getIBSRP3() {
        return IBSRP3;
    }

    public void setIBSRP3(String IBSRP3) {
        this.IBSRP3 = IBSRP3;
    }

    public Integer getIBMULT() {
        return IBMULT;
    }

    public void setIBMULT(Integer IBMULT) {
        this.IBMULT = IBMULT;
    }

    public Integer getIBVEND() {
        return IBVEND;
    }

    public void setIBVEND(Integer IBVEND) {
        this.IBVEND = IBVEND;
    }

    public Integer getBarCodeCount() {
        return barCodeCount;
    }

    public void setBarCodeCount(Integer barCodeCount) {
        this.barCodeCount = barCodeCount;
    }

    public Integer getApprovalBarCodeCount() {
        return ApprovalBarCodeCount;
    }

    public void setApprovalBarCodeCount(Integer approvalBarCodeCount) {
        ApprovalBarCodeCount = approvalBarCodeCount;
    }
}
