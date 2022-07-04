package com.example.och.day22;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;

/*
    < SurfaceView >
    - View의 자식클래스
    - 일반 View는 onDraw()가 실행될 때 화면이 구성
        -> 단점 : 자주 onDraw()가 실행되면 시스템 속도 저하
        ( 동영상, 애니메이션 처리, 캐릭터 이동 처리 등 )
    - SurfaceView는 Thread를 사용하여 미리 어떤화면을 그릴 지 정해두고
      원하는 시점에 Thread로 정해놨던 화면을 바로 띄움
    - SurfaceHolder가 Surface에 미리 화면을 그리고 이 Surface가 SurfaceView에 반영됨
    - SurfaceView 상속 받을 경우 필수 오버라이드 메서드
        - public void onDraw(Canvas canvas) : 화면을 그림 (=> 대체 draw() )
        - public void surfaceChanged() : 뷰가 변경될 때
        - public void surfaceCreated() : 뷰가 생성될 때
        - public void surfaceDestroyed() : 뷰가 종료될 때
 */

public class Ex01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Ex02SurfaceView(this));
    }

}
