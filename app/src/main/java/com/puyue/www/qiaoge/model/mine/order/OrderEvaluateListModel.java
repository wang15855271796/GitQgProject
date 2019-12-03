package com.puyue.www.qiaoge.model.mine.order;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/10.
 */

public class OrderEvaluateListModel implements Serializable {
    public int productId;
    public int businessType;
    public String name;
    public String picUrl;

    public  String star;

    public String returnPicPath;

    public String getReturnPicPath() {
        return returnPicPath;
    }

    public void setReturnPicPath(String returnPicPath) {
        this.returnPicPath = returnPicPath;
    }

    public OrderEvaluateListModel(int productId, int businessType, String name, String picUrl, String star, String returnPicPath) {
        this.productId = productId;
        this.businessType = businessType;
        this.name = name;
        this.picUrl = picUrl;
        this.star = star;
        this.returnPicPath =returnPicPath;

    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}
