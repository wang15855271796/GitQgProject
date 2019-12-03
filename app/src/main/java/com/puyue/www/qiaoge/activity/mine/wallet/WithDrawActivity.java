package com.puyue.www.qiaoge.activity.mine.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.mine.ApplyWithDrawAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.BigDecimalUtils;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.order.ApplyWithDrawModel;

import java.math.BigDecimal;
import java.math.RoundingMode;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/9.
 */

public class WithDrawActivity extends BaseSwipeActivity {
    private ImageView mIvBack;
    private EditText mEtAmount;
    private TextView mTvActualAmount;
    private TextView mTvPoundage;
    private Button mBtnSure;
    private EditText mEtName;
    private EditText mEtPhone;
    private LinearLayout mLlBank;
    private View mLine;
    private EditText mEtBank;
    private EditText mEtAccount;
    private RadioGroup mRgType;
    private RadioButton mRbAlipay;
    private RadioButton mRbBank;

    private double amount;
    private double actualAmount;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_with_draw);
    }

    @Override
    public void findViewById() {
        mIvBack = FVHelper.fv(this, R.id.iv_with_draw_back);
        mEtAmount = FVHelper.fv(this, R.id.et_activity_amount);
        mTvActualAmount = FVHelper.fv(this, R.id.tv_activity_actualAmount);
        mTvPoundage = FVHelper.fv(this, R.id.tv_activity_poundage);
        mBtnSure = FVHelper.fv(this, R.id.btn_activity_sure);
        mEtName = FVHelper.fv(this, R.id.et_activity_name);
        mEtPhone = FVHelper.fv(this, R.id.et_activity_phone);
        mLlBank = FVHelper.fv(this, R.id.ll_activity_bank);
        mLine = FVHelper.fv(this, R.id.line_activity_bank);
        mEtBank = FVHelper.fv(this, R.id.et_activity_bank);
        mEtAccount = FVHelper.fv(this, R.id.et_activity_account);
        mRgType = FVHelper.fv(this, R.id.rg_activity_type);
        mRbAlipay = FVHelper.fv(this, R.id.rb_activity_alipay);
        mRbBank = FVHelper.fv(this, R.id.rb_activity_bank);
    }

    @Override
    public void setViewData() {

    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(noDoubleClickListener);
        mEtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (mEtAmount.getText().toString().isEmpty()) {
                    mTvActualAmount.setText("");
                    mTvPoundage.setText("￥0.0");
                } else {
                    amount = Double.parseDouble(mEtAmount.getText().toString());
                    actualAmount = actual(mEtAmount.getText().toString());
                    BigDecimal actualAmountBigDecimal = new BigDecimal(actualAmount);
                    mTvActualAmount.setText(actualAmountBigDecimal.setScale(2, RoundingMode.HALF_UP) + "");
                    mTvPoundage.setText("￥" + BigDecimalUtils.mul(mEtAmount.getText().toString(), "0.006", 2));

                }
            }
        });
        mRgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_activity_alipay:
                        //提现到支付宝
                        mLlBank.setVisibility(View.GONE);
                        mLine.setVisibility(View.GONE);
                        mEtAccount.setHint("请输入支付宝账号");
                        break;
                    case R.id.rb_activity_bank:
                        //提现到银行卡
                        mLlBank.setVisibility(View.VISIBLE);
                        mLine.setVisibility(View.VISIBLE);
                        mEtAccount.setHint("请输入银行卡号");
                        break;
                    default:
                        break;
                }
            }
        });
        mBtnSure.setOnClickListener(noDoubleClickListener);

    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            if (view == mIvBack) {
                finish();
            } else if (view == mBtnSure) {
                judgeInformationComplete();
            }
        }
    };

    /**
     * 判断用户填写信息是否齐全
     */
    private void judgeInformationComplete() {
        if (mRbBank.isChecked()) {
            //选中银行
            if (mEtAmount.getText().toString().isEmpty()) {
                 AppHelper.showMsg(mContext, "请输入提现金额");
                return;
            }
            if (mEtName.getText().toString().isEmpty()) {
                 AppHelper.showMsg(mContext, "请输入姓名");
                return;
            }
            if (mEtPhone.getText().toString().isEmpty()) {
                 AppHelper.showMsg(mContext, "请输入联系电话");
                return;
            }
            if (mEtBank.getText().toString().isEmpty()) {
                 AppHelper.showMsg(mContext, "请输入开户行");
                return;
            }
            if (mEtAccount.getText().toString().isEmpty()) {
                 AppHelper.showMsg(mContext, "请输入账号");
                return;
            }
            //withdrawType 2 支付宝，3微信，4 网银
            applyWithDraw(amount, (byte) 4, mEtName.getText().toString(), mEtPhone.getText().toString(), mEtBank.getText().toString(), mEtAccount.getText().toString(), actualAmount);
        } else if (mRbAlipay.isChecked()) {
            //选中支付宝
            if (mEtAmount.getText().toString().isEmpty()) {
                 AppHelper.showMsg(mContext, "请输入提现金额");
                return;
            }
            if (mEtName.getText().toString().isEmpty()) {
                 AppHelper.showMsg(mContext, "请输入姓名");
                return;
            }
            if (mEtPhone.getText().toString().isEmpty()) {
                 AppHelper.showMsg(mContext, "请输入联系电话");
                return;
            }
            if (mEtAccount.getText().toString().isEmpty()) {
                 AppHelper.showMsg(mContext, "请输入账号");
                return;
            }
            applyWithDraw(amount, (byte) 2, mEtName.getText().toString(), mEtPhone.getText().toString(), null, mEtAccount.getText().toString(), actualAmount);
        } else {
            //未选中,基本不存在
             AppHelper.showMsg(mContext, "请选择提现方式");
        }
    }

    /**
     * 计算实际到账金额
     */
    public static double actual(String amount) {
        BigDecimal p1 = new BigDecimal(amount);
        BigDecimal p2 = new BigDecimal("0.994");
        return p1.multiply(p2).doubleValue();
    }

    /**
     * 计算手续费
     */
    public static double poundage(String amount) {
        BigDecimal p1 = new BigDecimal(amount);
        BigDecimal p2 = new BigDecimal("0.006");
        return p1.multiply(p2).doubleValue();
    }

    /**
     * 提前申请接口
     */
    private void applyWithDraw(double amount, byte withdrawType, String name, String contactPhone, String openBankName, String account, double actualAmount) {
        ApplyWithDrawAPI.requestData(mContext, amount, withdrawType, name, contactPhone, openBankName, account, actualAmount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ApplyWithDrawModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ApplyWithDrawModel applyWithDrawModel) {
                        logoutAndToHome(mContext, applyWithDrawModel.code);
                        if (applyWithDrawModel.success) {
                            Intent intent = new Intent(mContext, WalletResultActivity.class);
                            intent.putExtra(AppConstant.PAGETYPE, "withDraw");
                            startActivity(intent);
                            finish();
                        } else {
                            AppHelper.showMsg(WithDrawActivity.this, applyWithDrawModel.message);
                        }

                    }
                });
    }

}
