package com.and.andelectronics.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.and.andelectronics.R;
import com.and.andelectronics.service.BackService;
import com.and.andelectronics.view.main.ect.EtcFragment;
import com.and.andelectronics.view.main.home.HomeFragment;
import com.and.andelectronics.view.main.mypage.MyPageFragment;
import com.and.andelectronics.view.main.pleasure.RecommendFragment;
import com.and.andelectronics.view.main.roomservice.RoomServiceFragment;
import com.and.andelectronics.view.main.search.SearchFragment;
import com.and.andelectronics.view.main.surround.SurroundFragment;
import com.and.andelectronics.view.widget.MainTabWidget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Won on 2016-01-02.
 */
public class MainTabFragmentActivity extends AppCompatActivity implements View.OnClickListener{

    private Context context;
    private Map<Integer, Class> fragmentMap = new HashMap<Integer, Class>();
    protected List<Button> tabButtonList;
    protected List<Integer> tabSelectedImageList;
    protected List<Integer> tabUnSelectedImageList;

    private Integer currentTabButtonId;
    //region Controls
    private Button homeTabButton;
    private Button searchTabButton;
    private Button recommendTabButton;
    private Button roomServiceTabButton;
    private Button mypageTabButton;

    private MainTabWidget mainTabWidget;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maintab);

        this.context = this;
        this.bindView();
        this.bindEvent();
        this.initTabButtonList();

        this.fragmentMap.put(R.id.homeTabButton, HomeFragment.class);
        this.fragmentMap.put(R.id.searchTabButton, SearchFragment.class);
        this.fragmentMap.put(R.id.recommendTabButton, RecommendFragment.class);
        this.fragmentMap.put(R.id.roomServiceTabButton, RoomServiceFragment.class);
        this.fragmentMap.put(R.id.mypageTabButton, MyPageFragment.class);

        //내주변으로 맨처음 왔을 때 내주변탭이 떠야 함
        this.tabClick(this.homeTabButton);

        //startBackService(this); //백그라운드 서비스 실행

    }

    private void bindView(){
        this.mainTabWidget = new MainTabWidget(this.context, null);
        this.mainTabWidget = (MainTabWidget) findViewById(R.id.mainTabWidget);
        this.homeTabButton = (Button)mainTabWidget.findViewById(R.id.homeTabButton);
        this.searchTabButton = (Button)mainTabWidget.findViewById(R.id.searchTabButton);
        this.recommendTabButton = (Button)mainTabWidget.findViewById(R.id.recommendTabButton);
        this.roomServiceTabButton = (Button)mainTabWidget.findViewById(R.id.roomServiceTabButton);
        this.mypageTabButton = (Button)mainTabWidget.findViewById(R.id.mypageTabButton);
    }

    private void bindEvent(){
        this.homeTabButton.setOnClickListener(this);
        this.searchTabButton.setOnClickListener(this);
        this.recommendTabButton.setOnClickListener(this);
        this.roomServiceTabButton.setOnClickListener(this);
        this.mypageTabButton.setOnClickListener(this);
    }

    private void initTabButtonList(){
        this.tabButtonList = new ArrayList<Button>();
        this.tabButtonList.add(this.homeTabButton);
        this.tabButtonList.add(this.searchTabButton);
        this.tabButtonList.add(this.recommendTabButton);
        this.tabButtonList.add(this.roomServiceTabButton);
        this.tabButtonList.add(this.mypageTabButton);

        this.tabSelectedImageList = new ArrayList<Integer>();
        this.tabSelectedImageList.add(R.mipmap.btn_home_on);
        this.tabSelectedImageList.add(R.mipmap.btn_search_on);
        this.tabSelectedImageList.add(R.mipmap.btn_recommend_on);
        this.tabSelectedImageList.add(R.mipmap.btn_roomservice_on);
        this.tabSelectedImageList.add(R.mipmap.btn_mypage_on);

        this.tabUnSelectedImageList = new ArrayList<Integer>();
        this.tabUnSelectedImageList.add(R.mipmap.btn_home_off);
        this.tabUnSelectedImageList.add(R.mipmap.btn_search_off);
        this.tabUnSelectedImageList.add(R.mipmap.btn_recommend_off);
        this.tabUnSelectedImageList.add(R.mipmap.btn_roomservice_off);
        this.tabUnSelectedImageList.add(R.mipmap.btn_mypage_off);


    }




    private void replaceFragment(int buttonId) {
        Class clazz = this.fragmentMap.get(buttonId);
        Fragment fragment = null;
        try {
            fragment = (Fragment)clazz.getConstructor().newInstance();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

        if(fragment == null) return;

        //fragment replace
        final FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.tabContentParentLayout, fragment);
        transaction.commit();
    }

    public void changeTabButtonState(int selectedButtonId) {
        //현재 선택된 Tab버튼의 ID를 설정
        currentTabButtonId = selectedButtonId;
        //tab button상태 설정
        for(int i=0; i< this.tabButtonList.size(); i++){
            Button button = this.tabButtonList.get(i);
            if(selectedButtonId == button.getId()){
                button.setEnabled(false);
                button.setBackgroundResource(this.tabSelectedImageList.get(i));
            }
            else{
                button.setEnabled(true);
                button.setBackgroundResource(this.tabUnSelectedImageList.get(i));
            }
        }

        //tab button상태 설정
        /*for(Button button : this.tabButtonList) {
            if(button.getId() == selectedButtonId) {
                button.setEnabled(false);
                button.setBackgroundResource(this.selectedTabButtonColor);

            }
            else {
                button.setEnabled(true);
                button.setBackgroundResource(this.deSelectedTabButtonColor);
            }
        }*/
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tab_textview:

                break;

            default:
                tabClick(v);
                break;

        }
    }

    /**
     * @func tabClick
     * @param view
     * @desc 프래그먼트 탭버튼 클릭시 호출 (탭변경)
     */
    public void tabClick(View view) {
        int buttonId = view.getId();
        this.replaceFragment(buttonId);
        this.changeTabButtonState(buttonId);
    }


    public void startBackService(Context context){
        Intent intent = new Intent(context, BackService.class);
        context.startService(intent);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            if(currentTabButtonId == R.id.homeTabButton){
                long tempTime = System.currentTimeMillis();
                long intervalTime = tempTime - backPressedTime;

                if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
                    super.onBackPressed();
                } else {
                    backPressedTime = tempTime;
                    Toast.makeText(getApplicationContext(), "한번 더 뒤로가기 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
                }

            }else{
                moveToHomeTab();
            }



            return true;
        }
        return true;
    }

    private void moveToHomeTab(){
        this.tabClick(this.homeTabButton);
    }

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

//    @Override
//    public void onBackPressed() {
//        long tempTime = System.currentTimeMillis();
//        long intervalTime = tempTime - backPressedTime;
//
//        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
//            super.onBackPressed();
//        } else {
//            backPressedTime = tempTime;
//            Toast.makeText(getApplicationContext(), "한번 더 뒤로가기 누르면 꺼버린다.", Toast.LENGTH_SHORT).show();
//        }
//    }


}
