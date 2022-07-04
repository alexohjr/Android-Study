package com.example.och.day10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Ex01Activity extends AppCompatActivity implements View.OnClickListener {

    // OnClickListener
    @Override
    public void onClick(View v) {
        Toast.makeText(this, v.getId() + "번 버튼!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_ex01);

        // res -> layout -> activity_ex01.xml 로 화면구성하기

        // xml 없이 화면 구성

        // 루트 레이아웃
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // Button 7개 생성
        for(int i=0; i<7; i++) {
            Button button = new Button(this);
            button.setText(i + "번 버튼");
            button.setId(i);
            button.setOnClickListener(this);
            button.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25); // 글자크기 : 25dp
            button.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            button.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);

            linearLayout.addView(button); // 레이아웃에 버튼 추가
        }
        setContentView(linearLayout);
    }
}
