package com.puyue.www.qiaoge.adapter.coupon;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.coupons.UseOrNotUseActivity;
import com.puyue.www.qiaoge.adapter.home.HomeGroupAdapter;
import com.puyue.www.qiaoge.model.mine.coupons.queryUserDeductByStateModel;


import java.util.List;

/**
 * Created by ${daff} on 2018/9/20
 */
public class MyCouponsAdapter  extends  BaseQuickAdapter <queryUserDeductByStateModel.DataBean.ListBean,BaseViewHolder> {
    private ImageView moneyBag;
    private TextView couponsTitle;
    private  TextView couponsCount;
    private  TextView time;
    private  TextView note;
    private  TextView priceNum;
    private ImageView imagestate;
    private  ImageView imageButton;
    private  Context context;
    private LinearLayout LinearLayoutView;
    private LinearLayout LinearLayoutCoupon;



    List<queryUserDeductByStateModel.DataBean.ListBean> list;
    public MyCouponsAdapter(int layoutResId, @Nullable List<queryUserDeductByStateModel.DataBean.ListBean> data,Context context) {
        super(layoutResId, data);
        list=data;
        this.context=context;
    }


    @Override
    protected void convert(final BaseViewHolder helper, queryUserDeductByStateModel.DataBean.ListBean item) {
        LinearLayoutView=helper.getView(R.id.LinearLayoutView);
        moneyBag=helper.getView(R.id.moneyBag);
        couponsTitle=helper.getView(R.id.couponsTitle);
        couponsCount=helper.getView(R.id.couponsCount);
        time=helper.getView(R.id.time);
        note=helper.getView(R.id.note);
        priceNum=helper.getView(R.id.priceNum);
        imagestate=helper.getView(R.id.imagestate);
        imageButton=helper.getView(R.id.imageButton);
        LinearLayoutCoupon=helper.getView(R.id.LinearLayoutCoupon);

        if(!TextUtils.isEmpty(item.getApplyFrom())){
           couponsTitle.setText(item.getApplyFrom());
        }

        couponsCount.setText(item.getGiftType()+"   "+item.getGiftName());
        time.setText(item.getDateTime());
        priceNum.setText(item.getAmount()+"");
        if (item.getRole().size()>0){
            note.setText(item.getRole().get(0));
            note.setVisibility(View.VISIBLE);
            LinearLayoutView.setVisibility(View.VISIBLE);
        }else {
            note.setText("");
            note.setVisibility(View.INVISIBLE);
            LinearLayoutView.setVisibility(View.GONE);
        }
        if(item.getState().equals("ENABLED")){  // State== ENABLED   可用使用的优惠卷
            // state(优惠券状态，,, ENABLED 可用的)
           imagestate.setVisibility(View.GONE);
            moneyBag.setImageResource(R.mipmap.ic_money_bag_yes);
            LinearLayoutCoupon.setBackgroundResource(R.mipmap.coupons_yes);
            imageButton.setVisibility(View.VISIBLE);
            if(helper.getLayoutPosition()==list.size()-1){
                imageButton.setVisibility(View.VISIBLE);
            }else {
                imageButton.setVisibility(View.GONE);
            }


        }else  if (item.getState().equals("USED")){//USED 已使用
            imageButton.setVisibility(View.GONE);
             imagestate.setVisibility(View.VISIBLE);
             imagestate.setImageResource(R.mipmap.ic_user);
          moneyBag.setImageResource(R.mipmap.ic_moner_no);
            LinearLayoutCoupon.setBackgroundResource(R.mipmap.coupons_no);

        }else if(item.getState().equals("OVERTIME")){ //OVERTIME 过期
            imageButton.setVisibility(View.GONE);
         imagestate.setVisibility(View.VISIBLE);
           moneyBag.setImageResource(R.mipmap.ic_moner_no);
            LinearLayoutCoupon.setBackgroundResource(R.mipmap.coupons_no);
            imagestate.setImageResource(R.mipmap.ic_overdue);

        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              context.startActivity(UseOrNotUseActivity.getIntent(mContext, UseOrNotUseActivity.class));

            }
        });

    }


}
