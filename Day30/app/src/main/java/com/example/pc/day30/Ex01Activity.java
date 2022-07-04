package com.example.pc.day30;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Ex01Activity extends AppCompatActivity {

    TextView tv;
    ImageView iv;
    Button btn;
    Bundle bundle;

    int SELECT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);

        tv = findViewById(R.id.text_view);
        iv = findViewById(R.id.image_view);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ex01Activity.this, Ex02Activity.class);
                startActivityForResult(intent, SELECT);
            }
        });
    } // onCreate()

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == SELECT && resultCode == RESULT_OK) {
            bundle = data.getExtras(); // selectColor, selectColorText
            int color = bundle.getInt("selectColor");
            String text = bundle.getString("selectColorText");

            iv.setBackgroundColor(color);
            tv.setText(text);
        }
    }
}
