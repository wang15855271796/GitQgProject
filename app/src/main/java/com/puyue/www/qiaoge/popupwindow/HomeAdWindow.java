package com.puyue.www.qiaoge.popupwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.puyue.www.qiaoge.R;

/**
 * Created by ${王文博} on 2019/3/26
 * 首页广告轮播弹窗
 */
public class HomeAdWindow extends PopupWindow {

    private Context context;
    private int id;
    private View view;
    private TextView tv_confirm;
    private SliderLayout mViewBanner;
    private String urlImage;
    private String intentUrl;
    private String toPage;

    public HomeAdWindow(Context context, int id, String urlImage, String intentUrl, String toPage) {

        this.context = context;
        this.id = id;
        this.urlImage = urlImage;
        this.intentUrl = intentUrl;
        this.toPage = toPage;
        this.view = LayoutInflater.from(context).inflate(R.layout.popwindow_ad_home, null);
        initView();
    }

    public void initView() {

        mViewBanner = view.findViewById(R.id.ad_home_banner);
        tv_confirm = view.findViewById(R.id.tv_confirm);

        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        // 设置外部可点击
        //this.setOutsideTouchable(true);
        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        // 设置弹出窗体可点击
        this.setFocusable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x7f000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = view.findViewById(R.id.linearLayout).getTop();
                int buttom = view.findViewById(R.id.linearLayout).getBottom();
                int Left = v.findViewById(R.id.linearLayout).getLeft();
                int Right = v.findViewById(R.id.linearLayout).getRight();
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

    }
}
