package com.puyue.www.qiaoge.activity.mine;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.mine.SubAccountAdapter;
import com.puyue.www.qiaoge.api.mine.login.SendCodeAPI;
import com.puyue.www.qiaoge.api.mine.subaccount.SubAccountAddAPI;
import com.puyue.www.qiaoge.api.mine.subaccount.SubAccountDeleteAPI;
import com.puyue.www.qiaoge.api.mine.subaccount.SubAccountDisableAPI;
import com.puyue.www.qiaoge.api.mine.subaccount.SubAccountEnableAPI;
import com.puyue.www.qiaoge.api.mine.subaccount.SubAccountListAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.event.BackEvent;
import com.puyue.www.qiaoge.event.GoToMineEvent;
import com.puyue.www.qiaoge.event.MessageEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.NetWorkHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.SubAccountModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
 * Created by Administrator on 2018/4/8.
 */

public class SubAccountActivity extends BaseSwipeActivity implements View.OnClickListener {

    private ImageView mIvBack;
    private RecyclerView mRvSubAccount;
    private Button mBtnAdd;
    ImageView iv_no_data;
    private List<SubAccountModel.DataBean> mList = new ArrayList<>();
    private SubAccountAdapter mAdapterSubAccount;
    private PtrClassicFrameLayout mPtr;
    private SubAccountModel mModelSubAccount;
    private BaseModel mModelDeleteSubAccount;
    private boolean isSendingCode = false;
    private BaseModel mModelSendCode;
    private CountDownTimer countDownTimer;
    ImageView iv_message;
    TextView tv_number;
    String message;
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {

        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_sub_account);
    }

    @Override
    public void findViewById() {
        mIvBack = (ImageView) findViewById(R.id.iv_sub_account_back);
        iv_no_data = findViewById(R.id.iv_no_data);
        mRvSubAccount = (RecyclerView) findViewById(R.id.rv_sub_account);//有子账号的显示
        mBtnAdd = (Button) findViewById(R.id.btn_sub_account_add);//增加子账号
        mPtr = (PtrClassicFrameLayout) findViewById(R.id.ptr_sub_account);
        iv_message = (ImageView) findViewById(R.id.iv_message);
        tv_number = (TextView) findViewById(R.id.tv_number);
        iv_message.setOnClickListener(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessageEvent(BackEvent backEvent) {
        requestSubAccountList();
    }
    @Override
    public void setViewData() {
        EventBus.getDefault().register(this);
        mPtr.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                requestSubAccountList();
            }
        });
        mAdapterSubAccount = new SubAccountAdapter(mContext, mList);
        mAdapterSubAccount.setOnItemClickListener(new SubAccountAdapter.OnEventClickListener() {
            @Override
            public void onEventClick(View view, int position, String flag) {
                //以下这四个操作,操作完成之后都需要刷新列表
                //现在需要在进行操作之前弹框让用户确定进行操作
                //禁用,删除,启用
                showEditSubAccountDialog(position, flag);
            }

            @Override
            public void onEventLongClick(View view, int position, String flag) {

            }
        });
        mRvSubAccount.setLayoutManager(new LinearLayoutManager(mContext));
        mRvSubAccount.setAdapter(mAdapterSubAccount);
        requestSubAccountList();

        message = getIntent().getStringExtra("message");

        if(message.equals("0")) {
            tv_number.setVisibility(View.GONE);
        }else {
            tv_number.setVisibility(View.VISIBLE);
            tv_number.setText(message+"");
        }
    }

    private void showEditSubAccountDialog(final int position, final String flag) {
        final AlertDialog alertDialog = new AlertDialog.Builder(mContext, R.style.CommonDialogStyle).create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_sub_account);
        TextView mTvText = (TextView) window.findViewById(R.id.tv_dialog_sub_account);
        TextView mTvClose = (TextView) window.findViewById(R.id.tv_sub_account_close);
        TextView mTvConfirm = (TextView) window.findViewById(R.id.tv_sub_account_confirm);
        if (flag.equals("disable")) {
            mTvText.setText("确定禁用该子账号吗?");
        } else if (flag.equals("delete")) {
            //删除该子账号
            mTvText.setText("确定删除该子账号吗?");
        } else if (flag.equals("enable")) {
            //恢复被禁用的子账号
            mTvText.setText("确定启用该子账号吗?");
        }
        mTvClose.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                alertDialog.dismiss();
            }
        });
        mTvConfirm.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                    //删除该子账号
                Log.d("woshidajiashudd....",mModelSubAccount.data.get(position).subId+"");
                requestDeleteSubAccount(mModelSubAccount.data.get(position).subId+"");
                alertDialog.dismiss();
            }
        });
    }


    /**
     * 删除子账户
     * @param subId
     */
    private void requestDeleteSubAccount(String subId) {
        SubAccountDeleteAPI.requestDeleteSubAccount(mContext, subId)
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
                        mModelDeleteSubAccount = baseModel;
                        if (mModelDeleteSubAccount.success) {
                            //删除某个账号成功
                            AppHelper.showMsg(mContext, "删除成功");
                            mPtr.autoRefresh();
                        } else {
                            AppHelper.showMsg(mContext, mModelDeleteSubAccount.message);
                        }
                    }
                });
    }

    /**
     * 子账户列表
     */
    private void requestSubAccountList() {
        SubAccountListAPI.requestSubAccountList(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SubAccountModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SubAccountModel subAccountModel) {
                        mPtr.refreshComplete();
                        mModelSubAccount = subAccountModel;
                        if (mModelSubAccount.success) {
                            updateSubAccountList();
                        } else {
                            AppHelper.showMsg(mContext, mModelSubAccount.message);
                        }
                    }
                });
    }

    private void updateSubAccountList() {
        if (mModelSubAccount.data.size() > 0 && mModelSubAccount.data != null) {
            iv_no_data.setVisibility(View.GONE);
            mRvSubAccount.setVisibility(View.VISIBLE);
            mPtr.setVisibility(View.VISIBLE);
            mList.clear();
            mList.addAll(mModelSubAccount.data);
            mAdapterSubAccount.notifyDataSetChanged();
        } else {
            iv_no_data.setVisibility(View.VISIBLE);
            mRvSubAccount.setVisibility(View.GONE);
            mPtr.setVisibility(View.GONE);
        }
    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                finish();
            }
        });
        mBtnAdd.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
//                showAddAccountDialog();
                Intent intent = new Intent(mContext,AddSubAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_message:
                Intent intent = new Intent(mContext,SubAccountListActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void message(MessageEvent messageEvent) {
        tv_number.setVisibility(View.GONE);
    }
}
