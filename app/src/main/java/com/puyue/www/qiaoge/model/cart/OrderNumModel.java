package com.puyue.www.qiaoge.model.cart;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/25.
 */

public class OrderNumModel implements MultiItemEntity {
    public static final int lineType = 1;
    public static final int textType = 2;
    public int type;
    public String detailDesc;
    public String totalNum;

    @Override
    public int getItemType() {
        return type;
    }
}
