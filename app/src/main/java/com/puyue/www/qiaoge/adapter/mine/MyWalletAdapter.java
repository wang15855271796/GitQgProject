package com.puyue.www.qiaoge.adapter.mine;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.wallet.GetWalletInfoModel;

import java.util.List;

/**
 * @author daff
 * @date 2018/9/22.
 * 备注  钱包页面
 */
public class MyWalletAdapter extends BaseQuickAdapter<GetWalletInfoModel.DataBean.RechargeListBean, BaseViewHolder> {

    private Onclick onclick;
    private RelativeLayout relativeLayout;
    private TextView price;
    private ImageView imageView;
    private TextView textCampaign;
    private TextView textHint;

    private int selectPosition;

    public MyWalletAdapter(int layoutResId, @Nullable List<GetWalletInfoModel.DataBean.RechargeListBean> data, Onclick onclick) {
        super(layoutResId, data);
        this.onclick = onclick;
    }

    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }

    @Override
    protected void convert(final BaseViewHolder helper, GetWalletInfoModel.DataBean.RechargeListBean item) {
        textCampaign = helper.getView(R.id.textCampaign);
        relativeLayout = helper.getView(R.id.relativeLayout);
        imageView = helper.getView(R.id.imageView);
        price = helper.getView(R.id.price);
        price.setText(item.getAmount() + "元");


        price.setTextColor(item.isFlag() ? Color.WHITE : Color.BLACK);
        relativeLayout.setBackgroundResource(item.isFlag() ? R.drawable.app_my_wallet_bg_three :R.drawable.app_my_wallet_bg_two);

        textHint = helper.getView(R.id.textHint);
        textHint.setTextColor(item.isFlag() ? Color.WHITE : Color.parseColor("#ffff8003"));
       /* imageView.setVisibility(item.isFlag() ? View.INVISIBLE :
                !TextUtils.isEmpty(item.getDescUrl()) ? View.VISIBLE : View.INVISIBLE);*/
        textCampaign.setVisibility(item.isFlag() ? View.INVISIBLE :
                !TextUtils.isEmpty(item.getDescUrl()) ? View.VISIBLE : View.INVISIBLE);


        textCampaign.setText(item.getDescUrl() + "");

        if (!TextUtils.isEmpty(item.getDescUrl())) {
            textHint.setText(item.getDescUrl() + "");
            textHint.setVisibility(View.VISIBLE);
        } else {
            textHint.setVisibility(View.INVISIBLE);
        }
        //选中状态下字体居中 不选中下靠左
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) price.getLayoutParams();
        if (item.isFlag()) {
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        } else {
            params.addRule(RelativeLayout.CENTER_HORIZONTAL, 0);
        }
        price.setLayoutParams(params);


        relativeLayout.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                onclick.ItemOnclick(helper.getLayoutPosition());
            }
        });


    }

    public interface Onclick {
        void ItemOnclick(int Position);


    }

    public void selectPosition(int position) {
        this.selectPosition = position;

        notifyDataSetChanged();
    }

}
