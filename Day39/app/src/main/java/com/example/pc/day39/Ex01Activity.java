package com.example.pc.day39;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*
    < 브로드캐스트 >
    -> 앱(혹은 시스템)이 다른 앱에게 메세지 전송

    < 알림(notification) >
    -> 시스템으로부터 intent를 받으면 어플리케이션의 BroadcastReceiver를 통해
    -> 작업이 실행됨(이 부분에 알림창을 띄움)
 */

public class Ex01Activity extends AppCompatActivity implements View.OnClickListener {

    AlarmManager alarmManager;
    Button start, finish;
    static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);

        start = findViewById(R.id.start);
        finish = findViewById(R.id.finish);

        start.setOnClickListener(this);
        finish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent receiverIntent = null;
        PendingIntent pendingIntent = null;

        switch(v.getId()) {
            case R.id.start:
                // start 버튼 클릭 시 알람 실행
                Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();
                alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                receiverIntent = new Intent(Ex01Activity.this, MyReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(
                        this,
                        0,
                        receiverIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
                /*
                    PendingIntent.FLAG_UPDATE_CURRENT : 이미 인텐트가 열려있을 경우 내용만 갱신
                    PendingIntent.FLAG_ONESHOT : 단 한 번만 인텐트를 실행
                    PendingIntent.FLAG_CANCEL_CURRENT : 이미 실행된 인텐트를 취소하고 새로 생성
                    PendingIntent.FLAG_NO_CREATE : 인텐트가 실행 중이 아니더라도 새로 생성하지 않음
                 */

                long period = 1000 * 5;
                alarmManager.setRepeating(
                        AlarmManager.ELAPSED_REALTIME_WAKEUP, // 알람 타입
                        SystemClock.elapsedRealtime(), // 첫 실행 시간
                        period, // 반복 실행 간격
                        pendingIntent // 호출한 INTENT
                );
                /*
                    AlarmManager.ELAPSED_REALTIME_WAKEUP : 부팅 후 시간을 기준으로 측정한 시간
                    AlarmManager.ELAPSED_REALTIME : 부팅 후 시간을 기준으로 측정한 시간
                    AlarmManager.RTC_WAKEUP : UTC 표준시간(일상생활에 쓰는 시간)
                    AlarmManager.RTC : UTC 표준 시간(일상생활에 쓰는 시간)
                 */
                break;

            case R.id.finish:
                // 알람 종료
                Toast.makeText(this, "finished", Toast.LENGTH_SHORT).show();
                alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                receiverIntent = new Intent(Ex01Activity.this, MyReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(
                        Ex01Activity.this,
                        0,
                        receiverIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
                alarmManager.cancel(pendingIntent);
                break;
        }
    }
}
