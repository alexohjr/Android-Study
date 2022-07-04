package com.and.andelectronics.view.main.mypage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.and.andelectronics.Container;
import com.and.andelectronics.R;
import com.and.andelectronics.common.util.AppUtil;
import com.and.andelectronics.common.util.DateUtil;
import com.and.andelectronics.infrastructure.interfaces.ILodgementService;
import com.and.andelectronics.infrastructure.model.lodgement.ReserveRequestData;
import com.and.andelectronics.infrastructure.model.lodgement.ReserveRoom;
import com.and.andelectronics.view.widget.MainActionBar;
import com.and.andelectronics.webservice.lodgement.LodgementServiceXml;

import java.util.ArrayList;

/**
 * Created by Won on 2016-01-21.
 */
public class MyReserveFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Activity activity;

    private MainActionBar mainActionBar;
    private ImageView mapImageView;
    private Button reservedRoomButton;
    private android.app.ProgressDialog ProgressDialog;
    private ILodgementService iLodgementService;
    private ArrayList<ReserveRoom> reserveRoomList;
    private Container container;
    private ListView reserveListView;
    private ReserveListAdapter reserveListAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            for (String key : savedInstanceState.keySet()) {
            }
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mypage_reserve, null);
        onCreateViewInit(view);
        return view;

    }

    private void onCreateViewInit(View view) {
        // TODO Auto-generated method stub
        this.mainActionBar = new MainActionBar(activity, null);
        this.mainActionBar = (MainActionBar) view.findViewById(R.id.mainActionBar);
        TextView title = (TextView) this.mainActionBar.findViewById(R.id.TV_actionTitle);
        title.setText(R.string.comm_mypage);

        this.iLodgementService = new LodgementServiceXml(activity);
        this.container = (Container)activity.getApplicationContext();

        this.mapImageView = (ImageView) this.mainActionBar.findViewById(R.id.mapImageView);
        this.mapImageView.setVisibility(View.INVISIBLE);
        this.mainActionBar.findViewById(R.id.viewSettingButton).setVisibility(View.GONE);

        reservedRoomButton = (Button)view.findViewById(R.id.reservedRoomButton);
        reservedRoomButton.setOnClickListener(this);

        reserveListView = (ListView)view.findViewById(R.id.reserveListView);
        reserveListView.setOnItemClickListener(this);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            for (String key : savedInstanceState.keySet()) {
            }
        }
        super.onActivityCreated(savedInstanceState);
    }

    public static Fragment newInstance(int position) {
        MyReserveFragment fragment = new MyReserveFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    public MyReserveFragment() {
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.reservedRoomButton:
                //Intent intent = new Intent(activity, RemoteControlActivity.class);
                //startActivity(intent);
                break;

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    private void onRefresh(){
        new InquireReserveRoomAsync().execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //눌렀을때 반응없이 수정
        /*ReserveRoom mData = this.reserveListAdapter.reserveRoomList.get(position);
        container.setCurrentReserveRoom(mData);
        Intent intent = new Intent(activity, RemoteControlActivity.class);
        startActivity(intent);*/
    }


    private class InquireReserveRoomAsync extends AsyncTask<Void, String, Void> {


        private String message;
        private int resultCode;

        @Override
        protected void onPostExecute(Void result) {
            MyReserveFragment.this.ProgressDialog.dismiss();

            if(resultCode == 0 && reserveRoomList != null){
                reservedRoomButton.setText("예약된 객실: " + reserveRoomList.size());
                //Container에 예약정보 추가
                container.setReserveRoomList(reserveRoomList);
                //container.setCurrentReserveRoom(reserveRoomList.get(0));


                reserveListAdapter = new ReserveListAdapter(activity, reserveRoomList);
                reserveListView.setAdapter(reserveListAdapter);
            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            MyReserveFragment.this.ProgressDialog = android.app.ProgressDialog.show(
                    activity,
                    getResources().getString(R.string.async_reserve_title),getResources().getString(R.string.async_reserve_msg));

        }



        @Override
        protected Void doInBackground(Void... params) {

            ReserveRequestData reqData = new ReserveRequestData();
            reqData.setReserveDate(DateUtil.nowDateFormat("yyyy-MM-dd"));
            //reqData.setReserveDate("2017-11-22");
            reqData.setReserveUdid(AppUtil.getIMEI(activity));   //예약한 내역을 확인할 수 있게 각각 장치의 Udid를 받는다.

            reserveRoomList = iLodgementService.inquireReserveRoom(reqData);
            resultCode = iLodgementService.getResultCode();
            message = iLodgementService.getMessage();
            return null;
        }
    }
}