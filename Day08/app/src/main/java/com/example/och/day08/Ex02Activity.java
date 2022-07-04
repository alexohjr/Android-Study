package com.example.och.day08;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/*
                메인 액티비티             다른 액티비티
앱을 실행하면     onCreate()              onCreate()
                  onStart()               onStart()
                  onResume()              onResume()

홈 버튼
                  onPause()               onPause() <-- 액티비티 프로세스 종료
                  onStop()                onStop()  <-- 화면 사라짐

액티비티 복귀
                  onRestart()             onRestart()
                  onStart()               onStart()
                  onResume()              onResume()

뒤로가기
                  onPause()               onPause()
                  onStop()                onStop()
                  onDestroy()             onDestroy()
                  (앱 종료)               (이전 화면으로 돌아감. 이전 액티비티의 restart())

 */

// 실습 : 두 액티비티 모두 적용 -> 화면 복귀 시 'ex01 (ex02) 액티비티로 복귀했습니다.' 를 Toast로 출력

public class Ex02Activity extends AppCompatActivity {

    // android.util.Log 클래스 :: 로그캣에 로그 기록을 출력
    // Log.d( String tag, String message) : debug용
    // Log.w( String tag, String message) : warning
    // Log.e( String tag, String message) : error
    // Log.v( String tag, String message) : verbose

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MY", "Ex02의 onStart() 실행");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MY", "Ex02의 onStop() 실행");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MY", "Ex02의 onDestroy() 실행");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MY", "Ex02의 onRestart() 실행");
        Toast.makeText(getApplicationContext(), "ex02로 복귀", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MY", "Ex02의 onResume() 실행");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MY", "Ex02의 onPause() 실행");
    }

    // 뒤로가기
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Toast.makeText(this, "서브액티비티는 이전화면으로 돌아갑니다.", Toast.LENGTH_SHORT).show();
        // '어플을 종료합니다' 를 Toast로 출력하고 어플종료
        Toast.makeText(this, "어플을 종료합니다.", Toast.LENGTH_SHORT).show();
        finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex02);

        Log.d("MY", "Ex02의 onCreate() 실행");

        Button button02 = findViewById(R.id.button02);

        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 어플 종료
                finishAffinity();
            }
        });
    }
}
