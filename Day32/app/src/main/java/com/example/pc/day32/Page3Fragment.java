package com.example.pc.day32;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Page3Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Fragment는 Inflater를 사용한다.
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.activity_page3_fragment, container, false);

        Button btn5 = linearLayout.findViewById(R.id.btn5);
        Button btn6 = linearLayout.findViewById(R.id.btn6);

        btn5.setOnClickListener(listener);
        btn6.setOnClickListener(listener);

        return linearLayout;

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), "버튼 클릭됨", Toast.LENGTH_SHORT).show();
        }
    };
}
