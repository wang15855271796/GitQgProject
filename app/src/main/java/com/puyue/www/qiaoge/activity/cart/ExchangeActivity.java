package com.puyue.www.qiaoge.activity.cart;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.dialog.ExCouponDialog;
import com.puyue.www.qiaoge.model.cart.CartCommonGoodsModel;
import com.puyue.www.qiaoge.model.cart.ExChangeModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.puyue.www.qiaoge.utils.ToastUtil;
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
    @BindView(R.id.tv_exchange)
    TextView tv_exchange;
    @BindView(R.id.tv_amount)
    TextView tv_amount;
    int num = 0;
    private List<String> list = new ArrayList<>();
    private CouponListsAdapter couponListsAdapter;
    private ArrayList<String> mDatas = new ArrayList<>();
    List<ExChangeModel.DetailListBean> detailListBeans = new ArrayList<>();
    private String amount;
    public List<Double> amounts = new ArrayList<>();
    public List<Double> nums = new ArrayList<>();
//    private int amounts;
    EditText editTexts;

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
        couponListsAdapter = new CouponListsAdapter(mActivity,R.layout.item_coupon,list, new CouponListsAdapter.Onclick() {
            @Override
            public void exCoupon(EditText editText) {
                editTexts = editText;
                if(!editText.getText().toString().equals("")) {
                    double amount = Double.parseDouble(editText.getText().toString());
                    amounts.add(amount);
                }
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setAdapter(couponListsAdapter);
        iv_add.setOnClickListener(this);
        tv_exchange.setOnClickListener(this);
        bt_sure.setOnClickListener(this);
        tv_amount.setText(amount);
    }

    private List<String> initData() {
        for (int i = 0; i < 1; i++) {
            mDatas.add("sdsd"+i);
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
                if(editTexts!=null) {
                    editTexts.clearFocus();
                }

                if(editTexts.getText().toString().equals("")||editTexts.getText().toString().equals("0")) {
                    ToastUtil.showSuccessMsg(mActivity,"请填写金额");
                    return;
                }
                for (int i = 0; i <mDatas.size() ; i++) {
                    detailListBeans.add(new ExChangeModel.DetailListBean(amounts,1,amounts));
                }

                ExCouponDialog exCouponDialog = new ExCouponDialog(mActivity,detailListBeans);
                exCouponDialog.show();
                break;
            case R.id.iv_add:
//                couponListsAdapter.setCoupon();
                couponListsAdapter.addData(list.size());
//                couponListsAdapter.addDatas(list.size());
//                if(editTexts!=null) {
//                    if(editTexts.getText().toString().equals("")||editTexts.getText().toString().equals("0")) {
//                        ToastUtil.showSuccessMsg(mActivity,"请填写金额");
//                    }else {
//                        SharedPreferencesUtil.saveInt(mActivity,"nums",num);
//                        couponListsAdapter.addData(list.size());
//                    }
//                }


                break;

            case R.id.tv_exchange:
//                editTexts.clearFocus();
                couponListsAdapter.setCoupon(list.size(), Double.parseDouble(amount));
                if(amount.equals("0.0")) {
                    ToastUtil.showSuccessMsg(mActivity,"余额不足,无法兑换");
                }
                amounts.add(Double.valueOf(amount));
//                if(editTexts.getText().toString().equals("")||editTexts.getText().toString().equals("0")) {
//                    ToastUtil.showSuccessMsg(mActivity,"请填写金额");
//                }else {
//                    editTexts.setText(amount);
//                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {

            return true;
        }
        return super.onKeyDown(keyCode, event);//退出H5所在的Activity

    }
}
