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
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.cart.GetOrderDetailModel;
import com.puyue.www.qiaoge.view.GlideModel;

import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */

public class ReturnGoodsAgainListAdapter extends BaseQuickAdapter<GetOrderDetailModel.DataBean.ProductVOListBean, BaseViewHolder> {
    private String mUserType;

    public ReturnGoodsAgainListAdapter(int layoutResId, @Nullable List<GetOrderDetailModel.DataBean.ProductVOListBean> data, String userType) {
        super(layoutResId, data);
        this.mUserType = userType;
    }

    @Override
    protected void convert(BaseViewHolder helper, GetOrderDetailModel.DataBean.ProductVOListBean item) {
        if (StringHelper.notEmptyAndNull(item.picUrl)) {
            GlideModel.displayTransForms(mContext,item.picUrl,helper.getView(R.id.iv_item_return_goods_img));
           // Glide.with(mContext).load(item.picUrl).transform(new GlideRoundTransform(mContext, 3)).into((ImageView) helper.getView(R.id.iv_item_return_goods_img));
        }
        helper.setText(R.id.tv_item_return_goods_title, item.name);
        helper.setText(R.id.tv_item_return_goods_standard, item.spec);
        helper.setChecked(R.id.cb_item_return_goods, true);
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
                    final View view = inflater.inflate(R.layout.item_return_goods_standard, null);
                    view.setLayoutParams(lp);
                    ((LinearLayout) helper.getView(R.id.ll_item_return_goods_standard_container)).addView(view);
                    ((LinearLayout) helper.getView(R.id.ll_item_return_goods_standard_container)).setOnClickListener(new NoDoubleClickListener() {
                        @Override
                        public void onNoDoubleClick(View view) {
                            AppHelper.showMsg(mContext, "再次申请退货时,不可调整数量");
                        }
                    });
                    if (i == 0) {
                        view.findViewById(R.id.view_item_return_goods_standard).setVisibility(View.GONE);
                    } else {
                        view.findViewById(R.id.view_item_return_goods_standard).setVisibility(View.VISIBLE);
                    }
                    TextView mTvAmount = (TextView) view.findViewById(R.id.tv_return_goods_standard_item_amount);
                    mTvAmount.setText(item.productDescVOList.get(i).totalNum);
                    TextView mTvAmountNum = (TextView) view.findViewById(R.id.tv_return_goods_standard_item_amount_num);
                    mTvAmountNum.setText(item.productDescVOList.get(i).productNum + "");
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
