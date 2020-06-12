package com.puyue.www.qiaoge.activity.home;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.mine.CitysAdapter;
import com.puyue.www.qiaoge.dialog.CityDialog;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.home.CityChangeModel;
import com.puyue.www.qiaoge.view.FlowLayout;

import java.util.List;

/**
 * Created by ${王文博} on 2019/8/8
 * 选择省
 */
public class CityChangeAdapter extends BaseQuickAdapter<CityChangeModel.DataBean, BaseViewHolder> {

    private TextView tv_province;
    List<CityChangeModel.DataBean> data;
    private FlowLayout fl_container;
    private TextView tv_city;
    private String cityName;
    CityDialog cityDialog;
    public List<CityChangeModel.DataBean.CityNamesBean.AreaNamesBean> areaNames;
    Activity mActivity;
    String flag;
    public CityChangeAdapter(String flag,Activity mActivity, int layoutResId, @Nullable List<CityChangeModel.DataBean> data) {
        super(layoutResId, data);
        this.data = data;
        this.mActivity = mActivity;
        this.flag = flag;
    }



    @Override
    protected void convert(BaseViewHolder helper, CityChangeModel.DataBean item) {
        tv_province=helper.getView(R.id.tv_province);
        tv_province.setText(item.getProvinceName());
        fl_container = helper.getView(R.id.fl_container);

        CitysAdapter citysAdapter = new CitysAdapter(mContext,item.getCityNames());
        citysAdapter.setOnItemClickListeners(new CitysAdapter.OnEventClickListener() {
            @Override
            public void onEventClick(int position, View view) {
                citysAdapter.selectPosition(position);
                areaNames = item.getCityNames().get(position).getAreaNames();
                UserInfoHelper.saveCity(mContext, item.getCityNames().get(position).getCityName());
                cityDialog = new CityDialog(flag,mActivity,item.getCityNames().get(position).getAreaNames()) {
                    @Override
                    public void Confirm() {
                        mActivity.finish();
                    }

                    @Override
                    public void close() {
                        cityDialog.dismiss();
                    }
                };
                cityDialog.setCancelable(true);
                cityDialog.setCanceledOnTouchOutside(true);
                cityDialog.show();

            }
        });

        fl_container.setAdapter(citysAdapter);

    }

    public interface Onclick {
        void addDialog(int position, List<CityChangeModel.DataBean.CityNamesBean.AreaNamesBean> areaNames, View view);
    }
}
