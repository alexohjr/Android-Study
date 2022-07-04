package com.and.andelectronics.view.main.ect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.and.andelectronics.R;
import com.and.andelectronics.view.widget.MainActionBar;

/**
 * Created by Won on 2016-01-21.
 */
public class EtcFragment extends Fragment implements View.OnClickListener {

    private Activity activity;
    private MainActionBar mainActionBar;
    private ImageView mapImageView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
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

        View view = inflater.inflate(R.layout.fragment_etc, null);
        onCreateViewInit(view);
        return view;

    }

    private void onCreateViewInit(View view) {
        // TODO Auto-generated method stub
        this.mainActionBar = new MainActionBar(activity, null);
        this.mainActionBar = (MainActionBar) view.findViewById(R.id.mainActionBar);
        TextView title = (TextView) this.mainActionBar.findViewById(R.id.TV_actionTitle);
        title.setText(R.string.comm_etc);

        this.mapImageView = (ImageView) this.mainActionBar.findViewById(R.id.mapImageView);
        this.mapImageView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            for (String key : savedInstanceState.keySet()) {
            }
        }
        super.onActivityCreated(savedInstanceState);
    }

    public static Fragment newInstance(int position) {
        EtcFragment fragment = new EtcFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    public EtcFragment() {
    }


    @Override
    public void onClick(View v) {

    }
}