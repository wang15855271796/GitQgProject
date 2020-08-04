package com.puyue.www.qiaoge.adapter.home;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.adapter.HotAdapter;
import com.puyue.www.qiaoge.api.cart.AddCartAPI;
import com.puyue.www.qiaoge.fragment.cart.ReduceNumEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.cart.AddCartModel;
import com.puyue.www.qiaoge.model.home.SeckillListModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.puyue.www.qiaoge.view.GlideModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王文博} on 2019/4/12
 */
public class SpikeActiveQueryAdapter extends BaseQuickAdapter<SeckillListModel.DataBean.KillsBean, BaseViewHolder> {

    private ImageView ivSpike;
    private TextView tvPrice;
    private TextView tvOldPrice;
    private TextView tvTitle;
    private TextView ivSoldOut;
    private TextView tvPsc;
    private TextView tvSale;
    private TextView tvSoldSale;
    private FrameLayout frameLayout;
    private ImageView ivSoldOutLeft;
    private ProgressBar mProgressBar;
    private int activedId;
    LinearLayout ll_root;
    TextView tv_add_remind;
    RelativeLayout rl_price;
    Onclick onclick;
    public SpikeActiveQueryAdapter(int layoutResId, @Nullable List<SeckillListModel.DataBean.KillsBean> data,int activedId,Onclick onclick) {
        super(layoutResId, data);
        this.activedId = activedId;
        this.onclick = onclick;
    }

    @Override
    protected void convert(BaseViewHolder helper, SeckillListModel.DataBean.KillsBean item) {
        ll_root = helper.getView(R.id.ll_root);
        rl_price = helper.getView(R.id.rl_price);
        tv_add_remind = helper.getView(R.id.tv_add_remind);
        ivSpike = helper.getView(R.id.iv_item_spike_img);
        tvTitle = helper.getView(R.id.tv_item_spike_title);
        tvPrice = helper.getView(R.id.tv_item_spike_price);
        tvOldPrice = helper.getView(R.id.tv_item_spike_old_price);
        ivSoldOut = helper.getView(R.id.tv_sold_out);
        tvPsc = helper.getView(R.id.tv_item_spike_specification);
        frameLayout = helper.getView(R.id.iv_bg);
        ivSoldOutLeft = helper.getView(R.id.iv_sold);
        tvSale = helper.getView(R.id.tv_item_spike_sales);
        mProgressBar = helper.getView(R.id.pb_item_spike);
        tvSoldSale = helper.getView(R.id.tv_item_spike_sales);
        tvTitle.setText(item.title);
        tvOldPrice.setText(item.oldPrice);
        tvPsc.setText(item.spec);
        tvSale.setText(item.sales);
        mProgressBar.setProgressDrawable(mContext.getResources().getDrawable(R.drawable.seckill_progress));
        mProgressBar.setProgress(Integer.parseInt(item.progress));
        tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); //中划线
        tvSoldSale.setText("已抢购" + item.progress + "%");
        String spikeFlag = UserInfoHelper.getSpikePosition(mContext);
        GlideModel.disPlayError(mContext,item.pic,ivSpike);

        if(SharedPreferencesUtil.getString(mContext,"priceType").equals("1")) {
            if(Integer.parseInt(spikeFlag)==0) {
                //未开始
                tv_add_remind.setVisibility(View.GONE);
            }else {
                // 已开始
                ivSoldOut.setVisibility(View.GONE);

                if(item.soldOut==0) {
                    ivSoldOut.setVisibility(View.GONE);
                    tv_add_remind.setVisibility(View.VISIBLE);
                }else {
                    ivSoldOut.setVisibility(View.VISIBLE);
                    tv_add_remind.setVisibility(View.GONE);
                }
            }
            tvPrice.setText(item.price);
            rl_price.setVisibility(View.GONE);
        }else {

            if(Integer.parseInt(spikeFlag)==0) {
                //未开始
                tv_add_remind.setVisibility(View.GONE);
            }else {
                // 已开始
                ivSoldOut.setVisibility(View.GONE);

                if(item.soldOut==0) {
                    ivSoldOut.setVisibility(View.GONE);
                    tv_add_remind.setVisibility(View.VISIBLE);
                }else {
                    ivSoldOut.setVisibility(View.VISIBLE);
                    tv_add_remind.setVisibility(View.GONE);
                }
            }
            tvPrice.setText("价格授权后可见");
            ivSoldOut.setVisibility(View.GONE);
            tv_add_remind.setVisibility(View.GONE);
            rl_price.setVisibility(View.VISIBLE);
        }

        rl_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onclick!=null) {
                    onclick.tipClick();
                }
            }
        });
        tv_add_remind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    int activeId = item.activeId;
                    addCar(activeId, "", 2, "1");

                } else {
                    AppHelper.showMsg(mContext, "请先登录");
                    mContext.startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                }
            }
        });


//        if(Integer.parseInt(spikeFlag)==0) {
//            //未开始
//            tv_add_remind.setVisibility(View.GONE);
//        }else {
//            // 已开始
//            ivSoldOut.setVisibility(View.GONE);
//
//            if(item.soldOut==0) {
//                ivSoldOut.setVisibility(View.GONE);
//                tv_add_remind.setVisibility(View.VISIBLE);
//            }else {
//                ivSoldOut.setVisibility(View.VISIBLE);
//                tv_add_remind.setVisibility(View.GONE);
//            }
//        }




        if (Integer.parseInt(spikeFlag) == 0) {
            //秒杀预告
            ivSoldOut.setVisibility(View.GONE);
            ivSoldOutLeft.setVisibility(View.GONE);
            frameLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            ivSoldOut.setVisibility(View.GONE);
        } else if (Integer.parseInt(spikeFlag) == 1) {

            if (item.soldOut == 0) {
                ivSoldOut.setVisibility(View.GONE);
                ivSoldOutLeft.setVisibility(View.GONE);

            } else {
                ivSoldOut.setVisibility(View.VISIBLE);

                GlideModel.disPlayError(mContext,item.flagUrl,ivSoldOutLeft);
                ivSoldOutLeft.setVisibility(View.VISIBLE);

            }
        }
    }


    private void addCar(int businessId, String productCombinationPriceVOList, int businessType, String totalNum) {
        AddCartAPI.requestData(mContext, businessId, productCombinationPriceVOList, businessType, String.valueOf(totalNum))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AddCartModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AddCartModel addCartModel) {
                        if (addCartModel.success) {
                            AppHelper.showMsg(mContext, "成功加入购物车");
                            EventBus.getDefault().post(new ReduceNumEvent());
                        } else {
                            AppHelper.showMsg(mContext, addCartModel.message);
                        }

                    }
                });
    }

    public interface Onclick {
        void tipClick();
    }



}
