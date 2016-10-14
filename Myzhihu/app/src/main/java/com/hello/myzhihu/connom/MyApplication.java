package com.hello.myzhihu.connom;

import android.app.Application;
import android.content.Context;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.concurrent.TimeUnit;
import android.os.Handler;

import okhttp3.OkHttpClient;

/**
 * Created by hello on 2016/10/13.
 */

public class MyApplication extends Application {
    public static Context context = null;
    public static Handler handler = null;

    public void onCreate() {
        context = getApplicationContext();
        handler = new Handler();
        OkHttpUtils.initClient(new OkHttpClient.Builder().connectTimeout(10000L, TimeUnit.MILLISECONDS).readTimeout(10000L, TimeUnit.MILLISECONDS).build());
        super.onCreate();
    }


}
