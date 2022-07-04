/*
package com.and.andelectronics.view.main.map;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.and.andelectronics.R;
import com.and.andelectronics.common.util.LocationUtil;
import com.and.andelectronics.common.util.PreferenceUtil;
import com.and.andelectronics.common.util.SocialUtil;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.Hashtable;

*/
/**
 * Created by Won on 2016-01-02.
 *//*

public class SurroundMapFragmentActivity extends FragmentActivity implements LocationListener, View.OnClickListener,
        GoogleMap.InfoWindowAdapter {

    private GoogleMap mmap;

    Location mLocation;

    private LocationManager locationManager;
    private String provider;
    double lat;
    double lng;
    LatLng myLatLen;

    private com.and.andelectronics.Container Container;

    private android.app.ProgressDialog ProgressDialog;
    private Hashtable<String, String> markers;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_saleplace);


        this.context = this;
        this.Container = (com.and.andelectronics.Container)getApplicationContext();
        this.DOGService = new DoubleInqueryDogService(context);
        markers = new Hashtable<String, String>();

        LocationUtil geocoder = new LocationUtil(context);
        this.myLatLen = geocoder.searchLocation(PreferenceUtil.instance(context).getMyAddress());


        GooglePlayServicesUtil.isGooglePlayServicesAvailable(SurroundMapFragmentActivity.this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, true);

        if (provider == null) { // 위치정보 설정이 안되어 있으면 설정하는 엑티비티로 이동합니다
            new AlertDialog.Builder(SurroundMapFragmentActivity.this)
                    .setTitle("위치서비스 동의")
                    .setNeutralButton("이동",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    startActivityForResult(
                                            new Intent(
                                                    android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS),
                                            0);
                                }
                            })
                    .setOnCancelListener(
                            new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    finish();
                                }
                            }).show();
        } else { // 위치 정보 설정이 되어 있으면 현재위치를 받아옵니다
            locationManager.requestLocationUpdates(provider, 1, 1,
                    SurroundMapFragmentActivity.this);

            setUpMapIfNeeded();
        }

    }

    private ArrayList<DogStoreListItemData> getSurroundList(){
        ArrayList<DogStoreListItemData> itemList = new ArrayList<DogStoreListItemData>();
        LocationGeocoder geocoder = new LocationGeocoder(context);
        for(DogStoreListItemData item : StoreListItems){
            try{
                //LatLng latLng =  geocoder.searchLocation(item.getAddr1());
                //if(latLng == null || item.getAddr1() == null || item.getAddr1().equals("") )latLng = myLatLen;
                String adistance = SocialUtil.calculateDistanceMessage
                        (myLatLen.latitude, myLatLen.longitude, Double.valueOf(item.getPosi_x()).doubleValue(), Double.valueOf(item.getPosi_y()).doubleValue());
                int distance = SocialUtil.calculateDistance
                        (myLatLen.latitude, myLatLen.longitude, Double.valueOf(item.getPosi_x()).doubleValue(), Double.valueOf(item.getPosi_y()).doubleValue());
                item.setAddr2(adistance);
                if(distance < 20000){
                    itemList.add(item);
                }

            }catch(Exception ex){

            }

        }
        return itemList;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {// 위치설정
        // 엑티비티
        // 종료
        // 후
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                Criteria criteria = new Criteria();
                provider = locationManager.getBestProvider(criteria, true);
                if (provider == null) {// 사용자가 위치설정동의 안했을때 종료
                    finish();
                } else {// 사용자가 위치설정 동의 했을때
                    locationManager.requestLocationUpdates(provider, 1L, 2F,
                            SurroundMapFragmentActivity.this);
                    setUpMapIfNeeded();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }// //이게 과연 무엇인가

    private void setLocationInMap() {

    }

    private void setUpMapIfNeeded() {
        if (mmap == null) {
            mmap = ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map)).getMap();
        }

        if (mmap != null) {

            if (locationTag) {
                setUpMap();
                getStore();
                setCameraWindow();
            }

        }

    }

    private void setUpMap() {
        mmap.setMyLocationEnabled(true);
        mmap.getMyLocation();

    }

    boolean locationTag = true;

    @Override
    public void onLocationChanged(Location location) {
        if (locationTag) {// 한번만 위치를 가져오기 위해서 tag를 주었습니다
            Log.d("myLog", "onLocationChanged: !!" + "onLocationChanged!!");
            lat = location.getLatitude();
            lng = location.getLongitude();

            locationTag = false;
            setUpMapIfNeeded();

        }

    }

    private void getStore() {
        new getStoreListItem().execute();
    }

    private void setCameraWindow() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(myLatLen.latitude, myLatLen.longitude)).zoom(12).build();
        mmap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
        mmap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mmap.setInfoWindowAdapter(this);

        mmap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker arg0) {
                // TODO Auto-generated method stub
                // arg0.showInfoWindow();

                alertAction(markers.get(arg0.getId()),
                        markers.get(arg0.getId() + "tell"));

            }
        });
    }

    private class getStoreListItem extends AsyncTask<Void, String, Void> {

        @Override
        protected void onPostExecute(Void result) {

            SurroundMapFragmentActivity.this.ProgressDialog.dismiss();
            int size = StoreListItems.size();

            MarkerOptions mo = new MarkerOptions();
            mo.icon(BitmapDescriptorFactory.fromResource(R.drawable.location));

            // StoreListItems.get(0);
            for (int i = 0; i < size; i++) {
                String x = StoreListItems.get(i).getPosi_x();
                String y = StoreListItems.get(i).getPosi_y();
                double latitude = Double.valueOf(x).doubleValue();
                double longitude = Double.valueOf(y).doubleValue();

                mo.title(StoreListItems.get(i).getStr_name());
                // tell.setText(StoreListItems.get(i).getHp_no());
                // addr.setText( StoreListItems.get(i).getAddr1());
                mo.snippet("객실: " + StoreListItems.get(i).getItem_cnt() + "건");

                Marker maker = mmap.addMarker(mo.position(new LatLng(
                        latitude, longitude)));
                markers.put(maker.getId(), StoreListItems.get(i).getStr_code());
                markers.put(maker.getId() + "tell", StoreListItems.get(i)
                        .getHp_no());
                markers.put(maker.getId() + "addr", StoreListItems.get(i)
                        .getAddr1());
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            SurroundMapFragmentActivity.this.ProgressDialog = android.app.ProgressDialog
                    .show(context,
                            getResources()
                                    .getString(R.string.async_store_title),
                            getResources()
                                    .getString(R.string.async_store_title));

        }

        @Override
        protected Void doInBackground(Void... params) {

            StoreRequestData reqData = new StoreRequestData();
            reqData.setPosiX(String.valueOf(myLatLen.latitude));
            reqData.setPosiY(String.valueOf(myLatLen.longitude));
            reqData.setDistance("1000");

            StoreListItems = DOGService.getStoreList(reqData);
            StoreListItems = getSurroundList();
            // ArrayList<DogDepListItemData> ReceiptListItems =
            // pa.getDepListItem();

            return null;
        }

    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public View getInfoContents(Marker marker) {
        // TODO Auto-generated method stub
        View myContentView = getLayoutInflater().inflate(R.layout.custommarker,
                null);
        TextView tvTitle = ((TextView) myContentView.findViewById(R.id.title));
        tvTitle.setText(marker.getTitle());
        TextView tvSnippet = ((TextView) myContentView
                .findViewById(R.id.snippet));
        TextView tell = ((TextView) myContentView.findViewById(R.id.tell));
        tell.setText(markers.get(marker.getId() + "tell"));
        TextView addr = ((TextView) myContentView.findViewById(R.id.addr));
        addr.setText(markers.get(marker.getId() + "addr"));
        tvTitle.setText(marker.getTitle());

        // tell.setTag(marker.getId());
        tvSnippet.setText(marker.getSnippet());
        tvTitle.setOnClickListener(this);
        tvSnippet.setOnClickListener(this);
        return myContentView;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        // TODO Auto-generated method stub
        return null;
    }

    private void alertAction(final String str_code, final String hpNo) {

        String action[] = { "전화하기", "객실목록보기" };

        new AlertDialog.Builder(context).setItems(action,
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:

                                ActionEvent.callAction(context, hpNo);
                                break;
                            case 1:

                                PetListRequestData prd = new PetListRequestData();
                                prd.setPriceTo("9999999999999999");
                                prd.setPriceFrom("0");
                                prd.setStoreCode(str_code);

                                Container.setPetListRequestData(prd);
                                Intent intent = new Intent(context,
                                        ParcelingListActivity.class);
                                intent.putExtra("Abandon", 0);
                                startActivity(intent);
                                break;
                            default:
                                break;
                        }

                    }

                }).show();
    }

    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        switch (view.getId()) {
            case R.id.title:
                Toast.makeText(context, "타이틀클릭", Toast.LENGTH_SHORT).show();
                break;
            case R.id.snippet:
                Toast.makeText(context, "스니펫클릭", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
*/
