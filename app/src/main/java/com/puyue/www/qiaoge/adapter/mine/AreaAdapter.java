package com.puyue.www.qiaoge.adapter.mine;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;

import java.util.List;

/**
 * Created by ${王涛} on 2019/12/19
 */
public class AreaAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public AreaAdapter(int item_citys, List<String> areaNames) {
        super(item_citys, areaNames);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView tv_city = helper.getView(R.id.tv_city);
        tv_city.setText(item);
    }
}
