package com.puyue.www.qiaoge.fragment.home;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.NewWebViewActivity;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.HomeGoodsListActivity;
import com.puyue.www.qiaoge.activity.home.NewProductActivity;
import com.puyue.www.qiaoge.activity.mine.MessageCenterActivity;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.adapter.home.CommonProductActivity;
import com.puyue.www.qiaoge.adapter.home.HotProductActivity;
import com.puyue.www.qiaoge.adapter.home.ReductionProductActivity;
import com.puyue.www.qiaoge.api.home.IndexInfoModel;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.mine.order.HomeBaseModel;

import java.util.List;

public class RvIconAdapter extends BaseQuickAdapter<IndexInfoModel.DataBean.IconsBean,BaseViewHolder> {

    public RvIconAdapter(int item_home_icon, List<IndexInfoModel.DataBean.IconsBean> iconList) {
        super(item_home_icon, iconList);
    }

    @Override
    protected void convert(BaseViewHolder helper, IndexInfoModel.DataBean.IconsBean item) {
        helper.setText(R.id.tv_desc,item.getConfigDesc());
        ImageView iv_icon = helper.getView(R.id.iv_icon);
        Log.d("ggffasfsfeerre.....",item.getConfigCode());
        Glide.with(mContext)
                .load(item.getUrl())
                .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                .apply(new RequestOptions().placeholder(iv_icon.getDrawable()).skipMemoryCache(false).dontAnimate())
                .into(iv_icon);

        helper.getView(R.id.ll_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳新品
                if(AppConstant.NEWTYPE.equals(item.getRemark())) {
                    Intent newIntent = new Intent(mContext,NewProductActivity.class);
                    mContext.startActivity(newIntent);

                }else if(AppConstant.HOTTYPE.equals(item.getConfigCode())) {
                    //热销
                    Intent newIntent = new Intent(mContext,HotProductActivity.class);
                    mContext.startActivity(newIntent);

                }else if(AppConstant.COMMONTYPE.equals(item.getConfigCode())) {
                    //常用清单
                    if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                        Intent newIntent = new Intent(mContext, CommonProductActivity.class);
                        mContext.startActivity(newIntent);

                    } else {
                        AppHelper.showMsg(mContext, "请先登录");
                        mContext.startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                    }

                }else if(AppConstant.REDUCTIONTYPE.equals(item.getConfigCode())) {
                    //降价
                    Intent newIntent = new Intent(mContext, ReductionProductActivity.class);
                    mContext.startActivity(newIntent);

                }else if(AppConstant.SECONDTYPE.equals(item.getRemark())) {
                    //秒杀活动
                    if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                        Intent intent = new Intent(mContext, HomeGoodsListActivity.class);
                        intent.putExtra(AppConstant.PAGETYPE, AppConstant.SECONDTYPE);
                        mContext.startActivity(intent);

                    } else {
                        AppHelper.showMsg(mContext, "请先登录");
                        mContext.startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                    }


                }else if (AppConstant.SHARETYPE.equals(item.getRemark())) {
                    //分享有礼
                    setIntent(item.getUrl());

                }else if(AppConstant.VIPTYPE.equals(item.getConfigCode())) {
                    //VIP会员
                    setIntent(item.getUrl());
                }else if(AppConstant.CONSULT.equals(item.getConfigCode())) {
                    //行业资讯
                    setIntentConsult(item.getUrl());
                }
            }
        });
    }
    private void setIntent(String URL) {
        Intent intent = new Intent(mContext, NewWebViewActivity.class);
        intent.putExtra("URL", URL);
        intent.putExtra("TYPE", 2);
        intent.putExtra("name","");
        mContext.startActivity(intent);
    }

    private void setIntentConsult(String URL) {
        Intent intent = new Intent(mContext, NewWebViewActivity.class);
        intent.putExtra("URL", URL);
        intent.putExtra("TYPE", 2);
        intent.putExtra("name","consult");
        mContext.startActivity(intent);
    }

}
