package com.puyue.www.qiaoge.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.HomeActivity;
import com.puyue.www.qiaoge.adapter.mine.AreaAdapter;
import com.puyue.www.qiaoge.fragment.home.CityEvent;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.home.CityChangeModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.puyue.www.qiaoge.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ${王涛} on 2020/6/24
 */
public abstract class OrderDialog extends Dialog {
    CountDownTimer countDownTimer;
    Context mContext;
    View view;
    public Unbinder binder;
    @BindView(R.id.tv_second)
    TextView tv_second;
    @BindView(R.id.rl_bg)
    RelativeLayout rl_bg;
    public OrderDialog(Context context) {
        super(context, R.style.promptDialog);
        this.mContext = context;
        initView();
        handleCountDown();

    }

    @Override
    public void show() {
        super.show();
        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

    }


    @Override
    public void cancel() {
        super.cancel();
        EventBus.getDefault().unregister(this);
    }

    private void handleCountDown() {
        countDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_second.setText("("+millisUntilFinished / 1000 +"s"+")");
                rl_bg.setEnabled(false);
            }

            @Override
            public void onFinish() {
                rl_bg.setEnabled(true);
                tv_second.setVisibility(View.GONE);



            }
        }.start();
    }



    private void initView() {
        view = View.inflate(mContext, R.layout.dialog_order, null);
        binder = ButterKnife.bind(this, view);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setContentView(view);
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = Utils.getScreenWidth(mContext);
        getWindow().setAttributes(attributes);
    }







}
