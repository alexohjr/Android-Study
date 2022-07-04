package com.example.pc.day05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name_text; // 이름 텍스트
    Spinner tel_spinner; // 연락처 스피너
    EditText tel_text; // 연락처 텍스트
    RadioGroup radioGroup; // 성별 라디오 그룹
    RadioButton radiobutton1; // 성별 라디오 남자
    RadioButton radiobutton2; // 성별 라디오 여자
    CheckBox checkbox1; // 체크박스 자바
    CheckBox checkbox2; // 체크박스 씨
    CheckBox checkbox3; // 체크박스 씨플플
    CheckBox checkbox4; // 체크박스 파이썬
    CheckBox checkbox5; // 체크박스 안드로이드
    CheckBox checkbox6; // 체크박스 JSP
    Button sign_up; // 사인업 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_text = findViewById(R.id.name_text);
        tel_spinner = findViewById(R.id.tel_spinner);
        tel_text = findViewById(R.id.tel_text);
        radioGroup = findViewById(R.id.radioGroup);
        radiobutton1 = findViewById(R.id.radiobutton1);
        radiobutton2 = findViewById(R.id.radiobutton2);
        checkbox1 = findViewById(R.id.checkbox1);
        checkbox2 = findViewById(R.id.checkbox2);
        checkbox3 = findViewById(R.id.checkbox3);
        checkbox4 = findViewById(R.id.checkbox4);
        checkbox5 = findViewById(R.id.checkbox5);
        checkbox6 = findViewById(R.id.checkbox6);
        sign_up = findViewById(R.id.sign_up);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // 이름
            String name = name_text.getText().toString().trim();

            // 연락처
            String spinner = tel_spinner.getSelectedItem().toString();
            String tel = tel_text.getText().toString();

            // 성별
            int radio_Id = radioGroup.getCheckedRadioButtonId();
            RadioButton rb = findViewById(radio_Id);
            String gender =  rb == null? "" : rb.getText().toString();

            // 이수과목
            String checkedResult = "";
            CheckBox checkBoxArr[] = {checkbox1, checkbox2, checkbox3, checkbox4, checkbox5, checkbox6};

            for(int i=0; i<checkBoxArr.length; i++) {
                if(checkBoxArr[i].isChecked()) {
                    checkedResult += checkBoxArr[i].getText() + ", ";
                }
            }

            if(checkedResult.length() > 0) {
                checkedResult = checkedResult.substring(0, checkedResult.length() - 2);
            }

            if(name.isEmpty() || name.length() == 0) {
                Toast.makeText(MainActivity.this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                name_text.requestFocus();
                return;
            }

            if(tel_spinner.getSelectedItemPosition() == 0) {
                Toast.makeText(MainActivity.this, "연락처를 선택해주세요.", Toast.LENGTH_SHORT).show();
                tel_spinner.requestFocus();
                return;
            }

            if(tel.isEmpty() || tel.length() == 0) {
                Toast.makeText(MainActivity.this, "연락처를 입력해주세요.", Toast.LENGTH_SHORT).show();
                tel_text.requestFocus();
                return;
            }

            if(gender == null || gender.length() == 0) {
                Toast.makeText(MainActivity.this, "성별을 선택해주세요.", Toast.LENGTH_SHORT).show();
                radioGroup.requestFocus();
                return;
            }

            Toast.makeText(MainActivity.this, "이름 : " + name + "\n연락처 : " + spinner + tel + "\n성별 : " + gender + "\n이수과목 : " + checkedResult, Toast.LENGTH_LONG).show();
            }
        });
    }
}
