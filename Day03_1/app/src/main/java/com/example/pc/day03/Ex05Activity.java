package com.example.pc.day03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Ex05Activity extends AppCompatActivity implements View.OnClickListener {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex05);

        button = findViewById(R.id.button);

        final TextView[] textViews = new TextView[9];
        Integer[] textIds = {
                R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5,
                R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9
        };

        for(int i = 0; i < 9; i++)
        {
            textViews[i] = findViewById(textIds[i]);
            textViews[i].setTag(i);
        }

        for (int i = 0; i < textIds.length; i++)
        {
            final int index;
            index = i;
            textViews[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setVisibility(View.GONE);
                }
            });
        }

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                for (int i=0; i<textViews.length; i++) {
                    textViews[i].setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}