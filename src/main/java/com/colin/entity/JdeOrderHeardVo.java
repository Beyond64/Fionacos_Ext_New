package com.colin.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;


public class JdeOrderHeardVo {
    private Long pddoco;
    private String pddcto;
    private Long pdmcu;
    private String mcdc;
    private Integer litmCount;
    private String pdtrdj;
    private String pdpddj;
    private Integer pduorg;
    private Double pdurat;
    private Integer pdlttr;
    private Integer pdnxtr;

    public Long getPddoco() {
        return pddoco;
    }

    public void setPddoco(Long pddoco) {
        this.pddoco = pddoco;
    }

    public String getPddcto() {
        return pddcto;
    }

    public void setPddcto(String pddcto) {
        this.pddcto = pddcto;
    }

    public Long getPdmcu() {
        return pdmcu;
    }

    public void setPdmcu(Long pdmcu) {
        this.pdmcu = pdmcu;
    }

    public String getMcdc() {
        return mcdc;
    }

    public void setMcdc(String mcdc) {
        this.mcdc = mcdc;
    }

    public Integer getLitmCount() {
        return litmCount;
    }

    public void setLitmCount(Integer litmCount) {
        this.litmCount = litmCount;
    }

    public String getPdtrdj() {
        return pdtrdj;
    }

    public void setPdtrdj(String pdtrdj) {
        this.pdtrdj = pdtrdj;
    }

    public String getPdpddj() {
        return pdpddj;
    }

    public void setPdpddj(String pdpddj) {
        this.pdpddj = pdpddj;
    }

    public Integer getPduorg() {
        return pduorg;
    }

    public void setPduorg(Integer pduorg) {
        this.pduorg = pduorg;
    }

    public Double getPdurat() {
        return pdurat;
    }

    public void setPdurat(Double pdurat) {
        this.pdurat = pdurat;
    }

    public Integer getPdlttr() {
        return pdlttr;
    }

    public void setPdlttr(Integer pdlttr) {
        this.pdlttr = pdlttr;
    }

    public Integer getPdnxtr() {
        return pdnxtr;
    }

    public void setPdnxtr(Integer pdnxtr) {
        this.pdnxtr = pdnxtr;
    }
}
