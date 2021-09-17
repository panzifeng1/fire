package com.jyu.fire.vo;

public enum ErrorCode {

    FAIL_UPDATE(400,"更新失败"),
    FAIL_DELETE(400,"删除失败"),
    FAIL_SELECT(400,"查找不到相应的数据"),
    FAIL_INSERT(10007,"添加失败");

    private int code;
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
