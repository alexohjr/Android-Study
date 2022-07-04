package com.example.och.day20;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

public class MyView2 extends View {

    private static Paint paint = new Paint();

    // Path : 여러가지 그리기 명령을 모았다가 한번에 화면에 출력하는 버퍼 역할의 클래스
    private Path path = new Path();

    // 사용자가 클릭한 위치
    int x, y;

    // 생성자
    public MyView2(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);

        // Path 객체가 가지고 있는 경로를 화면에 그린다
        canvas.drawPath(path, paint);
    }

    // 이 뷰(MyView2)가 클릭 되었을 때 발생
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 클릭된 지점의 좌표 구하기
        x = (int)event.getX();
        y = (int)event.getY();

        switch(event.getAction()) {
            // 화면이 눌렸을 때
            case MotionEvent.ACTION_DOWN:
                // 현재 눌린 지점을 path에 저장
                path.moveTo(x, y);
                break;

            case MotionEvent.ACTION_MOVE:
                x = (int)event.getX();
                y = (int)event.getY();

                // 시작점 x,y 까지 그릴 라인을 path에 저장
                path.lineTo(x, y);
                break;
        }

        // View의 invalidate() : 이전의 View를 무효화 + onDraw() 재호출
        invalidate();

        return true;
    }

    public Paint getPaint() {
        return paint;
    }
}
