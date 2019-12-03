package com.puyue.www.qiaoge.activity.mine.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.puyue.www.qiaoge.NewWebViewActivity;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.market.MarketGoodsClassifyAPI;
import com.puyue.www.qiaoge.api.mine.GetMyBalanceAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.GlideRoundTransform;
import com.puyue.www.qiaoge.helper.TwoDeviceHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.market.MarketClassifyModel;
import com.puyue.www.qiaoge.model.mine.GetMyBalanceModle;

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
                Intent intent = new Intent(mActivity, MyWalletActivity.class);
                UserInfoHelper.saveUserWalletNum(getContext(), num);
                startActivity(intent);
                finish();
                //startActivity(MyWalletActivity.getIntent(mActivity, MyWalletActivity.class));
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
        relative_account_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(mContext,MyWalletDetailActivity.class);

                intent.putExtra("showType",2);
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
                        //    balancePrice.setText(getMyBalanceModle.getData().getAmount());

                            tv_amount.setText(getMyBalanceModle.getData().getAmount());
                            UserInfoHelper.saveUserWallet(mContext, getMyBalanceModle.getData().getAmount());

                            myCommissionPrice.setText(getMyBalanceModle.getData().getCommission());
                        /*    if (!TextUtils.isEmpty(getMyBalanceModle.getData().getAmountDesc())) {
                                balanceNum.setVisibility(View.VISIBLE);
                                balanceNum.setText(getMyBalanceModle.getData().getAmountDesc());
                            } else {
                                balanceNum.setVisibility(View.GONE);
                            }*/
                            bannerUrl = getMyBalanceModle.getData().getBanner().get(0).getBannerDetailUrl();
                            commissionUrl = getMyBalanceModle.getData().getCommissionUrl();
                            Glide.with(mActivity).load(getMyBalanceModle.getData().getBanner().get(0).getBannerUrl()).into(banner);

                        }


                    }
                });
    }


}
