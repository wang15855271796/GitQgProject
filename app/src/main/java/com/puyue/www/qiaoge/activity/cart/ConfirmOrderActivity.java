package com.puyue.www.qiaoge.activity.cart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.android.tu.loadingdialog.LoadingDailog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.puyue.www.qiaoge.QiaoGeApplication;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.account.AddressListActivity;
import com.puyue.www.qiaoge.activity.mine.coupons.ChooseCouponsActivity;
import com.puyue.www.qiaoge.adapter.cart.ConFirmOrderAdapter;
import com.puyue.www.qiaoge.api.cart.OrderPayAPI;
import com.puyue.www.qiaoge.api.home.GetOrderDetailAPI;
import com.puyue.www.qiaoge.api.mine.order.ChangeOrderAddressAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.WeChatPayEvent;
import com.puyue.www.qiaoge.event.WeChatUnPayEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.helper.GlideRoundTransform;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.cart.GetOrderDetailModel;
import com.puyue.www.qiaoge.model.cart.OrderNumModel;
import com.puyue.www.qiaoge.model.cart.OrderPayModel;
import com.puyue.www.qiaoge.model.mine.order.ChangeOrderAddressModel;
import com.puyue.www.qiaoge.view.GlideModel;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/24.
 */

public class ConfirmOrderActivity extends BaseSwipeActivity {
    private ImageView mIvBack;
    private LinearLayout mLlOrder;
    private EditText mEtNote;

    private LinearLayout mLlAddress;
    private TextView mTvName;
    private TextView mTvPhone;
    private TextView mTvAddress;
    private TextView mTvFirmName;
    private LinearLayout mLlAddAddress;

    private RadioGroup mRgPay;

    private TextView mTvTotalMoney;
    private TextView mTvGoPay;
    private TextView mTvTip;

