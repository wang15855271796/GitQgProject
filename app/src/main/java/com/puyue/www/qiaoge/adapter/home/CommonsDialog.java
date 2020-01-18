package com.puyue.www.qiaoge.adapter.home;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.cart.ItemChooseAdapter;
import com.puyue.www.qiaoge.adapter.market.SpecAdapter;
import com.puyue.www.qiaoge.model.home.CouponModel;
import com.puyue.www.qiaoge.model.home.ProductNormalModel;
import com.puyue.www.qiaoge.view.FlowLayout;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by ${王涛} on 2020/1/6
 */
class CommonsDialog extends Dialog implements View.OnClickListener {
    Context context;
    public View view;
    public Unbinder binder;
    @BindView(R.id.iv_close)
    ImageView iv_close;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_sale)
    TextView tv_sale;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_desc)
    TextView tv_desc;
    @BindView(R.id.tv_stock)
    TextView tv_stock;
    @BindView(R.id.fl_container)
    FlowLayout fl_container;
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private SpecAdapter specAdapter;
    CouponModel.DataBean.ActivesBean listBean;
    int pos = 0;
    private ItemChooseAdapter itemChooseAdapter;
    NewPriceAdapter searchInnerAdapter;
    private NewSpecAdapter searchSpecAdapter;

    public CommonsDialog(Context mContext, CouponModel.DataBean.ActivesBean item) {
        super(mContext, R.style.dialog);
        this.context = mContext;
        this.listBean = item;
    }

    @Override
    public void onClick(View v) {

    }
}
