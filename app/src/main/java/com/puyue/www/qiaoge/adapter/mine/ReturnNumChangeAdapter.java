package com.puyue.www.qiaoge.adapter.mine;

import android.app.Dialog;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.mine.order.GetReturnGoodDeductAPI;
import com.puyue.www.qiaoge.api.mine.order.GetReturnGoodNumAPI;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.DividerItemDecoration;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.mine.order.ReturnDetuctAmountModel;
import com.puyue.www.qiaoge.model.mine.order.ReturnOrderDetailModel;
import com.puyue.www.qiaoge.model.mine.order.ReturnSpecModel;

import java.math.BigDecimal;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.umeng.socialize.utils.DeviceConfig.context;

/**
 * Created by ${王文博} on 2019/5/22
 */
public class ReturnNumChangeAdapter extends BaseQuickAdapter<ReturnOrderDetailModel.DataBean.ProductsBean.DetailsBean, BaseViewHolder> {
    double totlaPrice = 0.0;

    public OnReturnClickListener listener;

    private int additionFlag;
    public OnReturnClickListener getListener() {
        return listener;
    }

    public void setListener(OnReturnClickListener listener) {
        this.listener = listener;
    }

   // EditText et_num;

    public interface OnReturnClickListener {
        void onEventClick();

    }

    public ReturnNumChangeAdapter(int layoutResId, @Nullable List<ReturnOrderDetailModel.DataBean.ProductsBean.DetailsBean> data,int additionFlag) {
        super(layoutResId, data);
        this.additionFlag=additionFlag;
    }

    @Override
    protected void convert(BaseViewHolder helper, ReturnOrderDetailModel.DataBean.ProductsBean.DetailsBean item) {

        item.setItemPrice(Double.parseDouble(item.getTotalPrice()));

    /*    if (et_num!=null){

        }else {
            et_num = helper.getView(R.id.et_num);
        }*/
      EditText  et_num = helper.getView(R.id.et_num);
        TextView tv_spec_num = helper.getView(R.id.tv_spec_num_return);
        TextView total_price = helper.getView(R.id.tv_total_price);
        RelativeLayout rl_spec_num = helper.getView(R.id.rl_spec_num);
        //    et_num.setText(item.getNum() + "");
        item.setItemUnitId(item.getUnitId());
        if ( et_num.getTag() instanceof TextWatcher) {
            et_num.removeTextChangedListener((TextWatcher) et_num.getTag());
        }
//et_num.getTag() != null ||

        et_num.setText(item.getNum()+"");
        total_price.setText(item.getTotalPrice());


        if (new BigDecimal(item.getPrice()).multiply(new BigDecimal(et_num.getText().toString())).setScale(2).doubleValue() -new BigDecimal(item.getDeductPrice()).multiply(new BigDecimal(et_num.getText().toString())).setScale(2).doubleValue()>0){
            String s = new BigDecimal(item.getPrice()).multiply(new BigDecimal(et_num.getText().toString())).setScale(2).doubleValue() - new BigDecimal(item.getDeductPrice()).multiply(new BigDecimal(et_num.getText().toString())).setScale(2).doubleValue() + "";
            BigDecimal bg = new BigDecimal(s);
            double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            total_price.setText(f1+"");

            item.setItemPrice(new BigDecimal(item.getPrice()).multiply(new BigDecimal(et_num.getText().toString())).setScale(2).doubleValue() -new BigDecimal(item.getDeductPrice()).multiply(new BigDecimal(et_num.getText().toString())).setScale(2).doubleValue());

           // total_price.setText(new BigDecimal(item.getPrice()).multiply(new BigDecimal(et_num.getText().toString())).setScale(2).doubleValue() -new BigDecimal(item.getDeductPrice()).multiply(new BigDecimal(et_num.getText().toString())).setScale(2).doubleValue()+ "");
        }else {
           item.setItemPrice(0);
           total_price.setText(0+"");
        }


        TextWatcher watcher =
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {


                        String text = s.toString();
                        int len = s.toString().length();
                        if (len > 1 && text.startsWith("0")) {
                            s.replace(0, 1, "");
                        }
                        if (et_num.getText().toString() == null || et_num.getText().toString().equals("")) {

                            item.setItemPrice(0.0);
                            item.setItemNum(0);
                        } else {
                            if (new BigDecimal(item.getPrice()).multiply(new BigDecimal(et_num.getText().toString())).setScale(2).doubleValue()- new BigDecimal(item.getDeductPrice()).multiply(new BigDecimal(et_num.getText().toString())).setScale(2).doubleValue()>0){
                                String s1 = new BigDecimal(item.getPrice()).multiply(new BigDecimal(et_num.getText().toString())).setScale(2).doubleValue() - new BigDecimal(item.getDeductPrice()).multiply(new BigDecimal(et_num.getText().toString())).setScale(2).doubleValue() + "";
                                BigDecimal bg = new BigDecimal(s1);
                                double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                total_price.setText(f1+"");


                               // total_price.setText(new BigDecimal(item.getPrice()).multiply(new BigDecimal(et_num.getText().toString())).setScale(2).doubleValue()- new BigDecimal(item.getDeductPrice()).multiply(new BigDecimal(et_num.getText().toString())).setScale(2).doubleValue()+ "");
                                item.setItemPrice(Double.parseDouble(total_price.getText().toString()));
                            }else {
                                total_price.setText(0+"");
                                item.setItemPrice(0);

                            }


                            item.setItemNum(Integer.parseInt(et_num.getText().toString()));
                        }


                        if (et_num.getText().toString() == null || et_num.getText().toString().equals("")) {
                            et_num.setText("0");
                        } else if (Integer.parseInt(et_num.getText().toString()) > item.getNum()) {
                            AppHelper.showMsg(mContext, "最大数量是" + item.getNum());
                            et_num.setText(item.getNum() + "");
                        } else {

                        }

                        et_num.setSelection(et_num.getText().length());
                        if (listener != null) {
                            listener.onEventClick();
                        }
                    }
                };
        et_num.addTextChangedListener(watcher);
        et_num.setTag(watcher);
        et_num.setSelection(et_num.getText().length());
        tv_spec_num.setText(item.getReturnUnits().get(0).getUnitName());
        // total_price.setText(item.getTotalPrice());

