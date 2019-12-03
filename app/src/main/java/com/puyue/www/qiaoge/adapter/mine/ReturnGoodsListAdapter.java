package com.puyue.www.qiaoge.adapter.mine;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.GlideRoundTransform;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.StringSpecialHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.cart.GetOrderDetailModel;
import com.puyue.www.qiaoge.view.GlideModel;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/16.
 */

public class ReturnGoodsListAdapter extends BaseQuickAdapter<GetOrderDetailModel.DataBean.ProductVOListBean, BaseViewHolder> {
    private OnEventClickListener mOnEventClickListener;
    private Map<Integer, Boolean> isCheck;
    private String mUserType;

    public ReturnGoodsListAdapter(int layoutResId, @Nullable List<GetOrderDetailModel.DataBean.ProductVOListBean> data, Map<Integer, Boolean> isCheck, String userType) {
        super(layoutResId, data);
        this.isCheck = isCheck;
        this.mUserType = userType;
    }

    public interface OnEventClickListener {
        void onEventClick(View view, int position, String flag, int standardPosition, int amount);

        void onEventLongClick(View view, int position, String flag);
    }


    public void setOnItemClickListener(OnEventClickListener onEventClickListener) {
        mOnEventClickListener = onEventClickListener;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final GetOrderDetailModel.DataBean.ProductVOListBean item) {
        if (StringHelper.notEmptyAndNull(item.picUrl)) {
            GlideModel.displayTransForms(mContext,item.picUrl,helper.getView(R.id.iv_item_return_goods_img));
           // Glide.with(mContext).load(item.picUrl).transform(new GlideRoundTransform(mContext, 3)).into((ImageView) helper.getView(R.id.iv_item_return_goods_img));
        }
        helper.setText(R.id.tv_item_return_goods_title, item.name);
        helper.setText(R.id.tv_item_return_goods_standard, item.spec);
//        helper.setText(R.id.tv_item_return_goods_price, item.price);
//        helper.setText(R.id.tv_item_return_goods_volume, item.volume);
//        helper.setText(R.id.tv_item_return_goods_stock, item.stock);
        helper.setChecked(R.id.cb_item_return_goods, isCheck.get(helper.getAdapterPosition()));
        ((LinearLayout) helper.getView(R.id.ll_item_return_goods_msg)).setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                mOnEventClickListener.onEventClick(view, helper.getAdapterPosition(), "check", -1, -1);
            }
        });
        //根据用户是批发还是零售来判断这个调整的布局是否要显示
        if (item.businessType == 1) {
            //普通商品才判断这个用户是零售用户还是批发用户
            if (mUserType.equals("1")) {
                //零售用户
                ((LinearLayout) helper.getView(R.id.ll_item_return_goods_standard_container)).setVisibility(View.VISIBLE);
                ((LinearLayout) helper.getView(R.id.ll_item_return_goods_standard_container)).removeAllViews();
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);       //LayoutInflater inflater1=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LayoutInflater inflater = LayoutInflater.from(mContext);
                for (int i = 0; i < item.productDescVOList.size(); i++) {
                    View view = inflater.inflate(R.layout.item_return_goods_standard, null);
                    view.setLayoutParams(lp);
                    ((LinearLayout) helper.getView(R.id.ll_item_return_goods_standard_container)).addView(view);
                    if (i == 0) {
                        view.findViewById(R.id.view_item_return_goods_standard).setVisibility(View.GONE);
                    } else {
                        view.findViewById(R.id.view_item_return_goods_standard).setVisibility(View.VISIBLE);
                    }
                    TextView mTvAmount = (TextView) view.findViewById(R.id.tv_return_goods_standard_item_amount);
                    final int maxNum = Integer.parseInt(StringSpecialHelper.getNumberFromString(item.productDescVOList.get(i).totalNum));
                    mTvAmount.setText(item.productDescVOList.get(i).totalNum);
                    final TextView mTvAmountNum = (TextView) view.findViewById(R.id.tv_return_goods_standard_item_amount_num);
                    mTvAmountNum.setText(item.productDescVOList.get(i).productNum + "");
                    final int finalI = i;
                    ((TextView) view.findViewById(R.id.tv_return_goods_standard_item_amount_reduce)).setOnClickListener(new NoDoubleClickListener() {
                        @Override
                        public void onNoDoubleClick(View view) {
                            //减少当前这个商品的当前规格数量,不能少于1
                            if (mTvAmountNum.getText().toString().equals("1")) {
                                //现在这个数目选择的是1,如果这个时候还点击了减少数目,弹出提示,也不修改list里面的数据
                                AppHelper.showMsg(mContext, "退货数量需大于0");
                            } else {
                                //现在的数量不是1,就修改当前的数量
                                mTvAmountNum.setText((Integer.parseInt(mTvAmountNum.getText().toString()) - 1) + "");
                                mOnEventClickListener.onEventClick(view, helper.getAdapterPosition(), "reduce", finalI, Integer.parseInt(mTvAmountNum.getText().toString()));
                            }
                        }
                    });
                    ((TextView) view.findViewById(R.id.tv_return_goods_standard_item_amount_add)).setOnClickListener(new NoDoubleClickListener() {
                        @Override
                        public void onNoDoubleClick(View view) {
                            //增加当前这个商品的当前规格的退货数量,不能多于它原本的下单数量
                            if (Integer.parseInt(mTvAmountNum.getText().toString()) == maxNum) {
                                //当前的数量就是这个商品生成订单的数量,无法再增加
                                AppHelper.showMsg(mContext, "退货数量不能超过最大数量");
                            } else {
                                //当前不是最大数量,可以增加
                                mTvAmountNum.setText((Integer.parseInt(mTvAmountNum.getText().toString()) + 1) + "");
                                mOnEventClickListener.onEventClick(view, helper.getAdapterPosition(), "add", finalI, Integer.parseInt(mTvAmountNum.getText().toString()));
                            }
                        }
                    });
                }
            } else if (mUserType.equals("2")) {
                //批发用户,不显示下方布局
                ((LinearLayout) helper.getView(R.id.ll_item_return_goods_standard_container)).setVisibility(View.GONE);
            }
        } else {
            //其他商品统一不显示下方布局
            ((LinearLayout) helper.getView(R.id.ll_item_return_goods_standard_container)).setVisibility(View.GONE);
        }
    }
}
