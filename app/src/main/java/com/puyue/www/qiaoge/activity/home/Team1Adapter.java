package com.puyue.www.qiaoge.activity.home;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.home.TeamActiveQueryModel;

import java.util.List;

/**
 * Created by ${王涛} on 2019/12/24
 */
public class Team1Adapter extends BaseQuickAdapter<TeamActiveQueryModel.DataBean,BaseViewHolder> {

    Onclick onclick;
    private ImageView iv_pic;
    TextView tv_old_price;
    private TeamActiveQueryModel.DataBean.ActivesBean activesBean;
    private TextView tv_total;
    ProgressBar pb;
    private TextView tv_add;
    private RelativeLayout rl_root;
    private int activeId;
    RecyclerView recyclerView;
    public Team1Adapter(int layoutResId, @Nullable List<TeamActiveQueryModel.DataBean> data, Onclick onclick) {
        super(layoutResId, data);
        this.onclick = onclick;
    }

    @Override
    protected void convert(BaseViewHolder helper, TeamActiveQueryModel.DataBean item) {
        recyclerView = helper.getView(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        TeamInnerAdapter teamInnerAdapter = new TeamInnerAdapter(R.layout.zuhe_inner,item.getActives(),onclick);
        recyclerView.setAdapter(teamInnerAdapter);
        helper.setText(R.id.tv_time,item.getTitle());
    }

    public interface Onclick {
        void addDialog();
    }


}
