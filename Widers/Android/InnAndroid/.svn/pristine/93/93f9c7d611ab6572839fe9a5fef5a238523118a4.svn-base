package com.and.andelectronics.view.main.detail;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.and.andelectronics.Container;
import com.and.andelectronics.R;
import com.and.andelectronics.common.ActivityManager;
import com.and.andelectronics.common.WebServiceInformation;
import com.and.andelectronics.common.util.DateUtil;
import com.and.andelectronics.common.util.PreferenceUtil;
import com.and.andelectronics.infrastructure.interfaces.ILodgementService;
import com.and.andelectronics.infrastructure.model.lodgement.Lodgement;
import com.and.andelectronics.infrastructure.model.lodgement.Room;
import com.and.andelectronics.infrastructure.model.lodgement.RoomRequestData;
import com.and.andelectronics.webservice.lodgement.LodgementServiceXml;

import java.util.ArrayList;

import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by yowonsm on 2018-05-25.
 */

public class RoomGroupActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;

    private ViewPager roomGroupViewPager;
    private LinearLayoutManager layoutManager;
    private RecyclerView roomGroupRecyclerView;
    private RoomGroupCardViewAdapter roomGroupCardViewAdapter = null;
    private RoomGroupHeaderPagerAdapter roomGroupHeaderPagerAdapter;


    private Lodgement lodgement;
    private static String storeCode;
    private ArrayList<Room> roomList;
    private android.app.ProgressDialog ProgressDialog;

    private ILodgementService iLodgementService;
    public static final String BASIC_DOWNLOAD_URL = WebServiceInformation.getImageDownloadUrl();

    private Container container;
    private ActivityManager activityManager = ActivityManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_group_collapsing);

        this.context = this;
        this.activityManager.addActivity(this);
        this.container = (com.and.andelectronics.Container)getApplicationContext();
        lodgement = (Lodgement)this.getIntent().getExtras().getSerializable("obj");

        this.iLodgementService = new LodgementServiceXml(this);
        this.storeCode = lodgement.getStoreCode();
        this.container.setCurrentLodgement(lodgement);
        this.activityInit();

    }

    /*
     * 액티비티 컨트롤러 초기화
     */
    private void activityInit(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        String address = PreferenceUtil.instance(context).getMyAddress();
        toolbar.setTitle(address);
        setSupportActionBar(toolbar);

        toolbarSetting();

        this.roomGroupRecyclerView = (RecyclerView) findViewById(R.id.roomGroupRecyclerView);

        this.roomGroupViewPager = (ViewPager)findViewById(R.id.roomGroupViewPager);
        CircleIndicator indicator = (CircleIndicator)findViewById(R.id.indicator);
        this.roomGroupHeaderPagerAdapter = new RoomGroupHeaderPagerAdapter(getSupportFragmentManager());
        this.setViewPager(indicator);

        this.layoutManager = new LinearLayoutManager(this.context);
        this.roomGroupRecyclerView.setHasFixedSize(true);
        this.roomGroupRecyclerView.setLayoutManager(layoutManager);

        onRefresh();
    }
    //region 툴바 셋팅
    private void toolbarSetting(){
        TextView roomGroupToolbar_title = (TextView)findViewById(R.id.toolbarTitle);
        roomGroupToolbar_title.setText(lodgement.getAddr1());
        Button leftButton1 = (Button)findViewById(R.id.toolbarLeftButton1);
        Button leftButton2 = (Button)findViewById(R.id.toolbarLeftButton2);
        Button rightButton1 = (Button)findViewById(R.id.toolbarRightButton1);
        Button rightButton2 = (Button)findViewById(R.id.toolbarRightButton2);
        ImageView titleImage = (ImageView)findViewById(R.id.toolbarTitleImage);
        leftButton1.setOnClickListener(this);
        leftButton2.setOnClickListener(this);
        rightButton1.setOnClickListener(this);
        rightButton2.setOnClickListener(this);

        leftButton1.setVisibility(View.VISIBLE);
        leftButton2.setVisibility(View.INVISIBLE);
        rightButton1.setVisibility(View.INVISIBLE);
        rightButton2.setVisibility(View.INVISIBLE);
        titleImage.setVisibility(View.GONE);
    }
    //endregion


    private void setViewPager(CircleIndicator indicator){
        roomGroupViewPager.setAdapter(this.roomGroupHeaderPagerAdapter);
        indicator.setViewPager(roomGroupViewPager);
        roomGroupViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                /*if(position < (roomGroupHeaderPagerAdapter.getCount() / 3) ) {
                    roomGroupViewPager.setCurrentItem(position + (roomGroupHeaderPagerAdapter.getCount() / 3), false);  // 두번째 false 인자값은
                    //위치이동 에니메이션을 꺼준다
                } else if (position >= (roomGroupHeaderPagerAdapter.getCount() / 3) *2) {      // true 로 바꿔서 실행해보면 어떤얘긴지
                    //단박에 알수 있다.
                    roomGroupViewPager.setCurrentItem(position - (roomGroupHeaderPagerAdapter.getCount() / 3), false);
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

    public void onRefresh() {
        //풀투 리프레시
        new RoomListAsync().execute();
        //새로고침 완료시에 아이콘 없앤다.
        //this.mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbarLeftButton1:
                onBackPressed();
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityManager.removeActivity(this);
    }


    private class RoomGroupHeaderPagerAdapter extends FragmentStatePagerAdapter {
        private static final int COUNT = 5;
        public RoomGroupHeaderPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // 해당하는 page의 Fragment를 생성합니다.
            return RoomGroupViewPagerFragment.create(position % COUNT);
        }

        @Override
        public int getCount() {
            return COUNT;  // 총 5개의 page를 보여줍니다.
        }


    }

    private class RoomListAsync extends AsyncTask<Void, String, Void> {

        @Override
        protected void onPostExecute(Void result)
        {
            RoomGroupActivity.this.ProgressDialog.dismiss();

            if(roomList != null){
                roomGroupCardViewAdapter = new RoomGroupCardViewAdapter(context, roomList, container.getCurrentLodgement());
                roomGroupRecyclerView.setAdapter(roomGroupCardViewAdapter);
            }


        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            RoomGroupActivity.this.ProgressDialog = android.app.ProgressDialog.show(
                    context,
                    getResources().getString(R.string.comm_stateRoom), getResources().getString(R.string.async_store_msg));

        }


        @Override
        protected Void doInBackground(Void... params) {

            RoomRequestData roomRequestData = new RoomRequestData();
            roomRequestData.setCompanyCode(storeCode);
            roomRequestData.setAction("G"); //그룹별 조회라서 G

            //roomRequestData.setReserveDate(container.getReserveFromDate().substring(0, 10));
            //roomRequestData.setReserveDate(DateUtil.nowDateFormat("yyyy-MM-dd"));    //날짜선택을 어떻게 할지 모르겠음 20180525 yowonsm
            roomList = iLodgementService.getRoomEmptyList(roomRequestData);

            return null;
        }
    }

}
