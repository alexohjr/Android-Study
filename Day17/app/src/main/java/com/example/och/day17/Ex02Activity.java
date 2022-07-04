package com.example.och.day17;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Ex02Activity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    EditText title_et, content_et;
    Button btn_save, btn_read, btn_remove_all;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // MyMemo 라는 이름으로 묶어서 저장하기
        preferences = getSharedPreferences("MyMemo", MODE_PRIVATE);
        // preferences = PreferenceManager.getDEfaultSharedPreferences(this);
        setContentView(R.layout.activity_ex02);

        title_et = findViewById(R.id.title_et);
        content_et = findViewById(R.id.content_et);
        btn_save = findViewById(R.id.btn_save);
        btn_read = findViewById(R.id.btn_read);
        btn_remove_all = findViewById(R.id.btn_remove_all);

        btn_save.setOnClickListener(this);
        btn_read.setOnClickListener(this);
        btn_remove_all.setOnClickListener(this);

        title_et.setOnKeyListener(this);
        content_et.setOnKeyListener(this);
    } // onCreate

    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor;
        switch(v.getId()) {
            case R.id.btn_save:
                editor = preferences.edit();
                String memo = title_et.getText().toString().trim()
                        + "#" + content_et.getText().toString().trim();
                editor.putString(String.valueOf(System.currentTimeMillis()), memo);
                editor.apply();
                Toast.makeText(this, "저장 완료 !", Toast.LENGTH_SHORT).show();
                title_et.setText("");
                content_et.setText("");
                break;

            case R.id.btn_read:
                Intent intent = new Intent(this, Ex03Activity.class);
                startActivity(intent);
                break;

            case R.id.btn_remove_all:
                editor = preferences.edit();
                editor.clear(); // 전체 삭제
                editor.apply();
                break;

        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {


        if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
           imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            return true;
        }
        return false;
    }
}

