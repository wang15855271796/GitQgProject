package com.puyue.www.qiaoge;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.puyue.www.qiaoge.activity.HomeActivity;
import com.puyue.www.qiaoge.activity.mine.IntegralPayActivity;
import com.puyue.www.qiaoge.activity.mine.coupons.MyCouponsActivity;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.GoToMarketEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.AppSafeHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.NewWebModel;
import com.puyue.www.qiaoge.model.mine.wallet.NewWebPhoneModel;
import com.puyue.www.qiaoge.view.CompatToolbar;
import com.puyue.www.qiaoge.wxapi.MyWebView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by ${daff}
 * on 2018/10/18
 * 备注
 */
public class NewWebViewActivity extends BaseSwipeActivity {

    private TextView mTvTitle;
    private ProgressBar mProgress;
    private ImageView mIvBack;

    private MyWebView mWv;
    // private WebView mWv;
    private String mUrl;
    private String urlRequest;
    private String[] arg;
    private String callback;
    private int type;
    private String mShareTitle;
    private String mShareDesc;
    private String mShareIcon;
    private String mShareUrl;
    private Uri requestURl;
    private CompatToolbar toolbar_h5;
    private static final int CHOOSE_REQUEST_CODE = 0x9001;
    public ValueCallback<Uri[]> uploadMessageAboveL;
    private ValueCallback<Uri> mUploadMessage;// 表单的数据信息
    private Uri imageUri;
    private final static int PHOTO_REQUEST = 100;


    private final static int FILECHOOSER_RESULTCODE = 1;// 表单的结果回调</span>

    private String name;


    private int index;

    private List<String> list;
    private int iphone;


    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_h5);
    }

    @Override
    public void findViewById() {
        mIvBack = (ImageView) findViewById(R.id.iv_h5_back);
        mWv = findViewById(R.id.frm_h5);
        mProgress = (ProgressBar) findViewById(R.id.progress_h5);
        mTvTitle = (TextView) findViewById(R.id.tv_h5_title);
        toolbar_h5 = (CompatToolbar) findViewById(R.id.toolbar_h5);


    }

    @Override
    public void setViewData() {
        mUrl = getIntent().getStringExtra("URL");
        type = getIntent().getIntExtra("TYPE", 0);
        name = getIntent().getStringExtra("name");
        if (name.equals("consult") || name.equals("协议")) {
            toolbar_h5.setVisibility(View.GONE);
            //调整距离
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 60, 0, 0);
            lp.width = LinearLayout.LayoutParams.MATCH_PARENT;
            lp.height = LinearLayout.LayoutParams.MATCH_PARENT;
            mWv.setLayoutParams(lp);
        } else {

            toolbar_h5.setVisibility(View.VISIBLE);
            mTvTitle.setText("登录");
        }


        WebSettings settings = mWv.getSettings();
        settings.setSavePassword(false);
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowContentAccess(true); // 是否可访问Content Provider的资源，默认值 true
        settings.setSupportMultipleWindows(false);//这里一定得是false,不然打开的网页中，不能在点击打开了


        settings.setDomStorageEnabled(true);
        settings.setAppCacheMaxSize(1024 * 1024 * 8);
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        settings.setAppCachePath(appCachePath);
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);

        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setSupportZoom(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mWv.addJavascriptInterface(new JsIntegral(), "discountJumpToPay");
        mWv.addJavascriptInterface(new JsIntegralUser(), "shopListJumpToPay");
        mWv.addJavascriptInterface(new JsShare(), "sharePlatformJumpToPay");
        mWv.addJavascriptInterface(new JsLoging(), "goLoginJumpToPay");
        mWv.addJavascriptInterface(new JsVIPJumpToPay(), "vipJumpToPay");
        mWv.addJavascriptInterface(new JsQuestToHomeActivity(), "goBack");

        mWv.addJavascriptInterface(new JsPhone(), "goPhone");
        mWv.addJavascriptInterface(new JsImageDetail(), "showImg");


        mWv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

                mTvTitle.setText(title);
            }


            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {


                uploadMessageAboveL = filePathCallback;

              /*  Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "Image Chooser"), 15);*/


                //调起摄像头
                take();

                return true;
            }


            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onPermissionRequest(PermissionRequest request) {
                //                super.onPermissionRequest(request);//必须要注视掉
                request.grant(request.getResources());
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress != 100) {
                    mProgress.setProgress(newProgress);
                    mProgress.setVisibility(View.VISIBLE);
                } else {
                    mProgress.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }


        });
        mWv.setWebViewClient(new WebViewClient() {

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                Log.d("---request->", request.getUrl() + "");
                requestURl = request.getUrl();
                if (request.getUrl().toString().contains("callback")) {
                    String[] strings1 = request.getUrl().toString().split("[?]");

                    if (request.getUrl().toString().contains("protocol")) {
                        //  urlRequest = strings1[0].split("ajax://")[1];
                        urlRequest = "https://"+strings1[0].split("://")[1];
                    } else {
                        urlRequest = "http://"+strings1[0].split("://")[1];
                    }


                    arg = strings1[1].split("&");
                    requestUrl();
                }
                return super.shouldInterceptRequest(view, request);


            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
        });

        mWv.loadUrl(mUrl);

    }


    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                if (mWv.canGoBack()) {
                    mWv.goBack();
                } else {
                    finish();
                }
            }
        });
    }


    /**
     * post请求方式，请求网络数据
     */
    public void requestUrl() {
        String stime = AppSafeHelper.getSTime();
        //创建网络处理的对象
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
        //post请求来获得数据
        FormBody.Builder builder = new FormBody.Builder();
        for (int i = 0; i < arg.length; i++) {
            if (arg[i].split("=")[0].equals("callback")) {
                callback = arg[i].split("=")[1];
            }

            builder.add(arg[i].split("=")[0], arg[i].split("=")[1]);

        }


        RequestBody body = builder
                // 以下是公共参数
                .add(AppConstant.APP_TYPE, "1")
                .add(AppConstant.VERSION, AppHelper.getVersion(mContext))
                .add(AppConstant.STIME, stime)
                .add(AppConstant.SIGN, AppSafeHelper.sign(stime))
                .add(AppConstant.TOKEN, UserInfoHelper.getUserId(mContext))
                .build();
        Request request = new Request.Builder()
                .url(urlRequest)
                .post(body).build();
        Log.d("------>urlRequest", urlRequest + "request" + request);
        //创建一个能处理请求数据的操作类
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("--response-请求失败-->", e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String url = "javascript:" + callback + "('" + response.body().string() + "')";
                mWv.post(new Runnable() {
                    @Override
                    public void run() {
                        mWv.loadUrl(url);
                        Log.i("wwburl", "run: " + url);

                    }
                });

            }
        });
    }
