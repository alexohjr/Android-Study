package com.and.andelectronics.view.main.search;

import android.content.Context;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.and.andelectronics.R;
import com.and.andelectronics.infrastructure.model.AreaItem;

import java.util.ArrayList;

/**
 * Created by Won on 2016-04-23.
 */
public class AreaCityAdapter  extends BaseAdapter {

    private class ViewHolder {

        public TextView tv_noticeSubject, tv_noticeTime;
    }

    private ViewHolder holder;
    private Context mContext;
    public ArrayList<AreaItem> areaItemArrayList;;

    public AreaCityAdapter(Context context,
                       ArrayList<AreaItem> areaItemArrayList) {
        super();
        this.mContext = context;
        this.areaItemArrayList = areaItemArrayList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return areaItemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return areaItemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_areacity, null);
            holder.tv_noticeSubject = (TextView)convertView.findViewById(R.id.tv_noticeTime);
            holder.tv_noticeTime = (TextView)convertView.findViewById(R.id.tv_noticeSubject);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final AreaItem areaItem = this.areaItemArrayList.get(position);
        holder.tv_noticeSubject.setText(areaItem.getDistrict());
        holder.tv_noticeTime.setText(areaItem.getAreaCity());

        return convertView;
    }
}
