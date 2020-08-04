package com.puyue.www.qiaoge.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;

import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.android.CaptureActivity;
import com.puyue.www.qiaoge.activity.home.MapActivity;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;

import com.puyue.www.qiaoge.api.driver.DriverSendLocationAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.AppSafeHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;

import com.puyue.www.qiaoge.model.mine.DriverReturnLoginAPI;
import com.puyue.www.qiaoge.view.CompatToolbar;
import com.puyue.www.qiaoge.wxapi.MyWebView;
import com.umeng.socialize.UMShareAPI;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王文博} on 2019/6/15
 */
public class WebDriverActivity extends BaseSwipeActivity {

//    private TextView mTvTitle;
    private ProgressBar mProgress;
    private ImageView mIvBack;

    private MyWebView mWv;

    MediaPlayer player;
    // private PosApi mPosSDK = null;
    private String mUrl;
    private String urlRequest;
    private String[] arg;
    // private ScanBroadcastReceiver scanBroadcastReceiver;
    public ValueCallback<Uri[]> uploadMessageAboveL;

    private Uri requestURl;

    private String callback;
    private String token;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    public LocationClient mLocationClient = null;

    private MyLocationListener myListener = new MyLocationListener();

    //BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口
//原有BDLocationListener接口暂时同步保留。具体介绍请参考后文中的说明
    private double latitude = 0.00;
    private double longitude = 0.00;
    private String locationMessage = "";


    private String orderNum;//订单编号


    private final static int FILECHOOSER_RESULTCODE = 1;// 表单的结果回调</span>
    private static final int CHOOSE_REQUEST_CODE = 0x9001;

    private ValueCallback<Uri> mUploadMessage;// 表单的数据信息
    private Uri imageUri;
    private final static int PHOTO_REQUEST = 100;

    private boolean isLogin = false;

    private int REQUEST_CODE_LOLIPOP = 1;  // 5.0以上版本
    private TextView tv_h5_title;
    private LinearLayout toolbar_h5;
    private Timer timer;


    double bdLon;
    double bdLat;
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_h5_driver);
    }

    @Override
    public void findViewById() {
        mIvBack = (ImageView) findViewById(R.id.iv_h5_back);
        mWv = findViewById(R.id.frm_h5);
        mProgress = (ProgressBar) findViewById(R.id.progress_h5);
        tv_h5_title = (TextView) findViewById(R.id.tv_h5_title);
        // mTvTitle = (TextView) findViewById(R.id.tv_h5_title);
        toolbar_h5 = (LinearLayout) findViewById(R.id.toolbar_h5);
    }

    @Override
    public void setViewData() {
        timer = new Timer();
        mUrl = getIntent().getStringExtra("URL");

        UserInfoHelper.saveDriverId(mContext, "");
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);

        option.setIsNeedAddress(true);
//可选，是否需要地址信息，默认为不需要，即参数为false
//如果开发者需要获得当前点的地址信息，此处必须为true


        option.setOpenGps(true);

//可选，设置是否使用gps，默认false
//使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
//可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(true);
//可选，定位SDK内部是一个service，并放到了独立进程。
//设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)
        option.setCoorType("bd09ll");
        option.SetIgnoreCacheException(false);
