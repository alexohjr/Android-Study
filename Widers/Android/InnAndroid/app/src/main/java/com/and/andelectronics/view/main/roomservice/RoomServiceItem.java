package com.and.andelectronics.view.main.roomservice;

/**
 * Created by yowonsm on 2018-05-27.
 */

public class RoomServiceItem {

    private String name;
    private int image;
    private boolean isReady;
    private RoomServiceStatus roomServiceStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isReady() {
        return isReady;
    }

    public RoomServiceStatus getRoomServiceStatus() {
        return roomServiceStatus;
    }

    public void setRoomServiceStatus(RoomServiceStatus roomServiceStatus) {
        this.roomServiceStatus = roomServiceStatus;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public RoomServiceItem() {
    }
    public RoomServiceItem(String name, int image, boolean isReady){
        this.name = name;
        this.image = image;
        this.isReady = isReady;
    }

    public RoomServiceItem(String name, int image, RoomServiceStatus status){
        this.name = name;
        this.image = image;
        this.isReady = isReady;
        this.roomServiceStatus = status;
    }

    public enum RoomServiceStatus{
        READY,
        REQUEST,
        COMPLETE,
        EMPTY,
    }

}
