package com.puyue.www.qiaoge.activity.cart;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.SpannedString;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.coupons.MyCouponsActivity;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.event.ExBackEvent;
import com.puyue.www.qiaoge.helper.StringSpecialHelper;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${王涛} on 2020/7/13
 */
public class PaysResultActivity  extends BaseSwipeActivity {
    @BindView(R.id.tv_look)
    TextView tv_look;
    @BindView(R.id.tv_desc)
    TextView tv_desc;
    @BindView(R.id.tv_back)
    TextView tv_back;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_pays_result);
    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);
        String s = tv_desc.getText().toString();
        SpannableStringBuilder spannableStringBuilder = StringSpecialHelper.buildSpanColorStyle(s, 4, 2, Color.parseColor("#ff5000"));
        tv_desc.setText(spannableStringBuilder);
        tv_look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyCouponsActivity.getIntent(mActivity, MyCouponsActivity.class));
                finish();
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_back.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },3000);

        EventBus.getDefault().post(new ExBackEvent());
    }

    @Override
    public void setViewData() {

    }

    @Override
    public void setClickEvent() {

    }
}
