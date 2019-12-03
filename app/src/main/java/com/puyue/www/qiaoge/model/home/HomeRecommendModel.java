package com.puyue.www.qiaoge.model.home;

/**
 * Created by Administrator on 2018/4/9.
 */

public class HomeRecommendModel {
    public String img;
    public String volume;
    public String content;
    public String price;
    public int hour;
    public int min;
    public int second;

    public HomeRecommendModel(String img, String volume, String content, String price, int hour, int min, int second) {
        this.img = img;
        this.volume = volume;
        this.content = content;
        this.price = price;
        this.hour = hour;
        this.min = min;
        this.second = second;
    }
}
