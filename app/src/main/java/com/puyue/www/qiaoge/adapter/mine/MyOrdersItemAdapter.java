package com.puyue.www.qiaoge.adapter.mine;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.order.NewOrderDetailActivity;
import com.puyue.www.qiaoge.activity.mine.order.OrderDetailActivity;
import com.puyue.www.qiaoge.activity.mine.order.ReturnGoodDetailActivity;
import com.puyue.www.qiaoge.activity.mine.order.SelfSufficiencyOrderDetailActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.GlideRoundTransform;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.order.MyOrdersModel;
import com.puyue.www.qiaoge.view.GlideModel;
import com.puyue.www.qiaoge.view.GradientColorTextView;

import java.util.List;

import static com.umeng.socialize.utils.ContextUtil.getContext;

/**
 * Created by Administrator on 2018/4/10.
 */

public class MyOrdersItemAdapter extends BaseQuickAdapter<MyOrdersModel.DataBean.ListBean, BaseViewHolder> {
    private MyOrderListAdapter mAdapterMyOrdersList;
    private int orderState;
    private ImageView commodityOne;
    private ImageView commodityTwo;
    private ImageView commodityThree;
    private ImageView commodityFour;
    private ImageView commodityMore;
    private GradientColorTextView orderType;
    private ImageView imageGo;//立即付款
    private ImageView evaluateNow; // 立即评价
    private ImageView againBay;  // 再次购买
    private OnClick onClick;
    private LinearLayout linearLayoutItem;
    private ImageView cancelOrder;//取消订单
    private ImageView deleteOrder;//删除订单
    private ImageView confirmOrder;//确认收货
    private ImageView iv_order_self_return;//待提货售后
    private ImageView iv_confirm_order_self;//待提货确认收货
    private boolean isCheck = true;

    private int orderDeliveryType;

