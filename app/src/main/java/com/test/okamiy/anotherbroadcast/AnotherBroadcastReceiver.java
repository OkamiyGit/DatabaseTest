package com.test.okamiy.anotherbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Okamiy on 2017/11/16.
 * Email: 542839122@qq.com
 */

public class AnotherBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.example.mycast.MyBroadcastReceiver")) {
            //获取广播的原始信息
            String msg = intent.getStringExtra("msg");
            //获取广播经过修改的信息
            Bundle bundle = getResultExtras(true);
            String change = bundle.getString("msg");
            //显示2种信息
            Toast.makeText(context, "Received in AnotherBroadcastReceiver!" + change + "," + msg, Toast.LENGTH_SHORT).show();
        }
    }
}
