package com.example.pc.day28;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


/*
    패키지명 : com.example.pc.day25
    Client ID : djoJH_LpsX0V6ROUXFM2
    Client Secret : J1G07lzyan
 */

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Button searchBtn;
    private EditText searchEt;
    private ProgressDialog dialog;
    private Dialog dateDialog;
    private Button dateBtn;

    private MyParser myParser;
    private ArrayList<MovieModel> list;
    private MyViewModelAdapter adapter;

    String keyword; // 검색어
    int start = 1; // 시작 번호

    // 스크롤링를 통한 추가 로드를 위해
    LayoutInflater mInflater;
    boolean mLockListView = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 다이얼로그 객체 준비
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");

        listView = findViewById(R.id.list_view);
        searchEt = findViewById(R.id.search);
        searchBtn = findViewById(R.id.search_btn);

        dateBtn = findViewById(R.id.dateBtn);
        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 데이트피커 다이얼로그
                dateDialog = new Dialog(MainActivity.this);
                dateDialog.setContentView(R.layout.activity_datepicker);

                final DatePicker dp = dateDialog.findViewById(R.id.dp);

                Button confirmBtn = dateDialog.findViewById(R.id.confirmBtn);
                Button cancelBtn = dateDialog.findViewById(R.id.cancelBtn);

                confirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String year = String.valueOf(dp.getYear());
                        final String month;
                        final String day;

                        if(dp.getMonth()+1 < 10) {
                            month = "0"+String.valueOf(dp.getMonth()+1);
                        } else {
                            month = String.valueOf(dp.getMonth()+1);
                        }

                        if(dp.getDayOfMonth() < 10) {
                            day = "0"+String.valueOf(dp.getDayOfMonth());
                        } else {
                            day = String.valueOf(dp.getDayOfMonth());
                        }

                        // 검색어 받기
                        searchEt.setText(year+month+day);
                        searchBtn.performClick();
                        dateDialog.dismiss();
                    }
                });

                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dateDialog.dismiss();
                    }
                });
                dateDialog.show();
            }
        });

        // 리스트뷰의 오버스크롤 효과 (다 올리면 파란색 그림자)를 제거
        listView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        // 검색 버튼 리스너
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 검색어 받기
                keyword = searchEt.getText().toString().trim();

                // 한 글자 이상 입력 받은 경우에 검색 실행
                if (!keyword.isEmpty()) {
                    dialog.show();  // 로딩 다이얼로그 실행
                    list = new ArrayList<>();
                    adapter = null;
                    start = 1;
                    new NaverAsync().execute(); // 네트워크(네이버에 요청하는 스레드) 실행

                    // searchEt의 키보드 감추기
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(searchEt.getWindowToken(), 0);
                    // ==> 검색 버튼 클릭 시 소프트키는 숨김

                }
            }
        }); // onClick()

        myParser = new MyParser(this);
        mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 에딧텍스트의 리스너
        searchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    // ==> 엔터 키 대신 있던 검색 키가 클릭되었을 때
                    // 참고) android:imeOptions="actionSearch" 로 설정되었다면 엔터 키를 눌렀을 때
                    //       돋보기 버튼 눌림

                    // 검색 버튼(searchBtn)의 클릭 이벤트 실행
                    searchBtn.performClick();
                // }

                // if() 를 없애면 (에뮬 상에서) 엔터 쳐도 검색 실행됨

                return true;
            }
        });

    } // onCreate()

    class NaverAsync extends AsyncTask<String, String, ArrayList<MovieModel>> {

        @Override
        protected void onPreExecute() {
            Log.d("MY", "onPreExecute()");
            super.onPreExecute();
        }

        @Override
        protected ArrayList<MovieModel> doInBackground(String... strings) {
            Log.d("MY", "doInBackground()");
            return myParser.requestToNaver(start, list);
        }

        @Override
        protected void onPostExecute(ArrayList<MovieModel> movieModels) {
            // 준비된 ArrayList를 가지고 UI 생성
            Log.d("MY", "onPostExecute()");
            if (adapter == null) {
                adapter = new MyViewModelAdapter(MainActivity.this, R.layout.movie_item, movieModels, listView);

                // 리스트뷰에 어댑터 실행
                listView.setAdapter(adapter);
            } // if

            // 리스트뷰에 변경사항이 있다면 갱신
            adapter.notifyDataSetChanged();
            mLockListView = false;
            dialog.dismiss(); // 로딩 다이얼로그 닫기
        }
    } // NaverAsyncTask


}