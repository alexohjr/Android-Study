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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.and.andelectronics.R;
import com.and.andelectronics.common.util.LogUtil;
import com.and.andelectronics.infrastructure.model.lodgement.ReserveTime;
import com.and.andelectronics.infrastructure.model.lodgement.Room;
import com.ez.EZToast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 커스텀 다이얼 로그
 */
public class ReserveTimeDialog extends DialogFragment implements View.OnClickListener {
    private static final String TAG = ReserveTimeDialog.class.getSimpleName();
    public interface EZAlertDialogListener {
        void onClickConfirm();             // 확인 버튼 클릭 시 호출
        void onClickCancel();              // 취소 버튼 클릭 시 호출
        void onSelectedTime(ReserveTime fromTime, ReserveTime toTime);  //예약시간 선택
        void onSelectedTimeList(ArrayList<ReserveTime> selectedTimeList);
    }

    // 리스너
    private EZAlertDialogListener _listener;


    //@Bind(R.id.btn_dialog_prev)
    //Button btn_dialog_prev;
    @Bind(R.id.btn_dialog_close)
    Button btn_dialog_close;

    ReserveTimeGridAdapter reserveTimeGridAdapter;
    // 모델
    private String _confirm; // 확인 버튼
    private String _cancel;  // 취소 버튼
    private ArrayList<ReserveTime> reserveTimes;
    private int rentTime;       // 대실시간

    private ReserveTime fromTime;
    private ReserveTime toTime;


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
    public static void show1(final FragmentManager fm, final ArrayList<ReserveTime> items, final int rentTime, final EZAlertDialogListener listener, final String cancel, final String confirm) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {

                    ReserveTimeDialog pd = new ReserveTimeDialog();
                    pd.reserveTimes = items;
                    pd.rentTime = rentTime - 1; //시간 맞추기 위해
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

    public static void show1(FragmentManager fm, ArrayList<ReserveTime> items, int rentTime, EZAlertDialogListener listener) {
        show1(fm, items, rentTime, listener, null, null);
    }


    private int fromPosition;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.dialog_reserve_time, container, false);

        ButterKnife.bind(this, rootView);

        LogUtil.d(TAG, "cancel->"+_cancel);
        LogUtil.d(TAG, "confirm->"+_confirm);

        // 버튼 이벤트 등록
        //btn_dialog_prev.setOnClickListener(this);
        btn_dialog_close.setOnClickListener(this);

        GridView reserveTimeGridView = (GridView)rootView.findViewById(R.id.reserveTimeGridView);
        // 커스텀 아답타 생성
        reserveTimeGridAdapter = new ReserveTimeGridAdapter(getContext(),
                R.layout.item_reserve_time_grid,       // GridView 항목의 레이아웃 row.xml
                reserveTimes);    // 데이터

        reserveTimeGridView.setAdapter(reserveTimeGridAdapter);  // 커스텀 아답타를 GridView 에 적용

        // GridView 아이템을 클릭하면 상단 텍스트뷰에 position 출력
        // JAVA8 에 등장한 lambda expression 으로 구현했습니다. 코드가 많이 간결해지네요
        reserveTimeGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ReserveTime currentTime = reserveTimes.get(position);

                if("N".equals(currentTime.getReserveYN())){
                    EZToast.show(getContext(), "예약 할 수 없는 시간입니다. ");
                }
                else {
                    if(fromTime == null){
                        fromTime = currentTime;
                        reserveTimeGridAdapter.items.get(position).setSelected(true);
                        reserveTimeGridAdapter.notifyDataSetChanged();
                        fromPosition = position;

                        int addPosition = 0;
                        if(reserveTimes.size() > fromPosition + rentTime){
                            addPosition = fromPosition + rentTime;
                        }else{
                            addPosition = reserveTimes.size() - 1;
                        }
                        //예약시간이 최대시간을 안넘었을 경우
                        toTime = reserveTimes.get(addPosition);
                        reserveTimeGridAdapter.items.get(addPosition).setSelected(true);

                        for (int i = fromPosition; i< addPosition; i++ ){
                            if(i <= addPosition){
                                reserveTimeGridAdapter.items.get(i).setSelected(true);
                            }
                        }
                        reserveTimeGridAdapter.notifyDataSetChanged();

                        if(fromTime != null && toTime != null){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    _listener.onSelectedTimeList(reserveTimeGridAdapter.items);
                                    _listener.onSelectedTime(fromTime, toTime);
                                    dismiss();
                                }
                            }, 500);
                        }

                    }
//                    else if(toTime == null){
//                        toTime = currentTime;
//                        reserveTimeGridAdapter.items.get(position).setSelected(true);
//
//                        for (int i = fromPosition; i< reserveTimeGridAdapter.items.size() - fromPosition; i++ ){
//                            if(i <= position){
//                                reserveTimeGridAdapter.items.get(i).setSelected(true);
//                            }
//                        }
//                        reserveTimeGridAdapter.notifyDataSetChanged();
//                    }
//
//                    if(fromTime != null && toTime != null){
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                _listener.onSelectedTimeList(reserveTimeGridAdapter.items);
//                                _listener.onSelectedTime(fromTime, toTime);
//                                dismiss();
//                            }
//                        }, 500);
//                    }
                }

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
