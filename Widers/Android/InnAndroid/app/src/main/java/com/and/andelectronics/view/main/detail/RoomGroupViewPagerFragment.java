package com.and.andelectronics.view.main.detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.and.andelectronics.Container;
import com.and.andelectronics.R;
import com.and.andelectronics.infrastructure.model.lodgement.Lodgement;
import com.and.andelectronics.view.main.roomservice.RoomServiceItem;

import java.util.ArrayList;

/**
 * Created by Won on 2016-04-12.
 */
public class RoomGroupViewPagerFragment extends Fragment {

    private int mPageNumber;
    private Lodgement lodgement;
    private Container container;

    public static RoomGroupViewPagerFragment create(int pageNumber) {
        RoomGroupViewPagerFragment fragment = new RoomGroupViewPagerFragment();
        Bundle args = new Bundle();
        args.putInt("page", pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mPageNumber = getArguments().getInt("page");
        mPageNumber = getArguments() != null ? getArguments().getInt("page") : 0;
        container = (Container)getActivity().getApplication();
        lodgement = container.getCurrentLodgement();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.pager_room_group, container, false);
        ((TextView) rootView.findViewById(R.id.roomGroupStoreNameTextView)).setText(lodgement.getStoreName());
        ((TextView) rootView.findViewById(R.id.roomGroupAddressTextView)).setText(lodgement.getAddr1());

        ArrayList<RoomServiceItem> items = new ArrayList<>();
        items.add(new RoomServiceItem("", R.mipmap.icon_main_service_car, true));
        items.add(new RoomServiceItem("", R.mipmap.icon_main_service_drink, true));
        items.add(new RoomServiceItem("", R.mipmap.icon_main_service_carom, false));
        items.add(new RoomServiceItem("", R.mipmap.icon_main_service_game, true));
        items.add(new RoomServiceItem("", R.mipmap.icon_main_service_move, false));
        items.add(new RoomServiceItem("", R.mipmap.icon_main_service_up, false));
        //items.add(new RoomServiceItem("", R.mipmap.icon_main_service_vr, false));
        //items.add(new RoomServiceItem("", R.mipmap.icon_main_service_bath, false));
        GridView roomGroupServiceItemGridView = (GridView) rootView.findViewById(R.id.roomGroupServiceItemGridView);
        // 커스텀 아답타 생성
        RoomGroupServiceGridAdapter adapter = new RoomGroupServiceGridAdapter (this.container,
                R.layout.item_common_grid,       // GridView 항목의 레이아웃 row.xml
                items);    // 데이터

        roomGroupServiceItemGridView.setAdapter(adapter);  // 커스텀 아답타를 GridView 에 적용
        return rootView;
    }
}
