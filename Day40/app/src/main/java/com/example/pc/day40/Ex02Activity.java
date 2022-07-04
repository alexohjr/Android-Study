package com.example.pc.day40;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ex02Activity extends AppCompatActivity {

    DrawerLayout layout;
    Button open_button, close_button;
    TextView drawer_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex02);

        layout = findViewById(R.id.layout);
        open_button = findViewById(R.id.open_button);
        close_button = findViewById(R.id.close_button);
        drawer_view = findViewById(R.id.drawer_view);

        open_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // left에 배치된 드러우어 열기
                layout.openDrawer(Gravity.LEFT);
            }
        });

        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // left에 배치된 드러우어 닫기
                layout.closeDrawer(Gravity.LEFT);
            }
        });
    }
}
