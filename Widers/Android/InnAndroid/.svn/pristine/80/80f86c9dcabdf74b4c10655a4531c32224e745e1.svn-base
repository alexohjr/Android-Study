package com.and.andelectronics.view.main.home;

import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.and.andelectronics.R;
import com.and.andelectronics.common.LocationService;
import com.and.andelectronics.common.util.LocationUtil;
import com.and.andelectronics.common.util.PreferenceUtil;
import com.and.andelectronics.common.view.GPSSettingAlert;
import com.and.andelectronics.infrastructure.interfaces.ILodgementService;
import com.and.andelectronics.infrastructure.model.lodgement.Lodgement;
import com.and.andelectronics.infrastructure.model.lodgement.LodgementRequestData;
import com.and.andelectronics.view.widget.HomeLocationTab;
import com.and.andelectronics.webservice.lodgement.LodgementServiceXml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Won on 2018-05-29.
 */
public class HomeLocationFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener { //, AdapterViewCompat.OnItemClickListener {

    public static final String TAG = HomeLocationFragment.class.getSimpleName();

    //region widget
    private LinearLayoutManager layoutManager;
    private RecyclerView homeLocationRecyclerView;
    private HomeLocationCardViewAdapter homeLocationCardViewAdapter = null;
    private Context context;
    private android.app.ProgressDialog ProgressDialog;

    private Button motelTabButton;
    private Button hotelTabButton;
    private Button pensionTabButton;
    private Button resortTabButton;
    private Button guestHouseTabButton;

    private HomeLocationTab homeLocationTab;
    //endregion

    protected List<Button> tabButtonList;
    protected int selectedTabButtonColor;
    protected int deSelectedTabButtonColor;
    protected int selectedTabButtonTextColor;
    protected int deSelectedTabButtonTextColor;

    private ILodgementService iLodgementService;
    private ArrayList<Lodgement> lodgementList;

    double latitude = 37.6099507;
    double longitude = 126.7236531;

    private AppCompatActivity activity;

