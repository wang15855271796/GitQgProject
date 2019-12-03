package com.puyue.www.qiaoge.adapter.cart;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.SearchReasultActivity;
import com.puyue.www.qiaoge.constant.AppConstant;

import java.util.List;

/**
 * Created by ${王涛} on 2019/9/27
 */
public class RecommendAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

    public RecommendAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView tv_name = helper.getView(R.id.tv_name);
        tv_name.setText(item);
        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,SearchReasultActivity.class);
                intent.putExtra(AppConstant.SEARCHWORD,item);
                mContext.startActivity(intent);
            }
        });
    }
}
