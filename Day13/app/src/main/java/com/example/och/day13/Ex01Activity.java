package com.example.och.day13;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ex01Activity extends AppCompatActivity {

    EditText et1;
    EditText et2;
    Button btn;
    int n1, n2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);

        et1 = findViewById(R.id.ex01_edit_text1);
        et2 = findViewById(R.id.ex01_edit_text2);
        btn = findViewById(R.id.ex01_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 정수1 이나 정수2 에 입력 값이 없으면
                // Toast로 '정수를 입력하셔야 합니다.'
                // 입력값이 있으면 --> 두 정수를 Ex02 액티비티로 전송 + Ex02 실행

                String s1 = "", s2 = "";

                if(et1.getText() != null) {
                    s1 = et1.getText().toString();
                }
                if(et2.getText() != null) {
                    s2 = et2.getText().toString();
                }

                if(s1.isEmpty() || s2.isEmpty()) {
                    Toast.makeText(Ex01Activity.this, "정수를 입력하셔야 합니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Ex01Activity.this, Ex02Activity.class);

                    // 다음 액티비티에게 데이터 : intent.purExtra("key", value)
                    n1 = Integer.parseInt(s1);
                    n2 = Integer.parseInt(s2);
                    intent.putExtra("num1", n1);
                    intent.putExtra("num2", n2);

                    startActivity(intent);

                }
            }
        });

    }
}
