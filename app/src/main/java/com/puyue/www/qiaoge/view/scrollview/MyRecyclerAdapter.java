package com.puyue.www.qiaoge.view.scrollview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.NewWebViewActivity;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.CommonH5Activity;
import com.puyue.www.qiaoge.activity.mine.FeedBackActivity;
import com.puyue.www.qiaoge.activity.mine.MyCollectionActivity;
import com.puyue.www.qiaoge.activity.mine.SubAccountActivity;
import com.puyue.www.qiaoge.activity.mine.account.AddressListActivity;
import com.puyue.www.qiaoge.activity.mine.coupons.MyCouponsActivity;
import com.puyue.www.qiaoge.activity.mine.order.MyOrdersActivity;
import com.puyue.www.qiaoge.activity.mine.wallet.MinerIntegralActivity;
import com.puyue.www.qiaoge.activity.mine.wallet.MyWalletActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.mine.order.MyOrderNumModel;
import com.puyue.www.qiaoge.view.SuperTextView;

import java.util.List;

/**
 * @author cginechen
 * @date 2016-12-29
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private List<MyOrderNumModel.DataBean> list;
    private Context context;
    private onClick onClick;

    public MyRecyclerAdapter(List<MyOrderNumModel.DataBean> list, Context context, onClick onClick) {
        this.list = list;
        this.context = context;
        this.onClick = onClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);


        /*TextView textView = new TextView(parent.getContext());
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                Util.dp2px(parent.getContext(), 50)));
        textView.setBackgroundResource(R.drawable.list_item_bg_with_border_bottom);
        int paddingHor = Util.dp2px(parent.getContext(), 16);
        textView.setPadding(paddingHor, 0, paddingHor, 0);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setTextSize(16);*/
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // holder.setText("item " + position);

        if (list.get(position).getBalance() != null) {
            holder.tv_amount.setText("¥" + list.get(position).getBalance());
        } else {
            holder.tv_amount.setText("¥" + "0.00");
        }
        if (list.get(position).getCommission() != null) {
            holder.tv_commission.setText("¥" + list.get(position).getCommission());
        } else {
            holder.tv_commission.setText("¥" + "0.00");
        }


        if (list.get(position).getInviteAward() != null) {
            holder.tv_inviteAward.setVisibility(View.VISIBLE);
            holder.tv_inviteAward.setText(list.get(position).getInviteAward());
        } else {
            holder.tv_inviteAward.setVisibility(View.GONE);
        }

        holder.tv_point.setText(String.valueOf(list.get(position).getPoint()));
        holder.tv_deductNum.setText(String.valueOf(list.get(position).getDeductNum()));


        if (list.get(position).getExpiredInfo() != null && StringHelper.notEmptyAndNull(list.get(position).getExpiredInfo())) {
            holder.ll_expiredInfo.setVisibility(View.VISIBLE);
            holder.tv_expiredInfo.setText(list.get(position).getExpiredInfo());
        } else {
            holder.ll_expiredInfo.setVisibility(View.GONE);
        }


        holder.tv_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //我的订单


                context.startActivity(MyOrdersActivity.getIntent(context, MyOrdersActivity.class, AppConstant.ALL));
            }
        });

        holder.iv_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //我的订单


                context.startActivity(MyOrdersActivity.getIntent(context, MyOrdersActivity.class, AppConstant.ALL));
            }
        });

        holder.ll_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = "0";
                Intent intent = new Intent(context, MyWalletActivity.class);

                UserInfoHelper.saveUserWalletNum(context, num);
                context.startActivity(intent);
            }
        });
        holder.ll_inviteAward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewWebViewActivity.class);
                intent.putExtra("URL", list.get(position).getCommissionUrl());
                intent.putExtra("TYPE", 1);
                intent.putExtra("name", "");
                context.startActivity(intent);

            }
        });
        holder.ll_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(CommonH5Activity.getIntent(context, MinerIntegralActivity.class));
            }
        });
        holder.ll_deduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(MyCouponsActivity.getIntent(context, MyCouponsActivity.class));
            }
        });
        holder.rl_return_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //退货
                context.startActivity(MyOrdersActivity.getIntent(context, MyOrdersActivity.class, AppConstant.RETURN));
            }
        });
        holder.mRlCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //我的收藏
                context.startActivity(MyCollectionActivity.getIntent(context, MyCollectionActivity.class));
            }
        });
        holder.mRlContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.mRlFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //建议反馈
                context.startActivity(FeedBackActivity.getIntent(context, FeedBackActivity.class));
            }
        });

        holder.mRlMyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //我的订单

                context.startActivity(MyOrdersActivity.getIntent(context, MyOrdersActivity.class, AppConstant.ALL));
            }
        });

        holder.accountAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(AddressListActivity.getIntent(context, AddressListActivity.class));
            }
        });
        holder.accountManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //子账户
                context.startActivity(SubAccountActivity.getIntent(context, SubAccountActivity.class));
            }
        });
        // 会员中心 url
        if (!TextUtils.isEmpty(list.get(position).getVipCenter())) {

            holder.relativeLayoutVip.setEnabled(true);

        } else {
            holder.relativeLayoutVip.setEnabled(false);
        }
        holder.relativeLayoutVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewWebViewActivity.class);
                intent.putExtra("URL", list.get(position).getVipCenter());
                intent.putExtra("TYPE", 1);
                intent.putExtra("name", "");
                context.startActivity(intent);
            }
        });

        //  会员中心角标
        if (!TextUtils.isEmpty((list.get(position).getVipDesc()))) {
            holder.vipDesc.setText(list.get(position).getVipDesc());
            holder.vipDesc.setVisibility(View.VISIBLE);
        } else {
            holder.vipDesc.setVisibility(View.GONE);
        }
