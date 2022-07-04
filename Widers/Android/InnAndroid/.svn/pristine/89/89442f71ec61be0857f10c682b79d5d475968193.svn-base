package com.and.andelectronics.view.main.search;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.and.andelectronics.R;

import com.and.andelectronics.view.widget.MainActionBar;

import java.util.Locale;


/**
 * Created by Won on 2016-04-20.
 */
public class SearchFragment extends Fragment {

    private Activity activity;
    private Context context;
    private MainActionBar mainActionBar;
    private ViewPager searchViewPager;
    private MyPagerAdapter mSectionsPagerAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            for (String key : savedInstanceState.keySet()) {
            }
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, null);
        onCreateViewInit(view);
        return view;

    }

    private void onCreateViewInit(View view) {
        // TODO Auto-generated method stub
//        this.mainActionBar = new MainActionBar(activity, null);
//        this.mainActionBar = (MainActionBar) view.findViewById(R.id.mainActionBar);
//        TextView title = (TextView) this.mainActionBar.findViewById(R.id.TV_actionTitle);
//        title.setText(R.string.comm_search);
        toolbarSetting(view);
        this.searchViewPager = (ViewPager)view.findViewById(R.id.searchViewPager);

        mSectionsPagerAdapter = new MyPagerAdapter(this.getChildFragmentManager());
        // Set up the ViewPager with the sections adapter.
        searchViewPager.setAdapter(mSectionsPagerAdapter);


    }

    private void toolbarSetting(View view){
        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(R.color.actionbar_red);
        TextView roomGroupToolbar_title = (TextView)view.findViewById(R.id.toolbarTitle);
        roomGroupToolbar_title.setText("검색");
        Button leftButton1 = (Button)view.findViewById(R.id.toolbarLeftButton1);
        Button leftButton2 = (Button)view.findViewById(R.id.toolbarLeftButton2);
        Button toolbarRightButton1 = (Button)view.findViewById(R.id.toolbarRightButton1);
        Button rightButton2 = (Button)view.findViewById(R.id.toolbarRightButton2);
        ImageView titleImage = (ImageView)view.findViewById(R.id.toolbarTitleImage);
//        leftButton1.setOnClickListener(this);
//        leftButton2.setOnClickListener(this);
//        toolbarRightButton1.setOnClickListener(this);
//        rightButton2.setOnClickListener(this);

        leftButton1.setVisibility(View.INVISIBLE);
        leftButton2.setVisibility(View.INVISIBLE);
        toolbarRightButton1.setVisibility(View.INVISIBLE);
        rightButton2.setVisibility(View.INVISIBLE);
        titleImage.setVisibility(View.GONE);

        toolbarRightButton1.setBackgroundResource(R.mipmap.btn_sub4_roomsave_off);
        rightButton2.setBackgroundResource(R.mipmap.btn_sub4_share);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            for (String key : savedInstanceState.keySet()) {
            }
        }
        super.onActivityCreated(savedInstanceState);
    }

    public static class MyPagerAdapter extends FragmentStatePagerAdapter {
        private static int PAGE_COUNT = 2;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return AreaFragment.newInstance(0, "지역별");
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return SubwaySurroundFragment.newInstance(1, "역주변");
                //case 2: // Fragment # 1 - This will show SecondFragment
                   // return SecondFragment.newInstance(2, "Page # 3");
                default:
                    return AreaFragment.newInstance(0, "지역별");
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            //return "Page " + position;
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return "지역별";
                case 1:
                    return "역주변";
            }
            return "지역별";
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
  /*  public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }



        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if(position == 0 ){
                return AreaFragment.newInstance(0);

            }else {
                return SubwaySurroundFragment.newInstance(position);
            }

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return "지역별";
                case 1:
                    return "역주변";
            }
            return null;
        }
    }*/


}