package com.and.andelectronics.common.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Base64;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Won on 2017-11-22.
 */

public class AppUtil {

    public static final String TAG = AppUtil.class.getSimpleName();
    /**
     * 디바이스의 IMEI 정보를 가져온다.
     * @return
     */
    public static String getIMEI(Context ctx) {
        TelephonyManager tm = (TelephonyManager)ctx.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * 디바이스의 전화번호 정보를 가져온다.
     * @return 디바이스의 전화번호
     */
    public String getPhoneNo(Context ctx) {
        TelephonyManager tm = (TelephonyManager)ctx.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getLine1Number();
    }

    /**
     * <pre>
     * 셀 위치 정보를 가져온다.
     * </pre>
     *
     * @return 기지국 셀 정보
     */
    public int getCellID(Context ctx) {
        TelephonyManager tm = (TelephonyManager)ctx.getSystemService(Context.TELEPHONY_SERVICE);
        GsmCellLocation location = (GsmCellLocation)tm.getCellLocation();
        return location.getCid();
    }

    /**
     * 디바이스의 시스템 버젼 정보를 가져온다.
     * @return 디바이스의 시스템 버젼
     */
    public String getSoftwareRevision(Context ctx) {
        String result = "\t<soft>\n";
        Runtime runtime = Runtime.getRuntime();

        try
        {
            Process proc = runtime.exec("cat /proc/version");
            int exit = proc.waitFor();
            if (exit == 0)
            {
                String content = getContent(proc.getInputStream());
                int index = content.indexOf(')');

                if (index >= 0)
                {
                    result += "\t\t<kernel>" + content.substring(0, index +1)
                            + "</kernel>\n";
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        result += "\t\t<buildNumber>" + Build.PRODUCT + Build.VERSION.RELEASE
                + "</buildNumber>\n";
        result += "\t</soft>\n";

        return result;
    }

    /**
     * 디바이스의 MAC Address 정보를 가져온다.
     * @param ctx
     * @return 맥 어드레스
     */
    public String getMacAddress(Context ctx) {
        WifiManager wifiManager = (WifiManager)ctx.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wfInfo = wifiManager.getConnectionInfo();
        String macAddress = wfInfo.getMacAddress();
        return macAddress;
    }

    /**
     * 디바이스의 하드웨어 버젼 정보를 가져온다.
     * @return 하드웨어 버젼
     */
    private String getHardwareRevision() {
        String result = "\t<hard>\n";
        Runtime runtime = Runtime.getRuntime();
        try
        {
            Process proc = runtime.exec("cat /proc/cpuinfo");
            int exit = proc.waitFor();

            if (exit == 0)
            {
                String content = getContent(proc.getInputStream());
                String [] lines = content.split("\n");
                String [] hInfo = {"Processor", "Hardware", "Revision"};

                if (lines != null)
                {
                    for (String line: lines)
                    {
                        for (String info: hInfo)
                        {
                            int index = line.indexOf(info);
                            if (index >= 0)
                            {
                                result += "\t\t<" + info.toLowerCase() + ">";
                                int vIndex = line.indexOf(':');
                                result += line.substring(vIndex + 1);
                                result += "\t\t</" + info.toLowerCase() + ">\n";
                            }
                        }
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        result += "\t</hard>\n";
        return result;
    }



    /**
     * <pre>
     * 입력스트림으로부터 정보를 읽어오는데 사용되는 유틸리티 메서드
     * </pre>
     *
     * @param input 입력스트림
     * @return 읽어들인 문자열
     * @throws IOException 예외 발생
     */
    public static String getContent(InputStream input) throws IOException {
        if (input == null)
        {
            return null;
        }

        byte [] b = new byte [1024];
        int readBytes = 0;
        String result = "";

        while ((readBytes = input.read(b)) >= 0)
        {
            result += new String(b, 0, readBytes);
        }

        return result;
    }

    public static String getSavePath() {
        String externalState = Environment.getExternalStorageState();
        String path;

        if (externalState.equals(Environment.MEDIA_MOUNTED)) {
            // 외부 저장 장치가 마운트 되어서 읽어올 준비가 되었을 때 (environment:컴퓨터,사용자환경)
            path = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            // 마운트 되지 않았을 때
            path = Environment.MEDIA_UNMOUNTED;
        }
        return path;
    }

    /**
     * 루팅이 여부 체크 메서드
     * @return true 루팅이 되어있음
     *         false 루팅이 되어 있지 않음
     */
    public static boolean isRootAccessed() {
        Process exec;
        try {
            exec = Runtime.getRuntime().exec(new String[]{"su","-c"});

            final OutputStreamWriter out = new OutputStreamWriter(exec.getOutputStream());
            out.write("exit");
            out.flush();

            //LogUtil.d(TAG, "su command executed successfully");
            return true; // returns zero when the command is executed successfully
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false; //returns one when the command execution fails
    }

    public static boolean isRootAccessed2() {
        String[] paths = { "/system/bin/su", "/system/xbin/su", "/system/app/Superuser.apk", "/system/app/su.apk", "/system/app/Spapasu.apk", "/data/data/com.noshufou.android.su"};
        for (String path : paths) {
            if (new File(path).exists()) return true;
        }
        return false;
    }


    /**
     * get wheter installed or not.
     * @param context Activity context
     * @param packageName package name like com.ezcocoa.app
     * @return
     */
    public static boolean isPackageInstalled(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * go to market that named.
     * @param context Activity context
     * @param packageName package name like com.ezcocoa.app
     */
    public static void gotoMarket(Context context, String packageName) {
        final String appPackageName = context.getPackageName(); // getPackageName() from Context or Activity object
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    /**
     * get the package installed.
     * @param ctx activity object.
     * @return if the package installed return true and nothing happend or not it will return false and move to market.
     */
    public static boolean isPackageInstalledIf(Context ctx, String packageName) {
        if (!AppUtil.isPackageInstalled(ctx, packageName)) {
            //EZToast.show(ctx, ctx.getString(R.string.no_application));
            //AppUtil.gotoMarket(activity, packageName);
            return false;
        }
        return true;
    }

    /**
     * 어플리케이션 버전 이름을 가져온다.
     * @param ctx 컨텍스트 객체
     * @return 버전 이름
     */
    public static String getVersionName(Context ctx) {
        try {
            PackageInfo pi = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }

    /**
     * 어플리케이션 버전 코드를 가져온다.
     * @param ctx 컨텍스트 객체
     * @return 버전 코드
     */
    public static String getVersionCode(Context ctx) {
        try {
            PackageInfo pi = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            return String.valueOf(pi.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }

    /**
     * 어플리케이션 정보를 가져온다.
     * @param packageName
     * @param context
     * @return
     */
    public static PackageInfo getAppInfo(String packageName, Context context) {
        try {
            PackageInfo p = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
            // p.applicationInfo.loadLabel(context.getPackageManager()).toString();
            // p.packageName;
            // p.versionName;
            // p.versionCode;
            // p.applicationInfo.loadIcon(context.getPackageManager());
            return p;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * APK 서명 가져오기
     * @param context 컨텍스트 객체
     * @return apk 서명 문자열
     */
    public static String getCert(Context context) {
        PackageManager pm = context.getPackageManager();
        String packageName = context.getPackageName();
        String cert = null;
        try {
            PackageInfo packageInfo = packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            Signature certSignature =  packageInfo.signatures[0];
            MessageDigest msgDigest = MessageDigest.getInstance("SHA1");
            msgDigest.update(certSignature.toByteArray());
            cert = Base64.encodeToString(msgDigest.digest(), Base64.DEFAULT);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return cert;
    }
}
