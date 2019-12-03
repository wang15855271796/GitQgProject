package com.puyue.www.qiaoge.adapter.mine;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.home.EvaluateGoodImageAdapter;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.model.mine.order.GetEvaDetailModel;
import com.puyue.www.qiaoge.view.StarBarView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ${王文博} on 2019/8/22
 */
public class UserEvaluateAdapter extends BaseQuickAdapter<GetEvaDetailModel.DataBean.ListBean, BaseViewHolder> {
    private ImageView iv;

    public UserEvaluateAdapter(int layoutResId, @Nullable List<GetEvaDetailModel.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetEvaDetailModel.DataBean.ListBean item) {
        StarBarView sbv_star_bar;
        TextView tv_status;
        TextView tv_replay_content;
        TextView edit_item_order_evaluate;
        ImageView iv_add;
        RecyclerView mRecyclerView;
        EvaluateGoodImageAdapter madapter;

        float star;
        helper.setText(R.id.tv_item_order_evaluate_name, item.productName);
        iv = helper.getView(R.id.iv_good);
        tv_replay_content = helper.getView(R.id.tv_replay_content);
        sbv_star_bar = helper.getView(R.id.sbv_star_bar);
        tv_status = helper.getView(R.id.tv_status);
        edit_item_order_evaluate = helper.getView(R.id.edit_item_order_evaluate);
        iv_add = helper.getView(R.id.iv_add);
        mRecyclerView = helper.getView(R.id.recycler);



     /*   String[] split = item.getReplayPic().split(",");

        Log.i("wreataq", "convert: "+split.toString());*/

        if (item.getCommentContent() != null && StringHelper.notEmptyAndNull(item.getCommentContent())) {
            edit_item_order_evaluate.setVisibility(View.VISIBLE);
            edit_item_order_evaluate.setText(item.getCommentContent());
        } else {
            edit_item_order_evaluate.setVisibility(View.GONE);
        }
        Glide.with(mContext).load(item.productUrl).into(iv);

        sbv_star_bar.setStarRating(Float.valueOf(item.getLevel()));
        setStarName(tv_status, Float.valueOf(item.getLevel()));
        sbv_star_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sbv_star_bar.setStarSolid(Float.valueOf(item.getLevel()));

            }
        });
        if (item.getReplayContent() != null && StringHelper.notEmptyAndNull(item.getReplayContent())) {
            tv_replay_content.setVisibility(View.VISIBLE);
            tv_replay_content.setText("翘歌客服:" + item.getReplayContent());
        } else {
            tv_replay_content.setVisibility(View.GONE);
        }

        if (item.getReplayPic() != null && StringHelper.notEmptyAndNull(item.getReplayPic())) {
            List<String> result = Arrays.asList(item.getReplayPic().split(","));
            mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));

            madapter = new EvaluateGoodImageAdapter(R.layout.evalute_image, result);

            mRecyclerView.setAdapter(madapter);
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
