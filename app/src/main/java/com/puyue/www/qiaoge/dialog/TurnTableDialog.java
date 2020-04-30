package com.puyue.www.qiaoge.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.CommonH5Activity;
import com.puyue.www.qiaoge.fragment.cart.NumEvent;
import com.puyue.www.qiaoge.utils.ToastUtil;
import com.puyue.www.qiaoge.view.LuckPan;
import com.puyue.www.qiaoge.view.LuckPanAnimEndCallBack;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by ${王涛} on 2020/4/21
 */
public class TurnTableDialog extends Dialog {
    Context mContext;
    LuckPan pan;
    ImageView iv_start;
//    private String[] mItemStrs = {"123","撒大声道1","撒大声道2","撒旦说","撒大声道3","哥哥哥","对应效果","对应代码"};
    private boolean isRunning;
    List<String> list;
    public TurnTableDialog(@NonNull Context context, List<String> list) {
        super(context, R.style.promptDialog);
        setContentView(R.layout.dialog_turn);
        mContext = context;
        this.list = list;
        initView();
        initAction();
    }

    private void initView() {
        pan= (LuckPan) findViewById(R.id.pan);
        iv_start = (ImageView) findViewById(R.id.iv_start);
        pan.setItems(list);
        pan.setLuckNumber(3);
        pan.setLuckPanAnimEndCallBack(new LuckPanAnimEndCallBack() {
            @Override
            public void onAnimEnd(String str) {
                isRunning = false;
                TurnResultDialog turnResultDialog = new TurnResultDialog(mContext);
                turnResultDialog.show();
                dismiss();
            }
        });

        iv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isRunning) {
                    pan.startAnim();
                    isRunning = true;
                }

            }
        });

    }



    private void initAction() {

    }
}
