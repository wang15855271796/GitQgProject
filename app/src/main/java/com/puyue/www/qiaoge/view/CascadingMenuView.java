package com.puyue.www.qiaoge.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.login.CascadingMenuViewOnSelectListener;
import com.puyue.www.qiaoge.model.home.AddressBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 三级级联动ListView
 * 
 */
public class CascadingMenuView extends LinearLayout {
	private static final String TAG = CascadingMenuView.class.getSimpleName();
	// 三级菜单选择后触发的接口，即最终选择的内容
	private CascadingMenuViewOnSelectListener mOnSelectListener;
	private ListView firstMenuListView;
	private ListView secondMenuListView;
	private ListView thirdMenuListView;

	// 每次选择的子菜单内容
	private List<AddressBean.DataBean.ListBeanX.ListBean> thirdItem = new ArrayList<>();
	private List<AddressBean.DataBean.ListBeanX> secondItem = new ArrayList<>();
	private List<AddressBean.DataBean> menuItem;

	private MenuItemAdapter firstMenuListViewAdapter;

	private Menu2ItemAdapter secondMenuListViewAdapter;

	private Menu3ItemAdapter thirdMenuListViewAdapter;

	private int firstPosition = 0;
	private int secondPosition = 0;
	private int thirdPosition = 0;

	private Context context;
    private AddressBean.DataBean.ListBeanX menuItem1;

    /**
	 * @param context
	 *            上下文
	 */
	public CascadingMenuView(Context context, List<AddressBean.DataBean> menuList) {
		super(context);
		this.menuItem = menuList;
		this.context = context;
		init(context);
	}

	public CascadingMenuView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init(context);
	}

	private void init(Context context) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_region, this, true);
		firstMenuListView = (ListView) findViewById(R.id.listView);
		secondMenuListView = (ListView) findViewById(R.id.listView2);
		thirdMenuListView = (ListView) findViewById(R.id.listView3);

		// 初始化一级主菜单
		firstMenuListViewAdapter = new MenuItemAdapter(context, menuItem, R.color.white, R.color.white);
		firstMenuListView.setAdapter(firstMenuListViewAdapter);
		firstMenuListViewAdapter.setTextSize(17);

		firstMenuListViewAdapter.setOnItemClickListener(new MenuItemAdapter.OnItemClickListener() {

					@Override
					public void onItemClick(View view, int position) {
						// 选择主菜单，清空原本子菜单内容，增加新内容
						if (mOnSelectListener != null) {
							mOnSelectListener.getValuess(menuItem.get(position));
						}

						if(menuItem.get(position).getList()!=null&&menuItem.get(position).getList().size()!=0) {
							secondItem = menuItem.get(position).getList();

							// 通知适配器刷新
							secondMenuListView.setVisibility(View.VISIBLE);
							secondMenuListViewAdapter.notifyDataSetChanged();
							secondMenuListViewAdapter.setSelectedPositionNoNotifys(0, secondItem);

							if(secondItem.get(secondPosition).getList()!=null&&secondItem.get(secondPosition).getList().size()!=0) {
								thirdItem = secondItem.get(secondPosition).getList();
								// 通知适配器刷新
								thirdMenuListView.setVisibility(View.VISIBLE);
								thirdMenuListViewAdapter.notifyDataSetChanged();
								thirdMenuListViewAdapter.setSelectedPositionNoNotifys(0,thirdItem);

							}else {

								thirdMenuListView.setVisibility(View.GONE);
							}

						}else {

							secondMenuListView.setVisibility(View.GONE);
							thirdMenuListView.setVisibility(View.GONE);
						}
					}
				});


		// 初始化二级主菜单
		secondItem = menuItem.get(firstPosition).getList();
		thirdItem = secondItem.get(secondPosition).getList();
		secondMenuListViewAdapter = new Menu2ItemAdapter(context, secondItem, R.color.white, R.color.white);
		secondMenuListViewAdapter.setTextSize(15);
		secondMenuListView.setAdapter(secondMenuListViewAdapter);
		secondMenuListViewAdapter.setOnItemClickListener(new MenuItemAdapter.OnItemClickListener() {

					@Override
					public void onItemClick(View view, final int position) {
						// 选择主菜单，清空原本子菜单内容，增加新内容
                        if (mOnSelectListener != null) {
                            mOnSelectListener.getValues(secondItem.get(position));
                        }
                        if(secondItem.get(position).getList()!=null&&secondItem.get(position).getList().size()!=0) {
                            thirdItem = secondItem.get(position).getList();
                            menuItem1 = secondItem.get(position);
                            // 通知适配器刷新
                            thirdMenuListViewAdapter.notifyDataSetChanged();
                            thirdMenuListViewAdapter.setSelectedPositionNoNotifys(0,thirdItem);
                            thirdMenuListView.setVisibility(View.VISIBLE);

                        }else {
                            thirdMenuListView.setVisibility(View.GONE);
                        }
					}
				});

		// 初始化三级主菜单
		thirdItem = secondItem.get(secondPosition).getList();
		thirdMenuListViewAdapter = new Menu3ItemAdapter(context, thirdItem, R.color.white,R.color.white);
		thirdMenuListViewAdapter.setTextSize(13);
		thirdMenuListView.setAdapter(thirdMenuListViewAdapter);
		thirdMenuListViewAdapter.setOnItemClickListener(new MenuItemAdapter.OnItemClickListener() {
					@Override
					public void onItemClick(View view, final int position) {
						AddressBean.DataBean.ListBeanX.ListBean menuItem = thirdItem.get(position);
						if (mOnSelectListener != null) {
							mOnSelectListener.getValue(menuItem);
						}
					}
				});
		// 设置默认选择
		setDefaultSelect();
	}

	public void setDefaultSelect() {
		firstMenuListView.setSelection(firstPosition);
		secondMenuListView.setSelection(secondPosition);
		thirdMenuListView.setSelection(thirdPosition);
	}

	public void setCascadingMenuViewOnSelectListener(CascadingMenuViewOnSelectListener onSelectListener) {
		mOnSelectListener = onSelectListener;
	}
}
