package com.puyue.www.qiaoge.activity.cart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${王涛} on 2020/6/4
 */
public class CompetActivity extends BaseSwipeActivity {
    @BindView(R.id.rl_cancle)
    RelativeLayout rl_cancle;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    String phone;
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {

        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_compet);
    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);
        if (getIntent().getStringExtra("phone") != null) {
            phone = getIntent().getStringExtra("phone");
        }

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rl_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity,AnnulActivity.class);
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
