package com.example.och.day19_1;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends BaseAdapter {

    String[] products = {"MENU1", "MENU2", "MENU3", "MENU4", "MENU5"};
    String[] prices = {"1000", "2000", "3000", "4000", "5000"};
    Drawable[] drawables = new Drawable[5];
    Context context;

    public MyAdapter(Context context) {
        this.context = context;
        drawables[0] = context.getResources().getDrawable(R.drawable.img1);
        drawables[1] = context.getResources().getDrawable(R.drawable.img2);
        drawables[2] = context.getResources().getDrawable(R.drawable.img3);
        drawables[3] = context.getResources().getDrawable(R.drawable.img4);
        drawables[4] = context.getResources().getDrawable(R.drawable.img5);

    }

    @Override
    public int getCount() {
        return products.length;
    }

    @Override
    public Object getItem(int position) {
        return products[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView != null) {
            return convertView;
        }

        // inflater 실행
        View view = ((Activity)context).getLayoutInflater().inflate(R.layout.sub_layout, null);
        final TextView tv1 = view.findViewById(R.id.product);
        TextView tv2 = view.findViewById(R.id.price);
        ImageView iv = view.findViewById(R.id.img);

        tv1.setText(products[position]);
        tv2.setText(prices[position] + "원");
        iv.setImageDrawable(drawables[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, tv1.getText() + " 선택", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
