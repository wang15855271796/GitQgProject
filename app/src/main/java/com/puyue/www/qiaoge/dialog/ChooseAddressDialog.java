package com.puyue.www.qiaoge.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.HomeActivity;
import com.puyue.www.qiaoge.activity.home.ChangeCityActivity;
import com.puyue.www.qiaoge.activity.mine.account.AddressAdapters;
import com.puyue.www.qiaoge.activity.mine.account.AddressListActivity;
import com.puyue.www.qiaoge.activity.mine.account.AddressListsActivity;
import com.puyue.www.qiaoge.activity.mine.account.EditAddressActivity;
import com.puyue.www.qiaoge.activity.mine.account.EditAndAddActivity;
import com.puyue.www.qiaoge.adapter.ChooseAddressAdapter;
import com.puyue.www.qiaoge.adapter.ChooseAddresssAdapter;
import com.puyue.www.qiaoge.adapter.ChooseAddressssAdapter;
import com.puyue.www.qiaoge.api.home.IndexHomeAPI;
import com.puyue.www.qiaoge.api.mine.address.AddressListAPI;
import com.puyue.www.qiaoge.api.mine.address.DefaultAddressAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.event.AddressEvent;
import com.puyue.www.qiaoge.event.GoToMarketEvent;
import com.puyue.www.qiaoge.event.TurnReceiveModel;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.model.mine.address.AddressModel;
import com.puyue.www.qiaoge.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2020/4/23
 */
public class ChooseAddressDialog extends Dialog {

    Context mContext;
    ImageView iv_close;
    View view;
    AddressModel mModelAddress;
    private int defaultId = -1;

    private List<AddressModel.DataBean> mListData = new ArrayList<>();
    private ChooseAddresssAdapter addressAdapters;
    private ChooseAddressssAdapter addressAdapterss;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView1;
    RelativeLayout rl_add_address;
    String orderId;
    TextView tv1;
    private AddressModel.DataBean dataBean;
    public String changeAddress;
    public ChooseAddressDialog(@NonNull Context context, String orderId) {
        super(context, R.style.dialog);
        mContext = context;
        this.orderId = orderId;
        initView();

    }

    private void initView() {
        view = View.inflate(mContext, R.layout.dialog_choose_addresss, null);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setContentView(view);
        recyclerView = view.findViewById(R.id.recyclerView);
        tv1 = view.findViewById(R.id.tv1);
        rl_add_address = view.findViewById(R.id.rl_add_address);
        recyclerView1 = view.findViewById(R.id.recyclerView1);
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = Utils.getScreenWidth(mContext);
        getWindow().setAttributes(attributes);
        getAddress();

        rl_add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = AddressListActivity.getIntent(mContext, AddressListsActivity.class);
                intent1.putExtra("mineAddress", "mineAddress");
                mContext.startActivity(intent1);

                dismiss();
            }
        });
    }
    List<AddressModel.DataBean> data0 = new ArrayList<>();
    List<AddressModel.DataBean> data1 = new ArrayList<>();
    private void getAddress() {
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
                            if (mModelAddress.data.size() > 0 && mModelAddress.data != null) {
                                mListData.clear();
                                mListData.addAll(mModelAddress.data);

                                for (int i = 0; i <mListData.size() ; i++) {
                                    if(mListData.get(i).sendType==1) {
                                        data0.add(mListData.get(i));
                                    }else {
                                        data1.add(mListData.get(i));
                                    }
                                }
                                addressAdapters = new ChooseAddresssAdapter(R.layout.item_dialog_address,data0,orderId, new ChooseAddresssAdapter.Onclick() {
                                    @Override
                                    public void jump(int position) {
                                        dataBean = data0.get(position);
                                        mContext.startActivity(EditAndAddActivity.getIntent(mContext, EditAndAddActivity.class, "edit", dataBean.userName, dataBean.contactPhone, dataBean.shopName, (dataBean.provinceName + " " + dataBean.cityName + " " + dataBean.areaName), dataBean.detailAddress, "true", String.valueOf(dataBean.id), dataBean.provinceCode,
                                                dataBean.cityCode,dataBean.areaCode, orderId));
                                        dismiss();
                                    }
                                });

                                addressAdapters.setOnItemChangeClickListener(new ChooseAddresssAdapter.OnEventClickListener() {
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
                                                        requestEditDefaultAddress(defaultId, orderId);
                                                        dismiss();
                                                    }
                                                } else {
                                                    mListData.get(i).isDefault = 0;
                                                }
                                            }
                                            addressAdapters.notifyDataSetChanged();
                                        }

                                    }

                                    @Override
                                    public void onEventLongClick(View view, int position, String flag) {

                                    }
                                });
                                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                                recyclerView.setAdapter(addressAdapters);
                                addressAdapters.notifyDataSetChanged();



                                addressAdapterss = new ChooseAddressssAdapter(R.layout.item_dialog_address,data1);
                                if(data1.size()==0) {
                                    tv1.setVisibility(View.GONE);
                                }else {
                                    tv1.setVisibility(View.VISIBLE);
                                }
                                recyclerView1.setLayoutManager(new LinearLayoutManager(mContext));
                                recyclerView1.setAdapter(addressAdapterss);

                                addressAdapterss.notifyDataSetChanged();

                                addressAdapterss.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                                        ChangeCityDialog changeCityDialog = new ChangeCityDialog(mContext) {
                                            @Override
                                            public void close() {
                                                Intent intent = new Intent(mContext,ChangeCityActivity.class);
                                                intent.putExtra("flag","1");
                                                mContext.startActivity(intent);
                                                ((Activity)mContext).finish();
                                                dismiss();
                                            }
                                        };
                                        changeCityDialog.show();

                                    }
                                });

                            }
                        } else {
                            AppHelper.showMsg(mContext, mModelAddress.message);
                        }
                    }
                });
    }

    /**
     * 设置默认地址
     * @param defaultId
     * @param orderId
     */
    private void requestEditDefaultAddress(int defaultId, String orderId) {
        DefaultAddressAPI.requestEditDefaultAddress(mContext, defaultId, orderId)
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
                            EventBus.getDefault().post(new AddressEvent());
                        } else {
                            AppHelper.showMsg(mContext, baseModel.message);
                        }
                    }
                });
    }

}
