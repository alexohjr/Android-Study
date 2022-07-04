package com.and.andelectronics.view.main.enterance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.and.andelectronics.R;
import com.and.andelectronics.view.main.payment.PayTypeListAdapter;
import com.and.andelectronics.view.widget.MainActionBar;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Won on 2017-06-29.
 */
public class EnteranceTypeActivity extends Activity implements AdapterView.OnItemClickListener {
    private ListView payTypeListView;
    private PayTypeListAdapter payTypeListAdapter;
    private MainActionBar mainActionBar;
    private ImageView mapImageView;
    private TextView viewSettingButton;


    private String[] enteranceType = {"비밀번호", "NFC", "카드수령"};
    private ArrayList<String> enteranceTypeList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paytype);

        this.mainActionBar = new MainActionBar(this, null);
        this.mainActionBar = (MainActionBar) findViewById(R.id.mainActionBar);
        TextView title = (TextView) this.mainActionBar.findViewById(R.id.TV_actionTitle);
        title.setText("출입방법 선택");

        this.mapImageView = (ImageView) this.mainActionBar.findViewById(R.id.mapImageView);
        this.mapImageView.setVisibility(View.GONE);
        this.viewSettingButton = (TextView) this.mainActionBar.findViewById(R.id.viewSettingButton);
        this.viewSettingButton.setVisibility(View.GONE);

        this.payTypeListView = (ListView) findViewById(R.id.payTypeListView);
        this.payTypeListView.setOnItemClickListener(this);

        this.enteranceTypeList = new ArrayList<String>();
        Collections.addAll(this.enteranceTypeList, this.enteranceType);

        payTypeListAdapter = new PayTypeListAdapter(this, enteranceTypeList);
        payTypeListView.setAdapter(payTypeListAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("enteranceType", enteranceTypeList.get(position));
        setResult(RESULT_OK, intent);
        finish();
    }
}