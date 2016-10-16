package com.hello.myzhihu;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by hello on 2016/8/22.
 */
public class MyApplication extends Application {
    public static Context context = null;

    public static Handler handler = null;

  ;



    @Override
    public void onCreate() {

       context = getApplicationContext();
        handler = new Handler();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

        super.onCreate();
    }
}
