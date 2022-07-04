package com.and.andelectronics.webservice.lodgement;

import android.content.Context;
import android.util.Log;

import com.and.andelectronics.common.WebServiceInformation;
import com.and.andelectronics.infrastructure.interfaces.ILodgementService;
import com.and.andelectronics.infrastructure.model.lodgement.CheckinOutRequestData;
import com.and.andelectronics.infrastructure.model.lodgement.HardwareInfo;
import com.and.andelectronics.infrastructure.model.lodgement.Lodgement;
import com.and.andelectronics.infrastructure.model.lodgement.LodgementRequestData;
import com.and.andelectronics.infrastructure.model.lodgement.ReservationRequestData;
import com.and.andelectronics.infrastructure.model.lodgement.ReserveRequestData;
import com.and.andelectronics.infrastructure.model.lodgement.ReserveRoom;
import com.and.andelectronics.infrastructure.model.lodgement.ReserveTime;
import com.and.andelectronics.infrastructure.model.lodgement.Room;
import com.and.andelectronics.infrastructure.model.lodgement.RoomRequestData;
import com.and.andelectronics.webservice.WebServiceClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by Won on 2016-08-27.
 */
public class LodgementServiceXml implements ILodgementService {

    private WebServiceClient WebServiceClient;

    //region Const 호출메소드
    private static String LODGEMENT_LIST = "SelectCompanyInfo";
    private static String LODGEMENT_LIST_BY_TYPE = "SelectCompanyByType";
    private static String ROOM_LIST = "SelectRoomInfo";
    private static String ROOM_LIST_GROUP = "SelectRoomGroup";  //객실조회(객실타입별로 Grade)
    private static String ROOM_LIST_EMPTY = "SelectRoomEmpty";  //빈객실조회 20181212

    private static String ROOM_LIST_CHOICE = "SelectRoomByChoice";  //예약하기전 객실선택
    private static String RESERVE_TIME_BY_ROOM = "SelectReserveTimeByRoom";  //예약시간 조회하기 20180224 yowonsm


    private static String RESERVE_ROOM = "ReserveRoom"; //객실예약
    private static String RESERVE_ROOM_BY_ROOMCODE = "ReserveRoomByRoomCode"; //객실예약
    private static String RESERVATION_ROOM = "ReservationRoom"; //객실예약 20190116 yowonsm

    private static String RESERVE_ROOM_ENTERANCE = "ReserveRoomEnterance"; //객실예약 NFC/PWD
    private static String INQUIRE_RESERVE_ROOM = "InquireReserveRoom"; //예약된 객실 조회 (Enduser앱으로 예약한 내역)
    private static String CHECKIN_OUT = "CheckinOut"; //체크인아웃

    private static String HARDWARE_INFO = "InquireHardwareInfo";    //하드웨어인포 조회 (허브 씨리얼번호, IP, PORT);

    private static String RESERVE_ROOM_CANCEL = "ReserveRoomCancel"; //객실예약췻(관리자, 엔드유저 공용
    private static String CHECKOUT_NOTICE = "InquireCheckoutNotice"; //체크아웃 타이머 시간
    //endregion

    private String message;
    private int result;

