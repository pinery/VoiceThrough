package com.cimcitech.voicethrough;

/**
 * Created by cimcitech on 2018/8/8.
 */

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class PhoneCallStateService extends Service {

    private OutgoingCallState outgoingCallState;
    private OutgoingCallReciver outgoingCallReciver;
    private MyRecorder recorder;

    @Override
    public void onCreate() {
        super.onCreate();

        //------以下应放在onStartCommand中，但2.3.5以下版本不会因service重新启动而重新调用--------
        //监听电话状态，如果是打入且接听 或者 打出 则开始自动录音
        //通话结束，保存文件到外部存储器上
        Log.d("Recorder", "正在监听中...");
        recorder = new MyRecorder();
        outgoingCallState = new OutgoingCallState(this);
        outgoingCallReciver = new OutgoingCallReciver(recorder);
        outgoingCallState.startListen();
        Toast.makeText(this, "服务已启动", Toast.LENGTH_LONG).show();

        //去电
        IntentFilter outgoingCallFilter = new IntentFilter();
        outgoingCallFilter.addAction(OutgoingCallState.ForeGroundCallState.IDLE);
        outgoingCallFilter.addAction(OutgoingCallState.ForeGroundCallState.DIALING);
        outgoingCallFilter.addAction(OutgoingCallState.ForeGroundCallState.ALERTING);
        outgoingCallFilter.addAction(OutgoingCallState.ForeGroundCallState.ACTIVE);
        outgoingCallFilter.addAction(OutgoingCallState.ForeGroundCallState.DISCONNECTED);

        outgoingCallFilter.addAction("android.intent.action.PHONE_STATE");
        outgoingCallFilter.addAction("android.intent.action.NEW_OUTGOING_CALL");

        //注册接收者
        registerReceiver(outgoingCallReciver, outgoingCallFilter);

        //来电
        TelephonyManager telMgr = (TelephonyManager) getApplicationContext().getSystemService(
                Context.TELEPHONY_SERVICE);
        telMgr.listen(new TelListener(), PhoneStateListener.LISTEN_CALL_STATE);


    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(outgoingCallReciver);
        Toast.makeText(
                this, "已关闭电话监听服务", Toast.LENGTH_LONG)
                .show();
        Log.d("Recorder", "已关闭电话监听服务");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

}