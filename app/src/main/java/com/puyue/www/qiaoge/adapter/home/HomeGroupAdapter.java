package com.puyue.www.qiaoge.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.TeamGoodsDetailActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.helper.GlideRoundTransform;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.model.home.HomeRecommendModel;
import com.puyue.www.qiaoge.model.home.IndexHomeModel;

import java.util.List;

/**
 * Created by Administrator on 2018/4/9.
 */

public class HomeGroupAdapter extends RecyclerView.Adapter<HomeGroupAdapter.HomeGroupViewHolder> {
    private Context context;
    private List<IndexHomeModel.DataBean.TeamListBean> mList;
    private OnClick onClick;

    public HomeGroupAdapter(Context context, List<IndexHomeModel.DataBean.TeamListBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    @Override
    public HomeGroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_home_sec_kill_new, parent, false);
        return new HomeGroupViewHolder(view, onClick);
    }

    @Override
    public void onBindViewHolder(HomeGroupViewHolder holder, final int position) {
        if (StringHelper.notEmptyAndNull(mList.get(position).defaultPic)) {
            Glide.with(context).load(mList.get(position).defaultPic).into(holder.mIv);
        }
        holder.shoppingCart.setImageResource(R.mipmap.ic_bay_car_manager);
//        holder.tvOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
//        holder.tvOriginalPrice.setVisibility(View.GONE);
        holder.mTvContent.setText(mList.get(position).activeTitle);
        holder.mTvVolume.setText(mList.get(position).monthSalesVolume);
        holder.mTvPrice.setText("￥" + mList.get(position).combinationPrice);
        holder.textSpec.setVisibility(View.VISIBLE);
        holder.textSpec.setText("/" + mList.get(position).unitName);
        holder.mLlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TeamGoodsDetailActivity.class);
                intent.putExtra(AppConstant.ACTIVEID, mList.get(position).activeId);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HomeGroupViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIv;
        private TextView mTvVolume;
        private TextView mTvContent;
        private TextView mTvPrice;
        private RelativeLayout mLlItem;
        private TextView tvOriginalPrice;
        private ImageView shoppingCart;
        //        private RelativeLayout relativeLayoutShoppingCart;
        private TextView textSpec;


        public HomeGroupViewHolder(View itemView, final OnClick onClick) {
            super(itemView);
            mIv = (itemView.findViewById(R.id.iv_home_seckill_img));
            mTvVolume = (itemView.findViewById(R.id.tvMonthlySale));
            mTvContent = (itemView.findViewById(R.id.tvTitle));
            mTvPrice = (itemView.findViewById(R.id.tvHomePrice));
            mLlItem = FVHelper.fv(itemView, R.id.ll_item_group);
//            tvOriginalPrice = itemView.findViewById(R.id.tvOriginalPrice);
            shoppingCart = itemView.findViewById(R.id.shoppingCart);
            textSpec = itemView.findViewById(R.id.textSpec);
//            relativeLayoutShoppingCart=itemView.findViewById(R.id.relativeLayoutShoppingCart);
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
