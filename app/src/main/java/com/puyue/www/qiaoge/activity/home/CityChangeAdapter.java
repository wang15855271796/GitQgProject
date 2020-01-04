package com.puyue.www.qiaoge.activity.home;

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
public class CityChangeAdapter extends BaseSectionQuickAdapter<CityChangeModel, BaseViewHolder> {

    private TextView tv_province;
    List<CityChangeModel.DataBean> data;
    private FlowLayout fl_container;
    private TextView tv_city;
    private String cityName;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public CityChangeAdapter(int layoutResId, int sectionHeadResId, List<CityChangeModel> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, CityChangeModel item) {
        helper.setText(R.id.tv_province,item.header);

    }

    @Override
    protected void convert(BaseViewHolder helper, CityChangeModel item) {
//        Log.d("sssssssss.........",item.getProvinceName());
        Log.d("sssssssss.....",item+"");
//        helper.setText(R.id.tv_city,item.getProvinceName()+"");
    }

//    public CityChangeAdapter(int layoutResId, @Nullable List<CityChangeModel.DataBean> data) {
//        super(layoutResId, data);
//        this.data = data;
//    }

//    @Override
//    protected void convert(BaseViewHolder helper, CityChangeModel.DataBean item) {
//        tv_province=helper.getView(R.id.tv_province);
//        tv_province.setText(item.getProvinceName());
//        tv_city = helper.getView(R.id.tv_city);
//        fl_container = helper.getView(R.id.fl_container);
//        CitysAdapter citysAdapter = new CitysAdapter(mContext,item.getCityNames());
////        for (int i = 0; i <item.getCityNames().size() ; i++) {
////            cityName = item.getCityNames().get(i).getCityName();
////            Log.d("sssdddd...",cityName);
////            tv_city.setText(cityName);
////        }
//
//        citysAdapter.setOnItemClickListeners(new CitysAdapter.OnEventClickListener() {
//            @Override
//            public void onEventClick(int position, View view) {
//                citysAdapter.selectPosition(position);
//            }
//        });
//        fl_container.setAdapter(citysAdapter);
//
//    }
}
