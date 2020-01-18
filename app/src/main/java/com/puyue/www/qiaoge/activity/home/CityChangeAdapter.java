package com.puyue.www.qiaoge.activity.home;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lljjcoder.style.citythreelist.CityAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.activity.home.SpecialGoodDetailActivity;
import com.puyue.www.qiaoge.adapter.market.MarketInnerAdapter;
import com.puyue.www.qiaoge.adapter.market.MarketSpecAdapter;
import com.puyue.www.qiaoge.adapter.mine.CitysAdapter;
import com.puyue.www.qiaoge.api.home.GetProductDetailAPI;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.dialog.CityDialog;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.OnItemClickListener;
import com.puyue.www.qiaoge.model.home.CityChangeModel;
import com.puyue.www.qiaoge.model.home.ExchangeProductModel;
import com.puyue.www.qiaoge.view.FlowLayout;

import java.time.format.TextStyle;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王文博} on 2019/8/8
 * 选择省
 */
public class CityChangeAdapter extends BaseQuickAdapter<CityChangeModel.DataBean, BaseViewHolder> {

    private TextView tv_province;
    List<CityChangeModel.DataBean> data;
    private FlowLayout fl_container;
    private TextView tv_city;
    private String cityName;
    Onclick onclick;
    CityDialog cityDialog;
    public List<CityChangeModel.DataBean.CityNamesBean.AreaNamesBean> areaNames;
    Activity mActivity;
    public CityChangeAdapter(Activity mActivity, int layoutResId, @Nullable List<CityChangeModel.DataBean> data, Onclick onclick) {
        super(layoutResId, data);
        this.data = data;
        this.onclick = onclick;
        this.mActivity = mActivity;
    }



    @Override
    protected void convert(BaseViewHolder helper, CityChangeModel.DataBean item) {
        tv_province=helper.getView(R.id.tv_province);
        tv_province.setText(item.getProvinceName());
        fl_container = helper.getView(R.id.fl_container);

        CitysAdapter citysAdapter = new CitysAdapter(mContext,item.getCityNames());
        citysAdapter.setOnItemClickListeners(new CitysAdapter.OnEventClickListener() {
            @Override
            public void onEventClick(int position, View view) {
                citysAdapter.selectPosition(position);
                areaNames = item.getCityNames().get(position).getAreaNames();
                UserInfoHelper.saveCity(mContext, item.getCityNames().get(position).getCityName());
                cityDialog = new CityDialog(mActivity,item.getCityNames().get(position).getAreaNames()) {
                    @Override
                    public void Confirm() {
                        mActivity.finish();
                    }
                };
                cityDialog.show();
            }
        });

        fl_container.setAdapter(citysAdapter);

    }

    public interface Onclick {
        void addDialog(int position, List<CityChangeModel.DataBean.CityNamesBean.AreaNamesBean> areaNames, View view);
    }
}
