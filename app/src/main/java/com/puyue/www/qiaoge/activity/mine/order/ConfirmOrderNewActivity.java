package com.puyue.www.qiaoge.activity.mine.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.NewWebViewActivity;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.HomeActivity;
import com.puyue.www.qiaoge.activity.mine.account.AddressListActivity;
import com.puyue.www.qiaoge.adapter.mine.ChooseCouponsAdapter;
import com.puyue.www.qiaoge.adapter.mine.ConfirmOrderNewAdapter;
import com.puyue.www.qiaoge.api.cart.CartBalanceAPI;
import com.puyue.www.qiaoge.api.home.GetDeliverTimeAPI;
import com.puyue.www.qiaoge.api.mine.coupon.userChooseDeductAPI;
import com.puyue.www.qiaoge.api.mine.order.GenerateOrderAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.event.AddressEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.cart.CartBalanceModel;
import com.puyue.www.qiaoge.model.home.GetDeliverTimeModel;
import com.puyue.www.qiaoge.model.mine.coupons.UserChooseDeductModel;
import com.puyue.www.qiaoge.model.mine.order.GenerateOrderModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author daff
 * @date 2018/9/23.
 * 目前不用
 * 备注  (新版本) 确认订单
 */
public class ConfirmOrderNewActivity extends BaseSwipeActivity {

    private static final String TAG = ConfirmOrderNewActivity.class.getSimpleName();

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private LinearLayout linearLayoutAddressHead;
    private TextView userName;
    private TextView userPhone;
    private TextView address;
    private TextView firmName; // 店名
    private LinearLayout LinearLayoutAddress;// 没地址的xml
    private TextView textViewNum; // 几件商品
    private TextView commodityAmount; // 商品总额
    private TextView distributionFee; // 配送活动
    private TextView distributionFeePrice; // 配送费
    private TextView textCoupons; //优惠劵
    private EditText messageEditText; // 买家留言
    private TextView totalPrice;  // 总价
    private TextView textViewDiscount; // 已优惠
    private TextView buttonPay; // 去支付
    private TextView commodity;
    private TextView payMoney; // 支付金额
    private LinearLayout LinearLayoutStoreName; // 店名xml

    private LinearLayout linearLayoutCoupons;// 优惠券xml
    private String orderId;

    private ConfirmOrderNewAdapter adapter;
    private List<CartBalanceModel.DataBean.ProductVOListBean>
            list = new ArrayList<>();
    // 获取想去的参数 和获取订单的参数
    private String normalProductBalanceVOStr;
    private String activityBalanceVOStr;
    private String equipmentBalanceVOStr;
    private String cartListStr;
    private String NewgiftDetailNo = "";//NewgiftDetailNo
    // 获取选择优惠券的参数
    private String proActAmount = "";
    private String teamAmount = "";
    private String killAmount = "";
    private String prodAmount = "";
    private String couponId = "";

    private String payAmount = "";
    // 判断是否匹配优惠券，0否1是，默认1
    CartBalanceModel cModel;
    //  优惠卷
    private RecyclerView couponsRecyclerView;
    private ChooseCouponsAdapter couponsAdapter;
    private List<UserChooseDeductModel.DataBean.AllBean> couponsList = new ArrayList<>();
    private TextView noData;


    private RelativeLayout relativeLayoutVIP; //
    private LinearLayout vipSubtractionLinearLayout;
    private LinearLayout subtractionActivitiesLinearLayout;
    private TextView textViewVipTitle;
    private ImageView imVipButton;
    private TextView vipSubtraction;
    private TextView subtractionActivities;
    private TextView vipSubtractionPrice;
    private TextView subtractionActivitiesPrice;
    private String VipURl;
    private String deductDetail = "";
    //记录图片的点击次数，设置一开始为0.
//    private int j=0;
    //请求次数
    private int requestCount = 0;

    private String areaContent;
    private String deliverTimeStart = "";
    private String deliverTimeEnd = "";

    private String deliverTimeName = "";

