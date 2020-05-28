package com.puyue.www.qiaoge.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.SetAmountEvent;
import com.puyue.www.qiaoge.event.SetAmountsEvent;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.puyue.www.qiaoge.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ${王涛} on 2020/4/3
 */
public abstract class AmountSetDialog extends Dialog implements View.OnClickListener {
    Context mContext;
    TextView tv_ok;
    RadioButton radioButton1;
    RadioButton radioButton2;
    EditText ed_amount;
    private String amount;

    public AmountSetDialog(@NonNull Context context) {
        super(context, R.style.promptDialog);
        setContentView(R.layout.amount_set);
        mContext = context;
        initView();
        initAction();
    }

    private void initAction() {
        tv_ok= (TextView) findViewById(R.id.tv_ok);
        radioButton1 = (RadioButton) findViewById(R.id.radio1);
        radioButton2 = (RadioButton) findViewById(R.id.radio2);
        ed_amount = (EditText) findViewById(R.id.ed_amount);

        tv_ok.setOnClickListener(this);
    }

    private void initView() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_ok:
                amount = ed_amount.getText().toString();
                    if(radioButton1.isChecked()) {
                        SharedPreferencesUtil.saveString(mContext,"notification","1");
                        SharedPreferencesUtil.saveString(mContext,"flag","1");
                        Confirm();
                    }else if(radioButton2.isChecked()) {
                        if(amount.equals("")||amount.equals("0")) {
                            ToastUtil.showErroMsg(mContext,"请输入正确金额");
                            break;
                        }else {
                            SharedPreferencesUtil.saveString(mContext,"notification","2");
                            SharedPreferencesUtil.saveString(mContext,"flag","2");
                            Confirm();
                        }

                    }else {
                        SharedPreferencesUtil.saveString(mContext,"notification","0");
                        SharedPreferencesUtil.saveString(mContext,"flag","0");
                        Confirm();
                    }
                    EventBus.getDefault().post(new SetAmountEvent(amount));
                    EventBus.getDefault().post(new SetAmountsEvent());
                    Confirm();

                break;
        }

    }

    public abstract void Confirm();

}
