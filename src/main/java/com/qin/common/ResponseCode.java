package com.qin.common;

public enum ResponseCode {

    USERNAME_NOT_EMPTY(1,"用户名不能为空"),
    PASSWORD_NOT_EMPTY(2,"密码不能为空"),
    USERNAME_NOT_EXIST(3,"用户名不存在"),
    PARAMETER_NOT_EMPTY(5,"参数不能为空"),
    USERNAME_IS_EXIST(6,"用户名已存在"),
    REGISTER_FAIL(7,"注册失败"),
    NOT_LOGIN(8,"未登录"),
    UPDATE_FAIL(9,"修改失败"),


    PASSWORD_ERROR(4,"密码错误")

    ;
    private int code;
    private String msg;

    ResponseCode(int code,String msg){
        this.code=code;
        this.msg=msg;
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
