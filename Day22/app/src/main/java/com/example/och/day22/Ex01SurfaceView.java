package com.example.och.day22;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Ex01SurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    /*
        < Callback 패턴 >
        - 호출자 메서드에 의해서 피호출자 메서드가 실행되는 것이 아닌,
          호출자 메서드의 상태가 변경되면 알아서 피호출자 메서드가 실행되는 것
     */

    int img_x; // 이미지의 x좌표
    Ex01Thread thread; // Ex01SurfaceView를 제어할 쓰레드

    public Ex01SurfaceView(Context context) {
        super(context);

        // 뷰의 움직임을 제어하고 속성을 정의하기 위해 Holder 객체를 사용한다.
        // => getHolder()
        getHolder().addCallback(this);
        // 상속 받은 SurfaceHolder 내부 클래스 Callback에게
        // 현재 클래스에 발생하는 변경사항들을 감지할 수 있도록 이 객체의 레퍼런스를 전달

        // holder에게 이 객체의 너비와 높이 정보를 전달한다.
        getHolder().setFixedSize(getWidth(), getHeight());

        // 쓰레드 생성
        thread = new Ex01Thread(this, getHolder()); // SurfaceView 관리 시작
    }

    @Override
    protected void onDraw(Canvas canvas) { // holder가 실행
        super.onDraw(canvas);
        drawImg(canvas);
    }

    @Override
    public void draw(Canvas canvas) { // Ex01Thread에서 호출
        super.draw(canvas);
        drawImg(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Ex01SurfaceView가 생성될 때 SurfaceView.Callback에 의해 호출될 것임
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Ex01SurfaceView가 변경될 때 SurfaceView.Callback에 의해 호출될 것임
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // Ex01SurfaceView가 소멸될 때 SurfaceView.Callback에 의해 호출될 것임
        thread = null; // thread 객체 삭제
    }

    private void drawImg(Canvas canvas) {
        // 이미지(drawable)를 객체화 하여 canvas에 붙이기
         canvas.drawColor(Color.WHITE);
         Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.naver);
         bitmap = MyUtil.resizeBitmap(bitmap, 0.2);
         // => Drawable 자원 => Bitmap 객체로 변환

         canvas.drawBitmap(bitmap, img_x, 100, null);
         // ( canvas에 띄울 bitmap객체, x좌표(왼쪽부터의 거리), y좌표(위쪽부터의 거리), 그릴 때 사용할  paint객체 )
    }

     public int getImg_x() {
        return img_x;
     }

     public void setImg_x(int x) {
        img_x = x;
     }
}
