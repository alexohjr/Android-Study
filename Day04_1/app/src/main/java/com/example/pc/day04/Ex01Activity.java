package com.example.pc.day04;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class Ex01Activity extends AppCompatActivity {

    // View(위젯)들 변수 선언
    CheckBox checkBox;
    EditText editText;
    Spinner spinner;
    RadioGroup radioGroup;
    ProgressBar progressBar;
    Button button1;
    Button button2;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);

        checkBox = findViewById(R.id.checkBox);
        editText = findViewById(R.id.editText);
        spinner = findViewById(R.id.spinner);
        radioGroup = findViewById(R.id.radioGroup);
        progressBar = findViewById(R.id.progressBar);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        linearLayout = findViewById(R.id.linearLayout);
        /*
            R : 어플리케이션의 모든 XML, 뷰의 ID 등의 정보를 담고 있는 클래스
                위젯을 xml에 추가하고 id를 부여하면
                해당 id의 이름으로 정수형 상수가 만들어짐

            findViewById( int 고유번호 ) : 고유번호를 통해 view를 찾을 때 사용

            View 클래스 : 모든 위젯 클래스(Button, Spinner, LinearLayout 등)의 루트클래스
         */

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()) { // 체크 되었으면 true, 빈 칸이면 false
                    Toast.makeText(Ex01Activity.this, "체크박스 체크됨", Toast.LENGTH_LONG).show();
                    // Toast.makeText( 이 토스트창의 소속 액티비티, 출력 할 텍스트, 보여줄 시간).show();
                    // Toast.LENGTH_SHORT : 짧게 보여줌
                    // Toast.LENGTH_LONG : 길게 보여줌
                } else {
                    Toast.makeText(Ex01Activity.this, "체크박스 체크 해제됨", Toast.LENGTH_SHORT).show();
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // => spinner 내부의 아이템이 선택 되었을 때 실행

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 특정 아이템이 선택 되었을 때 몇 번째 아이템이 선택되었는 지 정보를 알려줌
                String message = (String)spinner.getItemAtPosition(position);
                Toast.makeText(Ex01Activity.this, message + " 항목 선택 됨 " + position, Toast.LENGTH_SHORT).show();
                editText.setText(message);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 아무 것도 선택이 안되었을 때

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = group.getCheckedRadioButtonId();
                RadioButton b = findViewById(id);
                String text = (String)b.getText();
                // getText() 는 거의 모든 뷰에 다 있음
                // 리턴자료형이 String이 아니라서 형변환 필요
                // 리턴자료형 : (String의 조상격인 CharSequence)
                Toast.makeText(Ex01Activity.this, text, Toast.LENGTH_SHORT).show();
                switch(id) {
                    case R.id.radioButton1:
                        linearLayout.setBackgroundColor(Color.rgb(255, 0, 0));
                        break;
                    case R.id.radioButton2:
                        linearLayout.setBackgroundColor(Color.rgb(29, 219, 22));
                        break;
                    case R.id.radioButton3:
                        linearLayout.setBackgroundColor(Color.rgb(1, 0, 255));
                        break;
                }


            }
        });

        progressBar.setVisibility(View.GONE);
        // GONE : 화면에서 사라짐 (공간을 차지하지 않음)

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.INVISIBLE);
                // INVISIBLE : 화면에서 사라짐 (공간은 그대로 남아있음)
            }
        });
    }
}
