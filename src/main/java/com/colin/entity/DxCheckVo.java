package com.colin.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * Class description
 * @version        18/09/04
 * @author         Colin
 */
@ExcelTarget("DxCheckVo")
public class DxCheckVo {
    @Excel(name = "供应商",orderNum = "1")
    private String gysId;
    @Excel(name = "供应商名称",orderNum = "2")
    private String gysName;
    @Excel(name = "商品编码",orderNum = "3")
    private String barcode;
    @Excel(name = "商品描述",orderNum = "4")
    private String barcodeDesc;
    @Excel(name = "结算数量",orderNum = "5")
    private String jieSuanShuLiang;
    @Excel(name = "不含税单价",orderNum = "6")
    private String buHanShuiDanJia;
    @Excel(name = "含税单价",orderNum = "7")
    private String hanShuiDanJia;
    @Excel(name = "销售金额",orderNum = "8")
    private String xiaoShouJinE;
    @Excel(name = "结算金额",orderNum = "9")
    private String jieSuanJinE;
    @Excel(name = "POS零售价",orderNum = "10")
    private String posLingShouJia;


    public String getGysId() {
        return gysId;
    }

    public void setGysId(String gysId) {
        this.gysId = gysId;
    }

    public String getGysName() {
        return gysName;
    }

    public void setGysName(String gysName) {
        this.gysName = gysName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBarcodeDesc() {
        return barcodeDesc;
    }

    public void setBarcodeDesc(String barcodeDesc) {
        this.barcodeDesc = barcodeDesc;
    }

    public String getJieSuanShuLiang() {
        return jieSuanShuLiang;
    }

    public void setJieSuanShuLiang(String jieSuanShuLiang) {
        this.jieSuanShuLiang = jieSuanShuLiang;
    }

    public String getBuHanShuiDanJia() {

        return buHanShuiDanJia;
    }

    public void setBuHanShuiDanJia(String buHanShuiDanJia) {
        this.buHanShuiDanJia = buHanShuiDanJia;
    }

    public String getHanShuiDanJia() {
        return hanShuiDanJia;
    }

    public void setHanShuiDanJia(String hanShuiDanJia) {
        this.hanShuiDanJia = hanShuiDanJia;
    }

    public String getXiaoShouJinE() {
        return xiaoShouJinE;
    }

    public void setXiaoShouJinE(String xiaoShouJinE) {
        this.xiaoShouJinE = xiaoShouJinE;
    }

    public String getJieSuanJinE() {
        return jieSuanJinE;
    }

    public void setJieSuanJinE(String jieSuanJinE) {
        this.jieSuanJinE = jieSuanJinE;
    }

    public String getPosLingShouJia() {
        return posLingShouJia;
    }

    public void setPosLingShouJia(String posLingShouJia) {
        this.posLingShouJia = posLingShouJia;
    }

}


//~ Formatted by Jindent --- http://www.jindent.com
