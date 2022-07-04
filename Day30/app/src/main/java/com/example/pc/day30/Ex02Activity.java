package com.example.pc.day30;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ex02Activity extends AppCompatActivity implements View.OnClickListener {

    Button btn_red, btn_green, btn_blue;

    Bundle bundleColor;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex02);

        btn_red = findViewById(R.id.btn_red);
        btn_green = findViewById(R.id.btn_green);
        btn_blue = findViewById(R.id.btn_blue);

        btn_red.setOnClickListener(this);
        btn_green.setOnClickListener(this);
        btn_blue.setOnClickListener(this);

        // Ex01Activity에 값 전달용
        intent = new Intent();
        bundleColor = new Bundle();
    }

    @Override
    public void onClick(View v) {
        int color = 0;
        String text = "NONE";
        switch(v.getId()) {
            case R.id.btn_red:
                color = Color.RED;
                text = "Red";
                break;

            case R.id.btn_green:
                color = Color.GREEN;
                text = "Green";
                break;

            case R.id.btn_blue:
                color = Color.BLUE;
                text = "Blue";
                break;
        }
        bundleColor.putInt("selectColor", color);
        bundleColor.putString("selectColorText", text);
        intent.putExtras(bundleColor);

        // 원래 액티비티(Ex01)로 보낼 결과를 세팅
        setResult(RESULT_OK, intent);

        // 액티비티 종료 -> 자동으로 Ex01Activity로 넘어감
        finish();
    }
}
