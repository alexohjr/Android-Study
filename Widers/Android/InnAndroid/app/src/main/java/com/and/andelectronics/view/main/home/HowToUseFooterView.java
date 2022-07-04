package com.and.andelectronics.view.main.home;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.and.andelectronics.R;

/**
 * Created by yowonsm on 2018-06-10.
 */

public class HowToUseFooterView extends LinearLayout{

    private Context context;
    private LinearLayout linearLayout;

    public HowToUseFooterView(Context context) {
        super(context);
    }
    public HowToUseFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.linearLayout = (LinearLayout)inflater.inflate(R.layout.footerview_home_howtouse,  null);
        addView(this.linearLayout);

    }
}
