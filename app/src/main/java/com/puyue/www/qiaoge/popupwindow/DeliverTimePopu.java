package com.puyue.www.qiaoge.popupwindow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.mine.ReturnOrderAdapter;
import com.puyue.www.qiaoge.model.mine.wallet.OrderReturnSelectReasonModel;

import java.util.List;

/**
 * Created by ${王文博} on 2019/5/7
 */
public class DeliverTimePopu extends PopupWindow {
    private Context context;
    private List<OrderReturnSelectReasonModel> list;
    private View view;
    private RecyclerView recyclerView;
    private ReturnOrderAdapter mReturnAdapter;

    public DeliverTimePopu(Context context, List<OrderReturnSelectReasonModel> list) {
        this.context = context;
        this.list = list;
        this.view = LayoutInflater.from(context).inflate(R.layout.deliver_time_popu, null);
        initView();
    }


    private void initView() {
        recyclerView = view.findViewById(R.id.ry_select);

       // mReturnAdapter = new ReturnOrderAdapter(R.layout.select_reason, list);

       // recyclerView.setAdapter(mReturnAdapter);
    }
}
