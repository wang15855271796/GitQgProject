package com.puyue.www.qiaoge.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * auhtor
 */
public class HomeTest extends BaseActivity {
    Unbinder binder;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    List<String> list = new ArrayList<>();


    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.fragments_home);
    }

    @Override
    public void findViewById() {
        binder = ButterKnife.bind(this);
        for (int i = 0; i < 80; i++) {
            list.add("sdsdsdsd");
        }
        MyAdapter myAdapter = new MyAdapter(R.layout.test,list);
        recycler.setLayoutManager(new LinearLayoutManager(HomeTest.this));
        recycler.setAdapter(myAdapter);
    }

    @Override
    public void setViewData() {

    }

    @Override
    public void setClickEvent() {

    }

    public class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public MyAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }
}
