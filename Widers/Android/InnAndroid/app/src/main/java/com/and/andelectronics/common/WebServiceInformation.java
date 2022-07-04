package com.and.andelectronics.common;

/**
 * Created by Won on 2016-01-16.
 */
public class WebServiceInformation{

    //private final static String WEB_SERVER_URL = "http://192.168.10.5:9999/nike/";
    //private final static String WEB_SERVER_URL = "http://localhost:9999/lodgement/";
    //private final static String WEB_SERVER_URL = "http://192.168.10.201:1947/LodgementWcfService.asmx";  //회사컴터

    private final static String WEB_SERVER_URL = "http://115.68.41.244:30001/LodgementWcfService.asmx";  //RealServer

    private final static String IMAGE_DOWNLOAD_URL = "http://115.68.41.244:30001/Item/Image/";  //RealServer



    //서비스 URL
    public static String getUrl(){
        return WEB_SERVER_URL;
    }

    //이미지 다운로드 URL
    public static String getImageDownloadUrl(){
        return IMAGE_DOWNLOAD_URL;
    }
}
