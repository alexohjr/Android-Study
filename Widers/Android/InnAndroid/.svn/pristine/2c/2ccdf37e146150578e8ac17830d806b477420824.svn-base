package com.and.andelectronics.view.main;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.and.andelectronics.R;
import com.and.andelectronics.common.ez.EZAlertDialog;
import com.and.andelectronics.common.util.AppUtil;
import com.and.andelectronics.test.TestViewActivity;

/**
 * Created by Won on 2016-01-02.
 */
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        reqCert();
    }

    private void showSplash(){
        Handler startHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                finish();
                startActivity(new Intent(SplashActivity.this, MainTabFragmentActivity.class));
                finish();
                //startActivity(new Intent(SplashActivity.this, TestViewActivity.class));
            };
        };
        startHandler.sendEmptyMessageDelayed(0, 1000); // 1초딜레이
    }
    public void reqCert() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    // Android M Permission get
                    if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                            checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                            checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                            checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED //UDID가져오기 위해 추가
                            ) {
                        EZAlertDialog.show1(getSupportFragmentManager(), "어플리케이션을 사용하기 위해서는 위치 정보 접근, 파일 생성 권한이 필요합니다.", new EZAlertDialog.EZAlertDialogListener() {
                            @Override
                            public void onClickConfirm(int selected) {
                            }

                            @Override
                            public void onClickConfirm() {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    requestPermissions(new String[] {
                                            Manifest.permission.ACCESS_COARSE_LOCATION,
                                            Manifest.permission.ACCESS_FINE_LOCATION,
                                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                            Manifest.permission.READ_EXTERNAL_STORAGE,
                                            Manifest.permission.READ_PHONE_STATE,   //UDID가져오기 위해 추가
                                    }, REQ_PERMISSION_COARSE_LOCATION_AND_WRITE_EXTERNAL_STORAGE);
                                }
                            }

                            @Override
                            public void onClickCancel() {
                            }
                        });
                    } else {
                        showSplash();
                        }
                    }
                    else{
                    showSplash();
                }
                }

        });
    }

    private static final int REQ_PERMISSION_COARSE_LOCATION_AND_WRITE_EXTERNAL_STORAGE = 1;

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQ_PERMISSION_COARSE_LOCATION_AND_WRITE_EXTERNAL_STORAGE: {
                if (permissions.length > 0) {
                    for (int i=0; i<permissions.length; i++) {
                        if (grantResults.length <= i) {
                            return;
                        }

                        boolean r = grantResults[i] == PackageManager.PERMISSION_GRANTED;
                        String s = "";
                        if (permissions[i].equals(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                            s = "위치 접근 권한이 핍요합니다.";
                        } else if (permissions[i].equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                            s = "위치 접근 권한이 핍요합니다.";
                        } else if (permissions[i].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            s = "파일 접근 권한이 필요합니다.";
                        } else if (permissions[i].equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            s = "파일 접근 권한이 필요합니다.";
                        }else if(permissions[i].equals(Manifest.permission.READ_PHONE_STATE)){
                            s = "전화 접근 권한이 필요합니다.";
                        }

                        if (r == false) {
                            EZAlertDialog.show1(getSupportFragmentManager(), s + "\n앱을 다시 시작해주세요.", new EZAlertDialog.EZAlertDialogListener() {
                                @Override
                                public void onClickConfirm(int selected) {

                                }

                                @Override
                                public void onClickConfirm() {
                                    finish();
                                }

                                @Override
                                public void onClickCancel() {

                                }
                            });
                            return;
                        }
                    }

                    showSplash();

                } else {
                    EZAlertDialog.show1(getSupportFragmentManager(), "잘못된 접근입니다.", new EZAlertDialog.EZAlertDialogListener() {
                        @Override
                        public void onClickConfirm(int selected) {

                        }

                        @Override
                        public void onClickConfirm() {
                            finish();
                        }

                        @Override
                        public void onClickCancel() {

                        }
                    });
                }
                return;
            }
        }
    }
}
