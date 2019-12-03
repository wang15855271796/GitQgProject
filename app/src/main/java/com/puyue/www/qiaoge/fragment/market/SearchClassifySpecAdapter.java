package com.puyue.www.qiaoge.fragment.market;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.market.MarketRightModel;

import java.util.List;

class SearchClassifySpecAdapter extends BaseAdapter {

    Context context;
    List<MarketRightModel.DataBean.ProdClassifyBean.ListBean.ProdSpecsBean> prodSpecs;


    public SearchClassifySpecAdapter(Context mContext, List<MarketRightModel.DataBean.ProdClassifyBean.ListBean.ProdSpecsBean> prodSpecs) {
        this.context = mContext;
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
        holder.tv_spec.setText(prodSpecs.get(position).getSpec());
        holder.tv_spec.setEnabled(true);
        holder.tv_spec.setSelected(false);
//        holder.tv_spec.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!v.isEnabled()) {
//                    return;
//                }
//                for (int i = 0; i < prodSpecs.size(); i++) {
//                    prodSpecs.get(i).selected = false;
//                }
//                prodSpecs.get(position).selected = !holder.tv_spec.isSelected();
//                notifyDataSetChanged();
//            }
//        });


        return convertView;
    }

    static class Holder {
        public TextView tv_spec;
    }

}
