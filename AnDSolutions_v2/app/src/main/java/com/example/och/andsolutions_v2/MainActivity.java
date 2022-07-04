package com.example.och.andsolutions_v2;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {



    // 하단 메뉴
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Home homeMenuFrag = new Home();
    private SearchPage searchMenuFrag = new SearchPage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 바텀 네비게이션
        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom);

        // 첫 화면 지정
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, homeMenuFrag).commitAllowingStateLoss();

        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.menu_home: {
                        transaction.replace(R.id.frame_layout, homeMenuFrag).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.menu_search: {
                        transaction.replace(R.id.frame_layout, searchMenuFrag).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.menu_reserve: {
                        transaction.replace(R.id.frame_layout, homeMenuFrag).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.menu_quick: {
                        transaction.replace(R.id.frame_layout, searchMenuFrag).commitAllowingStateLoss();
                        break;
                    }
                }
                return true;
            }
        });


    }

}

