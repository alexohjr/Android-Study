package com.and.andelectronics.view.main.reserve;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.and.andelectronics.Constants;
import com.and.andelectronics.Container;
import com.and.andelectronics.R;
import com.and.andelectronics.common.ActivityManager;
import com.and.andelectronics.common.util.AppUtil;
import com.and.andelectronics.common.util.DateUtil;
import com.and.andelectronics.common.util.PriceFormatUtil;
import com.and.andelectronics.infrastructure.interfaces.ILodgementService;
import com.and.andelectronics.infrastructure.model.lodgement.ReservationRequestData;
import com.and.andelectronics.infrastructure.model.lodgement.ReserveRequestData;
import com.and.andelectronics.infrastructure.model.lodgement.ReserveRoom;
import com.and.andelectronics.infrastructure.model.lodgement.ReserveTime;
import com.and.andelectronics.infrastructure.model.lodgement.Room;
import com.and.andelectronics.infrastructure.model.lodgement.RoomRequestData;
import com.and.andelectronics.view.main.enterance.EnterKeypadDialog;
import com.and.andelectronics.view.main.enterance.EnterType;
import com.and.andelectronics.view.main.enterance.EnteranceConfigActivity;
import com.and.andelectronics.view.main.payment.PayTypeActivity;
import com.and.andelectronics.view.main.payment.PaymentActivity;
import com.and.andelectronics.view.main.room.ReserveTimeGridAdapter;
import com.and.andelectronics.view.main.room.RoomNumberDialog;
import com.and.andelectronics.webservice.lodgement.LodgementServiceXml;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.and.andelectronics.Constants.RSVSTRT;


/**
 * Created by Won on 2017-06-26.
 */
public class DayuseReserveActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener{

    private Context context;
    private Container container;
    private Room room;
    private ILodgementService iLodgementService;
    private android.app.ProgressDialog ProgressDialog;
    private TextView companyNameTextView;
    private TextView gradeNameTextView;
    private TextView roomNumberTextView;
    private TextView rentTimeTextView;

    private TextView reservePasswordChangeButton;
    private TextView reservePasswordTextView;
    private TextView reserveMessageTextView;        //password??????????????? ???????????? ????????? ?????? 20190120 yowonsm

    private TextView reserveCheckNumButton;   //???????????? ??????
    private EditText reserveCheckNumberEditText;    //???????????? ??????

    private TextView totalAmountTextView;

    private Button paymentButton;
    private EditText reserveNameEditText;
    private EditText reserveHpNoEditText;
    private RadioGroup reserveRadioGroup;
    private RadioButton reservePwdRadioButton;
    private RadioButton reserveCardRadioButton;
    private RadioButton reserveFrontRadioButton;

    private RadioGroup reserveVisitRadioGroup;
    private RadioButton reserveVisitWalkRadioButton;
    private RadioButton reserveVisitCarRadioButton;

    private LinearLayout payTypeLinearLayout;
    private LinearLayout passwordLayerLinearLayout;
    private RecyclerView reserveTimeRecyclerView;
    private LinearLayoutManager layoutManager;


    private DayuseReserveTimeRecyclerAdapter dayuseReserveTimeRecyclerAdapter;

    private String udid;

    private ActivityManager activityManager = ActivityManager.getInstance();

