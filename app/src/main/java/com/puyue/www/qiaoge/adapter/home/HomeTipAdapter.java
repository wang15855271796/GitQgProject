package com.puyue.www.qiaoge.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.puyue.www.qiaoge.NewWebViewActivity;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.HomeGoodsListActivity;
import com.puyue.www.qiaoge.activity.home.HomeUseActivity;
import com.puyue.www.qiaoge.activity.home.NewProductActivity;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.activity.mine.wallet.MinerIntegralActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.home.IndexHomeModel;
import com.puyue.www.qiaoge.view.GlideModel;

import java.util.List;

/**
 * Created by Administrator on 2018/3/29.
 */

public class HomeTipAdapter extends RecyclerView.Adapter<HomeTipAdapter.HomeTipViewHolder> {
    private Context context;
    private List<IndexHomeModel.DataBean.IconListBean> mList;

    public HomeTipAdapter(Context context, List<IndexHomeModel.DataBean.IconListBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public HomeTipViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_tip, parent, false);
        return new HomeTipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeTipViewHolder holder, final int position) {
        GlideModel.disPlayError(context,mList.get(position).configCode,holder.mIv);
        String configDesc = mList.get(position).configDesc;
        holder.mTv.setText(configDesc);

        holder.mLlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳新品
                if(AppConstant.NEWTYPE.equals(mList.get(position).remark)) {
                    Intent newIntent = new Intent(context,NewProductActivity.class);
                    context.startActivity(newIntent);

                }else if(AppConstant.HOTTYPE.equals(mList.get(position).remark)) {
                    //热销
                    Intent newIntent = new Intent(context,HotProductActivity.class);
                    context.startActivity(newIntent);

                }else if(AppConstant.COMMONTYPE.equals(mList.get(position).remark)) {
                    //常用清单
                    Intent newIntent = new Intent(context,CommonProductActivity.class);
                    context.startActivity(newIntent);
                }else if(AppConstant.REDUCTIONTYPE.equals(mList.get(position).remark)) {
                    //降价
                    Intent newIntent = new Intent(context, ReductionProductActivity.class);
                    context.startActivity(newIntent);

                }else if(AppConstant.SECONDTYPE.equals(mList.get(position).remark)) {
                    //秒杀活动
                    Intent intent = new Intent(context, HomeGoodsListActivity.class);
                    context.startActivity(intent);
                }else if (AppConstant.SHARETYPE.equals(mList.get(position).remark)) {
                    //分享有礼
                    setIntent(mList.get(position).url);

                }else if(AppConstant.VIPTYPE.equals(mList.get(position).remark)) {
                    //VIP会员
                    setIntent(mList.get(position).url);
                }else if(AppConstant.CONSULT.equals(mList.get(position).remark)) {
                    //行业资讯
                    setIntentConsult(mList.get(position).url);
                }
            }
        });
    }

    private void setIntent(String URL) {
        Intent intent = new Intent(context, NewWebViewActivity.class);
        intent.putExtra("URL", URL);
        intent.putExtra("TYPE", 2);
        intent.putExtra("name","");
        context.startActivity(intent);
    }

    private void setIntentConsult(String URL) {
        Intent intent = new Intent(context, NewWebViewActivity.class);
        intent.putExtra("URL", URL);
        intent.putExtra("TYPE", 2);
        intent.putExtra("name","consult");
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HomeTipViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLlItem;
        private ImageView mIv;
        private TextView mTv;

        public HomeTipViewHolder(View itemView) {
            super(itemView);
            mLlItem = FVHelper.fv(itemView, R.id.ll_item_home_tip);
            mIv = ((ImageView) itemView.findViewById(R.id.iv_home_tip_item));
            mTv = ((TextView) itemView.findViewById(R.id.tv_home_tip_item));
        }
    }
}
