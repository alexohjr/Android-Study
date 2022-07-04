package com.and.andelectronics.view.main.detail;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.and.andelectronics.Container;
import com.and.andelectronics.DemoListViewFragment;
import com.and.andelectronics.DemoRecyclerViewFragment;
import com.and.andelectronics.R;
import com.and.andelectronics.common.WebServiceInformation;
import com.and.andelectronics.common.ez.EZAlertDialog;
import com.and.andelectronics.common.util.DateUtil;
import com.and.andelectronics.common.util.LocationUtil;
import com.and.andelectronics.common.util.image.DetailImageDownloader;
import com.and.andelectronics.infrastructure.model.lodgement.Lodgement;
import com.and.andelectronics.slidingTab.SlidingTabLayout;
import com.and.andelectronics.view.main.map.LodgmentLocationMapFragmentActivity;
import com.desmond.parallaxviewpager.ParallaxFragmentPagerAdapter;
import com.desmond.parallaxviewpager.ParallaxViewPagerBaseActivity;
import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

/**
 * Created by Won on 2016-01-02.
 * 업체 상세정보 액티비티 페럴렉스 뷰페이저 액티비티 사용
 */
public class DetailViewLodgmentActivity extends ParallaxViewPagerBaseActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{


    private ImageView headerImageView;
    private SlidingTabLayout mNavigBar;
    private Context context;

    private Button callButton;
    private Button locationButton;
    private Button reserveDateButton;

    private TextView storeNameTextView;
    private TextView addressTextView;
    private TextView distanceAndSatisfactionTextView;
    private TextView reserveDateTextView;

    private Lodgement lodgement;
    private static String storeCode;

    private DetailImageDownloader detailImageDownloader = new DetailImageDownloader();
    public static final String BASIC_DOWNLOAD_URL = WebServiceInformation.getImageDownloadUrl();

