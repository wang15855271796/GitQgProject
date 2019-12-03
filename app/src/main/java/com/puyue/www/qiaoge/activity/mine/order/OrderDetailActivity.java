package com.puyue.www.qiaoge.activity.mine.order;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.bumptech.glide.Glide;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.cart.ConfirmOrderActivity;
import com.puyue.www.qiaoge.activity.mine.account.AddressListActivity;
import com.puyue.www.qiaoge.adapter.cart.ConFirmOrderAdapter;
import com.puyue.www.qiaoge.api.cart.CancelOrderAPI;
import com.puyue.www.qiaoge.api.home.GetOrderDetailAPI;
import com.puyue.www.qiaoge.api.mine.order.ChangeOrderAddressAPI;
import com.puyue.www.qiaoge.api.mine.order.ConfirmGetGoodsAPI;
import com.puyue.www.qiaoge.api.mine.order.CopyToCartAPI;
import com.puyue.www.qiaoge.api.mine.order.ReturnEquipmentOrderListByIdAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.helper.GlideRoundTransform;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.cart.CancelOrderModel;
import com.puyue.www.qiaoge.model.cart.GetOrderDetailModel;
import com.puyue.www.qiaoge.model.cart.OrderNumModel;
import com.puyue.www.qiaoge.model.mine.order.ChangeOrderAddressModel;
import com.puyue.www.qiaoge.model.mine.order.ConfirmGetGoodsModel;
import com.puyue.www.qiaoge.model.mine.order.CopyToCartModel;
import com.puyue.www.qiaoge.model.mine.order.OrderEvaluateListModel;
import com.puyue.www.qiaoge.model.mine.order.ReturnEquipmentOrderListByIdModel;
import com.puyue.www.qiaoge.view.GlideModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/13.
 */

public class OrderDetailActivity extends BaseSwipeActivity {
    private ImageView mIvBack;
    private TextView mTvCopy;
    private TextView mTvUserName;
    private TextView mTvUserPhone;
    private TextView mTvUserAddress;
    private TextView mTvUserStore;
    private LinearLayout mLlOrder;
    private LinearLayout mLlMessage;
    private LinearLayout mLlRemarks;
    private TextView mTvRemarks;
    private TextView mTvReturnReson;
    private LinearLayout mLlReviewFeedback;
    private TextView mTvReviewFeedback;
    private FrameLayout mFlButton;
    private LinearLayout mLlButtonFull;
    private Button mBtnFull;
    private LinearLayout mLlButtonPart;
    private Button mBtnPartOne;
    private Button mBtnPartTwo;

    private String orderId;
    private String orderState = "";
    private Dialog mDialog;
    private int orderStatus;
    private int businessType;
    private TextView mTvTotalNum;
    private TextView mTvTotalPrice;
    private TextView mTvOrderId;
    private TextView mTvOrderState;
    private boolean canReturn = true;
    private String cannotReturnGoodsMsg;
    private int checkStatus;
    private GetOrderDetailModel mModelOrderDetail;
    private List<String> equipmentId = new ArrayList<>();
    private ReturnEquipmentOrderListByIdModel mModelReturnEquipment;
    private List<OrderEvaluateListModel> mListEvaluate = new ArrayList<>();
    private List<GetOrderDetailModel.DataBean.ProductVOListBean> mListForReturn = new ArrayList<>();

    private ConfirmGetGoodsModel mModelConfirmGetGoods;
    private LoadingDailog dialog;
    private LinearLayout mLlAddAddress;
    private FrameLayout mFlHadAddress;
    private boolean hasAddress = true;
    private ChangeOrderAddressModel mModelChangeOrderAddress;
    private CopyToCartModel mModelCopyToCart;
    private LinearLayout mLlReturnMessage;
    private TextView mTvReturnAmount;
    private TextView mTvReturnPrice;

