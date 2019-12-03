package com.puyue.www.qiaoge.model.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.SpikeGoodsDetailsActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;

import java.util.List;

/**
 * Created by ${王文博} on 2019/4/11
 */
public class HomeSecKillPreAdapter extends RecyclerView.Adapter<HomeSecKillPreAdapter.HomeSecKillPreViewHolder> {

    private Context context;
    private List<IndexHomeModel.DataBean.NewSecKillBean.SecKill> secKillList;

    private OnClick onClick;

    public HomeSecKillPreAdapter(Context context, List<IndexHomeModel.DataBean.NewSecKillBean.SecKill> list) {
        this.context = context;
        this.secKillList = list;
    }

    public void setOnclick(OnClick onClick) {
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public HomeSecKillPreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_sec_kill, parent, false);
        return new HomeSecKillPreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeSecKillPreViewHolder holder, int position) {
        if (StringHelper.notEmptyAndNull(secKillList.get(position).pic)) {
            Glide.with(context).load(secKillList.get(position).pic).into(holder.mIv);
        }



        holder.tvTitle.setText(secKillList.get(position).title);
        holder.tvHomePrice.setText(secKillList.get(position).price);
        if (!TextUtils.isEmpty(secKillList.get(position).oldPrice)) {
            holder.tvOriginalPrice.setText("原价:"+secKillList.get(position).oldPrice);
        } else {
            holder.tvOriginalPrice.setText("");

        }

        holder.tvMonthlySale.setText(secKillList.get(position).sales);
        holder.mLlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SpikeGoodsDetailsActivity.class);
                intent.putExtra(AppConstant.ACTIVEID, secKillList.get(position).activeId);
                context.startActivity(intent);
            }
        });
        holder.tvOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
    }

    @Override
    public int getItemCount() {
        return secKillList.size();
    }

    class HomeSecKillPreViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout mLlItem;
        private ImageView mIv;
        private TextView tvMonthlySale;

        private TextView tvTitle;
        private TextView tvHomePrice;
        private ImageView shoppingCart;
        private RelativeLayout relativeLayoutShoppingCart;
        private TextView tvOriginalPrice;
        private ImageView ivSoldOut;
        public HomeSecKillPreViewHolder(View itemView) {
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
                shoppingCart.setOnClickListener(new View.OnClickListener() {
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
