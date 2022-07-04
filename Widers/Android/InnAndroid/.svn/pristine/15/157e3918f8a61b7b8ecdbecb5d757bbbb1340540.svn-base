package com.and.andelectronics.view.main.enterance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.and.andelectronics.Container;
import com.and.andelectronics.R;
import com.and.andelectronics.common.ActivityManager;
import com.and.andelectronics.common.util.AppUtil;
import com.and.andelectronics.common.util.DateUtil;
import com.and.andelectronics.infrastructure.interfaces.ILodgementService;
import com.and.andelectronics.infrastructure.model.lodgement.ReserveRequestData;
import com.and.andelectronics.infrastructure.model.lodgement.ReserveRoom;
import com.and.andelectronics.infrastructure.model.lodgement.Room;
import com.and.andelectronics.webservice.lodgement.LodgementServiceXml;

/**
 * Created by 원성민 on 2017-06-28.
 */

public class EnteranceConfigActivity extends Activity implements View.OnClickListener
{

    private Context context;
    private Container container;
    private Room room;
    private ReserveRequestData reserveRequestData;
    private ILodgementService iLodgementService;

    private android.app.ProgressDialog ProgressDialog;

    private TextView companyNameTextView;
    private TextView gradeNameTextView;
    private TextView reserveTextView;
    private TextView reserveNameTextView;
    private TextView reserveHpNoTextView;
    private TextView enteranceTypeTextView;

    private EditText sendPasswordEditText;
    private Button sendBluetoothButton;
    private Button sendNFCButton;
    private Button enteranceButton;

    private LinearLayout enteranceTypeLinearLayout;
    private LinearLayout enteranceTypeInputLinearLayout;

    /*예약출입방법
    '0':Key Pad, '1':NFC, '2':RF Card,
    '3':App, '4':Wifi*/
    private String enterMethod = null;
    private String udid;

