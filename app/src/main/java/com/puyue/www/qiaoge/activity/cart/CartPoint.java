package com.puyue.www.qiaoge.activity.cart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.order.ConfirmNewOrderActivity;
import com.puyue.www.qiaoge.activity.mine.order.ConfirmOrderNewActivity;
import com.puyue.www.qiaoge.activity.mine.order.MyConfireOrdersActivity;
import com.puyue.www.qiaoge.activity.mine.wallet.MyWalletActivity;
import com.puyue.www.qiaoge.activity.mine.wallet.MyWalletPointActivity;
import com.puyue.www.qiaoge.api.cart.CartPointAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.BackEvent;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.cart.CartPointModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.umeng.socialize.utils.ContextUtil.getContext;

public class CartPoint extends BaseSwipeActivity {

    // 获取想去的参数 和获取订单的参数
    private String normalProductBalanceVOStr;
    private String activityBalanceVOStr;
    private String equipmentBalanceVOStr;
    private String cartListStr;
//    private String orderNo;

    private TextView tvAmount;
    private TextView count;
    private ImageView ivCancel;
    private ImageView ivOK;
    private ImageView ivPointContent;

    //订单金额
    private Double amount;
    private String token;
    private int currentDay;
    String orderId;
    private LinearLayout ivBack;
    private String stringExtra;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_cart_point);
    }

    @Override
    public void findViewById() {
        tvAmount = findViewById(R.id.tv_amount);
        count = findViewById(R.id.tv_count);
        ivCancel = findViewById(R.id.iv_cancel);
        ivOK = findViewById(R.id.iv_ok);
        ivPointContent = findViewById(R.id.iv_recharge_content);
        ivBack =findViewById(R.id.linearLayout_back);
    }

    @Override
    public void setViewData() {
//        normalProductBalanceVOStr = getIntent().getStringExtra("normalProductBalanceVOStr");
//        activityBalanceVOStr = getIntent().getStringExtra("activityBalanceVOStr");
//        equipmentBalanceVOStr = getIntent().getStringExtra("equipmentBalanceVOStr");
//        cartListStr = getIntent().getStringExtra("cartListStr");
        amount = Double.parseDouble(getIntent().getStringExtra("orderAmount"));
        orderId = getIntent().getStringExtra(AppConstant.ORDERID);
//         orderNo = getIntent().getStringExtra("orderId");
        token = AppConstant.TOKEN;

        initData(amount, token);
        final Calendar mCalendar = Calendar.getInstance();
        long time = System.currentTimeMillis();
        mCalendar.setTimeInMillis(time);
        currentDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        UserInfoHelper.saveDate(mActivity, currentDay + "");
//        SharedPreferencesUtil.saveInt(mActivity,"days",currentDay);

    }

    @Override
    public void setClickEvent() {
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyConfireOrdersActivity.class);
                intent.putExtra(AppConstant.ORDERID, orderId);
                intent.putExtra("payAmount", amount);
//                intent.putExtra("normalProductBalanceVOStr", normalProductBalanceVOStr);
//                intent.putExtra("activityBalanceVOStr", activityBalanceVOStr);
//                intent.putExtra("equipmentBalanceVOStr", equipmentBalanceVOStr);
//                intent.putExtra("cartListStr", cartListStr);
                startActivity(intent);
                finish();
            }
        });

        ivOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, MyWalletActivity.class);
                UserInfoHelper.saveUserWalletNum(getContext(), "0");
                startActivity(intent);
                finish();
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initData(Double amount, String token) {

        CartPointAPI.requestCartPoint(mContext, amount, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CartPointModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CartPointModel cartPointModel) {
                        if (cartPointModel.isSuccess()) {
                            tvAmount.setText(cartPointModel.getData().getOrderAmount() + "元");
                            count.setText("建议您充值" + cartPointModel.getData().getRechargeAmount() + "元");
                            Glide.with(mContext).load(cartPointModel.getData().getRole()).into(ivPointContent);
                        }
                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            EventBus.getDefault().post(new BackEvent());
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
