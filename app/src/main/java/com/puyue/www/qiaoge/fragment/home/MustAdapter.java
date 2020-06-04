package com.puyue.www.qiaoge.fragment.home;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.dialog.MustDialog;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.home.MustModel;

import java.util.List;

/**
 * auhtor
 */
public class MustAdapter extends BaseQuickAdapter<MustModel.DataBean, BaseViewHolder> {

    private ImageView iv_pic;
    List<MustModel.DataBean> activesBean;
    private ImageView iv_add;
    Onclick onclick;
    private MustDialog mustDialog;
    private RelativeLayout rl_group;
    String flag;
    private TextView tv_sale;
    ImageView iv_flag;
    public MustAdapter(String flag, int layoutResId, @Nullable List<MustModel.DataBean> activeList, Onclick onclick) {
        super(layoutResId, activeList);
        this.activesBean = activeList;
        this.onclick = onclick;
        this.flag = flag;

    }

    @Override
    protected void convert(BaseViewHolder helper, MustModel.DataBean item) {

        iv_pic = helper.getView(R.id.iv_pic);
        iv_flag = helper.getView(R.id.iv_flag);
        iv_add = helper.getView(R.id.iv_add);
        rl_group = helper.getView(R.id.rl_group);
        tv_sale = helper.getView(R.id.tv_sale);

        helper.setText(R.id.tv_name,item.getProductName());
        helper.setText(R.id.tv_price,item.getMinMaxPrice());
        Glide.with(mContext).load(item.getDefaultPic()).into(iv_pic);
        tv_sale.setVisibility(View.GONE);
        iv_flag.setVisibility(View.GONE);

        rl_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CommonGoodsDetailActivity.class);
                intent.putExtra(AppConstant.ACTIVEID,item.getProductMainId());
                mContext.startActivity(intent);
            }
        });

        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onclick!=null) {
                    onclick.addDialog();
                }

                if(StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    mustDialog = new MustDialog(mContext,item);
                    mustDialog.show();
                }
            }
        });
    }

    public interface Onclick {
        void addDialog();
    }

}
