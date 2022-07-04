package com.example.och.day16;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.Calendar;

/*
    DatePicker를 사용하여 생일을 입력 받고
    생일 데이터를 Bundle에 담아 다음 액티비티로 보냄
 */

public class Ex01Activity extends AppCompatActivity {

    Button bt1_date, bt2_send, age_btn;
    Dialog dialog, dialog2;
    EditText et_name, et_age, et_b_day;

    DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            // 사용자가 데이트피커 다이얼로그에서 클릭한 날짜를 String 형태로 포맷
            String str = String.format("%d-%02d-%02d", year, month+1, dayOfMonth);

            // 화면에 출력
            et_b_day.setText(str);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);

        bt1_date = findViewById(R.id.btn_date);
        bt2_send = findViewById(R.id.btn_send);
        et_name = findViewById(R.id.edit_name);
        et_age = findViewById(R.id.edit_age);
        et_b_day = findViewById(R.id.edit_b_day);
        age_btn = findViewById(R.id.age_btn);

        bt1_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 오늘 날짜를 기준으로 달력 띄움
                // 오늘 날짜 얻어옴 ==> Calendar
                Calendar calendar = Calendar.getInstance();
                int y = calendar.get(Calendar.YEAR); // 년
                int m = calendar.get(Calendar.MONTH) + 1; // 월
                int d = calendar.get(Calendar.DAY_OF_MONTH); // 일

                dialog = new DatePickerDialog(Ex01Activity.this, dateListener, y, m, d);
                dialog.show();
            }
        });

        age_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 숫자입력 다이얼로그를 띄움
                dialog2 = new Dialog(Ex01Activity.this);

                // 다이얼로그창 설정
                dialog2.setContentView(R.layout.activity_ex01_wheel_dialog);


                // 다이얼로그 창 확인버튼
                Button button_ok = dialog2.findViewById(R.id.dialog_btn);

                NumberPicker numberPicker = dialog2.findViewById(R.id.numberPicker);
                numberPicker.setMinValue(20);
                numberPicker.setMaxValue(40);

                button_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        NumberPicker numberPicker = dialog2.findViewById(R.id.numberPicker);
                        Toast.makeText(Ex01Activity.this, "value : " + numberPicker.getValue(), Toast.LENGTH_SHORT).show();

                        et_age.setText(String.valueOf(numberPicker.getValue()));

                        dialog2.dismiss();
                    }
                });
                dialog2.show();
            }
        });

        bt2_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 입력한 이름, 나이, 생일 정보를 Bundle에 담아 Ex01Result로 전달 + startActivity
                String name = et_name.getText().toString().trim();
                String age = et_age.getText().toString().trim();
                String bDay = et_b_day.getText().toString().trim();

                // Bundle
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("age", age);
                bundle.putString("bDay", bDay);

                Intent intent = new Intent(Ex01Activity.this, Ex01ResultActivity.class);

                // Intent의 putExtras로 bundle 객체 넘김
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }
}
