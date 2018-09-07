package com.colin.entity;

public class TurnoverDayVo {
    private String category_code;
    private String category_desc;
    private Integer dx_low_turnover;
    private Integer gx_low_turnover;
    private Integer dx_high_turnover;
    private Integer gx_high_turnover;

    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }

    public String getCategory_desc() {
        return category_desc;
    }

    public void setCategory_desc(String category_desc) {
        this.category_desc = category_desc;
    }

    public Integer getDx_low_turnover() {
        return dx_low_turnover;
    }

    public void setDx_low_turnover(Integer dx_low_turnover) {
        this.dx_low_turnover = dx_low_turnover;
    }

    public Integer getGx_low_turnover() {
        return gx_low_turnover;
    }

    public void setGx_low_turnover(Integer gx_low_turnover) {
        this.gx_low_turnover = gx_low_turnover;
    }

    public Integer getDx_high_turnover() {
        return dx_high_turnover;
    }

    public void setDx_high_turnover(Integer dx_high_turnover) {
        this.dx_high_turnover = dx_high_turnover;
    }

    public Integer getGx_high_turnover() {
        return gx_high_turnover;
    }

    public void setGx_high_turnover(Integer gx_high_turnover) {
        this.gx_high_turnover = gx_high_turnover;
    }
}
