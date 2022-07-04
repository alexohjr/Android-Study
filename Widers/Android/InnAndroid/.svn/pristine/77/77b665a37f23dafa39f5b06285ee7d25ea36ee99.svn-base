package com.and.andelectronics.common.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.provider.Settings;

import com.and.andelectronics.R;
import com.and.andelectronics.common.LocationService;

/**
 * Created by Won on 2016-04-12.
 * GPS 설정 얼러트
 */
public class GPSSettingAlert {

    public static void showGpsSettingsAlert(final Context context) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setTitle(R.string.comm_location_turnOn);
        alertDialog.setMessage(R.string.comm_location_turnOnMessage);

        // OK 를 누르게 되면 설정창으로 이동합니다.
        alertDialog.setPositiveButton(R.string.comm_ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        context.startActivity(intent);
                    }
                });
        // Cancle 하면 종료 합니다.
        alertDialog.setNegativeButton(R.string.comm_cancel,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }
}
