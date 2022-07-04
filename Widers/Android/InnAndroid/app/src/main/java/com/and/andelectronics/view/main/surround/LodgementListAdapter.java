package com.and.andelectronics.view.main.surround;

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

import java.util.ArrayList;

/**
 * Created by Won on 2017-06-17.
 */
public class LodgementListAdapter extends BaseAdapter {

    private class ViewHolder
    {
        private ImageView thumbnailImageView;
        private TextView storeNameTextView;
        private TextView distanceTextView;
        private TextView satisfactionTextView;
        private TextView overnightPriceTextView;
        private TextView dayusePriceTextView;
        private TextView storePromotionTextView;
        private TextView storeNoticeTextView;
    }


    private final ImageDownloader imageDownloader = new ImageDownloader();
    private ViewHolder holder;
    private Context mContext = null;
    public ArrayList<Lodgement> lodgementList;


    public LodgementListAdapter(Context context, ArrayList<Lodgement> lodgements){
        super();
        this.mContext = context;
        this.lodgementList = lodgements;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return this.lodgementList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return this.lodgementList.get(position);
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
            convertView = inflater.inflate(R.layout.item_lodgement_surround, null);
            holder.thumbnailImageView = (ImageView)convertView.findViewById(R.id.thumbnailImageView);
            holder.storeNameTextView = (TextView)convertView.findViewById(R.id.storeNameTextView);
            holder.distanceTextView = (TextView)convertView.findViewById(R.id.distanceTextView);
            holder.satisfactionTextView = (TextView)convertView.findViewById(R.id.satisfactionTextView);
            holder.dayusePriceTextView = (TextView)convertView.findViewById(R.id.dayusePriceTextView);
            holder.overnightPriceTextView = (TextView)convertView.findViewById(R.id.overnightPriceTextView);
            holder.storeNoticeTextView = (TextView)convertView.findViewById(R.id.storeNoticeTextView);
            holder.storePromotionTextView = (TextView)convertView.findViewById(R.id.storePromotionTextView);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        final Lodgement mData= this.lodgementList.get(position);

        holder.storeNameTextView.setText(mData.getStoreName());
        holder.distanceTextView.setText(mData.getDistance());
        holder.satisfactionTextView.setText("100%");    //만족도
        holder.dayusePriceTextView.setText(PriceFormatUtil.getPriceFormat(String.valueOf(mData.getDayusePrice())) + "원");
        holder.overnightPriceTextView.setText(PriceFormatUtil.getPriceFormat(String.valueOf(mData.getOvernightPrice())) + "원");
        if(position/2 == 0)
        {
            holder.storeNoticeTextView.setVisibility(View.GONE);
            holder.storePromotionTextView.setVisibility(View.GONE);
        }else
        {
            holder.storeNoticeTextView.setVisibility(View.VISIBLE);
            holder.storePromotionTextView.setVisibility(View.VISIBLE);
        }
        holder.storeNoticeTextView.setText("업체공지");
        holder.storePromotionTextView.setText("프로모숀");
        if(mData.getImagePath() == null)
        {
            holder.thumbnailImageView.setImageResource(R.drawable.noimage_uploadphoto);
        }
        else
        {
            imageDownloader.download(WebServiceInformation.getImageDownloadUrl()+  mData.getStoreCode() + "/" +mData.getImagePath()+".jpg",
                    mData.getStoreCode() + mData.getImagePath()+".jpg", (ImageView) holder.thumbnailImageView);
        }

        return convertView;
    }

}