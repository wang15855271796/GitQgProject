package com.puyue.www.qiaoge.adapter.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.model.home.GetCommentListByPageModel;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/23.
 */

public class GetCommentListByPageAdapter extends BaseQuickAdapter<GetCommentListByPageModel.DataBean.ListBean, BaseViewHolder> {
    private ImageView mIvHeader;
    private TextView mTvReply;

    public GetCommentListByPageAdapter(int layoutResId, List<GetCommentListByPageModel.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetCommentListByPageModel.DataBean.ListBean model) {
        mIvHeader = (ImageView) helper.getView(R.id.iv_item_goods_evaluation);
        mTvReply = (TextView) helper.getView(R.id.tv_item_goods_evaluation_reply);
//        Glide.with(mContext).load(model.header).asBitmap().centerCrop().into(new BitmapImageViewTarget(mIvHeader) {
//            @Override
//            protected void setResource(Bitmap resource) {
//                RoundedBitmapDrawable circularBitmapDrawable =
//                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
//                circularBitmapDrawable.setCircular(true);
//                mIvHeader.setImageDrawable(circularBitmapDrawable);
//            }
//        });
        helper.setText(R.id.tv_item_goods_evaluation_number, model.customerName);
        helper.setText(R.id.tv_item_goods_evaluation_time, model.commentDate);
        helper.setText(R.id.tv_item_goods_evaluation_content, model.commentContent);
        if (!StringHelper.notEmptyAndNull(model.replayContent)) {
            mTvReply.setVisibility(View.GONE);
        } else {
            mTvReply.setVisibility(View.VISIBLE);
            mTvReply.setText(model.replayContent);
        }

    }
}