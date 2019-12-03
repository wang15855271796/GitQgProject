package com.puyue.www.qiaoge.model.cart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/5.
 */

public class CartCommonGoodsModel {

    public List<Double> amount = new ArrayList<>();
    public List<List<DetailListBean>> detailList = new ArrayList<>();
    public List<Integer> productIdList = new ArrayList<>();

    public static class DetailListBean {
        /**
         * num : 0
         * productCombinationPriceId : 38
         * totalNum : 2
         */

        public int productCombinationPriceId;
        public int totalNum;

        public DetailListBean(int productCombinationPriceId, int totalNum) {
            this.productCombinationPriceId = productCombinationPriceId;
            this.totalNum = totalNum;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("{");
            sb.append("\"productCombinationPriceId\":").append(productCombinationPriceId);
            sb.append(", \"totalNum\":").append(totalNum);
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        sb.append("\"amount\":").append(amount);
        sb.append(", \"detailList\":").append(detailList);
        sb.append(", \"productIdList\":").append(productIdList);
        sb.append('}');
        return sb.toString();
    }
}
