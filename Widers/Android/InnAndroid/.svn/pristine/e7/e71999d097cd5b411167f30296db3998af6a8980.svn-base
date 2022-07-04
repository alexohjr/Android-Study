package com.and.andelectronics.view.main.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.and.andelectronics.R;
import com.and.andelectronics.infrastructure.interfaces.ILodgementService;
import com.and.andelectronics.infrastructure.model.lodgement.Room;
import com.and.andelectronics.infrastructure.model.lodgement.RoomRequestData;
//import com.and.andelectronics.view.main.map.SurroundMapFragmentActivity;
import com.and.andelectronics.view.main.room.RoomActivity;
import com.and.andelectronics.view.widget.MainActionBar;
import com.and.andelectronics.webservice.lodgement.LodgementServiceXml;
import com.desmond.parallaxviewpager.ListViewFragment;

import java.util.ArrayList;

/**
 * Created by Won on 2016-01-02.
 */
public class StateRoomListViewFragment extends ListViewFragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    public static final String TAG = StateRoomListViewFragment.class.getSimpleName();



    private RoomListAdapter roomListAdapter = null;
    private Activity activity = null;
    private ArrayList<Room> roomList;
    private android.app.ProgressDialog ProgressDialog;
    private com.and.andelectronics.Container Container;
    private ILodgementService iLodgementService;
    private MainActionBar mainActionBar;
    private ImageView mapImageView;

    private String storeCode;


    public static Fragment newInstance(int position, String storeCode) {
        StateRoomListViewFragment fragment = new StateRoomListViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        args.putString("storeCode", storeCode);
        fragment.setArguments(args);
        return fragment;
    }

    public StateRoomListViewFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mPosition = getArguments().getInt(ARG_POSITION);
        storeCode = getArguments().getString("storeCode");
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);
        mListView = (ListView) view.findViewById(R.id.listview);
        View placeHolderView = inflater.inflate(R.layout.header_placeholder, mListView, false);
        mListView.addHeaderView(placeHolderView);
        mListView.setOnItemClickListener(this);

        TextView reserveDateTextView = new TextView(activity);
        reserveDateTextView.setText("날짜");
        mListView.addHeaderView(reserveDateTextView);

        setListViewOnScrollListener();
        onCreateViewInit(view);
        return view;
    }


    private void onCreateViewInit(View view) {
        // TODO Auto-generated method stub
        this.Container = (com.and.andelectronics.Container)activity.getApplicationContext();
        this.iLodgementService = new LodgementServiceXml(activity);
//        this.mainActionBar = new MainActionBar(activity,null);
//        this.mainActionBar = (MainActionBar)view.findViewById(R.id.mainActionBar);
//        TextView title = (TextView)this.mainActionBar.findViewById(R.id.TV_actionTitle);
//        title.setText(R.string.surround);
//
//        this.mapImageView = (ImageView)this.mainActionBar.findViewById(R.id.mapImageView);
//        this.mapImageView.setOnClickListener(this);
        //this.initialize();
    }


    private void initialize()
    {
        new RoomListAsync().execute();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.initialize();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        // TODO Auto-generated method stub

        if(position > 1) {
            //리스트뷰에 헤더가 붙어 있기 때문에 position 이 1늘어났다.

            position = position - 2;
            Room mData = roomListAdapter.roomList.get(position);

            Bundle extras = new Bundle();
            Intent intent = new Intent(activity, RoomActivity.class);
            extras.putSerializable("obj", mData);
            intent.putExtras(extras);
            startActivity(intent);
        }
    }

    private class RoomListAsync extends AsyncTask<Void, String, Void> {

        @Override
        protected void onPostExecute(Void result)
        {
            StateRoomListViewFragment.this.ProgressDialog.dismiss();
            roomListAdapter = new RoomListAdapter(activity, roomList);
            mListView.setAdapter(roomListAdapter);

        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            StateRoomListViewFragment.this.ProgressDialog = android.app.ProgressDialog.show(
                    activity,
                    getResources().getString(R.string.comm_stateRoom), getResources().getString(R.string.async_store_msg));

        }


        @Override
        protected Void doInBackground(Void... params) {


            RoomRequestData roomRequestData = new RoomRequestData();
            roomRequestData.setCompanyCode(storeCode);
            roomRequestData.setReserveDate(Container.getReserveFromDate().substring(0, 10));

            roomList = iLodgementService.getRoomGroupList(roomRequestData);
            return null;
        }
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId()){

            case R.id.IV_actionBack:
                activity.finish();
                break;
            case R.id.mapImageView:
                //Intent intent = new Intent(activity, SurroundMapFragmentActivity.class);
                //activity.startActivity(intent);
                break;


        }

    }

}