package com.example.och.day19;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Ex02Activity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout sub_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        //LinearLayout tmp_layout = findViewById(R.id.tmp_layout);
        sub_layout = findViewById(R.id.tmp_layout);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        View view = getLayoutInflater().inflate(R.layout.sub_layout, null);
        TextView textView = view.findViewById(R.id.text_view);
        textView.setText("★★★★★");

        sub_layout.addView(view);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button1:
                sub_layout.setVisibility(View.VISIBLE);
                break;

            case R.id.button2:
                sub_layout.setVisibility(View.GONE);
                break;
        }
    }
}
