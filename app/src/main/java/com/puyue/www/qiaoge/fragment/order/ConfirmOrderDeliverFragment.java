package com.puyue.www.qiaoge.fragment.order;


import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.puyue.www.qiaoge.NewWebViewActivity;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.cart.CartPoint;
import com.puyue.www.qiaoge.activity.mine.account.AddressListActivity;
import com.puyue.www.qiaoge.activity.mine.account.AddressListsActivity;
import com.puyue.www.qiaoge.activity.mine.order.MyConfireOrdersActivity;
import com.puyue.www.qiaoge.adapter.mine.ChooseCouponsAdapter;
import com.puyue.www.qiaoge.adapter.mine.ConfirmOrderNewAdapter;
import com.puyue.www.qiaoge.api.cart.CartBalanceAPI;
import com.puyue.www.qiaoge.api.home.GetDeliverTimeAPI;
import com.puyue.www.qiaoge.api.mine.coupon.userChooseDeductAPI;
import com.puyue.www.qiaoge.api.mine.order.GenerateOrderAPI;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.dialog.ChooseAddressDialog;
import com.puyue.www.qiaoge.dialog.ListViewDialog;
import com.puyue.www.qiaoge.event.AddressEvent;
import com.puyue.www.qiaoge.helper.AlwaysMarqueeTextViewHelper;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.cart.CartBalanceModel;
import com.puyue.www.qiaoge.model.home.GetDeliverTimeModel;
import com.puyue.www.qiaoge.model.mine.coupons.UserChooseDeductModel;
import com.puyue.www.qiaoge.model.mine.order.GenerateOrderModel;
import com.puyue.www.qiaoge.utils.ToastUtil;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmOrderDeliverFragment extends BaseFragment {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private LinearLayout linearLayoutAddressHead;
    private TextView userName;
    private TextView userPhone;
    private TextView address;
    public LinearLayout ll_info;
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
    private AVLoadingIndicatorView lav_activity_loading;
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

    private int currentDay;
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
    List<String> mlist = new ArrayList<>();
    private LinearLayout ll_collect_bills;
    private LinearLayout ll_go_market;
    private TextView tv_amount_spec;
    private TextView tv_vip_content_one;
    private TextView tv_vip_content_two;
    private TextView tv_go;
    private Double toRechargeAmount;
    private boolean toRecharge;
    private Double totalAmount;

    private PopupWindow popWin; // 弹出窗口
    private View popView; // 保存弹出窗口布局
    private WheelView wheelView;
    private TextView textView1;//取消
    private TextView textView2;//确定
    AlwaysMarqueeTextViewHelper sc;
    @Override
    public int setLayoutId() {
        return R.layout.fragment_confirm_deliver_order;
    }

    @Override
    public void initViews(View view) {
    }

    @Override
    public void findViewById(View view) {
        //  toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ll_info = (LinearLayout) view.findViewById(R.id.ll_info);
        lav_activity_loading = (AVLoadingIndicatorView) view.findViewById(R.id.lav_activity_loading);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        userName = (TextView) view.findViewById(R.id.userName);
        userPhone = (TextView) view.findViewById(R.id.userPhone);
        address = (TextView) view.findViewById(R.id.address);
        firmName = (TextView) view.findViewById(R.id.firmName);
        linearLayoutAddressHead = (LinearLayout) view.findViewById(R.id.linearLayoutAddressHead);
        LinearLayoutAddress = (LinearLayout) view.findViewById(R.id.LinearLayoutAddress);
        textViewNum = (TextView) view.findViewById(R.id.textViewNum);
        commodityAmount = (TextView) view.findViewById(R.id.commodityAmount);
        distributionFee = (TextView) view.findViewById(R.id.distributionFee);
        distributionFeePrice = (TextView) view.findViewById(R.id.distributionFeePrice);
        textCoupons = (TextView) view.findViewById(R.id.textCoupons);
        messageEditText = (EditText) view.findViewById(R.id.messageEditText);
        totalPrice = (TextView) view.findViewById(R.id.totalPrice);
        textViewDiscount = (TextView) view.findViewById(R.id.textViewDiscount);

        buttonPay = (TextView) view.findViewById(R.id.buttonPay);
        commodity = (TextView) view.findViewById(R.id.commodity);
        payMoney = (TextView) view.findViewById(R.id.payMoney);
        LinearLayoutStoreName = (LinearLayout) view.findViewById(R.id.LinearLayoutStoreName);
        linearLayoutCoupons = (LinearLayout) view.findViewById(R.id.linearLayoutCoupons);
        couponsRecyclerView = (RecyclerView) view.findViewById(R.id.couponsRecyclerView);

        relativeLayoutVIP = (RelativeLayout) view.findViewById(R.id.relativeLayoutVIP);
        vipSubtractionLinearLayout = (LinearLayout) view.findViewById(R.id.vipSubtractionLinearLayout);
        subtractionActivitiesLinearLayout = (LinearLayout) view.findViewById(R.id.subtractionActivitiesLinearLayout);
        textViewVipTitle = (TextView) view.findViewById(R.id.textViewVipTitle);
        imVipButton = (ImageView) view.findViewById(R.id.imVipButton);
        vipSubtraction = (TextView) view.findViewById(R.id.vipSubtraction);
        subtractionActivities = (TextView) view.findViewById(R.id.subtractionActivities);
        vipSubtractionPrice = (TextView) view.findViewById(R.id.vipSubtractionPrice);
        subtractionActivitiesPrice = (TextView) view.findViewById(R.id.subtractionActivitiesPrice);
        noData = (TextView) view.findViewById(R.id.noData);
        ll_collect_bills = (LinearLayout) view.findViewById(R.id.ll_collect_bills);
        ll_go_market = (LinearLayout) view.findViewById(R.id.ll_go_market);
        tv_amount_spec = (TextView) view.findViewById(R.id.tv_amount_spec);
        tv_vip_content_one = (TextView) view.findViewById(R.id.tv_vip_content_one);
        tv_vip_content_two = (TextView) view.findViewById(R.id.tv_tv_vip_content_two);
        tv_go = (TextView) view.findViewById(R.id.tv_go);

//        progressBar.startAnimation(Animation );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setNavigationBarColor(ContextCompat.getColor(getContext(),R.color.white));
        }
    }

    @Override
    public void setViewData() {
        EventBus.getDefault().register(this);

        final Calendar mCalendar = Calendar.getInstance();
        long time = System.currentTimeMillis();
        mCalendar.setTimeInMillis(time);
        currentDay = mCalendar.get(Calendar.DAY_OF_MONTH);

        normalProductBalanceVOStr = mActivity.getIntent().getStringExtra("normalProductBalanceVOStr");
        activityBalanceVOStr = mActivity.getIntent().getStringExtra("activityBalanceVOStr");
        equipmentBalanceVOStr = mActivity.getIntent().getStringExtra("equipmentBalanceVOStr");
        cartListStr = mActivity.getIntent().getStringExtra("cartListStr");
        adapter = new ConfirmOrderNewAdapter(R.layout.item_confirm_order_new, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setClickEvent() {

        linearLayoutAddressHead.setOnClickListener(noDoubleClickListener);
        LinearLayoutAddress.setOnClickListener(noDoubleClickListener);
        buttonPay.setOnClickListener(noDoubleClickListener);
        linearLayoutCoupons.setOnClickListener(noDoubleClickListener);
        imVipButton.setOnClickListener(noDoubleClickListener);
        ll_go_market.setOnClickListener(noDoubleClickListener);
    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            switch (view.getId()) {
                case R.id.linearLayoutAddressHead: // 地址切换
//                    Intent intent_ = new Intent(mActivity, AddressListsActivity.class);
//                    intent_.putExtra("type", 1);
//                    intent_.putExtra("mineAddress", "mineAddress");
//                    startActivityForResult(intent_, 31);
                    ChooseAddressDialog chooseAddressDialog = new ChooseAddressDialog(getContext(),orderId);
                    chooseAddressDialog.show();

                    break;
                case R.id.LinearLayoutAddress: // 添加地址
                    Intent intent1 = AddressListActivity.getIntent(mActivity, AddressListsActivity.class);
                    intent1.putExtra("mineAddress", "mineAddress");
                    startActivityForResult(intent1, 32);

                    break;
                case R.id.buttonPay:// 去支付
                    buttonPay.setEnabled(false);
                    lav_activity_loading.show();
                    if (LinearLayoutAddress.getVisibility() == View.VISIBLE) { // 没有地址
                        AppHelper.showMsg(mActivity, "请填写地址");
                        lav_activity_loading.hide();
                    } else {
                        GetDeliverTimeAPI.requestDeliverTime(mActivity, areaContent)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Subscriber<GetDeliverTimeModel>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {
//                                        LoadingDialog.getInstance(getActivity()).dismiss();
                                        lav_activity_loading.hide();
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


                                                    popView = View.inflate( getActivity(), R.layout.cztest_popwin, null);
                                                    popWin = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true); //实例化PopupWindow
                                                    //设置背景灰掉
                                                    backgroundAlpha(0.4f);
                                                    // 设置PopupWindow的弹出和消失效果
//                                                    popWin.setAnimationStyle(R.style.popupAnimation);
                                                    //显示弹出窗口
                                                    popWin.showAtLocation(buttonPay, Gravity.BOTTOM, 0, 0);
                                                    //pop关闭时变回原来
                                                    popWin.setOnDismissListener(new poponDismissListener());
                                                    //取消
                                                    textView1 = (TextView) popView.findViewById(R.id.textView1);
//                                                    sc = (AlwaysMarqueeTextViewHelper) popView.findViewById(R.id.sc);
//                                                    sc.setText("温馨提示：仓库不提供储货服务，超过自提时间需要您退货重新下单。请您及时提货哦！");
//                                                    sc.setTextColor(getResources().getColor(R.color.color_F6551A));
                                                    textView1.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            popWin.dismiss();//关闭
                                                        }
                                                    });
                                                    //确定
                                                    textView2 = (TextView) popView.findViewById(R.id.textView2);
                                                    wheelView = (WheelView) popView.findViewById(R.id.wheelView);
                                                    wheelView.setItems(mlist);
                                                    textView2.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            JSONObject jsonObjects = null;
                                                            try {
                                                                jsonObjects = jsonArray.getJSONObject(wheelView.pos-1);
                                                                deliverTimeStart = jsonObjects.getString("start");
                                                                deliverTimeName = jsonObjects.getString("name");
                                                                deliverTimeEnd = jsonObjects.getString("end");
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }
                                                            popWin.dismiss();//关闭
                                                            requestOrderNum();
                                                        }
                                                    });

