package com.puyue.www.qiaoge.popupwindow;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.puyue.www.qiaoge.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daff
 * @date 2018/9/22.
 * 备注 明细 筛选 时间的弹窗
 */
public class MyWallDetailPopuwindow extends PopupWindow {

    private Context context;
    private View view;
    private GridView gridview;

    private List list;

    public MyWallDetailPopuwindow(Context context, List list, final ItemClick itemsOnClick) {
        this.context = context;
        this.list = list;
        this.view = LayoutInflater.from(context).inflate(R.layout.item_detail_popuwindow, null);

        initview(itemsOnClick);
    }

    private void initview(final ItemClick itemsOnClick) {

        gridview = (GridView) view.findViewById(R.id.gridview);
        ArrayAdapter adapter = new ArrayAdapter(context, R.layout.item_my_detail_textview, R.id.text, list);
        gridview.setAdapter(adapter);
        gridview.getAdapter().getView(0, null, null).setActivated(true);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemsOnClick.GridviewOnClick(i);

            }
        });
        // 设置外部可点击
        this.setOutsideTouchable(true);
        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        // 设置弹出窗体可点击
        this.setFocusable(true);
        //实例化一个ColorDrawable颜色为半透明
        // ColorDrawable dw = new ColorDrawable(0x7f000000);
        // 设置弹出窗体的背景
        // this.setBackgroundDrawable(dw);
        this.view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = view.findViewById(R.id.gridview).getTop();
                int buttom = view.findViewById(R.id.gridview).getBottom();
                int Left = v.findViewById(R.id.gridview).getLeft();
                int Right = v.findViewById(R.id.gridview).getRight();
                int y = (int) event.getY();
                int X = (int) event.getX();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height || y > buttom || X < Left || X > Right) {
                        dismiss();
                    }
                }
                return true;
            }
        });
        // 设置弹出窗体显示时的动画，从底部向上弹出
        // this.setAnimationStyle(R.style.take_photo_anim);

    }


    public interface ItemClick {

        public void GridviewOnClick(int position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return view;
    }


}
