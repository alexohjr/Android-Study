package com.example.pc.day32;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Page2Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Fragment는 Inflater를 사용함
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.activity_page2_fragment, container, false);


        ImageView iv = linearLayout.findViewById(R.id.page2_iv);
        iv.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.nike));


        Button buy_btn = linearLayout.findViewById(R.id.buy_btn);
        buy_btn.setOnClickListener(listener);

        return linearLayout;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Uri uri = Uri.parse("https://www.nike.com/kr/ko_kr/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    };
}
