package com.puyue.www.qiaoge.activity.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${王涛} on 2020/6/4
 */
public class AnnulActivity extends BaseSwipeActivity {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_limit)
    TextView tv_limit;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    String phone;
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_annule);
    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);
        if(getIntent().getStringExtra("phone")!=null) {
            phone = getIntent().getStringExtra("phone");
        }
        tv_phone.setText(phone.substring(0,3)+"****"+phone.substring(7,phone.length()));

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_limit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(mActivity,ReasonActivity.class);
                intent.putExtra("phone",phone);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setViewData() {

    }

    @Override
    public void setClickEvent() {

    }
}
