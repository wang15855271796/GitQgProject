package com.puyue.www.qiaoge.activity.mine.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.mine.AddressAdapter;
import com.puyue.www.qiaoge.api.home.CityChangeAPI;
import com.puyue.www.qiaoge.api.mine.address.AddressListAPI;
import com.puyue.www.qiaoge.api.mine.address.DefaultAddressAPI;
import com.puyue.www.qiaoge.api.mine.address.DeleteAddressAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.event.AddressEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.IsShowModel;
import com.puyue.www.qiaoge.model.mine.address.AddressModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/12.
 */

public class AddressListActivity extends BaseSwipeActivity {

    private static final String TAG = AddressListActivity.class.getSimpleName();
    private ImageView mIvBack;
    private PtrClassicFrameLayout mPtr;
    private RecyclerView mRv;
    private ImageView mIvNoData;
    private Button mBtnAdd;

    private List<AddressModel.DataBean> mListData = new ArrayList<>();
    private AddressAdapter mAdapterAddress;
    private AddressModel mModelAddress;
    private BaseModel mModelDeleteAddress;
    private BaseModel mModelEditDefaultAddress;

    private int defaultId = -1;
    private String orderId;


    private boolean isChecked = false;
    private boolean isChanged = true;

    private String userAddress;
    private String areaName;
    private String cityName;
    private String mineToAddress;


