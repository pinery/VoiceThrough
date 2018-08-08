package com.cimcitech.voicethrough;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by cimcitech on 2018/8/8.
 */

public class MyApplication extends Application {

    IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_TICK);
    OutgoingCallReciver receiver = new OutgoingCallReciver();

    @Override
    public void onCreate() {
        super.onCreate();
        //注册广播
        registerReceiver(receiver, filter);
    }
}
