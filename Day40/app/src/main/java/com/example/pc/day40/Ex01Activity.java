package com.example.pc.day40;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/*
    < RecyclerView >
    - ListView의 최적화 버전
    - 일반 ListView 는 아이템이 화면에서 벗어날 경우 아이템을 삭제하고
    - 화면에 들어올 때 다시 생성 -> 아이템이 많아질 경우 메모리가 많이 소모
    - RecyclerView는 아이템을 한 번 생성하면 이를 보관해뒀다가 화면에 출력

    < 사용방법 >
    1. gradle.build(app)에 라이브러리 추가
    2. RecyclerView는 Adapter와 함께 사용
 */

public class Ex01Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter; // (RecyclerView.Adapter)
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);

        String[] itemList = {"Java", "Android", "JSP", " Python", "C", "C++", "C#"};
        recyclerView = findViewById(R.id.recyler_view);

        // 고정 값
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new MyAdapter(itemList);
        recyclerView.setAdapter(myAdapter);
    }
}
