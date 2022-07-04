package com.and.andelectronics.infrastructure.model;

/**
 * Created by Won on 2016-06-02.
 */
public class Theme {
    private String themeName;
    private String themeCode;
    private String themeImg;

    public Theme(String themeName, String themeCode, String themeImg) {
        this.themeImg = themeImg;
        this.themeName = themeName;
        this.themeCode = themeCode;
    }

    public Theme() {
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getThemeCode() {
        return themeCode;
    }

    public void setThemeCode(String themeCode) {
        this.themeCode = themeCode;
    }

    public String getThemeImg() {
        return themeImg;
    }

    public void setThemeImg(String themeImg) {
        this.themeImg = themeImg;
    }
}
