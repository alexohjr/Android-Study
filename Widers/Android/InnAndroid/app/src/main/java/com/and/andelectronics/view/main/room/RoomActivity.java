package com.and.andelectronics.view.main.room;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.and.andelectronics.Container;
import com.and.andelectronics.R;
import com.and.andelectronics.common.ActivityManager;
import com.and.andelectronics.common.ez.EZAlertDialog;
import com.and.andelectronics.common.util.DateUtil;
import com.and.andelectronics.common.util.LocationUtil;
import com.and.andelectronics.common.util.PreferenceUtil;
import com.and.andelectronics.common.util.PriceFormatUtil;
import com.and.andelectronics.infrastructure.interfaces.ILodgementService;
import com.and.andelectronics.infrastructure.model.lodgement.ReserveTime;
import com.and.andelectronics.infrastructure.model.lodgement.Room;
import com.and.andelectronics.infrastructure.model.lodgement.RoomRequestData;
import com.and.andelectronics.view.main.detail.DetailViewLodgmentActivity;
import com.and.andelectronics.view.main.enterance.EnterKeypadDialog;
import com.and.andelectronics.view.main.enterance.EnterType;
import com.and.andelectronics.view.main.enterance.EnterTypeSelectDialog;
import com.and.andelectronics.view.main.map.LodgmentLocationMapFragmentActivity;
import com.and.andelectronics.view.main.reserve.DayuseReserveActivity;
import com.and.andelectronics.view.main.reserve.OvernightReserveActivity;
import com.and.andelectronics.webservice.lodgement.LodgementServiceXml;
import com.ez.EZToast;
import com.google.android.gms.maps.model.LatLng;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Won on 2017-06-22.
 */
public class RoomActivity extends AppCompatActivity implements View.OnClickListener{

    private Context context;
    private Room room;
    private LocationManager locationManager;

    private ViewPager roomImageViewPager;
    private RoomImageViewPagerAdapter roomImageViewPagerAdapter;
    private TextView dayusePriceTextView;
    private TextView dayuseEndTimeTextView;
    private TextView dayuseTimeTextView;
    private TextView overnightPriceTextView;
    private TextView checkinTimeTextView;
    private TextView checkoutTimeTextView;

    private TextView dayuseReserveTextView;
    private TextView overnightReserveTextView;

    private Button roomDayuseReserveButton;
    private Button roomOvernightReserveButton;
    private LinearLayout dayuseReserveLinearLayout;
    private LinearLayout overnightReserveLinearLayout;
    private TextView roomMapButton; //현재 숙박업소의 위치보기

    //private Button roomGroupShareButton;

    private ActivityManager activityManager = ActivityManager.getInstance();

