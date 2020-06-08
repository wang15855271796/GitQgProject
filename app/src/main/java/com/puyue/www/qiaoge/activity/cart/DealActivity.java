package com.puyue.www.qiaoge.activity.cart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.home.CancleAPI;
import com.puyue.www.qiaoge.api.home.CityChangeAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.model.CancleModel;
import com.puyue.www.qiaoge.model.home.CityChangeModel;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2020/6/4
 */
public class DealActivity extends BaseSwipeActivity {
    Handler handler = new Handler();
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_deal);
    }

    @Override
    public void findViewById() {
    }


    @Override
    public void setViewData() {

    }

    @Override
    public void setClickEvent() {

    }
}
