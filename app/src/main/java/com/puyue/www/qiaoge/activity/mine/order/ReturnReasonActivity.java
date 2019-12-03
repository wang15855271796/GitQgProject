package com.puyue.www.qiaoge.activity.mine.order;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.mine.order.ReturnGoodsAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.order.ReturnGoodsModel;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/17.
 */

public class ReturnReasonActivity extends BaseSwipeActivity {

    private ImageView mIvBack;
    private EditText mEditReturnReason;
    private Button mBtnNext;

    private String listString;
    private String orderId;
    private int orderType;
    private String returnProductMainId;

    private ReturnGoodsModel mModelReturnGoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleExtra(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        listString = getIntent().getStringExtra("listString");
        orderId = getIntent().getStringExtra("orderId");
        orderType = getIntent().getIntExtra("orderType", -1);
        returnProductMainId = getIntent().getStringExtra("returnProductMainId");
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_return_reason);
    }

    @Override
    public void findViewById() {
        mIvBack = (ImageView) findViewById(R.id.iv_return_reason_back);
        mEditReturnReason = (EditText) findViewById(R.id.edit_return_reason);
        mBtnNext = (Button) findViewById(R.id.btn_return_reason_next);
    }

    @Override
    public void setViewData() {
        mBtnNext.setEnabled(false);
        mBtnNext.setTextColor(getResources().getColor(R.color.app_btn_unable));
    }

    @Override
    public void setClickEvent() {
        mEditReturnReason.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (StringHelper.notEmptyAndNull(mEditReturnReason.getText().toString())) {
                    mBtnNext.setEnabled(true);
                    mBtnNext.setTextColor(getResources().getColor(R.color.app_color_white));
                } else {
                    mBtnNext.setEnabled(false);
                    mBtnNext.setTextColor(getResources().getColor(R.color.app_btn_unable));
                }
            }
        });
        mIvBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                finish();
            }
        });
        mBtnNext.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                //重新回到订单详情页,订单状态应该已经修改过来了,记得这个流程从头到尾都需要传递订单号码
                requestReturnGoods();
            }
        });
    }

    private void requestReturnGoods() {
        ReturnGoodsAPI.requestReturnGoods(mContext, orderId, orderType, mEditReturnReason.getText().toString(), listString, returnProductMainId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReturnGoodsModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ReturnGoodsModel returnGoodsModel) {
                        mModelReturnGoods = returnGoodsModel;
                        if (mModelReturnGoods.success) {
                            AppHelper.showMsg(mContext, "申请退货成功,请等待审核");
                            //回到订单详情页并且重新请求数据
                            Intent intent = new Intent(mContext, NewOrderDetailActivity.class);
                            intent.putExtra(AppConstant.ORDERID, orderId);
                            intent.putExtra(AppConstant.ORDERSTATE, "11");
                            intent.putExtra(AppConstant.RETURNPRODUCTMAINID, returnGoodsModel.data);
                            startActivity(intent);
                            finish();
                        } else {
                            AppHelper.showMsg(mContext, mModelReturnGoods.message);
                        }
                    }
                });
    }
}
