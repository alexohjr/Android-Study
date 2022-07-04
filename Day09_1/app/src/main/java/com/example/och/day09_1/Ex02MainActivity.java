package com.example.och.day09_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class Ex02MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex02_main);
        ListView listView = findViewById(R.id.ex2_list_view);
        listView.setAdapter(new MyAdapter2(this));
    }
}
