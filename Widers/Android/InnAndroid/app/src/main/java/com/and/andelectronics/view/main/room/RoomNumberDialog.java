package com.and.andelectronics.view.main.room;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.and.andelectronics.R;
import com.and.andelectronics.common.util.LogUtil;
import com.and.andelectronics.infrastructure.model.lodgement.Room;
import com.and.andelectronics.view.main.roomservice.RoomServiceFragment;
import com.and.andelectronics.view.main.roomservice.RoomServiceGridAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 커스텀 다이얼 로그
 */
public class RoomNumberDialog extends DialogFragment implements View.OnClickListener {
    private static final String TAG = RoomNumberDialog.class.getSimpleName();
    public interface EZAlertDialogListener {
        void onClickConfirm();             // 확인 버튼 클릭 시 호출
        void onClickCancel();              // 취소 버튼 클릭 시 호출
        void onClickNumber(Room room);               //객실선택 시
    }

    // 리스너
    private EZAlertDialogListener _listener;


    @Bind(R.id.btn_dialog_prev)
    Button btn_dialog_prev;
    @Bind(R.id.btn_dialog_close)
    Button btn_dialog_close;

    // 모델
    private String _confirm; // 확인 버튼
    private String _cancel;  // 취소 버튼
    private ArrayList<Room> rooms;

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
    public static void show1(final FragmentManager fm, final ArrayList<Room> items, final EZAlertDialogListener listener, final String cancel, final String confirm) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {

                    RoomNumberDialog pd = new RoomNumberDialog();
                    pd.rooms = items;
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

    public static void show1(FragmentManager fm, ArrayList<Room> items, EZAlertDialogListener listener) {
        show1(fm, items, listener, null, null);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.dialog_room_number, container, false);

        ButterKnife.bind(this, rootView);

        LogUtil.d(TAG, "cancel->"+_cancel);
        LogUtil.d(TAG, "confirm->"+_confirm);

        // 버튼 이벤트 등록
        btn_dialog_prev.setOnClickListener(this);
        btn_dialog_close.setOnClickListener(this);

        GridView roomServiceGridView = (GridView)rootView.findViewById(R.id.roomNumberGridView);
        // 커스텀 아답타 생성
        RoomNumberGridAdapter adapter = new RoomNumberGridAdapter (getContext(),
                R.layout.item_room_number_grid,       // GridView 항목의 레이아웃 row.xml
                rooms);    // 데이터

        roomServiceGridView.setAdapter(adapter);  // 커스텀 아답타를 GridView 에 적용

        // GridView 아이템을 클릭하면 상단 텍스트뷰에 position 출력
        // JAVA8 에 등장한 lambda expression 으로 구현했습니다. 코드가 많이 간결해지네요
        roomServiceGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                _listener.onClickNumber(rooms.get(position));
                dismiss();
            }
        });

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
