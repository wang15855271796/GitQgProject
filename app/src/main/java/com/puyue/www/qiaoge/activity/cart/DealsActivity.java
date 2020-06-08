package com.puyue.www.qiaoge.activity.cart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.model.CancleModel;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${王涛} on 2020/6/5
 */
public class DealsActivity extends BaseSwipeActivity {
    Handler handler = new Handler();
    private CancleModel.DataBean data;
    String reason;
    @BindView(R.id.iv_back)
    ImageView iv_back;
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
        ButterKnife.bind(this);
        data = (CancleModel.DataBean)getIntent().getSerializableExtra("data");
        if(getIntent().getStringExtra("reason")!=null) {
            reason = getIntent().getStringExtra("reason");

        }


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mActivity,ErrorActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",data);
                intent.putExtras(bundle);
                intent.putExtra("reason",reason);
                startActivity(intent);
            }
        },3000);

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

    @Override
    protected void onStop() {
        super.onStop();

    }
}
