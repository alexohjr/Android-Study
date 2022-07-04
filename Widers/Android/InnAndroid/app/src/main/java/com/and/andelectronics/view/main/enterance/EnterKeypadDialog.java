package com.and.andelectronics.view.main.enterance;

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
import android.widget.EditText;
import android.widget.Toast;

import com.and.andelectronics.R;
import com.and.andelectronics.common.util.LogUtil;
import com.and.andelectronics.common.util.WidgetUtil;
import com.and.andelectronics.infrastructure.model.lodgement.Room;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 비밀번호 입력 키패드 다이얼로그
 */
public class EnterKeypadDialog extends DialogFragment implements View.OnClickListener {
    private static final String TAG = EnterKeypadDialog.class.getSimpleName();
    public interface EZAlertDialogListener {
        void onClickConfirm();             // 확인 버튼 클릭 시 호출
        void onClickCancel();              // 취소 버튼 클릭 시 호출
        void onSetPassword(String password);               //패스워드 입력 완료시
    }

    // 리스너
    private EZAlertDialogListener _listener;


    @Bind(R.id.btn_dialog_prev)
    Button btn_dialog_prev;
    @Bind(R.id.btn_dialog_close)
    Button btn_dialog_close;
    @Bind(R.id.passwordInputBox)
    EditText passwordInputBox;
    @Bind(R.id.btn_keypad1)
    Button btn_keypad1;
    @Bind(R.id.btn_keypad2)
    Button btn_keypad2;
    @Bind(R.id.btn_keypad3)
    Button btn_keypad3;
    @Bind(R.id.btn_keypad4)
    Button btn_keypad4;
    @Bind(R.id.btn_keypad5)
    Button btn_keypad5;
    @Bind(R.id.btn_keypad6)
    Button btn_keypad6;
    @Bind(R.id.btn_keypad7)
    Button btn_keypad7;
    @Bind(R.id.btn_keypad8)
    Button btn_keypad8;
    @Bind(R.id.btn_keypad9)
    Button btn_keypad9;
    @Bind(R.id.btn_keypad0)
    Button btn_keypad0;
    @Bind(R.id.btn_keypad_back)
    Button btn_keypad_back;
    @Bind(R.id.btn_keypad_deleteAll)
    Button btn_keypad_deleteAll;

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

//    public static Fragment newInstance(int position) {
//        RoomNumberDialog fragment = new RoomNumberDialog();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }
    public static void show1(final FragmentManager fm, final EZAlertDialogListener listener, final String cancel, final String confirm) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {

                    EnterKeypadDialog pd = new EnterKeypadDialog();
                    pd.setListener(listener);
                    if (cancel == null && confirm == null) {
                        pd.setConfirm("확인");
                    } else {
                        pd.setConfirm("확인");
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

    public static void show1(FragmentManager fm, EZAlertDialogListener listener) {
        show1(fm, listener, null, null);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.dialog_keypad, container, false);

        ButterKnife.bind(this, rootView);

        LogUtil.d(TAG, "cancel->"+_cancel);
        LogUtil.d(TAG, "confirm->"+_confirm);

        // 버튼 이벤트 등록
        btn_dialog_prev.setOnClickListener(this);
        btn_dialog_close.setOnClickListener(this);
        btn_keypad1.setOnClickListener(this);
        btn_keypad2.setOnClickListener(this);
        btn_keypad3.setOnClickListener(this);
        btn_keypad4.setOnClickListener(this);
        btn_keypad5.setOnClickListener(this);
        btn_keypad6.setOnClickListener(this);
        btn_keypad7.setOnClickListener(this);
        btn_keypad8.setOnClickListener(this);
        btn_keypad9.setOnClickListener(this);
        btn_keypad0.setOnClickListener(this);
        btn_keypad_back.setOnClickListener(this);
        btn_keypad_deleteAll.setOnClickListener(this);

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
                    if(passwordInputBox != null && passwordInputBox.getText().toString().length() == 6){
                        _listener.onSetPassword(passwordInputBox.getText().toString());
                    }else{
                        Toast.makeText(getContext(), "비밀번호 6자리를 입력해 주세요.",  Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                dismiss();
                break;

            case R.id.btn_keypad0:
            case R.id.btn_keypad1:
            case R.id.btn_keypad2:
            case R.id.btn_keypad3:
            case R.id.btn_keypad4:
            case R.id.btn_keypad5:
            case R.id.btn_keypad6:
            case R.id.btn_keypad7:
            case R.id.btn_keypad8:
            case R.id.btn_keypad9:
                onClickUnitNumber(v);
                break;

            case R.id.btn_keypad_back:
                onClickDelete(v);
                break;
            case R.id.btn_keypad_deleteAll:
                onClickDeleteAll(v);
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


    /**
     * 숫자 키패드 입력 시 호출
     * @param v 선택된 뷰
     */
    public void onClickUnitNumber(View v) {
        if(passwordInputBox.getText().toString().length() < 7){
            String number = WidgetUtil.val(v);
            passwordInputBox.append(number);
        }
    }

    /**
     * 삭제 버튼 클릭 시 호출
     * @param v 선택된 뷰
     */
    public void onClickDelete(View v) {
        if(passwordInputBox != null && passwordInputBox.getText().toString().length() > 0){
            passwordInputBox.setText(passwordInputBox.getText().toString().substring(0, passwordInputBox.getText().toString().length() -1));
        }
    }

    /**
     * 전체 삭제 버튼 클릭 시 호출
     * @param v 선택된 뷰
     */
    public void onClickDeleteAll(View v) {
        if(passwordInputBox != null && passwordInputBox.getText().toString().length() > 0){
            passwordInputBox.setText("");
        }
    }

}
