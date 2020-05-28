package com.puyue.www.qiaoge.activity.mine.order;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.autonavi.amap.mapcore.interfaces.IAMap;
import com.lljjcoder.style.citylist.Toast.AlarmDailog;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.mine.ReturnDetailOrderAdapter;
import com.puyue.www.qiaoge.api.mine.order.CancelReturnAPI;
import com.puyue.www.qiaoge.api.mine.order.NewReturnOderAPI;
import com.puyue.www.qiaoge.base.BaseActivity;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.model.mine.order.NewReturnOrderModel;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王文博} on 2019/8/27
 * 只显示退货商品详情
 */
public class ReturnGoodDetailActivity extends BaseActivity {
    private TextView tvOrderTitle;//状态标题
    private TextView tv_return_success;//退货成功
    private TextView tvOrderContent;//状态内容
    private ImageView iv_cancel_return;//撤销退货
    private TextView tv_cancel_return;//撤销退货
    private String returnProductMainId;
    private TextView tv_return_amount;//退货金额
    private TextView tv_return_way;//退货去向
    private TextView tv_return_reason;//退货原因
    private TextView tv_return_order;//订单编号
    private TextView tv_return_name;//订单申请人
    private TextView tv_return_time;//订单申请时间
    private ImageView imageViewBreak;
    private RecyclerView recycler_good;
    private List<NewReturnOrderModel.DataBean.ProductsBean> mProductList = new ArrayList<>();
    private ReturnDetailOrderAdapter returnDetailOrderAdapter;
    //0,审核 1，成功, 2，失败 4，已撤销
    private int orderDeliveryType;
    private RelativeLayout rl_order;
    private String orderId;
    private RelativeLayout rl_return_content;
    private TextView tv_return_content;//退款说明

