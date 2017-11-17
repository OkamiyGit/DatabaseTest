package com.test.okamiy.anotherbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * 本地广播安全、高效，属于局部广播，在App内部才有效
 */
public class MainActivity extends AppCompatActivity {
    private LocalBroadcastManager mLocalBroadcastManager;
    private LocalReceiver mLocalReceiver;
    private IntentFilter mFilter;
    private static final String LOCAL_ACTION = "com.test.okamiy.broadcast.LOCAL_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //点击发送本地广播
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LOCAL_ACTION);
                //发送广播
                mLocalBroadcastManager.sendBroadcast(intent);
            }
        });
    }

    /**
     * 广播接收者
     */
    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Received Local Broadcast!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 注册本地广播
     */
    @Override
    protected void onResume() {
        super.onResume();

        //获取本地广播实例，用来注册和发送广播
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(MainActivity.this);
        mFilter = new IntentFilter();
        mFilter.addAction(LOCAL_ACTION);
        mLocalReceiver = new LocalReceiver();
        //注册本地广播
        mLocalBroadcastManager.registerReceiver(mLocalReceiver, mFilter);
    }

    /**
     * 一定记得取消注册
     */
    @Override
    protected void onPause() {
        super.onPause();
        mLocalBroadcastManager.unregisterReceiver(mLocalReceiver);
    }
}
