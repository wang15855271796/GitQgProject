package com.puyue.www.qiaoge.fragment.home;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.home.DriverInfo;
import com.puyue.www.qiaoge.api.home.GetCustomerPhoneAPI;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringSpecialHelper;
import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2020/1/5
 */
public class DriverAdapter extends BaseQuickAdapter<DriverInfo.DataBean,BaseViewHolder> {

    private TextView tv_phone;
    private ImageView iv_icon;
    String cell;
    public DriverAdapter(int layoutResId, @Nullable List<DriverInfo.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DriverInfo.DataBean item) {
        tv_phone = helper.getView(R.id.tv_phone);
        helper.setText(R.id.tv_no,"订单"+item.getOrderId());
        helper.setText(R.id.tv_date,item.getSendTime());
        iv_icon = helper.getView(R.id.iv_icon);
        Glide.with(mContext).load(item.getIcon()).into(iv_icon);
        String s = item.getDriverName()+item.getDriverPhone()+"已出货，正在配送";
        SpannableStringBuilder spannableStringBuilder = StringSpecialHelper.buildSpanColorStyle(s, item.getDriverName().length(),
                11, Color.parseColor("#ff5000"));
        tv_phone.setText(spannableStringBuilder);
        getCustomerPhone();
        tv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + cell));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

    }

    private void getCustomerPhone() {
        GetCustomerPhoneAPI.requestData(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetCustomerPhoneModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetCustomerPhoneModel getCustomerPhoneModel) {
                        if (getCustomerPhoneModel.isSuccess()) {
                            cell = getCustomerPhoneModel.getData();
                        } else {
                            AppHelper.showMsg(mContext, getCustomerPhoneModel.getMessage() );
                        }
                    }
                });
    }

}
