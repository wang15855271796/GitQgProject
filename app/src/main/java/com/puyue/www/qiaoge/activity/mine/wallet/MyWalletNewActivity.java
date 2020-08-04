package com.puyue.www.qiaoge.activity.mine.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.puyue.www.qiaoge.NewWebViewActivity;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.cart.ExchangeActivity;
import com.puyue.www.qiaoge.api.mine.GetMyBalanceAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.event.BackEvent;
import com.puyue.www.qiaoge.event.ExBackEvent;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.GetMyBalanceModle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.umeng.socialize.utils.ContextUtil.getContext;

/**
 * Created by ${daff}
 * on 2018/10/16
 * 备注 我的钱包
 */
public class MyWalletNewActivity extends BaseSwipeActivity {
    private ImageView imageViewBack;
    private RelativeLayout relativeLayoutBalance;
    private RelativeLayout relativeLayoutMyCommission;
    private TextView balanceNum;
    private TextView balancePrice;
    private TextView myCommissionPrice;
    private ImageView banner;
    private String bannerUrl;
    private String commissionUrl;
    private String num = "0";
    GetMyBalanceModle getMyBalanceModles;

    private TextView tv_amount;

    private RelativeLayout relative_account_detail;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_my_wallet_new);
    }

    @Override
    public void findViewById() {
        EventBus.getDefault().register(this);
        imageViewBack = (ImageView) findViewById(R.id.imageViewBack);
        relativeLayoutBalance = (RelativeLayout) findViewById(R.id.relativeLayoutBalance);
        relativeLayoutMyCommission = (RelativeLayout) findViewById(R.id.relativeLayoutMyCommission);
        balanceNum = (TextView) findViewById(R.id.balanceNum);
        balancePrice = (TextView) findViewById(R.id.balancePrice);
        myCommissionPrice = (TextView) findViewById(R.id.myCommissionPrice);
        banner = (ImageView) findViewById(R.id.banner);
        tv_amount = (TextView) findViewById(R.id.tv_amount);
        relative_account_detail = (RelativeLayout) findViewById(R.id.relative_account_detail);

    }

    /**
     * 兑换优惠券返回刷新
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void exCoupon(ExBackEvent event) {
        requestGoodsList();

    }

    @Override
    public void setViewData() {
        requestGoodsList();
    }

    @Override
    public void setClickEvent() {
        imageViewBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                finish();
            }
        });
        relativeLayoutBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, MyWalletDetailActivity.class);
                UserInfoHelper.saveUserWalletNum(getContext(), num);
                intent.putExtra("showType",2);
                startActivity(intent);
            }
        });
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, NewWebViewActivity.class);
                intent.putExtra("URL", bannerUrl);
                intent.putExtra("TYPE", 1);
                intent.putExtra("name", "");
                startActivity(intent);
            }
        });
        relativeLayoutMyCommission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, NewWebViewActivity.class);
                intent.putExtra("URL", commissionUrl);
                intent.putExtra("TYPE", 1);
                intent.putExtra("name", "");
                startActivity(intent);
            }
        });

    }

    private void requestGoodsList() {
        GetMyBalanceAPI.requestGetMyBalance(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetMyBalanceModle>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetMyBalanceModle getMyBalanceModle) {
                        if (getMyBalanceModle.isSuccess()) {
                            getMyBalanceModles = getMyBalanceModle;
                            tv_amount.setText(getMyBalanceModle.getData().getAmount());

                            relative_account_detail.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent =new Intent(mContext,ExchangeActivity.class);
                                    intent.putExtra("amount",getMyBalanceModles.getData().getAmount());
                                    startActivity(intent);
                                }
                            });

                        }


                    }
                });
    }


}
