package com.puyue.www.qiaoge.adapter.mine;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.coupons.UserChooseDeductModel;
import com.puyue.www.qiaoge.model.mine.coupons.queryUserDeductByStateModel;

import java.util.List;

/**
 * @author daff
 * @date 2018/9/23.
 * 备注 选择优惠券
 */
public class ChooseCouponsAdapter extends BaseQuickAdapter<UserChooseDeductModel.DataBean.AllBean, BaseViewHolder> {

    private static final String WeiyiTAG=ChooseCouponsAdapter.class.getSimpleName();
    private TextView couponsTitle;
    private TextView couponsCount;
    private LinearLayout LinearLayoutCoupon;
    private TextView time;
    private TextView note;
    private TextView priceNum;
   private LinearLayout LinearLayoutOne;
    private LinearLayout LinearLayoutView;



    private TextView unCouponsTitle;
    private TextView unCouponsCount;
    private TextView unTime;
    private TextView unNote;
    private TextView unPriceNum;
    private ImageOnclick onclick;
    private LinearLayout LinearLayoutTwo;
    private LinearLayout LinearLayoutUnview;


    public ChooseCouponsAdapter(int layoutResId, @Nullable List<UserChooseDeductModel.DataBean.AllBean> data, ImageOnclick imageOnclick) {
        super(layoutResId, data);
        this.onclick = imageOnclick;
    }

    public void setOnclick(ImageOnclick onclick) {
        this.onclick = onclick;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final UserChooseDeductModel.DataBean.AllBean item) {


        couponsTitle = helper.getView(R.id.couponsTitle);
        couponsCount = helper.getView(R.id.couponsCount);
        LinearLayoutCoupon = helper.getView(R.id.LinearLayoutCoupon);
        time = helper.getView(R.id.time);
        note = helper.getView(R.id.note);
        priceNum = helper.getView(R.id.priceNum);
        LinearLayoutOne = helper.getView(R.id.LinearLayoutOne);
        LinearLayoutView=helper.getView(R.id.LinearLayoutView);
        unCouponsTitle = helper.getView(R.id.unCouponsTitle);
        unCouponsCount = helper.getView(R.id.unCouponsCount);
        unTime = helper.getView(R.id.unTime);
        unNote = helper.getView(R.id.unNote);
        unPriceNum = helper.getView(R.id.unPriceNum);
        LinearLayoutTwo = helper.getView(R.id.LinearLayoutTwo);
        LinearLayoutUnview=helper.getView(R.id.LinearLayoutUnview);




        if (item.getState().equals("ENABLED")) {
            couponsTitle.setText(item.getApplyFrom());
            //try
//            couponsTitle.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int i=0;
//                    i++;
//                    if(i%2==0){
//                        couponsTitle.setText("我是小弟");
//                    }else {
//                        couponsTitle.setText("我是大哥");
//                    }
//
//                }
//            });
            couponsCount.setText(item.getGiftType()+item.getGiftName());
            time.setText(item.getDateTime());
            priceNum.setText(item.getAmount() + "");
            //根据接口来判断这个图片的背景设置，这个做法会使联网状态下的图片一直重置。
//            LinearLayoutCoupon.setSelected(true);//或者false
//            Log.e(WeiyiTAG, "convert: "+item.isFlag() );
            LinearLayoutCoupon.setBackgroundResource(item.isFlag() ? R.mipmap.ic_coupon_ : R.mipmap.ic_coupon_new);
            Log.e(WeiyiTAG, "convert: "+item.isFlag() );
            LinearLayoutTwo.setVisibility(View.GONE);
            LinearLayoutOne.setVisibility(View.VISIBLE);
            if (item.getRole().size() > 0) {
                note.setText(item.getRole().get(0));
                LinearLayoutView.setVisibility(View.VISIBLE);
            }else {
                note.setText("");
                LinearLayoutView.setVisibility(View.GONE);
            }


        } else if (item.getState().equals("UN_ENABLED")){
            unCouponsTitle.setText(item.getApplyFrom());
            unCouponsCount.setText(item.getGiftType()+item.getGiftName());
            unTime.setText(item.getDateTime()+"");
            unPriceNum.setText(item.getAmount() + "");
            LinearLayoutTwo.setVisibility(View.VISIBLE);
          LinearLayoutOne.setVisibility(View.GONE);
            if (item.getRole().size() > 0) {
                LinearLayoutUnview.setVisibility(View.VISIBLE);
                unNote.setText(item.getRole().get(0));
            }else {
                LinearLayoutUnview.setVisibility(View.GONE);
                unNote.setText("");

            }
        }
        LinearLayoutOne.setOnClickListener(new NoDoubleClickListener() {
            @Override
             public void onNoDoubleClick(View view) {
                onclick.Onclick(helper.getLayoutPosition(), item.getGiftDetailNo());
              }
           }
        );

        //  onclick.Onclick(helper.getLayoutPosition(), item.getGiftDetailNo());
    }

    public interface ImageOnclick {
        void Onclick(int position, String giftDetailNo);
    }
}