package com.puyue.www.qiaoge.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.MoneyInputFilter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.dialog.ExCouponDialog;
import com.puyue.www.qiaoge.model.cart.ExChangeModel;
import com.puyue.www.qiaoge.model.cart.ItemModel;
import com.puyue.www.qiaoge.utils.ToastUtil;
import com.puyue.www.qiaoge.view.Arith;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${王涛} on 2020/7/30
 */
public class CouponListssAdapter extends BaseQuickAdapter<ItemModel,BaseViewHolder> {
    ItemModel item;
    public List<String> amounts = new ArrayList<>();
    private ImageView iv_clear;
    private EditText et;
    List<ItemModel> datas;
    String amount;
    List<ExChangeModel.DetailListBean> detailListBeans = new ArrayList<>();
    Activity mActivity;
    BaseViewHolder helper;
    public CouponListssAdapter(int layoutResId, @Nullable List<ItemModel> data, String amount, Activity mActivity) {
        super(layoutResId, data);
        this.datas = data;
        this.amount = amount;
        this.mActivity = mActivity;
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemModel item) {
        this.item = item;
        this.helper = helper;
        //第0 不能有删除功能
        int position = helper.getAdapterPosition();
        iv_clear = helper.getView(R.id.iv_clear);
        et = helper.getView(R.id.et);


        MoneyInputFilter filter = new MoneyInputFilter();
        filter.setDecimalLength(2);//保留小数点后2位
        InputFilter[] filters = {filter};
        et.setFilters(filters);

        if (position == 0) {
            iv_clear.setVisibility(View.GONE);
        } else {
            iv_clear.setVisibility(View.VISIBLE);
        }
        //判断是否有TextWatcher监听事件，有的话先移除
        if (et instanceof TextWatcher) {
            et.removeTextChangedListener(((TextWatcher) et.getTag(R.id.et)));
        }
        //移除了TextWatcher事件后设置item对应的文本
        et.setText(item.getNum());

        //设置焦点
        if (item.isFocus()) {
            if (!et.isFocused()) {
                et.requestFocus();
            }
            CharSequence text = item.getNum();
            et.setSelection(TextUtils.isEmpty(text) ? 0 : text.length());
        } else {
            if (et.isFocused()) {
                et.clearFocus();
            }
        }

        //删除该条数据
        iv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != 0) {
//                    amounts.remove(position);
                    datas.remove(position);
                    notifyDataSetChanged();
                    notifyItemRemoved(position);
                    if (position != datas.size()) {
                        notifyItemRangeChanged(position, getItemCount());
                    }
                }
            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                item.setNum(s.toString());
                getData().get(helper.getAdapterPosition()).setNum(String.valueOf(s));
                if (onArticleClickListener != null) {
                    onArticleClickListener.onArticleOnItemClick(et);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        et.setText(s.subSequence(0, 1));
                        et.setSelection(1);
                        return;
                    }
                }


                if (TextUtils.isEmpty(s)) {
                    item.setNum(null);
                } else {
                    item.setNum(s.toString());
                    if(datas.size()==1) {
                        if(!s.toString().equals(".")) {
                            Double s1 = Double.valueOf(s.toString());
                            Double amounts = Double.valueOf(amount);
                            if(amounts-s1<0.0) {
                                et.setText(amount);
                            }
                        }

                    }
                }
            }
        };
        et.setTag(R.id.et, textWatcher);
        et.addTextChangedListener(textWatcher);
    }

    Double total3 = 0.0;
    Double totals = 0.0;
    Double totalss = 0.0;
    public void addDatas(TextView tv_consume) {
        amounts.clear();
        if(et.getText().toString().equals("")||et.getText().toString().equals("0")||et.getText().toString().equals("0.0")||et.getText().toString().equals("0.00")||et.getText().toString().equals("0.")) {
            ToastUtil.showSuccessMsg(mContext,"请填写金额");
            return;
        }

        totals = 0.0;
        for (int i = 0; i <datas.size(); i++) {
            Double s1 = Double.valueOf(datas.get(i).getNum());
            totals +=s1;
            Double amounts = Double.valueOf(this.amount);
            if(totals>amounts) {
                totalss=0.0;
                for (int j = 0; j <datas.size()-1 ; j++) {
                    Double s2 = Double.valueOf(datas.get(j).getNum());
                    totalss +=s2;
                    Double amountss = Double.valueOf(this.amount);
                    et.setText((amountss-totalss)+"");
                }
            }
        }
        BigDecimal total=new BigDecimal("0.00");
        total3 = 0.0;
        for (int i = 0; i <datas.size() ; i++) {
            amounts.add(datas.get(i).getNum());
            Double s = Double.valueOf(datas.get(i).getNum());
            BigDecimal bigDecimal = new BigDecimal(datas.get(i).getNum());
            total = total.add(bigDecimal);
            total3+=s;
        }

        ExCouponDialog exCouponDialog = new ExCouponDialog(mActivity,detailListBeans,amounts,total);
        exCouponDialog.show();
    }

    /**
     * 一键兑换
     * @param amount
     */
    Double total = 0.0;
    Double total2 = 0.0;
    public void setCoupon(String amount) {
        BigDecimal total5 = new BigDecimal("0.00");
        BigDecimal total6 = new BigDecimal("0.00");
        Double examount = Double.valueOf(amount);
        total2 = 0.0;
        if(datas.size()==1) {
            et.setText(amount);

        }else {
            total=0.0;
            //当item大于1，并且当前item无任何内容时处理
            if(et.getText().toString().equals("")||et.getText().toString().equals("0")||et.getText().toString().equals("0.0")||et.getText().toString().equals("0.00")||et.getText().toString().equals("0.")) {
                for (int i = 0; i <datas.size()-1 ; i++) {
                    Double s = Double.valueOf(datas.get(i).getNum());
                    total+=s;
                    BigDecimal bigDecimal = new BigDecimal(datas.get(i).getNum());
                    total6 = total6.add(bigDecimal);
                    if(examount-total==0||examount-total<0) {
                        ToastUtil.showSuccessMsg(mContext,"可兑换余额不足");
                        return;
                    }

                    et.setText((examount-total)+"");
                }

            }else {
                total = 0.0;
                //当item大于1，并且当前item有内容时处理
                new android.os.Handler().postDelayed(new Runnable() {
                    public void run() {
                        for (int i = 0; i <datas.size()-1 ; i++) {
                            Double s = Double.valueOf(datas.get(i).getNum());

                            total+=s;
                            et.setText((examount-total)+"");
                        }
                    }
                }, 1000);

                for (int i = 0; i < datas.size(); i++) {
                    Double s = Double.valueOf(datas.get(i).getNum());
                    total2+=s;
                    BigDecimal bigDecimal = new BigDecimal(datas.get(i).getNum());
                    total5 = total5.add(bigDecimal);
                    if(examount-total2==0||examount-total2<0) {
                        ToastUtil.showSuccessMsg(mContext,"可兑换余额不足");
                        return;
                    }
                }
                if(datas.size()<10) {
                    datas.add(datas.size(), new ItemModel());
                }else {
                    ToastUtil.showSuccessMsg(mContext,"单次最多兑换10张");
                }

                notifyItemChanged(datas.size());
            }
        }


//        for (int i = 0; i < datas.size(); i++) {
//            Double s = Double.valueOf(datas.get(i).getNum());
//            total2+=s;
//            BigDecimal bigDecimal = new BigDecimal(datas.get(i).getNum());
//            total5 = total5.add(bigDecimal);
//            Log.d("dwsssssssss......",total5.toString()+"ff");
//            Log.d("dwsssssssss......",amount+"gg");
//            if(amount.equals(total5)) {
//                ToastUtil.showSuccessMsg(context,"可兑换余额不足");
//                return;
//            }
//        }

    }

    //  添加数据
    Double total1 = 0.0;
    int items = 0;
    List<Double> lists = new ArrayList<>();
    public void addData(ItemModel itemModel, String amount) {
        if(et.getText().toString().equals("")||et.getText().toString().equals("0")||et.getText().toString().equals("0.0")||et.getText().toString().equals("0.00")||et.getText().toString().equals("0.")) {
            ToastUtil.showSuccessMsg(mContext,"请填写金额");
            return;
        }

        //判断添加的第二个及以后的金额有没有超出总金额

        Double amounts = Double.valueOf(et.getText().toString());
        lists.add(amounts);
        total1+=amounts;
        Double amountss = Double.valueOf(amount);
        if(amountss-total1==0||amountss-total1<0) {
            et.setText(amount);
            ToastUtil.showSuccessMsg(mContext,"可兑换余额不足");
            return;
        }

//        Double totals3 = Double.valueOf(amount);
//        for (int i = 0; i < datas.size(); i++) {
//            if(et.getText().toString().equals(amount)) {
////                ToastUtil.showSuccessMsg(mContext,"可兑换余额不足");
//                return;
//            }else {
//                total1 = 0.0;
//                Double num = Double.valueOf(datas.get(i).getNum());
//                total1+=num;
//                String total2 = String.valueOf(total1);
//
//                if(total1> totals3) {
//                        et.setText(amount);
////                    ToastUtil.showSuccessMsg(mContext,"可兑换余额不足");
//                    return;
//                }
//                if(total2.equals(amount)) {
//                    et.setText(amount);
////                    ToastUtil.showSuccessMsg(mContext,"可兑换余额不足");
//                    return;
//                }
//            }
//        }

        //-----------------------------------------------
//        for (int i = 0; i <datas.size() ; i++) {
//            if(datas.size()==1&&holders.et.getText().toString().equals(amount)) {
//                ToastUtil.showSuccessMsg(context,"可兑换余额不足");
//                return;
//            }else if(datas.size()>1){
//                total1 = 0.0;
//                for (int j = 0; j < datas.size(); j++) {
//                    Double num = Double.valueOf(datas.get(j).getNum());
//                    total1+=num;
//                    String total2 = String.valueOf(total1);
//                    Log.d("dasdssssssss.......",total2+"");
//                    if(total1> totals3) {
//                        holders.et.setText(amount);
//                        ToastUtil.showSuccessMsg(context,"可兑换余额不足");
//                        return;
//                    }
//                    if(total2.equals(amount)) {
//                        ToastUtil.showSuccessMsg(context,"可兑换余额不足");
//                        return;
//                    }
//                }
//            }
//        }
//      在list中添加数据，并通知条目加入一条
        itemModel.setFocus(true);
        if(datas.size()<10) {
            datas.add(datas.size(), itemModel);
//            datas.add(new ItemModel());
//            notifyDataSetChanged();
            items++;
        }else {
            ToastUtil.showSuccessMsg(mContext,"单次最多兑换10张");
        }

        //添加动画
        notifyItemInserted(datas.size());
    }

    public interface OnArticleClickListener {
        void onArticleOnItemClick(EditText et);
    }

    private OnArticleClickListener onArticleClickListener;

    public void setOnArticleClickListener(OnArticleClickListener articleClickListener) {
        this.onArticleClickListener = articleClickListener;
    }
}
