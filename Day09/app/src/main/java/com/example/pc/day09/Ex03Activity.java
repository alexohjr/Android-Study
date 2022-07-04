package com.example.pc.day09;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Ex03Activity extends AppCompatActivity {

    private Intent intent;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex03);

        intent = getIntent();
        textView = findViewById(R.id.text_view);
        textView.setText(intent.getIntExtra("number", 0));
    }
}
