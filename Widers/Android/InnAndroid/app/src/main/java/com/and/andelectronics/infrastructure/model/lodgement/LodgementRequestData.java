package com.and.andelectronics.infrastructure.model.lodgement;

import java.util.Hashtable;

/**
 * Created by Won on 2017-06-18.
 */
public class LodgementRequestData {

    public String PosiX;
    public String PosiY;
    public String Distance;
    private String CompType;
    private String CompanyCode;

    public LodgementRequestData() {
        this.PosiX = "";
        this.PosiY = "";
        this.Distance = "";
        this.CompType = "";
        this.CompanyCode = "";

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

    public String getCompType() {
        return CompType;
    }

    public void setCompType(String compType) {
        CompType = compType;
    }

    public String getCompanyCode() {
        return CompanyCode;
    }

    public void setCompanyCode(String companyCode) {
        CompanyCode = companyCode;
    }

    public Hashtable getLodgementRequestData() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("CompanyCode", this.getCompanyCode());
        hashtable.put("CompType", this.getCompType());
        hashtable.put("PosiX", this.getPosiX());
        hashtable.put("PosiY", this.getPosiY());
        hashtable.put("Distance", this.getDistance());

        return hashtable;
    }

    public Hashtable getLodgementByTypeRequestData() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("PosiX", this.getPosiX());
        hashtable.put("PosiY", this.getPosiY());
        hashtable.put("CompType", this.getCompType());

        return hashtable;
    }
}
