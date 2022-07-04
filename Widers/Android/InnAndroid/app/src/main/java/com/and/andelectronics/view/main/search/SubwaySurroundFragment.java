package com.and.andelectronics.view.main.search;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.and.andelectronics.R;
import com.and.andelectronics.common.WebServiceInformation;
import com.and.andelectronics.common.jinlib.EasyJsonList;
import com.and.andelectronics.common.jinlib.GetHttp;
import com.and.andelectronics.infrastructure.model.AreaItem;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Won on 2016-04-24.
 */
public class SubwaySurroundFragment extends Fragment{

    private Activity activity;
    private Context context;
    private ListView areaListView;
    private ListView districtListView;

    private ArrayList<AreaItem> areaItemArrayList;
    private EasyJsonList ejl;
    private AreaCityAdapter areaCityAdapter;

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

    public static SubwaySurroundFragment newInstance(int pageNumber, String title) {
        SubwaySurroundFragment fragment = new SubwaySurroundFragment();
        Bundle args = new Bundle();
        args.putInt("page", pageNumber);
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_area, null);
        onCreateViewInit(view);
        return view;

    }

    private void onCreateViewInit(View view) {
        // TODO Auto-generated method stub
        this.areaListView = (ListView)view.findViewById(R.id.areaListView);
        this.districtListView = (ListView)view.findViewById(R.id.districtListView);

        new NoticeAllTask().execute();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            for (String key : savedInstanceState.keySet()) {
            }
        }
        super.onActivityCreated(savedInstanceState);
    }
    public class NoticeAllTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            try {
                areaItemArrayList = new ArrayList<AreaItem>();
                GetHttp getHttp = new GetHttp();
                String param_url = WebServiceInformation.getUrl()
                        + "/common/area.do?";

                List<NameValuePair> arguments = new ArrayList<NameValuePair>();
                //arguments.add(new BasicNameValuePair("companyNo", trxn.getCompanyNo()));

                System.out.println("!-- Log --req");
                JSONArray returnJson = getHttp.getPostArray(param_url, arguments,true);
                System.out.println("!-- Log --!8798798798797899897979");
                try {

                    ejl = new EasyJsonList(returnJson);
                    areaItemArrayList.clear();
                    int jsonSize = ejl.getLength();
                    for (int i = 0; i < jsonSize; i++) {
                        areaItemArrayList.add(new AreaItem(ejl.getValue(
                                i, "id"), ejl.getValue(i, "areaCity"),
                                ejl.getValue(i, "district"), ejl.getValue(i,
                                "dong")));
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

            areaCityAdapter = new AreaCityAdapter(activity, areaItemArrayList);
            areaListView.setAdapter(areaCityAdapter);

        }
    }
}
