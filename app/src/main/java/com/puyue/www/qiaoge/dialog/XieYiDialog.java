package com.puyue.www.qiaoge.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;

/**
 * Created by ${王涛} on 2020/1/2
 */
public abstract class XieYiDialog extends Dialog {
    Context mContext;

    public TextView tv_sure,hint;
    TextView tv_cancle;
    public TextView title;
    WebView webView;
    String url = "https://shaokao.qoger.com/apph5/html/czxy.html";
    public XieYiDialog(@NonNull Context context) {
        super(context, R.style.promptDialog);
        setContentView(R.layout.dialog_xieyi);

        mContext = context;

        initView();
        initAction();
    }

    private void initView() {
        tv_sure= (TextView) findViewById(R.id.tv_sure);
        webView = (WebView) findViewById(R.id.webView);
        title = findViewById(R.id.title);
        tv_cancle = findViewById(R.id.tv_cancle);
        webView.loadUrl(url);
    }


    private void initAction() {
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Confirm();
            }
        });

        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cancle();
            }
        });
    }



    public abstract void Confirm();
    public abstract void Cancle();
}
