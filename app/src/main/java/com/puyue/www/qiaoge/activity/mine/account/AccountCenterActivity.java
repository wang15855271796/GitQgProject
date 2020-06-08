package com.puyue.www.qiaoge.activity.mine.account;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.NewWebViewActivity;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.cart.CompetActivity;
import com.puyue.www.qiaoge.api.mine.AccountCenterAPI;
import com.puyue.www.qiaoge.api.mine.LogoutAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.DialogHelper;
import com.puyue.www.qiaoge.helper.NetWorkHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.AccountCenterModel;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/8.
 */

public class AccountCenterActivity extends BaseSwipeActivity {
    RelativeLayout rl_limit;
    private ImageView mIvBack;
    private RelativeLayout mRlPhone;
    private TextView mTvPhone;
    private RelativeLayout mRlLoginPassword;
    private RelativeLayout mRlPayPassword;
    private TextView mTvPayPassword;
    private RelativeLayout mRlAuthorization;
    private TextView mTvAuthorizationCode;
    private TextView mTvAuthorizationDate;
    private RelativeLayout mRlLogout;

    private String mUserCell;
    private String hasSetPayPwd;

    private AccountCenterModel mModelAccountCenter;
    private BaseModel mModelLogout;

    private RelativeLayout rl_account_secret;
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_account_center);
    }

    @Override
    public void findViewById() {
        mIvBack = (ImageView) findViewById(R.id.iv_account_back);
        mRlPhone = (RelativeLayout) findViewById(R.id.rl_account_phone);
        mTvPhone = (TextView) findViewById(R.id.tv_account_phone);
        mRlLoginPassword = (RelativeLayout) findViewById(R.id.rl_account_login_password);//修改登录密码
        mRlPayPassword = (RelativeLayout) findViewById(R.id.rl_account_pay_password);//支付密码
        mTvPayPassword = (TextView) findViewById(R.id.tv_account_pay_password);//是显示"修改支付密码"还是显示"设置支付密码"需要后台判断
        mRlAuthorization = (RelativeLayout) findViewById(R.id.rl_account_authorization);//授权码
        mTvAuthorizationCode = (TextView) findViewById(R.id.tv_account_authorization_code);
        mTvAuthorizationDate = (TextView) findViewById(R.id.tv_account_authorization_code_date);
        mRlLogout = (RelativeLayout) findViewById(R.id.rl_account_logout);//退出登录
        rl_account_secret = (RelativeLayout) findViewById(R.id.rl_account_secret);//隐私政策
        rl_limit =  (RelativeLayout) findViewById(R.id.rl_limit);
    }

    @Override
    public void setViewData() {

    }

    @Override
    public void setClickEvent() {
        String url= "https://shaokao.qoger.com/apph5/html/yszc.html";
        mIvBack.setOnClickListener(noDoubleClickListener);
        rl_limit.setOnClickListener(noDoubleClickListener);
        mRlLoginPassword.setOnClickListener(noDoubleClickListener);
        mRlPayPassword.setOnClickListener(noDoubleClickListener);
        mRlAuthorization.setOnClickListener(noDoubleClickListener);
        mRlLogout.setOnClickListener(noDoubleClickListener);
        rl_account_secret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,NewWebViewActivity.class);
                intent.putExtra("URL", url);
                intent.putExtra("TYPE", 1);
                intent.putExtra("name", "协议");
                startActivity(intent);
            }
        });
    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {

            if (view == mIvBack) {
                finish();
            } else if (view == mRlPhone) {
                startActivity(EditAccountInputPhoneActivity.getIntent(mContext, EditAccountInputPhoneActivity.class, "0", "account"));
            } else if (view == mRlLoginPassword) {
                startActivity(EditPasswordInputCodeActivity.getIntent(mContext, EditPasswordInputCodeActivity.class, "0", mUserCell, "login","",0,0));
            } else if (view == mRlPayPassword) {
                if (StringHelper.notEmptyAndNull(mUserCell) && StringHelper.notEmptyAndNull(hasSetPayPwd)) {
                    if (hasSetPayPwd.equals("1")) {
                        UserInfoHelper.saveForgetPas(mContext, "");
                        startActivity(EditPasswordInputCodeActivity.getIntent(mContext, EditPasswordInputCodeActivity.class, "1", mUserCell, "pay","",0,0));
                    } else if (hasSetPayPwd.equals("0")) {
                        startActivity(EditPasswordInputCodeActivity.getIntent(mContext, EditPasswordInputCodeActivity.class, "0", mUserCell, "pay","",0,0));
                    }
                } else {
                    AppHelper.showMsg(mContext, "手机号有误");
                }
            } else if (view == mRlAuthorization) {
            } else if (view == mRlLogout) {
                DialogHelper.showLogoutDialog(mContext, new NoDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View view) {
                        requestLogout();
                    }
                });
            }else if(view == rl_limit) {
                Intent intent = new Intent(mActivity,CompetActivity.class);
                intent.putExtra("phone",mModelAccountCenter.data.phone);

                startActivity(intent);
            }
        }
    };

    private void requestLogout() {
        LogoutAPI.requestLogout(mContext)
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
                        mModelLogout = baseModel;
                        if (mModelLogout.success) {
                            logoutAndToHome(mContext, -10000);
                        } else {
                            AppHelper.showMsg(mContext, mModelLogout.message);
                        }
                    }
                });
        DialogHelper.dismissLogoutDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //请求后台数据,实时更新用户密码信息
        requestUserInfo();
    }

    private void requestUserInfo() {
        if (!NetWorkHelper.isNetworkAvailable(mContext)) {
            AppHelper.showMsg(mContext, "网络不给力!");
        } else {
            AccountCenterAPI.requestAccountCenter(mContext)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<AccountCenterModel>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(AccountCenterModel accountCenterModel) {
                            logoutAndToHome(mContext, accountCenterModel.code);
                            mModelAccountCenter = accountCenterModel;
                            if (mModelAccountCenter.success) {
                                updateAccountCenter();
                            } else {
                                AppHelper.showMsg(mContext, mModelAccountCenter.message);
                            }
                        }
                    });
        }
    }

    private void updateAccountCenter() {
        mUserCell = mModelAccountCenter.data.phone;
        mTvPhone.setText(mModelAccountCenter.data.phone);
        mTvAuthorizationCode.setText(mModelAccountCenter.data.invitationCode);
        if (mModelAccountCenter.data.hasSetPayPwd) {
            //已经设置过支付密码
            mTvPayPassword.setText("修改支付密码");
            hasSetPayPwd = "1";
        } else {
            //没有设置过支付密码
            mTvPayPassword.setText("设置支付密码");
            hasSetPayPwd = "0";
        }
       /* if (mModelAccountCenter.data.mainAccount) {
            //主账号,可以添加子账号
            mRlSubAccount.setVisibility(View.VISIBLE);
        } else {
            //这个账号就是子账号,子账号不能再添加子账号
            mRlSubAccount.setVisibility(View.GONE);
        }*/
    }
}
