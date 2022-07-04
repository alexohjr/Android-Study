package com.example.pc.day25;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


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

    private MyParser myParser;
    private ArrayList<BookModel> list;
    private MyViewModelAdapter adapter;

    String keyword; // 검색어
    int start = 1; // 시작 번호

    // 스크롤링를 통한 추가 로드를 위해
    LayoutInflater mInflater;
    View footer_view;
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
                }
            }
        }); // onClick()

        myParser = new MyParser(this);
        mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        footer_view = mInflater.inflate(R.layout.footer, null);

    } // onCreate()


    /*
        < AsyncTask >
        - 네트워크는 반드시 백그라운드 스레드로 처리해야 한다.
          이유: 송수신의 규모가 클 경우. 만약 멀리스레드 처리를 안한다면
                어플이 그동안 동작할 수 없다.
        - AsyncTask란? 백그라운드에서 수행할 일을 다른 Runnable, Thread 보다
          더 쉽게 처리할 수 있는 클래스
        - 주요 메서드
          1. onPreExecuted()
             : 백그라운드에서 실행할 작업을 본격적으로 진행하기 전 사전에 실행되는 메서드
               (주로 로딩바를 띄울 때 사용)
          2. doInBackground()
             : 작업할 내용
          3. onProgressUpdate()
             :  doInBackground() 실행 중 중간 중간 결과를 보고해되는 상황을 위해 정의하는 메서드
                (실행을 원한다면 doInBackground 내부에 publishProgress()를 호출한다.)
                (주로 로딩바의 퍼센티지를 표현할 때 사용)
          4. onPostExecuted() : doInBackground() 수행 후 호출됨. 마무리 작업용
              (주로 로딩바를 쓸 때 사용)

        - 제네릭 3인방
          < Params, Progress , Result   >
           Params : doInBackground()을 호출할 때 들어갈 인자타입
           Progress : onProgressUpdate() 에 넣을 인자 타입
           Result : onPostExecuted() 에 넣을 인자 타입

          (없는 경우 Void 라고 쓴다.)
     */
    class NaverAsync extends AsyncTask<String, String, ArrayList<BookModel>> {
        @Override
        protected void onPreExecute() {
            Log.d("MY", "onPreExecute()");
            super.onPreExecute();
        }


        @Override
        protected ArrayList<BookModel> doInBackground(String... strings) {
            Log.d("MY", "doInBackground()");
            return myParser.requestToNaver(start, list);

        }

        @Override
        protected void onPostExecute(ArrayList<BookModel> bookModels) {
            // 준비된 ArrayList를 가지고 UI 생성
            Log.d("MY", "onPostExecute()");
            if (adapter == null) {
                adapter = new MyViewModelAdapter(MainActivity.this, R.layout.book_item, bookModels);

                // 리스트뷰의 리스너 등록
                listView.setOnScrollListener(scorllListener);

                // 리스트뷰의 footer 등록
                listView.addFooterView(footer_view);

                // 리스트뷰에 어댑터 실행
                listView.setAdapter(adapter);
            } // if

            // 리스트뷰에 변경사항이 있다면 갱신
            adapter.notifyDataSetChanged();
            mLockListView = false;
            dialog.dismiss(); // 로딩 다이얼로그 닫기
        }
    } // NaverAsyncTask

    AbsListView.OnScrollListener scorllListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            // 현재 스크롤 상태를 알려줌
            if (scrollState == SCROLL_STATE_IDLE) {
                Log.d("MY", "스크롤의 끝에 다다랐음");
                // 스크롤이 종료되어 어떤 애니메이션도 실행되지 않음
            } else if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                Log.d("MY", "터치한 상태에서 스크롤 중");
            } else if (scrollState == SCROLL_STATE_FLING) {
                Log.d("MY", "손을 뗀 상태에서 스크롤 중");
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            // 스크롤이 발생하는 동안 지속적으로 호출됨
            Log.d("MY", "onScroll()");

            // firstVisibleItem : 현재 화면에 보여지는 항목의 인덱스
            // visibleItemCount : 화면(ListView)에 노출할 아이템들의 개수
            // totalItemCount : 리스트의 총 아이템 전체 개수

            int count = totalItemCount - visibleItemCount;

            // mLockListView : false ( 추가 로드 함 )
            //                 true ( 추가 로드 안함 => 리스트뷰 구현 끝남 )
            if (firstVisibleItem >= count && totalItemCount != 0 && !mLockListView) {

                // 더이상 스크롤 추가 구현을 못하게 함
                mLockListView = true;

                // 추가 로드 하기
                if (start < 1000 - 10 && list.size() >= 10) {
                // start < 990 : 아이템이 1000개 미만이고
                // list.size() >= 10 : 현재 ArrayList의 책 아이템이 10개 이상일 때
                    if (start >= list.size()) { // start가 책 아이템 개수를 벗어나면
                        start = 1000 - 10; // start를 강제로 990으로 만들기(추가 로드 못하게)
                        footer_view.performClick();
                    }
                    start += 10; // start 값 증가
                    new NaverAsync().execute(); // 10개 추가 로드 진행
                } // if
                else {
                    Toast.makeText(MainActivity.this, "더 불러올 내용이 없습니다.",
                            Toast.LENGTH_SHORT).show();
                    listView.removeFooterView(footer_view);
                } // else
            } // if

        } // onScroll()
    };
}
