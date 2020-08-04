package com.puyue.www.qiaoge.activity.cart;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.CouponsAdapter;
import com.puyue.www.qiaoge.adapter.CouponListsAdapter;
import com.puyue.www.qiaoge.adapter.CouponListssAdapter;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.dialog.ExCouponDialog;
import com.puyue.www.qiaoge.helper.BigDecimalUtils;
import com.puyue.www.qiaoge.model.cart.CartCommonGoodsModel;
import com.puyue.www.qiaoge.model.cart.ExChangeModel;
import com.puyue.www.qiaoge.model.cart.ItemModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.puyue.www.qiaoge.utils.ToastUtil;
import com.puyue.www.qiaoge.utils.Utils;
import com.puyue.www.qiaoge.view.KeyboardChangeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${王涛} on 2020/6/26
 */
public class ExchangeActivity extends BaseSwipeActivity implements View.OnClickListener {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.iv_add)
    ImageView iv_add;
    @BindView(R.id.bt_sure)
    Button bt_sure;
    @BindView(R.id.tv_consume)
    TextView tv_consume;
    @BindView(R.id.tv_exchange)
    TextView tv_exchange;
    @BindView(R.id.tv_amount)
    TextView tv_amount;
    int num = 0;
    private List<ItemModel> list = new ArrayList<>();
    private CouponListssAdapter couponListsAdapter;
    private ArrayList<ItemModel> mDatas = new ArrayList<>();
    List<ExChangeModel.DetailListBean> detailListBeans = new ArrayList<>();
    private String amount;
    public List<Double> amounts = new ArrayList<>();
    public List<Double> nums = new ArrayList<>();
    EditText editTexts;
    private Double Amount = 0.0;
    int Amounts = 0;
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_exchange);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(detailListBeans!=null) {
            detailListBeans.clear();
        }

    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);
        amount = getIntent().getStringExtra("amount");
        iv_back.setOnClickListener(this);
        list = initData();
//        couponListsAdapter = new CouponListsAdapter(ExchangeActivity.this,list,R.layout.item_coupon,amount,tv_amount,tv_consume);
        couponListsAdapter = new CouponListssAdapter(R.layout.item_coupon,list,amount,mActivity);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setAdapter(couponListsAdapter);
        iv_add.setOnClickListener(this);
        tv_exchange.setOnClickListener(this);
        bt_sure.setOnClickListener(this);
        tv_amount.setText(amount);

        couponListsAdapter.setOnArticleClickListener(new CouponListssAdapter.OnArticleClickListener() {

            @Override
            public void onArticleOnItemClick(EditText et) {
                Amount = 0.0;
                for (int i = 0; i < couponListsAdapter.getItemCount(); i++) {
                    String amount = couponListsAdapter.getItem(i).getNum();
                    if (TextUtils.isEmpty(amount)) {

                        amount = "0";
                    }
                    Amount = BigDecimalUtils.add(Double.parseDouble(amount),Amount);
                }
                tv_consume.setText("消费金额"+Amount+"元");
                tv_amount.setText((BigDecimalUtils.sub(Double.parseDouble(amount),Amount))+"");
                Double amounts = Double.valueOf(amount);
                if(Amount-amounts==0.0) {
//                    et.setText("sss");
                    tv_amount.setText(0+"");
                    tv_consume.setText("消费金额"+amount+"元");
//                    ToastUtil.showSuccessMsg(mContext,"兑换余额不足");

                }else if(BigDecimalUtils.sub(Amount,amounts)>0.0){
                    tv_amount.setText(0+"");
                    tv_consume.setText("消费金额"+amount+"元");
                }
            }
        });

    }

    private List<ItemModel> initData() {
        for (int i = 0; i < 1; i++) {
            mDatas.add(new ItemModel());
        }
        return mDatas;
    }

    @Override
    public void setViewData() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

    }

    @Override
    public void setClickEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.bt_sure:
                detailListBeans.clear();
                couponListsAdapter.addDatas(tv_consume);

                break;
            case R.id.iv_add:
                couponListsAdapter.addData(new ItemModel(),amount);
                break;

            case R.id.tv_exchange:
                if(Utils.isFastClick()) {
                    return;
                }else {
                    if(amount.equals("0.0")) {
                        ToastUtil.showSuccessMsg(mActivity,"余额不足,无法兑换");
                    }else {
                        couponListsAdapter.setCoupon(amount);

                    }
                }



                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
