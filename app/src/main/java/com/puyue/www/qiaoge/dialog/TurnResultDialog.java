package com.puyue.www.qiaoge.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.HomeActivity;
import com.puyue.www.qiaoge.api.home.IndexHomeAPI;
import com.puyue.www.qiaoge.api.mine.order.MyOrderNumAPI;
import com.puyue.www.qiaoge.event.CouponEvent;
import com.puyue.www.qiaoge.event.GoToMarketEvent;
import com.puyue.www.qiaoge.event.PrivacyModel;
import com.puyue.www.qiaoge.event.TurnModel;
import com.puyue.www.qiaoge.event.TurnReceiveModel;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.model.mine.order.MyOrderNumModel;
import com.puyue.www.qiaoge.view.LuckPan;
import com.puyue.www.qiaoge.view.LuckPanAnimEndCallBack;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static cn.com.chinatelecom.account.api.CtAuth.mContext;

/**
 * Created by ${王涛} on 2020/4/21
 */
public class TurnResultDialog extends Dialog {

    Context mContext;
    TextView tv_num;
    ImageView iv_result;
    ImageView iv_close;
    private String content;

    public TurnResultDialog(@NonNull Context context) {
        super(context, R.style.promptDialog);
        setContentView(R.layout.dialog_turn_result);
        mContext = context;
        initView();
        initAction();
        getPrivacy();
    }

    private void initView() {
        tv_num = findViewById(R.id.tv_num);
        iv_result = findViewById(R.id.iv_result);
        iv_close = findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                PrivacyDialog privacyDialog = new PrivacyDialog(mContext,content);
                privacyDialog.show();
                EventBus.getDefault().post(new CouponEvent());
            }
        });
        iv_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, HomeActivity.class));
                EventBus.getDefault().post(new GoToMarketEvent());
                EventBus.getDefault().post(new CouponEvent());
                dismiss();
                PrivacyDialog privacyDialog = new PrivacyDialog(mContext,content);
                privacyDialog.show();

            }
        });
        getData();
    }

    private void getData() {
        IndexHomeAPI.TurnReceive(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TurnReceiveModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TurnReceiveModel turnModel) {
                        if(turnModel.isSuccess()) {
                            tv_num.setText(turnModel.getData().getPoolNo());

                        }else {
                            AppHelper.showMsg(mContext,turnModel.getMessage());
                        }
                    }
                });
    }


    private void initAction() {

    }


    /**
     * 获取权限
     */
    private void getPrivacy() {
        IndexHomeAPI.getPrivacy(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PrivacyModel>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PrivacyModel privacyModel) {
                        if(privacyModel.isSuccess()) {
                            content = privacyModel.getData().getContent();
                        }else {
                            AppHelper.showMsg(mContext,privacyModel.getMessage());
                        }
                    }
                });
    }

}
