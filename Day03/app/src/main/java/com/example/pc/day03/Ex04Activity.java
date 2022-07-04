package com.example.pc.day03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ex04Activity extends AppCompatActivity {

    boolean isChecked = false;

    Button b1;
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex04);
        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        // Btn1, Btn2 를 객체 생성
        // 객체 생성 안하면, 화면으로는 보이지만 클릭, 입력 등의 이벤트 사용불가능

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // isChecked 가 false면 버튼2가 보여짐
                if (!isChecked) {
                    isChecked = true;
                    b2.setVisibility(View.GONE);
                } else {
                    isChecked = false;
                    b2.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