    private String returnProductMainId = "";

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            orderId = bundle.getString(AppConstant.ORDERID, null);
            orderState = bundle.getString(AppConstant.ORDERSTATE);
            returnProductMainId = bundle.getString(AppConstant.RETURNPRODUCTMAINID);
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleExtra(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_order_detail);
    }

    @Override
    public void findViewById() {
        mIvBack = (ImageView) findViewById(R.id.iv_order_detail_back);
        mTvCopy = (TextView) findViewById(R.id.tv_order_detail_copy);
        mTvUserName = (TextView) findViewById(R.id.tv_order_detail_user_name);
        mTvUserPhone = (TextView) findViewById(R.id.tv_order_detail_user_phone);
        mTvUserAddress = (TextView) findViewById(R.id.tv_order_detail_user_address);
        mTvUserStore = (TextView) findViewById(R.id.tv_order_detail_user_store);
        mLlOrder = FVHelper.fv(this, R.id.ll_activity_detail_order);
        mLlMessage = FVHelper.fv(this, R.id.ll_activity_detail_message);
        mLlRemarks = (LinearLayout) findViewById(R.id.ll_order_detail_remarks);//订单的备注,要根据后台是否有备注来判断是否显示
        mTvRemarks = (TextView) findViewById(R.id.tv_order_detail_remarks);//备注文案
        mTvReturnReson = (TextView) findViewById(R.id.tv_order_detail_return_reason);//退货原因,根据订单状态来判断是否是退货状态,退货状态才会有这个
        mLlReviewFeedback = (LinearLayout) findViewById(R.id.ll_order_detail_review_feedback);//审核反馈,该订单是退货状态并且已经审核了才有这个
        mTvReviewFeedback = (TextView) findViewById(R.id.tv_order_detail_review_feedback);//审核反馈的文案
        mFlButton = (FrameLayout) findViewById(R.id.fl_order_detail_button);

        mLlButtonFull = (LinearLayout) findViewById(R.id.ll_order_detail_button_full);//单个按钮的情况
        mBtnFull = (Button) findViewById(R.id.btn_order_detail_full);//单个按钮的情况:退货,再次申请
        mLlButtonPart = (LinearLayout) findViewById(R.id.ll_order_detail_button_part);//双按钮状态的情况:立即支付(未支付),确认收货(已发货),立即评价(已到货)
        mBtnPartOne = (Button) findViewById(R.id.btn_order_detail_part_one);
        mBtnPartTwo = (Button) findViewById(R.id.btn_order_detail_part_two);
        mTvTotalNum = (TextView) findViewById(R.id.tv_order_detail_total_num);
        mTvTotalPrice = (TextView) findViewById(R.id.tv_order_detail_total_price);
        mTvOrderId = (TextView) findViewById(R.id.tv_order_detail_order_id);
        mTvOrderState = (TextView) findViewById(R.id.tv_order_detail_order_state);
        mLlAddAddress = (LinearLayout) findViewById(R.id.ll_order_detail_add_address);
        mFlHadAddress = (FrameLayout) findViewById(R.id.fl_order_detail_address);
        mLlReturnMessage = (LinearLayout) findViewById(R.id.ll_order_detail_return_message);
        mTvReturnAmount = (TextView) findViewById(R.id.tv_order_detail_return_amount);//退货数量
        mTvReturnPrice = (TextView) findViewById(R.id.tv_order_detail_return_price);
    }

    @Override
    public void setViewData() {
        equipmentId.clear();
        IntentFilter filter = new IntentFilter(AppConstant.SERVICE_ACTION);
        registerReceiver(broadcastReceiver, filter);
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(mContext)
                .setMessage("刷新数据中")
                .setCancelable(false)
                .setCancelOutside(false);
        dialog = loadBuilder.create();
        getOrderDetail(orderId, orderState, returnProductMainId);
    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                backEvent();
            }
        });
        mTvCopy.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                //将这个订单内的商品统一加入到购物车
                requestCopyToCart();
            }
        });
        mLlAddAddress.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                //跳转地址列表界面
                startActivityForResult(AddressListActivity.getIntent(mContext, AddressListActivity.class), 34);
            }
        });
    }

    private void requestCopyToCart() {
        CopyToCartAPI.requestCopyToCart(mContext, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CopyToCartModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CopyToCartModel copyToCartModel) {
                        mModelCopyToCart = copyToCartModel;
                        if (mModelCopyToCart.success) {
                            //将订单内的商品加入购物车
                            AppHelper.showMsg(mContext, mModelCopyToCart.message);
                        } else {
                             AppHelper.showMsg(mContext, mModelCopyToCart.message);
                        }
                    }
                });
    }

    /**
     * 获取详情
     */
    private void getOrderDetail(String orderId, String orderState, String returnProductMainId) {
        GetOrderDetailAPI.requestData(mContext, orderId, orderState, returnProductMainId)
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
                        logoutAndToHome(mContext, getOrderDetailModel.code);
                        dialog.dismiss();
                        mModelOrderDetail = getOrderDetailModel;
                        if (mModelOrderDetail.success) {
                            if (mModelOrderDetail.data.addressVO != null) {
                                if (mModelOrderDetail.data.addressVO.areaCode != null) {
                                    //这个用户有地址
                                    hasAddress = true;
                                    mFlHadAddress.setVisibility(View.VISIBLE);
                                    mLlAddAddress.setVisibility(View.GONE);
                                    GetOrderDetailModel.DataBean.AddressVOBean bean = mModelOrderDetail.data.addressVO;
                                    mTvUserName.setText(bean.userName);
                                    mTvUserPhone.setText(bean.contactPhone);
                                    mTvUserAddress.setText(bean.provinceName + bean.cityName + bean.areaName + bean.detailAddress);
                                    mTvUserStore.setText(bean.shopName);
                                } else {
                                    //这个用户没有地址
                                    hasAddress = false;
                                    mFlHadAddress.setVisibility(View.GONE);
                                    mLlAddAddress.setVisibility(View.VISIBLE);
                                }
                                orderStatus = mModelOrderDetail.data.orderStatus;
                                checkStatus = mModelOrderDetail.data.checkStatus;//退货订单的状态
                                businessType = mModelOrderDetail.data.productVOList.get(0).businessType;
                                setButtonTextAndClick();
                                mTvRemarks.setText(mModelOrderDetail.data.remark);
                                mTvTotalNum.setText("合计: " + mModelOrderDetail.data.totalNum + "样商品");
                                mTvTotalPrice.setText("¥ " + mModelOrderDetail.data.totalAmount);
                                mTvOrderId.setText("订单编号：" + mModelOrderDetail.data.orderId);
                                if (mModelOrderDetail.data.orderStatus == 11) {
                                    //这个订单是退货状态的,需要显示退货状态信息
                                    mTvOrderState.setText(mModelOrderDetail.data.checkStatusName);
                                } else {
                                    //这个订单是别的状态
                                    mTvOrderState.setText(mModelOrderDetail.data.orderStatusName);
                                }
                                canReturn = mModelOrderDetail.data.canReturnGoods;//是否能退货
                                cannotReturnGoodsMsg = mModelOrderDetail.data.cannotReturnGoodsMsg;//不能退货的原因
                            }
                            if (mModelOrderDetail.data.productVOList != null) {
                                addViewAndDate(mModelOrderDetail.data.productVOList);
                            }
                            if (mModelOrderDetail.data.dateList != null) {
                                addMessageView(mModelOrderDetail.data.dateList);
                            }
                            if (StringHelper.notEmptyAndNull(mModelOrderDetail.data.actuallyReturnProductDetail)) {
                                mLlReturnMessage.setVisibility(View.VISIBLE);
                                mTvReturnAmount.setText(mModelOrderDetail.data.actuallyReturnProductDetail);
                                mTvReturnPrice.setText(mModelOrderDetail.data.actuallyreturnAmount);
                            } else {
                                mLlReturnMessage.setVisibility(View.GONE);
                            }
                        } else {
                             AppHelper.showMsg(mContext, mModelOrderDetail.message);
                        }
                    }
                });
    }

    /**
     * 填充订单信息布局视图
     */
    private void addMessageView(List<String> dateList) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);       //LayoutInflater inflater1=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mLlMessage.removeAllViews();
        for (int i = 0; i < dateList.size(); i++) {
            View view = inflater.inflate(R.layout.item_order_detail, null);
            view.setLayoutParams(lp);
            mLlMessage.addView(view);
            TextView textView = FVHelper.fv(view, R.id.tv_item_detail_message);
            textView.setText(dateList.get(i));
        }
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
        LinearLayout mLlPrice = (LinearLayout) FVHelper.fv(view, R.id.ll_item_order_price);
        mLlPrice.setVisibility(View.GONE);
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

    private void setButtonTextAndClick() {
        if (orderStatus == 1) {
            //待付款,两个按钮,一个"取消订单",一个"立即支付"
            mLlButtonPart.setVisibility(View.VISIBLE);
            mLlButtonFull.setVisibility(View.GONE);
            mBtnPartOne.setText("取消订单");
            mBtnPartOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showCancleOrder();
                }
            });
            mBtnPartTwo.setText("立即付款");
            mBtnPartTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (hasAddress) {
                        //如果这个用户有地址
                        Intent intent = new Intent(mContext, MyConfireOrdersActivity.class);
                        intent.putExtra("remark", mModelOrderDetail.data.remark);
                        intent.putExtra("payAmount",Double.parseDouble(mModelOrderDetail.data.totalAmount));
                        intent.putExtra("orderId", orderId);
                        intent.putExtra("flag",true);
                        finish();
                        startActivity(intent);

                    } else {
                        //这个用户没有地址
                         AppHelper.showMsg(mContext, "请先添加收货地址!");
                    }
                }
            });
        } else if (orderStatus == 2) {
            //待发货,一个按钮,"退货"
            mLlButtonPart.setVisibility(View.GONE);
            mLlButtonFull.setVisibility(View.VISIBLE);
            mBtnFull.setText("退货");
            mBtnFull.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //进行订单退货
                    requestReturnGoods(1);
                }
            });
        } else if (orderStatus == 3) {
            //待收货,两个按钮,一个"退货",一个"确认收货"
            //待收货的商品还要分情况,如果是设备,场地,师傅(4,5,6)这种预约类商品,没有所谓的确认收货和退货,直接没有按钮
            //如果是普通购买商品,需要有确认收货和退货按钮
            if (businessType == 4 || businessType == 5 || businessType == 6) {
                //设备,场地,师傅,这种预约商品
                mLlButtonPart.setVisibility(View.GONE);
                mLlButtonFull.setVisibility(View.GONE);
            } else {
                //其他普通商品
                mLlButtonPart.setVisibility(View.VISIBLE);
                mLlButtonFull.setVisibility(View.GONE);
                mBtnPartOne.setText("退货");
                mBtnPartOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        requestReturnGoods(1);
                    }
                });
                mBtnPartTwo.setText("确认收货");
                mBtnPartTwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        requestConfirmGetGoods();
                    }
                });
            }
        } else if (orderStatus == 4 || orderStatus == 5) {
            //已完成,待评价是一样的
            //但是这个情况下要分商品情况来显示按钮,
            // 普通商品就是显示"退货","立即评价"
            //预约商品(场地,师傅)只能"立即评价"
            //设备预约(需要归还),可以"归还设备","立即评价"
            if (businessType == 4 || businessType == 5) {
                //预约商品(场地,师傅)
                mLlButtonFull.setVisibility(View.VISIBLE);
                mLlButtonPart.setVisibility(View.GONE);
                mBtnFull.setText("立即评价");
                mBtnFull.setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View view) {
                        //对这个订单进行评价
                        requestEvaluate();
                    }
                });
            } else if (businessType == 1 || businessType == 2 || businessType == 3 || businessType == 7) {
                //普通商品
                mLlButtonPart.setVisibility(View.VISIBLE);
                mLlButtonFull.setVisibility(View.GONE);
                mBtnPartOne.setText("退货");
                mBtnPartOne.setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View view) {
                        requestReturnGoods(1);
                    }
                });
                mBtnPartTwo.setText("立即评价");
                mBtnPartTwo.setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View view) {
                        requestEvaluate();
                    }
                });
            } else if (businessType == 6) {
                //设备预约
                mLlButtonPart.setVisibility(View.VISIBLE);
                mLlButtonFull.setVisibility(View.GONE);
                mBtnPartOne.setText("归还设备");
                mBtnPartOne.setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View view) {
                        equipmentId.clear();
                        equipmentId.add("\"" + mModelOrderDetail.data.orderId + "\"");
                        requestReturnEquipment(equipmentId.toString());
                    }
                });
                mBtnPartTwo.setText("立即评价");
                mBtnPartTwo.setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View view) {
                        requestEvaluate();
                    }
                });
            }
        } else if (orderStatus == 6) {
            //已评价,分状态
            if (businessType == 4 || businessType == 5) {
                //预约商品(场地,师傅)的已评价状态是没有按钮的
                mLlButtonFull.setVisibility(View.GONE);
                mLlButtonPart.setVisibility(View.GONE);
            } else if (businessType == 1 || businessType == 2 || businessType == 3 || businessType == 7) {
                //普通商品,这时候只有退货
                mLlButtonFull.setVisibility(View.VISIBLE);
                mLlButtonPart.setVisibility(View.GONE);
                mBtnFull.setText("退货");
                mBtnFull.setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View view) {
                        requestReturnGoods(1);
                    }
                });
            } else if (businessType == 6) {
                //设备预约,已评价状态是有一个按钮,是归还设备
                mLlButtonFull.setVisibility(View.VISIBLE);
                mLlButtonPart.setVisibility(View.GONE);
                mBtnFull.setText("归还设备");
                mBtnFull.setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View view) {
                        equipmentId.clear();
                        equipmentId.add("\"" + mModelOrderDetail.data.orderId + "\"");
                        requestReturnEquipment(equipmentId.toString());
                    }
                });
            }
        } else if (orderStatus == 7) {
            //已取消(订单关闭),没有按钮
            mLlButtonFull.setVisibility(View.GONE);
            mLlButtonPart.setVisibility(View.GONE);
        } else if (orderStatus == 8) {
            //待归还,两个按钮,一个"设备归还",一个"立即评价"
            mLlButtonFull.setVisibility(View.GONE);
            mLlButtonPart.setVisibility(View.VISIBLE);
            mBtnPartOne.setText("归还设备");
            mBtnPartOne.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    equipmentId.clear();
                    equipmentId.add("\"" + mModelOrderDetail.data.orderId + "\"");
                    requestReturnEquipment(equipmentId.toString());
                }
            });
            mBtnPartTwo.setText("立即评价");
            mBtnPartTwo.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    requestEvaluate();
                }
            });
        } else if (orderStatus == 9) {
            //归还中,无按钮
            mLlButtonPart.setVisibility(View.GONE);
            mLlButtonFull.setVisibility(View.GONE);
        } else if (orderStatus == 10) {
            //已归还,无按钮
            mLlButtonPart.setVisibility(View.GONE);
            mLlButtonFull.setVisibility(View.GONE);
        } else if (orderStatus == 11) {
            //退货,退货状态的订单显示还分不同
            //这个退货订单如果是"审核中"或者"审核通过"的状态,不显示按钮,并且只显示退货原因
            //如果是"不通过"状态,还要显示审核反馈:,并且显示一个按钮"再次申请"
            if (checkStatus == 0 || checkStatus == 1) {
                //审核中,审核通过
                mLlButtonFull.setVisibility(View.GONE);
                mLlButtonPart.setVisibility(View.GONE);
                mTvReturnReson.setVisibility(View.VISIBLE);
                mTvReturnReson.setText("退货原因：" + mModelOrderDetail.data.returnReson);
                mLlReviewFeedback.setVisibility(View.GONE);
            } else if (checkStatus == 2) {
                //审核不通过
                mLlButtonFull.setVisibility(View.VISIBLE);
                mLlButtonPart.setVisibility(View.GONE);
                mBtnFull.setText("再次申请");
                mBtnFull.setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View view) {
                        //再次申请退货,需要跳转再次申请退货的界面,再次申请退货的货物,不能选择数量,只能全选进行下一步
                        requestReturnGoods(2);
                    }
                });
                mTvReturnReson.setVisibility(View.VISIBLE);
                mTvReturnReson.setText("退货原因：" + mModelOrderDetail.data.returnReson);
                mLlReviewFeedback.setVisibility(View.VISIBLE);
                mTvReviewFeedback.setText(mModelOrderDetail.data.feedbackReson);
            }
        }
    }

    private void requestConfirmGetGoods() {
        ConfirmGetGoodsAPI.reuqestConfirmGetGoods(mContext, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ConfirmGetGoodsModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ConfirmGetGoodsModel confirmGetGoodsModel) {
                        mModelConfirmGetGoods = confirmGetGoodsModel;
                        if (mModelConfirmGetGoods.success) {
                            //确认收货成功
                             AppHelper.showMsg(mContext, "确认收货成功");
                            //刷新订单状态
                            getOrderDetail(orderId, orderState, returnProductMainId);
                        } else {
                             AppHelper.showMsg(mContext, mModelConfirmGetGoods.message);
                        }
                    }
                });
    }

    private void requestEvaluate() {
        //去评价需要将订单里面的商品列表中的商品的商品名,商品ID组成list,传到评价的界面
        mListEvaluate.clear();
        if (mModelOrderDetail.data.productVOList != null && mModelOrderDetail.data.productVOList.size() > 0) {
            for (int i = 0; i < mModelOrderDetail.data.productVOList.size(); i++) {
                mListEvaluate.add(new OrderEvaluateListModel(mModelOrderDetail.data.productVOList.get(i).productId, mModelOrderDetail.data.productVOList.get(i).businessType, mModelOrderDetail.data.productVOList.get(i).name,mModelOrderDetail.data.productVOList.get(i).picUrl,5+"",""));
            }
        } else {
             AppHelper.showMsg(mContext, "订单商品数据错误!");
        }
        Intent intentPut = new Intent(mContext, OrderEvaluateActivity.class);
        intentPut.putExtra("evaluateList", (Serializable) mListEvaluate);
        intentPut.putExtra("orderId", orderId);

        startActivityForResult(intentPut, 12);
    }

    private void requestReturnEquipment(String orderIds) {
        //将这个设备归还,这里是订单详情,如果是设备的订单详情,肯定只有一个设备
        ReturnEquipmentOrderListByIdAPI.requestData(mContext, orderIds)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReturnEquipmentOrderListByIdModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(ReturnEquipmentOrderListByIdModel returnEquipmentOrderListByIdModel) {
                        mModelReturnEquipment = returnEquipmentOrderListByIdModel;
                        if (mModelReturnEquipment.success) {
                             AppHelper.showMsg(mActivity, "设备归还成功");
                            //刷新数据
                            getOrderDetail(orderId, orderState, returnProductMainId);
                        } else {
                             AppHelper.showMsg(mActivity, mModelReturnEquipment.message);
                        }
                    }
                });
    }

    private void requestReturnGoods(int type) {
        //退货,先判断这个订单是否让退货
        if (canReturn) {
            //能退货,要根据商品类型来判断需要跳转哪里
            //批发用户申请退货,只能直接退货,不能选择规格,直接全部退货,选取orderId进行退货即可
            //零售用户
            mListForReturn.clear();
            mListForReturn.addAll(mModelOrderDetail.data.productVOList);
            if (type == 1) {
                //第一次退货,直接跳转退货列表
                Intent intent = new Intent(mContext, ReturnGoodsListActivity.class);
                intent.putExtra("goodsList", (Serializable) mListForReturn);
                intent.putExtra("orderId", orderId);
                intent.putExtra("orderType", mModelOrderDetail.data.orderType);
                intent.putExtra("returnProductMainId", returnProductMainId);
                startActivity(intent);
            } else if (type == 2) {
                //再次申请退货,跳转再次申请退货的列表,这个列表不能选择选中什么商品
                Intent intent = new Intent(mContext, ReturnGoodsAgainListActivity.class);
                intent.putExtra("goodsList", (Serializable) mListForReturn);
                intent.putExtra("orderId", orderId);
                intent.putExtra("orderType", mModelOrderDetail.data.orderType);
                intent.putExtra("returnProductMainId", returnProductMainId);
                startActivity(intent);
            }
        } else {
            //不能退货,弹框提示消息
            showCannotReturnDialog();
        }
    }

    private void showCannotReturnDialog() {
        final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(mContext, R.style.CommonDialogStyle).create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_can_not_return_reason);
        TextView mTvReason = (TextView) window.findViewById(R.id.tv_dialog_cannot_return_reason);
        Button mBtnConfirm = (Button) window.findViewById(R.id.btn_dialog_cannot_return_confirm);
        mTvReason.setText(cannotReturnGoodsMsg);
        mBtnConfirm.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    /**
     * 取消订单
     */
    private void cancelOrder(final String orderId) {
        CancelOrderAPI.requestData(mContext, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CancelOrderModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CancelOrderModel cancelOrderModel) {
                        if (cancelOrderModel.success) {
                            //取消成功
                             AppHelper.showMsg(OrderDetailActivity.this, "取消订单成功");
                            getOrderDetail(orderId, orderState, returnProductMainId);
                        } else {
                             AppHelper.showMsg(OrderDetailActivity.this, cancelOrderModel.message);
                        }
                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            backEvent();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void backEvent() {
        if (orderStatus == 1) {
            //待付款
            startActivity(MyOrdersActivity.getIntent(mContext, MyOrdersActivity.class, AppConstant.PAYMENT));
        } else if (orderStatus == 2) {
            //待发货
            startActivity(MyOrdersActivity.getIntent(mContext, MyOrdersActivity.class, AppConstant.DELIVERY));
        } else if (orderStatus == 3) {
            //待收货
            startActivity(MyOrdersActivity.getIntent(mContext, MyOrdersActivity.class, AppConstant.RECEIVED));
        } else if (orderStatus == 4) {
            //已完成
            startActivity(MyOrdersActivity.getIntent(mContext, MyOrdersActivity.class, AppConstant.ALL));
        } else if (orderStatus == 5) {
            //待评价
            startActivity(MyOrdersActivity.getIntent(mContext, MyOrdersActivity.class, AppConstant.EVALUATED));
        } else if (orderStatus == 6) {
            //已评价
            startActivity(MyOrdersActivity.getIntent(mContext, MyOrdersActivity.class, AppConstant.ALL));
        } else if (orderStatus == 7) {
            //已取消(订单关闭)
            startActivity(MyOrdersActivity.getIntent(mContext, MyOrdersActivity.class, AppConstant.ALL));
        } else if (orderStatus == 8) {
            //待归还
            startActivity(MyOrdersActivity.getIntent(mContext, MyOrdersActivity.class, AppConstant.ALL));
        } else if (orderStatus == 9) {
            //归还中
            startActivity(MyOrdersActivity.getIntent(mContext, MyOrdersActivity.class, AppConstant.ALL));
        } else if (orderStatus == 10) {
            //已归还
            startActivity(MyOrdersActivity.getIntent(mContext, MyOrdersActivity.class, AppConstant.ALL));
        } else if (orderStatus == 11) {
            //退货
            startActivity(MyOrdersActivity.getIntent(mContext, MyOrdersActivity.class, AppConstant.RETURN));
        }
        finish();
    }

    /**
     * 显示取消订单提示
     */
    private void showCancleOrder() {
        mDialog = new AlertDialog.Builder(mContext).create();
        mDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        mDialog.show();
        mDialog.getWindow().setContentView(R.layout.dialog_are_you_sure);
        TextView mTvTip = mDialog.getWindow().findViewById(R.id.tv_dialog_tip);
        mTvTip.setText("确认取消订单?");
        mDialog.getWindow().findViewById(R.id.tv_dialog_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.getWindow().findViewById(R.id.tv_dialog_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                cancelOrder(orderId);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 55) {
            dialog.show();
            getOrderDetail(orderId, orderState, returnProductMainId);
        } else if (resultCode == 33) {
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
                            getOrderDetail(orderId, orderState, returnProductMainId);
                        } else {
                             AppHelper.showMsg(mContext, mModelChangeOrderAddress.message);
                        }
                    }
                });
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String mType = intent.getExtras().getString("type");
            if (StringHelper.notEmptyAndNull(mType)) {
                if (mType.equals("return")) {
                    dialog.show();
                    //这里是退货完成了,这里应该要将退货详情显示出来
                    String newReturnOrderStatus = intent.getExtras().getString("orderStatus");
                    String newReturnProductMainId = intent.getExtras().getString("returnProductMainId");
                    getOrderDetail(orderId, newReturnOrderStatus, newReturnProductMainId);
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

}
