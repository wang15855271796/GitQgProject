package com.puyue.www.qiaoge.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.puyue.www.qiaoge.utils.ToastUtil;
import com.puyue.www.qiaoge.view.KeyboardChangeListener;

import java.util.List;

/**
 * Created by ${王涛} on 2020/7/3
 */
public class CouponListsAdapter extends BaseQuickAdapter<String,BaseViewHolder>{
    List<String> list;
    Onclick onclick;
    private EditText et;
    Activity mActivity;
    int pos = 1;
    public CouponListsAdapter(Activity mActivity,int layoutResId, @Nullable List<String> data, Onclick onclick) {
        super(layoutResId, data);
        this.list = data;
        this.onclick = onclick;
        this.mActivity = mActivity;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
         et = helper.getView(R.id.et);
        et.setFocusable(true);
        et.setFocusableInTouchMode(true);
        et.requestFocus();

        ImageView iv_clear = helper.getView(R.id.iv_clear);
        iv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDatas(helper.getAdapterPosition());

            }
        });

        if(onclick!=null) {
            onclick.exCoupon(et);
        }
        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(helper.getAdapterPosition() != pos) {
                    pos = helper.getAdapterPosition();
//                    et.getText().clear();
                }

                if(onclick!=null) {
                    onclick.exCoupon(et);
                }
            }
        });



//        helper.getView(R.id.et).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(onclick!=null) {
//                    onclick.exCoupon(et);
//                }
//            }
//        });


    }
    public void addData(int position) {
        et.setFocusable(true);
        et.setFocusableInTouchMode(true);
        et.requestFocus();
        if(position==0) {
            list.add(position,"ss");
            notifyItemInserted(position);
        }else {
            if(et.getText().toString().equals("")||et.getText().toString().equals("0")) {
                ToastUtil.showSuccessMsg(mContext,"请填写金额");
            }else {
                et.setFocusable(true);
                et.setFocusableInTouchMode(true);
                et.requestFocus();
                list.add(position,"ss");
                notifyItemInserted(position);
//                et.getText().clear();
            }
        }

    }

    public void addDatas(int position) {
        list.remove(position);//删除数据源,移除集合中当前下标的数据
        notifyItemRemoved(position);//刷新被删除的地方
        et.getText().clear();
        et.clearFocus();
    }


    public void setCoupon(int position,double amount) {
        et.clearFocus();
        if(position==1) {
            et.setText(33+"");
        }else {
            if(et.getText().toString().equals("")||et.getText().toString().equals("0")) {
                et.setText(amount+"");
            }else {
                Log.d("fffffff.......",et.getText().toString());
                list.add(position,"ss");
                notifyItemInserted(position);
                et.setText(amount+"");
            }
        }
    }

    public interface Onclick {
        void exCoupon(EditText view);
    }

}
