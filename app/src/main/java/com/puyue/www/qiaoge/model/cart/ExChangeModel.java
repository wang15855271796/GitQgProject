package com.puyue.www.qiaoge.model.cart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${王涛} on 2020/7/8
 */
public class ExChangeModel {


    public static class DetailListBean {
        public List<String> list = new ArrayList<>();
        public List<String> expend = new ArrayList<>();
        public int amount;
        public int num;
//        public int expend;

        public DetailListBean(List<String> list, int num,List<String> expend) {
            this.list = list;
            this.num = num;
            this.expend = expend;
        }

        @Override
        public String toString() {
            return "DetailListBean{" +
                    "list=" + list +
                    ", expend=" + expend +
                    ", amount=" + amount +
                    ", num=" + num +
                    '}';
        }
    }
}
