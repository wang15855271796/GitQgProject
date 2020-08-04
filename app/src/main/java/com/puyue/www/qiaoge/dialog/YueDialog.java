package com.puyue.www.qiaoge.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.cart.ExchangeActivity;

import java.util.List;

/**
 * Created by ${王涛} on 2020/7/23
 */
public abstract class YueDialog extends Dialog implements View.OnClickListener {

    Context mContext;
    TextView tv_sure;
    TextView tv_cancle;
    String userWalletAccount;
    public YueDialog(@NonNull Context context, String userWalletAccount) {
        super(context, R.style.promptDialog);
        setContentView(R.layout.yue_dialog);
        mContext = context;
        this.userWalletAccount = userWalletAccount;
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
//                dismiss();
                close();
                break;

            case R.id.tv_sure:
                close();
                Intent intent = new Intent(mContext,ExchangeActivity.class);
                intent.putExtra("amount",userWalletAccount);
                mContext.startActivity(intent);
                break;

        }

    }
    public abstract void close();

}
