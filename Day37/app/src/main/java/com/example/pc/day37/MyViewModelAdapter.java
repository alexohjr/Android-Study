package com.example.pc.day37;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;

public class MyViewModelAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<DTO> list;
    private DTO vo;
    private int resource;


    private int num, type, cost, year, month, date;
    private String category, name, time;
    private TextView money_num, money_type, money_category, money_name, money_cost, money_date, money_time;

    // 생성자
    public MyViewModelAdapter(Context context, int resource, ArrayList<DTO> list) {
        // ArrayAdapter의 생성자 인자 : context, 아이템을 보여줄 레이아웃 id, 아이템리스트
        super(context, resource, list);
        this.list = list;
        this.resource = resource;
        this.context = context;
    }

    // getView : List의 원소들을 View로 생성


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView != null) {
            return convertView;
        }

        // Inflater 객체 얻어옴
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // book_item.xml을 가지고 화면을 추가함
        convertView = inflater.inflate(resource, null);


        vo = list.get(position); // position 번째 원소 받기

        if(vo != null) {

            num = vo.getNum();
            type = vo.getType();
            cost = vo.getCost();
            year = vo.getYear();
            month = vo.getMonth();
            date = vo.getDate();
            category = vo.getCategory();
            name = vo.getName();
            time = vo.getTime();

            money_num = convertView.findViewById(R.id.money_num);
            money_category = convertView.findViewById(R.id.money_category);
            money_name = convertView.findViewById(R.id.money_name);
            money_cost = convertView.findViewById(R.id.money_cost);
            money_date = convertView.findViewById(R.id.money_date);
            money_time = convertView.findViewById(R.id.money_time);

            money_num.setText("No." + num);
            money_category.setText(category);
            money_name.setText(name);

            switch (type) {
                case 0:
                    money_cost.setText("+"+cost+"원");
                    money_cost.setTextColor(Color.BLUE);
                    break;

                case 1:
                    money_cost.setText("-"+cost+"원");
                    money_cost.setTextColor(Color.RED);
                    break;
            }

            money_date.setText(year+"-"+month+"-"+date);
            money_time.setText(time);

        } // if
        return convertView;
    } // getView()
}
