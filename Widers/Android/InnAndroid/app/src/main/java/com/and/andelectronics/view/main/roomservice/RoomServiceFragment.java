package com.and.andelectronics.view.main.roomservice;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.and.andelectronics.R;
import com.and.andelectronics.view.main.home.HomeFragment;

import java.util.ArrayList;

/**
 * Created by yowonsm on 2018-05-27.
 * 먹거리, 볼거리, 놀꺼리, 기타거리 등 페이지
 */

public class RoomServiceFragment extends Fragment {

    public static final String TAG = HomeFragment.class.getSimpleName();

    //region widget
    private Context context;
    private android.app.ProgressDialog ProgressDialog;
    //endregion

    private AppCompatActivity activity;

    public static Fragment newInstance(int position) {
        RoomServiceFragment fragment = new RoomServiceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public RoomServiceFragment() {
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

        View view = inflater.inflate(R.layout.fragment_room_service, container, false);
        this.onCreateViewInit(view);
        return view;
    }

    private void onCreateViewInit(View view) {
        // TODO Auto-generated method stub

        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);


        ArrayList<RoomServiceItem> items = new ArrayList<>();
        items.add(new RoomServiceItem("물", R.mipmap.icon_roomservice_water, RoomServiceItem.RoomServiceStatus.EMPTY));
        items.add(new RoomServiceItem("타올", R.mipmap.icon_roomservice_towel, RoomServiceItem.RoomServiceStatus.READY));
        items.add(new RoomServiceItem("음료", R.mipmap.icon_roomservice_drink, RoomServiceItem.RoomServiceStatus.COMPLETE));
        items.add(new RoomServiceItem("차량호출", R.mipmap.icon_roomservice_carcall, RoomServiceItem.RoomServiceStatus.EMPTY));
        items.add(new RoomServiceItem("청소요청", R.mipmap.icon_roomservice_cleaning, RoomServiceItem.RoomServiceStatus.EMPTY));
        items.add(new RoomServiceItem("택시", R.mipmap.icon_roomservice_taxi, RoomServiceItem.RoomServiceStatus.REQUEST));
        items.add(new RoomServiceItem("차/커피", R.mipmap.icon_roomservice_coffee, RoomServiceItem.RoomServiceStatus.EMPTY));
        items.add(new RoomServiceItem("맥주 1set", R.mipmap.icon_roomservice_beer, RoomServiceItem.RoomServiceStatus.EMPTY));
        items.add(new RoomServiceItem("일회용품", R.mipmap.icon_roomservice_disposable, RoomServiceItem.RoomServiceStatus.EMPTY));
        GridView roomServiceGridView = (GridView)view.findViewById(R.id.roomServiceGridView);
        // 커스텀 아답타 생성
        RoomServiceGridAdapter adapter = new RoomServiceGridAdapter (context,
                R.layout.item_room_service,       // GridView 항목의 레이아웃 row.xml
                items);    // 데이터

        roomServiceGridView.setAdapter(adapter);  // 커스텀 아답타를 GridView 에 적용

        // GridView 아이템을 클릭하면 상단 텍스트뷰에 position 출력
        // JAVA8 에 등장한 lambda expression 으로 구현했습니다. 코드가 많이 간결해지네요
        roomServiceGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
            }
        });
    }

}