//我的收藏


        if (list.get(position).getWaitPayment() > 0) {
            holder.mViewWaitPaymentNum.setVisibility(View.VISIBLE);
            holder.mViewWaitPaymentNum.setText(list.get(position).getWaitPayment() + "");

        } else {
            holder.mViewWaitPaymentNum.setVisibility(View.GONE);
        }
        //待发货
        if (list.get(position).getWaitShipments() > 0) {
            holder.mViewWaitShipmentNum.setVisibility(View.VISIBLE);
            holder.mViewWaitShipmentNum.setText(list.get(position).getWaitShipments() + "");
        } else {
            holder.mViewWaitShipmentNum.setVisibility(View.GONE);
        }
        //待收货
        if (list.get(position).getWaitReceiving() > 0) {
            holder.mViewWaitReceivingNum.setVisibility(View.VISIBLE);
            holder.mViewWaitReceivingNum.setText(list.get(position).getWaitReceiving() + "");
        } else {
            holder.mViewWaitReceivingNum.setVisibility(View.GONE);
        }
        //待评价
        if (list.get(position).getWaitEvaluate() > 0) {
            holder.mViewWaitEvaluateNum.setVisibility(View.VISIBLE);
            holder.mViewWaitEvaluateNum.setText(list.get(position).getWaitEvaluate() + "");
        } else {
            holder.mViewWaitEvaluateNum.setVisibility(View.GONE);
        }
        //退货
        if (list.get(position).getReturnSale() > 0) {
            holder.mViewReturnNum.setVisibility(View.VISIBLE);
            holder.mViewReturnNum.setText(list.get(position).getReturnSale() + "");
        } else {
            holder.mViewReturnNum.setVisibility(View.GONE);
        }


        holder.mLlReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //待收货
                context.startActivity(MyOrdersActivity.getIntent(context, MyOrdersActivity.class, AppConstant.RECEIVED));
            }
        });
        holder.mLlReturnGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //待收货
                context.startActivity(MyOrdersActivity.getIntent(context, MyOrdersActivity.class, AppConstant.RETURN));
            }
        });
        holder.mLlDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //待收货
                context.startActivity(MyOrdersActivity.getIntent(context, MyOrdersActivity.class, AppConstant.DELIVERY));
            }
        });
        holder.mLlEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //待收货
                context.startActivity(MyOrdersActivity.getIntent(context, MyOrdersActivity.class, AppConstant.EVALUATED));
            }
        });
        holder.mLlPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //待收货
                context.startActivity(MyOrdersActivity.getIntent(context, MyOrdersActivity.class, AppConstant.PAYMENT));
            }
        });
        holder.mRlVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.version(holder.getLayoutPosition());
            }
        });

        if (list.get(position).getCollectNum() > 0) {
            holder.mViewCollectionNum.setVisibility(View.VISIBLE);
            Log.i("ccaeq", "onBindViewHolder: "+list.get(position).getCollectNum());
            holder.mViewCollectionNum.setText(String.valueOf(list.get(position).getCollectNum()));
        } else {
            holder.mViewCollectionNum.setVisibility(View.GONE);
        }

        holder.mRlContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.compactMan(holder.getLayoutPosition());
            }
        });

        holder.tv_use_deduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ll_expiredInfo.setVisibility(View.GONE);
                onClick.useExpire(holder.getLayoutPosition());
            }
        });

        holder.iv_use_deduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ll_expiredInfo.setVisibility(View.GONE);
                onClick.useExpireTwo(holder.getLayoutPosition());
            }
        });

    }


    @Override
    public int getItemCount() {
        return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        public TextView tv_vip;
        public TextView tv_amount;
        public TextView tv_commission;//佣金
        public TextView tv_inviteAward;//佣金奖励
        public TextView tv_point;//积分
        public TextView tv_deductNum;//优惠券数量
        public TextView tv_expiredInfo;//优惠券到期通知
        public LinearLayout ll_expiredInfo;
        public TextView tv_order;//查看全部订单
        public ImageView iv_order;
        public ImageView iv_setting;//设置
        public TextView tv_use_deduct;//使用优惠券
        public ImageView iv_use_deduct;//使用优惠券


        private LinearLayout ll_amount;//余额
        private LinearLayout ll_inviteAward;//佣金
        private LinearLayout ll_account;//积分
        private LinearLayout ll_deduct;//优惠券
        private TextView tv_vip_more;//会员更多权益
        private LinearLayout iv_vip_more;//会员更多权益


        private RelativeLayout rl_return_order;
        private RelativeLayout mRlCollection;
        private RelativeLayout mRlContact;
        private RelativeLayout mRlFeedback;
        private RelativeLayout mRlVersion;

        private RelativeLayout mRlMyOrders;

        private RelativeLayout accountAddress;

        private RelativeLayout accountManagement;
        private RelativeLayout relativeLayoutVip; // 会员中心
        private TextView vipDesc; //会员中心角标


        private SuperTextView mViewWaitPaymentNum;
        private SuperTextView mViewWaitShipmentNum;
        private SuperTextView mViewWaitReceivingNum;
        private SuperTextView mViewWaitEvaluateNum;
        private SuperTextView mViewReturnNum;
        private SuperTextView mViewCollectionNum;


        private LinearLayout mLlPayment;
        private LinearLayout mLlEvaluate;
        private LinearLayout mLlDelivery;
        private LinearLayout mLlReturnGoods;
        private LinearLayout mLlReceived;

        public ViewHolder(View view) {
            super(view);
            //  mItemView = (TextView) view.findViewById(R.id.tv);

            tv_amount = (view.findViewById(R.id.tv_amount));
            tv_commission = (view.findViewById(R.id.tv_commission));
            tv_inviteAward = (view.findViewById(R.id.tv_inviteAward));
            tv_point = (view.findViewById(R.id.tv_point));
            tv_deductNum = (view.findViewById(R.id.tv_deductNum));
            tv_expiredInfo = (view.findViewById(R.id.tv_expiredInfo));
            ll_expiredInfo = (view.findViewById(R.id.ll_expiredInfo));
            tv_order = (view.findViewById(R.id.tv_order));
            iv_order = (view.findViewById(R.id.iv_order));
            tv_use_deduct = (view.findViewById(R.id.tv_use_deduct));
            iv_use_deduct = (view.findViewById(R.id.iv_use_deduct));
            ll_amount = (view.findViewById(R.id.ll_amount));
            ll_inviteAward = (view.findViewById(R.id.ll_inviteAward));
            ll_account = (view.findViewById(R.id.ll_account));
            ll_deduct = (view.findViewById(R.id.ll_deduct));
            rl_return_order = (view.findViewById(R.id.rl_return_order));
            mRlCollection = (view.findViewById(R.id.rl_mine_collection));
            mRlContact = (view.findViewById(R.id.rl_mine_contact));//联系客服
            mRlFeedback = (view.findViewById(R.id.rl_mine_feedback));//意见反馈
            mRlVersion = (view.findViewById(R.id.rl_mine_version));//版本信息
            mRlMyOrders = (view.findViewById(R.id.rl_mine_orders));//我的订单
            accountAddress = (view.findViewById(R.id.accountAddress));//我的地址
            accountManagement = (view.findViewById(R.id.accountManagement));//子账号管理
            relativeLayoutVip = (view.findViewById(R.id.relativeLayoutVip)); // 会员中心
            vipDesc = (view.findViewById(R.id.vipDesc));// 会员中心角标.


            mViewCollectionNum = (view.findViewById(R.id.textCollectionMount));//我的收藏数量
            mViewWaitPaymentNum = (view.findViewById(R.id.view_mine_order_wait_pay));//待付款数量
            mViewWaitShipmentNum = (view.findViewById(R.id.view_mine_order_wait_shipments));//待发货数量
            mViewWaitReceivingNum = (view.findViewById(R.id.view_mine_order_wait_receiving));//待收货数量
            mViewWaitEvaluateNum = (view.findViewById(R.id.view_mine_order_wait_evaluate));//待评价数量
            mViewReturnNum = (view.findViewById(R.id.view_mine_order_return_sale));//退货数量


            mLlPayment = (view.findViewById(R.id.ll_mine_tips_payment));//待付款
            mLlEvaluate = (view.findViewById(R.id.ll_mine_tips_evaluate));//待评价
            mLlDelivery = (view.findViewById(R.id.ll_mine_tips_delivery));//待发货
            mLlReturnGoods = (view.findViewById(R.id.ll_mine_tips_return_goods));//退货
            mLlReceived = (view.findViewById(R.id.ll_mine_tips_received));//待收货

        }


    }


    public interface onClick {
        void version(int pos);

        void compactMan(int pos);

        void useExpire(int pos);
        void  useExpireTwo(int pos);
    }

}
