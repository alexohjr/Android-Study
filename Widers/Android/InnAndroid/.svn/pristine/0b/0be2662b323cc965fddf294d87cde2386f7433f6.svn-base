package com.and.andelectronics.common.view.alert.theme;

import android.app.Dialog;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.TextView;

import com.and.andelectronics.R;
import com.and.andelectronics.common.WebServiceInformation;
import com.and.andelectronics.common.jinlib.EasyJsonList;
import com.and.andelectronics.common.jinlib.GetHttp;
import com.and.andelectronics.infrastructure.model.AreaItem;
import com.and.andelectronics.infrastructure.model.Theme;

/**
 * Created by Won on 2016-06-02.
 */
public class ThemeAlertView extends Dialog implements OnClickListener, OnItemClickListener, OnDismissListener{
    private  Context context;
    private ProgressDialog ProgressDialog;
    private ArrayList<Theme> themeArrayList;

    private EasyJsonList ejl;
    private TextView btn_cancel,btn_popClose;

    TextView tv01_popTitle;
    ListView lv_searchList;
    public ThemeListAdapter themeListAdapter;

    public ThemeAlertView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.comm_dialog_theme);

        btn_cancel = (TextView) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);
        tv01_popTitle = (TextView)findViewById(R.id.tv01_popTitle);
        tv01_popTitle.setText("팀/지구 조회");
        btn_popClose = (TextView)findViewById(R.id.btn_popClose);
        btn_popClose.setOnClickListener(this);
        lv_searchList = (ListView) findViewById(R.id.lv_select_dept);
        lv_searchList.setOnItemClickListener(this);
        elevSearch();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_popClose:

                break;

        }

    }


    private void elevSearch() {
        // TODO Auto-generated method stub
        new ElevatorSearch().execute();
    }

    public class ElevatorSearch extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ThemeAlertView.this.ProgressDialog =
                    android.app.ProgressDialog.show(context, "알림","조회 중 입니다...");
        }

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            try {
                themeArrayList = new ArrayList<Theme>();
                GetHttp getHttp = new GetHttp();
                String param_url = WebServiceInformation.getUrl()
                        + "/common/theme.do?";

                List<NameValuePair> arguments = new ArrayList<NameValuePair>();
                JSONArray returnJson = getHttp.getPostArray(param_url, arguments,true);

                try {

                    ejl = new EasyJsonList(returnJson);
                    themeArrayList.clear();
                    int jsonSize = ejl.getLength();
                    for (int i = 0; i < jsonSize; i++) {
                        themeArrayList.add(new Theme(ejl.getValue(
                                i, "themeName"), ejl.getValue(i, "themeCode"),
                                ejl.getValue(i, "themeImg")));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (Exception ex) {
                // 로그인이 실패하였습니다 띄어주기
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ThemeAlertView.this.ProgressDialog.dismiss();
            themeListAdapter = new ThemeListAdapter(context, themeArrayList);
            lv_searchList.setAdapter(themeListAdapter);

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        // TODO Auto-generated method stub

    }
}
