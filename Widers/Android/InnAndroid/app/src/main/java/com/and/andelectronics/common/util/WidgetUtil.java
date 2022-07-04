package com.and.andelectronics.common.util;

import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by hojunbaek on 5/20/16.
 */
public class WidgetUtil {
    public static final String TAG = WidgetUtil.class.getSimpleName();

    /**
     * 타입 체크 후 입력된 데이터를 가져온다.
     * @param v widget 객체
     * @return widget에 입력된 문자열 데이터
     */
    public static String val(Object v) {
        if (v == null) {
            return "";
        } else if (v instanceof EditText) {
            EditText et = (EditText)v;
            return et.getText().toString().trim();

        } else if (v instanceof TextView) {
            TextView tv = (TextView)v;
            return tv.getText().toString().trim();
        } else {
            return "";
        }
    }

    /**
     *
     * @param v
     * @return
     */
    public static boolean isNotEmpty(Object v) {
        if (v == null) {
            return false;
        } else if (v instanceof EditText) {
            EditText et = (EditText)v;
            if ("".equals(et.getText().toString().trim())) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
