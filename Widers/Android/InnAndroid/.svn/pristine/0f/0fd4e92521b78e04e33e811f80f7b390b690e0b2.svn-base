package com.and.andelectronics.view.main.roomservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.and.andelectronics.R;

import java.util.ArrayList;

/**
 * Created by yowonsm on 2018-05-27.
 */

public class RoomServiceGridAdapter extends BaseAdapter {

    private class ViewHolder
    {
        private ImageView roomServiceImageView;
        private TextView roomServiceNameTextView;
        private ImageView roomServiceReadyImageView;
    }
    private ViewHolder holder;
    Context context;
    int layout;
    ArrayList<RoomServiceItem> items;
    LayoutInflater inf;

    public RoomServiceGridAdapter(Context context, int layout, ArrayList<RoomServiceItem> items) {
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
            holder.roomServiceImageView = (ImageView)convertView.findViewById(R.id.roomServiceImageView);
            holder.roomServiceNameTextView = (TextView)convertView.findViewById(R.id.roomServiceNameTextView);
            holder.roomServiceReadyImageView = (ImageView)convertView.findViewById(R.id.roomServiceReadyImageView);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        final RoomServiceItem item= this.items.get(position);

        holder.roomServiceImageView.setImageResource(item.getImage());
        holder.roomServiceNameTextView.setText(item.getName());
        if(item.getRoomServiceStatus() == RoomServiceItem.RoomServiceStatus.EMPTY){
            holder.roomServiceReadyImageView.setVisibility(View.GONE);

        }else if(item.getRoomServiceStatus() == RoomServiceItem.RoomServiceStatus.REQUEST){
            holder.roomServiceReadyImageView.setVisibility(View.VISIBLE);
            holder.roomServiceReadyImageView.setBackgroundResource(R.mipmap.icon_roomservice_request);

        }else if(item.getRoomServiceStatus() == RoomServiceItem.RoomServiceStatus.READY){
            holder.roomServiceReadyImageView.setVisibility(View.VISIBLE);
            holder.roomServiceReadyImageView.setBackgroundResource(R.mipmap.icon_roomservice_ready);

        }else if(item.getRoomServiceStatus() == RoomServiceItem.RoomServiceStatus.COMPLETE){
            holder.roomServiceReadyImageView.setVisibility(View.VISIBLE);
            holder.roomServiceReadyImageView.setBackgroundResource(R.mipmap.icon_roomservice_complete);
        }

        return convertView;
    }

}
