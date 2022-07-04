package com.example.pc.day30;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter implements MyItem, View.OnClickListener {

    Context context;
    EditText[] editTexts = new EditText[ITEMS_NAME.length];
    View[] views = new View[ITEMS_NAME.length];

    public MyAdapter(Context context) {
        this.context = context;
        for(int i=0; i<ITEMS_NAME.length; i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_layout, null);
            TextView item_name_tv = view.findViewById(R.id.item_name_tv);
            TextView item_price_tv = view.findViewById(R.id.item_price_tv);
            EditText quan_et = view.findViewById(R.id.quan_et);

            editTexts[i] = quan_et;
            item_name_tv.setText(ITEMS_NAME[i]);
            item_price_tv.setText(ITEMS_PRICE[i] + "원");
            quan_et.setText("0");

            views[i] = view;
        }
    }

    @Override
    public int getCount() {
        return ITEMS_NAME.length;
    }

    @Override
    public Object getItem(int position) {
        return ITEMS_NAME[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == views[position]){
            return convertView;
        }
        return views[position];
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        for(int i=0; i<ITEMS_NAME.length; i++) {
            String text = editTexts[i].getText().toString();
            int quan = Integer.parseInt(text.isEmpty() ? "0" : text);
            intent.putExtra(ITEMS_NAME[i], quan);
            Log.d("MY", "페이지2의 상품명 : " + ITEMS_NAME[i]
            +", 수량 : " + quan);
        }
        ((Activity)context).setResult(Activity.RESULT_OK, intent);
        ((Activity)context).finish();
    }

    public View.OnClickListener getOnClickListener() {
        return this;
    }
}
