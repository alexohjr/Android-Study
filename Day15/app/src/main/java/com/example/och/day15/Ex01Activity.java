package com.example.och.day15;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

/*
    < 멀티 스레드 >
    - Thread 클래스, Runnable 인터페이스
    - Handler
    - AsyncTask ( 네트워크 통신 )

    < Handler >
    - 쓰레드 간에 데이터를 주고 받을 때 메시지를 전달하는 객체

    Looper 와  messageQueue
    1. Looper : 메인 스레드 내부에서 message queue 의 메세지를 처리하는 객체
    2. message queue : 외부 스레드로부터 전달받은 메세지를 담는 보관소
    --> handler의 sendMessage() : message queue에 메세지를 넣음
 */

public class Ex01Activity extends AppCompatActivity implements View.OnClickListener {

    final static int INIT = 0; // 처음 상태 [ 시작 / 기록(비활성화) ]
    final static int RUN = 1; // 진행중 [ 멈춤 / 기록(활성화) ]
    final static int PAUSE = 2; // 일시 정지 [ 시작 / 리셋(활성화) ]

    Button start_btn; // 시작 or 멈춤
    Button record_btn; // 기록 or 리셋
    // 리셋을 누르면 기록 초기화
    // 기록을 누르면 기록 TextView에 누적

    TextView time_out; // 시간을 표기할 뷰
    TextView records; // 기록을 표기할 뷰
    TextView textview; // 현재 상태를 표기할 뷰 (멈춤, 시작, 기록)

    ScrollView scroll; // 기록이 화면을 벗어날 정도로 많아지면 스크롤이 가능하도록 함

    int cur_status = INIT; // 현재 상태를 저장
    int my_count = 0; // 기록한 시간의 개수

    long myBaseTime; // 시작시간 ( INIT 상태에서 '시작'을 누른 시간 )
    long myPauseTime; // '기록'을 누른 시간

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            time_out.setText(getTimeOut());
            handler.sendEmptyMessage(0);
            super.handleMessage(msg);
        }
    };

    private String getTimeOut() {

        // 부팅 이후 경과 된 밀리초 ( sleep이나 절전모드 등으로 스레드가 멈춘 시간까지 포함 )
        long now = SystemClock.elapsedRealtime();
        Log.d("MY", "부팅 이후 현재 " + now + "밀리초 경과" );
        long outTime = now - myBaseTime;

        return String.format("%02d:%02d:%02d", (outTime/1000)/60, (outTime/1000)%60, (outTime%1000)/10);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);

        start_btn = findViewById(R.id.start_button);
        record_btn = findViewById(R.id.record_button);

        time_out = findViewById(R.id.text_view_timer);
        records = findViewById(R.id.text_view_records);
        textview = findViewById(R.id.text_view_text);
        scroll = findViewById(R.id.scroll_view);

        start_btn.setOnClickListener(this);
        record_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.start_button:
                switch(cur_status) {
                    case INIT: // 초기 화면에서 버튼1 (시작)을 눌렀을 때
                        myBaseTime = SystemClock.elapsedRealtime(); // 시작 시간을 저장
                        handler.sendEmptyMessage(0); // 핸들러 실행

                        // 버튼 텍스트 변경 및 활성화
                        start_btn.setText("멈춤");
                        record_btn.setText("기록");
                        record_btn.setEnabled(true);
                        textview.setText("시작");

                        // 상황 변경 (진행중 상태로)
                        cur_status = RUN;

                        break;

                    case RUN: // 진행 중에 버튼1 (멈춤)을 눌렀을 때
                        // 핸들러 정지 (TextView의 text 변경 정지)
                        handler.removeMessages(0);

                        // 부팅 후 ~ 현재 경과한 밀리초를 기록 + pauseTime 으로 설정
                        myPauseTime = SystemClock.elapsedRealtime();

                        // 버튼 변경 (실행 -> 일시정지)
                        start_btn.setText("시작");
                        record_btn.setText("리셋");
                        textview.setText("멈춤");


                        // 상태 변경
                        cur_status = PAUSE;
                        break;
                    case PAUSE: // 일시정지 상황에서 버튼1 (시작)을 눌렀을 때
                        // 현재 시간 저장
                        long now = SystemClock.elapsedRealtime();

                        // 핸들러 실행
                        handler.sendEmptyMessage(0);

                        myBaseTime += (now - myPauseTime);

                        // 버튼 변경
                        start_btn.setText("멈춤");
                        record_btn.setText("기록");
                        textview.setText("시작");

                        // 상태변경
                        cur_status = RUN;
                        break;
                }

                break;

            case R.id.record_button:
                switch (cur_status) {

                    case RUN: // 진행 중에 버튼2 (기록)을 눌렀을 때

                        // 기록 개수 추가
                        ++my_count;

                        // 기록 텍스트뷰의 내용 가져옴 ( 덮어쓰기 전 )
                        String str = records.getText().toString();

                        // str에 현재 기록을 누적
                        str += String.format("%d. %s\n", my_count, getTimeOut());

                        // 이전 기록 텍스트 + 새 기록을 다시 텍스트 뷰에 넣기
                        records.setText(str);

                        // 스크롤을 맨 밑으로 내리기
                        scroll.scrollTo(0, records.getHeight());

                        textview.setText("기록");
                        textview.setText("시작");

                        break;
                    case PAUSE: // 일시정지 상황에서 버튼2 (리셋)을 눌렀을 때

                        // 버튼 변경
                        start_btn.setText("시작");
                        record_btn.setText("기록");
                        record_btn.setEnabled(false);
                        time_out.setText("00:00:00");

                        textview.setText("스탑워치");

                        // 초기 상태로 변경
                        cur_status = INIT;
                        my_count = 0;
                        records.setText("");

                        break;
                }
                break;
        }
    }
}
