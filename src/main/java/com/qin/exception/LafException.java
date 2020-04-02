package com.qin.exception;

public class LafException extends RuntimeException {

    private String msg;
    private int id;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LafException( String msg){
        super(msg);
//        this.id=id;
        this.msg=msg;
    }
}
