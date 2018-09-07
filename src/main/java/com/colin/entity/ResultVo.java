package com.colin.entity;

public class ResultVo {
    private String state;
    private String msg;

    public ResultVo() {
    }

    public ResultVo(String state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
