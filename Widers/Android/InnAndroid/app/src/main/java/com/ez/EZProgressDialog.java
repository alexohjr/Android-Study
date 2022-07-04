package com.ez;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;


import com.and.andelectronics.R;
import com.and.andelectronics.common.util.LogUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 프로그래스 다이얼로그
 */
public class EZProgressDialog extends Dialog {
    private static final String TAG = EZProgressDialog.class.getSimpleName();
    @Bind(R.id.d_progress_msgTv)
    TextView _msgTv; // 메세지 표시 뷰
    /**
     * 콜백 인터페이스
     */

    private EZProgressDialogListener _listener; // 콜백 리스너

    /**
     * EZProgressDialog 생성자
     * @param context Context 객체
     */
    public EZProgressDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setContentView(R.layout.dialog_progress);
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        _msgTv.setVisibility(View.GONE);
    }

    /**
     * EZProgressDialog 생성자
     * @param context Context 객체
     * @param sec 타이머 시간 (1000 > 1초)
     */
    public EZProgressDialog(Context context, int sec) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setContentView(R.layout.dialog_progress);
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        _msgTv.setVisibility(View.GONE);
        createTimeoutTimer(sec);
    }

    /**
     * EZProgressDialog 생성자
     * @param context Context 객체
     * @param sec 타이머 시간 (1000 > 1초)
     * @param msg 보여질 메세지
     */
    public EZProgressDialog(Context context, int sec, String msg) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setContentView(R.layout.dialog_progress);
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        _msgTv.setText(msg);
        if (sec > 100) {
            createTimeoutTimer(sec);
        }
    }

    /**
     * EZProgressDialog 생성자
     * @param context Context 객체
     * @param sec 타이머 시간 (1000 > 1초)
     * @param msg 보여질 메세지
     * @param listener 다이얼로그가 사라질때 콜백 리스너
     */
    public EZProgressDialog(Context context, int sec, String msg, EZProgressDialogListener listener) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setContentView(R.layout.dialog_progress);
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        _msgTv.setText(msg);
        createTimeoutTimer(sec);
        _listener = listener;
    }

    @Override
    public void dismiss() {
        cancelTimeoutTimer();
        super.dismiss();
    }

    public void setMsg(String msg) {
        try {
            _msgTv.setText(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Timer _timer = null;   // 다이얼로그 타임아웃 타이머
    /**
     * 이전에 만들어논 타임 아웃 타이머를 삭제한다.
     */
    private void cancelTimeoutTimer() {
        if (_timer != null) {
            _timer.cancel();
            _timer = null;

            dismiss();
        }
    }

    /**
     * 타임 아웃 타이머를 생성한다.
     */
    private void createTimeoutTimer(int sec) {
        cancelTimeoutTimer();
        _timer = new Timer();
        _timer.schedule(new TimerTask() {
            @Override
            public void run() {
                _timer.cancel();
                _timer = null;
                try {
                    LogUtil.d(TAG, "dismiss progress with sec");
                    dismiss();
                    if (_listener != null){
                        _listener.onDismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, sec);
    }
}
