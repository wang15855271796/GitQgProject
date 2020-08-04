package com.puyue.www.qiaoge.fragment.mine;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.NewWebViewActivity;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.SophixStubApplication;
import com.puyue.www.qiaoge.UnicornManager;
import com.puyue.www.qiaoge.activity.CommonH5Activity;
import com.puyue.www.qiaoge.activity.mine.FeedBackActivity;
import com.puyue.www.qiaoge.activity.mine.MessageCenterActivity;
import com.puyue.www.qiaoge.activity.mine.MyCollectionActivity;
import com.puyue.www.qiaoge.activity.mine.SubAccountActivity;
import com.puyue.www.qiaoge.activity.mine.SubAccountListActivity;
import com.puyue.www.qiaoge.activity.mine.account.AccountCenterActivity;
import com.puyue.www.qiaoge.activity.mine.account.AddressListActivity;
import com.puyue.www.qiaoge.activity.mine.coupons.MyCouponsActivity;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.activity.mine.login.LogoutsEvent;
import com.puyue.www.qiaoge.activity.mine.order.MyOrdersActivity;
import com.puyue.www.qiaoge.activity.mine.wallet.MinerIntegralActivity;
import com.puyue.www.qiaoge.activity.mine.wallet.MyWalletDetailActivity;
import com.puyue.www.qiaoge.activity.mine.wallet.MyWalletNewActivity;
import com.puyue.www.qiaoge.api.home.GetCustomerPhoneAPI;
import com.puyue.www.qiaoge.api.mine.AccountCenterAPI;
import com.puyue.www.qiaoge.api.mine.UpdateAPI;
import com.puyue.www.qiaoge.api.mine.order.MyOrderNumAPI;
import com.puyue.www.qiaoge.api.mine.subaccount.MineAccountAPI;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.AddressEvent;
import com.puyue.www.qiaoge.event.CouponEvent;
import com.puyue.www.qiaoge.event.GoToMarketEvent;
import com.puyue.www.qiaoge.event.GoToMineEvent;
import com.puyue.www.qiaoge.event.MessageEvent;
import com.puyue.www.qiaoge.fragment.cart.CartFragment;
import com.puyue.www.qiaoge.fragment.home.CityEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.NetWorkHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.User;
import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;
import com.puyue.www.qiaoge.model.mine.AccountCenterModel;
import com.puyue.www.qiaoge.model.mine.UpdateModel;
import com.puyue.www.qiaoge.model.mine.order.MineCenterModel;
import com.puyue.www.qiaoge.model.mine.order.MyOrderNumModel;
import com.puyue.www.qiaoge.view.SuperTextView;
import com.puyue.www.qiaoge.view.scrollview.MyRecyclerAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/28.
 */

public class MineFragment extends BaseFragment {

    private static final String TAG = MineFragment.class.getSimpleName();
    private ImageView mIvAvatar;
    private TextView mTvPhone;
    private RelativeLayout rl_return_order;
    private LinearLayout mLlPayment;
    private LinearLayout mLlEvaluate;
    private LinearLayout mLlDelivery;
    private LinearLayout mLlReturnGoods;
    private LinearLayout mLlReceived;
    private RelativeLayout mRlCollection;
    // private RelativeLayout mRlReturnRent;
    private RelativeLayout mRlContact;
    private RelativeLayout mRlFeedback;
    private RelativeLayout mRlVersion;
    private TextView mTvVersion;
    private TextView mViewVersionPoint;
    private RelativeLayout mRlMyOrders;
    private AccountCenterModel mModelAccountCenter;
    private String mUserCell;
    private int mStateCode;
    TextView tv_phone;
    private SuperTextView mViewWaitPaymentNum;
    private SuperTextView mViewWaitShipmentNum;
    private SuperTextView mViewWaitReceivingNum;
    private SuperTextView mViewWaitEvaluateNum;
    private SuperTextView mViewReturnNum;
    private SuperTextView mViewCollectionNum;

    // protected ImmersionBar mImmersionBar;

    private AlertDialog mDialog;
    private String mCustomerPhone;
    private SuperTextView mViewMessageNum;
    private UpdateModel mModelUpdate;
    private boolean update;
    private boolean forceUpdate;
    private String content;
    private String url;//更新所用的url
    private RelativeLayout couponsLayout;//优惠券
    private TextView couponsNum; // 优惠券数量
    private TextView textCouponsPoint;// 钱包优惠券
    private ImageView imageViewBanner;
    private RelativeLayout accountAddress;
    private RelativeLayout accountManagement;
    private String MyBannerUrl = "";
    private RelativeLayout mineIntegral;// 我的积分
    private RelativeLayout relativeLayoutVip; // 会员中心
    private TextView vipDesc; //会员中心角标
    private TextView couponsDesc;//优惠券
    private ImageView vipImage;  //vip icon
    private ImageView vipDay; // 会员满减
    private String urlVIP;
    private String vipDayUrlVIP;
    private String commissionUrl;


