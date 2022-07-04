package com.and.andelectronics.test;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
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
import com.and.andelectronics.view.main.detail.DetailViewLodgmentActivity;
import com.and.andelectronics.view.main.home.AddressHeader;
import com.and.andelectronics.view.main.home.AdvertiseHeaderPagerAdapter;
import java.util.List;

/**
 * Created by Won on 2016-03-28.
 */
public class RecyclerCardViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
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
    public RecyclerCardViewAdapter(Context context, List<Lodgement> items, int item_layout, AddressHeader header, AdvertiseHeaderPagerAdapter pagerAdapter) {
        this.context=context;
        this.items=items;
        this.item_layout=item_layout;
        this.addressHeader = header;
        this.pagerAdapter = pagerAdapter;

    }
    //endregion


    //region ViewHolder Override 메소드
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER)
        {
            View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.header_home, null);
            return new VHHeader(v);
        }
        else if(viewType == TYPE_ITEM)
        {
            View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, null);
            return new VHItem(v);
        }

        return null;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof VHHeader)
        {
            final VHHeader vhHeader = (VHHeader)holder;
            vhHeader.homeHeaderAdvertiseViewPager.setAdapter(this.pagerAdapter);
            vhHeader.homeHeaderAdvertiseViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    if(position < (pagerAdapter.getCount() / 3) ) {
                        vhHeader.homeHeaderAdvertiseViewPager.setCurrentItem(position + (pagerAdapter.getCount() / 3), false);  // 두번째 false 인자값은
                        //위치이동 에니메이션을 꺼준다
                    } else if (position >= (pagerAdapter.getCount() / 3) *2) {      // true 로 바꿔서 실행해보면 어떤얘긴지
                        //단박에 알수 있다.
                        vhHeader.homeHeaderAdvertiseViewPager.setCurrentItem(position - (pagerAdapter.getCount() / 3), false);
                    }
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
        else if(holder instanceof  VHItem)
        {
            final Lodgement item = items.get(position -1); //헤더가 있기 때문에 position -1해주라
            VHItem vhItem = (VHItem)holder;

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
                vhItem.image.setImageResource(R.drawable.noimage_uploadphoto);
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
        if(isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position)
    {
        return position == 0;
    }
    @Override
    public int getItemCount() {
        return this.items.size();
    }
    //endregion

    private void moveToRoomActivity(String storeCode, Lodgement lodgement){
        this.currentStoreCode = storeCode;
        Lodgement mData = lodgement;

        Bundle extras = new Bundle();

        Intent intent = new Intent(context, DetailViewLodgmentActivity.class);
        extras.putSerializable("obj", mData);
        intent.putExtras(extras);
        context.startActivity(intent);
    }

    /**
     * ViewHolderHeader inner Class
     * 헤더아이템(주소, 퀵버튼 등 넣기위해 만들었따)
     */
    public class VHHeader extends RecyclerView.ViewHolder {
        ViewPager homeHeaderAdvertiseViewPager;

        public VHHeader(View itemView) {
            super(itemView);
            homeHeaderAdvertiseViewPager = (ViewPager) itemView.findViewById(R.id.homeHeaderAdvertiseViewPager);
        }
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