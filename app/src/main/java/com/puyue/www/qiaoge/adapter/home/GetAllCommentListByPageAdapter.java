package com.puyue.www.qiaoge.adapter.home;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.model.home.GetAllCommentListByPageModel;
import com.puyue.www.qiaoge.model.home.GetPlaceCommentListByPageModel;
import com.puyue.www.qiaoge.view.StarBarView;

import java.util.Arrays;
import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/24.
 */

public class GetAllCommentListByPageAdapter extends BaseQuickAdapter<GetAllCommentListByPageModel.DataBean.ListBean, BaseViewHolder> {
    private ImageView mIvHeader;
    private TextView mTvReply;




    public GetAllCommentListByPageAdapter(int layoutResId, List<GetAllCommentListByPageModel.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetAllCommentListByPageModel.DataBean.ListBean model) {

      StarBarView sbv_star_bar;
       TextView tv_evaluate_status;

        mIvHeader = (ImageView) helper.getView(R.id.iv_item_goods_evaluation);
        mTvReply = (TextView) helper.getView(R.id.tv_item_goods_evaluation_reply);
        sbv_star_bar = helper.getView(R.id.sbv_star_bar);
        tv_evaluate_status = helper.getView(R.id.tv_evaluate_status);
        RecyclerView recyclerView_image;
        EvaluateGoodImageAdapter madapter;

        recyclerView_image = helper.getView(R.id.recyclerView_image);
//
//    Glide.with(mContext).load(model.header).asBitmap().centerCrop().into(new BitmapImageViewTarget(mIvHeader) {
//            @Override
//            protected void setResource(Bitmap resource) {
//                RoundedBitmapDrawable circularBitmapDrawable =
//                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
//                circularBitmapDrawable.setCircular(true);
//                mIvHeader.setImageDrawable(circularBitmapDrawable);
//            }
//        });
        helper.setText(R.id.tv_item_goods_evaluation_number, model.customerPhone);
        helper.setText(R.id.tv_item_goods_evaluation_time, model.commentDate);
        helper.setText(R.id.tv_item_goods_evaluation_content, model.commentContent);
        if (!StringHelper.notEmptyAndNull(model.replayContent)) {
            mTvReply.setVisibility(View.GONE);
        } else {
            mTvReply.setVisibility(View.VISIBLE);
            mTvReply.setText("翘歌客服: "+model.replayContent);
        }


        if (model.level != null && StringHelper.notEmptyAndNull(model.level)) {
            sbv_star_bar.setVisibility(View.VISIBLE);
            tv_evaluate_status.setVisibility(View.VISIBLE);

            if (model.level.equals("5")) {
                sbv_star_bar.setStarRating(5.0f);
                setStarName(tv_evaluate_status, 5.0f);


            } else if (model.level.equals("4")) {
                sbv_star_bar.setStarRating(4.0f);
                setStarName(tv_evaluate_status, 4.0f);


            } else if (model.level.equals("3")) {
                sbv_star_bar.setStarRating(3.0f);
                setStarName(tv_evaluate_status, 3.0f);


            } else if (model.level.equals("2")) {
                sbv_star_bar.setStarRating(2.0f);
                setStarName(tv_evaluate_status, 2.0f);


            } else if (model.level.equals("1")) {
                sbv_star_bar.setStarRating(1.0f);
                setStarName(tv_evaluate_status, 1.0f);


            }


        } else {
            sbv_star_bar.setVisibility(View.GONE);
            tv_evaluate_status.setVisibility(View.GONE);
        }
        sbv_star_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sbv_star_bar.setStarSolid(Float.parseFloat(model.level));
            }
        });


        if (model.pictures != null && StringHelper.notEmptyAndNull(model.pictures)) {
            List<String> result = Arrays.asList(model.pictures.split(","));
            recyclerView_image.setLayoutManager(new GridLayoutManager(mContext, 3));

            madapter = new EvaluateGoodImageAdapter(R.layout.evalute_image, result);

            recyclerView_image.setAdapter(madapter);

            madapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    AppHelper.showPhotoDetailDialog(mContext, result, position);
                }
            });

        }




    }


    /**
     * 设置星星文字
     */
    private void setStarName(TextView tv_content, float star_num) {
        if (star_num == 5.0f) {
            tv_content.setText("很满意");

        } else if (star_num == 4.0f) {
            tv_content.setText("满意");

        } else if (star_num == 3.0f) {
            tv_content.setText("一般");

        } else if (star_num == 2.0f) {
            tv_content.setText("不满意");

        } else if (star_num == 1.0f) {
            tv_content.setText("非常差");

        }

    }
}