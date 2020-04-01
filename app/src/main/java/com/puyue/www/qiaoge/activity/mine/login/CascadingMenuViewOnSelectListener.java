package com.puyue.www.qiaoge.activity.mine.login;


import com.puyue.www.qiaoge.model.home.AddressBean;

/**
 * 通用级联菜单接口
 * @author LILIN
 * 下午3:21:35
 */
public interface CascadingMenuViewOnSelectListener {
	void getValue(AddressBean.DataBean.ListBeanX.ListBean area);
	void getValues(AddressBean.DataBean.ListBeanX area);
	void getValuess(AddressBean.DataBean area);
	void getValuesss(AddressBean area);
}
