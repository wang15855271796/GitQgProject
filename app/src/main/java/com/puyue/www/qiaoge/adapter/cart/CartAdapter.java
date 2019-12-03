package com.puyue.www.qiaoge.adapter.cart;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.cart.CartListModel;
import com.puyue.www.qiaoge.view.GlideModel;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/17.
 */

public class CartAdapter extends BaseQuickAdapter<CartListModel.DataBean.ListBean.ValidListBean, BaseViewHolder> {

    public Map<Integer, Boolean> isCheck;
    private OnEventClickListener mOnEventClickListener;
    private LinearLayout linearLayoutButton;
    private CheckBox checkBox;
    private List<CartListModel.DataBean.ListBean.ValidListBean> data;
    public int code;//加减
    private TextView tvStock;
    private Onclick onclick;


    ImageView iv_add;
    ImageView iv_flag_add;
    TextView tv_iv_bg;
    ImageView iv_finish;
    public CartAdapter(int layoutResId, @Nullable List<CartListModel.DataBean.ListBean.ValidListBean> data, Map<Integer, Boolean> isCheck
            , LinearLayout linearLayoutButton, int code, Onclick onclick) {
        super(layoutResId, data);
        this.isCheck = isCheck;
        this.linearLayoutButton = linearLayoutButton;
        this.data = data;
        this.code = code;
        this.onclick = onclick;
    }

    public interface OnEventClickListener {
        void onEventClick(View view, String direction, int typeId, int amount, int position, String type);

        void onEventLongClick(View view, int typeId, int amount, int position);

        void onEvenChangeNumClick(View view, int typeId, int amount, int position);
    }

    public void setOnItemClickListener(OnEventClickListener onEventClickListener) {
        mOnEventClickListener = onEventClickListener;
    }

    @Override
    protected void convert(final BaseViewHolder helper, CartListModel.DataBean.ListBean.ValidListBean item) {
//        CartFragment.CartStandardAdapter mAdapterCartStandard;
        iv_add = helper.getView(R.id.iv_item_cart_img_add);

        iv_flag_add = helper.getView(R.id.iv_flag_add);
        tv_iv_bg = helper.getView(R.id.tv_iv_bg);
        iv_finish = helper.getView(R.id.iv_finish);

        if (item.flagUrl != null) {
            helper.getView(R.id.iv_flag).setVisibility(View.VISIBLE);
            Glide.with(mContext).load(item.flagUrl).into((ImageView) helper.getView(R.id.iv_flag));
        } else {
            helper.getView(R.id.iv_flag).setVisibility(View.GONE);
        }

        checkBox = helper.getView(R.id.cb_item_cart);
        tvStock = helper.getView(R.id.tv_cart_stock);
        tvStock.setText(item.inventory);
        //根据这个购物车item是否已经失效来判断是否显示失效布局
        if (!item.valid) {
            //失效
            ((LinearLayout) helper.getView(R.id.ll_item_cart_invalid)).setVisibility(View.VISIBLE);
            checkBox.setVisibility(View.GONE);
            ((TextView) helper.getView(R.id.tv_item_cart_invalid)).setVisibility(View.GONE);
            tvStock.setVisibility(View.GONE);
        } else {
            //有效
            ((LinearLayout) helper.getView(R.id.ll_item_cart_invalid)).setVisibility(View.GONE);
            checkBox.setVisibility(View.VISIBLE);
            ((TextView) helper.getView(R.id.tv_item_cart_invalid)).setVisibility(View.GONE);
            tvStock.setVisibility(View.VISIBLE);
            //有效的商品才能跳转详情页
            RelativeLayout mRlJump = (RelativeLayout) helper.getView(R.id.rl_cart_item_jump);
            mRlJump.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    //跳转
                    mOnEventClickListener.onEventClick(view, "", -1, -1, helper.getAdapterPosition(), "jump");
                }
            });
            FrameLayout mFlCheck = (FrameLayout) helper.getView(R.id.fl_cart_item_check);
            mFlCheck.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    //选中
                    mOnEventClickListener.onEventClick(view, "", -1, -1, helper.getAdapterPosition(), "check");
                }
            });
        }
        if (StringHelper.notEmptyAndNull(item.picUrl)) {
            GlideModel.disPlayError(mContext, item.picUrl, helper.getView(R.id.iv_item_cart_img));
            // Glide.with(mContext).load(item.picUrl).transform(new GlideRoundTransform(mContext, 3)).into((ImageView) helper.getView(R.id.iv_item_cart_img));
        }
        helper.setText(R.id.tv_item_cart_title, item.name);
        checkBox.setChecked(isCheck.get(helper.getAdapterPosition()));
        helper.setText(R.id.tv_item_cart_standard, "规格：" + item.spec);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (linearLayoutButton != null) {
                        linearLayoutButton.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (data.size() > 0) {
                        linearLayoutButton.setVisibility(View.VISIBLE);
                    } else {
                        linearLayoutButton.setVisibility(View.GONE);
                    }

                }

            }
        });
        View view = helper.getView(R.id.tv_delete);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.deleletItem(helper.getLayoutPosition());

            }
        });

        //这个规格,要根据不同的产品类型来判断是否要显示
        ((RecyclerView) helper.getView(R.id.rv_item_cart)).setLayoutManager(new LinearLayoutManager(mContext));
