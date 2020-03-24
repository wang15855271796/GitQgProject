package com.puyue.www.qiaoge.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.mine.AreaAdapter;
import com.puyue.www.qiaoge.fragment.home.CityEvent;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.home.CityChangeModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by ${王涛} on 2019/12/19
 * 县 区 弹窗
 */
public abstract class CityDialog extends Dialog implements View.OnClickListener {
    Activity mContext;
    RecyclerView recyclerView;
    LinearLayout ll_root;
    List<CityChangeModel.DataBean.CityNamesBean.AreaNamesBean> areaNames;
    public CityDialog(@NonNull Activity context, List<CityChangeModel.DataBean.CityNamesBean.AreaNamesBean> areaNames) {
        super(context, R.style.promptDialog);
        setContentView(R.layout.dialog_city);
        this.mContext = context;
        this.areaNames = areaNames;
        initView();
        initAction();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        ll_root = findViewById(R.id.ll_root);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        AreaAdapter areaAdapter = new AreaAdapter(R.layout.item_citys,areaNames);

        recyclerView.setAdapter(areaAdapter);

        areaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Confirm();
                dismiss();
                UserInfoHelper.saveAreaName(mContext, areaNames.get(position).getAreaName());
                Log.d("yuyuyuyuyuyuyu.....",areaNames.get(position).getAreaName());
                SharedPreferencesUtil.saveInt(mContext,"isClick",1);
                UserInfoHelper.saveChangeFlag(mContext,1+"");
                Intent intent = new Intent();//跳回首页
                EventBus.getDefault().post(new CityEvent());
                mContext.setResult(104,intent);
                mContext.finish();


            }
        });

        ll_root.setOnClickListener(this);
    }


    private void initAction() {

    }
    public abstract void Confirm();

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_root:
//                Confirm();
                break;
        }
    }
}
