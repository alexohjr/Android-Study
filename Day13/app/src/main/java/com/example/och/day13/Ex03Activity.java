package com.example.och.day13;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class Ex03Activity extends AppCompatActivity {

    EditText name_edittext;
    Spinner tel1_spinner;
    EditText tel2_edittext;
    EditText tel3_edittext;
    RadioGroup gender_radiogroup;
    CheckBox[] checkBoxes = new CheckBox[6];
    Button submit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex03);

        name_edittext = findViewById(R.id.name_edittext);
        tel1_spinner = findViewById(R.id.tel1_spinner);
        tel2_edittext = findViewById(R.id.tel2_edittext);
        tel3_edittext = findViewById(R.id.tel3_edittext);
        gender_radiogroup = findViewById(R.id.gender_radiogroup);
        submit_button = findViewById(R.id.submit_button);
        checkBoxes[0] = findViewById(R.id.subject_checkbox1);
        checkBoxes[1] = findViewById(R.id.subject_checkbox2);
        checkBoxes[2] = findViewById(R.id.subject_checkbox3);
        checkBoxes[3] = findViewById(R.id.subject_checkbox4);
        checkBoxes[4] = findViewById(R.id.subject_checkbox5);
        checkBoxes[5] = findViewById(R.id.subject_checkbox6);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                submit_button.setClickable(false);
                submit_button.setBackgroundColor(Color.rgb(255,0,0));
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = name_edittext.getText() == null? "" : name_edittext.getText().toString();
                int tel1 = tel1_spinner.getSelectedItemPosition();

                String tel2 = tel2_edittext.getText() == null ? "" : tel2_edittext.getText().toString();
                String tel3 = tel3_edittext.getText() == null ? "" : tel3_edittext.getText().toString();

                int radio_Id = gender_radiogroup.getCheckedRadioButtonId();
                RadioButton rb = findViewById(radio_Id);
                String gender = rb == null ? "" : rb.getText().toString();

                name = name.trim();
                tel2 = tel2.trim();
                tel3 = tel3.trim();

                if(name.isEmpty() || tel1 == 0 || tel2.isEmpty() || tel3.isEmpty() || gender.isEmpty() || tel2.length() < 4 || tel3.length() < 4 ) {
                    submit_button.setClickable(false);
                    submit_button.setBackgroundColor(Color.rgb(255,0,0));
                } else {
                    submit_button.setClickable(true);
                    submit_button.setBackgroundColor(Color.rgb(0,0,255));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        name_edittext.addTextChangedListener(textWatcher);
        tel2_edittext.addTextChangedListener(textWatcher);
        tel3_edittext.addTextChangedListener(textWatcher);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent(Ex03Activity.this, Ex04Activity.class);

                // 다음 액티비티에게 데이터 : intent.putExtra("key", value)
                // 이름
                String name = name_edittext.getText().toString();

                // 연락처
                String tel1 = tel1_spinner.getSelectedItem() == null ? "" : tel1_spinner.getSelectedItem().toString();
                String tel2 = tel2_edittext.getText().toString();
                String tel3 = tel3_edittext.getText().toString();

                // 성별
                int radio_Id = gender_radiogroup.getCheckedRadioButtonId();
                RadioButton rb = findViewById(radio_Id);
                String gender = rb == null ? "" : rb.getText().toString();

                // 이수과목
                String checkedResult = "";

                for(int i=0; i<checkBoxes.length; i++) {
                    if(checkBoxes[i].isChecked()) {
                        checkedResult += checkBoxes[i].getText() + " ";
                    }
                }

                intent.putExtra("name", name);
                intent.putExtra("tel1", tel1);
                intent.putExtra("tel2", tel2);
                intent.putExtra("tel3", tel3);
                intent.putExtra("gender", gender);
                intent.putExtra("subject", checkedResult);

                startActivity(intent);
            }
        });
    }
}
