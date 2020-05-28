package com.puyue.www.qiaoge.event;

/**
 * Created by ${王涛} on 2020/5/28
 */
public class SetAmountMaxsEvent {
    public String amount;
    public SetAmountMaxsEvent(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "SetAmountMaxsEvent{" +
                "amount='" + amount + '\'' +
                '}';
    }
}
