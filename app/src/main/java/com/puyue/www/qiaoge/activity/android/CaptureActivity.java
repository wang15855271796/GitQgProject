package com.puyue.www.qiaoge.activity.android;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.camera.CameraManager;
import com.puyue.www.qiaoge.activity.view.ViewfinderView;
import com.puyue.www.qiaoge.api.driver.DriverCheckOrderAPI;
import com.puyue.www.qiaoge.api.driver.DriverScanOrderAPI;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.model.driver.DriverCheckModel;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 这个activity打开相机，在后台线程做常规的扫描；它绘制了一个结果view来帮助正确地显示条形码，在扫描的时候显示反馈信息，
 * 然后在扫描成功的时候覆盖扫描结果
 */
public final class CaptureActivity extends Activity implements
        SurfaceHolder.Callback {

    private static final String TAG = CaptureActivity.class.getSimpleName();

    // 相机控制
    private CameraManager cameraManager;
    private CaptureActivityHandler handler;
    private ViewfinderView viewfinderView;
    private boolean hasSurface;
    private IntentSource source;
    private Collection<BarcodeFormat> decodeFormats;
    private Map<DecodeHintType, ?> decodeHints;
    private String characterSet;
    // 电量控制
    private InactivityTimer inactivityTimer;
    // 声音、震动控制
    private BeepManager beepManager;

    private ImageButton imageButton_back;


    private ImageView iv_input_order;

    private TextView tv_one;


    private String orderNum;


    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public CameraManager getCameraManager() {
        return cameraManager;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();
    }

    /**
     * OnCreate中初始化一些辅助类，如InactivityTimer（休眠）、Beep（声音）以及AmbientLight（闪光灯）
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // 保持Activity处于唤醒状态
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.capture);

        hasSurface = false;

        inactivityTimer = new InactivityTimer(this);
        beepManager = new BeepManager(this);

        imageButton_back = (ImageButton) findViewById(R.id.capture_imageview_back);
        iv_input_order = (ImageView) findViewById(R.id.iv_input_order);
        tv_one = (TextView) findViewById(R.id.tv_one);
        imageButton_back.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Toast.makeText(CaptureActivity.this, "dianjil", Toast.LENGTH_SHORT).show();
            }
        });

        iv_input_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputOrderNum();
            }
        });
    }

    private TextView tv_no_order;

    private void showInputOrderNum() {

        AlertDialog dialog = new AlertDialog.Builder(CaptureActivity.this, R.style.DialogStyle).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams p = window.getAttributes();
        p.gravity = Gravity.CENTER;

        window.setAttributes(p);


        window.setContentView(R.layout.input_order_num);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        EditText et_order_num = window.findViewById(R.id.et_order_num);
        TextView tv_ok = window.findViewById(R.id.tv_ok);
        tv_no_order = window.findViewById(R.id.tv_no_order);


        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_order_num.getText() != null) {
                    orderNum = et_order_num.getText().toString();
                    checkDriver();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        // CameraManager必须在这里初始化，而不是在onCreate()中。
        // 这是必须的，因为当我们第一次进入时需要显示帮助页，我们并不想打开Camera,测量屏幕大小
        // 当扫描框的尺寸不正确时会出现bug
        cameraManager = new CameraManager(getApplication());

        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
        viewfinderView.setCameraManager(cameraManager);

        handler = null;

        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            // activity在paused时但不会stopped,因此surface仍旧存在；
            // surfaceCreated()不会调用，因此在这里初始化camera
            initCamera(surfaceHolder);
        } else {
            // 重置callback，等待surfaceCreated()来初始化camera
            surfaceHolder.addCallback(this);
        }


        beepManager.updatePrefs();
        inactivityTimer.onResume();

        source = IntentSource.NONE;
        decodeFormats = null;
        characterSet = null;
    }

    @Override
    protected void onPause() {
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        inactivityTimer.onPause();
        beepManager.close();
        cameraManager.closeDriver();
        if (!hasSurface) {
            SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
            SurfaceHolder surfaceHolder = surfaceView.getHolder();
            surfaceHolder.removeCallback(this);
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }


    /**
     * 扫描成功，处理反馈信息
     *
     * @param rawResult
     * @param barcode
     * @param scaleFactor
     */
    public void handleDecode(Result rawResult, Bitmap barcode, float scaleFactor) {
        inactivityTimer.onActivity();

        boolean fromLiveScan = barcode != null;
        //这里处理解码完成后的结果，此处将参数回传到Activity处理
        if (fromLiveScan) {
            closeCamera();
            beepManager.playBeepSoundAndVibrate();
            Toast.makeText(this, "扫描成功", Toast.LENGTH_SHORT).show();
            orderNum = rawResult.getText();
            checkDriver();

          restartCamera();


/*
            Intent intent = getIntent();
            intent.putExtra("codedContent", rawResult.getText());
            intent.putExtra("codedBitmap", barcode);
            setResult(RESULT_OK, intent);
            finish();*/
        }

    }

    /**
     * 初始化Camera
     *
     * @param surfaceHolder
     */
    private void initCamera(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        }
        if (cameraManager.isOpen()) {
            return;
        }
        try {
            // 打开Camera硬件设备
            cameraManager.openDriver(surfaceHolder);
            // 创建一个handler来打开预览，并抛出一个运行时异常
            if (handler == null) {
                handler = new CaptureActivityHandler(this, decodeFormats,
                        decodeHints, characterSet, cameraManager);
            }
        } catch (IOException ioe) {
            Log.w(TAG, ioe);
            displayFrameworkBugMessageAndExit();
        } catch (RuntimeException e) {
            Log.w(TAG, "Unexpected error initializing camera", e);
            displayFrameworkBugMessageAndExit();
        }
    }

    /**
     * 显示底层错误信息并退出应用
     */
    private void displayFrameworkBugMessageAndExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage(getString(R.string.msg_camera_framework_bug));
        builder.setPositiveButton(R.string.button_ok, new FinishListener(this));
        builder.setOnCancelListener(new FinishListener(this));
        builder.show();
    }

    private void checkDriver() {
        DriverCheckOrderAPI.requestData(this, orderNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DriverCheckModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DriverCheckModel driverCheckModel) {

                        if (driverCheckModel.isSuccess()) {

                            if (driverCheckModel.getData().isDriverIsRight()) {
                                String returnOrderId = driverCheckModel.getData().getOrderId();
                                ScanOrder(returnOrderId);
                            } else {
                                AlertDialog dialog = new AlertDialog.Builder(CaptureActivity.this, R.style.DialogStyle).create();
                                dialog.setCanceledOnTouchOutside(true);
                                dialog.show();
                                Window window = dialog.getWindow();
                                WindowManager.LayoutParams p = window.getAttributes();
                                p.gravity = Gravity.CENTER;

                                window.setAttributes(p);


                                window.setContentView(R.layout.scan_check_driver_dialog);

                                TextView tvNum = window.findViewById(R.id.tv_order_num);
                                TextView tv_cancel = window.findViewById(R.id.tv_cancel);
                                TextView tv_driver_name = window.findViewById(R.id.tv_driver_name);
                                TextView tv_ok = window.findViewById(R.id.tv_ok);
                                RelativeLayout rl_scan = window.findViewById(R.id.rl_scan);
                                TextView tv_address = window.findViewById(R.id.tv_order_address);

                                tvNum.setText(driverCheckModel.getData().getPickNumber());
                                tv_address.setText(driverCheckModel.getData().getAddress());
                                tv_driver_name.setText(driverCheckModel.getData().getDriverName());
                                rl_scan.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                                tv_cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                                tv_ok.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        String returnOrderIdTwo = driverCheckModel.getData().getOrderId();
                                        ScanOrder(returnOrderIdTwo);
                                    }
                                });


                            }


                        } else {

                            AppHelper.showMsg(CaptureActivity.this, driverCheckModel.getMessage());
                            if (tv_no_order != null) {
                                tv_no_order.setText("无此订单号，请重新输入");
                            }

                        }


                    }
                });
    }


    private void ScanOrder(String returnOrderId) {
        DriverScanOrderAPI.requestData(this, returnOrderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DriverCheckModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DriverCheckModel driverCheckModel) {

                        if (driverCheckModel.isSuccess()) {
                            AlertDialog alertDialog = new AlertDialog.Builder(CaptureActivity.this, R.style.DialogStyle).create();
                            alertDialog.setCanceledOnTouchOutside(true);
                            alertDialog.show();
                            Window window = alertDialog.getWindow();
                            WindowManager.LayoutParams p = window.getAttributes();
                            p.gravity = Gravity.CENTER;
                            window.setAttributes(p);


                            window.setContentView(R.layout.scan_order_dialog);
                            TextView tvNum = window.findViewById(R.id.tv_order_num);
                            RelativeLayout rl_scan = window.findViewById(R.id.rl_scan);
                            TextView tv_address = window.findViewById(R.id.tv_order_address);

                            tvNum.setText(driverCheckModel.getData().getPickNumber());
                            tv_address.setText(driverCheckModel.getData().getAddress());

                            rl_scan.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alertDialog.dismiss();
                                }
                            });
                        }
                    }
                });

    }


    private void restartCamera(){
        viewfinderView.setVisibility(View.VISIBLE);
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        initCamera(surfaceHolder);
        // 恢复活动监控器
        inactivityTimer.onResume();
    }

    private void closeCamera(){
        //完全退出
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        inactivityTimer.onPause();
        // 关闭设备相机
        cameraManager.closeDriver();
    }

}
