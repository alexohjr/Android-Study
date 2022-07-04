package com.example.och.day23;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class MyView extends View implements View.OnTouchListener {

    // 컨텍스트
    Context context;

    // 뷰의 전체 크기
    int width, height;

    // 배경(우주) 이미지, 우주선 이미지
    Bitmap back1, back2;
    Bitmap unit;

    // 각 배경의 좌표값
    int back1_y, back2_y;

    // 우주선의 위치좌표값과 크기
    int unit_x, unit_y, unit_w, unit_h;

    // 화면을 그리는 Canvas
    Canvas canvas;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            invalidate(); // 화면 지우고 다시그리기

            // 반복
            sendEmptyMessageDelayed(0, 10);
        }
    };

    // 생성자
    public MyView(Context context) {
        super(context);
        this.context = context;

        // 현재 뷰의 크기 알아오기
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);

        // DiaplayMetrics : 디스플레이 화면의 일반적인 정보를 담을 수 있는 클래스
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm); // 해상도 정보를 dm 객체에 저장

        width = dm.widthPixels; // 너비(해상도 픽셀 단위)
        height = dm.heightPixels;

        // png --> Bitmap 객체로
        back1 = BitmapFactory.decodeResource(getResources(), R.drawable.space);
        back2 = BitmapFactory.decodeResource(getResources(), R.drawable.space);
        unit = BitmapFactory.decodeResource(getResources(), R.drawable.gunship);

        // 배경1의 y좌표와 배경2의 y좌표값 설정
        back1_y = 0;
        back2_y = -height;

        // 초기화 작업 (이미지 크기 설정 등
        init();

        // 핸들러 추가 (초기화 작업 후에 진행)
        handler.sendEmptyMessage(0);

        setOnTouchListener(this);
    } // 생성자

    private void init() {
        // 화면 해상도에 맞게 이미지 크기 조정
        back1 = Bitmap.createScaledBitmap(back1, width, height+10, false);
        back2 = Bitmap.createScaledBitmap(back2, width, height+10, false);

        // 우주선 이미지 크기 조정
        unit_w = width / 2 - unit_w / 2;
        unit_y = height - (unit_h + 300);
    } // init()

    // 캔버스에 이미지를 추가하는 메서드
    private void progressState() {
        canvas.drawBitmap(back1, 0 ,back1_y, null);
        canvas.drawBitmap(back2, 0, back2_y, null);
        canvas.drawBitmap(unit, unit_x, unit_y, null);

        scrollback(); // 배경 스크롤
    } // progressState()

    // 배경 이미지들이 천천히 내려가는 듯하게 y 좌표 변경
    private void scrollback() {
        back1_y += 5;
        back2_y += 5;

        // 화면을 벗어난 배경을 다시 화면 위로 이동
        if(back1_y >= height) {
            back1_y = -height;
        }
        if(back2_y >= height) {
            back2_y = -height;
        }
    } // scrollback()

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
        progressState(); // canvas변경
    }


    /*
        터치 이벤트가 발생 했을 때
               onTouch()          -->     ( onLongClick() )         -->      onClick()
        ( View.OnTouchListener )      ( View.OnLongClickListener )      ( View.OnClickListener )

     */

    // View.OnTouchListener
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        // 사용자가 클릭한 부분의 좌표값을 받아서 우주선을 그 자리에 그리기
        int px = (int)event.getX();
        int py = (int)event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moveUnit(px, py);
                return true;
            case MotionEvent.ACTION_MOVE:
                moveUnit(px, py);
                return true;
            case MotionEvent.ACTION_UP:
                moveUnit(px, py);
                return true;
        }
        return false;
    } // onTouch

    public void moveUnit(int px, int py) {
        unit_x = px - (unit.getWidth() / 2);
        unit_y = py - (unit.getHeight() / 2);
    } // moveUnit


    /*
    boolean check = false;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // 사용자가 클릭한 부분의 좌표값을 받아서 우주선을 그 자리에 그리기
        Log.d("MY", "onTouch() x : " + (int)event.getX() + ", y : " + (int)event.getY());
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            int touched_x = (int)event.getX();
            int touched_y = (int)event.getY();
            check = touched_y >= unit_x && touched_x <= unit_x + unit_w;
            if(check) {
                check = touched_y >= unit_y && touched_y <= unit_y + unit_h;
            }
            return true;
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE && check) {
            unit_x = (int)event.getX() - unit_w / 2;
            unit_y = (int)event.getY() - unit_h / 2;
        }
        return false;
    }
    */

}
