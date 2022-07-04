package com.example.pc.day30;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class Ex03Activity extends AppCompatActivity implements View.OnClickListener, MyItem {

    TextView price_tv;
    TextView item_tv;
    Button add_btn;

    private final HashMap<String, Integer> cartMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex03);

        price_tv = findViewById(R.id.price_tv);
        item_tv = findViewById(R.id.item_tv);
        add_btn = findViewById(R.id.add_btn);

        add_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Ex03Activity.this, Ex04Activity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            for(String name : ITEMS_NAME) {
                int q = data.getIntExtra(name,0);
                if(cartMap.containsKey(name)) {
                    int q2 = cartMap.get(name);
                    cartMap.put(name, q + q2);
                } else {
                    cartMap.put(name, q);
                }
            }
        }
        String message = "----- 장바구니 목록 ------\n";
        int totalPrice = 0;
        for(int i =0; i<ITEMS_NAME.length; i++) {
            int itemCount = cartMap.get(ITEMS_NAME[i]);
            if(itemCount != 0) {
                message += ITEMS_NAME[i] + " " + itemCount + "개\n";
                totalPrice += ITEMS_PRICE[i] * itemCount;
            }
        }
        item_tv.setText(message);
        price_tv.setText(String.valueOf(totalPrice));
    }
}
