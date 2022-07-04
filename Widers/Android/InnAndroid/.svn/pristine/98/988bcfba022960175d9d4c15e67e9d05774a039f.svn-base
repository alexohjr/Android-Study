package com.and.andelectronics.view.main.map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;


import com.and.andelectronics.R;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Won on 2016-01-02.
 */
public class LodgmentLocationMapFragmentActivity extends FragmentActivity implements LocationListener {
    private GoogleMap googleMap;
    private LocationManager locationManager;
    private String provider;
    private double lat;
    private double lng;
    private LatLng latlng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_saleplace);

        Intent geti = getIntent();
        latlng = geti.getParcelableExtra("latlng");

        GooglePlayServicesUtil.isGooglePlayServicesAvailable(LodgmentLocationMapFragmentActivity.this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, true);

        if (provider == null) { // 위치정보 설정이 안되어 있으면 설정하는 엑티비티로 이동합니다
            new AlertDialog.Builder(LodgmentLocationMapFragmentActivity.this)
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
                    LodgmentLocationMapFragmentActivity.this);

            setUpMapIfNeeded();
        }

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
                            LodgmentLocationMapFragmentActivity.this);
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
    }////이게 과연 무엇인가

    private void setLocationInMap(){

    }
    private void setUpMapIfNeeded() {
        if (googleMap == null) {
            //googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        }


        if (googleMap != null) {
            setUpMap();
            googleMap.addMarker(new MarkerOptions()
                    .position(latlng));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latlng).zoom(12).build();
            googleMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            googleMap.setOnMarkerClickListener(new OnMarkerClickListener(){

                @Override
                public boolean onMarkerClick(Marker marker) {
                    // TODO Auto-generated method stub
                    LatLng latlng = marker.getPosition();
                    double lat = latlng.latitude;
                    double lng = latlng.longitude;
                    //Toast.makeText(LodgmentLocationMapFragmentActivity.this, "마커클릭", Toast.LENGTH_LONG)
                    //.show();
                    //Log.d("myLog", "onLocationChanged: !!" + "lat!!=" + lat+"lng!!=" + lng );
                    // replace fragment
                    //Intent intent = new Intent(LodgmentLocationMapFragmentActivity.this,MainFragmentActivity.class);
                    //startActivity(intent);
				        /* FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

				        transaction.replace(R.id.LL_parent, new SaleListFragment());

				        // Commit the transaction
				        transaction.commit();*/

                    return false;
                }

            });
        }



    }


    private void setUpMap() {
        googleMap.setMyLocationEnabled(true);
        googleMap.getMyLocation();

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
}