    private TextView tv_vip;
    private TextView tv_amount;
    private TextView tv_commission;//佣金
    private TextView tv_inviteAward;//佣金奖励
    private TextView tv_point;//积分
    private TextView tv_deductNum;//优惠券数量
    private TextView tv_expiredInfo;//优惠券到期通知
    private LinearLayout ll_expiredInfo;
    private TextView tv_order;//查看全部订单
    private ImageView iv_order;
    private ImageView iv_setting;//设置
    private LinearLayout ll_setting;//设置
    private TextView tv_use_deduct;//使用优惠券
    private ImageView iv_use_deduct;//使用优惠券
    RelativeLayout rl_zizhi;

    private int day;
    private String giftNo;
    private LinearLayout ll_amount;//余额
    private LinearLayout ll_inviteAward;//佣金
    private LinearLayout ll_account;//积分
    private LinearLayout ll_deduct;//优惠券
    private TextView tv_vip_more;//会员更多权益
    private LinearLayout iv_vip_more;//会员更多权益

    private boolean isChecked;

    private ImageView iv_message;


    private ViewPager mViewPager;
    private SparseArray<RecyclerView> mPageMap = new SparseArray<>();

    private List<MyOrderNumModel.DataBean> mListData = new ArrayList<>();


    private PagerAdapter mPagerAdapter = null;


    private MyOrderNumModel mModelMyOrderNum;

    private LinearLayout ll_self_sufficiency;
    private LinearLayout ll_deliver_order;
    TextView tv_number;

