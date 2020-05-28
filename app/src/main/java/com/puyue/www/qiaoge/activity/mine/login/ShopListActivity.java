package com.puyue.www.qiaoge.activity.mine.login;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.home.GetScenicSpotDetailByIdAndDateAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.model.home.AddressBean;
import com.puyue.www.qiaoge.utils.ToastUtil;
import com.puyue.www.qiaoge.view.CascadingMenuView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2020/3/23
 * 店铺类型界面
 */
public class ShopListActivity extends BaseSwipeActivity {
    protected Context mContext;
    private ArrayList<AddressBean.DataBean> menu=null;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.tv_save)
    TextView tv_save;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    // 两级联动菜单数据
    private CascadingMenuFragment cascadingMenuFragment = null;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_shop_list);
    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);
    }

    @Override
    public void setViewData() {
        mContext = this;
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showSuccessMsg(mContext,"请选择具体店铺类型");
//                finish();
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getShopList();
    }

    /**
     * 获取店铺类型
     */
    private void getShopList() {
        GetScenicSpotDetailByIdAndDateAPI.getShopList(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AddressBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AddressBean addressBean) {
                        if (addressBean.isSuccess()) {

                            showFragmentMenu();
                            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                                    .beginTransaction();

                            if (cascadingMenuFragment == null) {
                                cascadingMenuFragment = CascadingMenuFragment.getInstance();
                                cascadingMenuFragment.setMenuItems(addressBean.getData());
                                cascadingMenuFragment.setMenuViewOnSelectListener(new NMCascadingMenuViewOnSelectListener());
                                fragmentTransaction.replace(R.id.linearLayout, cascadingMenuFragment);
                            } else {
                                fragmentTransaction.remove(cascadingMenuFragment);
                                cascadingMenuFragment = null;
                            }
                            fragmentTransaction.commit();
                        } else {
                            AppHelper.showMsg(mContext, addressBean.getMessage());
                        }

                    }
                });
    }

    private void showFragmentMenu() {

    }
    AddressBean.DataBean.ListBeanX.ListBean area1;
    AddressBean.DataBean.ListBeanX area2;
    AddressBean.DataBean area3;

    // 级联菜单选择回调接口
    class NMCascadingMenuViewOnSelectListener implements CascadingMenuViewOnSelectListener {

        @Override
        public void getValue(AddressBean.DataBean.ListBeanX.ListBean area) {
            area1 = area;

            tv_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            cascadingMenuFragment = null;
            EventBus.getDefault().post(new ShopEvent(area.getName()));
        }

        @Override
        public void getValues(AddressBean.DataBean.ListBeanX area) {
            area2 = area;
            tv_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(area!=null&&area.getList()==null) {
                        finish();
                    }else {
                        ToastUtil.showSuccessMsg(mContext,"请选择具体店铺类型");
                    }
                }
            });

            cascadingMenuFragment = null;
            EventBus.getDefault().post(new ShopEvent(area.getName()));
        }

        @Override
        public void getValuess(AddressBean.DataBean area) {
            area3 = area;

            tv_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(area!=null&&area.getList()==null) {
                        finish();
                    }else {
                        ToastUtil.showSuccessMsg(mContext,"请选择具体店铺类型");
                    }
                }
            });

            cascadingMenuFragment = null;
            EventBus.getDefault().post(new ShopEvent(area.getName()));
        }

        @Override
        public void getValuesss(AddressBean area) {
            cascadingMenuFragment = null;
//            EventBus.getDefault().register(new ShopEvent(area.getName()));
        }
    }

//    //提供给外的接口
//    private CascadingMenuViewOnSelectListener menuViewOnSelectListener;
//    class MCascadingMenuViewOnSelectListener implements CascadingMenuViewOnSelectListener{
//
//        @Override
//        public void getValue(AddressBean.DataBean.ListBeanX.ListBean area) {
//            if(menuViewOnSelectListener!=null){
//                menuViewOnSelectListener.getValue(area);
//            }
//        }
//
//    }


    @Override
    public void setClickEvent() {

    }
}
