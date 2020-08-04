package com.puyue.www.qiaoge.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.MoneyInputFilter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.dialog.ExCouponDialog;
import com.puyue.www.qiaoge.helper.BigDecimalUtils;
import com.puyue.www.qiaoge.model.cart.AmountItemModel;
import com.puyue.www.qiaoge.model.cart.ExChangeModel;
import com.puyue.www.qiaoge.model.cart.ItemModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.puyue.www.qiaoge.utils.ToastUtil;
import com.puyue.www.qiaoge.view.Arith;
import com.puyue.www.qiaoge.view.KeyboardChangeListener;
import com.puyue.www.qiaoge.view.flowtaglayout.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${王涛} on 2020/7/3
 */
public class CouponListsAdapter extends RecyclerView.Adapter<CouponListsAdapter.ViewHolder>{
    public List<String> amounts = new ArrayList<>();
    private List<ItemModel> datas;
    private LayoutInflater mInflater;
    private int mLayoutId;
    ViewHolder holders;
    Activity context;
    Double totals = 0.0;
    Double totalss = 0.0;
    List<ExChangeModel.DetailListBean> detailListBeans = new ArrayList<>();
    String amount;
    TextView tv_amount;
    TextView tv_consume;
    public CouponListsAdapter(Activity context, List<ItemModel> data, int layoutId, String amount, TextView tv_amount, TextView tv_consume) {
        this.datas = data;
        this.amount = amount;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        this.context = context;
        this.tv_amount = tv_amount;
        this.tv_consume = tv_consume;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(mLayoutId, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holders = holder;
        ItemModel itemModel = datas.get(position);

        MoneyInputFilter filter = new MoneyInputFilter();
        filter.setDecimalLength(2);//保留小数点后2位
        InputFilter[] filters = {filter};
        holder.et.setFilters(filters);

        //第0 不能有删除功能
        if (position == 0) {
            holder.iv_clear.setVisibility(View.GONE);
        } else {
            holder.iv_clear.setVisibility(View.VISIBLE);
        }
        //判断是否有TextWatcher监听事件，有的话先移除
        if (holder.et.getTag(R.id.et) instanceof TextWatcher) {
            holder.et.removeTextChangedListener(((TextWatcher) holder.et.getTag(R.id.et)));
        }
        //移除了TextWatcher事件后设置item对应的文本
        holder.et.setText(datas.get(position).getNum());

        //设置焦点
        if (datas.get(position).isFocus()) {
            if (!holder.et.isFocused()) {
                holder.et.requestFocus();
            }
            CharSequence text = datas.get(position).getNum();
            holder.et.setSelection(TextUtils.isEmpty(text) ? 0 : text.length());
        } else {
            if (holder.et.isFocused()) {
                holder.et.clearFocus();
            }
        }


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                itemModel.setNum(s.toString());

//                if (onArticleClickListener != null) {
//                    onArticleClickListener.onArticleOnItemClick();
//                }


                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        holder.et.setText(s.subSequence(0, 1));
                        holder.et.setSelection(1);
                        return;
                    }
                }


                Double total4 = Double.parseDouble(CouponListsAdapter.this.amount);
                if(!s.toString().equals("")&&!s.toString().equals(".")) {
                    Double s2 = Double.valueOf(s.toString());
                    tv_amount.setText(Arith.sub(total4,s2)+"");
                }

                if(s.toString().equals("")) {
                    tv_amount.setText(CouponListsAdapter.this.amount);
                }



