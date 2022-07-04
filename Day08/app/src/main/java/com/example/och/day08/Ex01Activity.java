package com.example.och.day08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Ex01Activity extends AppCompatActivity {

    // Activity 클래스의 메서드 : onStart(), onResume, onDestroy()
    // 한번에 오버라이드 : Insert + Alt  or  Ctrl + O
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MY", "Ex01의 onStart() 실행");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MY", "Ex01의 onStop() 실행");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MY", "Ex01의 onDestroy() 실행");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MY", "Ex02의 onRestart() 실행");
        Toast.makeText(getApplicationContext(), "ex01로 복귀", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MY", "Ex01의 onResume() 실행");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MY", "Ex01의 onPause() 실행");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);
        Log.d("MY", "Ex01의 onCreate() 실행");

        Button button01 = findViewById(R.id.button01);
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼을 클릭하면 Ex02 액티비티로 이동(실행)
                Intent intent = new Intent(getApplicationContext(), Ex02Activity.class);
                startActivity(intent);
            }
        });
    }

    private long time = 0;

    // 뒤로가기


    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        if(System.currentTimeMillis()-time >= 2000) {
            time = System.currentTimeMillis();
            Toast.makeText(this, "한번 더 누르시면 종료합니다.", Toast.LENGTH_SHORT).show();
        } else if(System.currentTimeMillis()-time < 2000) {
            Toast.makeText(this, "어플을 종료합니다.", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }
    }
}
