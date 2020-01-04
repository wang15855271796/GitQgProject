package com.puyue.www.qiaoge.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.home.CityChangeAPI;
import com.puyue.www.qiaoge.base.BaseActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.home.CityChangeModel;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王文博} on 2019/8/8
 */
public class ChangeCityActivity extends BaseActivity {


    private ImageView ic_back;

    private RecyclerView rl_city_change;
    private CityChangeAdapter mAdapter;

    private List<CityChangeModel> listCity =new ArrayList<>();

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.change_city_activity);
    }

    @Override
    public void findViewById() {
        ic_back = findViewById(R.id.ic_back);
        rl_city_change = findViewById(R.id.rl_city_change);
    }

    @Override
    public void setViewData() {

        rl_city_change.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new CityChangeAdapter(R.layout.item_city,R.layout.city_item, listCity);
        rl_city_change.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                UserInfoHelper.saveCity(mContext, listCity.get(position).getProvinceName());
//                Intent intent = new Intent();//跳回首页
//                setResult(104,intent);
//                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getCityList();
    }

    @Override
    public void setClickEvent() {

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void getCityList() {
        CityChangeAPI.requestCity(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CityChangeModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("sdddddddddddddddd",e.getMessage()+"");
                    }

                    @Override
                    public void onNext(CityChangeModel cityChangeModel) {
                        Log.d("sdddddddddddddddd00",cityChangeModel.getData()+"");
                        if (cityChangeModel.isSuccess()) {
                            listCity.clear();
                            List<CityChangeModel.DataBean> data = cityChangeModel.getData();
                            listCity.add(cityChangeModel);

                            mAdapter.notifyDataSetChanged();
                        } else {
                            AppHelper.showMsg(mContext, cityChangeModel.getMessage());
                        }


                    }
                });

    }



}
