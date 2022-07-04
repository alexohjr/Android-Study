package com.example.och.day22;

import android.graphics.Bitmap;

public class MyUtil {

    public static Bitmap resizeBitmap(Bitmap original, double ratio) {
        int resizedHeight = (int)(original.getHeight() * ratio);
        int resizedWidth = (int)(original.getWidth() * ratio);

        Bitmap result = Bitmap.createScaledBitmap(original, resizedWidth, resizedHeight, false);
        if(result != original) {
            original.recycle();
        }
        return result;
    }

}
