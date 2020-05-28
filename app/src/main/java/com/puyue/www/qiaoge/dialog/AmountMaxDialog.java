package com.puyue.www.qiaoge.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.SetAmountEvent;
import com.puyue.www.qiaoge.event.SetAmountMaxEvent;
import com.puyue.www.qiaoge.event.SetAmountMaxsEvent;
import com.puyue.www.qiaoge.event.SetAmountsEvent;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.puyue.www.qiaoge.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ${王涛} on 2020/4/8
 */
public abstract class AmountMaxDialog extends Dialog implements View.OnClickListener {

    Context mContext;
    TextView tv_ok;
    EditText ed_amount;
    private String amount;
    SwitchCompat swipe;
    ImageView iv_close;
    public AmountMaxDialog(@NonNull Context context) {
        super(context, R.style.promptDialog);
        setContentView(R.layout.amount_max);
        mContext = context;
        initView();
        initAction();
    }

    private void initAction() {
        iv_close = (ImageView) findViewById(R.id.iv_close);
        tv_ok= (TextView) findViewById(R.id.tv_ok);
        ed_amount = (EditText) findViewById(R.id.ed_amount);
        swipe = (SwitchCompat) findViewById(R.id.swipe);
        swipe.setChecked(true);
        tv_ok.setOnClickListener(this);
        iv_close.setOnClickListener(this);
        //0不限制  1限制
        SharedPreferencesUtil.saveString(mContext,"amount_limit","1");
        swipe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            //0不限制 1限制
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    SharedPreferencesUtil.saveString(mContext,"amount_limit","1");
                }else {
                    SharedPreferencesUtil.saveString(mContext,"amount_limit","0");
                }
            }
        });
    }

    private void initView() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_ok:
                amount = ed_amount.getText().toString();
                if(swipe.isChecked()) {
                    if(amount.equals("")||amount.equals("0")) {
                        ToastUtil.showErroMsg(mContext,"请输入正确金额");
                        break;
                    } else {
                        EventBus.getDefault().post(new SetAmountMaxEvent(amount));
                    }
                }else {
                    EventBus.getDefault().post(new SetAmountMaxsEvent(""));
                }
                Confirm();

                break;

            case R.id.iv_close:
                Cancle();
                break;
        }

    }

    public abstract void Confirm();
    public abstract void Cancle();
}
