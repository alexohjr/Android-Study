package com.example.pc.day09;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

public class MyAdapter2 extends BaseAdapter {

    Context context;
    Button[] buttonArr = new Button[10];
    private int number = (int)(Math.random() * 10);
    private int count = 0;
    private int res_count = 0;

    public MyAdapter2(Context context) {
        this.context = context;
        setButtonArr();
    }


    private void setButtonArr() {

        count = 1;
        res_count = 5;

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == number) {
                    Toast.makeText(context, "당첨", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context.getApplicationContext(), Ex03Activity.class);
                    intent.putExtra("number", number+1);

                    context.startActivity(intent);

                } else {
                    res_count = res_count -1;
                    v.setVisibility(View.GONE);
                    Toast.makeText(context, count + "번째 / 남은기회 : " + res_count, Toast.LENGTH_SHORT).show();

                    if(count == 5) {
                        Toast.makeText(context, "실패", Toast.LENGTH_SHORT).show();
                    }

                    count++;
                }

            }
        };

        for(int i = 0; i < 10; ++i){
            buttonArr[i] = new Button(context);
            if(i == number) {
                buttonArr[i].setText( i+1 + "번 당첨" );
            } else {
                buttonArr[i].setText( i+1 + "번" );
            }

            buttonArr[i].setId(i);
            buttonArr[i].setOnClickListener(listener);
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView != null) {
            return convertView;
        } else {
            return buttonArr[position];
        }
    }
}
