package com.example.och.andsolutions_v2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {

    PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch(i) {
            case PageInfo.NEAR_PAGE:
                return new NearPage();

            case PageInfo.FAVORITE_PAGE:
                return new FavoritePage();

            case PageInfo.EVENT_PAGE:
                return new EventPage();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PageInfo.PAGES;
    }
}