//                                                    CustomDatePicker customDatePicker1 = new CustomDatePicker(mlist,getActivity(), new CustomDatePicker.ResultHandler() {
//                                                        @Override
//                                                        public void handle(String time) {
////                                                            Log.d("wfwwwdwddwd.....","sdwdwdwd");
//                                                        }
//                                                    });
//                                                    customDatePicker1.show("dwdwdw");

//                                                    ListViewDialog listViewDialog = new ListViewDialog(mActivity, mlist, new ListViewDialog.Onclick() {
//                                                        @Override
//                                                        public void click(int num) {
//                                                            ToastUtil.showSuccessMsg(mActivity,num+"");
//                                                        }
//                                                    });
//                                                    listViewDialog.show();
                                                    // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
//                                                    if(mlist.size()>0) {
//                                                        wheelView.lists(mlist).fontSize(16).select(0).listener(new WheelView.OnWheelViewItemSelectListener() {
//                                                            @Override
//                                                            public void onItemSelect(int index) {
//                                                                ToastUtil.showSuccessMsg(mActivity,"dwdwdw");
//                                                            }
//                                                        }).build();
//                                                    }


//                                                    PickCityUtil.showSinglePickView(mActivity, mlist, "请选择配送时间段", new PickCityUtil.ChoosePositionListener() {
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
                                            AppHelper.showMsg(mActivity, getDeliverTimeModel.message);
                                            lav_activity_loading.hide();
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

                    Intent intent = new Intent(mActivity, NewWebViewActivity.class);
                    intent.putExtra("URL", VipURl);
                    intent.putExtra("name", "");

                    startActivity(intent);

                    break;
            }
        }
    };

    /**
     * 结算接口
     */
    private void requestCartBalance(String giftDetailNo, int type) {
        CartBalanceAPI.requestCartBalance(mActivity, normalProductBalanceVOStr, activityBalanceVOStr, cartListStr, giftDetailNo, type, 0)
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
                            toRechargeAmount = cModel.getData().getToRechargeAmount();
                            toRecharge = cModel.getData().isToRecharge();
                            totalAmount = Double.valueOf(cartBalanceModel.getData().getTotalAmount());
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

        textViewNum.setText("共" + info.getTotalNum() + "" + "件商品");
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
            if(info.isAddressOk()) {
                address.setText(info.getAddressVO().getProvinceName() + info.getAddressVO().getCityName()
                        + info.getAddressVO().getAreaName() + info.getAddressVO().getDetailAddress());
                ll_info.setVisibility(View.VISIBLE);
            }else {
                address.setText("请选择收货地址");
                ll_info.setVisibility(View.GONE);
            }

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
            vipSubtractionLinearLayout.setVisibility(View.GONE);
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
    }


