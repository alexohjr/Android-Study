package com.and.andelectronics.view.main.reserve;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.and.andelectronics.R;
import com.and.andelectronics.infrastructure.model.lodgement.ReserveTime;

import java.util.ArrayList;

/**
 * Created by yowonsm on 2018-05-27.
 */

public class DayuseReserveTimeGridAdapter extends BaseAdapter {

    private class ViewHolder
    {
        public TextView reserveTimeTextView;
        public TextView noReserveTextView;
    }
    private ViewHolder holder;
    Context context;
    int layout;
    ArrayList<ReserveTime> items;
    LayoutInflater inf;

    private ArrayList<String> times;

    public DayuseReserveTimeGridAdapter(Context context, int layout, ArrayList<ReserveTime> items) {
        this.context = context;
        this.layout = layout;
        this.items = items;
        inf = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        times = new ArrayList<>();
        for (ReserveTime reserveTime : items) {
            times.add(reserveTime.getHourOfDate().substring(11, 13) + "시");
        }

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        if(convertView ==null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.reserveTimeTextView = (TextView)convertView.findViewById(R.id.reserveTimeTextView);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.reserveTimeTextView.setText(times.get(position));

        if(items.get(position).isSelected()){
            holder.reserveTimeTextView.setBackgroundResource(R.mipmap.btn_time_on);
            holder.reserveTimeTextView.setTextColor((ContextCompat.getColorStateList(context, R.color.white)));
        }else{
            holder.reserveTimeTextView.setBackgroundResource(R.mipmap.btn_time_off);
            holder.reserveTimeTextView.setTextColor((ContextCompat.getColorStateList(context, R.color.actionbar_red)));
        }

        return convertView;
    }

}
