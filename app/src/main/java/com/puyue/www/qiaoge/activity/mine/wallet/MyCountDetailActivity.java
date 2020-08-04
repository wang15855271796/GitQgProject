package com.puyue.www.qiaoge.activity.mine.wallet;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.NewWebViewActivity;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.order.NewOrderDetailActivity;
import com.puyue.www.qiaoge.activity.mine.order.ReturnGoodDetailActivity;
import com.puyue.www.qiaoge.activity.mine.order.SelfSufficiencyOrderDetailActivity;
import com.puyue.www.qiaoge.adapter.home.RelatedRecordAdapter;
import com.puyue.www.qiaoge.api.home.GetWalletDetailAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.DividerItemDecoration;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.home.WalletDetailModel;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * 账单详情
 * Created by ${王文博} on 2019/8/16
 */
public class MyCountDetailActivity extends BaseSwipeActivity {
    private TextView tv_bill_type;//账单类型
    private ImageView iv_flag;//类型标签
    private TextView tv_amount; //账单金额


    private TextView tv_order_status; //发货状态

    private LinearLayout ll_one; //关联记录
    private TextView tv_recode;//关联标题
    private ImageView iv_arrow;//关联箭头
    View v1;
    LinearLayout ll_desc;
    LinearLayout ll_orderId;
    private TextView tv_vip_date;//会员到期时间

    private TextView tv_pay;//支付方式
    private TextView tv_pay_order;//支付方法

    private TextView tv_explain;//说明
    private TextView tv_explain_content;//说明内容


    private LinearLayout ll_detail;//商品详情

    private TextView tv_detail; //详情内容

    private TextView tv_order_time;//创建时间

    private TextView tv_order_num;//订单号

    private TextView tv_order_user;//用户名

    //账单类型标签
    private int type;
    private int id;

    private LinearLayout ll_two;

    private LinearLayout ll_user;


    private String vipUrl;
    private String orderStatus;
    private int orderDeliverType;

    private String orderId;

    private String returnProductMainId;
    private String returnMainId;

    private Toolbar toolbar;

