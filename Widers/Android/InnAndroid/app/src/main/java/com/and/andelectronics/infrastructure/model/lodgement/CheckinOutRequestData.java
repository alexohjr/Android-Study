package com.and.andelectronics.infrastructure.model.lodgement;

import java.util.Hashtable;

/**
 * Created by 원성민 on 2017-11-23.
 */

public class CheckinOutRequestData {
    private String CompanyCode ="";
    private String RoomCode="";
    private String SaleDate="";

    private String CheckinDate="";
    private String CheckoutDate="";
    private String Status="";


    public Hashtable getCheckinOutRequestData()
    {
        Hashtable hashtable = new Hashtable();
        hashtable.put("CompanyCode", this.getCompanyCode());
        hashtable.put("RoomCode", this.getRoomCode());
        hashtable.put("SaleDate", this.getSaleDate());
        hashtable.put("CheckinDate", this.getCheckinDate());
        hashtable.put("CheckoutDate", this.getCheckoutDate());
        hashtable.put("Status", this.getStatus());

        return hashtable;
    }

    public String getCompanyCode() {
        return CompanyCode;
    }

    public void setCompanyCode(String companyCode) {
        CompanyCode = companyCode;
    }

    public String getRoomCode() {
        return RoomCode;
    }

    public void setRoomCode(String roomCode) {
        RoomCode = roomCode;
    }

    public String getSaleDate() {
        return SaleDate;
    }

    public void setSaleDate(String saleDate) {
        SaleDate = saleDate;
    }

    public String getCheckinDate() {
        return CheckinDate;
    }

    public void setCheckinDate(String checkinDate) {
        CheckinDate = checkinDate;
    }

    public String getCheckoutDate() {
        return CheckoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        CheckoutDate = checkoutDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
