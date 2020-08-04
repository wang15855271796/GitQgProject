package com.puyue.www.qiaoge.fragment.home;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.NewWebViewActivity;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.Test1Activity;
import com.puyue.www.qiaoge.activity.TestActivity;
import com.puyue.www.qiaoge.activity.home.HomeGoodsListActivity;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.adapter.home.CommonProductActivity;
import com.puyue.www.qiaoge.adapter.home.HotProductActivity;
import com.puyue.www.qiaoge.adapter.home.ReductionProductActivity;
import com.puyue.www.qiaoge.api.home.IndexInfoModel;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.dialog.CouponDialog;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.utils.LoginUtil;

import java.util.List;

import static cn.com.chinatelecom.account.api.CtAuth.mContext;

public class RvIconAdapter extends BaseQuickAdapter<IndexInfoModel.DataBean.IconsBean,BaseViewHolder> {

    String deductstr;
    public RvIconAdapter(int item_home_icon, List<IndexInfoModel.DataBean.IconsBean> iconList,String deductstr) {
        super(item_home_icon, iconList);
        this.deductstr = deductstr;
    }

    @Override
    protected void convert(BaseViewHolder helper, IndexInfoModel.DataBean.IconsBean item) {
        helper.setText(R.id.tv_desc,item.getConfigDesc());
        ImageView iv_tip = helper.getView(R.id.iv_tip);
        ImageView iv_icon = helper.getView(R.id.iv_icon);
        TextView tv_tip = helper.getView(R.id.tv_tip);

        if(deductstr.isEmpty()) {
            iv_tip.setVisibility(View.GONE);
            tv_tip.setVisibility(View.GONE);
        }else {
            tv_tip.setText(deductstr);

            if(AppConstant.REDUCTIONTYPE.equals(item.getConfigCode())) {
                iv_tip.setVisibility(View.VISIBLE);
                tv_tip.setVisibility(View.VISIBLE);
            }else {
                iv_tip.setVisibility(View.GONE);
                tv_tip.setVisibility(View.GONE);
            }

        }


        Glide.with(mContext)
                .load(item.getUrl())
                .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                .apply(new RequestOptions().placeholder(iv_icon.getDrawable()).skipMemoryCache(false).dontAnimate())
                .into(iv_icon);

        helper.getView(R.id.ll_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppConstant.HOTTYPE.equals(item.getConfigCode())) {
                    //热销
                    if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                        Intent newIntent = new Intent(mContext,HotProductActivity.class);
                        mContext.startActivity(newIntent);
                    }else {
                        initDialog();
                    }


                }else if(AppConstant.COMMONTYPE.equals(item.getConfigCode())) {
                    //常用清单
                    if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                        Intent newIntent = new Intent(mContext, CommonProductActivity.class);
                        mContext.startActivity(newIntent);

                    } else {
                        initDialog();
                    }

                }else if(AppConstant.REDUCTIONTYPE.equals(item.getConfigCode())) {
                    //降价
                    if(StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                        Intent newIntent = new Intent(mContext, ReductionProductActivity.class);
                        mContext.startActivity(newIntent);
                    }else {
                        initDialog();
                    }

                }else if(AppConstant.SECONDTYPE.equals(item.getRemark())) {
                    //秒杀活动
                    if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                        Intent intent = new Intent(mContext, HomeGoodsListActivity.class);
                        intent.putExtra(AppConstant.PAGETYPE, AppConstant.SECONDTYPE);
                        mContext.startActivity(intent);

                    } else {
                        initDialog();
                    }


                }else if (AppConstant.SHARETYPE.equals(item.getRemark())) {
                    //分享有礼
                    setIntent(item.getUrl());
                }else if(AppConstant.VIPTYPE.equals(item.getConfigCode())) {
                    //VIP会员
                    setIntent(item.getUrl());
                }else if(AppConstant.CONSULT.equals(item.getConfigCode())) {
                    //行业资讯
                    setIntentConsult(item.getRemark());
                }
            }
        });
    }
    CouponDialog couponDialog;
    private void initDialog() {
        couponDialog = new CouponDialog(mContext) {
            @Override
            public void Login() {
                mContext.startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                dismiss();
            }

            @Override
            public void Register() {
                LoginUtil.initRegister(getContext());
                dismiss();
            }
        };
        couponDialog.show();
    }

    private void setIntent(String URL) {
        Intent intent = new Intent(mContext, NewWebViewActivity.class);
        intent.putExtra("URL", URL);
        intent.putExtra("TYPE", 2);
        intent.putExtra("name","");
        mContext.startActivity(intent);
    }

    private void setIntentConsult(String URL) {

        Intent intent = new Intent(mContext, NewWebViewsActivity.class);
        intent.putExtra("URL", URL);
        intent.putExtra("city", UserInfoHelper.getCity(mContext));
        intent.putExtra("area",UserInfoHelper.getAreaName(mContext));
        intent.putExtra("changeFlag",UserInfoHelper.getChangeFlag(mContext));
        intent.putExtra("TYPE", 2);
        intent.putExtra("name","consult");
        mContext.startActivity(intent);
    }

}
