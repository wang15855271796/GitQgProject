package com.puyue.www.qiaoge.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.ChangeCityActivity;
import com.puyue.www.qiaoge.activity.mine.SetAmountEvent;
import com.puyue.www.qiaoge.event.SetAmountsEvent;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.puyue.www.qiaoge.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;



/**
 * Created by ${王涛} on 2020/4/29
 */
public abstract class ChangeCityDialog extends Dialog implements View.OnClickListener {

    Context mContext;
    TextView tv_sure;
    TextView tv_cancle;

    public ChangeCityDialog(@NonNull Context context) {
        super(context, R.style.promptDialog);
        setContentView(R.layout.change_city_dialog);
        mContext = context;
        initView();
        initAction();
    }

    private void initAction() {
        tv_sure = (TextView) findViewById(R.id.tv_sure);
        tv_cancle = (TextView) findViewById(R.id.tv_cancle);

        tv_sure.setOnClickListener(this);
        tv_cancle.setOnClickListener(this);
    }

    private void initView() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancle:
                dismiss();
                break;

            case R.id.tv_sure:
                close();
                break;

        }

    }
    public abstract void close();

}
