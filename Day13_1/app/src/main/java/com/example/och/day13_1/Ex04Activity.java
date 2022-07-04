package com.example.och.day13_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Ex04Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex04);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String tel =
                intent.getStringExtra("tel1") +
                intent.getStringExtra("tel2") +
                intent.getStringExtra("tel3");
        String gender = intent.getStringExtra("gender");
        ArrayList<String> subject_list = intent.getStringArrayListExtra("subject_list");

        String message =
                "이름 : " + name + "\n" +
                "연락처 : " +  tel + "\n" +
                "성별 : " + gender + "\n" +
                "수강과목 : ";

        for(String s : subject_list) {
            message += s + ", ";
        }

        // 마지막 쉼표 자르기
        int index = message.lastIndexOf(",");
        message = message.substring(0, index);

        TextView textView = findViewById(R.id.ex04_text_view);
        textView.setText(message);

    }
}