    protected int selectedTabButtonTextColor;
    protected int deSelectedTabButtonTextColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_dayuse);

        this.context = this;
        this.container = (Container)getApplicationContext();
        this.activityManager.addActivity(this);
        this.room = (Room)this.getIntent().getExtras().getSerializable("obj");
        this.iLodgementService = new LodgementServiceXml(context);
        this.selectedTabButtonTextColor = R.color.white;  //setTextColor?????? resource????????? Color?????? ???????????? ???
        this.deSelectedTabButtonTextColor = R.color.darkgrey;
        this.viewInit();
        this.dataInit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.activityManager.removeActivity(this);
    }

    /**
     * viewBinding ??? ????????? ??????
     */
    private void viewInit(){
        toolbarSetting();
        this.companyNameTextView = (TextView)findViewById(R.id.companyNameTextView);
        this.gradeNameTextView = (TextView)findViewById(R.id.gradeNameTextView);
        this.roomNumberTextView = (TextView)findViewById(R.id.roomNumberTextView);
        this.rentTimeTextView = (TextView)findViewById(R.id.rentTimeTextView);
        this.reservePasswordChangeButton = (TextView)findViewById(R.id.reservePasswordChangeButton);
        this.reservePasswordChangeButton.setOnClickListener(this);
        this.reservePasswordTextView = (TextView)findViewById(R.id.reservePasswordTextView);
        this.reserveMessageTextView = (TextView)findViewById(R.id.reserveMessageTextView);

        this.reserveTimeRecyclerView = (RecyclerView) findViewById(R.id.reserveTimeRecyclerView);
        this.layoutManager = new LinearLayoutManager(this.context);
        this.layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        //this.reserveTimeRecyclerView.setHasFixedSize(true);
        this.reserveTimeRecyclerView.setLayoutManager(layoutManager);

        this.totalAmountTextView = (TextView)findViewById(R.id.totalAmountTextView);

        //this.payTypeTextView = (TextView)findViewById(R.id.payTypeTextView);
        //this.payTypeMsgTextView = (TextView)findViewById(R.id.payTypeMsgTextView);

        this.paymentButton = (Button)findViewById(R.id.paymentButton);
        this.paymentButton.setOnClickListener(this);

        this.reserveNameEditText = (EditText)findViewById(R.id.reserveNameEditText);
        this.reserveHpNoEditText = (EditText)findViewById(R.id.reserveHpNoEditText);

        this.reserveRadioGroup = (RadioGroup) findViewById(R.id.reserveRadioGroup);
        this.reserveRadioGroup.setOnCheckedChangeListener(this);
        this.reservePwdRadioButton = (RadioButton)findViewById(R.id.reservePwdRadioButton);
        this.reserveCardRadioButton = (RadioButton)findViewById(R.id.reserveCardRadioButton);
        this.reserveFrontRadioButton = (RadioButton)findViewById(R.id.reserveFrontRadioButton);

        this.reserveVisitRadioGroup = (RadioGroup) findViewById(R.id.reserveVisitRadioGroup);
        this.reserveVisitRadioGroup.setOnCheckedChangeListener(this);
        this.reserveVisitWalkRadioButton = (RadioButton)findViewById(R.id.reserveVisitWalkRadioButton);
        this.reserveVisitCarRadioButton = (RadioButton)findViewById(R.id.reserveVisitCarRadioButton);

        this.passwordLayerLinearLayout = (LinearLayout)findViewById(R.id.passwordLayerLinearLayout);
        //this.payTypeLinearLayout = (LinearLayout)findViewById(R.id.payTypeLinearLayout);
        //this.payTypeLinearLayout.setOnClickListener(this);
    }

    private void toolbarSetting(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        //toolbar.setBackgroundResource(R.color.actionbar_red);
        TextView roomGroupToolbar_title = (TextView)findViewById(R.id.toolbarTitle);
        roomGroupToolbar_title.setText("?????? ??????");
        Button leftButton1 = (Button)findViewById(R.id.toolbarLeftButton1);
        Button leftButton2 = (Button)findViewById(R.id.toolbarLeftButton2);
        Button toolbarRightButton1 = (Button)findViewById(R.id.toolbarRightButton1);
        Button rightButton2 = (Button)findViewById(R.id.toolbarRightButton2);
        ImageView titleImage = (ImageView)findViewById(R.id.toolbarTitleImage);
        leftButton1.setOnClickListener(this);
        leftButton2.setOnClickListener(this);
        toolbarRightButton1.setOnClickListener(this);
        rightButton2.setOnClickListener(this);

        leftButton1.setVisibility(View.VISIBLE);
        leftButton2.setVisibility(View.INVISIBLE);
        toolbarRightButton1.setVisibility(View.INVISIBLE);
        rightButton2.setVisibility(View.INVISIBLE);
        titleImage.setVisibility(View.GONE);

        toolbarRightButton1.setBackgroundResource(R.mipmap.btn_sub4_roomsave_off);
        rightButton2.setBackgroundResource(R.mipmap.btn_sub4_share);
    }

    private void dataInit(){
        this.companyNameTextView.setText(container.getCurrentLodgement().getStoreName());
        this.gradeNameTextView.setText(room.getGradeName());
        this.roomNumberTextView.setText(room.getRoomCode() + "???");
        this.rentTimeTextView.setText(String.valueOf(room.getRentTime()) + "??????");

        this.reservePasswordTextView.setText(container.getCurrentPassword());
        //this.checkoutTextView.setText();
        this.totalAmountTextView.setText(PriceFormatUtil.getPriceFormat(String.valueOf(room.getOvernightPrice())));
        //this.payTypeTextView.setText("?????? >");

        this.udid = AppUtil.getIMEI(context);

        if(udid == null || udid.equals("")){
            Toast.makeText(context, "????????? ????????? ???????????? ???????????????. ?????? ??????????????? ???????????????.", Toast.LENGTH_SHORT).show();
            return;
        }

        EnterType entType = container.getCurrentEnterType();
        if(entType == EnterType.PASSWORD){
            reserveRadioGroup.check(R.id.reservePwdRadioButton);
        }else if(entType == EnterType.MEMBER_CARD){
            reserveRadioGroup.check(R.id.reserveCardRadioButton);
        }else{
            reserveRadioGroup.check(R.id.reserveFrontRadioButton);
        }
        reserveVisitRadioGroup.check(R.id.reserveVisitWalkRadioButton);
        initReserveTime();  //?????????????????? ??????
    }

    private void initReserveTime(){
        ArrayList<ReserveTime> reserveTimes = container.getReserveTimeList();

        dayuseReserveTimeRecyclerAdapter = new DayuseReserveTimeRecyclerAdapter(this,reserveTimes);
        reserveTimeRecyclerView.setAdapter(dayuseReserveTimeRecyclerAdapter);  // ????????? ???????????? GridView ??? ??????
    }


    public String getReserveNameEditText() {
        return reserveNameEditText.getText().toString();
    }

    public String getReserveHpNoEditText() {
        return reserveHpNoEditText.getText().toString();
    }

    public String getReservePasswordTextView(){
        return reservePasswordTextView.getText().toString();
    }

    private String getEnterTypeMethod(EnterType type){
        if(type == EnterType.PASSWORD){
            return "0";
        }else if(type == EnterType.MEMBER_CARD){
            return "1";
        }else if(type == EnterType.FRONT_KEY){
            return "2";
        }else{
            return "9";
        }
    }

    private void movePaymentActivity(Room room, ReserveRequestData reserve){

        Bundle extras = new Bundle();
        Intent intent = new Intent(context, PaymentActivity.class);
        extras.putSerializable("obj", room);
        extras.putSerializable("reserve", reserve);
        intent.putExtras(extras);
        startActivityForResult(intent, 99);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.payTypeLinearLayout:
                Intent intent = new Intent(context, PayTypeActivity.class);
                startActivityForResult(intent, 0);
                break;

            case R.id.reservePasswordChangeButton:
                EnterKeypadDialog.show1(getSupportFragmentManager(), new EnterKeypadDialog.EZAlertDialogListener() {

                    @Override
                    public void onClickConfirm() {

                    }
                    @Override
                    public void onClickCancel() {

                    }
                    @Override
                    public void onSetPassword(String password) {
                        reservePasswordTextView.setText(password);
                    }
                });
                break;

            case R.id.paymentButton:

                if(getReserveHpNoEditText().isEmpty() || getReserveNameEditText().isEmpty()){
                    Toast.makeText(context, "?????????????????? ????????? ????????????.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String udid = AppUtil.getIMEI(context);

                if(udid == null || udid.equals("")){
                    Toast.makeText(context, "????????? ????????? ???????????? ???????????????. ?????? ??????????????? ???????????????.", Toast.LENGTH_SHORT).show();
                    return;
                }


                new ReservationRoomAsync().execute();   //??????????????? ?????? ????????? ???????????? ??????

                break;

            case R.id.toolbarLeftButton1:
                onBackPressed();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){

            case RESULT_OK:
                if(requestCode == 99)//??????????????? ????????????;;
                {
                    finish();
                }
                else
                {
                    String payType = data.getStringExtra("payType");
                    //this.payTypeTextView.setText(payType);
                    break;
                }
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.reservePwdRadioButton:
                //passwordLayerLinearLayout.setVisibility(View.VISIBLE);
                reservePasswordTextView.setVisibility(View.VISIBLE);
                reservePasswordChangeButton.setVisibility(View.VISIBLE);
                reserveMessageTextView.setVisibility(View.GONE);
                break;
            case R.id.reserveCardRadioButton:
                //passwordLayerLinearLayout.setVisibility(View.GONE);
                reservePasswordTextView.setVisibility(View.GONE);
                reservePasswordChangeButton.setVisibility(View.GONE);
                reserveMessageTextView.setVisibility(View.VISIBLE);
                reserveMessageTextView.setText("????????? ????????? ????????? ???????????????.");
                break;
            case R.id.reserveFrontRadioButton:
                //passwordLayerLinearLayout.setVisibility(View.GONE);
                reservePasswordTextView.setVisibility(View.GONE);
                reservePasswordChangeButton.setVisibility(View.GONE);
                reserveMessageTextView.setVisibility(View.VISIBLE);
                reserveMessageTextView.setText("?????? ??? ??????????????? ???????????? ??? ??????????????????.");
                break;

            //???????????? ??????, ??????
            case R.id.reserveVisitWalkRadioButton:
                reserveVisitCarRadioButton.setTextColor((ContextCompat.getColorStateList(context, this.deSelectedTabButtonTextColor)));
                reserveVisitWalkRadioButton.setTextColor((ContextCompat.getColorStateList(context, this.selectedTabButtonTextColor)));
                break;
            case R.id.reserveVisitCarRadioButton:
                reserveVisitCarRadioButton.setTextColor((ContextCompat.getColorStateList(context, this.selectedTabButtonTextColor)));
                reserveVisitWalkRadioButton.setTextColor((ContextCompat.getColorStateList(context, this.deSelectedTabButtonTextColor)));
                break;
        }
    }


    /**
     * ???????????? Async 20190115 yowonsm
     */
    private class ReservationRoomAsync extends AsyncTask<Void, String, Void> {

        private ReserveRoom resultData;
        private String message;
        private int resultCode;

        @Override
        protected void onPostExecute(Void result) {
            DayuseReserveActivity.this.ProgressDialog.dismiss();

            if(resultCode == 0){
                Toast.makeText(context, "?????????????????????. ??????????????? ????????? -> ???????????? ?????? ????????? ??? ????????????.", Toast.LENGTH_SHORT).show();
                container.setReserveFromDate(null); //???????????? ?????????
                container.initCurrentItem();
                activityManager.finishAllActivity();

            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            DayuseReserveActivity.this.ProgressDialog = android.app.ProgressDialog.show(
                    context,
                    getResources().getString(R.string.async_reserve_title),getResources().getString(R.string.async_reserve_msg));

        }



        @Override
        protected Void doInBackground(Void... params) {

            String reserveTermSt = container.getReserveFromDate().substring(0, 16);
            ReservationRequestData reqData = new ReservationRequestData();
            reqData.setAction(Constants.Action.I.toString());
            reqData.setCompanyCode(container.getCurrentLodgement().getStoreCode());
            reqData.setReserveDate(container.getReserveFromDate().substring(0, 10));
            reqData.setReserveTime(container.getReserveFromDate().substring(11, 16));
            reqData.setReserveSeq("");  //???????????? ???????????? ??????
            reqData.setReserveGrade(room.getGrade());
            reqData.setReserveRoom(room.getRoomCode());
            reqData.setReserveStatus(RSVSTRT);  //?????? RS
            reqData.setReserveTermSt(reserveTermSt);
            reqData.setReserveTermEt(DateUtil.addDateTime(DateUtil.getDateTime(reserveTermSt), room.getRentTime()));  //???????????? + 1?????? ???????????? ???
            reqData.setReserveExpire(container.getReserveFromDate().substring(0, 10));
            reqData.setReserveUseDay(1);
            reqData.setReservePerson(room.getPerson());
            reqData.setReserveFeeAmt(room.getDayusePrice());
            reqData.setReserveFeeAdd(0);
            reqData.setReserveFeeRefund(0);
            reqData.setReserveFeeRecv(room.getDayusePrice());

            reqData.setReserveConfirm("0");
            reqData.setReserveSales("");    //????????? ??????
            reqData.setReserveMember("");
            reqData.setReserveOwner("");
            reqData.setReservePhone(getReserveHpNoEditText());
            reqData.setReserveSendFlag("0");

            reqData.setReservePwd(getReservePasswordTextView());
            reqData.setReserveMethod(getEnterTypeMethod(container.getCurrentEnterType()));
            reqData.setReserveNfcSerial("");
            reqData.setReserveUdid(udid);   //????????? ????????? ????????? ??? ?????? ?????? ????????? Udid??? ?????????.
            reqData.setReserveUser(getReserveNameEditText());
            reqData.setReserveMemo("");

            resultData = iLodgementService.reservationRoom(reqData);
            resultCode = iLodgementService.getResultCode();
            message = iLodgementService.getMessage();
            return null;
        }
    }


    private ReserveRequestData reserveRequestData;
    private class ReserveRoomAsync extends AsyncTask<Void, String, Void> {

        private Room resultData;
        @Override
        protected void onPostExecute(Void result) {
            DayuseReserveActivity.this.ProgressDialog.dismiss();

            if(resultData != null)
            {
                reserveRequestData = new ReserveRequestData();
                reserveRequestData.setCompanyCode(resultData.getCompanyCode());
                reserveRequestData.setReserveRoom(resultData.getRoomCode());
                reserveRequestData.setReserveUser(getReserveNameEditText());
                reserveRequestData.setReservePhone(getReserveHpNoEditText());
                reserveRequestData.setReserveFeeAmt(room.getOvernightPrice());
                reserveRequestData.setReserveFeeRecv(room.getOvernightPrice());
                reserveRequestData.setReserveStatus("ST02");
                //movePaymentActivity(resultData, reserveRequestData);
                new ReserveRoomEnteranceAsync().execute();
            }else{
                Toast.makeText(context, "?????? ?????????????????????.", Toast.LENGTH_SHORT).show();
            }


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            DayuseReserveActivity.this.ProgressDialog = android.app.ProgressDialog.show(
                    context,
                    getResources().getString(R.string.async_store_title),getResources().getString(R.string.async_store_msg));

        }



        @Override
        protected Void doInBackground(Void... params) {

            RoomRequestData reqData = new RoomRequestData();
            reqData.setCompanyCode(room.getCompanyCode());
            reqData.setGrade(room.getGrade());
            reqData.setRoomCode(room.getRoomCode());
            reqData.setStatus(room.getStatus());
            reqData.setReserveFromDate(container.getReserveFromDate());
            reqData.setReserveToDate("");
            resultData = iLodgementService.reserveRoomByRoomCode(reqData);

            return null;
        }
    }



    private class ReserveRoomEnteranceAsync extends AsyncTask<Void, String, Void> {

        private ReserveRoom resultData;
        private String message;
        private int resultCode;

        @Override
        protected void onPostExecute(Void result) {
            DayuseReserveActivity.this.ProgressDialog.dismiss();

            if(resultCode == 0){
                Toast.makeText(context, "?????????????????????. ??????????????? ????????? -> ???????????? ?????? ????????? ??? ????????????.", Toast.LENGTH_SHORT).show();
                container.setReserveFromDate(null); //???????????? ?????????
                container.initCurrentItem();
                activityManager.finishAllActivity();

            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            DayuseReserveActivity.this.ProgressDialog = android.app.ProgressDialog.show(
                    context,
                    getResources().getString(R.string.async_reserve_title),getResources().getString(R.string.async_reserve_msg));

        }



        @Override
        protected Void doInBackground(Void... params) {

            ReserveRequestData reqData = new ReserveRequestData();
            reqData.setCompanyCode(room.getCompanyCode());
            reqData.setReserveDate(container.getReserveFromDate());
            reqData.setReserveTermSt(container.getReserveFromDate());
            reqData.setReserveTermEt("");
            //reqData.setReserveTermEt(container.getReserveToDate());
            reqData.setReserveRoom(room.getRoomCode());
            reqData.setReservePerson(room.getPerson());
            reqData.setReserveFeeAmt(reserveRequestData.getReserveFeeAmt());
            reqData.setReserveFeeAdd(reserveRequestData.getReserveFeeAdd());
            reqData.setReserveFeeRefund(reserveRequestData.getReserveFeeRefund());
            reqData.setReserveFeeRecv(reserveRequestData.getReserveFeeRecv());
            reqData.setReserveMember("");
            reqData.setReservePhone(reserveRequestData.getReservePhone());
            reqData.setReserveStatus("0");
            //reqData.setReserveStatus(); //RsvSndFlg ????????? (???????????? ????????? ??????????????? ??????) SP??? ???????????? ???
            reqData.setReserveUser(reserveRequestData.getReserveUser());
            reqData.setReserveMemo("");
            reqData.setStatus(reserveRequestData.getReserveStatus());
            reqData.setReservePwd(getReservePasswordTextView());
            reqData.setReserveMethod(getEnterTypeMethod(container.getCurrentEnterType()));
            reqData.setReserveNfcSerial("");
            reqData.setReserveUdid(udid);   //????????? ????????? ????????? ??? ?????? ?????? ????????? Udid??? ?????????.

            resultData = iLodgementService.reserveRoomEnterance(reqData);
            resultCode = iLodgementService.getResultCode();
            message = iLodgementService.getMessage();
            return null;
        }
    }
}


