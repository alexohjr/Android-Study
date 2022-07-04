package com.and.andelectronics.common.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.and.andelectronics.R;
import com.and.andelectronics.common.control.RangeSeekbar;
import com.and.andelectronics.common.view.alert.theme.ThemeAlertView;
import com.and.andelectronics.view.widget.MainActionBar;

/**
 * Created by Won on 2016-04-03.
 */
public class ViewSettingActivity extends Activity implements View.OnClickListener{

    private MainActionBar mainActionBar;
    private Context context;
    private RangeSeekbar rangeSeekbar;
    private RadioGroup sortRadioGroup;
    private TextView themeSelectTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewsetting);

        this.context = this;
        this.mainActionBar = new MainActionBar(this, null);
        this.mainActionBar = (MainActionBar)findViewById(R.id.mainActionBar);
        TextView title = (TextView)this.mainActionBar.findViewById(R.id.TV_actionTitle);
        title.setText(R.string.viewSetting);

        this.themeSelectTextView = (TextView)findViewById(R.id.themeSelectTextView);
        this.themeSelectTextView.setOnClickListener(this);

        this.rangeSeekbar = (RangeSeekbar)findViewById(R.id.rangeSeekbar);

        this.rangeSeekbar.setOnCursorChangeListener(new RangeSeekbar.OnCursorChangeListener() {
            @Override
            public void onLeftCursorChanged(int location, String textMark) {
                Log.e("onLeftCursorChanged", location + textMark);
            }

            @Override
            public void onRightCursorChanged(int location, String textMark) {
                Log.v("onRightCursorChanged", location + textMark);
            }
        });

        this.sortRadioGroup = (RadioGroup)findViewById(R.id.sortRadioGroup);
        this.sortRadioGroup.check(R.id.distanceRadioButton);//거리순 초기화

        this.sortRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.distanceRadioButton:
                    Toast.makeText(context, "distanceRadioButton", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.dayuseHighRadioButton:
                    Toast.makeText(context, "dayuseHighRadioButton", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.dayuseLowRadioButton:
                    Toast.makeText(context, "dayuseLowRadioButton", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.overnightHighRadioButton:
                    Toast.makeText(context, "overnightHighRadioButton", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.overnightLowRadioButton:
                    Toast.makeText(context, "overnightLowRadioButton", Toast.LENGTH_SHORT).show();
                    break;
            }
            }
        });

        }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.themeSelectTextView:
                showThemeAlertView();
                break;
        }
    }

    private void showThemeAlertView(){
        ThemeAlertView themeAlertView = new ThemeAlertView(context);
        themeAlertView.show();

    }
}
