package com.qin.common;
/*
* 返回前端的高复用响应对象
* */

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)  //避免值为null的属性被序列化
public class ServerResponse <T>{
    private int state;  //状态,0:调用成功
    private T data;     //state=0,将返回数据封装到data
    private String msg; //提示信息

    private ServerResponse(){}

    private ServerResponse(int state){
        this.state=state;
    }

    private ServerResponse(int state,T data){
        this.state=state;
        this.data=data;
    }

    private ServerResponse(int state,T data,String msg){
        this.state=state;
        this.data=data;
        this.msg=msg;
    }

    @JsonIgnore //避免作为属性被序列化
    public boolean isSuccess(){
        return this.state==0;
    }


    public static ServerResponse createServerResponseBySuccess(){
        return new ServerResponse(0);
    }

    public static <T>ServerResponse createServerResponseBySuccess(T data){
        return new ServerResponse(0,data);
    }

    public static <T>ServerResponse createServerResponseBySuccess(T data,String msg){
        return new ServerResponse(0,data,msg);
    }

    public static ServerResponse createServerResponseByFail(int state){
        return new ServerResponse(state);
    }

    public static ServerResponse createServerResponseByFail(int state,String msg){
        return new ServerResponse(-1,null,msg);
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
