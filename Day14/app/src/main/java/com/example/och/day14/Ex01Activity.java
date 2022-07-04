package com.example.och.day14;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*
    < 쓰레드 추가 방법 >
    1. Thread 클래스 사용
    2. Runnable 인터페이스 사용
    ==> run() 오버라이드
    ==> 필요한 시점에 start()
    ==> 안드로이드에서 Handler를 사용
        Thread, Runnable을 통해 UI 변경이 불가능
        ( UI 수정은 main 스레드에서만 수정 가능 )
 */

public class Ex01Activity extends AppCompatActivity {

    Button button;
    int i;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0) {
                if(i == 20) {
                    handler.removeMessages(0);
                    return;
                }
                sendEmptyMessageDelayed(0, 1000);
                button.setText(i++ + "초");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(0);
                Toast.makeText(Ex01Activity.this, "버튼 클릭됨", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
