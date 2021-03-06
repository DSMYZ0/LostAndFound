package com.qin.common;

public enum ResponseCode {

    USERNAME_NOT_EMPTY(1,"用户名不能为空"),
    PASSWORD_NOT_EMPTY(2,"密码不能为空"),
    USERNAME_NOT_EXIST(3,"用户名不存在"),
    PASSWORD_ERROR(4,"密码错误"),
    PARAMETER_NOT_EMPTY(5,"参数不能为空"),
    USERNAME_IS_EXIST(6,"用户名已存在"),
    REGISTER_FAILED(7,"注册失败"),
    NOT_LOGIN(8,"未登录"),
    UPDATE_FAILED(9,"修改失败"),
    ILLEGAL_PARAM(10,"非法参数"),
    ORDER_CREATE_FAILED(11,"订单创建失败"),
    PAY_PARAM_ERROR(12,"支付参数错误"),
    ORDER_NOT_EXIST(13,"订单不存在"),
    ORDER_UPDATE_FAILED(14,"订单更新失败"),
    CAROUSEL_EMPTY(15,"当前无轮播图"),
    POST_FAILED(16,"发布失败"),
    POST_SUCCESSED(17,"发布成功"),
    POST_DELETE_FAILED(18,"删除失败"),
    POST_NOT_EXIST(19,"帖子不存在")
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
