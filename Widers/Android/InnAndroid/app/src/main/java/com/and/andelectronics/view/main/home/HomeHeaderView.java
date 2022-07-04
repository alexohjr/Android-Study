package com.and.andelectronics.view.main.home;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.and.andelectronics.R;

/**
 * Created by Won on 2016-03-31.
 */
public class HomeHeaderView extends LinearLayout {

    private Context context;
    private LinearLayout linearLayout;

    public HomeHeaderView(Context context) {
        super(context);
    }
    public HomeHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.linearLayout = (LinearLayout)inflater.inflate(R.layout.header_home,  null);
        addView(this.linearLayout);

    }
}
