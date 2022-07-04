package com.example.pc.day09;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class Ex02Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex02);

        GridView gridView = findViewById(R.id.grid_view2);
        gridView.setAdapter(new MyAdapter2(this));

    }
}
