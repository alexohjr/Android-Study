package com.and.andelectronics.view.main.home;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.and.andelectronics.R;
import com.and.andelectronics.common.LocationService;
import com.and.andelectronics.common.util.LocationUtil;
import com.and.andelectronics.common.util.PreferenceUtil;
import com.and.andelectronics.common.view.GPSSettingAlert;
import com.and.andelectronics.infrastructure.interfaces.ILodgementService;
import com.and.andelectronics.infrastructure.model.lodgement.Lodgement;
import com.and.andelectronics.infrastructure.model.lodgement.LodgementRequestData;
import com.and.andelectronics.view.main.MainTabFragmentActivity;
import com.and.andelectronics.view.main.map.LodgmentLocationMapFragmentActivity;
import com.and.andelectronics.view.main.map.StoreMapActivity;
import com.and.andelectronics.view.main.mypage.MyPageFragment;
import com.and.andelectronics.view.main.pleasure.RecommendFragment;
import com.and.andelectronics.webservice.lodgement.LodgementServiceXml;

import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Won on 2016-03-28.
 */
public class HomeFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener { //, AdapterViewCompat.OnItemClickListener {

    public static final String TAG = HomeFragment.class.getSimpleName();

    //region widget
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private HomeCardViewAdapter homeCardViewAdapter = null;
    private TextView homeHeaderAddressTextView;
    private Context context;
    private Button homeHeaderAddressRefreshButton;
    private AutoScrollViewPager homeHeaderAdvertiseViewPager;
    private AdvertiseHeaderPagerAdapter pagerAdapter;
    private android.app.ProgressDialog ProgressDialog;

    private Button homeSurroundButton;
    private Button homeLocationButton;
    private Button homeThemeButton;

    private Button homeRecommendButton;
    private Button homeMyChoiceButton;
    private Button homeMyPageButton;
    private ImageView homeMapImageView;
    //endregion

    private ILodgementService iLodgementService;
    private ArrayList<Lodgement> lodgementList;

    double latitude = 37.6099507;
    double longitude = 126.7236531;

    private AppCompatActivity activity;
    private MainTabFragmentActivity parentActivity;

