package com.puyue.www.qiaoge.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.order.NewOrderDetailActivity;
import com.puyue.www.qiaoge.api.home.DriverInfo;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.StringSpecialHelper;
import com.taobao.library.BaseBannerAdapter;
import com.taobao.library.VerticalBannerView;

import java.util.List;

/**
 * Created by ${王涛} on 2020/1/14
 */
class VerticalBannerAdapter extends BaseBannerAdapter<DriverInfo.DataBean> {
    private TextView tv_phone;
    private TextView tv_date;
    private TextView tv_no;
    private ImageView iv_icon;
    String cell;
    Context context;
    RelativeLayout rl_driver;
    public VerticalBannerAdapter(String cell, List<DriverInfo.DataBean> datas, Context context) {
        super(datas);
         this.context = context;
        this.cell = cell;
    }

    @Override
    public View getView(VerticalBannerView parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_driver,null);
    }

    @Override
    public void setItem(View view, DriverInfo.DataBean data) {
        tv_phone = view.findViewById(R.id.tv_phone);
        rl_driver = view.findViewById(R.id.rl_driver);
        tv_no = view.findViewById(R.id.tv_no);
        tv_date = view.findViewById(R.id.tv_date);
        iv_icon = view.findViewById(R.id.iv_icon);
        tv_date.setText(data.getSendTime());
        tv_no.setText("订单"+data.getOrderId());
        Glide.with(context).load(data.getIcon()).into(iv_icon);
        String s = data.getDriverName()+data.getDriverPhone()+"已出货，正在配送";
        String phone = String.valueOf(data.getDriverPhone());
        SpannableStringBuilder spannableStringBuilder = StringSpecialHelper.buildSpanColorStyle(s, data.getDriverName().length(),
                phone.length(), Color.parseColor("#ff5000"));
        tv_phone.setText(spannableStringBuilder);
        tv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + data.getDriverPhone()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        rl_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewOrderDetailActivity.class);
                intent.putExtra(AppConstant.ORDERID, data.getOrderId());
                context.startActivity(intent);
            }
        });

    }

}
