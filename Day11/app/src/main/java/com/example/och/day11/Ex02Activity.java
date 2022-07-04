package com.example.och.day11;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Ex02Activity extends AppCompatActivity {

    int index;
    String[] colorArr = {"RED", "GREEN", "BLUE", "PINK", "ORANGE"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex02);

        // dialog 를 띄움
        // 배경 색상을 선택하세요
        // 1.레드 2.그린 3.블루 4.핑크 5.오렌지
        // => 확인을 선택하면 Activity의 전체 배경이 선택된 색상으로 변경 되도록

        final RelativeLayout relativeLayout = findViewById(R.id.ex02_layout);
        Button button = findViewById(R.id.ex02_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Ex02Activity.this, android.R.style.Theme_Material_Dialog_Alert);
                dialog.setTitle("배경 색상을 선택해주세요.")
                        .setSingleChoiceItems(colorArr, index, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                index = which;
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch(index) {
                                    case 0:
                                        relativeLayout.setBackgroundColor(Color.rgb(255, 0, 0));
                                        index = 0;
                                        break;

                                    case 1:
                                        relativeLayout.setBackgroundColor(Color.rgb(29, 219, 22));
                                        index = 1;
                                        break;

                                    case 2:
                                        relativeLayout.setBackgroundColor(Color.rgb(0, 84, 255));
                                        index = 2;
                                        break;

                                    case 3:
                                        relativeLayout.setBackgroundColor(Color.rgb(255, 0, 211));
                                        index = 3;
                                        break;

                                    case 4:
                                        relativeLayout.setBackgroundColor(Color.rgb(255, 94, 0));
                                        index = 4;
                                        break;
                                }
                            }
                        })
                        .setNeutralButton("닫기", null)
                        .show();
            }
        });
    }
}
