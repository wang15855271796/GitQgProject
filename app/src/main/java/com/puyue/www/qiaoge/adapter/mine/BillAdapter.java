package com.puyue.www.qiaoge.adapter.mine;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.mine.GetWallertRecordByPageModel;
import com.puyue.www.qiaoge.view.PullStickyListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

/**
 * Created by ${王涛} on 2020/7/22
 */
public class BillAdapter extends BaseQuickAdapter<GetWallertRecordByPageModel.DataBean.RecordsBean,BaseViewHolder> {

    public BillAdapter(int layoutResId, @Nullable List<GetWallertRecordByPageModel.DataBean.RecordsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetWallertRecordByPageModel.DataBean.RecordsBean item) {
    }
}
