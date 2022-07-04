package com.example.och.day19_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class Ex01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);

        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(new MyAdapter(this));
    }
}
