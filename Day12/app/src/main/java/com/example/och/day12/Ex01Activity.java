package com.example.och.day12;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

/*
    별점 평가 구현
    -> res -> layout -> dialog.xml 추가
 */

public class Ex01Activity extends AppCompatActivity {

    Dialog dialog; // 별점을 띄울 커스텀 다이얼로그
    TextView textView; // 점수를 보여줄 텍스트뷰
    Button button; // 클릭하면 별점 다이얼로그 띄움
    RatingBar ratingBar; // 별점 위젯

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);

        // 액티비티에 있는 위젯 생성
        textView = findViewById(R.id.ex01_text_view);
        button = findViewById(R.id.ex01_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 다이얼로그 띄우기
                dialog = new Dialog(Ex01Activity.this);

                dialog.setContentView(R.layout.dialog);

                // 다이얼로그 외부 클릭하면 닫히는 걸 false
                dialog.setCanceledOnTouchOutside(false);

                // dialog 창의 확인 버튼
                Button button_ok = dialog.findViewById(R.id.button);

                button_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 다이얼로그 창 닫기
                        dialog.dismiss();
                    }
                });

                // 레이팅바 객체 생성
                ratingBar = dialog.findViewById(R.id.rating_bar);

                // false :: 사용자가 별점 선택 후에도 별점 변경 가능
                // true :: 사용자가 별점 선택 후에 별점 변경 불가능
                ratingBar.setIsIndicator(false);

                // 증가 폭 설정 ( 0.5 : 0.5 칸 씩 증가함 ==> 반 칸 당 1점 )
                //              ( 1 : 1 칸 씩 증가함 ==> 한 칸 당 1점 )
                ratingBar.setStepSize(0.5f);

                // 별점이 변경될 때 마다 실행할 리스너
                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        // 10 / 5 = 2
                        float st = 10.0f / ratingBar.getNumStars();
                        String str = String.format("%.1f", (st * rating));
                        textView.setText(str + "/10.0");
                    }
                });
                dialog.show();
            }
        });
    }
}
