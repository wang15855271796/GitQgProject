package com.puyue.www.qiaoge.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.order.MySubOrderActivity;
import com.puyue.www.qiaoge.activity.mine.order.NewOrderDetailActivity;
import com.puyue.www.qiaoge.activity.mine.order.ReturnGoodDetailActivity;
import com.puyue.www.qiaoge.activity.mine.order.SelfSufficiencyOrderDetailActivity;
import com.puyue.www.qiaoge.api.mine.subaccount.SubAccountAddAPI;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.SubAccountListModel;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.mine.MessageModel;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2020/4/9
 */
public class SubAccountListAdapter extends BaseQuickAdapter<SubAccountListModel.DataBean.ListBean,BaseViewHolder> {
    String deliverType;
    public SubAccountListAdapter(int layoutResId, @Nullable List<SubAccountListModel.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubAccountListModel.DataBean.ListBean item) {
        helper.setText(R.id.tv_order_num,item.getOrderId());
        helper.setText(R.id.tv_amount,item.getOrderAmount());
        helper.setText(R.id.tv_date,item.getDateTime());
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_phone,item.getPhone());
        helper.setText(R.id.tv_read,item.getState());
        LinearLayout ll_root = helper.getView(R.id.ll_root);
        deliverType = UserInfoHelper.getDeliverType(mContext);
        ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getReaded(item.getId(),item);

            }
        });
    }

    private void getReaded(int id, SubAccountListModel.DataBean.ListBean item) {
        SubAccountAddAPI.readed(mContext,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MessageModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MessageModel messageModel) {
                        if (messageModel.isSuccess()) {
                            if (deliverType.equals("0")) {
                                if (item.getOrderState() == 11){
                                    Intent intent1 =new Intent(mContext,ReturnGoodDetailActivity.class);
                                    intent1.putExtra("orderType" ,0);
                                    intent1.putExtra(AppConstant.RETURNPRODUCTMAINID, id);
                                    mContext.startActivity(intent1);

                                }else {
                                    Intent intent2 = new Intent(mContext, NewOrderDetailActivity.class);
                                    intent2.putExtra(AppConstant.ORDERID,item.getOrderId());
                                    intent2.putExtra(AppConstant.ORDERSTATE, "3");
                                    intent2.putExtra(AppConstant.RETURNPRODUCTMAINID, "");
                                    mContext.startActivity(intent2);
                                }

                            } else if (deliverType.equals("1")) {
                                if (item.getOrderState() == 11){
                                    Intent intent3 =new Intent(mContext,ReturnGoodDetailActivity.class);
                                    intent3.putExtra("orderType" ,1);
                                    intent3.putExtra(AppConstant.RETURNPRODUCTMAINID, id);
                                    mContext.startActivity(intent3);

                                }else {
                                    Intent intent4 = new Intent(mContext, SelfSufficiencyOrderDetailActivity.class);
                                    intent4.putExtra(AppConstant.ORDERID, item.getOrderId());
                                    intent4.putExtra(AppConstant.ORDERSTATE, "3");
                                    intent4.putExtra(AppConstant.RETURNPRODUCTMAINID, "");
                                    mContext.startActivity(intent4);
                                }
                            }
                        } else {
                            AppHelper.showMsg(mContext, messageModel.getMessage());
                        }
                    }
                });


    }
}
