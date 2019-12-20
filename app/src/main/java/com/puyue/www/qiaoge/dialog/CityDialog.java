package com.puyue.www.qiaoge.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.mine.AreaAdapter;

import java.util.List;

/**
 * Created by ${王涛} on 2019/12/19
 */
public abstract class CityDialog extends Dialog {
    Context mContext;
    RecyclerView recyclerView;
    List<String> areaNames;
    public CityDialog(@NonNull Context context, List<String> areaNames) {
        super(context, R.style.promptDialog);
        setContentView(R.layout.dialog_city);
        mContext = context;
        this.areaNames = areaNames;
        initView();
        initAction();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,4));
        AreaAdapter areaAdapter = new AreaAdapter(R.layout.item_citys,areaNames);
        recyclerView.setAdapter(areaAdapter);
    }


    private void initAction() {

    }
    public abstract void Confirm();
}
