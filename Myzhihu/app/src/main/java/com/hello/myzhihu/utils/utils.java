package com.hello.myzhihu.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.hello.myzhihu.MyApplication;
import com.hello.myzhihu.R;

import java.util.ArrayList;

/**
 * Created by hello on 2016/8/22.
 */
public class utils {





    public  static void ShowToast(Context contex, String s){
        Toast.makeText(contex,s, Toast.LENGTH_SHORT).show();
    }
    public static  void  setPref(int  theme){
        SharedPreferences config = MyApplication.context.getSharedPreferences("config", Context.MODE_PRIVATE);
        config
                .edit()
                .putInt("theme",theme)
                .commit();
    }
    public static int getPref(){
        SharedPreferences config = MyApplication.context.getSharedPreferences("config", Context.MODE_PRIVATE);
        if (config != null) {
            int theme = config.getInt("theme", R.style.MyAppTheme);
            return theme;

        }else {
            return  R.style.MyAppTheme;
        }




    }
}
