package com.puyue.www.qiaoge.model.mine;

/**
 * Created by Administrator on 2018/4/16.
 */

public class ReturnGoodsModel {
    public String img;
    public String name;
    public String standard;
    public String price;
    public String volume;
    public String stock;
    public int amount;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ReturnGoodsModel(String img, String name, String standard, String price, String volume, String stock, int amount) {
        this.img = img;
        this.name = name;
        this.standard = standard;
        this.price = price;
        this.volume = volume;
        this.stock = stock;
        this.amount = amount;
    }
}
