package com.qin.common;

public enum OrderStatusEnum {
    //    0:已取消 10:未付款 20:已付款 30:交易成功 40:交易关闭
    ORDER_CANCELED(0,"订单已取消"),
    ORDER_NO_PAYED(10,"未付款"),
    ORDER_PAYED(20,"已付款"),
    ORDER_SUCCESS(30,"交易成功"),
    ORDER_CLOSED(40,"交易关闭"),

    ;

    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    OrderStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
