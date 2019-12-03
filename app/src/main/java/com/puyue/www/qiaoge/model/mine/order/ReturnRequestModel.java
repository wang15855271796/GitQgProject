package com.puyue.www.qiaoge.model.mine.order;

import java.util.List;

/**
 * Created by Administrator on 2018/5/11.
 */

public class ReturnRequestModel {

    /**
     * businessType : 1
     * productCombinationPriceIdList : [38,39]
     * productId : 46
     * returnNumList : [1,1]
     */

    public int businessType;
    public int productId;
    public List<Integer> productCombinationPriceIdList;
    public List<Integer> returnNumList;
    public List<String> buyPriceList;

    public ReturnRequestModel(int businessType, int productId, List<Integer> productCombinationPriceIdList, List<Integer> returnNumList, List<String> buyPriceList) {
        this.businessType = businessType;
        this.productId = productId;
        this.productCombinationPriceIdList = productCombinationPriceIdList;
        this.returnNumList = returnNumList;
        this.buyPriceList = buyPriceList;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        sb.append("\"businessType\":").append(businessType);
        sb.append(", \"productId\":").append(productId);
        sb.append(", \"productCombinationPriceIdList\":").append(productCombinationPriceIdList);
        sb.append(", \"returnNumList\":").append(returnNumList);
        sb.append(", \"buyPriceList\":").append(buyPriceList);
        sb.append('}');
        return sb.toString();
    }
}
