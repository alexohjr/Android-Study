package com.example.och.day22;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Ex02Thread extends Thread {
    Canvas canvas;
    Ex02SurfaceView surfaceView;
    SurfaceHolder holder;

    public Ex02Thread(SurfaceView surfaceView, SurfaceHolder holder) {
        this.holder = holder;
        this.surfaceView = (Ex02SurfaceView)surfaceView;
    }

    @Override
    public void run() {
        try {
            while(surfaceView.check) {
                Thread.sleep(50);

                canvas = holder.lockCanvas();
                canvas.drawColor(Color.WHITE);
                canvas.drawBitmap(surfaceView.bitmap, surfaceView.x, surfaceView.y, null);
                holder.unlockCanvasAndPost(canvas); // 캔버스를 비편집모드로 + view에 붙이기

                int x = surfaceView.x;
                int y = surfaceView.y;
                if(x < 0 || surfaceView.getWidth() - surfaceView.bitmap.getWidth() < x) {
                    surfaceView.speedX *= -1;
                }

                if(y < 0 || surfaceView.getHeight() - surfaceView.bitmap.getHeight() < y) {
                    surfaceView.speedY *= -1;
                }

                surfaceView.x += surfaceView.speedX;
                surfaceView.y += surfaceView.speedY;
            }
        } catch(Exception e) {
            e.printStackTrace();
        } // try-catch

    }

}
