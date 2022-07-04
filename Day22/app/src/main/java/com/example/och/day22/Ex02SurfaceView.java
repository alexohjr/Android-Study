package com.example.och.day22;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Ex02SurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    // Ex02SurfaceView 관리하는 객체
    private SurfaceHolder surfaceHolder;
    private Thread thread;

    // 표현할 이미지 객체
    Bitmap bitmap;
    int x, y;
    int speedX = 10, speedY = 10; // 속도

    boolean check = true; // 쓰레드의 무한 while문의 종료 플래그

    public Ex02SurfaceView(Context context) {
        super(context);

        // 이미지 가져오기
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.naver);
        bitmap = MyUtil.resizeBitmap(bitmap, 2);

        // holder 구하기
        surfaceHolder = getHolder();

        // holder에게 this를 감지하라고 하기
        surfaceHolder.addCallback(this);

        // holder에게 이 View의 사이즈 전달하기
        surfaceHolder.setFixedSize(getWidth(), getHeight());

        // holder 작동 스레드
        thread = new Ex02Thread(this, surfaceHolder);
    } // Ex02SurfaceView

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        check = false; // 쓰레드 무한반복 종료
        thread.interrupt(); // 쓰레드 삭제 전 쓰레드 종료
        thread = null;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) { }
}
