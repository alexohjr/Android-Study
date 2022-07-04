package com.example.pc.day32;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Ex01Activity extends FragmentActivity implements View.OnClickListener {

    ViewPager mPager;
    Button btn_page1, btn_page2, btn_page3, btn_page4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);

        mPager = findViewById(R.id.pager);
        mPager.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
        mPager.setCurrentItem(PageInfo.FRAGMENT_PAGE1);

        btn_page1 = findViewById(R.id.btn_page1);
        btn_page2 = findViewById(R.id.btn_page2);
        btn_page3 = findViewById(R.id.btn_page3);
        btn_page4 = findViewById(R.id.btn_page4);

        btn_page1.setOnClickListener(this);
        btn_page2.setOnClickListener(this);
        btn_page3.setOnClickListener(this);
        btn_page4.setOnClickListener(this);

        // page_selector.xml 의 내용을 참고하여
        // selected = true 일 때의 이미지 띄움
        btn_page1.setSelected(true);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                // 사용자가 drag 하는 순간 호출
            }

            @Override
            public void onPageSelected(int i) {
                // i번째 페이지가 선택되었을 때 호출됨
                // 버튼들의 selected 상태를 모두 false
                btn_page1.setSelected(false);
                btn_page2.setSelected(false);
                btn_page3.setSelected(false);
                btn_page4.setSelected(false);

                switch(i) {
                    case PageInfo.FRAGMENT_PAGE1:
                        btn_page1.setSelected(true);
                        break;

                    case PageInfo.FRAGMENT_PAGE2:
                        btn_page2.setSelected(true);
                        break;

                    case PageInfo.FRAGMENT_PAGE3:
                        btn_page3.setSelected(true);
                        break;

                    case PageInfo.FRAGMENT_PAGE4:
                        btn_page4.setSelected(true);
                        Toast.makeText(Ex01Activity.this, "4번 페이지 호출", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                // 스크롤 상태가 변경되었을 때
                // 다른 화면이 일정 면적을 차지했을 때, 사용자가 스크롤 상태를 변경했을 때
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_page1:
                mPager.setCurrentItem(PageInfo.FRAGMENT_PAGE1);
                break;

            case R.id.btn_page2:
                mPager.setCurrentItem(PageInfo.FRAGMENT_PAGE2);
                break;

            case R.id.btn_page3:
                mPager.setCurrentItem(PageInfo.FRAGMENT_PAGE3);
                break;

            case R.id.btn_page4:
                Toast.makeText(this, "4번 페이지", Toast.LENGTH_SHORT).show();
                mPager.setCurrentItem(PageInfo.FRAGMENT_PAGE4);
                break;
        }
    }
}
