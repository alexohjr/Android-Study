package com.and.andelectronics.common.util;

import android.content.Context;

/**
 * Created by Won on 2016-04-13.
 */
public class PreferenceUtil extends BasePreferenceUtil {
    private static PreferenceUtil _instance = null;

    private static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private static final String PROPERTY_MY_ADDRESS = "my_address";

    public static synchronized PreferenceUtil instance(Context $context) {
        if (_instance == null)
            _instance = new PreferenceUtil($context);
        return _instance;
    }

    public PreferenceUtil(Context $context) {
        super($context);
    }

    public void putRedId(String $regId) {
        put(PROPERTY_REG_ID, $regId);
    }

    public String regId() {
        return get(PROPERTY_REG_ID);
    }

    public void putAppVersion(int $appVersion) {
        put(PROPERTY_APP_VERSION, $appVersion);
    }

    public int appVersion() {
        return get(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
    }

    public void putMyAddress(String $address) {
        put(PROPERTY_MY_ADDRESS, $address);
    }

    public String getMyAddress(){
        return get(PROPERTY_MY_ADDRESS);
    }

}