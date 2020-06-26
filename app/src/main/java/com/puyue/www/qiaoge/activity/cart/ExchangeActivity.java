package com.puyue.www.qiaoge.activity.cart;

import android.os.Bundle;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;

import butterknife.ButterKnife;

/**
 * Created by ${王涛} on 2020/6/26
 */
public class ExchangeActivity extends BaseSwipeActivity {
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_exchange);
    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);
    }

    @Override
    public void setViewData() {

    }

    @Override
    public void setClickEvent() {

    }
}