    private android.app.ProgressDialog ProgressDialog;
    private com.and.andelectronics.Container Container;
    private ILodgementService iLodgementService;
    private ArrayList<Room> roomList;
    private ArrayList<ReserveTime> reserveTimeList;
    private boolean isOvernight = false;
    private boolean isSweet;    //좋아요. 좋아요 데이터 조회하는것 만들어야 할듯


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roomgroup);

        this.context = this;
        this.activityManager.addActivity(this);
        this.Container = (Container)getApplicationContext();
        room = (Room)this.getIntent().getExtras().getSerializable("obj");
        iLodgementService = new LodgementServiceXml(context);
        this.Container.setReserveFromDate(DateUtil.nowDateFormat("yyyy-MM-dd HH:mm:ss"));
        this.bindView();
        this.bindEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityManager.removeActivity(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            showInitDialog();
            return true;
        }
        return true;

    }

    private void showInitDialog(){

        EZAlertDialog.show1(getSupportFragmentManager(),
                "뒤로 돌아가시면 설정한 모든정보가 초기화 됩니다."
                ,
                 new EZAlertDialog.EZAlertDialogListener() {
            @Override
            public void onClickConfirm(int selected) {
                Container.initCurrentItem();
                finish();
            }

            @Override
            public void onClickConfirm() {
                Container.initCurrentItem();
                finish();
            }

            @Override
            public void onClickCancel() {
            }
        }, "취소", "확인");
    }

    private void bindView(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        toolbarSetting();

        this.roomImageViewPagerAdapter =  new RoomImageViewPagerAdapter(this, 3, null, room);
        this.roomImageViewPager = (ViewPager)findViewById(R.id.roomImageViewPager);
        this.roomImageViewPager.setAdapter(roomImageViewPagerAdapter);

        this.dayusePriceTextView = (TextView)findViewById(R.id.dayusePriceTextView);
        this.dayuseEndTimeTextView = (TextView)findViewById(R.id.dayuseEndTimeTextView);
        this.dayuseTimeTextView = (TextView)findViewById(R.id.dayuseTimeTextView);
        this.overnightPriceTextView = (TextView)findViewById(R.id.overnightPriceTextView);
        this.checkinTimeTextView = (TextView)findViewById(R.id.checkinTimeTextView);
        this.checkoutTimeTextView = (TextView)findViewById(R.id.checkoutTimeTextView);
        this.dayuseReserveTextView = (TextView)findViewById(R.id.dayuseReserveTextView);
        this.overnightReserveTextView = (TextView)findViewById(R.id.overnightReserveTextView);

        this.dayuseReserveLinearLayout = (LinearLayout)findViewById(R.id.dayuseReserveLinearLayout);
        this.roomDayuseReserveButton = (Button)findViewById(R.id.roomDayuseReserveButton);
        this.overnightReserveLinearLayout = (LinearLayout)findViewById(R.id.overnightReserveLinearLayout);
        this.roomOvernightReserveButton = (Button)findViewById(R.id.roomOvernightReserveButton);


        this.dayusePriceTextView.setText(PriceFormatUtil.getPriceFormat(String.valueOf(room.getDayusePrice())) + "원");
        //this.dayuseEndTimeTextView.setText("18");
        this.dayuseTimeTextView.setText(room.getRentTime() + "시간");

        this.overnightPriceTextView.setText(PriceFormatUtil.getPriceFormat(String.valueOf(room.getOvernightPrice())) + "원");
        this.checkinTimeTextView.setText((room.getCheckIn()) + "~");

        //숙박/대실가능한 객실이 있을 때 예약버튼 활성화 및 ClickListener 단다.
        if(room.getEmptyRoomCount() > 0)
        {
            this.overnightReserveLinearLayout.setOnClickListener(this);
            this.roomDayuseReserveButton.setOnClickListener(this);
            //this.overnightReserveTextView.setVisibility(View.VISIBLE);
        }
        else
        {
            //this.overnightReserveTextView.setVisibility(View.INVISIBLE);
        }

        if(room.getEmptyRoomCount() > 0)
        {
            this.dayuseReserveLinearLayout.setOnClickListener(this);
            this.roomOvernightReserveButton.setOnClickListener(this);
            //this.dayuseReserveTextView.setVisibility(View.VISIBLE);
        }
        else
        {
            //this.dayuseReserveTextView.setVisibility(View.GONE);
        }

        //String address = PreferenceUtil.instance(context).getMyAddress();
        TextView roomAddressTextView = (TextView)findViewById(R.id.roomAddressTextView);
        roomAddressTextView.setText(Container.getCurrentLodgement().getAddr1());

        roomMapButton = (TextView)findViewById(R.id.roomMapButton);
        roomMapButton.setOnClickListener(this);
    }
    //region 툴바 셋팅
    /**
     * 좋아요버튼
     */
    private Button toolbarRightButton1;
    private void toolbarSetting(){
        TextView roomGroupToolbar_title = (TextView)findViewById(R.id.toolbarTitle);
        roomGroupToolbar_title.setText(Container.getCurrentLodgement().getStoreName());
        Button leftButton1 = (Button)findViewById(R.id.toolbarLeftButton1);
        Button leftButton2 = (Button)findViewById(R.id.toolbarLeftButton2);
        toolbarRightButton1 = (Button)findViewById(R.id.toolbarRightButton1);
        Button rightButton2 = (Button)findViewById(R.id.toolbarRightButton2);
        ImageView titleImage = (ImageView)findViewById(R.id.toolbarTitleImage);
        leftButton1.setOnClickListener(this);
        leftButton2.setOnClickListener(this);
        toolbarRightButton1.setOnClickListener(this);
        rightButton2.setOnClickListener(this);

        leftButton1.setVisibility(View.VISIBLE);
        leftButton2.setVisibility(View.INVISIBLE);
        toolbarRightButton1.setVisibility(View.VISIBLE);
        rightButton2.setVisibility(View.VISIBLE);
        titleImage.setVisibility(View.GONE);

        toolbarRightButton1.setBackgroundResource(R.mipmap.btn_sub4_roomsave_off);
        rightButton2.setBackgroundResource(R.mipmap.btn_sub4_share);
    }
    //endregion

    private void bindEvent(){

    }

    /**
     * 예약화면 이동
     * @param activity
     */
    private void reserveActivity(Class activity){
        Room mData = room;

        Bundle extras = new Bundle();
        Intent intent = new Intent(RoomActivity.this, activity);
        extras.putSerializable("obj", mData);
        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //하단 대실예약버튼 클릭시 포함
            case R.id.roomDayuseReserveButton:
            case R.id.dayuseReserveLinearLayout:
                if(room.getEmptyRoomCount() > 0)
                {
                    isOvernight = false;
                    //new RoomListByChoiceAsync().execute();
                    new ReserveTimeAsync().execute();   //대실일 경우 예약 시간을 선택하도록 한다.
                }

                //reserveActivity(DayuseReserveActivity.class);
                break;
            //하단 숙박예약 클릭시 포함
            case R.id.roomOvernightReserveButton:
            case R.id.overnightReserveLinearLayout:
                if(room.getEmptyRoomCount() > 0)
                {
                    isOvernight = true;
                    new RoomListByChoiceAsync().execute();
                }
                //reserveActivity(OvernightReserveActivity.class);
                break;

            case R.id.toolbarRightButton1:
                if(isSweet){
                    toolbarRightButton1.setBackgroundResource(R.mipmap.btn_sub4_roomsave_off);
                    isSweet = false;
                }else{
                    toolbarRightButton1.setBackgroundResource(R.mipmap.btn_sub4_roomsave_on);
                    isSweet = true;
                    EZToast.show(context, "좋아요!");
                }
                break;

            case R.id.toolbarRightButton2:
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");
                String text = "원하는 텍스트를 입력하세요";
                intent.putExtra(Intent.EXTRA_TEXT, text);
                Intent chooser = Intent.createChooser(intent, "친구에게 공유하기");
                startActivity(chooser);

                /*SharingDialog.show1(getSupportFragmentManager(), new SharingDialog.EZAlertDialogListener() {

                    @Override
                    public void onClickConfirm() {

                    }
                    @Override
                    public void onClickCancel() {

                    }

                });*/
                break;
            case R.id.toolbarLeftButton1:
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK);
                onKeyDown(KeyEvent.KEYCODE_BACK, event);

                break;
            case R.id.roomMapButton:
                new AsyncLocationCheck().execute();
                break;

        }
    }


    private class RoomListByChoiceAsync extends AsyncTask<Void, String, Void> {

        @Override
        protected void onPostExecute(Void result)
        {
            RoomActivity.this.ProgressDialog.dismiss();

            if(roomList == null || roomList.size() < 1){
                EZToast.show(context, "예약가능한 시간이 없습니다.");
                return;
            }
            RoomNumberDialog.show1(getSupportFragmentManager(), roomList, new RoomNumberDialog.EZAlertDialogListener() {

                @Override
                public void onClickConfirm() {

                }
                @Override
                public void onClickCancel() {

                }

                @Override
                public void onClickNumber(Room roomNumber) {

                    room.setRoomCode(roomNumber.getRoomCode()); //선택한 룸

                    if(isOvernight == true){
                        room.setStatus("ST01");
                        selectEnterType();
                        //reserveActivity(OvernightReserveActivity.class);
                    }else{
                        room.setStatus("ST02");
                        selectEnterType();
                        //new ReserveTimeAsync().execute();   //대실일 경우 예약 시간을 선택하도록 한다.
                        //room.setStatus("ST02");
                        //reserveActivity(DayuseReserveActivity.class);
                    }
                }
            });

        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            RoomActivity.this.ProgressDialog = android.app.ProgressDialog.show(
                    context,
                    getResources().getString(R.string.comm_stateRoom), getResources().getString(R.string.async_store_msg));

        }


        @Override
        protected Void doInBackground(Void... params) {


            RoomRequestData roomRequestData = new RoomRequestData();
            roomRequestData.setAction("R"); //객실번호 가져오기
            roomRequestData.setCompanyCode(Container.getCurrentLodgement().getStoreCode());
            roomRequestData.setGradeFrom(room.getGrade());  //선택한 GRADE
            roomRequestData.setReserveFromDate(Container.getReserveFromDate());
            //roomRequestData.setReserveDate(DateUtil.nowDate()); //예약할 시간 대를(yyyy-mm-dd hh:mm:ss) 넣으면 SP에서 파싱해서 객실선택할 때 시간 이후의 객실만 나오도록 한다.
            //roomList = iLodgementService.getRoomByChoice(roomRequestData);
            roomList = iLodgementService.getRoomEmptyList(roomRequestData);
            return null;
        }
    }



    private class ReserveTimeAsync extends AsyncTask<Void, String, Void> {

        @Override
        protected void onPostExecute(Void result)
        {

            if(reserveTimeList == null || reserveTimeList.size() == 0) {
                EZToast.show(context, "예약가능한 시간이 없습니다.");
                return;
            }
            ArrayList<ReserveTime> timeArrayList = new ArrayList<>();
            //예약시간이 13(11~24시까지)보다 크면 빈방과 예약이 같이 존재 구분자 'E'인 목록을 제거한다.
            if(reserveTimeList.size() > 13){
                for (ReserveTime time : reserveTimeList )
                {
                    if(!"E".equals(time.getReserveYN())){
                        timeArrayList.add(time);
                    }
                }
            }else{
                timeArrayList = reserveTimeList;
            }
            ReserveTimeDialog.show1(getSupportFragmentManager(), timeArrayList, room.getRentTime(), new ReserveTimeDialog.EZAlertDialogListener() {

                @Override
                public void onClickConfirm() {

                }
                @Override
                public void onClickCancel() {

                }

                @Override
                public void onSelectedTime(ReserveTime fromTime, ReserveTime toTime) {
                    String selectedFromText = fromTime.getHourOfDate();
                    selectedFromText = selectedFromText + ":00:00";

                    String selectedToText = toTime.getHourOfDate();
                    selectedToText = selectedToText + ":00:00";

                    Container.setReserveFromDate(selectedFromText);    //여기서 선택한 예약시간  From to를 컨테이너에 셋팅한다.
                    Container.setReserveToDate(selectedToText);
                    room.setStatus("ST02");
                    new RoomListByChoiceAsync().execute();
                    //selectEnterType();
                }

                @Override
                public void onSelectedTimeList(ArrayList<ReserveTime> selectedTimeList) {
                    Container.setReserveTimeList(selectedTimeList);
                }
            });

        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

        }


        @Override
        protected Void doInBackground(Void... params) {


            RoomRequestData roomRequestData = new RoomRequestData();
            roomRequestData.setCompanyCode(Container.getCurrentLodgement().getStoreCode());
            roomRequestData.setRoomCode("");
            roomRequestData.setReserveDate(Container.getReserveFromDate().substring(0, 10));
            //시간먼저 조회하는것 때문에 Grade 추가
            roomRequestData.setGrade(room.getGrade());

            reserveTimeList = iLodgementService.getReserveTimeByRoom(roomRequestData);
            return null;
        }
    }


    private void selectEnterType(){

        EnterTypeSelectDialog.show1(getSupportFragmentManager(), null, new EnterTypeSelectDialog.EZAlertDialogListener() {

            @Override
            public void onClickConfirm() {

            }
            @Override
            public void onClickCancel() {

            }

            @Override
            public void onClickEnterType(EnterType type) {
                Container.setCurrentEnterType(type);
                if(type == EnterType.PASSWORD){
                    setInputPassword();
                }else{
                    if(room.getStatus().equals("ST01")){    //숙박이면
                        reserveActivity(OvernightReserveActivity.class);
                    }else{  // 대실이면
                        reserveActivity(DayuseReserveActivity.class);
                    }
                }

            }
        });
    }

    private void setInputPassword(){
        EnterKeypadDialog.show1(getSupportFragmentManager(), new EnterKeypadDialog.EZAlertDialogListener() {

            @Override
            public void onClickConfirm() {

            }
            @Override
            public void onClickCancel() {

            }
            @Override
            public void onSetPassword(String password) {
                Container.setCurrentPassword(password);
                if(room.getStatus().equals("ST01")){    //숙박이면
                    reserveActivity(OvernightReserveActivity.class);
                }else{  // 대실이면
                    reserveActivity(DayuseReserveActivity.class);
                }
            }
        });
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
                final LatLng latlng = lgo.searchLocation(Container.getCurrentLodgement().getAddr1());

                if(latlng!=null)
                {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Intent intent = new Intent(RoomActivity.this, LodgmentLocationMapFragmentActivity.class);
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


}