        rl_spec_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show(item, tv_spec_num, et_num, total_price);
            }
        });
    }

    public void show(ReturnOrderDetailModel.DataBean.ProductsBean.DetailsBean item, TextView tv_spec_num, EditText et_num, TextView total_price) {
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.return_spec, null);
        RecyclerView recyclerView = view.findViewById(R.id.rv_spec);
        ReturnSpecNumAdapter returnSpecNumAdapter = new ReturnSpecNumAdapter(R.layout.retrun_spec_num, item.getReturnUnits());

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(returnSpecNumAdapter);
        //添加分隔线
        DividerItemDecoration dividerPreKillDecoration = new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL_LIST);
        dividerPreKillDecoration.setDivider(R.drawable.app_divider);
        recyclerView.addItemDecoration(dividerPreKillDecoration);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(lp);

        dialog.show();

        returnSpecNumAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                int priceId = item.getPriceId();
                int unitId = item.getReturnUnits().get(position).getUnitId();
                String orderId = UserInfoHelper.getOrderId(mContext);
                int businessId = item.getBusinessId();
                item.setItemUnitId(item.getReturnUnits().get(position).getUnitId());
                int businessType = item.getBusinessType();
                tv_spec_num.setText(item.getReturnUnits().get(position).getUnitName());
                // if (unitNum >)
                GetReturnGoodNumAPI.requestSpec(mContext, orderId, businessId, businessType, 1, unitId, priceId,additionFlag)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ReturnSpecModel>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(ReturnSpecModel returnSpecModel) {
                                if (returnSpecModel.isSuccess()) {

                                    int maxNum = returnSpecModel.getData().getTotalNum();
                                    item.setNum(maxNum);

                                    String price = returnSpecModel.getData().getPrice();
                                    item.setPrice(price);
                                    et_num.setText(maxNum + "");

                                    getDetuctPrice(orderId, businessId, businessType, additionFlag, unitId, priceId, maxNum, price, total_price, item);

                                } else {
                                    AppHelper.showMsg(context, returnSpecModel.getMessage());
                                }
                            }
                        });

                dialog.dismiss();
            }
        });
    }



    public void getDetuctPrice(String orderId, int businessId, int businessType, int additionFlag, int unitId, int priceId, int maxNum, String price, TextView total_price, ReturnOrderDetailModel.DataBean.ProductsBean.DetailsBean item) {
        GetReturnGoodDeductAPI.requestDetuctSpec(mContext, orderId, businessId, businessType, additionFlag, unitId, priceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReturnDetuctAmountModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ReturnDetuctAmountModel returnDetuctAmountModel) {

                        if (returnDetuctAmountModel.isSuccess()) {
                            item.setDeductPrice(returnDetuctAmountModel.getData());
                            if (new BigDecimal(price).multiply(new BigDecimal(maxNum)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() - new BigDecimal(returnDetuctAmountModel.getData()).multiply(new BigDecimal(maxNum)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() > 0) {

                                String s1 = new BigDecimal(item.getPrice()).multiply(new BigDecimal(maxNum)).setScale(2).doubleValue() - new BigDecimal(item.getDeductPrice()).multiply(new BigDecimal(maxNum)).setScale(2).doubleValue() + "";
                                BigDecimal bg = new BigDecimal(s1);
                                double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                total_price.setText(f1+"");

                              //  total_price.setText(new BigDecimal(price).multiply(new BigDecimal(maxNum)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() - new BigDecimal(returnDetuctAmountModel.getData()).multiply(new BigDecimal(maxNum)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "");
                                item.setItemPrice(Double.parseDouble(total_price.getText().toString()));
                            } else {
                                total_price.setText(0 + "");
                                item.setItemPrice(0);
                            }
                            if (listener != null) {
                                listener.onEventClick();
                            }

                        } else {
                            AppHelper.showMsg(mContext, returnDetuctAmountModel.getMessage());
                        }
                    }
                });


    }
}
