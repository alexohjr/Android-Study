package com.example.och.day13_1;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Ex03Activity extends AppCompatActivity implements View.OnClickListener, TextWatcher, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    EditText name_edit_text;
    Spinner tel1_spinner;
    EditText tel2_edit_text;
    EditText tel3_edit_text;
    RadioGroup gender_radiogroup;
    CheckBox[] subject_checkboxes = new CheckBox[6];
    Button submit_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex03);

        // view 객체 생성
        name_edit_text = findViewById(R.id.name_edittext);
        tel1_spinner = findViewById(R.id.tel1_spinner);
        tel2_edit_text = findViewById(R.id.tel2_edittext);
        tel3_edit_text = findViewById(R.id.tel3_edittext);
        gender_radiogroup = findViewById(R.id.gender_radiogroup);

        // subject_checkboxes[0] = findViewById(R.id.subject_checkbox1);
        // subject_checkboxes[1] = findViewById(R.id.subject_checkbox2);
        // subject_checkboxes[2] = findViewById(R.id.subject_checkbox3);
        // subject_checkboxes[3] = findViewById(R.id.subject_checkbox4);
        // subject_checkboxes[4] = findViewById(R.id.subject_checkbox5);
        // subject_checkboxes[5] = findViewById(R.id.subject_checkbox6);
        // id 값이 규칙적이라면 다음과 같이 생성가능
        for(int i=0; i<subject_checkboxes.length; i++) {
            try {
                subject_checkboxes[i] = findViewById((int)new R.id().getClass().getField("subject_checkbox" + (i+1)).get(null));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        submit_button = findViewById(R.id.submit_button);

        name_edit_text.addTextChangedListener(this);
        tel1_spinner.setOnItemSelectedListener(this);
        tel2_edit_text.addTextChangedListener(this);
        tel3_edit_text.addTextChangedListener(this);
        submit_button.setOnClickListener(this);
        gender_radiogroup.setOnCheckedChangeListener(this);
    }

    private boolean checkIntegrity() {
        if(name_edit_text.getText().toString().trim().isEmpty()) {
            // 이름 입력창에 데이터가 없으면
            return false;
        }
        if(tel1_spinner.getSelectedItem() == null) {
            // 연락처 스피너가 선택안되있으면
            return false;
        }
        if(tel2_edit_text.getText().toString().trim().isEmpty()) {
            // 연락처2 공백인 경우
            return false;
        }
        if(tel3_edit_text.getText().toString().trim().isEmpty()) {
            // 연락처3 공백인 경우
            return false;
        }
        switch(gender_radiogroup.getCheckedRadioButtonId()) {
            // 성별 둘중하나 선택된 경우
            case R.id.female_radio_button:
            case R.id.male_radio_button:
                return true;
        }
        return false;
    }

    // 필수 기입 사항을 확인하여 '회원가입' 버튼 색상을 변경하는 메서드
    private void changeSubmitColor(boolean integrity) {
        if(integrity) {
            submit_button.setBackgroundColor(Color.argb(0x70, 0xA0, 0x00, 0x00));
        } else {
            submit_button.setBackgroundColor(Color.argb(0x70, 0xA0, 0xA0, 0xA0));
        }
    }


    // View.OnClickListener
    @Override
    public void onClick(View v) {
        // 회원 가입 버튼을 누르면
        // 필수 입력 사항에 모든 데이터가 기입되었는 지 확인
        // 기입되었다면 Ex04 액티비티로 이동 (Extra 추가)
        if(checkIntegrity()) {
            Intent intent = new Intent(getApplicationContext(), Ex04Activity.class);
            intent.putExtra("name", name_edit_text.getText().toString().trim());
            intent.putExtra("tel1", tel1_spinner.getSelectedItem().toString());
            intent.putExtra("tel2", tel2_edit_text.getText().toString().trim());
            intent.putExtra("tel3", tel3_edit_text.getText().toString().trim());
            intent.putExtra("gender", gender_radiogroup.getCheckedRadioButtonId() == R.id.male_radio_button ? "남성" : "여성");

            // 수강과목은 몇 개를 선택했냐에 따라 다르게 넘겨야 하므로
            // 어레이리스트르 생성하여 사용한다.
            ArrayList<String> subject_list = new ArrayList<>();
            for(CheckBox b : subject_checkboxes) {
                if(b.isChecked()) {
                    subject_list.add(b.getText().toString());
                }
            }
            
            intent.putExtra("subject_list", subject_list);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "필수 기입 사항을 입력하세요.", Toast.LENGTH_SHORT).show();
        }
    }

    // TextWatcher
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        changeSubmitColor(checkIntegrity());
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    @Override
    public void afterTextChanged(Editable s) {}

    // RadioGroup.OnCheckedChangeListener
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        changeSubmitColor(checkIntegrity());
    }

    // OnItemSelectedListener
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        changeSubmitColor(checkIntegrity());
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}




}
