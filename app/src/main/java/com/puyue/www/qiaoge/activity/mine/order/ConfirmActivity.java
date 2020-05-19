package com.puyue.www.qiaoge.activity.mine.order;

import android.os.Bundle;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.dialog.ChooseAddressDialog;
import com.puyue.www.qiaoge.dialog.ChooseHomeDialog;

/**
 * Created by ${王涛} on 2020/5/6
 */
public class ConfirmActivity extends BaseSwipeActivity {
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_confirm);
    }

    @Override
    public void findViewById() {

    }

    @Override
    public void setViewData() {
        setTranslucentStatus();

        ChooseHomeDialog chooseAddressDialog = new ChooseHomeDialog(mActivity,"");
        chooseAddressDialog.show();
    }

    @Override
    public void setClickEvent() {

    }
}