//可选，设置是否收集Crash信息，默认收集，即参数为false
        option.setScanSpan(10 * 1000);
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
//可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false


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
        settings.setDomStorageEnabled(true);
        settings.setGeolocationEnabled(true);

        mWv.addJavascriptInterface(new JsIsLogin(), "isLogin");

        mWv.addJavascriptInterface(new JsReturnLogin(), "returnLogout");
        mWv.addJavascriptInterface(new JsOpenScanning(), "openScanning");
        mWv.addJavascriptInterface(new JsOpenMap(), "openMap");
        mWv.addJavascriptInterface(new JsReturnLoading(), "returnLanding");

        //传当前地址信息
        //sendLocation();
        showCustomWebChromeClient();
        // tv_h5_title.setText("司机登录");
        mWv.setWebViewClient(new WebViewClient() {

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {

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
        //   name = getIntent().getStringExtra("name");


        mLocationClient.start();
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        timer.schedule(task, 0, 10 * 1000);

        IntentFilter filter = new IntentFilter();
        //锁屏广播，由系统发出
        filter.addAction(Intent.ACTION_SCREEN_ON);
        //锁屏广播，由系统发出
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        //点击home键广播，由系统发出
        filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        registerReceiver(homeAndLockReceiver, filter);

    }

    private boolean isOpen = false;
    /**
     * 监听是否点击了home键将客户端推到后台
     */
    private BroadcastReceiver homeAndLockReceiver = new BroadcastReceiver() {
        String SYSTEM_REASON = "reason";
        String SYSTEM_HOME_KEY = "homekey";

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_REASON);
                if (TextUtils.equals(reason, SYSTEM_HOME_KEY)) {
                    //表示按了home键,程序到了后台
                    //    Toast.makeText(getApplicationContext(), "home", Toast.LENGTH_SHORT).show();
                }
            } else if (action.equals(Intent.ACTION_SCREEN_ON)) {
                //屏幕亮了
                Log.i("lock-", "--on");

                isOpen = true;
            } else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
                //屏幕黑了
                Log.i("lock-", "--off");
                isOpen = false;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        if (task != null) {

        } else {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = 1;
                    handler1.sendMessage(message);
                }
            }, 0, 10 * 1000);

        }

        //  timer.schedule(task, 0, 30 * 1000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //  timer.schedule(task, 0, 30 * 1000);
    }

    @Override
    public void onPause() {
        //  timer.schedule(task, 0, 30 * 1000);
        super.onPause();
    }

    @Override
    public void setClickEvent() {


        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        Log.d("------>", urlRequest);
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
                    }
                });

            }
        });
    }

    public class JsOpenMap extends Object {
        @JavascriptInterface
        public void OnClick(String msg) {
            if (msg != null) {
                String location = msg;
                Intent intent = new Intent(mContext, MapActivity.class);
                intent.putExtra("addressDriver", location);
                startActivity(intent);

            }
            // startActivity(new Intent(mContext, MapActivity.class));
        }
    }

    public class JsReturnLogin extends Object {

        @JavascriptInterface
        public void OnClick() {
            goLogin();
        }
    }

    public class JsReturnLoading extends Object {

        @JavascriptInterface
        public void OnClick() {

            startActivity(new Intent(mContext, LoginActivity.class));
            finish();
        }
    }

    public class JsOpenScanning extends Object {

        @JavascriptInterface
        public void OnClick() {
            isLogin = false;
            //ScanDomn();
            startActivity(new Intent(mContext, CaptureActivity.class));

        }
    }

    public class JsIsLogin extends Object {

        @JavascriptInterface
        public void OnClick(String msg) {
           /* mIvBack.setVisibility(View.GONE);
            tv_h5_title.setVisibility(View.GONE);*/

            if (msg != null && StringHelper.notEmptyAndNull(msg)) {
              /*  mIvBack.setVisibility(View.GONE);
                tv_h5_title.setVisibility(View.GONE);*/
                isLogin = true;
                token = msg;
                UserInfoHelper.saveDriverId(mContext, token);

            /*    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0, 60, 0, 0);
                lp.width = LinearLayout.LayoutParams.MATCH_PARENT;
                lp.height = LinearLayout.LayoutParams.MATCH_PARENT;
                mWv.setLayoutParams(lp);*/
            } /*else {
                startActivity(new Intent(mContext, LoginActivity.class));
                finish();
            }*/
        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWv.canGoBack()) {
        /*    if (token != null) {
                startActivity(new Intent(mContext, LoginActivity.class));
                finish();
            } else {
                mWv.goBack();//返回上个页面
            }*/

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


        timer.cancel();
        // 注销获取扫描数据的广播
        // unregisterReceiver(receiver);

        // 注销物理SCAN按键的接收广播
        // unregisterReceiver(scanBroadcastReceiver);

        // 关闭下层串口以及打印机
        //  mPosSDK.closeDev();

        super.onDestroy();
    }

    public void goLogin() {
        DriverReturnLoginAPI.requestData(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        if (baseModel.success) {
                            UserInfoHelper.saveDriverId(mContext, "");
                            startActivity(new Intent(mContext, LoginActivity.class));
                            finish();
                        } else {
                            AppHelper.showMsg(mContext, baseModel.message);
                        }

                    }
                });


    }


    Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            // 要做的事情
            super.handleMessage(msg);
            if (msg.what == 1) {
                //定时上传地址

                sendLocation();
            }
        }
    };


    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            Message message = new Message();
            message.what = 1;
            handler1.sendMessage(message);
        }
    };

    private LatLng pianyi(double lon, double lat) {
        double x = lon;
        double y = lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * Math.PI);
        double temp = Math.atan2(y, x) + 0.000003 * Math.cos(x * Math.PI);

        double bdLon = z * Math.cos(temp) + 0.0065;
        double bdLat = z * Math.sin(temp) + 0.006;
        LatLng newcenpt = new LatLng(bdLat, bdLon);
        return newcenpt;
    }

    private void sendLocation() {
        if (longitude == 4.9E-324 || latitude == 4.9E-324 || latitude <=0.0 || latitude <= 0.0) {

        } else {

            DriverSendLocationAPI.requestData(mContext, longitude, latitude)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<BaseModel>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(BaseModel baseModel) {
                            if (baseModel.success) {


                            }

                        }
                    });

        }


    }


    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明


            String country = location.getCountry();    //获取国家
            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市
            String district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //获取街道信息
            String streetNumber = location.getStreetNumber();
            latitude = location.getLatitude();
            longitude = location.getLongitude();

            locationMessage = location.getAddrStr();    //获取详细地址信息



        }
    }


    /**
     * 初始化
     */


    // 检查读写权限
    public static void verifyStoragePermissions(Activity activity) {
        try {
            // 检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity,
                        PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


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
    private void showCustomWebChromeClient() {
        mWv.setWebChromeClient(new WebChromeClient() {

            // 5.0+
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                if (uploadMessageAboveL != null) {
                    uploadMessageAboveL.onReceiveValue(null);
                }
                uploadMessageAboveL = filePathCallback;

                Intent intent = take();  // 选择文件及拍照
                startActivityForResult(intent, REQUEST_CODE_LOLIPOP);
                return true;
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
//                mTvTitle.setText(title);
            }


            // 4.1+
            public void openFileChooser(ValueCallback<Uri> uploadFile, String acceptType, String capture) {

            }
        });
    }

    /**
     * 随机产生文件名
     */
    private String randomFileName() {
        return UUID.randomUUID().toString();
    }
    private String mCameraPhotoPath = "";  // 拍照的图片路径
    private Intent take() {

        String saveName = Environment.getExternalStorageDirectory().getPath() + "/" + Environment.DIRECTORY_DCIM + "/Camera/";

        /**
         * 打开相机intent
         */
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
            File photoFile = null;
            photoFile = new File(saveName + randomFileName() + ".jpg");
            if (!photoFile.exists()) {
                mCameraPhotoPath = "file:" + photoFile.getAbsolutePath();
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));  // 把Uri赋值给takePictureIntent
            } else {
                takePictureIntent = null;
            }
        }

        Intent[] takeoutArray = null;
        if (takePictureIntent != null) {
            takeoutArray = new Intent[]{takePictureIntent};
        } else {
            takeoutArray = new Intent[0];
        }

