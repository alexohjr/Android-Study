package com.and.andelectronics.view.main.mypage;

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
import com.and.andelectronics.infrastructure.model.lodgement.Lodgement;
import com.and.andelectronics.infrastructure.model.lodgement.ReserveRoom;

import java.util.ArrayList;

/**
 * Created by Won on 2017-06-17.
 */
public class ReserveListAdapter extends BaseAdapter {

    private class ViewHolder
    {
        private ImageView thumbnailImageView;
        private TextView storeNameTextView;
        private TextView roomCodeTextView;
        private TextView reserveTypeTextView;
        private TextView reservePriceTextView;
    }


    private final ImageDownloader imageDownloader = new ImageDownloader();
    private ViewHolder holder;
    private Context mContext = null;
    public ArrayList<ReserveRoom> reserveRoomList;


    public ReserveListAdapter(Context context, ArrayList<ReserveRoom> reserveRooms){
        super();
        this.mContext = context;
        this.reserveRoomList = reserveRooms;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return this.reserveRoomList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return this.reserveRoomList.get(position);
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
            convertView = inflater.inflate(R.layout.item_reserve_room, parent, false);
            holder.thumbnailImageView = (ImageView)convertView.findViewById(R.id.thumbnailImageView);
            holder.storeNameTextView = (TextView)convertView.findViewById(R.id.storeNameTextView);
            holder.roomCodeTextView = (TextView)convertView.findViewById(R.id.roomCodeTextView);
            holder.reserveTypeTextView = (TextView)convertView.findViewById(R.id.reserveTypeTextView);
            holder.reservePriceTextView = (TextView)convertView.findViewById(R.id.reservePriceTextView);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        final ReserveRoom mData= this.reserveRoomList.get(position);

        holder.storeNameTextView.setText(mData.getCompanyName());
        holder.roomCodeTextView.setText(mData.getReserveRoom());
        holder.reserveTypeTextView.setText(mData.getStatusName());    //만족도
        holder.reservePriceTextView.setText(PriceFormatUtil.getPriceFormat(String.valueOf(mData.getReserveFeeAmt())) + "원");


        /*if(mData.getImagePath() == null)
        {
            holder.thumbnailImageView.setImageResource(R.drawable.noimage_uploadphoto);
        }
        else
        {
            imageDownloader.download(WebServiceInformation.getImageDownloadUrl()+  mData.getStoreCode() + "/" +mData.getImagePath()+".jpg",
                    mData.getStoreCode() + mData.getImagePath()+".jpg", (ImageView) holder.thumbnailImageView);
        }*/

        return convertView;
    }

}