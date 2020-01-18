package com.puyue.www.qiaoge.activity.home;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.adapter.mine.AddressAdapter;
import com.puyue.www.qiaoge.adapter.mine.SuggestAdressAdapter;
import com.puyue.www.qiaoge.api.mine.address.AddressListAPI;
import com.puyue.www.qiaoge.api.mine.address.DefaultAddressAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.event.AddressEvent;
import com.puyue.www.qiaoge.event.BackEvent;
import com.puyue.www.qiaoge.event.GoToMineEvent;
import com.puyue.www.qiaoge.fragment.home.CityEvent;
import com.puyue.www.qiaoge.fragment.market.MarketsFragment;
import com.puyue.www.qiaoge.fragment.market.SearchProdAdapter;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.mine.address.AddressModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.puyue.www.qiaoge.view.FlowLayout;
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
public class ChooseAddressActivity extends BaseSwipeActivity implements View.OnClickListener, SearchView.SearchViewListener, OnGetSuggestionResultListener {
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
                requestEditDefaultAddress(list.get(position).id,null);

                finish();
            }
        });


        tv_address.setOnClickListener(this);
        rl_empty.setOnClickListener(this);
        cityName = getIntent().getStringExtra("cityName");
        areaName = getIntent().getStringExtra("areaName");
        Log.d("wwdddddwwddd......",areaName);
        tv_area.setText(areaName);
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
        mSuggestionSearch.setOnGetSuggestionResultListener(this);

    }

    private void requestEditDefaultAddress(int id, String ids) {
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
//                            EventBus.getDefault().post(new AddressEvent());
                            UserInfoHelper.saveChangeFlag(mActivity,"0");
                            finish();
                            Log.d("woshidajiadeg.....","wwddddd");
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
                                list.addAll(addressModel.data);
                                Log.d("swrsdgdfgfg.....",list.size()+"");
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
                if (!StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
                    Intent intent = new Intent(mActivity, LoginActivity.class);
                    startActivity(intent);
                }
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
                Intent intents = new Intent(mActivity,ChangeCityActivity.class);
                startActivityForResult(intents, 105);
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

        if(!text.equals("")) {
            search_recycleView.setVisibility(View.VISIBLE);
            ll_address.setVisibility(View.GONE);
            if (cityName!=null&&StringHelper.notEmptyAndNull(cityName)) {
                /* 使用建议搜索服务获取建议列表，结果在onSuggestionResult()中更新 */
                mSuggestionSearch.requestSuggestion((new SuggestionSearchOption())
                        .keyword(text)
                        .city(cityName));

            }

        }else {
            search_recycleView.setVisibility(View.GONE);
            ll_address.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void onSearch(String text) {
        search_recycleView.setVisibility(View.GONE);

    }

    @Override
    public void onGetSuggestionResult(SuggestionResult suggestionResult) {

        if (suggestionResult == null || suggestionResult.getAllSuggestions() == null) {
            //permission_unfinished
            return;
        }

        List<String> suggest = new ArrayList<>();

        for (SuggestionResult.SuggestionInfo info : suggestionResult.getAllSuggestions()) {
            if (info.key != null) {
                suggest.add(info.key);
            }
        }
        search_recycleView.setVisibility(View.VISIBLE);

        adressAdapter = new SuggestAdressAdapter(suggest, mContext, new SuggestAdressAdapter.onClick() {
            @Override
            public void setLocation(int pos) {
                isClick = SharedPreferencesUtil.getInt(mActivity, "isClick");
//                UserInfoHelper.saveCity(mContext, suggest.get(pos));
                Intent intent = new Intent();//跳回首页
                UserInfoHelper.saveChangeFlag(mActivity,"1");
                Log.d("swdddddffffff.....","seffeeeee");
                if(isClick==0) {
                    UserInfoHelper.saveCity(mActivity,cityName);
                    UserInfoHelper.saveAreaName(mActivity,areaName);
                }else {
                    UserInfoHelper.saveCity(mActivity,city);
                    UserInfoHelper.saveAreaName(mActivity,areaName1);
                }
                setResult(104,intent);
                finish();
            }
        });
        search_recycleView.setLayoutManager(new LinearLayoutManager(mContext));
        search_recycleView.setAdapter(adressAdapter);
        adressAdapter.notifyDataSetChanged();
    }
}