    private LinearLayout ll_collect_bills;
    private LinearLayout ll_go_market;
    private TextView tv_amount_spec;
    private TextView tv_vip_content_one;
    private TextView tv_vip_content_two;
    private TextView tv_go;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_confirm_order_new);
    }

    @Override
    public void findViewById() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        userName = (TextView) findViewById(R.id.userName);
        userPhone = (TextView) findViewById(R.id.userPhone);
        address = (TextView) findViewById(R.id.address);
        firmName = (TextView) findViewById(R.id.firmName);
        linearLayoutAddressHead = (LinearLayout) findViewById(R.id.linearLayoutAddressHead);
        LinearLayoutAddress = (LinearLayout) findViewById(R.id.LinearLayoutAddress);
        textViewNum = (TextView) findViewById(R.id.textViewNum);
        commodityAmount = (TextView) findViewById(R.id.commodityAmount);
        distributionFee = (TextView) findViewById(R.id.distributionFee);
        distributionFeePrice = (TextView) findViewById(R.id.distributionFeePrice);
        textCoupons = (TextView) findViewById(R.id.textCoupons);
        messageEditText = (EditText) findViewById(R.id.messageEditText);
        totalPrice = (TextView) findViewById(R.id.totalPrice);
        textViewDiscount = (TextView) findViewById(R.id.textViewDiscount);
        buttonPay = (TextView) findViewById(R.id.buttonPay);
        commodity = (TextView) findViewById(R.id.commodity);
        payMoney = (TextView) findViewById(R.id.payMoney);
        LinearLayoutStoreName = (LinearLayout) findViewById(R.id.LinearLayoutStoreName);
        linearLayoutCoupons = (LinearLayout) findViewById(R.id.linearLayoutCoupons);
        couponsRecyclerView = (RecyclerView) findViewById(R.id.couponsRecyclerView);

        relativeLayoutVIP = (RelativeLayout) findViewById(R.id.relativeLayoutVIP);
        vipSubtractionLinearLayout = (LinearLayout) findViewById(R.id.vipSubtractionLinearLayout);
        subtractionActivitiesLinearLayout = (LinearLayout) findViewById(R.id.subtractionActivitiesLinearLayout);
        textViewVipTitle = (TextView) findViewById(R.id.textViewVipTitle);
        imVipButton = (ImageView) findViewById(R.id.imVipButton);
        vipSubtraction = (TextView) findViewById(R.id.vipSubtraction);
        subtractionActivities = (TextView) findViewById(R.id.subtractionActivities);
        vipSubtractionPrice = (TextView) findViewById(R.id.vipSubtractionPrice);
        subtractionActivitiesPrice = (TextView) findViewById(R.id.subtractionActivitiesPrice);
        noData = (TextView) findViewById(R.id.noData);
        ll_collect_bills = (LinearLayout) findViewById(R.id.ll_collect_bills);
        ll_go_market = (LinearLayout) findViewById(R.id.ll_go_market);
        tv_amount_spec = (TextView) findViewById(R.id.tv_amount_spec);
        tv_vip_content_one = (TextView) findViewById(R.id.tv_vip_content_one);
        tv_vip_content_two = (TextView) findViewById(R.id.tv_tv_vip_content_two);
        tv_go = (TextView) findViewById(R.id.tv_go);
    }

    @Override
    public void setViewData() {
        EventBus.getDefault().register(this);
        normalProductBalanceVOStr = getIntent().getStringExtra("normalProductBalanceVOStr");
        activityBalanceVOStr = getIntent().getStringExtra("activityBalanceVOStr");
        equipmentBalanceVOStr = getIntent().getStringExtra("equipmentBalanceVOStr");
        cartListStr = getIntent().getStringExtra("cartListStr");
        adapter = new ConfirmOrderNewAdapter(R.layout.item_confirm_order_new, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


//        userChooseDeduct();


    }

    @Override
    public void setClickEvent() {
        toolbar.setNavigationOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                startActivity(new Intent(mContext, HomeActivity.class));
                finish();
            }
        });
        linearLayoutAddressHead.setOnClickListener(noDoubleClickListener);
        LinearLayoutAddress.setOnClickListener(noDoubleClickListener);
        buttonPay.setOnClickListener(noDoubleClickListener);
        linearLayoutCoupons.setOnClickListener(noDoubleClickListener);
        imVipButton.setOnClickListener(noDoubleClickListener);
        ll_go_market.setOnClickListener(noDoubleClickListener);
    }

    List<String> mlist = new ArrayList<>();
    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            switch (view.getId()) {
                case R.id.linearLayoutAddressHead: // 地址切换
                    Intent intent_ = new Intent(mActivity, AddressListActivity.class);
                    intent_.putExtra("type", 1);
                    intent_.putExtra("mineAddress", "mineAddress");
                    intent_.putExtra("UseAddress","");
                    startActivity(intent_);
                    break;
                case R.id.LinearLayoutAddress: // 添加地址
                    Intent intent1 = AddressListActivity.getIntent(mActivity, AddressListActivity.class);
                    intent1.putExtra("mineAddress", "mineAddress");
                    intent1.putExtra("UseAddress","");
                    startActivity(intent1);

                    break;
                case R.id.buttonPay:// 去支付
                    if (LinearLayoutAddress.getVisibility() == View.VISIBLE) { // 没有地址
                        AppHelper.showMsg(mContext, "请填写地址");
                    } else {

                        GetDeliverTimeAPI.requestDeliverTime(mContext, areaContent)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Subscriber<GetDeliverTimeModel>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onNext(GetDeliverTimeModel getDeliverTimeModel) {
                                        if (getDeliverTimeModel.success) {
                                            if (getDeliverTimeModel.data != null) {

                                                mlist.clear();
                                                try {
                                                    JSONArray jsonArray = new JSONArray(getDeliverTimeModel.data);
                                                    for (int i = 0; i < jsonArray.length(); i++) {
                                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                        mlist.add(jsonObject.getString("name") + " " + jsonObject.getString("start") + "-" + jsonObject.getString("end"));

                                                    }

//                                                    PickCityUtil.showSinglePickView(mContext, mlist, "请选择配送时间段", new PickCityUtil.ChoosePositionListener() {
//                                                        @Override
//                                                        public void choosePosition(int position, String s) {
//                                                            try {
//                                                                JSONObject jsonObjects = jsonArray.getJSONObject(position);
//                                                                deliverTimeStart = jsonObjects.getString("start");
//                                                                deliverTimeName = jsonObjects.getString("name");
//                                                                deliverTimeEnd = jsonObjects.getString("end");
//                                                            } catch (JSONException e) {
//                                                                e.printStackTrace();
//                                                            }
//                                                            requestOrderNum();
//                                                        }
//                                                    });
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            } else {
                                                requestOrderNum();
                                            }
                                        } else {
                                            AppHelper.showMsg(mContext, getDeliverTimeModel.message);
                                        }


                                    }
                                });


                    }
                    break;
                case R.id.linearLayoutCoupons: // 优惠券
                  /*  Intent intent = new Intent(ConfirmOrderNewActivity.this, ChooseCouponsActivity.class);
                    intent.putExtra("proActAmount", proActAmount);
                    intent.putExtra("teamAmount", teamAmount);
                    intent.putExtra("killAmount", killAmount);
                    intent.putExtra("prodAmount", prodAmount);
                    intent.putExtra("giftDetailNo", couponId);
                    startActivityForResult(intent, ActivityResultHelper.ChOOSE_COUPONS_REQUESR_CODE);*/
                    //点击图片控件，根据当前的判断，设置点击之后的背景图片。
