package com.and.andelectronics.infrastructure.interfaces;

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

import java.util.ArrayList;

/**
 * Created by Won on 2016-08-27.
 */
public interface ILodgementService {
    /**
     * ref message
     * @return
     */
    String getMessage();

    /**
     * ref resultCode
     * @return
     */
    int getResultCode();

    ArrayList<Lodgement> getLodgementList(LodgementRequestData requestData);
    ArrayList<Lodgement> getLodgementListByType(LodgementRequestData requestData);
    ArrayList<Room> getRoomList(RoomRequestData requestData);

    ArrayList<Room> getRoomEmptyList(RoomRequestData requestData);
    ArrayList<Room> getRoomGroupList(RoomRequestData requestData);
    ArrayList<Room> getRoomByChoice(RoomRequestData requestData);
    ArrayList<ReserveTime> getReserveTimeByRoom(RoomRequestData requestData);

    Room reserveRoom(RoomRequestData requestData);
    Room reserveRoomByRoomCode(RoomRequestData requestData);


    ReserveRoom reserveRoomEnterance(ReserveRequestData requestData);
    ReserveRoom reservationRoom(ReservationRequestData requestData);

    ArrayList<ReserveRoom> inquireReserveRoom(ReserveRequestData requestData);

    Room checkinOut(CheckinOutRequestData requestData);

    HardwareInfo inquireHardwareInfo(RoomRequestData requestData);

    Room reserveRoomCancel(RoomRequestData requestData);

    ArrayList<ReserveRoom> inquireCheckoutNotice(ReserveRequestData requestData);
}
