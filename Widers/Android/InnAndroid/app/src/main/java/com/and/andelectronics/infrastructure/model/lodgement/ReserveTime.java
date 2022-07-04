package com.and.andelectronics.infrastructure.model.lodgement;

/**
 * Created by Won on 2018-02-24.
 */

public class ReserveTime {
    private String HourOfDate;
    private String Room;
    private String ReserveDate;
    private String ReserveYN;
    private boolean isSelected = false;

    public String getHourOfDate() {
        return HourOfDate;
    }

    public void setHourOfDate(String hourOfDate) {
        HourOfDate = hourOfDate;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getReserveDate() {
        return ReserveDate;
    }

    public void setReserveDate(String reserveDate) {
        ReserveDate = reserveDate;
    }

    public String getReserveYN() {
        return ReserveYN;
    }

    public void setReserveYN(String reserveYN) {
        ReserveYN = reserveYN;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
