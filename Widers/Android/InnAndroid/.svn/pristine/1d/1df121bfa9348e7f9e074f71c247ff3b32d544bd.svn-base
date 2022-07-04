package com.and.andelectronics.view.main.payment;

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
 * Created by Won on 2017-06-27.
 */
public class PayTypeListAdapter  extends BaseAdapter {

    private class ViewHolder {

        public TextView payTypeNameTextView, payTypeMsgTextView;
    }

    private ViewHolder holder;
    private Context mContext;
    public ArrayList<String> payTypeList;;

    public PayTypeListAdapter(Context context,
                           ArrayList<String> payTyeList) {
        super();
        this.mContext = context;
        this.payTypeList = payTyeList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return payTypeList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return payTypeList.get(position);
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
            convertView = inflater.inflate(R.layout.item_paytype, null);
            holder.payTypeNameTextView = (TextView)convertView.findViewById(R.id.payTypeNameTextView);
            holder.payTypeMsgTextView = (TextView)convertView.findViewById(R.id.payTypeMsgTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final String payType = this.payTypeList.get(position);
        holder.payTypeNameTextView.setText(payType);
        //holder.payTypeMsgTextView.setText(areaItem.getAreaCity());

        return convertView;
    }
}