//           address.setText(info.getAddressVO().getProvinceName() + info.getAddressVO().getCityName()
//                    + info.getAddressVO().getAreaName() + info.getAddressVO().getDetailAddress());
    // 获取订单号
    private void requestOrderNum() {

        GenerateOrderAPI.requestGenerateOrder(mActivity, activityBalanceVOStr, normalProductBalanceVOStr, cartListStr, NewgiftDetailNo, messageEditText.getText().toString(),
                deliverTimeStart, deliverTimeEnd, deliverTimeName, 0, "", "", "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GenerateOrderModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        lav_activity_loading.hide();
                    }

                    @Override
                    public void onNext(GenerateOrderModel generateOrderModel) {

                        if (generateOrderModel.success) {
                                if(generateOrderModel.getData()!=null) {
                                    orderId = generateOrderModel.getData();
                                    Intent intent = new Intent(mActivity, MyConfireOrdersActivity.class);
                                    intent.putExtra("orderId", generateOrderModel.getData());
                                    intent.putExtra("payAmount", Double.parseDouble(payAmount));
                                    intent.putExtra("remark", messageEditText.getText().toString());
                                    intent.putExtra("orderDeliveryType", 0);
                                    startActivity(intent);
                                    mActivity.finish();
                                }
                            lav_activity_loading.hide();
                            lav_activity_loading.setVisibility(View.GONE);
                            buttonPay.setEnabled(true);
                        } else {
                            AppHelper.showMsg(mActivity, generateOrderModel.message);
                            lav_activity_loading.setVisibility(View.GONE);
                            lav_activity_loading.hide();
                            buttonPay.setEnabled(true);
                        }
                    }
                });
    }

    @Subscribe
    public void onEventMainThread(AddressEvent event) {
        list.clear();
        requestCartBalance(NewgiftDetailNo, 1);////NewgiftDetailNo
        userChooseDeduct();
    }

    /**
     * 获取优惠卷列表
     */
    private void userChooseDeduct() {
        userChooseDeductAPI.requestData(mActivity, proActAmount, teamAmount,killAmount, prodAmount, deductDetail)
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
                                Log.d("wddwdwdwddddddddd.....","sdewdwwddddd");
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
                            AppHelper.showMsg(mActivity, model.message);
                        }

                    }
                });
    }

    private void setRecyclerView() {
        LinearLayoutManager linearLayoutManagerCoupons = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
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
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        requestCartBalance(NewgiftDetailNo, 1);//NewgiftDetailNo
    }
    //设置添加屏幕的背景透明度
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        mActivity.getWindow().setAttributes(lp);
    }

    //设置弹框关闭时背景颜色改回来
    class poponDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }
    }

}
