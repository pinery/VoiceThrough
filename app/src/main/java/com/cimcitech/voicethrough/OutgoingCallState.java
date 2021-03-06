package com.cimcitech.voicethrough;

/**
 * Created by cimcitech on 2018/8/8.
 */

import android.content.Context;
import android.util.Log;

public class OutgoingCallState {
    Context ctx;

    public OutgoingCallState(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * 前台呼叫状态
     *
     * @author sdvdxl
     */
    public static final class ForeGroundCallState {
        public static final String DIALING =
                "com.cimcitech.voicethrough.FORE_GROUND_DIALING";
        public static final String ALERTING =
                "com.cimcitech.voicethrough.FORE_GROUND_ALERTING";
        public static final String ACTIVE =
                "com.cimcitech.voicethrough.FORE_GROUND_ACTIVE";
        public static final String IDLE =
                "com.cimcitech.voicethrough.FORE_GROUND_IDLE";
        public static final String DISCONNECTED =
                "com.cimcitech.voicethrough.FORE_GROUND_DISCONNECTED";
    }

    /**
     * 开始监听呼出状态的转变，
     * 并在对应状态发送广播
     */
    public void startListen() {
        new ReadLog(ctx).start();
        Log.d("Recorder", "开始监听呼出状态的转变，并在对应状态发送广播");
    }

}