    private List<Integer> mReturnList = new ArrayList<>();

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.bill_detail_activity);
    }

    @Override
    public void findViewById() {
        v1 = findViewById(R.id.v1);
        ll_desc = findViewById(R.id.ll_desc);
        ll_orderId = findViewById(R.id.ll_orderId);
        tv_bill_type = findViewById(R.id.tv_bill_type);
        iv_flag = findViewById(R.id.iv_flag);
        tv_amount = findViewById(R.id.tv_amount);
        ll_one = findViewById(R.id.ll_one);
        tv_order_status = findViewById(R.id.tv_order_status);
        tv_recode = findViewById(R.id.tv_recode);
        iv_arrow = findViewById(R.id.iv_arrow);
        tv_vip_date = findViewById(R.id.tv_vip_date);
        tv_pay = findViewById(R.id.tv_pay);
        tv_pay_order = findViewById(R.id.tv_pay_order);
        tv_explain = findViewById(R.id.tv_explain);
        tv_explain_content = findViewById(R.id.tv_explain_content);
        ll_detail = findViewById(R.id.ll_detail);
        tv_detail = findViewById(R.id.tv_detail);
        tv_order_time = findViewById(R.id.tv_order_time);
        tv_order_num = findViewById(R.id.tv_order_num);
        tv_order_user = findViewById(R.id.tv_order_user);
        ll_two = findViewById(R.id.ll_two);
        ll_user = findViewById(R.id.ll_user);
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public void setViewData() {

        id = getIntent().getIntExtra("id", 0);
        type = getIntent().getIntExtra("type", 0);
        getData(id);

    }

    RelatedRecordAdapter adapter;

    @Override
    public void setClickEvent() {
        toolbar.setNavigationOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                finish();
            }
        });

        ll_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 1) {


                    if (orderDeliverType == 0) {

                        Intent intent = new Intent(mContext, NewOrderDetailActivity.class);
                        intent.putExtra(AppConstant.ORDERID, orderId);
                        intent.putExtra(AppConstant.ORDERSTATE, orderStatus);
                        //  intent.putExtra(AppConstant.RETURNPRODUCTMAINID, returnProductMainId);
                        intent.putExtra("goAccount", "goAccount");
                        startActivity(intent);

                    } else if (orderDeliverType == 1) {
                        Intent intent = new Intent(mContext, SelfSufficiencyOrderDetailActivity.class);
                        intent.putExtra(AppConstant.ORDERID, orderId);
                        intent.putExtra(AppConstant.ORDERSTATE, orderStatus);
                        //      intent.putExtra(AppConstant.RETURNPRODUCTMAINID, returnProductMainId);
                        intent.putExtra("goAccount", "goAccount");
                        startActivity(intent);

                    }


                } else if (type == 4) {
                    Intent intent = new Intent(mContext, ReturnGoodDetailActivity.class);
                    intent.putExtra("orderType", 1);
                    intent.putExtra(AppConstant.RETURNPRODUCTMAINID, returnMainId);

                    mContext.startActivity(intent);
                } else if (type == 2 || type == 3 || type == 5)

                {

                    String num = "0";
                    Intent intent = new Intent(mActivity, MyWalletNewActivity.class);

                    UserInfoHelper.saveUserWalletNum(mContext, num);
                    startActivity(intent);


                } else if (type == 7 || type == 8)

                {
                    Intent intent = new Intent(mActivity, NewWebViewActivity.class);
                    intent.putExtra("URL", vipUrl);
                    intent.putExtra("TYPE", 1);
                    intent.putExtra("name", "");
                    startActivity(intent);
                }


            }
        });


        ll_one.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                if (type == 1 || type == 4) {
                    final Dialog dialog = new Dialog(mActivity, R.style.DialogTheme);
                    View view = View.inflate(mActivity, R.layout.deliver_time_popu, null);

                    RecyclerView ry_select = view.findViewById(R.id.ry_select);
                    TextView tv_cancel_reason = view.findViewById(R.id.tv_cancel_reason);
                    TextView tv_buy_order = view.findViewById(R.id.tv_buy_order);
                    View tv_line = view.findViewById(R.id.tv_line);


                    dialog.setContentView(view);

                    dialog.setCanceledOnTouchOutside(true);
                    Window dialogWindow = dialog.getWindow();
                    WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    lp.gravity = Gravity.BOTTOM;
                    dialogWindow.setAttributes(lp);

                    dialog.show();
                    tv_cancel_reason.setVisibility(View.GONE);
                    tv_buy_order.setVisibility(View.VISIBLE);
                    tv_line.setVisibility(View.VISIBLE);
                    tv_buy_order.setText("购买订单");
                    tv_buy_order.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (orderDeliverType == 0) {

                                Intent intent = new Intent(mContext, NewOrderDetailActivity.class);
                                intent.putExtra(AppConstant.ORDERID, orderId);
                                intent.putExtra(AppConstant.ORDERSTATE, orderStatus);
                                //  intent.putExtra(AppConstant.RETURNPRODUCTMAINID, returnProductMainId);
                                intent.putExtra("goAccount", "goAccount");
                                startActivity(intent);

                            } else if (orderDeliverType == 1) {
                                Intent intent = new Intent(mContext, SelfSufficiencyOrderDetailActivity.class);
                                intent.putExtra(AppConstant.ORDERID, orderId);
                                intent.putExtra(AppConstant.ORDERSTATE, orderStatus);
                                //      intent.putExtra(AppConstant.RETURNPRODUCTMAINID, returnProductMainId);
                                intent.putExtra("goAccount", "goAccount");
                                startActivity(intent);

                            }
                            dialog.dismiss();
                        }
                    });


                    ry_select.setLayoutManager(new LinearLayoutManager(mActivity));

                    if (mReturnList.size() > 0) {
                        adapter = new RelatedRecordAdapter(R.layout.select_reason, mReturnList);
                        ry_select.setAdapter(adapter);
                        //添加分隔线
                        DividerItemDecoration dividerPreKillDecoration = new DividerItemDecoration(mActivity,
                                DividerItemDecoration.VERTICAL_LIST);
                        dividerPreKillDecoration.setDivider(R.drawable.app_divider);
                        ry_select.addItemDecoration(dividerPreKillDecoration);


                        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                dialog.dismiss();
                                Intent intent = new Intent(mContext, ReturnGoodDetailActivity.class);

                                intent.putExtra(AppConstant.RETURNPRODUCTMAINID, mReturnList.get(position) + "");

                                startActivity(intent);

                            }
                        });
                    }


                } else if (type == 3 || type == 5) {
                    String num = "0";
                    Intent intent = new Intent(mActivity, MyWalletNewActivity.class);

                    UserInfoHelper.saveUserWalletNum(mContext, num);
                    startActivity(intent);


                }
            }

        });


    }


    private void getData(int id) {
        GetWalletDetailAPI.requestWalletDetail(mContext, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WalletDetailModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WalletDetailModel walletDetailModel) {

                        if (walletDetailModel.isSuccess()) {
                            mReturnList.clear();
                            orderId = walletDetailModel.getData().getOrderId();
                            returnMainId = walletDetailModel.getData().returnMainId;
                            orderDeliverType = walletDetailModel.getData().orderDeliveryType;
                            if (walletDetailModel.getData().returnMainIdList != null && walletDetailModel.getData().returnMainIdList.size() > 0) {

                                returnProductMainId = String.valueOf(walletDetailModel.getData().returnMainIdList.get(0).byteValue());
                                mReturnList.addAll(walletDetailModel.getData().returnMainIdList);
                            }
                            orderStatus = walletDetailModel.getData().orderStatus;

                            vipUrl = walletDetailModel.getData().goUrl;

                            tv_amount.setText(String.valueOf(walletDetailModel.getData().amount));
                            tv_bill_type.setText(walletDetailModel.getData().flowRecordTypeStr);
                            tv_pay_order.setText(walletDetailModel.getData().getChannelType());

                            tv_order_time.setText(walletDetailModel.getData().getTime());
                            tv_order_num.setText(walletDetailModel.getData().getOrderId());

                            if (walletDetailModel.getData().getSubUser() != null && StringHelper.notEmptyAndNull(String.valueOf(walletDetailModel.getData().getSubUser()))) {
                                ll_user.setVisibility(View.VISIBLE);
                                tv_order_user.setText(String.valueOf(walletDetailModel.getData().getSubUser()));
                            } else {
                                ll_user.setVisibility(View.GONE);
                            }
                            if (type == 1) {
                                if (walletDetailModel.getData().returnMainIdList != null && walletDetailModel.getData().returnMainIdList.size() > 0) {
                                    ll_one.setVisibility(View.VISIBLE);
                                    tv_recode.setText("关联记录");
                                    iv_arrow.setVisibility(View.VISIBLE);
                                    tv_vip_date.setVisibility(View.GONE);
                                    tv_detail.setText("订单详情");

                                } else {
                                    ll_one.setVisibility(View.GONE);
                                    tv_detail.setText("订单详情");
                                }
                            } else if (type == 4) {
                                ll_one.setVisibility(View.VISIBLE);
                                tv_recode.setText("关联记录");
                                iv_arrow.setVisibility(View.VISIBLE);
                                tv_vip_date.setVisibility(View.GONE);
                                tv_detail.setText("订单详情");
                            } else if (type == 2) {
                                ll_one.setVisibility(View.VISIBLE);
                                tv_recode.setText("付款方式");
                                iv_arrow.setVisibility(View.GONE);
                                tv_vip_date.setVisibility(View.VISIBLE);
                                tv_vip_date.setText(walletDetailModel.getData().getChannelType());
                                tv_detail.setText("我的余额");
                            } else if (type == 7 || type == 8) {
                                ll_one.setVisibility(View.VISIBLE);
                                tv_recode.setText("会员到期日");
                                iv_arrow.setVisibility(View.GONE);
                                tv_vip_date.setVisibility(View.VISIBLE);
                                tv_vip_date.setText(String.valueOf(walletDetailModel.getData().getExpireTime()));
                                tv_detail.setText("会员中心");

                            } else if (type == 3 || type == 5) {
                                ll_one.setVisibility(View.VISIBLE);
                                tv_recode.setText("我的余额");
                                iv_arrow.setVisibility(View.VISIBLE);
                                tv_vip_date.setVisibility(View.GONE);

                            }else if(type == 9) {
                                tv_recode.setText("使用条件");
                                tv_vip_date.setText("无门槛");
                                tv_vip_date.setVisibility(View.VISIBLE);
                                tv_pay_order.setText("平台余额");
                                ll_detail.setVisibility(View.GONE);
                                v1.setVisibility(View.GONE);
                                ll_desc.setVisibility(View.GONE);
                                ll_orderId.setVisibility(View.GONE);
                            }


                            if (type == 1) {
                                ll_two.setVisibility(View.VISIBLE);
                                tv_pay.setText("支付方式");
                                tv_pay_order.setText(walletDetailModel.getData().getChannelType());
                                tv_explain.setText("积分奖励");


                            } else if (type == 4) {
                                tv_explain.setText("商品说明");
                                ll_two.setVisibility(View.VISIBLE);
                                tv_pay.setText("退款方式");
                                tv_pay_order.setText(walletDetailModel.getData().getChannelType());
                            } else if (type == 2 || type == 7 || type == 8) {
                                ll_two.setVisibility(View.VISIBLE);
                                tv_pay.setText("付款方式");
                                tv_explain.setText("商品说明");
                                tv_pay_order.setText(walletDetailModel.getData().getChannelType());
                            } else if (type == 5 || type == 3) {

                                ll_two.setVisibility(View.GONE);

                            }
                            tv_explain_content.setText(walletDetailModel.getData().getProDesc());

                            if (walletDetailModel.getData().amount.doubleValue() > 0) {
                                tv_amount.setTextColor(Color.parseColor("#fff6551a"));
                            } else {
                                tv_amount.setTextColor(Color.parseColor("#ff333333"));
                            }

                            Glide.with(mContext).load(walletDetailModel.getData().iconUrl).into(iv_flag);


                        } else {
                            AppHelper.showMsg(mContext, walletDetailModel.getMessage());
                        }


                    }
                });

    }
}