    public static MineFragment getInstance() {
        MineFragment fragment = new MineFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public int setLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initViews(View view) {
        Window window = getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void findViewById(View view) {

        EventBus.getDefault().register(this);
        rl_zizhi = (view.findViewById(R.id.rl_zizhi));

        mIvAvatar = (view.findViewById(R.id.iv_mine_avatar));//头像
        mineIntegral = (view.findViewById(R.id.mineIntegral));//积分
        mTvPhone = (view.findViewById(R.id.tv_mine_phone));
        rl_return_order = (view.findViewById(R.id.rl_return_order));
        tv_number = (view.findViewById(R.id.tv_number));
        mLlPayment = (view.findViewById(R.id.ll_mine_tips_payment));//待付款
        mLlEvaluate = (view.findViewById(R.id.ll_mine_tips_evaluate));//待评价

        mLlDelivery = (view.findViewById(R.id.ll_mine_tips_delivery));//待发货
        mLlReturnGoods = (view.findViewById(R.id.ll_mine_tips_return_goods));//退货
        mLlReceived = (view.findViewById(R.id.ll_mine_tips_received));//待收货

        mRlMyOrders = (view.findViewById(R.id.rl_mine_orders));//我的订单
        mRlCollection = (view.findViewById(R.id.rl_mine_collection));//我的收藏

        mRlContact = (view.findViewById(R.id.rl_mine_contact));//联系客服
        mRlFeedback = (view.findViewById(R.id.rl_mine_feedback));//意见反馈
        mRlVersion = (view.findViewById(R.id.rl_mine_version));//版本信息
        mTvVersion = (view.findViewById(R.id.tv_mine_version));
        mViewVersionPoint = (view.findViewById(R.id.view_mine_version_point));
        couponsLayout = (view.findViewById(R.id.couponsLayout));

        couponsNum = (view.findViewById(R.id.couponsNum));
        textCouponsPoint = (view.findViewById(R.id.textCouponsPoint));
        imageViewBanner = (view.findViewById(R.id.imageViewBanner));
        mViewCollectionNum = (view.findViewById(R.id.textCollectionMount));//我的收藏数量
        mViewWaitPaymentNum = (view.findViewById(R.id.view_mine_order_wait_pay));//待付款数量
        mViewWaitShipmentNum = (view.findViewById(R.id.view_mine_order_wait_shipments));//待发货数量
        mViewWaitReceivingNum = (view.findViewById(R.id.view_mine_order_wait_receiving));//待收货数量
        mViewWaitEvaluateNum = (view.findViewById(R.id.view_mine_order_wait_evaluate));//待评价数量
        mViewReturnNum = (view.findViewById(R.id.view_mine_order_return_sale));//退货数量
        //    mViewCollectionNum = ( view.findViewById(R.id.view_mine_collect_number));//收藏数量
        //mViewEquipmentNum = (view.findViewById(R.id.view_mine_equipment_number));//设备数量
        mViewMessageNum = (view.findViewById(R.id.view_mine_message_num));//消息数量
        accountAddress = (view.findViewById(R.id.accountAddress));//我的地址
        accountManagement = (view.findViewById(R.id.accountManagement));//子账号管理
        relativeLayoutVip = (view.findViewById(R.id.relativeLayoutVip)); // 会员中心
        vipDesc = (view.findViewById(R.id.vipDesc));// 会员中心角标
        couponsDesc = (view.findViewById(R.id.couponsDesc));
        //vipImage = (view.findViewById(R.id.vipImage));
        //   vipDay = (view.findViewById(R.id.vipDay));


        tv_vip = (view.findViewById(R.id.tv_vip));
        tv_amount = (view.findViewById(R.id.tv_amount));
        tv_commission = (view.findViewById(R.id.tv_commission));
        tv_inviteAward = (view.findViewById(R.id.tv_inviteAward));
        tv_point = (view.findViewById(R.id.tv_point));
        tv_deductNum = (view.findViewById(R.id.tv_deductNum));
        tv_expiredInfo = (view.findViewById(R.id.tv_expiredInfo));
        ll_expiredInfo = (view.findViewById(R.id.ll_expiredInfo));
        tv_order = (view.findViewById(R.id.tv_order));
        iv_order = (view.findViewById(R.id.iv_order));
        iv_setting = (view.findViewById(R.id.iv_setting));
        ll_setting = (view.findViewById(R.id.ll_setting));
        tv_use_deduct = (view.findViewById(R.id.tv_use_deduct));
        iv_use_deduct = (view.findViewById(R.id.iv_use_deduct));
        ll_amount = (view.findViewById(R.id.ll_amount));
        ll_inviteAward = (view.findViewById(R.id.ll_inviteAward));
        ll_account = (view.findViewById(R.id.ll_account));
        ll_deduct = (view.findViewById(R.id.ll_deduct));
        tv_vip_more = (view.findViewById(R.id.tv_vip_more));
        iv_vip_more = (view.findViewById(R.id.iv_vip_more));
        iv_message = (view.findViewById(R.id.iv_message));

        mViewPager = (view.findViewById(R.id.viewpager));
        ll_deliver_order = (view.findViewById(R.id.ll_deliver_order));
        ll_self_sufficiency = (view.findViewById(R.id.ll_self_sufficiency));

    }

    @Override
    public void setViewData() {
        mViewVersionPoint.setVisibility(View.GONE);
        mTvVersion.setText(getString(R.string.textVersion) + AppHelper.getVersion(getContext()));

        if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(getContext()))) {
            //有userId,显示userId,
            requestUserInfo();
            requestOrderNum();
//            requestOrderNumTwo();
            //getPagerAdapter();

        } else {
            //没有就显示"请登录"
            mTvPhone.setText("请登录");
            mTvPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(LoginActivity.getIntent(getContext(), LoginActivity.class));
                }
            });
            //没有userId,就将所有的角标清空
            mViewWaitPaymentNum.setVisibility(View.GONE);
            mViewWaitShipmentNum.setVisibility(View.GONE);
            mViewWaitReceivingNum.setVisibility(View.GONE);
            mViewWaitEvaluateNum.setVisibility(View.GONE);
            mViewReturnNum.setVisibility(View.GONE);
            mViewCollectionNum.setVisibility(View.GONE);
            if (mViewMessageNum != null) {
                mViewMessageNum.setVisibility(View.GONE);
            }
        }


        requestUpdate();
        getCustomerPhone();
    }

    @Override
    public void setClickEvent() {
        rl_zizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity,IntelliGencyActivity.class);
                startActivity(intent);
            }
        });
        mIvAvatar.setOnClickListener(noDoubleClickListener);
        rl_return_order.setOnClickListener(noDoubleClickListener);//售后
        mLlPayment.setOnClickListener(noDoubleClickListener);//待付款
        mLlEvaluate.setOnClickListener(noDoubleClickListener);//待评价
        mLlDelivery.setOnClickListener(noDoubleClickListener);//待发货
        mLlReturnGoods.setOnClickListener(noDoubleClickListener);//退货
        mLlReceived.setOnClickListener(noDoubleClickListener);//待收货
        mRlCollection.setOnClickListener(noDoubleClickListener);//我的收藏
        //mRlReturnRent.setOnClickListener(noDoubleClickListener);
        mRlContact.setOnClickListener(noDoubleClickListener);//联系客服
        mRlFeedback.setOnClickListener(noDoubleClickListener);
        mRlVersion.setOnClickListener(noDoubleClickListener);//关于版本
        mRlMyOrders.setOnClickListener(noDoubleClickListener);//我的订单
        couponsLayout.setOnClickListener(noDoubleClickListener);//优惠券
        accountAddress.setOnClickListener(noDoubleClickListener);//我的地址
        accountManagement.setOnClickListener(noDoubleClickListener);//子账号管理
        imageViewBanner.setOnClickListener(noDoubleClickListener);
        mineIntegral.setOnClickListener(noDoubleClickListener);
        relativeLayoutVip.setOnClickListener(noDoubleClickListener);
        tv_order.setOnClickListener(noDoubleClickListener);
        iv_order.setOnClickListener(noDoubleClickListener);
        iv_setting.setOnClickListener(noDoubleClickListener);
        ll_setting.setOnClickListener(noDoubleClickListener);
        //  vipDay.setOnClickListener(noDoubleClickListener);
        tv_use_deduct.setOnClickListener(noDoubleClickListener);
        iv_use_deduct.setOnClickListener(noDoubleClickListener);
        ll_inviteAward.setOnClickListener(noDoubleClickListener);
        ll_amount.setOnClickListener(noDoubleClickListener);
        ll_account.setOnClickListener(noDoubleClickListener);
        ll_deduct.setOnClickListener(noDoubleClickListener);
        tv_vip_more.setOnClickListener(noDoubleClickListener);
        iv_vip_more.setOnClickListener(noDoubleClickListener);


        ll_deliver_order.setOnClickListener(noDoubleClickListener);
        ll_self_sufficiency.setOnClickListener(noDoubleClickListener);

        iv_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(getActivity()))) {
//                    startActivity(MessageCenterActivity.getIntent(getContext(), MessageCenterActivity.class));
                    //写一个携带返回结果的跳转
                    Intent intent = new Intent(getActivity(), MessageCenterActivity.class);
                    startActivityForResult(intent, 101);
//                    this.startActivityForResult()
                } else {
                    AppHelper.showMsg(getActivity(), "请先登录");
                    startActivity(LoginActivity.getIntent(getActivity(), LoginActivity.class));
                }
            }
        });
    }


    private void updateOrderNum() {
        //消息中心
        if (mModelMyOrderNum.getData().getNotice() > 0) {
            mViewMessageNum.setVisibility(View.VISIBLE);
            mViewMessageNum.setText("  " + mModelMyOrderNum.getData().getNotice() + "  ");
        } else {
            mViewMessageNum.setVisibility(View.GONE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == 102) {
                int newPosition = data.getIntExtra("NewPosition", 5);//NewPosition
//                setNewPosition(newPosition);
                Log.e(TAG, "onActivityResult: " + newPosition);
                if (newPosition > 0) {
                    mViewMessageNum.setVisibility(View.VISIBLE);
                    mViewMessageNum.setText("  " + newPosition + "  ");
                } else {
                    mViewMessageNum.setVisibility(View.GONE);
                }


            }

        }
    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            if (view == mIvAvatar) {
                //头像
            } else if (view == iv_setting || view == ll_setting) {
                //我的账户

                if (mStateCode == -10000) {
                    //异地登录了,需要清除应用内部的userId,让用户重新登录

                    startActivity(LoginActivity.getIntent(getContext(), LoginActivity.class));
                } else if (mStateCode == -10001) {
                    //用户userId过期,也是需要清除userId,让用户重新登录
                    startActivity(LoginActivity.getIntent(getContext(), LoginActivity.class));
                } else {
                    if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(getContext()))) {
                        //有userId,跳转个人中心
                        startActivity(AccountCenterActivity.getIntent(getContext(), AccountCenterActivity.class));
                    } else {
                        //没有userId
                        //这个项目登录和输入密码在一个界面,不用在这里判断用户是否登录过,在登录界面判断有没有存过userCell即可
                        //所以跳转登录界面

                        startActivity(LoginActivity.getIntent(getContext(), LoginActivity.class));
                    }
                }
            } else if (view == ll_deliver_order) {
                //到家订单

                Intent intent = MyOrdersActivity.getIntent(getContext(), MyOrdersActivity.class, AppConstant.ALL);
                intent.putExtra("orderDeliveryType",0);
                startActivity(intent);
            } else if (view == ll_self_sufficiency) {
                //自提订单
                Intent intent1 = MyOrdersActivity.getIntent(getContext(), MyOrdersActivity.class, AppConstant.ALL);
                intent1.putExtra("orderDeliveryType",1);
                startActivity(intent1);
            } else if (view == mLlPayment)

            {
                //待付款
                startActivity(MyOrdersActivity.getIntent(getContext(), MyOrdersActivity.class, AppConstant.PAYMENT));
            } else if (view == mLlEvaluate)

            {
                //待评论
                startActivity(MyOrdersActivity.getIntent(getContext(), MyOrdersActivity.class, AppConstant.EVALUATED));
            } else if (view == mLlDelivery)

            {
                //待发货
                startActivity(MyOrdersActivity.getIntent(getContext(), MyOrdersActivity.class, AppConstant.DELIVERY));
            } else if (view == mLlReturnGoods)

            {
                //退货
                startActivity(MyOrdersActivity.getIntent(getContext(), MyOrdersActivity.class, AppConstant.RETURN));
            } else if (view == mLlReceived)

            {
                //待收货
                startActivity(MyOrdersActivity.getIntent(getContext(), MyOrdersActivity.class, AppConstant.RECEIVED));

            }  else if (view == rl_return_order) {
                //我的账单
                Intent intent =new Intent(mActivity,MyWalletDetailActivity.class);

                intent.putExtra("showType",1);
                startActivity(intent);
            } else if (view == mRlCollection)

            {
                //我的收藏
                startActivity(MyCollectionActivity.getIntent(getContext(), MyCollectionActivity.class));
            } else if (view == mRlContact)

            {
                //联系客服
                if (StringHelper.notEmptyAndNull(mCustomerPhone)) {
//

//                    User userBean = SophixStubApplication.getInstance().getUser();
//                    UnicornManager.setUnicornUserInfo(userBean);
//
//                    // 设置七鱼客服对话页面的UI
//                    UnicornManager.setUiCustomization(userBean);
                    // 设置未读消息监听
//                    setCountChangeListener();

                    // 进入七鱼客服首页（即智能机器人页面）
                    showPhoneDialog(mCustomerPhone);

                }
            } else if (view == mRlFeedback)

            {
                //建议反馈
                startActivity(FeedBackActivity.getIntent(getContext(), FeedBackActivity.class));
            } else if (view == mRlVersion)

            {
                //版本
                //后面做版本更新的功能,需要重新对接
                if (update) {
                    UserInfoHelper.saveGuide(mActivity, "");
                    showUpdateDialog();
                } else {
                    //已经是最新版本
                    final AlertDialog mDialog = new AlertDialog.Builder(getContext()).create();
                    mDialog.setTitle("已经是最新版本");
                    mDialog.show();
                    mDialog.getWindow().setContentView(R.layout.enddate_dialog);
                    mDialog.setCanceledOnTouchOutside(true);
                    LinearLayout mVersionDialog = mDialog.getWindow().findViewById(R.id.linearLayout_version_dialog);
                    mVersionDialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDialog.dismiss();
                        }
                    });
                }
            } else if (view == mRlMyOrders)

            {
                //我的订单

                startActivity(MyOrdersActivity.getIntent(getContext(), MyOrdersActivity.class, AppConstant.ALL));

            } else if (view == tv_order)

            {
                //我的订单

                startActivity(MyOrdersActivity.getIntent(getContext(), MyOrdersActivity.class, AppConstant.ALL));

            } else if (view == iv_order)

            {
                //我的订单

                startActivity(MyOrdersActivity.getIntent(getContext(), MyOrdersActivity.class, AppConstant.ALL));
            } else if (view == couponsLayout)

            { //我的优惠券
                startActivity(MyCouponsActivity.getIntent(getContext(), MyCouponsActivity.class));
            } else if (view == accountAddress)

            {
                Intent intent =new Intent(mActivity,AddressListActivity.class);
                intent.putExtra("mineAddress","mineAddress");
                startActivity(intent);

            } else if (view == accountManagement) {

                //子账户
                Intent intent = new Intent(getContext(),SubAccountActivity.class);
                startActivity(intent);

            } else if (view == imageViewBanner) {
                if (StringHelper.notEmptyAndNull(MyBannerUrl)) {
                    //我直接让他跳转到NewWebViewActivity 中去。
//                    String newWebViewUrl="http://116.62.67.230:8082/apph5/html/member.html";
                    Intent intent = new Intent(getActivity(), NewWebViewActivity.class);
                    intent.putExtra("URL", MyBannerUrl);
                    intent.putExtra("TYPE", 2);
                    intent.putExtra("name","consult");
                    startActivity(intent);
                }
            } else if (view == ll_account)

            {//积分
                startActivity(CommonH5Activity.getIntent(getContext(), MinerIntegralActivity.class));
            } else if (view == mineIntegral)

            {  // 我的积分
                startActivity(CommonH5Activity.getIntent(getContext(), MinerIntegralActivity.class));
            } else if (view == relativeLayoutVip || view == tv_vip_more || view == iv_vip_more)

            { //会员中心
                Intent intent = new Intent(getContext(), NewWebViewActivity.class);
                intent.putExtra("URL", urlVIP);
                intent.putExtra("TYPE", 1);
                intent.putExtra("name", "");
                startActivity(intent);
            } /*else if (view == vipDay) {
                Intent intent = new Intent(getContext(), NewWebViewActivity.class);
                intent.putExtra("URL", vipDayUrlVIP);
                intent.putExtra("name", "");
                startActivity(intent);
            }*/ else if (view == tv_use_deduct)

            {
                ll_expiredInfo.setVisibility(View.GONE);
                isChecked = true;
                useAccount();
            } else if (view == iv_use_deduct)

            {
                ll_expiredInfo.setVisibility(View.GONE);
                isChecked = false;
                useAccount();
            } else if (view == ll_amount)

            {
                String num = "0";
                Intent intent = new Intent(mActivity, MyWalletNewActivity.class);
                UserInfoHelper.saveUserWalletNum(getContext(), num);
                startActivity(intent);
            } else if (view == ll_inviteAward)

            {

                Intent intent = new Intent(mActivity, NewWebViewActivity.class);
                intent.putExtra("URL", commissionUrl);
                intent.putExtra("TYPE", 1);
                intent.putExtra("name", "");
                startActivity(intent);

            } else if (view == ll_deduct)

            {
                startActivity(MyCouponsActivity.getIntent(getContext(), MyCouponsActivity.class));
            }

        }

    };


    private void useAccount() {
        MineAccountAPI.requestMineAccount(mActivity, day, giftNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MineCenterModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MineCenterModel mineCenterModel) {

                        if (mineCenterModel.isSuccess()) {

                            if (isChecked) {
                                startActivity(MyCouponsActivity.getIntent(getContext(), MyCouponsActivity.class));
                            }


                        } else {
                            AppHelper.showMsg(mActivity, mineCenterModel.getMessage());
                        }


                    }
                });
    }

    private void showUpdateDialog() {
        final AlertDialog mDialog = new AlertDialog.Builder(getContext()).create();
        mDialog.show();
        mDialog.getWindow().setContentView(R.layout.update_dialog);
        Button mBtnForceUpdate = (Button) mDialog.getWindow().findViewById(R.id.btnForceUpdate);
        Button mBtnCancel = (Button) mDialog.getWindow().findViewById(R.id.btnCancel);
        Button mBtnOK = (Button) mDialog.getWindow().findViewById(R.id.btnOK);
        LinearLayout mLlButton = (LinearLayout) mDialog.getWindow().findViewById(R.id.llButton);
        TextView mTvContent = (TextView) mDialog.getWindow().findViewById(R.id.tvContent);
        mTvContent.setText(content);
        if (forceUpdate) {
            mDialog.setCancelable(false);
            mLlButton.setVisibility(View.GONE);
            mBtnForceUpdate.setVisibility(View.VISIBLE);
        } else {
            mDialog.setCancelable(true);
            mLlButton.setVisibility(View.VISIBLE);
            mBtnForceUpdate.setVisibility(View.GONE);
        }
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 下载
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(url);
                    intent.setData(content_url);
                    startActivity(intent);
                } catch (Exception e) {

                }
                mDialog.dismiss();
            }
        });
        mBtnForceUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 下载
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(url.contains("http://") ? ("http://" + url) : url);
                    intent.setData(content_url);
                    startActivity(intent);
                } catch (Exception e) {

                }
                mDialog.dismiss();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void requestUpdate() {
        UpdateAPI.requestUpdate(getContext(), AppHelper.getVersion(getContext()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UpdateModel updateModel) {
                        mModelUpdate = updateModel;
                        if (mModelUpdate.success) {
                            updateUpdate();
                        } else {
                            AppHelper.showMsg(mActivity, mModelUpdate.message);
                        }
                    }
                });
    }

    private void updateUpdate() {
        url = mModelUpdate.data.url;
        update = mModelUpdate.data.update;
        forceUpdate = mModelUpdate.data.forceUpdate;
        content = mModelUpdate.data.msg;
        if (update) {
            //因为服务器上面的是2.0.6，所以才会出现新版本和提示框的字样，只要上架之后重新上传一个2.0.7就可以了。
            //有更新
            mTvVersion.setText(getString(R.string.textVersion) + AppHelper.getVersion(getContext()));
            mViewVersionPoint.setVisibility(View.VISIBLE);
            showUpdateDialog();
        } else {
            mTvVersion.setText(getString(R.string.textVersion) + AppHelper.getVersion(getContext()));
            //没更新
            mViewVersionPoint.setVisibility(View.GONE);
        }
    }

    private void requestOrderNum() {
        MyOrderNumAPI.requestOrderNum(getContext())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyOrderNumModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MyOrderNumModel myOrderNumModel) {
                        mListData.clear();
                        if (myOrderNumModel.success) {
                            mModelMyOrderNum = myOrderNumModel;
                            Log.d("wwwwwwwwww.....","swdwd");

//                            if(myOrderNumModel.getData().getSubMessage()==0) {
//                                tv_number.setVisibility(View.GONE);
//                            }else {
//                                tv_number.setText(myOrderNumModel.getData().getSubMessage());
//                                tv_number.setVisibility(View.VISIBLE);
//                            }
                            mListData.add(myOrderNumModel.getData());
                            day = myOrderNumModel.getData().getDay();
                            giftNo = myOrderNumModel.getData().getGiftNo();
                            commissionUrl = myOrderNumModel.getData().getCommissionUrl();
                            mTvPhone.setVisibility(View.VISIBLE);
                            mTvPhone.setText(myOrderNumModel.getData().getPhone());
                            if(myOrderNumModel.getData().getInviteOpen()==1) {
                                ll_inviteAward.setVisibility(View.GONE);
                            }else {
                                ll_inviteAward.setVisibility(View.VISIBLE);
                            }

                            if (myOrderNumModel.getData().isVipUser()) {
                                tv_vip.setText("翘歌会员");
                            } else {
                                tv_vip.setText("普通用户");
                            }

                            if (myOrderNumModel.getData().getBalance() != null) {
                                tv_amount.setText("¥" + myOrderNumModel.getData().getBalance());
                            } else {
                                tv_amount.setText("¥" + "0.00");
                            }
                            if (myOrderNumModel.getData().getCommission() != null) {
                                tv_commission.setText("¥" + myOrderNumModel.getData().getCommission());
                            } else {
                                tv_commission.setText("¥" + "0.00");
                            }

                            if (myOrderNumModel.getData().getInviteAward() != null) {
                                tv_inviteAward.setVisibility(View.VISIBLE);
                                tv_inviteAward.setText(myOrderNumModel.getData().getInviteAward());
                            } else {
                                tv_inviteAward.setVisibility(View.GONE);
                            }


                            tv_point.setText(String.valueOf(myOrderNumModel.getData().getPoint()));
                            tv_deductNum.setText(String.valueOf(myOrderNumModel.getData().getDeductNum()));

                            if (myOrderNumModel.getData().getCollectNum() > 0) {
                                mViewCollectionNum.setVisibility(View.VISIBLE);
                                mViewCollectionNum.setText(String.valueOf(myOrderNumModel.getData().getCollectNum()));
                            } else {
                                mViewCollectionNum.setVisibility(View.GONE);
                            }

                            if (myOrderNumModel.getData().getExpiredInfo() != null && StringHelper.notEmptyAndNull(myOrderNumModel.getData().getExpiredInfo())) {
                                ll_expiredInfo.setVisibility(View.VISIBLE);
                                tv_expiredInfo.setText(myOrderNumModel.getData().getExpiredInfo());
                            } else {
                                ll_expiredInfo.setVisibility(View.GONE);
                            }

                            //  mViewPager.setAdapter(mPagerAdapter);
                            // 会员中心 url
                            if (!TextUtils.isEmpty(myOrderNumModel.getData().getVipCenter())) {
                                urlVIP = myOrderNumModel.getData().getVipCenter();
                                relativeLayoutVip.setEnabled(true);

                            } else {
                                relativeLayoutVip.setEnabled(false);
                            }
                            if (!TextUtils.isEmpty((myOrderNumModel.getData().getVipDesc()))) {
                                vipDesc.setText(myOrderNumModel.getData().getVipDesc());
                                vipDesc.setVisibility(View.VISIBLE);
                            } else {
                                vipDesc.setVisibility(View.GONE);
                            }
//我的收藏


                            //待付款
                            if (myOrderNumModel.getData().getWaitPayment() > 0) {
                                mViewWaitPaymentNum.setVisibility(View.VISIBLE);
                                mViewWaitPaymentNum.setText(myOrderNumModel.getData().getWaitPayment() + "");

                            } else {
                                mViewWaitPaymentNum.setVisibility(View.GONE);
                            }
                            //待发货
                            if (myOrderNumModel.getData().getWaitShipments() > 0) {
                                mViewWaitShipmentNum.setVisibility(View.VISIBLE);
                                mViewWaitShipmentNum.setText(myOrderNumModel.getData().getWaitShipments() + "");
                            } else {
                                mViewWaitShipmentNum.setVisibility(View.GONE);
                            }
                            //待收货
                            if (myOrderNumModel.getData().getWaitReceiving() > 0) {
                                mViewWaitReceivingNum.setVisibility(View.VISIBLE);
                                mViewWaitReceivingNum.setText(myOrderNumModel.getData().getWaitReceiving() + "");
                            } else {
                                mViewWaitReceivingNum.setVisibility(View.GONE);
                            }
                            //待评价
                            if (myOrderNumModel.getData().getWaitEvaluate() > 0) {
                                mViewWaitEvaluateNum.setVisibility(View.VISIBLE);
                                mViewWaitEvaluateNum.setText(myOrderNumModel.getData().getWaitEvaluate() + "");
                            } else {
                                mViewWaitEvaluateNum.setVisibility(View.GONE);
                            }
                            //退货
                            if (myOrderNumModel.getData().getReturnSale() > 0) {
                                mViewReturnNum.setVisibility(View.VISIBLE);
                                mViewReturnNum.setText(myOrderNumModel.getData().getReturnSale() + "");
                            } else {
                                mViewReturnNum.setVisibility(View.GONE);
                            }

                            //消息中心
                            if (myOrderNumModel.getData().getNotice() > 0) {
                                mViewMessageNum.setVisibility(View.VISIBLE);
                                mViewMessageNum.setText(myOrderNumModel.getData().getNotice() + "");
                            } else {
                                mViewMessageNum.setVisibility(View.GONE);
                            }

                        } else {
                            AppHelper.showMsg(mActivity, myOrderNumModel.message);
                        }
                    }
                });
    }

    private void requestUserInfo() {
        if (!NetWorkHelper.isNetworkAvailable(getContext())) {
            AppHelper.showMsg(getContext(), "网络不给力!");
        } else {
            AccountCenterAPI.requestAccountCenter(getContext())
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
                            mModelAccountCenter = accountCenterModel;
                            mStateCode = mModelAccountCenter.code;
                            AppHelper.UserLogout(getContext(), mStateCode, 1);
                            if (mModelAccountCenter.success) {
                                updateAccountCenter();

                            } else {
                                mTvPhone.setText("请登录");
                                AppHelper.showMsg(getContext(), mModelAccountCenter.message);
                            }
                        }
                    });
        }
    }

    private void updateAccountCenter() {
        mUserCell = mModelAccountCenter.data.phone;
        mCustomerPhone = mModelAccountCenter.data.customerPhone;
    }

    /**
     * 弹出电话号码
     */
    private void showPhoneDialog(final String cell) {
        mDialog = new AlertDialog.Builder(getActivity()).create();
        mDialog.show();
        mDialog.getWindow().setContentView(R.layout.dialog_call_phone);
        tv_phone = mDialog.getWindow().findViewById(R.id.tv_phone);
        mDialog.getWindow().findViewById(R.id.tv_dialog_call_phone_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        tv_phone.setText(cell);
        mDialog.getWindow().findViewById(R.id.tv_dialog_call_phone_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnicornManager.inToUnicorn(getContext());
//                if (isTablet(getActivity())) {
//                    AppHelper.showMsg(getActivity(), "当前设备不具备拨号功能");
//                } else {
//                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + cell));
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//                }
                mDialog.dismiss();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void message(MessageEvent messageEvent) {
        tv_number.setVisibility(View.GONE);
        getCustomerPhone();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginEvent(LogoutsEvent event) {
        requestOrderNum();
        requestUserInfo();
        useAccount();
        requestUpdate();
        getCustomerPhone();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessageEvent(GoToMineEvent goToMineEvent) {
        requestOrderNum();
        requestUserInfo();
        useAccount();
        requestUpdate();
        getCustomerPhone();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCoupon(CouponEvent couponEvent) {
        requestOrderNum();
        requestUserInfo();
        useAccount();
        requestUpdate();
        getCustomerPhone();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changes(CityEvent cityEvent) {
        requestOrderNum();
        requestUserInfo();
        useAccount();
        requestUpdate();
        getCustomerPhone();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changess(AddressEvent event) {
        requestOrderNum();
        requestUserInfo();
        useAccount();
        requestUpdate();
        getCustomerPhone();
    }
    String cell;
    private void getCustomerPhone() {
        GetCustomerPhoneAPI.requestData(mActivity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetCustomerPhoneModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetCustomerPhoneModel getCustomerPhoneModel) {
                        if (getCustomerPhoneModel.isSuccess()) {
                            cell = getCustomerPhoneModel.getData();
                        } else {
                            AppHelper.showMsg(mActivity, getCustomerPhoneModel.getMessage());
                        }
                    }
                });
    }

}
