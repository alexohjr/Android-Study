package com.example.och.day16;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class MyThread extends Thread {

    private Handler handler;
    // check 가 true 일 동안에는 프로그래스바를 계속 실행
    private boolean check = true;

    MyThread(Handler h) {
        handler = h;
    }

    void setCheck(boolean b) {
        check = b;
    }

    @Override
    public void run() {
        int value = 0;
        while(check) {

            try {
                Thread.sleep(300);
            } catch(Exception e) {
                e.printStackTrace();
            }

            // 핸들러에게 전달한 message는 그 핸들러에게 받아와야 함
            Message msg = handler.obtainMessage();

            // Message 객체에게 정보를 저장시킬 때는 Bundle형태로만 저장 가능
            Bundle bundle = new Bundle();
            bundle.putInt("value", value);

            // Bundle을 message에 담기
            msg.setData(bundle);

            // msg를 handler에게 전달
            handler.sendMessage(msg);

            value++;
        }
    }

}
