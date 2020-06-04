package com.puyue.www.qiaoge.popupwindow;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

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

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${daff}
 * on 2018/10/25
 * 备注  首页弹窗
 */
public class HomePopuWindow extends PopupWindow {
    private Context context;
    private int id;
    private View view;
    private ImageView buttonDelete;
    private ImageView popImageView;
    private String urlImage;
    private String intentUrl;
    private String toPage;

    public HomePopuWindow(Context context, int id, String urlImage, String intentUrl, String toPage) {
        this.context = context;
        this.id = id;
        this.view = LayoutInflater.from(context).inflate(R.layout.popwindow_home_dialog, null);
        this.urlImage = urlImage;
        this.intentUrl = intentUrl;
        this.toPage = toPage;

        initview();
    }


    //toPage：跳转页面，self 跳pageUrl，disable不跳转，其他跳原生界面
    //deduct:优惠券页面，seckill：秒杀页面，group:团购页面，priceDown：降价页面，
    // self：跳H5页面，disable:无，不跳转

    private void initview() {
        buttonDelete = view.findViewById(R.id.buttonDelete);
        popImageView = view.findViewById(R.id.popImageView);
        Glide.with(context).load(urlImage).into(popImageView);


        if (toPage.equals("disable")) {
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
        // 设置外部可点击
        //this.setOutsideTouchable(true);
        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        // 设置弹出窗体可点击
        this.setFocusable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x7f000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = view.findViewById(R.id.linearLayout).getTop();
                int buttom = view.findViewById(R.id.linearLayout).getBottom();
                int Left = v.findViewById(R.id.linearLayout).getLeft();
                int Right = v.findViewById(R.id.linearLayout).getRight();
                int y = (int) event.getY();
                int X = (int) event.getX();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height || y > buttom || X < Left || X > Right) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    private void QueryHomePropupDismiss() {
        PopupViewHomeAPI.requestData(context, id)
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

    // 点击x或跳转对应页面时，调用此接口，设为已读
    private void QueryHomePropup() {
        PopupViewHomeAPI.requestData(context, id)
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
                        Log.d("weeeeweeeeee.....",toPage);
                        //  toPage: 跳转页面 vip(会员),deduct(优惠券),seckill(秒杀),group（团购）,sharePage(分享),priceDown(降价),self(自定义界面),disable(无，不可跳转)
                        if (popupViewHomeModel.success) {
                            switch (toPage) {

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
                                    Log.i("ccccccc", "onNext: " + intentUrl);
                                    intent4.putExtra("URL", intentUrl);
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
