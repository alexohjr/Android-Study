package com.example.pc.day09;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

public class MyAdapter extends BaseAdapter {
    Context context;
    Button[] buttons = new Button[10];

    public MyAdapter(Context ctx){
        this.context = ctx;
        setButtons();
    }

    private void setButtons(){

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, v.getId() + "번 버튼 클릭됨!", Toast.LENGTH_SHORT).show();
            }
        };

        for(int i = 0; i < 10; ++i){
            buttons[i] = new Button(context);
            buttons[i].setText( i + "번" );

            int r = (int)(Math.random() * 256);
            int g = (int)(Math.random() * 256);
            int b = (int)(Math.random() * 256);
            buttons[i].setBackgroundColor(Color.rgb(r, g, b));
            buttons[i].setId(i);
            buttons[i].setOnClickListener(listener);
        }
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position; // return buttons[position].getId()
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView != null){
            return convertView;
        } else {
            return buttons[position];
        }
    }
}