package com.and.andelectronics.view.main.room;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.and.andelectronics.Container;
import com.and.andelectronics.R;
import com.and.andelectronics.common.WebServiceInformation;
import com.and.andelectronics.common.util.DateUtil;
import com.and.andelectronics.infrastructure.interfaces.ILodgementService;
import com.and.andelectronics.infrastructure.model.lodgement.Room;
import com.and.andelectronics.infrastructure.model.lodgement.RoomRequestData;
import com.and.andelectronics.webservice.lodgement.LodgementServiceXml;

import java.util.ArrayList;

/**
 * Created by yowonsm on 2018-05-25.
 */

public class RoomNumberActivity extends AppCompatActivity{
    private Context context;

    private LinearLayoutManager layoutManager;
    private RecyclerView roomNumberRecyclerView;
    private RoomNumberCardViewAdapter roomNumberCardViewAdapter;

    private ArrayList<Room> roomList;
    private android.app.ProgressDialog ProgressDialog;

    private ILodgementService iLodgementService;
    public static final String BASIC_DOWNLOAD_URL = WebServiceInformation.getImageDownloadUrl();

    private Container container;
    private Room room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_number);

        this.context = this;
        this.container = (com.and.andelectronics.Container)getApplicationContext();
        this.iLodgementService = new LodgementServiceXml(this);
        room = (Room)this.getIntent().getExtras().getSerializable("obj");
        this.activityInit();

    }

    /*
     * 액티비티 컨트롤러 초기화
     */
    private void activityInit(){

        TextView roomNumberToolbarTitle = (TextView)findViewById(R.id.roomNumberToolbarTitle);
        roomNumberToolbarTitle.setText(room.getGradeName());

        this.roomNumberRecyclerView = (RecyclerView) findViewById(R.id.roomNumberRecyclerView);

        this.layoutManager = new LinearLayoutManager(this.context);
        this.roomNumberRecyclerView.setHasFixedSize(true);
        this.roomNumberRecyclerView.setLayoutManager(layoutManager);

        onRefresh();
    }

    public void onRefresh() {
        //풀투 리프레시
        new RoomListByChoiceAsync().execute();
        //새로고침 완료시에 아이콘 없앤다.
        //this.mSwipeRefreshLayout.setRefreshing(false);
    }

    private class RoomListByChoiceAsync extends AsyncTask<Void, String, Void> {

        @Override
        protected void onPostExecute(Void result)
        {
            RoomNumberActivity.this.ProgressDialog.dismiss();
            roomNumberCardViewAdapter = new RoomNumberCardViewAdapter(context, roomList);
            roomNumberRecyclerView.setAdapter(roomNumberCardViewAdapter);

        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            RoomNumberActivity.this.ProgressDialog = android.app.ProgressDialog.show(
                    context,
                    getResources().getString(R.string.comm_stateRoom), getResources().getString(R.string.async_store_msg));

        }

        @Override
        protected Void doInBackground(Void... params) {

            RoomRequestData roomRequestData = new RoomRequestData();
            roomRequestData.setCompanyCode(room.getCompanyCode());
            roomRequestData.setGrade(room.getGrade());
            //roomRequestData.setReserveDate(Container.getReserveFromDate());
            roomRequestData.setReserveDate(DateUtil.nowDate()); //예약할 시간 대를(yyyy-mm-dd hh:mm:ss) 넣으면 SP에서 파싱해서 객실선택할 때 시간 이후의 객실만 나오도록 한다.
            roomList = iLodgementService.getRoomByChoice(roomRequestData);
            return null;
        }
    }


}
