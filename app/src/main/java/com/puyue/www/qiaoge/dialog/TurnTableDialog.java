package com.puyue.www.qiaoge.dialog;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.WheelSurfView;
import com.puyue.www.qiaoge.activity.CommonH5Activity;
import com.puyue.www.qiaoge.fragment.cart.NumEvent;
import com.puyue.www.qiaoge.listener.RotateListener;
import com.puyue.www.qiaoge.utils.ToastUtil;
import com.puyue.www.qiaoge.view.LuckPan;
import com.puyue.www.qiaoge.view.LuckPanAnimEndCallBack;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ${王涛} on 2020/4/21
 */
public class TurnTableDialog extends Dialog {
    Context mContext;
    LuckPan pan;
    ImageView iv_start;
    private boolean isRunning =false;
    List<String> list;
    List<Integer> list1 = new ArrayList<>();
    List<String> list2 = new ArrayList<>();
    List<Bitmap> mListBitmap = new ArrayList<>();
    public TurnTableDialog(@NonNull Context context, List<String> list) {
        super(context, R.style.promptDialog);
        setContentView(R.layout.dialog_turn);
        mContext = context;
        this.list = list;
        initView();
        initAction();
    }

    private void initView() {

        for ( int i = 0; i < list.size(); i++ ) {
            if(i%2==0) {
                mListBitmap.add(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_quan_blues));
            }else {
                mListBitmap.add(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_quan_reds));
            }
        }

        //主动旋转一下图片
        mListBitmap = WheelSurfView.rotateBitmaps(mListBitmap);
        String[] array =new String[list.size()];
        list.toArray(array);
        //获取第三个视图
        final WheelSurfView wheelSurfView = findViewById(R.id.wheelSurfView);
        WheelSurfView.Builder build = new WheelSurfView.Builder()
                .setmDeses(list.toArray(array))
                .setmIcons(mListBitmap)
                .setmType(1)
                .setmTypeNum(list.size())
//                .setSize(list.size())
                .build();

        wheelSurfView.setConfig(build);

        //添加滚动监听
        wheelSurfView.setRotateListener(new RotateListener() {
            @Override
            public void rotateEnd(int position, String des) {
                isRunning = false;
                dismiss();
                TurnResultDialog turnResultDialog = new TurnResultDialog(mContext);
                turnResultDialog.show();

            }

            @Override
            public void rotating(ValueAnimator valueAnimator) {

            }

            @Override
            public void rotateBefore(ImageView goImg) {
                if(!isRunning) {
                    int position = 3;
                    wheelSurfView.startRotate(position);
                    isRunning = true;
                }
            }
        });
    }



    private void initAction() {

    }
}
