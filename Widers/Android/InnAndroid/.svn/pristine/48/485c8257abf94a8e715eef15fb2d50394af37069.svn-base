package com.ez;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.Toast;

/**
 * created by hojun baek
 * on November 29 1026
 *
 * 커스터마이징 토스트 클래스
 */
public class EZToast {

    /**
     * show toast message with str
     * @param msg message
     */
    @NonNull
    public static void show(@NonNull final Context ctx, @NonNull final String msg) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast t = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
            }
        });
    }

    /**
     * show toast message with res str
     * @param ctx 컨텍스트 객체
     * @param res 문자열 리소스
     *
     *            토스트 메시지를 보여준다.
     */
    @NonNull
    public static void show(@NonNull final Context ctx, @StringRes final int res) {
        show(ctx, ctx.getString(res));
    }
}
