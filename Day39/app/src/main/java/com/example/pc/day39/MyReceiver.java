package com.example.pc.day39;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import me.leolin.shortcutbadger.ShortcutBadger;

public class MyReceiver extends BroadcastReceiver {

    @Override // 필수 메서드 (외부에서 브로드 캐스트를 받으면 실행 됨)
    public void onReceive(Context context, Intent intent) {
        String channelId = "channelId";
        String channelName = "channelName";

        NotificationManager manager =
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        // 현재 sdk (디바이스의 안드로이드의 버전)가 오레오 이상이면
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 알림을 보내기 위한 채널 생성
            int priority = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(
                    channelId, channelName, priority
            );
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, channelId);
        Intent notificationIntent = new Intent(context, Ex01Activity.class);

        notificationIntent.setFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP
        );

        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        builder.setContentTitle("title test")
                .setContentText("content test")
                .setAutoCancel(true) // 알림창 클릭하면 없어지도록
                .setSmallIcon(R.mipmap.ic_launcher)
                .setVibrate(new long[]{0, 100, 500, 100})
                .setContentIntent(pendingIntent);
        ShortcutBadger.applyCount(context, ++Ex01Activity.count);
        manager.notify(0, builder.build());
    }
}
