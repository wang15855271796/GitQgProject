package com.puyue.www.qiaoge.activity.cart;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.HomeActivity;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.model.CancleModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${王涛} on 2020/6/4
 */
public class ErrorActivity extends BaseSwipeActivity {
    @BindView(R.id.tv_commit)
    TextView tv_commit;
    @BindView(R.id.tv_cancle)
    TextView tv_cancle;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.iv4)
    ImageView iv4;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    private CancleModel.DataBean data;
    String reason;
    String phone;
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {

        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_error);
    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);
        if(getIntent().getStringExtra("reason")!=null) {
            reason = getIntent().getStringExtra("reason");

        }

        if(getIntent().getStringExtra("phone")!=null) {
            phone = getIntent().getStringExtra("phone");

        }

        data = (CancleModel.DataBean)getIntent().getSerializableExtra("data");
        if(data!=null) {
            if(data.getWeekOrderNum()==0) {
                iv1.setImageResource(R.mipmap.icon_green);
            }else {
                iv1.setImageResource(R.mipmap.icon_red);
            }

            if(data.getNoOrderNum()==0) {
                iv2.setImageResource(R.mipmap.icon_green);
            }else {
                iv2.setImageResource(R.mipmap.icon_red);
            }

            if(data.getSubUserNum()==0) {
                iv3.setImageResource(R.mipmap.icon_green);
            }else {
                iv3.setImageResource(R.mipmap.icon_red);
            }

            if(data.getBalance()==0.0) {
                iv4.setImageResource(R.mipmap.icon_green);
            }else {
                iv4.setImageResource(R.mipmap.icon_red);
            }

            if(data.getDeductNum()==0) {
                tv1.setText(0+"");
            }else {
                tv1.setText(data.getDeductNum()+"");
            }

            if(data.getPoint()==0) {
                tv2.setText("0");
            }else {
                tv2.setText(data.getPoint()+"");
            }
        }

        if(data.getWeekOrderNum()==0&&data.getBalance()==0.0&&data.getSubUserNum()==0&&data.getNoOrderNum()==0) {
            tv_commit.setVisibility(View.GONE);
            tv_cancle.setVisibility(View.VISIBLE);
            tv0.setText("当前账户可以注销");
            tv0.setTextColor(Color.parseColor("#1AFA29"));
        }else {
            tv_commit.setVisibility(View.VISIBLE);
            tv_cancle.setVisibility(View.GONE);
            tv0.setText("您的账户暂时无法注销账号！请谨慎处理！");
            tv0.setTextColor(Color.parseColor("#ff5800"));
        }
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity,CancleResultActivity.class);
                intent.putExtra("reason",reason);
                intent.putExtra("phone",phone);

                startActivity(intent);
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
