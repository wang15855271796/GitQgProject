package com.puyue.www.qiaoge.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.HomeActivity;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.view.StatusBarUtil;

/**
 * 与Activity通信的三种方式：Bundle Methods Listener
 * Created by GuoGai on 2016/7/6.
 */
public abstract class BaseFragment extends Fragment {
    public FragmentActivity mActivity;
    public FragmentManager supportFragmentManager;
    public FragmentTransaction fragmentTransaction;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (FragmentActivity) context;
        supportFragmentManager = getChildFragmentManager();

    }

    /**
     * The onCreate method is called when the Fragment instance is being created, or re-created.
     * onCreate for any data initializatio
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
//    private View rootView;
    /**
     * The onCreateView method is called when Fragment should create its View object hierarchy,
     * either dynamically or via XML layout inflation.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Defines the xml file for the fragment
//        if (null != rootView) {
//            ViewGroup parent = (ViewGroup) rootView.getParent();
//            if (null != parent) {
//                parent.removeView(rootView);
//            }
//        } else {
//            rootView = inflater.inflate(setLayoutId(), container, false);
//            findViewById(rootView);
//            initViews(rootView);
//            setViewData();
//            setClickEvent();
//            Log.d("woemingsddddddd....","ssssss");
//        }
//        return rootView;
        return inflater.inflate(setLayoutId(), container, false);

    }

    /**
     * This event is triggered soon after onCreateView().
     * onViewCreated() is only called if the view returned from onCreateView() is non-null.
     * Any view setup should occur here.  E.g., view lookups and attaching view listeners.
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //findViewById setViewData setClick

        findViewById(view);
        initViews(view);
        setViewData();
        setClickEvent();

    }

    // 设置白色导航栏
    protected void setTranslucentStatus() {
        // 5.0以上系统状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

    }

    /**
     * 设置黑色导航栏
     */
    public void setBlackStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getActivity().getWindow().setStatusBarColor(Color.BLACK);
            StatusBarUtil.setStatusBarLightMode(getActivity());
        }
    }


    /**
     * 设置布局
     *
     * @return
     */
    public abstract int setLayoutId();

    /**
     * 初始化控件
     *
     * @param view
     */
    public abstract void initViews(View view);

    /**
     * findViewById
     */
    public abstract void findViewById(View view);


    /**
     * setViewData
     */
    public abstract void setViewData();

    /**
     * setClickEvent
     */
    public abstract void setClickEvent();

    /**
     * 暂时未使用，后续整理两个账号模块
     *
     * @param context
     */
    public void logoutAndToHome(Context context, int mStateCode) {
        //清空UserId
        if (mStateCode == -10000 || mStateCode == -10001) {
            UserInfoHelper.saveUserId(context, "");
            UserInfoHelper.saveUserType(context, "");
            UserInfoHelper.saveUserHomeRefresh(context, "");
            UserInfoHelper.saveUserMarketRefresh(context, "");
            startActivity(new Intent(context, HomeActivity.class));
            ((Activity) context).finish();
        }
    }

}
