package com.puyue.www.qiaoge.adapter.home;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.view.LineBreakLayout;

import java.util.List;

/**
 * Created by ${daff}
 * on 2018/10/17
 * 备注  搜索历史记录
 */
public class SearchHistoryGragViewAdapter extends BaseAdapter {
    private List<String> data;
    private Context context;

   public SearchHistoryGragViewAdapter(List<String> data, Context context) {
        this.data = data;
        this.context = context;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            //加载子布局
            view = LayoutInflater.from(context).inflate(R.layout.item_search_history, viewGroup, false);
            viewHolder = new ViewHolder();

            viewHolder.textView = view.findViewById(R.id.tv_item_search_history);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        /*//历史记录
        viewHolder.textView.removeAllViews();
        for (int m = 0; m < data.size(); m++) {
            TextView  tv = new TextView(context);
            tv.setTextColor(Color.parseColor("#000000"));

            tv.setBackgroundResource(R.drawable.app_linebreak_search_bg);
            tv.setTextSize(12);
            tv.setPadding(30,30,
                    30,30);

            if (!TextUtils.isEmpty(data.get(i))) {
                tv.setText(data.get(i));

            } else {
                tv.setText("");
            }
           viewHolder. textView.addView(tv);

        }*/
        viewHolder.textView .setText(data.get(i));
        return view;

    }
    private class ViewHolder {

     private TextView textView;
    }
}
