package com.puyue.www.qiaoge.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.puyue.www.qiaoge.NewWebViewActivity;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.HomeActivity;
import com.puyue.www.qiaoge.activity.home.CouponDetailActivity;
import com.puyue.www.qiaoge.activity.home.HomeGoodsListActivity;
import com.puyue.www.qiaoge.activity.home.TeamDetailActivity;
import com.puyue.www.qiaoge.activity.mine.coupons.MyCouponsActivity;
import com.puyue.www.qiaoge.adapter.home.ReductionProductActivity;
import com.puyue.www.qiaoge.api.home.PopupViewHomeAPI;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.home.PopupViewHomeModel;
import com.puyue.www.qiaoge.model.home.QueryHomePropupModel;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2020/6/8
 */
public class HomeActivityDialog extends Dialog {

    Context context;
    ImageView popImageView;
    ImageView buttonDelete;
    QueryHomePropupModel.DataBean.HomePropupBean homePropup;

    public HomeActivityDialog(@NonNull Context mContext, QueryHomePropupModel.DataBean.HomePropupBean homePropup) {
        super(mContext, R.style.promptDialog);
        setContentView(R.layout.popwindow_home_dialog);
        this.context = mContext;
        this.homePropup = homePropup;

        initView();
    }

    private void initView() {

        popImageView = findViewById(R.id.popImageView);
        buttonDelete = findViewById(R.id.buttonDelete);
        Glide.with(context).load(homePropup.getShowUrl()).into(popImageView);

        if (homePropup.getToPage().equals("disable")) {
            popImageView.setEnabled(false);
        } else {
            popImageView.setEnabled(true);
        }
        popImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QueryHomePropup();
            }
        });

        buttonDelete.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                QueryHomePropupDismiss();

            }
        });

    }

    private void QueryHomePropupDismiss() {
        PopupViewHomeAPI.requestData(context, homePropup.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PopupViewHomeModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PopupViewHomeModel popupViewHomeModel) {

                        //  toPage: 跳转页面 vip(会员),deduct(优惠券),seckill(秒杀),group（团购）,sharePage(分享),priceDown(降价),self(自定义界面),disable(无，不可跳转)
                        if (popupViewHomeModel.success) {

                            dismiss();

                        } else {
                            AppHelper.showMsg(context, popupViewHomeModel.message);
                        }
                    }
                });
    }

    private void QueryHomePropup() {
        PopupViewHomeAPI.requestData(context, homePropup.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PopupViewHomeModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PopupViewHomeModel popupViewHomeModel) {
                        //  toPage: 跳转页面 vip(会员),deduct(优惠券),seckill(秒杀),group（团购）,sharePage(分享),priceDown(降价),self(自定义界面),disable(无，不可跳转)
                        if (popupViewHomeModel.success) {
                            switch (homePropup.getToPage()) {

                                case "deduct": // 优惠券页面
                                    context.startActivity(MyCouponsActivity.getIntent(context, MyCouponsActivity.class));
                                    break;

                                case "seckill": //秒杀页面
                                    Intent intent = new Intent(context, HomeGoodsListActivity.class);
                                    context.startActivity(intent);
                                    break;
                                case "group": //团购页面
                                    Intent intent2 = new Intent(context, TeamDetailActivity.class);
                                    context.startActivity(intent2);
                                    break;
                                case "priceDown": //降价页面
                                    Intent intent3 = new Intent(context, ReductionProductActivity.class);
                                    context.startActivity(intent3);
                                    break;
                                case "disable":
                                    Intent intent1 = new Intent(context, HomeActivity.class);
                                    context.startActivity(intent1);
                                    break;
                                case "offer":
                                    Intent intent5 = new Intent(context, CouponDetailActivity.class);
                                    context.startActivity(intent5);
                                    break;

                                default:
                                    Intent intent4 = new Intent(context, NewWebViewActivity.class);
                                    intent4.putExtra("URL", homePropup.getPageUrl());
                                    intent4.putExtra("TYPE", 1);
                                    intent4.putExtra("name", "");
                                    context.startActivity(intent4);
                                    break;


                            }
                            dismiss();

                        } else {
                            AppHelper.showMsg(context, popupViewHomeModel.message);
                        }
                    }
                });
    }
}
