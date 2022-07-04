package com.and.andelectronics.view.main.mypage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.and.andelectronics.Container;
import com.and.andelectronics.R;
import com.ez.EZToast;

/**
 * Created by Won on 2016-01-21.
 */
public class MyPageFragment extends Fragment implements View.OnClickListener{

    private AppCompatActivity activity;
    private LinearLayout myPageReserveButton;
    private Container container;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (AppCompatActivity) context;
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

        View view = inflater.inflate(R.layout.fragment_mypage, container, false);
        onCreateViewInit(view);
        return view;

    }

    private void onCreateViewInit(View view) {
        // TODO Auto-generated method stub

        this.container = (Container)activity.getApplicationContext();

        myPageReserveButton = (LinearLayout) view.findViewById(R.id.myPageReserveButton);
        myPageReserveButton.setOnClickListener(this);


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
        MyPageFragment fragment = new MyPageFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    public MyPageFragment() {
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.myPageReserveButton:
                replaceFragment(MyReserveFragment.class);
                break;
        }
    }


    private void replaceFragment(Class clazz) {
        Fragment fragment = null;
        try {
            fragment = (Fragment)clazz.getConstructor().newInstance();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

        if(fragment == null) return;

        //fragment replace
        final FragmentTransaction transaction = activity.getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.tabContentParentLayout, fragment);
        transaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


}