    @Override
    public boolean handleExtra(Bundle savedInstanceState) {


        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_address_list);
    }

    @Override
    public void findViewById() {
        mIvBack = (ImageView) findViewById(R.id.iv_address_list_back);
        mPtr = (PtrClassicFrameLayout) findViewById(R.id.ptr_address_list);
        mRv = (RecyclerView) findViewById(R.id.rv_address_list);
        mIvNoData = (ImageView) findViewById(R.id.iv_address_list_no_data);
        mBtnAdd = (Button) findViewById(R.id.btn_address_list_add);
    }

    private String changeAddress;

    @Override
    public void setViewData() {
        orderId = getIntent().getStringExtra("orderId");
        userAddress = getIntent().getStringExtra("UseAddress");
        mineToAddress = getIntent().getStringExtra("mineAddress");

        mPtr.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                requestAddressList();
            }
        });

        mAdapterAddress = new AddressAdapter(R.layout.item_address, mListData);


        mAdapterAddress.setOnItemChangeClickListener(new AddressAdapter.OnEventClickListener() {

            @Override
            public void onEventClick(View view, int position, String flag) {
                if (flag.equals("default")) {
                    //这里只是表面上地显示成这个地址为默认地址,要退出这个界面的时候调接口告知后台哪个变成默认地址了
                    for (int i = 0; i < mListData.size(); i++) {
                        if (i == position) {
                            if (mListData.get(i).isDefault == 1) {
                                //原来就是默认地址,这里点击没有效果,原来也设置了原来是默认地址这里就没有效果
                                //点击原来的默认地址,不会有操作,跳出界面的时候也不会有调接口操作

                            } else if (mListData.get(i).isDefault == 0) {
                                //原来不是默认地址,可以点击
                                //一旦点击,这个即变成默认地址了
                                //关键是,不能让用户点击一次就调一次接口重新刷新列表,需要在用户准备跳出这个界面的时候调接口
                                mListData.get(i).isDefault = 1;
                                //这里代表着切换了默认地址
                                defaultId = mListData.get(i).id;
                                changeAddress = mListData.get(i).provinceName + mListData.get(i).cityName + mListData.get(i).areaName + mListData.get(i).detailAddress;
                                areaName = mListData.get(position).getAreaName();
                                cityName = mListData.get(position).getCityName();
                            }
                        } else {
                            mListData.get(i).isDefault = 0;
                        }
                    }
                    mAdapterAddress.notifyDataSetChanged();
                } else if (flag.equals("delete")) {
                    showDeleteDialog(position);
                } else if (flag.equals("edit")) {
//                    startActivityForResult(EditAddressActivity.getIntent(mContext, EditAddressActivity.class, "edit", mListData.get(position).userName, mListData.get(position).contactPhone, mListData.get(position).shopName, (mListData.get(position).provinceName + " " + mListData.get(position).cityName + " " + mListData.get(position).areaName), mListData.get(position).detailAddress, "true", String.valueOf(mListData.get(position).id), mListData.get(position).provinceCode, mListData.get(position).cityCode, mListData.get(position).areaCode, orderId), 22);
//                    for (int i = 0; i < mListData.size(); i++) {
//                        if (i == position) {
//                            if (mListData.get(i).isDefault == 1) {
//                                //原来就是默认地址,这里点击没有效果,原来也设置了原来是默认地址这里就没有效果
//                                defaultId = mListData.get(i).id;
//                                requestEditDefaultAddress(mListData.get(i).id, orderId);
//                                //点击原来的默认地址,不会有操作,跳出界面的时候也不会有调接口操作
//
//                            } else if (mListData.get(i).isDefault == 0) {
//                                //原来不是默认地址,可以点击
//                                //一旦点击,这个即变成默认地址了
//                                //关键是,不能让用户点击一次就调一次接口重新刷新列表,需要在用户准备跳出这个界面的时候调接口
//                                mListData.get(i).isDefault = 1;
//                                //这里代表着切换了默认地址
//                                defaultId = mListData.get(i).id;
//                            }
//                        } else {
//                            mListData.get(i).isDefault = 0;
//                        }
//                    }
//                    mAdapterAddress.notifyDataSetChanged();
//                    if (defaultId != -1) {
//                        //说明修改过默认地址,在退出界面的时候请求接口来
//                        requestEditDefaultAddress(defaultId, orderId);
//                    }
//                    //跳转到编辑界面使用startActivityForResult,在完成操作之后回到地址列表的界面,重新请求一次地址列表的数据
                    if (mListData.get(position).isDefault == 1) {
                        //原本是默认地址
                        startActivityForResult(EditAddressActivity.getIntent(mContext, EditAddressActivity.class, "edit", mListData.get(position).userName, mListData.get(position).contactPhone, mListData.get(position).shopName, (mListData.get(position).provinceName + " " + mListData.get(position).cityName + " " + mListData.get(position).areaName), mListData.get(position).detailAddress, "true", String.valueOf(mListData.get(position).id), mListData.get(position).provinceCode, mListData.get(position).cityCode, mListData.get(position).areaCode, orderId), 22);
                    } else if (mListData.get(position).isDefault == 0) {
                        //原本不是默认地址
                        startActivityForResult(EditAddressActivity.getIntent(mContext, EditAddressActivity.class, "edit", mListData.get(position).userName, mListData.get(position).contactPhone, mListData.get(position).shopName, (mListData.get(position).provinceName + " " + mListData.get(position).cityName + " " + mListData.get(position).areaName), mListData.get(position).detailAddress, "false", String.valueOf(mListData.get(position).id), mListData.get(position).provinceCode, mListData.get(position).cityCode, mListData.get(position).areaCode, orderId), 22);
                    }
                } else if (flag.equals("order_address")) {
                    //从确定订单来的这个界面,选择一个地址item,回到上个界面,上传地址id和订单id,重新刷新数据即可获取到新的地址
                /*    Intent intent = new Intent();
                    intent.putExtra("address_id", mListData.get(position).id);
                    AddressListActivity.this.setResult(33, intent);*/
                }
            }

            @Override
            public void onEventLongClick(View view, int position, String flag) {
                changeAddress = mListData.get(position).provinceName + mListData.get(position).cityName + mListData.get(position).areaName + mListData.get(position).detailAddress;
                if (mineToAddress.equals("mineAddress")) {

                } else {
                    if (userAddress.equals(changeAddress)) {
                        finish();
                    } else {
                        defaultId = mListData.get(position).id;
                        //说明修改过默认地址,在退出界面的时候请求接口来
                        requestEditDefaultAddress(defaultId, orderId);
                        Intent intent = new Intent();
                        intent.putExtra("orderId", orderId);
                        AddressListActivity.this.setResult(39, intent);

                        finish();
                    }
                }

            }
        });


        mRv.setLayoutManager(new LinearLayoutManager(mContext));
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(-1)) {
                    mPtr.setEnabled(false);
                } else {
                    mPtr.setEnabled(true);
                }
            }
        });
        mRv.setAdapter(mAdapterAddress);
        requestAddressList();
    }

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
                        mPtr.refreshComplete();
                        logoutAndToHome(mContext, addressModel.code);
                        mModelAddress = addressModel;
                        if (mModelAddress.success) {
                            updateAddressList();
//                            EventBus.getDefault().post(new AddressEvent());
                        } else {
                            AppHelper.showMsg(mContext, mModelAddress.message);
                        }
                    }
                });
    }

    private void updateAddressList() {
        if (mModelAddress.data.size() > 0 && mModelAddress.data != null) {
            mListData.clear();
            mListData.addAll(mModelAddress.data);
            mAdapterAddress.notifyDataSetChanged();
            mRv.setVisibility(View.VISIBLE);
            mIvNoData.setVisibility(View.GONE);
        } else {
            mRv.setVisibility(View.GONE);
            mIvNoData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                if (mineToAddress.equals("mineAddress")) {
                    if (defaultId != -1) {


                            //说明修改过默认地址,在退出界面的时候请求接口来
                            requestEditDefaultAddress(defaultId, orderId);

                    }
                    finish();
                } else {
                    if (!isChanged) {
                        defaultId = 1;
                        //说明修改过默认地址,在退出界面的时候请求接口来
                        Intent intent = new Intent();
                        intent.putExtra("orderId", orderId);
                        AddressListActivity.this.setResult(39, intent);
                        defaultId = -1;
                        finish();

                    }
                    if (defaultId != -1) {

                        if (userAddress.equals(changeAddress)) {

                        } else {
                            //说明修改过默认地址,在退出界面的时候请求接口来
                            requestEditDefaultAddress(defaultId, orderId);
                            Intent intent = new Intent();
                            intent.putExtra("orderId", orderId);
                            AddressListActivity.this.setResult(39, intent);
                        }

                    }


                    finish();
                }


            }
        });
        mBtnAdd.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {

                if (defaultId != -1) {
                    //说明修改过默认地址,在退出界面的时候请求接口来
                    requestEditDefaultAddress(defaultId, orderId);

                }
                //跳转到编辑界面使用startActivityForResult,在完成操作之后回到地址列表的界面,重新请求一次地址列表的数据
                    startActivityForResult(EditAddressActivity.getIntent(mContext, EditAddressActivity.class, "add", "", "", "", "", "", "false", "", "", "", "", orderId), 11);

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (mineToAddress.equals("mineAddress")) {
                if (defaultId != -1) {
                    //说明修改过默认地址,在退出界面的时候请求接口来
                    requestEditDefaultAddress(defaultId, orderId);

                }
                finish();
            } else {
                if (!isChanged) {
                    defaultId = 1;
                    //说明修改过默认地址,在退出界面的时候请求接口来
                    Intent intent = new Intent();
                    intent.putExtra("orderId", orderId);
                    AddressListActivity.this.setResult(39, intent);
                    defaultId = -1;
                    finish();

                }
                if (defaultId != -1) {

                    if (userAddress.equals(changeAddress)) {

                    } else {
                        //说明修改过默认地址,在退出界面的时候请求接口来
                        requestEditDefaultAddress(defaultId, orderId);
                        Intent intent = new Intent();
                        intent.putExtra("orderId", orderId);
                        AddressListActivity.this.setResult(39, intent);
                    }

                }


                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
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
                            EventBus.getDefault().post(new AddressEvent());
                            if(isShowModel.data!=null) {
                                SharedPreferencesUtil.saveString(mActivity,"priceType",isShowModel.getData().enjoyProduct);

                            }
                        }else {
                            AppHelper.showMsg(mActivity,isShowModel.getMessage());
                        }
                    }
                });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //重新请求一次地址列表数据
        if (resultCode == 11) {
            String type = data.getStringExtra("type");
            int defaultNum = data.getIntExtra("defaultNum", 0);
            if (type.equals("add")) {
                //添加地址界面退出,重新请求一次列表接口

                isChanged = false;

                Log.i("isChanged", "onActivityResult: " + isChanged);

                mPtr.autoRefresh();


            }
        } else if (resultCode == 22) {
            String type = data.getStringExtra("type");
            if (type.equals("edit")) {
                //修改地址界面退出,重新请求一次列表接口
                mPtr.autoRefresh();
            }
        }
    }

    private void showDeleteDialog(final int position) {
        final AlertDialog alertDialog = new AlertDialog.Builder(mContext, R.style.CommonDialogStyle).create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_delete_address);
        TextView mTvCancel = (TextView) window.findViewById(R.id.tv_delete_address_dialog_cancel);
        TextView mTvConfirm = (TextView) window.findViewById(R.id.tv_delete_address_dialog_confirm);
        mTvCancel.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                alertDialog.dismiss();
            }
        });
        mTvConfirm.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                requestDeleteAddress(position);
                alertDialog.dismiss();
            }
        });
    }

    private void requestDeleteAddress(int position) {
        DeleteAddressAPI.requestDeleteAddress(mContext, mListData.get(position).id)
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
                        mModelDeleteAddress = baseModel;
                        if (mModelDeleteAddress.success) {
                            AppHelper.showMsg(mContext, "删除地址成功");
                            //删除地址之后重新请求一次地址列表
                            mPtr.autoRefresh();
                            EventBus.getDefault().post(new AddressEvent());

                        } else {
                            AppHelper.showMsg(mContext, mModelDeleteAddress.message);
                        }
                    }
                });
    }

    private void requestEditDefaultAddress(int id, String orderId) {
        DefaultAddressAPI.requestEditDefaultAddress(mContext, id, orderId)
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
                        mModelEditDefaultAddress = baseModel;
                        Log.e(TAG, "onNext: " + baseModel.success + baseModel.message + baseModel.code);
                        if (mModelEditDefaultAddress.success) {
                            UserInfoHelper.saveChangeFlag(mActivity,"0");
                            UserInfoHelper.saveCity(mActivity,cityName);
                            UserInfoHelper.saveAreaName(mActivity,areaName);
                            isShow();

                        } else {
                            AppHelper.showMsg(mContext, mModelEditDefaultAddress.message);
                        }

                    }
                });
    }
}
