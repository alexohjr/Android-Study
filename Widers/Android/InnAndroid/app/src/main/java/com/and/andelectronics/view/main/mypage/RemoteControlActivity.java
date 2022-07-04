package com.and.andelectronics.view.main.mypage;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.and.andelectronics.Container;
import com.and.andelectronics.R;
import com.and.andelectronics.common.ActivityManager;
import com.and.andelectronics.common.ez.EZAlertDialog;
import com.and.andelectronics.common.util.DateUtil;
import com.and.andelectronics.common.util.SocketProtocolUtil;
import com.and.andelectronics.infrastructure.interfaces.ILodgementService;
import com.and.andelectronics.infrastructure.model.lodgement.CheckinOutRequestData;
import com.and.andelectronics.infrastructure.model.lodgement.HardwareInfo;
import com.and.andelectronics.infrastructure.model.lodgement.ReserveRoom;
import com.and.andelectronics.infrastructure.model.lodgement.Room;
import com.and.andelectronics.infrastructure.model.lodgement.RoomRequestData;
import com.and.andelectronics.webservice.lodgement.LodgementServiceXml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;


/**
 * Created by 원성민 on 2017-11-22.
 */

public class RemoteControlActivity extends AppCompatActivity implements View.OnClickListener{

    private Context context;
    private ReserveRoom reserveRoom;
    private android.app.ProgressDialog ProgressDialog;
    private ActivityManager activityManager = ActivityManager.getInstance();
    private Container container;
    private Button checkinButton;
    private Button checkoutButton;
    private Button openDoorButton;
    private Button reserveCanceButton;


    //region 소켓통신
    private Socket socket;
    BufferedReader socket_in;
    PrintWriter socket_out;
    OutputStream outputStream;

    //endregion

    private ILodgementService iLodgementService;

    private boolean isCheckin = false;

