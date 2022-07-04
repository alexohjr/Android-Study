package com.example.pc.day40;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    /*
        < 필수 오버라이드 >
        1. onCreateViewHolder() : 새 아이템 View를 생성, LayoutManager에 의해 호출됨
        2. onBindViewHolder() : 아이템 view를 다른 아이템 view로 대체, LayoutManager에 의해 호출됨
        3. getItemCount() : 전체 아이템 개수

        < 내부 클래스 >
        - 아이템 View 생성할 때 사용
     */

    private String[] mDataset; // RecyclerView에 나열할 텍스트 들
    private int[] mBackgrounds = {R.drawable._1, R.drawable._2, R.drawable._3}; // RecyclerView에 나열할 배경의 id들

    // 생성자
    public MyAdapter(String[] dSet) {
        mDataset = dSet;
    }

    // 내부 클래스
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // 각 아이템들을 객체화 할 때 사용
        public Button button;
        public MyViewHolder(Button b) {
            super(b);
            button = b;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // 새 View 생성
        Button b = (Button) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        // viewGroup : 상위 뷰(레이아웃)

        // 뷰 홀더에 새 view 등록 + 리턴
        return new MyViewHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Button b = myViewHolder.button;
        b.setText(mDataset[i]);
        b.setBackgroundResource(mBackgrounds[i % 3]);
        b.setOnTouchListener(listener);
        b.getBackground().setAlpha(80);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    View.OnTouchListener listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                v.getBackground().setAlpha(255); // 0(투명) ~ 255(불투명)
            } else if(event.getAction() == MotionEvent.ACTION_UP) {
                v.getBackground().setAlpha(80);
            }
            return true;
        }
    };
}
