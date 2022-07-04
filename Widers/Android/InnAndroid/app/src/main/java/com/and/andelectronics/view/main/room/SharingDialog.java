package com.and.andelectronics.view.main.room;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.and.andelectronics.R;
import com.and.andelectronics.common.util.LogUtil;
import com.and.andelectronics.infrastructure.model.lodgement.Room;
import com.and.andelectronics.view.main.enterance.EnterType;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 입실방법 선택 다이얼로그
 */
public class SharingDialog extends DialogFragment implements View.OnClickListener {
    private static final String TAG = SharingDialog.class.getSimpleName();
    public interface EZAlertDialogListener {
        void onClickConfirm();             // 확인 버튼 클릭 시 호출
        void onClickCancel();              // 취소 버튼 클릭 시 호출
    }

    // 리스너
    private EZAlertDialogListener _listener;


    @Bind(R.id.btn_dialog_close)
    Button btn_dialog_close;
    // 모델
    private String _confirm; // 확인 버튼
    private String _cancel;  // 취소 버튼

    public void setListener(EZAlertDialogListener listener) {
        _listener = listener;
    }



    public void setConfirm(String confirm) {
        this._confirm = confirm;
    }

    public void setCancel(String cancel) {
        this._cancel = cancel;
    }

    public static void show1(final FragmentManager fm, final EZAlertDialogListener listener, final String cancel, final String confirm) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {

                    SharingDialog pd = new SharingDialog();
                    pd.setListener(listener);
                    if (cancel == null && confirm == null) {
                        pd.setConfirm("확인");
                    } else {
                        pd.setConfirm(confirm);
                    }
                    pd.setCancel(cancel);
                    pd.setCancelable(false);
                    // pd.show(fm, null);
                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.add(pd, null);
                    transaction.commitAllowingStateLoss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void show1(FragmentManager fm,  EZAlertDialogListener listener) {
        show1(fm, listener, null, null);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.dialog_sharing, container, false);

        ButterKnife.bind(this, rootView);

        btn_dialog_close.setOnClickListener(this);
        LogUtil.d(TAG, "cancel->"+_cancel);
        LogUtil.d(TAG, "confirm->"+_confirm);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_prev:
                if (_listener != null) {
                    _listener.onClickCancel();
                }
                dismiss();
                break;
            case R.id.btn_dialog_close:
                if (_listener != null) {
                    _listener.onClickConfirm();
                }
                dismiss();
                break;

            default:
            {
                if (_listener != null) {
                    LogUtil.d(TAG, "clicked confirm with tag");
                    //_listener.onClickConfirm(v.getId());
                }
                dismiss();
                break;
            }
        }
    }

}
