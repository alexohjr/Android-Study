package com.ez;


import com.and.andelectronics.Constants;

import java.util.HashMap;

/**
 * Created by hojunbaek on 03/11/2016.
 */

public class EZListUtil {
    /**
     *
     * @param title
     * @param value
     * @return
     */
    public static HashMap<String, String> makeRow(String title, String value) {
        HashMap<String, String> hm = new HashMap<>();
        hm.put(Constants.TITLE, title);
        hm.put(Constants.VALUE, value);
        return hm;
    }

    /**
     *
     * @param title
     * @param value
     * @param data
     * @return
     */
    public static HashMap<String, String> makeRowWithData(String title, String value, HashMap<String, String> data) {
        HashMap<String, String> hm = new HashMap<>();
        hm.put(Constants.TITLE, title);
        hm.put(Constants.VALUE, value);
        hm.putAll(data);
        return hm;
    }

}