    private String orderId;
    private double payAmount;
    private byte payChannel;
    private String remark;
    private String outTradeNo;


    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    private ChangeOrderAddressModel mModelChangeOrderAddress;
    private LoadingDailog dialog;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            orderId = bundle.getString(AppConstant.ORDERID);
        }
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        handleExtra(savedInstanceState);
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_confirm_order);
    }

    @Override
    public void findViewById() {
        mIvBack = FVHelper.fv(this, R.id.iv_activity_back);
        mLlOrder = FVHelper.fv(this, R.id.ll_activity_confirm_order);
        mEtNote = FVHelper.fv(this, R.id.et_activity_note);

        mLlAddress = FVHelper.fv(this, R.id.ll_activity_order_address);
        mLlAddAddress = FVHelper.fv(this, R.id.ll_activity_order_add_address);
        mTvName = FVHelper.fv(this, R.id.tv_activity_order_name);
        mTvPhone = FVHelper.fv(this, R.id.tv_activity_order_phone);
        mTvAddress = FVHelper.fv(this, R.id.tv_activity_order_address);
        mTvFirmName = FVHelper.fv(this, R.id.tv_activity_order_firm_name);

        mRgPay = FVHelper.fv(this, R.id.rg_activity_order);
        mTvTotalMoney = FVHelper.fv(this, R.id.tv_activity_order_total_money);
        mTvTip = FVHelper.fv(this, R.id.tv_activity_tip);
        mTvGoPay = FVHelper.fv(this, R.id.tv_activity_order_gopay);

    }

    @Override
    public void setViewData() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(mContext)
                .setMessage("刷新数据中")
                .setCancelable(false)
                .setCancelOutside(false);
        dialog = loadBuilder.create();
        String hintText = "如有需要,请输入备注内容&#8194<img src=\"" + R.mipmap.icon_edittext + "\" />";
        mEtNote.setHint(Html.fromHtml(hintText, imageGetter, null));
        //获取数据
        getOrderDetail(orderId);
    }

    /**
     * 提交订单
     */
    private void orderPay(final String orderId, final byte payChannel, double payAmount, String remark) {
        OrderPayAPI.requestData(mContext, orderId, payChannel, payAmount, remark)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderPayModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(OrderPayModel orderPayModel) {
                        if (orderPayModel.success) {
                            outTradeNo = orderPayModel.data.outTradeNo;
                            if (payChannel == 1) {
                                //余额支付
                                Intent intent = new Intent(mContext, PayResultActivity.class);
                                intent.putExtra(AppConstant.PAYCHANNAL, payChannel);
                                intent.putExtra(AppConstant.OUTTRADENO, orderPayModel.data.outTradeNo);
                                intent.putExtra(AppConstant.ORDERID, orderId);
                                startActivity(intent);
                                finish();
                            } else if (payChannel == 2) {
                                //支付宝支付
                                aliPay(orderPayModel.data.appPayRequest);
                            } else if (payChannel == 3) {
                                //微信支付
                                weChatPay(orderPayModel.data.appPayRequest);
                            }
                        } else {
                            AppHelper.showMsg(ConfirmOrderActivity.this, orderPayModel.message);
                        }
                    }
                });
    }

    /**
     * 微信支付
     */
    private void weChatPay(String json) {
        try {
            IWXAPI api = WXAPIFactory.createWXAPI(this, "wxbc18d7b8fee86977");
            JSONObject obj = new JSONObject(json);
            PayReq request = new PayReq();
            request.appId = obj.optString("appId");
            request.partnerId = obj.optString("mchID");
            request.prepayId = obj.optString("prepayId");
            request.packageValue = obj.optString("pkg");
            request.nonceStr = obj.optString("nonceStr");
            request.timeStamp = obj.optString("timeStamp");
            request.sign = obj.optString("paySign");
            api.sendReq(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 微信支付的回调,使用的eventBus.......((‵□′))
     **/
    @Subscribe
    public void onEventMainThread(WeChatPayEvent event) {
        Intent intent = new Intent(mContext, PayResultActivity.class);
        intent.putExtra(AppConstant.PAYCHANNAL, payChannel);
        intent.putExtra(AppConstant.OUTTRADENO, outTradeNo);
        intent.putExtra(AppConstant.ORDERID, orderId);
        startActivity(intent);
        finish();
    }

    /**
     * 微信支付用户已取消支付的回调,使用的eventBus.......((‵□′))
     **/
    @Subscribe
    public void onEventMainThread(WeChatUnPayEvent event) {

    }

    /**
     * 支付宝支付
     */
    private void aliPay(final String orderInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(ConfirmOrderActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 支付宝支付结果
     */
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    Map<String, String> result = (Map<String, String>) msg.obj;
                    if ("9000".equals(result.get("resultStatus"))) {
                        //支付成功
                        Intent intent = new Intent(mContext, PayResultActivity.class);
                        intent.putExtra(AppConstant.PAYCHANNAL, payChannel);
                        intent.putExtra(AppConstant.OUTTRADENO, outTradeNo);
                        intent.putExtra(AppConstant.ORDERID, orderId);
                        startActivity(intent);
                        finish();
                    } else if ("6001".equals(result.get("resultStatus"))) {
                        //用户取消支付
                        AppHelper.showMsg(mContext, "您已取消支付");
                    } else if ("6002".equals(result.get("resultStatus"))) {
                        //网络连接错误
                        AppHelper.showMsg(mContext, "网络连接错误");
                    } else {
                        //支付失败
                        Intent intent = new Intent(mContext, PayResultActivity.class);
                        intent.putExtra(AppConstant.PAYCHANNAL, payChannel);
                        intent.putExtra(AppConstant.OUTTRADENO, outTradeNo);
                        intent.putExtra(AppConstant.ORDERID, orderId);
                        startActivity(intent);
                        finish();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    /**
     * 获取详情
     */
    private void getOrderDetail(String orderId) {
        GetOrderDetailAPI.requestData(mContext, orderId, "", "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetOrderDetailModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetOrderDetailModel getOrderDetailModel) {
                        dialog.dismiss();
                        logoutAndToHome(mContext, getOrderDetailModel.code);
                        if (getOrderDetailModel.success) {
                            mTvTip.setText("满" + getOrderDetailModel.data.sendAmount + "元免费配送");
                            if (Float.parseFloat(getOrderDetailModel.data.deliveryFee) == 0) {
                                //配送费为0
                                mTvTotalMoney.setText("¥" + getOrderDetailModel.data.prodAmount);
                            } else {
                                mTvTotalMoney.setText("¥" + getOrderDetailModel.data.prodAmount + "+配送费¥" + getOrderDetailModel.data.deliveryFee);
                            }
                            payAmount = Double.parseDouble(getOrderDetailModel.data.totalAmount);
                            if (getOrderDetailModel.data.hasDefaultAddressFlag) {
                                mLlAddress.setVisibility(View.VISIBLE);
                                mLlAddAddress.setVisibility(View.GONE);
                                GetOrderDetailModel.DataBean.AddressVOBean bean = getOrderDetailModel.data.addressVO;
                                mTvName.setText(bean.userName);
                                mTvPhone.setText(bean.contactPhone);
                                mTvAddress.setText(bean.provinceName + bean.cityName + bean.areaName + bean.detailAddress);
                                mTvFirmName.setText(bean.shopName);
                            } else {
                                mLlAddress.setVisibility(View.GONE);
                                mLlAddAddress.setVisibility(View.VISIBLE);
                            }
                            addViewAndDate(getOrderDetailModel.data.productVOList);
                        } else {
                            AppHelper.showMsg(mContext, getOrderDetailModel.message);
                        }
                    }
                });
    }

    /**
     * 填充订单数据和布局
     */
    private void addViewAndDate(List<GetOrderDetailModel.DataBean.ProductVOListBean> mList) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);       //LayoutInflater inflater1=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mLlOrder.removeAllViews();
        for (int i = 0; i < mList.size(); i++) {
            View view = inflater.inflate(R.layout.item_confirm_order, null);
            view.setLayoutParams(lp);
            mLlOrder.addView(view);
            setOrderViewData(view, mList.get(i));
        }

    }

    /**
     * 设置数据
     */
    private void setOrderViewData(View view, GetOrderDetailModel.DataBean.ProductVOListBean bean) {
        ImageView mIvImg = FVHelper.fv(view, R.id.iv_item_order);
        if (StringHelper.notEmptyAndNull(bean.picUrl)) {
            GlideModel.displayTransForms(mContext,bean.picUrl,mIvImg);
            //Glide.with(mContext).load(bean.picUrl).crossFade().transform(new GlideRoundTransform(mContext, 3)).error(R.mipmap.icon_default_rec).into(mIvImg);
        }
        TextView mTvItem = FVHelper.fv(view, R.id.tv_item_order_title);
        mTvItem.setText(bean.name);
        mTvItem = FVHelper.fv(view, R.id.tv_item_order_spec);
        mTvItem.setText(bean.spec);
        mTvItem = FVHelper.fv(view, R.id.tv_item_total_money);
        mTvItem.setText("￥" + bean.amount);
        if (bean.productDescVOList != null) {
            List<OrderNumModel> mList = new ArrayList<>();
            OrderNumModel model;
            for (int i = 0; i < bean.productDescVOList.size(); i++) {
                if (i < bean.productDescVOList.size() - 1) {
                    model = new OrderNumModel();
                    model.type = OrderNumModel.textType;
                    model.detailDesc = bean.productDescVOList.get(i).detailDesc;
                    model.totalNum = bean.productDescVOList.get(i).totalNum;
                    mList.add(model);
                    model = new OrderNumModel();
                    model.type = OrderNumModel.lineType;
                    mList.add(model);
                } else {
                    model = new OrderNumModel();
                    model.type = OrderNumModel.textType;
                    model.detailDesc = bean.productDescVOList.get(i).detailDesc;
                    model.totalNum = bean.productDescVOList.get(i).totalNum;
                    mList.add(model);
                }
            }
            RecyclerView mRvNum = FVHelper.fv(view, R.id.rv_item_confirm_order);
            mRvNum.setLayoutManager(new LinearLayoutManager(mContext));
            ConFirmOrderAdapter mAdapter = new ConFirmOrderAdapter(mList);
            mRvNum.setAdapter(mAdapter);
        }
    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(noDoubleClickListener);
        mLlAddress.setOnClickListener(noDoubleClickListener);
        mLlAddAddress.setOnClickListener(noDoubleClickListener);
        mTvGoPay.setOnClickListener(noDoubleClickListener);


    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            if (view == mIvBack) {
                finish();
            } else if (view == mLlAddress) {
                //切换地址是这个用户原本就有地址,这里跳转到地址列表界面,点击某个地址
                startActivityForResult(AddressListActivity.getIntent(mContext, AddressListActivity.class), 31);
            } else if (view == mLlAddAddress) {
                startActivityForResult(AddressListActivity.getIntent(mContext, AddressListActivity.class), 32);
            } else if (view == mTvGoPay) {
                // 去支付
                if (mLlAddress.getVisibility() == View.VISIBLE) {
                    //判断收货地址是否可见
                    if (mRgPay.getCheckedRadioButtonId() == -1) {
                        //未选择支付方式
                        AppHelper.showMsg(mContext, "请选择支付方式");
                    } else if (mRgPay.getCheckedRadioButtonId() == R.id.rb_activity_order_alipay) {
                        payChannel = 2;
                        orderPay(orderId, payChannel, payAmount, mEtNote.getText().toString());
                    } else if (mRgPay.getCheckedRadioButtonId() == R.id.rb_activity_order_wechat) {
                        payChannel = 3;
                        orderPay(orderId, payChannel, payAmount, mEtNote.getText().toString());
                    } else if (mRgPay.getCheckedRadioButtonId() == R.id.rb_activity_order_balance) {
                        payChannel = 1;
                        orderPay(orderId, payChannel, payAmount, mEtNote.getText().toString());
                    }
                } else {
                    AppHelper.showMsg(mContext, "请填写地址");
                }
            }
        }
    };
    Html.ImageGetter imageGetter = new Html.ImageGetter() {

        @Override
        public Drawable getDrawable(String source) {
            Drawable drawable = null;
            int rId = Integer.parseInt(source);
            drawable = getResources().getDrawable(rId);
//	drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable.setBounds(0, 0, 40, 40);
            return drawable;
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 33) {
            int address_id = data.getIntExtra("address_id", -1);
            dialog.show();
            requestChangeOrderAddress(address_id);
        }
    }

    private void requestChangeOrderAddress(int address_id) {
        ChangeOrderAddressAPI.requestChangeOrderAddress(mContext, orderId, address_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ChangeOrderAddressModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ChangeOrderAddressModel changeOrderAddressModel) {
                        mModelChangeOrderAddress = changeOrderAddressModel;
                        if (mModelChangeOrderAddress.success) {
                            //修改成功,重新请求订单数据
                            getOrderDetail(orderId);
                        } else {
                            AppHelper.showMsg(mContext, mModelChangeOrderAddress.message);
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
