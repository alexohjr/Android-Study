package com.example.och.day21;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Ex01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }
}
