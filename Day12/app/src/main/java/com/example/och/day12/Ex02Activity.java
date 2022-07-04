package com.example.och.day12;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Ex02Activity extends AppCompatActivity {

    Button loginButton; // 로그인 버튼
    EditText nameEditText; // 아이디 입력 폼
    EditText passwordEditText; // 비밀번호 입력 폼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex02);

        loginButton = findViewById(R.id.ex02_button);
        nameEditText = findViewById(R.id.ex02_name);
        passwordEditText = findViewById(R.id.ex02_pwd);

        // 이름창에 숫자를 입력하면 바로 Toast 알림 띄우기
        nameEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.d("MY", "키보드 입력됨");

                // 키가 눌렸을 때
                if(event.getAction() == KeyEvent.ACTION_DOWN) {
                    // 눌린 키(keyCode)가 숫자 0~9 이면
                    if(keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {
                        Toast.makeText(Ex02Activity.this, "숫자는 안됨", Toast.LENGTH_SHORT).show();
                    }
                }
                // false 를 해야 계속 실행 됨
                // true 면 한번만 실행 됨
                return false;
            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 이름, 비밀번호 입력란에 모두 텍스트가 있으면 로그인버튼 색상 변경
                // 한 쪽이라도 비어있으면 버튼 비활성화
                String name = nameEditText.getText() == null ? "" : nameEditText.getText().toString();
                String pwd = passwordEditText.getText() == null ? "" : passwordEditText.getText().toString();

                if(pwd.isEmpty() && name.isEmpty()) {
                    // 버튼 클릭 비활성화
                    loginButton.setClickable(false);
                    loginButton.setBackgroundColor(Color.rgb(140, 140, 140));
                } else if(pwd.isEmpty() || name.isEmpty()) {
                    loginButton.setClickable(false);
                    loginButton.setBackgroundColor(Color.rgb(255, 0, 0));
                } else {
                    loginButton.setClickable(true);
                    loginButton.setBackgroundColor(Color.rgb(0, 255, 0));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                /*String name = nameEditText.getText() == null ? "" : nameEditText.getText().toString();
                if(name.matches("^[0-9]*$")) {
                    Toast.makeText(Ex02Activity.this, "숫자는 안됩니다.", Toast.LENGTH_SHORT).show();
                    nameEditText.setText("");
                }*/
            }
        };

        nameEditText.addTextChangedListener(textWatcher);
        passwordEditText.addTextChangedListener(textWatcher);
    }
}
