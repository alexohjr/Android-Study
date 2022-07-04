package com.and.andelectronics.infrastructure.model;

/**
 * Created by Won on 2016-04-23.
 */
public class AreaItem {

    private String id;
    private String areaCity; //Province,
    private String district;
    private String dong;

    public AreaItem(String id, String areaCity, String district, String dong) {
        this.id = id;
        this.areaCity = areaCity;
        this.district = district;
        this.dong = dong;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAreaCity() {
        return areaCity;
    }
    public void setAreaCity(String areaCity) {
        this.areaCity = areaCity;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getDong() {
        return dong;
    }
    public void setDong(String dong) {
        this.dong = dong;
    }
}
