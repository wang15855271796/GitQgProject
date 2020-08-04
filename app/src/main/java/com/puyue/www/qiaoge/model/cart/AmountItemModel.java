package com.puyue.www.qiaoge.model.cart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${王涛} on 2020/7/29
 */
public class AmountItemModel {

//    public AmountItemModel(String amount) {
//        this.amount = amount;
//    }

    public List<String> amountList = new ArrayList<>();
    public static class DetailListBean {
        public String amount;

        public DetailListBean(String amount) {
            this.amount = amount;
        }

    }

    public String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String num) {
        this.amount = num;
    }

    @Override
    public String toString() {
        return "AmountItemModel{" +
                "amountList=" + amountList +
                ", amount='" + amount + '\'' +
                '}';
    }
}
