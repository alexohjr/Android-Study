package com.example.och.day10;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/*
    < 다이얼로그 창 >
    - 상태 알림용 뷰
    - 구현 순서
        1 :: res -> layout -> 다이얼로그용 레이아웃을 xml 추가
        2 :: 다이얼로그를 띄울 부분에 위 레이아웃을 setContentView() 실행
        3 :: show() 실행 ( 화면닫기 : dismiss() )
 */

public class Ex02Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex02);

        // 버튼 클릭하면 다이얼로그 창이 뜨도록
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Ex02Activity.this, R.style.MyDialog);

                dialog.setContentView(R.layout.dialog_layout);

                Button yes_button = dialog.findViewById(R.id.yes_button);
                Button no_button = dialog.findViewById(R.id.no_button);

                no_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                yes_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

                // 다이얼로그창 배경을 투명하게
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                // 배경색 지정
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(100, 0, 0)));

                // 다이얼로그창 뜰 때 액티비티의 반투명화 제거
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

                // 다이얼로그 타이틀바 on
                // res -> styles.xml 에 Theme 추가
                dialog.setTitle("EXIT?");

                dialog.setCancelable(false); // 뒤로가기 버튼 눌렀을 때 알림창 안닫히게(false)
                dialog.show();
            }
        });
    }
}
