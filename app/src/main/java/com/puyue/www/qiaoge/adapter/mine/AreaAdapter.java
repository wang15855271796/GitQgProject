package com.puyue.www.qiaoge.adapter.mine;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.home.CityChangeModel;

import java.util.List;

/**
 * Created by ${王涛} on 2019/12/19
 */
public class AreaAdapter extends BaseQuickAdapter<CityChangeModel.DataBean.CityNamesBean.AreaNamesBean,BaseViewHolder> {

    public AreaAdapter(int item_citys, List<CityChangeModel.DataBean.CityNamesBean.AreaNamesBean> areaNames) {
        super(item_citys, areaNames);
    }

    @Override
    protected void convert(BaseViewHolder helper, CityChangeModel.DataBean.CityNamesBean.AreaNamesBean item) {
        TextView tv_city = helper.getView(R.id.tv_city);
        tv_city.setText(item.getAreaName());

//        tv_city.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                UserInfoHelper.saveCity(mContext, item.getAreaName());
//
//            }
//        });
    }
}
