package com.ez;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.and.andelectronics.Container;
import com.and.andelectronics.PauseHandler;
import com.and.andelectronics.R;
import com.and.andelectronics.common.util.LogUtil;
import com.and.andelectronics.common.util.ScreenUtil;


/**
 * Activity 최상위 클래스
 *
 * 공통적으로 애니메이션을 넣거나 할때 사용된다.
 *
 * created by hojun baek
 */
public class EZActivity extends AppCompatActivity {
    public static final String TAG = EZActivity.class.getSimpleName();

    public static final int MESSAGE_READ = 2;           // 데이터 읽어드림

    public void startActivityWitoutAni(Intent i) {
        super.startActivity(i);
    }
    public void finishWithoutAni() {
        super.finish();
    }
    private static boolean _isAllFinish = false;
    public void setFinishAll() {
        _isAllFinish = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            final Fragment state = new State();
            final FragmentManager fm = getSupportFragmentManager();
            final FragmentTransaction ft = fm.beginTransaction();
            ft.add(state, State.TAG);
            ft.commit();
        }

        Container.getInstance().setCurrentActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public void startActivity(Intent i) {
        super.startActivity(i);
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     *
     * @param i
     */
    public void startActivityWithFade(Intent i) {
        super.startActivity(i);
        // overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out);
    }

    /**
     *
     */
    public void finishWithFade() {
        super.finish();
        // overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out);
    }

    private EZProgressDialog _dialog;
    // =============================================================================================
    // progress methods
    // =============================================================================================

    /**
     * 다이얼 프로그래스바 생성
     */
    public void showProgress() {
        try {
            // Create and show the dialog.
            if (_dialog == null) {
                _dialog = new EZProgressDialog(this);
            }
            _dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 다이얼 프로그래스바 생성
     * @param sec 타이머 시간 (1000 > 1초)
     */
    public void showProgress(int sec) {
        try {
            // Create and show the dialog.
            if (_dialog == null) {
                _dialog = new EZProgressDialog(this, sec);
            }
            _dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 다이얼 프로그래스바 생성
     * @param sec 타이머 시간 (1000 > 1초)
     * @param msg 보여질 메세지
     */
    public void showProgress(final int sec, final String msg) {
        try {
            LogUtil.d(TAG, "show progress with sec->" + sec);
            // Create and show the dialog.
            if (_dialog == null) {
                _dialog = new EZProgressDialog(EZActivity.this, sec, msg);
            } else {
                _dialog.setMsg(msg);
            }
            _dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 다이얼 프로그래스바 생성
     * @param sec 타이머 시간 (1000 > 1초)
     * @param msg 보여질 메세지
     * @param listener 다이얼로그가 사라질때 콜백 리스너
     */
    public void showProgress(int sec, String msg, EZProgressDialogListener listener) {
        try {
            // Create and show the dialog.
            if (_dialog == null) {
                _dialog = new EZProgressDialog(this, sec, msg, listener);
            }
            _dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 다이얼 프로그래스바 없앤다.
     */
    public void dismissProgress() {
        try {
            if (_dialog != null) {
                _dialog.dismiss();
                LogUtil.d(TAG, "dismiss dialog.");
            } else {
                LogUtil.d(TAG, "dismiss failed dialog is null.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 프로그래스바가 활성화 되었는지 여부를 확인한다.
     * @return
     */
    public boolean isProgressShowing() {
        try {
            if (_dialog == null) return false;
            else return _dialog.isShowing();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 키보드를 보여준다.
     * @param viewGroup 키보드가 속한 뷰
     */
    public void showKeyboards(ViewGroup viewGroup) {
        if (viewGroup == null) viewGroup = (ViewGroup)getWindow().getDecorView().findViewById(android.R.id.content);
        for (int i=0; i<viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof ViewGroup)
                showKeyboards((ViewGroup)view);
            else if (view instanceof EditText) {
                showKeyboard((EditText)view);
            }
        }
    }

    /**
     * 키보드 보여줌
     * @param et
     */
    public static void showKeyboard(final EditText et) {
        et.requestFocus();
        InputMethodManager imm = (InputMethodManager)et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(et, 0);
    }

    /**
     * 키보드 숨김
     * @param et
     */
    public static void hideKeyboard(final EditText et) {
        InputMethodManager imm = (InputMethodManager)et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }


    /**
     * 리스트 뷰에 추가 할 수 있는 공백 뷰를 생성한다.
     * @param listView 리스트 뷰
     */
    public void addSpaceToListView(ListView listView) {
        if (listView.getHeaderViewsCount() <= 0) {
            // 상/하 공백 추가
            View spaceView = new View(this);
            spaceView.setLayoutParams(new AbsListView.LayoutParams(1, 3));
            listView.addHeaderView(spaceView, null, false);
            listView.addFooterView(spaceView, null, false);
        }
    }


    /**
     * 좌측 상단 백 버튼을 눌렀을 때 호출
     * @param v
     */
    public void onClickBack(View v) {
        finish();
    }

    /**
     * 빈공간 선택 시 호출 됨
     * @param v
     */
    public void onClickEmpty(View v) {
    }

    /**
     * 우측 상단 메뉴 버튼을 눌렀을 때 호출
     * @param v
     */
    public void onClickMenu(View v) {
    }


    /**
     * 종료 팝업
     * @param msg 표시할 메시지
     */
    public void showQuitDialog(String msg) {
        EZAlertDialog.show1(getSupportFragmentManager(), msg, new EZAlertDialog.EZAlertDialogListener() {
            @Override
            public void onClickConfirm(int selected) {

            }

            @Override
            public void onClickConfirm() {
                finish();
            }

            @Override
            public void onClickCancel() {

            }
        });
    }

    /**
     * 다이얼로그를 보여준다.
     * @param msg 보여질 메시지
     */
    public void showDialog(String msg) {
        showDialog(msg, null);
    }

    /**
     * 다이얼로그를 보여준다.
     * @param msg 보여질 메세지
     * @param listener 콜백 메서드
     */
    public void showDialog(String msg, EZAlertDialog.EZAlertDialogListener listener) {
        showDialog(msg, listener, null, null);
    }

    public void showDialog(String msg, EZAlertDialog.EZAlertDialogListener listener, String cancel, String confirm) {
        ConcreateMessage o = new ConcreateMessage(msg, listener, cancel, confirm);
        final FragmentManager fm = getSupportFragmentManager();
        State fragment = (State) fm.findFragmentByTag(State.TAG);
        if (fragment != null) {
            // Send a message with a delay onto the message looper
            fragment.handler.sendMessageDelayed(fragment.handler.obtainMessage(MSG_WHAT, MSG_SHOW_DIALOG, value++, o),
                    DELAY);
        }
    }

    // http://stackoverflow.com/questions/8040280/how-to-handle-handler-messages-when-activity-fragment-is-paused

    /**
     * Used for "what" parameter to handler messages
     */
    final static int MSG_WHAT = ('F' << 16) + ('T' << 8) + 'A';
    final static int MSG_SHOW_DIALOG = 1;

    int value = 1;

    @SuppressLint("ValidFragment")
    final static class State extends Fragment {

        static final String TAG = "State";
        /**
         * Handler for this activity
         */
        public ConcreteHandler handler = new ConcreteHandler();

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }

        @Override
        public void onResume() {
            super.onResume();

            handler.setActivity(getActivity());
            handler.resume();
        }

        @Override
        public void onPause() {
            super.onPause();

            handler.pause();
        }

        public void onDestroy() {
            super.onDestroy();
            handler.setActivity(null);
        }
    }

    /**
     * 2 second delay
     */
    final static int DELAY = 100;

    public static class ConcreateMessage {
        String message;
        EZAlertDialog.EZAlertDialogListener listener;
        String cancel;
        String confirm;
        public ConcreateMessage(String message, EZAlertDialog.EZAlertDialogListener listener) {
            this.message = message;
            this.listener = listener;
            this.cancel = null;
            this.confirm = null;
        }

        public ConcreateMessage(String message, EZAlertDialog.EZAlertDialogListener listener, String cancel, String confirm) {
            this.message = message;
            this.listener = listener;
            this.cancel = cancel;
            this.confirm = confirm;
        }
    }

    /**
     * Message Handler class that supports buffering up of messages when the
     * activity is paused i.e. in the background.
     */
    static class ConcreteHandler extends PauseHandler {

        /**
         * Activity instance
         */
        protected FragmentActivity activity;

        /**
         * Set the activity associated with the handler
         *
         * @param activity
         *            the activity to set
         */
        final void setActivity(FragmentActivity activity) {
            this.activity = activity;
        }

        @Override
        final protected boolean storeMessage(Message message) {
            // All messages are stored by default
            return true;
        };

        @Override
        final protected void processMessage(Message msg) {

            final FragmentActivity activity = this.activity;
            if (activity != null) {
                switch (msg.what) {

                    case MSG_WHAT:
                        switch (msg.arg1) {
                            case MSG_SHOW_DIALOG:
                                // We are on the UI thread so display the dialog
                                final FragmentManager fm = activity.getSupportFragmentManager();
                                ConcreateMessage obj = (ConcreateMessage) msg.obj;
                                if (obj.listener != null) {
                                    LogUtil.d(TAG, "=========================");
                                    LogUtil.d(TAG, "listener is not null");
                                    EZAlertDialog.show1(fm, obj.message, obj.listener, obj.cancel, obj.confirm);
                                } else {
                                    LogUtil.d(TAG, "=========================");
                                    LogUtil.d(TAG, "listener is null");
                                    EZAlertDialog.show1(fm, obj.message);
                                }
                                break;
                        }
                        break;
                }
            }
        }
    }

    /**
     * 커스텀 액션바 오른쪽 버튼을 추가한다.
     * @param res 이미지 리소스
     * @param listener 콜백 메서드
     */
    public void setCustomActionBarButton(@DrawableRes int res, @NonNull View.OnClickListener listener) {
        if (_actionBar != null) {
            ImageView iv = (ImageView) _actionBar.findViewById(R.id.v_action_bar_rightIv);
            iv.setImageResource(res);
            iv.setVisibility(View.VISIBLE);
            iv.setOnClickListener(listener);
        }
    }

    public void setCustomActionBarTitleStyle(@StyleRes int style) {
        if (_actionBar != null) {
            TextView titleTv = (TextView) _actionBar.findViewById(R.id.v_action_bar_titleTv);
            titleTv.setTextAppearance(this, style);
        }
    }

    @NonNull
    public String getActionBarTitle() {
        if (_actionBar != null) {
            TextView titleTv = (TextView) _actionBar.findViewById(R.id.v_action_bar_titleTv);
            return titleTv.getText().toString();
        }
        return "";
    }

    @Nullable
    private RelativeLayout _actionBar = null;
    public void setCustomActionBar(@NonNull String title, boolean hasRightBtn) {
        if (_actionBar != null) {

            View emptyView = _actionBar.findViewById(R.id.v_action_bar_emptyView);
            if (hasRightBtn) {
                emptyView.setVisibility(View.GONE);
            } else {
                emptyView.setVisibility(View.VISIBLE);
            }

            TextView titleTv = (TextView) _actionBar.findViewById(R.id.v_action_bar_titleTv);
            titleTv.setText(title);
            return;
        }

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        LayoutInflater in = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout rl = (RelativeLayout) in.inflate(R.layout.view_action_bar, null, false);
        TextView titleTv = (TextView) rl.findViewById(R.id.v_action_bar_titleTv);
        View emptyView = rl.findViewById(R.id.v_action_bar_emptyView);
        if (hasRightBtn) {
            emptyView.setVisibility(View.GONE);
        } else {
            emptyView.setVisibility(View.VISIBLE);
        }
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        getSupportActionBar().setCustomView(rl, params);
        // 커스텀 타이틀 뷰
        if (!title.equals(getString(R.string.app_name))) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } else {
            titleTv.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.title_color));
        }
        getSupportActionBar().setElevation(0);

        titleTv.setText(title);
        if (title.equals(getString(R.string.app_name))) {
            titleTv.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_launcher, 0, 0, 0);
            titleTv.setCompoundDrawablePadding((int) ScreenUtil.convertDpToPixel(getApplicationContext(), 8));

            View leftView = rl.findViewById(R.id.v_action_bar_leftEmptyView);
            leftView.setVisibility(View.VISIBLE);
        }
        //.커스텀 타이틀 뷰
        _actionBar = rl;
    }
    public void setCustomActionBar(@NonNull String title) {
        setCustomActionBar(title, false);
    }
    public void setCustomActionBar(@StringRes int res) {
        setCustomActionBar(res, false);
    }

    public void setCustomActionBar(@StringRes int res, boolean hasRightBtn) {
        setCustomActionBar(getResources().getString(res), hasRightBtn);
    }
}

