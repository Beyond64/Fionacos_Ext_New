package com.colin.entity;

/**
 * Class description
 * @version        18/08/28
 * @author         Colin
 */
public class ItemVo {

    /** 供应商码 */
    private Integer cban8;

    /** 供应商名称 */
    private String abalky;

    /** 国际条码 */
    private String cblitm;

    /** 条码描述 */
    private String imdsc1;

    /** 品类 */
    private String chsrp1;

    /** 品类描述 */
    private String chsrp1_desc;

    /** 代购销 */
    private String imprp3;

    /** 品牌 */
    private String brand;

    /** 周转天 */
    private Integer TurnoverDay;

    /** 标准低周转天 */
    private Integer BiaoZhunDiTurnoverDay;

    /** 标准高周转天 */
    private Integer BiaoZhunGaoTurnoverDay;

    public String getChsrp1_desc() {
        return chsrp1_desc;
    }

    public void setChsrp1_desc(String chsrp1_desc) {
        this.chsrp1_desc = chsrp1_desc;
    }

    public String getChsrp1() {
        return chsrp1;
    }

    public void setChsrp1(String chsrp1) {
        this.chsrp1 = chsrp1;
    }

    public Integer getBiaoZhunDiTurnoverDay() {
        return BiaoZhunDiTurnoverDay;
    }

    public void setBiaoZhunDiTurnoverDay(Integer biaoZhunDiTurnoverDay) {
        BiaoZhunDiTurnoverDay = biaoZhunDiTurnoverDay;
    }

    public Integer getBiaoZhunGaoTurnoverDay() {
        return BiaoZhunGaoTurnoverDay;
    }

    public void setBiaoZhunGaoTurnoverDay(Integer biaoZhunGaoTurnoverDay) {
        BiaoZhunGaoTurnoverDay = biaoZhunGaoTurnoverDay;
    }

    public Integer getTurnoverDay() {
        return TurnoverDay;
    }

    public void setTurnoverDay(Integer turnoverDay) {
        TurnoverDay = turnoverDay;
    }

    public Integer getCban8() {
        return cban8;
    }

    public void setCban8(Integer cban8) {
        this.cban8 = cban8;
    }

    public String getAbalky() {
        return abalky;
    }

    public void setAbalky(String abalky) {
        this.abalky = abalky;
    }

    public String getCblitm() {
        return cblitm;
    }

    public void setCblitm(String cblitm) {
        this.cblitm = cblitm;
    }

    public String getImdsc1() {
        return imdsc1;
    }

    public void setImdsc1(String imdsc1) {
        this.imdsc1 = imdsc1;
    }

    public String getImprp3() {
        return imprp3;
    }

    public void setImprp3(String imprp3) {
        this.imprp3 = imprp3;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
