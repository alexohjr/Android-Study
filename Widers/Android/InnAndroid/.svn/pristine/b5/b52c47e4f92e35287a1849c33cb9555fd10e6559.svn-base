package com.and.andelectronics.common.util;

import android.annotation.SuppressLint;
import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Won on 2017-07-20.
 */
public class DateUtil {
    public String getCurrentDateTime() {
        return changeLongDateTimeFormat(gurrentDateTime());
    }
    public String getCurrentShortDateTime()
    {
        return changeShortDateTimeFormat(gurrentDateTime());
    }
    public String getCurrentShortDate()
    {
        return changeShortDateFormat(gurrentDateTime());
    }
    public String getCurrentLongDate()
    {
        return changeLongDateFormat(gurrentDateTime());
    }

    public Date gurrentDateTime() {
        Date date = new Date();
        return date;
    }

    public static Date getDate(String dateString){
        Date date = null;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        try {
            date=  format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getDateTime(String dateString){
        Date date = null;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            date=  format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public String changeLongDateTimeFormat(Date date) {
        SimpleDateFormat mSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.KOREA);
        String sTime = mSDF.format(date);
        return sTime;
    }
    public String changeLongDateTimeFormat (String dateString){
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
        try {
            java.util.Date date = format.parse(dateString);
            return changeLongDateTimeFormat(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "0";
        }
    }
    public String changeLongDateFormat (String dateString){
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyyMMdd");
        try {
            java.util.Date date = format.parse(dateString);
            return changeLongDateFormat(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "0";
        }
    }

    public String changeShortDateFormat(Date date) {
        SimpleDateFormat mSDF = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        String sTime = mSDF.format(date);
        return sTime;
    }

    public String changeLongDateFormat(Date date)
    {
        SimpleDateFormat mSDF = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        String sTime = mSDF.format(date);
        return sTime;

    }

    public String changeShortDateTimeFormat(Date date)
    {
        SimpleDateFormat mSDF = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
        String sTime = mSDF.format(date);
        return sTime;
    }

    public static int getYear(String dateString){
        //java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date = format.parse(dateString);
            SimpleDateFormat CurYearFormat = new SimpleDateFormat("yyyy");
            return Integer.valueOf(CurYearFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
            try{
                Date currnetDateTime = new Date();
                SimpleDateFormat CurYearFormat = new SimpleDateFormat("yyyy");
                return Integer.valueOf(CurYearFormat.format(currnetDateTime));
            }
            catch(Exception ex)
            {

                ex.printStackTrace();
                return 0;
            }
        }
    }

    public static int getMonth(String dateString){
        //java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date = format.parse(dateString);
            SimpleDateFormat CurFormat = new SimpleDateFormat("MM");
            return Integer.valueOf(CurFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
            try{
                Date currnetDateTime = new Date();
                SimpleDateFormat CurFormat = new SimpleDateFormat("MM");
                return Integer.valueOf(CurFormat.format(currnetDateTime));
            }
            catch(Exception ex)
            {

                ex.printStackTrace();
                return 0;
            }
        }
    }

    public static int getDay(String dateString){
        //java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date = format.parse(dateString);
            SimpleDateFormat CurFormat = new SimpleDateFormat("dd");
            return Integer.valueOf(CurFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
            try{
                Date currnetDateTime = new Date();
                SimpleDateFormat CurFormat = new SimpleDateFormat("dd");
                return Integer.valueOf(CurFormat.format(currnetDateTime));
            }
            catch(Exception ex)
            {

                ex.printStackTrace();
                return 0;
            }
        }
    }

    public static int getHour(String dateString){
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            java.util.Date date = format.parse(dateString);
            SimpleDateFormat CurFormat = new SimpleDateFormat("HH");
            return Integer.valueOf(CurFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
            try{
                Date currnetDateTime = new Date();
                SimpleDateFormat CurFormat = new SimpleDateFormat("HH");
                return Integer.valueOf(CurFormat.format(currnetDateTime));
            }
            catch(Exception ex)
            {

                ex.printStackTrace();
                return 0;
            }
        }
    }

    public static int getMinute(String dateString){
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            java.util.Date date = format.parse(dateString);
            SimpleDateFormat CurFormat = new SimpleDateFormat("mm");
            return Integer.valueOf(CurFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
            try{
                Date currnetDateTime = new Date();
                SimpleDateFormat CurFormat = new SimpleDateFormat("mm");
                return Integer.valueOf(CurFormat.format(currnetDateTime));
            }
            catch(Exception ex)
            {

                ex.printStackTrace();
                return 0;
            }
        }
    }
    public static String nowDateTime(){

        SimpleDateFormat mSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
        Date date = new Date();
        String sDateTime = mSDF.format(date);
        return sDateTime;

    }
    public static String tomorrowDateTime(){
        SimpleDateFormat mSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date date = calendar.getTime();
        String sDateTime = mSDF.format(date);
        return sDateTime;

    }

    public static String addDateTime(Date date, int hours){
        SimpleDateFormat mSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        Date addDate = calendar.getTime();
        String sDateTime = mSDF.format(addDate);
        return sDateTime;
    }

    public static String nowDate(){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int  month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        //Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        String retSTr = getFormat(0).format(c.getTime());

        return retSTr;

    }

    public static String nowDateFormat(String format) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        c.set(year, month, day);
        String retSTr = getStrFormat(format).format(c.getTime());

        return retSTr;
    }


    public static String getDateString(int year, int month, int day){
        Calendar c = Calendar.getInstance();
//        int year = c.get(Calendar.YEAR);
//        int  month = c.get(Calendar.MONTH);
//        int day = c.get(Calendar.DAY_OF_MONTH);
        //Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        String retSTr = getFormat(0).format(c.getTime());

        return retSTr;

    }

    public static String toString(Calendar date) {

        return date.get(Calendar.YEAR)+"-"
                + (date.get(Calendar.MONTH)+1) + "-"
                + date.get(Calendar.DATE);
    }

    public static String getYM(){
        return nowDate().substring(0, 7);
    }

    /**
     *
     * @param paramInt: 1=MM-dd, 2=yyyy-MM, 0(other)=yyyy-MM-dd
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static SimpleDateFormat getFormat(int paramInt) {
        if (paramInt == 1)
            return new SimpleDateFormat("MM-dd");
        if (paramInt == 2)
            return new SimpleDateFormat("yyyy-MM");
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    @SuppressLint("SimpleDateFormat")
    public static SimpleDateFormat getStrFormat(String format) {
        return new SimpleDateFormat(format);
    }

    public static long getSecond(String time)
            throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("hh:mm");
        Date _time = sf.parse(time, new ParsePosition(0));
        return _time.getTime();
    }
}