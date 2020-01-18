package com.puyue.www.qiaoge.adapter.home;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.api.cart.AddCartAPI;
import com.puyue.www.qiaoge.api.home.SpikeActiveQueryAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.fragment.cart.ReduceNumEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.cart.AddCartModel;
import com.puyue.www.qiaoge.model.home.SeckillListModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.puyue.www.qiaoge.utils.ToastUtil;
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
    private TextView ivAdd;
    private TextView ivSoldOut;
    private TextView ivAddGood;
    private TextView tvPsc;
    private TextView tvSale;
    private TextView tvSoldSale;
    private FrameLayout frameLayout;
    private ImageView ivSoldOutLeft;
    private TextView tv_add_remind;
    private ProgressBar mProgressBar;
    private int activedId;
    LinearLayout ll_root;
    public SpikeActiveQueryAdapter(int layoutResId, @Nullable List<SeckillListModel.DataBean.KillsBean> data,int activedId) {
        super(layoutResId, data);
        this.activedId = activedId;

    }

    @Override
    protected void convert(BaseViewHolder helper, SeckillListModel.DataBean.KillsBean item) {
        tv_add_remind = helper.getView(R.id.tv_add_remind);
        ll_root = helper.getView(R.id.ll_root);
        ivSpike = helper.getView(R.id.iv_item_spike_img);
        tvTitle = helper.getView(R.id.tv_item_spike_title);
        tvPrice = helper.getView(R.id.tv_item_spike_price);
        tvOldPrice = helper.getView(R.id.tv_item_spike_old_price);
        ivAdd = helper.getView(R.id.addCar);
        ivSoldOut = helper.getView(R.id.tv_sold_out);
        ivAddGood = helper.getView(R.id.tv_add_remind);
        tvPsc = helper.getView(R.id.tv_item_spike_specification);
        frameLayout = helper.getView(R.id.iv_bg);
        ivSoldOutLeft = helper.getView(R.id.iv_sold);
        tvSale = helper.getView(R.id.tv_item_spike_sales);
        mProgressBar = helper.getView(R.id.pb_item_spike);
        tvSoldSale = helper.getView(R.id.tv_item_spike_sales);
        tvTitle.setText(item.title);
        tvPrice.setText(item.price);
        tvOldPrice.setText(item.oldPrice);
        tvPsc.setText(item.spec);
        tvSale.setText(item.sales);
        mProgressBar.setProgressDrawable(mContext.getResources().getDrawable(R.drawable.spike_progress));
        mProgressBar.setProgress(Integer.parseInt(item.progress));
        tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); //中划线
        tvSoldSale.setText("已抢购" + item.progress + "%");
        String spikeFlag = UserInfoHelper.getSpikePosition(mContext);
        GlideModel.disPlayError(mContext,item.pic,ivSpike);


        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("dsgefrgfffffff......","sdsdsddwdw");
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    int activeId = item.activeId;
                    addCar(activeId, "", 2, "1");

                } else {
                    AppHelper.showMsg(mContext, "请先登录");
                    mContext.startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                }
            }
        });


        if(Integer.parseInt(spikeFlag)==0) {
            //未开始
//            if(item.warnMe==0) {
//                tv_add_remind.setText("添加提醒");
//                SharedPreferencesUtil.saveInt(mContext,"warnMe",0);
//            }else {
//                tv_add_remind.setText("取消提醒");
//                SharedPreferencesUtil.saveInt(mContext,"warnMe",1);
//            }
            tv_add_remind.setVisibility(View.VISIBLE);
        }else {
            // 已开始

            ivAdd.setVisibility(View.GONE);
            ivSoldOut.setVisibility(View.GONE);

            if(item.soldOut==0) {
                tv_add_remind.setVisibility(View.GONE);
            }else {
                ivSoldOut.setVisibility(View.GONE);
                ivAdd.setVisibility(View.VISIBLE);
            }
        }




        if (Integer.parseInt(spikeFlag) == 0) {
            //秒杀预告
            ivAdd.setVisibility(View.GONE);
            ivAddGood.setVisibility(View.VISIBLE);
            ivSoldOut.setVisibility(View.GONE);
            ivSoldOutLeft.setVisibility(View.GONE);
            frameLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            ivSoldOut.setVisibility(View.GONE);
        } else if (Integer.parseInt(spikeFlag) == 1) {

            if (item.soldOut == 0) {
                ivAddGood.setVisibility(View.GONE);
                ivAdd.setVisibility(View.VISIBLE);
                ivSoldOut.setVisibility(View.GONE);
                ivSoldOutLeft.setVisibility(View.GONE);

            } else {
                ivAddGood.setVisibility(View.GONE);
                ivAdd.setVisibility(View.GONE);
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

    /**
     * 获取提醒状态  SpikeActiveQueryAPI
     * @param activeId
     * @param item
     */
    private void getStat(int activeId, SeckillListModel.DataBean.KillsBean item) {
        SpikeActiveQueryAPI.requestData(mContext, activeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseModel seckillListModel) {
                        if(seckillListModel.success) {
                            if(SharedPreferencesUtil.getInt(mContext,"warnMe")==0) {
                                tv_add_remind.setText("取消提醒");
                                SharedPreferencesUtil.saveInt(mContext,"warnMe",1);
                                Log.d("once..........","ssdfgdssd11111");
                            }else {
                                tv_add_remind.setText("添加提醒");
                                SharedPreferencesUtil.saveInt(mContext,"warnMe",0);
                                Log.d("once..........","ssdfgdssd22222");
                            }
                        }else {
                            ToastUtil.showSuccessMsg(mContext,seckillListModel.message);
                        }
                    }
                });
    }

}
