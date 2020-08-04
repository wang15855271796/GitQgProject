package com.puyue.www.qiaoge.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.cart.PaysResultActivity;
import com.puyue.www.qiaoge.activity.mine.account.AccountCenterActivity;
import com.puyue.www.qiaoge.activity.mine.account.EditPasswordInputCodeActivity;
import com.puyue.www.qiaoge.adapter.ExchangeAdapter;
import com.puyue.www.qiaoge.api.home.CancleAPI;
import com.puyue.www.qiaoge.api.mine.AccountCenterAPI;
import com.puyue.www.qiaoge.event.CouponEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.NetWorkHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.ExCouponModel;
import com.puyue.www.qiaoge.model.cart.ExChangeModel;
import com.puyue.www.qiaoge.model.mine.AccountCenterModel;
import com.puyue.www.qiaoge.utils.ToastUtil;
import com.puyue.www.qiaoge.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2020/7/8
 */
public class ExCouponDialog extends Dialog {
    private AlertDialog mDialog;
    public View view;
    Activity context;
    public Unbinder binder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_add_car)
    TextView tv_add_car;
    @BindView(R.id.tv_amount)
    TextView tv_amount;
    List<ExChangeModel.DetailListBean> mDatas;
    List<String> amounts;
    private EditText mEtPwd;
    Double total = 0.0;
    BigDecimal totals;
    public ExCouponDialog(Activity context, List<ExChangeModel.DetailListBean> mDatas, List<String> amounts, BigDecimal totals) {
        super(context, R.style.dialog);
        this.context = context;
        this.mDatas = mDatas;
        this.amounts = amounts;
        this.totals = totals;
        init();

    }

    public void init() {
        view = View.inflate(context, R.layout.dialog_ex_change, null);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        binder = ButterKnife.bind(this, view);
        setContentView(view);
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = Utils.getScreenWidth(context);
        getWindow().setAttributes(attributes);
        ExchangeAdapter exchangeAdapter = new ExchangeAdapter(R.layout.item_exchange,amounts);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(exchangeAdapter);
        for (int i = 0; i < amounts.size(); i++) {
           Double s = Double.valueOf(amounts.get(i));
           total+=s;
        }
        tv_amount.setText("兑换金额"+totals+"元");
        tv_add_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showInputPwdDialog();
                accountCenter();
            }
        });
    }

    /**
     * 显示输入支付密码弹窗
     */
    private void showInputPwdDialog() {
        mDialog = new AlertDialog.Builder(context).create();
        mDialog.setView(new EditText(context));
        mDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        mDialog.show();
        mDialog.getWindow().setContentView(R.layout.dialog_input_pwd_ex);
        mDialog.getWindow().findViewById(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        mEtPwd = mDialog.getWindow().findViewById(R.id.et_dialog_paypwd);
        mDialog.getWindow().findViewById(R.id.tv_dialog_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestUserInfo();

            }
        });
        mDialog.getWindow().findViewById(R.id.tv_dialog_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEtPwd.getText().toString())) {
                    AppHelper.showMsg(context, "请输入交易密码");
                } else {
                    mDialog.dismiss();
                    String s = new Gson().toJson(amounts);
                    getExCoupon(s);
                }
            }
        });
    }


    AccountCenterModel mModelAccountCenter;
    String hasSetPayPwd;
    String mUserCell;
    private void requestUserInfo() {
        if (!NetWorkHelper.isNetworkAvailable(context)) {
            AppHelper.showMsg(context, "网络不给力!");
        } else {
            AccountCenterAPI.requestAccountCenter(context)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<AccountCenterModel>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(AccountCenterModel accountCenterModel) {
                            if (accountCenterModel.success) {
                                mModelAccountCenter = accountCenterModel;
                                if (mModelAccountCenter.data.hasSetPayPwd) {
                                    //已经设置过支付密码
                                    mUserCell = mModelAccountCenter.data.phone;
                                    hasSetPayPwd = "1";
                                    UserInfoHelper.saveForgetPas(context, "");
                                    context.startActivity(EditPasswordInputCodeActivity.getIntent(context, EditPasswordInputCodeActivity.class, "1", mUserCell, "pay","",0,0));

                                    mDialog.dismiss();
                                } else {
                                    //没有设置过支付密码
                                    hasSetPayPwd = "0";
                                    context.startActivity(EditPasswordInputCodeActivity.getIntent(context, EditPasswordInputCodeActivity.class, "0", mUserCell, "pay","",0,0));
                                    mDialog.dismiss();
                                }

                            } else {
                                AppHelper.showMsg(context, mModelAccountCenter.message);
                            }
                        }
                    });
        }
    }

    private void accountCenter() {
        AccountCenterAPI.requestAccountCenter(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AccountCenterModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AccountCenterModel accountCenterModel) {

                        if (accountCenterModel.success) {
                            if (accountCenterModel.data.hasSetPayPwd) {
                                showInputPwdDialog();
                            } else {
                                showGoSetDialog();
                            }
                        } else {
                            AppHelper.showMsg(context, accountCenterModel.message);
                        }
                    }
                });
    }
    private void showGoSetDialog() {
        mDialog = new AlertDialog.Builder(context).create();
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
        mDialog.getWindow().setContentView(R.layout.dialog_not_set_paypwd);
        mDialog.getWindow().findViewById(R.id.tv_dialog_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
//                mLavLoading.setVisibility(View.GONE);
                //   mIvError.setVisibility(View.VISIBLE);
                //  mTvState.setText("取消支付");
            }
        });
        mDialog.getWindow().findViewById(R.id.tv_dialog_goset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,AccountCenterActivity.class);
                context.startActivity(intent);
                mDialog.dismiss();
//                UserInfoHelper.saveDeliverType(context,orderDeliveryType+"");
//                UserInfoHelper.saveForgetPas(context, "wwwe");
//                context.startActivity(EditPasswordInputCodeActivity.getIntent(context, EditPasswordInputCodeActivity.class, "0", mUserCell, "pay","forgetPsw",orderDeliveryType,payAmount));
//
//                mDialog.dismiss();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        //     mIvError.setVisibility(View.VISIBLE);
//                        //    mTvState.setText("取消支付");
//                    }
//                }, 1000);
            }
        });
    }

    private void getExCoupon(String s) {
        CancleAPI.getCopon(context,s,mEtPwd.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ExCouponModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ExCouponModel exCouponModel) {
                        if (exCouponModel.isSuccess()) {
                            Intent intent = new Intent(context,PaysResultActivity.class);
                            EventBus.getDefault().post(new CouponEvent());
                            context.startActivity(intent);
                            context.finish();
                            ToastUtil.showSuccessMsg(context,exCouponModel.getMessage());
                        } else {
                            AppHelper.showMsg(context, exCouponModel.getMessage());
                        }
                    }
                });
    }
}
