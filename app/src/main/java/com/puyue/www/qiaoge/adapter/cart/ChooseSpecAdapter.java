package com.puyue.www.qiaoge.adapter.cart;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.home.GetProductDetailModel;

import java.util.List;

/**
 * Created by ${王涛} on 2019/10/9
 */
public class ChooseSpecAdapter extends BaseAdapter {
    Context context;
    List<GetProductDetailModel.DataBean.ProdSpecsBean> prodSpecs;
    int selectPosition;
    Onclick onclick;
    com.puyue.www.qiaoge.listener.OnItemClickListener onItemClickListener;
    public ChooseSpecAdapter(Context context, List<GetProductDetailModel.DataBean.ProdSpecsBean> prodSpecs, Onclick onclick) {
        this.context = context;
        this.prodSpecs = prodSpecs;
        this.onclick = onclick;
    }

    @Override
    public int getCount() {
        return prodSpecs.size();

    }

    @Override
    public Object getItem(int position) {
        return prodSpecs.isEmpty() ? null : prodSpecs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_spec, null);
            holder = new Holder();
            holder.tv_spec = convertView.findViewById(R.id.tv_spec);
            holder.ll = convertView.findViewById(R.id.ll);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.tv_spec.setText(prodSpecs.get(position).getSpec());

        if(selectPosition==position) {
            holder.tv_spec.setTextColor(Color.parseColor("#FF680A"));
            holder.tv_spec.setBackgroundColor(Color.parseColor("#FEF5EF"));
        }else {

            holder.tv_spec.setTextColor(Color.parseColor("#333333"));
            holder.tv_spec.setBackgroundColor(Color.parseColor("#eeeeee"));
        }

        holder.tv_spec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.addDialog(position);
            }
        });





        return convertView;
    }

    public void setOnItemClickListener(com.puyue.www.qiaoge.listener.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class Holder {
        public TextView tv_spec;
        public LinearLayout ll;
    }
    public void selectPosition(int position) {
        this.selectPosition = position;

        notifyDataSetChanged();
    }

    public interface Onclick {
        void addDialog(int position);
    }
}


