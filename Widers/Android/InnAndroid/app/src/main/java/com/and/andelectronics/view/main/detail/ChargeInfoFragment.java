package com.and.andelectronics.view.main.detail;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.and.andelectronics.R;
import com.desmond.parallaxviewpager.NotifyingScrollView;
import com.desmond.parallaxviewpager.ScrollViewFragment;

/**
 * Created by Won on 2016-01-16.
 * 요금정보 프래그먼트.. 빼도무방할듯
 */
public class ChargeInfoFragment extends ScrollViewFragment {

    public static final String TAG = ChargeInfoFragment.class.getSimpleName();

    public static android.support.v4.app.Fragment newInstance(int position) {
        ChargeInfoFragment fragment = new ChargeInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public ChargeInfoFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mPosition = getArguments().getInt(ARG_POSITION);

        View view = inflater.inflate(R.layout.fragment_chargeinfo_scrollview, container, false);
        mScrollView = (NotifyingScrollView) view.findViewById(R.id.chargeInfoScrollview);
        setScrollViewOnScrollListener();
        WebView chargeInfoWebView = (WebView)view.findViewById(R.id.chargeInfoWebView);
        chargeInfoWebView.loadUrl("https://m.busterminal.or.kr:444/reserve/default.aspx");

        return view;
    }
}
