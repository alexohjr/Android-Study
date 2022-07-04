package com.and.andelectronics.view.main.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.and.andelectronics.R;
import com.and.andelectronics.infrastructure.model.lodgement.Room;
import com.and.andelectronics.view.main.roomservice.RoomServiceGridAdapter;
import com.and.andelectronics.view.main.roomservice.RoomServiceItem;

import java.util.ArrayList;

/**
 * Created by yowonsm on 2018-05-27.
 */

public class RoomGroupServiceGridAdapter extends BaseAdapter {

    private class ViewHolder
    {
        private TextView commonGridTextView;
    }
    private ViewHolder holder;
    Context context;
    int layout;
    ArrayList<RoomServiceItem> items;
    LayoutInflater inf;

    public RoomGroupServiceGridAdapter(Context context, int layout, ArrayList<RoomServiceItem> items) {
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
            holder.commonGridTextView = (TextView)convertView.findViewById(R.id.commonGridTextView);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        final RoomServiceItem item= this.items.get(position);

        holder.commonGridTextView.setBackgroundResource(item.getImage());
        holder.commonGridTextView.setText(item.getName());
        return convertView;
    }

}
