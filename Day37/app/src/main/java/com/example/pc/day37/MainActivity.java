package com.example.pc.day37;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    EditText name_et, cost_et, year_et, month_et, date_et;
    RadioGroup type_radiogroup;
    Spinner category_spinner;
    Button submit_button, delete_button;
    int type;
    String category;
    String name;
    int cost;
    int year;
    int month;
    int date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        type_radiogroup = findViewById(R.id.type_radiogroup);
        category_spinner = findViewById(R.id.category_spinner);

        name_et = findViewById(R.id.name);
        cost_et = findViewById(R.id.cost);
        year_et = findViewById(R.id.year);
        month_et = findViewById(R.id.month);
        date_et = findViewById(R.id.date);
        submit_button = findViewById(R.id.submit_button);
        delete_button = findViewById(R.id.delete_button);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Type
                int radio_Id = type_radiogroup.getCheckedRadioButtonId();
                switch (radio_Id) {
                    case R.id.radio_button_1:
                        type = 0;
                        break;

                    case R.id.radio_button_2:
                        type = 1;
                        break;
                }

                // Category
                category = category_spinner.getSelectedItem().toString();

                // Name
                name = name_et.getText().toString().trim();

                // Cost
                cost = Integer.parseInt(cost_et.getText().toString().trim());

                // year
                year = Integer.parseInt(year_et.getText().toString().trim());

                // month
                month = Integer.parseInt(month_et.getText().toString().trim());

                // date
                date = Integer.parseInt(date_et.getText().toString().trim());

                MyHelper myHelper = new MyHelper(MainActivity.this, "money.db", null, 1);
                myHelper.insert(type, category, name, cost, year, month, date+1);

                Intent intent = new Intent(MainActivity.this, Ex02Activity.class);
                startActivity(intent);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyHelper myHelper = new MyHelper(MainActivity.this, "money.db", null, 1);
                myHelper.deleteAll();
            }
        });

    }
}