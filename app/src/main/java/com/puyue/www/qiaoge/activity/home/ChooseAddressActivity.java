package com.puyue.www.qiaoge.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.account.AddressListActivity;
import com.puyue.www.qiaoge.activity.mine.account.EditAddressActivity;
import com.puyue.www.qiaoge.adapter.mine.SuggestAdressAdapter;
import com.puyue.www.qiaoge.api.home.CancleAPI;
import com.puyue.www.qiaoge.api.home.CityChangeAPI;
import com.puyue.www.qiaoge.api.mine.address.AddressListAPI;
import com.puyue.www.qiaoge.api.mine.address.DefaultAddressAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.event.AddressEvent;
import com.puyue.www.qiaoge.event.BackEvent;
import com.puyue.www.qiaoge.fragment.home.CityEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.CancleReasonModel;
import com.puyue.www.qiaoge.model.HotKeyModel;
import com.puyue.www.qiaoge.model.IsShowModel;
import com.puyue.www.qiaoge.model.mine.address.AddressModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.puyue.www.qiaoge.view.SearchView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2019/12/20
 */
public class ChooseAddressActivity extends BaseSwipeActivity implements View.OnClickListener, SearchView.SearchViewListener {
    @BindView(R.id.tv_tip)
    TextView tv_tip;
    @BindView(R.id.rl_tip)
    RelativeLayout rl_tip;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_address)
    TextView tv_address;
    private AddressModel mModelAddress;
    @BindView(R.id.tv_area_default)
    TextView tv_area_default;
    @BindView(R.id.tv_add_area)
    TextView tv_add_area;
    @BindView(R.id.tv_area)
    TextView tv_area;
    @BindView(R.id.rl_empty)
    RelativeLayout rl_empty;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.ll_list)
    LinearLayout ll_list;
    @BindView(R.id.search_recycleView)
    RecyclerView search_recycleView;
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.ll_address)
    LinearLayout ll_address;
    @BindView(R.id.ll_area)
    LinearLayout ll_area;
    List<AddressModel.DataBean> list = new ArrayList<>();
    private AddressListAdapter addressListAdapter;
    private SuggestionSearch mSuggestionSearch;
    private SuggestAdressAdapter adressAdapter;
    private String cityName;
    private String areaName;
    private int isClick;
    private String city;
    private String areaName1;
    private int isDefault;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.choose_activity);
    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mSuggestionSearch = SuggestionSearch.newInstance();
        SharedPreferencesUtil.saveInt(mActivity,"isClick",0);
        tv_tip.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        ll_area.setOnClickListener(this);
        tv_address.setOnClickListener(this);
        addressListAdapter = new AddressListAdapter(R.layout.item_address_list,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(addressListAdapter);
        addressListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                requestEditDefaultAddress(list.get(position).id,null,position);
                areaName = getIntent().getStringExtra("areaName");
//                SharedPreferencesUtil.saveString(mActivity,"cityName",list.get(position).getCityName());
//                SharedPreferencesUtil.saveString(mActivity,"areaName",list.get(position).getAreaName());
//                SharedPreferencesUtil.saveString(mActivity,"changeFlag","0");

                finish();
            }
        });


        tv_address.setOnClickListener(this);
        rl_empty.setOnClickListener(this);
        if(!TextUtils.isEmpty(getIntent().getStringExtra("cityName"))||!TextUtils.isEmpty(getIntent().getStringExtra("areaName"))) {
            cityName = getIntent().getStringExtra("cityName");
            areaName = getIntent().getStringExtra("areaName");
            tv_area.setText(cityName+areaName);
        }


        tv_add_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity,EditAddressActivity.class);
                intent.putExtra("type","add");
                startActivity(intent);
            }
        });

        //设置监听
        searchView.setSearchViewListener(this);

    }

    private void isShow() {
        CityChangeAPI.isShow(mActivity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<IsShowModel>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(IsShowModel isShowModel) {
                        if(isShowModel.isSuccess()) {
                            if(isShowModel.data!=null) {
                                EventBus.getDefault().post(new AddressEvent());
                                SharedPreferencesUtil.saveString(mActivity,"priceType",isShowModel.getData().enjoyProduct);
                                finish();
                            }
                        }else {
                            AppHelper.showMsg(mActivity,isShowModel.getMessage());
                        }
                    }
                });
    }

    private void requestEditDefaultAddress(int id, String ids, int position) {
        DefaultAddressAPI.requestEditDefaultAddress(mContext, id, ids)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        if (baseModel.success) {
                            UserInfoHelper.saveCity(mActivity,list.get(position).getCityName());
                            UserInfoHelper.saveAreaName(mActivity,list.get(position).getAreaName());
                            UserInfoHelper.saveChangeFlag(mActivity,"0");
                            isShow();


                        } else {
                            AppHelper.showMsg(mContext, baseModel.message);
                        }

                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getBack(BackEvent backEvent) {
        requestAddressList();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getBacks(CityEvent cityEvent) {
        String city = UserInfoHelper.getCity(mActivity);
        tv_area.setText(city);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setViewData() {
        requestAddressList();
    }

    /**
     * 获取地址列表
     */
    private void requestAddressList() {
        AddressListAPI.requestAddressModel(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AddressModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AddressModel addressModel) {
                        mModelAddress = addressModel;

                        if (mModelAddress.success) {
                            if(mModelAddress.data!=null&&mModelAddress.data.size()>0) {
                                list.clear();
                                list.addAll(addressModel.data);

                                addressListAdapter.notifyDataSetChanged();
                                for (int i = 0; i <list.size() ; i++) {
                                    isDefault = addressModel.data.get(i).isDefault;
                                    if(isDefault==1) {
                                        tv_area_default.setText(addressModel.data.get(i).detailAddress);
                                    }
                                }

                                ll_list.setVisibility(View.VISIBLE);
                                rl_empty.setVisibility(View.GONE);
                            }else {
                                ll_list.setVisibility(View.GONE);
                                rl_empty.setVisibility(View.VISIBLE);
                            }

//                            EventBus.getDefault().post(new AddressEvent());
                        } else {
                            AppHelper.showMsg(mContext, mModelAddress.message);
                        }
                    }
                });
    }

    @Override
    public void setClickEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_empty:
                    Intent intents = new Intent(mActivity,EditAddressActivity.class);
                    intents.putExtra("type","add");
                    startActivity(intents);
                break;

            case R.id.tv_tip:
                rl_tip.setVisibility(View.GONE);
                break;

            case R.id.iv_back:
                finish();
                break;

            case R.id.tv_address:
                Intent intent = new Intent(mActivity,AddressListActivity.class);
                intent.putExtra("mineAddress", "mineAddress");
                startActivity(intent);
                break;

            case R.id.ll_area:
                Intent intentss = new Intent(mActivity,ChangeCityActivity.class);
                startActivityForResult(intentss, 105);
                finish();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 105) {
            city = UserInfoHelper.getCity(mActivity);
            areaName1 = UserInfoHelper.getAreaName(mActivity);
        }
    }



    @Subscribe
    public void onEventMainThread(AddressEvent event) {
        list.clear();
        requestAddressList();

    }

    /**
     * 当搜索框 文本改变时 触发的回调 ,更新自动补全数据
     * @param text
     */
    @Override
    public void onRefreshAutoComplete(String text) {
        Log.d("defefffffff",text+"");
        if(!text.equals("")) {
            search_recycleView.setVisibility(View.VISIBLE);
            getReasonList(text);
        }else {
            search_recycleView.setVisibility(View.GONE);

        }

    }

    private void getReasonList(String text) {
        CancleAPI.getHot(mContext,text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HotKeyModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HotKeyModel hotKeyModel) {
                        if (hotKeyModel.isSuccess()) {
                            if(hotKeyModel.getData()!=null) {

                            }
                        } else {
                            AppHelper.showMsg(mContext, hotKeyModel.getMessage());
                        }
                    }
                });
    }

    @Override
    public void onSearch(String text) {
        search_recycleView.setVisibility(View.GONE);

    }
}
