package com.puyue.www.qiaoge.activity.mine;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${王涛} on 2020/4/1
 */
public class SubAccountOrderActivity extends BaseSwipeActivity {

    @BindView(R.id.rv_sub_account_order)
    RecyclerView rv_sub_account_order;
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_account_order);
    }

    @Override
    public void findViewById() {

    }

    @Override
    public void setViewData() {
        ButterKnife.bind(this);
    }

    @Override
    public void setClickEvent() {

    }
}
