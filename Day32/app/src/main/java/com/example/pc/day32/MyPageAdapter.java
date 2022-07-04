package com.example.pc.day32;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyPageAdapter extends FragmentStatePagerAdapter {
    // 현재 viewPager의 선택된 페이지를 Page1, 2, 3, 4 Fragment 클래스와 연동하는 adapter

    MyPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        // i번째 아이템이 선택되었을 때 반환할 Fragment
        switch(i) {
            case PageInfo.FRAGMENT_PAGE1:
                return new Page1Fragment();

            case PageInfo.FRAGMENT_PAGE2:
                return new Page2Fragment();

            case PageInfo.FRAGMENT_PAGE3:
                return new Page3Fragment();

            case PageInfo.FRAGMENT_PAGE4:
                return new Page4Fragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PageInfo.PAGES;
    }
}
