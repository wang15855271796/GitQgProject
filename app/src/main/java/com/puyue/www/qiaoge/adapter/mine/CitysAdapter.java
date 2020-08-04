package com.puyue.www.qiaoge.adapter.mine;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.CustomPopWindow;
import com.puyue.www.qiaoge.activity.mine.login.RegisterActivity;
import com.puyue.www.qiaoge.adapter.market.MarketSecondAdapter;
import com.puyue.www.qiaoge.dialog.CityDialog;
import com.puyue.www.qiaoge.dialog.PromptDialog;
import com.puyue.www.qiaoge.fragment.market.MarketsFragment;
import com.puyue.www.qiaoge.fragment.market.SearchProdAdapter;
import com.puyue.www.qiaoge.model.home.CityChangeModel;

import java.util.List;

/**
 * Created by ${王涛} on 2019/12/18
 * 选择城市
 */
public class CitysAdapter extends BaseAdapter {
    OnEventClickListener mOnEventClickListener;
    Context context;
    Holder holder;
    List<CityChangeModel.DataBean.CityNamesBean> prodSpecs;
    int selectPosition = -1;
    CityDialog cityDialog;
    CustomPopWindow mCustomPopWindow;
    public CitysAdapter(Context context, List<CityChangeModel.DataBean.CityNamesBean> prodSpecs) {
        this.context = context;
        this.prodSpecs = prodSpecs;
    }

    @Override
    public int getCount() {
        return prodSpecs.size();

    }

    public interface OnEventClickListener {
        void onEventClick(int position, View view);

    }

    public void setOnItemClickListeners(OnEventClickListener onEventClickListener) {
        mOnEventClickListener = onEventClickListener;
    }

    public void selectPosition(int position) {
        this.selectPosition = position;
        notifyDataSetChanged();
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_citys, null);
            holder = new Holder();
            holder.tv_city = convertView.findViewById(R.id.tv_city);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.tv_city.setText(prodSpecs.get(position).getCityName());
        prodSpecs.get(position).getAreaNames().get(position);
        if(mOnEventClickListener != null) {
            holder.tv_city.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnEventClickListener.onEventClick(position,holder.tv_city);
                }
            });


            if(selectPosition==position) {
                holder.tv_city.setTextColor(Color.parseColor("#FF680A"));
                holder.tv_city.setBackgroundColor(Color.parseColor("#FEF5EF"));

            }else {
                holder.tv_city.setTextColor(Color.parseColor("#333333"));
                holder.tv_city.setBackgroundColor(Color.parseColor("#eeeeee"));
            }
        }

        return convertView;
    }


    private void showsDialog() {
        View contentView = LayoutInflater.from(context).inflate(R.layout.popwindow_area,null);

        //当前界面没关闭，不是售罄产品才显示
        if (mCustomPopWindow == null){
            mCustomPopWindow= new CustomPopWindow.PopupWindowBuilder(context)
                    .setFocusable(false)
                    .setOutsideTouchable(false)
                    .setView(contentView)
                    .create()
                    .showAsDropDown(holder.tv_city);
        }
    }

    class Holder {
        public TextView tv_city;
    }

    public interface Onclick {
        void addDialog();
    }

}