    private int lodgement = 0;
    private String currentCompType = "CT01";
    private Map<Button, String> companyTypeMap = new HashMap<Button, String>();
    public static Fragment newInstance(int position) {
        HomeLocationFragment fragment = new HomeLocationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public HomeLocationFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        this.activity = (AppCompatActivity)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_location, container, false);
        this.onCreateViewInit(view);
        return view;
    }

    private void onCreateViewInit(View view) {
        // TODO Auto-generated method stub
        this.iLodgementService = new LodgementServiceXml(context);     //실제 사용할 서비스 WCF용

        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);

        this.homeLocationTab = new HomeLocationTab(this.context, null);
        this.homeLocationTab = (HomeLocationTab)view.findViewById(R.id.homeLocationTab);
        this.motelTabButton = (Button)homeLocationTab.findViewById(R.id.motelTabButton);
        this.hotelTabButton = (Button)homeLocationTab.findViewById(R.id.hotelTabButton);
        this.pensionTabButton = (Button)homeLocationTab.findViewById(R.id.pensionTabButton);
        this.resortTabButton = (Button)homeLocationTab.findViewById(R.id.resortTabButton);
        this.guestHouseTabButton = (Button)homeLocationTab.findViewById(R.id.guestHouseTabButton);

        this.motelTabButton.setOnClickListener(this);
        this.hotelTabButton.setOnClickListener(this);
        this.pensionTabButton.setOnClickListener(this);
        this.resortTabButton.setOnClickListener(this);
        this.guestHouseTabButton.setOnClickListener(this);

        this.homeLocationRecyclerView = (RecyclerView) view.findViewById(R.id.homeLocationRecyclerView);

        this.layoutManager = new LinearLayoutManager(this.context);
        this.homeLocationRecyclerView.setHasFixedSize(true);
        this.homeLocationRecyclerView.setLayoutManager(layoutManager);

        this.selectedTabButtonColor = R.mipmap.btn_home_location_tab_on;
        this.deSelectedTabButtonColor = R.color.lightgrey;
        this.selectedTabButtonTextColor = R.color.white;  //setTextColor에는 resource가아닌 Color값이 들어가야 함
        this.deSelectedTabButtonTextColor = R.color.darkgrey;

        getOnLocation();
        //onRefresh();
        initTabButtonList();

        //내주변으로 맨처음 왔을 때 내주변탭이 떠야 함
        this.tabClick(this.motelTabButton);
    }


    private void initTabButtonList(){
        this.tabButtonList = new ArrayList<Button>();
        this.tabButtonList.add(this.motelTabButton);
        this.tabButtonList.add(this.hotelTabButton);
        this.tabButtonList.add(this.pensionTabButton);
        this.tabButtonList.add(this.resortTabButton);
        this.tabButtonList.add(this.guestHouseTabButton);


        this.companyTypeMap.put(this.motelTabButton, "CT01");
        this.companyTypeMap.put(this.hotelTabButton, "CT02");
        this.companyTypeMap.put(this.pensionTabButton, "CT03");
        this.companyTypeMap.put(this.resortTabButton, "CT04");
        this.companyTypeMap.put(this.guestHouseTabButton, "CT05");
    }

    private void changeTabButtonState(int selectedButtonId) {
        //tab button상태 설정
        for(Button button : this.tabButtonList) {
            if(button.getId() == selectedButtonId) {
                button.setEnabled(false);
                button.setBackgroundResource(this.selectedTabButtonColor);
                button.setTextColor((ContextCompat.getColorStateList(context, this.selectedTabButtonTextColor)));
                //현재 탭버튼의 객실타입 가져오기
                String type = this.companyTypeMap.get(button);
                setCurrentCompType(type);
            }

            else {
                button.setEnabled(true);
                button.setBackgroundResource(this.deSelectedTabButtonColor);
                button.setTextColor((ContextCompat.getColorStateList(context, this.deSelectedTabButtonTextColor)));
            }
        }
    }

    /**
     * @func tabClick
     * @param view
     * @desc 프래그먼트 탭버튼 클릭시 호출 (탭변경)
     */
    protected void tabClick(View view) {
        int buttonId = view.getId();
        this.changeTabButtonState(buttonId);
        onRefresh();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            default:
                tabClick(v);
                break;
        }
    }


    public  AddressHeader getHeader()
    {
        AddressHeader header = new AddressHeader();
        header.setAddress("");
        return header;
    }


    public Location getLocation()
    {
        LocationService locationService = new LocationService(this.context);
        Location location = locationService.getLocation();
        return location;
    }

    public String getAddress(Location location)
    {
        String address = "";
        if(location != null)
        {
            LocationUtil geocoder = new LocationUtil(this.context);
            address = geocoder.searchAddress(location.getLatitude(), location.getLongitude());
            PreferenceUtil.instance(context).putMyAddress(address);
            this.latitude = location.getLatitude();
            this.longitude = location.getLongitude();
        }
        else
        {
            //없으면 이렇게 하던지 아니면 프리퍼런스에 내 위치 저장해서 불러오던지 (가장 최근위치)
            address = getString(R.string.comm_location_off);
        }
        return address;
    }

    private void getOnLocation() {
        Location location = getLocation();
        if(location == null){
            GPSSettingAlert.showGpsSettingsAlert(this.context);
        }
        //this.homeHeaderAddressTextView.setText(getAddress(location));
    }

    public String getCurrentCompType() {
        return currentCompType;
    }

    public void setCurrentCompType(String currentCompType) {
        this.currentCompType = currentCompType;
    }

    @Override
    public void onRefresh() {
        //풀투 리프레시
        new LodgementListAsync().execute();
        //새로고침 완료시에 아이콘 없앤다.
        //this.mSwipeRefreshLayout.setRefreshing(false);
    }

   /* @Override
    public void onItemClick(AdapterViewCompat<?> parent, View view, int position, long id) {

        Lodgement mData = this.homeCardViewAdapter.items.get(position);

        Bundle extras = new Bundle();

        Intent intent = new Intent(context, DetailViewLodgmentActivity.class);
        extras.putSerializable("obj", mData);
        intent.putExtras(extras);
        startActivity(intent);
    }*/


    private class LodgementListAsync extends AsyncTask<Void, String, Void> {

        @Override
        protected void onPostExecute(Void result) {
            HomeLocationFragment.this.ProgressDialog.dismiss();
            homeLocationCardViewAdapter = new HomeLocationCardViewAdapter(context, lodgementList, 0);
            homeLocationRecyclerView.setAdapter(homeLocationCardViewAdapter);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            HomeLocationFragment.this.ProgressDialog = android.app.ProgressDialog.show(
                    context,
                    getResources().getString(R.string.async_store_title), getResources().getString(R.string.async_store_msg));

        }


        @Override
        protected Void doInBackground(Void... params) {

            LodgementRequestData reqData = new LodgementRequestData();
            reqData.setPosiX(String.valueOf(latitude));
            reqData.setPosiY(String.valueOf(longitude));
            reqData.setCompType(getCurrentCompType());
            lodgementList = iLodgementService.getLodgementList(reqData);
            if(lodgementList != null)
            {

            }
            return null;
        }
    }


}