//        mAdapterCartStandard = new CartFragment.CartStandardAdapter(R.layout.item_cart_standard, item.productDescVOList, item.businessType, item.businessId);
//        mAdapterCartStandard.setOnItemClickListener(new CartFragment.CartStandardAdapter.OnEventClickListener() {
//            @Override
//            public void onEventClick(View view, String direction, int typeId, int amount) {
//                mOnEventClickListener.onEventClick(view, direction, typeId, amount, helper.getAdapterPosition(), "change");
//            }
//
//
//            @Override
//            public void onEventLongClick(View view, int typeId, int amount) {
//
//            }
//
//            @Override
//            public void refreshMarket() {
//                onclick.refreshCart();
//            }
//
//            @Override
//            public void changeAmount(View view, int typeId, int amount) {
//                mOnEventClickListener.onEvenChangeNumClick(view, typeId, amount, helper.getLayoutPosition());
//
//            }
//        });
//        ((RecyclerView) helper.getView(R.id.rv_item_cart)).setAdapter(mAdapterCartStandard);


        if (item.additionProductVOList != null) {


            helper.getView(R.id.rl_cart_item_add).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_item_cart_standard_add, "规格：" + item.additionProductVOList.get(0).spec);
            helper.setText(R.id.tv_cart_stock_add,  item.additionProductVOList.get(0).inventory);
            helper.setText(R.id.tv_item_cart_title_add, item.additionProductVOList.get(0).name);
            Glide.with(mContext).load(item.additionProductVOList.get(0).picUrl).into(iv_add);
            Glide.with(mContext).load(item.additionProductVOList.get(0).flagUrl).into(iv_flag_add);
            if (item.additionProductVOList.get(0).additionFlag == 1) {
              tv_iv_bg.setVisibility(View.GONE);
               iv_finish.setVisibility(View.GONE);
                helper.getView(R.id.tv_add).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_add_two).setVisibility(View.GONE);
                helper.setText(R.id.tv_add, item.additionProductVOList.get(0).additionNum);
            } else {
              tv_iv_bg.setVisibility(View.VISIBLE);
              iv_finish.setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_add_two).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_add).setVisibility(View.GONE);
                helper.setText(R.id.tv_add_two, item.additionProductVOList.get(0).additionNum);

             tv_iv_bg.getBackground().setAlpha(70);
          Glide.with(mContext).load(item.additionProductVOList.get(0).finishUrl).into(iv_finish);
            }

        } else {
            helper.getView(R.id.rl_cart_item_add).setVisibility(View.GONE);
        }
        helper.getView(R.id.rl_cart_item_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(mContext,CommonGoodsDetailActivity.class);
                intent.putExtra(AppConstant.ACTIVEID, item.additionProductVOList.get(0).productId);
                intent.putExtra("type", 1);
              mContext.startActivity(intent);


            }
        });

    }

    public interface Onclick {
        void deleletItem(int pos);

        void refreshCart();

    }

}
