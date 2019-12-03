package com.puyue.www.qiaoge.adapter.mine;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.helper.GlideRoundTransform;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.collection.CollectionListModel;
import com.puyue.www.qiaoge.view.GlideModel;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/10.
 */

public class MyCollectionAdapter extends BaseQuickAdapter<CollectionListModel.DataBean, BaseViewHolder> {
    public Map<Integer, Boolean> isCheck;
    private OnEventClickListener mOnEventClickListener;
    private ImageView imageViewStatus;

    public MyCollectionAdapter(int layoutResId, @Nullable List<CollectionListModel.DataBean> data, Map<Integer, Boolean> isCheck) {
        super(layoutResId, data);
        this.isCheck = isCheck;
    }

    public interface OnEventClickListener {
        void onEventClick(View view, int position, String type);

        void onEventLongClick(View view, int position, String type);
    }

    public void setOnItemClickListener(OnEventClickListener onEventClickListener) {
        mOnEventClickListener = onEventClickListener;
    }

    @Override
    protected void convert(final BaseViewHolder helper, CollectionListModel.DataBean item) {
        imageViewStatus = helper.getView(R.id.iv_item_my_collection_sold_out);
        if (StringHelper.notEmptyAndNull(item.getImgUrl())) {
            GlideModel.displayTransForms(mContext,item.getImgUrl(),helper.getView(R.id.iv_item_my_collection_img));
        }
        helper.setChecked(R.id.cb_item_my_collection, isCheck.get(helper.getAdapterPosition()));
        helper.setText(R.id.tv_item_my_collection_title, item.getProductName());
        helper.setText(R.id.tv_item_my_collection_price, item.getMinMaxPrice());
        helper.setText(R.id.tv_item_my_collection_volume, item.getMonthSalesVolume());


        if (item.getFlag()==0){
            imageViewStatus.setImageResource(R.mipmap.ic_collection_out);
            imageViewStatus.setVisibility(View.VISIBLE);

        }else if (item.getFlag()==1){
            imageViewStatus.setVisibility(View.GONE);

        }

        ((FrameLayout) helper.getView(R.id.fl_item_collection_check)).setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                mOnEventClickListener.onEventClick(view, helper.getAdapterPosition(), "check");
            }
        });

        ((RelativeLayout) helper.getView(R.id.rl_item_collection_data)).setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                mOnEventClickListener.onEventClick(view, helper.getAdapterPosition(), "jump");
            }
        });
    }
}
