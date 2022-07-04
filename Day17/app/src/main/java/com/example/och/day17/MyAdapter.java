package com.example.och.day17;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collection;
import java.util.Iterator;

public class MyAdapter extends BaseAdapter {

    Context context;
    TextView[] textViews;

    public void setTextViews() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String title = pref.getString("aaa", "");
        String content = pref.getString("content", "");
        Log.d("Result", content);

        // String title = pref.getString("title", "");

        // 키값없이 모든 저장값 가져오기
        Collection<?> col = pref.getAll().values();
        Iterator<?> it = col.iterator();

        while(it.hasNext()) {
            String msg = (String)it.next();
            Log.d("Result", msg);
        }
    }


    public MyAdapter(Context ctx) {
        this.context = ctx;
        setTextViews();
    }

    @Override
    public int getCount() {
        return 0;
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
            return textViews[position];
        }
    }
}
