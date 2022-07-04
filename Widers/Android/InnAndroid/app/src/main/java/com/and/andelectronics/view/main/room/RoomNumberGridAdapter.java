package com.and.andelectronics.view.main.room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.and.andelectronics.R;
import com.and.andelectronics.infrastructure.model.lodgement.Room;

import java.util.ArrayList;

/**
 * Created by yowonsm on 2018-05-27.
 */

public class RoomNumberGridAdapter extends BaseAdapter {

    private class ViewHolder
    {
        private TextView roomNumberNameTextView;
    }
    private ViewHolder holder;
    Context context;
    int layout;
    ArrayList<Room> items;
    LayoutInflater inf;

    public RoomNumberGridAdapter(Context context, int layout, ArrayList<Room> items) {
        this.context = context;
        this.layout = layout;
        this.items = items;
        inf = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
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

            holder.roomNumberNameTextView = (TextView)convertView.findViewById(R.id.roomNumberNameTextView);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        final Room item= this.items.get(position);
        holder.roomNumberNameTextView.setText(item.getRoomCode());

        return convertView;
    }

}