    public LodgementServiceXml(Context context){
        this.WebServiceClient = new WebServiceClient(WebServiceInformation.getUrl());
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getResultCode() {
        return result;
    }

    @Override
    public ArrayList<Lodgement> getLodgementList(LodgementRequestData requestData) {
        // TODO Auto-generated method stub
        try
        {
            this.setMessage("");
            Hashtable hashtable = new Hashtable();
            hashtable = requestData.getLodgementRequestData();
            HashMap output = new HashMap();
            output = this.WebServiceClient.RequestWebservice(LODGEMENT_LIST, hashtable,  Lodgement.class, true);
            ArrayList<Lodgement> list = (ArrayList<Lodgement>)output.get("output");
            Log.i("result", output.get("result").toString() +"???   ");

            Log.v("errmsg", output.get("message").toString() + "???");
            if(list == null)
            {
                list = new ArrayList<Lodgement>();
            }
            this.setResult(Integer.parseInt(output.get("result").toString()));
            this.setMessage(output.get("message").toString());
            return list;
        }
        catch(Exception ex)
        {

            return null;
        }
        finally
        {

        }
    }


    @Override
    public ArrayList<Lodgement> getLodgementListByType(LodgementRequestData requestData) {
        // TODO Auto-generated method stub
        try
        {
            this.setMessage("");
            Hashtable hashtable = new Hashtable();
            hashtable = requestData.getLodgementByTypeRequestData();
            HashMap output = new HashMap();
            output = this.WebServiceClient.RequestWebservice(LODGEMENT_LIST_BY_TYPE, hashtable,  Lodgement.class, true);
            ArrayList<Lodgement> list = (ArrayList<Lodgement>)output.get("output");
            Log.i("result", output.get("result").toString() +"???   ");

            Log.v("errmsg", output.get("message").toString() + "???");
            if(list == null)
            {
                list = new ArrayList<Lodgement>();
            }
            this.setResult(Integer.parseInt(output.get("result").toString()));
            this.setMessage(output.get("message").toString());
            return list;
        }
        catch(Exception ex)
        {

            return null;
        }
        finally
        {

        }
    }

    @Override
    public ArrayList<Room> getRoomList(RoomRequestData requestData) {
        try
        {
            this.setMessage("");
            Hashtable hashtable = new Hashtable();
            hashtable = requestData.getRoomRequestData();
            HashMap output = new HashMap();
            output = this.WebServiceClient.RequestWebservice(ROOM_LIST, hashtable,  Room.class, true);
            ArrayList<Room> list = (ArrayList<Room>)output.get("output");
            Log.i("result", output.get("result").toString() +"???   ");

            Log.v("errmsg", output.get("message").toString() + "???");
            if(list == null)
            {
                list = new ArrayList<Room>();
            }
            this.setResult(Integer.parseInt(output.get("result").toString()));
            this.setMessage(output.get("message").toString());
            return list;
        }
        catch(Exception ex)
        {

            return null;
        }
        finally
        {

        }
    }

    /**
     * 빈객실 조회
     * ACTION: R 객실별, G 그룹별
     * Company
     * Grade fromto
     * 일자 fromto
     * @param requestData
     * @return
     */
    @Override
    public ArrayList<Room> getRoomEmptyList(RoomRequestData requestData) {
        try
        {
            this.setMessage("");
            Hashtable hashtable = new Hashtable();
            hashtable = requestData.getRoomEmptyRequestData();
            HashMap output = new HashMap();
            output = this.WebServiceClient.RequestWebservice(ROOM_LIST_EMPTY, hashtable,  Room.class, true);
            ArrayList<Room> list = (ArrayList<Room>)output.get("output");
            Log.i("result", output.get("result").toString() +"???   ");

            Log.v("errmsg", output.get("message").toString() + "???");
            if(list == null)
            {
                list = new ArrayList<Room>();
            }
            this.setResult(Integer.parseInt(output.get("result").toString()));
            this.setMessage(output.get("message").toString());
            return list;
        }
        catch(Exception ex)
        {

            return null;
        }
        finally
        {

        }

    }
    @Override
    public ArrayList<Room> getRoomGroupList(RoomRequestData requestData) {
        try
        {
            this.setMessage("");
            Hashtable hashtable = new Hashtable();
            hashtable = requestData.getRoomRequestData();
            HashMap output = new HashMap();
            output = this.WebServiceClient.RequestWebservice(ROOM_LIST_GROUP, hashtable,  Room.class, true);
            ArrayList<Room> list = (ArrayList<Room>)output.get("output");
            Log.i("result", output.get("result").toString() +"???   ");

            Log.v("errmsg", output.get("message").toString() + "???");
            if(list == null)
            {
                list = new ArrayList<Room>();
            }
            this.setResult(Integer.parseInt(output.get("result").toString()));
            this.setMessage(output.get("message").toString());
            return list;
        }
        catch(Exception ex)
        {

            return null;
        }
        finally
        {

        }

    }



    @Override
    public ArrayList<Room> getRoomByChoice(RoomRequestData requestData) {
        try
        {
            this.setMessage("");
            Hashtable hashtable = new Hashtable();
            hashtable = requestData.getRoomReserveRequestData();
            HashMap output = new HashMap();
            output = this.WebServiceClient.RequestWebservice(ROOM_LIST_CHOICE, hashtable,  Room.class, true);
            ArrayList<Room> list = (ArrayList<Room>)output.get("output");
            Log.i("result", output.get("result").toString() +"???   ");

            Log.v("errmsg", output.get("message").toString() + "???");
            if(list == null)
            {
                list = new ArrayList<Room>();
            }
            this.setResult(Integer.parseInt(output.get("result").toString()));
            this.setMessage(output.get("message").toString());
            return list;
        }
        catch(Exception ex)
        {

            return null;
        }
        finally
        {

        }
    }


    @Override
    public ArrayList<ReserveTime> getReserveTimeByRoom(RoomRequestData requestData) {
        try
        {
            this.setMessage("");
            Hashtable hashtable = new Hashtable();
            hashtable = requestData.getReserveTimeRequestData();
            HashMap output = new HashMap();
            output = this.WebServiceClient.RequestWebservice(RESERVE_TIME_BY_ROOM, hashtable,  ReserveTime.class, true);
            ArrayList<ReserveTime> list = (ArrayList<ReserveTime>)output.get("output");
            Log.i("result", output.get("result").toString() +"???   ");

            Log.v("errmsg", output.get("message").toString() + "???");
            if(list == null)
            {
                list = new ArrayList<ReserveTime>();
            }
            this.setResult(Integer.parseInt(output.get("result").toString()));
            this.setMessage(output.get("message").toString());
            return list;
        }
        catch(Exception ex)
        {

            return null;
        }
        finally
        {

        }
    }

    @Override
    public Room reserveRoom(RoomRequestData requestData) {
        try
        {
            this.setMessage("");
            Hashtable hashtable = new Hashtable();
            hashtable = requestData.getRoomReserveRequestData();
            HashMap output = new HashMap();
            output = this.WebServiceClient.RequestWebservice(RESERVE_ROOM, hashtable,  Room.class, false);
            Room data = (Room)output.get("output");
            Log.i("result", output.get("result").toString() +"???   ");

            Log.v("errmsg", output.get("message").toString() + "???");
            if(data == null)
            {
                data = new Room();
            }
            this.setResult(Integer.parseInt(output.get("result").toString()));
            this.setMessage(output.get("message").toString());
            return data;
        }
        catch(Exception ex)
        {

            return null;
        }
        finally
        {

        }
    }

    @Override
    public Room reserveRoomByRoomCode(RoomRequestData requestData) {
        try
        {
            this.setMessage("");
            Hashtable hashtable = new Hashtable();
            hashtable = requestData.getRoomReserveByRoomCodeRequestData();
            HashMap output = new HashMap();
            output = this.WebServiceClient.RequestWebservice(RESERVE_ROOM_BY_ROOMCODE, hashtable,  Room.class, false);
            Room data = (Room)output.get("output");
            Log.i("result", output.get("result").toString() +"???   ");

            Log.v("errmsg", output.get("message").toString() + "???");
            if(data == null)
            {
                data = new Room();
            }
            this.setResult(Integer.parseInt(output.get("result").toString()));
            this.setMessage(output.get("message").toString());
            return data;
        }
        catch(Exception ex)
        {

            return null;
        }
        finally
        {

        }
    }

    @Override
    public ReserveRoom reserveRoomEnterance(ReserveRequestData requestData) {

        try
        {
            this.setMessage("");
            Hashtable hashtable = new Hashtable();
            hashtable = requestData.getReserveEnteranceRequestData();
            HashMap output = new HashMap();
            output = this.WebServiceClient.RequestWebservice(RESERVE_ROOM_ENTERANCE, hashtable,  ReserveRoom.class, false);
            ReserveRoom data = (ReserveRoom)output.get("output");
            Log.i("result", output.get("result").toString() +"???   ");

            Log.v("errmsg", output.get("message").toString() + "???");
            if(data == null)
            {
                data = new ReserveRoom();
            }
            this.setResult(Integer.parseInt(output.get("result").toString()));
            this.setMessage(output.get("message").toString());
            return data;
        }
        catch(Exception ex)
        {

            return null;
        }
        finally
        {

        }
    }


    @Override
    public ReserveRoom reservationRoom(ReservationRequestData requestData) {

        try
        {
            this.setMessage("");
            Hashtable hashtable = new Hashtable();
            hashtable = requestData.getReservationRequestData();
            HashMap output = new HashMap();
            output = this.WebServiceClient.RequestWebservice(RESERVATION_ROOM, hashtable,  ReserveRoom.class, false);
            ReserveRoom data = (ReserveRoom)output.get("output");
            Log.i("result", output.get("result").toString() +"???   ");

            Log.v("errmsg", output.get("message").toString() + "???");
            if(data == null)
            {
                data = new ReserveRoom();
            }
            this.setResult(Integer.parseInt(output.get("result").toString()));
            this.setMessage(output.get("message").toString());
            return data;
        }
        catch(Exception ex)
        {

            return null;
        }
        finally
        {

        }
    }


    @Override
    public ArrayList<ReserveRoom> inquireReserveRoom(ReserveRequestData requestData) {

        try
        {
            this.setMessage("");
            Hashtable hashtable = new Hashtable();
            hashtable = requestData.getInquireReserveRequestData();
            HashMap output = new HashMap();
            output = this.WebServiceClient.RequestWebservice(INQUIRE_RESERVE_ROOM, hashtable,  ReserveRoom.class, true);
            ArrayList<ReserveRoom> list = (ArrayList<ReserveRoom>)output.get("output");

            if(list == null)
            {
                list = new ArrayList<ReserveRoom>();
            }
            this.setResult(Integer.parseInt(output.get("result").toString()));
            this.setMessage(output.get("message").toString());
            return list;
        }
        catch(Exception ex)
        {

            return null;
        }
        finally
        {

        }

    }


    @Override
    public Room checkinOut(CheckinOutRequestData requestData) {
        try
        {
            this.setMessage("");
            Hashtable hashtable = new Hashtable();
            hashtable = requestData.getCheckinOutRequestData();
            HashMap output = new HashMap();
            output = this.WebServiceClient.RequestWebservice(CHECKIN_OUT, hashtable,  Room.class, false);
            Room data = (Room)output.get("output");

            if(data == null)
            {
                data = new Room();
            }
            this.setResult(Integer.parseInt(output.get("result").toString()));
            this.setMessage(output.get("message").toString());
            return data;
        }
        catch(Exception ex)
        {
            return null;
        }
        finally
        {

        }
    }

    @Override
    public HardwareInfo inquireHardwareInfo(RoomRequestData requestData) {
        try
        {
            this.setMessage("");
            Hashtable hashtable = new Hashtable();
            hashtable = requestData.getHardwareRequestData();
            HashMap output = new HashMap();
            output = this.WebServiceClient.RequestWebservice(HARDWARE_INFO, hashtable,  HardwareInfo.class, false);
            HardwareInfo data = (HardwareInfo)output.get("output");
            Log.i("result", output.get("result").toString() +"???   ");

            Log.v("errmsg", output.get("message").toString() + "???");
            if(data == null)
            {
                data = new HardwareInfo();
            }
            this.setResult(Integer.parseInt(output.get("result").toString()));
            this.setMessage(output.get("message").toString());
            return data;
        }
        catch(Exception ex)
        {

            return null;
        }
        finally
        {

        }
    }



    @Override
    public Room reserveRoomCancel(RoomRequestData requestData){
        try
        {
            this.setMessage("");
            Hashtable hashtable = new Hashtable();
            hashtable = requestData.getReserveCancelRequestData();
            HashMap output = new HashMap();
            output = this.WebServiceClient.RequestWebservice(RESERVE_ROOM_CANCEL, hashtable,  Room.class, false);
            Room data = (Room)output.get("output");
            Log.i("result", output.get("result").toString() +"???   ");

            Log.v("errmsg", output.get("message").toString() + "???");
            if(data == null)
            {
                data = new Room();
            }
            this.setResult(Integer.parseInt(output.get("result").toString()));
            this.setMessage(output.get("message").toString());
            return data;
        }
        catch(Exception ex)
        {

            return null;
        }
        finally
        {

        }
    }


    @Override
    public ArrayList<ReserveRoom> inquireCheckoutNotice(ReserveRequestData requestData) {

        try
        {
            this.setMessage("");
            Hashtable hashtable = new Hashtable();
            hashtable = requestData.getInquireCheckoutNoticeRequestData();
            HashMap output = new HashMap();
            output = this.WebServiceClient.RequestWebservice(CHECKOUT_NOTICE, hashtable,  ReserveRoom.class, true);
            ArrayList<ReserveRoom> list = (ArrayList<ReserveRoom>)output.get("output");

            if(list == null)
            {
                list = new ArrayList<ReserveRoom>();
            }
            this.setResult(Integer.parseInt(output.get("result").toString()));
            this.setMessage(output.get("message").toString());
            return list;
        }
        catch(Exception ex)
        {

            return null;
        }
        finally
        {

        }

    }

}
