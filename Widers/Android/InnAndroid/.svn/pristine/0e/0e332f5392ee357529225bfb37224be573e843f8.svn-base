package com.and.andelectronics.view.main.map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.and.andelectronics.Container;
import com.and.andelectronics.R;
import com.and.andelectronics.common.util.LocationUtil;
import com.and.andelectronics.common.util.PreferenceUtil;
import com.and.andelectronics.infrastructure.interfaces.ILodgementService;
import com.and.andelectronics.infrastructure.model.lodgement.Lodgement;
import com.and.andelectronics.infrastructure.model.lodgement.LodgementRequestData;
import com.and.andelectronics.view.main.detail.RoomGroupActivity;
import com.and.andelectronics.webservice.lodgement.LodgementServiceXml;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Hashtable;

public class StoreMapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter, View.OnClickListener
{

    GoogleMap googleMap;
    private ILodgementService iLodgementService;
    private ArrayList<Lodgement> lodgementList;
    private android.app.ProgressDialog ProgressDialog;
    private Context context;
    private Hashtable<String, String> markers;
    LatLng myLatLen;

    private Container container;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storemap);

        this.context = this;
        this.container = (Container)getApplicationContext();
        this.iLodgementService = new LodgementServiceXml(context);     //실제 사용할 서비스 WCF용
        markers = new Hashtable<>();

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.storeMapFragment);
        mapFragment.getMapAsync(this);
        LocationUtil geocoder = new LocationUtil(context);
        this.myLatLen = geocoder.searchLocation(PreferenceUtil.instance(context).getMyAddress());
        new LodgementListAsync().execute();

    }


    private void setCameraWindow() {
        LatLng seoul = new LatLng(37.52487, 126.92723);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(seoul).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        googleMap.setInfoWindowAdapter(this);

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker arg0) {
                // TODO Auto-generated method stub
                // arg0.showInfoWindow();

                alertAction(markers.get(arg0.getId()),
                        arg0);

            }
        });
    }

    private void alertAction(final String storeCode, Marker marker) {
        final int position = Integer.valueOf(markers.get(marker.getId() + "position"));
        String action[] = { "전화하기", "객실목록보기" };

        new AlertDialog.Builder(context).setItems(action,
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:

                                //ActionEvent.callAction(context, hpNo);
                                break;
                            case 1:
                                moveToRoomActivity(storeCode, lodgementList.get(position));

                                break;
                            default:
                                break;
                        }

                    }

                }).show();
    }

    private void moveToRoomActivity(String storeCode, Lodgement lodgement){
        Lodgement mData = lodgement;

        Bundle extras = new Bundle();

        Intent intent = new Intent(context, RoomGroupActivity.class);
        extras.putSerializable("obj", mData);
        intent.putExtras(extras);
        context.startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // 구글 맵 객체를 불러온다.
        this.googleMap = googleMap;

        // 서울에 대한 위치 설정
        LatLng seoul = new LatLng(37.52487, 126.92723);

        // 구글 맵에 표시할 마커에 대한 옵션 설정
        /*MarkerOptions makerOptions = new MarkerOptions();
        makerOptions
                .position(seoul)
                .title("원하는 위치(위도, 경도)에 마커를 표시했습니다.");

        // 마커를 생성한다.
        this.googleMap.addMarker(makerOptions);*/

        //카메라를 서울 위치로 옮긴다.
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(seoul));
        setCameraWindow();
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
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
    public void onClick(View v) {

    }


    private class LodgementListAsync extends AsyncTask<Void, String, Void> {

        @Override
        protected void onPostExecute(Void result) {
            StoreMapActivity.this.ProgressDialog.dismiss();

            if(lodgementList != null && lodgementList.size() > 0){

                MarkerOptions mo = new MarkerOptions();
                mo.icon(BitmapDescriptorFactory.fromResource(R.drawable.location));

                // StoreListItems.get(0);
                for (int i = 0; i < lodgementList.size() ; i++) {
                    String x = lodgementList.get(i).getPosiX();
                    String y = lodgementList.get(i).getPosiY();
                    double latitude = Double.valueOf(x).doubleValue();
                    double longitude = Double.valueOf(y).doubleValue();

                    mo.title(lodgementList.get(i).getStoreName());
                    // tell.setText(StoreListItems.get(i).getHp_no());
                    // addr.setText( StoreListItems.get(i).getAddr1());
                    mo.snippet("객실: " + lodgementList.get(i).getItemCount() + "건");

                    Marker maker = googleMap.addMarker(mo.position(new LatLng(latitude, longitude)));
                    markers.put(maker.getId(), lodgementList.get(i).getStoreCode());
                    markers.put(maker.getId() + "tell", lodgementList.get(i).getTelNo());
                    markers.put(maker.getId() + "addr", lodgementList.get(i).getAddr1());
                    markers.put(maker.getId() + "position", i + "");
                }

            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            StoreMapActivity.this.ProgressDialog = android.app.ProgressDialog.show(
                    context,
                    getResources().getString(R.string.async_store_title), getResources().getString(R.string.async_store_msg));

        }


        @Override
        protected Void doInBackground(Void... params) {

            LodgementRequestData reqData = new LodgementRequestData();
            reqData.setPosiX(String.valueOf(0));
            reqData.setPosiY(String.valueOf(0));
            reqData.setDistance("10");
            lodgementList = iLodgementService.getLodgementList(reqData);
            if(lodgementList != null)
            {

            }
            return null;
        }
    }

}
