package com.puyue.www.qiaoge.adapter.cart;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.home.ExchangeProductModel;
import com.puyue.www.qiaoge.model.home.GetProductDetailModel;
import com.puyue.www.qiaoge.model.home.SearchResultsModel;

import java.util.List;

/**
 * Created by ${王涛} on 2019/10/16
 */
public class SearchSpecAdapter extends BaseAdapter {

    Context context;
    List<GetProductDetailModel.DataBean.ProdSpecsBean> prodSpecs;
    int selectPosition;

    public SearchSpecAdapter(Context context, List<GetProductDetailModel.DataBean.ProdSpecsBean> prodSpecs) {
        this.context = context;
        this.prodSpecs = prodSpecs;
    }

    @Override
    public int getCount() {
        return prodSpecs.size();

    }

    @Override
    public Object getItem(int position) {
        return null;
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
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        if(selectPosition==position) {
            holder.tv_spec.setTextColor(Color.parseColor("#FF680A"));
            holder.tv_spec.setBackgroundColor(Color.parseColor("#FEF5EF"));

        }else {
            holder.tv_spec.setTextColor(Color.parseColor("#333333"));
            holder.tv_spec.setBackgroundColor(Color.parseColor("#eeeeee"));

        }

        holder.tv_spec.setText(prodSpecs.get(position).getSpec());
        holder.tv_spec.setEnabled(true);
        holder.tv_spec.setSelected(false);

        return convertView;
    }

    static class Holder {
        public TextView tv_spec;
    }

    public void selectPosition(int position) {
        this.selectPosition = position;

        notifyDataSetChanged();
    }

}