                if (TextUtils.isEmpty(s)) {
                    datas.get(position).setNum(null);
                } else {
                    datas.get(position).setNum(s.toString());
                    if(datas.size()==1) {
                        if(!s.toString().equals(".")) {
                            Double s1 = Double.valueOf(s.toString());
                            Double amounts = Double.valueOf(CouponListsAdapter.this.amount);
                            if(amounts-s1<0.0) {
                                holder.et.setText(CouponListsAdapter.this.amount);
                            }
                        }

                    }
                }
            }
        };

        holder.et.addTextChangedListener(textWatcher);
        holder.et.setTag(R.id.et, textWatcher);

        //删除该条数据
        holder.iv_clear.setOnClickListener(new View.OnClickListener() {
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
    }

    Double total3 = 0.0;
    public void addDatas(TextView tv_consume) {
        amounts.clear();
        if(holders.et.getText().toString().equals("")||holders.et.getText().toString().equals("0")||holders.et.getText().toString().equals("0.0")||holders.et.getText().toString().equals("0.00")||holders.et.getText().toString().equals("0.")) {
            ToastUtil.showSuccessMsg(context,"请填写金额");
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
                    holders.et.setText((amountss-totalss)+"");
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

        ExCouponDialog exCouponDialog = new ExCouponDialog(context,detailListBeans,amounts,total);
        exCouponDialog.show();
        tv_consume.setText("消费金额"+total+"元");
    }
    //  添加数据
    Double total1 = 0.0;
    int items = 0;
    List<Double> lists = new ArrayList<>();
    public void addData(ItemModel itemModel, String amount) {
        if(holders.et.getText().toString().equals("")||holders.et.getText().toString().equals("0")||holders.et.getText().toString().equals("0.0")||holders.et.getText().toString().equals("0.00")||holders.et.getText().toString().equals("0.")) {
            ToastUtil.showSuccessMsg(context,"请填写金额");
            return;
        }

        //判断添加的第二个及以后的金额有没有超出总金额

        Double amounts = Double.valueOf(holders.et.getText().toString());
        lists.add(amounts);
        total1+=amounts;
        Double amountss = Double.valueOf(amount);
        if(amountss-total1==0||amountss-total1<0) {
            ToastUtil.showSuccessMsg(context,"可兑换余额不足");
            return;
        }

//        Double totals3 = Double.valueOf(amount);
//        Log.d("dasdssssssss.......",datas.size()+"aa");
//        for (int i = 0; i < datas.size(); i++) {
//            if(holders.et.getText().toString().equals(amount)) {
//                ToastUtil.showSuccessMsg(context,"可兑换余额不足");
//                return;
//            }else {
//                total1 = 0.0;
//                Double num = Double.valueOf(datas.get(i).getNum());
//                total1+=num;
//                String total2 = String.valueOf(total1);
//
//                Log.d("dasdssssssss.......","vvv");
//                if(total1> totals3) {
////                        holders.et.setText(amount);
//                    ToastUtil.showSuccessMsg(context,"可兑换余额不足");
//                    return;
//                }
//                if(total2.equals(amount)) {
//                    ToastUtil.showSuccessMsg(context,"可兑换余额不足");
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
            items++;
        }else {
            ToastUtil.showSuccessMsg(context,"单次最多兑换10张");
        }

        //添加动画
        notifyItemInserted(datas.size());
    }



    @Override
    public int getItemCount() {
        return datas.size();
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
            holders.et.setText(amount);

        }else {
            total=0.0;
            //当item大于1，并且当前item无任何内容时处理
            if(holders.et.getText().toString().equals("")||holders.et.getText().toString().equals("0")||holders.et.getText().toString().equals("0.0")||holders.et.getText().toString().equals("0.00")||holders.et.getText().toString().equals("0.")) {
                for (int i = 0; i <datas.size()-1 ; i++) {
                    Double s = Double.valueOf(datas.get(i).getNum());
                    total+=s;
                    BigDecimal bigDecimal = new BigDecimal(datas.get(i).getNum());
                    total6 = total6.add(bigDecimal);
                    if(examount-total==0||examount-total<0) {
                        ToastUtil.showSuccessMsg(context,"可兑换余额不足");
                        return;
                    }

                    holders.et.setText((examount-total)+"");
                }

            }else {
                total = 0.0;
                //当item大于1，并且当前item有内容时处理
                new android.os.Handler().postDelayed(new Runnable() {
                    public void run() {
                        for (int i = 0; i <datas.size()-1 ; i++) {
                            Double s = Double.valueOf(datas.get(i).getNum());

                            total+=s;
                            holders.et.setText((examount-total)+"");
                        }
                    }
                }, 1000);

                for (int i = 0; i < datas.size(); i++) {
                    Double s = Double.valueOf(datas.get(i).getNum());
                    total2+=s;
                    BigDecimal bigDecimal = new BigDecimal(datas.get(i).getNum());
                    total5 = total5.add(bigDecimal);
                    if(examount-total2==0||examount-total2<0) {
                        ToastUtil.showSuccessMsg(context,"可兑换余额不足");
                        return;
                    }
                }
                if(datas.size()<10) {
                    datas.add(datas.size(), new ItemModel());
                }else {
                    ToastUtil.showSuccessMsg(context,"单次最多兑换10张");
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





    public class ViewHolder extends RecyclerView.ViewHolder {
        private EditText et;
        private ImageView iv_clear;

        public ViewHolder(View itemView) {
            super(itemView);
            et = itemView.findViewById(R.id.et);
            iv_clear = itemView.findViewById(R.id.iv_clear);

        }


    }
    public interface Onclick {
        void addExCoupon();
    }

//    public interface OnArticleClickListener {
//        void onArticleOnItemClick();
//    }

//    private OnArticleClickListener onArticleClickListener;
//
//    public void setOnArticleClickListener(OnArticleClickListener articleClickListener) {
//        this.onArticleClickListener = articleClickListener;
//    }


//    List<String> list;
//    Onclick onclick;
//    private EditText et;
//    Activity mActivity;
//    int pos = 1;
//    private LayoutInflater mInflater;
//    int layoutResId;
//    public CouponListsAdapter(Activity mActivity,int layoutResId, @Nullable List<String> data, Onclick onclick) {
//        super(layoutResId, data);
//        this.list = data;
//        mInflater = LayoutInflater.from(mActivity);
//        this.onclick = onclick;
//        this.mActivity = mActivity;
//        this.layoutResId = layoutResId;
//        View view = mInflater.inflate(layoutResId, null, false);
//    }
//
//    @Override
//    protected void convert(BaseViewHolder helper, String item) {
//
//        et = helper.getView(R.id.et);
//        et.setFocusable(true);
//        et.setFocusableInTouchMode(true);
//        et.requestFocus();
//
//        ImageView iv_clear = helper.getView(R.id.iv_clear);
//        iv_clear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addDatas(helper.getAdapterPosition());
//
//            }
//        });
//
//        if(onclick!=null) {
//            onclick.exCoupon(et);
//        }
//        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(helper.getAdapterPosition() != pos) {
//                    pos = helper.getAdapterPosition();
////                    et.getText().clear();
//                }
//
//                if(onclick!=null) {
//                    onclick.exCoupon(et);
//                }
//            }
//        });
//
//
//
////        helper.getView(R.id.et).setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if(onclick!=null) {
////                    onclick.exCoupon(et);
////                }
////            }
////        });
//
//
//    }
//    public void addData(int position) {
//        et.setFocusable(true);
//        et.setFocusableInTouchMode(true);
//        et.requestFocus();
//        if(position==0) {
//            list.add(position,"ss");
//            notifyItemInserted(position);
//        }else {
//            if(et.getText().toString().equals("")||et.getText().toString().equals("0")) {
//                ToastUtil.showSuccessMsg(mContext,"请填写金额");
//            }else {
//                et.setFocusable(true);
//                et.setFocusableInTouchMode(true);
//                et.requestFocus();
//                list.add(position,"ss");
//                notifyItemInserted(position);
////                et.getText().clear();
//            }
//        }
//
//    }
//
//    public void addDatas(int position) {
//        list.remove(position);//删除数据源,移除集合中当前下标的数据
//        notifyItemRemoved(position);//刷新被删除的地方
//        et.getText().clear();
//        et.clearFocus();
//    }
//
//
//    public void setCoupon(int position,double amount) {
//        et.clearFocus();
//        if(position==1) {
//            et.setText(33+"");
//        }else {
//            if(et.getText().toString().equals("")||et.getText().toString().equals("0")) {
//                et.setText(amount+"");
//            }else {
//                list.add(position,"ss");
//                notifyItemInserted(position);
//                et.setText(amount+"");
//            }
//        }
//    }
//
//    public interface Onclick {
//        void exCoupon(EditText view);
//    }


}