    private HardwareInfo hardwareInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remotecontrol);

        this.context = this;
        this.activityManager.addActivity(this);
        this.container = (Container)getApplicationContext();
        this.reserveRoom = container.getCurrentReserveRoom();
        iLodgementService = new LodgementServiceXml(context);
        this.bindView();
        this.bindEvent();

        mHandler = new Handler();

        String hostname = "211.247.239.56";
        //String hostname = "192.168.1.18";
        int port = 14631;
        final int timeout = 10000;

        final SocketAddress socketAddress = new InetSocketAddress(hostname, port);
        Thread worker = new Thread() {
            public void run() {
                try {
                    /*socket = new Socket("211.247.239.56", 14631);
                    socket_out = new PrintWriter(socket.getOutputStream(), true);
                    socket_in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 기존소스*/

                    socket = new Socket();
                    socket.setSoTimeout(timeout);
                    socket.connect(socketAddress, timeout);
                    outputStream = socket.getOutputStream();
                    //socket_out = new PrintWriter(socket.getOutputStream(), true);
                    socket_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally{

                }
                try {
                    while (true) {
                        readData = socket_in.readLine();
                        Log.w("readData", " " + readData);

                        mHandler.post(showUpdate);

//                        mH.post(new Runnable() {
//                            public void run() {
//                               // output.setText(readData);
//                                mHandler.post(showUpdate);
//                            }
//                        });
                    }
                } catch (Exception e) {
                }
            }
        };
        worker.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.activityManager.removeActivity(this);
        try {
            if(socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void bindView(){
        this.checkinButton = (Button)findViewById(R.id.checkinButton);
        this.checkoutButton = (Button)findViewById(R.id.checkoutButton);
        this.openDoorButton = (Button)findViewById(R.id.openDoorButton);
        this.reserveCanceButton = (Button)findViewById(R.id.reserveCanceButton);
        if(!reserveRoom.getStatus().equals("ST08"))//예약이 아니면
        {
            checkinButton.setClickable(false);
        }

    }

    private void bindEvent(){
        checkinButton.setOnClickListener(this);
        checkoutButton.setOnClickListener(this);
        openDoorButton.setOnClickListener(this);
        reserveCanceButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.checkinButton:

                EZAlertDialog.show1(getSupportFragmentManager(), "체크인 하시겠습니까?", new EZAlertDialog.EZAlertDialogListener() {
                    @Override
                    public void onClickConfirm(int selected) {

                    }

                    @Override
                    public void onClickConfirm() {
                        isCheckin = true;
                        new CheckinOutAsync().execute();
                        //openDoor();
                    }

                    @Override
                    public void onClickCancel() {

                    }
                });
                break;


            case R.id.checkoutButton:

                EZAlertDialog.show1(getSupportFragmentManager(), "체크아웃 하시겠습니까?", new EZAlertDialog.EZAlertDialogListener() {
                    @Override
                    public void onClickConfirm(int selected) {

                    }

                    @Override
                    public void onClickConfirm() {
                        isCheckin = false;
                        new CheckinOutAsync().execute();

                    }

                    @Override
                    public void onClickCancel() {

                    }
                });
                break;

            case R.id.openDoorButton:

                if((socket != null && socket.isConnected() == true) == false){
                    Toast.makeText(context, "허브와 연결되지 않았습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                EZAlertDialog.show1(getSupportFragmentManager(), "도어락 잠금을 푸시겠습니까?", new EZAlertDialog.EZAlertDialogListener() {
                    @Override
                    public void onClickConfirm(int selected) {

                    }

                    @Override
                    public void onClickConfirm() {

                        new InquireHardwareInfoAsync().execute();

                    }

                    @Override
                    public void onClickCancel() {

                    }
                });
                break;

            case R.id.reserveCanceButton:

                EZAlertDialog.show1(getSupportFragmentManager(), "예약을 취소하시겠습니까?", new EZAlertDialog.EZAlertDialogListener() {
                    @Override
                    public void onClickConfirm(int selected) {

                    }

                    @Override
                    public void onClickConfirm() {

                        new ReserveRoomCancelAsync().execute();

                    }

                    @Override
                    public void onClickCancel() {

                    }
                });
                break;
        }
    }

    private class CheckinOutAsync extends AsyncTask<Void, String, Void> {

        private Room resultRoom;

        @Override
        protected void onPostExecute(Void result) {
            RemoteControlActivity.this.ProgressDialog.dismiss();
            if(resultRoom == null) {
                if(isCheckin == true){
                    Toast.makeText(context, "체크인 실패했습니다..", Toast.LENGTH_SHORT).show();
                }

            }else{
                if(isCheckin == true){
                    Toast.makeText(context, "체크인 되었습니다.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "체크아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                }


            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            RemoteControlActivity.this.ProgressDialog = android.app.ProgressDialog.show(
                    context,
                    context.getResources().getString(R.string.async_store_title),context.getResources().getString(R.string.async_store_msg));

        }

        @Override
        protected Void doInBackground(Void... params) {

            CheckinOutRequestData reqData = new CheckinOutRequestData();
            reqData.setCompanyCode(reserveRoom.getCompanyCode());
            reqData.setRoomCode(reserveRoom.getReserveRoom());
            reqData.setSaleDate(DateUtil.nowDateFormat("yyyy-MM-dd"));

            if(isCheckin == true)
            {
                reqData.setCheckinDate(DateUtil.nowDateFormat("yyyy-MM-dd HH:mm:ss"));
                reqData.setCheckoutDate("");
                reqData.setStatus(reserveRoom.getStatus());  //객실상태
            }
            else
            {
                reqData.setCheckinDate("");
                reqData.setCheckoutDate(DateUtil.nowDateFormat("yyyy-MM-dd HH:mm:ss"));
                reqData.setStatus("ST09");  //청소
            }




            resultRoom = iLodgementService.checkinOut(reqData);

            return null;
        }
    }



    private class InquireHardwareInfoAsync extends AsyncTask<Void, String, Void> {

        @Override
        protected void onPostExecute(Void result) {
            RemoteControlActivity.this.ProgressDialog.dismiss();
            if(hardwareInfo == null)
            {
                Toast.makeText(context, "하드웨어 정보를 가져오지 못했습니다.",  Toast.LENGTH_SHORT).show();
            }
            else
            {
                openDoor();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            RemoteControlActivity.this.ProgressDialog = android.app.ProgressDialog.show(
                    context,
                    context.getResources().getString(R.string.async_store_title),context.getResources().getString(R.string.async_store_msg));

        }

        @Override
        protected Void doInBackground(Void... params) {

            RoomRequestData reqData = new RoomRequestData();
            reqData.setCompanyCode(reserveRoom.getCompanyCode());
            reqData.setRoomCode(reserveRoom.getReserveRoom());

            hardwareInfo = iLodgementService.inquireHardwareInfo(reqData);

            return null;
        }
    }

    private Thread checkUpdate = new Thread() {

        public void run() {
            try {
                String line;
                Log.w("ChattingStart", "Start Thread");
                while (true) {
                    Log.w("Chatting is running", "chatting is running");
                    line = socket_in.readLine();
                    readData = line;
                    mHandler.post(showUpdate);
                }
            } catch (Exception e) {

            }
        }
    };

    String readData;
    private byte[] sendData;
    private static final byte[] STX = {(byte)0x02};
    private static final byte[] MAIN_ONE = {(byte)0xAD};
    private static final byte[] MAIN_TWO = {(byte)0x11};
    private byte[] bhub = {(byte)0x60};
    private byte[] bhub2 = {(byte)0x01};
    private byte[] bhub3= {(byte)0x94};
    private byte[] bhub4 = {(byte)0x42};
    private byte[] bhub5 = {(byte)0x1E};
    private byte[] bhub6 = {(byte)0xFC};


    //허브 씨리얼넘버 하드코딩 60 01 94 42 1C 38
    //0x60 0x01 0x94 0x42 0x1c 0x39

    String hub = "60";
    String hub2 = "1";
    String hub3 = "94";
    String hub4 = "42";
    String hub5 = "1C";
    String hub6 = "38";

    private static final byte[] ETX = {(byte)0x03};

    String sStx = new String(STX);
    String main1 = "AD";
    String main2 = "11";

    String cmd = "DO";
    String inputData = "20170902121212";
    String chkSum = "1";
    String sEtx = new String(ETX);


    public byte[] socketData(){
        byte socketByte[] = new byte[34];
        socketByte[0] = STX[0];
        socketByte[1] = (byte)0xAD;
        socketByte[2] = (byte)0x11;
        socketByte[3] = (byte)0x60;
        socketByte[4] = (byte)0x01;
        socketByte[5] = (byte)0x94;
        socketByte[6] = (byte)0x42;
        socketByte[7] = (byte)0x1E;
        socketByte[8] = (byte)0xFC;
        socketByte[9] = 79;
        socketByte[10] = 68;

        socketByte[11] = 49;
        socketByte[12] = 57;
        socketByte[13] = 50;
        socketByte[14] = 46;
        socketByte[15] = 49;
        socketByte[16] = 54;
        socketByte[17] = 56;
        socketByte[18] = 46;
        socketByte[19] = 49;
        socketByte[20] = 46;
        socketByte[21] = 50;
        socketByte[22] = 50;
        socketByte[23] = 58;
        socketByte[24] = 52;
        socketByte[25] = 54;
        socketByte[26] = 48;
        socketByte[27] = 57;
        socketByte[28] = 57;
        socketByte[29] = 124;
        socketByte[30] = 48;
        socketByte[31] = 101;
        socketByte[32] = 102;
        socketByte[33] = ETX[0];


        return socketByte;
    }
    public String makePacketData(String cmd, String inputData)
    {
        /*String checkSumStr = sStx + main1 + main2 + hub + hub2 +hub3 +hub4 +hub5 + hub6
                + cmd + inputData;
        byte[] checkSumByte = checkSumStr.getBytes();
        chkSum = SocketProtocolUtil.getChecksum(checkSumByte);
        String appendData = sStx + main1 + main2 + hub + hub2 +hub3 +hub4 +hub5 + hub6 + cmd + inputData + chkSum + sEtx;
        byte[] temp = appendData.getBytes();
        String packetData = null;*/

        byte socketByte[] = new byte[31];
        socketByte[0] = STX[0];
        socketByte[1] = MAIN_ONE[0];
        socketByte[2] = MAIN_TWO[0];
        socketByte[3] = bhub[0];
        socketByte[4] = bhub2[0];
        socketByte[5] = bhub3[0];
        socketByte[6] = bhub4[0];
        socketByte[7] = bhub5[0];
        socketByte[8] = bhub6[0];
        socketByte[9] = 79;
        socketByte[10] = 68;

        socketByte[11] = 49;
        socketByte[12] = 50;
        socketByte[13] = 55;
        socketByte[14] = 46;
        socketByte[15] = 48;
        socketByte[16] = 46;
        socketByte[17] = 48;
        socketByte[18] = 46;
        socketByte[19] = 49;
        socketByte[20] = 58;
        socketByte[21] = 54;
        socketByte[22] = 50;
        socketByte[23] = 49;
        socketByte[24] = 49;
        socketByte[25] = 57;
        socketByte[26] = 124;
        socketByte[27] = 48;
        socketByte[28] = 1;
        socketByte[29] = ETX[0];
        socketByte[30] = 0;

        byte[] command = cmd.getBytes();
        byte[] data = inputData.getBytes();
        byte[] et = ETX;
        String checkSumStr = sStx + main1 + main2 + hub + hub2 +hub3 +hub4 +hub5 + hub6
                + cmd + inputData;
        byte[] checkSumByte = checkSumStr.getBytes();
        chkSum = SocketProtocolUtil.getChecksum(checkSumByte);
        String appendData = sStx + main1 + main2 + hub + hub2 +hub3 +hub4 +hub5 + hub6 + cmd + inputData + chkSum + sEtx;
        byte[] temp = appendData.getBytes();
        String packetData = null;

        try{
            packetData = new String(temp);
        }catch(Exception ex){

        }

        return packetData;
    }
    private void openDoor(){

        String cmd ="OD";
        String inputData = hardwareInfo.getIPAddress() + ":" + hardwareInfo.getPort() + "|0";
        String humSerial = "600194421EFC";
        //byte hubSerialByte[] =


        Toast.makeText(context,  cmd + "   " + inputData, Toast.LENGTH_LONG).show();


        String data = makePacketData(cmd, inputData);

        byte byteData[] = SocketProtocolUtil.openDoorCommand(cmd + inputData);
        byte requstByteData[] = SocketProtocolUtil.makeData(byteData);


        byte bData[] = socketData();
        //data = new String(bData);
        Log.w("NETWORK", " " + data);
        if(data != null) {
            try {
                //outputStream.write(data.getBytes());
                outputStream.write(requstByteData);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //socket_out.println(data);
            //socket_out.println(bData);
            //socket_out.println(bData);
            //socket_out.print(new byte[30]);
        }
    }


    private Handler mHandler;

    private Runnable showUpdate = new Runnable() {

        public void run() {
            Toast.makeText(RemoteControlActivity.this, "Coming word: " + readData, Toast.LENGTH_SHORT).show();
        }

    };



    private class ReserveRoomCancelAsync extends AsyncTask<Void, String, Void> {

        private Room resultRoom;
        private String message;
        private int resultCode;
        @Override
        protected void onPostExecute(Void result) {
            RemoteControlActivity.this.ProgressDialog.dismiss();

            if(resultCode == 0)
            {
                Toast.makeText(context, "예약 취소되었습니다.", Toast.LENGTH_SHORT).show();
                finish();

            }
            else
            {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            RemoteControlActivity.this.ProgressDialog = android.app.ProgressDialog.show(
                    context,
                    context.getResources().getString(R.string.async_store_title),context.getResources().getString(R.string.async_store_msg));

        }

        @Override
        protected Void doInBackground(Void... params) {

            RoomRequestData reqData = new RoomRequestData();
            reqData.setCompanyCode(reserveRoom.getCompanyCode());
            reqData.setRoomCode(reserveRoom.getReserveRoom());
            reqData.setReserveDate(reserveRoom.getReserveDate());
            reqData.setReserveSeq(reserveRoom.getReserveSeq());

            resultRoom = iLodgementService.reserveRoomCancel(reqData);
            resultCode = iLodgementService.getResultCode();
            message = iLodgementService.getMessage();
            return null;
        }
    }

}