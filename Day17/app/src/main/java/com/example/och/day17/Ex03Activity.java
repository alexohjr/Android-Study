package com.example.och.day17;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class Ex03Activity extends AppCompatActivity implements View.OnClickListener {

    Map<String, ?> memoMap;
    ArrayList<MemoVo> memoVoArrayList;
    Button saveBtn, deleteBtn;
    SharedPreferences preferences;

    // 방법2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex03);

        saveBtn = findViewById(R.id.save_btn);
        deleteBtn = findViewById(R.id.delete_btn);

        saveBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

        // 메모 정보 얻어오디
        // 키 : 저장시간(long -> String)
        // 값 : 제목 + 본문 (String 1개)
        // SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        // MyMemo 의 이름으로만 된 값들 가져오기
        SharedPreferences preferences = getSharedPreferences("MyMemo", MODE_PRIVATE);

        // 모든 값 가져오기
        memoMap = preferences.getAll();

        memoVoArrayList = new ArrayList<>(memoMap.size());
        for (Map.Entry<String, ?> entry : memoMap.entrySet()) {
            Log.d("MY", entry.getKey() + " : " + entry.getValue().toString());

            long savedTime = Long.parseLong(entry.getKey());
            String value = entry.getValue().toString();
            String[] arr = value.split("#");
            String title = arr[0];
            String content = arr[1];
            memoVoArrayList.add(new MemoVo(title, content, savedTime));
        }

        Log.d("MY", memoVoArrayList.toString());

        // 뷰
        ListView listView = findViewById(R.id.list_item);

        // 어댑터
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return memoVoArrayList.size();
            }

            @Override
            public Object getItem(int position) {
                return memoVoArrayList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return memoVoArrayList.get(position).getSavedTime();
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                if (convertView != null) {
                    return convertView;
                }
                MemoVo vo = memoVoArrayList.get(position);
                TextView titleTv = new TextView(getApplicationContext());
                titleTv.setText(vo.getTitle() + "(" + vo.getTextSavedTime() + ")");
                titleTv.setTextSize(20);
                titleTv.setClickable(true);
                titleTv.setTextColor(Color.BLACK);
                titleTv.setVisibility(View.VISIBLE);
                titleTv.setPadding(20,20,20,20);
                Log.d("MY", titleTv.getText().toString());

                // 리스너
                titleTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MemoVo vo = (MemoVo)getItem(position);
                        Intent intent = new Intent(Ex03Activity.this, Ex04Activity.class);
                        intent.putExtra("title", vo.getTitle());
                        intent.putExtra("content", vo.getContent());
                        intent.putExtra("time", vo.getTextSavedTime());
                        startActivity(intent);
                    }
                });
                return titleTv;
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.save_btn:
                Intent intent = new Intent();
                intent.setClass(Ex03Activity.this, Ex02Activity.class);
                startActivity(intent);
                break;

            case R.id.delete_btn:
                SharedPreferences.Editor editor;
                editor = preferences.edit();
                editor.clear(); // 전체 삭제
                editor.apply();
                break;
        }
    }
}
