package com.and.andelectronics.view.main.room;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.and.andelectronics.R;
import com.and.andelectronics.common.ApplicationInformation;
import com.and.andelectronics.common.WebServiceInformation;
import com.and.andelectronics.common.util.PriceFormatUtil;
import com.and.andelectronics.common.util.image.DetailImageDownloader;
import com.and.andelectronics.infrastructure.model.lodgement.Room;

/**
 * Created by Won on 2017-06-22.
 */
public class RoomImageViewPagerAdapter extends PagerAdapter {


    Context context;
    int count;
    String imageId = null;
    private LayoutInflater mInflater;
    private Room room;

    public static final String BASIC_DOWNLOAD_URL = WebServiceInformation.getImageDownloadUrl();
    public static final String CACHE_FOLDER = ApplicationInformation.LOCAL_CACHE_PATH;

    private final DetailImageDownloader detailImageDownloader = new DetailImageDownloader();
    public RoomImageViewPagerAdapter(Context c, int count, String imageId, Room room){
        super();
        this.count = count;
        this.context = c;
        this.mInflater = LayoutInflater.from(c);
        this.imageId = imageId;
        this.room = room;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return count;
    }



    public Object instantiateItem(View container, int position) {
        final int imgValue=position+1;

        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View page = inflater.inflate(R.layout.pager_room, null);
        final ImageView iv = (ImageView) page.findViewById(R.id.roomPagerImageView);

        /*detailImageDownloader.download(BASIC_DOWNLOAD_URL + imageId + "-0"
                        + imgValue + ".jpg", imageId + "-0" + imgValue + ".jpg",
                (ImageView) iv);*/

        detailImageDownloader.download(BASIC_DOWNLOAD_URL + "0001/04.jpg", "000104.jpg",
                (ImageView) iv);

        TextView roomNumberTextView=(TextView)page.findViewById(R.id.roomNumberTextView);
        TextView roomDayuseTextView = (TextView)page.findViewById(R.id.roomDayuseTextView);
        TextView roomDayusePriceTextView = (TextView)page.findViewById(R.id.roomDayusePriceTextView);
        TextView roomOvernightTextView = (TextView)page.findViewById(R.id.roomOvernightTextView);
        TextView roomOvernightPriceTextView = (TextView)page.findViewById(R.id.roomOvernightPriceTextView);

        roomNumberTextView.setText(room.getGradeName());
        roomDayusePriceTextView.setText(PriceFormatUtil.getPriceFormat(String.valueOf(room.getDayusePrice())) + "원");
        roomOvernightPriceTextView.setText(PriceFormatUtil.getPriceFormat(String.valueOf(room.getOvernightPrice())) + "원");


        ((ViewPager)container).addView(page, 0);
        return page;
    }


    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);

        System.gc();
    }

    @Override
    public boolean isViewFromObject(View pager, Object obj) {
        return pager == obj;
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(View arg0) {
    }

    @Override
    public void finishUpdate(View arg0) {
    }
}
