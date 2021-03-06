package com.puyue.www.qiaoge.activity.mine.account;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.mine.SuggestAdressAdapter;
import com.puyue.www.qiaoge.api.mine.address.AddAddressAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/12.
 */

public class EditAddressActivity extends BaseSwipeActivity implements OnGetSuggestionResultListener {
    public static final String TYPE = "type";//是来编辑地址,还是来新建地址的
    public static final String USER_NAME = "user_name";
    public static final String USER_PHONE = "user_phone";
    public static final String STORE_NAME = "store_name";
    public static final String AREA = "area";//三级城市
    public static final String ADDRESS = "address";//详细地址
    public static final String DEFAULT = "default";//是否是默认地址
    public static final String ADDRESS_ID = "address_id";//地址id
    public static final String PROVINCE_CODE = "province_code";//省ID
    public static final String CITY_CODE = "city_code";//市ID
    public static final String AREA_CODE = "area_code";
    public static final String ORDERID = "orderId";

    private String mType;
    private String mUserName;
    private String mUserPhone;
    private String mStoreName;
    private String mArea;
    private String mAddress;
    private String mDefault;
    private String mAddressId;
    private String mProvinceCode;
    private String mCityCode;
    private String mAreaCode;
    private ImageView mIvBack;
    private EditText mEditName;
    private EditText mEditPhone;
    private EditText mEditStore;
    private TextView mTvArea;
    private AutoCompleteTextView keyWorldsView;
    private LinearLayout mLlDefault;
    private CheckBox mCbDefault;
    private Button mBtnConfirm;
    private ListView list_view;
    private NestedScrollView mLlEditAddress;
    private TextView mTvTitle;
    //申明对象
    private CityPickerView mPicker = new CityPickerView();
    private String proviceCode;
    private String cityCode;
    private String areaCode;
    private int isDefault;
    private BaseModel mModelAddAddress;
    private BaseModel mModelEditAddress;
    private boolean isDefaultNow = false;

    //private PoiSearch poiSearch= PoiSearch.newInstance();
    private SuggestionSearch mSuggestionSearch;

    private ArrayAdapter<String> sugAdapter = null;
    private int loadIndex = 0;


    private SuggestAdressAdapter adressAdapter;
    private RecyclerView ry_suggest;
    private TextView tv_target;


    private String cityName;
    private String orderId;
    public static Intent getIntent(Context context, Class<?> cls, String type, String userName, String userPhone, String storeName, String area, String address, String defaultOr, String addressId, String proviceCode, String cityCode, String areaCode,String orderId) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.putExtra(TYPE, type);
        intent.putExtra(USER_NAME, userName);
        intent.putExtra(USER_PHONE, userPhone);
        intent.putExtra(STORE_NAME, storeName);
        intent.putExtra(AREA, area);
        intent.putExtra(ADDRESS, address);
        intent.putExtra(DEFAULT, defaultOr);
        intent.putExtra(ADDRESS_ID, addressId);
        intent.putExtra(PROVINCE_CODE, proviceCode);
        intent.putExtra(CITY_CODE, cityCode);
        intent.putExtra(AREA_CODE, areaCode);
        intent.putExtra(ORDERID, orderId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleExtra(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        mType = getIntent().getStringExtra(TYPE);
        mUserName = getIntent().getStringExtra(USER_NAME);
        mUserPhone = getIntent().getStringExtra(USER_PHONE);
        mStoreName = getIntent().getStringExtra(STORE_NAME);
        mArea = getIntent().getStringExtra(AREA);
        mAddress = getIntent().getStringExtra(ADDRESS);
        mDefault = getIntent().getStringExtra(DEFAULT);
        mAddressId = getIntent().getStringExtra(ADDRESS_ID);
        mProvinceCode = getIntent().getStringExtra(PROVINCE_CODE);
        mCityCode = getIntent().getStringExtra(CITY_CODE);
        mAreaCode = getIntent().getStringExtra(AREA_CODE);
        orderId = getIntent().getStringExtra(ORDERID);
        if (savedInstanceState != null) {
            mType = savedInstanceState.getString(TYPE);
            mUserName = savedInstanceState.getString(USER_NAME);
            mUserPhone = savedInstanceState.getString(USER_PHONE);
            mStoreName = savedInstanceState.getString(STORE_NAME);
            mArea = savedInstanceState.getString(AREA);
            mAddress = savedInstanceState.getString(ADDRESS);
            mDefault = savedInstanceState.getString(DEFAULT);
            mAddressId = savedInstanceState.getString(ADDRESS_ID);
            mProvinceCode = savedInstanceState.getString(PROVINCE_CODE);
            mCityCode = savedInstanceState.getString(CITY_CODE);
            mAreaCode = savedInstanceState.getString(AREA_CODE);
            orderId = savedInstanceState.getString(ORDERID);
        }
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_edit_address);
    }