//        File imageStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyApp");
//        // Create the storage directory if it does not exist
//        //创建目录
//        if (!imageStorageDir.exists()) {
//            imageStorageDir.mkdirs();
//        }
//        File file = new File(imageStorageDir + File.separator + "IMG_" + String.valueOf(System.currentTimeMillis()) + ".jpg");
//        imageUri = Uri.fromFile(file);
//
//        final List<Intent> cameraIntents = new ArrayList<Intent>();
//        final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        final PackageManager packageManager = getPackageManager();
//        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
//        for (ResolveInfo res : listCam) {
//            final String packageName = res.activityInfo.packageName;
//            final Intent i = new Intent(captureIntent);
//            i.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
//            i.setPackage(packageName);
//            i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//            cameraIntents.add(i);
//
//        }
//         Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//                i.addCategory(Intent.CATEGORY_OPENABLE);
//                i.setType("image/*");
//                startActivityForResult(Intent.createChooser(i, "Image Chooser"), 15);

//        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//        i.addCategory(Intent.CATEGORY_OPENABLE);
//        i.setType("image/*");
//        Intent chooserIntent = Intent.createChooser(i, "Image Chooser");
//        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));
//        NewWebViewActivity.this.startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE);

        /**
         * 获取图片intent
         */
        Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
        contentSelectionIntent.setType("image/*");


        /**
         * 使用系统选择器
         */
        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, takeoutArray);  // 额外的intent

        return chooserIntent;


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_LOLIPOP) {  // 选择文件返回 5.0+
            Uri[] results = null;
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    if (mCameraPhotoPath != null) {
                        results = new Uri[]{Uri.parse(mCameraPhotoPath)};
                    }
                } else {
                    String dataString = data.getDataString();
                    if (dataString != null) {
                        results = new Uri[]{Uri.parse(dataString)};
                    }
                }
            }
            uploadMessageAboveL.onReceiveValue(results);  // 当获取要传图片的Uri，通过该方法回调通知
            uploadMessageAboveL = null;
        }
    }

}
