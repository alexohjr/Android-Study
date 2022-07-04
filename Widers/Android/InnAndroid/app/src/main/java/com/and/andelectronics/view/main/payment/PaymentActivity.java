package com.and.andelectronics.view.main.payment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.and.andelectronics.R;
import com.and.andelectronics.common.ActivityManager;
import com.and.andelectronics.infrastructure.model.lodgement.ReserveRequestData;
import com.and.andelectronics.infrastructure.model.lodgement.Room;
import com.and.andelectronics.view.main.enterance.EnteranceConfigActivity;

/**
 * Created by 원성민 on 2017-06-28.
 */

public class PaymentActivity extends Activity
{

    private Context context;
    private Room room;
    private ReserveRequestData reserveRequestData;

    private ActivityManager activityManager = ActivityManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        this.context = this;
        this.activityManager.addActivity(this);
        this.room = (Room)this.getIntent().getExtras().getSerializable("obj");
        this.reserveRequestData = (ReserveRequestData)this.getIntent().getExtras().getSerializable("reserve");

        Handler startHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                finish();

                Room mData = room;

                Bundle extras = new Bundle();
                Intent intent = new Intent(context, EnteranceConfigActivity.class);
                extras.putSerializable("obj", mData);
                extras.putSerializable("reserve", reserveRequestData);
                intent.putExtras(extras);
                startActivityForResult(intent, 99);
                //startActivity(new Intent(PaymentActivity.this, EnteranceConfigActivity.class));
            };
        };
        startHandler.sendEmptyMessageDelayed(0, 1000); // 1초딜레이
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.activityManager.removeActivity(this);
    }
}
