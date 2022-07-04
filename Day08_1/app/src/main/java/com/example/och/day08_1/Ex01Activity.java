package com.example.och.day08_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

/*
    목록 형태의 View : GridView, ListView, RecyclerView
    -> View 내부에 Item을 나열할 수 있음
    -> Item을 나열하려면 adapter 개념이 필요함

 */

public class Ex01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);

        GridView gridView = findViewById(R.id.grid_view);
        gridView.setAdapter(new MyAdapter(this));
    }
}
