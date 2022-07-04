package com.example.och.andsolutions_v2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class EventPage extends Fragment {

    private ListView listView;
    private ArrayList<ListVO> list = new ArrayList<>();
    private ListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.event_page_fragment, container, false);
        listView = linearLayout.findViewById(R.id.event_list_view);

        for(int i=1; i<11; i++) {
            ListVO vo = new ListVO("이벤트매장 " + String.valueOf(i));
            list.add(vo);
        }

        if(adapter == null) {
            adapter = new ListAdapter(this.getActivity(), R.layout.list_item, list);
        }

        listView.setAdapter(adapter);

        return linearLayout;
    }
}
