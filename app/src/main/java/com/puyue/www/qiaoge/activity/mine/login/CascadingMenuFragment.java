package com.puyue.www.qiaoge.activity.mine.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.puyue.www.qiaoge.model.home.AddressBean;
import com.puyue.www.qiaoge.view.CascadingMenuView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${王涛} on 2020/3/25
 */
public class CascadingMenuFragment extends Fragment {

    private CascadingMenuView cascadingMenuView;
    private List<AddressBean.DataBean> areas=null;
    //提供给外的接口
    private CascadingMenuViewOnSelectListener menuViewOnSelectListener;
    private static CascadingMenuFragment instance=null;
    //单例模式
    public static CascadingMenuFragment getInstance(){
        if(instance==null){
            instance=new CascadingMenuFragment();
        }
        return instance;
    }

    public void setMenuItems(List<AddressBean.DataBean> areas) {
        this.areas = areas;

    }

    public void setMenuViewOnSelectListener(CascadingMenuViewOnSelectListener menuViewOnSelectListener) {
        this.menuViewOnSelectListener = menuViewOnSelectListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //实例化级联菜单
        cascadingMenuView=new CascadingMenuView(getActivity(),areas);
        //设置回调接口
        cascadingMenuView.setCascadingMenuViewOnSelectListener(new MCascadingMenuViewOnSelectListener());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return cascadingMenuView;
    }
    //级联菜单选择回调接口
    class MCascadingMenuViewOnSelectListener implements CascadingMenuViewOnSelectListener{

        @Override
        public void getValue(AddressBean.DataBean.ListBeanX.ListBean area) {
            if(menuViewOnSelectListener!=null){
                menuViewOnSelectListener.getValue(area);

            }
        }

        @Override
        public void getValues(AddressBean.DataBean.ListBeanX area) {
            if(menuViewOnSelectListener!=null){
                menuViewOnSelectListener.getValues(area);
            }
        }

        @Override
        public void getValuess(AddressBean.DataBean area) {
            if(menuViewOnSelectListener!=null){
                menuViewOnSelectListener.getValuess(area);
            }
        }

        @Override
        public void getValuesss(AddressBean area) {

        }
    }
}
