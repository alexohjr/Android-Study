package com.example.och.day16;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ex02Activity extends AppCompatActivity {

    MyThread myThread;
    ProgressDialog pDialog; // 환형 프로그래스, 바형 프로그래스
    Button wheelButton, barButton;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            // msg 받기
            Bundle b = msg.getData();

            // value 값 받기
            int v = b.getInt("value");

            // 전달받은 value를 '진행상황'
            pDialog.setProgress(v); // Bar 형태 프로그래스에서만 적용
            pDialog.setMessage("Loading..." + v + "%");

            if(v > 100) {
                pDialog.dismiss();
                myThread.setCheck(false);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex02);

        wheelButton = findViewById(R.id.btn_progress_wheel);
        barButton = findViewById(R.id.btn_progress_bar);

        wheelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 프로그래스 다이얼로그 타이틀, 메세지 설정
                pDialog = ProgressDialog.show(Ex02Activity.this, "Title", "Loading...");

                // 취소 버튼으로 다이얼로그 닫기 방지
                pDialog.setCancelable(false);

                // 스레드 생성
                myThread = new MyThread(handler);

                // 스레드 시작
                myThread.start();
            }
        });

        barButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog = new ProgressDialog(Ex02Activity.this);

                // 프로그래스 스타일 설정 ( 디폴트값 :: STYLE_SPINNER )
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

                // 타이틀 설정
                pDialog.setTitle("Title");

                // 메세지 설정
                pDialog.setMessage("Loading...");

                // 닫기버튼 비활성화
                //pDialog.setCancelable(false);
                pDialog.show();

                // 스레드 생성
                myThread = new MyThread(handler);

                // 스레드 시작
                myThread.start();
            }
        });
    }

}
