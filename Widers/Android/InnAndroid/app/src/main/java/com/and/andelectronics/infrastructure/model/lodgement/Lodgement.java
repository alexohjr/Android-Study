package com.and.andelectronics.infrastructure.model.lodgement;

import java.io.Serializable;

/**
 * Created by 원성민 on 2017-06-09.
 */

public class Lodgement implements Serializable{
    private static final long serialVersionUID = 1L;

    public String UserId; //사용자 ID
    public String StoreCode; //매장코드
    public String StoreName; //매장명
    public String BusiNo; //시업자번호
    public String OwnName; //대표자
    public String TelNo; //전화번호
    public String PostNo; //우편번호
    public String Addr1; //주소1
    public String Addr2; //주소2
    public String UserName; //사용자명
    public String HpNo; //휴대폰
    public String Password; //암호
    public String AppFG; //승인여부

    public String PosiX; //경도
    public String PosiY; //위도

    public String Distance; //거리

    public String ItemCount; //방갯수

    public String NationalCode; //국가코드
    public String NationalName; //국가명

    public String ImagePath;    //대표이미지

    public int DayusePrice; //대실료
    public int OvernightPrice;  //숙박료

    public String CheckIn;  //체크인 시작시간
    public int RentTime;    //대실시간 rev test

    public Lodgement()
    {

    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getStoreCode() {
        return StoreCode;
    }

    public void setStoreCode(String storeCode) {
        StoreCode = storeCode;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public String getBusiNo() {
        return BusiNo;
    }

    public void setBusiNo(String busiNo) {
        BusiNo = busiNo;
    }

    public String getOwnName() {
        return OwnName;
    }

    public void setOwnName(String ownName) {
        OwnName = ownName;
    }

    public String getTelNo() {
        return TelNo;
    }

    public void setTelNo(String telNo) {
        TelNo = telNo;
    }

    public String getPostNo() {
        return PostNo;
    }

    public void setPostNo(String postNo) {
        PostNo = postNo;
    }

    public String getAddr1() {
        return Addr1;
    }

    public void setAddr1(String addr1) {
        Addr1 = addr1;
    }

    public String getAddr2() {
        return Addr2;
    }

    public void setAddr2(String addr2) {
        Addr2 = addr2;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getHpNo() {
        return HpNo;
    }

    public void setHpNo(String hpNo) {
        HpNo = hpNo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAppFG() {
        return AppFG;
    }

    public void setAppFG(String appFG) {
        AppFG = appFG;
    }

    public String getPosiX() {
        return PosiX;
    }

    public void setPosiX(String posiX) {
        PosiX = posiX;
    }

    public String getPosiY() {
        return PosiY;
    }

    public void setPosiY(String posiY) {
        PosiY = posiY;
    }

    public String getDistance() {
        return Distance;
    }

    public void setDistance(String distance) {
        Distance = distance;
    }

    public String getItemCount() {
        return ItemCount;
    }

    public void setItemCount(String itemCount) {
        ItemCount = itemCount;
    }

    public String getNationalCode() {
        return NationalCode;
    }

    public void setNationalCode(String nationalCode) {
        NationalCode = nationalCode;
    }

    public String getNationalName() {
        return NationalName;
    }

    public void setNationalName(String nationalName) {
        NationalName = nationalName;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
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
}
