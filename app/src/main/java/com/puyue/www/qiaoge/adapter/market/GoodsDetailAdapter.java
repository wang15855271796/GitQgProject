package com.puyue.www.qiaoge.adapter.market;

import android.graphics.Color;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.market.GoodsDetailModel;
import com.puyue.www.qiaoge.model.mine.MineWalletModel;
import com.puyue.www.qiaoge.view.GlideModel;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/13.
 */

public class GoodsDetailAdapter extends BaseMultiItemQuickAdapter<GoodsDetailModel, BaseViewHolder> {

    public GoodsDetailAdapter(List data) {
        super(data);
        addItemType(GoodsDetailModel.typeTv, R.layout.item_goods_textview);
        addItemType(GoodsDetailModel.typeIv, R.layout.item_goods_imageview);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsDetailModel item) {
        switch (helper.getItemViewType()) {
            case GoodsDetailModel.typeTv:
                helper.setText(R.id.tv_item_goods, item.content);
                break;
            case GoodsDetailModel.typeIv:
                GlideModel.disPlayError(mContext,item.content,helper.getView(R.id.iv_item_goods));
              //  Glide.with(mContext).load(item.content).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into((ImageView) helper.getView(R.id.iv_item_goods));
                break;
        }
    }
}