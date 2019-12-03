package com.puyue.www.qiaoge.view.selectmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.puyue.www.qiaoge.R;

import java.util.List;

/**
 * 杭州融科网络
 * 刘宇飞创建 on 2017/6/9.
 * 描述：
 */

public class MyListView extends LinearLayout {
    private final List<String> contentList;
    private Context mContext;
    private OnSelectListener mOnSelectListener;

    public MyListView(Context context, List<String> contentList) {
        super(context);
        this.contentList = contentList;
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_list_view, this, true);
        ListView listView = (ListView) findViewById(R.id.listView);
        if (contentList == null || contentList.isEmpty()) {
            return;
        }
        TextAdapter adapter = new TextAdapter(mContext, contentList, R.color.colorWhite, R.color.colorWhite);
        listView.setAdapter(adapter);

        adapter.setOnItemClickListener(new TextAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (mOnSelectListener != null) {
                    mOnSelectListener.getValue(contentList.get(position), position);
                    adapter.showArrow(true);
                }

            }
        });
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        mOnSelectListener = onSelectListener;
    }

    public interface OnSelectListener {
        public void getValue(String value, int position);
    }
}
