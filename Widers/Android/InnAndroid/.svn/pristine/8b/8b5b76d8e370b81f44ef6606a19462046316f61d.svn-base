package com.and.andelectronics.view.main.reserve;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.and.andelectronics.R;
import com.and.andelectronics.common.WebServiceInformation;
import com.and.andelectronics.common.util.PriceFormatUtil;
import com.and.andelectronics.common.util.image.ImageDownloader;
import com.and.andelectronics.infrastructure.model.lodgement.ReserveTime;
import com.and.andelectronics.infrastructure.model.lodgement.Room;
import com.and.andelectronics.view.main.home.AddressHeader;
import com.and.andelectronics.view.main.home.AdvertiseHeaderPagerAdapter;
import com.and.andelectronics.view.main.room.RoomActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Won on 2018-05-25.
 */
public class DayuseReserveTimeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 1;
    private Context context;

    ArrayList<ReserveTime> items;

    private ArrayList<String> times;

    //region 생성자
    public DayuseReserveTimeRecyclerAdapter(Context context, ArrayList<ReserveTime> items) {
        this.context=context;
        this.items=items;
        times = new ArrayList<>();
        for (ReserveTime reserveTime : items) {
            times.add(reserveTime.getHourOfDate().substring(11, 13) + "시");
        }
    }

    //endregion

    //region ViewHolder Override 메소드
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dayuse_reserve_time_grid, parent, false);
        return new DayuseReserveTimeRecyclerAdapter.VHItem(v);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof DayuseReserveTimeRecyclerAdapter.VHItem)
        {
            final ReserveTime item = items.get(position); //헤더가 있기 때문에 position -1해주라
            DayuseReserveTimeRecyclerAdapter.VHItem vhItem = (DayuseReserveTimeRecyclerAdapter.VHItem)holder;

            vhItem.reserveTimeTextView.setText(times.get(position));
            if(item.isSelected()){
                vhItem.reserveTimeTextView.setBackgroundResource(R.mipmap.btn_time_on);
                vhItem.reserveTimeTextView.setTextColor((ContextCompat.getColorStateList(context, R.color.white)));
            }else{
                vhItem.reserveTimeTextView.setBackgroundResource(R.mipmap.btn_time_off);
                vhItem.reserveTimeTextView.setTextColor((ContextCompat.getColorStateList(context, R.color.actionbar_red)));
            }

        }
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
    //endregion



    public class VHItem extends RecyclerView.ViewHolder {
        TextView reserveTimeTextView;


        public VHItem(View itemView) {
            super(itemView);
            reserveTimeTextView=(TextView)itemView.findViewById(R.id.reserveTimeTextView);
        }
    }
}