    @Override
    public void findViewById() {
        mIvBack = (ImageView) findViewById(R.id.iv_edit_address_back);
        mEditName = (EditText) findViewById(R.id.edit_edit_address_name);
        mEditPhone = (EditText) findViewById(R.id.edit_edit_address_phone);
        mEditStore = (EditText) findViewById(R.id.edit_edit_address_store);//店名选填
        mTvArea = (TextView) findViewById(R.id.tv_edit_address_area);
        keyWorldsView = (AutoCompleteTextView) findViewById(R.id.edit_edit_address_address);
        mLlDefault = (LinearLayout) findViewById(R.id.ll_edit_address_default);
        mCbDefault = (CheckBox) findViewById(R.id.cb_edit_address_default);
        mBtnConfirm = (Button) findViewById(R.id.btn_edit_address_confirm);
        //  mLlEditAddress = (NestedScrollView) findViewById(R.id.ll_edit_address);
        mTvTitle = (TextView) findViewById(R.id.tv_edit_address_title);
        list_view = (ListView) findViewById(R.id.list_view);
        ry_suggest = (RecyclerView) findViewById(R.id.ry_suggest);
        tv_target = (TextView) findViewById(R.id.tv_target);
    }

    @Override
    public void setViewData() {
        //   setTranslucentStatus();
        mSuggestionSearch = SuggestionSearch.newInstance();
cityName=mArea;
        //  mSuggestionSearch = SuggestionSearch.newInstance();
        mPicker.init(mContext);
        if (StringHelper.notEmptyAndNull(mType)) {
            if (mType.equals("add")) {
                mBtnConfirm.setEnabled(false);
                mBtnConfirm.setTextColor(getResources().getColor(R.color.app_btn_unable));
                mTvTitle.setText("新增收货地址");
                mTvArea.setText("请选择所在地区");
                mTvArea.setTextColor(Color.parseColor("#D5D5D5"));
            } else if (mType.equals("edit")) {
                mBtnConfirm.setEnabled(true);
                mBtnConfirm.setTextColor(getResources().getColor(R.color.app_color_white));
                mTvTitle.setText("编辑收货地址");
                mTvArea.setText(mArea);
                mTvArea.setTextColor(Color.parseColor("#232131"));
                mEditName.setText(mUserName);
                mEditPhone.setText(mUserPhone);
                keyWorldsView.setText(mAddress);
                if (StringHelper.notEmptyAndNull(mStoreName)) {
                    mEditStore.setText(mStoreName);

                }
                if (StringHelper.notEmptyAndNull(mDefault)) {
                    if (mDefault.equals("true")) {
                        //原来就是默认地址,先显示成默认地址
                        mCbDefault.setChecked(true);
                    } else if (mDefault.equals("false")) {
                        //原来不是默认地址,不显示为默认地址
                        mCbDefault.setChecked(false);
                    }
                }
            }
        }

/*

        sugAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line);

        keyWorldsView.setAdapter(sugAdapter);

        keyWorldsView.setThreshold(1);
*/


        keyWorldsView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (keyWorldsView.getText().toString() != null) {
                        /* 使用建议搜索服务获取建议列表，结果在onSuggestionResult()中更新 */
                        if (cityName!=null&&StringHelper.notEmptyAndNull(cityName))
                        {
                            mSuggestionSearch.requestSuggestion((new SuggestionSearchOption())

                                    .keyword(keyWorldsView.getText().toString())

                                    .city(cityName)
                           )
                            ;
                        }

                    }
                }

            }
        });
        /* 当输入关键字变化时，动态更新建议列表 */
        keyWorldsView.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                if (cs.length() <= 0) {
                    ry_suggest.setVisibility(View.GONE);
                    tv_target.setVisibility(View.GONE);
                    return;
                }
                showSugDialog(cs);
            }
        });
    /*    OnGetSuggestionResultListener listener = new OnGetSuggestionResultListener() {
            @Override
            public void onGetSuggestionResult(SuggestionResult suggestionResult) {
                //处理sug检索结果
            }
        };*/

        mSuggestionSearch.setOnGetSuggestionResultListener(this);


    }

    private void showSugDialog(CharSequence cs) {
        if (cityName!=null&&StringHelper.notEmptyAndNull(cityName)) {
            /* 使用建议搜索服务获取建议列表，结果在onSuggestionResult()中更新 */
            mSuggestionSearch.requestSuggestion((new SuggestionSearchOption())

                    .keyword(cs.toString())
                    .city(cityName));

        }
    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(noDoubleClickListener);
        mTvArea.setOnClickListener(noDoubleClickListener);
        mBtnConfirm.setOnClickListener(noDoubleClickListener);
        mLlDefault.setOnClickListener(noDoubleClickListener);
        mEditName.addTextChangedListener(textWatcher);
        mEditPhone.addTextChangedListener(textWatcher);
        keyWorldsView.addTextChangedListener(textWatcher);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (StringHelper.notEmptyAndNull(mEditName.getText().toString())
                    && StringHelper.notEmptyAndNull(mEditPhone.getText().toString())
                    && StringHelper.notEmptyAndNull(keyWorldsView.getText().toString())
                    && StringHelper.notEmptyAndNull(mTvArea.getText().toString())
                    && !mTvArea.getText().toString().equals("请选择所在地区")) {
                mBtnConfirm.setEnabled(true);
                mBtnConfirm.setTextColor(getResources().getColor(R.color.app_color_white));
            } else {
                mBtnConfirm.setEnabled(false);
                mBtnConfirm.setTextColor(getResources().getColor(R.color.app_btn_unable));
            }
        }
    };

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            if (view == mIvBack) {
                //这里要判断,如果用户对文案进行了操作,但是要退出,需要弹框提示
                //如果没有对文案进行过操作,直接退出
                String nowDefault;
                if (mCbDefault.isChecked()) {
                    nowDefault = "true";
                } else {
                    nowDefault = "false";
                }
                if (StringHelper.notEmptyAndNull(mType)) {
                    if (mType.equals("add")) {
                        finish();
                    } else if (mType.equals("edit")) {
                        if (mUserName.equals(mEditName.getText().toString())
                                && mUserPhone.equals(mEditPhone.getText().toString())
                                && mArea.equals(mTvArea.getText().toString())
                                && mAddress.equals(keyWorldsView.getText().toString())
                                && (mDefault.equals(nowDefault))
                                && mTvArea.getText().toString().equals(mArea)) {
                            //数据没有动过,直接退出
                            finish();
                        } else {
                            //数据变更过
                            showBackDialog();
                        }
                    }
                }
            } else if (view == mTvArea) {
                hintKbTwo();
                selectCity();
            } else if (view == mBtnConfirm) {
                //在这里点击确定了,判断数据是否完整,完整才能请求接口
                //请求接口反成功之后,调用onResult,finish这个activity
                //回到上个地址列表页,重新请求一次地址列表数据
                if (mEditPhone.getText().toString().length() == 11) {
                    if (mType.equals("add")) {
                        requestAddAddress();
                    } else if (mType.equals("edit")) {
                        requestEditAddress();
                    }
                } else {
                    AppHelper.showMsg(mContext, "手机号位数错误");
                }
            } else if (view == mLlDefault) {
                if (isDefaultNow) {
                    //现在就是默认的,点击变成不是默认的
                    mCbDefault.setChecked(false);
                    isDefaultNow = false;
                } else {
                    mCbDefault.setChecked(true);
                    isDefaultNow = true;
                }
            }
        }


    };

    private void requestEditAddress() {
        if (mCbDefault.isChecked()) {
            //选为默认地址
            isDefault = 1;
        } else {
            //不选为默认地址
            isDefault = 0;
        }
        if (mTvArea.getText().toString().equals(mArea)) {
            //说明用户可能修改了其他的数据,但是没有修改所在区域,这时候直接将上个界面传过来的值赋值给现在要上传的数据
            proviceCode = mProvinceCode;
            cityCode = mCityCode;
            areaCode = mAreaCode;
        }
        AddAddressAPI.requestAddAddress(mContext, mEditName.getText().toString(), mEditPhone.getText().toString(), proviceCode, cityCode, areaCode, isDefault, keyWorldsView.getText().toString(), mEditStore.getText().toString(), mAddressId,orderId)
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
                        mModelEditAddress = baseModel;
                        if (mModelEditAddress.success) {
                            AppHelper.showMsg(mContext, "修改地址成功!");
                            Intent intent = new Intent();
                            intent.putExtra("type", "edit");
                            intent.putExtra("defaultNum",isDefault);
                            EditAddressActivity.this.setResult(22, intent);
                            finish();
                        } else {
                            AppHelper.showMsg(mContext, mModelEditAddress.message);
                        }
                    }
                });
    }

    private void requestAddAddress() {
        if (mCbDefault.isChecked()) {
            //选为默认地址
            isDefault = 1;
        } else {
            //不选为默认地址
            isDefault = 0;
        }
        AddAddressAPI.requestAddAddress(mContext, mEditName.getText().toString(), mEditPhone.getText().toString(), proviceCode, cityCode, areaCode, isDefault, keyWorldsView.getText().toString(), mEditStore.getText().toString(), "",orderId)
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
                        logoutAndToHome(mContext, baseModel.code);
                        mModelAddAddress = baseModel;
                        if (mModelAddAddress.success) {
                            AppHelper.showMsg(mContext, "新增地址成功!");
                            Intent intent = new Intent();
                            intent.putExtra("type", "add");
                            EditAddressActivity.this.setResult(11, intent);
                            finish();
                        } else {
                            AppHelper.showMsg(mContext, mModelAddAddress.message);
                        }
                    }
                });
    }

    private void selectCity() {
        CityConfig cityConfig = new CityConfig.Builder().title("")//标题
                .titleTextSize(0)//标题文字大小
                .titleTextColor("#585858")//标题文字颜色
                .titleBackgroundColor("#ffffff")//标题栏背景色
                .confirTextColor("#F56D23")//确认按钮文字颜色
                .confirmText("确认")//确认按钮文字
                .confirmTextSize(16)//确认按钮文字大小
                .cancelTextColor("#F56D23")//取消按钮文字颜色
                .cancelText("取消")//取消按钮文字
                .cancelTextSize(16)//取消按钮文字大小
                .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)//显示类，只显示省份一级，显示省市两级还是显示省市区三级
                .showBackground(false)//是否显示半透明背景
                .visibleItemsCount(5)//显示item的数量
                .province("浙江省")//默认显示的省份
                .city("杭州市")//默认显示省份下面的城市
                .district("滨江区")//默认显示省市下面的区县数据
                .provinceCyclic(false)//省份滚轮是否可以循环滚动
                .cityCyclic(false)//城市滚轮是否可以循环滚动
                .districtCyclic(false)//区县滚轮是否循环滚动
                .build();//自定义item布局里面的textViewid

        mPicker.setConfig(cityConfig);
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                proviceCode = province.getId();
                cityCode = city.getId();

                areaCode = district.getId();


            cityName=    city.getName();
            /*    if (city.getName().contains("杭州市")) {
                    mTvArea.setText(province.getName() + " " + city.getName() + " " + district.getName());
                    mTvArea.setTextColor(Color.parseColor("#232131"));
                } else {
                    AppHelper.showMsg(mContext, "目前只支持杭州市区域");
                    mPicker.hide();
                }*/
                mTvArea.setText(province.getName() + " " + city.getName() + " " + district.getName());
                mTvArea.setTextColor(Color.parseColor("#232131"));
                mPicker.hide();
                if (StringHelper.notEmptyAndNull(proviceCode)
                        && StringHelper.notEmptyAndNull(cityCode)
                        && StringHelper.notEmptyAndNull(areaCode)
                        && StringHelper.notEmptyAndNull(mEditName.getText().toString())
                        && StringHelper.notEmptyAndNull(mEditPhone.getText().toString())
                        && StringHelper.notEmptyAndNull(keyWorldsView.getText().toString())) {
                    mBtnConfirm.setEnabled(true);
                    mBtnConfirm.setTextColor(getResources().getColor(R.color.app_color_white));
                } else {
                    mBtnConfirm.setEnabled(false);
                    mBtnConfirm.setTextColor(getResources().getColor(R.color.app_btn_unable));
                }


            }

            @Override
            public void onCancel() {

            }
        });
        mPicker.showCityPicker();
    }

    //此方法只是关闭软键盘
    private void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    private void showBackDialog() {
        final AlertDialog alertDialog = new AlertDialog.Builder(mContext, R.style.CommonDialogStyle).create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_edit_address_back);
        TextView mTvCancel = (TextView) window.findViewById(R.id.tv_back_address_dialog_cancel);
        TextView mTvConfirm = (TextView) window.findViewById(R.id.tv_back_address_dialog_confirm);
        mTvCancel.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                alertDialog.dismiss();
            }
        });
        mTvConfirm.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                alertDialog.dismiss();
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //这里要判断,如果用户对文案进行了操作,但是要退出,需要弹框提示
            //如果没有对文案进行过操作,直接退出
            String nowDefault;
            if (mCbDefault.isChecked()) {
                nowDefault = "true";
            } else {
                nowDefault = "false";
            }
            if (StringHelper.notEmptyAndNull(mType)) {
                if (mType.equals("add")) {
                    finish();
                } else if (mType.equals("edit")) {
                    if (mUserName.equals(mEditName.getText().toString())
                            && mUserPhone.equals(mEditPhone.getText().toString())
                            && mArea.equals(mTvArea.getText().toString())
                            && mAddress.equals(keyWorldsView.getText().toString())
                            && (mDefault.equals(nowDefault))) {
                        //数据没有动过,直接退出
                        finish();
                    } else {
                        //数据变更过
                        showBackDialog();
                    }
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        mSuggestionSearch.destroy();
        super.onDestroy();
    }

    /**
     * 响应城市内搜索按钮点击事件
     *
     * @param v 检索Button
     */
    public void searchButtonProcess(View v) {
        // searchType = 1;

        // String citystr = editCity.getText().toString();
        String keystr = keyWorldsView.getText().toString();

      /*  mSuggestionSearch.searchInCity((new PoiCitySearchOption())
                .city("杭州市")
                .keyword(keystr)
                .pageNum(loadIndex)
                .scope(1));*/

        /**
         * 在您的项目中，keyword为随您的输入变化的值
         */
        mSuggestionSearch.requestSuggestion(new SuggestionSearchOption()
                .city("杭州市")

                .keyword(keystr)
                .citylimit(true)
        );

    }


    /**
     * 获取在线建议搜索结果，得到requestSuggestion返回的搜索结果
     *
     * @param res Sug检索结果
     */
    @Override
    public void onGetSuggestionResult(SuggestionResult res) {
        if (res == null || res.getAllSuggestions() == null) {
            //permission_unfinished
            return;
        }

        List<String> suggest = new ArrayList<>();

        for (SuggestionResult.SuggestionInfo info : res.getAllSuggestions()) {
            if (info.key != null) {
                suggest.add(info.key);
            }
        }
        ry_suggest.setVisibility(View.VISIBLE);
        tv_target.setVisibility(View.VISIBLE);
        //adressAdapter=new SuggestAdressAdapter(R.layout.suggest_address,suggest);
        adressAdapter = new SuggestAdressAdapter(suggest, mContext, new SuggestAdressAdapter.onClick() {
            @Override
            public void setLocation(int pos) {
                if (suggest.get(pos) != null) {
                    keyWorldsView.setText(suggest.get(pos));
                }
            }
        });
        ry_suggest.setLayoutManager(new LinearLayoutManager(mContext));
        ry_suggest.setAdapter(adressAdapter);
        adressAdapter.notifyDataSetChanged();

     /*   sugAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,
                suggest);
        keyWorldsView.setAdapter(sugAdapter);
        sugAdapter.notifyDataSetChanged();*/
    }


    protected void setTranslucentStatus() {
        // 5.0以上系统状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
