package com.qin.common;

public enum TradeStatusEnum {

    WAIT_BUYER_PAY("WAIT_BUYER_PAY",10),

    TRADE_CLOSED("TRADE_CLOSED",40),

    TRADE_SUCCESS("TRADE_SUCCESS",20),

    TRADE_FINISHED("TRADE_FINISHED",30)
            ;

    private String trade;

    TradeStatusEnum(String trade, int code) {
        this.trade = trade;
        this.code = code;
    }

    private int code;

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static int codeof(String trade){
        for (TradeStatusEnum tradeStatusEnum:values()){
            if(tradeStatusEnum.getTrade().equals(trade))
                return tradeStatusEnum.getCode();
        }
    return -1;
    }
}
