package com.example.och.andsolutions_v2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.och.andsolutions_v2.PageAdapter;
import com.example.och.andsolutions_v2.PageInfo;
import com.example.och.andsolutions_v2.R;

public class Home extends Fragment {

    private RelativeLayout relativeLayout;
    ViewPager view_pager;
    private TabLayout tab_layout;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        relativeLayout = (RelativeLayout)inflater.inflate(R.layout.home_fragment, container, false);

        tab_layout = relativeLayout.findViewById(R.id.tab_layout);
        tab_layout.addTab(tab_layout.newTab().setText("내주변"));
        tab_layout.addTab(tab_layout.newTab().setText("단골매장"));
        tab_layout.addTab(tab_layout.newTab().setText("이벤트매장"));

        view_pager = relativeLayout.findViewById(R.id.view_pager);
        view_pager.setAdapter(new PageAdapter(getFragmentManager()));
        view_pager.setCurrentItem(PageInfo.NEAR_PAGE);

        view_pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_layout));
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                view_pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return relativeLayout;
    }
}
