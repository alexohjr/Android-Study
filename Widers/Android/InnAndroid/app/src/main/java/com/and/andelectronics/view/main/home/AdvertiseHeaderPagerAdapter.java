package com.and.andelectronics.view.main.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.and.andelectronics.test.PageFragment;

/**
 * Created by Won on 2016-04-13.
 */
public class AdvertiseHeaderPagerAdapter extends FragmentStatePagerAdapter {
    private static final int COUNT = 5;
    public AdvertiseHeaderPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // 해당하는 page의 Fragment를 생성합니다.
        return PageFragment.create(position % COUNT);
    }

    @Override
    public int getCount() {
        return COUNT;  // 총 5개의 page를 보여줍니다.
    }


}