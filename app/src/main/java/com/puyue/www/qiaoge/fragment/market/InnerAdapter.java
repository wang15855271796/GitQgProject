package com.puyue.www.qiaoge.fragment.market;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.market.ClassIfyModel;

import java.util.List;

class InnerAdapter extends BaseQuickAdapter<ClassIfyModel.DataBean.SecondClassifyBean,BaseViewHolder> {

    public InnerAdapter(int layoutResId, @Nullable List<ClassIfyModel.DataBean.SecondClassifyBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassIfyModel.DataBean.SecondClassifyBean item) {
        TextView tv_name = helper.getView(R.id.tv_name_second);
        tv_name.setText(item.getName());
        helper.addOnClickListener(R.id.tv_name_second);
    }

    public interface OnEventInnerClickListener {
        void onEventInnerClick();
    }

}
