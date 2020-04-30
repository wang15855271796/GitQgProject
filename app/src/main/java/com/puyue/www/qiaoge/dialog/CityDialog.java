package com.puyue.www.qiaoge.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.HomeActivity;
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
    ImageView iv_close;
    List<CityChangeModel.DataBean.CityNamesBean.AreaNamesBean> areaNames;
    String flag;
    public CityDialog(String flag, Activity context, List<CityChangeModel.DataBean.CityNamesBean.AreaNamesBean> areaNames) {
        super(context, R.style.promptDialog);
        setContentView(R.layout.dialog_city);
        this.mContext = context;
        this.areaNames = areaNames;
        this.flag = flag;
        initView();
        initAction();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        ll_root = findViewById(R.id.ll_root);
        iv_close = findViewById(R.id.iv_close);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        AreaAdapter areaAdapter = new AreaAdapter(R.layout.item_citys,areaNames);
        recyclerView.setAdapter(areaAdapter);

        areaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Confirm();
                dismiss();

                if(flag!=null) {
                    UserInfoHelper.saveAreaName(mContext, areaNames.get(position).getAreaName());
                    SharedPreferencesUtil.saveInt(mContext,"isClick",1);
                    UserInfoHelper.saveChangeFlag(mContext,1+"");
                    Intent intent = new Intent();//跳回首页
                    mContext.setResult(104,intent);
                    EventBus.getDefault().post(new CityEvent());
                    mContext.finish();
                }else {
                    UserInfoHelper.saveAreaName(mContext, areaNames.get(position).getAreaName());
                    SharedPreferencesUtil.saveInt(mContext,"isClick",1);
                    UserInfoHelper.saveChangeFlag(mContext,1+"");
                    Intent intent = new Intent();//跳回首页
                    mContext.setResult(104,intent);
                    EventBus.getDefault().post(new CityEvent());
                    mContext.finish();
                }



            }
        });

        ll_root.setOnClickListener(this);
        iv_close.setOnClickListener(this);
    }


    private void initAction() {

    }
    public abstract void Confirm();
    public abstract void close();
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_root:
//                Confirm();
                break;

            case R.id.iv_close:
                close();
                break;
        }
    }
}
