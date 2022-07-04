package com.and.andelectronics.view.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.and.andelectronics.R;

/**
 * Created by Won on 2016-01-02.
 */
public class HomeActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button mySurroundButton = (Button)findViewById(R.id.mySurroundButton);
        mySurroundButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mySurroundButton:
                Intent intent = new Intent(HomeActivity.this, MainTabFragmentActivity.class);
                startActivity(intent);
                break;

        }
    }
}