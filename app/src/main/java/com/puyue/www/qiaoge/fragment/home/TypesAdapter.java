package com.puyue.www.qiaoge.fragment.home;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.SelectionGoodActivity;
import com.puyue.www.qiaoge.api.home.IndexInfoModel;

import java.util.List;

/**
 * Created by ${王涛} on 2020/1/4
 */
public class TypesAdapter extends BaseQuickAdapter<IndexInfoModel.DataBean.ClassifyListBean,BaseViewHolder> {

    private ImageView iv_pic;

    public TypesAdapter(int layoutResId, @Nullable List<IndexInfoModel.DataBean.ClassifyListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, IndexInfoModel.DataBean.ClassifyListBean classifyListBean) {
        iv_pic = baseViewHolder.getView(R.id.iv_pic);
        Glide.with(mContext).load(classifyListBean.getImg()).into(iv_pic);

        iv_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,SelectionGoodActivity.class);
                intent.putExtra("productId",classifyListBean.getId());
                intent.putExtra("title",classifyListBean.getTitle());
                mContext.startActivity(intent);
            }
        });
    }
}
