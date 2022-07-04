package com.example.och.day23;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Ex01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 타이틀 없애기
        // 매니페스트 . 액티비티 안에
        // android:theme = "@android:style/Theme.NoTitleBar"
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // status 바 없애기
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new MyView(this));
    }
}
