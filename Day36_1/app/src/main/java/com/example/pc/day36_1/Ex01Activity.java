package com.example.pc.day36_1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Ex01Activity extends AppCompatActivity {

    private final int STATUS_STOP = 0;
    private final int STATUS_RUN = 1;
    
    private int score;
    private int currentStatus;
    private String name;
    
    private TextView textView;
    private Button startButton;
    private Button rankButton;
    
    private LinearLayout bottomLayout;
    private EditText editName;
    private Button submitButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);
        
        textView = findViewById(R.id.text_view);
        startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(startButtonListener);
        
        rankButton = findViewById(R.id.rank_button);
        rankButton.setOnClickListener(rankButtonListener);
        
        bottomLayout = findViewById(R.id.bottom_layout);
        editName = findViewById(R.id.edit_name);
        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(submitButtonListener);
    }
    
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case STATUS_RUN:
                    score = (int)(Math.random()*1000) + 1;
                    textView.setText(String.valueOf(score));
                    sendEmptyMessageDelayed(STATUS_RUN, 10);
                    break;
                    
                case STATUS_STOP:
                    removeMessages(STATUS_RUN);
                    break;
            }
        }
    };
    
    // ?????? ?????? ?????? ?????????
    private View.OnClickListener startButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(currentStatus) {
                case STATUS_STOP: // ?????? ????????? ?????? ?????? ?????? -> RUN ?????????
                    currentStatus = STATUS_RUN;
                    startButton.setText("STOP");
                    textView.setTextColor(Color.GRAY);
                    rankButton.setVisibility(View.INVISIBLE);
                    break;
                    
                case STATUS_RUN: // ?????? ????????? ?????? ??? ??? ?????? -> STOP ?????????
                    currentStatus = STATUS_STOP;
                    startButton.setText("START");
                    textView.setTextColor(Color.BLUE);
                    rankButton.setVisibility(View.VISIBLE);
                    break;
            }
            handler.sendEmptyMessage(currentStatus);
        }
    };
    
    // ?????? ?????? ?????????
    private View.OnClickListener rankButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            bottomLayout.setVisibility(View.VISIBLE);
        }
    };
    
    // ?????? ?????? ?????????
    private View.OnClickListener submitButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            name = editName.getText().toString().trim();
            if(name.isEmpty()) {
                Toast.makeText(Ex01Activity.this, "????????? ???????????????", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(Ex01Activity.this, Ex02Activity.class);
            intent.putExtra("name", name);
            intent.putExtra("score", score);
            startActivity(intent);
        }
    };
}
