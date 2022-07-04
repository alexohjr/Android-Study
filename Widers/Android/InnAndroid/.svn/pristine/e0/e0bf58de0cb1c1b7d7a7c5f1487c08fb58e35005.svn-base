package com.ez;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;


import com.and.andelectronics.R;
import com.and.andelectronics.common.util.LogUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 커스텀 다이얼 로그
 */
public class EZInputDialog extends DialogFragment implements View.OnClickListener {

    private static final String TAG = EZInputDialog.class.getSimpleName();

    public interface InputDialogListener {
        void onClickConfirm(String text);  // 확인 버튼 클릭 시 호출
        void onClickCancel();              // 취소 버튼 클릭 시 호출
    }

    // 리스너
    private InputDialogListener _listener;
    // 뷰
    @Bind(R.id.d_input_messageTv)
    TextView _messageTv;
    @Bind(R.id.d_input_inputEt)
    EditText _inputEt;

    // 모델
    private String _message; // 메시지 (Null이면 메시지 창을 숨김)
    private int _inputType;
    private String _orgValue;
    private InputFilter[] _filters;
    private int _minLen = -1;
    private int _maxLen = -1;
    public void setMinLen(int minLen) { _minLen = minLen; }
    public void setMaxLen(int maxLen) { _maxLen = maxLen; }


    public void setListener(InputDialogListener listener) {
        _listener = listener;
    }

    public void setMessage(String message) {
        this._message = message;
    }
    public void setInputType(int inputType) {
        _inputType = inputType;
    }

    public void setOrgValue(String v) {
        _orgValue = v;
    }

    public void setInputFilters(InputFilter[] filters) {
        _filters = filters;
    }

    public static void show1(FragmentManager fm, String message, int inputType, InputFilter[] filters, InputDialogListener listener, String orgValue, int minLen, int maxLen) {
        EZInputDialog pd = new EZInputDialog();
        pd.setMessage(message);
        pd.setListener(listener);
        pd.setCancelable(false);
        pd.setInputType(inputType);
        pd.setInputFilters(filters);
        pd.setOrgValue(orgValue);
        pd.show(fm, null);
        pd.setMinLen(minLen);
        pd.setMaxLen(maxLen);
    }


    public static void show1(FragmentManager fm, String message, int inputType, InputFilter[] filters, InputDialogListener listener, String orgValue) {
        EZInputDialog pd = new EZInputDialog();
        pd.setMessage(message);
        pd.setListener(listener);
        pd.setCancelable(false);
        pd.setInputType(inputType);
        pd.setInputFilters(filters);
        pd.setOrgValue(orgValue);
        pd.show(fm, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.dialog_input, container, false);

        ButterKnife.bind(this, rootView);

        LogUtil.d(TAG, "org value->"+_orgValue);

        _inputEt.setText(_orgValue);

        // 뷰 등록
        _inputEt.setInputType(_inputType);
        if (_filters != null)
            _inputEt.setFilters(_filters);


        // 버튼 등록
        rootView.findViewById(R.id.d_input_cancelBtn).setOnClickListener(this);
        rootView.findViewById(R.id.d_input_confirmBtn).setOnClickListener(this);

        // 메시지
        if (_message != null) {
            _messageTv.setText(_message);
        } else {
            _messageTv.setVisibility(View.GONE);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                _inputEt.requestFocus();
                InputMethodManager imm = (InputMethodManager) _inputEt.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(_inputEt, 0);
            }
        }, 300);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.d_input_cancelBtn:
                if (_listener != null) {
                    _listener.onClickCancel();
                }
                dismiss();
                break;
            case R.id.d_input_confirmBtn:
                if (_listener != null) {
                    String text = _inputEt.getText().toString();
                    if (_minLen != -1 && _maxLen != -1) {
                        if (text.length() >= _minLen &&
                                text.length() <= _maxLen) {
                            _listener.onClickConfirm(text);
                            dismiss();
                            return;
                        } else {
                            EZToast.show(getContext(), "길이 "+_minLen+"-"+_maxLen+"로 입력해주세요.");
                            return;
                        }
                    } else {
                        _listener.onClickConfirm(text);
                    }
                    /*
                    for (int i=0; i<_filters.length; i++) {
                        InputFilter f = _filters[i];
                        if (f instanceof InputFilterMinMax) {
                            InputFilterMinMax ifm = ((InputFilterMinMax) f);
                            if (text.length() >= ifm.min &&
                                    text.length() <= ifm.max) {
                                _listener.onClickConfirm(text);
                                return;
                            } else {
                                EZToast.show(getContext(), "길이 "+ifm.min+"-"+ifm.max+"로 입력해주세요.");
                                return;
                            }

                        }
                    }
                    */

                }
                dismiss();
                break;
        }
    }
}
