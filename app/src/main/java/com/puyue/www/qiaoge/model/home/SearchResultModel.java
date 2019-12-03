package com.puyue.www.qiaoge.model.home;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/16.
 */

public class SearchResultModel {
    public String goodsImg;
    public String name;
    public String specification;
    public String price;
    public String sales;
    public String amount;
    public boolean marketType;

    public SearchResultModel(String goodsImg,String name, String specification, String price, String sales, String amount, boolean marketType) {
        this.goodsImg = goodsImg;
        this.name = name;
        this.specification = specification;
        this.price = price;
        this.sales = sales;
        this.amount = amount;
        this.marketType = marketType;
    }
}
