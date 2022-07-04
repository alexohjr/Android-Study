package com.example.pc.day30;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class Ex04Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex04);

        ListView item_list_view = findViewById(R.id.item_list_view);
        Button submit_btn = findViewById(R.id.submit_btn);

        MyAdapter myAdapter = new MyAdapter(this);
        item_list_view.setAdapter(myAdapter);
        submit_btn.setOnClickListener(myAdapter.getOnClickListener());
    }
}
