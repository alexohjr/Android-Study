package com.and.andelectronics.infrastructure.model.lodgement;

import java.util.Hashtable;

/**
 * Created by Won on 2017-06-18.
 */
public class RoomRequestData {

    public String CompanyCode;
    public String Grade;
    private String RoomCode;
    private String Status;

    private String ReserveDate;
    private int ReserveSeq;

    private String reserveFromDate = "";
    private String reserveToDate = "";

    private String Action = "";
    private String gradeFrom = "";
    private String gradeTo = "";


    public RoomRequestData(){

        this.CompanyCode="";
    }
    public String getCompanyCode()
    {
        return this.CompanyCode;
    }
    public void setCompanyCode(String value)
    {
        this.CompanyCode = value;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getRoomCode() {
        return RoomCode;
    }

    public void setRoomCode(String roomCode) {
        RoomCode = roomCode;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }


    public int getReserveSeq() {
        return ReserveSeq;
    }

    public void setReserveSeq(int reserveSeq) {
        ReserveSeq = reserveSeq;
    }

    public String getReserveDate() {

        return ReserveDate;
    }

    public String getReserveFromDate() {
        return reserveFromDate;
    }

    public void setReserveFromDate(String reserveFromDate) {
        this.reserveFromDate = reserveFromDate;
    }

    public String getReserveToDate() {
        return reserveToDate;
    }

    public void setReserveToDate(String reserveToDate) {
        this.reserveToDate = reserveToDate;
    }

    public void setReserveDate(String reserveDate) {
        ReserveDate = reserveDate;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public String getGradeFrom() {
        return gradeFrom;
    }

    public void setGradeFrom(String gradeFrom) {
        this.gradeFrom = gradeFrom;
    }

    public String getGradeTo() {
        return gradeTo;
    }

    public void setGradeTo(String gradeTo) {
        this.gradeTo = gradeTo;
    }

    public Hashtable getRoomRequestData()
    {
        Hashtable hashtable = new Hashtable();
        hashtable.put("CompanyCode", this.getCompanyCode());
        hashtable.put("ReserveDate", this.getReserveDate());
        return hashtable;
    }
    public Hashtable getRoomEmptyRequestData()
    {
        Hashtable hashtable = new Hashtable();
        hashtable.put("Action", this.getAction());
        hashtable.put("CompanyCode", this.getCompanyCode());
        hashtable.put("GradeFrom", this.getGradeFrom());
        hashtable.put("GradeTo", this.getGradeTo());
        hashtable.put("ReserveFromDate", this.getReserveFromDate());
        hashtable.put("ReserveToDate", this.getReserveToDate());
        return hashtable;
    }

    public Hashtable getRoomReserveRequestData()
    {
        Hashtable hashtable = new Hashtable();
        hashtable.put("CompanyCode", this.getCompanyCode());
        hashtable.put("Grade", this.getGrade());
        hashtable.put("ReserveDate", this.getReserveDate());

        return hashtable;
    }

    public Hashtable getReserveTimeRequestData()
    {
        Hashtable hashtable = new Hashtable();
        hashtable.put("CompanyCode", this.getCompanyCode());
        hashtable.put("ReserveDate", this.getReserveDate());
        hashtable.put("RoomCode", this.getRoomCode());
        hashtable.put("Grade", this.getGrade());
        return hashtable;
    }

    public Hashtable getRoomReserveByRoomCodeRequestData()
    {
        Hashtable hashtable = new Hashtable();
        hashtable.put("CompanyCode", this.getCompanyCode());
        hashtable.put("Grade", this.getGrade());
        hashtable.put("RoomCode", this.getRoomCode());
        hashtable.put("Status", this.getStatus());
        hashtable.put("ReserveFromDate", this.getReserveFromDate());
        hashtable.put("ReserveToDate", this.getReserveToDate());
        return hashtable;
    }

    public Hashtable getHardwareRequestData()
    {
        Hashtable hashtable = new Hashtable();
        hashtable.put("CompanyCode", this.getCompanyCode());
        hashtable.put("RoomCode", this.getRoomCode());
        return hashtable;
    }

    public Hashtable getReserveCancelRequestData()
    {
        Hashtable hashtable = new Hashtable();
        hashtable.put("CompanyCode", this.getCompanyCode());
        hashtable.put("RoomCode", this.getRoomCode());
        hashtable.put("ReserveDate", this.getReserveDate());
        hashtable.put("ReserveSeq", this.getReserveSeq());

        return hashtable;
    }

}