    public static Fragment newInstance(int position) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        this.activity = (AppCompatActivity)getActivity();
        parentActivity = (MainTabFragmentActivity)activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_for_collapsing, container, false);
        this.onCreateViewInit(view);
        return view;
    }

    private void onCreateViewInit(View view) {
        // TODO Auto-generated method stub
        this.iLodgementService = new LodgementServiceXml(context);     //실제 사용할 서비스 WCF용

        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);


        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)view.findViewById(R.id.collapseBar);
        //collapsingToolbarLayout.setTitle("내 주변 모텔");

        this.homeMapImageView = (ImageView)view.findViewById(R.id.homeMapImageView);
        this.homeMapImageView.setOnClickListener(this);
        this.recyclerView = (RecyclerView) view.findViewById(R.id.homeRecyclerView);
        this.homeHeaderAddressTextView = (TextView)view.findViewById(R.id.homeHeaderAddressTextView);
        this.homeHeaderAddressRefreshButton = (Button)view.findViewById(R.id.homeHeaderAddressRefreshButton);
        this.homeHeaderAddressRefreshButton.setOnClickListener(this);

        this.homeHeaderAdvertiseViewPager = (AutoScrollViewPager)view.findViewById(R.id.homeHeaderAdvertiseViewPager);
        CircleIndicator indicator = (CircleIndicator)view.findViewById(R.id.indicator);
        this.pagerAdapter = new AdvertiseHeaderPagerAdapter(getChildFragmentManager());
        this.setViewPager(indicator);

        this.homeSurroundButton = (Button)view.findViewById(R.id.homeSurroundButton);
        this.homeSurroundButton.setOnClickListener(this);
        this.homeLocationButton = (Button)view.findViewById(R.id.homeLocationButton);
        this.homeLocationButton.setOnClickListener(this);
        this.homeThemeButton = (Button)view.findViewById(R.id.homeThemeButton);
        this.homeThemeButton.setOnClickListener(this);
        this.homeRecommendButton = (Button)view.findViewById(R.id.homeRecommendButton);
        this.homeRecommendButton.setOnClickListener(this);
        this.homeMyChoiceButton = (Button)view.findViewById(R.id.homeMyChoiceButton);
        this.homeMyChoiceButton.setOnClickListener(this);
        this.homeMyPageButton = (Button)view.findViewById(R.id.homeMyPageButton);
        this.homeMyPageButton.setOnClickListener(this);

        this.layoutManager = new LinearLayoutManager(this.context);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(layoutManager);


        getOnLocation();
        onRefresh();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.homeMapImageView:
                //내위치 근처에 있는 가맹점 표시
                //구글지도 호출
                Intent intent = new Intent(activity, StoreMapActivity.class);
                startActivity(intent);

                break;
            case R.id.homeHeaderAddressRefreshButton:
                getOnLocation();
                break;

            case R.id.homeLocationButton:
                replaceFragment(HomeLocationFragment.class);
                break;

            case R.id.homeSurroundButton:
                recyclerView.scrollToPosition(0);
                break;

            case R.id.homeThemeButton:
                replaceFragment(HomeLocationFragment.class);
                break;

            case R.id.homeRecommendButton:
                replaceFragment(RecommendFragment.class);
                parentActivity.changeTabButtonState(R.id.recommendTabButton);
                break;
            case R.id.homeMyChoiceButton:
                //내 찜 프레그먼트로 이동하도록 한다.
                replaceFragment(MyPageFragment.class);
                parentActivity.changeTabButtonState(R.id.mypageTabButton);
                break;
            case R.id.homeMyPageButton:
                replaceFragment(MyPageFragment.class);
                parentActivity.changeTabButtonState(R.id.mypageTabButton);
                break;


        }
    }


    private void replaceFragment(Class clazz) {
        Fragment fragment = null;
        try {
            fragment = (Fragment)clazz.getConstructor().newInstance();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

        if(fragment == null) return;

        //fragment replace
        final FragmentTransaction transaction = activity.getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.tabContentParentLayout, fragment);
        transaction.commit();
    }

    public  AddressHeader getHeader()
    {
        AddressHeader header = new AddressHeader();
        header.setAddress("");
        return header;
    }


    public Location getLocation()
    {
        LocationService locationService = new LocationService(this.context);
        Location location = locationService.getLocation();
        return location;
    }

    public String getAddress(Location location)
    {
        String address = "";
        if(location != null)
        {
            LocationUtil geocoder = new LocationUtil(this.context);
            address = geocoder.searchAddress(location.getLatitude(), location.getLongitude());
            PreferenceUtil.instance(context).putMyAddress(address);
            this.latitude = location.getLatitude();
            this.longitude = location.getLongitude();
        }
        else
        {
            //없으면 이렇게 하던지 아니면 프리퍼런스에 내 위치 저장해서 불러오던지 (가장 최근위치)
            address = getString(R.string.comm_location_off);
        }
        return address;
    }

    private void getOnLocation() {
        Location location = getLocation();
        if(location == null){
            GPSSettingAlert.showGpsSettingsAlert(this.context);
        }
        this.homeHeaderAddressTextView.setText(getAddress(location));
    }

    private void setViewPager(CircleIndicator indicator){
        homeHeaderAdvertiseViewPager.setAdapter(this.pagerAdapter);
        indicator.setViewPager(homeHeaderAdvertiseViewPager);
        homeHeaderAdvertiseViewPager.setInterval(5000); //AutoScrollViewPager 추가하여 자동 페이지 이동 추가 20180607 yowonsm
        homeHeaderAdvertiseViewPager.startAutoScroll();
        homeHeaderAdvertiseViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
               /* if(position < (pagerAdapter.getCount() / 3) ) {
                    homeHeaderAdvertiseViewPager.setCurrentItem(position + (pagerAdapter.getCount() / 3), false);  // 두번째 false 인자값은
                    //위치이동 에니메이션을 꺼준다
                } else if (position >= (pagerAdapter.getCount() / 3) *2) {      // true 로 바꿔서 실행해보면 어떤얘긴지
                    //단박에 알수 있다.
                    homeHeaderAdvertiseViewPager.setCurrentItem(position - (pagerAdapter.getCount() / 3), false);
                }*/
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onRefresh() {
        //풀투 리프레시
        new LodgementListAsync().execute();
        //새로고침 완료시에 아이콘 없앤다.
        //this.mSwipeRefreshLayout.setRefreshing(false);
    }

    /*@Override
    public void onItemClick(AdapterViewCompat<?> parent, View view, int position, long id) {

        Lodgement mData = this.homeCardViewAdapter.items.get(position);

        Bundle extras = new Bundle();

        Intent intent = new Intent(context, DetailViewLodgmentActivity.class);
        extras.putSerializable("obj", mData);
        intent.putExtras(extras);
        startActivity(intent);
    }*/


    private class LodgementListAsync extends AsyncTask<Void, String, Void> {

        @Override
        protected void onPostExecute(Void result) {
            HomeFragment.this.ProgressDialog.dismiss();
            homeCardViewAdapter = new HomeCardViewAdapter(context, lodgementList, 0);
            recyclerView.setAdapter(homeCardViewAdapter);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            HomeFragment.this.ProgressDialog = android.app.ProgressDialog.show(
                    context,
                    getResources().getString(R.string.async_store_title), getResources().getString(R.string.async_store_msg));

        }


        @Override
        protected Void doInBackground(Void... params) {

            LodgementRequestData reqData = new LodgementRequestData();
            reqData.setPosiX(String.valueOf(latitude));
            reqData.setPosiY(String.valueOf(longitude));
            reqData.setDistance("10");
            lodgementList = iLodgementService.getLodgementList(reqData);
            if(lodgementList != null)
            {

            }
            return null;
        }
    }


}

