package com.puyue.www.qiaoge.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.SpikeGoodsDetailsActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.helper.GlideRoundTransform;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.model.home.HomeRecommendModel;
import com.puyue.www.qiaoge.model.home.IndexHomeModel;
import com.puyue.www.qiaoge.view.SnapUpCountDownTimerView;

import java.util.List;

/**
 * Created by Administrator on 2018/4/9.
 */

public class HomeSecKillAdapter extends RecyclerView.Adapter<HomeSecKillAdapter.HomeSecKillViewHolder> {
    private Context context;
    private List<IndexHomeModel.DataBean.SpikeListBean> mList;
    private OnClick onClick;

    public HomeSecKillAdapter(Context context, List<IndexHomeModel.DataBean.SpikeListBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    @Override
    public HomeSecKillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_sec_kill, parent, false);
        return new HomeSecKillViewHolder(view, onClick);
    }

    @Override
    public void onBindViewHolder(HomeSecKillViewHolder holder, final int position) {
        if (StringHelper.notEmptyAndNull(mList.get(position).defaultPic)) {
            Glide.with(context).load(mList.get(position).defaultPic).into(holder.mIv);
        }
        holder.tvTitle.setText(mList.get(position).activeTitle);
        holder.tvMonthlySale.setText(mList.get(position).monthSalesVolume);
        holder.tvHomePrice.setText(mList.get(position).price);
        if (!TextUtils.isEmpty(mList.get(position).oldPrice)) {
            holder.tvOriginalPrice.setText(mList.get(position).oldPrice);
        } else {
            holder.tvOriginalPrice.setVisibility(View.GONE);

        }
        holder.mLlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SpikeGoodsDetailsActivity.class);
                intent.putExtra(AppConstant.ACTIVEID, mList.get(position).activeId);
                context.startActivity(intent);
            }
        });

        holder.tvOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HomeSecKillViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout mLlItem;
        private ImageView mIv;
        private TextView tvMonthlySale;

        private TextView tvTitle;
        private TextView tvHomePrice;
        private ImageView shoppingCart;
        private RelativeLayout relativeLayoutShoppingCart;
        private TextView tvOriginalPrice;


        public HomeSecKillViewHolder(View itemView, final OnClick onClick) {
            super(itemView);
            mLlItem = FVHelper.fv(itemView, R.id.ll_item_group);
            mIv = (itemView.findViewById(R.id.iv_home_seckill_img));
            tvMonthlySale = (itemView.findViewById(R.id.tvMonthlySale));
            tvOriginalPrice = (itemView.findViewById(R.id.tvOriginalPrice));
            tvTitle = (itemView.findViewById(R.id.tvTitle));
            shoppingCart = (itemView.findViewById(R.id.shoppingCart));
            tvHomePrice = (itemView.findViewById(R.id.tvHomePrice));
            relativeLayoutShoppingCart = (itemView.findViewById(R.id.relativeLayoutShoppingCart));
            if (onClick != null) {
                relativeLayoutShoppingCart.setOnClickListener(new View.OnClickListener() {
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