/*private int position;

    private void addImageClickListener(WebView webView) {
        //"cc_detail_blog_img"这个ClassName和前端对应的，前端那边不能修改，对应的是img的ClassName
        for (int i = 0; i < list.size(); i++) {
            webView.loadUrl(list.get(i));
            position=i;
            webView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppHelper.showPhotoDetailDialog(mContext, list, position);
                }
            });
        }

    }*/


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWv.canGoBack()) {
            mWv.goBack();//返回上个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);//退出H5所在的Activity
    /*

        if (mWv.canGoBack()) {
            mWv.goBack();
        } else {
            finish();
        }
        return super.onKeyDown(keyCode, event);*/

    }

    @Override
    protected void onDestroy() {
        mWv.destroy();
        super.onDestroy();
    }

    public class JsImageDetail extends Object {
        @JavascriptInterface
        public void OnClick(String msg) {
            NewWebPhoneModel model = new Gson().fromJson(msg, NewWebPhoneModel.class);

            if (msg != null) {
                //showImageDialog(model.getIndex(), model.getList());
                list = model.list;
                index = model.index;
//点击图片详情
                Message message = new Message();

                message.what = 1;
                myHandler.sendMessage(message);
                //    showImageDialog(list, index);

                //
            }


        }
    }

    public class JsPhone extends Object {
        @JavascriptInterface
        public void OnClick(String msg) {
            if (msg != null) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + msg);
                intent.setData(data);
                mContext.startActivity(intent);
            }
        }
    }

    public class JsIntegral extends Object {


        @JavascriptInterface
        public void OnClick() {
            startActivity(MyCouponsActivity.getIntent(mContext, MyCouponsActivity.class));
        }
    }

    public class JsShare extends Object {


        @JavascriptInterface
        public void OnClick(String msg) {

            NewWebModel model = new Gson().fromJson(msg, NewWebModel.class);
            mShareTitle = model.getTitle();

            mShareDesc = model.getDescribe();
            mShareIcon = model.getPic();
            mShareUrl = model.getLink();

            showInviteDialog();


        }
    }

    public class JsLoging extends Object {
        @JavascriptInterface
        public void OnClick() {
            startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));

        }
    }

    public class JsQuestToHomeActivity extends Object {
        @JavascriptInterface
        public void OnClick() {
            startActivity(HomeActivity.getIntent(mActivity, HomeActivity.class));

        }
    }

    public class JsVIPJumpToPay extends Object {
        @JavascriptInterface
        public void OnClick(String msg) {
            //我的积分
            Intent intent = new Intent(NewWebViewActivity.this, IntegralPayActivity.class);
            intent.putExtra("vipPackageId", msg);
            startActivity(intent);
        }
    }


    public class JsIntegralUser extends Object {

        // 定义JS需要调用的方法
        // 被JS调用的方法必须加入@JavascriptInterface注解
        @JavascriptInterface
        public void OnClick() {

            startActivity(new Intent(mContext, HomeActivity.class));
            EventBus.getDefault().post(new GoToMarketEvent());
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        setTranslucentStatus();
    }

    protected void setTranslucentStatus() {
        // 5.0以上系统状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    /**
     * 分享
     */

    private File file;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage && null == uploadMessageAboveL)

                return;

            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (uploadMessageAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (mUploadMessage != null) {

                if (result != null) {
                    String path = getPath(getApplicationContext(),
                            result);

                    Uri uri = Uri.fromFile(new File(path));

                    mUploadMessage.onReceiveValue(uri);
                } else {
                    mUploadMessage.onReceiveValue(imageUri);
                }
                mUploadMessage = null;


            }
        } else {
            UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        }


    }


    Handler myHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case 1:
                    AppHelper.showPhotoDetailDialog(mContext, list, index);
                    break;
            }
         /*   ImageDialogPopuwindow imageDialogPopuwindow = new ImageDialogPopuwindow(mContext, index, list.get(index));
            imageDialogPopuwindow.showAtLocation(mWv,Gravity.NO_GRAVITY,0,0);*/
            //  Glide.with(mContext).load(list.get(index)).into(imageView);

            super.handleMessage(msg);
        }
    };


    // 分享
    private void showInviteDialog() {
        final Dialog dialog = new Dialog(mContext, R.style.Theme_Light_Dialog);
        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.popwindow_invite, null);
        //获得dialog的window窗口
        Window window = dialog.getWindow();
        //设置dialog在屏幕底部
        window.setGravity(Gravity.BOTTOM);
        //设置dialog弹出时的动画效果，从屏幕底部向上弹出
        window.setWindowAnimations(R.style.dialogStyle);
        window.getDecorView().setPadding(0, 0, 0, 0);
        //获得window窗口的属性
        WindowManager.LayoutParams lp = window.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度为包裹内容
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //将设置好的属性set回去
        window.setAttributes(lp);
        //将自定义布局加载到dialog上
        dialog.setContentView(dialogView);
        LinearLayout mLlInviteQQ = dialogView.findViewById(R.id.ll_invite_dialog_qq);
        LinearLayout mLlInviteWxCircle = dialogView.findViewById(R.id.ll_invite_dialog_wxcircle);
        LinearLayout mLlInviteWeChat = dialogView.findViewById(R.id.ll_invite_dialog_wechat);
        TextView mTvInviteText = dialogView.findViewById(R.id.tv_invite_dialog_text);
        TextView mTvInviteCancel = dialogView.findViewById(R.id.tv_invite_dialog_cancel);
        dialog.show();
        mLlInviteQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UMWeb umWeb = new UMWeb(mShareUrl);

                umWeb.setDescription(mShareDesc);
                umWeb.setThumb(new UMImage(NewWebViewActivity.this, mShareIcon));
                umWeb.setTitle(mShareTitle);

                new ShareAction(NewWebViewActivity.this)
                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                        .withMedia(umWeb)//分享内容
                        .setCallback(umShareListener)//回调监听器
                        .share();
            }
        });


        mLlInviteWeChat.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                if (StringHelper.notEmptyAndNull(mShareTitle)
                        && StringHelper.notEmptyAndNull(mShareDesc)
                        && StringHelper.notEmptyAndNull(mShareIcon)
                        && StringHelper.notEmptyAndNull(mShareUrl)) {

                    UMWeb umWeb = new UMWeb(mShareUrl);
                    umWeb.setDescription(mShareDesc);
                    umWeb.setThumb(new UMImage(NewWebViewActivity.this, mShareIcon));
                    umWeb.setTitle(mShareTitle);
                    new ShareAction(NewWebViewActivity.this)
                            .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                            .withMedia(umWeb)//分享内容
                            .setCallback(umShareListener)//回调监听器
                            .share();
                } else {
                    Toast.makeText(NewWebViewActivity.this, "数据不全!", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        mLlInviteWxCircle.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                if (StringHelper.notEmptyAndNull(mShareTitle)
                        && StringHelper.notEmptyAndNull(mShareDesc)
                        && StringHelper.notEmptyAndNull(mShareIcon)
                        && StringHelper.notEmptyAndNull(mShareUrl)) {
                    UMWeb umWeb = new UMWeb(mShareUrl);
                    umWeb.setDescription(mShareDesc);
                    umWeb.setThumb(new UMImage(NewWebViewActivity.this, mShareIcon));
                    umWeb.setTitle(mShareTitle);
                    new ShareAction(NewWebViewActivity.this)
                            .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                            .withMedia(umWeb)//分享内容
                            .setCallback(umShareListener)//回调监听器
                            .share();
                } else {
                    Toast.makeText(NewWebViewActivity.this, "数据不全!", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

        mTvInviteCancel.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                dialog.dismiss();
            }
        });
    }


    /**
     * 分享回调
     */
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(NewWebViewActivity.this, " 收藏成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(NewWebViewActivity.this, " 分享成功", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
//            Toast.makeText(MyInviteActivity.this, " 分享失败啦", Toast.LENGTH_SHORT).show();
//            if (t != null) {
//            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
//            Toast.makeText(MyInviteActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };


    @SuppressWarnings("null")
    @TargetApi(Build.VERSION_CODES.BASE)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent data) {
        if (requestCode != FILECHOOSER_RESULTCODE
                || uploadMessageAboveL == null) {
            return;
        }


        Uri[] results = null;

        if (resultCode == Activity.RESULT_OK) {

            if (data == null) {

                results = new Uri[]{imageUri};
            } else {
                String dataString = data.getDataString();
                ClipData clipData = data.getClipData();

                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }

                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        if (results != null) {
            uploadMessageAboveL.onReceiveValue(results);
            uploadMessageAboveL = null;
        } else {
            //注释之后取消不会默认
            //results = new Uri[]{imageUri};
            uploadMessageAboveL.onReceiveValue(results);
            uploadMessageAboveL = null;
        }

        return;
    }

    private void take() {
        File imageStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyApp");
        // Create the storage directory if it does not exist
        //创建目录
        if (!imageStorageDir.exists()) {
            imageStorageDir.mkdirs();
        }
        File file = new File(imageStorageDir + File.separator + "IMG_" + String.valueOf(System.currentTimeMillis()) + ".jpg");
        imageUri = Uri.fromFile(file);

        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent i = new Intent(captureIntent);
            i.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            i.setPackage(packageName);
            i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            cameraIntents.add(i);

        }
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        Intent chooserIntent = Intent.createChooser(i, "Image Chooser");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));
        NewWebViewActivity.this.startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE);
    }

    @SuppressLint("NewApi")
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPath(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {

                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    //删除图片后同步刷新媒体库
    public static void updateFileFromDatabase(Context context, File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            String[] paths = new String[]{Environment.getExternalStorageDirectory().toString()};
            MediaScannerConnection.scanFile(context, paths, null, null);
            MediaScannerConnection.scanFile(context, new String[]{
                            file.getAbsolutePath()},
                    null, new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                        }
                    });
        } else {
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
        }
    }
}



