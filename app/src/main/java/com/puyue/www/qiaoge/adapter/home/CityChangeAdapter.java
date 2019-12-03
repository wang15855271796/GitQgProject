package com.puyue.www.qiaoge.adapter.home;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.home.CityChangeModel;

import java.time.format.TextStyle;
import java.util.List;

/**
 * Created by ${王文博} on 2019/8/8
 */
public class CityChangeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

private TextView tv_city;

    public CityChangeAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        tv_city=helper.getView(R.id.tv_city);
        tv_city.setText(item);
    }
}
