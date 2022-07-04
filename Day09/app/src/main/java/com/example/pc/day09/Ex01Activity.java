package com.example.pc.day09;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class Ex01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);

        GridView gridView = findViewById(R.id.grid_view);
        gridView.setAdapter(new MyAdapter(this));



        // adapter를 사용하여10개 짜리 버튼 vertical로 나열
        // n번 버튼을 클릭하면 "n번 버튼을 클릭하셨습니다. " 를 Toast로 출력
    }
}
