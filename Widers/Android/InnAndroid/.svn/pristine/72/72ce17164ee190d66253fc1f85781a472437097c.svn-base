package com.and.andelectronics.view.main.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.and.andelectronics.R;
import com.desmond.parallaxviewpager.NotifyingScrollView;
import com.desmond.parallaxviewpager.ScrollViewFragment;

/**
 * Created by Won on 2016-01-16.
 */
public class NoticeInfoFragment extends ScrollViewFragment {

    public static final String TAG = NoticeInfoFragment.class.getSimpleName();

    public static android.support.v4.app.Fragment newInstance(int position) {
        NoticeInfoFragment fragment = new NoticeInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public NoticeInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mPosition = getArguments().getInt(ARG_POSITION);

        View view = inflater.inflate(R.layout.fragment_noticeinfo_scrollview, container, false);
        mScrollView = (NotifyingScrollView) view.findViewById(R.id.noticeInfoScrollview);
        setScrollViewOnScrollListener();
        WebView chargeInfoWebView = (WebView) view.findViewById(R.id.noticeInfoWebView);
        chargeInfoWebView.loadUrl("http://www.leagueofmc.kr/");


        return view;
    }

}