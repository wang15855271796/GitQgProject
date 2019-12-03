package com.puyue.www.qiaoge.popupwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;

/**
 * @author daff
 * @date 2018/9/22.
 * 备注  我的钱包输入金额弹窗
 */
public class MyWalletPopuWindow extends PopupWindow {
    private Context context;
    private View view;
    private EditText editText;
    private TextView textCancel;
    private ImageView button;

    private ViewOnclick Onclick;

    public MyWalletPopuWindow(Context context, ViewOnclick viewOnclick) {
        this.context = context;
        this.view = LayoutInflater.from(context).inflate(R.layout.item_my_waller_popuwindow, null);
        this.Onclick = viewOnclick;

        initView(Onclick);
    }



    private void initView(final ViewOnclick viewOnclick) {
        editText = view.findViewById(R.id.editText);
        textCancel = view.findViewById(R.id.textCancel);
        button = view.findViewById(R.id.button);
        showKeyboard(editText);
        button.setEnabled(false);
        button.setImageResource(R.mipmap.ic_edittext_button_no);
        textCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editText.getText().toString())) { // 为空
                    button.setEnabled(false);
                    button.setImageResource(R.mipmap.ic_edittext_button_no);
                } else { //不为空
                    button.setEnabled(true);
                    button.setImageResource(R.mipmap.ic_edittext_button);
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewOnclick.buttonOnclick(editText.getText().toString());
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
        // 设置弹出窗体显示时的动画，从底部向上弹出
        // this.setAnimationStyle(R.style.take_photo_anim);
    }

    public interface ViewOnclick {
        void buttonOnclick(String edit);

    } public void showKeyboard(EditText editText) {
        //其中editText为dialog中的输入框的 EditText
        if (editText != null) {
            //设置可获得焦点
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            //请求获得焦点
            editText.requestFocus();
            //调用系统输入法
            InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(editText, 0);
        }
    }
}
