package com.example.och.day21;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class MyView extends View {
    final int BLANK = 0; // 대기 상태
    final int PLAY = 1; // 게임 진행 중
    final int DELAY = 1500; // 도형 생성 시간 ( 1.5 초 )

    int status; // 현재 상태 (BLANK, PLAY)

    Activity mParent;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            addNewShape();
            status = PLAY;
            invalidate();
            String title = "STAGE : " + shapeList.size();
            mParent.setTitle(title);
        }
    }; // handler
    ArrayList<MyShape> shapeList = new ArrayList<>();

    // 생성자
    public MyView(Context context) {
        super(context);
        mParent = (Activity) context;
        status = BLANK;
        handler.sendEmptyMessageDelayed(0, DELAY);
    } // MyView2

    // MyShape ( 생성될 도형의 모양, 색상 등을 위한 내부 클래스 )
    class MyShape {
        final static int RECT = 0;
        final static int CIRCLE = 1;
        final static int TRIANGLE = 2;

        int shape; // RECT, CIRCLE, TRIANGLE 셋 중 하나
        int color; // 도형 색상
        Rect rect;
    } // MyShape

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 캔버스 전체 (바탕) 색 : 검정
        canvas.drawColor(Color.BLACK);
        if (status == BLANK) {
            return; // 현재 상태가 대기상태라면 DRAW 종료
        }

        for (int i = 0; i < shapeList.size(); i++) {
            Paint paint = new Paint();
            paint.setColor(shapeList.get(i).color);
            Rect rt = shapeList.get(i).rect;

            switch (shapeList.get(i).shape) {
                case MyShape.RECT:
                    canvas.drawRect(rt, paint);
                    break;

                case MyShape.CIRCLE:
                    canvas.drawCircle(
                            (float) (rt.left + rt.width() / 2.0), // 원 중심점의 x 위치
                            (float) (rt.top + rt.height() / 2.0), // 원 중심점의 y 위치
                            (float) (rt.width() / 2.0), // 반지름
                            paint
                    );
                    break;

                case MyShape.TRIANGLE:
                    Path path = new Path();
                    path.moveTo((float) (rt.left + rt.width() / 2.0), (float) rt.top);
                    path.lineTo(rt.left, rt.bottom);
                    path.lineTo(rt.right, rt.bottom);
                    canvas.drawPath(path, paint);
                    break;
            } // switch
        } // for
    } // onDraw

    // 새로운 MyShape 객체를 만들고, arrayList에 저장
    private void addNewShape() {
        MyShape myShape = new MyShape();
        Rect rt = new Rect();
        Random r = new Random();

        outer:
        while (true) {
            // 랜덤으로 도형 사이즈 생성하기 (50~150)

            int size = r.nextInt(101) + 50; // (0~100) + 50

            // 사각형의 범위(좌표)
            rt.left = r.nextInt(getWidth()); // MyView 화면의 getWidth() 중 아무 위치
            rt.top = r.nextInt(getHeight()); // MyView 화면의 getHeight() 중 아무위치
            rt.right = rt.left + size;
            rt.bottom = rt.top + size;

            // 화면을 벗어날 경우
            if (rt.right >= getWidth() || rt.bottom >= getHeight()) {
                continue; // while문 처음으로(조건식으로 돌아가기)
            }

            for (int i = 0; i < shapeList.size(); i++) {
                if (rt.intersect(shapeList.get(i).rect)) {
                    /*
                        Rect의 intersect ( Rect 다른 사각형 )
                        => 이 객체와 다른사각형이 좌표상에서 겹치는 지 확인 (true/false)
                     */

                    continue outer; // while문 처음으로(조건식으로) 돌아가기 ==> 다시뽑기
                } // if
            } // for

            // 위 과정에서 문제가 없을 경우에만 while문 종료
            // 도형 1개를 뽑더라도 문제가 있으면 다시 뽑아야 하기 때문에
            // 이를 while문으로 처리함
            break;
        } // while

        // 도형 모양 ( 0 ~ 2 )
        myShape.shape = r.nextInt(3); // 0 ~ 2

        // 도형 색상
        int red = r.nextInt(256); // 0~255
        int green = r.nextInt(256);
        int blue = r.nextInt(256);
        myShape.color = Color.rgb(red, green, blue);
        myShape.rect = rt;
        shapeList.add(myShape);
    } // addNewShape

    // MyView 의 터치 이벤트 리스너
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int sel = 0;

            // 도형의 위치를 제대로 클릭했는 지 판별하는 메서드 호출
            sel = findShapeIndex((int) event.getX(), (int) event.getY());

            // MotionEvent의 getX(), getY() => 사용자가 터치한 위치 좌표 얻어옴
            if (sel == -1) {
                // 리스너 종료 후 리스너 다시 실행 안함
                return true;
            }

            if (sel == shapeList.size() - 1) {
                status = BLANK;
                invalidate(); // 뷰 클리어하고 다시 그림
                handler.sendEmptyMessageDelayed(0, DELAY);
            } else {
                // 게임 종료를 위한 다이얼로그 실행
                AlertDialog.Builder builder = new AlertDialog.Builder(mParent);
                builder.setMessage("RESTART GAME ?")
                        .setTitle("GAME OVER")
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 게임 초기화 + 재실행
                                shapeList.clear(); // 도형 모두 삭제
                                status = BLANK;
                                invalidate();
                                handler.sendEmptyMessageDelayed(0, DELAY);
                            }
                        })
                        .setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 액티비티 종료
                                mParent.finish();
                            }
                        })
                        .setCancelable(false)
                        .show();
            }
        }
        return false;
    } // onTouchEvent

    // 사용자가 터치한 좌표 받아서 해당 도형의 index를 리턴
    // 도형이 아닌 부분을 터치했을 경우 -1 을 리턴
    int findShapeIndex(int x, int y) {
        for (int i = 0; i < shapeList.size(); i++) {
            if (shapeList.get(i).rect.contains(x, y)) {
                /*
                    Rect 의 contains(x, y)
                    => 사각형 객체 안에 x, y 좌표가 들어있으면 true
                 */
                return i;
            }
        }
        return -1;
    }
}