package com.puyue.www.qiaoge.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import com.puyue.www.qiaoge.R;


/**
 * 验证弹窗
 */
public abstract class PromptDialog extends Dialog {
    Context mContext;

    public TextView tv_sure,hint;
    public TextView title;

    public PromptDialog(@NonNull Context context) {
        super(context, R.style.promptDialog);
        setContentView(R.layout.dialog_prompt);

        mContext = context;

        initView();
        initAction();
    }

    private void initView() {
        tv_sure= (TextView) findViewById(R.id.tv_sure);
        hint = (TextView) findViewById(R.id.hint);
        title = findViewById(R.id.title);
    }


    private void initAction() {
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Confirm();
            }
        });
    }

    public abstract void Confirm();


}
