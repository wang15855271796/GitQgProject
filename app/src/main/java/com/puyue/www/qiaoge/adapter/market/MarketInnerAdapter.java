package com.puyue.www.qiaoge.adapter.market;

import android.app.AlertDialog;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.cart.AddMountChangeTwoAPI;
import com.puyue.www.qiaoge.api.market.MarketRightModel;
import com.puyue.www.qiaoge.event.GoToMarketEvent;
import com.puyue.www.qiaoge.event.UpDateNumEvent;
import com.puyue.www.qiaoge.fragment.cart.UpdateEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.model.cart.AddCartGoodModel;
import com.puyue.www.qiaoge.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2019/11/4
 */
public class MarketInnerAdapter extends BaseQuickAdapter<MarketRightModel.DataBean.ProdClassifyBean.ListBean.ProdPricesBean,BaseViewHolder> {

    private ImageView iv_cut;
    private ImageView iv_add;
    int productId;
    private TextView tv_price;
    int businessType;
    int activeId;
    public MarketInnerAdapter(int activeId,int businessType,int productId,int layoutResId, @Nullable List<MarketRightModel.DataBean.ProdClassifyBean.ListBean.ProdPricesBean> data) {
        super(layoutResId, data);
        this.productId = productId;
        this.activeId = activeId;
        this.businessType = businessType;
    }


    @Override
    protected void convert(BaseViewHolder helper, MarketRightModel.DataBean.ProdClassifyBean.ListBean.ProdPricesBean item) {
        tv_price = helper.getView(R.id.tv_price);
        tv_price.setText(item.getPrice());
        helper.setText(R.id.tv_unit,item.getUnitDesc());
        TextView tv_num = helper.getView(R.id.tv_num);
        tv_num.setText(item.getCartNum()+"");
        iv_cut = helper.getView(R.id.iv_cut);
        iv_add = helper.getView(R.id.iv_add);
        TextView tv_old_price = helper.getView(R.id.tv_old_price);
        tv_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        helper.setText(R.id.tv_old_price,item.getOldPrice());
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(tv_num.getText().toString());
                num++;
                if(businessType==11) {
                    addCart(num,item.getPriceId(),activeId,businessType,tv_num,item.getCartNum());
                }else {
                    addCart(num,item.getPriceId(),productId,businessType,tv_num,num);
                }
            }
        });

        iv_cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(tv_num.getText().toString());
                if(num>0) {
                    num--;
                    if(businessType==11) {
                        addCarts(num,item.getPriceId(),activeId,businessType,tv_num,item.getCartNum());
                    }else {
                        addCarts(num,item.getPriceId(),productId,businessType,tv_num,num);
                    }
                }
            }
        });

        tv_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog alertDialog = new AlertDialog.Builder(mContext, R.style.DialogStyle).create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();
                Window window = alertDialog.getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
                        | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

                window.setContentView(R.layout.dialog_cart_num_set);

                EditText et_num = window.findViewById(R.id.et_num);
                TextView tv_ok = window.findViewById(R.id.tv_ok);
                TextView tv_cancel = window.findViewById(R.id.tv_cancel);

                window.setGravity(Gravity.CENTER);

                tv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                tv_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(businessType==11) {
                            if (et_num.getText().toString() != null && StringHelper.notEmptyAndNull(et_num.getText().toString())) {

                                AddMountChangeTwoAPI.AddMountChangeService(mContext, businessType, activeId, Integer.parseInt(et_num.getText().toString()), item.getPriceId())
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Subscriber<AddCartGoodModel>() {
                                            @Override
                                            public void onCompleted() {

                                            }

                                            @Override
                                            public void onError(Throwable e) {

                                            }


                                            @Override
                                            public void onNext(AddCartGoodModel addMountReduceModel) {

                                                if (addMountReduceModel.isSuccess()) {
                                                    tv_num.setText(et_num.getText().toString());
                                                    alertDialog.dismiss();
                                                    EventBus.getDefault().post(new UpDateNumEvent());
                                                    ToastUtil.showSuccessMsg(mContext,"成功");
                                                } else {
                                                    ToastUtil.showSuccessMsg(mContext, addMountReduceModel.getMessage());
                                                    tv_num.setText(addMountReduceModel.data.toString());
                                                    alertDialog.dismiss();
                                                }
                                            }
                                        });


                            } else {
                                ToastUtil.showSuccessMsg(mContext, "请输入数量");
                            }
                        }else {
                            if (et_num.getText().toString() != null && StringHelper.notEmptyAndNull(et_num.getText().toString())) {

                                AddMountChangeTwoAPI.AddMountChangeService(mContext, businessType, productId, Integer.parseInt(et_num.getText().toString()), item.getPriceId())
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Subscriber<AddCartGoodModel>() {
                                            @Override
                                            public void onCompleted() {

                                            }

                                            @Override
                                            public void onError(Throwable e) {

                                            }


                                            @Override
                                            public void onNext(AddCartGoodModel addMountReduceModel) {

                                                if (addMountReduceModel.isSuccess()) {
                                                    tv_num.setText(et_num.getText().toString());
                                                    alertDialog.dismiss();
                                                    EventBus.getDefault().post(new UpDateNumEvent());
                                                    ToastUtil.showSuccessMsg(mContext,"成功");
                                                } else {
                                                    ToastUtil.showSuccessMsg(mContext, addMountReduceModel.getMessage());
                                                    tv_num.setText(addMountReduceModel.data.toString());
                                                    alertDialog.dismiss();
                                                }
                                            }
                                        });


                            } else {
                                ToastUtil.showSuccessMsg(mContext, "请输入数量");
                            }
                        }

                    }
                });

            }

        });

    }

    private void addCarts(int num, int id, int businessId, int productType, TextView tv_num,int cartNum) {
        AddMountChangeTwoAPI.AddMountChangeService(mContext,productType,businessId,num,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AddCartGoodModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AddCartGoodModel addMountReduceModel) {
                        if (addMountReduceModel.isSuccess()) {
                            tv_num.setText(num + "");
                            EventBus.getDefault().post(new UpDateNumEvent());
                        } else {
                            ToastUtil.showSuccessMsg(mContext,addMountReduceModel.getMessage());
                        }
                    }
                });
    }

    /**
     * 添加购物车
     * @param num
     * @param tv_num
     */
    private void addCart(int num, int id, int businessId, int productType, TextView tv_num,int cartNum) {
        AddMountChangeTwoAPI.AddMountChangeService(mContext,productType,businessId,num,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AddCartGoodModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AddCartGoodModel addMountReduceModel) {
                        if (addMountReduceModel.isSuccess()) {
                            tv_num.setText(num + "");
                            ToastUtil.showSuccessMsg(mContext,"添加购物车成功");
                            EventBus.getDefault().post(new UpDateNumEvent());
                        } else {
                            ToastUtil.showSuccessMsg(mContext,addMountReduceModel.getMessage());
                        }
                    }
                });
    }

}
