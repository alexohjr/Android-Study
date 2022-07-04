package com.example.och.day20;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Ex02Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView2(this));

        // 디바이스 회전 시 값 유지를 위한 코드
        Resources r = Resources.getSystem();

        // Configuration :: 화면 방향 정보
        Configuration config = r.getConfiguration();

        // 화면 방향이 변경될 때 onConfigurationChanged() 실행
        // 이 메서드를 호출해야 화면 방향이 변경되어도 onDestroy() 실행되지 않음
        // onDestroy()가 호출되면 기존에 그려놨던 그림이 사라짐
        onConfigurationChanged(config);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // 현재 디바이스의 화면 (세로 : Portrait / 가로 : Landscape)
        switch(newConfig.orientation) {
            // 가로화면
            case Configuration.ORIENTATION_LANDSCAPE:
                Toast.makeText(this, "가로화면 됨", Toast.LENGTH_SHORT).show();
                break;

            // 세로화면
            case Configuration.ORIENTATION_PORTRAIT:
                Toast.makeText(this, "세로화면 됨", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
