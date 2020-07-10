package com.puyue.www.qiaoge.model.cart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${王涛} on 2020/7/8
 */
public class ExChangeModel {


    public static class DetailListBean {
        public List<Double> list = new ArrayList<>();
        public List<Double> expend = new ArrayList<>();
        public int amount;
        public int num;
//        public int expend;

        public DetailListBean(List<Double> list, int num,List<Double> expend) {
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

//        @Override
//        public String toString() {
//            final StringBuffer sb = new StringBuffer("{")StringBuffer;
//            sb.append("\"productCombinationPriceId\":").append(productCombinationPriceId);
//            sb.append(", \"totalNum\":").append(totalNum);
//            sb.append('}');
//            return sb.toString();
//        }
    }
}
