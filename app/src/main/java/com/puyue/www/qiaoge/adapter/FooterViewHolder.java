package com.puyue.www.qiaoge.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;

/**
 * Created by Administrator on 2018/7/10.
 */

public class FooterViewHolder extends RecyclerView.ViewHolder {
    public static final int LOAD_MORE = 0;
    public static final int LOAD_PULL_TO = 1;
    public static final int LOAD_NONE = 2;
    public static final int LOAD_END = 3;

    private ProgressBar progressBar;
    private TextView tvLoadState;

    public FooterViewHolder(View itemView) {
        super(itemView);
        progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar_load_more);
        tvLoadState = (TextView) itemView.findViewById(R.id.tv_load_more_state);
    }

    public void bindItem(int status) {
        switch (status) {
            case LOAD_MORE:
                progressBar.setVisibility(View.VISIBLE);
                tvLoadState.setText("正在加载...");
                itemView.setVisibility(View.VISIBLE);
                break;
            case LOAD_PULL_TO:
                progressBar.setVisibility(View.GONE);
                tvLoadState.setText("加载更多");
                itemView.setVisibility(View.VISIBLE);
                break;
            case LOAD_NONE:
                progressBar.setVisibility(View.GONE);
                tvLoadState.setText("---我是有底线的---");
                break;
            case LOAD_END:
                itemView.setVisibility(View.GONE);
            default:
                break;
        }
    }
}
