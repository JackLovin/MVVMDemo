package com.sunyard.commonutils;

import android.content.Context;
import android.widget.Toast;

/**
 * @package name：com.sunyard.commonutils
 * @describe
 * @anthor jokerlover
 * @email:shengj.chen@sunyard.com
 * @time 2020/9/18 2:56 PM
 */
public class MyToats {
//自定义toast
    public void  myToast(Context context,String content){

        Toast.makeText(context,content,Toast.LENGTH_SHORT).show();
    }
}
