package com.puyue.www.qiaoge.adapter.home;

import android.graphics.Color;
import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.view.LineBreakLayout;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/16.
 */

public class SearchHistoryAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private LineBreakLayout lineBreakLayout;
    private  List<String> data;
    public SearchHistoryAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
        this.data=data;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        lineBreakLayout=  helper.getView(R.id.lineBreakLayout);
        //历史记录
        lineBreakLayout.removeAllViews();
        for (int i = 0; i < data.size(); i++) {
          TextView  tv = new TextView(mContext);
            tv.setTextColor(Color.parseColor("#666666"));
            tv.setBackgroundResource(R.drawable.app_linebreak_search_bg);
            tv.setTextSize(12);
            tv.setPadding(50,0, 50,0);
            if (!TextUtils.isEmpty(data.get(i))) {
                tv.setText(data.get(i));

            } else {
                tv.setText("");
            }
            lineBreakLayout.addView(tv);

        }
    }
}