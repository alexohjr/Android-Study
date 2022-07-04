package com.and.andelectronics.view.main.surround;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.and.andelectronics.R;
import com.and.andelectronics.common.util.LocationUtil;
import com.and.andelectronics.common.util.PreferenceUtil;
import com.and.andelectronics.common.util.SocialUtil;
import com.and.andelectronics.common.view.ViewSettingActivity;
import com.and.andelectronics.infrastructure.interfaces.ILodgementService;
import com.and.andelectronics.infrastructure.model.lodgement.Lodgement;
import com.and.andelectronics.infrastructure.model.lodgement.LodgementRequestData;
import com.and.andelectronics.view.main.detail.DetailViewLodgmentActivity;
//import com.and.andelectronics.view.main.map.SurroundMapFragmentActivity;
import com.and.andelectronics.view.widget.MainActionBar;
import com.and.andelectronics.webservice.lodgement.LodgementServiceXml;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Won on 2016-01-02.
 */
public class SurroundFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private ListView lodgementListView = null;
    private LodgementListAdapter lodgementListAdapter = null;
    private Activity activity = null;
    private ArrayList<Lodgement> lodgementList;
    private android.app.ProgressDialog ProgressDialog;
    private com.and.andelectronics.Container Container;
    private ILodgementService iLodgementService;
    private MainActionBar mainActionBar;
    private ImageView mapImageView;
    private TextView viewSettingButton;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity)context;
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

        View view = inflater.inflate(R.layout.fragment_surround, null);
        onCreateViewInit(view);
        return view;

    }

    private void onCreateViewInit(View view) {
        // TODO Auto-generated method stub
        this.Container = (com.and.andelectronics.Container)activity.getApplicationContext();
        this.iLodgementService = new LodgementServiceXml(activity);
        this.mainActionBar = new MainActionBar(activity,null);
        this.mainActionBar = (MainActionBar)view.findViewById(R.id.mainActionBar);
        TextView title = (TextView)this.mainActionBar.findViewById(R.id.TV_actionTitle);
        title.setText(R.string.comm_surround);

        this.mapImageView = (ImageView)this.mainActionBar.findViewById(R.id.mapImageView);
        this.mapImageView.setOnClickListener(this);
        this.viewSettingButton = (TextView)this.mainActionBar.findViewById(R.id.viewSettingButton);
        this.viewSettingButton.setOnClickListener(this);

        this.swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_layout);
        this.swipeRefreshLayout.setOnRefreshListener(this);

        this.lodgementListView = (ListView) view.findViewById(R.id.lodgementListView);
        this.lodgementListView.setOnItemClickListener(this);

        onRefresh();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            for (String key : savedInstanceState.keySet()) {
            }
        }
        super.onActivityCreated(savedInstanceState);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        // TODO Auto-generated method stub

        Lodgement mData = this.lodgementListAdapter.lodgementList.get(position);

        Bundle extras = new Bundle();

        Intent intent = new Intent(activity, DetailViewLodgmentActivity.class);
        extras.putSerializable("obj", mData);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public static Fragment newInstance(int position) {
        SurroundFragment fragment = new SurroundFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    public SurroundFragment() {}

    /**
     * 풀투 리프레시 override 메소드
     */
    @Override
    public void onRefresh() {
        new LodgementListAsync().execute();

        this.swipeRefreshLayout.setRefreshing(false);
    }

    private class LodgementListAsync extends AsyncTask<Void, String, Void> {

        @Override
        protected void onPostExecute(Void result) {
            SurroundFragment.this.ProgressDialog.dismiss();
            lodgementListAdapter = new LodgementListAdapter(activity,lodgementList);
            lodgementListView.setAdapter(lodgementListAdapter);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            SurroundFragment.this.ProgressDialog = android.app.ProgressDialog.show(
                    activity,
                    getResources().getString(R.string.async_store_title),getResources().getString(R.string.async_store_msg));

        }



        @Override
        protected Void doInBackground(Void... params) {

            LodgementRequestData reqData = new LodgementRequestData();
            //reqData.setPosiX(String.valueOf(latitude));
            //reqData.setPosiY(String.valueOf(longitude));
            //reqData.setDistance("10");


            lodgementList = iLodgementService.getLodgementList(reqData);
            lodgementList = getSurroundList();  //거리구해오기
            return null;
        }
    }

    private ArrayList<Lodgement> getSurroundList(){
        ArrayList<Lodgement> itemList = new ArrayList<Lodgement>();
        LocationUtil geocoder = new LocationUtil(activity);
        LatLng myLatLen = geocoder.searchLocation(PreferenceUtil.instance(activity).getMyAddress());

        for(Lodgement item : lodgementList){
            try
            {
                LatLng latLng =  geocoder.searchLocation(item.getAddr1());
                if(latLng == null || item.getAddr1() == null || item.getAddr1().equals("") )latLng = myLatLen;
                String adistance = SocialUtil.calculateDistanceMessage(myLatLen.latitude, myLatLen.longitude, latLng.latitude, latLng.longitude);
                int distance = SocialUtil.calculateDistance(myLatLen.latitude, myLatLen.longitude, latLng.latitude, latLng.longitude);
                item.setDistance(adistance);
                itemList.add(item);
                /*if(distance < 20000)
                {
                    itemList.add(item);
                }*/
            }
            catch(Exception ex)
            {

            }
        }

        return itemList;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId()){

            case R.id.mapImageView:
                //activity.startActivity(new Intent(activity, SurroundMapFragmentActivity.class));
                break;

            case R.id.viewSettingButton:
                activity.startActivity(new Intent(activity, ViewSettingActivity.class));
                break;
        }
    }


}
