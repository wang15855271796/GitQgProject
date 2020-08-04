package com.puyue.www.qiaoge.activity.mine.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.activity.mine.order.MyConfireOrdersActivity;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;

/**
 * Created by Administrator on 2018/4/9.
 */

public class EditAccountOrPasswordResultActivity extends BaseSwipeActivity {
    public static final String TEL = "tel";
    public static final String TYPE = "type";
    public static final String SOURCE = "source";
    public static final String ORDERTYPE = "orderDeliveryType";
    public static final String PAYAMOUNT = "payAmount";
    private String mType;
    private String mTel;
    private String mSource;

    private ImageView mIvBack;
    private TextView mTvTitle;
    private TextView mTvResult;
    private Button mBtn;


    private int orderDeliveryType;
    private double payAmount;
    public static Intent getIntent(Context context, Class<?> cls, String type, String tel, String source,int orderDeliveryType,double payAmount) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.putExtra(TYPE, type);
        intent.putExtra(TEL, tel);
        intent.putExtra(SOURCE, source);
        intent.putExtra(ORDERTYPE, orderDeliveryType);
        intent.putExtra(PAYAMOUNT, payAmount);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleExtra(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        mType = getIntent().getStringExtra(TYPE);
        mTel = getIntent().getStringExtra(TEL);
        mSource = getIntent().getStringExtra(SOURCE);
        payAmount=getIntent().getDoubleExtra(PAYAMOUNT,0);
        orderDeliveryType=getIntent().getIntExtra(ORDERTYPE,0);
        if (savedInstanceState != null) {
            mType = savedInstanceState.getString(TYPE);
            mTel = savedInstanceState.getString(TEL);
            mSource = savedInstanceState.getString(SOURCE);
            orderDeliveryType = savedInstanceState.getInt(ORDERTYPE,orderDeliveryType);
            payAmount = savedInstanceState.getDouble(PAYAMOUNT,payAmount);
        }
        return false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putString(TEL, mTel);
        outState.putString(TYPE, mType);
        outState.putString(SOURCE, mSource);
        outState.putInt(ORDERTYPE, orderDeliveryType);
        outState.putDouble(PAYAMOUNT,payAmount);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        handleExtra(savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_edit_pay_password_result);
    }

    @Override
    public void findViewById() {
        mIvBack = (ImageView) findViewById(R.id.iv_edit_pp_result_back);
        mTvTitle = (TextView) findViewById(R.id.tv_edit_pp_result_title);
        mTvResult = (TextView) findViewById(R.id.tv_edit_pp_result);
        mBtn = (Button) findViewById(R.id.btn_edit_pp_result_confirm);
    }

    int type;
    double amount;


    @Override
    public void setViewData() {
        String orderId = UserInfoHelper.getOrderId(mContext);
        String forgetPas = UserInfoHelper.getForgetPas(mContext);
        if (UserInfoHelper.getOrderamount(mContext) != null && StringHelper.notEmptyAndNull(UserInfoHelper.getOrderamount(mContext))) {
            amount = Double.parseDouble(UserInfoHelper.getOrderamount(mContext));
        }

        String remark = UserInfoHelper.getRemark(mContext);

        if (StringHelper.notEmptyAndNull(mType) && StringHelper.notEmptyAndNull(mSource)) {
            if (mSource.equals("pay")) {
                //操作完支付密码的结果页
                if (mType.equals("0")) {
                    mTvTitle.setText("设置支付密码");
                    mTvResult.setText("设置支付密码成功");
                    mBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (forgetPas != null && StringHelper.notEmptyAndNull(forgetPas)) {


                                Intent intent = new Intent(mContext, MyConfireOrdersActivity.class);
                                intent.putExtra("orderId", orderId);
                                intent.putExtra("payAmount", amount);
                                intent.putExtra("remark", remark);
                                intent.putExtra("orderDeliveryType", orderDeliveryType);
                                startActivity(intent);
                            } else {
                                startActivity(AccountCenterActivity.getIntent(mContext, AccountCenterActivity.class));
                            }


                            finish();
                        }
                    });
                    mIvBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (forgetPas != null && StringHelper.notEmptyAndNull(forgetPas)) {
//                                Intent intent = new Intent(mContext, MyConfireOrdersActivity.class);
//                                intent.putExtra("orderId", orderId);
//                                intent.putExtra("payAmount", amount);
//                                intent.putExtra("remark", remark);
//                                intent.putExtra("orderDeliveryType", orderDeliveryType);
//                                startActivity(intent);
                                finish();
                            } else {
                                startActivity(AccountCenterActivity.getIntent(mContext, AccountCenterActivity.class));
                            }


                            finish();
                        }
                    });
                } else if (mType.equals("1")) {
                    mTvTitle.setText("修改支付密码");
                    mTvResult.setText("修改支付密码成功");

                    mBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            if (forgetPas != null && StringHelper.notEmptyAndNull(forgetPas)) {
                                Intent intent = new Intent(mContext, MyConfireOrdersActivity.class);
                                intent.putExtra("orderId", orderId);
                                intent.putExtra("payAmount", amount);
                                intent.putExtra("remark", remark);
                                intent.putExtra("orderDeliveryType", orderDeliveryType);
                                startActivity(intent);
                            } else {
//                                startActivity(AccountCenterActivity.getIntent(mContext, AccountCenterActivity.class));
                            }


                            finish();
                        }
                    });
                    mIvBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (forgetPas != null) {
//                                Intent intent = new Intent(mContext, MyConfireOrdersActivity.class);
//                                intent.putExtra("orderId", orderId);
//                                intent.putExtra("payAmount", amount);
//                                intent.putExtra("remark", remark);
//                                intent.putExtra("orderDeliveryType", orderDeliveryType);
//                                startActivity(intent);
                                finish();
                            } else {
//                                startActivity(AccountCenterActivity.getIntent(mContext, AccountCenterActivity.class));
                            }

                            finish();
                        }
                    });
                }
            } else if (mSource.equals("login")) {
                //操作完登录密码的结果页
                mTvTitle.setText("修改登录密码");
                mTvResult.setText("修改登录密码成功");
                mBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        startActivity(AccountCenterActivity.getIntent(mContext, AccountCenterActivity.class));
                        finish();
                    }
                });
                mIvBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        startActivity(AccountCenterActivity.getIntent(mContext, AccountCenterActivity.class));
                        finish();
                    }
                });

            } else if (mSource.equals("account")) {
                //操作完更改账户的结果页
                mTvTitle.setText("修改登录账户");
                mTvResult.setText("更改登录账户成功");
            } else if (mSource.equals("forget")) {
                mTvTitle.setText("忘记密码");
                mTvResult.setText("找回登录密码成功");


                mBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                        finish();
                    }
                });
                mIvBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                        finish();
                    }
                });

            }
        }
    }

    @Override
    public void setClickEvent() {

    }

   /* @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                finish();
            }
        });
        mBtn.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                //这里修改支付密码完成
                Intent intent = new Intent(AppConstant.PAY_PASSWORD_ACTION);
                intent.putExtra("type", "balance");
                sendBroadcast(intent);
                finish();
            }
        });
    }*/
}
