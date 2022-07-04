package com.and.andelectronics.view.main.room;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import com.and.andelectronics.infrastructure.model.lodgement.Room;

import java.util.List;

/**
 * Created by Won on 2018-05-25.
 */
public class RoomNumberCardViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 1;
    public static final String BASIC_DOWNLOAD_URL = WebServiceInformation.getImageDownloadUrl();
    private Context context;

    private final ImageDownloader imageDownloader = new ImageDownloader();

    public List<Room>  items;

    //region 생성자
    public RoomNumberCardViewAdapter(Context context, List<Room> items) {
        this.context=context;
        this.items=items;
    }

    //endregion

    //region ViewHolder Override 메소드
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room_number_cardview, parent, false);
        return new RoomNumberCardViewAdapter.VHItem(v);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof RoomNumberCardViewAdapter.VHItem)
        {
            final Room item = items.get(position); //헤더가 있기 때문에 position -1해주라
            RoomNumberCardViewAdapter.VHItem vhItem = (RoomNumberCardViewAdapter.VHItem)holder;

            vhItem.roomNumberTextView.setText(item.getRoomCode() + "호");
            vhItem.roomNumberDayusePriceTextView.setText(PriceFormatUtil.getPriceFormat(String.valueOf(item.getDayusePrice())) + "원");
            vhItem.roomNumberOvernightPriceTextView.setText(PriceFormatUtil.getPriceFormat(String.valueOf(item.getOvernightPrice())) + "원");

            vhItem.roomNumberMaxUseTimeTextView.setText("최대 " + item.getRentTime() + "시간");
            vhItem.roomNumberEntTimeTextView.setText(item.getCheckIn() + " 입실");

            if(position/2 == 0)
            {

            }

            if(item.getEmptyRoomCount() == 0)
            {
                vhItem.image.setImageResource(R.mipmap.bg_main_page2);
            }
            else
            {
                imageDownloader.download(BASIC_DOWNLOAD_URL + item.getCompanyCode() + "/" + "0" + (position + 1) +".jpg",
                        item.getCompanyCode() + "/" + "0" + (position + 1) +".jpg",
                        (ImageView) vhItem.image);

            }

            vhItem.roomNumberCardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moveToRoomActivity(item);
                }
            });

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


    private void moveToRoomActivity(Room room){

        Bundle extras = new Bundle();
        Intent intent = new Intent(context, RoomActivity.class);
        extras.putSerializable("obj", room);
        intent.putExtras(extras);
        context.startActivity(intent);

    }

    public class VHItem extends RecyclerView.ViewHolder {
        ImageView image;
        TextView roomNumberTextView;

        TextView roomNumberDayuseTextView;
        TextView roomNumberMaxUseTimeTextView;
        TextView roomNumberDayusePriceTextView;

        TextView roomNumberOvernightTextView;
        TextView roomNumberEntTimeTextView;
        TextView roomNumberOvernightPriceTextView;


        CardView roomNumberCardview;

        public VHItem(View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.image);

            roomNumberTextView=(TextView)itemView.findViewById(R.id.roomNumberTextView);
            //대실
            roomNumberDayuseTextView = (TextView)itemView.findViewById(R.id.roomNumberDayuseTextView);
            roomNumberMaxUseTimeTextView = (TextView)itemView.findViewById(R.id.roomNumberMaxUseTimeTextView);
            roomNumberDayusePriceTextView = (TextView)itemView.findViewById(R.id.roomNumberDayusePriceTextView);
            //숙박
            roomNumberOvernightTextView = (TextView)itemView.findViewById(R.id.roomNumberOvernightTextView);
            roomNumberEntTimeTextView = (TextView)itemView.findViewById(R.id.roomNumberEntTimeTextView);
            roomNumberOvernightPriceTextView = (TextView)itemView.findViewById(R.id.roomNumberOvernightPriceTextView);

            roomNumberCardview=(CardView)itemView.findViewById(R.id.roomNumberCardview);

        }
    }
}