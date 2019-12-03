package com.puyue.www.qiaoge.helper;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;

/**
 * Created by Administrator on 2018/4/13.
 */

public class DialogHelper {
    private static BottomSheetDialog bottomSheetDialog;
    /**
     * @param context
     * @param confirmClickListener
     */
    public static void showLogoutDialog(Context context, NoDoubleClickListener confirmClickListener) {
        bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(R.layout.dialog_logout);
        bottomSheetDialog.show();
        TextView tvConfirm = (TextView) bottomSheetDialog.findViewById(R.id.tv_dialog_logout_confirm);
        TextView tvCancel = (TextView) bottomSheetDialog.findViewById(R.id.tv_dialog_logout_cancel);
        tvConfirm.setOnClickListener(confirmClickListener);
        tvCancel.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
    }

    public static void dismissLogoutDialog() {
        bottomSheetDialog.dismiss();
    }
}
