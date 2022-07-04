package com.example.och.andsolutions;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList list;
    private int layout;
    private Context context;

    public ListAdapter(Context context, int layout, ArrayList list) {
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int pos) {
        return list.get(pos).toString();
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }

        ListVO vo = (ListVO)list.get(pos);

        ImageView img = convertView.findViewById(R.id.item_image);
        TextView title = convertView.findViewById(R.id.item_title);

        img.setBackgroundResource(R.drawable.rounding);
        img.setClipToOutline(true);
        title.setText(vo.getTitle());

        return convertView;
    }

}
