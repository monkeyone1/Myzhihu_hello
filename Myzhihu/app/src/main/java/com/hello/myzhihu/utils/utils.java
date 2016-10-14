package com.hello.myzhihu.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hello on 2016/8/22.
 */
public class utils {

    public  static void ShowToast(Context contex,String s){
        Toast.makeText(contex,s, Toast.LENGTH_SHORT).show();
    }
}
