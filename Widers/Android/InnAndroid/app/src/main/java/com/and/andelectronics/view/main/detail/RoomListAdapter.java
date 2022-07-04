package com.and.andelectronics.view.main.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.and.andelectronics.R;
import com.and.andelectronics.common.WebServiceInformation;
import com.and.andelectronics.common.util.PriceFormatUtil;
import com.and.andelectronics.common.util.image.ImageDownloader;
import com.and.andelectronics.infrastructure.model.lodgement.Room;

import java.util.ArrayList;

/**
 * Created by Won on 2017-06-17.
 */
public class RoomListAdapter extends BaseAdapter {

    private class ViewHolder
    {
        private ImageView thumbnailImageView;
        private TextView roomNameTextView;
        private TextView reserveTextView;
        private TextView overnightPriceTextView;
        private TextView dayusePriceTextView;
        private TextView personTextView;
        private TextView maxPersonTextView;
        private TextView dayuseTimeTextView;
        private TextView overnightTimeTextView;
    }


    private final ImageDownloader imageDownloader = new ImageDownloader();
    private ViewHolder holder;
    private Context mContext = null;
    public ArrayList<Room> roomList;


    public RoomListAdapter(Context context, ArrayList<Room> roomList){
        super();
        this.mContext = context;
        this.roomList = roomList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return this.roomList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return this.roomList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        if(convertView ==null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_room, null);
            holder.thumbnailImageView = (ImageView)convertView.findViewById(R.id.thumbnailImageView);
            holder.roomNameTextView = (TextView)convertView.findViewById(R.id.roomNameTextView);
            holder.reserveTextView = (TextView)convertView.findViewById(R.id.reserveTextView);
            holder.dayusePriceTextView = (TextView)convertView.findViewById(R.id.dayusePriceTextView);
            holder.overnightPriceTextView = (TextView)convertView.findViewById(R.id.overnightPriceTextView);
            holder.personTextView = (TextView)convertView.findViewById(R.id.personTextView);
            holder.maxPersonTextView = (TextView)convertView.findViewById(R.id.maxPersonTextView);
            holder.dayuseTimeTextView = (TextView)convertView.findViewById(R.id.dayuseTimeTextView);
            holder.overnightTimeTextView = (TextView)convertView.findViewById(R.id.overnightTimeTextView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        final Room mData= this.roomList.get(position);

        holder.roomNameTextView.setText(mData.getGradeName());

        String reserve = mData.getEmptyRoomCount() > 0 ? "예약가능" : "";
        holder.reserveTextView.setText(reserve);
        holder.dayusePriceTextView.setText(PriceFormatUtil.getPriceFormat(String.valueOf(mData.getDayusePrice())) + "원");
        holder.overnightPriceTextView.setText(PriceFormatUtil.getPriceFormat(String.valueOf(mData.getOvernightPrice())) + "원");

        holder.personTextView.setText(mData.getPerson() + "명");
        holder.maxPersonTextView.setText((mData.getPerson() + 1) + "명");

        holder.dayuseTimeTextView.setText(mData.getRentTime() + "시간");
        holder.overnightTimeTextView.setText((mData.getCheckIn()) + "~");

        if(mData.getEmptyRoomCount() == 0)
        {
            holder.thumbnailImageView.setImageResource(R.drawable.noimage_uploadphoto);
        }
        else
        {
            imageDownloader.download(WebServiceInformation.getImageDownloadUrl() + mData.getCompanyCode() + "/" + "0" + (position + 1) +".jpg",
                    mData.getCompanyCode() + "/" + "0" + (position + 1) +".jpg",
                    (ImageView) holder.thumbnailImageView);
        }

        return convertView;
    }

}