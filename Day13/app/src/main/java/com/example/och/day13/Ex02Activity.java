package com.example.och.day13;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Ex02Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex02);

        // 이전 액티비티에서 전달한 intent 객체를 받음
        Intent intent = getIntent();

        // int getIntExtra("꺼낼 값의 key", 해당 값이 없을 때 디폴트 값 설정)
        int a = intent.getIntExtra("num1", 0);
        int b = intent.getIntExtra("num2", 0);

        TextView t1, t2, t3, t4;
        t1 = findViewById(R.id.ex02_add_text_view);
        t2 = findViewById(R.id.ex02_sub_text_view);
        t3 = findViewById(R.id.ex02_mul_text_view);
        t4 = findViewById(R.id.ex02_div_text_view);

        t1.setText(a + " + " + b + " = " + (a+b));
        t2.setText(a + " - " + b + " = " + (a-b));
        t3.setText(a + " * " + b + " = " + (a*b));
        t4.setText(a + " / " + b + " = " + ((double)a/b));
    }
}
