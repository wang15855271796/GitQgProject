package com.puyue.www.qiaoge.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.activity.home.SpecialGoodDetailActivity;
import com.puyue.www.qiaoge.activity.home.SpikeGoodsDetailsActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.model.home.IndexHomeModel;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by ${王文博} on 2019/4/10
 */
public class HomeSpecialOfferAdapter extends RecyclerView.Adapter<HomeSpecialOfferAdapter.HomeSpecialViewHolder> {

    private List<IndexHomeModel.DataBean.OfferListBean> offerListBeanList;
    private Context context;
    private OnClick onClick;

    public HomeSpecialOfferAdapter(Context context, List<IndexHomeModel.DataBean.OfferListBean> offerListBeans) {
        this.context = context;
        this.offerListBeanList = offerListBeans;
    }

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public HomeSpecialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_home_special, parent, false);

        return new HomeSpecialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeSpecialViewHolder holder, int position) {
        if (StringHelper.notEmptyAndNull(offerListBeanList.get(position).pic)) {
            Glide.with(context).load(offerListBeanList.get(position).pic).into(holder.imageView);
        }
        if (offerListBeanList.get(position).soldOut == 1) {
            holder.ivSoldOut.setVisibility(View.VISIBLE);


            Glide.with(context).load(offerListBeanList.get(position).flagUrl).into(holder.ivSoldOut);
            holder.tvBg.setVisibility(View.VISIBLE);
            holder.tvBg.getBackground().setAlpha(75);
        } else {
            holder.ivSoldOut.setVisibility(View.GONE);
            holder.tvBg.setVisibility(View.GONE);
        }

        holder.mSpecialTitle.setText(offerListBeanList.get(position).title);
        holder.mTvSale.setText(offerListBeanList.get(position).salesVolume);
        holder.tvSpecialPrice.setText(offerListBeanList.get(position).price);
        if (!TextUtils.isEmpty(offerListBeanList.get(position).oldPrice)) {
            holder.mOldPrice.setText("原价:" + offerListBeanList.get(position).oldPrice);
        } else {

            holder.mOldPrice.setText("");
        }

        holder.relativeLayoutSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SpecialGoodDetailActivity.class);
                intent.putExtra(AppConstant.ACTIVEID, offerListBeanList.get(position).activeId);
                context.startActivity(intent);
            }
        });
        holder.mOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public int getItemCount() {
        return offerListBeanList.size();
    }

    public class HomeSpecialViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout relativeLayoutSpecial;
        private ImageView imageView;
        private TextView mSpecialTitle;
        private TextView mTvSale;
        private RelativeLayout relativeLayoutShoppingCart;
        private TextView mSpecialPrice;
        private TextView mTextSpecial;
        private TextView tvSpecialPrice;
        private TextView mOldPrice;
        private ImageView mShopingCart;
        private ImageView ivSoldOut;
        private FrameLayout ll_special;

        private TextView tvBg;

        public HomeSpecialViewHolder(View itemView) {
            super(itemView);
            relativeLayoutSpecial = FVHelper.fv(itemView, R.id.relativeLayout_special);
            imageView = itemView.findViewById(R.id.iv_special);
            mSpecialTitle = itemView.findViewById(R.id.tv_special_title);
            mTvSale = itemView.findViewById(R.id.tv_sale);
            relativeLayoutShoppingCart = itemView.findViewById(R.id.relativeLayoutShoppingCart);
            tvSpecialPrice = itemView.findViewById(R.id.tvSpecialPrice);
            mOldPrice = itemView.findViewById(R.id.tvOldPrice);
            mShopingCart = itemView.findViewById(R.id.shoppingCart);
            ivSoldOut = itemView.findViewById(R.id.sold_out);
            ll_special = itemView.findViewById(R.id.ll_special);
            tvBg = itemView.findViewById(R.id.tv_bg);
            if (onClick != null) {
                mShopingCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClick.shoppingCartOnClick(getLayoutPosition());
                    }
                });
            }

        }
    }

    public interface OnClick {
        void shoppingCartOnClick(int position);
    }
}
