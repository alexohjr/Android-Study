package com.and.andelectronics.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.and.andelectronics.R;

/**
 * Created by Won on 2016-04-12.
 */
public class PageFragment extends Fragment {

    private int mPageNumber;
    private ImageView homeAdvertiseImageView;

    public static PageFragment create(int pageNumber) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt("page", pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mPageNumber = getArguments().getInt("page");
        mPageNumber = getArguments() != null ? getArguments().getInt("page") : 0;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.pager_advertise, container, false);
        homeAdvertiseImageView = (ImageView)rootView.findViewById(R.id.homeAdvertiseImageView);
        if(mPageNumber == 1){
            homeAdvertiseImageView.setBackgroundResource(R.mipmap.bg_main_page2);
        }else if(mPageNumber == 2){
            homeAdvertiseImageView.setBackgroundResource(R.mipmap.bg_main_pag3);
        }
        return rootView;
    }
}
