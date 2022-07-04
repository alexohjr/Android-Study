package com.example.och.day20;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class MyView extends View {

    // View 상속 시 필수 오버라이드
    // onDraw() :: View 가 생성 ==> 화면에 적용될 때 (setContentView 실행 시 호출)

    // 생성자
    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 붓 객체(Paint) 생성
        Paint paint = new Paint();

        // 붓에 파란색 적용
        paint.setColor(Color.BLUE);

        // 캔버스에 사각형 그리기 (paint 객체 사용)
        canvas.drawRect(100, 200,500, 400, paint);

        // 캔버스에 원 그리기 (paint 객체 사용)
        canvas.drawCircle(200, 600, 100, paint);

        // paint에 빨간색 적용
        paint.setColor(Color.RED);

        paint.setStyle(Paint.Style.STROKE);

        canvas.drawRect(200, 100, 300, 200, paint);

        Rect rect = new Rect();
        rect.set(300, 400, 400, 500);
        canvas.drawRect(rect, paint);
    }
}
