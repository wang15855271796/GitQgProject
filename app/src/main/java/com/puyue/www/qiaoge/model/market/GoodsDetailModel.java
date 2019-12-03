package com.puyue.www.qiaoge.model.market;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/13.
 */

public class GoodsDetailModel implements MultiItemEntity {
    public static final int typeTv = 1;
    public static final int typeIv = 2;

    private int type;
    public String content;

    public GoodsDetailModel(int itemType) {
        this.type = itemType;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
