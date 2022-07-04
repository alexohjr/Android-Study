package com.example.och.day17;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ex01Activity extends AppCompatActivity implements View.OnClickListener {

    TextView text_view;
    Button btn_value, btn_reset;
    int n = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        // defValue :: 해당 값이 없을 경우 디폴트값
        n = pref.getInt("value", 0);

        text_view = findViewById(R.id.value);
        btn_value = findViewById(R.id.btn_value);
        btn_reset = findViewById(R.id.btn_reset);

        text_view.setText(String.valueOf(n));

        btn_value.setOnClickListener(this);
        btn_reset.setOnClickListener(this);

        /*btn_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++n;
                text_view.setText(String.valueOf(n));
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n = 0;
                text_view.setText(String.valueOf(n));
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_value:
                ++n;
                text_view.setText(String.valueOf(n));
                break;

            case R.id.btn_reset:
                n = 0;
                text_view.setText(String.valueOf(n));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MY", "onDestroy 실행");

        // 액티비티가 종료될 때
        // 로컬에 n을 저장
        // SharedPreferences 객체를 얻어옴 --> PreferenceManager를 통해 얻어옴
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        // 편집 모드 실행 ( SharedPreferences.Editor :: 편집기 )
        SharedPreferences.Editor edit = pref.edit(); // 편집

        // 편집기를 사용하여 데이터 저장
        // int형 데이터를 저장 [key : 'value', value : n]
        edit.putInt("value", n);

        // 최종 저장 ( apply() or commit()을 사용 )
        // apply() :: 저장하는동안 다른 일을 할 수 있음(선호)
        // commit() :: 저장하는동안 다른 일을 할 수 없음
        edit.apply();

        // 참고 : edit.remove(String name) -> 원소삭제
        // edit.clear() -> 로컬에 저장되었던 모든 원소 삭제
    }
}
