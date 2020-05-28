package com.puyue.www.qiaoge.activity.home;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.home.NewSpecAdapter;
import com.puyue.www.qiaoge.api.market.MarketRightModel;
import com.puyue.www.qiaoge.model.home.ProductNormalModel;

import java.util.List;

/**
 * Created by ${王涛} on 2019/11/15
 */
public class SelectionSpecAdapter extends BaseAdapter {

    Context context;
    List<MarketRightModel.DataBean.ProdClassifyBean.ListBean.ProdSpecsBean> prodSpecs;
    int selectPosition;
    public SelectionSpecAdapter(Context mContext, List<MarketRightModel.DataBean.ProdClassifyBean.ListBean.ProdSpecsBean> spec) {
        this.context = mContext;
        this.prodSpecs = spec;
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
        holder.tv_spec.setText(prodSpecs.get(position).getSpec());

        if(selectPosition==position) {

            holder.tv_spec.setTextColor(Color.parseColor("#FF680A"));
            holder.tv_spec.setBackgroundColor(Color.parseColor("#FEF5EF"));
        }else {

            holder.tv_spec.setTextColor(Color.parseColor("#333333"));
            holder.tv_spec.setBackgroundColor(Color.parseColor("#eeeeee"));
        }

        return convertView;
    }


    class Holder {
        public TextView tv_spec;
    }

    public void selectPosition(int position) {
        this.selectPosition = position;
        notifyDataSetChanged();
    }
}
