package com.and.andelectronics.view.main.home;

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
import com.and.andelectronics.infrastructure.model.lodgement.Lodgement;
import com.and.andelectronics.view.main.detail.RoomGroupActivity;

import java.util.List;

/**
 * Created by Won on 2016-03-28.
 */
public class HomeLocationCardViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 1;

    public static final String BASIC_DOWNLOAD_URL = WebServiceInformation.getImageDownloadUrl();
    private Context context;

    private AddressHeader addressHeader;
    private String currentStoreCode;
    private int item_layout;
    private AdvertiseHeaderPagerAdapter pagerAdapter;
    private final ImageDownloader imageDownloader = new ImageDownloader();

    public List<Lodgement>  items;

    //region 생성자
    public HomeLocationCardViewAdapter(Context context, List<Lodgement> items, int item_layout) {
        this.context=context;
        this.items=items;
        this.item_layout=item_layout;
    }

    //endregion

    //region ViewHolder Override 메소드
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_cardview, parent, false);
        return new HomeLocationCardViewAdapter.VHItem(v);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof HomeLocationCardViewAdapter.VHItem)
        {
            final Lodgement item = items.get(position); //헤더가 있기 때문에 position -1해주라
            HomeLocationCardViewAdapter.VHItem vhItem = (HomeLocationCardViewAdapter.VHItem)holder;

            vhItem.title.setText(item.getStoreName());
            vhItem.addressTextView.setText(item.getAddr1());
            vhItem.distanceTextView.setText(item.getDistance() + "KM");
            vhItem.dayusePriceTextView.setText(PriceFormatUtil.getPriceFormat(String.valueOf(item.getDayusePrice())) + "원");
            vhItem.overnightPriceTextView.setText(PriceFormatUtil.getPriceFormat(String.valueOf(item.getOvernightPrice())) + "원");

            if(position/2 == 0)
            {

            }

            if(item.getImagePath() == null)
            {
                vhItem.image.setImageResource(R.mipmap.bg_main_page2);
            }
            else
            {
                imageDownloader.download(BASIC_DOWNLOAD_URL + item.getStoreCode() + "/" +item.getImagePath()+".jpg",
                        item.getStoreCode() +item.getImagePath()+".jpg", (ImageView) vhItem.image);

            }

            vhItem.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, item.getStr_name(), Toast.LENGTH_SHORT).show();

                    moveToRoomActivity(item.getStoreCode(), item);
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
        return this.items.size();   //Footer추가를 위해 카운트 에드
    }
    //endregion


    private void moveToRoomActivity(String storeCode, Lodgement lodgement){
        this.currentStoreCode = storeCode;
        Lodgement mData = lodgement;

        Bundle extras = new Bundle();

        Intent intent = new Intent(context, RoomGroupActivity.class);
        extras.putSerializable("obj", mData);
        intent.putExtras(extras);
        context.startActivity(intent);
    }

    public class VHItem extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView distanceTextView;
        TextView remarkTextView;
        TextView addressTextView;
        TextView overnightPriceTextView;
        TextView dayusePriceTextView;

        CardView cardview;

        public VHItem(View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.image);
            title=(TextView)itemView.findViewById(R.id.title);
            distanceTextView = (TextView)itemView.findViewById(R.id.distanceTextView);
            remarkTextView = (TextView)itemView.findViewById(R.id.remarkTextView);
            overnightPriceTextView = (TextView)itemView.findViewById(R.id.overnightPriceTextView);
            dayusePriceTextView = (TextView)itemView.findViewById(R.id.dayusePriceTextView);
            addressTextView = (TextView)itemView.findViewById(R.id.addressTextView);

            cardview=(CardView)itemView.findViewById(R.id.cardview);

        }
    }


}