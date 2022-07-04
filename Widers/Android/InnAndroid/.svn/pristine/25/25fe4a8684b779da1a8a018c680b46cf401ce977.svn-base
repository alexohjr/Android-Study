package com.and.andelectronics.common.view.alert.theme;

import android.widget.BaseAdapter;
import java.util.ArrayList;
import android.R.integer;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.and.andelectronics.R;
import com.and.andelectronics.infrastructure.model.Theme;

/**
 * Created by Won on 2016-06-02.
 */
public class ThemeListAdapter extends BaseAdapter {

    private class ViewHolder {
        private TextView themeNameTextView ;
        private CheckBox themeSelectCheckBox;
    }

    private ViewHolder holder;
    private Context context;
    private boolean[] isChecked;
    public ArrayList<Theme> themeItems;;

    public ThemeListAdapter(Context context, ArrayList<Theme> themes) {
        super();
        this.context = context;
        //this.isChecked = new boolean[themeItems.size()];
        this.themeItems = themes;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return themeItems.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return themeItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.comm_listitem_theme, null);
            holder.themeNameTextView = (TextView) convertView
                    .findViewById(R.id.themeNameTextView);
            holder.themeSelectCheckBox = (CheckBox) convertView
                    .findViewById(R.id.themeSelectCheckBox);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Theme theme = this.themeItems.get(position);
        holder.themeNameTextView.setText(theme.getThemeName());
//        holder.themeSelectCheckBox.setClickable(false);
//        holder.themeSelectCheckBox.setFocusable(false);
//        holder.themeSelectCheckBox.setChecked(isChecked[position]);
        return convertView;
    }

    public void setChecked(int position) {
        isChecked[position] = !isChecked[position];
    }

    public ArrayList<Integer> getChecked() {
        int tempSize = isChecked.length;
        ArrayList<Integer> mArrayList = new ArrayList<Integer>();
        for (int i = 0; i < tempSize; i++) {
            if (isChecked[i]) {
                mArrayList.add(i);
            }
        }
        return mArrayList;
    }

    public ArrayList<Theme> getSelectedData() {
        int tempSize = isChecked.length;
        ArrayList<Theme> themes = new ArrayList<Theme>();
            for (int i = 0; i < tempSize; i++) {
            if (isChecked[i]) {
                themes.add(themeItems.get(i));
            }
        }
        return themes;
    }
}