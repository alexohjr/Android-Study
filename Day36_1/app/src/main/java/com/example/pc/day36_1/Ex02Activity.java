package com.example.pc.day36_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Ex02Activity extends AppCompatActivity {

    TextView name_view;
    TextView score_view;
    String name;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex02);

        TextView tv = findViewById(R.id.ranking_list_textview);

        if(getIntent() != null) {
            name = getIntent().getStringExtra("name");
            score = getIntent().getIntExtra("score", 0);

            MyHelper myHelper = new MyHelper(this, "scores.db", null, 1);
            myHelper.insert(name, score);

            // MyHelper를 통해 readAll() ==> 모든 점수를 String 형태로 받기
            tv.setText(myHelper.readAll());
        }
    }
}
