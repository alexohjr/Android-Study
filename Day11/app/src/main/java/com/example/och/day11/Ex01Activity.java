package com.example.och.day11;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Ex01Activity extends AppCompatActivity {

    int theme = android.R.style.Theme_DeviceDefault_Light_Dialog_Alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);

        // 버튼1 누르면 AlertDialog (기본) 실행
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog1 = new AlertDialog.Builder(Ex01Activity.this, theme);
                dialog1.setMessage("이 곳은 메세지 영역")
                        .setTitle("이 곳은 타이틀 영역")
                        .setCancelable(false)
                        .setPositiveButton("YES", null) // (String 메세지, OnClickListener) 긍정버튼
                        .setNeutralButton("CONFIRM", null)
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] textArr = {"항목1", "항목2", "항목3", "항목4", "항목5"};
                AlertDialog.Builder dialog2 = new AlertDialog.Builder(Ex01Activity.this, theme);
                dialog2.setTitle("이 곳을 타이틀 영역")
                        .setItems(textArr, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(Ex01Activity.this, which + "번 항목 선택됨", Toast.LENGTH_SHORT).show();
                            }
                        })
                        //.setSingleChoiceItems(textArr, -1,null)
                        // 단 1개만 선택되도록 함 => radio 버튼처럼 됨
                        // checkedItem : -1 => 기본 선택 없음
                        .setNegativeButton("닫기", null)
                        .show();
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Progress
                ProgressDialog progressDialog = new ProgressDialog(Ex01Activity.this, theme);
                progressDialog.setMessage("잠시만 기다려주세요");
                //progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
            }
        });


    }
}
