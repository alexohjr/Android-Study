package com.example.pc.day37;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Ex02Activity extends AppCompatActivity {

    TextView name, cost;
    ListView listView;
    LayoutInflater mInflater;

    private ArrayList<DTO> list;
    private MyViewModelAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex02);

        name = findViewById(R.id.name);
        cost = findViewById(R.id.cost);
        listView = findViewById(R.id.list_view);

        MyHelper myHelper = new MyHelper(Ex02Activity.this, "money.db", null, 1);
        list = myHelper.readAll();


        adapter = new MyViewModelAdapter(Ex02Activity.this, R.layout.money_item, list);

        listView.setAdapter(adapter);


        /*for(int i=0; i<list.size(); i++) {
            String name1 = list.get(i).getName();

            Log.d("MY", "name :: " + name1);


            // name.setText(arrayList.get(i).getName());
            cost.setText(String.valueOf(list.get(i).getCost()));


        }*/
    }
}