    public MyOrdersItemAdapter(int layoutResId, @Nullable List<MyOrdersModel.DataBean.ListBean> data, int orderState, int orderDeliveryType, OnClick onClick) {
        super(layoutResId, data);
        this.orderState = orderState;
        this.onClick = onClick;
        this.orderDeliveryType = orderDeliveryType;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final MyOrdersModel.DataBean.ListBean item) {
        againBay = helper.getView(R.id.againBay);
        commodityOne = helper.getView(R.id.commodityOne);
        commodityTwo = helper.getView(R.id.commodityTwo);
        commodityThree = helper.getView(R.id.commodityThree);
        commodityFour = helper.getView(R.id.commodityFour);
        commodityMore = helper.getView(R.id.commodityMore);
        orderType = helper.getView(R.id.tv_item_my_order_type);
        imageGo = helper.getView(R.id.imageGo);
        deleteOrder = helper.getView(R.id.iv_delete_order);
        cancelOrder = helper.getView(R.id.iv_cancel_order);
        evaluateNow = helper.getView(R.id.evaluateNow);
        linearLayoutItem = helper.getView(R.id.linearLayoutItem);
        confirmOrder = helper.getView(R.id.iv_confirm_order);

        if (orderDeliveryType == 0) {

        } else if (orderDeliveryType == 1) {
            iv_order_self_return = helper.getView(R.id.iv_order_self_return);
            iv_confirm_order_self = helper.getView(R.id.iv_confirm_order_self);
        }


        helper.setText(R.id.tv_item_my_order_all_price, item.totalAmount);//总价
        if (item.productVOList.size() >= 1) {

            helper.setText(R.id.tv_item_my_order_final_text, item.productVOList.get(0).name + "等" + item.totalNum + "件商品");
        } else {
            helper.setText(R.id.tv_item_my_order_final_text, "等" + item.totalNum + "件商品");

        }

        helper.setText(R.id.subUserBuy, item.subUserBuy);
        // orderState 0全部 orderState 2 待发货订单 orderState 5 待评价订单
        // 1待付款订单 3待收货订单 11退货订单 7 已取消




        if (orderState == 11) { //退货订单 不显示按钮
            imageGo.setVisibility(View.GONE);
            againBay.setVisibility(View.VISIBLE);
            evaluateNow.setVisibility(View.GONE);
            confirmOrder.setVisibility(View.GONE);
            deleteOrder.setVisibility(View.GONE);
            cancelOrder.setVisibility(View.GONE);
            orderType.setText(item.returnOrderState);
        } else {
            if (item.orderStatusName.equals("待付款")) { // 待付款不显示 再次购买 其他的显示，取消的显示
                imageGo.setVisibility(View.VISIBLE);
                cancelOrder.setVisibility(View.VISIBLE);
                againBay.setVisibility(View.GONE);
                evaluateNow.setVisibility(View.GONE);
                confirmOrder.setVisibility(View.GONE);
                deleteOrder.setVisibility(View.GONE);
            } else if (item.orderStatusName.equals("已取消")) {
                imageGo.setVisibility(View.GONE);
                deleteOrder.setVisibility(View.VISIBLE);
                againBay.setVisibility(View.VISIBLE);
                cancelOrder.setVisibility(View.GONE);
                evaluateNow.setVisibility(View.GONE);
                confirmOrder.setVisibility(View.GONE);
            } else if (item.orderStatusName.equals("待发货")) {
                againBay.setVisibility(View.VISIBLE);
                cancelOrder.setVisibility(View.GONE);
                evaluateNow.setVisibility(View.GONE);
                imageGo.setVisibility(View.GONE);
                deleteOrder.setVisibility(View.GONE);
                confirmOrder.setVisibility(View.GONE);
            } else if (item.orderStatusName.equals("待收货")) {
                confirmOrder.setVisibility(View.VISIBLE);
                againBay.setVisibility(View.VISIBLE);
                imageGo.setVisibility(View.GONE);
                deleteOrder.setVisibility(View.GONE);
                cancelOrder.setVisibility(View.GONE);
                evaluateNow.setVisibility(View.GONE);
            }


            if (item.orderStatusName.equals("待评价")) {   // 待评价显示 立即评价 和再次购买
                evaluateNow.setVisibility(View.VISIBLE);
                againBay.setVisibility(View.VISIBLE);
                deleteOrder.setVisibility(View.GONE);
                cancelOrder.setVisibility(View.GONE);
                imageGo.setVisibility(View.GONE);
                confirmOrder.setVisibility(View.GONE);
            } else {
                evaluateNow.setVisibility(View.GONE);

            }
            orderType.setText(item.orderStatusName);
        }

        helper.setText(R.id.orderTime, item.gmtCreate);
        // 文字渐变色
        LinearGradient mLinearGradient = new LinearGradient(0, 0, 0, orderType.getPaint().getTextSize(), Color.parseColor("#CEA6FF")
                , Color.parseColor("#6F81FF"), Shader.TileMode.CLAMP);
        orderType.getPaint().setShader(mLinearGradient);


        /**显示4张图*/
        if (item.productVOList.size() >= 1) {
            if (item.productVOList.get(0).picUrl != null) {
                GlideModel.disPlayError(mContext, item.productVOList.get(0).picUrl, commodityOne);
                //  Glide.with(mContext).load(item.productVOList.get(0).picUrl).transform(new GlideRoundTransform(mContext, 3)).into(commodityOne);
            }

            commodityOne.setVisibility(View.VISIBLE);
        } else {

            commodityOne.setVisibility(View.GONE);
            commodityOne.setImageResource(R.mipmap.ic_launcher_round);
        }
        if (item.productVOList.size() >= 2) {
            if (item.productVOList.get(1).picUrl != null) {
                GlideModel.disPlayError(mContext, item.productVOList.get(1).picUrl, commodityTwo);
                //  Glide.with(mContext).load(item.productVOList.get(1).picUrl).transform(new GlideRoundTransform(mContext, 3)).into(commodityTwo);
            }

            commodityTwo.setVisibility(View.VISIBLE);
        } else {
            commodityTwo.setVisibility(View.GONE);
            commodityTwo.setImageResource(R.mipmap.ic_launcher_round);
        }
        if ((item.productVOList.size() >= 3)) {
            if (item.productVOList.get(2).picUrl != null) {
                GlideModel.disPlayError(mContext, item.productVOList.get(2).picUrl, commodityThree);
                //Glide.with(mContext).load(item.productVOList.get(2).picUrl).transform(new GlideRoundTransform(mContext, 3)).into(commodityThree);
            }

            commodityThree.setVisibility(View.VISIBLE);

        } else {
            commodityThree.setVisibility(View.GONE);
            commodityThree.setImageResource(R.mipmap.ic_launcher_round);
        }
        if (item.productVOList.size() >= 4) {
            if (item.productVOList.get(3).picUrl != null) {
                GlideModel.disPlayError(mContext, item.productVOList.get(3).picUrl, commodityFour);
                //  Glide.with(mContext).load(item.productVOList.get(3).picUrl).transform(new GlideRoundTransform(mContext, 3)).into(commodityFour);
            }

            commodityFour.setVisibility(View.VISIBLE);
        } else {
            commodityFour.setVisibility(View.GONE);
            commodityFour.setImageResource(R.mipmap.ic_launcher_round);
        }
        if (item.productVOList.size() >= 4) {
            commodityMore.setVisibility(View.VISIBLE);
        } else {
            commodityMore.setVisibility(View.GONE);

        }


        linearLayoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deliverType = UserInfoHelper.getDeliverType(mContext);
                if (deliverType.equals("0")) {

                    if (orderState == 11){
                        Intent intent =new Intent(mContext,ReturnGoodDetailActivity.class);
intent.putExtra("orderType" ,0);
                        intent.putExtra(AppConstant.RETURNPRODUCTMAINID, item.returnProductMainId);

                        mContext.startActivity(intent);
                    }else {
                        Intent intent = new Intent(mContext, NewOrderDetailActivity.class);
                        intent.putExtra(AppConstant.ORDERID, item.orderId);
                        intent.putExtra(AppConstant.ORDERSTATE, "");
                        intent.putExtra(AppConstant.RETURNPRODUCTMAINID, "");
                        mContext.startActivity(intent);
                    }
                /*
                    if (orderState == 11) {
                        //退货订单
                        intent.putExtra(AppConstant.ORDERSTATE, "11");
                        intent.putExtra(AppConstant.RETURNPRODUCTMAINID, item.returnProductMainId);
                    } else {

                    }*/

                } else if (deliverType.equals("1")) {


                    if (orderState == 11){
                        Intent intent =new Intent(mContext,ReturnGoodDetailActivity.class);
                        intent.putExtra("orderType" ,1);
                        intent.putExtra(AppConstant.RETURNPRODUCTMAINID, item.returnProductMainId);

                        mContext.startActivity(intent);
                    }else {
                        Intent intent = new Intent(mContext, SelfSufficiencyOrderDetailActivity.class);
                        intent.putExtra(AppConstant.ORDERID, item.orderId);
                        intent.putExtra(AppConstant.ORDERSTATE, "");
                        intent.putExtra(AppConstant.RETURNPRODUCTMAINID, "");
                        mContext.startActivity(intent);
                    }

                }


            }
        });


        evaluateNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.evaluateNowOnclick(helper.getLayoutPosition());
            }
        });
        againBay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.againBayOnclick(helper.getLayoutPosition());
            }
        });

        cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.cancelOnclick(item.orderId);

            }
        });
        deleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.deleteOnclick(item.orderId);
            }
        });
        imageGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.imageGo(item.orderId, item.totalAmount);
            }
        });

        confirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.requestConfirmGetGoods(item.orderId);
            }
        });


        if (orderDeliveryType == 1) {
            if (item.orderStatus == 2) {
                iv_order_self_return.setVisibility(View.VISIBLE);
                iv_confirm_order_self.setVisibility(View.VISIBLE);
                iv_order_self_return.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClick.confirmSelfReturnOrder(item.orderId,helper.getLayoutPosition());
                    }
                });
                iv_confirm_order_self.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClick.confirmSelfOrder(item.orderId);
                    }
                });

            } else {
                iv_order_self_return.setVisibility(View.GONE);
                iv_confirm_order_self.setVisibility(View.GONE);
            }
        }


    }


    public interface OnClick {


        void evaluateNowOnclick(int position);

        void againBayOnclick(int position);

        void cancelOnclick(String orderId);

        void deleteOnclick(String orderId);

        void imageGo(String orderId, String totalAmount);

        void requestConfirmGetGoods(String orderId);


        void confirmSelfOrder(String orderId);
        void confirmSelfReturnOrder(String orderId,int pos);

    }
}
