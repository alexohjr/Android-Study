package com.and.andelectronics.infrastructure.model.lodgement;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * Created by 원성민 on 2017-11-03.
 */

public class ReserveRequestData implements Serializable{
///톄톄톄톄
    private String CompanyCode;
    private String ReserveDate;
    private String ReserveTermSt;
    private String ReserveTermEt;
    private String ReserveRoom;
    private int ReservePerson;
    private int ReserveFeeAmt;
    private int ReserveFeeAdd;
    private int ReserveFeeRefund;
    private int ReserveFeeRecv;
    private String ReserveMember;
    private String ReservePhone;
    private String ReserveStatus;
    private String ReserveUser;
    private String ReserveMemo;
    private String Status;
    private String ReservePwd;
    private String ReserveMethod;
    private String ReserveNfcSerial;
    private String ReserveUdid;


    public Hashtable getReserveEnteranceRequestData()
    {
        Hashtable hashtable = new Hashtable();
        hashtable.put("CompanyCode", this.getCompanyCode());
        hashtable.put("ReserveDate", this.getReserveDate());
        hashtable.put("ReserveTermSt", this.getReserveTermSt());
        hashtable.put("ReserveTermEt", this.getReserveTermEt());
        hashtable.put("ReserveRoom", this.getReserveRoom());
        hashtable.put("ReservePerson", this.getReservePerson());
        hashtable.put("ReserveFeeAmt", this.getReserveFeeAmt());
        hashtable.put("ReserveFeeAdd", this.getReserveFeeAdd());
        hashtable.put("ReserveFeeRefund", this.getReserveFeeRefund());
        hashtable.put("ReserveFeeRecv", this.getReserveFeeRecv());
        hashtable.put("ReserveMember", this.getReserveMember());
        hashtable.put("ReservePhone", this.getReservePhone());
        hashtable.put("ReserveStatus", this.getReserveStatus());
        hashtable.put("ReserveUser", this.getReserveUser());
        hashtable.put("ReserveMemo", this.getReserveMemo());
        hashtable.put("Status", this.getStatus());
        hashtable.put("ReservePwd", this.getReservePwd());
        hashtable.put("ReserveMethod", this.getReserveMethod());
        hashtable.put("ReserveNfcSerial", this.getReserveNfcSerial());
        hashtable.put("ReserveUdid", this.getReserveUdid());
        return hashtable;
    }

    public Hashtable getInquireReserveRequestData()
    {
        Hashtable hashtable = new Hashtable();
        hashtable.put("Date", this.getReserveDate());
        hashtable.put("Udid", this.getReserveUdid());
        return hashtable;
    }

    public Hashtable getInquireCheckoutNoticeRequestData()
    {
        Hashtable hashtable = new Hashtable();
        hashtable.put("Date", this.getReserveDate());
        hashtable.put("Udid", this.getReserveUdid());
        hashtable.put("CompanyCode", this.getCompanyCode());
        hashtable.put("RoomCode", this.getReserveRoom());
        return hashtable;
    }

    public String getCompanyCode() {
        return CompanyCode;
    }

    public void setCompanyCode(String companyCode) {
        CompanyCode = companyCode;
    }

    public String getReserveDate() {
        return ReserveDate;
    }

    public void setReserveDate(String reserveDate) {
        ReserveDate = reserveDate;
    }

    public String getReserveTermSt() {
        return ReserveTermSt;
    }

    public void setReserveTermSt(String reserveTermSt) {
        ReserveTermSt = reserveTermSt;
    }

    public String getReserveTermEt() {
        return ReserveTermEt;
    }

    public void setReserveTermEt(String reserveTermEt) {
        ReserveTermEt = reserveTermEt;
    }

    public String getReserveRoom() {
        return ReserveRoom;
    }

    public void setReserveRoom(String reserveRoom) {
        ReserveRoom = reserveRoom;
    }

    public int getReservePerson() {
        return ReservePerson;
    }

    public void setReservePerson(int reservePerson) {
        ReservePerson = reservePerson;
    }

    public int getReserveFeeAmt() {
        return ReserveFeeAmt;
    }

    public void setReserveFeeAmt(int reserveFeeAmt) {
        ReserveFeeAmt = reserveFeeAmt;
    }

    public int getReserveFeeAdd() {
        return ReserveFeeAdd;
    }

    public void setReserveFeeAdd(int reserveFeeAdd) {
        ReserveFeeAdd = reserveFeeAdd;
    }

    public int getReserveFeeRefund() {
        return ReserveFeeRefund;
    }

    public void setReserveFeeRefund(int reserveFeeRefund) {
        ReserveFeeRefund = reserveFeeRefund;
    }

    public int getReserveFeeRecv() {
        return ReserveFeeRecv;
    }

    public void setReserveFeeRecv(int reserveFeeRecv) {
        ReserveFeeRecv = reserveFeeRecv;
    }

    public String getReserveMember() {
        return ReserveMember;
    }

    public void setReserveMember(String reserveMember) {
        ReserveMember = reserveMember;
    }

    public String getReservePhone() {
        return ReservePhone;
    }

    public void setReservePhone(String reservePhone) {
        ReservePhone = reservePhone;
    }

    public String getReserveStatus() {
        return ReserveStatus;
    }

    public void setReserveStatus(String reserveStatus) {
        ReserveStatus = reserveStatus;
    }

    public String getReserveUser() {
        return ReserveUser;
    }

    public void setReserveUser(String reserveUser) {
        ReserveUser = reserveUser;
    }

    public String getReserveMemo() {
        return ReserveMemo;
    }

    public void setReserveMemo(String reserveMemo) {
        ReserveMemo = reserveMemo;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getReservePwd() {
        return ReservePwd;
    }

    public void setReservePwd(String reservePwd) {
        ReservePwd = reservePwd;
    }

    public String getReserveMethod() {
        return ReserveMethod;
    }

    public void setReserveMethod(String reserveMethod) {
        ReserveMethod = reserveMethod;
    }

    public String getReserveNfcSerial() {
        return ReserveNfcSerial;
    }

    public void setReserveNfcSerial(String reserveNfcSerial) {
        ReserveNfcSerial = reserveNfcSerial;
    }

    public String getReserveUdid() {
        return ReserveUdid;
    }

    public void setReserveUdid(String reserveUdid) {
        ReserveUdid = reserveUdid;
    }
}