    private Container container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailview_lodgment);
        this.context = this;
        this.container = (com.and.andelectronics.Container)getApplicationContext();
        lodgement = (Lodgement)this.getIntent().getExtras().getSerializable("obj");
        initValues();

        this.headerImageView = (ImageView) findViewById(R.id.headerImageView);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mNavigBar = (SlidingTabLayout) findViewById(R.id.navig_tab);
        mHeader = findViewById(R.id.header);

        if (savedInstanceState != null)
        {
            this.headerImageView.setTranslationY(savedInstanceState.getFloat(IMAGE_TRANSLATION_Y));
            mHeader.setTranslationY(savedInstanceState.getFloat(HEADER_TRANSLATION_Y));
        }
        this.detailImageDownloader.download(BASIC_DOWNLOAD_URL + lodgement.getStoreCode() + "/" + lodgement.getImagePath() + ".jpg",
                lodgement.getStoreCode() + lodgement.getImagePath() + ".jpg",
                (ImageView) this.headerImageView);
        this.storeCode = lodgement.getStoreCode();

        setupAdapter();
        this.activityInit();

    }


    /*
     * 액티비티 컨트롤러 초기화
     */
    private void activityInit(){
        this.locationButton = (Button)findViewById(R.id.locationButton);
        this.callButton = (Button)findViewById(R.id.callButton);
        this.reserveDateButton = (Button)findViewById(R.id.reserveDateButton);

        this.storeNameTextView = (TextView)findViewById(R.id.storeNameTextView);
        this.addressTextView = (TextView)findViewById(R.id.addressTextView);
        this.distanceAndSatisfactionTextView = (TextView)findViewById(R.id.distanceAndSatisfactionTextView);
        this.reserveDateTextView = (TextView)findViewById(R.id.reserveDateTextView);

        this.locationButton.setOnClickListener(this);
        this.callButton.setOnClickListener(this);
        this.reserveDateButton.setOnClickListener(this);
    }

    private void dataInit(){
        this.storeNameTextView.setText(this.lodgement.getStoreName());
        this.addressTextView.setText(this.lodgement.getAddr1());
        this.distanceAndSatisfactionTextView.setText(this.lodgement.getDistance() + "/" + "100%");
        if(container.getReserveFromDate() == null || container.getReserveFromDate().equals(""))
        {
            container.setReserveFromDate(DateUtil.nowDateFormat("yyyy-MM-dd HH:mm:ss"));
            this.reserveDateTextView.setText(container.getReserveFromDate());

        }
        else
        {
            this.reserveDateTextView.setText(container.getReserveFromDate());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        dataInit();
    }

    @Override
    protected void initValues() {
        int tabHeight = getResources().getDimensionPixelSize(R.dimen.tab_height);
        mMinHeaderHeight = getResources().getDimensionPixelSize(R.dimen.min_header_height);
        mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        mMinHeaderTranslation = -mMinHeaderHeight + tabHeight;

        mNumFragments = 4;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putFloat(IMAGE_TRANSLATION_Y, this.headerImageView.getTranslationY());
        outState.putFloat(HEADER_TRANSLATION_Y, mHeader.getTranslationY());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void setupAdapter()
    {
        if (mAdapter == null)
        {
            mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mNumFragments);
        }
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(mNumFragments);
        mNavigBar.setOnPageChangeListener(getViewPagerChangeListener());
        mNavigBar.setViewPager(mViewPager);
    }

    @Override
    protected void scrollHeader(int scrollY) {
        float translationY = Math.max(-scrollY, mMinHeaderTranslation);
        mHeader.setTranslationY(translationY);
        this.headerImageView.setTranslationY(-translationY / 3);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.locationButton:
                new AsyncLocationCheck().execute();
                break;

            case R.id.callButton:
                //ActionEvent.callAction(this, lodgement.getHpNo());
                break;

            case R.id.reserveDateButton:
                showDialogDatePicker();
                break;
        }
    }

    private String getReserveDateTextView(){
        return this.reserveDateTextView.getText().toString();
    }

    private void showDialogDatePicker(){
        int year = DateUtil.getYear(getReserveDateTextView());
        int month = DateUtil.getMonth(getReserveDateTextView());
        int day = DateUtil.getDay(getReserveDateTextView());

        //월은 13으로 뜨니 -1해줘야 함
        month = month -1;

        DatePickerDialog dialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, this, year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String dateString = DateUtil.getDateString(year, monthOfYear, dayOfMonth);


        Date selectedDate = DateUtil.getDate(dateString);
        Date currentDate = DateUtil.getDate(DateUtil.nowDateFormat("yyyy-MM-dd"));

        int compare = selectedDate.compareTo(currentDate);

        if(compare < 0){
            Toast.makeText(context, "예약은 오늘날짜부터 가능합니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        this.reserveDateTextView.setText(dateString);
        container.setReserveFromDate(dateString);

        int hour = DateUtil.getHour(DateUtil.nowDateTime());
        int minute = DateUtil.getMinute(DateUtil.nowDateTime());

        TimePickerDialog dialog = new TimePickerDialog(this, this, hour, minute, false);
        dialog.show();

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String dateTime = container.getReserveFromDate();
        String hour = String.valueOf(hourOfDay);
        if(hourOfDay < 10){
            hour = "0" + hour;
        }
        String min = String.valueOf(minute);
        if(minute < 10){
            min = "0" + min;
        }
        dateTime = dateTime + " " +  hour + ":" + min + ":00";
        container.setReserveFromDate(dateTime);
        dataInit();
    }


    private static class ViewPagerAdapter extends ParallaxFragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm, int numFragments) {
            super(fm, numFragments);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch (position) {

                case 0:
                    //객실정보 프래그먼트
                    fragment = StateRoomListViewFragment.newInstance(0, storeCode);
                    break;

                case 1:
                    //요금정보 프래그먼트
                    fragment = ChargeInfoFragment.newInstance(1);
                    break;

                case 2:
                    fragment = DemoRecyclerViewFragment.newInstance(2);

                    break;
                case 3:
                    //공지사항 프래그먼트
                    fragment = NoticeInfoFragment.newInstance(3);
                    fragment = DemoListViewFragment.newInstance(2);
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
                    return "객실";

                case 1:
                    return "요금";

                case 2:
                    return "테마";

                case 3:
                    return "공지";

                default:
                    throw new IllegalArgumentException("wrong position for the fragment in vehicle page");
            }
        }
    }


    private class AsyncLocationCheck extends AsyncTask<Void, String, Void> {

        @Override
        protected void onPostExecute(Void result) {


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }


        @Override
        protected Void doInBackground(Void... params) {
            try{
                LocationUtil lgo = new LocationUtil(context);
                final LatLng latlng = lgo.searchLocation(lodgement.getAddr1());

                if(latlng!=null)
                {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Intent intent = new Intent(DetailViewLodgmentActivity.this, LodgmentLocationMapFragmentActivity.class);
                            intent.putExtra("latlng", latlng);
                            startActivity(intent);
                        }
                    });

                }else{
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(context, "지도위치를 파악하지 못했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }catch(Exception e){
                Log.w("settingFrag Excep", "not null===" + e);
            }
            return null;
        }

    }


    private static final int REQ_PERMISSION_CALL_PHONE = 1000;

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQ_PERMISSION_CALL_PHONE: {
                if (grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(getApplicationContext(), "권한이 부여되었습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        EZAlertDialog.show1(getSupportFragmentManager(), "권한요청실패");
                    }
                } else {
                    EZAlertDialog.show1(getSupportFragmentManager(), "권한이 없습니다.");
                }
            }
        }
    }
}
