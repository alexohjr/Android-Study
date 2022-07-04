package com.example.pc.day35;

import android.graphics.Bitmap;

public interface MyCallback {

    String CLIENT_ID = "vieoKOQCjtwEsYNxbfpS";
    String CLIENT_SECRET = "dWAW4Tpg6W";

    // Bitmap 객체를 받아 이를 imageView에 띄움
    void setCaptchaImage(Bitmap bitmap);

    // result값을 받아 이를 Toast로 띄움
    void setCaptchaResult(boolean result);

    // 구현 : Ex01Activity
    // 호출 : AsyncTask
}
