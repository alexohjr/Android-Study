package com.and.andelectronics.view.main.payment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.and.andelectronics.R;
import com.and.andelectronics.view.widget.MainActionBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Won on 2017-06-27.
 */
public class PayTypeActivity extends Activity implements AdapterView.OnItemClickListener{

    private ListView payTypeListView;
    private PayTypeListAdapter payTypeListAdapter;
    private MainActionBar mainActionBar;
    private ImageView mapImageView;
    private TextView viewSettingButton;


    private String[] payType = {"카드결제(신용, 체크)", "휴대폰 결제", "카카오페이", "PAYCO", "네이버페이"};
    private ArrayList<String> payTypeList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paytype);

        this.mainActionBar = new MainActionBar(this ,null);
        this.mainActionBar = (MainActionBar)findViewById(R.id.mainActionBar);
        TextView title = (TextView)this.mainActionBar.findViewById(R.id.TV_actionTitle);
        title.setText("결제수단 선택");

        this.mapImageView = (ImageView)this.mainActionBar.findViewById(R.id.mapImageView);
        this.mapImageView.setVisibility(View.GONE);
        this.viewSettingButton = (TextView)this.mainActionBar.findViewById(R.id.viewSettingButton);
        this.viewSettingButton.setVisibility(View.GONE);

        this.payTypeListView = (ListView)findViewById(R.id.payTypeListView);
        this.payTypeListView.setOnItemClickListener(this);

        this.payTypeList = new ArrayList<String>();
        Collections.addAll(this.payTypeList, this.payType);

        payTypeListAdapter = new PayTypeListAdapter(this, payTypeList);
        payTypeListView.setAdapter(payTypeListAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("payType", payTypeList.get(position));
        setResult(RESULT_OK, intent);
        finish();
    }
}
