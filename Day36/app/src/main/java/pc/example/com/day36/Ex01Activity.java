package pc.example.com.day36;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

/*
    < SQLite >
    - 안드로이드 디바이스에 내장되어있는
      MySql 기반의 경량화 DB
    - SQLiteOpenHelper 클래스를 사용하여 내장 DB를 다룬다.
    - 가져온 DB는 SQLiteDataBase 클래스를 사용한다.
 */

public class Ex01Activity extends AppCompatActivity implements View.OnClickListener {
    /*
        1. START 버튼을 누르면 text_view에 1~1000 숫자가 랜덤하게 10 밀리초 단위로 계속 변경
        (START 버튼은 STOP 버튼으로 바뀜)

        2. STOP 버튼을 누르면 text_view의 숫자가 멈추고 이게 점수가 됨
        '랭킹등록' 버튼이 생김

        3. 랭킹등록 버튼을 누르면 bottom_layout이 생김
        이름을 입력 받고, '저장' 버튼을 누르면 Ex02Activity를 실행 (점수와 이름 데이터를 넘김)
        만약 이름이 입력되지 않았다면 Toast로 "이름을 입력하세요." 띄움

     */


    Button start_button;
    TextView text_view;
    Button rank_button;
    LinearLayout bottom_layout;
    EditText edit_name;
    Button submit_button;
    int status = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);

        start_button = findViewById(R.id.start_button);
        text_view = findViewById(R.id.text_view);
        rank_button = findViewById(R.id.rank_button);
        bottom_layout = findViewById(R.id.bottom_layout);
        edit_name = findViewById(R.id.edit_name);
        submit_button = findViewById(R.id.submit_button);


        start_button.setOnClickListener(this);
        rank_button.setOnClickListener(this);
        submit_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.start_button:
                switch (status) {
                    case 0:
                        start_button.setText("STOP");
                        status = 1;

                        handler.sendEmptyMessage(0);
                        rank_button.setVisibility(View.INVISIBLE);
                        bottom_layout.setVisibility(View.GONE);

                        break;

                    case 1:
                        start_button.setText("START");
                        status = 0;

                        handler.sendEmptyMessage(1);
                        rank_button.setVisibility(View.VISIBLE);
                        break;
                }
                break;

            case R.id.rank_button:
                bottom_layout.setVisibility(View.VISIBLE);
                break;

            case R.id.submit_button:
                String name = edit_name.getText().toString().trim();
                int score = Integer.parseInt(text_view.getText().toString().trim());

                Intent intent = new Intent(Ex01Activity.this, Ex02Activity.class);
                intent.putExtra("name", name);
                intent.putExtra("score", score);
                startActivity(intent);

                break;


        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // msg.what이 0이면 text_view의 숫자가 계속 변경되도록
            // sendEmptyMessageDelayed()
            if(msg.what == 0) {
                Random r = new Random();
                int result = r.nextInt(1000) + 1;
                text_view.setText(String.valueOf(result));
                handler.sendEmptyMessageDelayed(0, 10);

            } else if(msg.what == 1) {
                removeMessages(0);
            }
            // msg.what이 1이면 핸들러 종료
            // removeMessages(0)
        }
    };
}