    private ActivityManager activityManager = ActivityManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_enterence_config);

        this.context = this;
        this.container = (Container)getApplicationContext();
        this.activityManager.addActivity(this);
        this.iLodgementService = new LodgementServiceXml(context);
        this.room = (Room)this.getIntent().getExtras().getSerializable("obj");
        this.reserveRequestData = (ReserveRequestData)this.getIntent().getExtras().getSerializable("reserve");
        this.viewInit();
        this.dataInit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.activityManager.removeActivity(this);
    }

    /**
     * viewBinding 및 리스너 달기
     **/
    private void viewInit()
    {
        this.companyNameTextView = (TextView)findViewById(R.id.companyNameTextView);
        this.gradeNameTextView = (TextView)findViewById(R.id.gradeNameTextView);
        this.reserveTextView = (TextView)findViewById(R.id.reserveTextView);
        this.reserveNameTextView = (TextView)findViewById(R.id.reserveNameTextView);
        this.reserveHpNoTextView = (TextView)findViewById(R.id.reserveHpNoTextView);
        this.enteranceTypeTextView = (TextView)findViewById(R.id.enteranceTypeTextView);

        this.sendPasswordEditText = (EditText)findViewById(R.id.sendPasswordEditText);
        this.sendBluetoothButton = (Button)findViewById(R.id.sendBluetoothButton);
        this.sendBluetoothButton.setOnClickListener(this);
        this.sendNFCButton = (Button)findViewById(R.id.sendNFCButton);
        this.sendNFCButton.setOnClickListener(this);
        this.enteranceButton = (Button)findViewById(R.id.enteranceButton);
        this.enteranceButton.setOnClickListener(this);

        this.sendPasswordEditText.setVisibility(View.GONE);
        this.sendBluetoothButton.setVisibility(View.GONE);
        this.sendNFCButton.setVisibility(View.GONE);

        this.enteranceTypeLinearLayout = (LinearLayout)findViewById(R.id.enteranceTypeLinearLayout);
        this.enteranceTypeLinearLayout.setOnClickListener(this);
        this.enteranceTypeInputLinearLayout = (LinearLayout)findViewById(R.id.enteranceTypeInputLinearLayout);
        this.enteranceTypeInputLinearLayout.setOnClickListener(this);
    }

    private void dataInit()
    {
        this.udid = AppUtil.getIMEI(context);

        if(udid == null || udid.equals("")){
            Toast.makeText(context, "장치의 정보를 가져오지 못했습니다. 앱의 권한설정을 확인하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        this.companyNameTextView.setText(room.getCompanyName());
        this.gradeNameTextView.setText(room.getGradeName());
        this.reserveTextView.setText(container.getReserveFromDate());    //입력 시간
        this.reserveNameTextView.setText(reserveRequestData.getReserveUser());
        this.reserveHpNoTextView.setText(reserveRequestData.getReservePhone());


    }

    public String getSendPasswordEditText() {
        return sendPasswordEditText.getText().toString();
    }

    private String getCurrentDate(){
        return DateUtil.nowDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 출입설정 방법 변경
     */
    private void onChangedEnteranceType(String enteranceType)
    {
        this.enteranceTypeTextView.setText(enteranceType);
        if(enteranceType.equals("비밀번호"))
        {
            this.sendPasswordEditText.setVisibility(View.VISIBLE);
            this.sendBluetoothButton.setVisibility(View.GONE);
            this.sendNFCButton.setVisibility(View.GONE);
            this.enterMethod = "0";
        }
        else if(enteranceType.equals("블루투스"))
        {
            this.sendPasswordEditText.setVisibility(View.GONE);
            this.sendBluetoothButton.setVisibility(View.VISIBLE);
            this.sendNFCButton.setVisibility(View.GONE);
        }
        else if(enteranceType.equals("NFC"))
        {
            this.sendPasswordEditText.setVisibility(View.GONE);
            this.sendBluetoothButton.setVisibility(View.GONE);
            this.sendNFCButton.setVisibility(View.VISIBLE);
            this.enterMethod = "1";
        }
        else if(enteranceType.equals("카드수령"))
        {
            this.sendPasswordEditText.setVisibility(View.GONE);
            this.sendBluetoothButton.setVisibility(View.GONE);
            this.sendNFCButton.setVisibility(View.GONE);
            this.enterMethod = "2";
        }
    }

    /**
     * 출입설정완료 (객실선택, 지불, 출입설정 모두완료) 메인화면으로 이동
     */
    private void completeEnteranceConfig(){
        new ReserveRoomEnteranceAsync().execute();
//        Intent intent = new Intent();
//        this.setResult(RESULT_OK);
//        finish();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.enteranceTypeLinearLayout:
                Intent intent = new Intent(context, EnteranceTypeActivity.class);
                startActivityForResult(intent, 0);

                break;

            case R.id.enteranceButton:
                if(enterMethod == null){
                    Toast.makeText(context, "입실방법을 선택해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                completeEnteranceConfig();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case RESULT_OK:
                String enteranceType = data.getStringExtra("enteranceType");
                onChangedEnteranceType(enteranceType);

                break;

            default:
                break;
        }
    }



    private class ReserveRoomEnteranceAsync extends AsyncTask<Void, String, Void> {

        private ReserveRoom resultData;
        private String message;
        private int resultCode;

        @Override
        protected void onPostExecute(Void result) {
            EnteranceConfigActivity.this.ProgressDialog.dismiss();

            if(resultCode == 0){
                Toast.makeText(context, "예약되었습니다. 예약내역은 내정보 -> 예약정보 에서 확인할 수 있습니다.", Toast.LENGTH_SHORT).show();
                container.setReserveFromDate(null); //예약시간 초기화
                activityManager.finishAllActivity();

            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            EnteranceConfigActivity.this.ProgressDialog = android.app.ProgressDialog.show(
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
            //reqData.setReserveStatus(); //RsvSndFlg 추가됨 (이부분이 빠져서 업데이트가 안됨) SP도 수정해야 함
            reqData.setReserveUser(reserveRequestData.getReserveUser());
            reqData.setReserveMemo("");
            reqData.setStatus(reserveRequestData.getReserveStatus());
            reqData.setReservePwd(getSendPasswordEditText());
            reqData.setReserveMethod(enterMethod);
            reqData.setReserveNfcSerial("");
            reqData.setReserveUdid(udid);   //예약한 내역을 확인할 수 있게 각각 장치의 Udid를 받는다.

            resultData = iLodgementService.reserveRoomEnterance(reqData);
            resultCode = iLodgementService.getResultCode();
            message = iLodgementService.getMessage();
            return null;
        }
    }



}
