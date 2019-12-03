package com.puyue.www.qiaoge.slidenested;

import android.view.View;

/**

 * TODO: 面板滑动回调
 */
public interface PanelSlideListener {

    //当面板滑动位置发送变化
    void onPanelSlide(View panel, float slideOffset);


    //当面板状态发送变化
    void onPanelStateChanged(View panel, PanelState preState, PanelState newState);
}