//                    if(linearLayoutCoupons.isSelected()){
//                        linearLayoutCoupons.setSelected(false);
//                    }else {
//                        linearLayoutCoupons.setSelected(true);
//                    }

                    break;
                case R.id.ll_go_market:

                    Intent intent = new Intent(mContext, NewWebViewActivity.class);
                    intent.putExtra("URL", VipURl);
                    intent.putExtra("name", "");

                    startActivity(intent);

                    break;
            }
        }
    };


    /**
     * 获取数据的网络请求
     */
    private void requestCartBalance(String giftDetailNo, int type) {


        CartBalanceAPI.requestCartBalance(mContext, normalProductBalanceVOStr, activityBalanceVOStr, cartListStr, giftDetailNo, type,0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CartBalanceModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CartBalanceModel cartBalanceModel) {
                        if (cartBalanceModel.success) {
                            cModel = cartBalanceModel;

                            if (cartBalanceModel != null) {
                                setText(cartBalanceModel);
                                if (requestCount == 0) {
                                    userChooseDeduct();
                                    requestCount++;
                                }

                                if (cartBalanceModel.getData().getProductVOList().size() > 0) {
                                    list.clear();
                                    list.addAll(cartBalanceModel.getData().getProductVOList());
                                }
                            }
                            adapter.notifyDataSetChanged();

                        } else {
                            AppHelper.showMsg(mActivity, cartBalanceModel.message);
                        }
                    }
                });
    }

    /**
     * 接收来自接口的数据进行数据设置。
     *
     * @param cartBalanceModel
     */
    private void setText(CartBalanceModel cartBalanceModel) {
        CartBalanceModel.DataBean info = cartBalanceModel.getData();
        proActAmount = info.getProActAmount();
        teamAmount = info.getTeamAmount();
        killAmount = info.getKillAmount();
        prodAmount = info.getNormalAmount();

        areaContent =
                info.getAddressVO().getAreaCode();

        if (info.getDeductDetail() != null) {
            deductDetail = info.getDeductDetail().getGiftDetailNo();
        } else {
            deductDetail = "";
        }

        textViewNum.setText("共" + info.getTotalNum() + "件商品");
        distributionFeePrice.setText("¥" + info.getDeliveryFee());
        payMoney.setText("¥" + info.getTotalAmount());
        commodity.setText("共" + info.getTotalNum() + "件商品");
        totalPrice.setText("¥" + info.getTotalAmount());
        payAmount = info.getTotalAmount();
        commodityAmount.setText("¥" + info.getProdAmount() + "");

        distributionFee.setText("满" + info.getSendAmount() + "元免配送费");

        if (info.getAddressVO().getContactPhone() != null && info.getAddressVO().getUserName() != null &&
                info.getAddressVO().getProvinceName() != null && info.getAddressVO().getCityName() != null &&
                info.getAddressVO().getAreaName() != null && info.getAddressVO().getDetailAddress() != null) {
            LinearLayoutAddress.setVisibility(View.GONE);
            linearLayoutAddressHead.setVisibility(View.VISIBLE);
            userName.setText(info.getAddressVO().getUserName());
            userPhone.setText(info.getAddressVO().getContactPhone());

            address.setText(info.getAddressVO().getProvinceName() + info.getAddressVO().getCityName()
                    + info.getAddressVO().getAreaName() + info.getAddressVO().getDetailAddress());
            if (!TextUtils.isEmpty(info.getAddressVO().getShopName())) {
                firmName.setText(info.getAddressVO().getShopName());
                // LinearLayoutStoreName.setVisibility(View.VISIBLE);
            } else {
                // LinearLayoutStoreName.setVisibility(View.GONE);
            }
        } else {
            linearLayoutAddressHead.setVisibility(View.GONE);
            LinearLayoutAddress.setVisibility(View.VISIBLE);
        }
        if (cartBalanceModel.getData().getOfferAmount() != null) {
            textViewDiscount.setText("已优惠¥" + cartBalanceModel.getData().getOfferAmount());
            textViewDiscount.setVisibility(View.VISIBLE);
        } else {
            textViewDiscount.setVisibility(View.GONE);

        }
        if (cartBalanceModel.getData().getDeductDetail() != null) {
            if (!TextUtils.isEmpty(cartBalanceModel.getData().getDeductDetail().getAmountStr())) {
                couponId = cartBalanceModel.getData().getDeductDetail().getGiftDetailNo();
                NewgiftDetailNo = cartBalanceModel.getData().getDeductDetail().getGiftDetailNo();////NewgiftDetailNo

            }
        }
        textCoupons.setText(cartBalanceModel.getData().getDeductDesc());

        VipURl = cartBalanceModel.getData().getVipCenterUrl();
        vipSubtractionPrice.setText("¥" + cartBalanceModel.getData().getVipReduct());

        if (!TextUtils.isEmpty(cartBalanceModel.getData().getVipReductDesc())) {

            vipSubtraction.setText(cartBalanceModel.getData().getVipReductDesc());
            vipSubtraction.setVisibility(View.VISIBLE);
        } else {
            vipSubtraction.setVisibility(View.GONE);
        }
        if (cartBalanceModel.getData().isVip()) { // 是否开通vip
            ll_collect_bills.setVisibility(View.GONE);

            vipSubtractionLinearLayout.setVisibility(View.VISIBLE);
            relativeLayoutVIP.setVisibility(View.GONE);
            //  vipSubtractionLinearLayout.setVisibility(View.GONE);
            textViewVipTitle.setText(cartBalanceModel.getData().getNotVipDesc());
        } else {
            //ll_collect_bills.setVisibility(View.VISIBLE);
            if (!cartBalanceModel.getData().isOpenVip()) {
                if (cartBalanceModel.getData().getVipDesc() > 0) {
                    ll_collect_bills.setVisibility(View.VISIBLE);
                    tv_vip_content_one.setText("续费会员本单立减");
                    tv_vip_content_two.setText("，随后享受单单满减优惠");
                    tv_go.setText("去续费");

                    tv_amount_spec.setText(cartBalanceModel.getData().getVipDesc().toString() + "" + "元");
                } else {
                    ll_collect_bills.setVisibility(View.GONE);
                }


            } else {


                ll_collect_bills.setVisibility(View.VISIBLE);

                tv_amount_spec.setText(cartBalanceModel.getData().getVipDesc().toString() + "" + "元");
                tv_vip_content_one.setText("开通会员本单立减");
                tv_vip_content_two.setText("，随后享受单单满减优惠");
                tv_go.setText("去开通");
            }
            relativeLayoutVIP.setVisibility(View.GONE);

        }
        if (cartBalanceModel.getData().isOfferIsOpen()) { // 活动满减
            subtractionActivitiesLinearLayout.setVisibility(View.VISIBLE);

            subtractionActivitiesPrice.setText("¥" + cartBalanceModel.getData().getNormalReduct());
            if (!TextUtils.isEmpty(cartBalanceModel.getData().getNormalReductDesc())) {
                subtractionActivities.setVisibility(View.VISIBLE);
                subtractionActivities.setText(cartBalanceModel.getData().getNormalReductDesc());
            } else {
                subtractionActivities.setVisibility(View.GONE);
            }

        } else {
            subtractionActivitiesLinearLayout.setVisibility(View.GONE);
            subtractionActivities.setVisibility(View.GONE);
        }


//        userChooseDeduct();
    }


    // 获取订单号
    private void requestOrderNum() {
        Log.e(TAG, "requestOrderNum: 2号：" + NewgiftDetailNo);
        GenerateOrderAPI.requestGenerateOrder(mContext, activityBalanceVOStr, normalProductBalanceVOStr,
                cartListStr, NewgiftDetailNo, messageEditText.getText().toString(), deliverTimeStart, deliverTimeEnd, deliverTimeName,0,"","","")//NewgiftDetailNo
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GenerateOrderModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GenerateOrderModel generateOrderModel) {
                        if (generateOrderModel.success) {

//                            Log.e(TAG, "onNext: activityBalanceVOStr="+activityBalanceVOStr+"?normalProductBalanceVOStr="+normalProductBalanceVOStr+"?equipmentBalanceVOStr="+equipmentBalanceVOStr
//                                    +"?cartListStr="+cartListStr+"?giftDetailNo="+giftDetailNo+"?=messageEditText.getText().toString()"+messageEditText.getText().toString() );
                            orderId = generateOrderModel.getData();
                            Intent intent = new Intent(ConfirmOrderNewActivity.this, MyConfireOrdersActivity.class);
                            intent.putExtra("orderId", generateOrderModel.getData());
                            intent.putExtra("payAmount", Double.parseDouble(payAmount));
                            intent.putExtra("remark", messageEditText.getText().toString());
                            finish();
                            startActivity(intent);
                        } else {
                            AppHelper.showMsg(mActivity, generateOrderModel.message);
                        }
                    }
                });
    }

    @Subscribe
    public void onEventMainThread(AddressEvent event) {

        list.clear();
        requestCartBalance(NewgiftDetailNo, 1);////NewgiftDetailNo

    }

    /**
     * 获取优惠卷列表
     */
    private void userChooseDeduct() {


        userChooseDeductAPI.requestData(mContext, proActAmount, teamAmount,
                killAmount, prodAmount, deductDetail)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserChooseDeductModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserChooseDeductModel model) {
                        if (model.success) {
                            if (model.getData().getAll().size() > 0) {
                                couponsRecyclerView.setVisibility(View.VISIBLE);
                                noData.setVisibility(View.GONE);
                                setRecyclerView();
                                couponsList.clear();
                                couponsList.addAll(model.getData().getAll());

                                for (int i = 0; i < couponsList.size(); i++) {
                                    if (model.getData().getAll().get(i).getGiftDetailNo().equals(couponId)) {
                                        //此处为第二次设置优惠券的isFlag
                                        model.getData().getAll().get(i).setFlag(true);

                                    } else {
                                        //此处为第二次设置优惠券的isFlag
                                        model.getData().getAll().get(i).setFlag(false);

                                    }
                                }
                                couponsAdapter.notifyDataSetChanged();
                            } else {
                                couponsRecyclerView.setVisibility(View.GONE);
                                noData.setVisibility(View.VISIBLE);

                            }
                            adapter.notifyDataSetChanged();


                        } else {
                            AppHelper.showMsg(mContext, model.message);
                        }

                    }
                });
    }

    private void setRecyclerView() {
        LinearLayoutManager linearLayoutManagerCoupons = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        couponsAdapter = new ChooseCouponsAdapter(R.layout.item_choose_copons, couponsList, new ChooseCouponsAdapter.ImageOnclick() {

            @Override
            public void Onclick(int position, String giftDetailNo) {
                UserChooseDeductModel.DataBean.AllBean info = couponsList.get(position);
//                Log.e(TAG, "Onclick: "+info.toString()+giftDetailNo );
                for (int i = 0; i < couponsList.size(); i++) {
                    //表示点到第几个是第几个，
                    if (i == position) {
                        //首个是true
                        //当它是true的时候，将这个该设置为false
                        //假设flag是用来控制ImageView 的状态的。
                        //第一次设置
                        //首次是true
                        couponsList.get(i).setFlag(!couponsList.get(i).isFlag());
//                        boolean flag = couponsList.get(i).isFlag();
//                        flag=!flag;
//                        Log.e(TAG, "Onclick: "+j );
                        if (couponsList.get(i).isFlag()) {
//                            couponsList.get(i).setFlag(!flag);
                            //1
                            list.clear();
                            requestCartBalance(info.getGiftDetailNo(), 1);
                            NewgiftDetailNo = info.getGiftDetailNo();
                        } else {
//                            couponsList.get(i).setFlag(flag);
                            //0
                            list.clear();
                            requestCartBalance("", 0);
                            NewgiftDetailNo = "";
                        }
//                        j++;

                    } else {
                        couponsList.get(i).setFlag(false);
                    }
                }
                couponsAdapter.notifyDataSetChanged();
            }
        });
        couponsRecyclerView.setLayoutManager(linearLayoutManagerCoupons);
        couponsRecyclerView.setAdapter(couponsAdapter);

    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        requestCartBalance(NewgiftDetailNo, 1);//NewgiftDetailNo
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if (keyCode == KeyEvent.KEYCODE_BACK) {
          startActivity(new Intent(mContext, HomeActivity.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
