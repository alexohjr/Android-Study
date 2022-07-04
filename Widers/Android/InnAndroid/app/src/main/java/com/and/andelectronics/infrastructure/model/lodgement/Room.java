package com.and.andelectronics.infrastructure.model.lodgement;

import java.io.Serializable;

/**
 * Created by Won on 2017-06-18.
 */
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    public String CompanyCode; //매장코드
    public String CompanyName; //매장명
    public String RoomCode = ""; //객실코드
    public String RoomName = ""; //객실명

    public int Person; //인원
    public String Grade;    //객실 Grade
    public String GradeName;    //객실 Grade
    public String Type; //Type?
    public String Memo; //메모
    public String Status; //방구분

    public int DayusePrice; //대실료
    public int OvernightPrice;  //숙박료

    public String CheckIn;  //체크인 시작시간
    public int RentTime;    //대실시간
    public int EmptyRoomCount; //이지미 건수

    public Room()
    {

    }

    public String getCompanyCode() {
        return CompanyCode;
    }

    public void setCompanyCode(String companyCode) {
        CompanyCode = companyCode;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getRoomCode() {
        return RoomCode;
    }

    public void setRoomCode(String roomCode) {
        RoomCode = roomCode;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public int getPerson() {
        return Person;
    }

    public void setPerson(int person) {
        Person = person;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getGradeName() {
        return GradeName;
    }

    public void setGradeName(String gradeName) {
        GradeName = gradeName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getMemo() {
        return Memo;
    }

    public void setMemo(String memo) {
        Memo = memo;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getDayusePrice() {
        return DayusePrice;
    }

    public void setDayusePrice(int dayusePrice) {
        DayusePrice = dayusePrice;
    }

    public int getOvernightPrice() {
        return OvernightPrice;
    }

    public void setOvernightPrice(int overnightPrice) {
        OvernightPrice = overnightPrice;
    }

    public String getCheckIn() {
        return CheckIn;
    }

    public void setCheckIn(String checkIn) {
        CheckIn = checkIn;
    }

    public int getRentTime() {
        return RentTime;
    }

    public void setRentTime(int rentTime) {
        RentTime = rentTime;
    }

    public int getEmptyRoomCount() {
        return EmptyRoomCount;
    }

    public void setEmptyRoomCount(int emptyRoomCount) {
        EmptyRoomCount = emptyRoomCount;
    }
}
