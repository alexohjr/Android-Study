package com.and.andelectronics.view.main.pleasure;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.and.andelectronics.Constants;
import com.and.andelectronics.R;
import com.and.andelectronics.common.util.LocationUtil;
import com.and.andelectronics.common.util.PreferenceUtil;
import com.and.andelectronics.view.main.home.HomeFragment;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by yowonsm on 2018-05-27.
 * 먹거리, 볼거리, 놀꺼리, 기타거리 등 페이지
 */

public class RecommendFragment extends Fragment implements View.OnClickListener{

    public static final String TAG = HomeFragment.class.getSimpleName();

    private TextView foodTextView;
    private TextView seeTextView;
    private TextView playTextView;
    private TextView etcTextView;

    //region widget
    private Context context;
    private android.app.ProgressDialog ProgressDialog;
    //endregion

    private AppCompatActivity activity;

    public static Fragment newInstance(int position) {
        RecommendFragment fragment = new RecommendFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public RecommendFragment() {
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

        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        this.onCreateViewInit(view);
        return view;
    }

    private void onCreateViewInit(View view) {
        // TODO Auto-generated method stub

        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        foodTextView = (TextView)view.findViewById(R.id.foodTextView);
        seeTextView = (TextView)view.findViewById(R.id.seeTextView);
        playTextView = (TextView)view.findViewById(R.id.playTextView);
        etcTextView = (TextView)view.findViewById(R.id.etcTextView);

        foodTextView.setOnClickListener(this);
        seeTextView.setOnClickListener(this);
        playTextView.setOnClickListener(this);
        etcTextView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        //String url = "https://search.naver.com/search.naver?where=post&sm=tab_jum&query=%EA%B0%80%EC%82%B0%EB%8F%99+%EB%A7%9B%EC%A7%91";
        String url = "https://search.naver.com/search.naver?where=post&sm=tab_jum&query=";
        LocationUtil geocoder = new LocationUtil(this.context);
        LatLng latLng = geocoder.searchLocation(PreferenceUtil.instance(activity).getMyAddress());
        String address = geocoder.getSubThoroughfare(latLng.latitude, latLng.longitude);
        if(address == null || address.equals("")){
            address = geocoder.getSubLocality(latLng.latitude, latLng.longitude);
        }
        switch (view.getId()){

            case R.id.foodTextView:
                url = url + address + "+맛집";
                moveActivity("먹꺼리", url);
                break;
            case R.id.seeTextView:
                url = url + address + "+가볼만한곳";
                moveActivity("볼꺼리", url);
                break;
            case R.id.playTextView:
                url = url + address + "+놀거리";
                moveActivity("놀꺼리", url);
                break;
            case R.id.etcTextView:
                url = url + address + "+맛사지";
                moveActivity("기타꺼리", url);
                break;

        }
    }

    private void moveActivity(String title, String url){

        Intent i = new Intent(context, RecommendWebViewActivity.class);
        i.putExtra(Constants.TITLE, title);
        i.putExtra(Constants.URL, url);
        startActivity(i);
    }
}
