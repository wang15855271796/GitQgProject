package com.puyue.www.qiaoge.model.home;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/24.
 */

public class ChoiceSpecModel {
    public int productCombinationPriceId;
    public int totalNum;
    public int num;
    public String productUnitName;

    @Override
    public String toString() {
        return "ChoiceSpecModel{" +
                "productCombinationPriceId=" + productCombinationPriceId +
                ", totalNum=" + totalNum +
                ", num=" + num +
                ", productUnitName='" + productUnitName + '\'' +
                '}';
    }
}
