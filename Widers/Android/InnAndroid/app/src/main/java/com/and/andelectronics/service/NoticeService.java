package com.and.andelectronics.service;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.and.andelectronics.Container;
import com.and.andelectronics.R;
import com.and.andelectronics.common.jinlib.EasyJsonList;
import com.and.andelectronics.common.util.AppUtil;
import com.and.andelectronics.common.util.DateUtil;
import com.and.andelectronics.infrastructure.interfaces.ILodgementService;
import com.and.andelectronics.infrastructure.model.lodgement.ReserveRequestData;
import com.and.andelectronics.infrastructure.model.lodgement.ReserveRoom;
import com.and.andelectronics.view.main.MainTabFragmentActivity;
import com.and.andelectronics.webservice.lodgement.LodgementServiceXml;

import java.util.ArrayList;

/**
 * Created by Won on 2018-02-11.
 */

public class NoticeService extends Service {

    private Context context;
    private EasyJsonList ejl;
    private Container container;
    private ILodgementService iLodgementService;

    public static final int NOTIFICATION_ID = 1;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Log.e("onCreate", "onCreate = NoticeService");
        this.container = (Container)getApplicationContext();
        this.iLodgementService = new LodgementServiceXml(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub

        Log.w("onStartCommand", "onStartCommand = NoticeService");
        this.context = this;
        new NoticeAllTask().execute();


        return START_NOT_STICKY;
        ///START_NOT_STICKY 앱을 종료했을 때 서비스 재시작 하지 않음 (
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    public class NoticeAllTask extends AsyncTask<Void, Void, Void> {
        private String message;
        private int resultCode;
        private ArrayList<ReserveRoom> reserveRoomList;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            ReserveRequestData reqData = new ReserveRequestData();
            reqData.setReserveDate(DateUtil.nowDateFormat("yyyy-MM-dd"));
            reqData.setReserveUdid(AppUtil.getIMEI(context));   //예약한 내역을 확인할 수 있게 각각 장치의 Udid를 받는다.
            reqData.setCompanyCode("");
            reqData.setReserveRoom("");

            reserveRoomList = iLodgementService.inquireCheckoutNotice(reqData);
            resultCode = iLodgementService.getResultCode();
            message = iLodgementService.getMessage();
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {

            if(resultCode == 0 && reserveRoomList != null){
                sendNotification(); //예약객실 인자로 넘겨서 객실번호랑 뜨게 하기 (for문돌리던)
            }

        }
    }

    private void sendNotification() {
        String title = "퇴실알림";
        String msg = "퇴실 5분전 입니다. 퇴실 준비를 해주시기 바랍니다.";
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(getApplicationContext(), MainTabFragmentActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this).setSmallIcon(R.drawable.app_icon).setContentTitle(title)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg).setAutoCancel(true)
                .setVibrate(new long[] { 0, 500 });

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

}