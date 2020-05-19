package com.puyue.www.qiaoge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.puyue.www.qiaoge.model.User;
import com.qiyukf.unicorn.api.ImageLoaderListener;
import com.qiyukf.unicorn.api.OnBotEventListener;
import com.qiyukf.unicorn.api.StatusBarNotificationConfig;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.UnicornImageLoader;
import com.qiyukf.unicorn.api.YSFOptions;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixApplication;
import com.taobao.sophix.SophixEntry;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;
import com.tencent.bugly.crashreport.CrashReport;

public class SophixStubApplication extends SophixApplication {
    private final String TAG = "SophixStubApplication";
    public static YSFOptions ysfOptions;
    private User user;
    private static SophixStubApplication application;
    //    此处SophixEntry应指定真正的Application，并且保证RealApplicationStub类名不被混淆。
    @Keep
    @SophixEntry(QiaoGeApplication.class)
    static class RealApplicationStub {
    }

    public static SophixStubApplication getInstance() {
        return application;
    }

    public User getUser() {
        if (null == user) {
            user = new User("", "", "", "", "");
        }
        return user;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SophixManager.getInstance().queryAndLoadNewPatch();
        Unicorn.init(this, "32e2c3d171b7d70287c22876a5622022", options(), new UnicornImageLoader() {
            @Nullable
            @Override
            public Bitmap loadImageSync(String uri, int width, int height) {
                return null;
            }

            @Override
            public void loadImage(String uri, int width, int height, ImageLoaderListener listener) {
                RequestOptions options = new RequestOptions()
//                        .placeholder(R.drawable.placeholder)
                        .centerCrop();
//                .error(R.drawable.error);
                if (width <= 0 || height <= 0) {
                    width = height = Integer.MIN_VALUE;
                }

                Glide.with(SophixStubApplication.this).asBitmap().load(uri).apply(options)
                        .into(new SimpleTarget<Bitmap>(width, height) {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                if (listener != null) {
                                    listener.onLoadComplete(resource);
                                }
                            }

                            @Override
                            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                                super.onLoadFailed(errorDrawable);
                                Throwable t = new Throwable("加载异常");
                                listener.onLoadFailed(t);
                            }
                        });
            }
        });
    }

    /**
     * 网易七鱼客服
     *
     * @return
     */
    private YSFOptions options() {
        YSFOptions options = new YSFOptions();
        /**
         * 客服消息通知
         */
        options.statusBarNotificationConfig = new StatusBarNotificationConfig();
        options.statusBarNotificationConfig.notificationSmallIconId = R.mipmap.ic_launcher;
        options.onBotEventListener = new OnBotEventListener() {
            @Override
            public boolean onUrlClick(Context context, String url) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(intent);
                return true;
            }
        };

        ysfOptions = options;
        return options;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        如果需要使用MultiDex，需要在此处调用。
        MultiDex.install(this);

        initSophix();
    }

    private void initSophix() {
        String appVersion = "1.0.0";
        try {
            appVersion = this.getPackageManager()
                    .getPackageInfo(this.getPackageName(), 0)
                    .versionName;
        } catch (Exception e) {
        }
        final SophixManager instance = SophixManager.getInstance();
        instance.setContext(this)
                .setAppVersion(appVersion)
                .setAesKey(null)
                .setSecretMetaData(null, null, null)
                .setEnableDebug(true)
                .setEnableFullLog()
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {

                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 如果需要在后台重启，建议此处用SharePreference保存状态。
                            //创建一个SharePreferences接口的实例对象，将生成一个XML名称为DATA_RELOAD，模式为MODE_PRIVATE
                            SharedPreferences sp= getSharedPreferences("DATA_RELOAD",MODE_PRIVATE);
                            //通过edit()方法创建一个SharePreferences.Editor类的实例对象
                            SharedPreferences.Editor editor =sp.edit();
                            //通过putString()方法，将数据存入文件中
                            editor.putBoolean("isReload",true);
                            //用commit()方法予以正式提交
                            editor.commit();
                        }
                    }
                }).initialize();
    }


}
