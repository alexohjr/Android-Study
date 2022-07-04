package com.example.och.day08_1;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

    Context context;

    String[] itemArr = {"피카츄", "라이츄", "파이리", "꼬부기", "버터풀", "야도란", "또가스"};

    public MyAdapter(Context context) {
        super();
        this.context = context;
    }

    // 아이템의 개수를 return
    @Override
    public int getCount() {
        return itemArr.length;
    }

    // position번 째 아이템 객체를 return
    @Override
    public Object getItem(int position) {
        return itemArr[position];
    }

    // position번 째 아이템의 id를 return
    @Override
    public long getItemId(int position) {
        return position;
    }

    // position번째의 아이템을 사용해서 View를 생성하고 이를 return
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int r = (int)(Math.random() * 256);
        int g = (int)(Math.random() * 256);
        int b = (int)(Math.random() * 256);

        TextView tv;

        if(convertView == null) {
            tv = new TextView(context); // TextView 새로 생성
            tv.setText(itemArr[position]);
            tv.setBackgroundColor(Color.rgb(r, g, b));
        } else {
            tv = (TextView)convertView;
        }
        return tv;
    }
}
