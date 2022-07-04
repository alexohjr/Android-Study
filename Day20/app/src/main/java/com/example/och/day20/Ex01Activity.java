package com.example.och.day20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
    Canvas와 Paint 클래스

    Canvas : 그림, 도형 등을 표현할 수 있는 판 역할의 클래스
             View 위에 얹어서 사용

    Paint : Canvas 위에 그림을 그리는 붓 역할의 클래스
 */

public class Ex01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }
}
