package com.example.och.day22;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class Ex01Thread extends Thread {

    // 이 쓰레드가 제어할 SurfaceView
    private Ex01SurfaceView surfaceView;

    // SurfaceHolder ( Ex01SurfaceView의 onDraw()를 실행할 때 사용할 canvas를 얻어 낼 때 사용 )
    private SurfaceHolder surfaceHolder;

    // 생성자
    public Ex01Thread(Ex01SurfaceView surfaceView, SurfaceHolder surfaceHolder) {
        this.surfaceView = surfaceView;
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void run() {
        // Ex01SurfaceView의 x값을 변경한 후
        // Ex01SurfaceView의 onDraw() 실행

        // Canvas 얻어오기

        Canvas canvas = null;

        try {
            while(true) {
                try {
                    Thread.sleep(50);

                    // 이미지의 x값이 화면을 벗어나면 while문 종료
                    if(surfaceView.getImg_x() >= surfaceView.getWidth()) {
                        break;
                    }

                    // x값을 조금씩 변경 ( 이미지 직진 )
                    int x = surfaceView.getImg_x() + 5;
                    surfaceView.setImg_x(x);
                    canvas = surfaceHolder.lockCanvas();
                    // SurfaceHolder의 lockCanvas() : canvas를 편집모드로 바꾸고 그 canvas를 리턴

                    // 캔버스 바탕 색 설정
                    // canvas.drawColor(Color.WHITE)
                    // 이 설정을 안하면 canvas는 투명이기 때문에
                    // 이미지가 이동할 때 마다 이미지가 겹쳐서 보임

                    surfaceView.draw(canvas);
                    // onDraw() 사용 시 에러발생
                    // 이유 : onDraw는 Holder나 부모 액티비티 등에서 자동으로 호출하는 용도임
                    // 개발자가 onDraw()를 직접 호출하는 것은 바랍직하지 않음
                } catch(Exception e) {
                    // Thread.sleep()에서 예외 발생했을 경우
                    Log.e("MY", e.getMessage());
                }

                // Canvas를 Holder에 적용하고 비편집모드로 변경
                surfaceHolder.unlockCanvasAndPost(canvas);

            } // while

        } catch(Exception e) {
            // Surface가 생성되기 전에 이 while()문이 먼저 실행됐을 경우 앱 종료 방지
            Log.e("MY", e.getMessage());
        }
    } // while
}
