package com.example.och.day13;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Ex04Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex04);

        // 이전 액티비티에서 전달한 intent 객체를 받음
        Intent intent = getIntent();

        // int getIntExtra("꺼낼 값의 key", 해당값이 없을 때 디폴트 값 설정)
        String name = intent.getStringExtra("name");
        String tel1 = intent.getStringExtra("tel1");
        String tel2 = intent.getStringExtra("tel2");
        String tel3 = intent.getStringExtra("tel3");
        String gender = intent.getStringExtra("gender");
        String subject = intent.getStringExtra("subject");

        TextView name_view, tel_view, gender_view, subject_view;
        name_view = findViewById(R.id.ex04_name_text_view);
        tel_view = findViewById(R.id.ex04_tel_text_view);
        gender_view = findViewById(R.id.ex04_gender_text_view);
        subject_view = findViewById(R.id.ex04_subject_text_view);

        name_view.setText(name);
        tel_view.setText(tel1 + " - " + tel2 + " = " + tel3);
        gender_view.setText(gender);
        subject_view.setText(subject);
    }
}
