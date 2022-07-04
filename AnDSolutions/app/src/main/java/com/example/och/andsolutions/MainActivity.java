package com.example.och.andsolutions;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<ListVO> list = new ArrayList<>();
    private ListAdapter adapter;
    private Button nearBtn, favoriteBtn, eventBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ListView listView = findViewById(R.id.main_list_view);
        View header = getLayoutInflater().inflate(R.layout.header, null, false);
        //View footer = getLayoutInflater().inflate(R.layout.footer, null, false);

        listView.addHeaderView(header);
       // listView.addFooterView(footer);

        nearBtn = header.findViewById(R.id.header_near_btn);
        favoriteBtn = header.findViewById(R.id.header_favorite_btn);
        eventBtn = header.findViewById(R.id.header_event_btn);

        nearBtn.setOnClickListener(this);
        favoriteBtn.setOnClickListener(this);
        eventBtn.setOnClickListener(this);

        for (int i = 0; i < 10; i++) {
            ListVO vo = new ListVO("매장이름 " + String.valueOf(i));
            list.add(vo);
        }

        if (adapter == null) {
            adapter = new ListAdapter(MainActivity.this, R.layout.list_item, list);
        }
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        v.getId();

        switch (v.getId()) {
            case R.id.header_near_btn:
                changeBtnColor(0);
                break;

            case R.id.header_favorite_btn:
                changeBtnColor(1);
                break;

            case R.id.header_event_btn:
                changeBtnColor(2);
                break;

        }

    }

    public void changeBtnColor(int flag) {
        switch (flag) {

            /*내주변 버튼 클릭*/
            case 0:
                nearBtn.setBackgroundColor(getColor(R.color.orange));
                nearBtn.setTextColor(getColor(R.color.white));
                favoriteBtn.setBackgroundColor(Color.rgb(213, 213, 213));
                eventBtn.setBackgroundColor(Color.rgb(213, 213, 213));
                favoriteBtn.setTextColor(Color.rgb(0, 0, 0));
                eventBtn.setTextColor(Color.rgb(0, 0, 0));
                break;

            /*단골매장 버튼 클릭*/
            case 1:
                favoriteBtn.setBackgroundColor(getColor(R.color.orange));
                favoriteBtn.setTextColor(getColor(R.color.white));
                nearBtn.setBackgroundColor(Color.rgb(213, 213, 213));
                eventBtn.setBackgroundColor(Color.rgb(213, 213, 213));
                nearBtn.setTextColor(Color.rgb(0, 0, 0));
                eventBtn.setTextColor(getResources().getColor(R.color.black));

                break;

             /*이벤트매장 버튼 클릭*/
            case 2:
                eventBtn.setBackgroundColor(getColor(R.color.orange));
                eventBtn.setTextColor(getColor(R.color.white));
                nearBtn.setBackgroundColor(Color.rgb(213, 213, 213));
                favoriteBtn.setBackgroundColor(Color.rgb(213, 213, 213));
                nearBtn.setTextColor(getColor(R.color.black));
                favoriteBtn.setTextColor(getColor(R.color.black));
                break;
        }
    }
}
