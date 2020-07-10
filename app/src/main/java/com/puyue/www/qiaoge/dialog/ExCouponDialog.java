package com.puyue.www.qiaoge.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.cart.ExchangeActivity;
import com.puyue.www.qiaoge.adapter.ExchangeAdapter;
import com.puyue.www.qiaoge.model.cart.ExChangeModel;
import com.puyue.www.qiaoge.model.home.ProductNormalModel;
import com.puyue.www.qiaoge.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ${王涛} on 2020/7/8
 */
public class ExCouponDialog extends Dialog {

    public View view;
    Context context;
    public Unbinder binder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<ExChangeModel.DetailListBean> mDatas;
    public ExCouponDialog(Context context, List<ExChangeModel.DetailListBean> mDatas) {
        super(context, R.style.dialog);
        this.context = context;
        this.mDatas = mDatas;
        init();

    }

    public void init() {
        view = View.inflate(context, R.layout.dialog_ex_change, null);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        binder = ButterKnife.bind(this, view);
        setContentView(view);
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = Utils.getScreenWidth(context);
        getWindow().setAttributes(attributes);
        ExchangeAdapter exchangeAdapter = new ExchangeAdapter(R.layout.item_exchange,mDatas);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(exchangeAdapter);

    }
}