    private RelativeLayout rl_accept;
    private TextView tv_accept_time;//审核时间
    private TextView tv_amount_content;
    private TextView tv_amount_spec;
    private String returnContent;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_return_good_detail);
    }

    @Override
    public void findViewById() {
        tvOrderTitle = findViewById(R.id.tvOrderTitle);
        tv_return_success = findViewById(R.id.tv_return_success);
        tvOrderContent = findViewById(R.id.tvOrderContent);
        iv_cancel_return = findViewById(R.id.iv_cancel_return);
        tv_cancel_return = findViewById(R.id.tv_cancel_return);
        tv_return_amount = findViewById(R.id.tv_return_amount);
        tv_return_way = findViewById(R.id.tv_return_way);
        tv_return_reason = findViewById(R.id.tv_return_reason);
        tv_return_order = findViewById(R.id.tv_return_order);
        tv_return_name = findViewById(R.id.tv_return_name);
        tv_return_time = findViewById(R.id.tv_return_time);
        imageViewBreak = findViewById(R.id.imageViewBreak);
        recycler_good = findViewById(R.id.recycler_good);
        rl_order = findViewById(R.id.rl_order);
        rl_return_content = findViewById(R.id.rl_return_content);
        tv_return_content = findViewById(R.id.tv_return_content);
        rl_accept = findViewById(R.id.rl_accept);
        tv_accept_time = findViewById(R.id.tv_accept_time);
        tv_amount_content = findViewById(R.id.tv_amount_content);
        tv_amount_spec = findViewById(R.id.tv_amount_spec);


    }

    @Override
    public void setViewData() {
        returnProductMainId = getIntent().getStringExtra(AppConstant.RETURNPRODUCTMAINID);
        orderDeliveryType = getIntent().getIntExtra("orderType", 0);
        mProductList.clear();
        getData();
    }

    @Override
    public void setClickEvent() {
        imageViewBreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        iv_cancel_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //撤销申请
                cancelReturnOrder();
            }
        });

        rl_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //0配送 1自提
                if (orderDeliveryType == 0) {
                    Intent intent = new Intent(mContext, NewOrderDetailActivity.class);
                    intent.putExtra(AppConstant.ORDERID, orderId);
                    intent.putExtra(AppConstant.ORDERSTATE, "11");
                   // intent.putExtra(AppConstant.RETURNPRODUCTMAINID, returnProductMainId);
                    intent.putExtra("goAccount", "goAccount");
                    startActivity(intent);
                } else if (orderDeliveryType == 1) {
                    Intent intent = new Intent(mContext, SelfSufficiencyOrderDetailActivity.class);
                    intent.putExtra(AppConstant.ORDERID, orderId);
                    intent.putExtra(AppConstant.ORDERSTATE, "11");
                //    intent.putExtra(AppConstant.RETURNPRODUCTMAINID, returnProductMainId);
                    intent.putExtra("goAccount", "goAccount");
                    startActivity(intent);

                }
            }
        });

        rl_return_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (returnContent != null && StringHelper.notEmptyAndNull(returnContent)) {
                    AlertDialog dialog = new AlertDialog.Builder(mContext, R.style.DialogStyle).create();
                    dialog.show();
                    dialog.setContentView(R.layout.return_order_content);
                    dialog.setCanceledOnTouchOutside(true);

                    TextView tv_ok = dialog.getWindow().findViewById(R.id.tv_ok);
                    TextView tv_order_content = dialog.getWindow().findViewById(R.id.tv_order_content);

                    tv_order_content.setText(returnContent);

                    tv_ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }


            }
        });
    }


    private void cancelReturnOrder() {
        CancelReturnAPI.requestCancelOrder(mContext, returnProductMainId)
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
                    public void onNext(BaseModel baseModel) {
                        if (baseModel.success) {
                            tvOrderTitle.setVisibility(View.GONE);
                            tvOrderContent.setVisibility(View.GONE);
                            iv_cancel_return.setVisibility(View.GONE);
                            tv_return_success.setVisibility(View.VISIBLE);
                            tv_return_success.setText("申请已撤销");
                            AppHelper.showMsg(mContext, "撤销成功");

                            getData();
                        } else {
                            AppHelper.showMsg(mContext, baseModel.message);
                        }
                    }
                });


    }
    private int returnState;
    private void getData() {
        NewReturnOderAPI.requestNewReturnDetail(mContext, returnProductMainId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewReturnOrderModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NewReturnOrderModel newReturnOrderModel) {
                        if (newReturnOrderModel.isSuccess()) {
                            orderId = newReturnOrderModel.getData().getOrderId();
                            returnState=newReturnOrderModel.getData().getReturnState();
                            if (newReturnOrderModel.getData().getReturnState() == 1) {
                                tv_amount_spec.setVisibility(View.GONE);
                                rl_return_content.setVisibility(View.VISIBLE);
                                returnContent = newReturnOrderModel.getData().getReturnMemo();
                                if (newReturnOrderModel.getData().getReturnMemo().equals("无")) {
                                    tv_return_content.setText(newReturnOrderModel.getData().getReturnMemo());
                                    rl_return_content.setEnabled(false);
                                } else {
                                    rl_return_content.setEnabled(true);
                                    tv_return_content.setText("");
                                }

                            } else {
                                rl_return_content.setVisibility(View.GONE);
                                tv_amount_spec.setVisibility(View.VISIBLE);
                            }

                            if (newReturnOrderModel.getData().getReturnState() == 1 || newReturnOrderModel.getData().getReturnState() == 2) {
                                rl_accept.setVisibility(View.VISIBLE);
                                tv_accept_time.setText(newReturnOrderModel.getData().getCheckDate());
                            } else {
                                rl_accept.setVisibility(View.GONE);
                            }

                            if (newReturnOrderModel.getData().getReturnState() == 0) {
                                tvOrderTitle.setVisibility(View.VISIBLE);
                                tvOrderContent.setVisibility(View.VISIBLE);
                                iv_cancel_return.setVisibility(View.VISIBLE);
                                tv_return_success.setVisibility(View.GONE);
                                tvOrderContent.setText(newReturnOrderModel.getData().titleText);
                            } else if (newReturnOrderModel.getData().getReturnState() == 1) {
                                tv_return_success.setVisibility(View.VISIBLE);
                                tv_return_success.setText("退款成功");
                                tvOrderTitle.setVisibility(View.GONE);
                                tvOrderContent.setVisibility(View.VISIBLE);
                                tvOrderContent.setText(newReturnOrderModel.getData().titleText);
                                iv_cancel_return.setVisibility(View.GONE);
                            } else if (newReturnOrderModel.getData().getReturnState() == 2) {
                                tv_return_success.setVisibility(View.VISIBLE);
                                tvOrderTitle.setVisibility(View.GONE);
                                tv_return_success.setText("审核未通过");
                                tvOrderContent.setVisibility(View.VISIBLE);
                                tvOrderContent.setText("说明:" + newReturnOrderModel.getData().titleText);
                                iv_cancel_return.setVisibility(View.GONE);
                            } else if (newReturnOrderModel.getData().getReturnState() == 4) {
                                tvOrderTitle.setVisibility(View.GONE);
                                tvOrderContent.setVisibility(View.GONE);
                                iv_cancel_return.setVisibility(View.GONE);
                                tv_return_success.setVisibility(View.VISIBLE);
                                tv_return_success.setText("申请已撤销");
                            }

                            if (newReturnOrderModel.getData().getReturnState() == 1) {
                                tv_return_amount.setText("￥" + newReturnOrderModel.getData().getActualAmount());
                                tv_amount_content.setText("退款金额");
                            } else {
                                tv_return_amount.setText("￥" + newReturnOrderModel.getData().getReturnAmount());
                                tv_amount_content.setText("预计退款金额");
                            }

                            tv_return_way.setText(newReturnOrderModel.getData().getReturnChannel());
                            tv_return_reason.setText(newReturnOrderModel.getData().getReturnReason());
                            tv_return_order.setText(newReturnOrderModel.getData().getOrderId());
                            tv_return_name.setText(newReturnOrderModel.getData().getApplier());
                            tv_return_time.setText(newReturnOrderModel.getData().getApplyDate());
                            if (newReturnOrderModel.getData().getProducts().size() > 0) {
                                mProductList.clear();
                                mProductList.addAll(newReturnOrderModel.getData().getProducts());
                                recycler_good.setLayoutManager(new LinearLayoutManager(mContext));
                                returnDetailOrderAdapter = new ReturnDetailOrderAdapter(R.layout.return_order_detail_item, mProductList,returnState,orderId);
                                recycler_good.setAdapter(returnDetailOrderAdapter);
                            }

                        } else {
                            AppHelper.showMsg(mContext, newReturnOrderModel.getMessage());
                        }
                    }
                });

    }
}
