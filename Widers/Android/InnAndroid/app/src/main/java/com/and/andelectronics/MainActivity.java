package com.and.andelectronics;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.and.andelectronics.slidingTab.SlidingTabLayout;
import com.desmond.parallaxviewpager.ParallaxFragmentPagerAdapter;
import com.desmond.parallaxviewpager.ParallaxViewPagerBaseActivity;

public class MainActivity extends ParallaxViewPagerBaseActivity {

    private ImageView mTopImage;
    private SlidingTabLayout mNavigBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 초기값 설정하기
        initValues();

        mTopImage = (ImageView) findViewById(R.id.image); // 헤더 탑 이미지
        mViewPager = (ViewPager) findViewById(R.id.view_pager); // 뷰 페이저
        mNavigBar = (SlidingTabLayout) findViewById(R.id.navig_tab); // 헤더 이미지 밑 슬라이드 탭
        mHeader = findViewById(R.id.header); // 헤더

        if (savedInstanceState != null) {
            mTopImage.setTranslationY(savedInstanceState.getFloat(IMAGE_TRANSLATION_Y));
            mHeader.setTranslationY(savedInstanceState.getFloat(HEADER_TRANSLATION_Y));
        }
        // 어댑터 연결
        setupAdapter();
    }

    @Override
    protected void initValues() {
        int tabHeight = getResources().getDimensionPixelSize(R.dimen.tab_height); // 50dp
        mMinHeaderHeight = getResources().getDimensionPixelSize(R.dimen.min_header_height); // 350dp
        mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.header_height); // 350dp
        mMinHeaderTranslation = -mMinHeaderHeight + tabHeight; // -350 + 50 = -300

        mNumFragments = 4;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putFloat(IMAGE_TRANSLATION_Y, mTopImage.getTranslationY());
        outState.putFloat(HEADER_TRANSLATION_Y, mHeader.getTranslationY());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void setupAdapter() {
        if (mAdapter == null) {
            mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mNumFragments);
        }

        // 어댑터 연결
        mViewPager.setAdapter(mAdapter);
        // 몇개를 미리 로딩할지 정함
        mViewPager.setOffscreenPageLimit(mNumFragments);
        // 페이지 전환시 발생할 이벤트 리스너
        mNavigBar.setOnPageChangeListener(getViewPagerChangeListener());
        // 뷰페이저 등록
        mNavigBar.setViewPager(mViewPager);
    }

    @Override
    protected void scrollHeader(int scrollY) {
        float translationY = Math.max(-scrollY, mMinHeaderTranslation);
        mHeader.setTranslationY(translationY);
        mTopImage.setTranslationY(-translationY / 3);
    }

//    private int getActionBarHeight() {
//        if (mActionBarHeight != 0) {
//            return mActionBarHeight;
//        }
//
//        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB){
//            getTheme().resolveAttribute(android.R.attr.actionBarSize, mTypedValue, true);
//        } else {
//            getTheme().resolveAttribute(R.attr.actionBarSize, mTypedValue, true);
//        }
//
//        mActionBarHeight = TypedValue.complexToDimensionPixelSize(
//                mTypedValue.data, getResources().getDisplayMetrics());
//
//        return mActionBarHeight;
//    }

    private static class ViewPagerAdapter extends ParallaxFragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm, int numFragments) {
            super(fm, numFragments);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch (position) {
                case 0:
                    fragment = FirstScrollViewFragment.newInstance(0);
                    break;

                case 1:
                    fragment = SecondScrollViewFragment.newInstance(1);
                    break;

                case 2:
                    fragment = DemoListViewFragment.newInstance(2);
                    break;

                case 3:
                    fragment = DemoRecyclerViewFragment.newInstance(3);
                    break;

                default:
                    throw new IllegalArgumentException("Wrong page given " + position);
            }
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "ScrollView";

                case 1:
                    return "ScrollView";

                case 2:
                    return "ListView";

                case 3:
                    return "RecyclerView";

                default:
                    throw new IllegalArgumentException("wrong position for the fragment in vehicle page");
            }
        }
    }
}
