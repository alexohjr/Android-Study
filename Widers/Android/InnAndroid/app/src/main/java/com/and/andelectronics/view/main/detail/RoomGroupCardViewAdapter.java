package com.and.andelectronics.view.main.detail;

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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.and.andelectronics.R;
import com.and.andelectronics.common.WebServiceInformation;
import com.and.andelectronics.common.util.PriceFormatUtil;
import com.and.andelectronics.common.util.image.ImageDownloader;
import com.and.andelectronics.infrastructure.model.lodgement.Lodgement;
import com.and.andelectronics.infrastructure.model.lodgement.Room;
import com.and.andelectronics.view.main.home.AddressHeader;
import com.and.andelectronics.view.main.home.AdvertiseHeaderPagerAdapter;
import com.and.andelectronics.view.main.room.RoomActivity;
import com.and.andelectronics.view.main.roomservice.RoomServiceItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Won on 2018-05-25.
 */
public class RoomGroupCardViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 1;
    public static final String BASIC_DOWNLOAD_URL = WebServiceInformation.getImageDownloadUrl();
    private Context context;

    private Lodgement currentLodgement;
    private AddressHeader addressHeader;
    private String currentStoreCode;
    private int item_layout;
    private AdvertiseHeaderPagerAdapter pagerAdapter;
    private final ImageDownloader imageDownloader = new ImageDownloader();

    public List<Room>  items;
    ArrayList<RoomServiceItem> roomServiceItems = new ArrayList<>();
    ArrayList<RoomServiceItem> roomServiceItems2 = new ArrayList<>();
    RoomGroupServiceGridAdapter adapter;
    RoomGroupServiceGridAdapter adapter2;
    //region 생성자
    public RoomGroupCardViewAdapter(Context context, List<Room> items, Lodgement lodgement) {
        this.context=context;
        this.items=items;
        this.currentLodgement = lodgement;

        roomServiceItems.add(new RoomServiceItem("", R.mipmap.icon_main_service_car, true));
        roomServiceItems.add(new RoomServiceItem("", R.mipmap.icon_main_service_drink, true));
        roomServiceItems.add(new RoomServiceItem("", R.mipmap.icon_main_service_carom, false));
        roomServiceItems2.add(new RoomServiceItem("", R.mipmap.icon_main_service_game, true));
        roomServiceItems2.add(new RoomServiceItem("", R.mipmap.icon_main_service_move, false));
        roomServiceItems2.add(new RoomServiceItem("", R.mipmap.icon_main_service_up, false));
        adapter = new RoomGroupServiceGridAdapter (context,
                R.layout.item_common_grid,       // GridView 항목의 레이아웃 row.xml
                roomServiceItems);    // 데이터

        adapter2 = new RoomGroupServiceGridAdapter (context,
                R.layout.item_common_grid,       // GridView 항목의 레이아웃 row.xml
                roomServiceItems2);    // 데이터
    }

    //endregion

    //region ViewHolder Override 메소드
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room_group_cardview, parent, false);
        return new RoomGroupCardViewAdapter.VHItem(v);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof RoomGroupCardViewAdapter.VHItem)
        {
            final Room item = items.get(position); //헤더가 있기 때문에 position -1해주라
            RoomGroupCardViewAdapter.VHItem vhItem = (RoomGroupCardViewAdapter.VHItem)holder;

            vhItem.roomGroupNameTextView.setText(item.getGradeName());
            vhItem.roomGroupDayusePrice.setText(PriceFormatUtil.getPriceFormat(String.valueOf(item.getDayusePrice())) + "원");
            vhItem.roomGroupOvernightPrice.setText(PriceFormatUtil.getPriceFormat(String.valueOf(item.getOvernightPrice())) + "원");

            if(position/2 == 0)
            {
                vhItem.roomGroupServiceItemGridView.setAdapter(adapter);  // 커스텀 아답타를 GridView 에 적용
            }else{
                vhItem.roomGroupServiceItemGridView.setAdapter(adapter2);  // 커스텀 아답타를 GridView 에 적용
            }

            if(item.getEmptyRoomCount() == 0)
            {
                vhItem.image.setImageResource(R.mipmap.bg_main2);
            }
            else
            {
                imageDownloader.download(BASIC_DOWNLOAD_URL + currentLodgement.getStoreCode() + "/" + "0" + (position + 1) +".jpg",
                        currentLodgement.getStoreCode()  + "/" + "0" + (position + 1) +".jpg",
                        (ImageView) vhItem.image);

            }

            vhItem.roomGroupCardview.setOnClickListener(new View.OnClickListener() {
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
        //Intent intent = new Intent(context, RoomNumberActivity.class);
        Intent intent = new Intent(context, RoomActivity.class);
        extras.putSerializable("obj", room);
        intent.putExtras(extras);
        context.startActivity(intent);

    }

    public class VHItem extends RecyclerView.ViewHolder {
        ImageView image;
        ImageView startImageView;
        TextView roomGroupNameTextView;
        TextView roomGroupDayuseTextView;
        TextView roomGroupDayusePrice;
        TextView roomGroupOvernightTextView;
        TextView roomGroupOvernightPrice;
        TextView dayusePriceTextView;
        CardView roomGroupCardview;

        GridView roomGroupServiceItemGridView;

        public VHItem(View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.image);
            startImageView=(ImageView)itemView.findViewById(R.id.startImageView);
            roomGroupNameTextView=(TextView)itemView.findViewById(R.id.roomGroupNameTextView);
            roomGroupDayuseTextView = (TextView)itemView.findViewById(R.id.roomGroupDayuseTextView);
            roomGroupDayusePrice = (TextView)itemView.findViewById(R.id.roomGroupDayusePrice);
            roomGroupOvernightTextView = (TextView)itemView.findViewById(R.id.roomGroupOvernightTextView);
            roomGroupOvernightPrice = (TextView)itemView.findViewById(R.id.roomGroupOvernightPrice);

            roomGroupCardview=(CardView)itemView.findViewById(R.id.roomGroupCardview);

            ArrayList<RoomServiceItem> items = new ArrayList<>();
            items.add(new RoomServiceItem("", R.mipmap.icon_main_service_car, true));
            items.add(new RoomServiceItem("", R.mipmap.icon_main_service_drink, true));
            items.add(new RoomServiceItem("", R.mipmap.icon_main_service_carom, false));
            items.add(new RoomServiceItem("", R.mipmap.icon_main_service_game, true));
            items.add(new RoomServiceItem("", R.mipmap.icon_main_service_move, false));
            items.add(new RoomServiceItem("", R.mipmap.icon_main_service_up, false));
            roomGroupServiceItemGridView = (GridView) itemView.findViewById(R.id.roomGroupServiceItemGridView);
            // 커스텀 아답타 생성


        }
    }
}