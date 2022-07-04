package com.and.andelectronics.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Won on 2018-02-11.
 */

public class BackService extends Service {

    private static final String TAG = "BackService";
    private Context context = null;

    private Handler noticeHandler = new Handler();


    private int noticeTimerCount = 1000 * 60 ; // 밀리세컨 * 60초 * 5분

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        this.context = this;
        Log.v(TAG, "onCreate for BackService");
        // this.registerAlarm();
        this.noticeHandler.postDelayed(this.noticeRunnable, this.noticeTimerCount);


    }


    @Override
    public void onDestroy() {
        Log.v(TAG, "onDestroy for BackService");
        this.stopSelf();

        super.onDestroy();
        this.context = this;

    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        Log.v(TAG, "onStartCommand for BackService");

        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    private Runnable noticeRunnable = new Runnable() {
        public void run() {
            Intent intent = new Intent(context, NoticeService.class);
            context.startService(intent);
            // 핸들러로 딜레이주며 반복적으로 돌린다.
            noticeHandler.postDelayed(noticeRunnable, noticeTimerCount);
        }
    };

}
